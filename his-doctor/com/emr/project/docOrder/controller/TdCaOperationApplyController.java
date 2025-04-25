package com.emr.project.docOrder.controller;

import com.emr.common.constant.CommonConstants;
import com.emr.common.utils.DateUtils;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.StringUtils;
import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.redis.RedisCache;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.common.service.ICommonService;
import com.emr.project.docOrder.domain.TdCaOperationApply;
import com.emr.project.docOrder.domain.TdPaItemDocQuery;
import com.emr.project.docOrder.domain.TdPaOrderItem;
import com.emr.project.docOrder.domain.vo.OpeAuthorityVo;
import com.emr.project.docOrder.domain.vo.OrderCommitVo;
import com.emr.project.docOrder.domain.vo.OrderStopSignVo;
import com.emr.project.docOrder.domain.vo.TdCaOperWorkloadVo;
import com.emr.project.docOrder.domain.vo.TdCaOperationApplyVo;
import com.emr.project.docOrder.service.ITdCaOperationApplyService;
import com.emr.project.docOrder.service.ITdPaItemDocQueryService;
import com.emr.project.docOrder.service.ITdPaOrderItemService;
import com.emr.project.emr.domain.Index;
import com.emr.project.emr.domain.vo.IndexVo;
import com.emr.project.emr.service.IIndexService;
import com.emr.project.emr.service.ISubfileIndexService;
import com.emr.project.his.service.IHisSyncService;
import com.emr.project.pat.domain.DiagInfo;
import com.emr.project.pat.domain.Visitinfo;
import com.emr.project.pat.domain.vo.VisitinfoPersonalVo;
import com.emr.project.pat.domain.vo.VisitinfoVo;
import com.emr.project.pat.service.IDiagInfoService;
import com.emr.project.pat.service.IVisitinfoService;
import com.emr.project.sys.domain.SysOpeIcd;
import com.emr.project.sys.service.ISysOpeIcdService;
import com.emr.project.system.domain.BasEmployee;
import com.emr.project.system.domain.SysDept;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.service.ISysEmrConfigService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/ope/apply"})
public class TdCaOperationApplyController extends BaseController {
   @Autowired
   private ITdCaOperationApplyService tdCaOperationApplyService;
   @Autowired
   private IHisSyncService hisSyncService;
   @Autowired
   private RedisCache redisCache;
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;
   @Autowired
   private IVisitinfoService visitinfoService;
   @Autowired
   private ITdPaOrderItemService tdPaOrderItemService;
   @Autowired
   private IIndexService indexService;
   @Autowired
   private ICommonService commonService;
   @Autowired
   private ITdPaItemDocQueryService tdPaItemDocQueryService;
   @Autowired
   private IDiagInfoService diagInfoService;
   @Autowired
   private ISubfileIndexService subfileIndexService;
   @Autowired
   private ISysOpeIcdService sysOpeIcdService;

