package com.emr.project.mrhp.controller;

import com.alibaba.fastjson.JSON;
import com.emr.common.utils.DateUtils;
import com.emr.common.utils.Obj2MapUtil;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.StringUtils;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.project.common.service.ICommonService;
import com.emr.project.docOrder.domain.TdPaItemDocQuery;
import com.emr.project.docOrder.service.ITdPaItemDocQueryService;
import com.emr.project.emr.domain.EmrSignData;
import com.emr.project.mrhp.domain.MrHp;
import com.emr.project.mrhp.domain.MrHpFee;
import com.emr.project.mrhp.domain.mris.TdCmDiagSave;
import com.emr.project.mrhp.domain.mris.TdCmHpLineVo;
import com.emr.project.mrhp.domain.mris.TdCmOperSave;
import com.emr.project.mrhp.domain.vo.MrHpVo;
import com.emr.project.mrhp.service.IMrHpFeeService;
import com.emr.project.mrhp.service.IMrHpService;
import com.emr.project.mrhp.service.IMrisService;
import com.emr.project.other.domain.BasCertInfo;
import com.emr.project.other.service.IBasCertInfoService;
import com.emr.project.pat.domain.DiagInfo;
import com.emr.project.pat.service.IDiagInfoService;
import com.emr.project.qc.domain.EmrQcStatusMes;
import com.emr.project.qc.domain.vo.EmrQcListVo;
import com.emr.project.sys.domain.OpeTypeEnum;
import com.emr.project.system.domain.BsStaff;
import com.emr.project.system.domain.SysDictData;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.service.IBsStaffService;
import com.emr.project.system.service.ISysDictDataService;
import com.emr.project.system.service.ISysEmrConfigService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/newmrhp/hp"})
public class MrisController extends BaseController {
   @Autowired
   private IMrisService mrisService;
   @Autowired
   private IBsStaffService bsStaffService;
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;
   @Autowired
   private ICommonService commonService;
   @Autowired
   private IMrHpService mrHpService;
   @Autowired
   private IBasCertInfoService basCertInfoService;
   @Autowired
   private IDiagInfoService diagInfoService;
   @Autowired
   private ISysDictDataService sysDictDataService;
   @Autowired
   private IMrHpFeeService iMrHpFeeService;
   @Autowired
   private ITdPaItemDocQueryService tdPaItemDocQueryService;

