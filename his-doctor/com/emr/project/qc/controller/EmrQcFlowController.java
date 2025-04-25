package com.emr.project.qc.controller;

import com.alibaba.fastjson.JSONObject;
import com.emr.common.utils.DateUtils;
import com.emr.common.utils.IPAddressUtil;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.StringUtils;
import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.common.service.ICommonService;
import com.emr.project.emr.service.IIndexService;
import com.emr.project.mrhp.domain.vo.MrHpVo;
import com.emr.project.mrhp.service.IMrHpService;
import com.emr.project.operation.domain.Baseinfomation;
import com.emr.project.operation.domain.Medicalinformation;
import com.emr.project.operation.service.IMedicalinfoService;
import com.emr.project.pat.domain.vo.BackLogVo;
import com.emr.project.pat.domain.vo.VisitinfoVo;
import com.emr.project.pat.service.IVisitinfoService;
import com.emr.project.qc.domain.EmrQcFlow;
import com.emr.project.qc.domain.EmrQcRecord;
import com.emr.project.qc.domain.vo.EmrQcFlowScoreVo;
import com.emr.project.qc.domain.vo.EmrQcFlowStatisticVo;
import com.emr.project.qc.domain.vo.EmrQcFlowVo;
import com.emr.project.qc.domain.vo.RevokeToFileReq;
import com.emr.project.qc.service.IEmrQcFlowService;
import com.emr.project.qc.service.IEmrQcRecordService;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.service.ISysEmrConfigService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.collections.CollectionUtils;
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
@RequestMapping({"/qc/flow"})
public class EmrQcFlowController extends BaseController {
   @Autowired
   private IEmrQcFlowService emrQcFlowService;
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;
   @Autowired
   private IEmrQcRecordService emrQcRecordService;
   @Autowired
   private IVisitinfoService visitinfoService;
   @Autowired
   private IMrHpService mrHpService;
   @Autowired
   private IIndexService indexService;
   @Autowired
   private IMedicalinfoService medicalinfoService;
   @Autowired
   private ICommonService commonService;

