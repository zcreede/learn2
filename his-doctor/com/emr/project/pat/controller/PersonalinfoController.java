package com.emr.project.pat.controller;

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
import com.emr.project.operation.domain.Baseinfomation;
import com.emr.project.pat.domain.Personalinfo;
import com.emr.project.pat.domain.vo.AlleInfoVo;
import com.emr.project.pat.domain.vo.PersonalinfoForSxVo;
import com.emr.project.pat.domain.vo.PersonalinfoVo;
import com.emr.project.pat.domain.vo.VisitinfoVo;
import com.emr.project.pat.service.IAlleInfoService;
import com.emr.project.pat.service.IBabyInfoService;
import com.emr.project.pat.service.IPersonalinfoService;
import com.emr.project.pat.service.ISyncHisPatientInfoService;
import com.emr.project.pat.service.ITransinfoService;
import com.emr.project.pat.service.IVisitinfoService;
import com.emr.project.system.domain.SysDictData;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.domain.vo.SqlVo;
import com.emr.project.system.service.ISysDeptService;
import com.emr.project.system.service.ISysDictDataService;
import com.emr.project.system.service.ISysEmrConfigService;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
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
@RequestMapping({"/pat/personalinfo"})
public class PersonalinfoController extends BaseController {
   @Autowired
   private IPersonalinfoService personalinfoService;
   @Autowired
   private ISyncHisPatientInfoService syncHisPatientInfoService;
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;
   @Autowired
   private ISysDictDataService sysDictDataService;
   @Autowired
   private IAlleInfoService alleInfoService;
   @Autowired
   private RedisCache redisCache;
   @Autowired
   private ICommonService commonService;
   @Autowired
   private ITransinfoService transinfoService;
   @Autowired
   private IVisitinfoService visitinfoService;
   @Autowired
   private IBabyInfoService babyInfoService;
   @Autowired
   private ISysDeptService sysDeptService;

