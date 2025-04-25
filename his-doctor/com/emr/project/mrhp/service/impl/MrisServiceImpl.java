package com.emr.project.mrhp.service.impl;

import com.alibaba.fastjson.JSON;
import com.drools.core.KieTemplate;
import com.emr.common.constant.CommonConstants;
import com.emr.common.utils.AgeUtil;
import com.emr.common.utils.DateUtils;
import com.emr.common.utils.Obj2MapUtil;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.common.utils.StringUtils;
import com.emr.project.common.service.ICommonService;
import com.emr.project.emr.domain.EmrSignData;
import com.emr.project.emr.domain.EmrSignRecord;
import com.emr.project.emr.service.IEmrSignDataService;
import com.emr.project.emr.service.IEmrSignRecordService;
import com.emr.project.mrhp.domain.MrHp;
import com.emr.project.mrhp.domain.MrHpAttach;
import com.emr.project.mrhp.domain.MrHpDia;
import com.emr.project.mrhp.domain.MrHpDrawField;
import com.emr.project.mrhp.domain.MrHpDrawMain;
import com.emr.project.mrhp.domain.MrHpFee;
import com.emr.project.mrhp.domain.TmCmRules;
import com.emr.project.mrhp.domain.mris.TdCmAttachSave;
import com.emr.project.mrhp.domain.mris.TdCmDiagSave;
import com.emr.project.mrhp.domain.mris.TdCmHpLineVo;
import com.emr.project.mrhp.domain.mris.TdCmIcuSave;
import com.emr.project.mrhp.domain.mris.TdCmOperSave;
import com.emr.project.mrhp.domain.mris.TmCmQcList;
import com.emr.project.mrhp.domain.vo.MrHpVo;
import com.emr.project.mrhp.mapper.MrHpMapper;
import com.emr.project.mrhp.mapper.MrisMapper;
import com.emr.project.mrhp.service.IMrHpAttachService;
import com.emr.project.mrhp.service.IMrHpDiaService;
import com.emr.project.mrhp.service.IMrHpDrawFieldService;
import com.emr.project.mrhp.service.IMrHpDrawMainService;
import com.emr.project.mrhp.service.IMrHpFeeService;
import com.emr.project.mrhp.service.IMrHpIcuService;
import com.emr.project.mrhp.service.IMrHpOpeService;
import com.emr.project.mrhp.service.IMrisService;
import com.emr.project.mrhp.service.ITmCmRulesService;
import com.emr.project.mrhp.service.ITmDsPreserveOutService;
import com.emr.project.mrhp.service.callable.MrisAttachCallable;
import com.emr.project.other.domain.BasCertInfo;
import com.emr.project.other.service.IBasCertInfoService;
import com.emr.project.pat.domain.AlleInfo;
import com.emr.project.pat.domain.BabyInfo;
import com.emr.project.pat.domain.DiagInfo;
import com.emr.project.pat.domain.Transinfo;
import com.emr.project.pat.domain.vo.CriticalVo;
import com.emr.project.pat.domain.vo.VisitinfoVo;
import com.emr.project.pat.service.IAlleInfoService;
import com.emr.project.pat.service.IBabyInfoService;
import com.emr.project.pat.service.ITransinfoService;
import com.emr.project.pat.service.IVisitinfoService;
import com.emr.project.qc.domain.vo.EmrQcListVo;
import com.emr.project.qc.service.IEmrQcListService;
import com.emr.project.qc.service.IEmrQcRecordService;
import com.emr.project.sys.domain.SysRegionInfo;
import com.emr.project.sys.service.ISysRegionInfoService;
import com.emr.project.system.domain.BasEmployee;
import com.emr.project.system.domain.SysDept;
import com.emr.project.system.domain.SysDictData;
import com.emr.project.system.domain.SysOrg;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.domain.vo.SqlVo;
import com.emr.project.system.mapper.SysOrgMapper;
import com.emr.project.system.service.ISysDeptService;
import com.emr.project.system.service.ISysDictDataService;
import com.emr.project.system.service.ISysEmrConfigService;
import com.emr.project.system.service.ISysUserService;
import java.io.File;
import java.math.BigDecimal;
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
import org.apache.commons.collections.MapUtils;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MrisServiceImpl implements IMrisService {
   @Autowired
   private IVisitinfoService visitinfoService;
   @Autowired
   private ISysDeptService sysDeptService;
   @Autowired
   private IBabyInfoService babyInfoService;
   @Autowired
   private ISysRegionInfoService iSysRegionInfoService;
   @Autowired
   private IAlleInfoService iAlleInfoService;
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;
   @Autowired
   private ITransinfoService transinfoService;
   @Autowired
   private IMrHpDiaService mrHpDiaService;
   @Autowired
   private IMrHpOpeService mrHpOpeService;
   @Autowired
   private IBasCertInfoService basCertInfoService;
   @Autowired
   private IMrHpFeeService iMrHpFeeService;
   @Autowired
   private IMrHpIcuService iMrHpIcuService;
   @Autowired
   private IMrHpAttachService mrHpAttachService;
   @Autowired
   private ISysDictDataService sysDictDataService;
   @Autowired
   private ISysUserService sysUserService;
   @Autowired
   private IEmrSignDataService emrSignDataService;
   @Autowired
   private IEmrSignRecordService emrSignRecordService;
   @Autowired
   private ITmCmRulesService tmCmRulesService;
   @Autowired
   private IEmrQcListService emrQcListService;
   @Autowired
   private ICommonService commonService;
   @Autowired
   private KieTemplate kieTemplate;
   private KieSession kSession;
   @Autowired
   private IEmrQcRecordService emrQcRecordService;
   @Value("${spring.drools.path}")
   private String path;
   @Autowired
   private MrisMapper mrisMapper;
   @Autowired
   private MrHpMapper mrHpMapper;
   @Autowired
   private SysOrgMapper sysOrgMapper;
   @Autowired
   private IMrHpDrawMainService mrHpDrawMainService;
   @Autowired
   private IMrHpDrawFieldService mrHpDrawFieldService;
   @Autowired
   private ITmDsPreserveOutService tmDsPreserveOutService;
   private static final String RULE_NAME = "indocemr_";

   public TdCmHpLineVo selectMrHpPatientDetail(String patientId) throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      SysDept sysDept = sysUser.getDept();
      SysDept sysDeptVis = new SysDept();
      VisitinfoVo visitinfoVo = this.visitinfoService.selectVisitinfoByPatientId(patientId);
      if (visitinfoVo != null && StringUtils.isNotBlank(visitinfoVo.getInDocCd())) {
         sysDeptVis = this.sysDeptService.getOneByCode(visitinfoVo.getInDocCd());
      }

      MrHpDrawMain mrHpDrawMain = new MrHpDrawMain();
      mrHpDrawMain.setStatus("0");
      mrHpDrawMain.setInterfaceType("2");
      mrHpDrawMain.setHisTableName("MR_HP_ATTACH");
      List<MrHpDrawMain> mrHpDrawMainList = this.mrHpDrawMainService.selectMrHpDrawMainList(mrHpDrawMain);
      ExecutorService executor = Executors.newFixedThreadPool(5);
      Future<TdCmAttachSave> attachFuture = null;
      if (!mrHpDrawMainList.isEmpty()) {
         List<Long> mainIdList = (List)mrHpDrawMainList.stream().map(MrHpDrawMain::getId).collect(Collectors.toList());
         List<MrHpDrawField> fieldList = this.mrHpDrawFieldService.selectFieldByMainIdList(mainIdList);
         attachFuture = executor.submit(new MrisAttachCallable(visitinfoVo.getRecordNo(), visitinfoVo.getInpNo(), visitinfoVo.getVisitId(), this.tmDsPreserveOutService, mrHpDrawMainList, fieldList));
      }

      TdCmHpLineVo tdCmHpLineVo = this.mrisMapper.selectMrHpByPatientId(patientId);
      String severeCaseFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0080");
      if (tdCmHpLineVo == null) {
         tdCmHpLineVo = this.selectMrHpPatientVisit(patientId);
         if (tdCmHpLineVo != null) {
            DiagInfo diagInfo = new DiagInfo();
            diagInfo.setPatientId(patientId);
            if (sysDeptVis != null && StringUtils.isNotBlank(sysDeptVis.getMrClass()) && "ZY".equals(sysDeptVis.getMrClass())) {
               diagInfo.setDiagClass("ZY");
               List<TdCmDiagSave> diaZYList = this.mrHpDiaService.selectMrisDiagInfoByPatientId(diagInfo);
               tdCmHpLineVo.setTdCmDiagZyList(diaZYList);
               tdCmHpLineVo.setMr_type("ZY");
            } else {
               diagInfo.setDiagClass("XY");
               List<TdCmDiagSave> diaList = this.mrHpDiaService.selectMrisDiagInfoByPatientId(diagInfo);
               tdCmHpLineVo.setTdCmDiagXyList(diaList);
               tdCmHpLineVo.setMr_type("XY");
            }

            List<TdCmOperSave> opeList = this.mrHpOpeService.selectMrisOperatRecordByPatientId(patientId);
            tdCmHpLineVo.setTdCmOperList(opeList);
            if ("1".equals(severeCaseFlag)) {
               SqlVo sqlVo = new SqlVo();
               sqlVo.setInpNo(patientId);
               List<CriticalVo> mapList = this.visitinfoService.selectCriticalInfo(sqlVo);
               List<TdCmIcuSave> mrHpIcuList = new ArrayList();
               if (mapList.size() > 0) {
                  for(CriticalVo criticalVo : mapList) {
                     TdCmIcuSave mrHpIcu = new TdCmIcuSave();
                     mrHpIcu.setIcu_in_time(criticalVo.getInTime());
                     mrHpIcu.setIcu_out_time(criticalVo.getOutTime());
                     mrHpIcu.setIcu_hour(criticalVo.getHours());
                     mrHpIcu.setIcu_code(criticalVo.getDeptId());
                     mrHpIcuList.add(mrHpIcu);
                  }

                  tdCmHpLineVo.setTdCmIcuList(mrHpIcuList);
               }
            }
         }

         tdCmHpLineVo.setRecord_id(sysUser.getHospital().getOrgCode() + patientId);
         Transinfo tranParam = new Transinfo();
         tranParam.setPatientId(patientId);
         tranParam.setTranInState("2");
         TdCmAttachSave mrHpAttachVo = this.mrHpAttachService.selectMrisHpAttachByPatientId(patientId);
         if (mrHpAttachVo != null) {
            BeanUtils.copyProperties(mrHpAttachVo, tdCmHpLineVo);
         }

         List<Transinfo> inHosTran = this.transinfoService.selectTransinfoList(tranParam);
         if (inHosTran != null && !inHosTran.isEmpty()) {
            tdCmHpLineVo.setIn_dept_cd(((Transinfo)inHosTran.get(0)).getTiDeptCd());
            tdCmHpLineVo.setIn_dept_name(((Transinfo)inHosTran.get(0)).getTiDeptName());
         }

         if (!mrHpDrawMainList.isEmpty() && attachFuture != null) {
            TdCmAttachSave tdCmAttachSave = (TdCmAttachSave)attachFuture.get();
            BeanUtils.copyProperties(tdCmAttachSave, tdCmHpLineVo);
         }
      } else {
         this.openMrHpSetPatientinfo(tdCmHpLineVo);
         if ("5".equals(sysDept.getSysFlag())) {
            tdCmHpLineVo.setObsDeptFlag(CommonConstants.BOOL_TRUE);
         }

         if ("03".equals(tdCmHpLineVo.getMrState())) {
            BasCertInfo basCertInfo = this.basCertInfoService.selectBasCertInfoByEmployeenumber(sysUser.getUserName());
            tdCmHpLineVo.setSignPic(basCertInfo != null ? basCertInfo.getCertPic() : "");
         }

         if (StringUtils.isNotEmpty(tdCmHpLineVo.getRecord_id())) {
            MrHpDia mrHpDia = new MrHpDia();
            mrHpDia.setRecordId(tdCmHpLineVo.getRecord_id());
            mrHpDia.setDiaType("XY");
            List<TdCmDiagSave> mrHpDiaXYList = this.mrHpDiaService.selectMrisMrHpDiaList(mrHpDia);
            tdCmHpLineVo.setTdCmDiagXyList(mrHpDiaXYList);
            if ("ZY".equals(tdCmHpLineVo.getMr_type())) {
               mrHpDia.setDiaType("ZY");
               List<TdCmDiagSave> mrHpDiaZYList = this.mrHpDiaService.selectMrisMrHpDiaList(mrHpDia);
               tdCmHpLineVo.setTdCmDiagZyList(mrHpDiaZYList);
            }

            List<TdCmOperSave> mrHpOpeList = this.mrHpOpeService.selectMrisMrHpOpeByRescordId(tdCmHpLineVo.getRecord_id());
            tdCmHpLineVo.setTdCmOperList(mrHpOpeList);
         }

         if ("1".equals(severeCaseFlag)) {
            List<TdCmIcuSave> mrHpIcuList = this.iMrHpIcuService.selectMrisHpIcuListByRecordId(tdCmHpLineVo.getRecord_id());
            tdCmHpLineVo.setTdCmIcuList(mrHpIcuList);
         }

         TdCmAttachSave mrHpAttachVo = this.mrHpAttachService.selectMrisHpAttachByPatientId(patientId);
         if (mrHpAttachVo == null) {
            mrHpAttachVo = new TdCmAttachSave();
            if (!mrHpDrawMainList.isEmpty() && attachFuture != null) {
               TdCmAttachSave tdCmAttachSave = (TdCmAttachSave)attachFuture.get();
               BeanUtils.copyProperties(tdCmAttachSave, tdCmHpLineVo);
            }
         }

         BeanUtils.copyProperties(mrHpAttachVo, tdCmHpLineVo);
      }

      if (StringUtils.isNotEmpty(tdCmHpLineVo.getCard_type_no()) && !tdCmHpLineVo.getCard_type_no().equals("-")) {
         String idCard = tdCmHpLineVo.getCard_type_no();
         int idCardLength = idCard.length();
         if (idCardLength == 15 || idCardLength == 18) {
            Map<String, String> map = AgeUtil.getBirAgeSex(tdCmHpLineVo.getCard_type_no());
            if (!MapUtils.isEmpty(map)) {
               if (tdCmHpLineVo.getBir_date() == null) {
                  SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
                  tdCmHpLineVo.setBir_date(sdf.parse((String)map.get("birthday")));
               }

               if (tdCmHpLineVo.getAge_y() == null || tdCmHpLineVo.getAge_y() == 0) {
                  tdCmHpLineVo.setAge_y(Integer.valueOf((String)map.get("ageY")));
               }

               if (tdCmHpLineVo.getAge_m() == null || tdCmHpLineVo.getAge_m() == 0) {
                  tdCmHpLineVo.setAge_m(Integer.valueOf((String)map.get("ageM")));
               }

               if (StringUtils.isEmpty(tdCmHpLineVo.getSex_cd())) {
                  tdCmHpLineVo.setSex_cd((String)map.get("sexCode"));
               }
            }
         }
      }

      List<Transinfo> transinfoList = this.transinfoService.selectTransinfoByPatientId(patientId);
      tdCmHpLineVo.setTransinfoList(transinfoList);
      if ("03".equals(tdCmHpLineVo.getMrState())) {
         this.genHpFee(tdCmHpLineVo);
      } else {
         Map<String, Object> param = new HashMap();
         param.put("inpNo", tdCmHpLineVo.getInp_no());
         param.put("mrHpFee", new MrHpFee());
         List<MrHpFee> mrHpFeeList = this.iMrHpFeeService.selectMrHpFeeListByProc(param);
         if (CollectionUtils.isNotEmpty(mrHpFeeList)) {
            this.genUnSaveHpFee(mrHpFeeList, tdCmHpLineVo);

            for(MrHpFee mrHpFee : mrHpFeeList) {
               mrHpFee.setRecordId(tdCmHpLineVo.getRecord_id());
               mrHpFee.setFeeId(tdCmHpLineVo.getRecord_id() + mrHpFee.getFeeCd());
            }

            this.iMrHpFeeService.saveHpFeeList(mrHpFeeList);
         }
      }

      return tdCmHpLineVo;
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void saveMrHp(TdCmHpLineVo mrHpVo) throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      SysOrg sysOrg = this.sysOrgMapper.selectSysOrgByOrgNo(sysUser.getHospital().getOrgCode());
      this.emrQcRecordService.saveMrisHpQcRecord(mrHpVo);
      MrHp mrHp = this.mrHpMapper.selectMrHpById(mrHpVo.getRecord_id());
      this.mrHpDiaService.insertMrisHpDiaList(mrHpVo);
      this.mrHpOpeService.insertMrisHpOpeList(mrHpVo);
      MrHpAttach mrHpAttach = this.genMrHpAttach(mrHpVo);
      List<TdCmIcuSave> tdCmIcuList = mrHpVo.getTdCmIcuList();
      if (tdCmIcuList != null && tdCmIcuList.size() > 0) {
         this.iMrHpIcuService.insertMrisHpIcuList(mrHpVo);
      }

      String[] dictStr = new String[]{"RC035", "s022", "RC003", "RC038", "RC032"};
      List<SysDictData> dictList = this.sysDictDataService.selectDictDataListByType(dictStr);

      for(SysDictData sysDictData : (List)dictList.stream().filter((s) -> s.getDictType().equals("RC035")).collect(Collectors.toList())) {
         if (StringUtils.isNotBlank(mrHpVo.getNation()) && sysDictData.getDictValue().equals(mrHpVo.getNation_cd())) {
            mrHpVo.setNation(sysDictData.getDictLabel());
         }
      }

      for(SysDictData sysDictData : (List)dictList.stream().filter((s) -> s.getDictType().equals("RC038")).collect(Collectors.toList())) {
         if (StringUtils.isNotBlank(mrHpVo.getCitizenship_cd()) && sysDictData.getDictValue().equals(mrHpVo.getCitizenship_cd())) {
            mrHpVo.setCitizenship(sysDictData.getDictLabel());
         }
      }

      for(SysDictData sysDictData : (List)dictList.stream().filter((s) -> s.getDictType().equals("s022")).collect(Collectors.toList())) {
         if (StringUtils.isNotBlank(mrHpVo.getCard_type_cd()) && sysDictData.getDictValue().equals(mrHpVo.getCard_type_cd())) {
            mrHpVo.setCard_type_name(sysDictData.getDictLabel());
         }
      }

      for(SysDictData sysDictData : (List)dictList.stream().filter((s) -> s.getDictType().equals("RC003")).collect(Collectors.toList())) {
         if (StringUtils.isNotBlank(mrHpVo.getPro_type_cd()) && sysDictData.getDictValue().equals(mrHpVo.getPro_type_cd())) {
            mrHpVo.setPro_type_name(sysDictData.getDictLabel());
         }
      }

      for(SysDictData sysDictData : (List)dictList.stream().filter((s) -> s.getDictType().equals("RC032")).collect(Collectors.toList())) {
         if (StringUtils.isNotBlank(mrHpVo.getPay_type_cd()) && sysDictData.getDictValue().equals(mrHpVo.getPay_type_cd())) {
            mrHpVo.setPay_type_name(sysDictData.getDictLabel());
         }
      }

      Map<String, Object> map = Obj2MapUtil.objectToMap(mrHpVo);
      MrHpVo genMrHp = new MrHpVo();
      if (!map.isEmpty()) {
         String ss = JSON.toJSONString(map);
         genMrHp = (MrHpVo)JSON.parseObject(ss, MrHpVo.class);
      }

      if (mrHp == null) {
         mrHpAttach.setCrePerCode(sysUser.getUserName());
         mrHpAttach.setCrePerName(sysUser.getNickName());
         this.mrHpAttachService.insertMrHpAttach(mrHpAttach);
         genMrHp.setOrgCd(sysOrg.getOrgCode());
         genMrHp.setOrgName(sysOrg.getOrgName());
         genMrHp.setCrePerCode(sysUser.getUserName());
         genMrHp.setCrePerName(sysUser.getNickName());
         this.mrHpMapper.insertMrHp(genMrHp);
      } else {
         mrHpAttach.setAltPerCode(sysUser.getUserName());
         mrHpAttach.setAltPerName(sysUser.getNickName());
         this.mrHpAttachService.updateMrHpAttach(mrHpAttach);
         genMrHp.setAltPerCode(sysUser.getUserName());
         genMrHp.setAltPerName(sysUser.getNickName());
         genMrHp.setOrgCd(sysOrg.getOrgCode());
         genMrHp.setOrgName(sysOrg.getOrgName());
         this.mrHpMapper.updateMrHp(genMrHp);
      }

      String[] staffCodes = new String[]{mrHpVo.getHd_cd(), mrHpVo.getArc_doc_cd(), mrHpVo.getAtt_doc_cd(), mrHpVo.getRes_doc_cd(), mrHpVo.getDuty_nur_cd(), mrHpVo.getStu_doc_cd(), mrHpVo.getInt_doc_cd(), mrHpVo.getCoder_cd(), mrHpVo.getQcon_doc_cd(), mrHpVo.getQcon_nur_cd()};
      List<String> staffCodeList = new ArrayList();

      for(String str : staffCodes) {
         if (StringUtils.isNotBlank(str)) {
            staffCodeList.add(str);
         }
      }

      staffCodes = (String[])staffCodeList.toArray(new String[0]);
      if (staffCodes != null) {
         this.sysUserService.addUseCount(sysUser.getDept().getDeptCode(), staffCodes);
      }

   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void saveSignAndMrHp(TdCmHpLineVo mrHpVo, EmrSignData emrSignData) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      this.saveMrHp(mrHpVo);
      this.mrHpSaveSign(mrHpVo, emrSignData);
      String[] staffCodes = new String[]{mrHpVo.getHd_cd(), mrHpVo.getArc_doc_cd(), mrHpVo.getAtt_doc_cd(), mrHpVo.getRes_doc_cd(), mrHpVo.getDuty_nur_cd(), mrHpVo.getStu_doc_cd(), mrHpVo.getInt_doc_cd(), mrHpVo.getCoder_cd(), mrHpVo.getQcon_doc_cd(), mrHpVo.getQcon_nur_cd()};
      List<String> staffCodeList = new ArrayList();

      for(String str : staffCodes) {
         if (StringUtils.isNotBlank(str)) {
            staffCodeList.add(str);
         }
      }

      staffCodes = (String[])staffCodeList.toArray(new String[0]);
      if (staffCodes != null) {
         this.sysUserService.addUseCount(user.getDept().getDeptCode(), staffCodes);
      }

   }

   public void mrHpSaveSign(TdCmHpLineVo mrHpVo, EmrSignData emrSignData) throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      Long dataId = SnowIdUtils.uniqueLong();
      emrSignData.setId(dataId);
      emrSignData.setTypeCd("2");
      emrSignData.setCertType("1");
      emrSignData.setSignFileId(mrHpVo.getRecord_id());
      emrSignData.setSignerCd(sysUser.getUserName());
      emrSignData.setSignerName(sysUser.getNickName());
      emrSignData.setSignTime(new Date());
      emrSignData.setTimeStamp(String.valueOf(System.currentTimeMillis()));
      emrSignData.setIsValid("1");
      emrSignData.setCrePerCode(sysUser.getUserName());
      emrSignData.setCrePerName(sysUser.getNickName());
      this.emrSignDataService.insertEmrSignData(emrSignData);
      EmrSignRecord emrSignRecord = new EmrSignRecord();
      emrSignRecord.setId(SnowIdUtils.uniqueLong());
      emrSignRecord.setMrFileId(Long.parseLong(mrHpVo.getRecord_id()));
      emrSignRecord.setSignDataId(dataId);
      emrSignRecord.setDocCode(sysUser.getUserName());
      emrSignRecord.setDocName(sysUser.getNickName());
      emrSignRecord.setSignTime(new Date());
      emrSignRecord.setSignCancFlag("0");
      emrSignRecord.setCrePerCode(sysUser.getUserName());
      emrSignRecord.setCrePerName(sysUser.getNickName());
      this.emrSignRecordService.insertEmrSignRecord(emrSignRecord);
      MrHp mrHp = new MrHp();
      mrHp.setRecordId(mrHpVo.getRecord_id());
      mrHp.setMrState("03");
      this.mrHpMapper.updateMrHpState(mrHp);
      this.basCertInfoService.updateCertPicSn(sysUser.getUserName(), mrHpVo.getSignPic(), emrSignData.getCertSn());
   }

   public List getQcErrorList(TdCmHpLineVo mrHpVo) throws Exception {
      List<EmrQcListVo> emrQcListVoList = mrHpVo.getEmrQcListList();
      List<EmrQcListVo> qcExcepationList = new ArrayList();
      if (emrQcListVoList != null && emrQcListVoList.size() > 0) {
         qcExcepationList = (List)emrQcListVoList.stream().filter((s) -> org.apache.commons.lang3.StringUtils.isNotEmpty(s.getTreatDesc()) && s.getQcState().equals("1")).collect(Collectors.toList());
      }

      if (mrHpVo.getRecord_id() != null) {
         EmrQcListVo emrQcListVo = new EmrQcListVo();
         List<String> qcSectionList = new ArrayList();
         qcSectionList.add("01");
         qcSectionList.add("04");
         emrQcListVo.setQcSectionList(qcSectionList);
         emrQcListVo.setMrFileId(mrHpVo.getRecord_id());
         List<EmrQcListVo> dbQcList = this.emrQcListService.selectEmrQcListList(emrQcListVo);
         if (dbQcList != null && !dbQcList.isEmpty()) {
            List<Long> qcIdList = qcExcepationList.size() > 0 ? (List)((List)qcExcepationList.stream().filter((s) -> s.getId() != null).collect(Collectors.toList())).stream().map((s) -> s.getId()).collect(Collectors.toList()) : null;

            for(EmrQcListVo emrQcListVo1 : dbQcList) {
               if (qcIdList == null || !qcIdList.contains(emrQcListVo1.getId())) {
                  qcExcepationList.add(emrQcListVo1);
               }
            }
         }
      }

      return qcExcepationList;
   }

   public List mrHpCheckOut(TdCmHpLineVo mrHpVo) throws Exception {
      List<TmCmRules> paramList = new ArrayList();
      List<String> kieTemplatesRes = new ArrayList(1);
      TmCmRules tmCmRules = new TmCmRules();
      tmCmRules.setRuleState("0");
      List<TmCmRules> rulesList = this.tmCmRulesService.selectTmCmRulesList(tmCmRules);
      this.getQcErrorList(mrHpVo);

      for(TmCmRules rules : rulesList) {
         String ruleFileName = "indocemr_" + rules.getId() + ".drl";
         File checkFileOld = new File(this.path + "/" + ruleFileName);
         if (checkFileOld.exists()) {
            kieTemplatesRes.add(ruleFileName);
            paramList.add(rules);
         }
      }

      Map<Long, List<TmCmRules>> idMapList = new HashMap();
      if (!rulesList.isEmpty()) {
         idMapList = (Map)rulesList.stream().collect(Collectors.groupingBy(TmCmRules::getId));
      }

      List<TmCmQcList> tmCmQcLists = new ArrayList(1);
      mrHpVo.setTmCmQcLists(tmCmQcLists);
      if (!kieTemplatesRes.isEmpty()) {
         this.kSession = this.kieTemplate.getKieSession((String[])kieTemplatesRes.toArray(new String[kieTemplatesRes.size()]));
         this.kSession.insert(mrHpVo);
         this.kSession.fireAllRules();
         this.kSession.delete(this.kSession.getFactHandle(mrHpVo));
         this.kSession.dispose();
      }

      List<EmrQcListVo> fixedQcList = new ArrayList();
      Date currDate = this.commonService.getDbSysdate();
      BasEmployee employee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      if (!tmCmQcLists.isEmpty()) {
         for(TmCmQcList tmCmQc : tmCmQcLists) {
            EmrQcListVo emrQcList = new EmrQcListVo();
            emrQcList.setPatientId(mrHpVo.getPatient_id());
            emrQcList.setMrType("61");
            emrQcList.setMrTypeName("病案首页");
            if (idMapList.containsKey(Long.parseLong(tmCmQc.getProRuleId()))) {
               TmCmRules rules = (TmCmRules)((List)idMapList.get(Long.parseLong(tmCmQc.getProRuleId()))).get(0);
               emrQcList.setRuleName(rules.getRuleName());
               emrQcList.setFlawDesc(rules.getRuleDesc());
               if (rules.getForceType().equals("1")) {
                  emrQcList.setDefeLevel("3");
               } else {
                  emrQcList.setDefeLevel("1");
               }

               emrQcList.setEmrEleId(rules.getRuleQcFieids());
               emrQcList.setEmrEleName(rules.getRuleDesc());
               emrQcList.setRuleId(rules.getId());
            }

            emrQcList.setMrFileId(mrHpVo.getRecord_id());
            emrQcList.setMrFileName("病案首页");
            emrQcList.setQcState("0");
            emrQcList.setQcdoctCd("sys");
            emrQcList.setQcdoctName("系统");
            emrQcList.setQcSection("01");
            emrQcList.setCrePerCode(employee.getEmplNumber());
            emrQcList.setCrePerName(employee.getEmplName());
            emrQcList.setCreDate(currDate);
            emrQcList.setQcDate(currDate);
            fixedQcList.add(emrQcList);
         }
      }

      List<EmrQcListVo> resultList = new ArrayList();
      resultList.addAll(fixedQcList);
      return resultList;
   }

   private MrHpAttach genMrHpAttach(TdCmHpLineVo tdCmHpLineVo) throws Exception {
      Map<String, Object> map = Obj2MapUtil.objectToMap(tdCmHpLineVo);
      String ss = JSON.toJSONString(map);
      MrHpAttach mrHpAttach = (MrHpAttach)JSON.parseObject(ss, MrHpAttach.class);
      return mrHpAttach;
   }

   private void genUnSaveHpFee(List mrHpFeeList, TdCmHpLineVo tdCmHpLineVo) {
      for(MrHpFee mrHpFee : mrHpFeeList) {
         String feeCd = mrHpFee.getFeeCd();
         BigDecimal amount = mrHpFee.getAmount() != null ? BigDecimal.valueOf(mrHpFee.getAmount()) : BigDecimal.ZERO;
         switch (feeCd) {
            case "0001":
               tdCmHpLineVo.setZfy(amount);
               break;
            case "000101":
               tdCmHpLineVo.setZfje(amount);
               break;
            case "0101":
               tdCmHpLineVo.setYbylfwf(amount);
               break;
            case "0102":
               tdCmHpLineVo.setYbzlczf(amount);
               break;
            case "0103":
               tdCmHpLineVo.setHlf(amount);
               break;
            case "0104":
               tdCmHpLineVo.setQtfy(amount);
               break;
            case "0201":
               tdCmHpLineVo.setBlzdf(amount);
               break;
            case "0202":
               tdCmHpLineVo.setSyszdf(amount);
               break;
            case "0203":
               tdCmHpLineVo.setYxxzdf(amount);
               break;
            case "0204":
               tdCmHpLineVo.setLczdxmf(amount);
               break;
            case "0301":
               tdCmHpLineVo.setFsszlxmf(amount);
               break;
            case "0302":
               tdCmHpLineVo.setSszlf(amount);
               break;
            case "0401":
               tdCmHpLineVo.setKff(amount);
               break;
            case "0501":
               tdCmHpLineVo.setZyzlf(amount);
               break;
            case "0502":
               tdCmHpLineVo.setZyzd(amount);
               break;
            case "0503":
               tdCmHpLineVo.setZyqt(amount);
               break;
            case "0601":
               tdCmHpLineVo.setXyf(amount);
               break;
            case "0701":
               tdCmHpLineVo.setZcyf(amount);
               break;
            case "0702":
               tdCmHpLineVo.setZcyf_zcy(amount);
               break;
            case "0801":
               tdCmHpLineVo.setXf(amount);
               break;
            case "0802":
               tdCmHpLineVo.setBdblzpf(amount);
               break;
            case "0803":
               tdCmHpLineVo.setQdblzpf(amount);
               break;
            case "0804":
               tdCmHpLineVo.setNxyzlzpf(amount);
               break;
            case "0805":
               tdCmHpLineVo.setXbyzlzpf(amount);
               break;
            case "0901":
               tdCmHpLineVo.setJcyycxyyclf(amount);
               break;
            case "0902":
               tdCmHpLineVo.setZlyycxyyclf(amount);
               break;
            case "0903":
               tdCmHpLineVo.setSsyycxyyclf(amount);
               break;
            case "1001":
               tdCmHpLineVo.setQtf(amount);
               break;
            case "010101":
               tdCmHpLineVo.setZybzlzf(amount);
               break;
            case "010102":
               tdCmHpLineVo.setZybzlzhzf(amount);
               break;
            case "030101":
               tdCmHpLineVo.setLcwlzlf(amount);
               break;
            case "030201":
               tdCmHpLineVo.setMzf(amount);
               break;
            case "030202":
               tdCmHpLineVo.setSsf(amount);
               break;
            case "050101":
               tdCmHpLineVo.setZywz(amount);
               break;
            case "050102":
               tdCmHpLineVo.setZygs(amount);
               break;
            case "050103":
               tdCmHpLineVo.setZcyjf(amount);
               break;
            case "050104":
               tdCmHpLineVo.setZytzl(amount);
               break;
            case "050105":
               tdCmHpLineVo.setZygczl(amount);
               break;
            case "050106":
               tdCmHpLineVo.setZytszl(amount);
               break;
            case "050301":
               tdCmHpLineVo.setZytstpjg(amount);
               break;
            case "050302":
               tdCmHpLineVo.setBzss(amount);
               break;
            case "060101":
               tdCmHpLineVo.setKjywfy(amount);
               break;
            case "070101":
               tdCmHpLineVo.setYljgzyzjf(amount);
         }
      }

   }

   private void genHpFee(TdCmHpLineVo tdCmHpLineVo) {
      TdCmHpLineVo feeHpVo = this.mrisMapper.selectMrisFee(tdCmHpLineVo.getRecord_id());
      if (feeHpVo != null) {
         tdCmHpLineVo.setZfy(feeHpVo.getZfy());
         tdCmHpLineVo.setZfje(feeHpVo.getZfje());
         tdCmHpLineVo.setYbylfwf(feeHpVo.getYbylfwf());
         tdCmHpLineVo.setYbzlczf(feeHpVo.getYbzlczf());
         tdCmHpLineVo.setHlf(feeHpVo.getHlf());
         tdCmHpLineVo.setQtfy(feeHpVo.getQtfy());
         tdCmHpLineVo.setBlzdf(feeHpVo.getBlzdf());
         tdCmHpLineVo.setSyszdf(feeHpVo.getSyszdf());
         tdCmHpLineVo.setYxxzdf(feeHpVo.getYxxzdf());
         tdCmHpLineVo.setLczdxmf(feeHpVo.getLczdxmf());
         tdCmHpLineVo.setFsszlxmf(feeHpVo.getFsszlxmf());
         tdCmHpLineVo.setSszlf(feeHpVo.getSszlf());
         tdCmHpLineVo.setKff(feeHpVo.getKff());
         tdCmHpLineVo.setZyzlf(feeHpVo.getZyzlf());
         tdCmHpLineVo.setZyzd(feeHpVo.getZyzd());
         tdCmHpLineVo.setZyqt(feeHpVo.getZyqt());
         tdCmHpLineVo.setXyf(feeHpVo.getXyf());
         tdCmHpLineVo.setZcyf_zcy(feeHpVo.getZcyf_zcy());
         tdCmHpLineVo.setXf(feeHpVo.getXf());
         tdCmHpLineVo.setBdblzpf(feeHpVo.getBdblzpf());
         tdCmHpLineVo.setQdblzpf(feeHpVo.getQdblzpf());
         tdCmHpLineVo.setNxyzlzpf(feeHpVo.getNxyzlzpf());
         tdCmHpLineVo.setXbyzlzpf(feeHpVo.getXbyzlzpf());
         tdCmHpLineVo.setJcyycxyyclf(feeHpVo.getJcyycxyyclf());
         tdCmHpLineVo.setZlyycxyyclf(feeHpVo.getZlyycxyyclf());
         tdCmHpLineVo.setSsyycxyyclf(feeHpVo.getSsyycxyyclf());
         tdCmHpLineVo.setQtf(feeHpVo.getQtf());
         tdCmHpLineVo.setZybzlzf(feeHpVo.getZybzlzf());
         tdCmHpLineVo.setZybzlzhzf(feeHpVo.getZybzlzhzf());
         tdCmHpLineVo.setLcwlzlf(feeHpVo.getLcwlzlf());
         tdCmHpLineVo.setMzf(feeHpVo.getMzf());
         tdCmHpLineVo.setSsf(feeHpVo.getSsf());
         tdCmHpLineVo.setZywz(feeHpVo.getZywz());
         tdCmHpLineVo.setZygs(feeHpVo.getZygs());
         tdCmHpLineVo.setZcyjf(feeHpVo.getZcyjf());
         tdCmHpLineVo.setZytzl(feeHpVo.getZytzl());
         tdCmHpLineVo.setZygczl(feeHpVo.getZygczl());
         tdCmHpLineVo.setZytszl(feeHpVo.getZytszl());
         tdCmHpLineVo.setZytstpjg(feeHpVo.getZytstpjg());
         tdCmHpLineVo.setBzss(feeHpVo.getBzss());
         tdCmHpLineVo.setKjywfy(feeHpVo.getKjywfy());
         tdCmHpLineVo.setYljgzyzjf(feeHpVo.getYljgzyzjf());
      }

   }

   private void openMrHpSetPatientinfo(TdCmHpLineVo tdCmHpLineVo) throws Exception {
      MrHpVo resultVo = this.mrHpMapper.selectVistVoByPatientId(tdCmHpLineVo.getPatient_id());
      if (StringUtils.isEmpty(tdCmHpLineVo.getHc_no())) {
         tdCmHpLineVo.setHc_no(resultVo.getHcNo());
      }

      if (tdCmHpLineVo.getOut_time() == null && resultVo.getOutTime() != null) {
         tdCmHpLineVo.setOut_time(resultVo.getOutTime());
         tdCmHpLineVo.setOut_room_no(resultVo.getInRoomNo());
         int days = DateUtils.getInHosDays(resultVo.getInhosTime(), resultVo.getOutTime());
         tdCmHpLineVo.setReal_inhos_days(days == 0 ? 1L : (long)days);
      }

      if (StringUtils.isEmpty(tdCmHpLineVo.getPay_type_cd())) {
         tdCmHpLineVo.setPay_type_cd(resultVo.getPayTypeCd());
      }

      if (StringUtils.isEmpty(tdCmHpLineVo.getCitizenship_cd())) {
         tdCmHpLineVo.setCitizenship_cd(resultVo.getCitizenshipCd());
      }

      if (StringUtils.isEmpty(tdCmHpLineVo.getCard_type_cd())) {
         tdCmHpLineVo.setCard_type_cd(resultVo.getCardTypeCd());
      }

      if (StringUtils.isEmpty(tdCmHpLineVo.getOut_dept_name())) {
         tdCmHpLineVo.setOut_dept_name(resultVo.getOutDeptName());
      }

      if (StringUtils.isEmpty(tdCmHpLineVo.getOut_dept_cd())) {
         tdCmHpLineVo.setOut_dept_cd(resultVo.getOutDeptCd());
      }

      if (StringUtils.isEmpty(tdCmHpLineVo.getOut_room_no()) && tdCmHpLineVo.getOut_time() != null) {
         tdCmHpLineVo.setOut_room_no(resultVo.getInRoomNo());
         tdCmHpLineVo.setReal_inhos_days((long)DateUtils.getInHosDays(tdCmHpLineVo.getInhos_time(), tdCmHpLineVo.getOut_time()));
      }

      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      if ("5".equals(sysUser.getDept().getSysFlag())) {
         tdCmHpLineVo.setObsDeptFlag(CommonConstants.BOOL_TRUE);
         List<BabyInfo> babyInfoList = this.babyInfoService.selectBabyInfoListByPatientId(tdCmHpLineVo.getPatient_id());
         if (babyInfoList.size() > 0) {
            for(int i = 0; i < babyInfoList.size(); ++i) {
               if (i == 0) {
                  tdCmHpLineVo.setBaby1_bir_weight(((BabyInfo)babyInfoList.get(i)).getWeight());
               }

               if (i == 1) {
                  tdCmHpLineVo.setBaby2_bir_weight(((BabyInfo)babyInfoList.get(i)).getWeight());
               }

               if (i == 2) {
                  tdCmHpLineVo.setBaby3_bir_weight(((BabyInfo)babyInfoList.get(i)).getWeight());
               }

               if (i == 3) {
                  tdCmHpLineVo.setBaby4_bir_weight(((BabyInfo)babyInfoList.get(i)).getWeight());
               }
            }
         }
      }

      if (StringUtils.isEmpty(tdCmHpLineVo.getAlle_drug())) {
         List<AlleInfo> alleList = this.iAlleInfoService.selectAlleInfoByPatientId(resultVo.getRecordNo());
         if (alleList.size() > 0) {
            StringBuilder sdb = new StringBuilder();

            for(AlleInfo alleInfo : alleList) {
               if (sdb.length() > 0) {
                  sdb.append(",").append(alleInfo.getAlleName());
               } else {
                  sdb.append(alleInfo.getAlleName());
               }
            }

            tdCmHpLineVo.setAlle_drug(sdb.toString());
            tdCmHpLineVo.setDrug_alle_flag(CommonConstants.MR_HP.DRUG_ALLE_FLAG_2.toString());
         }
      }

   }

   private TdCmHpLineVo selectMrHpPatientVisit(String patientId) throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      SysDept sysDept = sysUser.getDept();
      TdCmHpLineVo resultVo = this.mrisMapper.selectVistVoByPatientId(patientId);
      if (resultVo != null) {
         if (resultVo.getOut_time() != null) {
            resultVo.setOut_room_no(resultVo.getIn_room_no());
            int days = DateUtils.getInHosDays(resultVo.getInhos_time(), resultVo.getOut_time());
            resultVo.setReal_inhos_days(days == 0 ? 1L : (long)days);
         }

         String mrType = "XY";
         if ("4".equals(sysDept.getSysFlag())) {
            mrType = "ZY";
         }

         resultVo.setMr_type(mrType);
         if ("5".equals(sysDept.getSysFlag())) {
            resultVo.setObsDeptFlag(CommonConstants.BOOL_TRUE);
            List<BabyInfo> babyInfoList = this.babyInfoService.selectBabyInfoListByPatientId(patientId);
            if (babyInfoList.size() > 0) {
               for(int i = 0; i < babyInfoList.size(); ++i) {
                  if (i == 0) {
                     resultVo.setBaby1_bir_weight(((BabyInfo)babyInfoList.get(i)).getWeight());
                  }

                  if (i == 1) {
                     resultVo.setBaby2_bir_weight(((BabyInfo)babyInfoList.get(i)).getWeight());
                  }

                  if (i == 2) {
                     resultVo.setBaby3_bir_weight(((BabyInfo)babyInfoList.get(i)).getWeight());
                  }

                  if (i == 3) {
                     resultVo.setBaby4_bir_weight(((BabyInfo)babyInfoList.get(i)).getWeight());
                  }
               }
            }
         } else if (resultVo.getAge_y() != null && resultVo.getAge_m() != null && resultVo.getAge_d() != null && ((long)resultVo.getAge_y() == 0L && (long)resultVo.getAge_m() == 0L && (long)resultVo.getAge_d() == 0L || (long)resultVo.getAge_y() == 0L && (long)resultVo.getAge_m() == 0L && (long)resultVo.getAge_d() < 28L) && StringUtils.isNotEmpty(resultVo.getWeight())) {
            BigDecimal weight = (new BigDecimal(resultVo.getWeight())).multiply(BigDecimal.valueOf(1000L));
            resultVo.setInhos_weight(weight.longValue());
         }

         if (StringUtils.isEmpty(resultVo.getNow_addr_pro()) && StringUtils.isEmpty(resultVo.getNow_addr_cou()) && StringUtils.isEmpty(resultVo.getNow_addr_flagty())) {
            for(SysRegionInfo sysRegionInfo : this.iSysRegionInfoService.selectSysRegionDefault()) {
               if (sysRegionInfo.getRegLevel() == CommonConstants.MR_HP.REG_LEVEL_1) {
                  resultVo.setNow_addr_pro(sysRegionInfo.getName());
               }

               if (CommonConstants.MR_HP.REG_LEVEL_2 == sysRegionInfo.getRegLevel()) {
                  resultVo.setNow_addr_flagty(sysRegionInfo.getName());
               }

               if (CommonConstants.MR_HP.REG_LEVEL_3 == sysRegionInfo.getRegLevel()) {
                  resultVo.setNow_addr_cou(sysRegionInfo.getName());
               }
            }
         }

         List<AlleInfo> alleList = this.iAlleInfoService.selectAlleInfoByPatientId(resultVo.getRecord_no());
         if (alleList.size() > 0) {
            StringBuilder sdb = new StringBuilder();

            for(AlleInfo alleInfo : alleList) {
               if (sdb.length() > 0) {
                  sdb.append(",").append(alleInfo.getAlleName());
               } else {
                  sdb.append(alleInfo.getAlleName());
               }
            }

            resultVo.setAlle_drug(sdb.toString());
            resultVo.setDrug_alle_flag(CommonConstants.MR_HP.DRUG_ALLE_FLAG_2.toString());
         }
      }

      return resultVo;
   }
}