   @PreAuthorize("@ss.hasAnyPermi('ope:apply:list,mrhp:hp:mrHpDetail,pat:visitinfo:mrhp')")
   @GetMapping({"/list"})
   public TableDataInfo list(TdCaOperationApplyVo tdCaOperationApply) {
      new TableDataInfo();

      TableDataInfo tableDataInfo;
      try {
         if (tdCaOperationApply != null && !StringUtils.isEmpty(tdCaOperationApply.getPatientId())) {
            this.startPage();
            List<TdCaOperationApplyVo> list = this.tdCaOperationApplyService.selectTdCaOperationApplyList(tdCaOperationApply);
            tableDataInfo = this.getDataTable(list);
         } else {
            tableDataInfo = new TableDataInfo(500, "患者id不能为空");
         }

         Map<String, Object> map = new HashMap();
         TdPaItemDocQuery tdPaItemDocQuery = new TdPaItemDocQuery();
         tdPaItemDocQuery.setDocCd(SecurityUtils.getLoginUser().getUser().getBasEmployee().getEmplNumber());
         tdPaItemDocQuery.setOrderFlag("9");
         List<TdPaItemDocQuery> queryList = this.tdPaItemDocQueryService.selectTdPaItemDocQueryList(tdPaItemDocQuery);
         map.put("icdFlag", queryList != null && !queryList.isEmpty() ? ((TdPaItemDocQuery)queryList.get(0)).getQueryStatus() : "0");
         tdPaItemDocQuery.setOrderFlag("13");
         List<TdPaItemDocQuery> list = this.tdPaItemDocQueryService.selectTdPaItemDocQueryList(tdPaItemDocQuery);
         map.put("opeFlag", list != null && !list.isEmpty() ? ((TdPaItemDocQuery)list.get(0)).getQueryStatus() : "0");
         DiagInfo diagInfo = new DiagInfo();
         diagInfo.setPatientId(tdCaOperationApply.getPatientId());
         diagInfo.setDiagClassCd("01");
         diagInfo.setDiagTypeCd("02");
         DiagInfo diag = this.diagInfoService.selectDiagInfo(diagInfo);
         if (diag != null) {
            map.put("diagInfo", diag);
         }

         String sqtlFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0091");
         map.put("sqtlFlag", sqtlFlag);
         String ssjbFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("009101");
         map.put("ssjbFlag", ssjbFlag);
         String sqtlCode = this.sysEmrConfigService.selectSysEmrConfigByKey("0092");
         map.put("sqtlCode", sqtlCode);
         String opeOrderFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0082");
         map.put("opeOrderFlag", opeOrderFlag);
         tableDataInfo.setObject(map);
      } catch (Exception e) {
         this.log.error("查询手术申请单列表出现异常", e);
         tableDataInfo = new TableDataInfo(500, "查询手术申请单列表出现异常");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasPermi('ope:apply:query')")
   @GetMapping({"/{applyFormNo}"})
   public AjaxResult getInfo(@PathVariable("applyFormNo") String applyFormNo) {
      return AjaxResult.success((Object)this.tdCaOperationApplyService.selectTdCaOperationApplyById(applyFormNo));
   }

   @PreAuthorize("@ss.hasAnyPermi('ope:apply:add,ope:apply:list')")
   @Log(
      title = "手术申请",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody TdCaOperationApplyVo tdCaOperationApply) throws Exception {
      AjaxResult ajaxResult = AjaxResult.success("保存成功");

      try {
         ajaxResult = this.verify(tdCaOperationApply);
         Boolean flag = ajaxResult.get("code").equals(200);
         SysUser sysUser = SecurityUtils.getLoginUser().getUser();
         VisitinfoVo vparam = new VisitinfoVo();
         vparam.setPatientId(tdCaOperationApply.getPatientId());
         Visitinfo visitinfo = flag ? this.visitinfoService.selectPatientById(vparam) : null;
         if (flag && visitinfo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("查询不到患者信息，请刷新页面再操作");
         }

         if (flag && !visitinfo.getInpNo().equals(tdCaOperationApply.getPatientId())) {
            flag = false;
            ajaxResult = AjaxResult.error("患者信息不正确，请刷新页面再操作");
         }

         boolean userHasPatFlag = flag ? this.commonService.userHasPat(visitinfo, sysUser) : flag;
         if (flag && !userHasPatFlag) {
            flag = false;
            ajaxResult = AjaxResult.error("只有患者所在科医生或者有临时授权的医生才能开立医嘱！");
         }

         if (flag) {
            String patientSaveOrderFlag = (String)this.redisCache.getCacheObject("patient_save_order:" + tdCaOperationApply.getPatientId());
            if (StringUtils.isNotBlank(patientSaveOrderFlag) && patientSaveOrderFlag.equals("1")) {
               flag = false;
               ajaxResult = AjaxResult.error("当前患者有正在保存医嘱的操作，请稍后再保存");
            } else {
               this.redisCache.setCacheObject("patient_save_order:" + tdCaOperationApply.getPatientId(), "1");
            }
         }

         String sqtlFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0091");
         String sqtlCode = this.sysEmrConfigService.selectSysEmrConfigByKey("0092");
         String ssjbCode = this.sysEmrConfigService.selectSysEmrConfigByKey("009101");
         if ("1".equals(sqtlFlag) && "15".equals(sqtlCode) && "2".equals(tdCaOperationApply.getOperTypeCd())) {
            if (tdCaOperationApply.getMrFileList() != null && tdCaOperationApply.getMrFileList().size() > 0) {
               List<IndexVo> indexVoListSqtl = tdCaOperationApply.getMrFileList();
               List<IndexVo> indexVoListSqtlRes = (List)indexVoListSqtl.stream().filter((s) -> s.getAgiRuleId().equals(CommonConstants.AGI_RULE_ID.AGI_RULE_ID_SQTL.toString())).collect(Collectors.toList());
               if (indexVoListSqtlRes == null || indexVoListSqtlRes.size() <= 0) {
                  flag = false;
                  ajaxResult = AjaxResult.error("当前患者还未书写术前讨论记录，请书写");
               }

               List<IndexVo> indexVoListSszztysRes = (List)indexVoListSqtl.stream().filter((s) -> s.getAgiRuleId().equals(CommonConstants.AGI_RULE_ID.AGI_RULE_ID_SSZZTYS.toString())).collect(Collectors.toList());
               if (indexVoListSszztysRes == null || indexVoListSszztysRes.size() <= 0) {
                  flag = false;
                  ajaxResult = AjaxResult.error("当前患者还未书写术手术知情同意书，请书写");
               }
            }
         } else if ("2".equals(tdCaOperationApply.getOperTypeCd()) && "3".equals(sqtlFlag) && StringUtils.isNotBlank(tdCaOperationApply.getOperLevelCd()) && StringUtils.isNotBlank(ssjbCode) && Integer.valueOf(tdCaOperationApply.getOperLevelCd()) >= Integer.valueOf(ssjbCode)) {
            if (tdCaOperationApply.getMrFileList() != null && tdCaOperationApply.getMrFileList().size() > 0) {
               List<IndexVo> indexVoListSqtl = tdCaOperationApply.getMrFileList();
               List<IndexVo> indexVoListSqtlRes = (List)indexVoListSqtl.stream().filter((s) -> s.getAgiRuleId().equals(CommonConstants.AGI_RULE_ID.AGI_RULE_ID_SQTL.toString())).collect(Collectors.toList());
               if (indexVoListSqtlRes == null || indexVoListSqtlRes.size() <= 0) {
                  flag = false;
                  ajaxResult = AjaxResult.error("当前患者还未书写术前讨论记录，请书写");
               }

               List<IndexVo> indexVoListSszztysRes = (List)indexVoListSqtl.stream().filter((s) -> s.getAgiRuleId().equals(CommonConstants.AGI_RULE_ID.AGI_RULE_ID_SSZZTYS.toString())).collect(Collectors.toList());
               if (indexVoListSszztysRes == null || indexVoListSszztysRes.size() <= 0) {
                  flag = false;
                  ajaxResult = AjaxResult.error("当前患者还未书写术手术知情同意书，请书写");
               }
            }
         } else if (!"1".equals(sqtlFlag) && !"3".equals(sqtlFlag) && "2".equals(tdCaOperationApply.getOperTypeCd()) && tdCaOperationApply.getMrFileList() != null && tdCaOperationApply.getMrFileList().size() > 0) {
            List<IndexVo> indexVoListSqtl = tdCaOperationApply.getMrFileList();
            List<IndexVo> indexVoListSszztysRes = (List)indexVoListSqtl.stream().filter((s) -> s.getAgiRuleId().equals(CommonConstants.AGI_RULE_ID.AGI_RULE_ID_SSZZTYS.toString())).collect(Collectors.toList());
            if (indexVoListSszztysRes == null || indexVoListSszztysRes.size() <= 0) {
               flag = false;
               ajaxResult = AjaxResult.error("当前患者还未书写术手术知情同意书，请书写");
            }
         }

         VisitinfoPersonalVo personalVo = flag ? this.visitinfoService.selectVisitinfoPersonalById(tdCaOperationApply.getPatientId()) : null;
         List<String> icdCdList = new ArrayList(1);
         icdCdList.add(tdCaOperationApply.getPlanOper1Cd());
         icdCdList.add(tdCaOperationApply.getPlanOper2Cd());
         icdCdList.add(tdCaOperationApply.getPlanOper3Cd());
         icdCdList.add(tdCaOperationApply.getPlanOper4Cd());
         icdCdList = (List)icdCdList.stream().filter((t) -> StringUtils.isNotBlank(t)).collect(Collectors.toList());
         List<SysOpeIcd> opeIcdList = flag ? this.sysOpeIcdService.selectOpeIcdListByIcdCdList(icdCdList) : null;
         if (flag && CollectionUtils.isEmpty(opeIcdList)) {
            flag = false;
            ajaxResult = AjaxResult.error("没有这个手术编码记录，请确认后再试！");
         }

         opeIcdList = flag ? (List)opeIcdList.stream().filter((t) -> StringUtils.isNotBlank(t.getSex()) && !t.getSex().equals(personalVo.getSexCd())).collect(Collectors.toList()) : null;
         if (flag && CollectionUtils.isNotEmpty(opeIcdList)) {
            flag = false;
            List<String> opeIcdNameList = (List)opeIcdList.stream().map((t) -> t.getIcdName()).collect(Collectors.toList());
            String opeIcdNames = String.join(",", opeIcdNameList);
            ajaxResult = AjaxResult.error("手术【" + opeIcdNames + "】不适用与性别【" + personalVo.getSexCd() + "】，请确认后再试！");
         }

         if (flag) {
            this.tdCaOperationApplyService.saveTdCaOperationApply(tdCaOperationApply, personalVo);
            ajaxResult.put("orderNoList", StringUtils.isEmpty(tdCaOperationApply.getOrderNo()) ? new ArrayList() : Arrays.asList(tdCaOperationApply.getOrderNo()));
            ajaxResult.put("tdCaOperationApply", tdCaOperationApply);
            this.redisCache.deleteObject("patient_save_order:" + tdCaOperationApply.getPatientId());
         } else {
            this.redisCache.deleteObject("patient_save_order:" + tdCaOperationApply.getPatientId());
         }
      } catch (Exception e) {
         this.log.error("保存手术出现异常", e);
         this.redisCache.deleteObject("patient_save_order:" + tdCaOperationApply.getPatientId());
         ajaxResult = AjaxResult.error("保存手术出现异常");
      }

      return ajaxResult;
   }

   public AjaxResult verify(TdCaOperationApply tdCaOperationApply) {
      AjaxResult ajaxResult = AjaxResult.success("保存成功");
      Boolean flag = true;
      if (tdCaOperationApply == null) {
         flag = false;
         ajaxResult = AjaxResult.error("参数不能为空");
      }

      if (flag && StringUtils.isEmpty(tdCaOperationApply.getPatientId())) {
         flag = false;
         ajaxResult = AjaxResult.error("患者id不能为空");
      }

      if (flag && StringUtils.isEmpty(tdCaOperationApply.getExecDeptCd())) {
         flag = false;
         ajaxResult = AjaxResult.error("执行科室代码不能为空");
      }

      if (flag && StringUtils.isEmpty(tdCaOperationApply.getStatus())) {
         flag = false;
         ajaxResult = AjaxResult.error("状态不能为空");
      }

      if (flag && StringUtils.isEmpty(tdCaOperationApply.getExecDeptName())) {
         flag = false;
         ajaxResult = AjaxResult.error("执行科室名称不能为空");
      }

      if (flag && tdCaOperationApply.getPlanOperTime() == null) {
         flag = false;
         ajaxResult = AjaxResult.error("拟手术时间不能为空");
      }

      if (flag && StringUtils.isEmpty(tdCaOperationApply.getOperTypeCd())) {
         flag = false;
         ajaxResult = AjaxResult.error("手术类型不能为空");
      }

      if (flag && StringUtils.isEmpty(tdCaOperationApply.getPreoperDiag1Cd())) {
         flag = false;
         ajaxResult = AjaxResult.error("术前诊断1不能为空");
      }

      if (flag && StringUtils.isEmpty(tdCaOperationApply.getPreoperDiag1Name())) {
         flag = false;
         ajaxResult = AjaxResult.error("术前诊断1名称不能为空");
      }

      if (flag && StringUtils.isEmpty(tdCaOperationApply.getPlanOper1Cd())) {
         flag = false;
         ajaxResult = AjaxResult.error("计划手术1编码不能为空");
      }

      if (flag && StringUtils.isEmpty(tdCaOperationApply.getPlanOper1Name())) {
         flag = false;
         ajaxResult = AjaxResult.error("计划手术1名称不能为空");
      }

      if (flag && StringUtils.isEmpty(tdCaOperationApply.getOperSiteCd())) {
         flag = false;
         ajaxResult = AjaxResult.error("手术部位编码不能为空");
      }

      if (flag && StringUtils.isEmpty(tdCaOperationApply.getOperSiteName())) {
         flag = false;
         ajaxResult = AjaxResult.error("手术部位名称不能为空");
      }

      if (flag && StringUtils.isEmpty(tdCaOperationApply.getOperLevelCd())) {
         flag = false;
         ajaxResult = AjaxResult.error("手术级别编码不能为空");
      }

      if (flag && StringUtils.isEmpty(tdCaOperationApply.getOperLevelName())) {
         flag = false;
         ajaxResult = AjaxResult.error("手术级别名称不能为空");
      }

      if (flag && StringUtils.isEmpty(tdCaOperationApply.getOperInciCd())) {
         flag = false;
         ajaxResult = AjaxResult.error("切口类型编码不能为空");
      }

      if (flag && StringUtils.isEmpty(tdCaOperationApply.getOperInciName())) {
         flag = false;
         ajaxResult = AjaxResult.error("切口类型名称不能为空");
      }

      if (flag && StringUtils.isEmpty(tdCaOperationApply.getOperDocCd())) {
         flag = false;
         ajaxResult = AjaxResult.error("主刀医师编码不能为空");
      }

      if (flag && StringUtils.isEmpty(tdCaOperationApply.getOperDocName())) {
         flag = false;
         ajaxResult = AjaxResult.error("主刀医师姓名不能为空");
      }

      if (flag && StringUtils.isEmpty(tdCaOperationApply.getOperAid1Cd())) {
         flag = false;
         ajaxResult = AjaxResult.error("1助编码不能为空");
      }

      if (flag && StringUtils.isEmpty(tdCaOperationApply.getOperAid1Name())) {
         flag = false;
         ajaxResult = AjaxResult.error("1助姓名不能为空");
      }

      if (flag && StringUtils.isEmpty(tdCaOperationApply.getAnestMethCd())) {
         flag = false;
         ajaxResult = AjaxResult.error("麻醉方式编码不能为空");
      }

      if (flag && StringUtils.isEmpty(tdCaOperationApply.getAnestMethName())) {
         flag = false;
         ajaxResult = AjaxResult.error("麻醉方式名称不能为空");
      }

      if (flag && StringUtils.isEmpty(tdCaOperationApply.getAboCd())) {
         flag = false;
         ajaxResult = AjaxResult.error("ABO血型编码不能为空");
      }

      if (flag && StringUtils.isEmpty(tdCaOperationApply.getAboName())) {
         flag = false;
         ajaxResult = AjaxResult.error("ABO血型名称不能为空");
      }

      if (flag && StringUtils.isEmpty(tdCaOperationApply.getRhCd())) {
         flag = false;
         ajaxResult = AjaxResult.error("rh血型编码不能为空");
      }

      if (flag && StringUtils.isEmpty(tdCaOperationApply.getRhName())) {
         flag = false;
         ajaxResult = AjaxResult.error("rh血型名称不能为空");
      }

      if (flag && StringUtils.isEmpty(tdCaOperationApply.getHbv())) {
         flag = false;
         ajaxResult = AjaxResult.error("hbv不能为空");
      }

      if (flag && StringUtils.isEmpty(tdCaOperationApply.getHcv())) {
         flag = false;
         ajaxResult = AjaxResult.error("hcv不能为空");
      }

      if (flag && StringUtils.isEmpty(tdCaOperationApply.getHiv())) {
         flag = false;
         ajaxResult = AjaxResult.error("hiv不能为空");
      }

      if (flag && StringUtils.isEmpty(tdCaOperationApply.getTp())) {
         flag = false;
         ajaxResult = AjaxResult.error("tp不能为空");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('ope:apply:add')")
   @Log(
      title = "提交手术申请",
      businessType = BusinessType.UPDATE
   )
   @PostMapping({"/submit"})
   public AjaxResult submit(@RequestBody TdCaOperationApplyVo tdCaOperationApply, @RequestBody OrderCommitVo orderCommitVo) {
      AjaxResult ajaxResult = AjaxResult.success();

      try {
         Boolean flag = true;
         orderCommitVo.setPatientId(tdCaOperationApply.getPatientId());
         if (flag && StringUtils.isEmpty(tdCaOperationApply.getPatientId())) {
            flag = false;
            ajaxResult = AjaxResult.error("患者id不能为空");
         }

         if (flag && StringUtils.isEmpty(tdCaOperationApply.getApplyFormNo())) {
            flag = false;
            ajaxResult = AjaxResult.error("申请单编号不能为空");
         }

         String opeOrderFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0082");
         if (opeOrderFlag != null && opeOrderFlag.equals("1")) {
            String caFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0001");
            if (flag && caFlag.equals("1")) {
               if (flag && StringUtils.isEmpty(orderCommitVo.getEncryptedInfo())) {
                  flag = false;
                  ajaxResult = AjaxResult.error("加密后密文不能为空");
               }

               if (flag && StringUtils.isEmpty(orderCommitVo.getSignCertificate())) {
                  flag = false;
                  ajaxResult = AjaxResult.error("签名证书不能为空");
               }

               if (flag && StringUtils.isEmpty(orderCommitVo.getSignedText())) {
                  flag = false;
                  ajaxResult = AjaxResult.error("签名明文不能为空");
               }

               if (flag && orderCommitVo.getSubmitTime() == null) {
                  flag = false;
                  ajaxResult = AjaxResult.error("提交时间不能为空，请重新操作");
               }
            }
         }

         Visitinfo visitinfo = flag ? this.visitinfoService.selectVisitinfoById(orderCommitVo.getPatientId()) : null;
         if (flag && visitinfo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("没有这个患者记录，请重新操作");
         }

         List<TdPaOrderItem> orderItemList2 = null;
         if (opeOrderFlag != null && opeOrderFlag.equals("1")) {
            List<String> orderNoList = flag ? orderCommitVo.getOrderNoList() : null;
            if (flag && orderNoList == null) {
               flag = false;
               ajaxResult = AjaxResult.error("没有待提交的医嘱记录，请重新操作");
            }

            List<TdPaOrderItem> orderItemList = flag ? this.tdPaOrderItemService.selectFromStatusByOrderNoList(orderNoList) : null;
            if (flag && (orderItemList == null || orderItemList != null && orderItemList.isEmpty())) {
               flag = false;
               ajaxResult = AjaxResult.error("没有待提交的医嘱记录，请重新操作");
            }

            if (flag) {
               List var10000 = (List)orderItemList.stream().filter((t) -> !t.getOrderStatus().equals("-1")).collect(Collectors.toList());
            } else {
               Object var13 = null;
            }

            orderItemList2 = flag ? (List)orderItemList.stream().filter((t) -> t.getOrderStatus().equals("-1")).collect(Collectors.toList()) : null;
            if (flag && (orderItemList2 == null || orderItemList2 != null && orderItemList2.isEmpty())) {
               flag = false;
               ajaxResult = AjaxResult.error("没有待提交的医嘱记录，请重新操作");
            }
         }

         if (flag) {
            this.tdCaOperationApplyService.submitTdCaOperationApply(tdCaOperationApply, orderItemList2, orderCommitVo);
         }
      } catch (Exception e) {
         this.log.error("提交手术申请出现异常", e);
         ajaxResult = AjaxResult.error("提交手术申请出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('ope:apply:edit,ope:apply:list')")
   @Log(
      title = "撤销申请",
      businessType = BusinessType.UPDATE
   )
   @PutMapping({"/cancelSubmit"})
   public AjaxResult edit(@RequestBody TdCaOperationApply tdCaOperationApply, OrderStopSignVo orderStopSignVo) {
      AjaxResult ajaxResult = AjaxResult.success("撤销成功");
      boolean flag = true;

      try {
         if (tdCaOperationApply == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(tdCaOperationApply.getApplyFormNo())) {
            flag = false;
            ajaxResult = AjaxResult.error("申请单编号不能为空");
         }

         String opeOrder = this.sysEmrConfigService.selectSysEmrConfigByKey("0082");
         if (opeOrder != null && opeOrder.equals("1") && flag && StringUtils.isEmpty(tdCaOperationApply.getOrderNo())) {
            flag = false;
            ajaxResult = AjaxResult.error("医嘱编号不能为空");
         }

         if (flag) {
            TdCaOperationApply apply = this.tdCaOperationApplyService.selectTdCaOperationApplyById(tdCaOperationApply.getApplyFormNo());
            if (apply == null) {
               flag = false;
               ajaxResult = AjaxResult.error("未查询到当前申请单记录，请刷新后重试");
            } else if (apply.getStatus().equals("06")) {
               flag = false;
               ajaxResult = AjaxResult.error("申请单已安排手术不可撤销");
            } else if (apply.getOperTypeCd().equals("2") && apply.getStatus().equals("05")) {
               flag = false;
               ajaxResult = AjaxResult.error("申请单已审核不可撤销");
            } else if (apply.getStatus().equals("01")) {
               flag = false;
               ajaxResult = AjaxResult.error("申请单未提交不可撤销");
            }
         }

         if (flag) {
            this.tdCaOperationApplyService.cancelSubmitOpeApply(tdCaOperationApply, orderStopSignVo);
         }
      } catch (Exception e) {
         this.log.error("撤销手术申请出现异常", e);
         ajaxResult = AjaxResult.error("撤销手术申请出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('ope:apply:edit,ope:apply:list')")
   @PutMapping({"/remove"})
   public AjaxResult remove(@RequestBody TdCaOperationApply tdCaOperationApply, OrderStopSignVo orderStopSignVo) {
      AjaxResult ajaxResult = AjaxResult.success("作废成功");
      boolean flag = true;

      try {
         if (tdCaOperationApply == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(tdCaOperationApply.getApplyFormNo())) {
            flag = false;
            ajaxResult = AjaxResult.error("手术申请单编号不能为空");
         }

         if (flag && StringUtils.isEmpty(tdCaOperationApply.getPatientId())) {
            flag = false;
            ajaxResult = AjaxResult.error("患者id不能为空");
         }

         if (flag) {
            TdCaOperationApply apply = this.tdCaOperationApplyService.selectTdCaOperationApplyById(tdCaOperationApply.getApplyFormNo());
            if (apply == null) {
               flag = false;
               ajaxResult = AjaxResult.error("未查询到申请单记录，请刷新后重试");
            } else if (apply.getStatus().equals("06")) {
               flag = false;
               ajaxResult = AjaxResult.error("申请单已安排手术不可作废");
            } else if (apply.getStatus().equals("05")) {
               flag = false;
               ajaxResult = AjaxResult.error("申请单已审核不可作废");
            } else if (apply.getStatus().equals("02")) {
               flag = false;
               ajaxResult = AjaxResult.error("申请单已提交不可作废");
            }
         }

         if (flag) {
            this.tdCaOperationApplyService.deleteTdCaOperationApply(tdCaOperationApply, orderStopSignVo);
         }
      } catch (Exception e) {
         this.log.error("手术申请单作废出现异常", e);
         ajaxResult = AjaxResult.error("手术申请单作废出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('ope:apply:list')")
   @GetMapping({"/opeEmrList"})
   public AjaxResult opeEmrList(TdCaOperationApplyVo tdCaOperationApply) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");
      boolean flag = true;

      try {
         if (tdCaOperationApply == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(tdCaOperationApply.getPatientId())) {
            flag = false;
            ajaxResult = AjaxResult.error("患者id不能为空");
         }

         if (flag && StringUtils.isEmpty(tdCaOperationApply.getEmrTypeCode())) {
            flag = false;
            ajaxResult = AjaxResult.error("病历类型不能为空");
         }

         if (flag && tdCaOperationApply.getAgiRuleId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("时效规则id不能为空");
         }

         if (flag) {
            List<IndexVo> list = this.tdCaOperationApplyService.selectOpeIndexByEmrType(tdCaOperationApply);
            ajaxResult = AjaxResult.success((Object)list);
         }
      } catch (Exception e) {
         this.log.error("查询关联手术病历列表出现异常", e);
         ajaxResult = AjaxResult.error("查询关联手术病历列表出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('ope:apply:list')")
   @GetMapping({"/operBefore"})
   public AjaxResult operBefore(TdCaOperationApply tdCaOperationApply) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");
      boolean flag = true;

      try {
         if (tdCaOperationApply == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(tdCaOperationApply.getPatientId())) {
            flag = false;
            ajaxResult = AjaxResult.error("患者id不能为空");
         }

         if (flag) {
            List<TdCaOperationApplyVo> map = this.tdCaOperationApplyService.selectOperBeforeList(tdCaOperationApply, "17");
            ajaxResult = AjaxResult.success((Object)map);
            Index index = this.indexService.selectIndexByPatientId(tdCaOperationApply.getPatientId());
            ajaxResult.put("mainId", index == null ? "" : index.getId());
         }
      } catch (Exception e) {
         this.log.error("查询术前信息列表出现异常", e);
         ajaxResult = AjaxResult.error("查询术前信息列表出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('ope:apply:list')")
   @GetMapping({"/operAfter"})
   public AjaxResult operAfter(TdCaOperationApply tdCaOperationApply) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");
      boolean flag = true;

      try {
         if (tdCaOperationApply == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(tdCaOperationApply.getPatientId())) {
            flag = false;
            ajaxResult = AjaxResult.error("患者id不能为空");
         }

         if (flag) {
            List<TdCaOperationApplyVo> map = this.tdCaOperationApplyService.selectOperBeforeList(tdCaOperationApply, "06");
            ajaxResult = AjaxResult.success((Object)map);
            Index index = this.indexService.selectIndexByPatientId(tdCaOperationApply.getPatientId());
            ajaxResult.put("mainId", index == null ? "" : index.getId());
         }
      } catch (Exception e) {
         this.log.error("查询术后信息列表出现异常", e);
         ajaxResult = AjaxResult.error("查询术后信息列表出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('ope:apply:list')")
   @GetMapping({"/operApplyAuth"})
   public AjaxResult operApplyAuth(OpeAuthorityVo opeAuthorityVo) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");
      boolean flag = true;

      try {
         if (opeAuthorityVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(opeAuthorityVo.getOperLevel())) {
            flag = false;
            ajaxResult = AjaxResult.error("手术级别不能为空");
         }

         if (flag) {
            BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
            OpeAuthorityVo result = this.tdCaOperationApplyService.selectDocOpeAuth(basEmployee.getEmplNumber(), opeAuthorityVo.getOperLevel());
            ajaxResult = AjaxResult.success((Object)result);
         }
      } catch (Exception e) {
         this.log.error("查询医师手术级别申请权限出现异常", e);
         ajaxResult = AjaxResult.error("查询医师手术级别申请权限出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('ope:apply:list')")
   @GetMapping({"/operateAuth"})
   public AjaxResult operateAuth(OpeAuthorityVo opeAuthorityVo) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");
      boolean flag = true;

      try {
         if (opeAuthorityVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(opeAuthorityVo.getOperLevel())) {
            flag = false;
            ajaxResult = AjaxResult.error("手术级别不能为空");
         }

         if (flag && StringUtils.isEmpty(opeAuthorityVo.getDocCd())) {
            flag = false;
            ajaxResult = AjaxResult.error("医师编码不能为空");
         }

         if (flag) {
            OpeAuthorityVo result = this.tdCaOperationApplyService.selectDocOpeAuth(opeAuthorityVo.getDocCd(), opeAuthorityVo.getOperLevel());
            ajaxResult = AjaxResult.success((Object)result);
         }
      } catch (Exception e) {
         this.log.error("查询医师手术级别主刀权限出现异常", e);
         ajaxResult = AjaxResult.error("查询医师手术级别主刀权限出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('ope:apply:unAuditList')")
   @GetMapping({"/unAuditList"})
   public TableDataInfo unAuditList(TdCaOperationApplyVo tdCaOperationApply) {
      new TableDataInfo();

      TableDataInfo tableDataInfo;
      try {
         this.startPage();
         List<TdCaOperationApplyVo> tdCaOperationApplies = this.tdCaOperationApplyService.selectOperUnAuditList(tdCaOperationApply);
         tableDataInfo = this.getDataTable(tdCaOperationApplies);
      } catch (Exception e) {
         this.log.error("查询手术待审批列表出现异常", e);
         tableDataInfo = new TableDataInfo(500, "查询手术待审批列表出现异常");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasPermi('ope:apply:auditList')")
   @GetMapping({"/auditList"})
   public TableDataInfo auditList(TdCaOperationApplyVo tdCaOperationApply) {
      new TableDataInfo();

      TableDataInfo tableDataInfo;
      try {
         this.startPage();
         if (tdCaOperationApply.getPlanOperEndTime() != null && tdCaOperationApply.getPlanOperStartTime() != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(tdCaOperationApply.getPlanOperEndTime());
            c.add(5, 1);
            tdCaOperationApply.setPlanOperEndTime(c.getTime());
         }

         List<TdCaOperationApplyVo> tdCaOperationApplies = this.tdCaOperationApplyService.selectOperAuditList(tdCaOperationApply);
         tableDataInfo = this.getDataTable(tdCaOperationApplies);
      } catch (Exception e) {
         this.log.error("查询手术已审批列表出现异常", e);
         tableDataInfo = new TableDataInfo(500, "查询手术已审批列表出现异常");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasPermi('ope:apply:audit')")
   @PutMapping({"/audit"})
   public AjaxResult audit(@RequestBody TdCaOperationApplyVo tdCaOperationApply) {
      AjaxResult ajaxResult = AjaxResult.success("审核成功");
      boolean flag = true;

      try {
         if (tdCaOperationApply == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(tdCaOperationApply.getApplyFormNo())) {
            flag = false;
            ajaxResult = AjaxResult.error("申请单id不能为空");
         }

         if (flag && StringUtils.isEmpty(tdCaOperationApply.getStatus())) {
            flag = false;
            ajaxResult = AjaxResult.error("审核状态不能为空");
         }

         if (flag && tdCaOperationApply.getStatus().equals("04") && StringUtils.isEmpty(tdCaOperationApply.getAuditRemark())) {
            flag = false;
            ajaxResult = AjaxResult.error("审核备注不能为空");
         }

         if (flag) {
            this.tdCaOperationApplyService.updateApplyAudit(tdCaOperationApply);
         }
      } catch (Exception e) {
         this.log.error("审核手术申请出现异常", e);
         ajaxResult = AjaxResult.error("审核手术申请出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('ope:apply:list')")
   @GetMapping({"/operFlowChart"})
   public AjaxResult operFlowChart(TdCaOperationApplyVo tdCaOperationApply) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");
      boolean flag = true;

      try {
         if (tdCaOperationApply == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(tdCaOperationApply.getApplyFormNo())) {
            flag = false;
            ajaxResult = AjaxResult.error("手术申请单编号不能为空");
         }

         if (flag) {
            List<Map<String, Object>> result = this.tdCaOperationApplyService.selectOperFlowChart(tdCaOperationApply.getApplyFormNo());
            ajaxResult = AjaxResult.success((Object)result);
         }
      } catch (Exception e) {
         this.log.error("查询手术流程图出现异常", e);
         ajaxResult = AjaxResult.error("查询手术流程图出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('ope:apply:list')")
   @GetMapping({"/hisCheckResult"})
   public AjaxResult hisCheckResult(TdCaOperationApplyVo tdCaOperationApply) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");
      boolean flag = true;

      try {
         if (tdCaOperationApply == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(tdCaOperationApply.getPatientId())) {
            flag = false;
            ajaxResult = AjaxResult.error("患者id不能为空");
         }

         if (flag) {
            Map<String, Object> result = this.tdCaOperationApplyService.selectHisCheckResult(tdCaOperationApply.getPatientId());
            ajaxResult = AjaxResult.success((Object)result);
         }
      } catch (Exception e) {
         this.log.error("查询his项目检验结果信息出现异常", e);
         ajaxResult = AjaxResult.error("查询his项目检验结果信息出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('ope:apply:operEmr')")
   @PutMapping({"/addOperEmr"})
   public AjaxResult addOperEmr(@RequestBody TdCaOperationApplyVo tdCaOperationApply) {
      AjaxResult ajaxResult = AjaxResult.success("保存成功");
      boolean flag = true;

      try {
         if (tdCaOperationApply == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(tdCaOperationApply.getApplyFormNo())) {
            flag = false;
            ajaxResult = AjaxResult.error("申请单id不能为空");
         }

         if (flag && tdCaOperationApply.getAgiRuleId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("时效规则id不能为空");
         }

         TdCaOperationApply info = null;
         if (flag) {
            info = this.tdCaOperationApplyService.selectTdCaOperationApplyById(tdCaOperationApply.getApplyFormNo());
            if (info == null) {
               flag = false;
               ajaxResult = AjaxResult.error("未查询到申请单信息，请刷新后重试");
            } else if (info.getStatus().equals("03") || info.getStatus().equals("99")) {
               flag = false;
               ajaxResult = AjaxResult.error("删除和作废的申请单不能关联病历信息");
            }
         }

         if (flag) {
            this.tdCaOperationApplyService.addOperEmr(tdCaOperationApply);
         }
      } catch (Exception e) {
         this.log.error("手术关联病历文件出现异常", e);
         ajaxResult = AjaxResult.error("手术关联病历文件出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('ope:apply:operPlan')")
   @GetMapping({"/operPlan"})
   public TableDataInfo operPlan(TdCaOperationApplyVo tdCaOperationApply) {
      new TableDataInfo();

      TableDataInfo tableDataInfo;
      try {
         if (tdCaOperationApply.getOperStartDate() != null && tdCaOperationApply.getOperEndDate() != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(tdCaOperationApply.getOperEndDate());
            c.add(5, 1);
            tdCaOperationApply.setOperEndDate(c.getTime());
         }

         if (StringUtils.isBlank(tdCaOperationApply.getApplyDeptCd())) {
            tdCaOperationApply.setApplyDeptCd(SecurityUtils.getLoginUser().getUser().getDept().getDeptCode());
         }

         if (StringUtils.isNotBlank(tdCaOperationApply.getApplyDeptCd()) && tdCaOperationApply.getApplyDeptCd().equals(CommonConstants.SYSTEM.ALL_DEPT_ID.toString())) {
            tdCaOperationApply.setApplyDeptCd((String)null);
         }

         this.startPage();
         List<TdCaOperationApplyVo> list = this.tdCaOperationApplyService.selectOperPlanList(tdCaOperationApply);
         tableDataInfo = this.getDataTable(list);
      } catch (Exception e) {
         this.log.error("查询手术排班列表出现异常", e);
         tableDataInfo = new TableDataInfo(500, "查询手术排班列表出现异常");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasPermi('ope:apply:list')")
   @GetMapping({"/operDateTime"})
   public AjaxResult operDateTime() {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         ajaxResult = AjaxResult.success((Object)this.tdCaOperationApplyService.selectOperDateTime());
      } catch (Exception e) {
         this.log.error("查询手术排班配置时间出现异常", e);
         ajaxResult = AjaxResult.error("查询手术排班配置时间出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('ope:apply:operRoom')")
   @GetMapping({"/operRoom"})
   public TableDataInfo operRoom(TdCaOperationApplyVo tdCaOperationApply) {
      new TableDataInfo();

      TableDataInfo tableDataInfo;
      try {
         if (tdCaOperationApply.getOperEndDate() != null) {
            Date operEndDate = DateUtils.addDays(tdCaOperationApply.getOperEndDate(), 1);
            tdCaOperationApply.setOperEndDate(operEndDate);
         }

         SysDept sysDept = SecurityUtils.getLoginUser().getUser().getDept();
         tdCaOperationApply.setApplyDeptCd(sysDept.getDeptCode());
         this.startPage();
         List<TdCaOperationApplyVo> list = this.tdCaOperationApplyService.selectOperPlanList(tdCaOperationApply);
         tableDataInfo = this.getDataTable(list);
      } catch (Exception e) {
         this.log.error("查询手术排班列表出现异常", e);
         tableDataInfo = new TableDataInfo(500, "查询手术排班列表出现异常");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasAnyPermi('oper:room:workload')")
   @GetMapping({"/docWorkload"})
   public AjaxResult docWorkload(TdCaOperationApplyVo tdCaOperationApply) {
      AjaxResult ajaxResult = AjaxResult.success();

      try {
         if (tdCaOperationApply.getOperStartDate() != null && tdCaOperationApply.getOperEndDate() != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(tdCaOperationApply.getOperEndDate());
            c.add(5, 1);
            tdCaOperationApply.setOperEndDate(c.getTime());
         }

         List<TdCaOperWorkloadVo> list = this.tdCaOperationApplyService.selectOperRoomDocWorkload(tdCaOperationApply);
         ajaxResult = AjaxResult.success((Object)list);
         ajaxResult.put("statisDoctor", SecurityUtils.getLoginUser().getUser().getNickName());
         ajaxResult.put("statisDate", DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss", this.commonService.getDbSysdate()));
      } catch (Exception e) {
         this.log.error("查询手术排班列表出现异常", e);
         ajaxResult = AjaxResult.success("查询手术排班列表出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('ope:apply:operRoom')")
   @GetMapping({"/export"})
   public AjaxResult export(TdCaOperationApplyVo tdCaOperationApply) {
      try {
         if (tdCaOperationApply.getOperStartDate() != null && tdCaOperationApply.getOperEndDate() != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(tdCaOperationApply.getOperEndDate());
            c.add(5, 1);
            tdCaOperationApply.setOperEndDate(c.getTime());
         }

         SysDept sysDept = SecurityUtils.getLoginUser().getUser().getDept();
         tdCaOperationApply.setApplyDeptCd(sysDept.getDeptCode());
         this.startPage();
         List<TdCaOperationApplyVo> list = this.tdCaOperationApplyService.selectOperPlanList(tdCaOperationApply);
         ExcelUtil<TdCaOperationApplyVo> util = new ExcelUtil(TdCaOperationApplyVo.class);
         return util.exportExcel(list, "住院手术申请数据");
      } catch (Exception e) {
         this.log.error("导出手术申请列表出现异常", e);
         return AjaxResult.error("导出手术申请列表出现异常");
      }
   }
}
