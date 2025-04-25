package com.emr.project.docOrder.controller;

import com.emr.common.utils.CommonUtils;
import com.emr.common.utils.DateUtils;
import com.emr.common.utils.EncryptPSWUtil;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.StringUtils;
import com.emr.framework.redis.OrderRedisCache;
import com.emr.framework.redis.RedisCache;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.project.docOrder.domain.TdPaOrderItem;
import com.emr.project.docOrder.domain.vo.AllergyRecordDTO;
import com.emr.project.docOrder.domain.vo.InpatientOrderPerformDTO;
import com.emr.project.docOrder.domain.vo.InpatientOrderPerformVo;
import com.emr.project.docOrder.domain.vo.InpatientOrderResultDetailDTO;
import com.emr.project.docOrder.domain.vo.OrderDoHandleUpVo;
import com.emr.project.docOrder.domain.vo.OrderDoPerformParamVo;
import com.emr.project.docOrder.domain.vo.OrderSignCommitVo;
import com.emr.project.docOrder.domain.vo.SkinTestResuleVo;
import com.emr.project.docOrder.service.IInpatientOrderPerformService;
import com.emr.project.docOrder.service.ITdPaOrderItemService;
import com.emr.project.docOrder.service.ITdPaOrderPerformService;
import com.emr.project.docOrder.service.ITdPaOrderSignMainService;
import com.emr.project.docOrder.service.ReportConfigService;
import com.emr.project.pat.domain.Visitinfo;
import com.emr.project.pat.service.IVisitinfoService;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.service.ISysEmrConfigService;
import com.emr.project.system.service.ISysUserService;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/orderPerform"})
public class InpatientOrderPerformController extends BaseController {
   @Autowired
   private IInpatientOrderPerformService inpatientOrderPerformService;
   @Autowired
   private RedisCache redisCache;
   @Autowired
   private ISysUserService sysUserService;
   @Autowired
   private IVisitinfoService visitinfoService;
   @Autowired
   private ITdPaOrderItemService tdPaOrderItemService;
   @Autowired
   private ITdPaOrderPerformService tdPaOrderPerformService;
   @Autowired
   private ReportConfigService reportConfigService;
   @Autowired
   private ITdPaOrderSignMainService orderSignMainService;
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;
   @Autowired
   private OrderRedisCache orderRedisCache;

