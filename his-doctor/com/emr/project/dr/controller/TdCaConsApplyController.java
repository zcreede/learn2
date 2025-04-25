package com.emr.project.dr.controller;

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
import com.emr.project.dr.domain.TdCaConsApply;
import com.emr.project.dr.domain.vo.TdCaConsApplyForExcelVo;
import com.emr.project.dr.domain.vo.TdCaConsApplyVo;
import com.emr.project.dr.service.ITdCaConsApplyService;
import com.emr.project.emr.domain.EmrBinary;
import com.emr.project.emr.domain.EmrSignRecord;
import com.emr.project.emr.domain.Index;
import com.emr.project.emr.domain.vo.IndexSaveReturnVo;
import com.emr.project.emr.domain.vo.IndexSignVo;
import com.emr.project.emr.service.IEmrBinaryService;
import com.emr.project.emr.service.IEmrSignRecordService;
import com.emr.project.emr.service.IIndexService;
import com.emr.project.operation.domain.Medicalinformation;
import com.emr.project.operation.service.IMedicalinfoService;
import com.emr.project.other.service.IGrantOutDoctorService;
import com.emr.project.pat.domain.vo.BackLogVo;
import com.emr.project.qc.domain.EmrQcFlow;
import com.emr.project.qc.domain.PatEvent;
import com.emr.project.qc.domain.vo.EmrMessageVo;
import com.emr.project.qc.service.IEmrMessageService;
import com.emr.project.qc.service.IEmrQcFlowService;
import com.emr.project.qc.service.IPatEventService;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.service.ISysEmrConfigService;
import com.emr.project.tmpl.domain.ElemSign;
import com.emr.project.tmpl.service.IElemSignService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
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
@RequestMapping({"/td/apply"})
public class TdCaConsApplyController extends BaseController {
   @Autowired
   private ITdCaConsApplyService tdCaConsApplyService;
   @Autowired
   private IIndexService indexService;
   @Autowired
   private IElemSignService elemSignService;
   @Autowired
   private ICommonService commonService;
   @Autowired
   private IEmrMessageService emrMessageService;
   @Autowired
   private RedisCache redisCache;
   @Autowired
   private IEmrSignRecordService emrSignRecordService;
   @Autowired
   private IGrantOutDoctorService grantOutDoctorService;
   @Autowired
   private IEmrQcFlowService emrQcFlowService;
   @Autowired
   private IEmrBinaryService emrBinaryService;
   @Autowired
   private IPatEventService patEventService;
   @Autowired
   private IMedicalinfoService medicalinfoService;
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;

   @PreAuthorize("@ss.hasPermi('td:apply:list')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");
      boolean flag = true;