   @PreAuthorize("@ss.hasPermi('pat:personalinfo:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(Personalinfo personalinfo) {
      this.startPage();
      List<Personalinfo> list = this.personalinfoService.selectPersonalinfoList(personalinfo);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('pat:personalinfo:export')")
   @Log(
      title = "患者基本信息",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(Personalinfo personalinfo) {
      List<Personalinfo> list = this.personalinfoService.selectPersonalinfoList(personalinfo);
      ExcelUtil<Personalinfo> util = new ExcelUtil(Personalinfo.class);
      return util.exportExcel(list, "患者基本信息数据");
   }

   @PreAuthorize("@ss.hasPermi('pat:personalinfo:query')")
   @GetMapping({"/{patientId}"})
   public AjaxResult getInfo(@PathVariable("patientId") String patientId) {
      return AjaxResult.success((Object)this.personalinfoService.selectPersonalinfoById(patientId));
   }

   @PreAuthorize("@ss.hasPermi('pat:personalinfo:add')")
   @Log(
      title = "患者基本信息",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody Personalinfo personalinfo) {
      return this.toAjax(this.personalinfoService.insertPersonalinfo(personalinfo));
   }

   @PreAuthorize("@ss.hasAnyPermi('pat:personalinfo:edit')")
   @Log(
      title = "患者基本信息",
      businessType = BusinessType.UPDATE
   )
   @PutMapping({"/editPatInfo"})
   public AjaxResult edit(@RequestBody PersonalinfoVo personalinfo) {
      AjaxResult ajaxResult = AjaxResult.success("保存成功");

      try {
         if (StringUtils.isEmpty(personalinfo.getPatientId())) {
            ajaxResult = AjaxResult.error("患者id不能为空");
         } else {
            this.personalinfoService.updatePersonAndVisit(personalinfo);
         }
      } catch (Exception e) {
         this.log.error("修改患者基本信息出现异常", e);
         ajaxResult = AjaxResult.error("修改患者基本信息出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('pat:personalinfo:remove')")
   @Log(
      title = "患者基本信息",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{patientIds}"})
   public AjaxResult remove(@PathVariable String[] patientIds) {
      return this.toAjax(this.personalinfoService.deletePersonalinfoByIds(patientIds));
   }

   @PreAuthorize("@ss.hasAnyPermi('pat:visitinfo:allList,operation:pat:info')")
   @GetMapping({"/allList"})
   public AjaxResult allList(PersonalinfoVo personalinfoVo) {
      new AjaxResult();
      this.log.info("syncHisPatient========1111start" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.sss", new Date()));

      AjaxResult ajaxResult;
      try {
         if (StringUtils.isEmpty(personalinfoVo.getPatientId())) {
            ajaxResult = AjaxResult.error("患者id参数不能为空");
         } else if (StringUtils.isEmpty(personalinfoVo.getInpNo())) {
            ajaxResult = AjaxResult.error("住院号参数不能为空");
         } else {
            personalinfoVo = this.personalinfoService.selectAllList(personalinfoVo);
            VisitinfoVo vo = personalinfoVo.getVisitinfoVo();
            Date newDate = this.commonService.getDbSysdate();
            String newDay = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, newDate);
            int dayNum = DateUtils.getInHosDays(vo.getInhosTime(), vo.getOutTime() != null ? vo.getOutTime() : newDate);
            if (DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, vo.getInhosTime()).equals(newDay)) {
               dayNum = 0;
            }

            vo.setInhosDayNum(dayNum);
            ajaxResult = AjaxResult.success((Object)personalinfoVo);
            String dictStr = this.sysEmrConfigService.selectSysEmrConfigByKey("0040");
            if (StringUtils.isNotEmpty(dictStr)) {
               String[] dictType = dictStr.split(",");
               List<SysDictData> dictDataList = this.sysDictDataService.selectDictDataListByType(dictType);
               ajaxResult.put("dictList", dictDataList.stream().collect(Collectors.groupingBy((t) -> t.getDictType())));
            }

            List<AlleInfoVo> alleInfoVos = this.alleInfoService.selectAlleDictList();
            ajaxResult.put("alleList", alleInfoVos);
            String machineFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0076");
            ajaxResult.put("machineFlag", machineFlag);
            if (personalinfoVo != null && personalinfoVo.getVisitinfoVo() != null && personalinfoVo.getVisitinfoVo().getPatientId() != null) {
               Baseinfomation baseinfomation = this.commonService.findBaseInfo(personalinfoVo.getVisitinfoVo().getPatientId());
               if (baseinfomation != null && StringUtils.isNotBlank(baseinfomation.getIdcard())) {
                  List<Baseinfomation> baseinfomationList = this.commonService.findBaseInfoByIdcard(baseinfomation.getIdcard());
                  if (baseinfomationList != null && baseinfomationList.size() > 1) {
                     PersonalinfoVo personalinfoVoReq = new PersonalinfoVo();
                     String patientId = ((Baseinfomation)baseinfomationList.get(1)).getAdmissionNo();
                     if (StringUtils.isNotBlank(patientId) && ((Baseinfomation)baseinfomationList.get(1)).getCaseNo() != null) {
                        personalinfoVoReq.setPatientId(patientId);
                        personalinfoVoReq.setInpNo(((Baseinfomation)baseinfomationList.get(1)).getCaseNo());
                        PersonalinfoVo personalinfoRes = this.personalinfoService.selectAllList(personalinfoVoReq);
                        ajaxResult.put("patientInfoJws", personalinfoRes);
                     }
                  }
               }
            }
         }
      } catch (Exception e) {
         this.log.error("查询患者浏览首页出现异常,", e);
         ajaxResult = AjaxResult.error("查询患者浏览首页出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('pat:personalinfo:operRoom,operation:pat:info')")
   @GetMapping({"/patIdAndMainIdByInpNo"})
   public AjaxResult patIdAndMainIdByInpNo(String inpNo) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");
      boolean flag = true;

      try {
         if (flag && StringUtils.isEmpty(inpNo)) {
            ajaxResult = AjaxResult.error("住院号不能为空");
         }

         if (flag) {
            String emrFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0050");
            if (emrFlag.equals("1")) {
               String patientAddFlag = (String)this.redisCache.getCacheObject("his_pat_add_data:" + inpNo);
               if (StringUtils.isBlank(patientAddFlag) || !patientAddFlag.equals("1")) {
                  this.redisCache.setCacheObject("his_pat_add_data:" + inpNo, "1");
                  SysUser sysUser = SecurityUtils.getLoginUser().getUser();
                  SqlVo sqlVo = new SqlVo();
                  sqlVo.setDeptCode(sysUser.getDept().getDeptCode());
                  sqlVo.setResDocCd(sysUser.getBasEmployee().getEmplNumber());
                  sqlVo.setInpNo(inpNo);
                  this.log.info("syncHisPatient========2222" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.sss", new Date()));
                  List<Map<String, Object>> visitList = this.visitinfoService.selectPatientData(sqlVo);
                  this.log.info("syncHisPatient========3333" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.sss", new Date()));
                  List<Map<String, Object>> perList = this.personalinfoService.setSyncPersonalInfoList(sqlVo);
                  this.log.info("syncHisPatient========4444" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.sss", new Date()));
                  if (visitList != null && !visitList.isEmpty()) {
                     List<String> inpNoList = (List)visitList.stream().map((s) -> s.get("admission_no").toString()).collect(Collectors.toList());
                     this.syncHisPatientInfoService.syncHisPatient(inpNoList);
                     this.log.info("syncHisPatient========异步同步11111111" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.sss", new Date()));
                     this.visitinfoService.syncPatVisitinfo(visitList, perList);
                     this.log.info("syncHisPatient========5555" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.sss", new Date()));
                  }

                  this.redisCache.deleteObject("his_pat_add_data:" + inpNo);
               }

               String caFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0001");
               if (StringUtils.isNotBlank(caFlag) && caFlag.equals("1")) {
                  SysUser sysUser = SecurityUtils.getLoginUser().getUser();
                  String emplNumber = sysUser.getBasEmployee().getEmplNumber();
                  String ca = this.visitinfoService.selectCaMesByUserName(emplNumber);
                  if (StringUtils.isNotEmpty(ca)) {
                     this.visitinfoService.updateStaffCa(emplNumber, ca);
                  }
               }
            }

            Personalinfo personalinfo = this.personalinfoService.selectPersonalinfoByInpNo(inpNo);
            ajaxResult = AjaxResult.success((Object)personalinfo);
         }
      } catch (Exception e) {
         this.log.error("根据住院号查询患者id和患者mainId出现异常,", e);
         ajaxResult = AjaxResult.error("根据住院号查询患者id和患者mainId出现异常");
      } finally {
         this.redisCache.deleteObject("his_pat_add_data:" + inpNo);
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('pat:personalinfo:syncHisPatientInfo,operation:pat:info')")
   @GetMapping({"/syncHisPatientInfo"})
   public AjaxResult syncHisPatientInfo(String inpNo) {
      AjaxResult ajaxResult = AjaxResult.success("同步成功");
      boolean flag = true;

      try {
         if (flag && StringUtils.isBlank(inpNo)) {
            flag = false;
            ajaxResult = AjaxResult.error("患者的住院号不能为空！");
         }

         if (flag) {
            String emrFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0050");
            if (emrFlag.equals("1")) {
               String patientAddFlag = (String)this.redisCache.getCacheObject("his_pat_add_data:" + inpNo);
               if (StringUtils.isBlank(patientAddFlag) || !patientAddFlag.equals("1")) {
                  this.redisCache.setCacheObject("his_pat_add_data:" + inpNo, "1");
                  SysUser sysUser = SecurityUtils.getLoginUser().getUser();
                  SqlVo sqlVo = new SqlVo();
                  sqlVo.setDeptCode(sysUser.getDept().getDeptCode());
                  sqlVo.setResDocCd(sysUser.getBasEmployee().getEmplNumber());
                  sqlVo.setInpNo(inpNo);
                  this.log.info("syncHisPatient========2222" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.sss", new Date()));
                  List<Map<String, Object>> visitList = this.visitinfoService.selectPatientData(sqlVo);
                  this.log.info("syncHisPatient========3333" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.sss", new Date()));
                  List<Map<String, Object>> perList = this.personalinfoService.setSyncPersonalInfoList(sqlVo);
                  this.log.info("syncHisPatient========4444" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.sss", new Date()));
                  if (visitList != null && !visitList.isEmpty()) {
                     List<String> inpNoList = (List)visitList.stream().map((s) -> s.get("admission_no").toString()).collect(Collectors.toList());
                     this.syncHisPatientInfoService.syncHisPatient(inpNoList);
                     this.log.info("syncHisPatient========异步同步11111111" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.sss", new Date()));
                     this.visitinfoService.syncPatVisitinfo(visitList, perList);
                     this.log.info("syncHisPatient========5555" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.sss", new Date()));
                  }
               }
            }
         }
      } catch (Exception e) {
         this.log.error("同步患者数据出现异常,", e);
         ajaxResult = AjaxResult.error("同步患者数据出现异常");
      } finally {
         this.redisCache.deleteObject("his_pat_add_data:" + inpNo);
      }

      return ajaxResult;
   }

   @GetMapping({"/getPatientInfoForSx"})
   public TableDataInfo getPatientInfoForSx(PersonalinfoForSxVo personalinfoForSxVo) {
      this.startPage();
      List<Map<String, Object>> perList = null;

      try {
         perList = this.personalinfoService.getPatientInfoForSx(personalinfoForSxVo);
      } catch (Exception e) {
         e.printStackTrace();
      }

      return this.getDataTable(perList);
   }
}