   @ResponseBody
   @GetMapping({"/selectInpatientOrderPerform"})
   @PreAuthorize("@ss.hasAnyPermi('docOrder:doctorder:performList')")
   public AjaxResult selectInpatientOrderPerform(InpatientOrderPerformVo param, Integer pageSize, Integer pageNum) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         param.setSign("3");
         param.setDepCode(SecurityUtils.getLoginUser().getUser().getDept().getDeptCode());
         List<InpatientOrderPerformDTO> listAll = this.inpatientOrderPerformService.selectInpatientOrderPerform(param);
         List<InpatientOrderPerformDTO> list = this.inpatientOrderPerformService.selectInpatientOrderPerformPageData(param, listAll, pageSize, pageNum);
         ajaxResult.put("rows", list);
         ajaxResult.put("total", listAll.size());
      } catch (Exception e) {
         ajaxResult = AjaxResult.error("查询医嘱执行单列表出现异常，请联系管理员!");
         this.log.error("查询医嘱执行单列表出现异常", e);
      }

      return ajaxResult;
   }

   @GetMapping({"/selectSkinTestResultByPatient"})
   @PreAuthorize("@ss.hasAnyPermi('docOrder:doctorder:performList')")
   public AjaxResult selectSkinTestResultByPatient(String admissionNo) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");
      boolean flag = true;
      if (StringUtils.isBlank(admissionNo)) {
         flag = false;
         ajaxResult = AjaxResult.error("患者住院号不能为空");
      }

      try {
         if (flag) {
            List<SkinTestResuleVo> list = this.inpatientOrderPerformService.selectSkinTestResultByPatient(admissionNo);
            ajaxResult.put("data", list);
         }
      } catch (Exception e) {
         ajaxResult = AjaxResult.error("查询医嘱执行单列表出现异常，请联系管理员!");
         this.log.error("查询医嘱执行单列表出现异常", e);
      }

      return ajaxResult;
   }

   @ResponseBody
   @PostMapping({"/updateInpatientOrderPerform"})
   @PreAuthorize("@ss.hasAnyPermi('docOrder:doctorder:doPerform')")
   public AjaxResult updateInpatientOrderPerform(@RequestBody OrderDoPerformParamVo performParamVo) {
      AjaxResult ajaxResult = AjaxResult.success("执行成功");
      List<InpatientOrderResultDetailDTO> inpatientOrderResultDetailDTOS = new ArrayList();
      List<Map<String, Object>> printList = new ArrayList();
      String deptCode = SecurityUtils.getLoginUser().getUser().getDept().getDeptCode();
      Boolean flag = true;

      try {
         if (flag && CollectionUtils.isEmpty(performParamVo.getPerformList())) {
            flag = false;
            ajaxResult = AjaxResult.error("执行单集合不能为空");
         }

         String redisDeptCode = (String)this.redisCache.getCacheObject("inpatientOrderPerforming:" + deptCode);
         if (StringUtils.isNotBlank(redisDeptCode) && redisDeptCode.equals("1")) {
            flag = false;
            ajaxResult = AjaxResult.error("当前科室正在核对医嘱，请稍后再核对");
         } else {
            this.redisCache.setCacheObject("inpatientOrderPerforming:" + deptCode, "1");
         }

         String caFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0001");
         if (flag && StringUtils.isNotBlank(caFlag) && caFlag.equals("1")) {
            List<OrderSignCommitVo> orderSignCommitVoList = performParamVo.getOrderSignCommitVoList();
            if (orderSignCommitVoList != null && orderSignCommitVoList.size() > 0) {
               for(OrderSignCommitVo orderSignCommitVo : orderSignCommitVoList) {
                  if (flag && StringUtils.isBlank(orderSignCommitVo.getEncryptedInfo())) {
                     flag = false;
                     ajaxResult = AjaxResult.error("签名加密后密文不能为空，请重新操作");
                  }

                  if (flag && StringUtils.isBlank(orderSignCommitVo.getSignCertificate())) {
                     flag = false;
                     ajaxResult = AjaxResult.error("签名证书不能为空，请重新操作");
                  }

                  if (flag && StringUtils.isBlank(orderSignCommitVo.getSignedText())) {
                     flag = false;
                     ajaxResult = AjaxResult.error("签名明文不能为空，请重新操作");
                  }
               }

               if (flag) {
                  this.orderSignMainService.addTdPaOrderOperationSignText(orderSignCommitVoList, "1");
               }
            }
         }

         if (flag) {
            List<String> standardCdList = (List)performParamVo.getPerformList().stream().filter((t) -> StringUtils.isNotBlank(t.getStandardCd())).map((t) -> t.getStandardCd()).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(standardCdList) && standardCdList.contains("5")) {
               if (performParamVo.getAllergyRecord() == null) {
                  flag = false;
                  ajaxResult = AjaxResult.error("执行单中有皮试医嘱，皮试结果不能为空！");
               } else if (performParamVo.getAllergyRecord() != null && performParamVo.getPerformList().size() > 1) {
                  flag = false;
                  ajaxResult = AjaxResult.error("含有皮试医嘱只能选择一条执行！");
               }
            }
         }

         if (flag && performParamVo.getAllergyRecord() != null) {
            if (flag && StringUtils.isBlank(performParamVo.getAllergyRecord().getSkinTestResults())) {
               flag = false;
               ajaxResult = AjaxResult.error("皮试结果不能为空");
            }

            if (flag && performParamVo.getAllergyRecord().getOperatorDate() == null) {
               flag = false;
               ajaxResult = AjaxResult.error("皮试结果日期不能为空");
            }

            if (flag && StringUtils.isBlank(performParamVo.getAllergyRecord().getSkinTestResultsLot())) {
               flag = false;
               ajaxResult = AjaxResult.error("皮试结果批号不能为空");
            }

            if (flag && StringUtils.isBlank(performParamVo.getAllergyRecord().getUserCode())) {
               flag = false;
               ajaxResult = AjaxResult.error("皮试结果工号不能为空");
            }

            if (flag && StringUtils.isBlank(performParamVo.getAllergyRecord().getPassWord())) {
               flag = false;
               ajaxResult = AjaxResult.error("皮试结果账号密码不能为空");
            }
         }

         if (flag && performParamVo.getAllergyRecord() != null) {
            AllergyRecordDTO recordDTO = performParamVo.getAllergyRecord();
            SysUser skinTestUser = this.sysUserService.selectUserByUserName(recordDTO.getUserCode());
            String passWord = EncryptPSWUtil.createEncryptPSW(recordDTO.getPassWord());
            String skinTestPassword = skinTestUser.getPassword();
            if (!skinTestPassword.equals(passWord)) {
               flag = false;
               ajaxResult = AjaxResult.error("账号密码有误");
            }
         }

         if (flag) {
            Map<String, List<InpatientOrderPerformDTO>> performListMap = new HashMap();

            for(InpatientOrderPerformDTO inpatientOrderPerformDTO : performParamVo.getPerformList()) {
               if (performListMap.containsKey(inpatientOrderPerformDTO.getPerformListNo() + inpatientOrderPerformDTO.getPerformListSortNumber())) {
                  List<InpatientOrderPerformDTO> inpatientOrderPerformDTOList = (List)performListMap.get(inpatientOrderPerformDTO.getPerformListNo() + inpatientOrderPerformDTO.getPerformListSortNumber());
                  inpatientOrderPerformDTOList.add(inpatientOrderPerformDTO);
               } else {
                  List<InpatientOrderPerformDTO> inpatientOrderPerformDTOList = new ArrayList();
                  inpatientOrderPerformDTOList.add(inpatientOrderPerformDTO);
                  performListMap.put(inpatientOrderPerformDTO.getPerformListNo() + inpatientOrderPerformDTO.getPerformListSortNumber(), inpatientOrderPerformDTOList);
               }
            }

            List<String> patientIdList = (List)performParamVo.getPerformList().stream().filter((t) -> StringUtils.isNotBlank(t.getAdmissionNo())).map((t) -> t.getAdmissionNo()).distinct().collect(Collectors.toList());
            List<Visitinfo> visitinfoList = this.visitinfoService.selectVistinfoListByPatList(patientIdList);
            List<Visitinfo> patientInHospitalList = (List)visitinfoList.stream().filter((t) -> t.getOutTime() == null).collect(Collectors.toList());
            Map<String, Visitinfo> patientInHospitalMap = (Map)patientInHospitalList.stream().collect(Collectors.toMap((t) -> t.getInpNo(), Function.identity()));

            for(String key : performListMap.keySet()) {
               Boolean isAllowFlag = true;
               StringBuffer msgSb = new StringBuffer();
               List<InpatientOrderPerformDTO> inpatientOrderPerformDTOList = (List)performListMap.get(key);
               InpatientOrderPerformDTO dto = (InpatientOrderPerformDTO)inpatientOrderPerformDTOList.get(0);
               String admissionNo = dto.getAdmissionNo();
               Visitinfo visitinfo = (Visitinfo)patientInHospitalMap.get(admissionNo);
               Boolean patientInHospitalFlag = visitinfo != null && visitinfo.getOutTime() == null;
               if (!patientInHospitalFlag) {
                  msgSb.append("患者已经出院，不能执行他的医嘱");
                  isAllowFlag = false;
               }

               TdPaOrderItem orderItem = this.tdPaOrderItemService.selectTdPaOrderItemById(dto.getOrderNo());
               Date perfromTime = DateUtils.parseDate(dto.getPerformTime());
               boolean mark = CommonUtils.getOrderStatusIsEffective(orderItem, 7, perfromTime);
               if (!mark) {
                  msgSb.append("患者医当前不是可以处理的状态，不能重复进行，请重新提取数据进行处理!");
                  isAllowFlag = false;
               }

               int performStatus = this.tdPaOrderPerformService.getPerformStatus(dto.getPerformListNo(), dto.getPerformListSortNumber());
               if (performStatus != 0) {
                  msgSb.append("该执行单已执行,请刷新页面");
                  isAllowFlag = false;
               }

               String orderNoHandlingKey = "inpatientOrderNoHandling:" + dto.getOrderNo();
               String orderNoHandling = (String)this.orderRedisCache.getCacheObject(orderNoHandlingKey);
               if (StringUtils.isNotBlank(orderNoHandling)) {
                  String orderNoHandlingMsg = CommonUtils.getOrderNoHandlingMsg(Integer.valueOf(orderNoHandling));
                  isAllowFlag = false;
                  msgSb.append(orderNoHandlingMsg);
               } else {
                  this.orderRedisCache.setCacheObject(orderNoHandlingKey, 3, 300, TimeUnit.SECONDS);
               }

               List<OrderDoHandleUpVo> handleUplist = new ArrayList();
               if (isAllowFlag) {
                  try {
                     this.inpatientOrderPerformService.updateInpatientOrderPerform(inpatientOrderPerformDTOList, performParamVo.getAllergyRecord(), visitinfo, printList, handleUplist);
                  } catch (Exception e) {
                     msgSb.append("医嘱执行失败");
                     this.log.error("医嘱执行失败，患者姓名：" + dto.getPatientName() + " ； orderNo：" + dto.getOrderNo() + " 。", e);
                  }
               }

               this.orderRedisCache.deleteObject(orderNoHandlingKey);
               if (StringUtils.isNotBlank(msgSb.toString())) {
                  for(InpatientOrderPerformDTO inpatientOrderPerformDTO : inpatientOrderPerformDTOList) {
                     InpatientOrderResultDetailDTO inpatientOrderResultDetailDTO = new InpatientOrderResultDetailDTO();
                     inpatientOrderResultDetailDTO.setOrderNo(inpatientOrderPerformDTO.getOrderNo());
                     inpatientOrderResultDetailDTO.setOrderGroupNo(inpatientOrderPerformDTO.getOrderGroupNo());
                     inpatientOrderResultDetailDTO.setAdmissionNo(inpatientOrderPerformDTO.getAdmissionNo());
                     inpatientOrderResultDetailDTO.setCaseNo(inpatientOrderPerformDTO.getCaseNo());
                     inpatientOrderResultDetailDTO.setChargeName(inpatientOrderPerformDTO.getChargeName());
                     inpatientOrderResultDetailDTO.setPatientName(inpatientOrderPerformDTO.getPatientName());
                     inpatientOrderResultDetailDTO.setErroMsg(msgSb.toString());
                     inpatientOrderResultDetailDTO.setBedId(inpatientOrderPerformDTO.getBedid());
                     inpatientOrderResultDetailDTO.setPrice(inpatientOrderPerformDTO.getPrice());
                     inpatientOrderResultDetailDTO.setTotal(inpatientOrderPerformDTO.getOrderTotal());
                     inpatientOrderResultDetailDTO.setOrderTotalDose(inpatientOrderPerformDTO.getOrderTotalDose());
                     inpatientOrderResultDetailDTOS.add(inpatientOrderResultDetailDTO);
                  }
               }

               if (!handleUplist.isEmpty()) {
                  for(InpatientOrderPerformDTO inpatientOrderPerformDTO : inpatientOrderPerformDTOList) {
                     InpatientOrderResultDetailDTO inpatientOrderResultDetailDTO = new InpatientOrderResultDetailDTO();
                     inpatientOrderResultDetailDTO.setOrderNo(inpatientOrderPerformDTO.getOrderNo());
                     inpatientOrderResultDetailDTO.setOrderGroupNo(inpatientOrderPerformDTO.getOrderGroupNo());
                     inpatientOrderResultDetailDTO.setAdmissionNo(inpatientOrderPerformDTO.getAdmissionNo());
                     inpatientOrderResultDetailDTO.setCaseNo(inpatientOrderPerformDTO.getCaseNo());
                     inpatientOrderResultDetailDTO.setChargeName(inpatientOrderPerformDTO.getChargeName());
                     inpatientOrderResultDetailDTO.setPatientName(inpatientOrderPerformDTO.getPatientName());
                     inpatientOrderResultDetailDTO.setErroMsg(((OrderDoHandleUpVo)handleUplist.get(0)).getMsg().toString());
                     inpatientOrderResultDetailDTO.setBedId(inpatientOrderPerformDTO.getBedid());
                     inpatientOrderResultDetailDTO.setPrice(inpatientOrderPerformDTO.getPrice());
                     inpatientOrderResultDetailDTO.setTotal(inpatientOrderPerformDTO.getOrderTotal());
                     inpatientOrderResultDetailDTO.setOrderTotalDose(inpatientOrderPerformDTO.getOrderTotalDose());
                     inpatientOrderResultDetailDTOS.add(inpatientOrderResultDetailDTO);
                  }
               }
            }

            List<Map<String, List<Map<String, Object>>>> mapList1 = new ArrayList();
            this.reportConfigService.groupByExecutorDpt(mapList1, printList);
            ajaxResult.put("printList", mapList1);
            ajaxResult.put("failResult", inpatientOrderResultDetailDTOS);
         }

         this.redisCache.deleteObject("inpatientOrderPerforming:" + deptCode);
      } catch (Exception e) {
         this.redisCache.deleteObject("inpatientOrderPerforming:" + deptCode);
         ajaxResult = AjaxResult.error("执行出现异常，请联系管理员");
         this.log.error("执行出现异常：", e);
      }

      return ajaxResult;
   }
}
