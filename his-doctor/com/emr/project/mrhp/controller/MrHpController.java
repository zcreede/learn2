package com.emr.project.mrhp.controller;

import com.emr.common.utils.AgeUtil;
import com.emr.common.utils.DateUtils;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.common.utils.StringUtils;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.aspectj.lang.enums.DataSourceType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.common.service.ICommonService;
import com.emr.project.docOrder.domain.TdPaItemDocQuery;
import com.emr.project.docOrder.service.ITdPaItemDocQueryService;
import com.emr.project.emr.domain.EmrSignData;
import com.emr.project.emr.domain.EmrSignRecord;
import com.emr.project.emr.service.IEmrSignRecordService;
import com.emr.project.esSearch.service.IEmrFileService;
import com.emr.project.mrhp.domain.MrHp;
import com.emr.project.mrhp.domain.MrHpAttach;
import com.emr.project.mrhp.domain.MrHpDia;
import com.emr.project.mrhp.domain.MrHpDrawField;
import com.emr.project.mrhp.domain.MrHpDrawMain;
import com.emr.project.mrhp.domain.MrHpFee;
import com.emr.project.mrhp.domain.MrHpOpe;
import com.emr.project.mrhp.domain.req.MrHpRecordDipReq;
import com.emr.project.mrhp.domain.resp.MrHpRecordDipResp;
import com.emr.project.mrhp.domain.vo.MrHpAttachVo;
import com.emr.project.mrhp.domain.vo.MrHpOpeVo;
import com.emr.project.mrhp.domain.vo.MrHpPrintVo;
import com.emr.project.mrhp.domain.vo.MrHpVo;
import com.emr.project.mrhp.domain.vo.PatientInfoDetailVo;
import com.emr.project.mrhp.service.IMrHpDiaService;
import com.emr.project.mrhp.service.IMrHpDipQcService;
import com.emr.project.mrhp.service.IMrHpDrawFieldService;
import com.emr.project.mrhp.service.IMrHpDrawMainService;
import com.emr.project.mrhp.service.IMrHpFeeService;
import com.emr.project.mrhp.service.IMrHpOpeService;
import com.emr.project.mrhp.service.IMrHpService;
import com.emr.project.mrhp.service.ITmDsPreserveOutService;
import com.emr.project.mrhp.service.callable.MrAttachCallable;
import com.emr.project.operation.service.ExpenseDetailService;
import com.emr.project.other.domain.BasCertInfo;
import com.emr.project.other.service.IBasCertInfoService;
import com.emr.project.pat.domain.DiagInfo;
import com.emr.project.pat.service.IDiagInfoService;
import com.emr.project.qc.domain.EmrQcStatusMes;
import com.emr.project.qc.domain.vo.EmrQcListVo;
import com.emr.project.sys.domain.OpeTypeEnum;
import com.emr.project.system.domain.BsStaff;
import com.emr.project.system.domain.SyncDatasource;
import com.emr.project.system.domain.SysDept;
import com.emr.project.system.domain.SysDictData;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.service.IBsStaffService;
import com.emr.project.system.service.ISyncDatasourceService;
import com.emr.project.system.service.ISysDictDataService;
import com.emr.project.system.service.ISysEmrConfigService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/mrhp/hp"})
public class MrHpController extends BaseController {
   @Autowired
   private IMrHpService mrHpService;
   @Autowired
   private IBasCertInfoService basCertInfoService;
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;
   @Autowired
   private IEmrSignRecordService emrSignRecordService;
   @Autowired
   private ISyncDatasourceService syncDatasourceService;
   @Autowired
   private ITmDsPreserveOutService tmDsPreserveOutService;
   @Autowired
   private IMrHpFeeService iMrHpFeeService;
   @Autowired
   private IMrHpOpeService mrHpOpeService;
   @Autowired
   private IMrHpDiaService mrHpDiaService;
   @Autowired
   private ISysDictDataService sysDictDataService;
   @Autowired
   private ITdPaItemDocQueryService tdPaItemDocQueryService;
   @Autowired
   private IDiagInfoService diagInfoService;
   @Autowired
   private ICommonService commonService;
   @Autowired
   private IBsStaffService bsStaffService;
   @Autowired
   private IMrHpDrawMainService mrHpDrawMainService;
   @Autowired
   private IMrHpDrawFieldService mrHpDrawFieldService;
   @Autowired
   private IEmrFileService emrFileService;
   @Autowired
   private IMrHpDipQcService mrHpDipQcService;
   @Autowired
   private ExpenseDetailService expenseDetailService;

