package com.emr.project.pat.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.emr.common.exception.CustomException;
import com.emr.common.utils.AgeUtil;
import com.emr.common.utils.DateUtils;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.common.utils.StringUtils;
import com.emr.common.utils.ToggleCaseUtils;
import com.emr.common.utils.sql.SqlUtil;
import com.emr.framework.aspectj.lang.annotation.DataSource;
import com.emr.framework.aspectj.lang.enums.DataSourceType;
import com.emr.framework.datasource.DruidUtil;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.PageDomain;
import com.emr.framework.web.page.TableSupport;
import com.emr.framework.web.service.ISyncService;
import com.emr.project.common.domain.vo.ReplaceUrlParamVo;
import com.emr.project.common.service.ICommonService;
import com.emr.project.docOrder.domain.TdPaItemDocQuery;
import com.emr.project.docOrder.domain.vo.OrderSaveVo;
import com.emr.project.docOrder.domain.vo.OrderSearchVo;
import com.emr.project.docOrder.domain.vo.PhysignDayVo;
import com.emr.project.docOrder.domain.vo.PhysignValueVo;
import com.emr.project.docOrder.domain.vo.TdPaOrderAgentVo;
import com.emr.project.docOrder.mapper.TdCaOperationApplyMapper;
import com.emr.project.docOrder.service.IDoctorderService;
import com.emr.project.docOrder.service.IPhysignValueService;
import com.emr.project.docOrder.service.ITdCaOperationApplyService;
import com.emr.project.docOrder.service.ITdPaItemDocQueryService;
import com.emr.project.dr.domain.TdCaConsApply;
import com.emr.project.dr.domain.vo.TdCaConsApplyVo;
import com.emr.project.dr.service.ITdCaConsApplyService;
import com.emr.project.emr.domain.Index;
import com.emr.project.emr.domain.vo.IndexVo;
import com.emr.project.emr.service.IEmrTaskInfoService;
import com.emr.project.emr.service.IIndexService;
import com.emr.project.monitor.domain.SysJobLog;
import com.emr.project.monitor.service.ISysJobLogService;
import com.emr.project.operation.domain.Medicalinformation;
import com.emr.project.operation.mapper.MedicalinformationMapper;
import com.emr.project.operation.mapper.TmPmHsxmMapper;
import com.emr.project.pat.domain.AlleInfo;
import com.emr.project.pat.domain.BasDoctGroupMember;
import com.emr.project.pat.domain.DiagInfo;
import com.emr.project.pat.domain.OperatRecord;
import com.emr.project.pat.domain.Personalinfo;
import com.emr.project.pat.domain.Visitinfo;
import com.emr.project.pat.domain.vo.AlleInfoVo;
import com.emr.project.pat.domain.vo.BackLogVo;
import com.emr.project.pat.domain.vo.CriticalVo;
import com.emr.project.pat.domain.vo.OperRoomVisitinfoVo;
import com.emr.project.pat.domain.vo.OperatRecordVo;
import com.emr.project.pat.domain.vo.PatientInfoVo;
import com.emr.project.pat.domain.vo.VisitinfoPersonalVo;
import com.emr.project.pat.domain.vo.VisitinfoVo;
import com.emr.project.pat.mapper.BabyInfoMapper;
import com.emr.project.pat.mapper.PersonalinfoMapper;
import com.emr.project.pat.mapper.TransinfoMapper;
import com.emr.project.pat.mapper.VisitinfoMapper;
import com.emr.project.pat.service.IAlleInfoService;
import com.emr.project.pat.service.IBasDoctGroupMemberService;
import com.emr.project.pat.service.IDiagInfoService;
import com.emr.project.pat.service.IOperatRecordService;
import com.emr.project.pat.service.IPersonalinfoService;
import com.emr.project.pat.service.ITlNaAdjustLogService;
import com.emr.project.pat.service.IVisitinfoService;
import com.emr.project.qc.domain.PatEvent;
import com.emr.project.qc.domain.vo.RunTimeQcCheckVo;
import com.emr.project.qc.service.IEmrQcFlowService;
import com.emr.project.qc.service.IEmrQcListService;
import com.emr.project.qc.service.IEmrQcRecordService;
import com.emr.project.qc.service.IPatEventService;
import com.emr.project.sys.domain.BusIntegMenu;
import com.emr.project.sys.service.IBusIntegMenuService;
import com.emr.project.system.domain.BasEmployee;
import com.emr.project.system.domain.PatAtt;
import com.emr.project.system.domain.SyncDatasource;
import com.emr.project.system.domain.SysDept;
import com.emr.project.system.domain.SysDictData;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.domain.vo.SqlVo;
import com.emr.project.system.mapper.PatAttMapper;
import com.emr.project.system.service.IBasEmployeeService;
import com.emr.project.system.service.ISyncDatasourceService;
import com.emr.project.system.service.ISysDictDataService;
import com.emr.project.system.service.ISysEmrConfigService;
import com.emr.project.webEditor.zb.util.WeekUtil;
import com.emr.project.webEditor.zb.vo.DayVo;
import com.emr.project.webEditor.zb.vo.TimeLineDataVo;
import com.emr.project.webEditor.zb.vo.WeeklyVo;
import com.github.pagehelper.PageHelper;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VisitinfoServiceImpl implements IVisitinfoService, ISyncService {
   protected final Logger log = LoggerFactory.getLogger(VisitinfoServiceImpl.class);
   @Autowired
   private VisitinfoMapper visitinfoMapper;
   @Autowired
   private IPersonalinfoService personalinfoService;
   @Autowired
   private IAlleInfoService alleInfoService;
   @Autowired
   private IDiagInfoService diagInfoService;
   @Resource
   private IPhysignValueService physignValueService;
   @Resource
   private ISyncDatasourceService syncDatasourceService;
   @Resource
   private IIndexService indexService;
   @Resource
   private IDoctorderService doctorderService;
   @Autowired
   private IOperatRecordService operatRecordService;
   @Autowired
   private IBasEmployeeService basEmployeeService;
   @Autowired
   private IBasDoctGroupMemberService doctGroupMemberService;
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;
   @Autowired
   private IEmrQcRecordService emrQcRecordService;
   @Autowired
   private ITdCaConsApplyService tdCaConsApplyService;
   @Autowired
   private ICommonService commonService;
   @Autowired
   private ISysDictDataService sysDictDataService;
   @Autowired
   private TransinfoMapper transinfoMapper;
   @Autowired
   private BabyInfoMapper babyInfoMapper;
   @Autowired
   private TdCaOperationApplyMapper tdCaOperationApplyMapper;
   @Autowired
   private IEmrQcListService emrQcListService;
   @Autowired
   private IPatEventService patEventService;
   @Autowired
   private ISysJobLogService sysJobLogService;
   @Autowired
   private ITdPaItemDocQueryService tdPaItemDocQueryService;
   @Autowired
   private ITdCaOperationApplyService tdCaOperationApplyService;
   @Autowired
   private ITlNaAdjustLogService tlNaAdjustLogService;
   @Autowired
   private PersonalinfoMapper personalinfoMapper;
   @Autowired
   private MedicalinformationMapper medicalinformationMapper;
   @Autowired
   private TmPmHsxmMapper tmPmHsxmMapper;
   @Autowired
   private PatAttMapper patAttMapper;
   @Autowired
   private IEmrTaskInfoService emrTaskInfoService;
   @Autowired
   private IEmrQcFlowService emrQcFlowService;
   @Autowired
   private IBusIntegMenuService busIntegMenuService;

   public VisitinfoVo selectVisitinfoById(String patientId) throws Exception {
      VisitinfoVo param = new VisitinfoVo();
      param.setPatientId(patientId);
      VisitinfoVo vo = this.visitinfoMapper.selectPatientById(param);
      Medicalinformation medicalinformation = this.medicalinformationMapper.selectMedicalinfoByPatientId(patientId);
      vo.setAge(AgeUtil.getAgeStr(vo.getAgeY(), vo.getAgeM(), vo.getAgeD(), vo.getAgeH(), vo.getAgeMi()));
      vo.setInhosDayNum(DateUtils.getDiffDay(vo.getOutTime() != null ? vo.getOutTime() : new Date(), vo.getInhosTime()) + 1);
      vo.setDaycaseFlag(medicalinformation.getDaycaseFlag());
      AlleInfo alleInfoParam = new AlleInfo();
      alleInfoParam.setPatientId(vo.getPatientId());
      List<AlleInfo> alleInfoList = this.alleInfoService.selectAlleInfoList(alleInfoParam);
      Map<String, List<VisitinfoVo>> colorMap = this.selectColorplan();
      List<VisitinfoVo> colorList = (List)colorMap.get("2");
      if (StringUtils.isNotEmpty(vo.getNgCd())) {
         List<VisitinfoVo> color = (List)colorList.stream().filter((t) -> vo.getNgCd().equals(t.getColorTypeCd())).collect(Collectors.toList());
         if (color != null && !color.isEmpty()) {
            vo.setColorValue(((VisitinfoVo)color.get(0)).getColorValue());
         }
      }

      vo.setAlleInfoList(alleInfoList);
      if (StringUtils.isBlank(vo.getInhosDiag())) {
         List<DiagInfo> diagInfoList = this.diagInfoService.selectPatientMainDiag(vo.getPatientId(), "02", "01");
         if (CollectionUtils.isNotEmpty(diagInfoList)) {
            DiagInfo diagInfo = (DiagInfo)diagInfoList.get(0);
            vo.setInhosDiagCd(diagInfo.getDiagCd());
            vo.setInhosDiag(diagInfo.getDiagName());
         }
      }

      return vo;
   }

   public VisitinfoVo selectVisitinfoByPatientId(String patientId) throws Exception {
      return this.visitinfoMapper.selectVisitinfoById(patientId);
   }

   public VisitinfoPersonalVo selectVisitinfoPersonalById(String patientId) throws Exception {
      VisitinfoPersonalVo personalVo = this.visitinfoMapper.selectVisitinfoPersonalVoById(patientId);
      personalVo.setAge(AgeUtil.getAgeStr(personalVo.getAgeY(), personalVo.getAgeM(), personalVo.getAgeD(), personalVo.getAgeH(), personalVo.getAgeMi()));
      return personalVo;
   }

   public List selectVisitinfoList(Visitinfo visitinfo) throws Exception {
      return this.visitinfoMapper.selectVisitinfoList(visitinfo);
   }

   public int insertVisitinfo(Visitinfo visitinfo) {
      return this.visitinfoMapper.insertVisitinfo(visitinfo);
   }

   public int updateVisitinfo(Visitinfo visitinfo) {
      return this.visitinfoMapper.updateVisitinfo(visitinfo);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void updateVisitAndPersonInfo(VisitinfoVo visitinfoVo, Personalinfo personalinfo) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      BasEmployee basEmployee = user.getBasEmployee();
      Visitinfo visitinfo = this.visitinfoMapper.selectVisitinfoById(visitinfoVo.getPatientId());
      Personalinfo person = this.personalinfoService.selectPersonalinfoById(visitinfoVo.getPatientId());
      personalinfo.setAltPerCode(basEmployee.getEmplNumber());
      personalinfo.setAltPerName(basEmployee.getEmplName());
      personalinfo.setAltDate(this.commonService.getDbSysdate());
      this.personalinfoService.updatePersonalinfo(personalinfo);
      visitinfoVo.setAltPerCode(basEmployee.getEmplNumber());
      visitinfoVo.setAltPerName(basEmployee.getEmplName());
      visitinfoVo.setAltDate(this.commonService.getDbSysdate());
      if (StringUtils.isEmpty(visitinfo.getMainPatFlag()) && StringUtils.isNotEmpty(visitinfoVo.getMainPatFlag())) {
         visitinfoVo.setMarkDate(this.commonService.getDbSysdate());
      }

      this.alleInfoService.deleteAlleInfoByPatientId(visitinfoVo.getPatientId());
      if (visitinfoVo.getAlleInfoList() != null && !visitinfoVo.getAlleInfoList().isEmpty()) {
         List<AlleInfo> alleInfoList = visitinfoVo.getAlleInfoList();

         for(AlleInfo alleInfo : alleInfoList) {
            alleInfo.setId(SnowIdUtils.uniqueLong());
            alleInfo.setOrgCd(user.getHospital().getOrgCode());
            alleInfo.setPatientMainId(person.getPatientMainId());
            alleInfo.setAdmissionNo(visitinfo.getInpNo());
            alleInfo.setHospitalizedCount(visitinfo.getVisitId());
            alleInfo.setOccurDate(this.commonService.getDbSysdate());
            alleInfo.setCreDate(this.commonService.getDbSysdate());
            alleInfo.setCrePerCode(basEmployee.getEmplNumber());
            alleInfo.setCrePerName(basEmployee.getEmplName());
         }

         this.alleInfoService.insertAlleInfoList(alleInfoList);
         List<String> allergyNameList = (List)alleInfoList.stream().filter((t) -> StringUtils.isNotBlank(t.getAlleName())).map((t) -> t.getAlleName()).distinct().collect(Collectors.toList());
         String allergyNames = StringUtils.join(allergyNameList, " ");
         PatAtt patAtt = new PatAtt();
         patAtt.setAllergyName(allergyNames);
         patAtt.setAdmissionNo(visitinfoVo.getPatientId());
         this.patAttMapper.updatePatAtt(patAtt);
      } else {
         PatAtt patAtt = new PatAtt();
         patAtt.setAllergyName((String)null);
         patAtt.setAdmissionNo(visitinfoVo.getPatientId());
         this.patAttMapper.updatePatAtt(patAtt);
      }

      Visitinfo insertInfo = new Visitinfo();
      BeanUtils.copyProperties(visitinfoVo, insertInfo);
      insertInfo.setBedNo((String)null);
      this.visitinfoMapper.updateVisitinfo(insertInfo);
   }

   public int deleteVisitinfoByIds(String[] patientIds) {
      return this.visitinfoMapper.deleteVisitinfoByIds(patientIds);
   }

   public int deleteVisitinfoById(String patientId) {
      return this.visitinfoMapper.deleteVisitinfoById(patientId);
   }

   public void syncData(List hisDataList) throws Exception {
      int i = 0;

      for(Visitinfo temp : hisDataList) {
         this.log.info("i-> {}", i++);

         try {
            this.visitinfoMapper.insertVisitinfo(temp);
         } catch (Exception e) {
            this.log.info("对象数值=====  {}", temp.toString());
            this.log.error("", e.getMessage());
            throw new Exception(e.getMessage());
         }
      }

   }

   public List selectVisitinfoVoList(VisitinfoVo visitinfoVo) throws Exception {
      String orgCode = SecurityUtils.getLoginUser().getUser().getDept().getOrgCode();
      String userCode = SecurityUtils.getUsername();
      List<VisitinfoVo> result = new ArrayList(1);
      String orderFlag = "10-" + visitinfoVo.getBrowsType();
      TdPaItemDocQuery itemDocQuery = new TdPaItemDocQuery(orderFlag, orgCode, userCode);
      List<TdPaItemDocQuery> itemDocQueryList = this.tdPaItemDocQueryService.selectTdPaItemDocQueryList(itemDocQuery);
      if (StringUtils.isBlank(visitinfoVo.getOrderField()) && StringUtils.isBlank(visitinfoVo.getOrderDesc())) {
         if (CollectionUtils.isNotEmpty(itemDocQueryList) && StringUtils.isNotBlank(((TdPaItemDocQuery)itemDocQueryList.get(0)).getQueryStatus())) {
            TdPaItemDocQuery itemDocQuery1 = (TdPaItemDocQuery)itemDocQueryList.get(0);
            String queryStatus = itemDocQuery1.getQueryStatus();
            String[] queryStatusArr = queryStatus.split("-");
            visitinfoVo.setOrderField(queryStatusArr[0]);
            visitinfoVo.setOrderDesc(queryStatusArr[1]);
         }
      } else if (StringUtils.isNotBlank(visitinfoVo.getOrderField()) && StringUtils.isBlank(visitinfoVo.getOrderDesc())) {
         visitinfoVo.setOrderField((String)null);
         visitinfoVo.setOrderDesc((String)null);
         if (CollectionUtils.isNotEmpty(itemDocQueryList) && StringUtils.isNotBlank(((TdPaItemDocQuery)itemDocQueryList.get(0)).getQueryStatus())) {
            this.tdPaItemDocQueryService.deleteTdPaItemDocQueryById(((TdPaItemDocQuery)itemDocQueryList.get(0)).getId());
         }
      } else {
         String queryStatus = visitinfoVo.getOrderField() + "-" + visitinfoVo.getOrderDesc();
         itemDocQuery.setQueryStatus(queryStatus);
         this.tdPaItemDocQueryService.insertTdPaItemDocQuery(itemDocQuery);
      }

      String syncStr = this.sysEmrConfigService.selectSysEmrConfigByKey("0057");
      visitinfoVo.setIsInpNoOrder(syncStr);
      switch (visitinfoVo.getBrowsType()) {
         case "1":
            result = this.selectAdminPatList(visitinfoVo);
            break;
         case "2":
            result = this.selectDeptPatientList(visitinfoVo);
            break;
         case "3":
            result = this.selectOutHosPatList(visitinfoVo);
            break;
         case "4":
            result = this.selectImpPatList(visitinfoVo);
            break;
         case "5":
            result = this.selectPatientGroupList(visitinfoVo);
            break;
         case "6":
            result = this.selectOutHosAndNotFiledPatList(visitinfoVo);
      }

      if (StringUtils.isNotBlank(visitinfoVo.getOrderField()) && visitinfoVo.getOrderField().equals("inhosDayNum")) {
         if (StringUtils.isNotBlank(visitinfoVo.getOrderDesc())) {
            result = visitinfoVo.getOrderDesc().equals("asc") ? (List)result.stream().sorted(Comparator.comparingInt(VisitinfoVo::getInhosDayNum)).collect(Collectors.toList()) : (List)result.stream().sorted(Comparator.comparingInt(VisitinfoVo::getInhosDayNum).reversed()).collect(Collectors.toList());
         } else {
            result = (List)result.stream().sorted(Comparator.comparingInt(VisitinfoVo::getInhosDayNum)).collect(Collectors.toList());
         }
      }

      return result;
   }

   public void setColor(List visitinfoVos) throws Exception {
      Map<String, List<VisitinfoVo>> colorMap = this.selectColorplan();

      for(VisitinfoVo visitinfoVo : visitinfoVos) {
         List<VisitinfoVo> colorList = (List)colorMap.get("2");
         if (StringUtils.isNotEmpty(visitinfoVo.getNgCd())) {
            List<VisitinfoVo> color = (List)colorList.stream().filter((t) -> visitinfoVo.getNgCd().equals(t.getColorTypeCd())).collect(Collectors.toList());
            if (color != null && !color.isEmpty()) {
               visitinfoVo.setColorValue(((VisitinfoVo)color.get(0)).getColorValue());
            }
         }
      }

   }

   public List selectAdminPatList(VisitinfoVo visitinfoVo) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      String deptCd = user.getDept().getDeptCode();
      visitinfoVo.setResDocCd(user.getBasEmployee().getEmplNumber());
      visitinfoVo.setDeptCd(deptCd);
      PageDomain pageDomain = TableSupport.buildPageRequest();
      Integer pageNum = pageDomain.getPageNum();
      Integer pageSize = pageDomain.getPageSize();
      if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize)) {
         String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
         PageHelper.startPage(pageNum, pageSize, orderBy);
      }

      List<VisitinfoVo> list = this.visitinfoMapper.selectAdminPatList(visitinfoVo);
      VisitinfoVo listTotal = this.visitinfoMapper.selectAdminPatListTotal(visitinfoVo);
      int total = listTotal.getTotal();
      this.setVisitinfoDictLabel(list);
      List<VisitinfoVo> resultList = this.setDocPowerType(list, total);
      this.setColor(resultList);
      return resultList;
   }

   public List selectDeptPatientList(VisitinfoVo visitinfoVo) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      String deptCd = user.getDept().getDeptCode();
      visitinfoVo.setDeptCd(deptCd);
      PageDomain pageDomain = TableSupport.buildPageRequest();
      Integer pageNum = pageDomain.getPageNum();
      Integer pageSize = pageDomain.getPageSize();
      if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize)) {
         String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
         PageHelper.startPage(pageNum, pageSize, orderBy);
      }

      List<VisitinfoVo> list = this.visitinfoMapper.selectDeptPatientList(visitinfoVo);
      VisitinfoVo listTotal = this.visitinfoMapper.selectDeptPatientListTotal(visitinfoVo);
      int total = listTotal.getTotal();
      this.setVisitinfoDictLabel(list);
      List<VisitinfoVo> resultList = this.setDocPowerType(list, total);
      this.setColor(resultList);
      return resultList;
   }

   public List selectOutHosPatList(VisitinfoVo visitinfoVo) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      String deptCd = user.getDept().getDeptCode();
      visitinfoVo.setDeptCd(deptCd);
      if (visitinfoVo.getOutEndTime() != null && visitinfoVo.getOutStartTime() != null) {
         Calendar c = Calendar.getInstance();
         c.setTime(visitinfoVo.getOutEndTime());
         c.add(5, 1);
         visitinfoVo.setOutEndTime(c.getTime());
      }

      PageDomain pageDomain = TableSupport.buildPageRequest();
      Integer pageNum = pageDomain.getPageNum();
      Integer pageSize = pageDomain.getPageSize();
      if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize)) {
         String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
         PageHelper.startPage(pageNum, pageSize, orderBy);
      }

      List<VisitinfoVo> list = this.visitinfoMapper.selectOutHosPatList(visitinfoVo);
      VisitinfoVo listTotal = this.visitinfoMapper.selectOutHosPatListTotal(visitinfoVo);
      int total = listTotal.getTotal();
      this.setVisitinfoDictLabel(list);
      List<VisitinfoVo> resultList = this.setDocPowerType(list, total);
      this.setColor(resultList);
      return resultList;
   }

   public List selectOutHosAndNotFiledPatList(VisitinfoVo visitinfoVo) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      String deptCd = user.getDept().getDeptCode();
      visitinfoVo.setDeptCd(deptCd);
      if (visitinfoVo.getOutEndTime() != null && visitinfoVo.getOutStartTime() != null) {
         Calendar c = Calendar.getInstance();
         c.setTime(visitinfoVo.getOutEndTime());
         c.add(5, 1);
         visitinfoVo.setOutEndTime(c.getTime());
      }

      PageDomain pageDomain = TableSupport.buildPageRequest();
      Integer pageNum = pageDomain.getPageNum();
      Integer pageSize = pageDomain.getPageSize();
      if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize)) {
         String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
         PageHelper.startPage(pageNum, pageSize, orderBy);
      }

      List<VisitinfoVo> list = this.visitinfoMapper.selectOutHosAndNotFiledPatList(visitinfoVo);
      VisitinfoVo listTotal = this.visitinfoMapper.selectOutHosAndNotFiledPatListTotal(visitinfoVo);
      int total = listTotal.getTotal();
      this.setVisitinfoDictLabel(list);
      List<VisitinfoVo> resultList = this.setDocPowerType(list, total);
      this.setColor(resultList);
      return resultList;
   }

   public List selectImpPatList(VisitinfoVo visitinfoVo) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      String deptCd = user.getDept().getDeptCode();
      visitinfoVo.setDeptCd(deptCd);
      visitinfoVo.setImpDeptCd(deptCd);
      visitinfoVo.setImpDocCd(user.getBasEmployee().getEmplNumber());
      PageDomain pageDomain = TableSupport.buildPageRequest();
      Integer pageNum = pageDomain.getPageNum();
      Integer pageSize = pageDomain.getPageSize();
      if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize)) {
         String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
         PageHelper.startPage(pageNum, pageSize, orderBy);
      }

      List<VisitinfoVo> list = this.visitinfoMapper.selectImpPatList(visitinfoVo);
      VisitinfoVo listTotal = this.visitinfoMapper.selectImpPatListTotal(visitinfoVo);
      int total = listTotal.getTotal();
      this.setVisitinfoDictLabel(list);
      List<VisitinfoVo> resultList = this.setDocPowerType(list, total);
      this.setColor(resultList);
      return resultList;
   }

   public List selectPatientGroupList(VisitinfoVo visitinfoVo) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      String deptCd = user.getDept().getDeptCode();
      visitinfoVo.setDeptCd(deptCd);
      visitinfoVo.setResDocCd(user.getUserName());
      PageDomain pageDomain = TableSupport.buildPageRequest();
      Integer pageNum = pageDomain.getPageNum();
      Integer pageSize = pageDomain.getPageSize();
      if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize)) {
         String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
         PageHelper.startPage(pageNum, pageSize, orderBy);
      }

      List<VisitinfoVo> list = this.visitinfoMapper.selectPatientGroupList(visitinfoVo);
      VisitinfoVo listTotal = this.visitinfoMapper.selectPatientGroupListTotal(visitinfoVo);
      int total = listTotal.getTotal();
      this.setVisitinfoDictLabel(list);
      List<VisitinfoVo> visitinfoVoList = this.setDocPowerType(list, total);
      if (visitinfoVoList != null && !visitinfoVoList.isEmpty()) {
         visitinfoVoList = (List)visitinfoVoList.stream().filter((s) -> s.getOutTime() == null).collect(Collectors.toList());

         for(VisitinfoVo vo : visitinfoVoList) {
            Boolean bool = this.selectIsPrivacyLevel(vo.getPatientId());
            if (bool) {
               vo.setDocPowerType("1");
            } else {
               vo.setDocPowerType("2");
            }
         }
      }

      this.setColor(visitinfoVoList);
      return visitinfoVoList;
   }

   private List setDocPowerType(List visitinfoVoList, int total) throws Exception {
      Date newDate = this.commonService.getDbSysdate();
      String newDay = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, this.commonService.getDbSysdate());

      for(VisitinfoVo vo : visitinfoVoList) {
         vo.setTotal(total);
         newDate = this.commonService.getDbSysdate();
         if (vo.getOutTime() != null) {
            newDate = vo.getOutTime();
         }

         int dayNum = DateUtils.getInHosDays(vo.getInhosTime(), newDate);
         if (DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, vo.getInhosTime()).equals(newDay)) {
            dayNum = 0;
         }

         vo.setInhosDayNum(dayNum);
         String age = AgeUtil.getAgeStr(vo.getAgeY(), vo.getAgeM(), vo.getAgeD(), vo.getAgeH(), vo.getAgeMi());
         vo.setAge(age);
         Boolean bool = this.selectIsPrivacyLevel(vo.getPatientId());
         if (bool) {
            vo.setDocPowerType("1");
         } else {
            vo.setDocPowerType("2");
         }
      }

      return visitinfoVoList;
   }

   private void setVisitinfoDictLabel(List list) throws Exception {
      if (list != null && !list.isEmpty()) {
         String[] dictStr = new String[]{"s035", "s016"};
         List<SysDictData> dictList = this.sysDictDataService.selectDictDataListByType(dictStr);
         List<SysDictData> marriageList = (List)dictList.stream().filter((s) -> s.getDictType().equals("s035")).collect(Collectors.toList());
         List<SysDictData> condList = (List)dictList.stream().filter((s) -> s.getDictType().equals("s016")).collect(Collectors.toList());
         List<AlleInfoVo> alleInfoListParm = new ArrayList(list.size());
         list.stream().forEach((t) -> {
            AlleInfoVo alleInfo = new AlleInfoVo();
            alleInfo.setPatientMainId(t.getPatientMainId());
            alleInfo.setPatientId(t.getPatientId());
            alleInfo.setCaseNo(t.getRecordNo());
            alleInfoListParm.add(alleInfo);
         });
         List<AlleInfo> alleInfoList = this.alleInfoService.selectAlleInfosByInpNoOrMainId(alleInfoListParm);
         VisitinfoVo visitinfoVo1 = new VisitinfoVo();
         List<String> patientList = (List)list.stream().filter((t) -> StringUtils.isNotBlank(t.getPatientId())).map((t) -> t.getPatientId()).collect(Collectors.toList());
         visitinfoVo1.setPatientIdList(patientList);
         List<VisitinfoVo> visitinfoVoList = this.medicalinformationMapper.selectPatientInfoOther(visitinfoVo1);
         List<SysDictData> sysDictDataList = this.sysDictDataService.selectDictDataByType("s102");

         for(VisitinfoVo infoVo : list) {
            if (visitinfoVoList != null && visitinfoVoList.size() > 0) {
               List<VisitinfoVo> visitinfo = (List)visitinfoVoList.stream().filter((s) -> s.getAdmissionNo().equals(infoVo.getInpNo())).collect(Collectors.toList());
               if (visitinfo != null && visitinfo.size() > 0) {
                  infoVo.setMrState(((VisitinfoVo)visitinfo.get(0)).getMrState());
                  infoVo.setClosingDate(((VisitinfoVo)visitinfo.get(0)).getClosingDate());
                  infoVo.setFinalFilingDate(((VisitinfoVo)visitinfo.get(0)).getFinalFilingDate());
                  if (StringUtils.isNotBlank(((VisitinfoVo)visitinfo.get(0)).getMrState())) {
                     List<SysDictData> sysDictData = (List)sysDictDataList.stream().filter((s) -> s.getDictValue().equals(((VisitinfoVo)visitinfo.get(0)).getMrState())).collect(Collectors.toList());
                     if (sysDictData != null && sysDictData.size() > 0) {
                        infoVo.setMrStateName(((SysDictData)sysDictData.get(0)).getDictLabel());
                     }
                  }
               }
            }

            for(SysDictData sysDictData : marriageList) {
               if (sysDictData.getDictValue().equals(infoVo.getMarStaCd())) {
                  infoVo.setMarSta(sysDictData.getDictLabel());
               }
            }

            for(SysDictData sysDictData : condList) {
               if (sysDictData.getDictValue().equals(infoVo.getInhosCondCd())) {
                  infoVo.setInhosCond(sysDictData.getDictLabel());
               }
            }

            String allInfo = "";

            for(AlleInfo alleInfo : alleInfoList != null && !alleInfoList.isEmpty() ? (List)alleInfoList.stream().filter((s) -> s.getPatientMainId().equals(infoVo.getPatientMainId()) || s.getPatientId().equals(infoVo.getPatientId())).collect(Collectors.toList()) : new ArrayList()) {
               allInfo = allInfo + alleInfo.getAlleName() + ",";
            }

            infoVo.setAlleInfo(StringUtils.isEmpty(allInfo) ? allInfo : allInfo.substring(0, allInfo.length() - 1));
         }
      }

   }

   private void setConsState(List visitinfoVoList) throws Exception {
      TdCaConsApplyVo tdCaConsApplyVo = new TdCaConsApplyVo();
      List<String> stateList = new ArrayList();
      stateList.add("01");
      stateList.add("02");
      stateList.add("03");
      stateList.add("04");
      tdCaConsApplyVo.setStateList(stateList);
      List<TdCaConsApplyVo> tdCaConsApplyList = this.tdCaConsApplyService.selectTdCaConsApplyList(tdCaConsApplyVo);
      Map<String, List<TdCaConsApplyVo>> map = (Map<String, List<TdCaConsApplyVo>>)(tdCaConsApplyList != null ? (Map)tdCaConsApplyList.stream().collect(Collectors.groupingBy((s) -> s.getPatientId())) : new HashMap());

      for(VisitinfoVo vo : visitinfoVoList) {
         List<TdCaConsApplyVo> tdCaConsApplies = (List)map.get(vo.getPatientId());
         if (tdCaConsApplies != null && !tdCaConsApplies.isEmpty()) {
            tdCaConsApplies.sort(Comparator.comparing(TdCaConsApply::getCreDate).reversed());
            vo.setConsState(((TdCaConsApplyVo)tdCaConsApplies.get(0)).getState());
            vo.setConsId(((TdCaConsApplyVo)tdCaConsApplies.get(0)).getId());
            vo.setConsEmrId(((TdCaConsApplyVo)tdCaConsApplies.get(0)).getMrFileId());
         } else {
            vo.setConsState("00");
         }
      }

   }

   public Map selectColorplan() throws Exception {
      List<VisitinfoVo> list = this.visitinfoMapper.selectColorplan();
      return (Map)list.stream().collect(Collectors.groupingBy(VisitinfoVo::getPlanType));
   }

   public List selectVisitinfosById(String patientId) {
      return this.visitinfoMapper.selectVisitinfosById(patientId);
   }

   public List selectVisitinfosByPatientId(String patientId, String visitNo) {
      return this.visitinfoMapper.selectVisitinfosByPatientId(patientId, visitNo);
   }

   public VisitinfoVo selectvistDiagInfo(String patientId) throws Exception {
      VisitinfoVo visitinfoVo = this.visitinfoMapper.selectVisitinfoById(patientId);
      if (visitinfoVo != null) {
         List<DiagInfo> diagList = this.diagInfoService.selectDiagInfoByPatientId(patientId);
         String diagInfos = "";

         for(DiagInfo diagInfo : diagList) {
            diagInfos = diagInfos + diagInfo.getDiagName() + ",";
         }

         visitinfoVo.setDiagInfos(diagInfos);
      }

      return visitinfoVo;
   }

   public Visitinfo selectVisitinfoByInpNo(String inpNo) {
      return this.visitinfoMapper.selectPatientByInpNo(inpNo);
   }

   public List selectVisitinfoPersonalList(VisitinfoPersonalVo visitinfoPersonalVo) throws Exception {
      return this.visitinfoMapper.selectVisitinfoPersonalList(visitinfoPersonalVo);
   }

   public List inspectReportTimes(String patientId) throws Exception {
      List<VisitinfoVo> list = new ArrayList();
      Personalinfo personalinfo = this.personalinfoService.selectPersonalinfoById(patientId);
      if (personalinfo != null) {
         list = this.visitinfoMapper.inspectReportTimes(personalinfo.getPatientMainId(), patientId);

         for(VisitinfoVo visitinfoVo : list) {
            VisitinfoVo visitinfo = this.selectvistDiagInfo(visitinfoVo.getPatientId());
            visitinfoVo.setDiagInfos(visitinfo.getDiagInfos());
         }
      }

      return list;
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void syncAddData(List hisDataList, SqlVo sqlVo) throws Exception {
      List<PatEvent> patEventList = new ArrayList();
      SqlVo param = new SqlVo();
      param.setTableName("T_AR_MEDICALINFORMATION");
      param.setDeptCode(sqlVo.getDeptCode());
      List<Visitinfo> emrData = this.visitinfoMapper.selectSyncVisitinfoList(param);
      List<String> emrInpNoList = (List)emrData.stream().map((s) -> s.getInpNo()).collect(Collectors.toList());
      List<String> paramInpNoList = (List)hisDataList.stream().map((s) -> s.get("admission_no").toString()).collect(Collectors.toList());
      List<Visitinfo> outPatList = this.visitinfoMapper.selectSyncVisitinfoOutTimeList(paramInpNoList, sqlVo.getDeptCode());
      List<String> outInpNoList = (List)outPatList.stream().map((s) -> s.getInpNo()).collect(Collectors.toList());
      if ("T_AR_MEDICALINFORMATION".equals(sqlVo.getTableName())) {
         for(Map map : hisDataList) {
            String inpno = map.get("admission_no").toString();
            if (!emrInpNoList.contains(inpno)) {
               Date date = (Date)map.get("hospitalized_date");
               PatEvent patEvent = new PatEvent(map, "01", "入院", date, (Date)null);
               patEventList.add(patEvent);
            }

            this.visitinfoMapper.deleteVisitinfoByTableName(sqlVo.getTableName(), inpno);
            map.put("tableName", sqlVo.getTableName());
            this.visitinfoMapper.insertMapList(map);
         }
      } else {
         if ("T_AR_MEDICALINFORMATION_DAY".equals(sqlVo.getTableName())) {
            this.visitinfoMapper.deleteVisitinfoByTableName(sqlVo.getTableName(), (String)null);
         }

         for(Map map : hisDataList) {
            map.put("tableName", sqlVo.getTableName());
            if (!outInpNoList.contains(map.get("admission_no").toString())) {
               PatEvent patEvent = new PatEvent(map, "02", "出院", DateUtils.parseDate(map.get("leave_hospital_date")), (Date)null);
               patEventList.add(patEvent);
               if (DateUtils.getDateHours(DateUtils.parseDate(map.get("leave_hospital_date")), DateUtils.parseDate(map.get("hospitalized_date"))) < 24) {
                  patEvent = new PatEvent(map, "04", "24小时出院", DateUtils.parseDate(map.get("leave_hospital_date")), (Date)null);
                  patEventList.add(patEvent);
               }
            } else {
               List<Visitinfo> dept = (List)outPatList.stream().filter((s) -> s.getDeptCd().equals(map.get("department_no").toString())).collect(Collectors.toList());
               if (!map.get("department_no").toString().equals(((Visitinfo)dept.get(0)).getDeptCd())) {
                  PatEvent patEvent = new PatEvent(map, "19", "入科", DateUtils.parseDate(map.get("entry_date")), DateUtils.parseDate(map.get("entry_date")));
                  patEventList.add(patEvent);
               }
            }

            if ("T_AR_MEDICALINFORMATION_H".equals(sqlVo.getTableName())) {
               this.visitinfoMapper.deleteVisitinfoByTableName(sqlVo.getTableName(), map.get("admission_no").toString());
            }

            this.visitinfoMapper.insertMapList(map);
         }
      }

      if (!patEventList.isEmpty()) {
         this.patEventService.insertList(patEventList);
      }

   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void syncDataMap(List mapList, String tableName) throws Exception {
      if (mapList != null && !mapList.isEmpty()) {
         this.visitinfoMapper.deleteVisitinfoByTableName(tableName, (String)null);

         for(Map map : mapList) {
            map.put("tableName", tableName);
            this.visitinfoMapper.insertMapList(map);
         }
      }

   }

   public List selectSevenDayInfo(Visitinfo visitinfo) throws Exception {
      String startTime = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, visitinfo.getInhosTime());
      String endTime = visitinfo.getOutTime() != null ? DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, visitinfo.getOutTime()) : DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, new Date());
      List<WeeklyVo> weeklyVoList = WeekUtil.getWeekList(startTime, endTime);
      return weeklyVoList;
   }

   public TimeLineDataVo selectTimeInfo(String startDate, String endDate, List dayVoList, Visitinfo visitinfo) throws Exception {
      TimeLineDataVo timeLineDataVo = new TimeLineDataVo();
      Long baseId = this.getBaseId(visitinfo.getInpNo());
      String timelineTimesStr = this.sysEmrConfigService.selectSysEmrConfigByKey("0011");
      JSONArray timelineTimeArr = (JSONArray)JSONArray.parse(timelineTimesStr);
      List<String> timelineTimes = JSONObject.parseArray(timelineTimeArr.toJSONString(), String.class);
      List<String> timeList = new ArrayList(1);
      dayVoList.forEach((t) -> timeList.addAll(timelineTimes));
      timeLineDataVo.setTimeList(timeList);
      List<Index> indexList = this.getIndex(visitinfo.getPatientId(), DateUtils.parseDate(startDate, new String[]{DateUtils.YYYY_MM_DD_HH_MM_SS}), DateUtils.parseDate(endDate, new String[]{DateUtils.YYYY_MM_DD_HH_MM_SS}));
      this.getTimeInfoIndex(indexList, timeLineDataVo, dayVoList);
      List<OrderSearchVo> doctorderVoList = this.getHisDocorderVoList(visitinfo.getPatientId(), startDate, endDate);
      this.getTimeInfoDocList(doctorderVoList, timeLineDataVo, dayVoList);
      SyncDatasource syncDatasource1 = this.syncDatasourceService.selectSyncDatasourceByCode(DataSourceType.physignValueListCurDayRange.toString());
      PhysignValueVo physignValueVo = new PhysignValueVo(baseId, startDate, endDate);
      physignValueVo.setSqlStr(syncDatasource1.getQuerySql());
      List<PhysignValueVo> physignValueVoList = this.physignValueService.getValueRangeList(physignValueVo);
      this.getTimeInfoPhysignList(physignValueVoList, timeLineDataVo, dayVoList, timelineTimes);
      List<String> dayList = (List)dayVoList.stream().map((t) -> t.getDayDate()).collect(Collectors.toList());
      timeLineDataVo.setDayList(dayList);
      List<Integer> dayNumList = (List)dayVoList.stream().map((t) -> {
         int dayNum = 0;

         try {
            Date InHosDate = DateUtils.parseDate(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, visitinfo.getInhosTime()), new String[]{DateUtils.YYYY_MM_DD});
            dayNum = DateUtils.getDiffDay(DateUtils.parseDate(t.getDayDate(), new String[]{DateUtils.YYYY_MM_DD}), InHosDate) + 1;
         } catch (ParseException e) {
            throw new CustomException(e.getMessage());
         }

         return dayNum;
      }).collect(Collectors.toList());
      timeLineDataVo.setDayNumList(dayNumList);
      List<OperatRecord> operatRecordWeekList = this.getOperatRecord(visitinfo.getPatientId(), (String)null, endDate);
      this.getTimeInfoOperatRecord(operatRecordWeekList, timeLineDataVo, dayVoList);
      return timeLineDataVo;
   }

   private void getTimeInfoOperatRecord(List operatRecordWeekList, TimeLineDataVo timeLineDataVo, List dayVoList) throws Exception {
      List<OperatRecord> operatRecordList = new ArrayList(1);
      List<Integer> operatDayList = new ArrayList(1);
      int operatDay = -1;

      for(DayVo dayVo : dayVoList) {
         if (operatRecordWeekList != null && operatRecordWeekList.size() > 0) {
            List<OperatRecord> operatRecords = (List)operatRecordWeekList.stream().filter((o) -> DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, o.getOprBeginDatetime()).equals(dayVo.getDayDate())).collect(Collectors.toList());
            if (operatRecords != null && operatRecords.size() > 0) {
               operatRecordList.add(operatRecords.get(0));
               ++operatDay;
            } else {
               operatRecordList.add((Object)null);
            }
         } else {
            operatRecordList.add((Object)null);
         }

         if (operatDay > 0 && operatDay < 15) {
            operatDayList.add(operatDay);
            ++operatDay;
         }
      }

      if (operatRecordWeekList != null && !operatRecordWeekList.isEmpty() && operatDayList.isEmpty()) {
         OperatRecord temp = (OperatRecord)operatRecordWeekList.get(0);

         for(DayVo dayVo : dayVoList) {
            operatDay = DateUtils.getDiffDay(DateUtils.parseDate(DateUtils.YYYY_MM_DD, new String[]{dayVo.getDayDate()}), temp.getOprBeginDatetime());
            if (operatDay > 0 && operatDay < 15) {
               operatDayList.add(operatDay);
            } else {
               operatDayList.add((Object)null);
            }
         }
      }

      if (operatDayList.isEmpty()) {
         for(DayVo dayVo : dayVoList) {
            operatDayList.add((Object)null);
         }
      }

      timeLineDataVo.setOperatRecordList(operatRecordList);
      timeLineDataVo.setOperatDayList(operatDayList);
   }

   private void getTimeInfoIndex(List indexList, TimeLineDataVo timeLineDataVo, List dayVoList) {
      int indexDayMaxSize = 1;

      for(DayVo dayVo : dayVoList) {
         if (indexList != null && indexList.size() > 0) {
            List<Index> indices = (List)indexList.stream().filter((ix) -> DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, ix.getCreDate()).equals(dayVo.getDayDate())).collect(Collectors.toList());
            if (indices != null && indices.size() > 0) {
               dayVo.setIndexList(indices);
            }

            indexDayMaxSize = indices.size() > indexDayMaxSize ? indices.size() : indexDayMaxSize;
         }
      }

      List<List<Index>> timeLineIndexList = new ArrayList(1);

      for(int i = 0; i < indexDayMaxSize; ++i) {
         List<Index> dayIndexList = new ArrayList(7);

         for(DayVo dayVo : dayVoList) {
            List<Index> indexListTemp = dayVo.getIndexList();
            Index indexTemp = indexListTemp != null && indexListTemp.size() > i ? (Index)indexListTemp.get(i) : null;
            dayIndexList.add(indexTemp);
         }

         timeLineIndexList.add(dayIndexList);
      }

      timeLineDataVo.setIndexList(timeLineIndexList);
   }

   private void getTimeInfoDocList(List doctorderVoList, TimeLineDataVo timeLineDataVo, List dayVoList) throws Exception {
      int testDayMaxSize = 1;
      int examDayMaxSize = 1;
      int drugDayMaxSize = 1;
      int nursingDayMaxSize = 1;
      int foodDayMaxSize = 1;
      String maTypeTest = this.sysEmrConfigService.selectSysEmrConfigByKey("0005");
      String maTypeExam = this.sysEmrConfigService.selectSysEmrConfigByKey("0006");
      String maTypeDrug = this.sysEmrConfigService.selectSysEmrConfigByKey("0007");
      String maTypeNursing = this.sysEmrConfigService.selectSysEmrConfigByKey("0008");
      String maTypeFood = this.sysEmrConfigService.selectSysEmrConfigByKey("0009");

      for(DayVo dayVo : dayVoList) {
         if (doctorderVoList != null && doctorderVoList.size() > 0) {
            List<OrderSearchVo> doctorderVos = (List)doctorderVoList.stream().filter((d) -> DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, d.getOrderStartTime()).equals(dayVo.getDayDate())).collect(Collectors.toList());
            if (doctorderVos != null && doctorderVos.size() > 0) {
               Map<String, List<OrderSearchVo>> map = (Map)doctorderVos.stream().collect(Collectors.groupingBy(OrderSearchVo::getOrderClassCode));
               List<OrderSearchVo> testList = (List)map.get(maTypeTest);
               List<OrderSearchVo> examList = (List)map.get(maTypeExam);
               List<OrderSearchVo> drugList = (List)map.get(maTypeDrug);
               List<OrderSearchVo> nursingList = (List)map.get(maTypeNursing);
               List<OrderSearchVo> foodList = (List)map.get(maTypeFood);
               testDayMaxSize = testList != null && testList.size() > testDayMaxSize ? testList.size() : testDayMaxSize;
               examDayMaxSize = examList != null && examList.size() > examDayMaxSize ? examList.size() : examDayMaxSize;
               drugDayMaxSize = drugList != null && drugList.size() > drugDayMaxSize ? drugList.size() : drugDayMaxSize;
               nursingDayMaxSize = nursingList != null && nursingList.size() > nursingDayMaxSize ? nursingList.size() : nursingDayMaxSize;
               foodDayMaxSize = foodList != null && foodList.size() > foodDayMaxSize ? foodList.size() : foodDayMaxSize;
               dayVo.setMap(map);
            }
         }
      }

      List<List<OrderSearchVo>> testList = this.getDocTypeList(testDayMaxSize, dayVoList, maTypeTest);
      timeLineDataVo.setTestList(testList);
      List<List<OrderSearchVo>> examList = this.getDocTypeList(examDayMaxSize, dayVoList, maTypeExam);
      timeLineDataVo.setExamList(examList);
      List<List<OrderSearchVo>> drugList = this.getDocTypeList(drugDayMaxSize, dayVoList, maTypeDrug);
      timeLineDataVo.setDrugList(drugList);
      List<List<OrderSearchVo>> nursingList = this.getDocTypeList(nursingDayMaxSize, dayVoList, maTypeNursing);
      timeLineDataVo.setNursingList(nursingList);
      List<List<OrderSearchVo>> foodList = this.getDocTypeList(foodDayMaxSize, dayVoList, maTypeFood);
      timeLineDataVo.setFoodList(foodList);
   }

   private List getDocTypeList(int dayMaxSize, List dayVoList, String maType) {
      List<List<OrderSearchVo>> testList = new ArrayList(1);

      for(int i = 0; i < dayMaxSize; ++i) {
         List<OrderSearchVo> dayTestList = new ArrayList(7);

         for(DayVo dayVo : dayVoList) {
            Map<String, List<OrderSearchVo>> map = dayVo.getMap();
            if (map != null) {
               List<OrderSearchVo> docListTemp = (List)map.get(maType);
               OrderSearchVo docTemp = docListTemp != null && docListTemp.size() > i ? (OrderSearchVo)docListTemp.get(i) : new OrderSearchVo();
               dayTestList.add(docTemp);
            } else {
               dayTestList.add(new OrderSearchVo());
            }
         }

         testList.add(dayTestList);
      }

      return testList;
   }

   private void getTimeInfoPhysignList(List physignValueVoList, TimeLineDataVo timeLineDataVo, List dayVoList, List timelineTimes) throws Exception {
      int inDayMaxSize = 1;
      int outMaxSize = 1;
      int stoolDayMaxSize = 1;
      int urinateDayMaxSize = 1;
      int bloodpressureDayMaxSize = 1;
      int weightDayMaxSize = 1;
      String physignTypeIn = "in";
      String physignTypeOut = "out";
      String physignTypeStool = "stool";
      String physignTypeUrinate = "urinate";
      String physignTypeBloodpressure1 = "bloodpressure1";
      String physignTypeBreathing = "breathing";
      String physignTypePulse = "pulse";
      String physignTypeTemperature = "temperature";
      String physignTypeWeight = "weight";

      for(DayVo dayVo : dayVoList) {
         if (physignValueVoList != null && !physignValueVoList.isEmpty()) {
            List<PhysignValueVo> physignValueVos = (List)physignValueVoList.stream().filter((p) -> DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, p.getCurDay()).equals(dayVo.getDayDate())).collect(Collectors.toList());
            if (physignValueVos != null && physignValueVos.size() > 0) {
               Map<String, List<PhysignValueVo>> map = (Map)physignValueVos.stream().collect(Collectors.groupingBy(PhysignValueVo::getTzNameCode));
               List<PhysignValueVo> inList = (List)map.get(physignTypeIn);
               List<PhysignValueVo> outList = (List)map.get(physignTypeOut);
               List<PhysignValueVo> stoolList = (List)map.get(physignTypeStool);
               List<PhysignValueVo> urinateList = (List)map.get(physignTypeUrinate);
               List<PhysignValueVo> weightList = (List)map.get(physignTypeWeight);
               inDayMaxSize = inList != null && inList.size() > inDayMaxSize ? inList.size() : inDayMaxSize;
               outMaxSize = outList != null && outList.size() > outMaxSize ? outList.size() : outMaxSize;
               stoolDayMaxSize = stoolList != null && stoolList.size() > stoolDayMaxSize ? stoolList.size() : stoolDayMaxSize;
               urinateDayMaxSize = urinateList != null && urinateList.size() > urinateDayMaxSize ? urinateList.size() : urinateDayMaxSize;
               weightDayMaxSize = weightList != null && weightList.size() > weightDayMaxSize ? weightList.size() : weightDayMaxSize;
               dayVo.setPhysignValueVoListMap(map);
               dayVo.setPhysignValueVoList(physignValueVos);
            }
         }
      }

      List<List<PhysignValueVo>> inList = this.getPhysignTypeList(inDayMaxSize, dayVoList, physignTypeIn);
      timeLineDataVo.setInList(inList);
      List<List<PhysignValueVo>> outList = this.getPhysignTypeList(outMaxSize, dayVoList, physignTypeOut);
      timeLineDataVo.setOutList(outList);
      List<List<PhysignValueVo>> stoolList = this.getPhysignTypeList(stoolDayMaxSize, dayVoList, physignTypeStool);
      timeLineDataVo.setStoolList(stoolList);
      List<List<PhysignValueVo>> urinateList = this.getPhysignTypeList(urinateDayMaxSize, dayVoList, physignTypeUrinate);
      timeLineDataVo.setUrinateList(urinateList);
      List<List<PhysignValueVo>> weightList = this.getPhysignTypeList(weightDayMaxSize, dayVoList, physignTypeWeight);
      timeLineDataVo.setWeightList(weightList);
      List<List<PhysignValueVo>> bloodpressureList = this.getPhysignBloodpressureList(bloodpressureDayMaxSize, dayVoList, physignTypeBloodpressure1);
      timeLineDataVo.setBloodPressureList(bloodpressureList);
      List<List<PhysignValueVo>> breathingList = this.getPhysignTzList(timelineTimes, dayVoList, physignTypeBreathing);
      timeLineDataVo.setBreathingList(breathingList);
      List<List<PhysignValueVo>> pulseList = this.getPhysignTzList(timelineTimes, dayVoList, physignTypePulse);
      timeLineDataVo.setPulseList(pulseList);
      List<List<PhysignValueVo>> temperatureList = this.getPhysignTzList(timelineTimes, dayVoList, physignTypeTemperature);
      timeLineDataVo.setTemperatureList(temperatureList);
   }

   private List getPhysignTypeList(int dayMaxSize, List dayVoList, String physignType) {
      List<List<PhysignValueVo>> resList = new ArrayList(1);

      for(int i = 0; i < dayMaxSize; ++i) {
         List<PhysignValueVo> dayList = new ArrayList(1);

         for(DayVo dayVo : dayVoList) {
            Map<String, List<PhysignValueVo>> map = dayVo.getPhysignValueVoListMap();
            if (map != null) {
               List<PhysignValueVo> list = (List)map.get(physignType);
               PhysignValueVo physignValueVo = list != null && list.size() > i ? (PhysignValueVo)list.get(i) : new PhysignValueVo();
               dayList.add(physignValueVo);
            } else {
               dayList.add(new PhysignValueVo());
            }
         }

         resList.add(dayList);
      }

      return resList;
   }

   private List getPhysignTzList(List timelineTimes, List dayVoList, String physignType) {
      List<List<PhysignValueVo>> breathingList = new ArrayList(1);
      List<PhysignValueVo> listDay = new ArrayList(1);

      for(DayVo dayVo : dayVoList) {
         Map<String, List<PhysignValueVo>> map = dayVo.getPhysignValueVoListMap();
         List<PhysignValueVo> tempList = map != null ? (List)map.get(physignType) : null;

         for(int i = 0; i < timelineTimes.size(); ++i) {
            if (tempList != null) {
               PhysignValueVo temp = i < tempList.size() && tempList.get(i) != null ? (PhysignValueVo)tempList.get(i) : new PhysignValueVo();
               listDay.add(temp);
            } else {
               listDay.add(new PhysignValueVo());
            }
         }
      }

      breathingList.add(listDay);
      return breathingList;
   }

   private List getPhysignBloodpressureList(int bloodpressureDayMaxSize, List dayVoList, String physignType) throws Exception {
      String bloodpressureNumStr = this.sysEmrConfigService.selectSysEmrConfigByKey("0010");
      int bloodpressureNum = Integer.valueOf(bloodpressureNumStr);
      List<List<PhysignValueVo>> bloodpressureList = new ArrayList(1);

      for(int i = 0; i < bloodpressureDayMaxSize; ++i) {
         List<PhysignValueVo> bloodpressureListDay = new ArrayList(1);

         for(DayVo dayVo : dayVoList) {
            Map<String, List<PhysignValueVo>> map = dayVo.getPhysignValueVoListMap();
            if (map != null) {
               for(int j = 1; j < bloodpressureNum + 1; ++j) {
                  String physignTypeBloodpressure = physignType.replace("1", "");
                  List<PhysignValueVo> tempList = (List)map.get(physignTypeBloodpressure + j);
                  PhysignValueVo temp = tempList != null && tempList.size() > 0 ? (PhysignValueVo)tempList.get(i) : new PhysignValueVo();
                  bloodpressureListDay.add(temp);
               }
            } else {
               for(int j = 0; j < bloodpressureNum; ++j) {
                  bloodpressureListDay.add(new PhysignValueVo());
               }
            }
         }

         bloodpressureList.add(bloodpressureListDay);
      }

      return bloodpressureList;
   }

   public BasEmployee getUperDoct(String patientId, Visitinfo visitinfo) throws Exception {
      BasEmployee basEmployee = null;
      String uperDoctCode = visitinfo.getAttDocCd();
      if (StringUtils.isEmpty(uperDoctCode) || StringUtils.isBlank(uperDoctCode)) {
         SysUser user = SecurityUtils.getLoginUser().getUser();
         List<BasDoctGroupMember> uperDoctList = this.doctGroupMemberService.getUperDoctFromGroupMember(user.getUserName(), user.getHospital().getOrgCode(), user.getDept().getDeptCode());
         if (uperDoctList != null && !uperDoctList.isEmpty()) {
            BasDoctGroupMember tmpl = (BasDoctGroupMember)uperDoctList.get(0);
            uperDoctCode = tmpl.getDocCode();
         }
      }

      if (StringUtils.isNotEmpty(uperDoctCode) && StringUtils.isNotBlank(uperDoctCode)) {
         basEmployee = this.basEmployeeService.selectByEmplNumber(uperDoctCode);
      }

      return basEmployee;
   }

   public List selectResDocPersonList(String resDocCd) throws Exception {
      return this.visitinfoMapper.selectResDocPersonList(resDocCd, (String)null);
   }

   public List selectRunTimeQcCheckVo(RunTimeQcCheckVo runTimeQcCheckVo) throws Exception {
      List<RunTimeQcCheckVo> list = this.selectRunTimeQcCheckVoNoInfo(runTimeQcCheckVo);
      List<VisitinfoVo> visitinfoVoList = this.visitinfoMapper.selectColorplan();
      visitinfoVoList = (List)visitinfoVoList.stream().filter((t) -> t.getPlanType().equals("4")).collect(Collectors.toList());
      Map<String, VisitinfoVo> visitinfoMap = (Map)visitinfoVoList.stream().collect(Collectors.toMap((t) -> t.getColorTypeCd(), Function.identity()));

      for(RunTimeQcCheckVo rt : list) {
         String age = AgeUtil.getAgeStr(rt.getAgeY(), rt.getAgeM(), rt.getAgeD(), rt.getAgeH(), rt.getAgeMi());
         rt.setAge(age);
         rt.setOperIcon(((VisitinfoVo)visitinfoMap.get("operFlag")).getColorValue());
         rt.setConsIcon(((VisitinfoVo)visitinfoMap.get("consFlag")).getColorValue());
         rt.setDieIcon(((VisitinfoVo)visitinfoMap.get("dieFlag")).getColorValue());
         rt.setBloodIcon(((VisitinfoVo)visitinfoMap.get("bloodTrans")).getColorValue());
         rt.setChangeIcon(((VisitinfoVo)visitinfoMap.get("changeFlag")).getColorValue());
         rt.setInfectIcon(((VisitinfoVo)visitinfoMap.get("infectFlag")).getColorValue());
         rt.setRescueIcon(((VisitinfoVo)visitinfoMap.get("rescueFlag")).getColorValue());
         rt.setDayNumIcon(((VisitinfoVo)visitinfoMap.get("dayNumFlag")).getColorValue());
         rt.setCostSumIcon(((VisitinfoVo)visitinfoMap.get("costSumFlag")).getColorValue());
         rt.setAlertIcon(((VisitinfoVo)visitinfoMap.get("alertFlag")).getColorValue());
         rt.setDayNum(DateUtils.getDiffDay(rt.getOutTime() == null ? new Date() : rt.getOutTime(), rt.getInhosTime()));
         if (rt.getPatientId().equals(runTimeQcCheckVo.getPatientId())) {
            rt.setState("1");
            rt.setStateName("质控中");
         } else {
            rt.setState("0");
            rt.setStateName("未质控");
         }
      }

      return list;
   }

   public Integer selectRunTimeQcCheckCount(RunTimeQcCheckVo runTimeQcCheckVo) throws Exception {
      return this.visitinfoMapper.selectRunTimeQcCheckCount(runTimeQcCheckVo);
   }

   public List selectRunTimeQcCheckVoNoInfo(RunTimeQcCheckVo runTimeQcCheckVo) throws Exception {
      return this.visitinfoMapper.selectRunTimeQcCheck(runTimeQcCheckVo);
   }

   public Long getBaseId(String inpNo) throws Exception {
      Long baseId = new Long(0L);
      SyncDatasource syncDatasource = this.syncDatasourceService.selectSyncDatasourceByCode(DataSourceType.physignDayList.toString());
      if (syncDatasource != null) {
         PhysignDayVo physignDayVo = new PhysignDayVo();
         physignDayVo.setInpNo(inpNo);
         physignDayVo.setSqlStr(syncDatasource.getQuerySql());
         List<PhysignDayVo> physignDayVoList = this.physignValueService.getDayList(physignDayVo);
         if (physignDayVoList != null && physignDayVoList.size() > 0) {
            baseId = ((PhysignDayVo)physignDayVoList.get(0)).getBaseid();
         }
      }

      return baseId;
   }

   public void getPhysignValueVo(SyncDatasource syncDatasource1, Long baseId, SimpleDateFormat sdf, DayVo dayVo) throws Exception {
      PhysignValueVo physignValueVo = new PhysignValueVo();
      if (syncDatasource1 != null) {
         physignValueVo.setBaseid(baseId);
         physignValueVo.setCurDay(sdf.parse(dayVo.getDayDate()));
         physignValueVo.setSqlStr(syncDatasource1.getQuerySql());
         List<PhysignValueVo> physignValueVoList = this.physignValueService.getValueList(physignValueVo);
         if (physignValueVo != null && physignValueVoList.size() > 0) {
            dayVo.setPhysignValueVoList(physignValueVoList);
         }
      }

   }

   public List getIndex(String patientId, Date startTime, Date endTime) throws Exception {
      IndexVo indexVo = new IndexVo();
      indexVo.setPatientId(patientId);
      indexVo.setCreDateBegin(startTime);
      indexVo.setCreDateEnd(endTime);
      List<Index> indexList = this.indexService.selectSealupIndexList(indexVo);
      return indexList;
   }

   public List getHisDocorderVoList(String patientId, String startTime, String endTime) throws Exception {
      SyncDatasource sd = this.syncDatasourceService.selectSyncDatasourceByCode(DataSourceType.searchHisDocorder.toString());
      OrderSearchVo doctorderVo = new OrderSearchVo();
      doctorderVo.setSqlStr(sd.getQuerySql());
      doctorderVo.setPatientId(patientId);
      doctorderVo.setOrderStartTimeBegin(DateUtils.parseDate(startTime, new String[]{"YYYY-MM-DD HH:mm:ss"}));
      doctorderVo.setOrderStartTimeEnd(DateUtils.parseDate(endTime, new String[]{"YYYY-MM-DD HH:mm:ss"}));
      List<OrderSearchVo> doctorderVoList = this.doctorderService.selectHisDocorderVoList(doctorderVo);
      return doctorderVoList;
   }

   public List getOperatRecord(String patientId, String startTime, String endTime) throws Exception {
      OperatRecordVo operatRecordVo = new OperatRecordVo();
      operatRecordVo.setPatientId(patientId);
      operatRecordVo.setCreDateBegin(startTime);
      operatRecordVo.setCreDateEnd(endTime);
      List<OperatRecord> operatRecordList = this.operatRecordService.selectOperatRecordByDate(operatRecordVo);
      return operatRecordList;
   }

   public void getDayInfo(SyncDatasource syncDatasource1, Long baseId, SimpleDateFormat sdf, DayVo dayVo, List indexList, List doctorderVoList, List operatRecordList) throws Exception {
      this.getPhysignValueVo(syncDatasource1, baseId, sdf, dayVo);
      if (indexList != null && indexList.size() > 0) {
         List<Index> indices = (List)indexList.stream().filter((i) -> sdf.format(i.getCreDate()).equals(dayVo.getDayDate())).collect(Collectors.toList());
         if (indices != null && indices.size() > 0) {
            dayVo.setIndexList(indices);
         }
      }

      if (doctorderVoList != null && doctorderVoList.size() > 0) {
         List<OrderSearchVo> doctorderVos = (List)doctorderVoList.stream().filter((d) -> sdf.format(d.getOrderStartTime()).equals(dayVo.getDayDate())).collect(Collectors.toList());
         if (doctorderVos != null && doctorderVos.size() > 0) {
            Map<String, List<OrderSearchVo>> map = (Map)doctorderVos.stream().collect(Collectors.groupingBy(OrderSearchVo::getOrderClassCode));
            dayVo.setMap(map);
         }
      }

      if (operatRecordList != null && operatRecordList.size() > 0) {
         List<OperatRecord> operatRecords = (List)operatRecordList.stream().filter((o) -> sdf.format(o.getCreDate()).equals(dayVo.getDayDate())).collect(Collectors.toList());
         if (operatRecords != null && operatRecords.size() > 0) {
            dayVo.setOperatRecordList(operatRecords);
         }
      }

   }

   public VisitinfoVo selectResDocPatNumber() throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      List<VisitinfoVo> visitinfoVoList = this.visitinfoMapper.selectResDocPersonList(sysUser.getBasEmployee().getEmplNumber(), sysUser.getDept().getDeptCode());
      return this.getPatientNumber(visitinfoVoList);
   }

   public VisitinfoVo selectGroupPatNumber() throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      String deptCd = user.getDept().getDeptCode();
      VisitinfoVo visitinfoVo = new VisitinfoVo();
      visitinfoVo.setDeptCd(deptCd);
      List<VisitinfoVo> visitinfoVoList = this.visitinfoMapper.selectPatientGroupList(visitinfoVo);
      return this.getPatientNumber(visitinfoVoList);
   }

   public VisitinfoVo selectDeptPatNumber() throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      String deptCd = user.getDept().getDeptCode();
      List<VisitinfoVo> visitinfoVoList = this.visitinfoMapper.selectDeptPatList(deptCd);
      return this.getPatientNumber(visitinfoVoList);
   }

   public VisitinfoVo getPatientNumber(List visitinfoVoList) {
      Date date = new Date();
      SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
      String today = format.format(date);
      VisitinfoVo visitinfoVo = new VisitinfoVo();
      if (visitinfoVoList != null && !visitinfoVoList.isEmpty()) {
         List<VisitinfoVo> unOutList = (List)visitinfoVoList.stream().filter((s) -> s.getOutTime() == null).collect(Collectors.toList());
         List<VisitinfoVo> todayInhos = (List)visitinfoVoList.stream().filter((s) -> format.format(s.getInhosTime()).equals(today)).collect(Collectors.toList());
         List<VisitinfoVo> todayOuthos = (List)visitinfoVoList.stream().filter((s) -> s.getOutTime() != null && format.format(s.getOutTime()).equals(today)).collect(Collectors.toList());
         visitinfoVo.setInHosNum(unOutList == null ? 0 : unOutList.size());
         visitinfoVo.setToInHosNum(todayInhos == null ? 0 : todayInhos.size());
         visitinfoVo.setToOutHosNum(todayOuthos == null ? 0 : todayOuthos.size());
      }

      return visitinfoVo;
   }

   public Boolean selectIsPrivacyLevel(String patientId) throws Exception {
      boolean flag = true;
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      VisitinfoVo visitinfoVo = this.visitinfoMapper.selectVisitinfoById(patientId);
      if (!basEmployee.getEmplNumber().equals(visitinfoVo.getResDocCd()) && !basEmployee.getEmplNumber().equals(visitinfoVo.getAttDocCd()) && !basEmployee.getEmplNumber().equals(visitinfoVo.getArcDocCd())) {
         flag = false;
      }

      return flag;
   }

   public List selectInHosPersonList(VisitinfoVo visitinfoVo) throws Exception {
      List<VisitinfoVo> visitinfoPersonalVoList = this.visitinfoMapper.selectInHosPersonList(visitinfoVo);
      Date newDate = this.commonService.getDbSysdate();
      if (visitinfoPersonalVoList != null && !visitinfoPersonalVoList.isEmpty()) {
         for(VisitinfoVo vo : visitinfoPersonalVoList) {
            String age = AgeUtil.getAgeStr(vo.getAgeY(), vo.getAgeM(), vo.getAgeD(), vo.getAgeH(), vo.getAgeMi());
            vo.setAge(age);
            String newDay = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, newDate);
            int dayNum = DateUtils.getInHosDays(vo.getInhosTime(), vo.getOutTime() != null ? vo.getOutTime() : newDate);
            if (DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, vo.getInhosTime()).equals(newDay)) {
               dayNum = 0;
            }

            vo.setInhosDayNum(dayNum);
         }
      }

      return visitinfoPersonalVoList;
   }

   public List selectVistinfoListByPatList(List patientIdList) throws Exception {
      return this.visitinfoMapper.selectVistinfoListByPatList(patientIdList);
   }

   public List selectVistinfoListByInpNoList(List inpNoList) throws Exception {
      return this.visitinfoMapper.selectVistinfoListByInpNoList(inpNoList);
   }

   public void updateOutInfoFromOrder(OrderSaveVo orderSaveVo) throws Exception {
      Visitinfo updateParam = new Visitinfo();
      updateParam.setPatientId(orderSaveVo.getPatientId());
      updateParam.setOutCon(orderSaveVo.getOutCon());
      updateParam.setReferralHospital(orderSaveVo.getReferralHospital());
      this.updateVisitinfo(updateParam);
   }

   public VisitinfoVo selectPatientById(VisitinfoVo visitinfoVo) throws Exception {
      return this.visitinfoMapper.selectPatientById(visitinfoVo);
   }

   public List selectHisVisitinfoListByPatientId(String patientId) throws Exception {
      return this.visitinfoMapper.selectHisVisitinfoListByPatientId(patientId);
   }

   public void updateFromOrderAgent(TdPaOrderAgentVo agentVo, String patientId) throws Exception {
      Visitinfo updateParam = new Visitinfo();
      updateParam.setPatientId(patientId);
      updateParam.setWeight(agentVo.getWeight());
      this.updateVisitinfo(updateParam);
   }

   public VisitinfoVo selectInhosMedicalData() throws Exception {
      VisitinfoVo result = new VisitinfoVo();
      SysDept sysDept = SecurityUtils.getLoginUser().getUser().getDept();
      Integer inhosNum = this.transinfoMapper.selectTodayInhosNum(sysDept.getDeptCode());
      Integer turnInNum = this.transinfoMapper.selectTodayTrunInNum(sysDept.getDeptCode());
      Integer outhosNum = this.visitinfoMapper.selectTodayOutHosNum(sysDept.getDeptCode());
      Integer turnOutNum = this.transinfoMapper.selectTodayTurnOutNum(sysDept.getDeptCode());
      Integer operPatNum = this.tdCaOperationApplyMapper.selectOperPatNum(sysDept.getDeptCode());
      Integer babyNum = this.babyInfoMapper.selectBabyInfolistByDeptCd(sysDept.getDeptCode());
      result.setToInHosNum(inhosNum);
      result.setTurnInNum(turnInNum);
      result.setToOutHosNum(outhosNum);
      result.setTurnOutNum(turnOutNum);
      result.setOperPatNum(operPatNum);
      result.setBabyNum(babyNum);
      return result;
   }

   public AjaxResult selectHomeBacklogList(String patientId) throws Exception {
      AjaxResult ajaxResult = AjaxResult.success();
      List<BackLogVo> allList = new ArrayList();
      if (StringUtils.isNotEmpty(patientId)) {
         VisitinfoVo visitinfoVo = this.visitinfoMapper.selectVisitinfoById(patientId);
         ajaxResult.put("patientName", visitinfoVo.getPatientName());
      }

      List<BackLogVo> toDoList = this.emrTaskInfoService.selectEmrToDoList(patientId);
      allList.addAll(toDoList);
      List<BackLogVo> unSignList = this.indexService.selectUnSignEmrList(patientId);
      allList.addAll(unSignList);
      List<BackLogVo> unUpdateList = this.emrQcListService.selectUnUpdateEmrList(patientId);
      allList.addAll(unUpdateList);
      List<BackLogVo> unSubmitQcList = this.emrQcFlowService.selectUnSubmitQcList(patientId);
      allList.addAll(unSubmitQcList);
      List<BackLogVo> unReturnList = this.emrQcFlowService.selectUnReturnRecordList(patientId);
      allList.addAll(unReturnList);
      List<BackLogVo> qcReturnList = this.emrQcRecordService.selectQcReturnList(patientId);
      allList.addAll(qcReturnList);
      List<BackLogVo> consList = this.tdCaConsApplyService.selectUnFinishConsList(patientId);
      allList.addAll(consList);
      ajaxResult.put("allList", allList);
      if (StringUtils.isEmpty(patientId)) {
         ajaxResult.put("toDoList", toDoList);
         ajaxResult.put("unSignList", unSignList);
         ajaxResult.put("unUpdateList", unUpdateList);
         ajaxResult.put("unSubmitQcList", unSubmitQcList);
         ajaxResult.put("unReturnList", unReturnList);
         ajaxResult.put("qcReturnList", qcReturnList);
         ajaxResult.put("consList", consList);
      }

      return ajaxResult;
   }

   public void syncPatData() throws Exception {
      String syncStr = this.sysEmrConfigService.selectSysEmrConfigByKey("0051");
      String[] strs = syncStr.split(",");

      for(String str : strs) {
         SysJobLog sysJobLog = new SysJobLog();

         try {
            sysJobLog.setJobLogId(SnowIdUtils.uniqueLong());
            sysJobLog.setJobName("首页刷新患者同步");
            sysJobLog.setInvokeTarget("syncDatasourceServiceImpl.syncAddDataBySqlVoAndCode('" + str + "')");
            sysJobLog.setStatus("0");
            sysJobLog.setCreateTime(this.commonService.getDbSysdate());
            this.syncDatasourceService.syncAddDataBySqlVoAndCode(str);
         } catch (Exception e) {
            sysJobLog.setStatus("1");
            sysJobLog.setExceptionInfo(e.toString());
         }

         this.sysJobLogService.addJobLog(sysJobLog);
      }

   }

   public List selectPatientData(SqlVo deptVo) throws Exception {
      List<Map<String, Object>> resultList = null;

      try {
         this.log.info("selectPatientData========1111" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.sss", new Date()));
         SyncDatasource syncDatasource = this.syncDatasourceService.selectSyncDatasourceByCode(DataSourceType.hisPatientList.toString());
         this.log.info("selectPatientData========2222" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.sss", new Date()));
         deptVo.setSqlStr(syncDatasource.getQuerySql());
         DruidUtil.switchDB(syncDatasource);
         this.log.info("selectPatientData========3333" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.sss", new Date()));
         resultList = this.visitinfoMapper.selectHisAddPatientList(deptVo);
         this.log.info("selectPatientData========4444" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.sss", new Date()));
      } finally {
         DruidUtil.clearDataSource();
         this.log.info("selectPatientData========5555" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.sss", new Date()));
      }

      return resultList;
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void syncPatVisitinfo(List hisData, List hisPerData) throws Exception {
      this.log.info("syncHisPatient========99990101" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.sss", new Date()));
      List<String> paramInpNoList = (List)hisData.stream().map((s) -> s.get("admission_no").toString()).collect(Collectors.toList());
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      List<PatEvent> patEventList = new ArrayList();
      SqlVo param = new SqlVo();
      param.setTableName("T_AR_MEDICALINFORMATION");
      param.setInpNoList(paramInpNoList);
      List<Visitinfo> emrData = this.visitinfoMapper.selectSyncVisitinfoList(param);
      this.log.info("syncHisPatient========99990102" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.sss", new Date()));
      List<String> emrInpNoList = (List)emrData.stream().map((s) -> s.getInpNo()).collect(Collectors.toList());
      List<Visitinfo> outPatList = this.visitinfoMapper.selectSyncVisitinfoOutTimeList(paramInpNoList, sysUser.getDept().getDeptCode());
      this.log.info("syncHisPatient========99990103" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.sss", new Date()));
      List<String> outInpNoList = (List)outPatList.stream().map((s) -> s.getInpNo()).collect(Collectors.toList());
      List<Map<String, Object>> inhosMap = new ArrayList();
      List<Map<String, Object>> outMap = new ArrayList();
      List<Map<String, Object>> outDayMap = new ArrayList();
      List<Map<String, Object>> inhosPerMap = new ArrayList();
      List<Map<String, Object>> outPerMap = new ArrayList();
      List<Map<String, Object>> outDayPerMap = new ArrayList();
      VisitinfoVo visitinfoVo = new VisitinfoVo();
      visitinfoVo.setDeptCode(sysUser.getDept().getDeptCode());
      visitinfoVo.setResDocCd(sysUser.getBasEmployee().getEmplNumber());
      List<VisitinfoVo> visitinfoVoList = this.visitinfoMapper.selectBackoutList(visitinfoVo);
      List<String> backoutInpNoList = (List)visitinfoVoList.stream().map((s) -> s.getInpNo()).collect(Collectors.toList());
      List<String> backoutList = new ArrayList();

      for(String administerNo : backoutInpNoList) {
         if (!paramInpNoList.contains(administerNo)) {
            backoutList.add(administerNo);
         }
      }

      for(Map map : hisData) {
         String inpno = map.get("admission_no").toString();
         new ArrayList();
         List emrList = (List)emrData.stream().filter((s) -> s.getInpNo().equals(inpno)).collect(Collectors.toList());
         if (emrList == null || emrList.isEmpty()) {
            emrList = (List)outPatList.stream().filter((s) -> s.getInpNo().equals(inpno)).collect(Collectors.toList());
         }

         if (emrList != null && !emrList.isEmpty()) {
            Date hisDate = (Date)map.get("modify_time");
            Date emrDate = ((Visitinfo)emrList.get(0)).getModifyTime();
            this.log.debug("syncPatVisitinfo---1 : " + inpno);
            this.log.debug("syncPatVisitinfo---2 : " + hisDate);
            this.log.debug("syncPatVisitinfo---3 : " + emrDate);
            if (hisDate != null && emrDate != null && hisDate.compareTo(emrDate) == 0) {
               continue;
            }
         }

         Map<String, Object> perinfo = (Map)((List)hisPerData.stream().filter((s) -> s.get("admission_no").toString().equals(inpno)).collect(Collectors.toList())).get(0);
         if (map.get("hospital_mark").toString().equals("4")) {
            this.log.info("syncHisPatient========99990104" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.sss", new Date()));
            if (emrInpNoList.contains(inpno)) {
               Date outTime = (Date)map.get("leave_hospital_date");
               PatEvent patEvent = new PatEvent(map, "02", "出院", outTime, (Date)null);
               patEventList.add(patEvent);
               Date outDate = (Date)map.get("leave_hospital_date");
               Date inhosDate = (Date)map.get("hospitalized_date");
               if (DateUtils.getDateHours(outDate, inhosDate) < 24) {
                  patEvent = new PatEvent(map, "04", "24小时出院", outTime, (Date)null);
                  patEventList.add(patEvent);
               }

               this.log.debug("删除在院患者----1");
            } else if (outInpNoList.contains(inpno)) {
               List<Visitinfo> out = (List)outPatList.stream().filter((s) -> s.getInpNo().equals(inpno)).collect(Collectors.toList());
               if (((Visitinfo)out.get(0)).getOutType().equals("1")) {
                  this.log.debug("删除今日出院患者----1");
               } else {
                  this.log.debug("删除历史出院患者----1");
               }
            } else {
               Date outTime = (Date)map.get("leave_hospital_date");
               PatEvent patEvent = new PatEvent(map, "02", "出院", outTime, (Date)null);
               patEventList.add(patEvent);
               Date outDate = (Date)map.get("leave_hospital_date");
               Date inhosDate = (Date)map.get("hospitalized_date");
               if (DateUtils.getDateHours(outDate, inhosDate) < 24) {
                  patEvent = new PatEvent(map, "04", "24小时出院", outTime, (Date)null);
                  patEventList.add(patEvent);
               }
            }

            Date date = (Date)map.get("leave_hospital_date");
            if (DateUtils.isTheSameDay(date, this.commonService.getDbSysdate())) {
               outDayMap.add(map);
               outDayPerMap.add(perinfo);
               this.log.debug("插入今日出院患者----2");
            } else {
               outMap.add(map);
               outPerMap.add(perinfo);
               this.log.debug("插入历史出院患者----2");
            }
         } else {
            this.log.info("syncHisPatient========99990105" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.sss", new Date()));
            inhosMap.add(map);
            inhosPerMap.add(perinfo);
            if (!emrInpNoList.contains(inpno) && !outInpNoList.contains(inpno)) {
               Date date = (Date)map.get("hospitalized_date");
               PatEvent patEvent = new PatEvent(map, "01", "入院", date, (Date)null);
               patEventList.add(patEvent);
               if (map.get("entry_date") != null) {
                  Date entryDate = (Date)map.get("entry_date");
                  PatEvent patEvent1 = new PatEvent(map, "19", "入科", entryDate, entryDate);
                  patEventList.add(patEvent1);
               }
            }
         }

         this.log.info("syncHisPatient========99990105" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.sss", new Date()));
      }

      paramInpNoList = (List)paramInpNoList.stream().filter((t) -> StringUtils.isNotBlank(t)).collect(Collectors.toList());
      if (CollectionUtils.isNotEmpty(paramInpNoList) && paramInpNoList.size() > 0) {
         this.visitinfoMapper.deleteVisitinfoByInpNoList("T_AR_MEDICALINFORMATION", paramInpNoList);
         this.visitinfoMapper.deleteVisitinfoByInpNoList("T_AR_MEDICALINFORMATION_DAY", paramInpNoList);
         this.visitinfoMapper.deleteVisitinfoByInpNoList("T_AR_MEDICALINFORMATION_H", paramInpNoList);
         this.log.info("syncHisPatient========99990106" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.sss", new Date()));
         this.personalinfoService.deletePersonalinfoByInpNo("T_AR_BASEINFOMATION", paramInpNoList);
         this.personalinfoService.deletePersonalinfoByInpNo("T_AR_BASEINFOMATION_DAY", paramInpNoList);
         this.personalinfoService.deletePersonalinfoByInpNo("T_AR_BASEINFOMATION_H", paramInpNoList);
         this.log.info("syncHisPatient========99990107" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.sss", new Date()));
      }

      backoutList = (List)backoutList.stream().filter((t) -> StringUtils.isNotBlank(t)).collect(Collectors.toList());
      if (!inhosMap.isEmpty()) {
         for(Map map : inhosMap) {
            map.put("tableName", "T_AR_MEDICALINFORMATION");
            this.visitinfoMapper.insertMapList(map);
         }

         this.log.info("syncHisPatient========99990108" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.sss", new Date()));
      }

      if (!outMap.isEmpty()) {
         for(Map map : outMap) {
            map.put("tableName", "T_AR_MEDICALINFORMATION_H");
            this.visitinfoMapper.insertMapList(map);
         }

         this.log.info("syncHisPatient========99990109" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.sss", new Date()));
      }

      if (!outDayMap.isEmpty()) {
         for(Map map : outDayMap) {
            map.put("tableName", "T_AR_MEDICALINFORMATION_DAY");
            this.visitinfoMapper.insertMapList(map);
         }

         this.log.info("syncHisPatient========999901==10" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.sss", new Date()));
      }

      if (!inhosPerMap.isEmpty()) {
         for(Map map : inhosPerMap) {
            map.put("tableName", "T_AR_BASEINFOMATION");
            this.personalinfoService.insertMapList(map);
         }

         this.log.info("syncHisPatient========999901==11" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.sss", new Date()));
      }

      if (!outPerMap.isEmpty()) {
         for(Map map : outPerMap) {
            map.put("tableName", "T_AR_BASEINFOMATION_H");
            this.personalinfoService.insertMapList(map);
         }

         this.log.info("syncHisPatient========999901==12" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.sss", new Date()));
      }

      if (!outDayPerMap.isEmpty()) {
         for(Map map : outDayPerMap) {
            map.put("tableName", "T_AR_BASEINFOMATION_DAY");
            this.personalinfoService.insertMapList(map);
         }

         this.log.info("syncHisPatient========999901==13" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.sss", new Date()));
      }

      if (!patEventList.isEmpty()) {
         this.patEventService.insertList(patEventList);
         this.log.info("syncHisPatient========999901==14" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.sss", new Date()));
      }

   }

   @DataSource(DataSourceType.hisPatientList)
   public Visitinfo selectHisPatByInpNo(String inpNo) throws Exception {
      return this.visitinfoMapper.selectHisPatByInpNo(inpNo);
   }

   public List selectDeptArchiveList(Integer days, List mrStateList) throws Exception {
      return this.visitinfoMapper.selectDeptArchiveList(days, mrStateList);
   }

   public List selectPatInfoByOpeList(OperRoomVisitinfoVo visitinfoVo) throws Exception {
      List<OperRoomVisitinfoVo> resultList = new ArrayList();
      SysDept sysDept = SecurityUtils.getLoginUser().getUser().getDept();
      List<String> noStatusList = new ArrayList(Arrays.asList("01", "02", "03", "04", "99"));
      switch (visitinfoVo.getQueryType()) {
         case "1":
            noStatusList.add("05");
            visitinfoVo.setNoStatusList(noStatusList);
            visitinfoVo.setOperStatus("06");
            visitinfoVo.setShiftDateBegin(visitinfoVo.getStartDate());
            Date shiftDateEnd = visitinfoVo.getEndDate() != null ? DateUtils.addDays(visitinfoVo.getEndDate(), 1) : visitinfoVo.getEndDate();
            visitinfoVo.setShiftDateEnd(shiftDateEnd);
            visitinfoVo.setExecDeptCd(sysDept.getDeptCode());
            resultList = this.tdCaOperationApplyService.selectOperPlanStatusList(visitinfoVo);
            break;
         case "2":
            noStatusList = Arrays.asList("01", "03", "04", "99");
            visitinfoVo.setNoStatusList(noStatusList);
            visitinfoVo.setOperStatus("05");
            visitinfoVo.setPlanOperTimeBegin(visitinfoVo.getStartDate());
            Date planOperTimeEnd = visitinfoVo.getEndDate() != null ? DateUtils.addDays(visitinfoVo.getEndDate(), 1) : visitinfoVo.getEndDate();
            visitinfoVo.setPlanOperTimeEnd(planOperTimeEnd);
            visitinfoVo.setExecDeptCd(sysDept.getDeptCode());
            resultList = this.tdCaOperationApplyService.selectOperPlanStatusList(visitinfoVo);
            break;
         case "3":
            noStatusList = Arrays.asList("03", "04", "99");
            visitinfoVo.setNoStatusList(noStatusList);
            visitinfoVo.setExecDeptCd(sysDept.getDeptCode());
            resultList = this.visitinfoMapper.selectOperVisitinfoList(visitinfoVo);

            for(OperRoomVisitinfoVo roomVisitinfoVo : resultList) {
               String ageStr = AgeUtil.getAgeStr(roomVisitinfoVo.getAgeY(), roomVisitinfoVo.getAgeM(), roomVisitinfoVo.getAgeD(), roomVisitinfoVo.getAgeH(), roomVisitinfoVo.getAgeMi());
               roomVisitinfoVo.setAge(ageStr);
            }
      }

      if (resultList != null && !resultList.isEmpty()) {
         SysUser user = SecurityUtils.getLoginUser().getUser();
         String url = "";
         BusIntegMenu busIntegMenu = new BusIntegMenu();
         busIntegMenu.setMenuClass("1");
         busIntegMenu.setCode("temp_list");
         busIntegMenu.setSysCode("1");
         List<BusIntegMenu> busIntegMenuList = this.busIntegMenuService.selectBusIntegMenuList(busIntegMenu);
         if (busIntegMenuList != null && !busIntegMenuList.isEmpty() && StringUtils.isNotEmpty(((BusIntegMenu)busIntegMenuList.get(0)).getRoutePath())) {
            String urlStr = ((BusIntegMenu)busIntegMenuList.get(0)).getRoutePath();

            for(OperRoomVisitinfoVo roomVisitinfoVo : resultList) {
               ReplaceUrlParamVo replaceUrlParamVo = new ReplaceUrlParamVo(roomVisitinfoVo.getInpNo(), roomVisitinfoVo.getInpNo(), roomVisitinfoVo.getRecordNo(), (String)null, user.getUserName(), user.getNickName(), user.getDept().getDeptCode(), (String)null, (String)null, (String)null, (String)null);
               url = this.commonService.replaceUrlParam(replaceUrlParamVo, urlStr);
               roomVisitinfoVo.setNurseEmrUrl(url);
            }
         }
      }

      TdPaItemDocQuery tdPaItemDocQuery = new TdPaItemDocQuery();
      tdPaItemDocQuery.setOrderFlag("12");
      tdPaItemDocQuery.setQueryStatus(visitinfoVo.getQueryType());
      this.tdPaItemDocQueryService.insertTdPaItemDocQuery(tdPaItemDocQuery);
      return resultList;
   }

   public PatientInfoVo selectPatientInfoByOpeList(OperRoomVisitinfoVo visitinfoVo) throws Exception {
      int count = this.visitinfoMapper.getCountByAdmissionNo(visitinfoVo.getPatientId());
      new PatientInfoVo();
      PatientInfoVo patientInfoVo;
      if (count != 0) {
         patientInfoVo = this.visitinfoMapper.getPatientInfoDetail(visitinfoVo);
         if (StringUtils.isNotEmpty(patientInfoVo.getAdmittingDiagnosis())) {
            String mc = this.tmPmHsxmMapper.getByIcd(patientInfoVo.getAdmittingDiagnosis());
            patientInfoVo.setAdmittingDiagnosisName(mc);
         }

         patientInfoVo.setIsOut("0");
      } else {
         patientInfoVo = this.visitinfoMapper.getPatientInfoDetailFromHis(visitinfoVo);
         patientInfoVo.setIsOut("1");
      }

      return patientInfoVo;
   }

   public String selectOperRoomQueryType() throws Exception {
      String result = "1";
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      TdPaItemDocQuery param = new TdPaItemDocQuery();
      param.setDocCd(basEmployee.getEmplNumber());
      param.setOrderFlag("12");
      List<TdPaItemDocQuery> list = this.tdPaItemDocQueryService.selectTdPaItemDocQueryList(param);
      if (list != null && !list.isEmpty()) {
         result = ((TdPaItemDocQuery)list.get(0)).getQueryStatus();
      }

      return result;
   }

   public List selectCriticalInfo(SqlVo deptVo) throws Exception {
      List<CriticalVo> criticalVoList = new ArrayList();

      try {
         this.log.info("查询患者重症信息开始" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.sss", new Date()));
         SyncDatasource syncDatasource = this.syncDatasourceService.selectSyncDatasourceByCode(DataSourceType.hisCriticalInfo.toString());
         deptVo.setSqlStr(syncDatasource.getQuerySql());
         DruidUtil.switchDB(syncDatasource);
         List<Map<String, Object>> resultList = this.visitinfoMapper.selectCriticalInfo(deptVo);
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         if (resultList.size() > 0) {
            for(Map map : ToggleCaseUtils.toLowerCaseList(resultList)) {
               CriticalVo criticalVo = new CriticalVo();
               if (map.get("in_time") != null) {
                  criticalVo.setInTime(sdf.parse(String.valueOf(map.get("in_time"))));
               }

               if (map.get("out_time") != null) {
                  criticalVo.setOutTime(sdf.parse(String.valueOf(map.get("out_time"))));
               }

               if (map.get("dept_id") != null) {
                  criticalVo.setDeptId(String.valueOf(map.get("dept_id")));
               }

               if (map.get("in_time") != null && map.get("out_time") != null) {
                  Date inTime = sdf.parse(String.valueOf(map.get("in_time")));
                  Date outTime = sdf.parse(String.valueOf(map.get("out_time")));
                  int hours = DateUtils.getDateHours(outTime, inTime);
                  map.put("hours", hours);
                  criticalVo.setHours(new BigDecimal(hours));
               }

               criticalVoList.add(criticalVo);
            }
         }

         this.log.info("查询患者重症信息结束" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss.sss", new Date()));
      } finally {
         DruidUtil.clearDataSource();
      }

      return criticalVoList;
   }

   public void updatePatientLevelCare(VisitinfoVo visitinfo, String levelCare, String levelName) throws Exception {
      this.visitinfoMapper.updatePatientCareLevel(levelCare, visitinfo.getInpNo());
      this.tlNaAdjustLogService.addAdjustLog("0", "09", "04", visitinfo.getNgCd(), visitinfo.getNgName(), levelCare, levelName, visitinfo.getInpNo(), visitinfo.getVisitId().toString(), visitinfo.getRecordNo(), visitinfo.getPatientName());
   }

   public void updatePatientIllness(String inpNo, String illness) {
      this.visitinfoMapper.updatePatientIllness(illness, inpNo);
   }

   public List selectVistinfoListByOuTime(String endDate) {
      return this.visitinfoMapper.selectVistinfoListByOuTime(endDate);
   }

   public List selectDiagInfo(String patientId) {
      return this.visitinfoMapper.selectDiagInfo(patientId);
   }

   public List selectDiagInfoByAdmissionNo(String admissionNo) {
      return this.visitinfoMapper.selectDiagInfoByAdmissionNo(admissionNo);
   }

   @DataSource(DataSourceType.searchHisDocorder)
   public String selectCaMesByUserName(String emplNumber) {
      return this.visitinfoMapper.selectCaMesByUserName(emplNumber);
   }

   public void updateStaffCa(String emplNumber, String ca) {
      this.visitinfoMapper.updateStaffCa(emplNumber, ca);
   }
}