   @GetMapping({"/mrHpDetail"})
   public AjaxResult selectMrHpPatientDetail(String patientId) {
      new AjaxResult();

      AjaxResult ajaxResult;
      try {
         if (StringUtils.isEmpty(patientId)) {
            ajaxResult = AjaxResult.error("患者就诊id参数不能为空");
         } else {
            TdCmHpLineVo mrHpDetail = this.mrisService.selectMrHpPatientDetail(patientId);
            BsStaff bsStaff = new BsStaff();
            bsStaff.setStaffType("bmy");
            List<BsStaff> bsStaffList = this.bsStaffService.selectBsStaffByStaffType(bsStaff);
            if (CollectionUtils.isNotEmpty(bsStaffList) && bsStaffList.size() == 1 && StringUtils.isBlank(mrHpDetail.getCoder_cd())) {
               mrHpDetail.setCoder_cd(((BsStaff)bsStaffList.get(0)).getStaffCode());
               mrHpDetail.setCoder_name(((BsStaff)bsStaffList.get(0)).getStaffName());
            }

            ajaxResult = AjaxResult.success((Object)mrHpDetail);
            String caFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0001");
            String caType = this.sysEmrConfigService.selectSysEmrConfigByKey("0002");
            ajaxResult.put("caFlag", caFlag);
            ajaxResult.put("caType", caType);
            String configKey = this.sysEmrConfigService.selectSysEmrConfigByKey("0062");
            ajaxResult.put("requiredStatus", configKey);
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
            String dipFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0074");
            ajaxResult.put("dipFlag", dipFlag);
         }
      } catch (Exception e) {
         this.log.error("病案首页患者相关信息查询出现异常", e);
         ajaxResult = AjaxResult.error("病案首页患者相关信息查询出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('mrhp:hp:save,pat:visitinfo:mrhp')")
   @Log(
      title = "病案首页",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult save(@RequestBody TdCmHpLineVo mrHpVo) {
      AjaxResult ajaxResult = AjaxResult.success("保存成功");
      boolean flag = true;

      try {
         if (flag && StringUtils.isBlank(mrHpVo.getPatient_id())) {
            flag = false;
            ajaxResult = AjaxResult.error("患者id参数不能为空");
         }

         if (flag && !"01".equals(mrHpVo.getMrState())) {
            List<EmrQcListVo> qcExcepationList = this.mrisService.getQcErrorList(mrHpVo);
            List<EmrQcListVo> list = this.mrisService.mrHpCheckOut(mrHpVo);
            if (list != null && list.size() > 0) {
               flag = false;
               ajaxResult = AjaxResult.error("有质控缺陷，请处理后再保存");
            }

            qcExcepationList.addAll(list);
            ajaxResult.put("emrQcList", qcExcepationList);
            mrHpVo.setEmrQcListList(qcExcepationList);
            if (flag) {
               List<TdCmOperSave> mrHpOpeList = mrHpVo.getTdCmOperList();
               mrHpOpeList.removeIf((t) -> StringUtils.isEmpty(t.getOper_name()));
               if (mrHpOpeList != null && mrHpOpeList.size() > 0) {
                  for(TdCmOperSave ope : mrHpOpeList) {
                     if (StringUtils.isNotBlank(ope.getOper_name())) {
                        String opeType = ope.getOper_type();
                        String opeMain = ope.getOper_main();
                        if (StringUtils.isBlank(opeType)) {
                           flag = false;
                           ajaxResult = AjaxResult.error("病案续页-手术信息：手术类型没有填写，请确认后再保存！");
                        }

                        if (StringUtils.isBlank(opeMain)) {
                           flag = false;
                           ajaxResult = AjaxResult.error("病案续页-手术信息：主手术没有填写，请确认后再保存！");
                        }

                        if (flag && (ope.getOper_begin_dt() == null || ope.getOper_end_dt() == null)) {
                           flag = false;
                           ajaxResult = AjaxResult.error("病案续页-手术信息：手术开始时间及结束时间不能为空，请确认后再保存！");
                        }

                        if (flag && opeType.equals(OpeTypeEnum.OPERATION.getTypeCode())) {
                           if (StringUtils.isBlank(ope.getOper_level_code())) {
                              flag = false;
                              ajaxResult = AjaxResult.error("病案续页-手术信息：手术级别不能为空，请确认后再保存！");
                           }

                           if (flag && StringUtils.isBlank(ope.getOper_inci_code())) {
                              flag = false;
                              ajaxResult = AjaxResult.error("病案续页-手术信息：手术切口类别不能为空，请确认后再保存！");
                           }

                           if (flag && StringUtils.isBlank(ope.getOper_heal_code())) {
                              flag = false;
                              ajaxResult = AjaxResult.error("病案续页-手术信息：手术愈合等级不能为空，请确认后再保存！");
                           }

                           if (flag && StringUtils.isBlank(ope.getAnest_meth_code())) {
                              flag = false;
                              ajaxResult = AjaxResult.error("病案续页-手术信息：麻醉方法不能为空，请确认后再保存！");
                           }

                           if (flag && StringUtils.isBlank(ope.getAnest_name())) {
                              flag = false;
                              ajaxResult = AjaxResult.error("病案续页-手术信息：麻醉医师姓名不能为空，请确认后再保存！");
                           }
                        }
                     }
                  }

                  if (flag) {
                     Map<String, List<TdCmOperSave>> opeMainMap = (Map)mrHpOpeList.stream().collect(Collectors.groupingBy(TdCmOperSave::getOper_main));
                     if (opeMainMap.containsKey("1")) {
                        List<TdCmOperSave> mrHpOpeVos = (List)opeMainMap.get("1");
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
            }

            if (flag && mrHpVo.getInhos_time() != null && mrHpVo.getOut_time() != null && mrHpVo.getReal_inhos_days() != null) {
               Date inhosTime = mrHpVo.getInhos_time();
               Date outTime = mrHpVo.getOut_time();
               SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
               String inhosTimeStr = sdf.format(inhosTime);
               inhosTime = sdf.parse(inhosTimeStr);
               String outTimeStr = sdf.format(outTime);
               outTime = sdf.parse(outTimeStr);
               int inhosDays = mrHpVo.getReal_inhos_days().intValue();
               int diffDay = DateUtils.getDiffDay(outTime, inhosTime);
               if (inhosDays < diffDay - 1 || inhosDays > diffDay + 1) {
                  flag = false;
                  ajaxResult = AjaxResult.error("出院天数设置超出范围，请核对后再保存！");
               }
            }
         }

         if (flag) {
            this.mrisService.saveMrHp(mrHpVo);
         }
      } catch (Exception e) {
         this.log.error("保存病案首页出现异常", e);
         ajaxResult = AjaxResult.error("保存病案首页出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('mrhp:hp:signSave')")
   @PostMapping({"/signSave"})
   public AjaxResult signSave(@RequestBody TdCmHpLineVo mrHpVo, @RequestBody EmrSignData emrSignData) {
      AjaxResult ajaxResult = AjaxResult.success("签名成功");
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      boolean flag = true;

      try {
         MrHp mrHp = this.mrHpService.selectMrHpById(mrHpVo.getRecord_id());
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
            List<EmrQcListVo> list = this.mrisService.mrHpCheckOut(mrHpVo);
            if (list != null && list.size() > 0) {
               flag = false;
               ajaxResult = AjaxResult.error("有质控缺陷，请处理后再保存");
               qcExcepationList.addAll(list);
               ajaxResult.put("emrQcList", qcExcepationList);
            }
         }

         List<TdCmDiagSave> mzDiaList = CollectionUtils.isNotEmpty(mrHpVo.getTdCmDiagXyList()) ? (List)mrHpVo.getTdCmDiagXyList().stream().filter((t) -> t.getDiag_item().equals("01") && t.getDiag_class().equals("01") && StringUtils.isNotBlank(t.getDiag_cd())).collect(Collectors.toList()) : null;
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
               BasCertInfo basCertInfo = this.basCertInfoService.selectBasCertInfoByCerSn(emrSignData.getCertSn());
               if (basCertInfo != null) {
                  if (!sysUser.getUserName().equals(basCertInfo.getEmployeenumber())) {
                     flag = false;
                     ajaxResult = AjaxResult.error("签名人和登录人不一致");
                     if (flag && !basCertInfo.getEmployeenumber().equals(mrHpVo.getAtt_doc_cd()) && !basCertInfo.getEmployeenumber().equals(mrHpVo.getRes_doc_cd())) {
                        flag = false;
                        ajaxResult = AjaxResult.error("只有病案中主治医师和住院医师可以签名");
                     }
                  }
               } else {
                  flag = false;
                  ajaxResult = AjaxResult.error("未查询到签名信息");
               }
            }

            if (flag) {
               this.mrisService.saveSignAndMrHp(mrHpVo, emrSignData);
               Map<String, Object> map = Obj2MapUtil.objectToMap(mrHpVo);
               MrHpVo mrHpVo1 = new MrHpVo();
               if (!map.isEmpty()) {
                  String ss = JSON.toJSONString(map);
                  mrHpVo1 = (MrHpVo)JSON.parseObject(ss, MrHpVo.class);
               }

               List<DiagInfo> diagInfoList = this.diagInfoService.selectPatientMainDiag(mrHpVo.getPatient_id(), "02", "01");
               mrHpVo1.setDiagInfoList(diagInfoList);
               List<SysDictData> rc013List = this.sysDictDataService.selectDictDataByType("RC013");

               try {
                  this.mrHpService.syncMrHpVoToHis(mrHpVo1, rc013List);
               } catch (Exception e) {
                  this.log.error("患者:" + mrHpVo.getPatient_id() + "签名人:" + mrHpVo.getAtt_doc_cd() + "登陆人:" + SecurityUtils.getLoginUser().getUser().getUserName() + "往sqlserver插入签名信息异常-----------", e);
               }

               Map<String, String> signMap = this.mrHpService.selectMrHpSignPicList(mrHpVo.getRecord_id());
               ajaxResult.put("signMap", signMap);
               ajaxResult.put("emrQcList", qcExcepationList);
            }
         }

         if (flag && !caFlag.equals("1")) {
            if (flag && !sysUser.getUserName().equals(mrHpVo.getAtt_doc_cd()) && !sysUser.getUserName().equals(mrHpVo.getRes_doc_cd())) {
               flag = false;
               ajaxResult = AjaxResult.error("只有病案中主治医师和住院医师可以签名");
            }

            if (flag) {
               this.mrisService.saveMrHp(mrHpVo);
               Map<String, Object> map = Obj2MapUtil.objectToMap(mrHpVo);
               MrHpVo mrHpVo1 = new MrHpVo();
               if (!map.isEmpty()) {
                  String ss = JSON.toJSONString(map);
                  mrHpVo1 = (MrHpVo)JSON.parseObject(ss, MrHpVo.class);
               }

               MrHpFee mrHpFee = new MrHpFee();
               mrHpFee.setRecordId(mrHpVo.getRecord_id());
               List<MrHpFee> feeList = this.iMrHpFeeService.selectMrHpFeeList(mrHpFee);
               mrHpVo1.setMrHpFeeList(feeList);
               List<DiagInfo> diagInfoList = this.diagInfoService.selectPatientMainDiag(mrHpVo.getPatient_id(), "02", "01");
               mrHpVo1.setDiagInfoList(diagInfoList);
               List<SysDictData> rc013List = this.sysDictDataService.selectDictDataByType("RC013");

               try {
                  this.mrHpService.syncMrHpVoToHis(mrHpVo1, rc013List);
               } catch (Exception e) {
                  this.log.error("患者:" + mrHp.getPatientId() + "签名人:" + mrHp.getAttDocCd() + "登陆人:" + sysUser.getUserName() + "往sqlserver插入签名信息异常-----------", e);
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

   @PreAuthorize("@ss.hasAnyPermi('mrhp:hp:save,mrhp:hp:checkQcFlaw,pat:visitinfo:mrhp')")
   @PostMapping({"/checkQcFlaw"})
   public AjaxResult checkQcFlaw(@RequestBody TdCmHpLineVo mrHpVo) {
      AjaxResult ajaxResult = AjaxResult.success("检查成功");

      try {
         if (StringUtils.isEmpty(mrHpVo.getPatient_id())) {
            ajaxResult = AjaxResult.error("患者id参数不能为空");
         } else {
            List<EmrQcListVo> qcExcepationList = new ArrayList();
            if (mrHpVo.getEmrQcListList() != null && mrHpVo.getEmrQcListList().size() > 0) {
               qcExcepationList = (List)mrHpVo.getEmrQcListList().stream().filter((s) -> StringUtils.isNotEmpty(s.getTreatDesc()) && s.getQcState().equals("1")).collect(Collectors.toList());
            }

            List<EmrQcListVo> list = this.mrisService.mrHpCheckOut(mrHpVo);
            qcExcepationList.addAll(list);
            ajaxResult.put("emrQcList", qcExcepationList);
         }
      } catch (Exception e) {
         this.log.error("病案首页检查缺陷出现异常", e);
         ajaxResult = AjaxResult.error("病案首页检查缺陷出现异常");
      }

      return ajaxResult;
   }
}
