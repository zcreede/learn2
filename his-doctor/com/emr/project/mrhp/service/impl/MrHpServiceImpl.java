package com.emr.project.mrhp.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.drools.core.KieTemplate;
import com.emr.common.constant.CommonConstants;
import com.emr.common.enums.DiagPrintInfoEnum;
import com.emr.common.utils.AgeUtil;
import com.emr.common.utils.BarCodeUtils;
import com.emr.common.utils.DateUtils;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.common.utils.StringUtils;
import com.emr.common.utils.spring.SpringUtils;
import com.emr.framework.aspectj.lang.annotation.DataSource;
import com.emr.framework.aspectj.lang.enums.DataSourceType;
import com.emr.framework.datasource.DruidUtil;
import com.emr.project.common.service.ICommonService;
import com.emr.project.docOrder.domain.vo.OrderSaveVo;
import com.emr.project.docOrder.util.OrderUtil;
import com.emr.project.emr.domain.EmrSignData;
import com.emr.project.emr.domain.EmrSignRecord;
import com.emr.project.emr.service.IEmrSignDataService;
import com.emr.project.emr.service.IEmrSignRecordService;
import com.emr.project.his.service.IHisSyncService;
import com.emr.project.mrhp.domain.MrHp;
import com.emr.project.mrhp.domain.MrHpAttach;
import com.emr.project.mrhp.domain.MrHpCheckSet;
import com.emr.project.mrhp.domain.MrHpDia;
import com.emr.project.mrhp.domain.MrHpFee;
import com.emr.project.mrhp.domain.MrHpIcu;
import com.emr.project.mrhp.domain.MrHpOpe;
import com.emr.project.mrhp.domain.vo.MedicalInforVo;
import com.emr.project.mrhp.domain.vo.MrHpAttachVo;
import com.emr.project.mrhp.domain.vo.MrHpCheckSetVo;
import com.emr.project.mrhp.domain.vo.MrHpFeePbVo;
import com.emr.project.mrhp.domain.vo.MrHpOpeVo;
import com.emr.project.mrhp.domain.vo.MrHpPrintVo;
import com.emr.project.mrhp.domain.vo.MrHpVo;
import com.emr.project.mrhp.domain.vo.PatientInfoDetailVo;
import com.emr.project.mrhp.domain.vo.PicInfoVo;
import com.emr.project.mrhp.domain.vo.PrintOperInfoVo;
import com.emr.project.mrhp.domain.vo.PrintXyDiagInfoVo;
import com.emr.project.mrhp.domain.vo.PrintZyDiagInfoVo;
import com.emr.project.mrhp.domain.vo.TdCmFeeVo;
import com.emr.project.mrhp.mapper.MrHpMapper;
import com.emr.project.mrhp.service.IMrHpAttachService;
import com.emr.project.mrhp.service.IMrHpCheckSetService;
import com.emr.project.mrhp.service.IMrHpDiaService;
import com.emr.project.mrhp.service.IMrHpFeeService;
import com.emr.project.mrhp.service.IMrHpIcuService;
import com.emr.project.mrhp.service.IMrHpOpeService;
import com.emr.project.mrhp.service.IMrHpService;
import com.emr.project.mrhp.service.MrHpCheckSet06UtilService;
import com.emr.project.other.domain.BasCertInfo;
import com.emr.project.other.mapper.BasCertInfoMapper;
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
import com.emr.project.qc.domain.vo.PatEventVo;
import com.emr.project.qc.service.IEmrQcListService;
import com.emr.project.qc.service.IEmrQcRecordService;
import com.emr.project.sys.domain.OpeTypeEnum;
import com.emr.project.sys.domain.SysRegionInfo;
import com.emr.project.sys.service.ISysRegionInfoService;
import com.emr.project.system.domain.BasEmployee;
import com.emr.project.system.domain.SyncDatasource;
import com.emr.project.system.domain.SysDept;
import com.emr.project.system.domain.SysDictData;
import com.emr.project.system.domain.SysOrg;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.domain.vo.SqlVo;
import com.emr.project.system.mapper.SysOrgMapper;
import com.emr.project.system.service.IBasEmployeeService;
import com.emr.project.system.service.ISyncDatasourceService;
import com.emr.project.system.service.ISysDeptService;
import com.emr.project.system.service.ISysDictDataService;
import com.emr.project.system.service.ISysEmrConfigService;
import com.emr.project.system.service.ISysUserService;
import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MrHpServiceImpl implements IMrHpService {
   protected final Logger log = LoggerFactory.getLogger(this.getClass());
   @Autowired
   private MrHpMapper mrHpMapper;
   @Autowired
   private IMrHpDiaService mrHpDiaService;
   @Autowired
   private IMrHpOpeService mrHpOpeService;
   @Autowired
   private ITransinfoService transinfoService;
   @Autowired
   private IBabyInfoService babyInfoService;
   @Autowired
   private ISyncDatasourceService iSyncDatasourceService;
   @Autowired
   private IMrHpFeeService iMrHpFeeService;
   @Autowired
   private ISysRegionInfoService iSysRegionInfoService;
   @Autowired
   private IAlleInfoService iAlleInfoService;
   @Autowired
   private IMrHpAttachService mrHpAttachService;
   @Autowired
   private IMrHpIcuService iMrHpIcuService;
   @Autowired
   private ISyncDatasourceService syncDatasourceService;
   @Autowired
   private IEmrSignDataService emrSignDataService;
   @Autowired
   private IEmrSignRecordService emrSignRecordService;
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;
   @Autowired
   private IBasCertInfoService basCertInfoService;
   @Autowired
   private IMrHpCheckSetService mrHpCheckSetService;
   @Autowired
   private KieTemplate kieTemplate;
   private KieSession kSession;
   @Autowired
   private IEmrQcRecordService emrQcRecordService;
   @Value("${spring.drools.path}")
   private String path;
   @Autowired
   private ICommonService commonService;
   @Autowired
   private IEmrQcListService emrQcListService;
   @Autowired
   private IBasEmployeeService basEmployeeService;
   @Autowired
   private IHisSyncService hisSyncService;
   @Autowired
   private ISysDeptService sysDeptService;
   @Autowired
   private SysOrgMapper sysOrgMapper;
   @Autowired
   private IVisitinfoService visitinfoService;
   @Autowired
   private ISysUserService sysUserService;
   @Autowired
   private IMrHpFeeService mrHpFeeService;
   @Autowired
   private BasCertInfoMapper basCertInfoMapper;
   @Autowired
   private ISysRegionInfoService sysRegionInfoService;
   @Autowired
   private ISysDictDataService sysDictDataService;

   public MrHp selectMrHpById(String recordId) {
      return this.mrHpMapper.selectMrHpById(recordId);
   }

   public MrHpVo selectMrHpVoById(String recordId) {
      return this.mrHpMapper.selectMrHpVoById(recordId);
   }

   public MrHpVo selectMrHpByPatientId(String patientId) {
      return this.mrHpMapper.selectMrHpByPatientId(patientId);
   }

   public List selectMrHpList(MrHp mrHp) {
      return this.mrHpMapper.selectMrHpList(mrHp);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void saveMrHp(MrHpVo mrHpVo) throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      SysOrg sysOrg = this.sysOrgMapper.selectSysOrgByOrgNo(sysUser.getHospital().getOrgCode());
      this.emrQcRecordService.saveMrHpQcRecord(mrHpVo);
      MrHp mrHp = this.mrHpMapper.selectMrHpById(mrHpVo.getRecordId());
      this.mrHpDiaService.insertMrHpDiaList(mrHpVo);
      this.mrHpOpeService.insertMrHpOpeList(mrHpVo);
      MrHpAttachVo mrHpAttachVo = mrHpVo.getMrHpAttachVo();
      mrHpAttachVo.setPatientId(mrHpVo.getPatientId());
      mrHpAttachVo.setRecordId(mrHpVo.getRecordId());
      if (mrHpAttachVo.getMrHpIcuList() != null && mrHpAttachVo.getMrHpIcuList().size() > 0) {
         this.iMrHpIcuService.insertMrHpIcuList(mrHpAttachVo);
      }

      String[] dictStr = new String[]{"RC035", "s022", "RC003", "RC038", "RC032"};
      List<SysDictData> dictList = this.sysDictDataService.selectDictDataListByType(dictStr);

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

      if (mrHp == null) {
         ((MrHpAttach)mrHpAttachVo).setCrePerCode(sysUser.getUserName());
         ((MrHpAttach)mrHpAttachVo).setCrePerName(sysUser.getNickName());
         this.mrHpAttachService.insertMrHpAttach(mrHpAttachVo);
         mrHpVo.setOrgCd(sysOrg.getOrgCode());
         mrHpVo.setOrgName(sysOrg.getOrgName());
         mrHpVo.setCrePerCode(sysUser.getUserName());
         mrHpVo.setCrePerName(sysUser.getNickName());
         this.mrHpMapper.insertMrHp(mrHpVo);
      } else {
         ((MrHpAttach)mrHpAttachVo).setAltPerCode(sysUser.getUserName());
         ((MrHpAttach)mrHpAttachVo).setAltPerName(sysUser.getNickName());
         this.mrHpAttachService.updateMrHpAttach(mrHpAttachVo);
         mrHpVo.setAltPerCode(sysUser.getUserName());
         mrHpVo.setAltPerName(sysUser.getNickName());
         mrHpVo.setOrgCd(sysOrg.getOrgCode());
         mrHpVo.setOrgName(sysOrg.getOrgName());
         this.mrHpMapper.updateMrHp(mrHpVo);
      }

      String[] staffCodes = new String[]{mrHpVo.getHdCd(), mrHpVo.getArcDocCd(), mrHpVo.getAttDocCd(), mrHpVo.getResDocCd(), mrHpVo.getDutyNurCd(), mrHpVo.getStuDocCd(), mrHpVo.getIntDocCd(), mrHpVo.getCoderCd(), mrHpVo.getQconDocCd(), mrHpVo.getQconNurCd()};
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

   public int deleteMrHpByIds(String[] recordIds) {
      return this.mrHpMapper.deleteMrHpByIds(recordIds);
   }

   public int deleteMrHpById(String recordId) {
      return this.mrHpMapper.deleteMrHpById(recordId);
   }

   public MrHpVo selectMrHpPatientDetail(MrHpVo mrHpVo) throws Exception {
      new MrHpVo();
      MrHpVo resultVo = this.mrHpMapper.selectMrHpByPatientId(mrHpVo.getPatientId());
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      SysDept sysDept = sysUser.getDept();
      SysDept sysDeptVis = new SysDept();
      VisitinfoVo visitinfoVo = this.visitinfoService.selectVisitinfoByPatientId(mrHpVo.getPatientId());
      if (visitinfoVo != null && StringUtils.isNotBlank(visitinfoVo.getInDocCd())) {
         sysDeptVis = this.sysDeptService.getOneByCode(visitinfoVo.getInDocCd());
      }

      if (resultVo == null) {
         resultVo = this.selectMrHpPatientVisit(mrHpVo.getPatientId());
         if (resultVo != null) {
            DiagInfo diagInfo = new DiagInfo();
            diagInfo.setPatientId(mrHpVo.getPatientId());
            if (sysDeptVis != null && StringUtils.isNotBlank(sysDeptVis.getMrClass()) && "ZY".equals(sysDeptVis.getMrClass())) {
               diagInfo.setDiagClass("ZY");
               List<MrHpDia> diaZYList = this.mrHpDiaService.selectDiagInfoByPatientId(diagInfo);
               resultVo.setMrHpDiaZYList(diaZYList);
               resultVo.setMrType("ZY");
            } else {
               diagInfo.setDiagClass("XY");
               List<MrHpDia> diaList = this.mrHpDiaService.selectDiagInfoByPatientId(diagInfo);
               resultVo.setMrHpDiaXYList(diaList);
               resultVo.setMrType("XY");
            }

            List<MrHpOpeVo> opeList = this.mrHpOpeService.selectOperatRecordByPatientId(mrHpVo.getPatientId());

            for(MrHpOpeVo vo : opeList) {
               if (StringUtils.isNotEmpty(vo.getOpeType())) {
                  vo.setOpeTypeName(OpeTypeEnum.getTypeNameByCode(vo.getOpeType()));
               }
            }

            resultVo.setMrHpOpeList(opeList);
            String severeCaseFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0080");
            if ("1".equals(severeCaseFlag)) {
               SqlVo sqlVo = new SqlVo();
               sqlVo.setInpNo(mrHpVo.getPatientId());
               List<CriticalVo> mapList = this.visitinfoService.selectCriticalInfo(sqlVo);
               List<MrHpIcu> mrHpIcuList = new ArrayList();
               if (mapList.size() > 0) {
                  for(CriticalVo criticalVo : mapList) {
                     MrHpIcu mrHpIcu = new MrHpIcu();
                     mrHpIcu.setIcuInTime(criticalVo.getInTime());
                     mrHpIcu.setIcuOutTime(criticalVo.getOutTime());
                     mrHpIcu.setIcuHour(criticalVo.getHours());
                     mrHpIcu.setIcuCode(criticalVo.getDeptId());
                     mrHpIcuList.add(mrHpIcu);
                  }

                  MrHpAttachVo mrHpAttachVo = new MrHpAttachVo();
                  mrHpAttachVo.setMrHpIcuList(mrHpIcuList);
                  mrHpVo.setMrHpAttachVo(mrHpAttachVo);
               }
            }
         }

         resultVo.setRecordId(resultVo.getOrgCd() + mrHpVo.getPatientId());
         Transinfo tranParam = new Transinfo();
         tranParam.setPatientId(mrHpVo.getPatientId());
         tranParam.setTranInState("2");
         List<Transinfo> inHosTran = this.transinfoService.selectTransinfoList(tranParam);
         if (inHosTran != null && !inHosTran.isEmpty()) {
            resultVo.setInDeptCd(((Transinfo)inHosTran.get(0)).getTiDeptCd());
            resultVo.setInDeptName(((Transinfo)inHosTran.get(0)).getTiDeptName());
         }
      } else {
         this.openMrHpSetPatientinfo(resultVo);
         if ("5".equals(sysDept.getSysFlag())) {
            resultVo.setObsDeptFlag(CommonConstants.BOOL_TRUE);
         }

         if ("03".equals(resultVo.getMrState())) {
            BasCertInfo basCertInfo = this.basCertInfoService.selectBasCertInfoByEmployeenumber(sysUser.getUserName());
            resultVo.setSignPic(basCertInfo != null ? basCertInfo.getCertPic() : "");
         }

         if (StringUtils.isNotEmpty(resultVo.getRecordId())) {
            MrHpDia mrHpDia = new MrHpDia();
            mrHpDia.setRecordId(resultVo.getRecordId());
            mrHpDia.setDiaType("XY");
            List<MrHpDia> mrHpDiaXYList = this.mrHpDiaService.selectMrHpDiaList(mrHpDia);
            resultVo.setMrHpDiaXYList(mrHpDiaXYList);
            if ("ZY".equals(resultVo.getMrType())) {
               mrHpDia.setDiaType("ZY");
               List<MrHpDia> mrHpDiaZYList = this.mrHpDiaService.selectMrHpDiaList(mrHpDia);
               resultVo.setMrHpDiaZYList(mrHpDiaZYList);
            }

            List<MrHpOpeVo> mrHpOpeList = this.mrHpOpeService.selectMrHpOpeByRescordId(resultVo.getRecordId());
            List<MrHpOpeVo> mrHpOpeList2 = this.voSortByOpeMain(mrHpOpeList);

            for(MrHpOpeVo vo : mrHpOpeList2) {
               if (StringUtils.isNotEmpty(vo.getOpeType())) {
                  vo.setOpeTypeName(OpeTypeEnum.getTypeNameByCode(vo.getOpeType()));
               }
            }

            resultVo.setMrHpOpeList(mrHpOpeList2);
         }
      }

      if (StringUtils.isNotEmpty(resultVo.getCardTypeNo()) && !resultVo.getCardTypeNo().equals("-") && "1".equals(resultVo.getCardTypeCd())) {
         String idCard = resultVo.getCardTypeNo();
         int idCardLength = idCard.length();
         if ((idCardLength == 15 || idCardLength == 18) && resultVo.getAgeY() == null && resultVo.getAgeM() == null && resultVo.getAgeD() == null && resultVo.getAgeH() == null && resultVo.getAgeMi() == null && resultVo.getInhosTime() != null) {
            Map<String, String> map = AgeUtil.getBirAgeSexByInhosDate(resultVo.getCardTypeNo(), resultVo.getInhosTime());
            if (!MapUtils.isEmpty(map)) {
               if (resultVo.getBirDate() == null) {
                  SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
                  resultVo.setBirDate(sdf.parse((String)map.get("birthday")));
               }

               if (resultVo.getAgeY() == null || resultVo.getAgeY() == 0L) {
                  resultVo.setAgeY(Long.parseLong((String)map.get("ageY")));
               }

               if (resultVo.getAgeM() == null || resultVo.getAgeM() == 0L) {
                  resultVo.setAgeM(Long.parseLong((String)map.get("ageM")));
               }

               if (resultVo.getAgeD() == null || resultVo.getAgeD() == 0L) {
                  resultVo.setAgeD(Long.parseLong((String)map.get("ageD")));
               }

               if (StringUtils.isEmpty(resultVo.getSexCd())) {
                  resultVo.setSexCd((String)map.get("sexCode"));
               }
            }
         }
      }

      List<Transinfo> transinfoList = this.transinfoService.selectTransinfoByPatientId(mrHpVo.getPatientId());
      resultVo.setTransinfoList(transinfoList);
      if ("03".equals(resultVo.getMrState())) {
         MrHpFee feeParam = new MrHpFee();
         feeParam.setRecordId(resultVo.getRecordId());
         List<MrHpFee> mrHpFeeList = this.iMrHpFeeService.selectMrHpFeeList(feeParam);
         resultVo.setMrHpFeeList(mrHpFeeList);
      } else {
         Map<String, Object> param = new HashMap();
         param.put("inpNo", resultVo.getInpNo());
         param.put("mrHpFee", new MrHpFee());
         List<MrHpFee> mrHpFeeList = this.iMrHpFeeService.selectMrHpFeeListByProc(param);
         resultVo.setMrHpFeeList(mrHpFeeList);

         for(MrHpFee mrHpFee : mrHpFeeList) {
            mrHpFee.setRecordId(resultVo.getRecordId());
            mrHpFee.setFeeId(resultVo.getRecordId() + mrHpFee.getFeeCd());
         }

         this.iMrHpFeeService.saveHpFeeList(mrHpFeeList);
      }

      int ageY = resultVo.getAgeY() != null ? resultVo.getAgeY().intValue() : 0;
      int ageM = resultVo.getAgeM() != null ? resultVo.getAgeM().intValue() : 0;
      int ageD = resultVo.getAgeD() != null ? resultVo.getAgeD().intValue() : 0;
      int ageH = resultVo.getAgeH() != null ? resultVo.getAgeH().intValue() : 0;
      int ageMi = resultVo.getAgeMi() != null ? resultVo.getAgeMi().intValue() : 0;
      String personAge = AgeUtil.getFormatAge(ageY, ageM, ageD, ageH, ageMi);
      if (ageY < 1) {
         resultVo.setPersonAge("0岁");
         resultVo.setBabyAge(personAge);
      } else {
         resultVo.setPersonAge(personAge);
         resultVo.setBabyAge("-");
      }

      resultVo.setBaby1_bir_weightS(resultVo.getBaby1BirWeight() != null ? resultVo.getBaby1BirWeight().toString() : "-");
      resultVo.setInhos_weightS(resultVo.getInhosWeight() != null ? resultVo.getInhosWeight().toString() : "-");
      return resultVo;
   }

   public void openMrHpSetPatientinfo(MrHpVo mrHpVo) throws Exception {
      MrHpVo resultVo = this.mrHpMapper.selectVistVoByPatientId(mrHpVo.getPatientId());
      if (StringUtils.isEmpty(mrHpVo.getHcNo())) {
         mrHpVo.setHcNo(resultVo.getHcNo());
      }

      if (mrHpVo.getOutTime() == null && resultVo.getOutTime() != null) {
         mrHpVo.setOutTime(resultVo.getOutTime());
         mrHpVo.setOutRoomNo(resultVo.getInRoomNo());
         int days = DateUtils.getInHosDays(resultVo.getInhosTime(), resultVo.getOutTime());
         mrHpVo.setRealInhosDays(days == 0 ? 1 : days);
      }

      if (StringUtils.isEmpty(mrHpVo.getPayTypeCd())) {
         mrHpVo.setPayTypeCd(resultVo.getPayTypeCd());
      }

      if (StringUtils.isEmpty(mrHpVo.getCitizenshipCd())) {
         mrHpVo.setCitizenshipCd(resultVo.getCitizenshipCd());
      }

      if (StringUtils.isEmpty(mrHpVo.getCardTypeCd())) {
         mrHpVo.setCardTypeCd(resultVo.getCardTypeCd());
      }

      if (StringUtils.isEmpty(mrHpVo.getOutDeptName())) {
         mrHpVo.setOutDeptName(resultVo.getOutDeptName());
      }

      if (StringUtils.isEmpty(mrHpVo.getOutDeptCd())) {
         mrHpVo.setOutDeptCd(resultVo.getOutDeptCd());
      }

      if (StringUtils.isEmpty(mrHpVo.getOutRoomNo()) && mrHpVo.getOutTime() != null) {
         mrHpVo.setOutRoomNo(resultVo.getInRoomNo());
         mrHpVo.setRealInhosDays(DateUtils.getInHosDays(mrHpVo.getInhosTime(), mrHpVo.getOutTime()));
      }

      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      if ("5".equals(sysUser.getDept().getSysFlag())) {
         mrHpVo.setObsDeptFlag(CommonConstants.BOOL_TRUE);
         List<BabyInfo> babyInfoList = this.babyInfoService.selectBabyInfoListByPatientId(mrHpVo.getPatientId());
         if (babyInfoList.size() > 0) {
            for(int i = 0; i < babyInfoList.size(); ++i) {
               if (i == 0) {
                  mrHpVo.setBaby1BirWeight(((BabyInfo)babyInfoList.get(i)).getWeight());
               }

               if (i == 1) {
                  mrHpVo.setBaby2BirWeight(((BabyInfo)babyInfoList.get(i)).getWeight());
               }

               if (i == 2) {
                  mrHpVo.setBaby3BirWeight(((BabyInfo)babyInfoList.get(i)).getWeight());
               }

               if (i == 3) {
                  mrHpVo.setBaby4BirWeight(((BabyInfo)babyInfoList.get(i)).getWeight());
               }
            }
         }
      }

      if (StringUtils.isEmpty(mrHpVo.getAlleDrug())) {
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

            mrHpVo.setAlleDrug(sdb.toString());
            mrHpVo.setDrugAlleFlag(CommonConstants.MR_HP.DRUG_ALLE_FLAG_2);
         }
      }

   }

   public MrHpVo selectMrHpPatientVisit(String patientId) throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      SysDept sysDept = sysUser.getDept();
      MrHpVo resultVo = this.mrHpMapper.selectVistVoByPatientId(patientId);
      if (resultVo != null) {
         if (resultVo.getOutTime() != null) {
            resultVo.setOutRoomNo(resultVo.getInRoomNo());
            int days = DateUtils.getInHosDays(resultVo.getInhosTime(), resultVo.getOutTime());
            resultVo.setRealInhosDays(days == 0 ? 1 : days);
         }

         String mrType = "XY";
         if ("4".equals(sysDept.getSysFlag())) {
            mrType = "ZY";
         }

         resultVo.setMrType(mrType);
         List<BabyInfo> babyInfoList = this.babyInfoService.selectBabyInfoListByPatientId(patientId);
         if (babyInfoList.size() > 0) {
            resultVo.setObsDeptFlag(CommonConstants.BOOL_TRUE);

            for(int i = 0; i < babyInfoList.size(); ++i) {
               if (i == 0 && ((BabyInfo)babyInfoList.get(i)).getWeight() != null) {
                  resultVo.setBaby1BirWeight(((BabyInfo)babyInfoList.get(i)).getWeight() * 1000L);
               }

               if (i == 1 && ((BabyInfo)babyInfoList.get(i)).getWeight() != null) {
                  resultVo.setBaby2BirWeight(((BabyInfo)babyInfoList.get(i)).getWeight() * 1000L);
               }

               if (i == 2 && ((BabyInfo)babyInfoList.get(i)).getWeight() != null) {
                  resultVo.setBaby3BirWeight(((BabyInfo)babyInfoList.get(i)).getWeight() * 1000L);
               }

               if (i == 3 && ((BabyInfo)babyInfoList.get(i)).getWeight() != null) {
                  resultVo.setBaby4BirWeight(((BabyInfo)babyInfoList.get(i)).getWeight() * 1000L);
               }
            }
         }

         if (resultVo.getAgeY() != null && resultVo.getAgeM() != null && resultVo.getAgeD() != null && (resultVo.getAgeY() == 0L && resultVo.getAgeM() == 0L && resultVo.getAgeD() == 0L || resultVo.getAgeY() == 0L && resultVo.getAgeM() == 0L && resultVo.getAgeD() < 28L) && StringUtils.isNotEmpty(resultVo.getWeight())) {
            BigDecimal weight = (new BigDecimal(resultVo.getWeight())).multiply(BigDecimal.valueOf(1000L));
            resultVo.setInhosWeight(weight.longValue());
         }

         SysOrg sysOrg = this.sysOrgMapper.checkOrgCodeUnique(SecurityUtils.getLoginUser().getUser().getHospital().getOrgCode());
         String nowAddrPro = resultVo.getNowAddrPro();
         if (StringUtils.isEmpty(resultVo.getNowAddrPro())) {
            resultVo.setNowAddrPro(sysOrg.getAddressSheng());
         }

         if (StringUtils.isEmpty(nowAddrPro) && StringUtils.isEmpty(resultVo.getNowAddrFlagty())) {
            resultVo.setNowAddrFlagty(sysOrg.getAddressShi());
         }

         if (StringUtils.isEmpty(nowAddrPro) && StringUtils.isEmpty(resultVo.getNowAddrCou())) {
            resultVo.setNowAddrCou(sysOrg.getAddressXian());
         }

         String citAddrPro = resultVo.getApAddrPro();
         if (StringUtils.isEmpty(citAddrPro)) {
            resultVo.setApAddrPro(sysOrg.getAddressSheng());
         }

         if (StringUtils.isEmpty(citAddrPro) && StringUtils.isEmpty(resultVo.getApAddrPlagty())) {
            resultVo.setApAddrPlagty(sysOrg.getAddressShi());
         }

         String rprAddrPro = resultVo.getRprAddrPro();
         if (StringUtils.isEmpty(rprAddrPro)) {
            resultVo.setRprAddrPro(sysOrg.getAddressSheng());
         }

         if (StringUtils.isEmpty(rprAddrPro) && StringUtils.isEmpty(resultVo.getRprAddrFlagty())) {
            resultVo.setRprAddrFlagty(sysOrg.getAddressShi());
         }

         if (StringUtils.isEmpty(rprAddrPro) && StringUtils.isEmpty(resultVo.getRprAddrCou())) {
            resultVo.setRprAddrCou(sysOrg.getAddressXian());
         }

         if (StringUtils.isEmpty(rprAddrPro) && StringUtils.isEmpty(resultVo.getRprAddr())) {
            resultVo.setRprAddr(sysOrg.getAddressXian());
         }

         if (StringUtils.isEmpty(resultVo.getPrpAddrPost()) && StringUtils.isNotEmpty(resultVo.getRprAddrPro()) && StringUtils.isNotEmpty(resultVo.getRprAddrFlagty()) && StringUtils.isNotEmpty(resultVo.getRprAddrCou())) {
            SysRegionInfo info = this.sysRegionInfoService.selectSysRegionInfoByName(resultVo.getRprAddrPro(), resultVo.getRprAddrFlagty(), resultVo.getRprAddrCou());
            if (info != null) {
               resultVo.setPrpAddrPost(info.getZipcode());
            }
         }

         String birAddrPro = resultVo.getBirAddrPro();
         if (StringUtils.isEmpty(birAddrPro)) {
            resultVo.setBirAddrPro(sysOrg.getAddressSheng());
         }

         if (StringUtils.isEmpty(birAddrPro) && StringUtils.isEmpty(resultVo.getBirAddrPlagty())) {
            resultVo.setBirAddrPlagty(sysOrg.getAddressShi());
         }

         if (StringUtils.isEmpty(birAddrPro) && StringUtils.isEmpty(resultVo.getBirAddrCou())) {
            resultVo.setBirAddrCou(sysOrg.getAddressXian());
         }

         String workAddrPro = resultVo.getWorkAddrPro();
         if (StringUtils.isEmpty(workAddrPro)) {
            resultVo.setWorkAddrPro(sysOrg.getAddressSheng());
         }

         if (StringUtils.isEmpty(workAddrPro) && StringUtils.isEmpty(resultVo.getWorkAddrFlagty())) {
            resultVo.setWorkAddrFlagty(sysOrg.getAddressShi());
         }

         if (StringUtils.isEmpty(workAddrPro) && StringUtils.isEmpty(resultVo.getWorkAddrCou())) {
            resultVo.setWorkAddrCou(sysOrg.getAddressXian());
         }

         if (StringUtils.isEmpty(workAddrPro) && StringUtils.isEmpty(resultVo.getWorkNameAddr())) {
            resultVo.setWorkNameAddr(sysOrg.getAddressXiang());
         }

         if (StringUtils.isEmpty(resultVo.getWorkPost()) && StringUtils.isNotEmpty(resultVo.getWorkAddrPro()) && StringUtils.isNotEmpty(resultVo.getWorkAddrFlagty()) && StringUtils.isNotEmpty(resultVo.getWorkAddrCou())) {
            SysRegionInfo info = this.sysRegionInfoService.selectSysRegionInfoByName(resultVo.getWorkAddrPro(), resultVo.getWorkAddrFlagty(), resultVo.getWorkAddrCou());
            if (info != null) {
               resultVo.setWorkPost(info.getZipcode());
            }
         }

         String contAddrPro = resultVo.getContAddrPro();
         if (StringUtils.isEmpty(contAddrPro)) {
            resultVo.setContAddrPro(sysOrg.getAddressSheng());
         }

         if (StringUtils.isEmpty(contAddrPro) && StringUtils.isBlank(resultVo.getContAddrFlagty())) {
            resultVo.setContAddrFlagty(sysOrg.getAddressShi());
         }

         if (StringUtils.isEmpty(contAddrPro) && StringUtils.isBlank(resultVo.getContAddrCou())) {
            resultVo.setContAddrCou(sysOrg.getAddressXian());
         }

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

            resultVo.setAlleDrug(sdb.toString());
            resultVo.setDrugAlleFlag(CommonConstants.MR_HP.DRUG_ALLE_FLAG_2);
         }

         if (StringUtils.isEmpty(resultVo.getNowAddrPro())) {
            resultVo.setNowAddrPro(sysOrg.getAddressSheng());
         }

         if (StringUtils.isEmpty(resultVo.getNowAddrPro()) && StringUtils.isEmpty(resultVo.getNowAddrFlagty())) {
            resultVo.setNowAddrFlagty(sysOrg.getAddressShi());
         }

         if (StringUtils.isEmpty(resultVo.getNowAddrPro()) && StringUtils.isEmpty(resultVo.getNowAddrCou())) {
            resultVo.setNowAddrCou(sysOrg.getAddressXian());
         }

         if (StringUtils.isEmpty(resultVo.getNowAddrPro()) && StringUtils.isEmpty(resultVo.getNowAddr())) {
            resultVo.setNowAddr(sysOrg.getAddressXiang());
         }

         if (StringUtils.isEmpty(resultVo.getNowAddrPost()) && StringUtils.isNotEmpty(resultVo.getNowAddrPro()) && StringUtils.isNotEmpty(resultVo.getNowAddrFlagty()) && StringUtils.isNotEmpty(resultVo.getNowAddrCou())) {
            SysRegionInfo info = this.sysRegionInfoService.selectSysRegionInfoByName(resultVo.getNowAddrPro(), resultVo.getNowAddrFlagty(), resultVo.getNowAddrCou());
            if (info != null) {
               resultVo.setNowAddrPost(info.getZipcode());
            }
         }
      }

      return resultVo;
   }

   public MrHpAttachVo selectMrHpAttachList(MrHpVo mrHpVo) {
      new MrHpAttachVo();
      MrHp mrHp = this.mrHpMapper.selectMrHpByPatientId(mrHpVo.getPatientId());
      MrHpAttachVo mrHpAttachVo;
      if (mrHp == null) {
         mrHpAttachVo = this.mrHpAttachService.selectMrHpAttachByPatientId(mrHpVo.getPatientId());
         if (mrHpAttachVo != null) {
            SyncDatasource syncDatasource = this.syncDatasourceService.selectSyncDatasourceByCode(DataSourceType.icuPatientInout.toString());
            SqlVo sqlVo = new SqlVo();
            sqlVo.setSqlStr(syncDatasource.getQuerySql());
            sqlVo.setInpNo(mrHpVo.getInpNo());
            List<MrHpIcu> mrHpIcuList = this.iMrHpIcuService.selectMrHpIcuDataSourceList(sqlVo);
            mrHpAttachVo.setMrHpIcuList(mrHpIcuList);
         }
      } else {
         mrHpAttachVo = this.mrHpAttachService.selectMrHpAttachByPatientId(mrHpVo.getPatientId());
         if (mrHpAttachVo != null) {
            List<MrHpIcu> mrHpIcuList = this.iMrHpIcuService.selectMrHpIcuListByRecordId(mrHpVo.getRecordId());
            mrHpAttachVo.setMrHpIcuList(mrHpIcuList);
         }
      }

      return mrHpAttachVo;
   }

   public void saveSignAndMrHp(MrHpVo mrHpVo, EmrSignData emrSignData) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      this.saveMrHp(mrHpVo);
      this.mrHpSaveSign(mrHpVo, emrSignData);
      String[] staffCodes = new String[]{mrHpVo.getHdCd(), mrHpVo.getArcDocCd(), mrHpVo.getAttDocCd(), mrHpVo.getResDocCd(), mrHpVo.getDutyNurCd(), mrHpVo.getStuDocCd(), mrHpVo.getIntDocCd(), mrHpVo.getCoderCd(), mrHpVo.getQconDocCd(), mrHpVo.getQconNurCd()};
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

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void mrHpSaveSign(MrHpVo mrHpVo, EmrSignData emrSignData) throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      Long dataId = SnowIdUtils.uniqueLong();
      emrSignData.setId(dataId);
      emrSignData.setTypeCd("2");
      emrSignData.setCertType("1");
      emrSignData.setSignFileId(mrHpVo.getRecordId());
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
      emrSignRecord.setMrFileId(Long.parseLong(mrHpVo.getRecordId()));
      emrSignRecord.setSignDataId(dataId);
      emrSignRecord.setDocCode(sysUser.getUserName());
      emrSignRecord.setDocName(sysUser.getNickName());
      emrSignRecord.setSignTime(new Date());
      emrSignRecord.setSignCancFlag("0");
      emrSignRecord.setCrePerCode(sysUser.getUserName());
      emrSignRecord.setCrePerName(sysUser.getNickName());
      this.emrSignRecordService.insertEmrSignRecord(emrSignRecord);
      MrHp mrHp = new MrHp();
      mrHp.setRecordId(mrHpVo.getRecordId());
      mrHp.setMrState("03");
      this.mrHpMapper.updateMrHpState(mrHp);
      this.basCertInfoService.updateCertPicSn(sysUser.getUserName(), mrHpVo.getSignPic(), emrSignData.getCertSn());
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void mrHpCancelSign(MrHp mrHp) throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      EmrSignRecord emrSignRecord = new EmrSignRecord();
      emrSignRecord.setMrFileId(Long.parseLong(mrHp.getRecordId()));
      emrSignRecord.setSignCancFlag("1");
      emrSignRecord.setAltPerCode(sysUser.getUserName());
      emrSignRecord.setAltPerName(sysUser.getNickName());
      this.emrSignRecordService.updateEmrSignRecordByMrFileId(emrSignRecord);
      EmrSignData emrSignData = new EmrSignData();
      emrSignData.setSignFileId(mrHp.getRecordId());
      emrSignData.setIsValid("0");
      emrSignData.setAltPerCode(sysUser.getUserName());
      emrSignData.setAltPerName(sysUser.getNickName());
      this.emrSignDataService.updateEmrSignDataByFileId(emrSignData);
      mrHp.setMrState("02");
      this.mrHpMapper.updateMrHpState(mrHp);
      MrHpVo mrHpVo = this.mrHpMapper.selectMrHpVoById(mrHp.getRecordId());
      String[] staffCodes = new String[]{mrHpVo.getHdCd(), mrHpVo.getArcDocCd(), mrHpVo.getAttDocCd(), mrHpVo.getResDocCd(), mrHpVo.getDutyNurCd(), mrHpVo.getStuDocCd(), mrHpVo.getIntDocCd(), mrHpVo.getCoderCd(), mrHpVo.getQconDocCd(), mrHpVo.getQconNurCd()};
      List<String> staffCodeList = new ArrayList();

      for(String str : staffCodes) {
         if (StringUtils.isNotBlank(str)) {
            staffCodeList.add(str);
         }
      }

      staffCodes = (String[])staffCodeList.toArray(new String[0]);
      if (staffCodes != null) {
         this.sysUserService.reduceUseCount(sysUser.getDept().getDeptCode(), staffCodes);
      }

   }

   public MrHpVo selectMrHpPrintView(MrHpVo mrHpVo) throws Exception {
      MrHpVo resultVo = this.mrHpMapper.selectMrHpByPatientId(mrHpVo.getPatientId());
      String spFlag = "";
      String mrType = "XY";
      SysDept sysDept1 = SecurityUtils.getLoginUser().getUser().getDept();
      VisitinfoVo visitinfoVo = this.visitinfoService.selectVisitinfoByPatientId(mrHpVo.getPatientId());
      if (visitinfoVo != null) {
         SysDept sysDept = this.sysDeptService.selectDeptByDeptCode(visitinfoVo.getDeptCd(), (Long)null);
         spFlag = sysDept.getSysFlag();
      }

      if ("4".equals(spFlag)) {
         mrType = "ZY";
      }

      if (resultVo != null) {
         resultVo.setMrType(mrType);
         MrHpDia mrHpDia = new MrHpDia();
         mrHpDia.setRecordId(resultVo.getRecordId());
         mrHpDia.setDiaType("XY");
         List<MrHpDia> mrHpDiaXYList = this.mrHpDiaService.selectMrHpDiaList(mrHpDia);
         resultVo.setMrHpDiaXYList(mrHpDiaXYList);
         if ("ZY".equals(resultVo.getMrType())) {
            mrHpDia.setDiaType("ZY");
            List<MrHpDia> mrHpDiaZYList = this.mrHpDiaService.selectMrHpDiaList(mrHpDia);
            resultVo.setMrHpDiaZYList(mrHpDiaZYList);
         }

         List<MrHpOpeVo> mrHpOpeList = this.mrHpOpeService.selectMrHpOpeByRescordId(resultVo.getRecordId());
         resultVo.setMrHpOpeList(mrHpOpeList);
         String[] dictStr = new String[]{"RC035", "s022", "RC003", "RC038", "RC032"};
         List<SysDictData> dictList = this.sysDictDataService.selectDictDataListByType(dictStr);

         for(SysDictData sysDictData : (List)dictList.stream().filter((s) -> s.getDictType().equals("RC035")).collect(Collectors.toList())) {
            if (StringUtils.isNotBlank(resultVo.getNationCd()) && sysDictData.getDictValue().equals(resultVo.getNationCd())) {
               resultVo.setNation(sysDictData.getDictLabel());
            }
         }

         for(SysDictData sysDictData : (List)dictList.stream().filter((s) -> s.getDictType().equals("RC038")).collect(Collectors.toList())) {
            if (StringUtils.isNotBlank(resultVo.getCitizenshipCd()) && sysDictData.getDictValue().equals(resultVo.getCitizenshipCd())) {
               resultVo.setCitizenship(sysDictData.getDictLabel());
            }
         }

         for(SysDictData sysDictData : (List)dictList.stream().filter((s) -> s.getDictType().equals("s022")).collect(Collectors.toList())) {
            if (StringUtils.isNotBlank(resultVo.getCardTypeCd()) && sysDictData.getDictValue().equals(resultVo.getCardTypeCd())) {
               resultVo.setCardTypeName(sysDictData.getDictLabel());
            }
         }

         for(SysDictData sysDictData : (List)dictList.stream().filter((s) -> s.getDictType().equals("RC003")).collect(Collectors.toList())) {
            if (StringUtils.isNotBlank(resultVo.getProTypeCd()) && sysDictData.getDictValue().equals(resultVo.getProTypeCd())) {
               resultVo.setProTypeName(sysDictData.getDictLabel());
            }
         }

         for(SysDictData sysDictData : (List)dictList.stream().filter((s) -> s.getDictType().equals("RC032")).collect(Collectors.toList())) {
            if (StringUtils.isNotBlank(resultVo.getPayTypeCd()) && sysDictData.getDictValue().equals(resultVo.getPayTypeCd())) {
               resultVo.setPayTypeName(sysDictData.getDictLabel());
            }
         }

         List<BasEmployee> basList = this.basEmployeeService.selectBasEmployeeAllList();
         if (basList != null && !basList.isEmpty()) {
            for(BasEmployee basEmployee : basList) {
               if (StringUtils.isNotBlank(resultVo.getHdCd()) && basEmployee.getEmplNumber().equals(resultVo.getHdCd())) {
                  resultVo.setHdName(basEmployee.getEmplName());
               }

               if (StringUtils.isNotBlank(resultVo.getArcDocCd()) && basEmployee.getEmplNumber().equals(resultVo.getArcDocCd())) {
                  resultVo.setArcDocName(basEmployee.getEmplName());
               }

               if (StringUtils.isNotBlank(resultVo.getAttDocCd()) && basEmployee.getEmplNumber().equals(resultVo.getAttDocCd())) {
                  resultVo.setAttDocName(basEmployee.getEmplName());
               }

               if (StringUtils.isNotBlank(resultVo.getResDocCd()) && basEmployee.getEmplNumber().equals(resultVo.getResDocCd())) {
                  resultVo.setResDocName(basEmployee.getEmplName());
               }

               if (StringUtils.isNotBlank(resultVo.getDutyNurCd()) && basEmployee.getEmplNumber().equals(resultVo.getDutyNurCd())) {
                  resultVo.setDutyNurName(basEmployee.getEmplName());
               }

               if (StringUtils.isNotBlank(resultVo.getStuDocCd()) && basEmployee.getEmplNumber().equals(resultVo.getStuDocCd())) {
                  resultVo.setStuDocName(basEmployee.getEmplName());
               }

               if (StringUtils.isNotBlank(resultVo.getIntDocCd()) && basEmployee.getEmplNumber().equals(resultVo.getIntDocCd())) {
                  resultVo.setIntDocName(basEmployee.getEmplName());
               }

               if (StringUtils.isNotBlank(resultVo.getCoderCd()) && basEmployee.getEmplNumber().equals(resultVo.getCoderCd())) {
                  resultVo.setCoderName(basEmployee.getEmplName());
               }
            }
         }

         MrHpFee mrHpFee = new MrHpFee();
         mrHpFee.setRecordId(resultVo.getRecordId());
         List<MrHpFee> mrHpFeeList = this.iMrHpFeeService.selectMrHpFeeList(mrHpFee);
         resultVo.setMrHpFeeList(mrHpFeeList);
      } else {
         resultVo = new MrHpVo();
         resultVo.setMrType(sysDept1.getMrClass());
      }

      int ageY = resultVo.getAgeY() != null ? resultVo.getAgeY().intValue() : 0;
      int ageM = resultVo.getAgeM() != null ? resultVo.getAgeM().intValue() : 0;
      int ageD = resultVo.getAgeD() != null ? resultVo.getAgeD().intValue() : 0;
      int ageH = resultVo.getAgeH() != null ? resultVo.getAgeH().intValue() : 0;
      int ageMi = resultVo.getAgeMi() != null ? resultVo.getAgeMi().intValue() : 0;
      String personAge = AgeUtil.getFormatAge(ageY, ageM, ageD, ageH, ageMi);
      if (ageY < 1) {
         resultVo.setPersonAge("0岁");
         resultVo.setBabyAge(personAge);
      } else {
         resultVo.setPersonAge(personAge);
         resultVo.setBabyAge("-");
      }

      resultVo.setContAddrPro(StringUtils.isNotBlank(resultVo.getContAddrPro()) ? resultVo.getContAddrPro() : "-");
      resultVo.setWorkAddrPro(StringUtils.isNotBlank(resultVo.getWorkAddrPro()) ? resultVo.getWorkAddrPro() : "-");
      resultVo.setWorkPost(StringUtils.isNotBlank(resultVo.getWorkPost()) ? resultVo.getWorkPost() : "-");
      resultVo.setWorkTel(StringUtils.isNotBlank(resultVo.getWorkTel()) ? resultVo.getWorkTel() : "-");
      resultVo.setBaby1_bir_weightS(resultVo.getBaby1BirWeight() != null ? resultVo.getBaby1BirWeight().toString() : "-");
      resultVo.setInhos_weightS(resultVo.getInhosWeight() != null ? resultVo.getInhosWeight().toString() : "-");
      resultVo.setChDeptName(StringUtils.isNotBlank(resultVo.getChDeptName()) ? resultVo.getChDeptName() : "-");
      resultVo.setAlleDrug(StringUtils.isNotBlank(resultVo.getAlleDrug()) ? resultVo.getAlleDrug() : "-");
      resultVo.setHcNo(StringUtils.isNotBlank(resultVo.getHcNo()) ? resultVo.getHcNo() : "-");
      return resultVo;
   }

   private MrHpVo getMrHpPrintViewHis(SyncDatasource mrhpSource, SqlVo sqlVoMrHp, SqlVo sqlVoMrHpDia, SqlVo sqlVoMrHpOpe, SqlVo sqlVoMrHpFee, SqlVo sqlVoMrHpKjFee, SqlVo sqlVoMrHpZfFee, MrHpVo mrHpVo) throws Exception {
      MrHpVo resultVo = null;

      try {
         DruidUtil.switchDB(mrhpSource);
         resultVo = this.mrHpMapper.selectPbHisMrHp(sqlVoMrHp);
         List<MrHpDia> mrHpDias = this.mrHpDiaService.selectHisPbMrDiaList(sqlVoMrHpDia);
         List<MrHpDia> mrHpDiaXyList = CollectionUtils.isNotEmpty(mrHpDias) ? (List)mrHpDias.stream().filter((t) -> t.getDiaType().equals("XY") && (t.getDiaClass().equals("1") || t.getDiaClass().equals("2"))).collect(Collectors.toList()) : null;
         List<MrHpDia> mrHpDiaZyList = CollectionUtils.isNotEmpty(mrHpDias) ? (List)mrHpDias.stream().filter((t) -> t.getDiaType().equals("ZY") && (t.getDiaClass().equals("1") || t.getDiaClass().equals("2"))).collect(Collectors.toList()) : null;
         if (CollectionUtils.isNotEmpty(mrHpDiaXyList)) {
            mrHpDiaXyList.stream().forEach((t) -> {
               if (t.getDiaClass().equals("1")) {
                  t.setDiaClass("01");
               }

               if (t.getDiaClass().equals("2")) {
                  t.setDiaClass("02");
               }

               t.setDiaItem("03");
            });
         }

         if (CollectionUtils.isNotEmpty(mrHpDiaZyList)) {
            mrHpDiaZyList.stream().forEach((t) -> {
               if (t.getDiaClass().equals("1")) {
                  t.setDiaClass("01");
               }

               if (t.getDiaClass().equals("2")) {
                  t.setDiaClass("02");
               }

               t.setDiaItem("03");
            });
         }

         if (StringUtils.isNotBlank(resultVo.getOsWmdCd()) && StringUtils.isNotBlank(resultVo.getOsWmdName())) {
            mrHpDiaXyList = (List<MrHpDia>)(CollectionUtils.isEmpty(mrHpDiaXyList) ? new ArrayList(1) : mrHpDiaXyList);
            MrHpDia temp = new MrHpDia();
            temp.setDiaItem("01");
            temp.setDiaClass("01");
            temp.setDiaCd(resultVo.getOsWmdCd());
            temp.setDiaName(resultVo.getOsWmdName());
            mrHpDiaXyList.add(temp);
         }

         if (StringUtils.isNotBlank(resultVo.getOsChdCd()) && StringUtils.isNotBlank(resultVo.getOsChdName())) {
            mrHpDiaZyList = (List<MrHpDia>)(CollectionUtils.isEmpty(mrHpDiaZyList) ? new ArrayList(1) : mrHpDiaZyList);
            MrHpDia temp = new MrHpDia();
            temp.setDiaItem("01");
            temp.setDiaClass("01");
            temp.setDiaCd(resultVo.getOsChdCd());
            temp.setDiaName(resultVo.getOsChdName());
            mrHpDiaZyList.add(temp);
         }

         resultVo.setMrHpDiaXYList(mrHpDiaXyList);
         resultVo.setMrHpDiaZYList(mrHpDiaZyList);
         List<MrHpDia> mrHpDiaBlList = CollectionUtils.isNotEmpty(mrHpDias) ? (List)mrHpDias.stream().filter((t) -> t.getDiaClass().equals("3")).collect(Collectors.toList()) : null;
         List<MrHpDia> mrHpDiaSsList = CollectionUtils.isNotEmpty(mrHpDias) ? (List)mrHpDias.stream().filter((t) -> t.getDiaClass().equals("4")).collect(Collectors.toList()) : null;
         if (CollectionUtils.isNotEmpty(mrHpDiaBlList)) {
            MrHpDia blDia = (MrHpDia)mrHpDiaBlList.get(0);
            resultVo.setPatDiaCd(blDia.getDiaCd());
            resultVo.setPatDiaName(blDia.getDiaName());
         }

         if (CollectionUtils.isNotEmpty(mrHpDiaSsList)) {
            MrHpDia ssDia = (MrHpDia)mrHpDiaSsList.get(0);
            resultVo.setHarmOr(ssDia.getDiaName());
            resultVo.setHarmOrCd(ssDia.getDiaCd());
         }

         List<MrHpOpeVo> mrHpOpeList = this.mrHpOpeService.selectPbHisMrHpOpe(sqlVoMrHpOpe);
         resultVo.setMrHpOpeList(mrHpOpeList);
         List<MrHpFeePbVo> mrHpFeePbVoList = this.iMrHpFeeService.selectHisPbMrHpFeeList(sqlVoMrHpFee);
         List<MrHpFee> mrHpFeeList = null;
         if (CollectionUtils.isNotEmpty(mrHpFeePbVoList)) {
            BigDecimal amountAll = new BigDecimal("0");
            mrHpFeeList = new ArrayList(1);

            for(MrHpFeePbVo feePbVo : mrHpFeePbVoList) {
               BigDecimal amountTemp = new BigDecimal(feePbVo.getJe());
               MrHpFee mrHpFee = new MrHpFee();
               String feeCd = feePbVo.getBafybh();
               switch (feePbVo.getBafybh()) {
                  case "1":
                     feeCd = "0101";
                     break;
                  case "2":
                     feeCd = "0102";
                     break;
                  case "3":
                     feeCd = "0103";
                     break;
                  case "4":
                     feeCd = "0104";
                     break;
                  case "5":
                     feeCd = "0201";
                     break;
                  case "6":
                     feeCd = "0202";
                     break;
                  case "7":
                     feeCd = "0203";
                     break;
                  case "8":
                     feeCd = "0204";
                     break;
                  case "9":
                     feeCd = "0301";
                     break;
                  case "10":
                     feeCd = "0302";
                     break;
                  case "11":
                     feeCd = "0401";
                     break;
                  case "12":
                     if (mrHpVo.getMrType().equals("XY")) {
                        feeCd = "0501";
                     }
                     break;
                  case "13":
                     feeCd = "0601";
                     break;
                  case "14":
                     feeCd = "0701";
                     break;
                  case "15":
                     feeCd = "0702";
                     break;
                  case "16":
                     feeCd = "0801";
                     break;
                  case "17":
                     feeCd = "0802";
                     break;
                  case "18":
                     feeCd = "0803";
                     break;
                  case "19":
                     feeCd = "0804";
                     break;
                  case "20":
                     feeCd = "0805";
                     break;
                  case "21":
                     feeCd = "0901";
                     break;
                  case "22":
                     feeCd = "0902";
                     break;
                  case "23":
                     feeCd = "0903";
                     break;
                  case "24":
                     feeCd = "1001";
                     break;
                  case "25":
                     feeCd = "030202";
                     break;
                  case "26":
                     feeCd = "030201";
               }

               mrHpFee.setFeeCd(feeCd);
               mrHpFee.setAmount(amountTemp.doubleValue());
               mrHpFeeList.add(mrHpFee);
               if (feePbVo.getBz().equals("1")) {
                  amountAll = amountAll.add(amountTemp);
               }
            }

            MrHpFee mrHpFeeAll = new MrHpFee();
            mrHpFeeAll.setFeeCd("0001");
            mrHpFeeAll.setAmount(amountAll.doubleValue());
            mrHpFeeList.add(mrHpFeeAll);
         }

         MrHpFeePbVo mrHpFeeKjPbVo = this.iMrHpFeeService.selectHisPbMrHpFeeKJList(sqlVoMrHpKjFee);
         if (mrHpFeeKjPbVo != null && mrHpFeeKjPbVo.getJe() != null) {
            MrHpFee mrHpFee = new MrHpFee();
            mrHpFee.setFeeCd("000101");
            mrHpFee.setAmount(Double.valueOf(mrHpFeeKjPbVo.getJe()));
            mrHpFeeList.add(mrHpFee);
         }

         MrHpFeePbVo mrHpFeeZfPbVo = this.iMrHpFeeService.selectHisPbMrHpFeeZFList(sqlVoMrHpZfFee);
         if (mrHpFeeZfPbVo != null && mrHpFeeZfPbVo.getJe() != null) {
            MrHpFee mrHpFee = new MrHpFee();
            mrHpFee.setFeeCd("060101");
            mrHpFee.setAmount(Double.valueOf(mrHpFeeZfPbVo.getJe()));
            mrHpFeeList.add(mrHpFee);
         }

         resultVo.setMrHpFeeList(mrHpFeeList);
      } finally {
         DruidUtil.clearDataSource();
      }

      return resultVo;
   }

   public MrHpVo selectMrHpPrintViewHis(MrHpVo mrHpVo) throws Exception {
      SyncDatasource mrhpSource = this.syncDatasourceService.selectSyncDatasourceByCode(DataSourceType.hisMrHp.toString());
      SyncDatasource mrhpDiaSource = this.syncDatasourceService.selectSyncDatasourceByCode(DataSourceType.hisMrHpDia.toString());
      SyncDatasource mrhpOpeSource = this.syncDatasourceService.selectSyncDatasourceByCode(DataSourceType.hisMrHpOpe.toString());
      SyncDatasource mrhpFeeSource = this.syncDatasourceService.selectSyncDatasourceByCode(DataSourceType.hisMrHpFee.toString());
      SyncDatasource mrhpFeeKjSource = this.syncDatasourceService.selectSyncDatasourceByCode(DataSourceType.hisMrHpFeeKj.toString());
      SyncDatasource mrhpFeeZfSource = this.syncDatasourceService.selectSyncDatasourceByCode(DataSourceType.hisMrHpFeeZf.toString());
      SqlVo sqlVoMrHp = new SqlVo(mrhpSource.getQuerySql());
      sqlVoMrHp.setInpNo(mrHpVo.getPatientId());
      SqlVo sqlVoMrHpDia = new SqlVo(mrhpDiaSource.getQuerySql());
      sqlVoMrHpDia.setInpNo(mrHpVo.getPatientId());
      SqlVo sqlVoMrHpOpe = new SqlVo(mrhpOpeSource.getQuerySql());
      sqlVoMrHpOpe.setInpNo(mrHpVo.getPatientId());
      SqlVo sqlVoMrHpFee = new SqlVo(mrhpFeeSource.getQuerySql());
      sqlVoMrHpFee.setInpNo(mrHpVo.getPatientId());
      SqlVo sqlVoMrHpKjFee = new SqlVo(mrhpFeeKjSource.getQuerySql());
      sqlVoMrHpKjFee.setInpNo(mrHpVo.getPatientId());
      SqlVo sqlVoMrHpZfFee = new SqlVo(mrhpFeeZfSource.getQuerySql());
      sqlVoMrHpZfFee.setInpNo(mrHpVo.getPatientId());
      MrHpVo resultVo = this.getMrHpPrintViewHis(mrhpSource, sqlVoMrHp, sqlVoMrHpDia, sqlVoMrHpOpe, sqlVoMrHpFee, sqlVoMrHpKjFee, sqlVoMrHpZfFee, mrHpVo);
      return resultVo;
   }

   public Map selectMrHpDictMap() throws Exception {
      new ArrayList();
      Map<String, List<SysDictData>> map = new HashMap();
      String dictStr = this.sysEmrConfigService.selectSysEmrConfigByKey("0003");
      if (StringUtils.isNotEmpty(dictStr)) {
         String[] dictType = dictStr.split(",");
         List dictDataList = this.sysDictDataService.selectDictDataListByType(dictType);
         map = (Map)dictDataList.stream().collect(Collectors.groupingBy((t) -> t.getDictType()));
      }

      return map;
   }

   public Map selectMrHpSignPicList(String recordId) throws Exception {
      String code = this.mrHpMapper.selectMrHpSignCodes(recordId);
      MrHp mrHp = this.mrHpMapper.selectMrHpById(recordId);
      String[] signStr = code.split(",");
      List<BasCertInfo> basList = this.basCertInfoService.selectBasCertInfoByEmployeenumberList(signStr);
      Map<String, String> map = new HashMap();
      map.put("hdCd", "");
      map.put("arcDocCd", "");
      map.put("attDocCd", "");
      map.put("resDocCd", "");
      map.put("dutyNurCd", "");
      map.put("stuDocCd", "");
      map.put("intDocCd", "");
      map.put("coderCd", "");
      map.put("qconDocCd", "");
      map.put("qconNurCd", "");

      for(BasCertInfo basCertInfo : basList) {
         String pic = basCertInfo.getCertPic() != null ? basCertInfo.getCertPic() : "";
         if (basCertInfo.getEmployeenumber().equals(mrHp.getHdCd())) {
            map.put("hdCd", pic);
         }

         if (basCertInfo.getEmployeenumber().equals(mrHp.getArcDocCd())) {
            map.put("arcDocCd", pic);
         }

         if (basCertInfo.getEmployeenumber().equals(mrHp.getAttDocCd())) {
            map.put("attDocCd", pic);
         }

         if (basCertInfo.getEmployeenumber().equals(mrHp.getResDocCd())) {
            map.put("resDocCd", pic);
         }

         if (basCertInfo.getEmployeenumber().equals(mrHp.getDutyNurCd())) {
            map.put("dutyNurCd", pic);
         }

         if (basCertInfo.getEmployeenumber().equals(mrHp.getStuDocCd())) {
            map.put("stuDocCd", pic);
         }

         if (basCertInfo.getEmployeenumber().equals(mrHp.getIntDocCd())) {
            map.put("intDocCd", pic);
         }

         if (basCertInfo.getEmployeenumber().equals(mrHp.getCoderCd())) {
            map.put("coderCd", pic);
         }

         if (basCertInfo.getEmployeenumber().equals(mrHp.getQconDocCd())) {
            map.put("qconDocCd", pic);
         }

         if (basCertInfo.getEmployeenumber().equals(mrHp.getQconNurCd())) {
            map.put("qconNurCd", pic);
         }
      }

      return map;
   }

   public String verificationRequired(MrHpVo mrHpVo, MrHpAttachVo mrHpAttachVo) throws Exception {
      Map<String, String> map = new HashMap();
      if (StringUtils.isEmpty(mrHpVo.getRecordNo())) {
         map.put("recordNo", "病案号不能为空");
      }

      if (StringUtils.isEmpty(mrHpVo.getSexCd())) {
         map.put("sexCd", "性别代码不能为空");
      }

      if (mrHpVo.getBirDate() == null) {
         map.put("birDate", "出生日期不能为空");
      }

      if (mrHpVo.getAgeY() == null && mrHpVo.getAgeD() == null && mrHpVo.getAgeH() == null && mrHpVo.getAgeM() == null && mrHpVo.getAgeMi() == null) {
         map.put("ageY", "年龄不能为空");
      }

      if (mrHpVo.getAgeY() == null && mrHpVo.getAgeM() == null && mrHpVo.getAgeD() != null && mrHpVo.getAgeD() < 28L) {
         if (mrHpVo.getInhosWeight() == null) {
            map.put("inhosWeight", "出生小于28天，新生儿入院体重必填");
         }

         if (mrHpVo.getBaby1BirWeight() == null) {
            map.put("baby1BirWeight", "出生小于28天，新生儿出生体重必填");
         }
      }

      if (StringUtils.isEmpty(mrHpVo.getPayTypeCd())) {
         map.put("payTypeCd", "医疗付费方式代码不能为空");
      }

      if (StringUtils.isEmpty(mrHpVo.getPatientName())) {
         map.put("patientName", "患者名称不能为空");
      }

      if (StringUtils.isEmpty(mrHpVo.getBirAddrPro())) {
         map.put("birAddrPro", "出生地省份不能为空");
      }

      if (StringUtils.isEmpty(mrHpVo.getBirAddrPlagty())) {
         map.put("birAddrPlagty", "出生地市不能为空");
      }

      if (StringUtils.isEmpty(mrHpVo.getBirAddrCou())) {
         map.put("birAddrCou", "出生地县不能为空");
      }

      if (StringUtils.isEmpty(mrHpVo.getApAddrPlagty())) {
         map.put("apAddrPlagty", "籍贯市不能为空");
      }

      if (StringUtils.isEmpty(mrHpVo.getApAddrPro())) {
         map.put("apAddrPro", "籍贯省不能为空");
      }

      if (StringUtils.isEmpty(mrHpVo.getNationCd())) {
         map.put("nationCd", "民族代码不能为空");
      }

      if (StringUtils.isEmpty(mrHpVo.getCardTypeNo())) {
         map.put("cardTypeNo", "身份证号不能为空");
      }

      if (StringUtils.isEmpty(mrHpVo.getProTypeCd())) {
         map.put("proTypeCd", "职业类型代码不能为空");
      }

      if (StringUtils.isNotEmpty(mrHpVo.getProTypeCd()) && mrHpVo.getProTypeCd().equals("90") && StringUtils.isEmpty(mrHpVo.getProTypeRem())) {
         map.put("proTypeRem", "职业说明不能为空");
      }

      if (StringUtils.isEmpty(mrHpVo.getMarStaCd())) {
         map.put("marStaCd", "婚姻状况代码不能为空");
      }

      if (StringUtils.isEmpty(mrHpVo.getNowAddrPro())) {
         map.put("nowAddrPro", "现住址省份");
      }

      if (StringUtils.isEmpty(mrHpVo.getNowAddrFlagty())) {
         map.put("nowAddrFlagty", "现住址市");
      }

      if (StringUtils.isEmpty(mrHpVo.getNowAddrCou())) {
         map.put("nowAddrCou", "现住址县");
      }

      if (StringUtils.isEmpty(mrHpVo.getNowAddr())) {
         map.put("nowAddr", "现住址");
      }

      if (StringUtils.isEmpty(mrHpVo.getNowAddrPost())) {
         map.put("nowAddrPost", "现住址邮编");
      }

      if (StringUtils.isEmpty(mrHpVo.getNowAddrTel())) {
         map.put("nowAddrTel", "现住址电话");
      }

      if (StringUtils.isEmpty(mrHpVo.getRprAddrPro())) {
         map.put("rprAddrPro", "户口省份");
      }

      if (StringUtils.isEmpty(mrHpVo.getRprAddrFlagty())) {
         map.put("rprAddrFlagty", "户口市");
      }

      if (StringUtils.isEmpty(mrHpVo.getRprAddrCou())) {
         map.put("rprAddrCou", "户口县");
      }

      if (StringUtils.isEmpty(mrHpVo.getRprAddr())) {
         map.put("rprAddr", "户口地址");
      }

      if (StringUtils.isEmpty(mrHpVo.getPrpAddrPost())) {
         map.put("prpAddrPost", "户口邮编");
      }

      if (StringUtils.isEmpty(mrHpVo.getWorkAddrPro())) {
         map.put("workAddrPro", "工作单位省份");
      }

      if (StringUtils.isEmpty(mrHpVo.getWorkAddrFlagty())) {
         map.put("workAddrFlagty", "工作单位市");
      }

      if (StringUtils.isEmpty(mrHpVo.getWorkAddrCou())) {
         map.put("workAddrCou", "工作单位县");
      }

      if (StringUtils.isEmpty(mrHpVo.getWorkNameAddr())) {
         map.put("workNameAddr", "工作单位及地址");
      }

      if (StringUtils.isEmpty(mrHpVo.getWorkPost())) {
         map.put("workPost", "单位地址邮编");
      }

      if (StringUtils.isEmpty(mrHpVo.getWorkTel())) {
         map.put("workTel", "单位电话");
      }

      if (StringUtils.isEmpty(mrHpVo.getContAddrPro())) {
         map.put("contAddrPro", "联系人地址省");
      }

      if (StringUtils.isEmpty(mrHpVo.getContAddrFlagty())) {
         map.put("contAddrFlagty", "联系人地址市");
      }

      if (StringUtils.isEmpty(mrHpVo.getContAddrCou())) {
         map.put("contAddrCou", "联系人地址县");
      }

      if (StringUtils.isEmpty(mrHpVo.getContAddr())) {
         map.put("contAddr", "联系人地址");
      }

      if (StringUtils.isEmpty(mrHpVo.getContName())) {
         map.put("contName", "联系人姓名不能为空");
      }

      if (StringUtils.isEmpty(mrHpVo.getConRelaCd())) {
         map.put("conRelaCd", "联系人关系代码不能为空");
      }

      if (StringUtils.isNotEmpty(mrHpVo.getConRelaCd()) && mrHpVo.getConRelaCd().equals("9") && StringUtils.isEmpty(mrHpVo.getConRelaRem())) {
         map.put("conRelaRem", "联系人关系说明不能为空");
      }

      if (StringUtils.isEmpty(mrHpVo.getContTel())) {
         map.put("contTel", "联系人电话不能为空");
      }

      if (StringUtils.isEmpty(mrHpVo.getLeaveWayCd())) {
         map.put("leaveWayCd", "离院方式不能为空");
      }

      if (mrHpVo.getInhosTime() == null) {
         map.put("inhosTime", "入院时间不能为空");
      }

      MrHpVo resultVo = this.mrHpMapper.selectVistVoByPatientId(mrHpVo.getPatientId());
      if (resultVo != null && resultVo.getOutTime() != null) {
         if (mrHpVo.getOutTime() == null) {
            map.put("outTime", "出院时间不能为空");
         }

         if (mrHpVo.getRealInhosDays() == null) {
            map.put("leaveWayCd", "实际住院天数不能为空");
         }

         if (StringUtils.isEmpty(mrHpVo.getOutDeptCd())) {
            map.put("outDeptCd", "出院科别代码不能为空");
         }

         if (StringUtils.isEmpty(mrHpVo.getOutDeptName())) {
            map.put("outDeptName", "出院科别名称不能为空");
         }
      }

      if (mrHpVo.getOutInFlag() == null) {
         map.put("outInFlag", "是否有31天内再住院计划不能为空");
      }

      if (StringUtils.isEmpty(mrHpVo.getInWay())) {
         map.put("inWay", "入院途径不能为空");
      }

      if (StringUtils.isEmpty(mrHpVo.getInDeptCd())) {
         map.put("inDeptCd", "入院科别代码不能为空");
      }

      if (StringUtils.isEmpty(mrHpVo.getInDeptName())) {
         map.put("inDeptName", "入院科别名称不能为空");
      }

      if (StringUtils.isEmpty(mrHpVo.getAboCd())) {
         map.put("aboCd", "血型代码不能为空");
      }

      if (StringUtils.isEmpty(mrHpVo.getRhCd())) {
         map.put("rhCd", "Rh血型代码不能为空");
      }

      if (mrHpVo.getMrHpDiaXYList() != null && mrHpVo.getMrHpDiaXYList().size() > 0) {
         boolean boolClass = true;
         boolean boolType = true;

         for(MrHpDia mrHpDia : mrHpVo.getMrHpDiaXYList()) {
            if (StringUtils.isNotEmpty(mrHpDia.getDiaName())) {
               if ("01".equals(mrHpDia.getDiaClass())) {
                  boolClass = false;
               }

               if ("01".equals(mrHpDia.getDiaItem())) {
                  boolType = false;
               }
            }
         }

         if (boolClass) {
            map.put("diaClass", "必须存在主要诊断信息");
         }

         if (boolType) {
            map.put("diaClass", "必须存在门诊诊断信息");
         }
      } else {
         map.put("mrHpDiaList", "诊断信息未填写");
      }

      if (mrHpVo.getMrHpOpeList() != null && mrHpVo.getMrHpOpeList().size() > 0) {
         for(int i = 0; i < mrHpVo.getMrHpOpeList().size(); ++i) {
            if (StringUtils.isNotEmpty(((MrHpOpeVo)mrHpVo.getMrHpOpeList().get(i)).getOprName())) {
               if (StringUtils.isEmpty(((MrHpOpeVo)mrHpVo.getMrHpOpeList().get(i)).getOprInciCode())) {
                  map.put("oprInciCode," + ((MrHpOpeVo)mrHpVo.getMrHpOpeList().get(i)).getOpeNo(), "切口类别等级为空");
               }

               if (StringUtils.isEmpty(((MrHpOpeVo)mrHpVo.getMrHpOpeList().get(i)).getOprHealCode())) {
                  map.put("oprHealCode," + ((MrHpOpeVo)mrHpVo.getMrHpOpeList().get(i)).getOpeNo(), "切口愈合等级");
               }

               if (StringUtils.isEmpty(((MrHpOpeVo)mrHpVo.getMrHpOpeList().get(i)).getAnestMethCode())) {
                  map.put("anestMethCode," + ((MrHpOpeVo)mrHpVo.getMrHpOpeList().get(i)).getOpeNo(), "麻醉方式");
               }

               if (StringUtils.isEmpty(((MrHpOpeVo)mrHpVo.getMrHpOpeList().get(i)).getOprLevelCode())) {
                  map.put("oprLevelCode," + ((MrHpOpeVo)mrHpVo.getMrHpOpeList().get(i)).getOpeNo(), "手术级别");
               }

               if (StringUtils.isEmpty(((MrHpOpeVo)mrHpVo.getMrHpOpeList().get(i)).getOpeCode())) {
                  map.put("opeCode," + ((MrHpOpeVo)mrHpVo.getMrHpOpeList().get(i)).getOpeNo(), "手术者");
               }
            }
         }
      }

      String elemsJson = "";
      if (map != null && map.size() > 0) {
         elemsJson = JSON.toJSONString(map);
      }

      return elemsJson;
   }

   public List getQcErrorList(MrHpVo mrHpVo) throws Exception {
      List<EmrQcListVo> emrQcListVoList = mrHpVo.getEmrQcListList();
      List<EmrQcListVo> qcExcepationList = new ArrayList();
      if (emrQcListVoList != null && emrQcListVoList.size() > 0) {
         qcExcepationList = (List)emrQcListVoList.stream().filter((s) -> org.apache.commons.lang3.StringUtils.isNotEmpty(s.getTreatDesc()) && s.getQcState().equals("1")).collect(Collectors.toList());
      }

      if (mrHpVo.getRecordId() != null) {
         EmrQcListVo emrQcListVo = new EmrQcListVo();
         List<String> qcSectionList = new ArrayList();
         qcSectionList.add("01");
         qcSectionList.add("04");
         emrQcListVo.setQcSectionList(qcSectionList);
         emrQcListVo.setMrFileId(mrHpVo.getRecordId());
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

   public MrHpPrintVo getPrintPersonInfoDetail(MrHpVo mrHpVo) throws Exception {
      MrHpPrintVo mrHpPrintVo = new MrHpPrintVo();
      List<MedicalInforVo> medicalInfor = new ArrayList();
      List<PrintXyDiagInfoVo> diagPrintXyList = new ArrayList();
      List<PrintZyDiagInfoVo> printDiagZyList = new ArrayList();
      List<PrintOperInfoVo> printOperInfoList = new ArrayList();
      List<PicInfoVo> picInfoVoList = new ArrayList();
      new MedicalInforVo();
      PicInfoVo picInfoVo = new PicInfoVo();
      MedicalInforVo medicalInforVo = this.mrHpMapper.selectMrHpAndAttachByPatientId(mrHpVo);
      if (medicalInforVo != null) {
         String mrType = medicalInforVo.getMr_type();

         for(PrintZyDiagInfoVo zyDiagInfoVo : this.mrHpDiaService.selectTdCmDiagListLineByRecordId(medicalInforVo.getRecord_id(), "ZY")) {
            PrintZyDiagInfoVo resultVo = new PrintZyDiagInfoVo();
            resultVo.setDiagZyName(":" + zyDiagInfoVo.getDiagZyName());
            resultVo.setDiagZyCode(zyDiagInfoVo.getDiagZyCode());
            resultVo.setInHosCondZyCd(zyDiagInfoVo.getInHosCondZyCd());
            resultVo.setDiagZyClassName(DiagPrintInfoEnum.getDiagClassName(zyDiagInfoVo.getDiagZyClassName()));
            printDiagZyList.add(resultVo);
         }

         int sizeZy = printDiagZyList.size();
         if (sizeZy > 9) {
            printDiagZyList = printDiagZyList.subList(0, 9);
         } else {
            int l = 9 - sizeZy;

            for(int i = 0; i < l; ++i) {
               PrintZyDiagInfoVo resultVo = new PrintZyDiagInfoVo();
               resultVo.setDiagZyName("");
               resultVo.setDiagZyCode("");
               resultVo.setInHosCondZyCd("");
               resultVo.setDiagZyClassName("");
               printDiagZyList.add(resultVo);
            }
         }

         for(PrintZyDiagInfoVo diagInfoVo : this.mrHpDiaService.selectTdCmDiagListLineByRecordId(medicalInforVo.getRecord_id(), "XY")) {
            PrintXyDiagInfoVo resultVo = new PrintXyDiagInfoVo();
            resultVo.setDiagXyName(":" + diagInfoVo.getDiagZyName());
            resultVo.setDiagXyCode(diagInfoVo.getDiagZyCode());
            resultVo.setInHosCondXyCd(diagInfoVo.getInHosCondZyCd());
            resultVo.setDiagXyClassName(DiagPrintInfoEnum.getDiagClassName(diagInfoVo.getDiagZyClassName()));
            diagPrintXyList.add(resultVo);
         }

         int sizeXy = diagPrintXyList.size();
         int count = 22;
         if (mrType.equals("XY")) {
            count = 22;
         } else {
            count = 9;
         }

         if (sizeXy > count) {
            diagPrintXyList = diagPrintXyList.subList(0, count);
         } else {
            int l = count - sizeXy;

            for(int i = 0; i < l; ++i) {
               PrintXyDiagInfoVo resultVo = new PrintXyDiagInfoVo();
               resultVo.setDiagXyName("");
               resultVo.setDiagXyCode("");
               resultVo.setInHosCondXyCd("");
               resultVo.setDiagXyClassName("");
               diagPrintXyList.add(resultVo);
            }
         }

         TdCmFeeVo tdCmFeeList = this.mrHpFeeService.selectMrHpFeeByRecordId(medicalInforVo.getRecord_id());
         if (tdCmFeeList != null) {
            BeanUtils.copyProperties(tdCmFeeList, medicalInforVo);
         }

         List<MrHpOpe> mrHpOpeList = this.mrHpOpeService.selectMrHpOpeByRecordId(medicalInforVo.getRecord_id());
         printOperInfoList = this.getPrintOperInfo(mrHpOpeList);
         String bm = "00";
         String recordNo = medicalInforVo.getRecord_no();
         if (medicalInforVo.getVisit_num() != null) {
            bm = BarCodeUtils.getNumStr(medicalInforVo.getVisit_num());
         }

         String lis = recordNo + bm;
         medicalInforVo.setBar_code(lis);
         String personAge = AgeUtil.getFormatAge(medicalInforVo.getAge_y(), medicalInforVo.getAge_m(), medicalInforVo.getAge_d(), medicalInforVo.getAge_h(), medicalInforVo.getAge_mi());
         if (medicalInforVo.getAge_y() != null && medicalInforVo.getAge_y() < 1) {
            medicalInforVo.setPersonAge("0岁");
            medicalInforVo.setBabyAge(personAge);
         } else {
            medicalInforVo.setPersonAge(personAge);
            medicalInforVo.setBabyAge("-");
         }

         medicalInforVo.setCont_addr_pro(StringUtils.isNotBlank(medicalInforVo.getCont_addr_pro()) ? medicalInforVo.getCont_addr_pro() : "-");
         medicalInforVo.setWork_addr_pro(StringUtils.isNotBlank(medicalInforVo.getWork_addr_pro()) ? medicalInforVo.getWork_addr_pro() : "-");
         medicalInforVo.setWork_post(StringUtils.isNotBlank(medicalInforVo.getWork_post()) ? medicalInforVo.getWork_post() : "-");
         medicalInforVo.setWork_tel(StringUtils.isNotBlank(medicalInforVo.getWork_tel()) ? medicalInforVo.getWork_tel() : "-");
         medicalInforVo.setBaby1_bir_weightS(medicalInforVo.getBaby1_bir_weight() != null ? medicalInforVo.getBaby1_bir_weight().toString() : "-");
         medicalInforVo.setInhos_weightS(medicalInforVo.getInhos_weight() != null ? medicalInforVo.getInhos_weight().toString() : "-");
         medicalInforVo.setCh_dept_name(StringUtils.isNotBlank(medicalInforVo.getCh_dept_name()) ? medicalInforVo.getCh_dept_name() : "-");
         medicalInforVo.setAlle_drug(StringUtils.isNotBlank(medicalInforVo.getAlle_drug()) ? medicalInforVo.getAlle_drug() : "-");
         medicalInforVo.setHc_no(StringUtils.isNotBlank(medicalInforVo.getHc_no()) ? medicalInforVo.getHc_no() : "-");
         medicalInfor.add(medicalInforVo);
         mrHpPrintVo.setMedicalInfor(medicalInfor);
         this.genPicCode(medicalInforVo, picInfoVo);
         picInfoVoList.add(picInfoVo);
         mrHpPrintVo.setPicInfoVoList(picInfoVoList);
         mrHpPrintVo.setDiagPrintXyList(diagPrintXyList);
         mrHpPrintVo.setDiagPrintZyList(printDiagZyList);
         mrHpPrintVo.setPrintOperInfoList(printOperInfoList);
      } else {
         int l = 9;

         for(int i = 0; i < l; ++i) {
            PrintZyDiagInfoVo resultVo = new PrintZyDiagInfoVo();
            resultVo.setDiagZyName("");
            resultVo.setDiagZyCode("");
            resultVo.setInHosCondZyCd("");
            resultVo.setDiagZyClassName("");
            printDiagZyList.add(resultVo);
         }

         l = 9;

         for(int i = 0; i < l; ++i) {
            PrintXyDiagInfoVo resultVo = new PrintXyDiagInfoVo();
            resultVo.setDiagXyName("");
            resultVo.setDiagXyCode("");
            resultVo.setInHosCondXyCd("");
            resultVo.setDiagXyClassName("");
            diagPrintXyList.add(resultVo);
         }

         l = 8;

         for(int i = 0; i < l; ++i) {
            PrintOperInfoVo resultVo = new PrintOperInfoVo();
            resultVo.setAid1Name("");
            resultVo.setAid2Name("");
            resultVo.setAnestMethName("");
            resultVo.setAnestName("");
            resultVo.setOperIcd("");
            resultVo.setOperName("");
            resultVo.setOperLevelName("");
            resultVo.setOperDocName("");
            resultVo.setOperInci("/");
            printOperInfoList.add(resultVo);
         }

         mrHpPrintVo.setPicInfoVoList(picInfoVoList);
         mrHpPrintVo.setDiagPrintXyList(diagPrintXyList);
         mrHpPrintVo.setDiagPrintZyList(printDiagZyList);
         mrHpPrintVo.setPrintOperInfoList(printOperInfoList);
      }

      return mrHpPrintVo;
   }

   private List voSortByOpeMain(List mrHpOpeList) {
      List<MrHpOpeVo> mrHpOpeList2 = new ArrayList(1);
      if (CollectionUtils.isNotEmpty(mrHpOpeList)) {
         List<MrHpOpeVo> mrHpOpeListOpeMain = (List)mrHpOpeList.stream().filter((t) -> StringUtils.isNotBlank(t.getOpeMain()) && t.getOpeMain().equals("1")).collect(Collectors.toList());
         List<MrHpOpeVo> mrHpOpeListNoOpeMain = (List)mrHpOpeList.stream().filter((t) -> StringUtils.isBlank(t.getOpeMain()) || StringUtils.isNotBlank(t.getOpeMain()) && !t.getOpeMain().equals("1")).collect(Collectors.toList());
         mrHpOpeList2.addAll(mrHpOpeListOpeMain);
         mrHpOpeList2.addAll(mrHpOpeListNoOpeMain);
      } else {
         mrHpOpeList2 = mrHpOpeList;
      }

      return mrHpOpeList2;
   }

   private List sortByOpeMain(List mrHpOpeList) {
      List<MrHpOpe> mrHpOpeList2 = new ArrayList(1);
      if (CollectionUtils.isNotEmpty(mrHpOpeList)) {
         List<MrHpOpe> mrHpOpeListOpeMain = (List)mrHpOpeList.stream().filter((t) -> t.getOpeMain().equals("1")).collect(Collectors.toList());
         List<MrHpOpe> mrHpOpeListNoOpeMain = (List)mrHpOpeList.stream().filter((t) -> !t.getOpeMain().equals("1")).collect(Collectors.toList());
         mrHpOpeList2.addAll(mrHpOpeListOpeMain);
         mrHpOpeList2.addAll(mrHpOpeListNoOpeMain);
      } else {
         mrHpOpeList2 = mrHpOpeList;
      }

      return mrHpOpeList2;
   }

   private List getPrintOperInfo(List mrHpOpeList) {
      List<MrHpOpe> mrHpOpeList2 = this.sortByOpeMain(mrHpOpeList);
      List<PrintOperInfoVo> list = new ArrayList();

      for(MrHpOpe oper : mrHpOpeList2) {
         PrintOperInfoVo resultVo = new PrintOperInfoVo();
         resultVo.setAid1Name(oper.getAid1Name());
         resultVo.setAid2Name(oper.getAid2Name());
         resultVo.setAnestMethName(oper.getAnestMethName());
         resultVo.setAnestName(oper.getAnestName());
         resultVo.setOperBeginDt(oper.getOprBeginDatetime());
         resultVo.setOperIcd(oper.getOprIcd());
         resultVo.setOperName(oper.getOprName());
         resultVo.setOperLevelName(oper.getOprLevel());
         resultVo.setOperDocName(oper.getOpeName());
         String inci = StringUtils.isNotBlank(oper.getOprInciName()) ? oper.getOprInciName() : "";
         String heal = StringUtils.isNotBlank(oper.getOprHealName()) ? oper.getOprHealName() : "";
         String operInci = inci + "/" + heal;
         resultVo.setOperInci(operInci);
         list.add(resultVo);
      }

      int size = list.size();
      if (size > 8) {
         list = list.subList(0, 8);
      } else {
         int l = 8 - size;

         for(int i = 0; i < l; ++i) {
            PrintOperInfoVo resultVo = new PrintOperInfoVo();
            resultVo.setAid1Name("");
            resultVo.setAid2Name("");
            resultVo.setAnestMethName("");
            resultVo.setAnestName("");
            resultVo.setOperIcd("");
            resultVo.setOperName("");
            resultVo.setOperLevelName("");
            resultVo.setOperDocName("");
            resultVo.setOperInci("/");
            list.add(resultVo);
         }
      }

      return list;
   }

   public void genPicCode(MedicalInforVo medicalInforVo, PicInfoVo picInfoVo) {
      BeanUtils.copyProperties(medicalInforVo, picInfoVo);
      List<String> staffCodeList = new ArrayList();
      String hd_cd = medicalInforVo.getHd_cd();
      if (StringUtils.isNotBlank(hd_cd)) {
         staffCodeList.add(hd_cd);
      }

      String arc_doc_cd = medicalInforVo.getArc_doc_cd();
      if (StringUtils.isNotBlank(arc_doc_cd)) {
         staffCodeList.add(arc_doc_cd);
      }

      String att_doc_cd = medicalInforVo.getAtt_doc_cd();
      if (StringUtils.isNotBlank(att_doc_cd)) {
         staffCodeList.add(att_doc_cd);
      }

      String res_doc_cd = medicalInforVo.getRes_doc_cd();
      if (StringUtils.isNotBlank(res_doc_cd)) {
         staffCodeList.add(res_doc_cd);
      }

      String duty_nur_cd = medicalInforVo.getDuty_nur_cd();
      if (StringUtils.isNotBlank(duty_nur_cd)) {
         staffCodeList.add(duty_nur_cd);
      }

      String stu_doc_cd = medicalInforVo.getStu_doc_cd();
      if (StringUtils.isNotBlank(stu_doc_cd)) {
         staffCodeList.add(stu_doc_cd);
      }

      String int_doc_cd = medicalInforVo.getInt_doc_cd();
      if (StringUtils.isNotBlank(int_doc_cd)) {
         staffCodeList.add(int_doc_cd);
      }

      String coder_cd = medicalInforVo.getCoder_cd();
      if (StringUtils.isNotBlank(coder_cd)) {
         staffCodeList.add(coder_cd);
      }

      String qcon_doc_cd = medicalInforVo.getQcon_doc_cd();
      if (StringUtils.isNotBlank(qcon_doc_cd)) {
         staffCodeList.add(qcon_doc_cd);
      }

      String qcon_nur_cd = medicalInforVo.getQcon_nur_cd();
      if (StringUtils.isNotBlank(qcon_nur_cd)) {
         staffCodeList.add(qcon_nur_cd);
      }

      if (!staffCodeList.isEmpty()) {
         List<BasCertInfo> list = this.basCertInfoMapper.selectBasCertInfoByList(staffCodeList);
         Map<String, List<BasCertInfo>> staffCodeMap = (Map)list.stream().collect(Collectors.groupingBy(BasCertInfo::getEmployeenumber));
         if (StringUtils.isNotBlank(hd_cd) && staffCodeMap.containsKey(hd_cd)) {
            BasCertInfo basCertInfo = (BasCertInfo)((List)staffCodeMap.get(hd_cd)).get(0);
            picInfoVo.setHd_img(basCertInfo.getCertPic());
         }

         if (StringUtils.isNotBlank(arc_doc_cd) && staffCodeMap.containsKey(arc_doc_cd)) {
            BasCertInfo basCertInfo = (BasCertInfo)((List)staffCodeMap.get(arc_doc_cd)).get(0);
            picInfoVo.setArc_doc_img(basCertInfo.getCertPic());
         }

         if (StringUtils.isNotBlank(att_doc_cd) && staffCodeMap.containsKey(att_doc_cd)) {
            BasCertInfo basCertInfo = (BasCertInfo)((List)staffCodeMap.get(att_doc_cd)).get(0);
            picInfoVo.setAtt_doc_img(basCertInfo.getCertPic());
         }

         if (StringUtils.isNotBlank(res_doc_cd) && staffCodeMap.containsKey(res_doc_cd)) {
            BasCertInfo basCertInfo = (BasCertInfo)((List)staffCodeMap.get(res_doc_cd)).get(0);
            picInfoVo.setRes_doc_img(basCertInfo.getCertPic());
         }

         if (StringUtils.isNotBlank(duty_nur_cd) && staffCodeMap.containsKey(duty_nur_cd)) {
            BasCertInfo basCertInfo = (BasCertInfo)((List)staffCodeMap.get(duty_nur_cd)).get(0);
            picInfoVo.setDuty_nur_img(basCertInfo.getCertPic());
         }

         if (StringUtils.isNotBlank(stu_doc_cd) && staffCodeMap.containsKey(stu_doc_cd)) {
            BasCertInfo basCertInfo = (BasCertInfo)((List)staffCodeMap.get(stu_doc_cd)).get(0);
            picInfoVo.setStu_doc_img(basCertInfo.getCertPic());
         }

         if (StringUtils.isNotBlank(int_doc_cd) && staffCodeMap.containsKey(int_doc_cd)) {
            BasCertInfo basCertInfo = (BasCertInfo)((List)staffCodeMap.get(int_doc_cd)).get(0);
            picInfoVo.setInt_doc_img(basCertInfo.getCertPic());
         }

         if (StringUtils.isNotBlank(coder_cd) && staffCodeMap.containsKey(coder_cd)) {
            BasCertInfo basCertInfo = (BasCertInfo)((List)staffCodeMap.get(coder_cd)).get(0);
            picInfoVo.setCoder_img(basCertInfo.getCertPic());
         }

         if (StringUtils.isNotBlank(qcon_doc_cd) && staffCodeMap.containsKey(qcon_doc_cd)) {
            BasCertInfo basCertInfo = (BasCertInfo)((List)staffCodeMap.get(qcon_doc_cd)).get(0);
            picInfoVo.setQcon_doc_img(basCertInfo.getCertPic());
         }

         if (StringUtils.isNotBlank(qcon_nur_cd) && staffCodeMap.containsKey(qcon_nur_cd)) {
            BasCertInfo basCertInfo = (BasCertInfo)((List)staffCodeMap.get(qcon_nur_cd)).get(0);
            picInfoVo.setQcon_nur_img(basCertInfo.getCertPic());
         }
      }

   }

   public List mrHpCheckOut(MrHpVo mrHpVo) throws Exception {
      List<String> kieTemplatesRes = new ArrayList(1);
      List<MrHpCheckSet> paramList = new ArrayList();
      MrHpCheckSetVo mrHpCheckSetVo = new MrHpCheckSetVo();
      List<String> eventList = new ArrayList();
      eventList.add("1");
      mrHpCheckSetVo.setCheckEventNoList(eventList);
      List<MrHpCheckSet> allList = this.mrHpCheckSetService.selectMrHpCheckSetList(mrHpCheckSetVo);
      List<MrHpCheckSet> fixedList = (List)allList.stream().filter((r) -> r.getCheckClassNo().equals("06")).collect(Collectors.toList());
      List<MrHpCheckSet> noFixedList = (List)allList.stream().filter((r) -> !r.getCheckClassNo().equals("06")).collect(Collectors.toList());
      List<EmrQcListVo> qcExcepationList = this.getQcErrorList(mrHpVo);

      for(MrHpCheckSet mrHpCheckSet : noFixedList) {
         File checkFileOld = new File(this.path + "/" + mrHpCheckSet.getCheckFileName());
         if (checkFileOld.exists()) {
            kieTemplatesRes.add(mrHpCheckSet.getCheckFileName());
            paramList.add(mrHpCheckSet);
         }
      }

      if (kieTemplatesRes != null && !kieTemplatesRes.isEmpty()) {
         this.kSession = this.kieTemplate.getKieSession((String[])kieTemplatesRes.toArray(new String[kieTemplatesRes.size()]));
         this.kSession.insert(mrHpVo);
         this.kSession.insert(mrHpVo.getMrHpOpeList());
         this.kSession.fireAllRules();
         this.kSession.delete(this.kSession.getFactHandle(mrHpVo));
         this.kSession.dispose();
      }

      List<Long> idList = (List<Long>)(paramList != null && !paramList.isEmpty() ? (List)paramList.stream().map((s) -> s.getId()).collect(Collectors.toList()) : new ArrayList(1));
      List<EmrQcListVo> fixedQcList = new ArrayList();
      Date currDate = this.commonService.getDbSysdate();

      for(MrHpCheckSet mrHpCheckSet : fixedList) {
         if (idList == null || idList.size() <= 0 || !idList.contains(mrHpCheckSet.getId())) {
            String serviceName = "MrHpCheckSet" + mrHpCheckSet.getCheckTypeNo();
            MrHpCheckSet06UtilService mrHpCheckSet06UtilService = (MrHpCheckSet06UtilService)SpringUtils.getBean(serviceName);
            Boolean flag = mrHpCheckSet06UtilService.infoVerify(mrHpVo);
            if (!flag) {
               EmrQcListVo emrQcList = new EmrQcListVo();
               emrQcList.setPatientId(mrHpVo.getPatientId());
               emrQcList.setMrType("61");
               emrQcList.setMrTypeName("病案首页");
               emrQcList.setRuleName(mrHpCheckSet.getCheckName());
               emrQcList.setFlawDesc(mrHpCheckSet.getCheckTips());
               emrQcList.setMrFileId(mrHpVo.getRecordId());
               emrQcList.setMrFileName("病案首页");
               emrQcList.setQcState("0");
               emrQcList.setQcdoctCd("sys");
               emrQcList.setQcdoctName("系统");
               emrQcList.setQcSection("01");
               BasEmployee employee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
               emrQcList.setCrePerCode(employee.getEmplNumber());
               emrQcList.setCrePerName(employee.getEmplName());
               emrQcList.setCreDate(currDate);
               emrQcList.setQcDate(currDate);
               emrQcList.setDefeLevel(mrHpCheckSet.getCheckLevel());
               JSONObject jsonObject = JSONObject.parseObject(mrHpCheckSet.getCheckColumn1());
               emrQcList.setEmrEleId(jsonObject.getString("fieldProp"));
               emrQcList.setEmrEleName(jsonObject.getString("fieldName"));
               fixedQcList.add(emrQcList);
            }
         }
      }

      List<EmrQcListVo> resultList = this.emrQcRecordService.getCheckResultQcList(mrHpVo, paramList, mrHpVo.getResultVoList(), qcExcepationList);
      resultList.addAll(fixedQcList);
      return resultList;
   }

   public List selectPatMrHpList(PatEventVo patEventVo) {
      return this.mrHpMapper.selectPatMrHpList(patEventVo);
   }

   public List selectPatMrHpNewList(PatEventVo patEventVo) {
      return this.mrHpMapper.selectPatMrHpNewList(patEventVo);
   }

   public void updateLastFinishTimeList(List list) throws Exception {
      this.mrHpMapper.updateLastFinishTimeList(list);
   }

   public void updateOutInfoFromOrder(OrderSaveVo orderSaveVo) throws Exception {
      MrHp mrHp = this.mrHpMapper.selectMrHpByPatientId(orderSaveVo.getPatientId());
      MrHpAttach mrHpAttach = this.mrHpAttachService.selectMrHpAttachByPatientId(orderSaveVo.getPatientId());
      if (mrHp != null && StringUtils.isNotBlank(orderSaveVo.getReferralHospital())) {
         MrHpVo updateParam = new MrHpVo();
         updateParam.setInOrgName(orderSaveVo.getReferralHospital());
         updateParam.setRecordId(mrHp.getRecordId());
         this.mrHpMapper.updateMrHpOutTime(updateParam);
      }

      if (mrHp != null && mrHpAttach != null) {
         MrHpAttach updateParam = new MrHpAttach();
         updateParam.setDiaEff(orderSaveVo.getOutCon());
         updateParam.setRecordId(mrHp.getRecordId());
         this.mrHpAttachService.updateMrHpAttachNotNull(updateParam);
      }

   }

   @DataSource(DataSourceType.mrHpToPbHis)
   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void syncMrHpVoToHis(MrHpVo mrHpVo, List rc013List) throws Exception {
      if (mrHpVo.getInhosTime() != null) {
         Calendar c = Calendar.getInstance();
         c.setTime(mrHpVo.getInhosTime());
         mrHpVo.setInHosYear(String.valueOf(c.get(1)));
         mrHpVo.setInHosMouth(String.valueOf(c.get(2) + 1));
         mrHpVo.setInHosDay(String.valueOf(c.get(5)));
         mrHpVo.setInHosHour(String.valueOf(c.get(11)));
         mrHpVo.setInHosMin(String.valueOf(c.get(12)));
      }

      if (mrHpVo.getOutTime() != null) {
         Calendar c = Calendar.getInstance();
         c.setTime(mrHpVo.getOutTime());
         mrHpVo.setOutTimeYear(String.valueOf(c.get(1)));
         mrHpVo.setOutTimeMouth(String.valueOf(c.get(2) + 1));
         mrHpVo.setOutTimeDay(String.valueOf(c.get(5)));
         mrHpVo.setOutTimeHour(String.valueOf(c.get(11)));
         mrHpVo.setOutTimeMin(String.valueOf(c.get(12)));
      }

      if (mrHpVo.getBirDate() != null) {
         Calendar c = Calendar.getInstance();
         c.setTime(mrHpVo.getBirDate());
         mrHpVo.setBirDateYear(String.valueOf(c.get(1)));
         mrHpVo.setBirDateMouth(String.valueOf(c.get(2) + 1));
         mrHpVo.setBirDateDay(String.valueOf(c.get(5)));
      }

      if (mrHpVo.getMrHpAttachVo().getDiaDate() != null) {
         Calendar c = Calendar.getInstance();
         c.setTime(mrHpVo.getMrHpAttachVo().getDiaDate());
         mrHpVo.getMrHpAttachVo().setDiaDateYear(String.valueOf(c.get(1)));
         mrHpVo.getMrHpAttachVo().setDiaDateMouth(String.valueOf(c.get(2) + 1));
         mrHpVo.getMrHpAttachVo().setDiaDateDay(String.valueOf(c.get(5)));
      }

      String prpAddrAll = (StringUtils.isNotBlank(mrHpVo.getRprAddrPro()) ? mrHpVo.getRprAddrPro() : "") + (StringUtils.isNotBlank(mrHpVo.getRprAddrFlagty()) ? mrHpVo.getRprAddrFlagty() : "") + (StringUtils.isNotBlank(mrHpVo.getRprAddrCou()) ? mrHpVo.getRprAddrCou() : "") + (StringUtils.isNotBlank(mrHpVo.getRprAddr()) ? mrHpVo.getRprAddr() : "");
      mrHpVo.setPrpAddrAll(prpAddrAll);
      String contAddrAll = (StringUtils.isNotBlank(mrHpVo.getContAddrPro()) ? mrHpVo.getContAddrPro() : "") + (StringUtils.isNotBlank(mrHpVo.getContAddrFlagty()) ? mrHpVo.getContAddrFlagty() : "") + (StringUtils.isNotBlank(mrHpVo.getContAddrCou()) ? mrHpVo.getContAddrCou() : "") + (StringUtils.isNotBlank(mrHpVo.getContAddr()) ? mrHpVo.getContAddr() : "");
      mrHpVo.setContAddrAll(contAddrAll);
      String nowAddrAll = (StringUtils.isNotBlank(mrHpVo.getNowAddrPro()) ? mrHpVo.getNowAddrPro() : "") + (StringUtils.isNotBlank(mrHpVo.getNowAddrFlagty()) ? mrHpVo.getNowAddrFlagty() : "") + (StringUtils.isNotBlank(mrHpVo.getNowAddrCou()) ? mrHpVo.getNowAddrCou() : "") + (StringUtils.isNotBlank(mrHpVo.getNowAddr()) ? mrHpVo.getNowAddr() : "");
      mrHpVo.setNowAddrAll(nowAddrAll);
      String apAddrAll = (StringUtils.isNotBlank(mrHpVo.getApAddrPro()) ? mrHpVo.getApAddrPro() : "") + (StringUtils.isNotBlank(mrHpVo.getApAddrPlagty()) ? mrHpVo.getApAddrPlagty() : "");
      mrHpVo.setApAddrAll(apAddrAll);
      List<MrHpDia> xyList = new ArrayList(1);
      List<MrHpDia> zyList = new ArrayList(1);
      List<MrHpOpe> opeLList = new ArrayList(1);
      List<MrHpFee> feeList = new ArrayList(1);
      if (CollectionUtils.isNotEmpty(mrHpVo.getDiagInfoList())) {
         DiagInfo diagInfo = (DiagInfo)mrHpVo.getDiagInfoList().get(0);
         mrHpVo.setRyzdBh(diagInfo.getDiagCd());
         mrHpVo.setRyzdBm(diagInfo.getDiagCd());
         mrHpVo.setRyzdMc(diagInfo.getDiagName());
      }

      if (mrHpVo.getMrHpDiaXYList() != null && !mrHpVo.getMrHpDiaXYList().isEmpty()) {
         List<MrHpDia> dias = (List)mrHpVo.getMrHpDiaXYList().stream().filter((s) -> s.getDiaItem().equals("01") && s.getDiaClass().equals("01")).collect(Collectors.toList());
         if (dias != null && !dias.isEmpty()) {
            mrHpVo.setOsChdCd(((MrHpDia)dias.get(0)).getDiaCd());
            mrHpVo.setOsChdName(((MrHpDia)dias.get(0)).getDiaName());
         }

         List<MrHpDia> dias2 = (List)mrHpVo.getMrHpDiaXYList().stream().filter((s) -> s.getDiaItem().equals("01") && s.getDiaClass().equals("02")).collect(Collectors.toList());
         if (CollectionUtils.isNotEmpty(dias2)) {
            mrHpVo.setMzDiaCd1(((MrHpDia)dias2.get(0)).getDiaCd());
            mrHpVo.setMzDiaName1(((MrHpDia)dias2.get(0)).getDiaName());
            if (dias2.size() > 1) {
               mrHpVo.setMzDiaCd2(((MrHpDia)dias2.get(1)).getDiaCd());
               mrHpVo.setMzDiaName2(((MrHpDia)dias2.get(1)).getDiaName());
            }
         }

         for(MrHpDia mrHpDia : mrHpVo.getMrHpDiaXYList()) {
            if (!StringUtils.isEmpty(mrHpDia.getDiaCd()) && !mrHpDia.getDiaItem().equals("01")) {
               mrHpDia.setRecordNo(mrHpVo.getRecordNo());
               mrHpDia.setInpNo(mrHpVo.getInpNo());
               mrHpDia.setVisitId(mrHpVo.getVisitId());
               mrHpDia.setPatientName(mrHpVo.getPatientName());
               switch (mrHpDia.getDiaItem()) {
                  case "03":
                     if (mrHpDia.getDiaClass().equals("01")) {
                        mrHpDia.setDiaItem("1");
                     } else if (mrHpDia.getDiaClass().equals("02")) {
                        mrHpDia.setDiaItem("2");
                     }
                     break;
                  case "05":
                     mrHpDia.setDiaItem("3");
                     break;
                  case "04":
                     mrHpDia.setDiaItem("4");
                     break;
                  default:
                     mrHpDia.setDiaItem("2");
               }

               xyList.add(mrHpDia);
            }
         }
      }

      if (mrHpVo.getMrHpDiaZYList() != null && !mrHpVo.getMrHpDiaZYList().isEmpty()) {
         List<MrHpDia> dias = (List)mrHpVo.getMrHpDiaZYList().stream().filter((s) -> s.getDiaItem().equals("01") && s.getDiaClass().equals("11")).collect(Collectors.toList());
         if (dias != null && !dias.isEmpty()) {
            mrHpVo.setZyMzDiaCd(((MrHpDia)dias.get(0)).getDiaCd());
            mrHpVo.setZyMzDiaName(((MrHpDia)dias.get(0)).getDiaName());
         }

         for(MrHpDia mrHpDia : mrHpVo.getMrHpDiaZYList()) {
            if (!StringUtils.isEmpty(mrHpDia.getDiaCd())) {
               mrHpDia.setRecordNo(mrHpVo.getRecordNo());
               mrHpDia.setInpNo(mrHpVo.getInpNo());
               mrHpDia.setVisitId(mrHpVo.getVisitId());
               mrHpDia.setPatientName(mrHpVo.getPatientName());
               if (mrHpDia.getDiaItem().equals("11")) {
                  mrHpDia.setDiaItem("1");
               }

               if (mrHpDia.getDiaItem().equals("12")) {
                  mrHpDia.setDiaItem("2");
               }

               zyList.add(mrHpDia);
            }
         }
      }

      if (mrHpVo.getMrHpOpeList() != null && !mrHpVo.getMrHpOpeList().isEmpty()) {
         Map<String, List<SysDictData>> rc013Map = (Map<String, List<SysDictData>>)(CollectionUtils.isNotEmpty(rc013List) ? (Map)rc013List.stream().collect(Collectors.groupingBy((t) -> t.getDictValue())) : new HashMap(1));

         for(MrHpOpe mrHpOpe : mrHpVo.getMrHpOpeList()) {
            if (!StringUtils.isEmpty(mrHpOpe.getOprName())) {
               mrHpOpe.setRecordNo(mrHpVo.getRecordNo());
               mrHpOpe.setInpNo(mrHpVo.getInpNo());
               mrHpOpe.setVisitId(mrHpVo.getVisitId());
               mrHpOpe.setPatientName(mrHpVo.getPatientName());
               if (mrHpOpe.getOprBeginDatetime() != null && mrHpOpe.getOprEndDatetime() != null) {
                  Long duration = (long)DateUtils.getDateMinutes(mrHpOpe.getOprEndDatetime(), mrHpOpe.getOprBeginDatetime());
                  String durationUnit = "分钟";
                  mrHpOpe.setDuration(duration);
                  mrHpOpe.setDurationUnit(durationUnit);
               }

               if (StringUtils.isNotBlank(mrHpOpe.getAnestMethCode())) {
                  List<SysDictData> rc013 = (List)rc013Map.get(mrHpOpe.getAnestMethCode());
                  String anestMethCode = CollectionUtils.isNotEmpty(rc013) && StringUtils.isNotBlank(((SysDictData)rc013.get(0)).getRemark()) ? ((SysDictData)rc013.get(0)).getRemark() : mrHpOpe.getAnestMethCode();
                  mrHpOpe.setAnestMethCode(anestMethCode);
               }

               opeLList.add(mrHpOpe);
            }
         }
      }

      if (CollectionUtils.isNotEmpty(mrHpVo.getMrHpFeeList())) {
         for(MrHpFee mrHpFee : mrHpVo.getMrHpFeeList()) {
            if (!mrHpFee.getFeeCd().equals("0001") && !mrHpFee.getFeeCd().equals("000101")) {
               MrHpFee mrHpFeeTemp = new MrHpFee();
               BeanUtils.copyProperties(mrHpFee, mrHpFeeTemp);
               mrHpFeeTemp.setRecordNo(mrHpVo.getRecordNo());
               mrHpFeeTemp.setInpNo(mrHpVo.getInpNo());
               mrHpFeeTemp.setVisitId(mrHpVo.getVisitId());
               mrHpFeeTemp.setPatientName(mrHpVo.getPatientName());
               String feeCd = mrHpFee.getFeeCd();
               switch (mrHpFee.getFeeCd()) {
                  case "0101":
                     feeCd = "1";
                     break;
                  case "0102":
                     feeCd = "2";
                     break;
                  case "0103":
                     feeCd = "3";
                     break;
                  case "0104":
                     feeCd = "4";
                     break;
                  case "0201":
                     feeCd = "5";
                     break;
                  case "0202":
                     feeCd = "6";
                     break;
                  case "0203":
                     feeCd = "7";
                     break;
                  case "0204":
                     feeCd = "8";
                     break;
                  case "0301":
                     feeCd = "9";
                     break;
                  case "0302":
                     feeCd = "10";
                     break;
                  case "0401":
                     feeCd = "11";
                     break;
                  case "0501":
                     feeCd = "12";
                     break;
                  case "0601":
                     feeCd = "13";
                     break;
                  case "0701":
                     feeCd = "14";
                     break;
                  case "0702":
                     feeCd = "15";
                     break;
                  case "0801":
                     feeCd = "16";
                     break;
                  case "0802":
                     feeCd = "17";
                     break;
                  case "0803":
                     feeCd = "18";
                     break;
                  case "0804":
                     feeCd = "19";
                     break;
                  case "0805":
                     feeCd = "20";
                     break;
                  case "0901":
                     feeCd = "21";
                     break;
                  case "0902":
                     feeCd = "22";
                     break;
                  case "0903":
                     feeCd = "23";
                     break;
                  case "1001":
                     feeCd = "24";
                     break;
                  case "030202":
                     feeCd = "25";
                     break;
                  case "030201":
                     feeCd = "26";
                     break;
                  case "060101":
                     feeCd = "999";
               }

               mrHpFeeTemp.setFeeCd(feeCd);
               feeList.add(mrHpFeeTemp);
            }
         }
      }

      if (mrHpVo.getMrHpAttachVo().getRedBloodCell() == null && mrHpVo.getMrHpAttachVo().getPlatelet() == null && mrHpVo.getMrHpAttachVo().getPlasma() == null && mrHpVo.getMrHpAttachVo().getWholeBlood() == null && mrHpVo.getMrHpAttachVo().getOtherBlood() == null) {
         mrHpVo.getMrHpAttachVo().setBloodFlag("0");
      } else {
         mrHpVo.getMrHpAttachVo().setBloodFlag("1");
      }

      if (StringUtils.isNotBlank(mrHpVo.getLeaveWayCd())) {
         String inOrgName1 = mrHpVo.getLeaveWayCd().equals("2") ? mrHpVo.getInOrgName() : null;
         String inOrgName2 = mrHpVo.getLeaveWayCd().equals("3") ? mrHpVo.getInOrgName() : null;
         mrHpVo.setInOrgName1(inOrgName1);
         mrHpVo.setInOrgName2(inOrgName2);
      }

      if (!StringUtils.isNotBlank(mrHpVo.getMrHpAttachVo().getIsEmeRecord()) && !StringUtils.isNotBlank(mrHpVo.getMrHpAttachVo().getIsCriRecord())) {
         mrHpVo.getMrHpAttachVo().setIsEmeCriRecord("0");
      } else {
         mrHpVo.getMrHpAttachVo().setIsEmeCriRecord("1");
      }

      this.hisSyncService.deleteHisMrHpVo(mrHpVo.getInpNo());
      this.hisSyncService.deleteHisMrHpOpe(mrHpVo.getInpNo());
      this.hisSyncService.deleteHisMrHpXYDia(mrHpVo.getInpNo());
      this.hisSyncService.deleteHisMrHpZYDia(mrHpVo.getInpNo());
      this.hisSyncService.deleteHisMrHpFee(mrHpVo.getInpNo());
      this.hisSyncService.insertHisMrHpVo(mrHpVo);
      if (zyList != null && !zyList.isEmpty()) {
         this.hisSyncService.insertHisMrHpZYDia(zyList);
      }

      if (xyList != null && !xyList.isEmpty()) {
         this.hisSyncService.insertHisMrHpXYDia(xyList);
      }

      if (opeLList != null && !opeLList.isEmpty()) {
         this.hisSyncService.insertHisMrHpOpe(opeLList);
      }

      if (feeList != null && !feeList.isEmpty()) {
         this.hisSyncService.insertHisMrHpFee(feeList);
      }

   }

   public PatientInfoDetailVo selectPatientInfoDetail(String admissionNo) {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      PatientInfoDetailVo patientInfoDetailVo = this.mrHpMapper.selectPatientInfoDetail(admissionNo);
      SysOrg sysOrg = this.sysOrgMapper.checkOrgCodeUnique(sysUser.getHospital().getOrgCode());
      if (StringUtils.isEmpty(patientInfoDetailVo.getNowAddrPro())) {
         patientInfoDetailVo.setNowAddrPro(sysOrg.getAddressSheng());
      }

      if (StringUtils.isEmpty(patientInfoDetailVo.getNowAddrFlagty())) {
         patientInfoDetailVo.setNowAddrFlagty(sysOrg.getAddressShi());
      }

      if (StringUtils.isEmpty(patientInfoDetailVo.getNowAddrCou())) {
         patientInfoDetailVo.setNowAddrCou(sysOrg.getAddressXian());
      }

      if (StringUtils.isEmpty(patientInfoDetailVo.getNowAddr())) {
         patientInfoDetailVo.setNowAddr(sysOrg.getAddressXiang());
      }

      return patientInfoDetailVo;
   }

   public String getLis(String patientId) {
      MrHpVo mrHpVo = this.mrHpMapper.selectVistVoByPatientId(patientId);
      String bm = "00";
      String recordNo = mrHpVo.getRecordNo();
      if (mrHpVo != null && StringUtils.isNotBlank(String.valueOf(mrHpVo.getVisitId()))) {
         bm = OrderUtil.getNumStr(mrHpVo.getVisitId().intValue());
      }

      String lis = recordNo + bm;
      String barCode = BarCodeUtils.generateBarCode128(lis, "0.5", "10");
      return barCode;
   }
}