   @PreAuthorize("@ss.hasPermi('qc:flow:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(EmrQcFlow emrQcFlow) {
      this.startPage();
      List<EmrQcFlow> list = new ArrayList();

      try {
         list = this.emrQcFlowService.selectEmrQcFlowList(emrQcFlow);
      } catch (Exception e) {
         this.log.error("查询病历质控流程列表出现异常,", e);
      }

      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('qc:flow:dept')")
   @GetMapping({"/deptListNo"})
   public TableDataInfo deptListNo(EmrQcFlowVo emrQcFlow) {
      List<EmrQcFlowVo> list = new ArrayList();

      try {
         emrQcFlow.setMrState("10");
         List<String> deptQcStateList = new ArrayList();
         deptQcStateList.add("0");
         deptQcStateList.add("1");
         emrQcFlow.setDeptQcStateList(deptQcStateList);
         String inhosDayLong = this.sysEmrConfigService.selectSysEmrConfigByKey("0018");
         String costSumHigh = this.sysEmrConfigService.selectSysEmrConfigByKey("0019");
         emrQcFlow.setDayNum(Integer.parseInt(inhosDayLong));
         emrQcFlow.setCostSum(Double.parseDouble(costSumHigh));
         SysUser user = SecurityUtils.getLoginUser().getUser();
         emrQcFlow.setOrgCd(user.getHospital().getOrgCode());
         emrQcFlow.setDeptCd(user.getDept().getDeptCode());
         if (emrQcFlow.getOutTimeEnd() != null) {
            Date outTimeEnd = emrQcFlow.getOutTimeEnd();
            emrQcFlow.setOutTimeEnd(DateUtils.addDays(outTimeEnd, 1));
         }

         emrQcFlow.setQcSection("02");
         this.startPage();
         list = this.emrQcFlowService.selectEmrQcFlowVoListOrderByOutTime(emrQcFlow);
      } catch (Exception e) {
         this.log.error("查询病历质控流程列表出现异常,", e);
      }

      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('qc:flow:dept')")
   @GetMapping({"/deptList"})
   public TableDataInfo deptList(EmrQcFlowVo emrQcFlow) {
      List<EmrQcFlowVo> list = new ArrayList();

      try {
         if (emrQcFlow != null && StringUtils.isBlank(emrQcFlow.getMrState())) {
            List<String> mrStateList = new ArrayList(1);
            mrStateList.add("12");
            mrStateList.add("13");
            mrStateList.add("14");
            mrStateList.add("16");
            emrQcFlow.setMrStateList(mrStateList);
         }

         String inhosDayLong = this.sysEmrConfigService.selectSysEmrConfigByKey("0018");
         String costSumHigh = this.sysEmrConfigService.selectSysEmrConfigByKey("0019");
         emrQcFlow.setDayNum(Integer.parseInt(inhosDayLong));
         emrQcFlow.setCostSum(Double.parseDouble(costSumHigh));
         SysUser user = SecurityUtils.getLoginUser().getUser();
         Medicalinformation medicalinformation = this.medicalinfoService.selectMedicalinfoByPatientId(emrQcFlow.getInpNo());
         if (medicalinformation != null && StringUtils.isNotBlank(medicalinformation.getDepartmentNo())) {
            emrQcFlow.setOrgCd(medicalinformation.getHospitalCode());
            emrQcFlow.setDeptCd(medicalinformation.getDepartmentNo());
         } else {
            emrQcFlow.setOrgCd(user.getHospital().getOrgCode());
            emrQcFlow.setDeptCd(user.getDept().getDeptCode());
         }

         if (emrQcFlow.getApplyFileTimeEnd() != null) {
            Date applyFileTimeEnd = emrQcFlow.getApplyFileTimeEnd();
            emrQcFlow.setApplyFileTimeEnd(DateUtils.addDays(applyFileTimeEnd, 1));
         }

         emrQcFlow.setQcSection("02");
         this.startPage();
         list = this.emrQcFlowService.selectEmrQcFlowVoListOrderByApplyFileTime(emrQcFlow);
      } catch (Exception e) {
         this.log.error("查询病历质控流程列表出现异常,", e);
      }

      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('qc:flow:dept')")
   @GetMapping({"/deptListAll"})
   public TableDataInfo deptListAll(EmrQcFlowVo emrQcFlow) {
      this.startPage();
      List<EmrQcFlowVo> list = new ArrayList();

      try {
         SysUser user = SecurityUtils.getLoginUser().getUser();
         emrQcFlow.setOrgCd(user.getHospital().getOrgCode());
         emrQcFlow.setDeptCd(user.getDept().getDeptCode());
         if (emrQcFlow.getOutTimeEnd() != null) {
            Date outTimeEnd = emrQcFlow.getOutTimeEnd();
            emrQcFlow.setOutTimeEnd(DateUtils.addDays(outTimeEnd, 1));
         }

         list = this.emrQcFlowService.selectEmrQcFlowVoAllList(emrQcFlow);
      } catch (Exception e) {
         this.log.error("查询病历质控流程列表出现异常,", e);
      }

      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('qc:flow:term')")
   @GetMapping({"/termListNo"})
   public TableDataInfo termListNo(EmrQcFlowVo emrQcFlow) {
      List<EmrQcFlowVo> list = new ArrayList();
      if (StringUtils.isNotEmpty(emrQcFlow.getTermQcState()) && !emrQcFlow.getTermQcState().equals("0") && !emrQcFlow.getTermQcState().equals("1")) {
         this.log.error("查询病历质控流程列表出现错误,质控状态类型不合法！");
         return this.getDataTable(list);
      } else {
         try {
            emrQcFlow.setMrState("12");
            List<String> termQcStateList = new ArrayList();
            if (StringUtils.isEmpty(emrQcFlow.getTermQcState())) {
               termQcStateList.add("0");
               termQcStateList.add("1");
            } else {
               termQcStateList.add(emrQcFlow.getTermQcState());
            }

            emrQcFlow.setTermQcStateList(termQcStateList);
            emrQcFlow.setCostSum(emrQcFlow.getCostSum() != null ? emrQcFlow.getCostSum() * (double)10000.0F : null);
            String inhosDayLong = this.sysEmrConfigService.selectSysEmrConfigByKey("0018");
            String costSumHigh = this.sysEmrConfigService.selectSysEmrConfigByKey("0019");
            emrQcFlow.setDayNum(Integer.parseInt(inhosDayLong));
            emrQcFlow.setCostSum(Double.parseDouble(costSumHigh));
            if (emrQcFlow.getDayNum() == null) {
               emrQcFlow.setDayNum(Integer.parseInt(inhosDayLong));
            }

            if (emrQcFlow.getCostSum() == null) {
               emrQcFlow.setCostSum(Double.parseDouble(costSumHigh));
            }

            if (emrQcFlow.getApplyFileTimeEnd() != null) {
               Date applyFileTimeEnd = emrQcFlow.getApplyFileTimeEnd();
               emrQcFlow.setApplyFileTimeEnd(DateUtils.addDays(applyFileTimeEnd, 1));
            }

            emrQcFlow.setQcSection("05");
            String deptCd = StringUtils.isNotBlank(emrQcFlow.getDeptCd()) && emrQcFlow.getDeptCd().equals("000000") ? null : emrQcFlow.getDeptCd();
            emrQcFlow.setDeptCd(deptCd);
            this.startPage();
            list = this.emrQcFlowService.selectEmrQcFlowVoListOrderByApplyFileTime(emrQcFlow);
         } catch (Exception e) {
            this.log.error("查询病历质控流程列表出现异常,", e);
         }

         return this.getDataTable(list);
      }
   }

   @PreAuthorize("@ss.hasPermi('qc:flow:term')")
   @GetMapping({"/termList"})
   public TableDataInfo termList(EmrQcFlowVo emrQcFlow) {
      List<EmrQcFlowVo> list = new ArrayList();

      try {
         String configValue0023 = this.sysEmrConfigService.selectSysEmrConfigByKey("0023");
         String mrstate = configValue0023.equals("1") ? "14" : "16";
         if (StringUtils.isNotBlank(emrQcFlow.getTermReturnFlag()) && emrQcFlow.getTermReturnFlag().equals("1")) {
            mrstate = "13";
         }

         if (emrQcFlow != null && !StringUtils.isNotBlank(emrQcFlow.getMrState())) {
            emrQcFlow.setMrState(mrstate);
         }

         String inhosDayLong = this.sysEmrConfigService.selectSysEmrConfigByKey("0018");
         String costSumHigh = this.sysEmrConfigService.selectSysEmrConfigByKey("0019");
         emrQcFlow.setDayNum(Integer.parseInt(inhosDayLong));
         emrQcFlow.setCostSum(Double.parseDouble(costSumHigh));
         if (emrQcFlow.getFileTimeEnd() != null) {
            Date fileTimeEnd = emrQcFlow.getFileTimeEnd();
            emrQcFlow.setFileTimeEnd(DateUtils.addDays(fileTimeEnd, 1));
         }

         emrQcFlow.setQcSection("05");
         String deptCd = StringUtils.isNotBlank(emrQcFlow.getDeptCd()) && emrQcFlow.getDeptCd().equals("000000") ? null : emrQcFlow.getDeptCd();
         emrQcFlow.setDeptCd(deptCd);
         this.startPage();
         list = this.emrQcFlowService.selectEmrQcFlowVoListOrderByFileTime(emrQcFlow);
      } catch (Exception e) {
         this.log.error("查询病历质控流程列表出现异常,", e);
      }

      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('qc:flow:term')")
   @GetMapping({"/termListAll"})
   public TableDataInfo termListAll(EmrQcFlowVo emrQcFlow) {
      this.startPage();
      List<EmrQcFlowVo> list = new ArrayList();

      try {
         if (emrQcFlow.getOutTimeEnd() != null) {
            Date outTimeEnd = emrQcFlow.getOutTimeEnd();
            emrQcFlow.setOutTimeEnd(DateUtils.addDays(outTimeEnd, 1));
         }

         String deptCd = StringUtils.isNotBlank(emrQcFlow.getDeptCd()) && emrQcFlow.getDeptCd().equals("000000") ? null : emrQcFlow.getDeptCd();
         emrQcFlow.setDeptCd(deptCd);
         list = this.emrQcFlowService.selectEmrQcFlowVoAllList(emrQcFlow);
      } catch (Exception e) {
         this.log.error("查询病历质控流程列表出现异常,", e);
      }

      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('qc:rt:dept')")
   @PostMapping({"/deptApplyFile"})
   public AjaxResult deptApplyFile(@RequestBody EmrQcFlowVo emrQcFlowVo, @RequestBody EmrQcFlowScoreVo emrQcFlowScoreVo, HttpServletRequest request) {
      AjaxResult ajaxResult = AjaxResult.success("申请归档成功");
      boolean flag = true;

      try {
         if (flag && emrQcFlowVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && emrQcFlowScoreVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("评分信息不能为空");
         }

         if (flag && emrQcFlowVo.getFlowId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("患者病历质控流程id不能为空");
         }

         if (flag && emrQcFlowVo.getQcId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("患者病历质控记录id不能为空");
         }

         EmrQcRecord emrQcRecord = flag ? this.emrQcRecordService.selectEmrQcRecordById(emrQcFlowVo.getQcId()) : null;
         if (flag && emrQcRecord == null) {
            flag = false;
            ajaxResult = AjaxResult.error("没有当前患者的质控记录，不能申请归档");
         }

         EmrQcFlow emrQcFlow = flag ? this.emrQcFlowService.selectEmrQcFlowById(emrQcFlowVo.getFlowId()) : null;
         if (flag && emrQcFlow == null) {
            flag = false;
            ajaxResult = AjaxResult.error("没有当前患者的病历质控流程记录，不能申请归档");
         }

         if (flag) {
            emrQcFlowVo.setId(emrQcFlowVo.getFlowId());
            String ip = IPAddressUtil.getIPAddress(request);
            emrQcFlowVo.setIp(ip);
            this.emrQcFlowService.saveScoreInfo(emrQcFlowScoreVo, emrQcFlowVo, emrQcRecord);
            ajaxResult.put("emrQcRecord", emrQcRecord);
         }
      } catch (Exception e) {
         this.log.error("科室质控申请归档出现异常：", e);
         this.log.warn("质控保存-科室质控归档出现异常");
         ajaxResult = AjaxResult.error("科室质控申请归档出现异常，请联系管理员");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('qc:rt:dept')")
   @PostMapping({"/deptGoBack"})
   public AjaxResult deptGoBack(@RequestBody EmrQcFlowVo emrQcFlowVo, HttpServletRequest request) {
      AjaxResult ajaxResult = AjaxResult.success();
      boolean flag = true;

      try {
         if (flag && emrQcFlowVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && emrQcFlowVo.getId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("患者病历质控流程id不能为空");
         }

         if (flag && emrQcFlowVo.getQcId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("患者病历质控记录id不能为空");
         }

         EmrQcRecord emrQcRecord = flag ? this.emrQcRecordService.selectEmrQcRecordById(emrQcFlowVo.getQcId()) : null;
         if (flag && emrQcRecord == null) {
            flag = false;
            ajaxResult = AjaxResult.error("没有当前患者的质控记录，不能申请归档");
         }

         EmrQcFlow emrQcFlow = flag ? this.emrQcFlowService.selectEmrQcFlowById(emrQcFlowVo.getId()) : null;
         if (flag && emrQcFlow == null) {
            flag = false;
            ajaxResult = AjaxResult.error("没有当前患者的病历质控流程记录，不能申请归档");
         }

         if (flag) {
            String ip = IPAddressUtil.getIPAddress(request);
            emrQcFlowVo.setIp(ip);
            this.emrQcFlowService.deptTermGoBack(emrQcFlowVo, emrQcRecord);
            ajaxResult.put("emrQcRecord", emrQcRecord);
         }
      } catch (Exception e) {
         this.log.error("科室质控申请归档出现异常：", e);
         this.log.warn("质控保存-科室质控退回病历出现异常");
         ajaxResult = AjaxResult.error("科室质控申请归档出现异常，请联系管理员");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('qc:rt:term')")
   @PostMapping({"/termApplyFile"})
   public AjaxResult termApplyFile(@RequestBody EmrQcFlowVo emrQcFlowVo, @RequestBody EmrQcFlowScoreVo emrQcFlowScoreVo, HttpServletRequest request) {
      AjaxResult ajaxResult = AjaxResult.success("病历归档成功");
      boolean flag = true;

      try {
         if (flag && emrQcFlowVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && emrQcFlowScoreVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("评分信息不能为空");
         }

         if (flag && emrQcFlowVo.getFlowId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("患者病历质控流程id不能为空");
         }

         if (flag && emrQcFlowVo.getQcId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("患者病历质控记录id不能为空");
         }

         EmrQcRecord emrQcRecord = flag ? this.emrQcRecordService.selectEmrQcRecordById(emrQcFlowVo.getQcId()) : null;
         if (flag && emrQcRecord == null) {
            flag = false;
            ajaxResult = AjaxResult.error("没有当前患者的质控记录，不能申请归档");
         }

         EmrQcFlow emrQcFlow = flag ? this.emrQcFlowService.selectEmrQcFlowById(emrQcFlowVo.getFlowId()) : null;
         if (flag && emrQcFlow == null) {
            flag = false;
            ajaxResult = AjaxResult.error("没有当前患者的病历质控流程记录，不能申请归档");
         }

         if (flag) {
            emrQcFlowVo.setId(emrQcFlowVo.getFlowId());
            String ip = IPAddressUtil.getIPAddress(request);
            emrQcFlowVo.setIp(ip);
            this.emrQcFlowService.saveScoreInfo(emrQcFlowScoreVo, emrQcFlowVo, emrQcRecord);
            String orderFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0050");
            if (orderFlag.equals("1")) {
               Medicalinformation medicalinformation = this.medicalinfoService.selectMedicalinfoByPatientId(emrQcRecord.getPatientId());
               Baseinfomation baseInfo = this.commonService.findBaseInfo(emrQcRecord.getPatientId());
               if (medicalinformation != null && baseInfo != null) {
                  this.log.warn("质控保存-终末质控归档保存his数据写入开始" + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
                  this.emrQcFlowService.saveHisSqgd(medicalinformation, emrQcFlowScoreVo, baseInfo, emrQcFlow);
               }
            }

            ajaxResult.put("emrQcRecord", emrQcRecord);
         }
      } catch (Exception e) {
         this.log.error("科室质控申请归档出现异常：", e);
         this.log.warn("质控保存-终末质控归档出现异常");
         ajaxResult = AjaxResult.error("科室质控申请归档出现异常，请联系管理员");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:rt:term,qc:rt:returnTerm')")
   @PostMapping({"/termGoBack"})
   public AjaxResult termGoBack(@RequestBody EmrQcFlowVo emrQcFlowVo, HttpServletRequest request) {
      AjaxResult ajaxResult = AjaxResult.success();
      boolean flag = true;

      try {
         if (flag && emrQcFlowVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && emrQcFlowVo.getId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("患者病历质控流程id不能为空");
         }

         if (flag && emrQcFlowVo.getQcId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("患者病历质控记录id不能为空");
         }

         EmrQcRecord emrQcRecord = flag ? this.emrQcRecordService.selectEmrQcRecordById(emrQcFlowVo.getQcId()) : null;
         if (flag && emrQcRecord == null) {
            flag = false;
            ajaxResult = AjaxResult.error("没有当前患者的质控记录，不能申请归档");
         }

         EmrQcFlow emrQcFlow = flag ? this.emrQcFlowService.selectEmrQcFlowById(emrQcFlowVo.getId()) : null;
         if (flag && emrQcFlow == null) {
            flag = false;
            ajaxResult = AjaxResult.error("没有当前患者的病历质控流程记录，不能申请归档");
         }

         if (flag) {
            String ip = IPAddressUtil.getIPAddress(request);
            emrQcFlowVo.setIp(ip);
            this.emrQcFlowService.deptTermGoBack(emrQcFlowVo, emrQcRecord);
            String orderFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0050");
            if (orderFlag.equals("1")) {
               Medicalinformation medicalinformation = this.medicalinfoService.selectMedicalinfoByPatientId(emrQcRecord.getPatientId());
               if (medicalinformation != null) {
                  this.log.warn("质控保存-终末质控退回病历开始删除视图yy_dzbl_sqgd数据" + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
                  this.emrQcFlowService.deleteHisSqgd(medicalinformation.getAdmissionNo(), medicalinformation.getHospitalizedCount());
               }
            }

            ajaxResult.put("emrQcRecord", emrQcRecord);
         }
      } catch (Exception e) {
         this.log.error("退回病历出现异常：", e);
         this.log.warn("质控保存-终末质控-退回病历出现异常");
         ajaxResult = AjaxResult.error("退回病历出现异常，请联系管理员");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:flow:query,emr:index:qcApp')")
   @GetMapping({"/getInfo"})
   public AjaxResult getInfo(EmrQcFlow emrQcFlow) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      boolean flag = true;

      try {
         if (flag && emrQcFlow == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && emrQcFlow.getPatientId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("患者id不能为空");
         }

         if (flag) {
            EmrQcFlow emrQcFlow1 = this.emrQcFlowService.selectEmrQcFlowById(sysUser.getHospital().getOrgCode(), emrQcFlow.getPatientId());
            ajaxResult.put("mrState", emrQcFlow1 == null ? "00" : emrQcFlow1.getMrState());
            String deptFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0083");
            ajaxResult.put("deptFlag", deptFlag);
         }
      } catch (Exception e) {
         this.log.error("出现异常：", e);
         ajaxResult = AjaxResult.error("申请科室质控出现异常，请联系管理员");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:flow:add,qc:flow:edit,emr:index:qcApp')")
   @GetMapping({"/submitQcFlow"})
   public AjaxResult submitQcFlow(EmrQcFlowVo emrQcFlow, HttpServletRequest request) {
      AjaxResult ajaxResult = AjaxResult.success("申请成功");
      boolean flag = true;

      try {
         if (flag && emrQcFlow == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && emrQcFlow.getPatientId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("患者id不能为空");
         }

         if (flag && StringUtils.isEmpty(emrQcFlow.getMrState())) {
            flag = false;
            ajaxResult = AjaxResult.error("申请状态不能为空");
         }

         if (flag) {
            String ip = IPAddressUtil.getIPAddress(request);
            this.emrQcFlowService.saveEmrQcFlow(emrQcFlow, ip, (Long)null);
         }
      } catch (Exception e) {
         this.log.error("申请科室质控出现异常：", e);
         ajaxResult = AjaxResult.error("申请科室质控出现异常，请联系管理员");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:flow:add,qc:flow:edit,emr:index:qcApp')")
   @GetMapping({"/isSubmitQcFlow"})
   public AjaxResult isSubmitQcFlow(EmrQcFlow emrQcFlow) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");
      boolean flag = true;

      try {
         if (flag && emrQcFlow == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && emrQcFlow.getPatientId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("患者id不能为空");
         }

         VisitinfoVo visitinfoVo = flag ? this.visitinfoService.selectVisitinfoByPatientId(emrQcFlow.getPatientId()) : null;
         if (flag && visitinfoVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("没有此患者信息");
         }

         if (flag) {
            emrQcFlow.setOrgCd(visitinfoVo.getOrgCd());
            ajaxResult = this.emrQcFlowService.isSubmitQcFlowDecide(emrQcFlow);
         }
      } catch (Exception e) {
         this.log.error("申请科室质控出现异常：", e);
         ajaxResult = AjaxResult.error("申请科室质控出现异常，请联系管理员");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('qc:flow:export')")
   @Log(
      title = "病历质控流程",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(EmrQcFlow emrQcFlow) {
      List<EmrQcFlow> list = this.emrQcFlowService.selectEmrQcFlowList(emrQcFlow);
      ExcelUtil<EmrQcFlow> util = new ExcelUtil(EmrQcFlow.class);
      return util.exportExcel(list, "病历质控流程数据");
   }

   @PreAuthorize("@ss.hasPermi('qc:flow:query')")
   @GetMapping({"/{orgCd}"})
   public AjaxResult getInfo(@PathVariable("orgCd") String orgCd) {
      String patientId = "";
      return AjaxResult.success((Object)this.emrQcFlowService.selectEmrQcFlowById(orgCd, patientId));
   }

   @PreAuthorize("@ss.hasPermi('qc:flow:add')")
   @Log(
      title = "病历质控流程",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody EmrQcFlow emrQcFlow) {
      return this.toAjax(this.emrQcFlowService.insertEmrQcFlow(emrQcFlow));
   }

   @PreAuthorize("@ss.hasPermi('qc:flow:edit')")
   @Log(
      title = "病历质控流程",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody EmrQcFlow emrQcFlow) {
      return this.toAjax(this.emrQcFlowService.updateEmrQcFlow(emrQcFlow));
   }

   @PreAuthorize("@ss.hasAnyPermi('emr:home:list')")
   @GetMapping({"/unSubmitQc"})
   public TableDataInfo unSubmitQc() {
      new TableDataInfo();

      TableDataInfo tableDataInfo;
      try {
         this.startPage();
         List<BackLogVo> list = this.emrQcFlowService.selectUnSubmitQcList((String)null);
         tableDataInfo = this.getDataTable(list);
      } catch (Exception e) {
         this.log.error("待提交质控-待办事项出现异常：", e);
         tableDataInfo = new TableDataInfo(500, "待提交质控-待办事项出现异常");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasAnyPermi('emr:home:list')")
   @GetMapping({"/unReturnRecord"})
   public TableDataInfo unReturnRecord() {
      new TableDataInfo();

      TableDataInfo tableDataInfo;
      try {
         this.startPage();
         List<BackLogVo> list = this.emrQcFlowService.selectUnReturnRecordList((String)null);
         tableDataInfo = this.getDataTable(list);
      } catch (Exception e) {
         this.log.error("待提交质控-待办事项出现异常：", e);
         tableDataInfo = new TableDataInfo(500, "待提交质控-待办事项出现异常");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:flow:toFile,revoke:log:list')")
   @PostMapping({"/toFile"})
   public AjaxResult indexToFile(String patientId) {
      AjaxResult ajaxResult = AjaxResult.success("归档成功");
      Boolean flag = true;
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();

      try {
         if (flag && StringUtils.isBlank(patientId)) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空！");
         }

         EmrQcFlow emrQcFlow = flag ? this.emrQcFlowService.selectEmrQcFlowById(sysUser.getHospital().getOrgCode(), patientId) : null;
         if (flag && emrQcFlow != null && (emrQcFlow.getMrState().equals("16") || emrQcFlow.getMrState().equals("14"))) {
            flag = false;
            ajaxResult = AjaxResult.error("当前患者的病历已归档，不能重复归档！");
         }

         VisitinfoVo visitinfoVo = flag ? this.visitinfoService.selectVisitinfoByPatientId(patientId) : null;
         if (flag && (visitinfoVo == null || visitinfoVo.getOutTime() == null)) {
            flag = false;
            ajaxResult = AjaxResult.error("患者暂未出院，不能归档病历！");
         }

         if (flag) {
            MrHpVo mrHpVo = this.mrHpService.selectMrHpByPatientId(patientId);
            String orderFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0050");
            if (orderFlag.equals("0") && flag && (mrHpVo == null || StringUtils.isEmpty(mrHpVo.getMrState()) || !mrHpVo.getMrState().equals("03"))) {
               flag = false;
               ajaxResult = AjaxResult.error("病案首页还未完成签名，不能归档病历！");
            }
         }

         if (flag) {
            this.emrQcFlowService.indexToFile(patientId, emrQcFlow);
         }
      } catch (Exception e) {
         ajaxResult = AjaxResult.error("患者病历直接归档出现异常，请联系管理员！");
         this.log.error("患者病历直接归档出现异常：", e);
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:flow:revokeToFile')")
   @PostMapping({"/revokeToFile"})
   public AjaxResult revokeToFile(@RequestBody RevokeToFileReq req) {
      AjaxResult ajaxResult = AjaxResult.success("保存成功");
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      if (StringUtils.isEmpty(req.getPatientId())) {
         ajaxResult = AjaxResult.error("患者住院号不能为空！");
         return ajaxResult;
      } else if (StringUtils.isEmpty(req.getRevokeType())) {
         ajaxResult = AjaxResult.error("撤销类型不能为空！");
         return ajaxResult;
      } else if (!req.getRevokeType().equals("1") && !req.getRevokeType().equals("2")) {
         ajaxResult = AjaxResult.error("撤销类型错误！");
         return ajaxResult;
      } else {
         try {
            VisitinfoVo visitinfoVo = this.visitinfoService.selectVisitinfoByPatientId(req.getPatientId());
            if (visitinfoVo == null || visitinfoVo.getOutTime() == null) {
               ajaxResult = AjaxResult.error("患者暂未出院，不能撤销质控！");
               return ajaxResult;
            }

            EmrQcFlow emrQcFlow = this.emrQcFlowService.selectEmrQcFlowById(sysUser.getHospital().getOrgCode(), req.getPatientId());
            if (emrQcFlow == null) {
               ajaxResult = AjaxResult.error("没有当前患者的病历质控流程记录，不能撤销质控");
               return ajaxResult;
            }

            if (emrQcFlow.getMrState().equals("16") || emrQcFlow.getMrState().equals("14")) {
               ajaxResult = AjaxResult.error("当前患者的病历已归档，不能撤销质控！");
               return ajaxResult;
            }

            if (req.getRevokeType().equals("1")) {
               if (!emrQcFlow.getMrState().equals("10")) {
                  ajaxResult = AjaxResult.error("当前患者的病历状态不为提交科室质控，不能撤销质控！");
                  return ajaxResult;
               }

               EmrQcRecord emrQcRecord = this.emrQcRecordService.selectEmrQcRecordByPatientSection(req.getPatientId(), "02", (Long)null);
               if (emrQcRecord != null) {
                  String state = emrQcRecord.getState();
                  if (state.equals("1")) {
                     ajaxResult = AjaxResult.error("当前患者病历正在质控中，不能撤销质控！");
                     return ajaxResult;
                  }
               }
            } else {
               if (!emrQcFlow.getMrState().equals("12")) {
                  ajaxResult = AjaxResult.error("当前患者的病历状态不为提交终末质控，不能撤销质控！");
                  return ajaxResult;
               }

               EmrQcRecord emrQcRecord = this.emrQcRecordService.selectEmrQcRecordByPatientSection(req.getPatientId(), "05", (Long)null);
               if (emrQcRecord != null) {
                  String state = emrQcRecord.getState();
                  if (state.equals("1")) {
                     ajaxResult = AjaxResult.error("当前患者病历正在质控中，不能撤销质控！");
                     return ajaxResult;
                  }
               }
            }

            this.emrQcFlowService.updateMrStatus(emrQcFlow, req);
         } catch (Exception e) {
            this.log.error("撤销质控出现异常，请联系管理员！", e);
            ajaxResult = AjaxResult.error("撤销质控出现异常，请联系管理员！");
         }

         return ajaxResult;
      }
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:statis:checkStatis')")
   @GetMapping({"/getQcFlowStatistic"})
   public TableDataInfo getQcFlowStatistic(EmrQcFlowVo emrQcFlowVo) {
      TableDataInfo tableDataInfo = new TableDataInfo();
      boolean flag = true;

      try {
         if (emrQcFlowVo == null) {
            flag = false;
            tableDataInfo = new TableDataInfo(500, "参数不能为空");
         }

         if (emrQcFlowVo.getOutHospitalTimeEnd() != null) {
            Date outTimeEnd = emrQcFlowVo.getOutHospitalTimeEnd();
            emrQcFlowVo.setOutHospitalTimeEnd(DateUtils.addDays(outTimeEnd, 1));
         }

         if (flag) {
            this.startPage();
            List<EmrQcFlowStatisticVo> emrQcFlowList = this.emrQcFlowService.getQcFlowStatistic(emrQcFlowVo);
            List<EmrQcFlowStatisticVo> emrQcFlowAllList = this.emrQcFlowService.getQcFlowStatistic(emrQcFlowVo);
            EmrQcFlowStatisticVo emrQcFlowAllCountList = this.emrQcFlowService.getQcFlowStatisticAllCount(emrQcFlowVo);
            emrQcFlowVo.setOutDeptTotal(emrQcFlowAllCountList.getTotal());
            EmrQcFlowVo emrQcFlowVo1 = this.emrQcFlowService.getQcFlowVoStatistic(emrQcFlowVo, emrQcFlowAllList);
            tableDataInfo = this.getDataTable(emrQcFlowList);
            emrQcFlowVo.setOutHospitalTotal(emrQcFlowVo1.getOutHospitalTotal());
            emrQcFlowVo.setYgdTotal(emrQcFlowVo1.getYgdTotal());
            emrQcFlowVo.setJjProportion(emrQcFlowVo1.getJjProportion());
            emrQcFlowVo.setFxlProportion(emrQcFlowVo1.getFxlProportion());
            emrQcFlowVo.setTwoProportion(emrQcFlowVo1.getTwoProportion());
            emrQcFlowVo.setThreeProportion(emrQcFlowVo1.getThreeProportion());
            emrQcFlowVo.setSevenProportion(emrQcFlowVo1.getSevenProportion());
            Object json = JSONObject.toJSON(emrQcFlowVo);
            tableDataInfo.setObject(json);
         }
      } catch (Exception e) {
         this.log.error("查询归档病历缺陷出现异常：", e);
         tableDataInfo = new TableDataInfo(500, "查询归档病历缺陷出现异常");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:statis:checkStatis')")
   @GetMapping({"/getQcFlowStatisticExport"})
   public AjaxResult getQcFlowStatisticExport(EmrQcFlowVo emrQcFlowVo, HttpServletResponse response) {
      AjaxResult ajaxResult = AjaxResult.success("导出成功");
      boolean flag = true;

      try {
         if (flag && emrQcFlowVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (emrQcFlowVo.getOutHospitalTimeEnd() != null) {
            Date outTimeEnd = emrQcFlowVo.getOutHospitalTimeEnd();
            emrQcFlowVo.setOutHospitalTimeEnd(DateUtils.addDays(outTimeEnd, 1));
         }

         if (flag) {
            return this.emrQcFlowService.getQcFlowStatisticExport(emrQcFlowVo, response);
         }
      } catch (Exception e) {
         ajaxResult = AjaxResult.error("导出归档病历统计出现异常");
         this.log.error("导出归档病历统计出现异常，", e);
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:statis:checkStatis')")
   @GetMapping({"/getQcFlowStatisticByDept"})
   public TableDataInfo getQcFlowStatisticByDept(EmrQcFlowVo emrQcFlowVo) {
      TableDataInfo tableDataInfo = new TableDataInfo();
      boolean flag = true;

      try {
         if (emrQcFlowVo == null) {
            flag = false;
            tableDataInfo = new TableDataInfo(500, "参数不能为空");
         }

         if (emrQcFlowVo.getOutHospitalTimeEnd() != null) {
            Date outTimeEnd = emrQcFlowVo.getOutHospitalTimeEnd();
            emrQcFlowVo.setOutHospitalTimeEnd(DateUtils.addDays(outTimeEnd, 1));
         }

         if (flag) {
            int outDeptTotal = this.emrQcFlowService.getOutDeptTotal(emrQcFlowVo);
            emrQcFlowVo.setOutDeptTotal(outDeptTotal);
            if (outDeptTotal > 0) {
               List<EmrQcFlowStatisticVo> emrQcFlowList = this.emrQcFlowService.getQcFlowStatisticByDept(emrQcFlowVo);
               List<EmrQcFlowStatisticVo> emrQcFlowListAll = this.emrQcFlowService.getQcFlowStatistic(emrQcFlowVo);
               EmrQcFlowStatisticVo emrQcFlowAllCountList = this.emrQcFlowService.getQcFlowStatisticAllCount(emrQcFlowVo);
               emrQcFlowVo.setOutDeptTotal(emrQcFlowAllCountList.getTotal());
               EmrQcFlowVo emrQcFlowVo1 = this.emrQcFlowService.getQcFlowVoStatistic(emrQcFlowVo, emrQcFlowListAll);
               if (CollectionUtils.isNotEmpty(emrQcFlowList)) {
                  tableDataInfo = this.getDataTable(emrQcFlowList, (long)((EmrQcFlowStatisticVo)emrQcFlowList.get(0)).getTotal());
               }

               emrQcFlowVo.setOutHospitalTotal(emrQcFlowVo1.getOutHospitalTotal());
               emrQcFlowVo.setYgdTotal(emrQcFlowVo1.getYgdTotal());
               emrQcFlowVo.setJjProportion(emrQcFlowVo1.getJjProportion());
               emrQcFlowVo.setFxlProportion(emrQcFlowVo1.getFxlProportion());
               emrQcFlowVo.setTwoProportion(emrQcFlowVo1.getTwoProportion());
               emrQcFlowVo.setThreeProportion(emrQcFlowVo1.getThreeProportion());
               emrQcFlowVo.setSevenProportion(emrQcFlowVo1.getSevenProportion());
               Object json = JSONObject.toJSON(emrQcFlowVo);
               tableDataInfo.setObject(json);
            }
         }
      } catch (Exception e) {
         this.log.error("查询归档率出现异常：", e);
         tableDataInfo = new TableDataInfo(500, "查询归档率出现异常");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:statis:checkStatis')")
   @GetMapping({"/getQcFlowStatisticByDeptExport"})
   public AjaxResult getQcFlowStatisticByDeptExport(EmrQcFlowVo emrQcFlowVo, HttpServletResponse response) {
      AjaxResult ajaxResult = AjaxResult.success("导出成功");
      boolean flag = true;

      try {
         if (flag && emrQcFlowVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (emrQcFlowVo.getOutHospitalTimeEnd() != null) {
            Date outTimeEnd = emrQcFlowVo.getOutHospitalTimeEnd();
            emrQcFlowVo.setOutHospitalTimeEnd(DateUtils.addDays(outTimeEnd, 1));
         }

         if (flag) {
            int outDeptTotal = this.emrQcFlowService.getOutDeptTotal(emrQcFlowVo);
            if (outDeptTotal > 0) {
               EmrQcFlowStatisticVo emrQcFlowAllCountList = this.emrQcFlowService.getQcFlowStatisticAllCount(emrQcFlowVo);
               emrQcFlowVo.setOutDeptTotal(emrQcFlowAllCountList.getTotal());
               return this.emrQcFlowService.getQcFlowStatisticByDeptExport(emrQcFlowVo, response);
            }
         }
      } catch (Exception e) {
         ajaxResult = AjaxResult.error("导出归档率出现异常");
         this.log.error("导出归档率出现异常，", e);
      }

      return ajaxResult;
   }
}
