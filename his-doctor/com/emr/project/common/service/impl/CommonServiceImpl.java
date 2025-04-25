package com.emr.project.common.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.emr.common.utils.DateUtils;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.StringUtils;
import com.emr.project.common.domain.vo.ReplaceUrlParamVo;
import com.emr.project.common.mapper.CommonMapper;
import com.emr.project.common.service.ICommonService;
import com.emr.project.holiday.service.ITmBsHolidayService;
import com.emr.project.operation.domain.Baseinfomation;
import com.emr.project.operation.mapper.BaseinfomationMapper;
import com.emr.project.other.domain.ImpDoctorTemp;
import com.emr.project.other.service.IImpDoctorTempService;
import com.emr.project.pat.domain.Visitinfo;
import com.emr.project.qc.domain.EmrQcFlow;
import com.emr.project.qc.domain.EmrQcStatusMes;
import com.emr.project.qc.service.IEmrQcFlowService;
import com.emr.project.sys.domain.TmBsDefineConfigureP;
import com.emr.project.system.domain.SysDept;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.service.ISysDeptService;
import com.emr.project.system.service.ISysEmrConfigService;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonServiceImpl implements ICommonService {
   @Autowired
   private CommonMapper commonMapper;
   @Autowired
   private ISysDeptService sysDeptService;
   @Autowired
   private ITmBsHolidayService tmBsHolidayService;
   @Autowired
   private IEmrQcFlowService emrQcFlowService;
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;
   @Autowired
   private BaseinfomationMapper baseinfomationMapper;
   @Autowired
   private IImpDoctorTempService impDoctorTempService;

   public Date getDbSysdate() throws Exception {
      String currDateStr = this.commonMapper.getSysdate();
      Date currDate = DateUtils.parseDate(currDateStr, new String[]{"yyyy-MM-dd HH:mm:ss.S"});
      return currDate;
   }

   public String getSystimestamp() throws Exception {
      return this.commonMapper.getSystimestamp();
   }

   public String replaceUrlParam(ReplaceUrlParamVo replaceUrlParamVo, String urlStr) throws Exception {
      if (StringUtils.isNotBlank(urlStr)) {
         Field[] fields = replaceUrlParamVo.getClass().getDeclaredFields();

         for(int i = 0; i < fields.length; ++i) {
            String fieldName = fields[i].getName();
            Method m = replaceUrlParamVo.getClass().getMethod("get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1));
            String strValue = (String)m.invoke(replaceUrlParamVo);
            String paramStr = "@" + fieldName;
            if (StringUtils.isNotBlank(strValue) && urlStr.contains(paramStr)) {
               urlStr = urlStr.replaceAll(paramStr, strValue);
            }
         }
      }

      return urlStr;
   }

   public TmBsDefineConfigureP selectDefineConfigureByCode(String code) throws Exception {
      return this.commonMapper.selectDefineConfigureByCode(code);
   }

   public Boolean isQCDept() throws Exception {
      SysDept sysDept = SecurityUtils.getLoginUser().getUser().getDept();
      List<SysDept> qcDeptList = this.sysDeptService.selectdeptListByTypeCode("14");
      List<String> qcDeptCodeList = (List<String>)(CollectionUtils.isNotEmpty(qcDeptList) ? (List)qcDeptList.stream().map((t) -> t.getDeptCode()).collect(Collectors.toList()) : new ArrayList(1));
      return qcDeptCodeList.contains(sysDept.getDeptCode()) ? true : false;
   }

   public Date queryFileDate(Date leaveHospitalDate, int days) throws Exception {
      if (days != 0 && leaveHospitalDate != null) {
         Date date = leaveHospitalDate;
         int i = 0;

         while(i != days + 1) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(5, 1);
            date = calendar.getTime();
            calendar.setTime(date);
            int week = calendar.get(7) - 1;
            if (week != 6 && week != 0) {
               int count = this.tmBsHolidayService.selectHolidayCount(date, "1");
               if (count == 0) {
                  ++i;
               }
            } else {
               int count = this.tmBsHolidayService.selectHolidayCount(date, "2");
               if (count != 0) {
                  ++i;
               }
            }
         }

         Calendar cal = Calendar.getInstance();
         cal.setTime(date);
         cal.set(11, 0);
         cal.set(12, 0);
         cal.set(13, 0);
         return cal.getTime();
      } else {
         return null;
      }
   }

   public boolean dayIsHoliday(Date day) throws Exception {
      boolean isHoliday = false;
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(day);
      int week = calendar.get(7) - 1;
      if (week != 6 && week != 0) {
         int count = this.tmBsHolidayService.selectHolidayCount(day, "1");
         if (count > 0) {
            isHoliday = true;
         }
      } else {
         int count = this.tmBsHolidayService.selectHolidayCount(day, "2");
         if (count == 0) {
            isHoliday = true;
         }
      }

      return isHoliday;
   }

   public boolean timeIsWorkingTime(Date time) throws Exception {
      boolean isWorkingTime = false;
      String beginEndTimeStr = this.sysEmrConfigService.selectSysEmrConfigByKey("0100");
      JSONObject beginEndTimeObj = JSONObject.parseObject(beginEndTimeStr);
      String dayStr = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, time);
      String amBeginTimeStr = dayStr + beginEndTimeObj.get("amBeginTime");
      String amEndTimeStr = dayStr + beginEndTimeObj.get("amEndTime");
      String pmBeginTimeStr = dayStr + beginEndTimeObj.get("pmBeginTime");
      String pmEndTimeStr = dayStr + beginEndTimeObj.get("pmEndTime");
      Date amBeginTime = DateUtils.parseDate(amBeginTimeStr, new String[]{DateUtils.YYYY_MM_DD_HH_MM_SS});
      Date amEndTime = DateUtils.parseDate(amEndTimeStr, new String[]{DateUtils.YYYY_MM_DD_HH_MM_SS});
      Date pmBeginTime = DateUtils.parseDate(pmBeginTimeStr, new String[]{DateUtils.YYYY_MM_DD_HH_MM_SS});
      Date pmEndTime = DateUtils.parseDate(pmEndTimeStr, new String[]{DateUtils.YYYY_MM_DD_HH_MM_SS});
      if (time.compareTo(amBeginTime) >= 0 && time.compareTo(amEndTime) < 0 || time.compareTo(pmBeginTime) >= 0 && time.compareTo(pmEndTime) < 0) {
         isWorkingTime = true;
      }

      return isWorkingTime;
   }

   public boolean dateIsWorkingTime(Date time) throws Exception {
      boolean isWorkingTime = false;
      boolean isHoliday = this.dayIsHoliday(time);
      if (!isHoliday) {
         isWorkingTime = this.timeIsWorkingTime(time);
      }

      return isWorkingTime;
   }

   public EmrQcStatusMes queryEmrQcStatus(String patientId) throws Exception {
      EmrQcStatusMes mes = new EmrQcStatusMes();
      mes.setFlag(Boolean.TRUE);
      String isUse = this.sysEmrConfigService.selectSysEmrConfigByKey("0086");
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      EmrQcFlow emrQcFlow = this.emrQcFlowService.selectEmrQcFlowById(sysUser.getHospital().getOrgCode(), patientId);
      if (emrQcFlow != null && StringUtils.isNotBlank(isUse) && isUse.equals("1")) {
         switch (emrQcFlow.getMrState()) {
            case "10":
               mes.setFlag(Boolean.FALSE);
               mes.setMsg("当前病历已提交科室质控，请在质控信息页面撤销科室质控后再试！");
               break;
            case "12":
            case "14":
            case "16":
               mes.setFlag(Boolean.FALSE);
               mes.setMsg("当前病历已归档/申请归档，请在质控信息页面召回病历后再试！");
         }
      }

      return mes;
   }

   public List nursingLevelList() throws Exception {
      return this.commonMapper.selectNursingLevelList();
   }

   public Baseinfomation findBaseInfo(String patientId) throws Exception {
      return this.baseinfomationMapper.findBaseInfo(patientId);
   }

   public List findBaseInfoByIdcard(String idcard) throws Exception {
      return this.baseinfomationMapper.findBaseInfoByIdcard(idcard);
   }

   public Boolean userHasPat(Visitinfo visitinfo, SysUser user) throws Exception {
      Boolean flag = false;
      if (user.getModule() != null && StringUtils.isNotBlank(user.getModule().getModuleCode()) && user.getModule().getModuleCode().equals("0403")) {
         flag = true;
      } else if (user.getModule() != null && StringUtils.isNotBlank(user.getModule().getModuleCode()) && user.getModule().getModuleCode().equals("0304")) {
         boolean userDeptFlag = user.getDept().getDeptCode().equals(visitinfo.getDeptCd());
         List<ImpDoctorTemp> temp = this.impDoctorTempService.selectTmpByPatAndDocOrDept(visitinfo.getInpNo(), user.getUserName(), user.getDept().getDeptCode());
         boolean userImpFalg = CollectionUtils.isNotEmpty(temp);
         if (userDeptFlag || userImpFalg) {
            flag = true;
         }
      } else {
         flag = true;
      }

      return flag;
   }
}
