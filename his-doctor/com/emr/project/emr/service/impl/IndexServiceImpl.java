package com.emr.project.emr.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.drools.core.KieTemplate;
import com.emr.common.constant.CommonConstants;
import com.emr.common.utils.Base64Util;
import com.emr.common.utils.CommonUtils;
import com.emr.common.utils.DateUtils;
import com.emr.common.utils.DroolsUtil;
import com.emr.common.utils.IPAddressUtil;
import com.emr.common.utils.MessageUtils;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.common.utils.SolarTermsUtils;
import com.emr.framework.config.EMRConfig;
import com.emr.framework.redis.IndexRedisCache;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.domain.TreeSelect;
import com.emr.project.common.service.ICommonService;
import com.emr.project.docOrder.domain.TdCaOperationApply;
import com.emr.project.docOrder.service.ITdCaOperationApplyService;
import com.emr.project.dr.domain.TdCaConsApply;
import com.emr.project.dr.domain.vo.TdCaConsApplyVo;
import com.emr.project.dr.service.ITdCaConsApplyService;
import com.emr.project.emr.domain.BasEmrAcceAuthor;
import com.emr.project.emr.domain.EditState;
import com.emr.project.emr.domain.EmrBinary;
import com.emr.project.emr.domain.EmrElemstoe;
import com.emr.project.emr.domain.EmrSharing;
import com.emr.project.emr.domain.EmrSignData;
import com.emr.project.emr.domain.EmrSignRecord;
import com.emr.project.emr.domain.EmrTaskInfo;
import com.emr.project.emr.domain.Index;
import com.emr.project.emr.domain.ModifyAppl;
import com.emr.project.emr.domain.SubfileIndex;
import com.emr.project.emr.domain.SysEmrLog;
import com.emr.project.emr.domain.SysEmrTypeConfig;
import com.emr.project.emr.domain.SysEmrTypeLevel;
import com.emr.project.emr.domain.UnConsultationDTO;
import com.emr.project.emr.domain.vo.AnalysisXmlDataReq;
import com.emr.project.emr.domain.vo.ElemSignVo;
import com.emr.project.emr.domain.vo.EmrBinaryVo;
import com.emr.project.emr.domain.vo.EmrSharingVo;
import com.emr.project.emr.domain.vo.EmrTaskInfoVo;
import com.emr.project.emr.domain.vo.HisIndexVo;
import com.emr.project.emr.domain.vo.IndexHFInfoPageVo;
import com.emr.project.emr.domain.vo.IndexHFInfoResultVo;
import com.emr.project.emr.domain.vo.IndexHFInfoVo;
import com.emr.project.emr.domain.vo.IndexNoSignListVo;
import com.emr.project.emr.domain.vo.IndexSaveReturnVo;
import com.emr.project.emr.domain.vo.IndexSaveVo;
import com.emr.project.emr.domain.vo.IndexSecLevelVo;
import com.emr.project.emr.domain.vo.IndexSignPatVo;
import com.emr.project.emr.domain.vo.IndexSignVo;
import com.emr.project.emr.domain.vo.IndexVo;
import com.emr.project.emr.domain.vo.ModifyApplVo;
import com.emr.project.emr.domain.vo.SubFileIndexHFInfoVo;
import com.emr.project.emr.domain.vo.SubfileIndexVo;
import com.emr.project.emr.domain.vo.SysEmrTypeConfigVo;
import com.emr.project.emr.domain.vo.XmlElementParseConfigVo;
import com.emr.project.emr.mapper.IndexMapper;
import com.emr.project.emr.service.IBasEmrAcceAuthorService;
import com.emr.project.emr.service.IEditStateService;
import com.emr.project.emr.service.IEmrBinaryService;
import com.emr.project.emr.service.IEmrElemstoeService;
import com.emr.project.emr.service.IEmrSharingService;
import com.emr.project.emr.service.IEmrSignDataService;
import com.emr.project.emr.service.IEmrSignRecordService;
import com.emr.project.emr.service.IEmrTaskInfoService;
import com.emr.project.emr.service.IIndexService;
import com.emr.project.emr.service.IModifyApplService;
import com.emr.project.emr.service.ISealupRecordService;
import com.emr.project.emr.service.ISubfileIndexService;
import com.emr.project.emr.service.ISysEmrLogService;
import com.emr.project.emr.service.ISysEmrTypeConfigService;
import com.emr.project.other.domain.BasCertInfo;
import com.emr.project.other.domain.GrantOutDoctor;
import com.emr.project.other.domain.ImpDoctorTemp;
import com.emr.project.other.service.IBasCertInfoService;
import com.emr.project.other.service.IGrantOutDoctorService;
import com.emr.project.other.service.IImpDoctorTempService;
import com.emr.project.pat.domain.BasDoctGroupMember;
import com.emr.project.pat.domain.Personalinfo;
import com.emr.project.pat.domain.TestExamAlertResult;
import com.emr.project.pat.domain.vo.BackLogVo;
import com.emr.project.pat.domain.vo.DeptBEDateVo;
import com.emr.project.pat.domain.vo.VisitinfoPersonalVo;
import com.emr.project.pat.domain.vo.VisitinfoVo;
import com.emr.project.pat.service.IBasDoctGroupMemberService;
import com.emr.project.pat.service.IPersonalinfoService;
import com.emr.project.pat.service.ITestExamAlertResultService;
import com.emr.project.pat.service.ITransinfoService;
import com.emr.project.pat.service.IVisitinfoService;
import com.emr.project.qc.domain.EmrQcCommRecord;
import com.emr.project.qc.domain.EmrQcFlow;
import com.emr.project.qc.domain.EmrQcList;
import com.emr.project.qc.domain.EmrQcRecord;
import com.emr.project.qc.domain.QcAgiRule;
import com.emr.project.qc.domain.QcRuleConRela;
import com.emr.project.qc.domain.vo.EmrQcListVo;
import com.emr.project.qc.domain.vo.PatEventVo;
import com.emr.project.qc.domain.vo.QcRuleResultVo;
import com.emr.project.qc.domain.vo.QcRulesVo;
import com.emr.project.qc.mapper.EmrQcRecordMapper;
import com.emr.project.qc.service.IEmrQcCommRecordService;
import com.emr.project.qc.service.IEmrQcFlowService;
import com.emr.project.qc.service.IEmrQcListService;
import com.emr.project.qc.service.IEmrQcRecordService;
import com.emr.project.qc.service.IQcAgiRuleService;
import com.emr.project.qc.service.IQcRuleConRelaService;
import com.emr.project.qc.service.IQcRuleIterEmrService;
import com.emr.project.sys.domain.QuoteElem;
import com.emr.project.sys.domain.vo.BusIntegMenuVo;
import com.emr.project.sys.domain.vo.QuoteElemVo;
import com.emr.project.sys.mapper.QuoteElemMapper;
import com.emr.project.sys.service.IBusIntegMenuService;
import com.emr.project.sys.service.IQuoteElemService;
import com.emr.project.system.domain.BasEmployee;
import com.emr.project.system.domain.SysDept;
import com.emr.project.system.domain.SysDictData;
import com.emr.project.system.domain.SysElemStrstore;
import com.emr.project.system.domain.SysEmployeeRoleDept;
import com.emr.project.system.domain.SysOrg;
import com.emr.project.system.domain.SysShareElem;
import com.emr.project.system.domain.SysStaElem;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.domain.vo.SysStaElemVo;
import com.emr.project.system.mapper.SysDeptMapper;
import com.emr.project.system.mapper.SysStaElemMapper;
import com.emr.project.system.service.IBasEmployeeService;
import com.emr.project.system.service.ISysDictDataService;
import com.emr.project.system.service.ISysDictTypeService;
import com.emr.project.system.service.ISysElemStrstoreService;
import com.emr.project.system.service.ISysEmployeeRoleDeptService;
import com.emr.project.system.service.ISysEmrConfigService;
import com.emr.project.system.service.ISysShareElemService;
import com.emr.project.system.service.ISysUserService;
import com.emr.project.tmpl.domain.ElemAttri;
import com.emr.project.tmpl.domain.ElemDate;
import com.emr.project.tmpl.domain.ElemGender;
import com.emr.project.tmpl.domain.ElemMacro;
import com.emr.project.tmpl.domain.ElemSign;
import com.emr.project.tmpl.domain.TmplIndex;
import com.emr.project.tmpl.domain.TmplQuoteElem;
import com.emr.project.tmpl.domain.vo.ElemAttriVo;
import com.emr.project.tmpl.domain.vo.ElemMacroVo;
import com.emr.project.tmpl.domain.vo.TempIndexSaveElemVo;
import com.emr.project.tmpl.domain.vo.TmplElemLinkVo;
import com.emr.project.tmpl.service.IElemAttriService;
import com.emr.project.tmpl.service.IElemDateService;
import com.emr.project.tmpl.service.IElemGenderService;
import com.emr.project.tmpl.service.IElemMacroService;
import com.emr.project.tmpl.service.IElemSignService;
import com.emr.project.tmpl.service.ITmplElemLinkService;
import com.emr.project.tmpl.service.ITmplIndexService;
import com.emr.project.tmpl.service.ITmplQuoteElemService;
import com.emr.project.webEditor.util.ElemVoToElemUtil;
import com.emr.project.webEditor.util.FtpUtil;
import com.emr.project.webEditor.util.WriteFileUtil;
import com.emr.project.webEditor.util.XmlElementParseUtil;
import com.emr.project.webEditor.zb.vo.IndexFileVo;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Clob;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IndexServiceImpl implements IIndexService {
   private final Logger log = LoggerFactory.getLogger(IndexServiceImpl.class);
   @Autowired
   private IndexMapper indexMapper;
   @Autowired
   private ISysEmrTypeConfigService sysEmrTypeConfigService;
   @Autowired
   private ITmplIndexService tmplIndexService;
   @Resource
   private SysDeptMapper sysDeptMapper;
   @Resource
   private ISysDictDataService sysDictDataService;
   @Autowired
   private ISubfileIndexService subfileIndexService;
   @Autowired
   private IVisitinfoService visitinfoService;
   @Autowired
   private IModifyApplService modifyApplService;
   @Autowired
   private IPersonalinfoService personalinfoService;
   @Autowired
   private IElemGenderService elemGenderService;
   @Autowired
   private IElemDateService elemDateService;
   @Autowired
   private IElemMacroService elemMacroService;
   @Autowired
   private IQuoteElemService quoteElemService;
   @Autowired
   private ISysDictTypeService dictTypeService;
   @Autowired
   private IEmrSignRecordService emrSignRecordService;
   @Autowired
   private IEmrBinaryService emrBinaryService;
   @Autowired
   private IEditStateService editStateService;
   @Autowired
   private IEmrElemstoeService emrElemstoeService;
   @Autowired
   private IElemSignService elemSignService;
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;
   @Autowired
   private ITmplQuoteElemService tmplQuoteElemService;
   @Autowired
   private IEmrTaskInfoService emrTaskInfoService;
   @Autowired
   private IGrantOutDoctorService grantOutDoctorService;
   @Autowired
   private IBusIntegMenuService busIntegMenuService;
   @Autowired
   private ITransinfoService transinfoService;
   @Autowired
   private ISysEmrLogService sysEmrLogService;
   @Autowired
   private IBasEmrAcceAuthorService basEmrAcceAuthorService;
   @Autowired
   private KieTemplate kieTemplate;
   private KieSession kSession;
   @Autowired
   private IEmrQcRecordService emrQcRecordService;
   @Autowired
   private IQcRuleConRelaService qcRuleConRelaService;
   @Autowired
   private IEmrQcListService emrQcListService;
   @Autowired
   private EmrQcRecordMapper emrQcRecordMapper;
   @Autowired
   private IQcAgiRuleService qcAgiRuleService;
   @Autowired
   private IQcRuleIterEmrService qcRuleIterEmrService;
   @Autowired
   private IImpDoctorTempService iImpDoctorTempService;
   @Autowired
   private ISealupRecordService sealupRecordService;
   @Autowired
   private IBasDoctGroupMemberService basDoctGroupMemberService;
   @Autowired
   private ITdCaOperationApplyService tdCaOperationApplyService;
   @Autowired
   private IBasCertInfoService basCertInfoService;
   @Autowired
   private ITdCaConsApplyService tdCaConsApplyService;
   @Autowired
   private IImpDoctorTempService impDoctorTempService;
   @Autowired
   private ITmplElemLinkService tmplElemLinkService;
   @Autowired
   private IEmrQcFlowService emrQcFlowService;
   @Autowired
   private SysStaElemMapper sysStaElemMapper;
   @Autowired
   private IElemAttriService elemAttriService;
   @Autowired
   private QuoteElemMapper quoteElemMapper;
   @Autowired
   private ITestExamAlertResultService testExamAlertResultService;
   @Value("${spring.drools.path}")
   private String path;
   @Autowired
   private IndexRedisCache indexRedisCache;
   @Autowired
   private ISysUserService sysUserService;
   @Autowired
   private ISysShareElemService sysShareElemService;
   @Autowired
   private IEmrSharingService emrSharingService;
   @Autowired
   private ISysElemStrstoreService sysElemStrstoreService;
   @Autowired
   private ICommonService commonService;
   @Autowired
   private IBasEmployeeService basEmployeeService;
   @Autowired
   private IEmrSignDataService emrSignDataService;
   @Autowired
   private ISysEmployeeRoleDeptService employeeRoleDeptService;
   @Autowired
   private IEmrQcCommRecordService emrQcCommRecordService;

   public Index selectIndexById(Long id) {
      return this.indexMapper.selectIndexById(id);
   }

   public IndexVo selectIndexVoById(Long id) {
      return this.indexMapper.selectIndexVoById(id);
   }

   public Index selectIndexInfoById(Long id) {
      return this.indexMapper.selectIndexInfoById(id);
   }

   public List selectDelIndexPageList(IndexVo indexVo) throws Exception {
      return this.indexMapper.selectDelIndexPageList(indexVo);
   }

   public List selectIndexList(Index index) throws Exception {
      if (index != null && StringUtils.isNotEmpty(index.getMrStates())) {
         List<String> mrStatesList = Arrays.asList(index.getMrStates().split(","));
         index.setMrStateList(mrStatesList);
      }

      List<String> secLevelList = this.getIndexSecLevelByEmplNumber(SecurityUtils.getLoginUser().getUser().getBasEmployee().getEmplNumber(), index.getPatientId());
      index.setSecLevelList(secLevelList);
      return this.indexMapper.selectIndexList(index);
   }

   public int updateIndex(Index index) throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      index.setAltPerCode(sysUser.getUserName());
      index.setAltPerName(sysUser.getNickName());
      return this.indexMapper.updateIndex(index);
   }

   public int updateIndexByIds(Long[] ids) {
      return this.indexMapper.updateIndexByIds(ids);
   }

   public int updateIndexById(Long id) throws Exception {
      return this.indexMapper.updateIndexById(id);
   }

   public void updateIndexSecLevel(IndexVo indexVo) throws Exception {
      this.indexMapper.updateIndexSecLevel(indexVo);
   }

   public boolean isDel(Index index, SubfileIndex subfileIndex) throws Exception {
      String mrState = subfileIndex == null ? index.getMrState() : subfileIndex.getMrState();
      Long id = subfileIndex == null ? index.getId() : subfileIndex.getId();
      String creCode = subfileIndex == null ? index.getCrePerCode() : subfileIndex.getCrePerCode();
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      boolean creCodeFlag = sysUser.getBasEmployee().getEmplNumber().equals(creCode);
      boolean flag = false;
      if (!creCodeFlag || !"01".equals(mrState) && !"03".equals(mrState)) {
         ModifyAppl modify = this.modifyApplService.selectModifyApplByEndDate(CommonConstants.EMR_MODIFY_APPLY.APP_REASON_2, "1", id, sysUser.getBasEmployee().getEmplNumber(), (String)null);
         if (modify != null && creCodeFlag) {
            flag = true;
         }
      } else {
         flag = true;
      }

      return flag;
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public List editDelFlagById(Index index, SubfileIndex subfileIndex, String base64Str, String xmlStr, HttpServletRequest httpServletRequest) throws Exception {
      List<SubfileIndex> subfileIndexList = null;
      Long id = subfileIndex == null ? index.getId() : subfileIndex.getId();
      if (subfileIndex != null) {
         this.log.warn("editDelFlagById ：：病历的base64：" + index.getId() + " ： " + base64Str);
         this.subfileIndexService.deleteSubfileIndexById(subfileIndex.getId());
         EmrBinary emrBinary = new EmrBinary();
         emrBinary.setMrFileId(index.getId());
         emrBinary.setMrCon(this.getMrCon(base64Str, xmlStr, "", ""));
         this.emrBinaryService.updateEmrBinary(emrBinary);
         subfileIndexList = this.subfileIndexService.selectSubfileIndexByMainId(index.getId());
         if (subfileIndexList == null || subfileIndexList != null && subfileIndexList.isEmpty()) {
            this.delIndexById(index.getId());
         }

         this.emrIndexToRedis(index.getId(), base64Str);
         Base64Util.decode(base64Str, "emrBase64/" + index.getPatientId(), index.getId().toString() + ".txt");
         String fileDateStr = this.commonService.getSystimestamp();
         Base64Util.decode(base64Str, "emrLogBase64/" + index.getPatientId(), index.getId().toString() + "_" + fileDateStr + ".txt");
      } else {
         this.delIndexById(index.getId());
      }

      this.delIndexTask(index, subfileIndex);
      index = subfileIndex == null ? index : this.indexMapper.selectIndexById(subfileIndex.getMainId());
      this.sysEmrLogService.insertSysEmrLog(index, subfileIndex, "3", "病历删除", IPAddressUtil.getIPAddress(httpServletRequest));
      EmrQcList emrQcList = new EmrQcList();
      emrQcList.setMrFileId(String.valueOf(id));
      emrQcList.setQcState("7");
      this.emrQcListService.updateEmrQcListByMrFileId(emrQcList);
      if (index != null && "17".equals(index.getMrType())) {
         TdCaConsApply tdCaConsApply = this.tdCaConsApplyService.selectTdCaConsApplyByMrFileId(index.getId());
         if (tdCaConsApply != null) {
            TdCaConsApplyVo tdCaConsApplyVo = new TdCaConsApplyVo();
            tdCaConsApplyVo.setId(tdCaConsApply.getId());
            tdCaConsApplyVo.setState("05");
            this.tdCaConsApplyService.updateTdCaConsApply(tdCaConsApplyVo);
         }
      }

      return subfileIndexList;
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public List editDelFlagOnly(Index index, SubfileIndex subfileIndex, HttpServletRequest httpServletRequest) throws Exception {
      List<SubfileIndex> subfileIndexList = null;
      if (subfileIndex == null) {
         index.getId();
      } else {
         subfileIndex.getId();
      }

      if (subfileIndex != null) {
         this.subfileIndexService.deleteSubfileIndexById(subfileIndex.getId());
         subfileIndexList = this.subfileIndexService.selectSubfileIndexByMainId(index.getId());
         if (subfileIndexList == null || subfileIndexList != null && subfileIndexList.isEmpty()) {
            this.delIndexById(index.getId());
         }
      } else {
         this.delIndexById(index.getId());
      }

      this.delIndexTask(index, subfileIndex);
      index = subfileIndex == null ? index : this.indexMapper.selectIndexById(subfileIndex.getMainId());
      this.sysEmrLogService.insertSysEmrLog(index, subfileIndex, "3", "病历删除", IPAddressUtil.getIPAddress(httpServletRequest));
      return subfileIndexList;
   }

   private void delIndexTask(Index index, SubfileIndex subfileIndex) throws Exception {
      Long id = subfileIndex == null ? index.getId() : subfileIndex.getId();
      String mrType = subfileIndex == null ? index.getMrType() : subfileIndex.getMrType();
      EmrTaskInfo emrTaskInfoParam = new EmrTaskInfo();
      emrTaskInfoParam.setMrFileId(id);
      List<EmrTaskInfo> emrTaskInfoList = this.emrTaskInfoService.selectEmrTaskInfoList(emrTaskInfoParam);
      List<EmrTaskInfo> emrTaskInfoListTaskType1 = (List)emrTaskInfoList.stream().filter((t) -> "1".equals(t.getTaskType())).collect(Collectors.toList());
      List<EmrTaskInfo> emrTaskInfoListTaskType2 = (List)emrTaskInfoList.stream().filter((t) -> !"1".equals(t.getTaskType()) && !"6".equals(t.getTaskType())).collect(Collectors.toList());
      if (CollectionUtils.isNotEmpty(emrTaskInfoListTaskType1)) {
         this.emrTaskInfoService.updateTaskInfoByDel(id, "1");
         List<String> emrTypeCodeList = this.qcRuleIterEmrService.getIterTypeCodeByEmrTypeCode(mrType);
         IndexVo indexVo = new IndexVo();
         indexVo.setPatientId(index.getPatientId());
         indexVo.setEmrTypeCodeList(emrTypeCodeList);
         List<IndexVo> indexList = this.indexMapper.selectYetEmrList(indexVo);
         indexList = CollectionUtils.isNotEmpty(indexList) ? (List)indexList.stream().filter((t) -> !t.getId().equals(id)).collect(Collectors.toList()) : indexList;
         if (CollectionUtils.isNotEmpty(indexList)) {
            Long targetMrFileId = ((IndexVo)indexList.get(0)).getId();
            List<IndexVo> indexList1 = (List)indexList.stream().filter((t) -> t.getMrType().equals(mrType)).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(indexList1)) {
               targetMrFileId = ((IndexVo)indexList1.get(0)).getId();
            }

            for(EmrTaskInfo emrTaskInfo : emrTaskInfoListTaskType1) {
               this.emrTaskInfoService.updateTaskInfoByAdd(emrTaskInfo.getId(), targetMrFileId);
            }
         }
      }

      if (CollectionUtils.isNotEmpty(emrTaskInfoListTaskType2)) {
         for(EmrTaskInfo emrTaskInfo : emrTaskInfoListTaskType2) {
            this.emrTaskInfoService.updateTaskInfoByAdd(emrTaskInfo.getId(), id);
         }
      }

   }

   public void delIndexById(Long indexId) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      Index info = new Index();
      info.setId(indexId);
      info.setAltPerCode(basEmployee.getEmplNumber());
      info.setAltPerName(basEmployee.getEmplName());
      info.setDelFlag("1");
      this.indexMapper.updateIndexDelFlag(info);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void changeDelFlag(Index index, SubfileIndex subfileIndex, HttpServletRequest httpServletRequest) throws Exception {
      if (index != null) {
         Index info = new Index();
         info.setId(index.getId());
         info.setDelFlag("0");
         this.indexMapper.updateIndex(info);
      }

      if (subfileIndex != null) {
         index = this.indexMapper.selectIndexById(subfileIndex.getMainId());
         SubfileIndex subfileIndex1 = new SubfileIndex();
         subfileIndex1.setId(subfileIndex.getId());
         subfileIndex1.setDelFlag("0");
         this.subfileIndexService.updateSubfileIndex(subfileIndex1);
      }

      String mrType = subfileIndex != null && subfileIndex.getId() != null ? subfileIndex.getMrType() : index.getMrType();
      Long mrFileId = subfileIndex != null && subfileIndex.getId() != null ? subfileIndex.getId() : index.getId();
      String mrFileName = subfileIndex != null && subfileIndex.getMrFileShowName() != null ? subfileIndex.getMrFileShowName() : index.getMrFileShowName();
      List<SysEmrLog> sysEmrLogVoList = new ArrayList();
      SysEmrLog sysEmrLog = new SysEmrLog();
      sysEmrLog.setPatientId(index.getPatientId());
      sysEmrLog.setMrTypeCd(mrType);
      sysEmrLog.setMrFileId(mrFileId);
      sysEmrLog.setMrFileName(mrFileName);
      sysEmrLog.setOptType("2");
      sysEmrLog.setOptTypeName("病历恢复");
      sysEmrLog.setIp(IPAddressUtil.getIPAddress(httpServletRequest));
      sysEmrLogVoList.add(sysEmrLog);
      this.sysEmrLogService.insertSysEmrLog(sysEmrLogVoList);
      List<EmrTaskInfoVo> emrTaskInfoVoList = this.emrTaskInfoService.selectNoFinishTaskList(index.getPatientId(), "1");
      if (CollectionUtils.isNotEmpty(emrTaskInfoVoList)) {
         for(EmrTaskInfoVo emrTaskInfoVo : emrTaskInfoVoList) {
            String emrTypeCode = emrTaskInfoVo.getEmrTypeCode();
            String iterTypeCode = emrTaskInfoVo.getIterTypeCode();
            List<String> iterTypeCodeList = new ArrayList(1);
            if (StringUtils.isNotBlank(iterTypeCode)) {
               String[] iterTypeCodeArr = iterTypeCode.split(",");
               CollectionUtils.addAll(iterTypeCodeList, iterTypeCodeArr);
            }

            iterTypeCodeList.add(emrTypeCode);
            List<String> iterTypeCodeListTemp = (List)iterTypeCodeList.stream().filter((t) -> t.equals(mrType)).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(iterTypeCodeListTemp)) {
               this.emrTaskInfoService.updateTaskInfoByAdd(emrTaskInfoVo.getId(), mrFileId);
               break;
            }
         }
      }

      Long id = subfileIndex != null && subfileIndex.getId() != null ? subfileIndex.getId() : index.getId();
      if (subfileIndex != null && ("05".equals(subfileIndex.getMrState()) || "07".equals(subfileIndex.getMrState())) || index != null && ("05".equals(index.getMrState()) || "07".equals(index.getMrState()))) {
         EmrTaskInfo emrTaskInfoReq = new EmrTaskInfo();
         emrTaskInfoReq.setTaskAppDoc(subfileIndex != null ? subfileIndex.getCrePerCode() : index.getCrePerCode());
         emrTaskInfoReq.setMrFileId(id);
         emrTaskInfoReq.setTaskType("3");
         emrTaskInfoReq.setBusSection("2");
         List<EmrTaskInfo> emrTaskInfoList = this.emrTaskInfoService.selectEmrTaskInfoList(emrTaskInfoReq);
         if (emrTaskInfoList != null && emrTaskInfoList.size() > 0) {
            if (subfileIndex != null && "05".equals(subfileIndex.getMrState()) || index != null && "05".equals(index.getMrState())) {
               EmrTaskInfo emrTaskInfo = new EmrTaskInfo();
               emrTaskInfo.setMrFileId(id);
               emrTaskInfo.setId(((EmrTaskInfo)emrTaskInfoList.get(0)).getId());
               emrTaskInfo.setTreatFlag("0");
               this.emrTaskInfoService.updateEmrTaskInfo(emrTaskInfo);
            }

            if (subfileIndex != null && "07".equals(subfileIndex.getMrState()) || index != null && "07".equals(index.getMrState())) {
               EmrTaskInfo emrTaskInfoReq07 = new EmrTaskInfo();
               if (emrTaskInfoList != null && emrTaskInfoList.size() > 0) {
                  emrTaskInfoReq07.setTaskAppDoc(((EmrTaskInfo)emrTaskInfoList.get(0)).getDocCd());
                  emrTaskInfoReq07.setMrFileId(id);
                  List<EmrTaskInfo> emrTaskInfoList07 = this.emrTaskInfoService.selectEmrTaskInfoList(emrTaskInfoReq07);
                  if (emrTaskInfoList07 != null && emrTaskInfoList07.size() > 0) {
                     EmrTaskInfo emrTaskInfo = new EmrTaskInfo();
                     emrTaskInfo.setMrFileId(id);
                     emrTaskInfo.setId(((EmrTaskInfo)emrTaskInfoList07.get(0)).getId());
                     emrTaskInfo.setTreatFlag("0");
                     this.emrTaskInfoService.updateEmrTaskInfo(emrTaskInfo);
                  }
               }
            }
         }
      }

      if (subfileIndex != null && "08".equals(subfileIndex.getMrState()) || index != null && "08".equals(index.getMrState())) {
         Long FreeId = subfileIndex != null ? subfileIndex.getId() : index.getId();
         EmrTaskInfo emrTaskInfoReq = new EmrTaskInfo();
         emrTaskInfoReq.setTaskAppDoc(subfileIndex != null ? subfileIndex.getCrePerCode() : index.getCrePerCode());
         emrTaskInfoReq.setMrFileId(FreeId);
         emrTaskInfoReq.setTaskType("2");
         emrTaskInfoReq.setBusSection("22");
         List<EmrTaskInfo> emrTaskInfoList = this.emrTaskInfoService.selectEmrTaskInfoList(emrTaskInfoReq);
         if (emrTaskInfoList != null && emrTaskInfoList.size() > 0) {
            List<String> signerCdList = (List)emrTaskInfoList.stream().filter((t) -> "2".equals(t.getTaskType())).map((t) -> t.getDocCd()).collect(Collectors.toList());
            List<Long> taskListIdList = (List)emrTaskInfoList.stream().filter((t) -> "2".equals(t.getTaskType())).map((t) -> t.getId()).collect(Collectors.toList());
            EmrSignData emrSignData = new EmrSignData();
            List<EmrSignData> emrSignDataList = new ArrayList();
            if (signerCdList != null && signerCdList.size() > 0) {
               emrSignData.setSignerCdList(signerCdList);
               emrSignData.setSignFileId(String.valueOf(id));
               emrSignDataList = this.emrSignDataService.selectEmrSignDataForFreeMoveList(emrSignData);
            }

            List<String> docCdSign = new ArrayList();
            List<Long> taskInfoList = new ArrayList();
            if (emrSignDataList != null && emrSignDataList.size() > 0) {
               docCdSign = (List)emrSignDataList.stream().filter((t) -> "1".equals(t.getIsValid())).map((t) -> t.getSignerCd()).collect(Collectors.toList());
            }

            if (docCdSign != null && docCdSign.size() > 0) {
               for(EmrTaskInfo emrTaskInfo : emrTaskInfoList) {
                  if (docCdSign.contains(emrTaskInfo.getDocCd())) {
                     taskInfoList.add(emrTaskInfo.getId());
                  }
               }
            } else {
               taskInfoList.addAll(taskListIdList);
            }

            if (taskInfoList != null && taskInfoList.size() > 0) {
               EmrTaskInfo emrTaskInfo = new EmrTaskInfo();
               emrTaskInfo.setMrFileId(id);
               emrTaskInfo.setIds(taskInfoList);
               emrTaskInfo.setTreatFlag("0");
               emrTaskInfo.setBusSection("22");
               this.emrTaskInfoService.updateEmrTaskInfo(emrTaskInfo);
            }
         }
      }

   }

   public List selectSealupIndexList(IndexVo indexVo) throws Exception {
      return this.indexMapper.selectSealupIndexList(indexVo);
   }

   public List selectTodoEmrList(IndexVo indexVo) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      List<IndexVo> indexList = this.indexMapper.selectTodoList(indexVo);
      this.getIndexSecLevelByEmplNumber(SecurityUtils.getLoginUser().getUser().getBasEmployee().getEmplNumber(), indexVo.getPatientId());
      List<IndexVo> indexVos = (List)indexList.stream().filter((t) -> t.getDataType().equals("3")).collect(Collectors.toList());
      List<String> mrTypeList = (List)indexVos.stream().map((t) -> t.getMrType()).collect(Collectors.toList());
      EmrTaskInfo emrTaskInfo = new EmrTaskInfo();
      emrTaskInfo.setTaskType("3");
      emrTaskInfo.setTreatFlag("0");
      emrTaskInfo.setPatientId(indexVo.getPatientId());
      List<EmrTaskInfo> emrTaskInfoList = this.emrTaskInfoService.selectEmrTaskInfoList(emrTaskInfo);
      Map<String, List<EmrTaskInfo>> taskMap = (Map<String, List<EmrTaskInfo>>)(emrTaskInfoList != null && !emrTaskInfoList.isEmpty() ? (Map)emrTaskInfoList.stream().collect(Collectors.groupingBy((s) -> s.getBusId())) : new HashMap(1));
      Map<String, List<String>> mrTypeListMap = (Map<String, List<String>>)(mrTypeList != null && !mrTypeList.isEmpty() ? this.qcRuleIterEmrService.getIterTypeCodeByEmrTypeCodes(mrTypeList) : new HashMap());
      List<UnConsultationDTO> unListData = this.indexMapper.selectUnConsultation(indexVo.getPatientId());
      List<String> qcStateList = new ArrayList();
      qcStateList.add("0");
      qcStateList.add("3");
      List<EmrQcList> emrQcListList = this.emrQcListService.selectByPatientsAndQcState(indexVo.getPatientId(), qcStateList, (Long)null);
      if (indexList != null && indexList.size() > 0) {
         for(IndexVo indexVo1 : indexList) {
            indexVo1.setAgingFlag(false);
            String vo1MrType = indexVo1.getMrType();
            if (StringUtils.isNotBlank(vo1MrType) && vo1MrType.equals("17")) {
               if (StringUtils.isNotBlank(indexVo1.getMrState()) && indexVo1.getMrState().equals("05")) {
                  UnConsultationDTO consultationDTO = (UnConsultationDTO)unListData.stream().filter((unConsultationDTO) -> indexVo1.getId().toString().equals(unConsultationDTO.getId())).findFirst().orElse((Object)null);
                  if (consultationDTO != null) {
                     indexVo1.setDutyDocName(StringUtils.isNotBlank(consultationDTO.getInvDocName()) ? consultationDTO.getInvDocName() : null);
                  } else {
                     indexVo1.setDutyDocName((String)null);
                  }
               } else {
                  indexVo1.setDutyDocName(indexVo1.getCrePerName());
               }
            } else if (StringUtils.isNotBlank(indexVo1.getMrState()) && indexVo1.getMrState().equals("03")) {
               indexVo1.setDutyDocName(indexVo1.getCrePerName());
            } else if (StringUtils.isNotBlank(indexVo1.getMrState()) && indexVo1.getMrState().equals("01")) {
               indexVo1.setDutyDocName((String)null);
            } else {
               List<EmrTaskInfo> emrTaskInfos = (List)taskMap.get(indexVo1.getId() == null ? indexVo1.getId() : indexVo1.getId().toString());
               indexVo1.setDutyDocName(emrTaskInfos != null && !emrTaskInfos.isEmpty() ? ((EmrTaskInfo)emrTaskInfos.get(0)).getDocName() : "");
            }

            if (indexVo1.getDataType().equals("3")) {
               List<String> mrTypeListTemp = (List)mrTypeListMap.get(indexVo1.getMrType());
               JSONArray mrTypeArr = mrTypeListTemp != null && !mrTypeListTemp.isEmpty() ? JSONArray.parseArray(JSON.toJSONString(mrTypeListTemp)) : new JSONArray();
               if (mrTypeArr.size() == 0) {
                  mrTypeArr.add(indexVo1.getMrType());
               }

               String mrType = mrTypeArr.toJSONString();
               indexVo1.setMrType(mrType);
            }

            if (StringUtils.isNotEmpty(indexVo1.getMainName()) && indexVo1.getMainName().equals("病程记录") && indexVo1.getRecoDate() != null) {
               indexVo1.setMainName(indexVo1.getMainName() + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss", indexVo1.getRecoDate()));
            }

            if (emrQcListList != null && emrQcListList.size() > 0) {
               List<EmrQcList> qcList = (List)emrQcListList.stream().filter((s) -> s.getMrFileId() != null).collect(Collectors.toList());
               if (qcList != null && qcList.size() > 0) {
                  Map<String, List<EmrQcList>> qcMap = (Map)qcList.stream().collect(Collectors.groupingBy((s) -> s.getMrFileId()));
                  if (indexVo1.getId() != null) {
                     List<EmrQcList> emrQcLists = (List)qcMap.get(indexVo1.getId().toString());
                     if (emrQcLists != null && emrQcLists.size() > 0) {
                        indexVo1.setFlawNum(emrQcLists.size() + "");
                     }
                  }
               }
            }
         }
      } else if (CollectionUtils.isNotEmpty(emrQcListList)) {
         List<EmrQcList> qcList = (List)emrQcListList.stream().filter((s) -> s.getMrFileId() == null).collect(Collectors.toList());
         if (CollectionUtils.isNotEmpty(qcList)) {
            for(EmrQcList emrQcList : qcList) {
               IndexVo indexVo1 = new IndexVo();
               indexVo1.setAltDate(emrQcList.getCreDate());
               List<EmrQcList> emrQcListList1 = (List)qcList.stream().filter((s) -> s.getMrType().equals(emrQcList.getMrType())).collect(Collectors.toList());
               indexVo1.setFlawNum(emrQcListList1.size() + "");
               indexVo1.setDataType("4");
               indexVo1.setRuleCode(String.valueOf(emrQcList.getRuleId()));
               indexVo1.setMrState("-1");
               indexVo1.setTaskId(emrQcList.getId());
               indexVo1.setPrimaryId(emrQcList.getId());
               indexVo1.setMainFileFlag("FALSE");
               indexVo1.setSecLevel("1");
               indexVo1.setAgingFlag(false);
               indexVo1.setMrTypeName(emrQcList.getMrTypeName());
               indexList.add(indexVo1);
            }
         }
      }

      return indexList;
   }

   public List selectTodoListFromTask(IndexVo indexVo) throws Exception {
      List<IndexVo> indexList = this.indexMapper.selectTodoListFromTask(indexVo);
      return indexList;
   }

   public List selectYetEmrList(IndexVo indexVo) throws Exception {
      List<String> mrStateList = new ArrayList();
      mrStateList.add("08");
      indexVo.setMrStateList(mrStateList);
      List<IndexVo> indexVoList = this.indexMapper.selectYetEmrList(indexVo);
      if (indexVoList != null && indexVoList.size() > 0) {
         List<Long> idList = (List)indexVoList.stream().map(Index::getId).distinct().collect(Collectors.toList());
         List<EmrSignRecord> emrSignRecordList = this.emrSignRecordService.selectEmrSignRecordListByIdAndFlag(idList, "0");
         Map<Long, List<EmrSignRecord>> signMap = (Map)emrSignRecordList.stream().collect(Collectors.groupingBy(EmrSignRecord::getMrFileId));
         List<String> qcStateList = new ArrayList();
         qcStateList.add("0");
         qcStateList.add("3");
         String recordBz = "1";
         List<EmrQcList> emrQcListList = this.emrQcListService.selectByPatientsAndQcStateAndRecordBz(indexVo.getPatientId(), qcStateList, (Long)null, recordBz);

         for(IndexVo indexVo1 : indexVoList) {
            List<EmrSignRecord> emrSignRecords = (List)signMap.get(indexVo1.getId());
            if (emrSignRecords != null && emrSignRecords.size() > 0) {
               emrSignRecords.stream().sorted(Comparator.comparing(EmrSignRecord::getCreDate));
               SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
               if (emrSignRecords.size() == 1) {
                  indexVo1.setFirstSign(sdf.format(((EmrSignRecord)emrSignRecords.get(0)).getSignTime()));
                  indexVo1.setFinishSign(((EmrSignRecord)emrSignRecords.get(0)).getDocName() + "(" + sdf.format(((EmrSignRecord)emrSignRecords.get(0)).getSignTime()) + ")");
               }

               if (emrSignRecords.size() == 2) {
                  indexVo1.setFirstSign(sdf.format(((EmrSignRecord)emrSignRecords.get(0)).getSignTime()));
                  indexVo1.setFinishSign(((EmrSignRecord)emrSignRecords.get(1)).getDocName() + "(" + sdf.format(((EmrSignRecord)emrSignRecords.get(1)).getSignTime()) + ")");
               }

               if (emrSignRecords.size() == 3) {
                  indexVo1.setFirstSign(sdf.format(((EmrSignRecord)emrSignRecords.get(0)).getSignTime()));
                  indexVo1.setSuperSign(((EmrSignRecord)emrSignRecords.get(1)).getDocName() + "(" + sdf.format(((EmrSignRecord)emrSignRecords.get(1)).getSignTime()) + ")");
                  indexVo1.setFinishSign(((EmrSignRecord)emrSignRecords.get(2)).getDocName() + "(" + sdf.format(((EmrSignRecord)emrSignRecords.get(2)).getSignTime()) + ")");
               }

               if (emrSignRecords.size() > 3) {
                  indexVo1.setFirstSign(sdf.format(((EmrSignRecord)emrSignRecords.get(0)).getSignTime()));
                  indexVo1.setSuperSign(((EmrSignRecord)emrSignRecords.get(1)).getDocName() + "(" + sdf.format(((EmrSignRecord)emrSignRecords.get(1)).getSignTime()) + ")");
                  indexVo1.setFinishSign(((EmrSignRecord)emrSignRecords.get(2)).getDocName() + "(" + sdf.format(((EmrSignRecord)emrSignRecords.get(2)).getSignTime()) + ")");
               }
            }

            if (emrQcListList != null && emrQcListList.size() > 0) {
               List<EmrQcList> qcList = (List)emrQcListList.stream().filter((s) -> s.getMrFileId() != null).collect(Collectors.toList());
               if (qcList != null && qcList.size() > 0) {
                  Map<String, List<EmrQcList>> qcMap = (Map)qcList.stream().collect(Collectors.groupingBy((s) -> s.getMrFileId()));
                  List<EmrQcList> emrQcLists = (List)qcMap.get(indexVo1.getId().toString());
                  if (emrQcLists != null && emrQcLists.size() > 0) {
                     indexVo1.setFlawNum(emrQcLists.size() + "");
                  }
               }
            }

            if (StringUtils.isNotEmpty(indexVo1.getMainName()) && indexVo1.getMainName().equals("病程记录") && indexVo1.getRecoDate() != null) {
               indexVo1.setMainName(indexVo1.getMainName() + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss", indexVo1.getRecoDate()));
            }
         }
      }

      return indexVoList;
   }

   public List selectTodoSubList(IndexVo indexVo) throws Exception {
      List<IndexVo> indexVos = this.indexMapper.selectTodoSubList(indexVo);
      List<String> mrTypeList = (List)indexVos.stream().map((t) -> t.getMrType()).collect(Collectors.toList());
      Map<String, List<String>> mrTypeListMap = (Map<String, List<String>>)(mrTypeList != null && !mrTypeList.isEmpty() ? this.qcRuleIterEmrService.getIterTypeCodeByEmrTypeCodes(mrTypeList) : new HashMap());

      for(IndexVo indexVo1 : indexVos) {
         List<String> mrTypeListTemp = (List)mrTypeListMap.get(indexVo1.getMrType());
         JSONArray mrTypeArr = mrTypeListTemp != null && !mrTypeListTemp.isEmpty() ? JSONArray.parseArray(JSON.toJSONString(mrTypeListTemp)) : new JSONArray();
         if (mrTypeArr.size() == 0) {
            mrTypeArr.add(indexVo1.getMrType());
         }

         String mrType = mrTypeArr.toJSONString();
         indexVo1.setMrType(mrType);
      }

      return indexVos;
   }

   public Map selectSysUserDept(Long userId) {
      Map<Long, String> map = new HashMap();

      for(SysDept dept : this.sysDeptMapper.selectDeptByUserId(userId)) {
         map.put(dept.getDeptId(), dept.getDeptName());
      }

      return map;
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void settingSecLevel(IndexSecLevelVo indexSecLevelVo) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      List<Long> idList = new ArrayList();

      for(Long id : indexSecLevelVo.getIds()) {
         idList.add(id);
      }

      if (idList != null && idList.size() > 0) {
         IndexVo indexVo = new IndexVo();
         indexVo.setSecLevel(indexSecLevelVo.getSecLevel());
         indexVo.setAltPerCode(user.getUserName());
         indexVo.setAltPerName(user.getNickName());
         indexVo.setIdList(idList);
         this.indexMapper.updateIndexSecLevel(indexVo);
         SubfileIndexVo subfileIndexVo = new SubfileIndexVo();
         subfileIndexVo.setIdList(idList);
         subfileIndexVo.setSecLevel(indexSecLevelVo.getSecLevel());
         this.subfileIndexService.updateSubfileIndexSecLevel(subfileIndexVo);
      }

   }

   public List selectOtherIndexTreeList(Index index) throws Exception {
      List<TreeSelect> treeList = new ArrayList();
      List<Index> list = this.indexMapper.selectIndexList(index);
      List<Index> indexList = (List)list.stream().filter((s) -> !s.getMrTypeCd().equals("01")).collect(Collectors.toList());
      Map<String, List<Index>> indexListMap = (Map)indexList.stream().collect(Collectors.groupingBy((i) -> i.getMrTypeCd()));
      List<SysDictData> dictDataList = this.sysDictDataService.selectDictDataByType("s003");
      Map<String, SysDictData> dictDataMap = (Map)dictDataList.stream().collect(Collectors.toMap((t) -> t.getDictValue(), Function.identity()));
      Map<String, List<TreeSelect>> babyIndexMap = new HashMap(1);

      for(String mrType : indexListMap.keySet()) {
         List<Index> mrIndexList = (List)indexListMap.get(mrType);
         SysDictData mrTypeObj = (SysDictData)dictDataMap.get(mrType);
         TreeSelect treeSelect = new TreeSelect(mrTypeObj.getDictCode(), mrTypeObj.getDictValue(), mrTypeObj.getDictLabel());
         List<TreeSelect> children = new ArrayList(1);

         for(Index param : mrIndexList) {
            TreeSelect tree = new TreeSelect(param.getId(), String.valueOf(param.getId()), param.getMrFileShowName());
            if (StringUtils.isNotBlank(index.getBabyId())) {
               List<TreeSelect> babyListTemp = (List)babyIndexMap.get(index.getBabyId());
               if (babyListTemp == null || babyListTemp != null && babyListTemp.isEmpty()) {
                  babyListTemp = new ArrayList(1);
               }

               babyListTemp.add(tree);
               babyIndexMap.put(index.getBabyId(), babyListTemp);
            } else {
               children.add(tree);
            }
         }

         treeSelect.setChildren(children);
         treeList.add(treeSelect);
      }

      return treeList;
   }

   public List selectSetPropElemList(String patientId, List list) throws Exception {
      List<ElemGender> resultList = new ArrayList();
      Personalinfo personalinfo = this.personalinfoService.selectPersonalinfoById(patientId);
      if (personalinfo != null) {
         for(ElemGender gender : (List)list.stream().filter((s) -> s.getSexCd() != null && !s.getSexCd().equals(personalinfo.getSexCd())).collect(Collectors.toList())) {
            ElemGender elemGender = new ElemGender();
            elemGender.setContElemName(gender.getContElemName());
            elemGender.setTypeFlag(gender.getTypeFlag());
            resultList.add(elemGender);
         }
      }

      return resultList;
   }

   public Map selectSetStructsTextIdList(TmplIndex tempIndex, String patientId, String testExamResultId, String babyId) throws Exception {
      Map<String, List<String>> map = new HashMap();
      List<String> keyList = new ArrayList();
      List<String> valueList = new ArrayList();
      List<ElemDate> elemDateList = this.elemDateService.selectElemDateListByTempId(tempIndex.getId());
      Map<String, List<String>> elemDateMap = this.elemDateKeyAndValue(elemDateList);
      keyList.addAll((Collection)elemDateMap.get("setStructsTextIdList"));
      valueList.addAll((Collection)elemDateMap.get("setStructsTextValueList"));
      List<ElemMacro> elemMacroList = this.elemMacroService.selectElemMacroListByTempId(tempIndex.getId());
      Map<String, List<String>> elemMacroMap = this.elemMacroKeyAndValue(patientId, testExamResultId, babyId, elemMacroList);
      keyList.addAll((Collection)elemMacroMap.get("setStructsTextIdList"));
      valueList.addAll((Collection)elemMacroMap.get("setStructsTextValueList"));
      QuoteElem quoteElemParam = new QuoteElem();
      quoteElemParam.setTempType(tempIndex.getTempType());
      quoteElemParam.setIsValid("1");
      List<QuoteElem> quoteElemList = this.quoteElemService.selectQuoteElemList(quoteElemParam);
      String editorType = this.sysEmrConfigService.selectSysEmrConfigByKey("0000");
      String xmlStr = "";
      if (StringUtils.isNotEmpty(tempIndex.getTempFile())) {
         Map<String, String> jsonMap = JSON.parseObject(tempIndex.getTempFile());
         xmlStr = (String)jsonMap.get("xmlStr");
      }

      TempIndexSaveElemVo tempIndexSaveElemVo = XmlElementParseUtil.getSaveElemFromXml(xmlStr, editorType);
      List<Long> elemIdList = (List)quoteElemList.stream().map((t) -> Long.valueOf(t.getElemId())).collect(Collectors.toList());
      List<QuoteElemVo> tmplQuoteElemList = XmlElementParseUtil.getSysQuoteElem(tempIndexSaveElemVo.getElemAttriList(), elemIdList);
      String consTempId = this.sysEmrConfigService.selectSysEmrConfigByKey("0032");
      if (!consTempId.equals(tempIndex.getId().toString())) {
         Map<String, List<String>> quoteMap = this.quoteElemKeyAndValue(tempIndex.getId(), patientId, tmplQuoteElemList);
         keyList.addAll((Collection)quoteMap.get("setStructsTextIdList"));
         valueList.addAll((Collection)quoteMap.get("setStructsTextValueList"));
         map.put("base64IdList", quoteMap.get("base64IdList"));
         map.put("base64ValueList", quoteMap.get("base64ValueList"));
         map.put("mrFileIdList", quoteMap.get("mrFileIdList"));
      }

      map.put("setStructsTextIdList", keyList);
      map.put("setStructsTextValueList", valueList);
      List<QuoteElemVo> elemForBase64 = this.quoteElemService.selectFromQuoteElemForBase64(tempIndex.getTempType());
      List<Long> elemIdList2 = (List<Long>)(CollectionUtils.isNotEmpty(elemForBase64) ? (List)elemForBase64.stream().map((t) -> Long.valueOf(t.getElemId())).collect(Collectors.toList()) : new ArrayList(1));
      List<QuoteElemVo> tmplQuoteElemBase64List = XmlElementParseUtil.getSysQuoteElem(tempIndexSaveElemVo.getElemAttriList(), elemIdList2);
      List<String> tmplQuoteElemBase64NameList = CollectionUtils.isNotEmpty(tmplQuoteElemBase64List) ? (List)tmplQuoteElemBase64List.stream().map((t) -> t.getContElemName()).collect(Collectors.toList()) : null;
      map.put("tmplQuoteElemBase64List", tmplQuoteElemBase64NameList);
      return map;
   }

   public Map elemDateKeyAndValue(List list) throws Exception {
      Map<String, List<String>> map = new HashMap();
      List<String> keyList = new ArrayList();
      List<String> valueList = new ArrayList();
      List<ElemDate> elemDateList = (List)list.stream().filter((s) -> s.getNowDate() != null && s.getNowDate().equals("TRUE")).collect(Collectors.toList());
      SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      String nowDate = df.format(new Date());

      for(ElemDate elemDateObj : elemDateList) {
         keyList.add(elemDateObj.getContElemName());
         valueList.add(nowDate);
      }

      map.put("setStructsTextIdList", keyList);
      map.put("setStructsTextValueList", valueList);
      return map;
   }

   public Map elemMacroKeyAndValue(String patientId, String testExamResultId, String babyId, List elemMacroList) throws Exception {
      VisitinfoVo visitinfoVo = this.visitinfoService.selectVisitinfoByPatientId(patientId);
      Map<String, List<String>> mapList = new HashMap();
      List<String> keyList = new ArrayList();
      List<String> valueList = new ArrayList();
      List<ElemMacro> macroList = (List)elemMacroList.stream().filter((s) -> StringUtils.isEmpty(s.getReplType()) || s.getReplType().equals("TRUE")).collect(Collectors.toList());
      List<ElemMacro> proList = (List)elemMacroList.stream().filter((s) -> StringUtils.isNotEmpty(s.getReplType()) && s.getReplType().equals("SOLAR")).collect(Collectors.toList());
      if (macroList != null && !macroList.isEmpty()) {
         Map<String, List<ElemMacro>> map = (Map)macroList.stream().collect(Collectors.groupingBy((t) -> t.getSourTable()));

         for(String key : map.keySet()) {
            Map<String, Object> macroMap = new HashMap(1);
            ElemMacroVo elemMacroVo = new ElemMacroVo();
            elemMacroVo.setSourTable(key);
            if (key.toLowerCase().equals("v_test_exam_result")) {
               elemMacroVo.setPatientId(testExamResultId);
               macroMap = StringUtils.isNotBlank(testExamResultId) ? this.elemMacroService.selectElemMacroInfoByTable(elemMacroVo) : macroMap;
            } else {
               elemMacroVo.setPatientId(patientId);
               if (key.toLowerCase().equals("v_baby_info")) {
                  elemMacroVo.setBabyId(babyId);
               }

               macroMap = this.elemMacroService.selectElemMacroInfoByTable(elemMacroVo);
            }

            for(ElemMacro macro : (List)map.get(key)) {
               String macroStr = "";

               try {
                  macroStr = macroMap.get(macro.getSourColu().toUpperCase()).toString();
                  if (macroMap.get(macro.getSourColu().toUpperCase()).toString().contains("oracle.sql.CLOB")) {
                     String clobToString = CommonUtils.ClobToString((Clob)macroMap.get(macro.getSourColu().toUpperCase()));
                     macroStr = clobToString;
                  }
               } catch (Exception var30) {
                  System.out.println("宏替换内容为空");
               }

               if (StringUtils.isNotBlank(macroStr)) {
                  keyList.add(macro.getContElemName());
                  valueList.add(macroStr);
               }
            }
         }
      }

      if (proList != null && !proList.isEmpty()) {
         SolarTermsUtils.LunarCalendarFestivalUtils festival = new SolarTermsUtils.LunarCalendarFestivalUtils();
         Date inhosTime = visitinfoVo.getInhosTime();
         festival.initLunarCalendarInfo(DateUtils.parseDateToStr("yyyy-MM-dd", inhosTime));
         String lunarTerm = festival.getLunarTerm();
         if (StringUtils.isEmpty(lunarTerm)) {
            for(Date tempDate = inhosTime; StringUtils.isEmpty(lunarTerm); lunarTerm = festival.getLunarTerm()) {
               tempDate = DateUtils.addDays(tempDate, -1);
               festival.initLunarCalendarInfo(DateUtils.parseDateToStr("yyyy-MM-dd", tempDate));
            }
         }

         ElemMacroVo elemMacroVo = new ElemMacroVo();
         elemMacroVo.setPatientId(patientId);
         Map<String, Object> macroMap = this.elemMacroService.selectElemMacroInfoByPat(elemMacroVo);
         BigDecimal weight = macroMap.get("weight") != null ? new BigDecimal(macroMap.get("weight").toString()) : null;
         BigDecimal height = macroMap.get("height") != null ? new BigDecimal(macroMap.get("height").toString()) : null;
         BigDecimal bmi = null;
         if (weight != null && height != null) {
            bmi = weight.divide(height.divide(new BigDecimal("100")).multiply(new BigDecimal("2"))).setScale(2, 4);
         }

         List<ElemAttri> elemAttriList = new ArrayList(1);

         for(ElemMacro elemMacro : proList) {
            ElemAttri param = new ElemAttri();
            param.setTempId(elemMacro.getTempId());
            param.setContElemName(elemMacro.getContElemName());
            if (elemMacro.getTempId() != null && StringUtils.isNotBlank(elemMacro.getContElemName())) {
               elemAttriList.add(param);
            }
         }

         List<ElemAttri> elemAttris = CollectionUtils.isNotEmpty(elemAttriList) ? this.elemAttriService.selectElemAttriByTempIdAndContElemName(elemAttriList) : null;
         Map<String, ElemAttri> elemAttriMap = (Map<String, ElemAttri>)(CollectionUtils.isNotEmpty(elemAttris) ? (Map)elemAttris.stream().collect(Collectors.toMap((t) -> t.getContElemName(), Function.identity())) : new HashMap(1));

         for(ElemMacro elemMacro : proList) {
            ElemAttri elemAttri = (ElemAttri)elemAttriMap.get(elemMacro.getContElemName());
            String elemAttriStr = elemAttri != null ? elemAttri.getElemAttri() : null;
            JSONObject elemAttriObj = StringUtils.isNotBlank(elemAttriStr) ? JSON.parseObject(elemAttriStr) : null;
            JSONArray elems = (JSONArray)elemAttriObj.get("elems");
            JSONObject elemsObj = (JSONObject)elems.get(0);
            String macroField = elemsObj.getJSONObject("elem").getString("macroField");
            if (macroField.equals("1")) {
               keyList.add(elemMacro.getContElemName());
               valueList.add(festival.getLunarTerm());
            } else if (macroField.equals("2") && bmi != null) {
               keyList.add(elemMacro.getContElemName());
               valueList.add(bmi.toString());
            }
         }
      }

      mapList.put("setStructsTextIdList", keyList);
      mapList.put("setStructsTextValueList", valueList);
      return mapList;
   }

   public Map quoteElemKeyAndValue(Long tempId, String patientId, List quoteList) throws Exception {
      Map<String, List<String>> map = new HashMap(1);
      List<String> keyList = new ArrayList(1);
      List<String> valueList = new ArrayList(1);
      List<String> base64KeyList = new ArrayList(1);
      List<String> base64ValueList = new ArrayList(1);
      List<String> mrFileIdList = new ArrayList(1);
      TmplIndex tmplIndex = this.tmplIndexService.selectTmplIndexById(tempId);
      List<Long> elemIdList = (List)quoteList.stream().map((t) -> Long.valueOf(t.getElemId())).distinct().collect(Collectors.toList());
      if (elemIdList != null && elemIdList.size() > 0) {
         List<QuoteElemVo> quoteElemList = this.quoteElemService.selectQuoteElemVoByTemp(tmplIndex.getTempType(), elemIdList);
         Map<String, List<QuoteElemVo>> mapList = (Map)quoteElemList.stream().collect(Collectors.groupingBy((i) -> i.getElemId()));
         EmrElemstoe emrElemstoe = new EmrElemstoe();
         emrElemstoe.setPatientId(patientId);
         List<EmrElemstoe> emrElemstoeList = this.emrElemstoeService.selectEmrElemstoeList(emrElemstoe);

         for(QuoteElemVo tmplQuoteElem : quoteList) {
            StringBuffer elemString = new StringBuffer("");
            new ArrayList(1);
            List quoteElemVoList = (List)mapList.get(tmplQuoteElem.getElemId().toString());
            if (quoteElemVoList != null) {
               quoteElemVoList.sort(Comparator.comparing(QuoteElem::getElemOrder));

               for(QuoteElemVo quoteElemVo : quoteElemVoList) {
                  List<EmrElemstoe> elemList = (List)emrElemstoeList.stream().filter((s) -> s.getMrTypeCd().equals(quoteElemVo.getFromMrTypeCd()) && s.getElemId().equals(quoteElemVo.getFromElemId())).sorted(Comparator.comparing(EmrElemstoe::getCreDate).reversed()).collect(Collectors.toList());
                  if (StringUtils.isNotBlank(quoteElemVo.getBase64Flag()) && quoteElemVo.getBase64Flag().equals("1")) {
                     elemList = (List)elemList.stream().filter((s) -> StringUtils.isNotBlank(s.getElemConBase64())).collect(Collectors.toList());
                     String elemBase64 = CollectionUtils.isNotEmpty(elemList) ? ((EmrElemstoe)elemList.get(0)).getElemConBase64() : null;
                     String mrFileId = CollectionUtils.isNotEmpty(elemList) ? String.valueOf(((EmrElemstoe)elemList.get(0)).getMrFileId()) : null;
                     if (StringUtils.isNotBlank(elemBase64)) {
                        base64KeyList.add(tmplQuoteElem.getContElemName());
                        base64ValueList.add(elemBase64);
                        mrFileIdList.add(mrFileId);
                     }
                  } else {
                     elemList = (List)elemList.stream().filter((s) -> StringUtils.isNotBlank(s.getElemCon())).collect(Collectors.toList());
                     if (elemList != null && elemList.size() > 0 && ((EmrElemstoe)elemList.get(0)).getElemCon() != null) {
                        String elemCon = ((EmrElemstoe)elemList.get(0)).getElemCon();
                        if (elemCon.substring(elemCon.length() - 1).equals("\n")) {
                           elemCon = elemCon.substring(0, elemCon.length() - 1);
                        }

                        elemString.append(elemCon);
                     }

                     if (StringUtils.isNotBlank(elemString)) {
                        keyList.add(tmplQuoteElem.getContElemName());
                        valueList.add(elemString.toString());
                     }
                  }
               }
            }
         }
      }

      map.put("setStructsTextIdList", keyList);
      map.put("setStructsTextValueList", valueList);
      map.put("base64IdList", base64KeyList);
      map.put("base64ValueList", base64ValueList);
      map.put("mrFileIdList", mrFileIdList);
      return map;
   }

   public Map quoteElemLink(Long tempId, String patientId, List quoteList) throws Exception {
      Map<String, List<String>> map = new HashMap(1);
      List<String> keyList = new ArrayList(1);
      List<String> valueList = new ArrayList(1);
      List<String> base64KeyList = new ArrayList(1);
      List<String> base64ValueList = new ArrayList(1);
      TmplIndex tmplIndex = this.tmplIndexService.selectTmplIndexById(tempId);
      List<Long> elemIdList = (List)quoteList.stream().map((t) -> Long.valueOf(t.getElemId())).distinct().collect(Collectors.toList());
      if (elemIdList != null && elemIdList.size() > 0) {
         List<QuoteElemVo> quoteElemList = this.quoteElemService.selectQuoteElemVoByTemp(tmplIndex.getTempType(), elemIdList);
         Map<String, List<QuoteElemVo>> mapList = (Map)quoteElemList.stream().collect(Collectors.groupingBy((i) -> i.getElemId()));
         EmrElemstoe emrElemstoe = new EmrElemstoe();
         emrElemstoe.setPatientId(patientId);
         List<EmrElemstoe> emrElemstoeList = this.emrElemstoeService.selectEmrElemstoeList(emrElemstoe);

         for(QuoteElemVo tmplQuoteElem : quoteList) {
            StringBuffer elemString = new StringBuffer("");
            new ArrayList(1);
            List quoteElemVoList = (List)mapList.get(tmplQuoteElem.getElemId().toString());
            if (quoteElemVoList != null) {
               quoteElemVoList.sort(Comparator.comparing(QuoteElem::getElemOrder));

               for(QuoteElemVo quoteElemVo : quoteElemVoList) {
                  List<EmrElemstoe> elemList = (List)emrElemstoeList.stream().filter((s) -> s.getMrTypeCd().equals(quoteElemVo.getFromMrTypeCd()) && s.getElemId().equals(quoteElemVo.getFromElemId())).sorted(Comparator.comparing(EmrElemstoe::getCreDate).reversed()).collect(Collectors.toList());
                  if (StringUtils.isNotBlank(quoteElemVo.getBase64Flag()) && quoteElemVo.getBase64Flag().equals("1")) {
                     elemList = (List)elemList.stream().filter((s) -> StringUtils.isNotBlank(s.getElemConBase64())).collect(Collectors.toList());
                     String elemBase64 = CollectionUtils.isNotEmpty(elemList) ? ((EmrElemstoe)elemList.get(0)).getElemConBase64() : null;
                     if (StringUtils.isNotBlank(elemBase64)) {
                        base64KeyList.add(tmplQuoteElem.getContElemName());
                        base64ValueList.add(elemBase64);
                     }
                  } else {
                     elemList = (List)elemList.stream().filter((s) -> StringUtils.isNotBlank(s.getElemCon())).collect(Collectors.toList());
                     if (elemList != null && elemList.size() > 0 && ((EmrElemstoe)elemList.get(0)).getElemCon() != null) {
                        String elemCon = ((EmrElemstoe)elemList.get(0)).getElemCon();
                        if (elemCon.substring(elemCon.length() - 1).equals("\n")) {
                           elemCon = elemCon.substring(0, elemCon.length() - 1);
                        }

                        elemString.append(elemCon);
                     }

                     if (StringUtils.isNotBlank(elemString)) {
                        keyList.add(tmplQuoteElem.getContElemName());
                        valueList.add(elemString.toString());
                     }
                  }
               }
            }
         }
      }

      map.put("setStructsTextIdList", keyList);
      map.put("setStructsTextValueList", valueList);
      map.put("base64IdList", base64KeyList);
      map.put("base64ValueList", base64ValueList);
      return map;
   }

   public List selectHisIndexTreeList(String patientId) throws Exception {
      List<VisitinfoVo> visitinfoVos = this.visitinfoService.selectVisitinfosById(patientId);
      List<HisIndexVo> hisIndexVoList = new ArrayList();
      if (visitinfoVos != null && !visitinfoVos.isEmpty()) {
         List<VisitinfoVo> hisVisitinfoVos = (List)visitinfoVos.stream().filter((t) -> !t.getPatientId().equals(patientId)).collect(Collectors.toList());
         if (hisVisitinfoVos != null && hisVisitinfoVos.size() > 0) {
            for(VisitinfoVo visitinfoVo : hisVisitinfoVos) {
               String suffixStr = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, visitinfoVo.getInhosTime());
               suffixStr = suffixStr.substring(2);
               List<HisIndexVo> treeList = this.getMrDocList(visitinfoVo.getPatientId());
               HisIndexVo hisIndexVo = new HisIndexVo();
               hisIndexVo.setCode(visitinfoVo.getPatientId());
               hisIndexVo.setName(visitinfoVo.getInDeptName() + " " + DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, visitinfoVo.getInhosTime()));
               if (treeList != null && treeList.size() > 0) {
                  hisIndexVo.setChildren(treeList);
               }

               hisIndexVoList.add(hisIndexVo);
            }
         }
      }

      return hisIndexVoList;
   }

   private List getMrDocList(String patientId) {
      Index indexParam = new Index();
      indexParam.setPatientId(patientId);
      indexParam.setDelFlag("0");
      List<Index> indexList = this.indexMapper.selectIndexList(indexParam);
      Map<String, List<Index>> indexListMap = (Map)indexList.stream().collect(Collectors.groupingBy((t) -> t.getMrTypeCd()));
      List<SysDictData> dictDataList = this.dictTypeService.selectDictDataByType("s003");
      Map<String, SysDictData> dictDataMap = (Map)dictDataList.stream().collect(Collectors.toMap((t) -> t.getDictValue(), Function.identity()));
      List<HisIndexVo> mrDocList = new ArrayList(1);
      Map<String, List<HisIndexVo>> babyIndexMap = new HashMap(1);

      for(String mrType : indexListMap.keySet()) {
         List<Index> mrIndexList = (List)indexListMap.get(mrType);
         SysDictData mrTypeObj = (SysDictData)dictDataMap.get(mrType);
         HisIndexVo tempIndex = new HisIndexVo();
         tempIndex.setName(mrTypeObj.getDictLabel());
         tempIndex.setCode(mrType);
         List<HisIndexVo> children = new ArrayList(1);

         for(Index index : mrIndexList) {
            HisIndexVo tempChild = new HisIndexVo();
            tempChild.setCode(index.getId().toString());
            tempChild.setName(index.getMrFileShowName());
            tempChild.setMrType(index.getMrType());
            if (StringUtils.isNotBlank(index.getBabyId())) {
               List<HisIndexVo> babyListTemp = (List)babyIndexMap.get(index.getBabyId());
               if (babyListTemp == null || babyListTemp != null && babyListTemp.isEmpty()) {
                  babyListTemp = new ArrayList(1);
               }

               babyListTemp.add(tempChild);
               babyIndexMap.put(index.getBabyId(), babyListTemp);
            } else {
               children.add(tempChild);
            }
         }

         tempIndex.setChildren(children);
         mrDocList.add(tempIndex);
      }

      return mrDocList;
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public IndexVo selectEmrInfoAndFileList(Long id, String secrecyBz, HttpServletRequest httpServletRequest) throws Exception {
      List<SysStaElem> sysStaElemList = this.sysStaElemMapper.selectEncryptInfo();
      List<SysEmrTypeConfigVo> emrTypes = this.sysEmrTypeConfigService.selectSysEmrTypeConfigList(new SysEmrTypeConfig());
      Index index = this.indexMapper.selectIndexById(id);
      IndexVo indexVo = new IndexVo();
      if (index != null) {
         EmrTaskInfo emrTaskInfo = new EmrTaskInfo();
         emrTaskInfo.setTaskType("3");
         emrTaskInfo.setTreatFlag("0");
         emrTaskInfo.setPatientId(index.getPatientId());
         List<EmrTaskInfo> emrTaskInfoList = this.emrTaskInfoService.selectEmrTaskInfoList(emrTaskInfo);
         Map<String, List<EmrTaskInfo>> taskMap = (Map<String, List<EmrTaskInfo>>)(emrTaskInfoList != null && !emrTaskInfoList.isEmpty() ? (Map)emrTaskInfoList.stream().collect(Collectors.groupingBy((s) -> s.getBusId())) : new HashMap(1));
         List<String> mrStateList = Arrays.asList("05", "07");
         List<SysEmrLog> sysEmrLogList = new ArrayList();
         if (!index.getMrType().equals("MAINFILE")) {
            SysEmrLog sysEmrLog = new SysEmrLog();
            sysEmrLog.setOptType("7");
            sysEmrLog.setOptTypeName("病历浏览");
            sysEmrLog.setMrFileId(index.getId());
            sysEmrLog.setMrTypeCd(index.getMrType());
            sysEmrLog.setMrFileName(index.getMrFileShowName());
            sysEmrLog.setPatientId(index.getPatientId());
            sysEmrLog.setIp(IPAddressUtil.getIPAddress(httpServletRequest));
            sysEmrLogList.add(sysEmrLog);
         }

         BeanUtils.copyProperties(index, indexVo);
         String base64 = "";

         try {
            base64 = Base64Util.getFileBase64("emrBase64/" + index.getPatientId(), indexVo.getId().toString() + ".txt");
            this.log.warn("selectEmrInfoAndFileList : 读取病历内容-文件服务器 : " + indexVo.getId().toString() + " : " + base64);
         } catch (Exception var31) {
            this.log.error("未找到文件服务器中该病历的base64文件");
            EmrBinary emrBinary = this.emrBinaryService.selectEmrBinaryById(indexVo.getId());
            if (emrBinary != null && StringUtils.isNotEmpty(emrBinary.getMrCon())) {
               Map<String, String> map = (Map)JSON.parse(emrBinary.getMrCon());
               base64 = (String)map.get("base64");
               this.log.warn("selectEmrInfoAndFileList : 读取病历内容-emrBinary : " + indexVo.getId().toString() + " : " + base64);
            }
         }

         indexVo.setMrCon(base64);
         SubfileIndex subfileIndex = new SubfileIndex();
         subfileIndex.setMainId(indexVo.getId());
         List<SubfileIndex> subfileIndexList = this.subfileIndexService.selectSubfileIndexList(subfileIndex);
         List<SubfileIndexVo> subfileIndexVoList = new ArrayList(subfileIndexList.size());
         List<EmrQcListVo> list = this.getIndexQcList(indexVo, subfileIndexList);
         if (subfileIndexList != null && subfileIndexList.size() > 0) {
            List<Long> tmplIndexIds = (List)subfileIndexList.stream().map((t) -> t.getTempId()).distinct().collect(Collectors.toList());
            List<TmplIndex> subTempIndexList = this.tmplIndexService.selectListByIds(tmplIndexIds);
            Map<Long, TmplIndex> tmplIndexMap = (Map<Long, TmplIndex>)(CollectionUtils.isNotEmpty(subTempIndexList) ? (Map)subTempIndexList.stream().collect(Collectors.toMap((t) -> t.getId(), Function.identity())) : new HashMap(1));
            Map<String, List<EmrQcListVo>> qcListMap = (Map)list.stream().collect(Collectors.groupingBy((t) -> t.getMrFileId()));

            for(SubfileIndex subfileIndex1 : subfileIndexList) {
               SubfileIndexVo subfileIndexVo = new SubfileIndexVo();
               BeanUtils.copyProperties(subfileIndex1, subfileIndexVo);
               List<EmrQcListVo> listTemp = (List)qcListMap.get(subfileIndex1.getId().toString());
               String manMadeQcListFlag = CollectionUtils.isNotEmpty(listTemp) ? "1" : "0";
               subfileIndexVo.setManMadeQcListFlag(manMadeQcListFlag);
               subfileIndexVo.setChecked(CommonConstants.BOOL_FALSE);
               SysEmrLog sysEmrLog = new SysEmrLog();
               sysEmrLog.setOptType("7");
               sysEmrLog.setOptTypeName("病历浏览");
               sysEmrLog.setMrFileId(subfileIndex1.getId());
               sysEmrLog.setMrTypeCd(subfileIndex1.getMrType());
               sysEmrLog.setMrFileName(subfileIndex1.getMrFileShowName());
               sysEmrLog.setPatientId(index.getPatientId());
               sysEmrLogList.add(sysEmrLog);
               List<SysEmrTypeConfigVo> emrType = (List)emrTypes.stream().filter((s) -> s.getEmrTypeCode().equals(subfileIndex1.getMrType())).collect(Collectors.toList());
               subfileIndexVo.setEmrTypeName(((SysEmrTypeConfigVo)emrType.get(0)).getEmrTypeName());
               if (mrStateList.contains(subfileIndexVo.getMrState())) {
                  List<EmrTaskInfo> emrTaskInfos = (List)taskMap.get(subfileIndex1.getId() == null ? subfileIndex1.getId() : subfileIndex1.getId().toString());
                  subfileIndexVo.setDutyDocName(emrTaskInfos != null && !emrTaskInfos.isEmpty() ? ((EmrTaskInfo)emrTaskInfos.get(0)).getDocName() : "");
                  if (emrTaskInfos != null && CollectionUtils.isNotEmpty(emrTaskInfos)) {
                     BasCertInfo basCertInfo = this.basCertInfoService.selectBasCertInfoByEmployeenumber(((EmrTaskInfo)emrTaskInfos.get(0)).getDocCd());
                     subfileIndexVo.setSignCertSn(basCertInfo != null ? basCertInfo.getCertSn() : null);
                  }
               }

               subfileIndexVoList.add(subfileIndexVo);
               TmplIndex tmplIndex = (TmplIndex)tmplIndexMap.get(subfileIndexVo.getTempId());
               subfileIndexVo.setTempName(tmplIndex != null ? tmplIndex.getTempName() : null);
               subfileIndexVo.setShowName(tmplIndex != null ? tmplIndex.getShowName() : null);
               subfileIndexVo.setTempMajor(tmplIndex != null ? tmplIndex.getTempMajor() : null);
               subfileIndexVo.setTempDisease(tmplIndex != null ? tmplIndex.getTempDisease() : null);
            }

            if (indexVo.getMrFileShowName().equals("病程记录")) {
               indexVo.setMrFileShowName(indexVo.getMrFileShowName() + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss", indexVo.getRecoDate()));
            }
         } else {
            List<SysEmrTypeConfigVo> emrType = (List)emrTypes.stream().filter((s) -> s.getEmrTypeCode().equals(indexVo.getMrType())).collect(Collectors.toList());
            indexVo.setEmrTypeName(((SysEmrTypeConfigVo)emrType.get(0)).getEmrTypeName());
            if (mrStateList.contains(indexVo.getMrState())) {
               List<EmrTaskInfo> emrTaskInfos = (List)taskMap.get(indexVo.getId() == null ? indexVo.getId() : indexVo.getId().toString());
               indexVo.setDutyDocName(emrTaskInfos != null && !emrTaskInfos.isEmpty() ? ((EmrTaskInfo)emrTaskInfos.get(0)).getDocName() : "");
               if (emrTaskInfos != null && CollectionUtils.isNotEmpty(emrTaskInfos)) {
                  BasCertInfo basCertInfo = this.basCertInfoService.selectBasCertInfoByEmployeenumber(((EmrTaskInfo)emrTaskInfos.get(0)).getDocCd());
                  indexVo.setSignCertSn(basCertInfo != null ? basCertInfo.getCertSn() : null);
               }
            }
         }

         subfileIndexVoList.sort(Comparator.comparing(SubfileIndex::getRecoDate).reversed());
         indexVo.setSubFileIndexList(subfileIndexVoList);
         List<Map<String, Object>> dataElementList = new ArrayList();
         List<Map<String, Object>> dataArrayList = new ArrayList();
         List<Map<String, Object>> dataAreaList = new ArrayList();
         if (!index.getMrType().equals("MAINFILE")) {
            List<ElemMacro> elemMacroList = this.elemMacroService.selectElemMacroListByTempId(index.getTempId());
            List<String> contElemNameList = (List)elemMacroList.stream().map((t) -> t.getContElemName()).collect(Collectors.toList());
            List<ElemAttri> attriList = this.elemAttriService.selectElemAttriByTempId(index.getTempId());
            if (StringUtils.isNotBlank(secrecyBz) && "1".equals(secrecyBz)) {
               if (sysStaElemList != null && sysStaElemList.size() > 0 && !attriList.isEmpty()) {
                  for(Long elemId : (List)sysStaElemList.stream().map((t) -> t.getId()).collect(Collectors.toList())) {
                     List<ElemAttri> attriListRes = (List)attriList.stream().filter((t) -> t.getElemId().equals(elemId)).collect(Collectors.toList());
                     if (attriListRes != null && attriListRes.size() > 0) {
                        for(ElemAttri elemAttri : attriListRes) {
                           Map<String, Object> map = new HashMap();
                           if ("1".equals(elemAttri.getTypeFlag())) {
                              map.put(elemAttri.getContElemName(), "{ViewSecret:true}");
                              dataElementList.add(map);
                           }

                           if ("2".equals(elemAttri.getTypeFlag())) {
                              map.put(elemAttri.getContElemName(), "{ViewSecret:true}");
                              dataArrayList.add(map);
                           }

                           if ("3".equals(elemAttri.getTypeFlag())) {
                              map.put(elemAttri.getContElemName(), "{ViewSecret:true}");
                              dataAreaList.add(map);
                           }
                        }
                     }
                  }
               }

               indexVo.setDataElementList(dataElementList);
               indexVo.setDataArrayList(dataArrayList);
               indexVo.setDataAreaList(dataAreaList);
            }

            List<String> signList = new ArrayList();
            if (!attriList.isEmpty()) {
               for(ElemAttri vo : attriList) {
                  String contType = vo.getContType();
                  if (StringUtils.isNotBlank(contType) && (contType.equals("11") || contType.equals("12"))) {
                     signList.add(vo.getContElemName());
                  }
               }
            }

            indexVo.setSignList(signList);
            indexVo.setSetStructsTextIdList(contElemNameList);
         } else if (StringUtils.isNotBlank(secrecyBz) && "1".equals(secrecyBz)) {
            String emrXml = this.emrBinaryService.selectIndexXmlStrById(id);
            XmlElementParseConfigVo configVo = this.getXmlElementParseConfigs();
            List<ElemAttriVo> elemAttriVoList = XmlElementParseUtil.getElemAttriVoListFromXml(emrXml, configVo);
            if (sysStaElemList != null && sysStaElemList.size() > 0 && !elemAttriVoList.isEmpty()) {
               for(Long elemId : (List)sysStaElemList.stream().map((t) -> t.getId()).collect(Collectors.toList())) {
                  List<ElemAttri> attriListRes = (List)elemAttriVoList.stream().filter((t) -> t.getElemId() != null && t.getElemId().equals(elemId)).collect(Collectors.toList());
                  if (attriListRes != null && attriListRes.size() > 0) {
                     for(ElemAttri elemAttri : attriListRes) {
                        Map<String, Object> map = new HashMap();
                        if ("1".equals(elemAttri.getTypeFlag())) {
                           map.put(elemAttri.getContElemName(), "{ViewSecret:true}");
                           dataElementList.add(map);
                        }

                        if ("2".equals(elemAttri.getTypeFlag())) {
                           map.put(elemAttri.getContElemName(), "{ViewSecret:true}");
                           dataArrayList.add(map);
                        }

                        if ("3".equals(elemAttri.getTypeFlag())) {
                           map.put(elemAttri.getContElemName(), "{ViewSecret:true}");
                           dataAreaList.add(map);
                        }
                     }
                  }
               }

               indexVo.setDataElementList(dataElementList);
               indexVo.setDataArrayList(dataArrayList);
               indexVo.setDataAreaList(dataAreaList);
            }
         }

         this.sysEmrLogService.insertSysEmrLog(sysEmrLogList);
         TmplIndex tempIndex = this.tmplIndexService.selectTmplIndexById(indexVo.getTempId());
         boolean isMainFile = this.isEmrTypeMainFile(tempIndex);
         String editMode = "7";
         if (!isMainFile) {
            SysEmrTypeConfigVo sysEmrTypeConfigVo = this.sysEmrTypeConfigService.selectSysEmrTypeConfigByTypeId(indexVo.getMrType());
            editMode = sysEmrTypeConfigVo != null && StringUtils.isNotBlank(sysEmrTypeConfigVo.getEditMode()) ? sysEmrTypeConfigVo.getEditMode() : "7";
            indexVo.setTempName(tempIndex.getTempName());
            indexVo.setShowName(tempIndex.getShowName());
            indexVo.setTempMajor(tempIndex.getTempMajor());
            indexVo.setTempDisease(tempIndex.getTempDisease());
         }

         indexVo.setEditMode(editMode);
         if (CollectionUtils.isNotEmpty(list)) {
            String manMadeQcListFlag = CollectionUtils.isNotEmpty(list) ? "1" : "0";
            indexVo.setManMadeQcListFlag(manMadeQcListFlag);
         }

         VisitinfoVo visitinfo = this.visitinfoService.selectVisitinfoByPatientId(index.getPatientId());
         if (visitinfo != null) {
            indexVo.setInhosTime(visitinfo.getInhosTime());
            indexVo.setOutTime(visitinfo.getOutTime());
         }
      }

      return indexVo;
   }

   private List getIndexQcList(IndexVo indexVo, List subfileIndexList) throws Exception {
      EmrQcListVo emrQcList = new EmrQcListVo();
      List<String> qcSectionList = new ArrayList();
      qcSectionList.add("02");
      qcSectionList.add("03");
      qcSectionList.add("05");
      emrQcList.setQcSectionList(qcSectionList);
      if (CollectionUtils.isNotEmpty(subfileIndexList)) {
         List<String> mrFileIdList = (List)subfileIndexList.stream().map((t) -> t.getId().toString()).collect(Collectors.toList());
         mrFileIdList.add(indexVo.getId().toString());
         emrQcList.setMrFileIdList(mrFileIdList);
      } else {
         emrQcList.setMrFileId(indexVo.getId().toString());
      }

      List<EmrQcListVo> list = this.emrQcListService.selectEmrQcListList(emrQcList);
      return list;
   }

   public AjaxResult selectIsAuthUpdate(Index indexInfo, SubfileIndex subfileIndex, String newSubFileFlag, String mainFileCancelSignFlag, HttpServletRequest request, IndexVo indexVo) throws Exception {
      this.log.debug("authUpdate-333333333333333333333333: " + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
      AjaxResult ajaxResult = AjaxResult.success();
      boolean editFlag = CommonConstants.BOOL_FALSE;
      Boolean displayUnlockingFlag = null;
      boolean mainFlag = "MAINFILE".equals(indexInfo.getMrType());
      Long id = subfileIndex == null ? indexInfo.getId() : subfileIndex.getId();
      this.log.debug("authUpdate-333333333333333333333333--------1: " + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
      String crePer = subfileIndex == null ? indexInfo.getCrePerCode() : subfileIndex.getCrePerCode();
      String crePerName = subfileIndex == null ? indexInfo.getCrePerName() : subfileIndex.getCrePerName();
      String fileName = subfileIndex == null ? indexInfo.getMrFileShowName() : subfileIndex.getMrFileShowName();
      String intDocCd = subfileIndex == null ? indexInfo.getIntDocCd() : subfileIndex.getIntDocCd();
      String mrState = subfileIndex == null ? indexInfo.getMrState() : subfileIndex.getMrState();
      String ip = IPAddressUtil.getIPAddress(request);
      String msg = MessageUtils.message("emr.update.auth.true");
      this.log.debug("authUpdate-333333333333333333333333--------2: " + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
      this.log.debug("authUpdate-333333333333333333333333--------3-1 " + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
      Long tempId = subfileIndex == null ? indexInfo.getTempId() : subfileIndex.getTempId();
      this.log.debug("authUpdate-333333333333333333333333--------3 " + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
      TmplIndex tmplIndex = this.tmplIndexService.selectTmplIndexById(tempId);
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      this.log.debug("authUpdate-333333333333333333333333--------4: " + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
      this.log.debug("authUpdate-44444444444444444444444: " + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
      String currSignUser = this.currUserByBJCAs(indexVo.getSignFlag(), indexVo.getSignCertSn()).getUserName();
      List<EmrTaskInfo> emrTaskInfoList = this.emrTaskInfoService.selectByInfo("3", "0", currSignUser, id.toString());
      this.log.debug("authUpdate-55555555555555555555555: " + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
      ModifyAppl modify = this.modifyApplService.selectModifyApplByEndDate(CommonConstants.EMR_MODIFY_APPLY.APP_REASON_1, "1", id, currSignUser, sysUser.getDept().getDeptCode());
      ModifyAppl modifyDsh = this.modifyApplService.selectModifyApplByEndDate(CommonConstants.EMR_MODIFY_APPLY.APP_REASON_1, "0", id, currSignUser, sysUser.getDept().getDeptCode());
      modify = modify != null ? modify : this.modifyApplService.selectModifyApplByEndDate(CommonConstants.EMR_MODIFY_APPLY.APP_REASON_2, "1", id, currSignUser, sysUser.getDept().getDeptCode());
      this.log.debug("authUpdate-6666666666666666666666666: " + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
      EditState editState = this.editStateService.selectEditStateByEmrId(indexInfo.getId());
      Date dbDate = this.commonService.getDbSysdate();
      if (editState != null && editState.getUpdateTime() != null && DateUtils.getDateMinutes(dbDate, editState.getUpdateTime()) > 8) {
         EditState editStateNew = new EditState();
         BeanUtils.copyProperties(editState, editStateNew);
         editStateNew.setDeitState("0");
         ajaxResult.put("editStateBefore", editStateNew);
         this.editStateService.updateEditState(indexInfo.getId());
         editState = null;
      }

      TdCaConsApply tdCaConsApply = this.tdCaConsApplyService.selectTdCaConsApplyByMrFileId(indexInfo.getId());
      tdCaConsApply = null;
      EmrQcFlow emrQcFlow = this.emrQcFlowService.selectEmrQcFlowById(sysUser.getHospital().getOrgCode(), indexInfo.getPatientId());
      String autoFlag97 = this.sysEmrConfigService.selectSysEmrConfigByKey("0097");
      EmrQcRecord emrQcRecord = new EmrQcRecord();
      emrQcRecord.setPatientId(indexInfo.getPatientId());
      emrQcRecord.setBackoutFileBz("1");
      List<EmrQcRecord> emrQcRecordList = this.emrQcRecordMapper.selectEmrQcRecordList(emrQcRecord);
      String BackoutFileBz = null;
      if (emrQcRecordList != null && emrQcRecordList.size() > 0 && StringUtils.isNotBlank(autoFlag97) && "0".equals(autoFlag97)) {
         BackoutFileBz = "1";
      }

      Boolean qcEditFlag = true;
      if (emrQcFlow != null && (emrQcFlow.getMrState().equals("16") || emrQcFlow.getMrState().equals("14") || emrQcFlow.getMrState().equals("12"))) {
         qcEditFlag = false;
      }

      if (tdCaConsApply == null && qcEditFlag) {
         if (editState != null || mainFlag && (!mainFlag || !StringUtils.isBlank(newSubFileFlag) && (!StringUtils.isNotBlank(newSubFileFlag) || !newSubFileFlag.equals("0")))) {
            if (!mainFlag || editState == null || !StringUtils.isBlank(newSubFileFlag) && (!StringUtils.isNotBlank(newSubFileFlag) || !newSubFileFlag.equals("0"))) {
               if (editState != null && mainFlag && StringUtils.isNotBlank(newSubFileFlag) && newSubFileFlag.equals("1")) {
                  if (!editFlag && ip.equals(editState.getIp()) && sysUser.getUserName().equals(editState.getEditPersonCd())) {
                     editFlag = CommonConstants.BOOL_TRUE;
                  }

                  if (!editFlag && !ip.equals(editState.getIp()) && sysUser.getUserName().equals(editState.getEditPersonCd())) {
                     displayUnlockingFlag = CommonConstants.BOOL_TRUE;
                  }
               } else if (editState == null && mainFlag && StringUtils.isNotBlank(newSubFileFlag) && newSubFileFlag.equals("1")) {
                  editFlag = CommonConstants.BOOL_TRUE;
               } else if (!editFlag && editState != null && ip.equals(editState.getIp()) && sysUser.getUserName().equals(editState.getEditPersonCd()) && (modify == null || modify != null && "1".equals(modify.getConState()))) {
                  editFlag = this.verifyIndexState(editFlag, id, mrState, crePer, sysUser, intDocCd, modify, emrTaskInfoList, tmplIndex, mainFileCancelSignFlag, BackoutFileBz, indexVo.getQcBillFlag());
               }
            } else {
               if (!editFlag && ip.equals(editState.getIp()) && sysUser.getUserName().equals(editState.getEditPersonCd())) {
                  editFlag = this.verifyIndexState(editFlag, id, mrState, crePer, sysUser, intDocCd, modify, emrTaskInfoList, tmplIndex, mainFileCancelSignFlag, BackoutFileBz, indexVo.getQcBillFlag());
               }

               if (!editFlag && !ip.equals(editState.getIp()) && sysUser.getUserName().equals(editState.getEditPersonCd())) {
                  displayUnlockingFlag = CommonConstants.BOOL_TRUE;
               }
            }
         } else {
            editFlag = this.verifyIndexState(editFlag, id, mrState, crePer, sysUser, intDocCd, modify, emrTaskInfoList, tmplIndex, mainFileCancelSignFlag, BackoutFileBz, indexVo.getQcBillFlag());
         }

         String ipFlag = "";
         if (!editFlag) {
            Boolean errorFlag = CommonConstants.BOOL_FALSE;
            Integer lockwState = subfileIndex == null ? indexInfo.getLockState() : subfileIndex.getLockState();
            if (!errorFlag && lockwState != null && CommonConstants.SEALUP_RECORD_STATUS.STATUS_1.equals(lockwState)) {
               errorFlag = CommonConstants.BOOL_TRUE;
               msg = MessageUtils.message("emr.update.auth.lockw");
            }

            if (!errorFlag && editState != null && (!ip.equals(editState.getIp()) || !sysUser.getUserName().equals(editState.getEditPersonCd()))) {
               errorFlag = CommonConstants.BOOL_TRUE;
               msg = MessageUtils.message("emr.update.auth.edit.record.one") + editState.getEditPersonName() + MessageUtils.message("emr.update.auth.edit.record.two") + editState.getIp() + MessageUtils.message("emr.update.auth.edit.record.three");
               ipFlag = "需要解锁";
            }

            if (!errorFlag && (mrState.equals("01") || mrState.equals("03")) && !crePer.equals(sysUser.getUserName()) && (StringUtils.isBlank(intDocCd) || StringUtils.isNotBlank(intDocCd) && !intDocCd.equals(sysUser.getUserName()))) {
               errorFlag = CommonConstants.BOOL_TRUE;
               msg = MessageUtils.message("emr.update.auth.edit.noCrePer", crePerName);
            }

            if (!errorFlag && (mrState.equals("05") || mrState.equals("07")) && (emrTaskInfoList == null || emrTaskInfoList != null && emrTaskInfoList.isEmpty()) && modify == null) {
               errorFlag = CommonConstants.BOOL_TRUE;
               msg = MessageUtils.message("emr.update.auth.node");
            }

            if (!errorFlag && mrState.equals("08")) {
               if (modify == null) {
                  if (modifyDsh != null) {
                     errorFlag = CommonConstants.BOOL_TRUE;
                     msg = MessageUtils.message("emr.update.auth.finish.submitCheck");
                  } else {
                     errorFlag = CommonConstants.BOOL_TRUE;
                     msg = MessageUtils.message("emr.update.auth.finish.crePer");
                  }
               }

               if (!crePer.equals(sysUser.getUserName())) {
                  errorFlag = CommonConstants.BOOL_TRUE;
                  msg = MessageUtils.message("emr.update.auth.edit.noCrePer", crePerName);
               }
            }
         }

         this.log.debug("authUpdate-777777777777777777777777: " + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
         if (editFlag && editState == null && (!StringUtils.isNotBlank(autoFlag97) || !"0".equals(autoFlag97) || !mrState.equals("08") || modifyDsh == null)) {
            VisitinfoVo visitinfoVo = this.visitinfoService.selectVisitinfoById(indexInfo.getPatientId());
            if ("MAINFILE".equals(indexInfo.getMrType())) {
               id = indexInfo.getId();
            }

            this.editStateService.insertEditState(visitinfoVo, id, fileName, ip);
         }

         if (editFlag && editState != null) {
            EditState editStateUpdate = new EditState();
            editStateUpdate.setId(editState.getId());
            editStateUpdate.setUpdateTime(this.commonService.getDbSysdate());
            this.editStateService.updateEditState(editStateUpdate);
         }

         this.log.debug("authUpdate-88888888888888888888888888: " + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
         ajaxResult = editFlag ? AjaxResult.success(msg) : AjaxResult.error(msg);
         if (StringUtils.isNotEmpty(ipFlag)) {
            ajaxResult.put("ipFlag", "999");
            ajaxResult.put("ipFlagMsg", MessageUtils.message("emr.update.auth.edit.record.one") + editState.getEditPersonName() + MessageUtils.message("emr.update.auth.edit.record.two") + editState.getIp() + "进行编辑，需要解锁吗？");
         }
      } else {
         if (!qcEditFlag) {
            ajaxResult = AjaxResult.error(MessageUtils.message("emr.update.auth.qcFlow16"));
            editFlag = CommonConstants.BOOL_FALSE;
         }

         if (tdCaConsApply != null) {
            ajaxResult = AjaxResult.error(MessageUtils.message("emr.update.auth.consApply"));
            editFlag = CommonConstants.BOOL_FALSE;
         }
      }

      if (mainFlag && !editFlag && displayUnlockingFlag != null && displayUnlockingFlag) {
         ajaxResult.put("displayUnlockFlag", displayUnlockingFlag);
      }

      ajaxResult.put("editFlag", editFlag);
      return ajaxResult;
   }

   private boolean verifyIndexState(boolean editFlag, Long id, String mrState, String crePer, SysUser sysUser, String intDocCd, ModifyAppl modify, List emrTaskInfoList, TmplIndex tmplIndex, String mainFileCancelSignFlag, String BackoutFileBz, String qcBillFlag) {
      if (!editFlag && (mrState.equals("01") || mrState.equals("03"))) {
         if (!crePer.equals(sysUser.getUserName()) && (!StringUtils.isNotBlank(intDocCd) || !intDocCd.equals(sysUser.getUserName()))) {
            if (StringUtils.isNotBlank(qcBillFlag) && qcBillFlag.equals("1")) {
               editFlag = CommonConstants.BOOL_TRUE;
            }
         } else {
            editFlag = CommonConstants.BOOL_TRUE;
         }
      }

      if (!editFlag && (mrState.equals("05") || mrState.equals("07"))) {
         if (StringUtils.isNotBlank(mainFileCancelSignFlag) && mainFileCancelSignFlag.equals("1")) {
            editFlag = CommonConstants.BOOL_TRUE;
         } else if ((emrTaskInfoList == null || emrTaskInfoList.isEmpty()) && modify == null) {
            if (StringUtils.isNotBlank(qcBillFlag) && qcBillFlag.equals("1")) {
               editFlag = CommonConstants.BOOL_TRUE;
            }
         } else {
            editFlag = CommonConstants.BOOL_TRUE;
         }
      }

      SysUser user = SecurityUtils.getLoginUser().getUser();
      EmrTaskInfoVo emrTaskInfoVo = new EmrTaskInfoVo();
      emrTaskInfoVo.setMrFileId(id);
      emrTaskInfoVo.setDocCd(user.getUserName());
      emrTaskInfoVo.setBusSection("22");
      List<EmrTaskInfo> emrTaskInfoList1 = this.emrTaskInfoService.selectInfoByOdcsAndIndex(emrTaskInfoVo);
      if (!editFlag && mrState.equals("08")) {
         if (StringUtils.isNotBlank(mainFileCancelSignFlag) && mainFileCancelSignFlag.equals("1")) {
            editFlag = CommonConstants.BOOL_TRUE;
         } else if ((modify == null || !crePer.equals(sysUser.getUserName())) && (emrTaskInfoList1 == null || emrTaskInfoList1.size() <= 0)) {
            if (StringUtils.isNotBlank(qcBillFlag) && qcBillFlag.equals("1")) {
               editFlag = CommonConstants.BOOL_TRUE;
            }
         } else {
            editFlag = CommonConstants.BOOL_TRUE;
         }
      }

      if (!editFlag && (mrState.equals("01") || mrState.equals("03") || mrState.equals("08")) && StringUtils.isNotBlank(tmplIndex.getEditType()) && tmplIndex.getEditType().equals("2") && (!StringUtils.isNotBlank(BackoutFileBz) || !"1".equals(BackoutFileBz))) {
         editFlag = CommonConstants.BOOL_TRUE;
      }

      return editFlag;
   }

   public Boolean getIndexSaveAuth(Index index, SubfileIndex subfileIndex, HttpServletRequest request) throws Exception {
      Boolean res = false;
      Long id = index.getId();
      EditState editState = this.editStateService.selectEditStateByEmrId(id);
      String ip = IPAddressUtil.getIPAddress(request);
      if (editState != null && editState.getIp().equals(ip)) {
         res = true;
      }

      return res;
   }

   public Map selectEmrFirstOpenList(TmplIndex tempIndex, IndexVo indexVo, Date nowDate, String indexName) throws Exception {
      Map<String, Object> map = new HashMap();
      String jsonStr = "";
      String xmlStr = "";
      if (StringUtils.isNotEmpty(tempIndex.getTempFile())) {
         Map<String, String> jsonMap = JSON.parseObject(tempIndex.getTempFile());
         jsonStr = (String)jsonMap.get("base64");
         xmlStr = (String)jsonMap.get("xmlStr");
      }

      boolean bool = this.isEmrTypeMainFile(tempIndex);
      map.put("base64", jsonStr);
      map.put("xmlStr", xmlStr);
      map.put("tempId", tempIndex.getId());
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
      map.put("indexCreDate", sdf.format(bool ? indexVo.getSubRecoDate() : nowDate));
      map.put("recoDate", indexVo.getSubRecoDate());
      List<ElemGender> elemGenderList = this.elemGenderService.selectElemGenderQuaList(tempIndex.getId());
      List<ElemGender> setPropElemList = this.selectSetPropElemList(indexVo.getPatientId(), elemGenderList);
      map.put("setPropElemList", setPropElemList);
      Map<String, List<String>> list = this.selectSetStructsTextIdList(tempIndex, indexVo.getPatientId(), indexVo.getTestExamResultId(), indexVo.getBabyId());
      List<String> idList = (List)list.get("setStructsTextIdList");
      List<String> valueList = (List)list.get("setStructsTextValueList");
      List<String> base64IdList = (List)list.get("base64IdList");
      List<String> base64ValueList = (List)list.get("base64ValueList");
      List<String> mrFileIdList = (List)list.get("mrFileIdList");
      if (bool) {
         this.subfileIndexService.setSubFileIndexRecDate(tempIndex.getId(), (List)null, indexVo.getSubRecoDate(), idList, valueList);
      }

      map.put("setStructsTextIdList", idList);
      map.put("setStructsTextValueList", valueList);
      map.put("base64IdList", base64IdList);
      map.put("base64ValueList", base64ValueList);
      map.put("tmplQuoteElemBase64List", list.get("tmplQuoteElemBase64List"));
      map.put("mrFileIdList", mrFileIdList);
      List<TmplElemLinkVo> tmplElemLinkList = new ArrayList();
      if (CollectionUtils.isNotEmpty(base64IdList)) {
         tmplElemLinkList = this.tmplElemLinkService.getTmplElemLinkElemList(base64IdList, mrFileIdList);
      }

      ElemSign elemSign = new ElemSign();
      elemSign.setTempId(tempIndex.getId());
      List<ElemSign> elemSignList = this.elemSignService.selectElemSignList(elemSign);
      map.put("tmplELemSignList", elemSignList);
      List<SubfileIndex> subfileIndexList = new ArrayList();
      if (bool) {
         map.put("mrType", "MAINFILE");
         SubfileIndex subfileIndex = new SubfileIndex();
         subfileIndex.setMrFileShowName("首次病程");
         subfileIndex.setCreDate(nowDate);
         subfileIndex.setRecoDate(indexVo.getSubRecoDate());
         VisitinfoVo visitinfoVo = this.visitinfoService.selectVisitinfoByPatientId(indexVo.getPatientId());
         DeptBEDateVo deptBEDateVoCurn = new DeptBEDateVo((Long)null, SecurityUtils.getLoginUser().getUser().getDept().getDeptCode(), SecurityUtils.getLoginUser().getUser().getDept().getDeptName(), visitinfoVo.getBedNo(), (Date)null, (Date)null);
         String emrFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0050");
         if (emrFlag.equals("0")) {
            deptBEDateVoCurn = this.subfileIndexService.getDeptBEDateVoCurn(indexVo.getPatientId(), subfileIndex);
         }

         subfileIndex.setChangeDepBedId(deptBEDateVoCurn == null ? null : deptBEDateVoCurn.getId());
         subfileIndexList.add(subfileIndex);
         map.put("indexName", "病程记录" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss", indexVo.getSubRecoDate()));
         map.put("subFileIndexName", tempIndex.getShowName() + sdf.format(indexVo.getSubRecoDate()));
         map.put("createParagraphElemFlag", true);
         map.put("deptBEDateVoCurnId", deptBEDateVoCurn == null ? null : deptBEDateVoCurn.getId());
         List<IndexHFInfoPageVo> indexHFInfoPageVoList = this.subfileIndexService.getIndexHFInfoPageVoList(xmlStr, indexVo.getPatientId(), deptBEDateVoCurn);
         map.put("indexHFInfoPageVoList", indexHFInfoPageVoList);
         Map<String, IndexHFInfoPageVo> indexHFInfoPageVoMap = (Map)indexHFInfoPageVoList.stream().collect(Collectors.toMap((t) -> t.getContElemName(), Function.identity()));

         for(int i = 0; i < idList.size(); ++i) {
            String contElemName = (String)idList.get(i);
            IndexHFInfoPageVo indexHFInfoPageVo = (IndexHFInfoPageVo)indexHFInfoPageVoMap.get(contElemName);
            if (indexHFInfoPageVo != null) {
               valueList.set(i, indexHFInfoPageVo.getText());
            }
         }

         map.put("setStructsTextIdList", idList);
         map.put("setStructsTextValueList", valueList);
      } else {
         map.put("mrType", "");
         map.put("indexName", indexName);
         map.put("subFileIndexName", "");
      }

      List<ElemAttri> attriList = this.elemAttriService.selectElemAttriByTempId(tempIndex.getId());
      List<String> signList = new ArrayList();
      if (!attriList.isEmpty()) {
         for(ElemAttri vo : attriList) {
            String contType = vo.getContType();
            if (StringUtils.isNotBlank(contType) && (contType.equals("11") || contType.equals("12"))) {
               signList.add(vo.getContElemName());
            }
         }
      }

      map.put("signList", signList);
      map.put("indexId", String.valueOf(SnowIdUtils.uniqueLong()));
      map.put("subFileIndexList", subfileIndexList);
      boolean isMainFile = this.isEmrTypeMainFile(tempIndex);
      if (!isMainFile) {
         SysEmrTypeConfigVo sysEmrTypeConfigVo = this.sysEmrTypeConfigService.selectSysEmrTypeConfigByTypeId(tempIndex.getTempType());
         map.put("editMode", sysEmrTypeConfigVo != null && StringUtils.isNotBlank(sysEmrTypeConfigVo.getEditMode()) ? sysEmrTypeConfigVo.getEditMode() : "7");
      } else {
         map.put("editMode", "7");
      }

      VisitinfoVo visitinfo = this.visitinfoService.selectVisitinfoByPatientId(indexVo.getPatientId());
      if (visitinfo != null) {
         map.put("inHosTime", DateUtils.parseDateToStr("yyyy-MM-dd HH:mm", visitinfo.getInhosTime()));
         if (visitinfo.getOutTime() != null) {
            map.put("outTime", DateUtils.parseDateToStr("yyyy-MM-dd HH:mm", visitinfo.getOutTime()));
         }
      }

      List<TmplElemLinkVo> tmplElemLinkVoList = this.tmplElemLinkService.selectTmplElemLinkVoByTempId(tempIndex.getId());
      tmplElemLinkVoList.addAll(tmplElemLinkList);
      map.put("tmplElemLinkVoList", tmplElemLinkVoList);
      return map;
   }

   public Map emrElemXmlManage(TmplIndex tempIndex, String patientId, String xmlStr, Date subRecoDate, IndexVo indexVo) throws Exception {
      Long tempId = tempIndex.getId();
      Map<String, Object> map = new HashMap();
      List<String> keyList = new ArrayList();
      List<String> valueList = new ArrayList();
      String jsonStr = "";
      if (StringUtils.isNotEmpty(tempIndex.getTempFile())) {
         Map<String, String> jsonMap = JSON.parseObject(tempIndex.getTempFile());
         jsonStr = (String)jsonMap.get("base64");
      }

      map.put("base64", jsonStr);
      map.put("tempId", tempIndex.getId());
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
      map.put("indexName", tempIndex.getShowName() + sdf.format(new Date()));
      map.put("indexCreDate", sdf.format(new Date()));
      String editorType = this.sysEmrConfigService.selectSysEmrConfigByKey("0000");
      TempIndexSaveElemVo tempIndexSaveElemVo = XmlElementParseUtil.getSaveElemFromXml(xmlStr, editorType);
      if (tempIndexSaveElemVo != null) {
         List<ElemGender> elemGenderMap = this.selectSetPropElemList(patientId, tempIndexSaveElemVo.getElemGenderList());
         map.put("setPropElemList", elemGenderMap);
         Map<String, List<String>> elemDateMap = this.elemDateKeyAndValue(tempIndexSaveElemVo.getElemDateList());
         this.subfileIndexService.setSubFileIndexRecDate((Long)null, tempIndexSaveElemVo.getElemDateList(), subRecoDate, keyList, valueList);
         keyList.addAll((Collection)elemDateMap.get("setStructsTextIdList"));
         valueList.addAll((Collection)elemDateMap.get("setStructsTextValueList"));
         Map<String, List<String>> elemMacroMap = this.elemMacroKeyAndValue(patientId, indexVo.getTestExamResultId(), indexVo.getBabyId(), tempIndexSaveElemVo.getElemMacroList());
         keyList.addAll((Collection)elemMacroMap.get("setStructsTextIdList"));
         valueList.addAll((Collection)elemMacroMap.get("setStructsTextValueList"));
         List<TmplQuoteElem> quoteList = this.tmplQuoteElemService.selectTmplQuoteElemListByTempId(tempId);
         List<Long> elemIdList = (List)quoteList.stream().map((t) -> t.getElemId()).distinct().collect(Collectors.toList());
         List<ElemAttri> elemAttriList = tempIndexSaveElemVo.getElemAttriList();
         List<QuoteElemVo> tmplQuoteElemList = XmlElementParseUtil.getSysQuoteElem(elemAttriList, elemIdList);
         Map<String, List<String>> quoteMap = this.quoteElemKeyAndValue(tempId, patientId, tmplQuoteElemList);
         keyList.addAll((Collection)quoteMap.get("setStructsTextIdList"));
         valueList.addAll((Collection)quoteMap.get("setStructsTextValueList"));
         map.put("base64IdList", quoteMap.get("base64IdList"));
         map.put("base64ValueList", quoteMap.get("base64ValueList"));
         map.put("mrFileIdList", quoteMap.get("mrFileIdList"));
         if ((tempIndex.getTempType().equals("45") || tempIndex.getTempType().equals("51")) && (StringUtils.isNotEmpty(indexVo.getUperTitleName()) || StringUtils.isNotEmpty(indexVo.getUperDoctName()))) {
            List<ElemAttri> elemAttris = tempIndexSaveElemVo.getElemAttriList();
            if (elemAttris != null && !elemAttris.isEmpty()) {
               for(ElemAttri elemAttri : elemAttris) {
                  if (elemAttri.getElemId().equals(CommonConstants.EMR.ELEM_UP_DOCTOR) && StringUtils.isNotEmpty(indexVo.getUperDoctName())) {
                     keyList.add(elemAttri.getContElemName());
                     valueList.add(indexVo.getUperDoctName());
                  }

                  if (elemAttri.getElemId().equals(CommonConstants.EMR.ELEM_UP_TITLE) && StringUtils.isNotEmpty(indexVo.getUperTitleName())) {
                     String name = "";

                     try {
                        name = indexVo.getUperTitleName().substring(0, indexVo.getUperTitleName().length() - 2);
                        keyList.add(elemAttri.getContElemName());
                        valueList.add(name);
                     } catch (Exception var27) {
                        this.log.error("医师职称名称截取失败");
                     }
                  }
               }
            }
         }

         List<TmplElemLinkVo> tmplElemLinkVoList = this.tmplElemLinkService.selectTmplElemLinkVoByTempId(tempIndex.getId());
         if (CollectionUtils.isNotEmpty(tmplElemLinkVoList)) {
            tmplElemLinkVoList = this.tmplElemLinkService.selectTmplElemLinkVoByXmlStr(tmplElemLinkVoList, elemAttriList, indexVo);
            map.put("tmplElemLinkVoList", tmplElemLinkVoList);
         }
      } else {
         System.out.println("解析病历xml结果为空");
      }

      List<QuoteElemVo> elemForBase64 = this.quoteElemService.selectFromQuoteElemForBase64(tempIndex.getTempType());
      List<Long> elemIdList2 = (List<Long>)(CollectionUtils.isNotEmpty(elemForBase64) ? (List)elemForBase64.stream().map((t) -> Long.valueOf(t.getElemId())).collect(Collectors.toList()) : new ArrayList(1));
      List<QuoteElemVo> tmplQuoteElemBase64List = XmlElementParseUtil.getSysQuoteElem(tempIndexSaveElemVo.getElemAttriList(), elemIdList2);
      List<String> tmplQuoteElemBase64NameList = CollectionUtils.isNotEmpty(tmplQuoteElemBase64List) ? (List)tmplQuoteElemBase64List.stream().map((t) -> t.getContElemName()).collect(Collectors.toList()) : null;
      map.put("tmplQuoteElemBase64List", tmplQuoteElemBase64NameList);
      map.put("setStructsTextIdList", keyList);
      map.put("setStructsTextValueList", valueList);
      map.put("mrType", "");
      map.put("subFileIndexList", new ArrayList());
      return map;
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public IndexSaveReturnVo saveIndex(IndexSaveVo indexSaveVo, Index index, SubfileIndex subfileIndex, List elemAttriVoList, HttpServletRequest request) throws Exception {
      IndexSaveReturnVo result = new IndexSaveReturnVo();
      this.log.warn("saveIndex-333333333333333333333: " + (index != null ? index.getId() : "") + " ：" + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
      Long id = subfileIndex != null ? subfileIndex.getId() : (index != null ? index.getId() : indexSaveVo.getId());
      Long subId = subfileIndex != null ? subfileIndex.getId() : SnowIdUtils.uniqueLong();
      Date updateTime = this.commonService.getDbSysdate();
      if ("0".equals(indexSaveVo.getEmrIsUpdate()) || "1".equals(indexSaveVo.getIsCancelSave())) {
         updateTime = subfileIndex != null ? subfileIndex.getAltDate() : index.getAltDate();
         updateTime = updateTime == null ? this.commonService.getDbSysdate() : updateTime;
      }

      SysUser updateUser = SecurityUtils.getLoginUser().getUser();
      Boolean addFlag = false;
      Boolean addMainFlag = false;
      if (index == null) {
         addMainFlag = true;
         addFlag = true;
         index = new Index();
         subfileIndex = new SubfileIndex();
         index.setId(id);
         indexSaveVo.setId(id);
         this.insertIndex(indexSaveVo, index, subfileIndex);
      } else if (index.getMrType().equals("MAINFILE")) {
         if (subfileIndex != null) {
            String elemId = this.sysEmrConfigService.selectSysEmrConfigByKey("0028");
            List<ElemAttriVo> elemList = (List)elemAttriVoList.stream().filter((s) -> String.valueOf(s.getElemId()).equals(elemId)).collect(Collectors.toList());
            Date recoDate = null;
            if (elemList != null && !elemList.isEmpty()) {
               recoDate = DateUtils.parseDate(((ElemAttriVo)elemList.get(0)).getElemConText());
            }

            if (StringUtils.isNotBlank(indexSaveVo.getFreeMoveType()) && "22".equals(indexSaveVo.getFreeMoveType())) {
               this.subfileIndexService.updateSubfileIndexMrState(subfileIndex.getId(), indexSaveVo.getSaveType(), (Date)null, recoDate);
               subfileIndex.setAltDate((Date)null);
            } else {
               this.subfileIndexService.updateSubfileIndexMrState(subfileIndex.getId(), indexSaveVo.getSaveType(), updateTime, recoDate);
               subfileIndex.setAltDate(updateTime);
            }
         } else {
            addFlag = true;
            subfileIndex = new SubfileIndex();
            subfileIndex.setId(subId);
            this.insertSubIndex(indexSaveVo, index, subfileIndex);
         }

         indexSaveVo.setId(subId);
      } else if (StringUtils.isNotBlank(indexSaveVo.getFreeMoveType()) && "22".equals(indexSaveVo.getFreeMoveType())) {
         this.updateIndexMrState(index.getId(), indexSaveVo.getSaveType(), (Date)null);
         index.setAltDate((Date)null);
      } else {
         this.updateIndexMrState(index.getId(), indexSaveVo.getSaveType(), updateTime);
         index.setAltDate(updateTime);
      }

      if (StringUtils.isNotEmpty(indexSaveVo.getRegainSaveFlag()) && indexSaveVo.getRegainSaveFlag().equals("TRUE")) {
         this.changeDelFlag((Index)null, subfileIndex, request);
         SubfileIndex subfileIndex1 = new SubfileIndex();
         subfileIndex1.setId(subfileIndex.getId());
         subfileIndex1.setContElemName(indexSaveVo.getSubIndexContElemName());
         this.subfileIndexService.updateSubfileIndex(subfileIndex1);
      }

      this.log.warn("saveIndex-444444444444444444444444441111111: " + (index.getId() != null ? index.getId() : "") + " ：" + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
      if (subfileIndex != null && subfileIndex.getId() != null) {
         subfileIndex.getMrType();
      } else {
         index.getMrType();
      }

      Long mrFileId = subfileIndex != null && subfileIndex.getId() != null ? subfileIndex.getId() : index.getId();
      String mrFileName = subfileIndex != null && StringUtils.isNotEmpty(subfileIndex.getMrFileShowName()) ? subfileIndex.getMrFileShowName() : index.getMrFileShowName();
      index.setMrContent(indexSaveVo.getMrContent());
      if (subfileIndex != null && "9".equals(subfileIndex.getMrType()) && indexSaveVo.getResultId() != null) {
         TestExamAlertResult testExamAlertResult = new TestExamAlertResult();
         testExamAlertResult.setId(indexSaveVo.getResultId());
         testExamAlertResult.setMrFileId(mrFileId);
         this.testExamAlertResultService.updateMrFileId(testExamAlertResult);
      }

      if ((indexSaveVo.getSaveType().equals("03") || indexSaveVo.getSaveType().equals("05")) && (indexSaveVo.getAutoSaveFlag() == null || indexSaveVo.getAutoSaveFlag() != null && !indexSaveVo.getAutoSaveFlag())) {
         this.log.warn("saveIndex-444444444444444444444444441222-ing: " + (index.getId() != null ? index.getId() : "") + " ：" + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
         this.saveEmrCheckFlaw(indexSaveVo);
      }

      this.log.warn("saveIndex-77777777777777777777777: " + (index.getId() != null ? index.getId() : "") + " ：" + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
      if (!addFlag) {
         boolean mainFlag = "MAINFILE".equals(index.getMrType());
         if (indexSaveVo.getKeepEdit() != null && !indexSaveVo.getKeepEdit()) {
            this.editStateService.updateEditState(index.getId());
         } else if (StringUtils.isNotBlank(indexSaveVo.getRegainSaveFlag()) && indexSaveVo.getRegainSaveFlag().equals("TRUE")) {
            this.editStateService.updateEditState(index.getId());
         }

         if (indexSaveVo.getAutoSaveFlag() != null && indexSaveVo.getAutoSaveFlag()) {
         }

         if (!mainFlag && !StringUtils.isNotBlank(indexSaveVo.getIsCancelSave()) && "0".equals(indexSaveVo.getIsCancelSave())) {
         }
      }

      VisitinfoVo visitinfoVo = this.visitinfoService.selectVisitinfoById(indexSaveVo.getPatientId());
      if (addFlag && indexSaveVo.getKeepEdit() != null && indexSaveVo.getKeepEdit() && !"MAINFILE".equals(indexSaveVo.getMrType())) {
         String ip = IPAddressUtil.getIPAddress(request);
         String msg = MessageUtils.message("emr.update.auth.true");
         this.editStateService.insertEditState(visitinfoVo, index.getId(), mrFileName, ip);
      }

      EditState editState = this.editStateService.selectEditStateByEmrId(index.getId());
      if ("MAINFILE".equals(indexSaveVo.getMrType()) && addFlag && indexSaveVo.getKeepEdit() == null && StringUtils.isNotBlank(indexSaveVo.getIsCancelSave()) && "0".equals(indexSaveVo.getIsCancelSave()) && editState == null) {
         String ip = IPAddressUtil.getIPAddress(request);
         String msg = MessageUtils.message("emr.update.auth.true");
         this.editStateService.insertEditState(visitinfoVo, index.getId(), mrFileName, ip);
      }

      this.log.warn("saveIndex-8888888888888888888888: " + (index.getId() != null ? index.getId() : "") + " ：" + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
      String optType = addFlag ? "8" : "4";
      String optTypeName = addFlag ? "新建病历" : "病历修改";
      this.sysEmrLogService.insertSysEmrLog(index, subfileIndex, optType, optTypeName, IPAddressUtil.getIPAddress(request));
      this.log.warn("saveIndex-99999999999999999999991111111: " + (index.getId() != null ? index.getId() : "") + " ：" + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
      if (indexSaveVo.getTaskId() != null) {
         this.emrTaskInfoService.updateTaskInfoByAdd(indexSaveVo.getTaskId(), mrFileId);
         ModifyAppl modifyAppl = new ModifyAppl();
         modifyAppl.setMrFileId(indexSaveVo.getTaskId());
         modifyAppl.setConState("1");
         modifyAppl.setMrFlag("0");
         List<ModifyAppl> modifyApplRes1 = this.modifyApplService.selectModifyAppl(modifyAppl);
         SubfileIndex subfileIndex1 = this.subfileIndexService.selectSubfileIndexById(mrFileId);
         if (modifyApplRes1 != null && modifyApplRes1.size() > 0 && subfileIndex1 != null) {
            ModifyAppl appl = (ModifyAppl)modifyApplRes1.get(0);
            if (StringUtils.isNotBlank(appl.getDeadlineUnit())) {
               List<Long> idList = (List)modifyApplRes1.stream().map((t) -> t.getId()).distinct().collect(Collectors.toList());
               ModifyApplVo modifyApplVo = new ModifyApplVo();
               modifyApplVo.setIds(idList);
               modifyApplVo.setMrFlag("1");
               modifyApplVo.setRecoDate(indexSaveVo.getRecoDate());
               modifyApplVo.setAltPerCode(updateUser.getUserName());
               modifyApplVo.setAltPerName(updateUser.getNickName());
               modifyApplVo.setAltDate(indexSaveVo.getRecoDate());
               this.modifyApplService.updateModifyApplVo(modifyApplVo);
            }
         }
      }

      this.log.warn("saveIndex-9999999999999999999999222222: " + (index.getId() != null ? index.getId() : "") + " ：" + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
      if (StringUtils.isNotEmpty(indexSaveVo.getOperApplyFormNo()) && indexSaveVo.getOperAgiRuleId() != null) {
         QcAgiRule qcAgiRule = this.qcAgiRuleService.selectQcAgiRuleById(indexSaveVo.getOperAgiRuleId());
         QcAgiRule qcAgiRule28 = this.qcAgiRuleService.selectYxQcAgiRuleByCode("R028");
         QcAgiRule qcAgiRule29 = this.qcAgiRuleService.selectYxQcAgiRuleByCode("R029");
         VisitinfoPersonalVo personalVo = this.visitinfoService.selectVisitinfoPersonalById(indexSaveVo.getPatientId());
         TdCaOperationApply tdCaOperationApply = this.tdCaOperationApplyService.selectTdCaOperationApplyById(indexSaveVo.getOperApplyFormNo());
         IndexVo indexVo = new IndexVo();
         indexVo.setId(indexSaveVo.getId());
         String fileName = StringUtils.isNotEmpty(indexSaveVo.getSubIndexName()) ? indexSaveVo.getSubIndexName() : indexSaveVo.getIndexName();
         indexVo.setMrFileShowName(fileName);
         indexVo.setAgiRuleId(qcAgiRule.getId().toString());
         indexVo.setAgiRuleName(qcAgiRule.getRuleName());
         if (!tdCaOperationApply.getStatus().equals("03") && !tdCaOperationApply.getStatus().equals("99")) {
            EmrTaskInfoVo emrTaskInfoVo = this.emrTaskInfoService.selectTaskByEventNoAndBus(qcAgiRule.getId().toString(), "1", indexSaveVo.getOperApplyFormNo());
            if (emrTaskInfoVo == null) {
               EmrTaskInfoVo emrTaskInfo = this.tdCaOperationApplyService.setEmrTaskInfo(indexVo, personalVo);
               emrTaskInfo.setBusSection(qcAgiRule.getAgiEvnt());
               if (qcAgiRule.getAgiEvnt().equals("17")) {
                  emrTaskInfo.setBusSectionName("手术预约");
                  if ("5".equals(indexVo.getMrType())) {
                     if (qcAgiRule29 != null) {
                        emrTaskInfo.setLimitTimeUnit(qcAgiRule29.getLimitTimeUnit());
                        emrTaskInfo.setLimitTime(qcAgiRule29.getFinishLimitTime());
                     }
                  } else if ("15".equals(indexVo.getMrType()) && qcAgiRule28 != null) {
                     emrTaskInfo.setLimitTimeUnit(qcAgiRule28.getLimitTimeUnit());
                     emrTaskInfo.setLimitTime(qcAgiRule28.getFinishLimitTime());
                  }
               } else {
                  emrTaskInfo.setBusSectionName("手术");
               }

               emrTaskInfo.setEventNo(indexSaveVo.getOperApplyFormNo());
               this.emrTaskInfoService.insertEmrTaskInfo(emrTaskInfo);
            }

            this.indexMapper.updateEmrOrderNoByIdList(indexSaveVo.getOperApplyFormNo(), Arrays.asList(indexSaveVo.getId()));
            this.subfileIndexService.updateEmrOrderNoByIdList(indexSaveVo.getOperApplyFormNo(), Arrays.asList(indexSaveVo.getId()));
         }
      }

      this.log.warn("saveIndex-101010101010101010101010: " + (index.getId() != null ? index.getId() : "") + " ：" + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
      if (!StringUtils.isEmpty(indexSaveVo.getSignSaveFlag()) && indexSaveVo.getSignSaveFlag().equals("1")) {
         if (indexSaveVo.getSaveType().equals("08") || indexSaveVo.getSaveType().equals("05") || indexSaveVo.getSaveType().equals("07")) {
            if (StringUtils.isNotEmpty(indexSaveVo.getEmrFileUrl())) {
               this.log.warn("saveIndex-121212121212121212: 病历文件pdf路径：" + (index.getId() != null ? index.getId() : "") + " ：" + indexSaveVo.getEmrFileUrl() + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
               String fileName = index.getId().toString();
               String pdfUrl = Base64Util.createLocalImageMethod(indexSaveVo.getEmrFileUrl(), fileName, index.getPatientId());
               Index indexFile = new Index();
               indexFile.setId(index.getId());
               indexFile.setMrServPath(pdfUrl);
               indexFile.setMrLocaPath(indexSaveVo.getEmrFileUrl());
               this.updateIndex(indexFile);
            } else {
               this.log.warn("完成状态保存时saveIndex-121212121212121212: 病历文件pdf路径为空" + (index.getId() != null ? index.getId() : "") + " ：" + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
            }
         }
      } else {
         result = this.getIndexSaveReturnVo(index, subfileIndex, addMainFlag);
      }

      String subFileQcFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0063");
      if (addFlag && index.getMrType().equals("MAINFILE") && StringUtils.isNotBlank(subFileQcFlag) && subFileQcFlag.equals("1")) {
         ModifyApplVo modifyParam = new ModifyApplVo();
         modifyParam.setPatientId(indexSaveVo.getPatientId());
         modifyParam.setRecoDate(subfileIndex.getRecoDate());
         List<ModifyAppl> modifyApplList = this.modifyApplService.selectSubFileAppls(modifyParam);
         if (CollectionUtils.isNotEmpty(modifyApplList)) {
            ModifyAppl modifyAppl = (ModifyAppl)modifyApplList.get(0);
            ModifyAppl modifyApplUpdate = new ModifyAppl();
            modifyApplUpdate.setId(modifyAppl.getId());
            modifyApplUpdate.setMrFlag("1");
            modifyApplUpdate.setMrFileId(subId);
            modifyApplUpdate.setFileName(subfileIndex.getMrFileShowName());
            this.modifyApplService.updateModifyAppl(modifyApplUpdate);
         }
      }

      this.indexPatSign(indexSaveVo, mrFileId, updateUser);
      if (StringUtils.isNotBlank(indexSaveVo.getFreeMoveType()) && "22".equals(indexSaveVo.getFreeMoveType())) {
         result.setUpdateTime((Date)null);
      } else {
         result.setUpdateTime(updateTime);
      }

      result.setUpdateUser(updateUser);
      this.emrIndexToRedis(index.getId(), indexSaveVo.getBase64Str());
      if (subfileIndex != null && StringUtils.isBlank(indexSaveVo.getSubFileBase64Str()) && subfileIndex.getId() != null) {
         this.log.warn("saveIndex-44444444444444444444444444122222222: " + (index.getId() != null ? index.getId() : "") + " ：" + (subfileIndex != null ? subfileIndex.getId() : "") + " ：子病程base64是空的！！！ ： " + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
         this.log.warn("saveIndex-44444444444444444444444444122222222:大病程base64: " + (index.getId() != null ? index.getId() : "") + " ：" + indexSaveVo.getBase64Str());
         this.log.warn("saveIndex-44444444444444444444444444122222222:子病程base64: " + (index.getId() != null ? index.getId() : "") + " ：" + (subfileIndex != null ? subfileIndex.getId() : "") + " ：" + indexSaveVo.getSubFileBase64Str());
      } else {
         this.log.warn("saveIndex-44444444444444444444444444122222222:病历base64: " + (index.getId() != null ? index.getId() : "") + " ：" + indexSaveVo.getBase64Str());
         if (subfileIndex != null && subfileIndex.getId() != null) {
            this.log.warn("saveIndex-44444444444444444444444444122222222:子病程base64: " + (index.getId() != null ? index.getId() : "") + " ：" + (subfileIndex != null ? subfileIndex.getId() : "") + " ：" + indexSaveVo.getSubFileBase64Str());
         }
      }

      this.log.warn("saveIndexAsync-1111111111111111111111111 ： " + index.getId());
      this.emrBinaryService.addOrUpdateEmrBinary(index.getId(), index, updateUser, this.getMrCon((String)null, indexSaveVo.getXmlStr(), indexSaveVo.getTextStr(), indexSaveVo.getSubFileTextStr()), updateTime);
      this.log.warn("saveIndexAsync-222222222222222222222222 ： " + index.getId());
      if (subfileIndex != null && subfileIndex.getId() != null) {
         this.emrBinaryService.addOrUpdateEmrBinary(subfileIndex.getId(), index, updateUser, this.getMrCon((String)null, indexSaveVo.getSubFileXmlStr(), indexSaveVo.getTextStr(), indexSaveVo.getSubFileTextStr()), updateTime);
         this.log.debug("saveIndexAsync-333333333333333333333 ： " + index.getId());
      }

      this.log.warn("saveIndexAsync-444444444444444444444444444 ： " + index.getId());
      String fileDateStr = this.commonService.getSystimestamp();
      Base64Util.decode(indexSaveVo.getBase64Str(), "emrBase64/" + indexSaveVo.getPatientId(), index.getId().toString() + ".txt");
      Base64Util.decode(indexSaveVo.getBase64Str(), "emrLogBase64/" + indexSaveVo.getPatientId(), index.getId().toString() + "_" + fileDateStr + ".txt");
      if (subfileIndex != null && subfileIndex.getId() != null) {
         Base64Util.decode(indexSaveVo.getSubFileBase64Str(), "emrBase64/" + indexSaveVo.getPatientId(), subfileIndex.getId().toString() + ".txt");
         Base64Util.decode(indexSaveVo.getSubFileBase64Str(), "emrLogBase64/" + indexSaveVo.getPatientId(), subfileIndex.getId().toString() + "_" + fileDateStr + ".txt");
      }

      this.log.warn("saveIndex-44444444444444444444444444122222222: " + (index.getId() != null ? index.getId() : "") + " ：" + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
      return result;
   }

   private void emrIndexToRedis(Long mrFileId, String base64Str) {
      try {
         this.indexRedisCache.setCacheObject("index_base64_key:" + mrFileId, base64Str, 1, TimeUnit.HOURS);
      } catch (Exception e) {
         this.log.error("病历内容存储到redis出现异常，", e);
      }

   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void saveIndexAsync(IndexSaveVo indexSaveVo, Index index, SubfileIndex subfileIndex, Date updateTime, SysUser updateUser) throws Exception {
      this.log.warn("saveIndexAsync-1111111111111111111111111 ： " + index.getId());
      this.emrBinaryService.addOrUpdateEmrBinary(index.getId(), index, updateUser, this.getMrCon((String)null, indexSaveVo.getXmlStr(), indexSaveVo.getTextStr(), indexSaveVo.getSubFileTextStr()), updateTime);
      this.log.warn("saveIndexAsync-222222222222222222222222 ： " + index.getId());
      if (subfileIndex != null && subfileIndex.getId() != null) {
         this.emrBinaryService.addOrUpdateEmrBinary(subfileIndex.getId(), index, updateUser, this.getMrCon((String)null, indexSaveVo.getSubFileXmlStr(), indexSaveVo.getTextStr(), indexSaveVo.getSubFileTextStr()), updateTime);
         this.log.debug("saveIndexAsync-333333333333333333333 ： " + index.getId());
      }

      this.log.warn("saveIndexAsync-444444444444444444444444444 ： " + index.getId());
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void saveIndexElemAsync(Index index, SubfileIndex subfileIndex, List elemAttriVoList, SysUser updateUser, Map elemBase64Map) throws Exception {
      this.log.debug("saveIndexElemAsync-1111111111111111111111111");
      String mrType = subfileIndex != null && subfileIndex.getId() != null ? subfileIndex.getMrType() : index.getMrType();
      Long mrFileId = subfileIndex != null && subfileIndex.getId() != null ? subfileIndex.getId() : index.getId();
      this.log.debug("saveIndex-5555555555555555555555: " + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
      this.emrElemstoeService.indexSaveEmrElemstoes(mrType, mrFileId, index, updateUser, elemAttriVoList, elemBase64Map);
      this.log.debug("saveIndex-66666666666666666666666: " + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
      this.log.debug("saveIndexElemAsync-22222222222222222222222222");
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void saveIndexElemSharingAsync(Index index, SubfileIndex subfileIndex, List elemAttriVoList, SysUser updateUser) throws Exception {
      this.log.debug("saveIndexElemSharingAsync-1111111111111111111111111");
      String mrType = subfileIndex != null && subfileIndex.getId() != null ? subfileIndex.getMrType() : index.getMrType();
      this.emrSharingService.indexSaveEmrSharingElems(mrType, index, updateUser, elemAttriVoList);
      this.log.debug("saveIndexElemSharingAsync-22222222222222222222222222");
   }

   private void indexPatSign(IndexSaveVo indexSaveVo, Long mrFileId, SysUser updateUser) throws Exception {
      String patSignFlag = indexSaveVo.getPatSignFlag();
      IndexSignPatVo signPatVo = indexSaveVo.getIndexSignPat();
      if (StringUtils.isNotBlank(patSignFlag) && patSignFlag.equals("1") && signPatVo != null) {
         List<ElemSignVo> signElemList = signPatVo.getSignElemList();
         List<EmrSignRecord> signRecordList = new ArrayList(signElemList.size());
         List<EmrSignData> signDataList = new ArrayList(signElemList.size());
         if (signElemList == null || signElemList.isEmpty()) {
            ElemSignVo signElem = new ElemSignVo();
            signElem.setElemId(mrFileId);
            signElem.setContElemName(mrFileId.toString());
            signElemList.add(signElem);
         }

         Date signTime = this.commonService.getDbSysdate();

         for(ElemSignVo signElem : signElemList) {
            EmrSignData emrSignData = new EmrSignData();
            emrSignData.setId(SnowIdUtils.uniqueLong());
            emrSignData.setTypeCd("1");
            emrSignData.setCertType("1");
            emrSignData.setSignFileId(mrFileId.toString());
            emrSignData.setSignerCd(StringUtils.isNotBlank(signPatVo.getSignerCd()) ? signPatVo.getSignerCd() : "pat");
            emrSignData.setSignerName(StringUtils.isNotBlank(signPatVo.getSignerName()) ? signPatVo.getSignerName() : "pat");
            emrSignData.setOldText(signPatVo.getOldText());
            emrSignData.setSignText(signPatVo.getSignText());
            emrSignData.setTimeStamp(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, signTime));
            emrSignData.setSignTime(signTime);
            emrSignData.setCertInfo(signPatVo.getCertInfo());
            emrSignData.setCertSn(signPatVo.getCertSn());
            emrSignData.setIsValid("1");
            emrSignData.setCreDate(signTime);
            emrSignData.setCrePerCode(updateUser.getUserName());
            emrSignData.setCrePerName(updateUser.getNickName());
            emrSignData.setSignPersonType(indexSaveVo.getPatSignFlag());
            emrSignData.setSignPersonId(indexSaveVo.getSignPersonId());
            signDataList.add(emrSignData);
            EmrSignRecord emrSignRecord = new EmrSignRecord();
            emrSignRecord.setId(SnowIdUtils.uniqueLong());
            emrSignRecord.setSignDataId(emrSignData.getId());
            emrSignRecord.setMrFileId(mrFileId);
            emrSignRecord.setSignPos(signElem.getElemId() != null ? String.valueOf(signElem.getElemId()) : null);
            emrSignRecord.setSignTime(signTime);
            emrSignRecord.setSignCancFlag("0");
            emrSignRecord.setCrePerCode(updateUser.getUserName());
            emrSignRecord.setCrePerName(updateUser.getNickName());
            emrSignRecord.setCreDate(signTime);
            emrSignRecord.setSignImagePos(signElem.getSignImageName());
            emrSignRecord.setSignSname(signElem.getContElemName());
            emrSignRecord.setEmrUpdateTime(signTime);
            signRecordList.add(emrSignRecord);
         }

         this.log.debug("saveIndex-1313131313131313131313131313: " + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
         if (!signRecordList.isEmpty()) {
            this.emrSignRecordService.addPatList(signRecordList);
         }

         if (!signDataList.isEmpty()) {
            this.emrSignDataService.addPatList(signDataList);
         }
      }

   }

   private IndexSaveReturnVo getIndexSaveReturnVo(Index index, SubfileIndex subfileIndex, Boolean addMainFlag) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      IndexSaveReturnVo indexSaveReturnVo = new IndexSaveReturnVo();
      indexSaveReturnVo.setMrState(subfileIndex != null && subfileIndex.getId() != null ? subfileIndex.getMrState() : index.getMrState());
      SubfileIndex subfileIndexParam = new SubfileIndex();
      subfileIndexParam.setMainId(index.getId());
      List<SubfileIndex> subfileIndexList = this.subfileIndexService.selectSubfileIndexList(subfileIndexParam);
      List<SubfileIndexVo> subfileIndexVoList = new ArrayList(subfileIndexList.size());
      EmrTaskInfo emrTaskInfo = new EmrTaskInfo();
      emrTaskInfo.setTaskType("3");
      emrTaskInfo.setTreatFlag("0");
      emrTaskInfo.setPatientId(index.getPatientId());
      List<EmrTaskInfo> emrTaskInfoListAll = this.emrTaskInfoService.selectEmrTaskInfoList(emrTaskInfo);
      Map<String, List<EmrTaskInfo>> taskMap = (Map<String, List<EmrTaskInfo>>)(emrTaskInfoListAll != null && !emrTaskInfoListAll.isEmpty() ? (Map)emrTaskInfoListAll.stream().collect(Collectors.groupingBy((s) -> s.getBusId())) : new HashMap(1));
      Map<Long, TmplIndex> tmplIndexMap = new HashMap(1);
      if (CollectionUtils.isNotEmpty(subfileIndexList)) {
         List<Long> tempIdList = (List)subfileIndexList.stream().map((t) -> t.getTempId()).distinct().collect(Collectors.toList());
         List<TmplIndex> tmplIndexList = this.tmplIndexService.selectListByIds(tempIdList);
         tmplIndexMap = (Map)tmplIndexList.stream().collect(Collectors.toMap((t) -> t.getId(), Function.identity()));
      }

      for(SubfileIndex subfileIndex1 : subfileIndexList) {
         SubfileIndexVo subfileIndexVo = new SubfileIndexVo();
         BeanUtils.copyProperties(subfileIndex1, subfileIndexVo);
         EmrTaskInfoVo emrTaskInfoVo = new EmrTaskInfoVo();
         emrTaskInfoVo.setMrFileId(subfileIndex1.getId());
         emrTaskInfoVo.setDocCd(user.getUserName());
         emrTaskInfoVo.setBusSection("22");
         List<EmrTaskInfo> emrTaskInfoList = this.emrTaskInfoService.selectInfoByOdcsAndIndex(emrTaskInfoVo);
         if (emrTaskInfoList != null && emrTaskInfoList.size() > 0 && "08".equals(subfileIndex1.getMrState())) {
            subfileIndexVo.setFreeMoveType("22");
         }

         subfileIndexVo.setChecked(CommonConstants.BOOL_FALSE);
         List<EmrTaskInfo> emrTaskInfos = (List)taskMap.get(subfileIndex1.getId() == null ? subfileIndex1.getId() : subfileIndex1.getId().toString());
         subfileIndexVo.setDutyDocName(emrTaskInfos != null && !emrTaskInfos.isEmpty() ? ((EmrTaskInfo)emrTaskInfos.get(0)).getDocName() : "");
         if (emrTaskInfos != null && CollectionUtils.isNotEmpty(emrTaskInfos)) {
            BasCertInfo basCertInfo = this.basCertInfoService.selectBasCertInfoByEmployeenumber(((EmrTaskInfo)emrTaskInfos.get(0)).getDocCd());
            subfileIndexVo.setSignCertSn(basCertInfo != null ? basCertInfo.getCertSn() : null);
         }

         TmplIndex tmplIndex = (TmplIndex)tmplIndexMap.get(subfileIndexVo.getTempId());
         subfileIndexVo.setTempName(tmplIndex != null ? tmplIndex.getTempName() : null);
         subfileIndexVo.setShowName(tmplIndex != null ? tmplIndex.getShowName() : null);
         subfileIndexVo.setTempMajor(tmplIndex != null ? tmplIndex.getTempMajor() : null);
         subfileIndexVo.setTempDisease(tmplIndex != null ? tmplIndex.getTempDisease() : null);
         subfileIndexVoList.add(subfileIndexVo);
      }

      subfileIndexVoList.sort(Comparator.comparing(SubfileIndex::getRecoDate).reversed());
      indexSaveReturnVo.setSubfileIndexList(subfileIndexVoList);
      if (addMainFlag) {
         BusIntegMenuVo busIntegMenuVo = this.busIntegMenuService.busmenuTreeslist(index.getPatientId());
         indexSaveReturnVo.setCurrMenuTreeList(busIntegMenuVo.getCurrMenuTreeList());
      }

      indexSaveReturnVo.setIndex(index);
      indexSaveReturnVo.setSubfileIndex(subfileIndex);
      return indexSaveReturnVo;
   }

   public void insertIndex(IndexSaveVo indexSaveVo, Index index, SubfileIndex subfileIndex) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      TmplIndex tmplIndex = this.tmplIndexService.selectTmplIndexById(indexSaveVo.getTempId());
      VisitinfoVo visitinfo = this.visitinfoService.selectVisitinfoByPatientId(indexSaveVo.getPatientId());
      EmrTaskInfo emrTaskInfoOverTime = new EmrTaskInfo();
      if (indexSaveVo.getTaskId() != null) {
         emrTaskInfoOverTime = this.emrTaskInfoService.selectEmrTaskInfoById(indexSaveVo.getTaskId());
      }

      Personalinfo personalinfo = this.personalinfoService.selectPersonalinfoById(indexSaveVo.getPatientId());
      GrantOutDoctor grantOutDoctor = this.grantOutDoctorService.selectGrantOutDoctorBySubNo(user.getUserName());
      String tempClassName = this.sysDictDataService.selectDictLabel("s003", tmplIndex.getTempClass());
      index.setOrgCd(user.getHospital().getOrgCode());
      index.setOrgName(user.getHospital().getOrgName());
      index.setPatientId(indexSaveVo.getPatientId());
      SysEmrTypeConfigVo sysEmrTypeConfigVo = this.sysEmrTypeConfigService.selectSysEmrTypeConfigByTypeId(tmplIndex.getTempType());
      String mrType = "01".equals(tmplIndex.getTempClass()) && sysEmrTypeConfigVo != null && sysEmrTypeConfigVo.getMergeFlag() != 0L ? "MAINFILE" : tmplIndex.getTempType();
      index.setMrType(mrType);
      index.setMrFileShowName(indexSaveVo.getIndexName());
      index.setMrTypeCd(tmplIndex.getTempClass());
      index.setMrTypeName(tempClassName);
      index.setAreaCode(visitinfo.getAreaCode());
      index.setWardName(visitinfo.getWardName());
      if (indexSaveVo.getTaskId() != null) {
         index.setDeptCd(emrTaskInfoOverTime.getDeptCd());
         index.setDeptName(emrTaskInfoOverTime.getDeptName());
      } else {
         index.setDeptCd(visitinfo.getDeptCd());
         index.setDeptName(visitinfo.getDeptName());
      }

      index.setMrState(indexSaveVo.getSaveType());
      index.setTempId(indexSaveVo.getTempId());
      index.setSecLevel(StringUtils.isNotBlank(indexSaveVo.getSecLevel()) ? indexSaveVo.getSecLevel() : "1");
      index.setCrePerName(user.getNickName());
      index.setCrePerCode(user.getUserName());
      index.setCpDeptCd(user.getDept().getDeptCode());
      index.setCpDeptName(user.getDept().getDeptName());
      index.setRecoDate(indexSaveVo.getCreDate());
      index.setFileType("EMR");
      index.setBedNo(visitinfo.getBedNo());
      index.setBabyId(indexSaveVo.getBabyId());
      index.setDelFlag("0");
      index.setCreDate(indexSaveVo.getCreDate());
      index.setInpNo(visitinfo.getInpNo());
      index.setPatientName(personalinfo.getPatientName());
      index.setUperDoct(indexSaveVo.getUperDoct());
      index.setUperDoctName(indexSaveVo.getUperDoctName());
      index.setUperTitle(indexSaveVo.getTitleCode());
      index.setUperTitleName(indexSaveVo.getTitleName());
      if (grantOutDoctor != null) {
         index.setIntDocCd(user.getUserName());
         index.setIntDocName(user.getNickName());
         index.setCrePerName(grantOutDoctor.getDocName());
         index.setCrePerCode(grantOutDoctor.getDocCode());
      }

      if (StringUtils.isNotBlank(indexSaveVo.getDataType()) && indexSaveVo.getDataType().equals("3") && indexSaveVo.getTaskId() != null) {
         EmrTaskInfo emrTaskInfo = this.emrTaskInfoService.selectEmrTaskInfoById(indexSaveVo.getTaskId());
         Date lastFinishTime = emrTaskInfo != null ? emrTaskInfo.getEndTime() : null;
         index.setLastFinishTime(lastFinishTime);
         index.setLastFinishRuleId(Long.valueOf(emrTaskInfo.getBusId()));
         this.emrQcListService.updateMissingFileListByMrFileId(String.valueOf(indexSaveVo.getTaskId()));
      } else {
         EmrTaskInfo emrTaskInfo = this.qcAgiRuleService.getIndexLastFinishTime(index.getPatientId(), tmplIndex.getTempType(), mrType.equals("MAINFILE"));
         index.setLastFinishTime(emrTaskInfo != null ? emrTaskInfo.getEndTime() : null);
         index.setLastFinishRuleId(emrTaskInfo != null ? Long.valueOf(emrTaskInfo.getBusId()) : null);
      }

      if (mrType.equals("MAINFILE")) {
         BeanUtils.copyProperties(index, subfileIndex);
         Long id = SnowIdUtils.uniqueLong();
         indexSaveVo.setId(id);
         subfileIndex.setId(id);
         subfileIndex.setMainId(index.getId());
         subfileIndex.setMrType(tmplIndex.getTempType());
         subfileIndex.setMrFileShowName(indexSaveVo.getSubIndexName());
         subfileIndex.setContElemName(indexSaveVo.getSubIndexContElemName());
         subfileIndex.setChangeDepBedId(indexSaveVo.getDeptBEDateVoCurnId() == null ? null : Long.parseLong(indexSaveVo.getDeptBEDateVoCurnId()));
         this.subfileIndexService.insertSubfileIndex(subfileIndex);
      }

      this.indexMapper.insertIndex(index);
   }

   private void insertSubIndex(IndexSaveVo indexSaveVo, Index index, SubfileIndex subfileIndex) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      TmplIndex tmplIndex = this.tmplIndexService.selectTmplIndexById(indexSaveVo.getTempId());
      VisitinfoVo visitinfo = this.visitinfoService.selectVisitinfoByPatientId(indexSaveVo.getPatientId());
      GrantOutDoctor grantOutDoctor = this.grantOutDoctorService.selectGrantOutDoctorBySubNo(user.getUserName());
      subfileIndex.setMainId(index.getId());
      subfileIndex.setMrType(tmplIndex.getTempType());
      subfileIndex.setMrFileShowName(indexSaveVo.getSubIndexName());
      subfileIndex.setAreaCode(visitinfo.getAreaCode());
      subfileIndex.setWardName(visitinfo.getWardName());
      subfileIndex.setDeptCd(visitinfo.getDeptCd());
      subfileIndex.setDeptName(visitinfo.getDeptName());
      subfileIndex.setMrState(indexSaveVo.getSaveType());
      subfileIndex.setTempId(indexSaveVo.getTempId());
      subfileIndex.setSecLevel(StringUtils.isNotBlank(indexSaveVo.getSecLevel()) ? indexSaveVo.getSecLevel() : "1");
      subfileIndex.setRecoDate(indexSaveVo.getRecoDate());
      subfileIndex.setBedNo(visitinfo.getBedNo());
      subfileIndex.setDelFlag("0");
      subfileIndex.setCreDate(indexSaveVo.getCreDate());
      subfileIndex.setCrePerName(user.getNickName());
      subfileIndex.setCrePerCode(user.getUserName());
      subfileIndex.setCpDeptCd(user.getDept().getDeptCode());
      subfileIndex.setCpDeptName(user.getDept().getDeptName());
      subfileIndex.setUperDoct(indexSaveVo.getSubIndexUperDoct());
      subfileIndex.setUperDoctName(indexSaveVo.getSubIndexUperDoctName());
      subfileIndex.setUperTitle(indexSaveVo.getSubIndexTitleCode());
      subfileIndex.setUperTitleName(indexSaveVo.getSubIndexTitleName());
      subfileIndex.setContElemName(indexSaveVo.getSubIndexContElemName());
      subfileIndex.setChangeDepBedId(indexSaveVo.getDeptBEDateVoCurnId() == null ? null : Long.parseLong(indexSaveVo.getDeptBEDateVoCurnId()));
      subfileIndex.setMergeFlag(indexSaveVo.getSubIndexMergeFlag());
      if (grantOutDoctor != null) {
         index.setIntDocCd(user.getUserName());
         index.setIntDocName(user.getNickName());
         index.setCrePerName(grantOutDoctor.getDocName());
         index.setCrePerCode(grantOutDoctor.getDocCode());
      }

      if (StringUtils.isNotBlank(indexSaveVo.getDataType()) && indexSaveVo.getDataType().equals("3") && indexSaveVo.getTaskId() != null) {
         EmrTaskInfo emrTaskInfo = this.emrTaskInfoService.selectEmrTaskInfoById(indexSaveVo.getTaskId());
         Date lastFinishTime = emrTaskInfo != null ? emrTaskInfo.getEndTime() : null;
         index.setLastFinishTime(lastFinishTime);
         index.setLastFinishRuleId(Long.valueOf(emrTaskInfo.getBusId()));
         this.emrQcListService.updateMissingFileListByMrFileId(String.valueOf(indexSaveVo.getTaskId()));
      } else {
         EmrTaskInfo emrTaskInfo = this.qcAgiRuleService.getIndexLastFinishTime(index.getPatientId(), tmplIndex.getTempType(), true);
         index.setLastFinishTime(emrTaskInfo != null ? emrTaskInfo.getEndTime() : null);
         index.setLastFinishRuleId(emrTaskInfo != null ? Long.valueOf(emrTaskInfo.getBusId()) : null);
      }

      this.subfileIndexService.insertSubfileIndex(subfileIndex);
   }

   public void updateIndexMrState(Long id, String mrState, Date updateTime) throws Exception {
      SysUser updateUser = SecurityUtils.getLoginUser().getUser();
      Index indexUpdate = new Index();
      indexUpdate.setId(id);
      indexUpdate.setMrState(mrState);
      indexUpdate.setAltPerCode(updateUser.getUserName());
      indexUpdate.setAltPerName(updateUser.getNickName());
      indexUpdate.setAltDate(updateTime);
      this.updateIndex(indexUpdate);
   }

   private void getSaveEmrSharingElems(String mrType, Index index, SysUser updateUser, List elemAttriVoList, List emrSharingList, List patientEmrSharingElemIdDelList) {
      SysShareElem shareElemParam = new SysShareElem();
      shareElemParam.setTempType(mrType);
      List<SysShareElem> shareElemList = this.sysShareElemService.selectSysShareElemList(shareElemParam);
      List<Long> shareElemIdList = (List)shareElemList.stream().map((t) -> t.getElemId()).distinct().collect(Collectors.toList());
      List<ElemAttriVo> emrShareElemAttriVoList = (List)elemAttriVoList.stream().filter((t) -> shareElemIdList.contains(t.getElemId())).collect(Collectors.toList());
      EmrSharing emrSharingParam = new EmrSharing();
      emrSharingParam.setPatientId(index.getPatientId());
      List<EmrSharing> patientEmrSharingList = this.emrSharingService.selectEmrSharingList(emrSharingParam);
      List<Long> patientEmrSharingElemIdList = (List)patientEmrSharingList.stream().map((t) -> t.getElemId()).distinct().collect(Collectors.toList());
      if (emrShareElemAttriVoList != null && !emrShareElemAttriVoList.isEmpty()) {
         for(ElemAttriVo elemAttriVo : emrShareElemAttriVoList) {
            EmrSharing emrSharing = new EmrSharing();
            emrSharing.setId(SnowIdUtils.uniqueLong());
            emrSharing.setOrgCd(index.getOrgCd());
            emrSharing.setPatientId(index.getPatientId());
            emrSharing.setElemId(elemAttriVo.getElemId());
            emrSharing.setElemCd(elemAttriVo.getElemCd());
            emrSharing.setElemTypeCd(elemAttriVo.getElemTypeCd());
            emrSharing.setElemCon(elemAttriVo.getElemCon());
            emrSharing.setElemConText(elemAttriVo.getElemConText());
            emrSharing.setCrePerCode(updateUser.getUserName());
            emrSharing.setCrePerName(updateUser.getNickName());
            emrSharingList.add(emrSharing);
            if (patientEmrSharingElemIdList.contains(elemAttriVo.getElemId())) {
               patientEmrSharingElemIdDelList.add(elemAttriVo.getElemId());
            }
         }
      }

   }

   public String getMrCon(String base64Str, String xmlStr, String textStr, String subFileTextStr) throws Exception {
      JSONObject indexMrConObj = new JSONObject();
      indexMrConObj.put("base64", base64Str);
      indexMrConObj.put("xmlStr", xmlStr);
      textStr = StringUtils.isBlank(textStr) ? "" : textStr.replace("\n", "").replace("\r", "");
      indexMrConObj.put("textStr", textStr);
      subFileTextStr = StringUtils.isBlank(subFileTextStr) ? "" : subFileTextStr.replace("\n", "").replace("\r", "");
      indexMrConObj.put("subFileTextStr", subFileTextStr);
      String mrCon = indexMrConObj.toJSONString();
      return mrCon;
   }

   private void getSaveEmrElemstoes(String mrType, Long mrFileId, Index index, SysUser updateUser, List elemAttriVoList, List emrElemstoeList) {
      SysElemStrstore sysElemStrstoreParam = new SysElemStrstore();
      sysElemStrstoreParam.setTempType(mrType);
      List<SysElemStrstore> sysElemStrstoreList = this.sysElemStrstoreService.selectSysElemStrstoreList(sysElemStrstoreParam);
      List<Long> sysElemStrstoreElemIdList = (List)sysElemStrstoreList.stream().map((t) -> t.getElemId()).distinct().collect(Collectors.toList());
      List<ElemAttriVo> strstoreElemAttriVoList = (List)elemAttriVoList.stream().filter((t) -> sysElemStrstoreElemIdList.contains(t.getElemId())).collect(Collectors.toList());
      if (strstoreElemAttriVoList != null && !strstoreElemAttriVoList.isEmpty()) {
         for(ElemAttriVo elemAttriVo : strstoreElemAttriVoList) {
            EmrElemstoe elemstoe = new EmrElemstoe();
            elemstoe.setId(SnowIdUtils.uniqueLong());
            elemstoe.setOrgCd(index.getOrgCd());
            elemstoe.setOrgName(index.getOrgName());
            elemstoe.setPatientId(index.getPatientId());
            elemstoe.setMrFileId(mrFileId);
            elemstoe.setMrTypeCd(mrType);
            elemstoe.setPrioElemCd(elemAttriVo.getParentElemCd());
            elemstoe.setPrioElemId(elemAttriVo.getParentElemId());
            elemstoe.setPrioElemName(elemAttriVo.getParentElemName());
            elemstoe.setPrioFileElemid(elemAttriVo.getParentContElemName());
            elemstoe.setElemId(elemAttriVo.getElemId());
            elemstoe.setElemCd(elemAttriVo.getElemCd());
            elemstoe.setElemName(elemAttriVo.getElemName());
            elemstoe.setFileElemId(elemAttriVo.getContElemName());
            elemstoe.setElemCon(elemAttriVo.getElemConText());
            elemstoe.setUnitId(elemAttriVo.getUnitId());
            elemstoe.setCrePerCode(updateUser.getUserName());
            elemstoe.setCrePerName(updateUser.getNickName());
            emrElemstoeList.add(elemstoe);
         }
      }

   }

   public List selectElemMacroListByType(List indexTempIdList, Long mainId) throws Exception {
      List<ElemMacro> elemMacroList = this.elemMacroService.selectListByTempIds(indexTempIdList, mainId);
      return elemMacroList;
   }

   public List selectElemQuoteByType(TmplIndex tmplIndex) throws Exception {
      new ArrayList();
      QuoteElem quoteElem = new QuoteElem();
      quoteElem.setTempType(tmplIndex.getTempType());
      List<QuoteElem> quoteElemList = this.quoteElemService.selectQuoteElemList(quoteElem);
      List<QuoteElem> distinctClass = (List)quoteElemList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet(Comparator.comparing((o) -> o.getElemId()))), ArrayList::new));
      return distinctClass;
   }

   public EmrBinaryVo selectSubfileIndexList(String patientId) throws Exception {
      EmrBinaryVo emrBinaryVo = new EmrBinaryVo();
      new ArrayList();
      Index indexInfo = new Index();
      indexInfo.setPatientId(patientId);
      indexInfo.setMrType("MAINFILE");
      List<Index> indexList = this.indexMapper.selectIndexList(indexInfo);
      if (indexList != null && indexList.size() > 0) {
         String base64 = Base64Util.getFileBase64("emrBase64/" + patientId, ((Index)indexList.get(0)).getId().toString() + ".txt");
         List list = this.subfileIndexService.selectSubfileIndexByMainId(((Index)indexList.get(0)).getId());
         EmrBinary emrBinary = this.emrBinaryService.selectEmrBinaryById(((Index)indexList.get(0)).getId());
         BeanUtils.copyProperties(emrBinary, emrBinaryVo);
         Map<String, String> map = (Map)JSON.parse(emrBinary.getMrCon());
         map.put("base64", base64);
         emrBinaryVo.setMrCon(JSON.toJSONString(map));
         emrBinaryVo.setSubfileIndexList(list);
      }

      return emrBinaryVo;
   }

   public List selectEmrSignList(Index index, SubfileIndex subfileIndex, String xmlStr) throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      Long tempId = subfileIndex == null ? index.getTempId() : subfileIndex.getTempId();
      Long id = subfileIndex == null ? index.getId() : subfileIndex.getId();
      List<ElemSign> signList = new ArrayList();
      EmrSignRecord emrSignRecord = new EmrSignRecord();
      emrSignRecord.setMrFileId(id);
      emrSignRecord.setSignCancFlag("0");
      emrSignRecord.setDocCode(sysUser.getUserName());
      List<EmrSignRecord> emrSignRecordList = this.emrSignRecordService.selectEmrSignRecordList(emrSignRecord);
      if (emrSignRecordList != null && emrSignRecordList.size() > 0) {
         List<ElemSign> elemSignList = null;
         if (subfileIndex != null) {
            String editorType = this.sysEmrConfigService.selectSysEmrConfigByKey("0000");
            TempIndexSaveElemVo tempIndexSaveElemVo = XmlElementParseUtil.getSaveElemFromXml(xmlStr, editorType);
            elemSignList = tempIndexSaveElemVo.getElemSignList();
         } else {
            elemSignList = this.elemSignService.selectElemSignByTempId(tempId);
         }

         for(ElemSign elemSign : elemSignList) {
            List<EmrSignRecord> signRecordListTemp = (List)emrSignRecordList.stream().filter((t) -> String.valueOf(elemSign.getElemId()).equals(t.getSignPos()) && elemSign.getContElemName().equals(t.getSignSname())).collect(Collectors.toList());
            if (signRecordListTemp != null && !signRecordListTemp.isEmpty()) {
               signList.add(elemSign);
            }
         }
      }

      return signList;
   }

   public List selectEmrNoSignList(Index index, SubfileIndex subfileIndex, String xmlStr) throws Exception {
      Long id = subfileIndex == null ? index.getId() : subfileIndex.getId();
      List<ElemSign> signList = new ArrayList();
      EmrSignRecord emrSignRecord = new EmrSignRecord();
      emrSignRecord.setMrFileId(id);
      emrSignRecord.setSignCancFlag("0");
      List<EmrSignRecord> emrSignRecordList = this.emrSignRecordService.selectEmrSignRecordList(emrSignRecord);
      List<EmrSignRecord> emrSignRecordFreeList = this.emrSignRecordService.selectEmrSignRecordFreeList(emrSignRecord);
      emrSignRecordList.addAll(emrSignRecordFreeList);
      String editorType = this.sysEmrConfigService.selectSysEmrConfigByKey("0000");
      TempIndexSaveElemVo tempIndexSaveElemVo = XmlElementParseUtil.getSaveElemFromXml(xmlStr, editorType);
      List<ElemSign> elemSignList = tempIndexSaveElemVo.getElemSignList();

      for(ElemSign elemSign : (List)elemSignList.stream().filter((s) -> s.getSignTypeCd().equals("11")).collect(Collectors.toList())) {
         List<EmrSignRecord> signRecordListTemp = (List)emrSignRecordList.stream().filter((t) -> elemSign.getElemId().toString().equals(t.getSignPos()) && elemSign.getContElemName().equals(t.getSignSname())).collect(Collectors.toList());
         if (signRecordListTemp == null || signRecordListTemp != null && signRecordListTemp.isEmpty()) {
            signList.add(elemSign);
         }
      }

      if (!signList.isEmpty()) {
         HashMap<Long, Integer> hashMap = new HashMap();
         List<Long> elemIdList = (List)signList.stream().map(ElemSign::getElemId).collect(Collectors.toList());

         for(SysStaElem elem : this.sysStaElemMapper.selectSysStaElemByIdList(elemIdList)) {
            String elemQua = elem.getElemQua();
            if (StringUtils.isNotBlank(elemQua)) {
               HashMap map = (HashMap)JSON.parseObject(elemQua, HashMap.class);
               if (map != null) {
                  Object o = map.get("elems");
                  if (o != null) {
                     List<String> list = JSONObject.parseArray(o.toString(), String.class);
                     if (list != null && list.size() > 0) {
                        for(String value : list) {
                           HashMap mapChild = (HashMap)JSON.parseObject(value, HashMap.class);
                           if (mapChild != null) {
                              Object obj = mapChild.get("elem");
                              if (obj != null) {
                                 SysStaElemVo elo = (SysStaElemVo)JSON.parseObject(obj.toString(), SysStaElemVo.class);
                                 if (elo != null) {
                                    int sort = elo.getSort();
                                    if (sort != 0) {
                                       hashMap.put(elem.getId(), sort);
                                    }
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
         }

         for(ElemSign elemSign : signList) {
            Long elemId = elemSign.getElemId();
            if (hashMap.containsKey(elemId)) {
               Integer sort = (Integer)hashMap.get(elemId);
               elemSign.setSort(sort);
            } else {
               elemSign.setSort(0);
            }
         }

         signList = (List)signList.stream().sorted(Comparator.comparing(ElemSign::getSort)).collect(Collectors.toList());
      }

      return signList;
   }

   public Map selectElemAnewList(IndexVo indexVo, Boolean isMainFile) throws Exception {
      Long tempId = indexVo.getTempId();
      Map<String, List<String>> map = new HashMap();
      List<String> keyList = new ArrayList();
      List<String> valueList = new ArrayList();
      List<ElemMacro> elemMacroList = new ArrayList();
      List<QuoteElemVo> quoElemList = new ArrayList();
      String editorType = this.sysEmrConfigService.selectSysEmrConfigByKey("0000");
      TempIndexSaveElemVo tempIndexSaveElemVo = XmlElementParseUtil.getSaveElemFromXml(indexVo.getXmlStr(), editorType);
      if (tempIndexSaveElemVo != null) {
         if (tempIndexSaveElemVo.getElemMacroList() != null && tempIndexSaveElemVo.getElemMacroList().size() > 0) {
            for(ElemMacro elemMacro : tempIndexSaveElemVo.getElemMacroList()) {
               if ((!StringUtils.isNotBlank(indexVo.getAllFlag()) || !indexVo.getAllFlag().equals("1") || !StringUtils.isNotBlank(elemMacro.getSourColu()) || !elemMacro.getSourColu().toLowerCase().equals("dept_name") && !elemMacro.getSourColu().toLowerCase().equals("bed_no")) && indexVo.getMacroList().contains(elemMacro.getElemId())) {
                  elemMacroList.add(elemMacro);
               }
            }
         }

         if (isMainFile) {
            tempIndexSaveElemVo = XmlElementParseUtil.getSaveElemFromXml(indexVo.getSubFileXmlStr(), editorType);
         }

         List<ElemAttri> elemAttriList = tempIndexSaveElemVo.getElemAttriList();

         for(Long quoteElemId : indexVo.getQuoteList()) {
            List<ElemAttri> tempList = (List)elemAttriList.stream().filter((t) -> t.getElemId() != null && t.getElemId().equals(quoteElemId)).collect(Collectors.toList());
            if (tempList != null && !tempList.isEmpty()) {
               for(ElemAttri elemAttri : tempList) {
                  QuoteElemVo quoteElemVo = new QuoteElemVo();
                  quoteElemVo.setElemId(String.valueOf(quoteElemId));
                  quoteElemVo.setContElemName(elemAttri.getContElemName());
                  quoElemList.add(quoteElemVo);
               }
            }
         }
      }

      if (elemMacroList != null && elemMacroList.size() > 0) {
         Map<String, List<String>> elemMacroMap = this.elemMacroKeyAndValue(indexVo.getPatientId(), indexVo.getTestExamResultId(), indexVo.getBabyId(), elemMacroList);
         keyList.addAll((Collection)elemMacroMap.get("setStructsTextIdList"));
         valueList.addAll((Collection)elemMacroMap.get("setStructsTextValueList"));
      }

      if (quoElemList != null && quoElemList.size() > 0) {
         Map<String, List<String>> quoteMap = this.quoteElemKeyAndValue(tempId, indexVo.getPatientId(), quoElemList);
         keyList.addAll((Collection)quoteMap.get("setStructsTextIdList"));
         valueList.addAll((Collection)quoteMap.get("setStructsTextValueList"));
         map.put("base64IdList", quoteMap.get("base64IdList"));
         map.put("base64ValueList", quoteMap.get("base64ValueList"));
      }

      map.put("setStructsTextIdList", keyList);
      map.put("setStructsTextValueList", valueList);
      return map;
   }

   public Map selectElemSmartQuote(IndexVo indexVo) throws Exception {
      Map<String, List<String>> map = new HashMap();
      List<String> keyList = new ArrayList();
      List<String> valueList = new ArrayList();
      String editorType = this.sysEmrConfigService.selectSysEmrConfigByKey("0000");
      TempIndexSaveElemVo tempIndexSaveElemVo = XmlElementParseUtil.getSaveElemFromXml(indexVo.getXmlStr(), editorType);
      List<EmrSharingVo> emrSharingList = this.emrSharingService.selectEmrSharingListByPatientId(indexVo.getPatientId());

      for(ElemAttri elemAttri : tempIndexSaveElemVo.getElemAttriList()) {
         if (indexVo.getQuoteList().contains(elemAttri.getElemId())) {
            keyList.add(elemAttri.getContElemName());

            for(EmrSharingVo emrSharingVo : emrSharingList) {
               if (elemAttri.getElemId().equals(emrSharingVo.getElemId())) {
                  valueList.add(emrSharingVo.getElemConText());
                  break;
               }
            }
         }
      }

      map.put("setStructsTextIdList", keyList);
      map.put("setStructsTextValueList", valueList);
      return map;
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public IndexSaveReturnVo signIndex(IndexSignVo indexSignVo, Index index, SubfileIndex subfileIndex, SysEmrTypeLevel saveTypeLevel) throws Exception {
      this.log.debug("signIndex-1111111111111111111111111: " + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
      VisitinfoVo visitinfoVo = this.visitinfoService.selectVisitinfoById(index.getPatientId());
      SysUser user = this.getUserBySignCertSn(indexSignVo.getCertSn());
      BasEmployee employee = this.basEmployeeService.selectByEmplNumber(user.getUserName());
      user.setBasEmployee(employee);
      Long mrFileId = subfileIndex != null ? subfileIndex.getId() : index.getId();
      String mrFileName = subfileIndex != null ? subfileIndex.getMrFileShowName() : index.getMrFileShowName();
      Date signTime = this.commonService.getDbSysdate();
      Date updateTime = subfileIndex != null ? subfileIndex.getAltDate() : index.getAltDate();
      List<ElemSignVo> signElemList = indexSignVo.getSignElemList();
      List<EmrSignRecord> signRecordList = new ArrayList(signElemList.size());
      List<EmrSignData> signDataList = new ArrayList(signElemList.size());
      if (signElemList == null || signElemList.isEmpty()) {
         ElemSignVo signElem = new ElemSignVo();
         signElem.setElemId(mrFileId);
         signElem.setContElemName(mrFileId.toString());
         signElemList.add(signElem);
      }

      if (StringUtils.isNotBlank(indexSignVo.getSignSnameOld())) {
         EmrSignRecord emrSignRecordOld = new EmrSignRecord();
         emrSignRecordOld.setMrFileId(mrFileId);
         emrSignRecordOld.setSignSname(indexSignVo.getSignSnameOld());
         List<EmrSignRecord> emrSignRecordMoveList = this.emrSignRecordService.selectEmrSignRecordForFeeMoveList(emrSignRecordOld);
         List<EmrSignRecord> emrSignRecordList = this.emrSignRecordService.selectEmrSignRecordList(emrSignRecordOld);
         if (CollectionUtils.isNotEmpty(emrSignRecordMoveList) && emrSignRecordMoveList.size() > 0) {
            EmrSignRecord emrSignRecord = (EmrSignRecord)emrSignRecordList.get(0);
            EmrSignData emrSignData = new EmrSignData();
            emrSignData.setId(emrSignRecord.getSignDataId());
            emrSignData.setTypeCd("1");
            emrSignData.setCertType("1");
            emrSignData.setSignFileId(mrFileId.toString());
            emrSignData.setSignerCd(user.getUserName());
            emrSignData.setSignerName(user.getNickName());
            emrSignData.setOldText(indexSignVo.getOldText());
            emrSignData.setSignText(indexSignVo.getSignText());
            emrSignData.setTimeStamp(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, signTime));
            emrSignData.setSignTime(signTime);
            emrSignData.setCertInfo(indexSignVo.getCertInfo());
            emrSignData.setCertSn(indexSignVo.getCertSn());
            emrSignData.setIsValid("1");
            emrSignData.setCreDate(signTime);
            emrSignData.setCrePerCode(user.getUserName());
            emrSignData.setCrePerName(user.getNickName());
            this.emrSignDataService.updateEmrSignDataForMove(emrSignData);
         } else if (CollectionUtils.isNotEmpty(emrSignRecordList) && emrSignRecordList.size() > 0) {
            EmrSignRecord emrSignRecord = (EmrSignRecord)emrSignRecordList.get(0);
            EmrSignData emrSignData = new EmrSignData();
            emrSignData.setId(emrSignRecord.getSignDataId());
            emrSignData.setTypeCd("1");
            emrSignData.setCertType("1");
            emrSignData.setSignFileId(mrFileId.toString());
            emrSignData.setSignerCd(user.getUserName());
            emrSignData.setSignerName(user.getNickName());
            emrSignData.setOldText(indexSignVo.getOldText());
            emrSignData.setSignText(indexSignVo.getSignText());
            emrSignData.setTimeStamp(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, signTime));
            emrSignData.setSignTime(signTime);
            emrSignData.setCertInfo(indexSignVo.getCertInfo());
            emrSignData.setCertSn(indexSignVo.getCertSn());
            emrSignData.setIsValid("1");
            emrSignData.setCreDate(signTime);
            emrSignData.setCrePerCode(user.getUserName());
            emrSignData.setCrePerName(user.getNickName());
            this.emrSignDataService.updateEmrSignData(emrSignData);
         }
      } else {
         for(ElemSignVo signElem : signElemList) {
            EmrSignData emrSignData = new EmrSignData();
            emrSignData.setId(SnowIdUtils.uniqueLong());
            emrSignData.setTypeCd("1");
            emrSignData.setCertType("1");
            emrSignData.setSignFileId(mrFileId.toString());
            emrSignData.setSignerCd(user.getUserName());
            emrSignData.setSignerName(user.getNickName());
            emrSignData.setOldText(indexSignVo.getOldText());
            emrSignData.setSignText(indexSignVo.getSignText());
            emrSignData.setTimeStamp(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, signTime));
            emrSignData.setSignTime(signTime);
            emrSignData.setCertInfo(indexSignVo.getCertInfo());
            emrSignData.setCertSn(indexSignVo.getCertSn());
            emrSignData.setIsValid("1");
            emrSignData.setCreDate(signTime);
            emrSignData.setCrePerCode(user.getUserName());
            emrSignData.setCrePerName(user.getNickName());
            signDataList.add(emrSignData);
            EmrSignRecord emrSignRecord = new EmrSignRecord();
            emrSignRecord.setId(SnowIdUtils.uniqueLong());
            emrSignRecord.setSignDataId(emrSignData.getId());
            emrSignRecord.setMrFileId(mrFileId);
            emrSignRecord.setDocLevelCd(employee.getTitleCode());
            emrSignRecord.setDocLevelName(employee.getTitleName());
            emrSignRecord.setDocCode(employee.getEmplNumber());
            emrSignRecord.setDocName(employee.getEmplName());
            emrSignRecord.setSignPos(String.valueOf(signElem.getElemId()));
            emrSignRecord.setSignTime(signTime);
            emrSignRecord.setSignCancFlag("0");
            emrSignRecord.setCrePerCode(user.getUserName());
            emrSignRecord.setCrePerName(user.getNickName());
            emrSignRecord.setCreDate(signTime);
            emrSignRecord.setSignImagePos(signElem.getSignImageName());
            emrSignRecord.setSignSname(signElem.getContElemName());
            emrSignRecord.setEmrUpdateTime(updateTime);
            signRecordList.add(emrSignRecord);
         }
      }

      this.log.debug("signIndex-222222222222222222222222222222: " + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
      if (!signRecordList.isEmpty()) {
         this.emrSignRecordService.addList(signRecordList);
      }

      if (!signDataList.isEmpty()) {
         this.emrSignDataService.addList(signDataList);
      }

      this.log.debug("signIndex-33333333333333333333333333333: " + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
      String mrState = saveTypeLevel.getMrState();
      String nextDocCod = "";
      EmrTaskInfo task = new EmrTaskInfo();
      task.setPatientId(index.getPatientId());
      task.setMrFileId(mrFileId);
      task.setTaskType("3");
      task.setDocCd(user.getBasEmployee().getEmplNumber());
      task.setTreatFlag("0");
      List<EmrTaskInfo> emrInfos = this.emrTaskInfoService.selectEmrTaskInfoList(task);
      if (CollectionUtils.isNotEmpty(emrInfos) && StringUtils.isNotBlank(((EmrTaskInfo)emrInfos.get(0)).getDutyDeptCd())) {
         String deptCd = ((EmrTaskInfo)emrInfos.get(0)).getDutyDeptCd();
         String deptName = ((EmrTaskInfo)emrInfos.get(0)).getDutyDeptName();
         SysDept dept = new SysDept();
         dept.setDeptCode(deptCd);
         dept.setDeptName(deptName);
         user.setDept(dept);
         user.setHospital(SecurityUtils.getLoginUser().getUser().getHospital());
      } else {
         user.setDept(SecurityUtils.getLoginUser().getUser().getDept());
      }

      if (saveTypeLevel.getBusSection().equals("2") || saveTypeLevel.getBusSection().equals("3")) {
         String deptCode = user.getDept().getDeptCode();
         String deptNameImpl = user.getDept().getDeptName();
         String deptCd = subfileIndex != null ? subfileIndex.getDeptCd() : index.getDeptCd();
         String deptName = subfileIndex != null ? subfileIndex.getDeptName() : index.getDeptName();
         EmrTaskInfo emrTaskInfo = new EmrTaskInfo();
         emrTaskInfo.setId(SnowIdUtils.uniqueLong());
         emrTaskInfo.setInpNo(visitinfoVo.getInpNo());
         emrTaskInfo.setPatientId(index.getPatientId());
         emrTaskInfo.setPatientName(index.getPatientName());
         emrTaskInfo.setDeptCd(deptCd);
         emrTaskInfo.setDeptName(deptName);
         emrTaskInfo.setTaskType("3");
         emrTaskInfo.setTaskTypeName("上级审签");
         emrTaskInfo.setTaskAppDoc(user.getBasEmployee().getEmplNumber());
         emrTaskInfo.setTaskAppDocName(user.getBasEmployee().getEmplName());
         emrTaskInfo.setBusId(mrFileId.toString());
         emrTaskInfo.setBusName(mrFileName);
         emrTaskInfo.setBusSection(saveTypeLevel.getBusSection());
         emrTaskInfo.setDutyDeptCd(indexSignVo.getNextDeptCd());
         emrTaskInfo.setDutyDeptName(indexSignVo.getNextDeptName());
         emrTaskInfo.setBeginTime(this.commonService.getDbSysdate());
         if (mrState.equals("05")) {
            deptCode = saveTypeLevel.getSecondDeptCode();
            deptNameImpl = saveTypeLevel.getSecondDeptName();
            emrTaskInfo.setDocCd(saveTypeLevel.getSecondDocCode());
            emrTaskInfo.setDocName(saveTypeLevel.getSecondDocName());
         } else if (mrState.equals("07")) {
            deptCode = saveTypeLevel.getThirdDeptCode();
            deptNameImpl = saveTypeLevel.getThirdDeptName();
            emrTaskInfo.setDocCd(saveTypeLevel.getThirdDocCode());
            emrTaskInfo.setDocName(saveTypeLevel.getThirdDocName());
         } else {
            deptCode = user.getDept().getDeptCode();
            deptNameImpl = user.getDept().getDeptName();
            emrTaskInfo.setDocCd(indexSignVo.getNextDocCd());
            emrTaskInfo.setDocName(indexSignVo.getNextDocName());
         }

         emrTaskInfo.setTreatFlag("0");
         emrTaskInfo.setMrFileId(mrFileId);
         emrTaskInfo.setBusSectionName(saveTypeLevel.getBusSectionName());
         if (emrInfos != null && emrInfos.size() > 0) {
            for(EmrTaskInfo tasks : emrInfos) {
               this.emrTaskInfoService.updateTaskInfoById(tasks.getId());
            }
         }

         this.log.warn("signIndex-33333333333333333333333333333-insertEmrTaskInfo-begin: " + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
         this.emrTaskInfoService.insertEmrTaskInfo(emrTaskInfo);
         this.log.warn("signIndex-33333333333333333333333333333-insertEmrTaskInfo-end: " + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
         nextDocCod = emrTaskInfo.getDocCd();
         if (!deptCode.equals(index.getDeptCd())) {
            List<ImpDoctorTemp> temp = this.impDoctorTempService.selectImpDoctorTemp(emrTaskInfo.getDocCd(), index.getPatientId());
            if (temp == null || temp.size() == 0) {
               ImpDoctorTemp impDoctorTemp = new ImpDoctorTemp();
               impDoctorTemp.setId(SnowIdUtils.uniqueLong());
               impDoctorTemp.setDocCode(user.getUserName());
               impDoctorTemp.setDocName(user.getNickName());
               impDoctorTemp.setCrePerCode(user.getUserName());
               impDoctorTemp.setCrePerName(user.getNickName());
               SysOrg hospital = user.getHospital();
               impDoctorTemp.setOrgCd(hospital.getOrgCode());
               impDoctorTemp.setValidFlag("1");
               impDoctorTemp.setPatientId(emrTaskInfo.getPatientId());
               impDoctorTemp.setPatientName(emrTaskInfo.getPatientName());
               impDoctorTemp.setBedNo(index.getBedNo());
               impDoctorTemp.setImpDocCd(emrTaskInfo.getDocCd());
               impDoctorTemp.setImpDocName(emrTaskInfo.getDocName());
               impDoctorTemp.setImpDeptCd(deptCode);
               impDoctorTemp.setImpDeptName(deptNameImpl);
               impDoctorTemp.setImpRange("0");
               impDoctorTemp.setImpBegTime(new Date());
               Calendar calendar = new GregorianCalendar();
               calendar.setTime(new Date());
               calendar.add(5, 7);
               impDoctorTemp.setImpEndTime(calendar.getTime());
               impDoctorTemp.setImpType("2");
               impDoctorTemp.setImpAim("病历签名");
               this.impDoctorTempService.insertImpDoctor(impDoctorTemp);
            }
         }
      }

      if (!index.getDeptCd().equals(user.getDept().getDeptCode()) && (!nextDocCod.equals(user.getUserName()) || nextDocCod.equals(user.getUserName()) && mrState.equals("08"))) {
         this.impDoctorTempService.updateImpPatAndDoc(index.getPatientId(), employee.getEmplNumber(), "2");
      }

      this.log.debug("signIndex-44444444444444444444444444: " + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
      if (saveTypeLevel.getBusSection().equals("1")) {
         this.emrTaskInfoService.updateTaskInfoByMrfileId(mrFileId, "1");
         Boolean lockFlag = this.sealupRecordService.addSealupFile(index, subfileIndex);
         if (lockFlag) {
            if (subfileIndex != null && subfileIndex.getId() != null) {
               SubfileIndex lockStateParam = new SubfileIndex();
               lockStateParam.setId(subfileIndex.getId());
               lockStateParam.setLockState(subfileIndex.getLockState());
               this.subfileIndexService.updateSubfileIndex(lockStateParam);
            } else {
               Index lockStateParam = new Index();
               lockStateParam.setId(index.getId());
               lockStateParam.setLockState(index.getLockState());
               this.updateIndex(lockStateParam);
            }
         }
      }

      if (StringUtils.isNotEmpty(indexSignVo.getEmrIsUpdate()) && indexSignVo.getEmrIsUpdate().equals("0")) {
         if (subfileIndex != null && subfileIndex.getId() != null) {
            SubfileIndex subfileIndex1 = new SubfileIndex();
            subfileIndex1.setId(subfileIndex.getId());
            subfileIndex1.setMrState(mrState);
            subfileIndex1.setAltDate(updateTime);
            this.subfileIndexService.updateSubfileIndex(subfileIndex1);
         } else {
            Index index1 = new Index();
            index1.setId(index.getId());
            index1.setMrState(mrState);
            index1.setAltPerCode(user.getBasEmployee().getEmplNumber());
            index1.setAltPerName(user.getBasEmployee().getEmplName());
            index1.setAltDate(updateTime);
            this.indexMapper.updateIndex(index1);
         }

         EmrBinary emrBinaryUpdate = new EmrBinary();
         emrBinaryUpdate.setMrFileId(mrFileId);
         emrBinaryUpdate.setAltPerCode(user.getBasEmployee().getEmplNumber());
         emrBinaryUpdate.setAltPerName(user.getBasEmployee().getEmplName());
         emrBinaryUpdate.setAltDate(updateTime);
         this.emrBinaryService.updateEmrBinary(emrBinaryUpdate);
         if (StringUtils.isNotEmpty(indexSignVo.getEmrFileUrl())) {
            this.log.debug("saveIndex-121212121212121212: 病历文件pdf路径：" + indexSignVo.getEmrFileUrl());
            String fileName = index.getId().toString();
            String pdfUrl = Base64Util.createLocalImageMethod(indexSignVo.getEmrFileUrl(), fileName, index.getPatientId());
            Index indexFile = new Index();
            indexFile.setId(index.getId());
            indexFile.setMrServPath(pdfUrl);
            indexFile.setMrLocaPath(indexSignVo.getEmrFileUrl());
            this.updateIndex(indexFile);
         } else {
            this.log.debug("完成状态保存时saveIndex-121212121212121212: 病历文件pdf路径为空");
         }
      }

      this.log.debug("signIndex-6666666666666666666666666666666666666: " + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
      IndexSaveReturnVo indexSaveReturnVo = this.getIndexSaveReturnVo(index, subfileIndex, false);
      indexSaveReturnVo.setMrState(mrState);
      return indexSaveReturnVo;
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public IndexSaveReturnVo signfileForFreeMove(IndexSignVo indexSignVo, Index index, SubfileIndex subfileIndex, SysEmrTypeLevel saveTypeLevel) throws Exception {
      this.log.debug("signIndex-1111111111111111111111111: " + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
      SysUser user = SecurityUtils.getLoginUser().getUser();
      BasEmployee employee = user.getBasEmployee();
      Long mrFileId = subfileIndex != null ? subfileIndex.getId() : index.getId();
      Date signTime = this.commonService.getDbSysdate();
      Date updateTime = subfileIndex != null ? subfileIndex.getAltDate() : index.getAltDate();
      List<ElemSignVo> signElemList = indexSignVo.getSignElemList();
      List<EmrSignRecord> signRecordList = new ArrayList(signElemList.size());
      List<EmrSignData> signDataList = new ArrayList(signElemList.size());
      if (signElemList == null || signElemList.isEmpty()) {
         ElemSignVo signElem = new ElemSignVo();
         signElem.setElemId(mrFileId);
         signElem.setContElemName(mrFileId.toString());
         signElemList.add(signElem);
      }

      if (StringUtils.isNotBlank(indexSignVo.getSignSnameOld())) {
         EmrSignRecord emrSignRecordOld = new EmrSignRecord();
         emrSignRecordOld.setMrFileId(mrFileId);
         emrSignRecordOld.setSignSname(indexSignVo.getSignSnameOld());
         List<EmrSignRecord> emrSignRecordMoveList = this.emrSignRecordService.selectEmrSignRecordForFeeMoveList(emrSignRecordOld);
         List<EmrSignRecord> emrSignRecordList = this.emrSignRecordService.selectEmrSignRecordList(emrSignRecordOld);
         if (CollectionUtils.isNotEmpty(emrSignRecordMoveList) && emrSignRecordMoveList.size() > 0) {
            EmrSignRecord emrSignRecord = (EmrSignRecord)emrSignRecordList.get(0);
            EmrSignData emrSignData = new EmrSignData();
            emrSignData.setId(emrSignRecord.getSignDataId());
            emrSignData.setTypeCd("1");
            emrSignData.setCertType("1");
            emrSignData.setSignFileId(mrFileId.toString());
            emrSignData.setSignerCd(user.getUserName());
            emrSignData.setSignerName(user.getNickName());
            emrSignData.setOldText(indexSignVo.getOldText());
            emrSignData.setSignText(indexSignVo.getSignText());
            emrSignData.setTimeStamp(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, signTime));
            emrSignData.setSignTime(signTime);
            emrSignData.setCertInfo(indexSignVo.getCertInfo());
            emrSignData.setCertSn(indexSignVo.getCertSn());
            emrSignData.setIsValid("1");
            emrSignData.setCreDate(signTime);
            emrSignData.setCrePerCode(user.getUserName());
            emrSignData.setCrePerName(user.getNickName());
            this.emrSignDataService.updateEmrSignDataForMove(emrSignData);
         } else if (CollectionUtils.isNotEmpty(emrSignRecordList) && emrSignRecordList.size() > 0) {
            EmrSignRecord emrSignRecord = (EmrSignRecord)emrSignRecordList.get(0);
            EmrSignData emrSignData = new EmrSignData();
            emrSignData.setId(emrSignRecord.getSignDataId());
            emrSignData.setTypeCd("1");
            emrSignData.setCertType("1");
            emrSignData.setSignFileId(mrFileId.toString());
            emrSignData.setSignerCd(user.getUserName());
            emrSignData.setSignerName(user.getNickName());
            emrSignData.setOldText(indexSignVo.getOldText());
            emrSignData.setSignText(indexSignVo.getSignText());
            emrSignData.setTimeStamp(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, signTime));
            emrSignData.setSignTime(signTime);
            emrSignData.setCertInfo(indexSignVo.getCertInfo());
            emrSignData.setCertSn(indexSignVo.getCertSn());
            emrSignData.setIsValid("1");
            emrSignData.setCreDate(signTime);
            emrSignData.setCrePerCode(user.getUserName());
            emrSignData.setCrePerName(user.getNickName());
            this.emrSignDataService.updateEmrSignData(emrSignData);
         }
      } else {
         for(ElemSignVo signElem : signElemList) {
            EmrSignData emrSignData = new EmrSignData();
            emrSignData.setId(SnowIdUtils.uniqueLong());
            emrSignData.setTypeCd("1");
            emrSignData.setCertType("1");
            emrSignData.setSignFileId(mrFileId.toString());
            emrSignData.setSignerCd(user.getUserName());
            emrSignData.setSignerName(user.getNickName());
            emrSignData.setOldText(indexSignVo.getOldText());
            emrSignData.setSignText(indexSignVo.getSignText());
            emrSignData.setTimeStamp(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, signTime));
            emrSignData.setSignTime(signTime);
            emrSignData.setCertInfo(indexSignVo.getCertInfo());
            emrSignData.setCertSn(indexSignVo.getCertSn());
            emrSignData.setIsValid("1");
            emrSignData.setCreDate(signTime);
            emrSignData.setCrePerCode(user.getUserName());
            emrSignData.setCrePerName(user.getNickName());
            signDataList.add(emrSignData);
            EmrSignRecord emrSignRecord = new EmrSignRecord();
            emrSignRecord.setId(SnowIdUtils.uniqueLong());
            emrSignRecord.setSignDataId(emrSignData.getId());
            emrSignRecord.setMrFileId(mrFileId);
            emrSignRecord.setDocLevelCd(employee.getTitleCode());
            emrSignRecord.setDocLevelName(employee.getTitleName());
            emrSignRecord.setDocCode(employee.getEmplNumber());
            emrSignRecord.setDocName(employee.getEmplName());
            emrSignRecord.setSignPos(String.valueOf(signElem.getElemId()));
            emrSignRecord.setSignTime(signTime);
            emrSignRecord.setSignCancFlag("0");
            emrSignRecord.setCrePerCode(user.getUserName());
            emrSignRecord.setCrePerName(user.getNickName());
            emrSignRecord.setCreDate(signTime);
            emrSignRecord.setSignImagePos(signElem.getSignImageName());
            emrSignRecord.setSignSname(signElem.getContElemName());
            emrSignRecord.setEmrUpdateTime(updateTime);
            signRecordList.add(emrSignRecord);
         }
      }

      this.log.debug("signIndex-222222222222222222222222222222: " + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
      if (!signRecordList.isEmpty()) {
         this.emrSignRecordService.batchAddFreeMoveList(signRecordList);
      }

      if (!signDataList.isEmpty()) {
         this.emrSignDataService.batchAddFreeMoveList(signDataList);
      }

      this.log.debug("signIndex-33333333333333333333333333333: " + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
      String mrState = saveTypeLevel.getMrState();
      String nextDocCod = "";
      this.log.debug("signIndex-6666666666666666666666666666666666666: " + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
      this.emrTaskInfoService.updateTaskInfoByMrfileIdAndDocCdForFreeMove(mrFileId, user.getUserName());
      index.setMrState("08");
      return this.getIndexSaveReturnVo(index, subfileIndex, false);
   }

   public Index selectIndexByPatientId(String patientId) {
      return this.indexMapper.selectIndexByPatientId(patientId);
   }

   public boolean isEmrTypeMainFile(TmplIndex tmplIndex) {
      SysEmrTypeConfigVo sysEmrTypeConfigVo = this.sysEmrTypeConfigService.selectSysEmrTypeConfigByTypeId(tmplIndex.getTempType());
      return "01".equals(tmplIndex.getTempClass()) && sysEmrTypeConfigVo != null && sysEmrTypeConfigVo.getMergeFlag() != 0L;
   }

   private String getNextNodeName(String nextNode) {
      String nextNodeName = "";
      switch (nextNode) {
         case "1":
            nextNodeName = "已完成";
            break;
         case "2":
            nextNodeName = "上级审签";
            break;
         case "3":
            nextNodeName = "其他审签";
      }

      return nextNodeName;
   }

   public IndexFileVo insertFile(IndexVo indexVo) throws Exception {
      IndexFileVo indexFileVo = new IndexFileVo();
      Boolean flag = true;
      String xmlStr = indexVo.getXmlStr();
      String base64Str = indexVo.getBase64Str();
      String upDirPath = "D:/FILE";
      String dirpath = upDirPath + File.separatorChar + indexVo.getPatientId();
      Boolean createXml = WriteFileUtil.createFile(indexVo, dirpath, ".xml");
      if (createXml) {
         String realPath = dirpath + File.separatorChar + indexVo.getMrFileShowName() + ".xml";
         WriteFileUtil.writeText(realPath, xmlStr, flag);
         indexFileVo.setXmlFilePath(realPath);
      }

      Boolean createBase64 = WriteFileUtil.createFile(indexVo, dirpath, ".btn");
      if (createBase64) {
         String realPathBase64 = dirpath + File.separatorChar + indexVo.getMrFileShowName() + ".btn";
         WriteFileUtil.writeText(realPathBase64, base64Str, flag);
         indexFileVo.setBase64Path(realPathBase64);
      }

      return indexFileVo;
   }

   public Boolean insertFtpFile(IndexFileVo indexFileVo, IndexVo indexVo) throws Exception {
      Boolean flag = false;
      Boolean xmlFlag = this.createFile(indexFileVo.getXmlFilePath(), indexVo);
      Boolean base64Flag = this.createFile(indexFileVo.getBase64Path(), indexVo);
      if (xmlFlag && base64Flag) {
         flag = true;
      }

      return flag;
   }

   public IndexNoSignListVo getIndexSignInfo(Index index, SubfileIndex subfileIndex, String xmlStr, IndexVo indexVo, EmrTaskInfo targetTask) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      BasEmployee basEmployee = user.getBasEmployee();
      String currSignUserName = this.currUserByBJCAs(indexVo.getSignFlag(), indexVo.getSignCertSn()).getUserName();
      if (!currSignUserName.equals(user.getUserName())) {
         basEmployee = this.basEmployeeService.selectByEmplNumber(currSignUserName);
      }

      IndexNoSignListVo indexNoSignListVo = new IndexNoSignListVo();
      List<ElemSign> signList = this.selectEmrNoSignList(index, subfileIndex, xmlStr);
      indexNoSignListVo.setSignList(signList);
      String caFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0001");
      String caType = this.sysEmrConfigService.selectSysEmrConfigByKey("0002");
      indexNoSignListVo.setCaFlag(caFlag);
      indexNoSignListVo.setCaType(caType);
      String mrState = index.getMrState();
      TmplIndex tempIndex = this.tmplIndexService.selectTmplIndexById(index.getTempId());
      boolean isMainFile = this.isEmrTypeMainFile(tempIndex);
      if (isMainFile) {
         mrState = subfileIndex.getMrState();
         tempIndex = this.tmplIndexService.selectTmplIndexById(subfileIndex.getTempId());
      }

      SysEmrTypeConfigVo sysEmrTypeConfigVo = this.sysEmrTypeConfigService.selectSysEmrTypeConfigByTypeId(tempIndex.getTempType());
      if (sysEmrTypeConfigVo != null) {
         String reviewedLevel = sysEmrTypeConfigVo.getReviewedLevel();
         String secondDocCode = sysEmrTypeConfigVo.getSecondDocCode();
         String thirdDocCode = sysEmrTypeConfigVo.getThirdDocCode();
         if (!"1".equals(reviewedLevel) && (!"2".equals(reviewedLevel) || !mrState.equals("05")) && (!"3".equals(reviewedLevel) || !mrState.equals("07"))) {
            if ("2".equals(reviewedLevel)) {
               if (mrState.equals("03")) {
                  if (StringUtils.isNotBlank(secondDocCode)) {
                     indexNoSignListVo.setType("3");
                     indexNoSignListVo.setNextDeptName(sysEmrTypeConfigVo.getSecondDeptName());
                     indexNoSignListVo.setNextDeptCode(sysEmrTypeConfigVo.getSecondDeptCode());
                     indexNoSignListVo.setNextDocName(sysEmrTypeConfigVo.getSecondDocName());
                     indexNoSignListVo.setNextDocCode(sysEmrTypeConfigVo.getSecondDocCode());
                  } else {
                     indexNoSignListVo.setType("2");
                  }
               } else {
                  indexNoSignListVo.setType("1");
               }
            } else if (mrState.equals("03")) {
               if (StringUtils.isNotBlank(secondDocCode)) {
                  indexNoSignListVo.setType("3");
                  indexNoSignListVo.setNextDeptName(sysEmrTypeConfigVo.getSecondDeptName());
                  indexNoSignListVo.setNextDeptCode(sysEmrTypeConfigVo.getSecondDeptCode());
                  indexNoSignListVo.setNextDocName(sysEmrTypeConfigVo.getSecondDocName());
                  indexNoSignListVo.setNextDocCode(sysEmrTypeConfigVo.getSecondDocCode());
               } else {
                  indexNoSignListVo.setType("2");
               }
            } else if (mrState.equals("05")) {
               if (StringUtils.isNotBlank(thirdDocCode)) {
                  indexNoSignListVo.setType("3");
                  indexNoSignListVo.setNextDeptName(sysEmrTypeConfigVo.getThirdDeptName());
                  indexNoSignListVo.setNextDeptCode(sysEmrTypeConfigVo.getThirdDeptCode());
                  indexNoSignListVo.setNextDocName(sysEmrTypeConfigVo.getThirdDocName());
                  indexNoSignListVo.setNextDocCode(sysEmrTypeConfigVo.getThirdDocCode());
               } else {
                  indexNoSignListVo.setType("2");
               }
            } else {
               indexNoSignListVo.setType("1");
            }
         } else {
            indexNoSignListVo.setType("1");
         }
      }

      String doctorType = "other";
      indexNoSignListVo.setDoctorType(doctorType);
      if (!"22".equals(index.getFreeMoveType())) {
         String titleCode = basEmployee.getTitleCode();
         String arcDocCd = this.sysEmrConfigService.selectSysEmrConfigByKey("0013");
         String attDocCd = this.sysEmrConfigService.selectSysEmrConfigByKey("0012");
         String resDocCd = this.sysEmrConfigService.selectSysEmrConfigByKey("0014");
         doctorType = resDocCd.contains(titleCode) ? "resDoc" : doctorType;
         doctorType = attDocCd.contains(titleCode) ? "attDoc" : doctorType;
         doctorType = arcDocCd.contains(titleCode) ? "arcDoc" : doctorType;
         indexNoSignListVo.setDoctorType(doctorType);
      }

      indexNoSignListVo.setUperDoct(subfileIndex != null && subfileIndex.getId() != null ? subfileIndex.getUperDoct() : index.getUperDoct());
      indexNoSignListVo.setUperDoctName(subfileIndex != null && subfileIndex.getId() != null ? subfileIndex.getUperDoctName() : index.getUperDoctName());
      if (caFlag.equals("2")) {
         BasCertInfo basCertInfo = this.basCertInfoService.selectBasCertInfoByEmployeenumber(SecurityUtils.getUsername());
         if (basCertInfo != null) {
            String certPic = basCertInfo.getCertPic();
            indexNoSignListVo.setCertPic(certPic);
         }
      }

      if (indexNoSignListVo.getType().equals("1")) {
         indexNoSignListVo.setNextDeptCd(targetTask != null ? targetTask.getDutyDeptCd() : user.getDept().getDeptCode());
         indexNoSignListVo.setNextDeptName2(targetTask != null ? targetTask.getDutyDeptName() : user.getDept().getDeptName());
         indexNoSignListVo.setNextDocCd(targetTask != null ? targetTask.getDocCd() : basEmployee.getEmplNumber());
         indexNoSignListVo.setNextDocName2(targetTask != null ? targetTask.getDocName() : basEmployee.getEmplName());
      }

      return indexNoSignListVo;
   }

   public List getIndexHFInfo(IndexHFInfoVo indexHFInfoVo, VisitinfoVo visitinfoVo) throws Exception {
      List<IndexHFInfoResultVo> resultVoList = new ArrayList(1);
      List<IndexHFInfoPageVo> indexHFInfoPageVoList = this.getIndexHFInfoResultVo(indexHFInfoVo.getXmlStr(), visitinfoVo.getPatientId());
      String deptContElemName = "dept_name";
      String bedNoContElemName = "bed_no";
      List<DeptBEDateVo> deptBEDateVoList = this.transinfoService.selectDetpDate(indexHFInfoVo.getPatientId());
      deptBEDateVoList = (List)deptBEDateVoList.stream().sorted(Comparator.comparing(DeptBEDateVo::getEndDate)).collect(Collectors.toList());
      List<SubFileIndexHFInfoVo> tempList = (List)indexHFInfoVo.getSubFileIndexList().stream().sorted(Comparator.comparing(SubFileIndexHFInfoVo::getRecoDate)).collect(Collectors.toList());

      for(int j = 1; j < tempList.size(); ++j) {
         SubFileIndexHFInfoVo subFileIndexHFInfoVo = (SubFileIndexHFInfoVo)tempList.get(j);
         DeptBEDateVo deptBEDateVoCurn = null;

         for(int i = deptBEDateVoList.size() - 1; i > -1; --i) {
            DeptBEDateVo deptBEDateVo = (DeptBEDateVo)deptBEDateVoList.get(i);
            if (deptBEDateVo.getEndDate().compareTo(subFileIndexHFInfoVo.getRecoDate()) <= 0) {
               deptBEDateVoCurn = deptBEDateVo;
               break;
            }
         }

         IndexHFInfoResultVo indexHFInfoResultVo = new IndexHFInfoResultVo();
         List<IndexHFInfoPageVo> infoPageVoListTemp = new ArrayList(indexHFInfoPageVoList.size());
         if (deptBEDateVoCurn != null) {
            for(IndexHFInfoPageVo indexHFInfoPageVo : indexHFInfoPageVoList) {
               IndexHFInfoPageVo infoPageVoTemp = new IndexHFInfoPageVo();
               BeanUtils.copyProperties(indexHFInfoPageVo, infoPageVoTemp);
               if (infoPageVoTemp.getValue().equals(deptContElemName)) {
                  infoPageVoTemp.setText(deptBEDateVoCurn.getDeptName());
               }

               if (infoPageVoTemp.getValue().equals(bedNoContElemName)) {
                  infoPageVoTemp.setText(deptBEDateVoCurn.getBedNo());
               }

               infoPageVoListTemp.add(infoPageVoTemp);
            }

            indexHFInfoResultVo.setIndexHFInfoPageVoList(infoPageVoListTemp);
            indexHFInfoResultVo.setRegionName(((SubFileIndexHFInfoVo)tempList.get(0)).getRegionName());
            resultVoList.add(indexHFInfoResultVo);
         }
      }

      return resultVoList;
   }

   public List getIndexHFInfoResultVo(String xmlStr, String patientId) throws Exception {
      String sourceColuStrs = this.sysEmrConfigService.selectSysEmrConfigByKey("0048");
      List<String> sourceColuList = (List<String>)(StringUtils.isNotBlank(sourceColuStrs) ? Arrays.asList(sourceColuStrs.split(",")) : new ArrayList(1));
      List<IndexHFInfoPageVo> indexHFInfoPageVoList = new ArrayList(sourceColuList.size());
      String editorType = this.sysEmrConfigService.selectSysEmrConfigByKey("0000");
      List<ElemAttri> headElemMacro = XmlElementParseUtil.getHeaderElementListFromXmlStr(xmlStr, editorType);
      List<ElemMacro> elemMacroList = new ArrayList(1);

      for(ElemAttri elemAttri : headElemMacro) {
         ElemMacro elemMacro = new ElemMacro();
         String elemStr = elemAttri.getElemAttri();
         JSONObject jsonObject = ElemVoToElemUtil.getElemJSONObject(elemStr);
         JSONArray elems = (JSONArray)jsonObject.get("elems");
         JSONObject elemsTemp = (JSONObject)elems.get(0);
         JSONObject elem = (JSONObject)elemsTemp.get("elem");
         String sourceTable = (String)elem.get("sourceTable");
         String sourceColu = (String)elem.get("sourceColu");
         elemMacro.setSourTable(sourceTable);
         elemMacro.setSourColu(sourceColu);
         elemMacro.setContElemName(elemAttri.getContElemName());
         elemMacroList.add(elemMacro);
      }

      Map<String, List<ElemMacro>> map = (Map)elemMacroList.stream().collect(Collectors.groupingBy((t) -> t.getSourTable()));

      for(String key : map.keySet()) {
         ElemMacroVo elemMacroVo = new ElemMacroVo();
         elemMacroVo.setSourTable(key);
         elemMacroVo.setPatientId(patientId);
         Map<String, Object> macroMap = this.elemMacroService.selectElemMacroInfoByTable(elemMacroVo);

         for(ElemMacro macro : (List)map.get(key)) {
            String macroStr = "";
            macroStr = macroMap.get(macro.getSourColu().toUpperCase()) != null ? macroMap.get(macro.getSourColu().toUpperCase()).toString() : macroStr;
            String contElemName = macro.getContElemName();
            IndexHFInfoPageVo indexHFInfoPageVo = new IndexHFInfoPageVo("sourceColu");
            indexHFInfoPageVo.setValue(macro.getSourColu());
            indexHFInfoPageVo.setText(macroStr);
            indexHFInfoPageVo.setContElemName(contElemName);
            indexHFInfoPageVoList.add(indexHFInfoPageVo);
         }
      }

      return indexHFInfoPageVoList;
   }

   public Boolean createFile(String realPath, IndexVo indexVo) throws Exception {
      File file = new File(realPath);
      String fileName = file.getName();
      InputStream inputStream = new FileInputStream(file);
      String pathName = indexVo.getPatientId();
      return FtpUtil.uploadFile(pathName, fileName, inputStream);
   }

   public void emrCancelEdit(IndexVo indexVo) throws Exception {
      EditState editState = this.editStateService.selectEditStateByEmrId(indexVo.getId());
      if (editState != null) {
         EditState editStateUpdate = new EditState();
         editStateUpdate.setId(editState.getId());
         editStateUpdate.setEndTime(this.commonService.getDbSysdate());
         editStateUpdate.setDeitState("0");
         this.editStateService.updateEditState(editStateUpdate);
      }

   }

   public List getIndexSecLevelByEmplNumber(String emplNumber, String patientId) throws Exception {
      List<String> secLevelList = new ArrayList(1);
      List<String> resSecLevelList = new ArrayList(1);
      Boolean flag = this.visitinfoService.selectIsPrivacyLevel(patientId);
      if (flag) {
         resSecLevelList.add("1");
         resSecLevelList.add("2");
         resSecLevelList.add("3");
      } else {
         BasEmployee employee = this.basEmployeeService.selectByEmplNumber(emplNumber);
         if (employee != null) {
            BasEmrAcceAuthor basEmrAcceAuthor = this.basEmrAcceAuthorService.selectBasEmrAcceAuthorByObjectId(employee.getId().toString());
            if (basEmrAcceAuthor != null) {
               secLevelList.add(basEmrAcceAuthor.getAuthorLevel());
            }
         }

         List<SysEmployeeRoleDept> employeeRoleDeptList = this.employeeRoleDeptService.selectByUserId(employee.getId());
         if (employeeRoleDeptList != null && !employeeRoleDeptList.isEmpty()) {
            List<String> roleIds = (List)employeeRoleDeptList.stream().map((t) -> t.getRoleId().toString()).collect(Collectors.toList());
            List<BasEmrAcceAuthor> basEmrAcceAuthorList = this.basEmrAcceAuthorService.selectBasEmrAcceAuthorByObjectIds(roleIds);
            if (basEmrAcceAuthorList != null && !basEmrAcceAuthorList.isEmpty()) {
               secLevelList.addAll((Collection)basEmrAcceAuthorList.stream().map((t) -> t.getAuthorLevel()).collect(Collectors.toList()));
            }
         }

         secLevelList = (List)secLevelList.stream().distinct().collect(Collectors.toList());
         if (resSecLevelList.size() == 0 && secLevelList.contains("3")) {
            resSecLevelList.add("1");
            resSecLevelList.add("2");
            resSecLevelList.add("3");
         }

         if (resSecLevelList.size() == 0 && secLevelList.contains("2")) {
            resSecLevelList.add("1");
            resSecLevelList.add("2");
         }

         if (resSecLevelList.size() == 0 && secLevelList.contains("1")) {
            resSecLevelList.add("1");
         }
      }

      return resSecLevelList;
   }

   public void updateIndexLockState(Index index) {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      index.setAltPerCode(sysUser.getUserName());
      index.setAltPerName(sysUser.getNickName());
      this.indexMapper.updateIndexLockState(index);
   }

   public List getElemMacroListFromXml(String xmlStr) throws Exception {
      String editorType = this.sysEmrConfigService.selectSysEmrConfigByKey("0000");
      List<ElemMacro> elemMacroList = XmlElementParseUtil.getElemMacroListFromXml(xmlStr, editorType);
      List<String> list = elemMacroList != null && !elemMacroList.isEmpty() ? (List)elemMacroList.stream().map((t) -> t.getContElemName()).collect(Collectors.toList()) : null;
      return list;
   }

   public List getQcErrorList(IndexSaveVo indexSaveVo) throws Exception {
      List<EmrQcListVo> emrQcListVoList = indexSaveVo.getEmrQcListVoList();
      List<EmrQcListVo> qcExcepationList = new ArrayList();
      if (emrQcListVoList != null && emrQcListVoList.size() > 0) {
         qcExcepationList = (List)emrQcListVoList.stream().filter((s) -> StringUtils.isNotEmpty(s.getTreatDesc()) && s.getQcState().equals("1")).collect(Collectors.toList());
      }

      if (indexSaveVo.getId() != null) {
         Long id = indexSaveVo.getSubFileIndexId() == null ? indexSaveVo.getId() : indexSaveVo.getSubFileIndexId();
         EmrQcListVo emrQcListVo = new EmrQcListVo();
         List<String> qcSectionList = new ArrayList();
         qcSectionList.add("01");
         qcSectionList.add("04");
         emrQcListVo.setQcSectionList(qcSectionList);
         emrQcListVo.setMrFileId(id.toString());
         List<EmrQcListVo> dbQcList = this.emrQcListService.selectEmrQcListList(emrQcListVo);
         if (dbQcList != null && !dbQcList.isEmpty()) {
            List<Long> qcIdList = qcExcepationList != null && qcExcepationList.size() > 0 ? (List)((List)qcExcepationList.stream().filter((s) -> s.getId() != null).collect(Collectors.toList())).stream().map((s) -> s.getId()).collect(Collectors.toList()) : null;

            for(EmrQcListVo emrQcListVo1 : dbQcList) {
               if (qcIdList == null || !qcIdList.contains(emrQcListVo1.getId())) {
                  qcExcepationList.add(emrQcListVo1);
               }
            }
         }
      }

      return qcExcepationList;
   }

   public List emrQualityCheck(IndexSaveVo indexSaveVo, List elemAttriList, List qcExcepationList) throws Exception {
      List<String> kieTemplates = new ArrayList(1);
      List<EmrQcListVo> resultList = new ArrayList(1);
      String emrTypeCode = indexSaveVo.getEmrTypeCode();
      String emrTypeName = this.sysDictDataService.selectDictLabel("s004", emrTypeCode);
      indexSaveVo.setEmrTypeName(emrTypeName);
      VisitinfoPersonalVo visitinfo = this.visitinfoService.selectVisitinfoPersonalById(indexSaveVo.getPatientId());
      if (visitinfo != null) {
         Date nowDate = new Date();
         List<ElemAttriVo> qcElemList = new ArrayList(elemAttriList.size());

         for(ElemAttriVo elemAttriVo : elemAttriList) {
            ElemAttriVo qcElem = new ElemAttriVo();
            qcElem.setContElemName(elemAttriVo.getContElemName());
            qcElem.setElemId(elemAttriVo.getElemId());
            qcElem.setElemName(elemAttriVo.getElemName());
            qcElem.setElemConText(elemAttriVo.getElemConText());
            qcElem.setContType(elemAttriVo.getContType());
            if (elemAttriVo.getContType() != null && elemAttriVo.getContType().equals("2")) {
               try {
                  qcElem.setElemConTextInt(elemAttriVo.getElemConText() == null ? null : Double.parseDouble(elemAttriVo.getElemConText()));
               } catch (NumberFormatException var21) {
                  qcElem.setElemConTextInt((Double)null);
               }
            }

            qcElem.setNowDate(nowDate);
            qcElem.setInhosTime(visitinfo.getInhosTime());
            qcElem.setOutTime(visitinfo.getOutTime());
            qcElemList.add(qcElem);
            this.log.info("病历保存质控：=====   " + qcElem.getElemId() + "  " + qcElem.getContElemName() + "  " + qcElem.getElemConText());
         }

         indexSaveVo.setQcElemList(qcElemList);
         String[] age = new String[]{"1", "2", "3", "4"};
         Long ageY = visitinfo.getAgeY();
         if (ageY != null) {
            if (ageY > 50L) {
               age[3] = age[age.length - 1];
            } else if (ageY > 14L) {
               age[2] = age[age.length - 1];
            } else {
               age[1] = age[age.length - 1];
            }
         } else if (visitinfo.getAgeM() == null && visitinfo.getAgeD() <= 28L) {
            age[0] = age[age.length - 1];
         } else {
            age[1] = age[age.length - 1];
         }

         age = (String[])Arrays.copyOf(age, age.length - 1);
         String sex = "1".equals(visitinfo.getSexCd()) ? "2" : "1";
         List<Integer> ruleTypeList = new ArrayList();
         ruleTypeList.add(CommonConstants.QC.RULE_TYPE_CODE_1);
         ruleTypeList.add(CommonConstants.QC.RULE_TYPE_CODE_2);
         ruleTypeList.add(CommonConstants.QC.RULE_TYPE_CODE_3);
         ruleTypeList.add(CommonConstants.QC.RULE_TYPE_CODE_4);
         ruleTypeList.add(CommonConstants.QC.RULE_TYPE_CODE_5);
         ruleTypeList.add(CommonConstants.QC.RULE_TYPE_CODE_6);
         List<QcRulesVo> rulesList = this.indexMapper.selectQcRulesList(ruleTypeList, indexSaveVo.getEmrTypeCode());

         for(QcRulesVo qcRulesVo : (List)rulesList.stream().filter((s) -> s.getRuleType() != CommonConstants.QC.RULE_TYPE_CODE_5).collect(Collectors.toList())) {
            if (qcRulesVo.getRuleType() == CommonConstants.QC.RULE_TYPE_CODE_1) {
               if (qcRulesVo.getRuleClass().equals("1")) {
                  for(int i = 0; i < age.length; ++i) {
                     kieTemplates.add(qcRulesVo.getId() + "_" + "1" + "_" + age[i] + ".drl");
                  }
               } else {
                  kieTemplates.add(qcRulesVo.getId() + "_" + "2" + "_" + sex + ".drl");
               }
            } else {
               kieTemplates.add(qcRulesVo.getId() + ".drl");
            }
         }

         List<String> kieTemplatesRes = new ArrayList(1);

         for(String ruleFileName : kieTemplates) {
            File checkFileOld = new File(this.path + "/" + ruleFileName);
            if (checkFileOld.exists()) {
               kieTemplatesRes.add(ruleFileName);
               this.log.info("规则文件名称：=====   " + ruleFileName);
            }
         }

         IndexSaveVo ruleParam = new IndexSaveVo();
         String fileName = StringUtils.isNotBlank(indexSaveVo.getSubIndexName()) ? indexSaveVo.getSubIndexName() : indexSaveVo.getIndexName();
         ruleParam.setMrFileShowName(fileName);
         ruleParam.setQcElemList(indexSaveVo.getQcElemList());
         if (kieTemplatesRes != null && kieTemplatesRes.size() > 0) {
            this.kSession = this.kieTemplate.getKieSession((String[])kieTemplatesRes.toArray(new String[kieTemplatesRes.size()]));
            this.kSession.insert(ruleParam);
            this.kSession.fireAllRules();
            this.kSession.delete(this.kSession.getFactHandle(ruleParam));
            this.kSession.dispose();
            this.getRelaFlawList(ruleParam);
            ruleParam.setPatientId(indexSaveVo.getPatientId());
            ruleParam.setEmrTypeCode(indexSaveVo.getEmrTypeCode());
            ruleParam.setEmrTypeName(indexSaveVo.getEmrTypeName());
            resultList = this.emrQcRecordService.getEmrCheckResultQcList(ruleParam, rulesList, qcExcepationList);
         }
      }

      return resultList;
   }

   public void getRelaFlawList(IndexSaveVo indexSaveVo) throws Exception {
      List<QcRuleConRela> qcRuleConRelaList = this.qcRuleConRelaService.selectQcRuleConRela(indexSaveVo.getEmrTypeCode());
      if (qcRuleConRelaList != null && qcRuleConRelaList.size() > 0) {
         for(QcRuleConRela qcRuleConRela : qcRuleConRelaList) {
            String elemA = "";
            String elemB = "";
            ElemAttriVo elemAttriVoA = new ElemAttriVo();
            new ElemAttriVo();

            for(ElemAttriVo elemAttriVo : indexSaveVo.getQcElemList()) {
               if (StringUtils.isNotEmpty(elemAttriVo.getElemConText())) {
                  if (elemAttriVo.getElemId() != null && elemAttriVo.getElemId().toString().equals(qcRuleConRela.getEleId1())) {
                     elemA = elemAttriVo.getElemConText();
                     elemAttriVoA = elemAttriVo;
                  }

                  if (elemAttriVo.getElemId() != null && elemAttriVo.getElemId().toString().equals(qcRuleConRela.getEleId2())) {
                     elemB = elemAttriVo.getElemConText();
                  }
               }

               if (StringUtils.isNotEmpty(elemA) && StringUtils.isNotEmpty(elemB)) {
                  break;
               }
            }

            if (!StringUtils.isEmpty(elemA) && !StringUtils.isEmpty(elemB)) {
               JSONObject jsonObject = JSONObject.parseObject(qcRuleConRela.getComChar());
               String comChar = jsonObject.get("operator").toString();
               boolean flag = true;
               switch (comChar) {
                  case "包含":
                     flag = elemA.contains(elemB);
                     break;
                  case "不包含":
                     flag = !elemA.contains(elemB);
                     break;
                  default:
                     if (elemAttriVoA.getContType().equals("2")) {
                        Double A = Double.parseDouble(elemA);
                        Double B = Double.parseDouble(elemB);
                        if (comChar.equals("等于")) {
                           flag = A != B;
                        } else if (comChar.equals("大于")) {
                           flag = !(A > B);
                        } else if (comChar.equals("大于等于")) {
                           flag = !(A >= B);
                        } else if (comChar.equals("小于")) {
                           flag = !(A < B);
                        } else if (comChar.equals("小于等于")) {
                           flag = !(A <= B);
                        } else if (comChar.equals("不等于")) {
                           flag = A == B;
                        }
                     }

                     if (elemAttriVoA.getContType().equals("7")) {
                        try {
                           Date dateStr1 = DateUtils.parseDate(elemA);
                           Date dateStr2 = DateUtils.parseDate(elemB);
                           if (dateStr1 != null && dateStr2 != null) {
                              if (comChar.equals("等于")) {
                                 flag = DroolsUtil.dateCompareTo(elemA, elemB) != 0;
                              } else if (comChar.equals("大于")) {
                                 flag = DroolsUtil.dateCompareTo(elemA, elemB) <= 0;
                              } else if (comChar.equals("大于等于")) {
                                 flag = DroolsUtil.dateCompareTo(elemA, elemB) < 0;
                              } else if (comChar.equals("小于")) {
                                 flag = DroolsUtil.dateCompareTo(elemA, elemB) >= 0;
                              } else if (comChar.equals("小于等于")) {
                                 flag = DroolsUtil.dateCompareTo(elemA, elemB) > 0;
                              } else if (comChar.equals("不等于")) {
                                 flag = DroolsUtil.dateCompareTo(elemA, elemB) == 0;
                              }
                           } else {
                              this.log.warn("病历质控时间比较处-有空值-有不包含的时间格式：elemA：" + elemA + "：elemB：" + elemB);
                           }
                        } catch (Exception var16) {
                           this.log.warn("病历质控时间比较处-异常：elemA：" + elemA + "：elemB：" + elemB);
                        }
                     }
               }

               if (!flag) {
                  QcRuleResultVo qcRuleResultVo = new QcRuleResultVo(qcRuleConRela.getId().toString(), elemAttriVoA.getElemId() + "|" + elemAttriVoA.getElemName());
                  qcRuleResultVo.setContElemName(elemAttriVoA.getContElemName());
                  qcRuleResultVo.setElemName(elemAttriVoA.getElemName());
                  indexSaveVo.getResultVoList().add(qcRuleResultVo);
               }
            }
         }
      }

   }

   public void saveEmrCheckFlaw(IndexSaveVo indexSaveVo) throws Exception {
      this.log.debug("saveIndex-1212121212121212-start: " + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      VisitinfoPersonalVo personalVo = this.visitinfoService.selectVisitinfoPersonalById(indexSaveVo.getPatientId());
      EmrQcRecord emrQcRecord = new EmrQcRecord();
      emrQcRecord.setId(SnowIdUtils.uniqueLong());
      emrQcRecord.setPatientId(personalVo.getPatientId());
      emrQcRecord.setPatientName(personalVo.getPatientName());
      emrQcRecord.setSex(personalVo.getSexCd());
      emrQcRecord.setInpNo(personalVo.getInpNo());
      emrQcRecord.setInDeptCd(personalVo.getInDeptCd());
      emrQcRecord.setInDeptName(personalVo.getInDeptName());
      emrQcRecord.setQcSection("01");
      emrQcRecord.setQcdoctCd("sys");
      emrQcRecord.setQcdoctName("系统");
      emrQcRecord.setCrePerCode(basEmployee.getEmplNumber());
      emrQcRecord.setCrePerName(basEmployee.getEmplName());
      this.log.debug("saveIndex-1212121212121212-end: " + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
      if (indexSaveVo.getEmrQcListVoList() != null && !indexSaveVo.getEmrQcListVoList().isEmpty()) {
         List<EmrQcList> insertEmrQcListList = new ArrayList(indexSaveVo.getEmrQcListVoList().size());
         List<EmrQcCommRecord> insertQcCommRecords = new ArrayList();

         for(EmrQcListVo emrQcListVo : indexSaveVo.getEmrQcListVoList()) {
            Long id = SnowIdUtils.uniqueLong();
            if (emrQcListVo.getId() == null) {
               emrQcListVo.setMainId(emrQcRecord.getId());
               emrQcListVo.setId(id);
               emrQcListVo.setMrFileId(indexSaveVo.getId().toString());
               insertEmrQcListList.add(emrQcListVo);
               if (emrQcListVo.getQcCommRecordList() != null && !emrQcListVo.getQcCommRecordList().isEmpty()) {
                  this.log.debug("saveIndex-1515151515151515: " + emrQcListVo.getQcCommRecordList() + "######" + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));

                  for(EmrQcCommRecord emrQcCommRecord : emrQcListVo.getQcCommRecordList()) {
                     if (emrQcCommRecord.getId() == null) {
                        emrQcCommRecord.setId(SnowIdUtils.uniqueLong());
                        emrQcCommRecord.setMainId(id);
                        emrQcCommRecord.setMrFileId(indexSaveVo.getId().toString());
                        emrQcCommRecord.setCrePerCode(basEmployee.getEmplNumber());
                        emrQcCommRecord.setCrePerName(basEmployee.getEmplName());
                        emrQcCommRecord.setFedbPerName(basEmployee.getEmplName());
                        emrQcCommRecord.setFedbPerId(basEmployee.getEmplNumber());
                        insertQcCommRecords.add(emrQcCommRecord);
                     }
                  }
               }
            }
         }

         if (!insertEmrQcListList.isEmpty()) {
            this.emrQcListService.insertEmrQcLists(insertEmrQcListList);
         }

         if (!insertQcCommRecords.isEmpty()) {
            this.emrQcCommRecordService.insertList(insertQcCommRecords);
         }
      }

      this.log.debug("saveIndex-1515151515151515: " + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
      this.emrQcRecordMapper.insertEmrQcRecord(emrQcRecord);
   }

   public List selectEmrUnFinishList(String patientId) throws Exception {
      List<IndexVo> indexVoResultList = new ArrayList(1);
      List<IndexVo> indexVoList = this.indexMapper.selectEmrUnFinishList(patientId);
      if (indexVoList != null && indexVoList.size() > 0) {
         for(IndexVo indexVo : indexVoList) {
            String state = indexVo.getMrState();
            if (!state.equals("05") && !state.equals("07")) {
               if (state.equals("03")) {
                  indexVo.setDutyDocName(indexVo.getCrePerName());
               } else if (state.equals("01")) {
                  indexVo.setDutyDocName("-");
               } else {
                  indexVo.setDutyDocName(indexVo.getUperDoctName());
               }

               indexVoResultList.add(indexVo);
            }
         }
      }

      return indexVoResultList;
   }

   public List selectEmrUnUpdateList(String patientId) throws Exception {
      List<String> qcStateList = new ArrayList();
      qcStateList.add("0");
      qcStateList.add("3");
      String recordBz = "1";
      List<EmrQcList> emrQcListList = this.emrQcListService.selectByPatientsAndQcStateAndRecordBz(patientId, qcStateList, (Long)null, recordBz);
      List<EmrQcRecord> emrQcRecordList = this.emrQcRecordMapper.selectEmrQcRecordByPatientId(patientId);
      List<EmrQcListVo> resultList = new ArrayList();
      List<IndexVo> indexVoList = this.indexMapper.selectEmrUnUpdateList(patientId);
      if (emrQcListList != null && emrQcListList.size() > 0) {
         Map<String, List<IndexVo>> emrMap = (Map)indexVoList.stream().collect(Collectors.groupingBy((s) -> s.getId().toString()));
         Map<String, List<EmrQcList>> map = (Map)emrQcListList.stream().filter((s) -> s.getMrFileId() != null).collect(Collectors.groupingBy((s) -> s.getMrFileId()));
         Map<String, List<EmrQcRecord>> recordMap = (Map)emrQcRecordList.stream().collect(Collectors.groupingBy((s) -> s.getId().toString()));

         for(String mrFileId : map.keySet()) {
            List<EmrQcList> qclist = (List)map.get(mrFileId);
            List<IndexVo> indexVoList1 = (List)emrMap.get(mrFileId);
            if (indexVoList1 != null && !indexVoList1.isEmpty()) {
               EmrQcListVo emrQcListVo = new EmrQcListVo();
               BeanUtils.copyProperties(qclist.get(0), emrQcListVo);
               emrQcListVo.setQcNum(qclist.size());
               emrQcListVo.setMrFileShowName(((IndexVo)indexVoList1.get(0)).getMrFileShowName());
               List<EmrQcRecord> emrQcRecordList1 = (List)recordMap.get(((EmrQcList)qclist.get(0)).getMainId().toString());
               emrQcListVo.setLimitHours(((EmrQcRecord)emrQcRecordList1.get(0)).getLimitHours() == null ? "即时" : ((EmrQcRecord)emrQcRecordList1.get(0)).getLimitHours() + "小时内");
               resultList.add(emrQcListVo);
            }
         }

         Map<String, List<EmrQcList>> emrQcListQxMap = (Map)emrQcListList.stream().filter((s) -> StringUtils.isBlank(s.getMrFileId())).collect(Collectors.groupingBy((s) -> s.getMrType()));

         for(String mrType : emrQcListQxMap.keySet()) {
            List<EmrQcList> qcList = (List)emrQcListQxMap.get(mrType);
            if (qcList != null && !qcList.isEmpty()) {
               EmrQcListVo emrQcListVo = new EmrQcListVo();
               BeanUtils.copyProperties(qcList.get(0), emrQcListVo);
               emrQcListVo.setQcNum(qcList.size());
               resultList.add(emrQcListVo);
            }
         }
      }

      return resultList;
   }

   public List selectUnSignEmrList(String patientId) throws Exception {
      List<BackLogVo> result = new ArrayList();
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      String mrHpUseFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0086");
      List<IndexVo> indexVoList = this.indexMapper.selectUnSignEmrList(sysUser.getBasEmployee().getEmplNumber(), patientId);
      List<ImpDoctorTemp> list = this.iImpDoctorTempService.selectDocTempImpowerList(sysUser.getBasEmployee().getEmplNumber(), sysUser.getDept().getDeptCode());
      List<String> patientIdList = (List<String>)(list == null ? new ArrayList(1) : (List)list.stream().map((s) -> s.getPatientId()).collect(Collectors.toList()));
      if (indexVoList != null && !indexVoList.isEmpty()) {
         for(IndexVo indexVo : indexVoList) {
            if (!StringUtils.isNotBlank(mrHpUseFlag) || !mrHpUseFlag.equals("0") || !indexVo.getMrTypeName().equals("病案首页")) {
               String bedNo = indexVo.getBedNo() == null ? "" : indexVo.getBedNo();
               String patientName = indexVo.getPatientName() == null ? "" : indexVo.getPatientName();
               String sex = indexVo.getSex() == null ? "" : indexVo.getSex();
               String caseNo = indexVo.getRecordNo() == null ? "" : indexVo.getRecordNo();
               String mrTypeName = indexVo.getMrTypeName() == null ? "" : indexVo.getMrTypeName();
               String resDocName = indexVo.getResDocName() == null ? "" : indexVo.getResDocName();
               String resDocCd = indexVo.getResDocCd() == null ? "" : indexVo.getResDocCd();
               BackLogVo backLogVo = new BackLogVo();
               backLogVo.setMessageStr("【" + bedNo + "床 " + patientName + " " + sex + " " + caseNo + "】的【" + mrTypeName + "】已被住院医师【" + resDocName + " " + resDocCd + "】流转给您审签,请及时完成");
               if (sysUser.getDept().getDeptCode().equals(indexVo.getDeptCd())) {
                  backLogVo.setDeptFlag(CommonConstants.BOOL_TRUE);
               } else if (patientIdList.contains(indexVo.getPatientId())) {
                  backLogVo.setDeptFlag(CommonConstants.BOOL_TRUE);
               } else {
                  backLogVo.setDeptFlag(CommonConstants.BOOL_TRUE);
               }

               backLogVo.setPatientMainId(indexVo.getPatientMainId());
               backLogVo.setPatientId(indexVo.getPatientId());
               backLogVo.setInpNo(indexVo.getInpNo());
               backLogVo.setEmrFileId(indexVo.getId());
               backLogVo.setMainId(indexVo.getMainId());
               backLogVo.setType("2");
               backLogVo.setMrType(indexVo.getMrType());
               result.add(backLogVo);
            }
         }
      }

      return result;
   }

   public List selectPatIndexList(PatEventVo patEventVo) throws Exception {
      return this.indexMapper.selectPatIndexList(patEventVo);
   }

   public void updatePrintInfo(Long id) throws Exception {
      this.indexMapper.updatePrintInfo(id);
   }

   public List selectOvertimeWriteList(String patientId) throws Exception {
      return this.indexMapper.selectOvertimeWriteList(patientId);
   }

   public void updateLastFinishTimeList(List list) throws Exception {
      this.indexMapper.updateLastFinishTimeList(list);
   }

   public Boolean betweenInhosTimeAndNow(String patientId, Date recoDate) throws Exception {
      boolean flag = false;
      VisitinfoVo visitinfoVo = this.visitinfoService.selectVisitinfoByPatientId(patientId);
      if (visitinfoVo != null && visitinfoVo.getInhosTime().compareTo(recoDate) < 0 && this.commonService.getDbSysdate().compareTo(recoDate) >= 0) {
         flag = true;
      }

      return flag;
   }

   public JSONObject setUserInfo() throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      JSONObject userInfo = new JSONObject();
      userInfo.put("UserID", sysUser.getBasEmployee().getEmplNumber());
      userInfo.put("UserName", sysUser.getBasEmployee().getEmplName() + "(" + sysUser.getBasEmployee().getEmplNumber() + ")");
      userInfo.put("UserUnit", sysUser.getDept().getDeptCode());
      userInfo.put("Department", sysUser.getDept().getDeptName());
      userInfo.put("TrackColor", "ff0000");
      BasDoctGroupMember basDoctGroupMember = this.basDoctGroupMemberService.selectGroupDocInfo(sysUser.getBasEmployee().getEmplNumber(), sysUser.getHospital().getOrgCode(), sysUser.getDept().getDeptCode());
      userInfo.put("TrackMark", basDoctGroupMember == null ? "0" : (StringUtils.isNotBlank(basDoctGroupMember.getDocLevel()) ? basDoctGroupMember.getDocLevel() : "0"));
      return userInfo;
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public IndexSaveReturnVo saveConsIndex(TdCaConsApplyVo tdCaConsApplyVo, HttpServletRequest request) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      Date updateTime = this.commonService.getDbSysdate();
      SysUser updateUser = SecurityUtils.getLoginUser().getUser();
      Boolean addFlag = false;
      Index index = new Index();
      if (tdCaConsApplyVo.getMrFileId() == null) {
         Long id = SnowIdUtils.uniqueLong();
         addFlag = true;
         TmplIndex tmplIndex = this.tmplIndexService.selectTmplIndexById(tdCaConsApplyVo.getTempId());
         VisitinfoVo visitinfo = this.visitinfoService.selectVisitinfoByPatientId(tdCaConsApplyVo.getPatientId());
         Personalinfo personalinfo = this.personalinfoService.selectPersonalinfoById(tdCaConsApplyVo.getPatientId());
         GrantOutDoctor grantOutDoctor = this.grantOutDoctorService.selectGrantOutDoctorBySubNo(user.getUserName());
         String tempClassName = this.sysDictDataService.selectDictLabel("s003", tmplIndex.getTempClass());
         index.setId(id);
         tdCaConsApplyVo.setMrFileId(id);
         index.setOrgCd(user.getHospital().getOrgCode());
         index.setOrgName(user.getHospital().getOrgName());
         index.setPatientId(tdCaConsApplyVo.getPatientId());
         SysEmrTypeConfigVo sysEmrTypeConfigVo = this.sysEmrTypeConfigService.selectSysEmrTypeConfigByTypeId(tmplIndex.getTempType());
         index.setMrType("01".equals(tmplIndex.getTempClass()) && sysEmrTypeConfigVo != null && sysEmrTypeConfigVo.getMergeFlag() != 0L ? "MAINFILE" : tmplIndex.getTempType());
         index.setMrFileShowName(tdCaConsApplyVo.getIndexName());
         index.setMrTypeCd(tmplIndex.getTempClass());
         index.setMrTypeName(tempClassName);
         index.setAreaCode(visitinfo.getAreaCode());
         index.setWardName(visitinfo.getWardName());
         index.setDeptCd(visitinfo.getDeptCd());
         index.setDeptName(visitinfo.getDeptName());
         index.setMrState(tdCaConsApplyVo.getMrState());
         index.setTempId(tdCaConsApplyVo.getTempId());
         index.setSecLevel("1");
         index.setCrePerName(user.getNickName());
         index.setCrePerCode(user.getUserName());
         index.setCpDeptCd(user.getDept().getDeptCode());
         index.setCpDeptName(user.getDept().getDeptName());
         index.setRecoDate(tdCaConsApplyVo.getCreDate());
         index.setFileType("EMR");
         index.setBedNo(visitinfo.getBedNo());
         index.setDelFlag("0");
         index.setCreDate(tdCaConsApplyVo.getCreDate());
         index.setInpNo(visitinfo.getInpNo());
         index.setPatientName(personalinfo.getPatientName());
         if (grantOutDoctor != null) {
            index.setIntDocCd(user.getUserName());
            index.setIntDocName(user.getNickName());
            index.setCrePerName(grantOutDoctor.getDocName());
            index.setCrePerCode(grantOutDoctor.getDocCode());
         }

         this.indexMapper.insertIndex(index);
      } else {
         index = this.indexMapper.selectIndexById(tdCaConsApplyVo.getMrFileId());
         this.updateIndexMrState(tdCaConsApplyVo.getMrFileId(), tdCaConsApplyVo.getMrState(), updateTime);
      }

      String mrType = tdCaConsApplyVo.getMrType();
      Long mrFileId = tdCaConsApplyVo.getMrFileId();
      index.setMrContent(tdCaConsApplyVo.getMrContent());
      String optType = addFlag ? "8" : "4";
      String optTypeName = addFlag ? "新建病历" : "病历修改";
      this.sysEmrLogService.insertSysEmrLog(index, (SubfileIndex)null, optType, optTypeName, IPAddressUtil.getIPAddress(request));
      if (tdCaConsApplyVo.getMrState().equals("08")) {
         if (StringUtils.isNotEmpty(tdCaConsApplyVo.getEmrFileUrl())) {
            this.log.debug("saveConsIndex-121212121212121212: 病历文件pdf路径：" + tdCaConsApplyVo.getEmrFileUrl());
            String fileName = index.getId().toString();
            String pdfUrl = Base64Util.createLocalImageMethod(tdCaConsApplyVo.getEmrFileUrl(), fileName, index.getPatientId());
            Index indexFile = new Index();
            indexFile.setId(index.getId());
            indexFile.setMrServPath(pdfUrl);
            indexFile.setMrLocaPath(tdCaConsApplyVo.getEmrFileUrl());
            this.updateIndex(indexFile);
         } else {
            this.log.debug("完成状态保存时 saveConsIndex-121212121212121212: 病历文件pdf路径为空");
         }
      }

      IndexSaveReturnVo indexSaveReturnVo = new IndexSaveReturnVo();
      indexSaveReturnVo.setIndex(index);
      indexSaveReturnVo.setUpdateTime(updateTime);
      this.log.warn("saveConsIndex-44444444444444444444444444122222222:病历base64: " + (index.getId() != null ? index.getId() : "") + " ：" + tdCaConsApplyVo.getBase64Str());
      this.emrBinaryService.addOrUpdateEmrBinary(mrFileId, index, updateUser, this.getMrCon((String)null, tdCaConsApplyVo.getXmlStr(), tdCaConsApplyVo.getTextStr(), ""), updateTime);
      Base64Util.decode(tdCaConsApplyVo.getBase64Str(), "emrBase64/" + tdCaConsApplyVo.getPatientId(), index.getId().toString() + ".txt");
      String fileDateStr = this.commonService.getSystimestamp();
      Base64Util.decode(tdCaConsApplyVo.getBase64Str(), "emrLogBase64/" + tdCaConsApplyVo.getPatientId(), index.getId().toString() + "_" + fileDateStr + ".txt");
      return indexSaveReturnVo;
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void saveConsIndexAsync(TdCaConsApplyVo tdCaConsApplyVo, Index index, Date updateTime) throws Exception {
      SysUser updateUser = SecurityUtils.getLoginUser().getUser();
      Long mrFileId = tdCaConsApplyVo.getMrFileId();
      this.emrBinaryService.addOrUpdateEmrBinary(mrFileId, index, updateUser, this.getMrCon((String)null, tdCaConsApplyVo.getXmlStr(), tdCaConsApplyVo.getTextStr(), ""), updateTime);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void saveConsIndexElemAsync(TdCaConsApplyVo tdCaConsApplyVo, Index index) throws Exception {
      SysUser updateUser = SecurityUtils.getLoginUser().getUser();
      String mrType = index.getMrType();
      Long mrFileId = tdCaConsApplyVo.getMrFileId();
      XmlElementParseConfigVo configVo = this.getXmlElementParseConfigs();
      List<ElemAttriVo> elemAttriList = XmlElementParseUtil.getElemAttriVoListFromXml(tdCaConsApplyVo.getXmlStr(), configVo);
      this.emrElemstoeService.indexSaveEmrElemstoes(mrType, mrFileId, index, updateUser, elemAttriList, (Map)null);
      this.emrSharingService.indexSaveEmrSharingElems(mrType, index, updateUser, elemAttriList);
   }

   public Map selectPatCondSescString(String patientId, TmplIndex tmplIndex) throws Exception {
      Map<String, StringBuffer> map = new HashMap();
      StringBuffer emrDigest = new StringBuffer("");
      StringBuffer consDiag = new StringBuffer("");
      QuoteElem quoteElemParam = new QuoteElem();
      quoteElemParam.setTempType(tmplIndex.getTempType());
      quoteElemParam.setIsValid("1");
      List<QuoteElem> quoteList = this.quoteElemService.selectQuoteElemList(quoteElemParam);
      quoteList = quoteList != null && !quoteList.isEmpty() ? (List)quoteList.stream().filter((s) -> s.getElemId().equals("100585") || s.getElemId().equals("100379")).collect(Collectors.toList()) : null;
      if (quoteList != null && !quoteList.isEmpty()) {
         EmrElemstoe emrElemstoe = new EmrElemstoe();
         emrElemstoe.setPatientId(patientId);
         List<EmrElemstoe> emrElemstoeList = this.emrElemstoeService.selectEmrElemstoeList(emrElemstoe);
         Map<String, List<QuoteElem>> mapList = (Map)quoteList.stream().collect(Collectors.groupingBy((s) -> s.getElemId()));

         for(String elemId : mapList.keySet()) {
            List<QuoteElem> quoteElemList = (List)mapList.get(elemId);
            if (quoteElemList != null && !quoteElemList.isEmpty()) {
               for(QuoteElem quoteElem : quoteElemList) {
                  List<EmrElemstoe> elemList = (List)emrElemstoeList.stream().filter((s) -> s.getMrTypeCd().equals(quoteElem.getFromMrTypeCd()) && s.getElemId().equals(quoteElem.getFromElemId())).collect(Collectors.toList());
                  elemList.sort((m1, m2) -> m2.getCreDate().compareTo(m1.getCreDate()));
                  if (elemList != null && elemList.size() > 0 && ((EmrElemstoe)elemList.get(0)).getElemCon() != null) {
                     if (quoteElem.getElemId().equals("100585")) {
                        emrDigest.append(((EmrElemstoe)elemList.get(0)).getElemCon());
                     } else {
                        consDiag.append(((EmrElemstoe)elemList.get(0)).getElemCon());
                     }
                  }
               }
            }
         }
      }

      map.put("emrDigest", emrDigest);
      map.put("consDiag", consDiag);
      return map;
   }

   public List selectOpeIndexByEmrType(String patientId, List emrTypeCodeList) throws Exception {
      return this.indexMapper.selectOpeIndexByEmrType(patientId, emrTypeCodeList);
   }

   public List selectOpeIndexByOrderNo(String applyFormNo) throws Exception {
      return this.indexMapper.selectOpeIndexByOrderNo(applyFormNo);
   }

   public void updateEmrOrderNo(String orderNo) throws Exception {
      this.indexMapper.updateEmrOrderNo(orderNo);
   }

   public void updateOrderNoEmptyByIdList(List mrFileIdList) throws Exception {
      this.indexMapper.updateOrderNoEmptyByIdList(mrFileIdList);
   }

   public void updateEmrOrderNoByIdList(String orderNo, List mrFileIdList) throws Exception {
      this.indexMapper.updateEmrOrderNoByIdList(orderNo, mrFileIdList);
   }

   public void updateOrderNoByOrderNo(String oldOrderNo, String newOrderNo) throws Exception {
      this.indexMapper.updateOrderNoByOrderNo(oldOrderNo, newOrderNo);
   }

   public List selectPatEmrPrintList(IndexVo indexVo) throws Exception {
      List<IndexVo> resultList = new ArrayList();
      List<SysDictData> dictType = this.sysDictDataService.selectDictDataByType("s003");
      List<IndexVo> emrList = this.indexMapper.selectEmrListByPat(indexVo);
      if (emrList != null && !emrList.isEmpty()) {
         Map<String, List<IndexVo>> emrMap = (Map)emrList.stream().collect(Collectors.groupingBy((s) -> s.getMrTypeCd()));
         int i = 0;
         Iterator var7 = dictType.iterator();

         while(true) {
            List<IndexVo> indexVoList;
            IndexVo index;
            label54:
            while(true) {
               if (!var7.hasNext()) {
                  return resultList;
               }

               SysDictData sysDictData = (SysDictData)var7.next();
               indexVoList = (List)emrMap.get(sysDictData.getDictValue());
               if (indexVoList != null && !indexVoList.isEmpty()) {
                  index = new IndexVo();
                  index.setMrTypeCd(sysDictData.getDictValue());
                  index.setMrTypeName(sysDictData.getDictLabel());
                  index.setMrTypeCdIndex(i++);

                  for(IndexVo indexVo1 : indexVoList) {
                     if (StringUtils.isNotEmpty(indexVo1.getMrServPath())) {
                        String reOld = EMRConfig.getProfile();
                        String reNew = EMRConfig.getNfsPdfurl();
                        String path = indexVo1.getMrServPath().replace(reOld, reNew);
                        indexVo1.setMrServPath(path);
                     }
                  }

                  if (!sysDictData.getDictValue().equals("01")) {
                     break;
                  }

                  List<SubfileIndex> subList = this.subfileIndexService.selectSubFileByPat(indexVo);
                  if (subList != null && !subList.isEmpty()) {
                     Iterator var17 = indexVoList.iterator();

                     while(true) {
                        if (!var17.hasNext()) {
                           break label54;
                        }

                        IndexVo indexVo1 = (IndexVo)var17.next();
                        indexVo1.setMrFileShowName("病程合并记录");
                     }
                  }
               }
            }

            index.setMrFileShowName(index.getMrTypeName());
            index.setChildList(indexVoList);
            resultList.add(index);
         }
      } else {
         return resultList;
      }
   }

   public AjaxResult isCancelSign(Index index, SubfileIndex subfileIndex, String xmlStr) throws Exception {
      AjaxResult ajaxResult = AjaxResult.success();
      boolean flag = true;
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      String emrState = subfileIndex == null ? index.getMrState() : subfileIndex.getMrState();
      Long id = subfileIndex == null ? index.getId() : subfileIndex.getId();
      Date emrUpdateDate = subfileIndex == null ? index.getAltDate() : subfileIndex.getAltDate();
      if (subfileIndex == null) {
         index.getTempId();
      } else {
         subfileIndex.getTempId();
      }

      if (!emrState.equals("05") && !emrState.equals("07") && !emrState.equals("08")) {
         flag = false;
         ajaxResult = AjaxResult.error("当前病历暂未签名");
      }

      List<ElemSign> resultList = new ArrayList();
      if (flag) {
         EmrSignRecord emrSignRecord = new EmrSignRecord();
         emrSignRecord.setMrFileId(id);
         emrSignRecord.setSignCancFlag("0");
         emrSignRecord.setDocCode(sysUser.getUserName());
         List<EmrSignRecord> emrSignRecordList = this.emrSignRecordService.selectEmrSignRecordForFeeMoveList(emrSignRecord);
         if (emrSignRecordList != null && emrSignRecordList.size() > 0) {
            List<EmrSignRecord> signRecordList = this.emrSignRecordService.selectEmrSignRecordListByMainFileIdForFreeMove(id.toString());
            if (signRecordList != null && signRecordList.size() > 0) {
               Date date = ((EmrSignRecord)signRecordList.get(0)).getSignTime();
               String editorType = this.sysEmrConfigService.selectSysEmrConfigByKey("0000");
               TempIndexSaveElemVo tempIndexSaveElemVo = XmlElementParseUtil.getSaveElemFromXml(xmlStr, editorType);

               for(ElemSign elemSign : tempIndexSaveElemVo.getElemSignList()) {
                  List<EmrSignRecord> signRecordListTemp = (List)signRecordList.stream().filter((t) -> date.compareTo(t.getSignTime()) == 0).collect(Collectors.toList());
                  if (signRecordListTemp != null && !signRecordListTemp.isEmpty()) {
                     List<String> signList = (List)signRecordListTemp.stream().map((s) -> s.getSignSname()).collect(Collectors.toList());
                     if (signList.contains(elemSign.getContElemName())) {
                        resultList.add(elemSign);
                     }
                  }
               }

               ajaxResult.put("signList", resultList);
            } else {
               flag = false;
               ajaxResult = AjaxResult.error("未查询到签名记录");
            }
         } else {
            List<EmrSignRecord> signRecordList = this.emrSignRecordService.selectEmrSignRecordListByMainFileId(id.toString());
            if (signRecordList != null && !signRecordList.isEmpty()) {
               Date date = ((EmrSignRecord)signRecordList.get(0)).getSignTime();
               Date emrUpdate = ((EmrSignRecord)signRecordList.get(0)).getEmrUpdateTime();
               if (!((EmrSignRecord)signRecordList.get(0)).getDocCode().equals(sysUser.getBasEmployee().getEmplNumber())) {
                  flag = false;
                  ajaxResult = AjaxResult.error("仅" + ((EmrSignRecord)signRecordList.get(0)).getDocName() + "医师，在病历签名后未修改内容前可取消签名！");
               }

               EmrBinary emrBinary = this.emrBinaryService.selectEmrBinaryById(id);
               if (flag && (emrUpdateDate != null || emrUpdate != null || emrBinary.getAltDate() != null) && (emrUpdateDate != null && emrUpdate == null || emrUpdateDate == null || emrUpdate == null || emrBinary.getAltDate() == null || emrUpdateDate.compareTo(emrUpdate) != 0 || emrUpdateDate.compareTo(emrBinary.getAltDate()) != 0)) {
                  flag = false;
                  ajaxResult = AjaxResult.error("仅" + ((EmrSignRecord)signRecordList.get(0)).getDocName() + "医师，在病历签名后未修改内容前可取消签名！");
               }

               if (flag) {
                  String editorType = this.sysEmrConfigService.selectSysEmrConfigByKey("0000");
                  TempIndexSaveElemVo tempIndexSaveElemVo = XmlElementParseUtil.getSaveElemFromXml(xmlStr, editorType);

                  for(ElemSign elemSign : tempIndexSaveElemVo.getElemSignList()) {
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
         }
      }

      return ajaxResult;
   }

   public void saveBaseStr(IndexVo indexVo) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      Long id = indexVo.getId();
      EmrBinary emrBinaryUpdate = new EmrBinary();
      emrBinaryUpdate.setMrFileId(id);
      emrBinaryUpdate.setAltPerCode(basEmployee.getEmplNumber());
      emrBinaryUpdate.setAltPerName(basEmployee.getEmplName());
      String mrCon = this.getMrCon(indexVo.getBase64Str(), indexVo.getXmlStr(), (String)null, (String)null);
      emrBinaryUpdate.setMrCon(mrCon);
      emrBinaryUpdate.setAltDate(this.commonService.getDbSysdate());
      this.emrBinaryService.updateEmrBinary(emrBinaryUpdate);
      Base64Util.decode(indexVo.getBase64Str(), "emrBase64/" + indexVo.getPatientId(), id.toString() + ".txt");
      String fileDateStr = this.commonService.getSystimestamp();
      Base64Util.decode(indexVo.getBase64Str(), "emrLogBase64/" + indexVo.getPatientId(), id.toString() + "_" + fileDateStr + ".txt");
   }

   public void filterEmrPdf(String ips, String fileNames) throws Exception {
      List<IndexVo> emrList = this.indexMapper.selectEmrPdfPath();
      String[] ipStr = ips.split(",");
      String[] fileNameStr = fileNames.split(",");
      List<IndexVo> updateList = new ArrayList();
      List<Long> noList = new ArrayList();
      this.log.debug("查找pdf开始--------------------------------------------------------------------------------------------------");

      for(IndexVo index : emrList) {
         if (StringUtils.isEmpty(index.getMrLocaPath())) {
            this.log.debug("pdf路径为空111111111：住院号：" + index.getPatientId() + " 病历id：" + index.getId() + " 病历创建人：" + index.getCrePerName() + index.getCrePerCode());
         } else {
            try {
               boolean flag = false;

               for(String ip : ipStr) {
                  if (!flag) {
                     int a = index.getMrLocaPath().indexOf(ip);
                     if (a > -1) {
                        for(String fileName : fileNameStr) {
                           String filePath = index.getMrLocaPath();
                           filePath = filePath.replace(ip, fileName);
                           File file = new File(filePath);
                           if (file.exists()) {
                              this.log.debug("已找到文件");
                              flag = true;
                              index.setFilePath(filePath);
                              updateList.add(index);
                              break;
                           }
                        }
                     }
                  }
               }

               if (!flag) {
                  noList.add(index.getId());
                  this.log.debug("未找到当前pdf文件111111111：住院号：" + index.getPatientId() + " 病历id：" + index.getId() + " 病历创建人：" + index.getCrePerName() + index.getCrePerCode());
               }
            } catch (Exception var22) {
               this.log.debug("未找到此路径文件1111111111：住院号：" + index.getPatientId() + " 病历id：" + index.getId() + " 病历创建人：" + index.getCrePerName() + index.getCrePerCode());
            }
         }
      }

      this.log.debug("查找pdf结束--------------------------------------------------------------------------------------------------");
      if (updateList != null && !updateList.isEmpty()) {
         this.log.debug("保存pdf开始--------------------------------------------------------------------------------------------------");

         for(IndexVo indexVo : updateList) {
            String pdfUrl = Base64Util.createLocalImage(indexVo.getFilePath(), indexVo.getId().toString(), indexVo.getPatientId());
            Index indexFile = new Index();
            indexFile.setId(indexVo.getId());
            indexFile.setMrServPath(pdfUrl);
            indexFile.setMrLocaPath(indexVo.getMrLocaPath());
            this.updateIndex(indexFile);
         }

         this.log.debug("保存pdf结束--------------------------------------------------------------------------------------------------");
      }

      String ids = noList != null && !noList.isEmpty() ? StringUtils.join(new Object[]{",", noList}) : "";
      this.log.debug("打印未找到pdf病历id：" + ids);
   }

   public void saveEmrPdf(IndexSaveVo indexSaveVo) throws Exception {
      if (StringUtils.isNotEmpty(indexSaveVo.getEmrFileUrl())) {
         this.log.debug("saveEmrPdf-111111111111111111111: 病历文件pdf路径：" + indexSaveVo.getEmrFileUrl());
         String fileName = indexSaveVo.getId().toString();
         String pdfUrl = Base64Util.createLocalImageMethod(indexSaveVo.getEmrFileUrl(), fileName, indexSaveVo.getPatientId());
         Index indexFile = new Index();
         indexFile.setId(indexSaveVo.getId());
         indexFile.setMrServPath(pdfUrl);
         indexFile.setMrLocaPath(indexSaveVo.getEmrFileUrl());
         this.updateIndex(indexFile);
      } else {
         this.log.debug("saveEmrPdf-2222222222222222222222: 病历文件pdf路径为空");
      }

   }

   public List selectUnSavePdfEmr() throws Exception {
      return this.indexMapper.selectUnSavePdfEmr();
   }

   public String selectEmrBase64(Long id) throws Exception {
      String base64 = "";
      EmrBinary emrBinary = this.emrBinaryService.selectEmrBinaryById(id);
      if (emrBinary != null) {
         base64 = Base64Util.getFileBase64("emrBase64/" + emrBinary.getPatientId(), id.toString() + ".txt");
      }

      return base64;
   }

   public List selectEmrListByPat(IndexVo indexVo) throws Exception {
      return this.indexMapper.selectEmrListByPat(indexVo);
   }

   public List selectSubEmrListByPat(String patientId) throws Exception {
      return this.indexMapper.selectSubEmrListByPat(patientId);
   }

   public List selectAllIndexList(IndexVo indexVo) throws Exception {
      return this.indexMapper.selectAllIndexList(indexVo);
   }

   public XmlElementParseConfigVo getXmlElementParseConfigs() throws Exception {
      String editorType = this.sysEmrConfigService.selectSysEmrConfigByKey("0000");
      String replaceLineBreaks = this.sysEmrConfigService.selectSysEmrConfigByKey("0060");
      String lineBreaksElemIds = this.sysEmrConfigService.selectSysEmrConfigByKey("006001");
      String lineBreaksPosition = this.sysEmrConfigService.selectSysEmrConfigByKey("006002");
      XmlElementParseConfigVo configVo = new XmlElementParseConfigVo(editorType, replaceLineBreaks, lineBreaksElemIds, lineBreaksPosition);
      return configVo;
   }

   public List selectIndexListByTmplList(List elemList, String patientId) throws Exception {
      return this.indexMapper.selectIndexListByTmplList(elemList, patientId);
   }

   public Index selectIndexByTmplId(String patientId, Long tempId) throws Exception {
      return this.indexMapper.selectIndexByTmplId(patientId, tempId);
   }

   public List selectElemListByMrFileId(AnalysisXmlDataReq req) throws Exception {
      XmlElementParseConfigVo configVo = this.getXmlElementParseConfigs();
      List<ElemAttriVo> attriVos = new ArrayList();
      String toXmlStr = req.getToXmlStr();
      String fromXmlStr = req.getFromXmlStr();
      List<ElemAttriVo> elemToList = XmlElementParseUtil.getElemAttriVoListFromXml(toXmlStr, configVo);
      List<ElemAttriVo> elemFromList = XmlElementParseUtil.getElemAttriVoListFromXml(fromXmlStr, configVo);
      QuoteElem quoteElem = new QuoteElem();
      quoteElem.setTempType(req.getFromEmrTypeCode());
      quoteElem.setFromMrTypeCd(req.getToEmrTypeCode());
      List<QuoteElem> quoteElems = this.quoteElemMapper.selectQuoteElemList(quoteElem);
      if (!quoteElems.isEmpty()) {
         Map<Long, List<QuoteElem>> elemMap = (Map)quoteElems.stream().collect(Collectors.groupingBy(QuoteElem::getFromElemId));

         for(Long key : elemMap.keySet()) {
            QuoteElem elem = (QuoteElem)((List)elemMap.get(key)).get(0);
            List<ElemAttriVo> attriVoToList = (List)elemToList.stream().filter((t) -> t.getElemId() != null && t.getElemId().toString().equals(key.toString())).collect(Collectors.toList());
            if (!attriVoToList.isEmpty()) {
               ElemAttriVo attriVo = (ElemAttriVo)attriVoToList.get(0);
               String elemToId = elem.getElemId();
               String elemConText = attriVo.getElemConText();
               List<ElemAttriVo> attriVoFromList = (List)elemFromList.stream().filter((t) -> t.getElemId() != null && t.getElemId().toString().equals(elemToId)).collect(Collectors.toList());
               if (!attriVoFromList.isEmpty()) {
                  attriVoFromList.forEach((t) -> t.setElemConText(elemConText));
               }

               attriVos.addAll(attriVoFromList);
            }
         }
      }

      return attriVos;
   }

   public List selectOrderNoByIdList(List idList) {
      return this.indexMapper.selectOrderNoByIdList(idList);
   }

   public Map selectChangeAlertElems(String patientId, TempIndexSaveElemVo tempIndexSaveElemVo, List elemAttriList, String babyId) throws Exception {
      Map<String, Object> resMap = null;
      List<ElemMacro> elemMacroList = tempIndexSaveElemVo.getElemMacroList();
      elemMacroList = CollectionUtils.isNotEmpty(elemMacroList) ? (List)elemMacroList.stream().filter((t) -> StringUtils.isNotBlank(t.getChangeAlertFlag()) && t.getChangeAlertFlag().equals("1")).collect(Collectors.toList()) : null;
      List<String> keyList = new ArrayList(1);
      List<String> valueList = new ArrayList(1);
      StringBuffer msg = new StringBuffer();
      if (CollectionUtils.isNotEmpty(elemMacroList)) {
         Map<String, List<String>> elemMacroMap = this.elemMacroKeyAndValue(patientId, (String)null, babyId, elemMacroList);
         List<String> textIdList = (List)elemMacroMap.get("setStructsTextIdList");
         List<String> textValueList = (List)elemMacroMap.get("setStructsTextValueList");
         List<String> elemMacroTextIdList = (List)elemMacroList.stream().map((t) -> t.getContElemName()).collect(Collectors.toList());
         List<ElemAttriVo> elemMacroValueList = CollectionUtils.isNotEmpty(elemAttriList) ? (List)elemAttriList.stream().filter((t) -> elemMacroTextIdList.contains(t.getContElemName())).collect(Collectors.toList()) : null;

         for(int i = 0; i < textIdList.size(); ++i) {
            String textId = (String)textIdList.get(i);
            String textValue = StringUtils.isNotBlank((CharSequence)textValueList.get(i)) ? (String)textValueList.get(i) : "空";
            boolean changeAlertFlag = true;
            List<ElemAttriVo> elemMacroValueList2 = (List)elemMacroValueList.stream().filter((t) -> StringUtils.isNotBlank(t.getContElemName()) && t.getContElemName().equals(textId)).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(elemMacroValueList2)) {
               ElemAttriVo elemAttriVo = (ElemAttriVo)elemMacroValueList2.get(0);
               String elemConText = StringUtils.isNotBlank(elemAttriVo.getElemConText()) ? elemAttriVo.getElemConText() : "空";
               if (StringUtils.isNotBlank(textValue) && StringUtils.isNotBlank(elemConText) && textValue.equals(elemConText)) {
                  changeAlertFlag = false;
               }

               if (StringUtils.isBlank(textValue) && StringUtils.isBlank(elemConText)) {
                  changeAlertFlag = false;
               }

               if (changeAlertFlag) {
                  if (StringUtils.isNotBlank(elemConText) && DateUtils.isValidDateFormat(elemConText)) {
                     Date elemConTextD = DateUtils.parseDate(elemConText);
                     elemConText = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, elemConTextD);
                  }

                  if (StringUtils.isNotBlank(textValue) && DateUtils.isValidDateFormat(textValue)) {
                     Date textValueD = DateUtils.parseDate(textValue);
                     textValue = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, textValueD);
                  }

                  if (StringUtils.isNotBlank(textValue) && StringUtils.isNotBlank(elemConText) && textValue.equals(elemConText)) {
                     changeAlertFlag = false;
                  }
               }

               if (changeAlertFlag) {
                  keyList.add(textId);
                  valueList.add(textValue);
                  msg.append("【" + elemAttriVo.getElemName() + "】元素，内容发生了变化，原始内容【" + elemConText + "】，变化后内容【" + textValue + "】；");
               }
            }
         }

         if (CollectionUtils.isNotEmpty(keyList) && CollectionUtils.isNotEmpty(valueList)) {
            resMap = new HashMap(1);
            resMap.put("setStructsTextIdList", keyList);
            resMap.put("setStructsTextValueList", valueList);
            msg.append("是否修改？");
            resMap.put("changeAlertMsg", msg.toString());
         }
      }

      return resMap;
   }

   public Map selectAlertElems(String patientId, String babyId, Long tempId, TempIndexSaveElemVo tempIndexSaveElemVo, List elemAttriList) throws Exception {
      Map<String, Object> resMap = null;
      List<ElemMacro> elemMacroList = tempIndexSaveElemVo.getElemMacroList();
      elemMacroList = CollectionUtils.isNotEmpty(elemMacroList) ? (List)elemMacroList.stream().filter((t) -> t.getChangeAlertFlag().equals("1")).collect(Collectors.toList()) : null;
      List<String> keyList = new ArrayList(1);
      List<String> valueList = new ArrayList(1);
      if (CollectionUtils.isNotEmpty(elemMacroList)) {
         Map<String, List<String>> elemMacroMap = this.elemMacroKeyAndValue(patientId, (String)null, babyId, elemMacroList);
         List<String> textIdList = (List)elemMacroMap.get("setStructsTextIdList");
         List<String> textValueList = (List)elemMacroMap.get("setStructsTextValueList");

         for(int i = 0; i < textIdList.size(); ++i) {
            String textId = (String)textIdList.get(i);
            String textValue = StringUtils.isNotBlank((CharSequence)textValueList.get(i)) ? (String)textValueList.get(i) : "空";
            if (StringUtils.isNotBlank(textValue) && DateUtils.isValidDateFormat(textValue)) {
               ElemMacro elemMacro = new ElemMacro();
               elemMacro.setTempId(tempId);
               elemMacro.setContElemName(textId);
               List<ElemMacro> elemMacros = this.elemMacroService.selectElemMacroList(elemMacro);
               if (CollectionUtils.isNotEmpty(elemMacros)) {
                  ElemMacro elemMacro1 = (ElemMacro)elemMacros.get(0);
                  if (elemMacro1 != null && StringUtils.isNotBlank(elemMacro1.getDataForm())) {
                     Date dateValue = DateUtils.parseDate(textValue);
                     String dataForm = StringUtils.isNotBlank(elemMacro1.getDataForm()) && elemMacro1.getDataForm().contains("DD") ? elemMacro1.getDataForm().replaceAll("DD", "dd") : elemMacro1.getDataForm();
                     dataForm = StringUtils.isNotBlank(dataForm) && dataForm.contains("D") ? elemMacro1.getDataForm().replaceAll("D", "d") : dataForm;
                     dataForm = StringUtils.isNotBlank(dataForm) && dataForm.contains("MM") ? elemMacro1.getDataForm().replaceAll("MM", "mm") : dataForm;
                     textValue = DateUtils.dateFormatS(dateValue, dataForm);
                  }
               }
            }

            keyList.add(textId);
            valueList.add(textValue);
         }
      }

      if (CollectionUtils.isNotEmpty(keyList) && CollectionUtils.isNotEmpty(valueList)) {
         resMap = new HashMap(1);
         resMap.put("setStructsTextIdList", keyList);
         resMap.put("setStructsTextValueList", valueList);
      }

      return resMap;
   }

   public String queryEmrBase64FromRedis(Long mrFileId) throws Exception {
      String base64 = (String)this.indexRedisCache.getCacheObject("index_base64_key:" + mrFileId);
      if (StringUtils.isBlank(base64)) {
         Index index = this.selectIndexById(mrFileId);
         base64 = Base64Util.getFileBase64("emrBase64/" + index.getPatientId(), mrFileId.toString() + ".txt");
      }

      return base64;
   }

   public SysUser currUserByBJCAs(String signFlag, String signCertSn) throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      String caFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0001");
      String caType = this.sysEmrConfigService.selectSysEmrConfigByKey("0002");
      if (caFlag.equals("1") && caType.equals("BJCA") && StringUtils.isNotBlank(signFlag) && signFlag.equals("1")) {
         sysUser = this.getUserBySignCertSn(signCertSn);
      }

      return sysUser;
   }

   public SysUser getUserBySignCertSn(String signCertSn) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      if (StringUtils.isNotBlank(signCertSn)) {
         BasCertInfo basCertInfo = this.basCertInfoService.selectBasCertInfoByCerSn(signCertSn);
         if (basCertInfo != null) {
            SysUser user1 = this.sysUserService.selectUserByUserName(basCertInfo.getEmployeenumber());
            if (!user.getUserName().equals(user1.getUserName())) {
               user1.setHospital(user.getHospital());
               user = user1;
            }
         }
      }

      return user;
   }

   public int deleteByIds(List idList) throws Exception {
      if (CollectionUtils.isNotEmpty(idList) && idList.size() > 0) {
         this.indexMapper.deleteByIds(idList);
      }

      return 1;
   }
}