   @PreAuthorize("@ss.hasPermi('mrhp:hp:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(MrHp mrHp) {
      this.startPage();
      List<MrHpVo> list = this.mrHpService.selectMrHpList(mrHp);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('mrhp:hp:query')")
   @GetMapping({"/{recordId}"})
   public AjaxResult getInfo(@PathVariable("recordId") String recordId) {
      return AjaxResult.success((Object)this.mrHpService.selectMrHpById(recordId));
   }

   @PreAuthorize("@ss.hasAnyPermi('mrhp:hp:save,pat:visitinfo:mrhp')")
   @Log(
      title = "病案首页",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult save(@RequestBody MrHpVo mrHpVo, @RequestBody MrHpAttachVo mrHpAttachVo) {
      AjaxResult ajaxResult = AjaxResult.success("保存成功");
      boolean flag = true;

      try {
         mrHpVo.setMrHpAttachVo(mrHpAttachVo);
         if (flag && StringUtils.isBlank(mrHpVo.getPatientId())) {
            flag = false;
            ajaxResult = AjaxResult.error("患者id参数不能为空");
         }

         if (flag) {
            EmrQcStatusMes statusMes = this.commonService.queryEmrQcStatus(mrHpVo.getPatientId());
            if (statusMes != null && !statusMes.getFlag()) {
               flag = statusMes.getFlag();
               ajaxResult = AjaxResult.error(statusMes.getMsg());
            }
         }

         if (flag && !"01".equals(mrHpVo.getMrState())) {
            List<EmrQcListVo> qcExcepationList = this.mrHpService.getQcErrorList(mrHpVo);
            List<EmrQcListVo> list = this.mrHpService.mrHpCheckOut(mrHpVo);
            if (list != null && list.size() > 0) {
               flag = false;
               ajaxResult = AjaxResult.error("有质控缺陷，请处理后再保存");
            }

            qcExcepationList.addAll(list);
            ajaxResult.put("emrQcList", qcExcepationList);
            mrHpVo.setEmrQcListList(qcExcepationList);
            if (flag) {
               List<MrHpOpeVo> mrHpOpeList = mrHpVo.getMrHpOpeList();
               mrHpOpeList.removeIf((t) -> StringUtils.isEmpty(t.getOprName()));
               if (mrHpOpeList != null && mrHpOpeList.size() > 0) {
                  for(MrHpOpeVo ope : mrHpOpeList) {
                     if (StringUtils.isNotBlank(ope.getOprName())) {
                        String opeType = ope.getOpeType();
                        String opeMain = ope.getOpeMain();
                        if (StringUtils.isBlank(opeType)) {
                           flag = false;
                           ajaxResult = AjaxResult.error("病案续页-手术信息：手术类型没有填写，请确认后再保存！");
                        }

                        if (StringUtils.isBlank(opeMain)) {
                           flag = false;
                           ajaxResult = AjaxResult.error("病案续页-手术信息：主手术没有填写，请确认后再保存！");
                        }

                        if (flag && (ope.getOprBeginDatetime() == null || ope.getOprEndDatetime() == null)) {
                           flag = false;
                           ajaxResult = AjaxResult.error("病案续页-手术信息：手术开始时间及结束时间不能为空，请确认后再保存！");
                        }

                        if (flag && opeType.equals(OpeTypeEnum.OPERATION.getTypeCode())) {
                           if (StringUtils.isBlank(ope.getOprLevelCode())) {
                              flag = false;
                              ajaxResult = AjaxResult.error("病案续页-手术信息：手术级别不能为空，请确认后再保存！");
                           }

                           if (flag && StringUtils.isBlank(ope.getOprInciCode())) {
                              flag = false;
                              ajaxResult = AjaxResult.error("病案续页-手术信息：手术切口类别不能为空，请确认后再保存！");
                           }

                           if (flag && StringUtils.isBlank(ope.getOprHealCode())) {
                              flag = false;
                              ajaxResult = AjaxResult.error("病案续页-手术信息：手术愈合等级不能为空，请确认后再保存！");
                           }

                           if (flag && StringUtils.isBlank(ope.getAnestMethCode())) {
                              flag = false;
                              ajaxResult = AjaxResult.error("病案续页-手术信息：麻醉方法不能为空，请确认后再保存！");
                           }

                           if (flag && StringUtils.isBlank(ope.getAnestName())) {
                              flag = false;
                              ajaxResult = AjaxResult.error("病案续页-手术信息：麻醉医师姓名不能为空，请确认后再保存！");
                           }
                        }
                     }
                  }

                  if (flag) {
                     Map<String, List<MrHpOpeVo>> opeMainMap = (Map)mrHpOpeList.stream().collect(Collectors.groupingBy(MrHpOpe::getOpeMain));
                     if (opeMainMap.containsKey("1")) {
                        List<MrHpOpeVo> mrHpOpeVos = (List)opeMainMap.get("1");
                        if (mrHpOpeVos.size() > 1) {
                           flag = false;
                           ajaxResult = AjaxResult.error("病案续页-手术信息：必须有且只有一条主手术信息！");
                        }
                     } else {
                        flag = false;
                        ajaxResult = AjaxResult.error("病案续页-手术信息：必须有一条主手术信息！");
                     }
                  }
               }

               if (flag && mrHpOpeList.size() == 0) {
                  List<MrHpFee> feeList = null;
                  if (StringUtils.isBlank(mrHpVo.getRecordId())) {
                     Map<String, Object> param = new HashMap();
                     param.put("inpNo", mrHpVo.getInpNo());
                     param.put("mrHpFee", new MrHpFee());
                     feeList = this.iMrHpFeeService.selectMrHpFeeListByProc(param);
                  } else {
                     MrHpFee mrHpFee = new MrHpFee();
                     mrHpFee.setRecordId(mrHpVo.getRecordId());
                     mrHpFee.setFeeCd("030202");
                     feeList = this.iMrHpFeeService.selectMrHpFeeList(mrHpFee);
                  }

                  List<MrHpFee> operationFee = CollectionUtils.isNotEmpty(feeList) ? (List)feeList.stream().filter((t) -> StringUtils.isNotBlank(t.getFeeCd()) && t.getFeeCd().equals("030202")).collect(Collectors.toList()) : null;
                  Double amount = CollectionUtils.isNotEmpty(operationFee) ? ((MrHpFee)operationFee.get(0)).getAmount() : null;
                  if (amount != null && amount.compareTo(Double.valueOf("0")) > 0) {
                     flag = false;
                     ajaxResult = AjaxResult.error("病案续页-手术信息：存在手术费用信息,需增加一条主手术信息！");
                  }
               }
            }

            if (flag && mrHpVo.getInhosTime() != null && mrHpVo.getOutTime() != null && mrHpVo.getRealInhosDays() != null) {
               Date inhosTime = mrHpVo.getInhosTime();
               Date outTime = mrHpVo.getOutTime();
               SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
               String inhosTimeStr = sdf.format(inhosTime);
               inhosTime = sdf.parse(inhosTimeStr);
               String outTimeStr = sdf.format(outTime);
               outTime = sdf.parse(outTimeStr);
               int inhosDays = mrHpVo.getRealInhosDays();
               int diffDay = DateUtils.getDiffDay(outTime, inhosTime);
               if (inhosDays < diffDay - 1 || inhosDays > diffDay + 1) {
                  flag = false;
                  ajaxResult = AjaxResult.error("出院天数设置超出范围，请核对后再保存！");
               }
            }

            if (flag) {
               String cardTypeCd = mrHpVo.getCardTypeCd();
               String cardTypeNo = mrHpVo.getCardTypeNo();
               if (StringUtils.isNotEmpty(cardTypeCd) && StringUtils.isNotEmpty(cardTypeNo) && !cardTypeNo.equals("-") && cardTypeCd.equals("1")) {
                  if (cardTypeNo.length() != 18 && cardTypeNo.length() != 15) {
                     flag = false;
                     ajaxResult = AjaxResult.error("身份证号位数不正确，请确认后再试");
                  }

                  SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
                  Date birDate = mrHpVo.getBirDate();
                  Long ageY = mrHpVo.getAgeY();
                  Map<String, String> map = AgeUtil.getBirAgeSexByInhosDate(cardTypeNo, mrHpVo.getInhosTime());
                  String birthday = (String)map.get("birthday");
                  String ageYs = (String)map.get("ageY");
                  if (flag && !sdf.format(birDate).equals(birthday)) {
                     flag = false;
                     ajaxResult = AjaxResult.error("身份证号对应的出生日期与输入的出生日期不一致，请修改后再试");
                  }

                  if (flag && ageY != null && !ageY.toString().equals(ageYs)) {
                     flag = false;
                     ajaxResult = AjaxResult.error("身份证号对应的年龄与输入的年龄不一致，请修改后再试");
                  }
               }
            }
         }

         if (flag) {
            this.mrHpService.saveMrHp(mrHpVo);

            try {
               String dipFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0074");
               if (dipFlag != null && "1".equals(dipFlag)) {
                  String dipFactory = this.sysEmrConfigService.selectSysEmrConfigByKey("007401");
                  if ("LBDZ".equals(dipFactory)) {
                     SysUser user = SecurityUtils.getLoginUser().getUser();
                     MrHpRecordDipReq mrHpRecordDipReq = new MrHpRecordDipReq();
                     mrHpRecordDipReq.setDeptCode(mrHpVo.getInDeptCd());
                     mrHpRecordDipReq.setPatientId(mrHpVo.getPatientId());
                     mrHpRecordDipReq.setOrgCode(user.getHospital().getChsCode());
                     mrHpRecordDipReq.setUserCode(user.getUserName());
                     mrHpRecordDipReq.setQutyType("cyzk");
                     MrHpRecordDipResp dipResp = this.mrHpDipQcService.selectDipQcUrl(mrHpRecordDipReq);
                     if (dipResp != null && 200 == dipResp.getCode()) {
                        ajaxResult = AjaxResult.success("质控成功", dipResp.getData());
                        ajaxResult.put("dipUrl", dipResp.getData());
                     } else {
                        this.log.error("dip质控失败返回信息", dipResp.getMsg());
                     }
                  }
               }
            } catch (Exception e) {
               this.log.error("dip质控出现异常，请联系系统管理员", e);
            }
         }
      } catch (Exception e) {
         this.log.error("保存病案首页出现异常", e);
         ajaxResult = AjaxResult.error("保存病案首页出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('mrhp:hp:remove')")
   @Log(
      title = "病案首页",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{recordIds}"})
   public AjaxResult remove(@PathVariable String[] recordIds) {
      return this.toAjax(this.mrHpService.deleteMrHpByIds(recordIds));
   }

   @PreAuthorize("@ss.hasAnyPermi('mrhp:hp:mrHpDetail,pat:visitinfo:mrhp')")
   @GetMapping({"/mrHpDetail"})
   public AjaxResult selectMrHpPatientDetail(MrHpVo mrHpVo) {
      new AjaxResult();

      AjaxResult ajaxResult;
      try {
         if (StringUtils.isEmpty(mrHpVo.getPatientId())) {
            ajaxResult = AjaxResult.error("患者就诊id参数不能为空");
         } else {
            MrHpVo mrHpDetail = this.mrHpService.selectMrHpPatientDetail(mrHpVo);
            MrHpDrawMain mrHpDrawMain = new MrHpDrawMain();
            mrHpDrawMain.setStatus("1");
            mrHpDrawMain.setInterfaceType("2");
            mrHpDrawMain.setHisTableName("MR_HP_ATTACH");
            List<MrHpDrawMain> mrHpDrawMainList = this.mrHpDrawMainService.selectMrHpDrawMainList(mrHpDrawMain);
            ExecutorService executor = Executors.newFixedThreadPool(5);
            Future<MrHpAttach> attachFuture = null;
            if (!mrHpDrawMainList.isEmpty()) {
               List<Long> mainIdList = (List)mrHpDrawMainList.stream().map(MrHpDrawMain::getId).collect(Collectors.toList());
               List<MrHpDrawField> fieldList = this.mrHpDrawFieldService.selectFieldByMainIdList(mainIdList);
               attachFuture = executor.submit(new MrAttachCallable(mrHpDetail.getRecordNo(), mrHpDetail.getInpNo(), mrHpDetail.getVisitId().intValue(), mrHpDrawMainList, fieldList, this.tmDsPreserveOutService));
            }

            BsStaff bsStaff = new BsStaff();
            bsStaff.setStaffType("bmy");
            List<BsStaff> bsStaffList = this.bsStaffService.selectBsStaffByStaffType(bsStaff);
            if (CollectionUtils.isNotEmpty(bsStaffList) && bsStaffList.size() == 1 && StringUtils.isBlank(mrHpDetail.getCoderCd())) {
               mrHpDetail.setCoderCd(((BsStaff)bsStaffList.get(0)).getStaffCode());
               mrHpDetail.setCoderName(((BsStaff)bsStaffList.get(0)).getStaffName());
            }

            ajaxResult = AjaxResult.success((Object)mrHpDetail);
            Map<String, List<SysDictData>> map = this.mrHpService.selectMrHpDictMap();
            ajaxResult.put("dictList", map);
            MrHpAttachVo mrHpAttachVo = this.mrHpService.selectMrHpAttachList(mrHpDetail);
            if (mrHpAttachVo == null) {
               mrHpAttachVo = new MrHpAttachVo();
               if (!mrHpDrawMainList.isEmpty() && attachFuture != null) {
                  MrHpAttach mrHpAttach = (MrHpAttach)attachFuture.get();
                  BeanUtils.copyProperties(mrHpAttach, mrHpAttachVo);
               }
            }

            ajaxResult.put("mrHpAttachVo", mrHpAttachVo);
            String caFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0001");
            String caType = this.sysEmrConfigService.selectSysEmrConfigByKey("0002");
            ajaxResult.put("caFlag", caFlag);
            ajaxResult.put("caType", caType);
            String configKey = this.sysEmrConfigService.selectSysEmrConfigByKey("0062");
            ajaxResult.put("requiredStatus", configKey);
            configKey = this.sysEmrConfigService.selectSysEmrConfigByKey("006201");
            ajaxResult.put("requiredStatus_archiater", configKey);
            String outTimeFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("008001");
            ajaxResult.put("outTimeFlag", outTimeFlag);
            TdPaItemDocQuery tdPaItemDocQuery = new TdPaItemDocQuery();
            tdPaItemDocQuery.setDocCd(SecurityUtils.getLoginUser().getUser().getBasEmployee().getEmplNumber());
            tdPaItemDocQuery.setOrderFlag("9");
            List<TdPaItemDocQuery> queryList = this.tdPaItemDocQueryService.selectTdPaItemDocQueryList(tdPaItemDocQuery);
            ajaxResult.put("icdFlag", queryList != null && !queryList.isEmpty() ? ((TdPaItemDocQuery)queryList.get(0)).getQueryStatus() : "0");
            tdPaItemDocQuery.setOrderFlag("13");
            List<TdPaItemDocQuery> list = this.tdPaItemDocQueryService.selectTdPaItemDocQueryList(tdPaItemDocQuery);
            ajaxResult.put("opeFlag", list != null && !list.isEmpty() ? ((TdPaItemDocQuery)list.get(0)).getQueryStatus() : "0");
            if (caFlag.equals("1") && mrHpDetail != null & mrHpDetail.getMrState() != null && (mrHpDetail.getMrState().equals("03") || mrHpDetail.getMrState().equals("04"))) {
               Map<String, String> signMap = this.mrHpService.selectMrHpSignPicList(mrHpDetail.getRecordId());
               ajaxResult.put("signMap", signMap);
            }

            String dipFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0074");
            ajaxResult.put("dipFlag", dipFlag);
            String dipFactory = this.sysEmrConfigService.selectSysEmrConfigByKey("007401");
            ajaxResult.put("dipFactory", dipFactory);
         }
      } catch (Exception e) {
         this.log.error("病案首页患者相关信息查询出现异常", e);
         ajaxResult = AjaxResult.error("病案首页患者相关信息查询出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('mrhp:hp:signSave')")
   @PostMapping({"/signSave"})
   public AjaxResult signSave(@RequestBody MrHpVo mrHpVo, @RequestBody EmrSignData emrSignData, @RequestBody MrHpAttachVo mrHpAttachVo) {
      AjaxResult ajaxResult = AjaxResult.success("签名成功");
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      boolean flag = true;

      try {
         mrHpVo.setMrHpAttachVo(mrHpAttachVo);
         MrHp mrHp = this.mrHpService.selectMrHpById(mrHpVo.getRecordId());
         String caFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0001");
         if ("01".equals(mrHp.getMrState())) {
            flag = false;
            ajaxResult = AjaxResult.error("暂存状态不可签名");
         }

         if ("03".equals(mrHp.getMrState())) {
            flag = false;
            ajaxResult = AjaxResult.error("签名已成功，请勿重复提交");
         }

         String lbMrHpFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0086");
         String mrisFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("000301");
         if (flag && lbMrHpFlag.equals("1") && mrisFlag.equals("1") && lbMrHpFlag.equals("0")) {
            EmrQcStatusMes statusMes = this.commonService.queryEmrQcStatus(mrHp.getPatientId());
            if (statusMes != null && !statusMes.getFlag()) {
               flag = statusMes.getFlag();
               ajaxResult = AjaxResult.error(statusMes.getMsg());
            }
         }

         List<EmrQcListVo> qcExcepationList = new ArrayList();
         if (mrHpVo.getEmrQcListList() != null && mrHpVo.getEmrQcListList().size() > 0) {
            qcExcepationList = (List)mrHpVo.getEmrQcListList().stream().filter((s) -> StringUtils.isNotEmpty(s.getTreatDesc()) && s.getQcState().equals("1")).collect(Collectors.toList());
         }

         if (flag) {
            List<EmrQcListVo> list = this.mrHpService.mrHpCheckOut(mrHpVo);
            if (list != null && list.size() > 0) {
               flag = false;
               ajaxResult = AjaxResult.error("有质控缺陷，请处理后再保存");
               qcExcepationList.addAll(list);
               ajaxResult.put("emrQcList", qcExcepationList);
            }
         }

         List<MrHpDia> mzDiaList = CollectionUtils.isNotEmpty(mrHpVo.getMrHpDiaXYList()) ? (List)mrHpVo.getMrHpDiaXYList().stream().filter((t) -> t.getDiaItem().equals("01") && t.getDiaClass().equals("01") && StringUtils.isNotBlank(t.getDiaCd())).collect(Collectors.toList()) : null;
         if (flag && CollectionUtils.isEmpty(mzDiaList)) {
            flag = false;
            ajaxResult = AjaxResult.error("没有门诊主要诊断，请处理后再保存");
         }

         if (flag && caFlag.equals("1")) {
            if (flag && StringUtils.isEmpty(emrSignData.getOldText())) {
               flag = false;
               ajaxResult = AjaxResult.error("病案原文不能为空");
            }

            if (flag && StringUtils.isEmpty(emrSignData.getSignText())) {
               flag = false;
               ajaxResult = AjaxResult.error("签名值不能为空");
            }

            if (flag && StringUtils.isEmpty(emrSignData.getCertSn())) {
               flag = false;
               ajaxResult = AjaxResult.error("证书序列号不能为空");
            }

            if (flag) {
               if (StringUtils.isNotBlank(mrHpVo.getMobileSignFlag()) && mrHpVo.getMobileSignFlag().equals("1")) {
                  if (!sysUser.getUserName().equals(mrHpVo.getMobileSignUserName())) {
                     flag = false;
                     ajaxResult = AjaxResult.error("签名人和登录人不一致");
                  }

                  if (flag && !mrHpVo.getMobileSignUserName().equals(mrHpVo.getAttDocCd()) && !mrHpVo.getMobileSignUserName().equals(mrHpVo.getResDocCd()) && !mrHpVo.getMobileSignUserName().equals(mrHpVo.getHdCd()) && !mrHpVo.getMobileSignUserName().equals(mrHpVo.getArcDocCd())) {
                     flag = false;
                     ajaxResult = AjaxResult.error("只有病案中科主任,主任医师,主治医师和住院医师可以签名");
                  }
               } else {
                  BasCertInfo basCertInfo = this.basCertInfoService.selectBasCertInfoByCerSn(emrSignData.getCertSn());
                  if (basCertInfo != null) {
                     if (!sysUser.getUserName().equals(basCertInfo.getEmployeenumber())) {
                        flag = false;
                        ajaxResult = AjaxResult.error("签名人和登录人不一致");
                     }

                     if (flag && !basCertInfo.getEmployeenumber().equals(mrHpVo.getAttDocCd()) && !basCertInfo.getEmployeenumber().equals(mrHpVo.getResDocCd()) && !basCertInfo.getEmployeenumber().equals(mrHpVo.getHdCd()) && !basCertInfo.getEmployeenumber().equals(mrHpVo.getArcDocCd())) {
                        flag = false;
                        ajaxResult = AjaxResult.error("只有病案中科主任,主任医师,主治医师和住院医师可以签名");
                     }
                  } else {
                     flag = false;
                     ajaxResult = AjaxResult.error("未查询到签名信息");
                  }
               }

               if (flag) {
               }
            }

            if (flag) {
               this.mrHpService.saveSignAndMrHp(mrHpVo, emrSignData);
               List<DiagInfo> diagInfoList = this.diagInfoService.selectPatientMainDiag(mrHpVo.getPatientId(), "02", "01");
               mrHpVo.setDiagInfoList(diagInfoList);
               List<SysDictData> rc013List = this.sysDictDataService.selectDictDataByType("RC013");

               try {
                  this.emrFileService.syncEmrFileAddToEs(mrHpVo.getPatientId());
               } catch (Exception e) {
                  this.log.error("患者:" + mrHpVo.getPatientId() + "签名人:" + mrHpVo.getAttDocCd() + "登陆人:" + SecurityUtils.getLoginUser().getUser().getUserName() + " 向ES推送病历对象中的诊断信息出现异常-------------", e);
               }

               Map<String, String> signMap = this.mrHpService.selectMrHpSignPicList(mrHpVo.getRecordId());
               ajaxResult.put("signMap", signMap);
               ajaxResult.put("emrQcList", qcExcepationList);
            }
         }

         if (flag && !caFlag.equals("1")) {
            if (flag && !sysUser.getUserName().equals(mrHpVo.getAttDocCd()) && !sysUser.getUserName().equals(mrHpVo.getResDocCd()) && !sysUser.getUserName().equals(mrHpVo.getHdCd()) && !sysUser.getUserName().equals(mrHpVo.getArcDocCd())) {
               flag = false;
               ajaxResult = AjaxResult.error("只有病案中科主任,主任医师,主治医师和住院医师可以签名");
            }

            if (flag) {
               this.mrHpService.saveMrHp(mrHpVo);
               MrHpFee mrHpFee = new MrHpFee();
               mrHpFee.setRecordId(mrHpVo.getRecordId());
               List<MrHpFee> feeList = this.iMrHpFeeService.selectMrHpFeeList(mrHpFee);
               mrHpVo.setMrHpFeeList(feeList);
               List<DiagInfo> diagInfoList = this.diagInfoService.selectPatientMainDiag(mrHpVo.getPatientId(), "02", "01");
               mrHpVo.setDiagInfoList(diagInfoList);
               List<SysDictData> rc013List = this.sysDictDataService.selectDictDataByType("RC013");

               try {
                  this.emrFileService.syncEmrFileAddToEs(mrHpVo.getPatientId());
               } catch (Exception e) {
                  this.log.error("患者:" + mrHpVo.getPatientId() + "签名人:" + mrHpVo.getAttDocCd() + "登陆人:" + SecurityUtils.getLoginUser().getUser().getUserName() + " 向ES推送病历对象中的诊断信息出现异常-------------", e);
               }

               ajaxResult.put("emrQcList", qcExcepationList);
            }
         }
      } catch (Exception e) {
         this.log.error("病案首页签名保存出现异常", e);
         ajaxResult = AjaxResult.error("病案首页签名保存出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('mrhp:hp:cancelSign')")
   @PutMapping({"/cancelSign"})
   public AjaxResult cancelSign(@RequestBody MrHpVo mrHpVo) {
      AjaxResult ajaxResult = AjaxResult.success("取消签名成功");
      boolean flag = true;

      try {
         if (mrHpVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && mrHpVo.getRecordId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("病案id不能为空");
         }

         MrHp mrHp = this.mrHpService.selectMrHpById(mrHpVo.getRecordId());
         if (flag && mrHp == null) {
            flag = false;
            ajaxResult = AjaxResult.error("未查询到病案信息");
         }

         if (flag && mrHp.getMrState() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("病案状态不能为空");
         }

         String lbMrHpFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0086");
         String mrisFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("000301");
         if (flag && lbMrHpFlag.equals("1") && mrisFlag.equals("1") && lbMrHpFlag.equals("0")) {
            EmrQcStatusMes statusMes = this.commonService.queryEmrQcStatus(mrHp.getPatientId());
            if (statusMes != null && !statusMes.getFlag()) {
               flag = statusMes.getFlag();
               ajaxResult = AjaxResult.error(statusMes.getMsg());
            }
         }

         if (flag && "04".equals(mrHp.getMrState())) {
            flag = false;
            ajaxResult = AjaxResult.error("当前病案首页已经提取，请联系病案室退后首页后再试");
         }

         if (flag && !"03".equals(mrHp.getMrState())) {
            flag = false;
            ajaxResult = AjaxResult.error("病案未签名，不可取消签名");
         }

         if (flag) {
            SysUser sysUser = SecurityUtils.getLoginUser().getUser();
            String caFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0001");
            if (caFlag.equals("1")) {
               EmrSignRecord emrSignRecord = this.emrSignRecordService.selectEmrSignRecordByFileIdAndDoc(Long.parseLong(mrHpVo.getRecordId()) + "", sysUser.getUserName());
               if (emrSignRecord == null) {
               }
            } else if (!sysUser.getUserName().equals(mrHp.getAttDocCd()) && !sysUser.getUserName().equals(mrHp.getResDocCd()) && !sysUser.getUserName().equals(mrHp.getHdCd()) && !sysUser.getUserName().equals(mrHp.getArcDocCd())) {
               flag = false;
               ajaxResult = AjaxResult.error("只有病案中科主任,主任医师,主治医师和住院医师可以取消签名");
            }

            if (flag) {
               this.mrHpService.mrHpCancelSign(mrHp);
            }
         }
      } catch (Exception e) {
         this.log.error("病案首页取消签名出现异常", e);
         ajaxResult = AjaxResult.error("病案首页取消签名出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('mrhp:hp:printView,qc:flow:term,pat:info:emrAllList,qc:statis:checkCase')")
   @GetMapping({"/printView"})
   public AjaxResult printView(MrHpVo mrHpVo) {
      AjaxResult ajaxResult = new AjaxResult();
      new MrHpVo();
      boolean flag = true;

      try {
         if (mrHpVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && mrHpVo.getPatientId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("患者id不能为空");
         }

         if (flag) {
            String emrFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0050");
            if (StringUtils.isNotBlank(emrFlag) && emrFlag.equals("1")) {
               SysDept sysDept = SecurityUtils.getLoginUser().getUser().getDept();
               String mrType = sysDept.getMrClass();
               if ("4".equals(sysDept.getSysFlag())) {
                  mrType = "ZY";
               }

               mrHpVo.setMrType(mrType);
               MrHpVo var9 = this.mrHpService.selectMrHpPrintViewHis(mrHpVo);
               if (var9 == null) {
                  ajaxResult = AjaxResult.success((Object)(new MrHpVo()));
               } else {
                  var9.setMrType(mrType);
                  ajaxResult = AjaxResult.success((Object)var9);
               }
            } else {
               MrHpVo mrHpDetail = this.mrHpService.selectMrHpPrintView(mrHpVo);
               if (mrHpDetail == null) {
                  ajaxResult = AjaxResult.success((Object)(new MrHpVo()));
               } else {
                  ajaxResult = AjaxResult.success((Object)mrHpDetail);
               }

               String caFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0001");
               if (caFlag.equals("1") && mrHpDetail != null && mrHpDetail.getMrState() != null && (mrHpDetail.getMrState().equals("03") || mrHpDetail.getMrState().equals("04"))) {
                  Map<String, String> signMap = this.mrHpService.selectMrHpSignPicList(mrHpDetail.getRecordId());
                  ajaxResult.put("signMap", signMap);
               }

               String lis = this.mrHpService.getLis(mrHpVo.getPatientId());
               mrHpDetail.setLis(lis);
            }
         }
      } catch (Exception e) {
         this.log.error("病案首页预览打印查询出现异常", e);
         ajaxResult = AjaxResult.error("病案首页预览打印查询出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('mrhp:hp:printView,qc:flow:term,pat:info:emrAllList,qc:statis:checkCase')")
   @GetMapping({"/getPrintPersonInfoDetail"})
   public AjaxResult getPrintPersonInfoDetail(MrHpVo mrHpVo) {
      AjaxResult result = new AjaxResult();
      boolean flag = true;
      if (mrHpVo == null) {
         flag = false;
         result = AjaxResult.error("参数不能为空");
      }

      if (flag && mrHpVo.getPatientId() == null) {
         flag = false;
         result = AjaxResult.error("患者id不能为空");
      }

      try {
         if (flag) {
            MrHpPrintVo mrHpPrintVo = this.mrHpService.getPrintPersonInfoDetail(mrHpVo);
            result = AjaxResult.success((Object)mrHpPrintVo);
         }
      } catch (Exception e) {
         this.log.error("病案首页预览打印查询出现异常", e);
         result = AjaxResult.error("病案首页预览打印查询出现异常");
      }

      return result;
   }

   @PreAuthorize("@ss.hasAnyPermi('mrhp:hp:save,mrhp:hp:checkQcFlaw,pat:visitinfo:mrhp')")
   @PostMapping({"/checkQcFlaw"})
   public AjaxResult checkQcFlaw(@RequestBody MrHpVo mrHpVo, @RequestBody MrHpAttachVo mrHpAttachVo) {
      AjaxResult ajaxResult = AjaxResult.success("检查成功");

      try {
         mrHpVo.setMrHpAttachVo(mrHpAttachVo);
         if (StringUtils.isEmpty(mrHpVo.getPatientId())) {
            ajaxResult = AjaxResult.error("患者id参数不能为空");
         } else {
            List<EmrQcListVo> qcExcepationList = new ArrayList();
            if (mrHpVo.getEmrQcListList() != null && mrHpVo.getEmrQcListList().size() > 0) {
               qcExcepationList = (List)mrHpVo.getEmrQcListList().stream().filter((s) -> StringUtils.isNotEmpty(s.getTreatDesc()) && s.getQcState().equals("1")).collect(Collectors.toList());
            }

            List<EmrQcListVo> list = this.mrHpService.mrHpCheckOut(mrHpVo);
            qcExcepationList.addAll(list);
            ajaxResult.put("emrQcList", qcExcepationList);
         }
      } catch (Exception e) {
         this.log.error("病案首页检查缺陷出现异常", e);
         ajaxResult = AjaxResult.error("病案首页检查缺陷出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('mrhp:hp:save,mrhp:hp:mrHpDetail,pat:visitinfo:mrhp')")
   @GetMapping({"/getCode"})
   public AjaxResult getCode() {
      AjaxResult ajaxResult = new AjaxResult();

      try {
         ajaxResult.put("diaId", SnowIdUtils.uniqueLongHex());
      } catch (Exception e) {
         this.log.error("生成id出现异常", e);
         ajaxResult = AjaxResult.error("生成id出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('mrhp:hp:signSave')")
   @PostMapping({"/syncMrHpToHis"})
   public AjaxResult syncMrHpToHis(@RequestBody MrHpVo mrHpVoParam) {
      AjaxResult ajaxResult = new AjaxResult();

      try {
         SyncDatasource syncDatasource = this.syncDatasourceService.selectSyncDatasourceByCode(DataSourceType.mrHpToPbHis.toString());
         mrHpVoParam = mrHpVoParam == null ? new MrHpVo() : mrHpVoParam;
         mrHpVoParam.setMrState("03");
         List<MrHpVo> mrHpVoList = this.mrHpService.selectMrHpList(mrHpVoParam);
         if (mrHpVoList != null && !mrHpVoList.isEmpty()) {
            String[] dictStr = new String[]{"RC035", "s022", "RC003", "RC038", "RC032"};
            List<SysDictData> dictList = this.sysDictDataService.selectDictDataListByType(dictStr);

            for(MrHpVo mrHpVo : mrHpVoList) {
               MrHpOpe opeParam = new MrHpOpe();
               opeParam.setRecordId(mrHpVo.getRecordId());
               List<MrHpOpe> opeList = this.mrHpOpeService.selectMrHpOpeList(opeParam);
               MrHpFee feeParam = new MrHpFee();
               feeParam.setRecordId(mrHpVo.getRecordId());
               List<MrHpFee> feeList = this.iMrHpFeeService.selectMrHpFeeList(feeParam);
               MrHpDia zy = new MrHpDia();
               zy.setRecordId(mrHpVo.getRecordId());
               zy.setDiaType("ZY");
               MrHpDia xy = new MrHpDia();
               xy.setRecordId(mrHpVo.getRecordId());
               xy.setDiaType("XY");
               List<MrHpDia> zyList = this.mrHpDiaService.selectMrHpDiaList(zy);
               List<MrHpDia> xyList = this.mrHpDiaService.selectMrHpDiaList(xy);

               for(SysDictData sysDictData : (List)dictList.stream().filter((s) -> s.getDictType().equals("RC035")).collect(Collectors.toList())) {
                  if (StringUtils.isNotBlank(mrHpVo.getNationCd()) && sysDictData.getDictValue().equals(mrHpVo.getNationCd())) {
                     mrHpVo.setNation(sysDictData.getDictLabel());
                  }
               }

               for(SysDictData sysDictData : (List)dictList.stream().filter((s) -> s.getDictType().equals("RC038")).collect(Collectors.toList())) {
                  if (StringUtils.isNotBlank(mrHpVo.getCitizenshipCd()) && sysDictData.getDictValue().equals(mrHpVo.getCitizenshipCd())) {
                     mrHpVo.setCitizenship(sysDictData.getDictLabel());
                  }
               }

               for(SysDictData sysDictData : (List)dictList.stream().filter((s) -> s.getDictType().equals("s022")).collect(Collectors.toList())) {
                  if (StringUtils.isNotBlank(mrHpVo.getCardTypeCd()) && sysDictData.getDictValue().equals(mrHpVo.getCardTypeCd())) {
                     mrHpVo.setCardTypeName(sysDictData.getDictLabel());
                  }
               }

               for(SysDictData sysDictData : (List)dictList.stream().filter((s) -> s.getDictType().equals("RC003")).collect(Collectors.toList())) {
                  if (StringUtils.isNotBlank(mrHpVo.getProTypeCd()) && sysDictData.getDictValue().equals(mrHpVo.getProTypeCd())) {
                     mrHpVo.setProTypeName(sysDictData.getDictLabel());
                  }
               }

               for(SysDictData sysDictData : (List)dictList.stream().filter((s) -> s.getDictType().equals("RC032")).collect(Collectors.toList())) {
                  if (StringUtils.isNotBlank(mrHpVo.getPayTypeCd()) && sysDictData.getDictValue().equals(mrHpVo.getPayTypeCd())) {
                     mrHpVo.setPayTypeName(sysDictData.getDictLabel());
                  }
               }

               List<MrHpOpeVo> opes = new ArrayList();
               List<MrHpFee> fees = new ArrayList();
               List<MrHpDia> zys = new ArrayList();
               List<MrHpDia> xys = new ArrayList();
               if (opeList != null && !opeList.isEmpty()) {
                  List<MrHpOpe> opeListTemp = (List)opeList.stream().filter((t) -> t.getRecordId().equals(mrHpVo.getRecordId())).collect(Collectors.toList());
                  if (CollectionUtils.isNotEmpty(opeListTemp)) {
                     for(MrHpOpe mrHpOpe : opeListTemp) {
                        if (!StringUtils.isEmpty(mrHpOpe.getOprName())) {
                           mrHpOpe.setRecordNo(mrHpVo.getRecordNo());
                           mrHpOpe.setInpNo(mrHpVo.getInpNo());
                           mrHpOpe.setVisitId(mrHpVo.getVisitId());
                           mrHpOpe.setPatientName(mrHpVo.getPatientName());
                           MrHpOpeVo mrHpOpeVo = new MrHpOpeVo();
                           BeanUtils.copyProperties(mrHpOpe, mrHpOpeVo);
                           opes.add(mrHpOpeVo);
                        }
                     }
                  }
               }

               if (CollectionUtils.isNotEmpty(feeList)) {
                  List<MrHpFee> feeListTemp = (List)feeList.stream().filter((t) -> t.getRecordId().equals(mrHpVo.getRecordId())).collect(Collectors.toList());
                  if (CollectionUtils.isNotEmpty(feeListTemp)) {
                     for(MrHpFee mrHpFee : feeListTemp) {
                        mrHpFee.setRecordNo(mrHpVo.getRecordNo());
                        mrHpFee.setInpNo(mrHpVo.getInpNo());
                        mrHpFee.setVisitId(mrHpVo.getVisitId());
                        mrHpFee.setPatientName(mrHpVo.getPatientName());
                        fees.add(mrHpFee);
                     }
                  }
               }

               if (CollectionUtils.isNotEmpty(zyList)) {
                  zys = (List)zyList.stream().filter((t) -> t.getRecordId().equals(mrHpVo.getRecordId())).collect(Collectors.toList());
               }

               if (CollectionUtils.isNotEmpty(xyList)) {
                  xys = (List)xyList.stream().filter((t) -> t.getRecordId().equals(mrHpVo.getRecordId())).collect(Collectors.toList());
               }

               List<DiagInfo> diagInfoList = this.diagInfoService.selectPatientMainDiag(mrHpVo.getPatientId(), "02", "01");
               List<SysDictData> rc013List = this.sysDictDataService.selectDictDataByType("RC013");
               mrHpVo.setDiagInfoList(diagInfoList);
               mrHpVo.setMrHpOpeList(opes);
               mrHpVo.setMrHpFeeList(fees);
               mrHpVo.setMrHpDiaZYList(zys);
               mrHpVo.setMrHpDiaXYList(xys);
               this.mrHpService.syncMrHpVoToHis(mrHpVo, rc013List);
            }
         }
      } catch (Exception e) {
         this.log.error("同步历史病案首页数据到his", e);
         ajaxResult = AjaxResult.error("同步历史病案首页数据到his");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('mrhp:hp:mrHpDetail,pat:visitinfo:mrhp')")
   @GetMapping({"/selectPatientInfoDetail"})
   public AjaxResult selectPatientInfoDetail(String admissionNo) {
      new AjaxResult();
      if (StringUtils.isEmpty(admissionNo)) {
         AjaxResult var5 = AjaxResult.error("患者住院号不能为空");
         return var5;
      } else {
         AjaxResult ajaxResult;
         try {
            PatientInfoDetailVo detailVo = this.mrHpService.selectPatientInfoDetail(admissionNo);
            ajaxResult = AjaxResult.success((Object)detailVo);
         } catch (Exception e) {
            this.log.error("病案首页提取患者信息查询出现异常", e);
            ajaxResult = AjaxResult.error("病案首页提取患者信息查询出现异常");
         }

         return ajaxResult;
      }
   }

   @PreAuthorize("@ss.hasAnyPermi('mrhp:hp:save')")
   @PostMapping({"/selectDipQcUrl"})
   public AjaxResult selectDipQcUrl(@RequestBody MrHpRecordDipReq req) {
      AjaxResult result = new AjaxResult();
      boolean flag = true;
      if (req == null) {
         flag = false;
         result = AjaxResult.error("参数不能为空");
      }

      if (flag && StringUtils.isBlank(req.getPatientId())) {
         flag = false;
         result = AjaxResult.error("患者id不能为空");
      }

      MrHpVo mrHpVo = flag ? this.mrHpService.selectMrHpByPatientId(req.getPatientId()) : null;
      if (flag && mrHpVo == null) {
         flag = false;
         result = AjaxResult.error("患者信息不存在");
      }

      try {
         if (flag) {
            SysUser user = SecurityUtils.getLoginUser().getUser();
            req.setOrgCode(user.getHospital().getChsCode());
            req.setDeptCode(mrHpVo.getInDeptCd());
            req.setUserCode(user.getUserName());
            req.setQutyType("cyzk");
            MrHpRecordDipResp resp = this.mrHpDipQcService.selectDipQcUrl(req);
            if (resp != null && 200 == resp.getCode()) {
               result = AjaxResult.success("质控成功", resp.getData());
            } else {
               result = AjaxResult.error(resp.getMsg());
            }
         }
      } catch (Exception e) {
         this.log.error("获取dip病案首页质控界面地址出现异常", e);
         result = AjaxResult.error("获取dip病案首页质控界面地址出现异常，清联系系统管理员");
      }

      return result;
   }
}