      try {
         TdCaConsApply tdCaConsApply = this.tdCaConsApplyService.selectTdCaConsApplyById(id);
         if (flag && tdCaConsApply == null) {
            flag = false;
            ajaxResult = AjaxResult.error("没有此会诊记录");
         }

         Index index = flag ? this.indexService.selectIndexById(tdCaConsApply.getMrFileId()) : null;
         if (flag && index == null) {
            flag = false;
            ajaxResult = AjaxResult.error("没有此会诊病历记录");
         }

         if (flag) {
            ajaxResult = AjaxResult.success((Object)tdCaConsApply);
            String caFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0001");
            String caType = this.sysEmrConfigService.selectSysEmrConfigByKey("0002");
            ajaxResult.put("caFlag", caFlag);
            ajaxResult.put("caType", caType);
            List<ElemSign> elemSigns = this.elemSignService.selectElemSignByTempId(index.getTempId());
            String applyElem = this.sysEmrConfigService.selectSysEmrConfigByKey("0034");
            String consElem = this.sysEmrConfigService.selectSysEmrConfigByKey("0035");

            for(ElemSign elem : elemSigns) {
               if (elem.getContElemName().equals(applyElem)) {
                  ajaxResult.put("applyElem", elem);
               } else if (elem.getContElemName().equals(consElem)) {
                  ajaxResult.put("consElem", elem);
               }
            }
         }
      } catch (Exception e) {
         this.log.error("查询会诊申请详细信息出现异常", e);
         ajaxResult = AjaxResult.error("查询会诊申请详细信息出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('td:apply:add')")
   @Log(
      title = "会诊申请",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody TdCaConsApplyVo tdCaConsApplyVo, HttpServletRequest request) {
      AjaxResult ajaxResult = AjaxResult.success("保存成功");
      boolean flag = true;
      String indexFileKey = null;

      try {
         if (flag && tdCaConsApplyVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isBlank(tdCaConsApplyVo.getState())) {
            flag = false;
            ajaxResult = AjaxResult.error("保存状态不能为空");
         }

         if (flag && (StringUtils.isEmpty(tdCaConsApplyVo.getPatientId()) || StringUtils.isBlank(tdCaConsApplyVo.getPatientId()))) {
            flag = false;
            ajaxResult = AjaxResult.error("患者id不能为空");
         }

         if (flag && (StringUtils.isEmpty(tdCaConsApplyVo.getBase64Str()) || StringUtils.isBlank(tdCaConsApplyVo.getBase64Str()))) {
            flag = false;
            ajaxResult = AjaxResult.error("病历文件base64不能为空");
         }

         if (flag && (StringUtils.isEmpty(tdCaConsApplyVo.getXmlStr()) || StringUtils.isBlank(tdCaConsApplyVo.getXmlStr()))) {
            flag = false;
            ajaxResult = AjaxResult.error("病历文件xmlStr不能为空");
         }

         if (flag && tdCaConsApplyVo.getId() != null && tdCaConsApplyVo.getMrFileId() != null) {
            indexFileKey = "index_file_read_write:" + tdCaConsApplyVo.getMrFileId();
            String indexFileReadFlag = (String)this.redisCache.getCacheObject(indexFileKey);
            if (StringUtils.isNotBlank(indexFileReadFlag) && indexFileReadFlag.equals("1")) {
               flag = false;
               ajaxResult = AjaxResult.error("当前病历有正在保存的操作，不能继续保存，请稍后再试！");
               indexFileKey = null;
            } else {
               this.redisCache.setCacheObject(indexFileKey, "1", 10, TimeUnit.MINUTES);
            }
         }

         if (flag) {
            tdCaConsApplyVo.setMrState(tdCaConsApplyVo.getState());
            IndexSaveReturnVo returnVo = this.tdCaConsApplyService.saveTdCaConsApply(tdCaConsApplyVo, request);
            this.indexService.saveConsIndexElemAsync(tdCaConsApplyVo, returnVo.getIndex());
            ajaxResult.put("tdCaConsApply", tdCaConsApplyVo);
            if (StringUtils.isNotBlank(indexFileKey)) {
               this.redisCache.deleteObject(indexFileKey);
            }
         }
      } catch (Exception e) {
         this.log.error("保存会诊申请出现异常", e);
         ajaxResult = AjaxResult.error("保存会诊申请出现异常");
         if (StringUtils.isNotBlank(indexFileKey)) {
            this.redisCache.deleteObject(indexFileKey);
         }
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('td:apply:add')")
   @PostMapping({"/consSign"})
   public AjaxResult consSign(@RequestBody TdCaConsApplyVo tdCaConsApplyVo, @RequestBody IndexSignVo indexSignVo, HttpServletRequest request) {
      AjaxResult ajaxResult = AjaxResult.success("保存成功");
      boolean flag = true;
      String indexFileKey = null;

      try {
         String caFlagStr = this.sysEmrConfigService.selectSysEmrConfigByKey("0001");
         Boolean caFlag = caFlagStr.equals("1");
         if (flag && tdCaConsApplyVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isBlank(tdCaConsApplyVo.getState())) {
            flag = false;
            ajaxResult = AjaxResult.error("保存状态不能为空");
         }

         if (flag && (StringUtils.isEmpty(tdCaConsApplyVo.getPatientId()) || StringUtils.isBlank(tdCaConsApplyVo.getPatientId()))) {
            flag = false;
            ajaxResult = AjaxResult.error("患者id不能为空");
         }

         if (flag && (StringUtils.isEmpty(tdCaConsApplyVo.getBase64Str()) || StringUtils.isBlank(tdCaConsApplyVo.getBase64Str()))) {
            flag = false;
            ajaxResult = AjaxResult.error("病历文件base64不能为空");
         }

         if (flag && (StringUtils.isEmpty(tdCaConsApplyVo.getXmlStr()) || StringUtils.isBlank(tdCaConsApplyVo.getXmlStr()))) {
            flag = false;
            ajaxResult = AjaxResult.error("病历文件xmlStr不能为空");
         }

         SysUser sysUser = SecurityUtils.getLoginUser().getUser();
         String userName = sysUser.getUserName();
         Boolean checkFlag = this.grantOutDoctorService.checkIsOutDoctor(userName);
         if (flag && checkFlag) {
            flag = false;
            ajaxResult = AjaxResult.error("子账号不能进行签名！请联系责任医师进行签名！");
         }

         if (caFlag) {
            if (flag && (indexSignVo.getSignElemList() == null || indexSignVo.getSignElemList() != null && indexSignVo.getSignElemList().isEmpty())) {
               flag = false;
               ajaxResult = AjaxResult.error("签名元素id集合不能为空");
            }

            if (flag && (StringUtils.isEmpty(indexSignVo.getCertSn()) || StringUtils.isBlank(indexSignVo.getCertSn()))) {
               flag = false;
               ajaxResult = AjaxResult.error("签名证书序列号不能为空");
            }
         }

         if (flag && tdCaConsApplyVo.getMrFileId() != null) {
            indexFileKey = "index_file_read_write:" + tdCaConsApplyVo.getMrFileId();
            String indexFileReadFlag = (String)this.redisCache.getCacheObject(indexFileKey);
            if (StringUtils.isNotBlank(indexFileReadFlag) && indexFileReadFlag.equals("1")) {
               flag = false;
               ajaxResult = AjaxResult.error("当前病历有正在保存的操作，不能继续保存，请稍后再试！");
               indexFileKey = null;
            } else {
               this.redisCache.setCacheObject(indexFileKey, "1", 10, TimeUnit.MINUTES);
            }
         }

         if (flag) {
            tdCaConsApplyVo.setApplyDate(tdCaConsApplyVo.getApplyDate());
            tdCaConsApplyVo.setApplySignDate(tdCaConsApplyVo.getApplyDate());
            tdCaConsApplyVo.setMrState("05");
            tdCaConsApplyVo.setMrType("17");
            IndexSaveReturnVo returnVo = this.indexService.saveConsIndex(tdCaConsApplyVo, request);
            this.indexService.saveConsIndexElemAsync(tdCaConsApplyVo, returnVo.getIndex());
            this.tdCaConsApplyService.signTdCaConsApply(tdCaConsApplyVo, indexSignVo, returnVo.getUpdateTime(), request);
            ajaxResult.put("tdCaConsApply", tdCaConsApplyVo);
            if (StringUtils.isNotBlank(tdCaConsApplyVo.getInvDocCd())) {
               EmrMessageVo emrMessageVo = new EmrMessageVo();
               emrMessageVo.setDoctCd(tdCaConsApplyVo.getInvDocCd());
               List<EmrMessageVo> messageList = this.emrMessageService.selectEmrMessageByDoctCd(emrMessageVo);
               String messageKey = "message_key:" + tdCaConsApplyVo.getInvDocCd();
               if (messageList != null && !messageList.isEmpty()) {
                  this.redisCache.deleteObject(messageKey);
                  this.redisCache.setCacheList(messageKey, messageList);
               }
            }

            if (StringUtils.isNotBlank(indexFileKey)) {
               this.redisCache.deleteObject(indexFileKey);
            }
         }
      } catch (Exception e) {
         this.log.error("申请会诊签名出现异常", e);
         ajaxResult = AjaxResult.error("申请会诊签名出现异常");
         if (StringUtils.isNotBlank(indexFileKey)) {
            this.redisCache.deleteObject(indexFileKey);
         }
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('td:apply:edit')")
   @PostMapping({"/consDocSign"})
   public AjaxResult consDocSign(@RequestBody TdCaConsApplyVo tdCaConsApplyVo, @RequestBody IndexSignVo indexSignVo, HttpServletRequest request) {
      AjaxResult ajaxResult = AjaxResult.success("保存成功");
      boolean flag = true;
      String indexFileKey = null;

      try {
         TdCaConsApply tdCaConsApply = this.tdCaConsApplyService.selectTdCaConsApplyById(tdCaConsApplyVo.getId());
         if (tdCaConsApply == null) {
            flag = false;
            ajaxResult = AjaxResult.error("未查询到会诊申请记录，请刷新后重试");
         }

         String caFlagStr = this.sysEmrConfigService.selectSysEmrConfigByKey("0001");
         Boolean caFlag = caFlagStr.equals("1");
         if (flag && tdCaConsApplyVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isBlank(tdCaConsApplyVo.getState())) {
            flag = false;
            ajaxResult = AjaxResult.error("保存状态不能为空");
         }

         if (flag && (StringUtils.isEmpty(tdCaConsApplyVo.getPatientId()) || StringUtils.isBlank(tdCaConsApplyVo.getPatientId()))) {
            flag = false;
            ajaxResult = AjaxResult.error("患者id不能为空");
         }

         if (flag && (StringUtils.isEmpty(tdCaConsApplyVo.getBase64Str()) || StringUtils.isBlank(tdCaConsApplyVo.getBase64Str()))) {
            flag = false;
            ajaxResult = AjaxResult.error("病历文件base64不能为空");
         }

         if (flag && (StringUtils.isEmpty(tdCaConsApplyVo.getXmlStr()) || StringUtils.isBlank(tdCaConsApplyVo.getXmlStr()))) {
            flag = false;
            ajaxResult = AjaxResult.error("病历文件xmlStr不能为空");
         }

         SysUser user = SecurityUtils.getLoginUser().getUser();
         String userName = user.getUserName();
         Boolean checkFlag = this.grantOutDoctorService.checkIsOutDoctor(userName);
         if (flag && checkFlag) {
            flag = false;
            ajaxResult = AjaxResult.error("外来医师，不能进行签名操作！");
         }

         if (!caFlag) {
            SysUser sysUser = SecurityUtils.getLoginUser().getUser();
            String truePassword = sysUser.getPassword();
            if (flag && !tdCaConsApply.getConsDocCd().equals(sysUser.getBasEmployee().getEmplNumber())) {
               flag = false;
               ajaxResult = AjaxResult.error("当前登录人不是接收会诊医师不能完成会诊");
            }
         } else {
            if (flag && (indexSignVo.getSignElemList() == null || indexSignVo.getSignElemList() != null && indexSignVo.getSignElemList().isEmpty())) {
               flag = false;
               ajaxResult = AjaxResult.error("签名元素id集合不能为空");
            }

            if (flag && (StringUtils.isEmpty(indexSignVo.getCertSn()) || StringUtils.isBlank(indexSignVo.getCertSn()))) {
               flag = false;
               ajaxResult = AjaxResult.error("签名证书序列号不能为空");
            }
         }

         if (flag && tdCaConsApplyVo.getMrFileId() != null) {
            indexFileKey = "index_file_read_write:" + tdCaConsApplyVo.getMrFileId();
            String indexFileReadFlag = (String)this.redisCache.getCacheObject(indexFileKey);
            if (StringUtils.isNotBlank(indexFileReadFlag) && indexFileReadFlag.equals("1")) {
               flag = false;
               ajaxResult = AjaxResult.error("当前病历有正在保存的操作，不能继续保存，请稍后再试！");
               indexFileKey = null;
            } else {
               this.redisCache.setCacheObject(indexFileKey, "1", 10, TimeUnit.MINUTES);
            }
         }

         if (flag) {
            Date signTime = this.commonService.getDbSysdate();
            tdCaConsApplyVo.setConsSignDate(signTime);
            tdCaConsApplyVo.setMrState("08");
            IndexSaveReturnVo returnVo = this.indexService.saveConsIndex(tdCaConsApplyVo, request);
            this.indexService.saveConsIndexElemAsync(tdCaConsApplyVo, returnVo.getIndex());
            this.tdCaConsApplyService.signTdCaConsApply(tdCaConsApplyVo, indexSignVo, returnVo.getUpdateTime(), request);
            ajaxResult.put("tdCaConsApply", tdCaConsApplyVo);
            if (StringUtils.isNotBlank(tdCaConsApplyVo.getState()) && tdCaConsApplyVo.getState().equals("04")) {
               Medicalinformation baseInfo = this.medicalinfoService.selectMedicalinfoByPatientId(tdCaConsApply.getInpNo());
               if (baseInfo != null) {
                  PatEvent patEvent = new PatEvent(baseInfo.getHospitalCode(), baseInfo.getAdmissionNo(), baseInfo.getAdmissionNo(), baseInfo.getName(), baseInfo.getResidentCode(), baseInfo.getResidentName(), "07", "会诊", signTime, (Date)null);
                  this.patEventService.insertPatEvent(patEvent);
               }
            }

            if (StringUtils.isNotBlank(indexFileKey)) {
               this.redisCache.deleteObject(indexFileKey);
            }
         }
      } catch (Exception e) {
         this.log.error("申请会诊签名出现异常", e);
         ajaxResult = AjaxResult.error("申请会诊签名出现异常");
         if (StringUtils.isNotBlank(indexFileKey)) {
            this.redisCache.deleteObject(indexFileKey);
         }
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('td:apply:add')")
   @PostMapping({"/isConsSign"})
   public AjaxResult isConsSign(@RequestBody TdCaConsApplyVo tdCaConsApplyVo, @RequestBody IndexSignVo indexSignVo, HttpServletRequest request) {
      AjaxResult ajaxResult = AjaxResult.success("保存成功");
      boolean flag = true;

      try {
         String caFlagStr = this.sysEmrConfigService.selectSysEmrConfigByKey("0001");
         Boolean caFlag = caFlagStr.equals("1");
         if (flag && tdCaConsApplyVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isBlank(tdCaConsApplyVo.getState())) {
            flag = false;
            ajaxResult = AjaxResult.error("保存状态不能为空");
         }

         if (flag && (StringUtils.isEmpty(tdCaConsApplyVo.getPatientId()) || StringUtils.isBlank(tdCaConsApplyVo.getPatientId()))) {
            flag = false;
            ajaxResult = AjaxResult.error("患者id不能为空");
         }

         if (flag && (StringUtils.isEmpty(tdCaConsApplyVo.getBase64Str()) || StringUtils.isBlank(tdCaConsApplyVo.getBase64Str()))) {
            flag = false;
            ajaxResult = AjaxResult.error("病历文件base64不能为空");
         }

         if (flag && (StringUtils.isEmpty(tdCaConsApplyVo.getXmlStr()) || StringUtils.isBlank(tdCaConsApplyVo.getXmlStr()))) {
            flag = false;
            ajaxResult = AjaxResult.error("病历文件xmlStr不能为空");
         }

         if (caFlag) {
            if (flag && (indexSignVo.getSignElemList() == null || indexSignVo.getSignElemList() != null && indexSignVo.getSignElemList().isEmpty())) {
               flag = false;
               ajaxResult = AjaxResult.error("签名元素id集合不能为空");
            }

            if (flag && (StringUtils.isEmpty(indexSignVo.getCertSn()) || StringUtils.isBlank(indexSignVo.getCertSn()))) {
               flag = false;
               ajaxResult = AjaxResult.error("签名证书序列号不能为空");
            }
         }

         if (flag) {
            Date date = tdCaConsApplyVo.getApplyDate();
            if (tdCaConsApplyVo.getApplyDate() == null) {
               date = this.commonService.getDbSysdate();
               tdCaConsApplyVo.setApplyDate(date);
            }

            Map<String, List<String>> map = this.tdCaConsApplyService.getConsElemValue(tdCaConsApplyVo);
            ajaxResult = AjaxResult.success((Object)map);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            ajaxResult.put("applyDate", sdf.format(date));
         }
      } catch (Exception e) {
         this.log.error("判断申请会诊是否可以签名出现异常", e);
         ajaxResult = AjaxResult.error("判断申请会诊是否可以签名出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('td:apply:add')")
   @PostMapping({"/isConsDocSign"})
   public AjaxResult isConsDocSign(@RequestBody TdCaConsApplyVo tdCaConsApplyVo, @RequestBody IndexSignVo indexSignVo, HttpServletRequest request) {
      AjaxResult ajaxResult = AjaxResult.success("保存成功");
      boolean flag = true;

      try {
         String caFlagStr = this.sysEmrConfigService.selectSysEmrConfigByKey("0001");
         Boolean caFlag = caFlagStr.equals("1");
         if (flag && tdCaConsApplyVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isBlank(tdCaConsApplyVo.getState())) {
            flag = false;
            ajaxResult = AjaxResult.error("保存状态不能为空");
         }

         if (flag && (StringUtils.isEmpty(tdCaConsApplyVo.getPatientId()) || StringUtils.isBlank(tdCaConsApplyVo.getPatientId()))) {
            flag = false;
            ajaxResult = AjaxResult.error("患者id不能为空");
         }

         if (flag && (StringUtils.isEmpty(tdCaConsApplyVo.getBase64Str()) || StringUtils.isBlank(tdCaConsApplyVo.getBase64Str()))) {
            flag = false;
            ajaxResult = AjaxResult.error("病历文件base64不能为空");
         }

         if (flag && (StringUtils.isEmpty(tdCaConsApplyVo.getXmlStr()) || StringUtils.isBlank(tdCaConsApplyVo.getXmlStr()))) {
            flag = false;
            ajaxResult = AjaxResult.error("病历文件xmlStr不能为空");
         }

         if (caFlag) {
            if (flag && (indexSignVo.getSignElemList() == null || indexSignVo.getSignElemList() != null && indexSignVo.getSignElemList().isEmpty())) {
               flag = false;
               ajaxResult = AjaxResult.error("签名元素id集合不能为空");
            }

            if (flag && (StringUtils.isEmpty(indexSignVo.getCertSn()) || StringUtils.isBlank(indexSignVo.getCertSn()))) {
               flag = false;
               ajaxResult = AjaxResult.error("签名证书序列号不能为空");
            }
         }

         if (flag) {
            Map<String, List<String>> map = this.tdCaConsApplyService.getConsElemValue(tdCaConsApplyVo);
            ajaxResult = AjaxResult.success((Object)map);
         }
      } catch (Exception e) {
         this.log.error("判断会诊是否可以签名出现异常", e);
         ajaxResult = AjaxResult.error("判断会诊是否可以签名出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('td:apply:add')")
   @PostMapping({"/isCancelConsDocSign"})
   public AjaxResult isCancelConsDocSign(@RequestBody TdCaConsApplyVo tdCaConsApplyVo) {
      AjaxResult ajaxResult = AjaxResult.success("可以取消签名");
      boolean flag = true;

      try {
         if (flag && tdCaConsApplyVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && tdCaConsApplyVo.getId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         TdCaConsApply tdCaConsApply = flag ? this.tdCaConsApplyService.selectTdCaConsApplyById(tdCaConsApplyVo.getId()) : null;
         if (flag && tdCaConsApply == null) {
            flag = false;
            ajaxResult = AjaxResult.error("没有这个会诊申请记录");
         }

         SysUser sysUser = SecurityUtils.getLoginUser().getUser();
         EmrQcFlow emrQcFlow = flag ? this.emrQcFlowService.selectEmrQcFlowById(sysUser.getHospital().getOrgCode(), tdCaConsApply.getPatientId()) : null;
         if (flag && emrQcFlow != null && (emrQcFlow.getMrState().equals("14") || emrQcFlow.getMrState().equals("16"))) {
            flag = false;
            ajaxResult = AjaxResult.error("当前患者的病历已归档，不能取消签名");
         }

         if (flag && emrQcFlow != null && emrQcFlow.getMrState().equals("10")) {
            flag = false;
            ajaxResult = AjaxResult.error("当前患者的病历已提交科室质控，不能取消签名");
         }

         if (flag && emrQcFlow != null && emrQcFlow.getMrState().equals("12")) {
            flag = false;
            ajaxResult = AjaxResult.error("当前患者的病历已申请归档，不能取消签名");
         }

         if (flag && !tdCaConsApply.getState().equals("04")) {
            flag = false;
            ajaxResult = AjaxResult.error("当前会诊状态不是【已完成会诊】，不能取消签名");
         }

         if (flag && !tdCaConsApply.getConsDocCd().equals(sysUser.getUserName())) {
            flag = false;
            ajaxResult = AjaxResult.error("当前会诊的签名医师是【" + tdCaConsApply.getConsDocName() + "】，与当前登录人不是同一个，不能取消签名");
         }

         if (flag) {
            List<ElemSign> resultList = new ArrayList();
            List<EmrSignRecord> signRecordList = this.emrSignRecordService.selectEmrSignRecordListByMainFileId(tdCaConsApply.getMrFileId().toString());
            if (signRecordList != null && !signRecordList.isEmpty()) {
               Date date = ((EmrSignRecord)signRecordList.get(0)).getSignTime();
               Date emrUpdate = ((EmrSignRecord)signRecordList.get(0)).getEmrUpdateTime();
               emrUpdate = emrUpdate == null ? date : emrUpdate;
               if (!((EmrSignRecord)signRecordList.get(0)).getDocCode().equals(sysUser.getBasEmployee().getEmplNumber())) {
                  flag = false;
                  ajaxResult = AjaxResult.error("仅" + ((EmrSignRecord)signRecordList.get(0)).getDocName() + "医师，在病历签名后未修改内容前可取消签名！");
               }

               Index index = this.indexService.selectIndexById(tdCaConsApply.getMrFileId());
               Date emrUpdateDate = index.getAltDate();
               EmrBinary emrBinary = this.emrBinaryService.selectEmrBinaryById(tdCaConsApply.getMrFileId());
               if (flag && (emrUpdateDate != null || emrUpdate != null || emrBinary.getAltDate() != null) && (emrUpdateDate != null && emrUpdate == null || emrUpdateDate == null || emrUpdate == null || emrBinary.getAltDate() == null || emrUpdateDate.compareTo(emrUpdate) != 0 || emrUpdateDate.compareTo(emrBinary.getAltDate()) != 0)) {
                  flag = false;
                  ajaxResult = AjaxResult.error("仅" + ((EmrSignRecord)signRecordList.get(0)).getDocName() + "医师，在病历签名后未修改内容前可取消签名！");
               }

               if (flag) {
                  for(ElemSign elemSign : this.elemSignService.selectElemSignByTempId(index.getTempId())) {
                     List<EmrSignRecord> signRecordListTemp = (List)signRecordList.stream().filter((t) -> date.compareTo(t.getSignTime()) == 0).collect(Collectors.toList());
                     if (signRecordListTemp != null && !signRecordListTemp.isEmpty()) {
                        List<String> signList = (List)signRecordListTemp.stream().map((s) -> s.getSignSname()).collect(Collectors.toList());
                        if (signList.contains(elemSign.getContElemName())) {
                           resultList.add(elemSign);
                        }
                     }
                  }

                  ajaxResult.put("signList", resultList);
               }
            } else {
               flag = false;
               ajaxResult = AjaxResult.error("未查询到签名记录");
            }

            String caFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0001");
            String caType = this.sysEmrConfigService.selectSysEmrConfigByKey("0002");
            ajaxResult.put("caFlag", caFlag);
            ajaxResult.put("caType", caType);
         }
      } catch (Exception e) {
         this.log.error("判断会诊完成是否可以取消签名出现异常，", e);
         ajaxResult = AjaxResult.error("判断会诊完成是否可以取消签名出现异常，请联系管理员");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('td:apply:add')")
   @PostMapping({"/cancelConsDocSign"})
   public AjaxResult cancelConsDocSign(@RequestBody TdCaConsApplyVo tdCaConsApplyVo, HttpServletRequest request) {
      AjaxResult ajaxResult = AjaxResult.success("取消签名成功");
      boolean flag = true;
      String indexFileKey = null;

      try {
         if (flag && tdCaConsApplyVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && tdCaConsApplyVo.getId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && tdCaConsApplyVo.getMrFileId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && (tdCaConsApplyVo.getSignElemNameList() == null || tdCaConsApplyVo.getSignElemNameList() != null && CollectionUtils.isEmpty(tdCaConsApplyVo.getSignElemNameList()))) {
            flag = false;
            ajaxResult = AjaxResult.error("签名元素不能为空");
         }

         Index index = null;
         if (flag) {
            index = this.indexService.selectIndexById(tdCaConsApplyVo.getMrFileId());
            if (index == null) {
               flag = false;
               ajaxResult = AjaxResult.error("不存在此病历文件记录");
            }
         }

         if (flag && (StringUtils.isEmpty(tdCaConsApplyVo.getBase64Str()) || StringUtils.isBlank(tdCaConsApplyVo.getBase64Str()))) {
            flag = false;
            ajaxResult = AjaxResult.error("病历文件base64不能为空");
         }

         if (flag && (StringUtils.isEmpty(tdCaConsApplyVo.getXmlStr()) || StringUtils.isBlank(tdCaConsApplyVo.getXmlStr()))) {
            flag = false;
            ajaxResult = AjaxResult.error("病历文件xmlStr不能为空");
         }

         if (flag) {
            indexFileKey = "index_file_read_write:" + index.getId();
            String indexFileReadFlag = (String)this.redisCache.getCacheObject(indexFileKey);
            if (StringUtils.isNotBlank(indexFileReadFlag) && indexFileReadFlag.equals("1")) {
               flag = false;
               ajaxResult = AjaxResult.error("当前病历有正在保存的操作，不能继续保存，请稍后再试！");
               indexFileKey = null;
            } else {
               this.redisCache.setCacheObject(indexFileKey, "1", 10, TimeUnit.MINUTES);
            }
         }

         if (flag) {
            IndexSaveReturnVo returnVo = this.tdCaConsApplyService.cancleSignTdCaConsApply(index, tdCaConsApplyVo, request, ajaxResult);
            this.indexService.saveConsIndexElemAsync(tdCaConsApplyVo, returnVo.getIndex());
            if (StringUtils.isNotBlank(indexFileKey)) {
               this.redisCache.deleteObject(indexFileKey);
            }
         }
      } catch (Exception e) {
         this.log.error("会诊病历文件取消签名异常", e);
         ajaxResult = AjaxResult.error("会诊病历文件取消签名异常");
         if (StringUtils.isNotBlank(indexFileKey)) {
            this.redisCache.deleteObject(indexFileKey);
         }
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('td:apply:edit')")
   @PostMapping({"/isCancelApply"})
   public AjaxResult isCancelApply(@RequestBody TdCaConsApplyVo tdCaConsApplyVo) {
      AjaxResult ajaxResult = AjaxResult.success("取消申请成功");
      boolean flag = true;

      try {
         if (flag && tdCaConsApplyVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && tdCaConsApplyVo.getId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("申请单id不能为空");
         }

         if (flag && tdCaConsApplyVo.getMrFileId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("病历id不能为空");
         }

         if (flag && (StringUtils.isEmpty(tdCaConsApplyVo.getBase64Str()) || StringUtils.isBlank(tdCaConsApplyVo.getBase64Str()))) {
            flag = false;
            ajaxResult = AjaxResult.error("病历文件base64不能为空");
         }

         if (flag && (StringUtils.isEmpty(tdCaConsApplyVo.getXmlStr()) || StringUtils.isBlank(tdCaConsApplyVo.getXmlStr()))) {
            flag = false;
            ajaxResult = AjaxResult.error("病历文件xmlStr不能为空");
         }

         TdCaConsApply tdCaConsApply = flag ? this.tdCaConsApplyService.selectTdCaConsApplyById(tdCaConsApplyVo.getId()) : null;
         if (flag && !tdCaConsApply.getState().equals("02")) {
            flag = false;
            ajaxResult = AjaxResult.error("当前会诊申请状态不是待应邀，不能取消申请");
         }

         if (flag) {
            List<EmrSignRecord> signRecordList = this.emrSignRecordService.selectEmrSignRecordListByMainFileId(tdCaConsApplyVo.getMrFileId().toString());
            if (signRecordList != null && !signRecordList.isEmpty()) {
               ajaxResult.put("cancelSignData", signRecordList.get(signRecordList.size() - 1));
            }
         }
      } catch (Exception e) {
         this.log.error("取消会诊申请出现异常", e);
         ajaxResult = AjaxResult.error("取消会诊申请出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('td:apply:edit')")
   @PutMapping({"/cancelApply"})
   public AjaxResult cancelApply(@RequestBody TdCaConsApplyVo tdCaConsApplyVo, HttpServletRequest request) {
      AjaxResult ajaxResult = AjaxResult.success("取消申请成功");
      boolean flag = true;
      String indexFileKey = null;

      try {
         if (flag && tdCaConsApplyVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && tdCaConsApplyVo.getId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("申请单id不能为空");
         }

         if (flag && tdCaConsApplyVo.getMrFileId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("病历id不能为空");
         }

         if (flag && (StringUtils.isEmpty(tdCaConsApplyVo.getBase64Str()) || StringUtils.isBlank(tdCaConsApplyVo.getBase64Str()))) {
            flag = false;
            ajaxResult = AjaxResult.error("病历文件base64不能为空");
         }

         if (flag && (StringUtils.isEmpty(tdCaConsApplyVo.getXmlStr()) || StringUtils.isBlank(tdCaConsApplyVo.getXmlStr()))) {
            flag = false;
            ajaxResult = AjaxResult.error("病历文件xmlStr不能为空");
         }

         if (flag && tdCaConsApplyVo.getMrFileId() != null) {
            indexFileKey = "index_file_read_write:" + tdCaConsApplyVo.getMrFileId();
            String indexFileReadFlag = (String)this.redisCache.getCacheObject(indexFileKey);
            if (StringUtils.isNotBlank(indexFileReadFlag) && indexFileReadFlag.equals("1")) {
               flag = false;
               ajaxResult = AjaxResult.error("当前病历有正在保存的操作，不能继续保存，请稍后再试！");
               indexFileKey = null;
            } else {
               this.redisCache.setCacheObject(indexFileKey, "1", 10, TimeUnit.MINUTES);
            }
         }

         if (flag) {
            IndexSaveReturnVo returnVo = this.tdCaConsApplyService.updateCancelApply(tdCaConsApplyVo, ajaxResult, request);
            TdCaConsApply tdCaConsApply = this.tdCaConsApplyService.selectTdCaConsApplyById(tdCaConsApplyVo.getId());
            ajaxResult.put("tdCaConsApply", tdCaConsApply);
            this.indexService.saveConsIndexElemAsync(tdCaConsApplyVo, returnVo.getIndex());
            if (StringUtils.isNotBlank(indexFileKey)) {
               this.redisCache.deleteObject(indexFileKey);
            }
         }
      } catch (Exception e) {
         this.log.error("取消会诊申请出现异常", e);
         ajaxResult = AjaxResult.error("取消会诊申请出现异常");
         if (StringUtils.isNotBlank(indexFileKey)) {
            this.redisCache.deleteObject(indexFileKey);
         }
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('td:apply:remove')")
   @Log(
      title = "删除会诊申请",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/deleteApply"})
   public AjaxResult remove(@RequestBody TdCaConsApplyVo tdCaConsApplyVo) {
      AjaxResult ajaxResult = AjaxResult.success("作废成功");
      boolean flag = true;

      try {
         if (tdCaConsApplyVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && tdCaConsApplyVo.getId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("id不能为空");
         }

         if (flag && tdCaConsApplyVo.getMrFileId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("病历id不能为空");
         }

         TdCaConsApply tdCaConsApply = flag ? this.tdCaConsApplyService.selectTdCaConsApplyById(tdCaConsApplyVo.getId()) : null;
         if (flag && !tdCaConsApply.getState().equals("01") && !tdCaConsApply.getState().equals("02")) {
            flag = false;
            ajaxResult = AjaxResult.error("会诊医师已接受邀请，不能删除");
         }

         if (flag) {
            this.tdCaConsApplyService.deleteTdCaConsApply(tdCaConsApplyVo);
         }
      } catch (Exception e) {
         this.log.error("会诊申请作废出现异常", e);
         ajaxResult = AjaxResult.error("会诊申请作废出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('td:apply:list')")
   @GetMapping({"/unFinishConsList"})
   public TableDataInfo unFinishConsList() {
      new TableDataInfo();

      TableDataInfo tableDataInfo;
      try {
         this.startPage();
         List<BackLogVo> list = this.tdCaConsApplyService.selectUnFinishConsList((String)null);
         tableDataInfo = this.getDataTable(list);
      } catch (Exception e) {
         this.log.error("查询待完成会诊列表出现异常", e);
         tableDataInfo = new TableDataInfo(500, "查询待完成会诊列表出现异常");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasPermi('td:apply:edit')")
   @PutMapping({"/acceptApply"})
   public AjaxResult acceptApply(@RequestBody TdCaConsApplyVo tdCaConsApplyVo) {
      AjaxResult ajaxResult = AjaxResult.success("接受邀请成功");
      boolean flag = true;

      try {
         if (flag && tdCaConsApplyVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && tdCaConsApplyVo.getId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("申请单id不能为空");
         }

         SysUser user = SecurityUtils.getLoginUser().getUser();
         String userName = user.getUserName();
         Boolean checkFlag = this.grantOutDoctorService.checkIsOutDoctor(userName);
         if (flag && checkFlag) {
            flag = false;
            ajaxResult = AjaxResult.error("外来医师，不能接受邀请！");
         }

         if (flag) {
            this.tdCaConsApplyService.acceptApplyUpdate(tdCaConsApplyVo);
            TdCaConsApply tdCaConsApply = this.tdCaConsApplyService.selectTdCaConsApplyById(tdCaConsApplyVo.getId());
            ajaxResult.put("tdCaConsApply", tdCaConsApply);
         }
      } catch (Exception e) {
         this.log.error("接受邀请出现异常", e);
         ajaxResult = AjaxResult.error("接受邀请出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('td:apply:edit')")
   @PutMapping({"/returnApply"})
   public AjaxResult returnApply(@RequestBody TdCaConsApplyVo tdCaConsApplyVo) {
      AjaxResult ajaxResult = AjaxResult.success("邀请退回成功");
      boolean flag = true;

      try {
         if (flag && tdCaConsApplyVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && tdCaConsApplyVo.getId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("申请单id不能为空");
         }

         SysUser user = SecurityUtils.getLoginUser().getUser();
         String userName = user.getUserName();
         Boolean checkFlag = this.grantOutDoctorService.checkIsOutDoctor(userName);
         if (flag && checkFlag) {
            flag = false;
            ajaxResult = AjaxResult.error("外来医师，不能退回邀请！");
         }

         TdCaConsApply caConsApply = flag ? this.tdCaConsApplyService.selectTdCaConsApplyById(tdCaConsApplyVo.getId()) : null;
         if (flag && (caConsApply == null || caConsApply != null && !caConsApply.getState().equals("03"))) {
            flag = false;
            ajaxResult = AjaxResult.error("没有这个会诊申请，或者会诊申请状态不是已应邀，不能退回邀请！");
         }

         if (flag) {
            tdCaConsApplyVo.setPatientId(caConsApply.getPatientId());
            this.tdCaConsApplyService.returnApplyUpdate(tdCaConsApplyVo);
            TdCaConsApply tdCaConsApply = this.tdCaConsApplyService.selectTdCaConsApplyById(tdCaConsApplyVo.getId());
            ajaxResult.put("tdCaConsApply", tdCaConsApply);
         }
      } catch (Exception e) {
         this.log.error("邀请退回出现异常，", e);
         ajaxResult = AjaxResult.error("邀请退回出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('td:apply:edit')")
   @PutMapping({"/consOpinion"})
   public AjaxResult consOpinion(@RequestBody TdCaConsApplyVo tdCaConsApplyVo) {
      AjaxResult ajaxResult = AjaxResult.success("保存成功");
      boolean flag = true;

      try {
         if (flag && tdCaConsApplyVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && tdCaConsApplyVo.getId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("申请单id不能为空");
         }

         if (flag && tdCaConsApplyVo.getConsDate() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("会诊时间不能为空");
         }

         if (flag && StringUtils.isEmpty(tdCaConsApplyVo.getGenReview())) {
            flag = false;
            ajaxResult = AjaxResult.error("一般情况回顾不能为空");
         }

         if (flag && StringUtils.isEmpty(tdCaConsApplyVo.getPhyExam())) {
            flag = false;
            ajaxResult = AjaxResult.error("体格检查不能为空");
         }

         if (flag && StringUtils.isEmpty(tdCaConsApplyVo.getConsDiag())) {
            flag = false;
            ajaxResult = AjaxResult.error("诊断不能为空");
         }

         if (flag && StringUtils.isEmpty(tdCaConsApplyVo.getConsOpinion())) {
            flag = false;
            ajaxResult = AjaxResult.error("处理意见不能为空");
         }

         if (flag && StringUtils.isEmpty(tdCaConsApplyVo.getConsSatiCd())) {
            flag = false;
            ajaxResult = AjaxResult.error("满意度编码不能为空");
         }

         if (flag && StringUtils.isEmpty(tdCaConsApplyVo.getConsSatiName())) {
            flag = false;
            ajaxResult = AjaxResult.error("满意度名称不能为空");
         }

         if (flag && !tdCaConsApplyVo.getConsSatiCd().equals("1") && StringUtils.isEmpty(tdCaConsApplyVo.getConsEavlCd()) && StringUtils.isEmpty(tdCaConsApplyVo.getConsEavlDesc())) {
            flag = false;
            ajaxResult = AjaxResult.error("其他评价和申请单评价必须填写一个");
         }

         if (flag && StringUtils.isNotBlank(tdCaConsApplyVo.getApplyEavlDesc()) && StringUtils.getStringLengthByByte(tdCaConsApplyVo.getApplyEavlDesc()) > 200) {
            flag = false;
            ajaxResult = AjaxResult.error("其他评价最多只能输入200字符");
         }

         SysUser user = SecurityUtils.getLoginUser().getUser();
         String userName = user.getUserName();
         Boolean checkFlag = this.grantOutDoctorService.checkIsOutDoctor(userName);
         if (flag && checkFlag) {
            flag = false;
            ajaxResult = AjaxResult.error("外来医师，不能进行录入会诊意见！");
         }

         if (flag) {
            this.tdCaConsApplyService.updateTdCaConsApply(tdCaConsApplyVo);
            TdCaConsApply tdCaConsApply = this.tdCaConsApplyService.selectTdCaConsApplyById(tdCaConsApplyVo.getId());
            ajaxResult.put("tdCaConsApply", tdCaConsApply);
            Map<String, List<String>> map = this.tdCaConsApplyService.getConsElemValue(tdCaConsApply);
            ajaxResult.put("result", map);
         }
      } catch (Exception e) {
         this.log.error("会诊意见录入出现异常", e);
         ajaxResult = AjaxResult.error("会诊意见录入出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('td:apply:edit')")
   @PutMapping({"/applyEvaluate"})
   public AjaxResult applyEvaluate(@RequestBody TdCaConsApplyVo tdCaConsApplyVo) {
      AjaxResult ajaxResult = AjaxResult.success("保存成功");
      boolean flag = true;

      try {
         if (flag && tdCaConsApplyVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && tdCaConsApplyVo.getId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("申请单id不能为空");
         }

         if (flag && StringUtils.isEmpty(tdCaConsApplyVo.getApplySatiCd())) {
            flag = false;
            ajaxResult = AjaxResult.error("申请满意度编码不能为空");
         }

         if (flag && StringUtils.isEmpty(tdCaConsApplyVo.getApplySatiName())) {
            flag = false;
            ajaxResult = AjaxResult.error("申请满意度名称不能为空");
         }

         if (flag && !tdCaConsApplyVo.getApplyEavlCd().equals("1") && StringUtils.isEmpty(tdCaConsApplyVo.getApplyEavlCd()) && StringUtils.isEmpty(tdCaConsApplyVo.getApplyEavlDesc())) {
            flag = false;
            ajaxResult = AjaxResult.error("其他评价和申请单评价必须填写一个");
         }

         if (flag && StringUtils.isNotBlank(tdCaConsApplyVo.getApplyEavlDesc()) && StringUtils.getStringLengthByByte(tdCaConsApplyVo.getApplyEavlDesc()) > 200) {
            flag = false;
            ajaxResult = AjaxResult.error("其他评价最多只能输入200字符");
         }

         if (flag) {
            this.tdCaConsApplyService.updateTdCaConsApply(tdCaConsApplyVo);
            TdCaConsApply tdCaConsApply = this.tdCaConsApplyService.selectTdCaConsApplyById(tdCaConsApplyVo.getId());
            ajaxResult.put("tdCaConsApply", tdCaConsApply);
         }
      } catch (Exception e) {
         this.log.error("申请评价出现异常", e);
         ajaxResult = AjaxResult.error("申请评价出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('td:apply:list')")
   @GetMapping({"/isConsOrApply"})
   public AjaxResult isConsOrApply(Long id) {
      AjaxResult ajaxResult = AjaxResult.success();

      try {
         if (id == null) {
            ajaxResult = AjaxResult.error("id不能为空");
         } else {
            String flag = this.tdCaConsApplyService.selectIsConsOrApply(id);
            ajaxResult.put("flag", flag);
         }
      } catch (Exception e) {
         this.log.error("判断是申请医师还是会诊医师出现异常", e);
         ajaxResult = AjaxResult.error("判断是申请医师还是会诊医师出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('td:apply:inviteList')")
   @GetMapping({"/unFinishInvite"})
   public TableDataInfo unFinishInvite(TdCaConsApplyVo tdCaConsApply) {
      new TableDataInfo();

      TableDataInfo tableDataInfo;
      try {
         this.startPage();
         List<TdCaConsApplyVo> list = this.tdCaConsApplyService.selectUnFinishInviteList(tdCaConsApply);
         tableDataInfo = this.getDataTable(list);
      } catch (Exception e) {
         this.log.error("查询未完成应邀会诊出现异常", e);
         tableDataInfo = new TableDataInfo(500, "查询未完成应邀会诊出现异常");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasPermi('td:apply:inviteList')")
   @GetMapping({"/finishInvite"})
   public TableDataInfo finishInvite(TdCaConsApplyVo tdCaConsApply) {
      new TableDataInfo();

      TableDataInfo tableDataInfo;
      try {
         if (tdCaConsApply.getStartDate() != null && tdCaConsApply.getEndDate() != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(tdCaConsApply.getEndDate());
            c.add(5, 1);
            tdCaConsApply.setEndDate(c.getTime());
         }

         this.startPage();
         List<TdCaConsApplyVo> list = this.tdCaConsApplyService.selectFinishInviteList(tdCaConsApply);
         tableDataInfo = this.getDataTable(list);
      } catch (Exception e) {
         this.log.error("查询已完成应邀会诊出现异常", e);
         tableDataInfo = new TableDataInfo(500, "查询已完成应邀会诊出现异常");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasPermi('td:apply:applyList')")
   @GetMapping({"/unFinishApply"})
   public TableDataInfo unFinishApply(TdCaConsApplyVo tdCaConsApply) {
      new TableDataInfo();

      TableDataInfo tableDataInfo;
      try {
         this.startPage();
         List<TdCaConsApplyVo> list = this.tdCaConsApplyService.selectUnFinishApplyList(tdCaConsApply);
         tableDataInfo = this.getDataTable(list);
      } catch (Exception e) {
         this.log.error("查询未完成申请会诊出现异常", e);
         tableDataInfo = new TableDataInfo(500, "查询未完成申请会诊出现异常");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasPermi('td:apply:applyList')")
   @GetMapping({"/finishApply"})
   public TableDataInfo finishApply(TdCaConsApplyVo tdCaConsApply) {
      new TableDataInfo();

      TableDataInfo tableDataInfo;
      try {
         if (tdCaConsApply.getStartDate() != null && tdCaConsApply.getEndDate() != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(tdCaConsApply.getEndDate());
            c.add(5, 1);
            tdCaConsApply.setEndDate(c.getTime());
         }

         this.startPage();
         List<TdCaConsApplyVo> list = this.tdCaConsApplyService.selectFinishApplyList(tdCaConsApply);
         tableDataInfo = this.getDataTable(list);
      } catch (Exception e) {
         this.log.error("查询已完成申请会诊出现异常", e);
         tableDataInfo = new TableDataInfo(500, "查询已完成申请会诊出现异常");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasPermi('td:apply:applyList')")
   @GetMapping({"/finishApplyExport"})
   public AjaxResult finishInviteExport(TdCaConsApplyVo tdCaConsApply) {
      AjaxResult ajaxResult = AjaxResult.success("导出成功！");

      try {
         if (tdCaConsApply.getStartDate() != null && tdCaConsApply.getEndDate() != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(tdCaConsApply.getEndDate());
            c.add(5, 1);
            tdCaConsApply.setEndDate(c.getTime());
         }

         List<TdCaConsApplyVo> list = this.tdCaConsApplyService.selectFinishApplyList(tdCaConsApply);
         ExcelUtil<TdCaConsApplyVo> util = new ExcelUtil(TdCaConsApplyVo.class);
         ajaxResult = util.exportExcel(list, "已完成申请会诊");
      } catch (Exception e) {
         this.log.error("导出已完成申请会诊出现异常", e);
         ajaxResult = AjaxResult.error("导出出现异常，请联系管理员！");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('td:apply:list,td:apply:applyList,td:apply:inviteList')")
   @GetMapping({"/flowChart"})
   public AjaxResult flowChart(TdCaConsApply tdCaConsApply) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         if (tdCaConsApply.getId() == null) {
            ajaxResult = AjaxResult.error("会诊id不能为空");
         } else {
            List<TdCaConsApplyVo> list = this.tdCaConsApplyService.selectConsFlowChart(tdCaConsApply.getId());
            ajaxResult = AjaxResult.success((Object)list);
         }
      } catch (Exception e) {
         this.log.error("查询流程图出现异常", e);
         ajaxResult = AjaxResult.error("查询流程图出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('td:apply:statisQuery')")
   @GetMapping({"/statisList"})
   public TableDataInfo statisList(TdCaConsApplyVo tdCaConsApply) {
      new TableDataInfo();

      TableDataInfo tableDataInfo;
      try {
         if (tdCaConsApply.getStartDate() != null && tdCaConsApply.getEndDate() != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(tdCaConsApply.getEndDate());
            c.add(5, 1);
            tdCaConsApply.setEndDate(c.getTime());
         }

         this.startPage();
         List<TdCaConsApplyVo> list = this.tdCaConsApplyService.selectConsStatisList(tdCaConsApply);
         tableDataInfo = this.getDataTable(list);
      } catch (Exception e) {
         this.log.error("会诊信息查询列表出现异常", e);
         tableDataInfo = new TableDataInfo(500, "会诊信息查询列表出现异常");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasAnyPermi('td:apply:statisQuery')")
   @GetMapping({"/statisListForExport"})
   public AjaxResult statisListForExport(TdCaConsApplyVo tdCaConsApply) throws Exception {
      new ArrayList();
      if (tdCaConsApply.getStartDate() != null && tdCaConsApply.getEndDate() != null) {
         Calendar c = Calendar.getInstance();
         c.setTime(tdCaConsApply.getEndDate());
         c.add(5, 1);
         tdCaConsApply.setEndDate(c.getTime());
      }

      List<TdCaConsApplyVo> list = this.tdCaConsApplyService.selectConsStatisList(tdCaConsApply);
      List tdCaConsApplyForExcelVoList = (List)list.stream().map((a) -> {
         TdCaConsApplyForExcelVo tdCaConsApplyForExcelVo = new TdCaConsApplyForExcelVo();
         BeanUtils.copyProperties(a, tdCaConsApplyForExcelVo);
         if (a.getConsSignDate() != null) {
            tdCaConsApplyForExcelVo.setConsSignDateS(DateUtils.dateFormatS(a.getConsSignDate(), "yyyy-MM-dd HH:mm"));
         }

         return tdCaConsApplyForExcelVo;
      }).collect(Collectors.toList());
      ExcelUtil<TdCaConsApplyForExcelVo> util = new ExcelUtil(TdCaConsApplyForExcelVo.class);
      return util.exportExcel(tdCaConsApplyForExcelVoList, "会诊信息统计");
   }

   @PreAuthorize("@ss.hasAnyPermi('td:apply:add')")
   @GetMapping({"/patConsApplyList"})
   public AjaxResult patConsApplyList(TdCaConsApplyVo tdCaConsApply) {
      AjaxResult ajaxResult = AjaxResult.success();
      boolean flag = true;

      try {
         if (tdCaConsApply == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(tdCaConsApply.getPatientId())) {
            flag = false;
            ajaxResult = AjaxResult.error("患者id不能为空");
         }

         if (flag) {
            List<TdCaConsApplyVo> list = this.tdCaConsApplyService.selectTdCaConsApplyList(tdCaConsApply);
            ajaxResult = AjaxResult.success((Object)list);
         }
      } catch (Exception e) {
         this.log.error("查询患者会诊申请单异常", e);
         ajaxResult = AjaxResult.error("查询患者会诊申请单异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('td:apply:list')")
   @GetMapping({"/update/{id}"})
   public AjaxResult getInfoAndUpdate(@PathVariable("id") Long id) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");
      boolean flag = true;

      try {
         TdCaConsApply tdCaConsApply = this.tdCaConsApplyService.selectTdCaConsApplyById(id);
         if (flag && tdCaConsApply == null) {
            flag = false;
            ajaxResult = AjaxResult.error("没有此会诊记录");
         }

         Index index = flag ? this.indexService.selectIndexById(tdCaConsApply.getMrFileId()) : null;
         if (flag && index == null) {
            flag = false;
            ajaxResult = AjaxResult.error("没有此会诊病历记录");
         }

         if (flag) {
            if (tdCaConsApply.getState().equals("02")) {
               this.tdCaConsApplyService.updateStateById(tdCaConsApply);
               ajaxResult = AjaxResult.success((Object)this.tdCaConsApplyService.selectTdCaConsApplyById(id));
            } else {
               ajaxResult = AjaxResult.success((Object)tdCaConsApply);
            }

            String caFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0001");
            String caType = this.sysEmrConfigService.selectSysEmrConfigByKey("0002");
            ajaxResult.put("caFlag", caFlag);
            ajaxResult.put("caType", caType);
            List<ElemSign> elemSigns = this.elemSignService.selectElemSignByTempId(index.getTempId());
            String applyElem = this.sysEmrConfigService.selectSysEmrConfigByKey("0034");
            String consElem = this.sysEmrConfigService.selectSysEmrConfigByKey("0035");

            for(ElemSign elem : elemSigns) {
               if (elem.getContElemName().equals(applyElem)) {
                  ajaxResult.put("applyElem", elem);
               } else if (elem.getContElemName().equals(consElem)) {
                  ajaxResult.put("consElem", elem);
               }
            }
         }
      } catch (Exception e) {
         this.log.error("查询会诊申请详细信息出现异常", e);
         ajaxResult = AjaxResult.error("查询会诊申请详细信息出现异常");
      }

      return ajaxResult;
   }
}
