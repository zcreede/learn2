package com.emr.project.pat.service.impl;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.common.utils.StringUtils;
import com.emr.framework.aspectj.lang.enums.DataSourceType;
import com.emr.framework.datasource.DruidUtil;
import com.emr.framework.web.service.ISyncService;
import com.emr.project.common.service.ICommonService;
import com.emr.project.mrhp.domain.MrHpDia;
import com.emr.project.mrhp.domain.vo.MrHpVo;
import com.emr.project.mrhp.mapper.MrHpDiaMapper;
import com.emr.project.mrhp.mapper.MrHpMapper;
import com.emr.project.operation.domain.Medicalinformation;
import com.emr.project.operation.mapper.MedicalinformationMapper;
import com.emr.project.pat.domain.DiagInfo;
import com.emr.project.pat.domain.Personalinfo;
import com.emr.project.pat.domain.Visitinfo;
import com.emr.project.pat.mapper.DiagInfoMapper;
import com.emr.project.pat.service.IDiagInfoService;
import com.emr.project.pat.service.IPersonalinfoService;
import com.emr.project.pat.service.IVisitinfoService;
import com.emr.project.system.domain.BasEmployee;
import com.emr.project.system.domain.SyncDatasource;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.domain.vo.SqlVo;
import com.emr.project.system.service.ISyncDatasourceService;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DiagInfoServiceImpl implements IDiagInfoService, ISyncService {
   protected final Logger log = LoggerFactory.getLogger(DiagInfoServiceImpl.class);
   @Autowired
   private DiagInfoMapper diagInfoMapper;
   @Autowired
   private ICommonService commonService;
   @Autowired
   private IVisitinfoService visitinfoService;
   @Autowired
   private ISyncDatasourceService syncDatasourceService;
   @Autowired
   private MedicalinformationMapper medicalinformationMapper;
   @Autowired
   private MrHpMapper mrHpMapper;
   @Autowired
   private MrHpDiaMapper mrHpDiaMapper;
   @Autowired
   private IPersonalinfoService personalinfoService;

   public DiagInfo selectDiagInfoById(String id) {
      return this.diagInfoMapper.selectDiagInfoById(id);
   }

   public List selectDiagInfoList(DiagInfo diagInfo) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      String emplNumber = basEmployee.getEmplNumber();
      List<DiagInfo> list = this.diagInfoMapper.selectDiagInfoList(diagInfo);
      Medicalinformation medicalinformation = this.medicalinformationMapper.selectMedicalinfoByPatientId(diagInfo.getPatientId());
      if (list != null && !list.isEmpty()) {
         for(DiagInfo diagInfo1 : list) {
            if (emplNumber.equals(diagInfo1.getDiaDocCd())) {
               diagInfo1.setEditFlag(Boolean.TRUE);
            } else if (medicalinformation != null) {
               String residentNo = medicalinformation.getResidentNo();
               if (StringUtils.isNotEmpty(residentNo) && emplNumber.equals(residentNo)) {
                  diagInfo1.setEditFlag(Boolean.TRUE);
               } else {
                  diagInfo1.setEditFlag(Boolean.FALSE);
               }
            } else {
               diagInfo1.setEditFlag(Boolean.FALSE);
            }
         }
      }

      return list;
   }

   public DiagInfo selectDiagInfo(DiagInfo diagInfo) {
      return this.diagInfoMapper.selectDiagInfo(diagInfo);
   }

   public int insertDiagInfo(DiagInfo diagInfo) {
      return this.diagInfoMapper.insertDiagInfo(diagInfo);
   }

   public int updateDiagInfo(DiagInfo diagInfo) {
      return this.diagInfoMapper.updateDiagInfo(diagInfo);
   }

   public int deleteDiagInfoByIds(String[] ids) {
      return this.diagInfoMapper.deleteDiagInfoByIds(ids);
   }

   public int deleteDiagInfoById(String id) {
      return this.diagInfoMapper.deleteDiagInfoById(id);
   }

   public List selectHistoryList(DiagInfo diagInfo) throws Exception {
      return this.diagInfoMapper.selectHistoryList(diagInfo);
   }

   public List selectDiagInfoByPatientId(String patientId) throws Exception {
      return this.diagInfoMapper.selectDiagInfoByPatientId(patientId);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void syncData(List hisList) throws Exception {
      this.diagInfoMapper.deleteDiagInfoAll();
      this.insertDiagInfoAdd(hisList);
   }

   public void insertDiagInfoAdd(List list) throws Exception {
      list.forEach((t) -> t.put("id", SnowIdUtils.uniqueLong()));
      int i = 0;

      for(Map temp : list) {
         this.log.info("i-> {}", i++);

         try {
            this.diagInfoMapper.insertMap(temp);
         } catch (Exception e) {
            this.log.info("对象数值=====  {}", temp.toString());
            this.log.error("同步诊断出现异常，", e.getMessage());
            throw new Exception(e.getMessage());
         }
      }

   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void syncAddData(List list, SqlVo sqlVo) throws Exception {
      this.insertDiagInfoAdd(list);
   }

   public void syncDataMap(List mapList, String tableName) throws Exception {
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void saveDiagInfo(List diagInfoList) throws Exception {
      Personalinfo personalinfo = this.personalinfoService.selectPersonalinfoById(((DiagInfo)diagInfoList.get(0)).getPatientId());
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      BasEmployee basEmployee = sysUser.getBasEmployee();
      List<DiagInfo> updateList = (List)diagInfoList.stream().filter((s) -> StringUtils.isNotEmpty(s.getId())).collect(Collectors.toList());
      List<DiagInfo> insertList = (List)diagInfoList.stream().filter((s) -> StringUtils.isEmpty(s.getId())).collect(Collectors.toList());
      String inhosDiagCd = "";
      String inhosDiagName = "";
      if (updateList != null && !updateList.isEmpty()) {
         for(DiagInfo diagInfo : updateList) {
            diagInfo.setAltDate(this.commonService.getDbSysdate());
            diagInfo.setAltPerCode(basEmployee.getEmplNumber());
            diagInfo.setAltPerName(basEmployee.getEmplName());
            if (diagInfo.getDiagClassCd().equals("01") && diagInfo.getDiagTypeCd().equals("02")) {
               inhosDiagCd = diagInfo.getDiagCd();
               inhosDiagName = diagInfo.getDiagName();
            }
         }

         this.diagInfoMapper.updateDiagInfoList(updateList);
      }

      if (insertList != null && !insertList.isEmpty()) {
         for(DiagInfo diagInfo : insertList) {
            diagInfo.setId(SnowIdUtils.uniqueLongHex());
            diagInfo.setPatientMainId(personalinfo.getPatientMainId());
            diagInfo.setOrgCd(sysUser.getHospital().getOrgCode());
            diagInfo.setOrgName(sysUser.getHospital().getOrgName());
            diagInfo.setCrePerCode(basEmployee.getEmplNumber());
            diagInfo.setCrePerName(basEmployee.getEmplName());
            diagInfo.setDiagDeptCd(sysUser.getDept().getDeptCode());
            diagInfo.setDiagDeptName(sysUser.getDept().getDeptName());
            if (diagInfo.getDiagClassCd().equals("01") && diagInfo.getDiagTypeCd().equals("02")) {
               inhosDiagCd = diagInfo.getDiagCd();
               inhosDiagName = diagInfo.getDiagName();
            }
         }

         this.diagInfoMapper.insertDiagInfoList(insertList);
      }

      Visitinfo visitinfo = new Visitinfo();
      visitinfo.setInhosDiagCd(inhosDiagCd);
      visitinfo.setInhosDiag(inhosDiagName);
      visitinfo.setPatientId(((DiagInfo)diagInfoList.get(0)).getPatientId());
      this.visitinfoService.updateVisitinfo(visitinfo);
      MrHpVo mrHpVo = this.mrHpMapper.selectMrHpByPatientId(personalinfo.getPatientId());
      if (mrHpVo != null) {
         String recordId = mrHpVo.getRecordId();
         List<MrHpDia> mrHpDiaList = this.mrHpDiaMapper.selectMrHpDiaByRescordId(recordId);
         if (CollectionUtils.isNotEmpty(mrHpDiaList)) {
            for(MrHpDia mrHpDia : mrHpDiaList) {
               DiagInfo diagInfo = (DiagInfo)diagInfoList.stream().filter((t) -> t.getDiagClass().equals(mrHpDia.getDiaType()) && t.getDiagClassCd().equals(mrHpDia.getDiaClass()) && t.getDiagTypeCd().equals(mrHpDia.getDiaItem())).findFirst().orElse((Object)null);
               if (diagInfo != null) {
                  mrHpDia.setDiaCd(diagInfo.getDiagCd());
                  mrHpDia.setDiaName(diagInfo.getDiagName());
                  mrHpDia.setInhosCondCd(diagInfo.getInhosCondCd());
                  mrHpDia.setInhosCond(diagInfo.getInhosCondName());
                  mrHpDia.setOutcomeCd(diagInfo.getOutcomeCd());
                  mrHpDia.setOutcome(diagInfo.getOutcomeName());
                  mrHpDia.setAltPerCode(sysUser.getUserName());
                  mrHpDia.setAltPerName(sysUser.getNickName());
                  this.mrHpDiaMapper.updateMrHpDia(mrHpDia);
               }
            }
         }
      }

   }

   public List selectPatientMainDiag(String patientId, String diagTypeCd, String diagClassCd) throws Exception {
      return this.diagInfoMapper.selectPatientMainDiag(patientId, diagTypeCd, diagClassCd);
   }

   public List syncHisDiagIInfoList(List inpNoList) throws Exception {
      List<Map<String, Object>> list = null;

      try {
         SqlVo sqlVo = new SqlVo();
         SyncDatasource syncDatasource = this.syncDatasourceService.selectSyncDatasourceByCode(DataSourceType.hisPatDiag.toString());
         sqlVo.setSqlStr(syncDatasource.getQuerySql());
         sqlVo.setInpNoList(inpNoList);
         DruidUtil.switchDB(syncDatasource);
         list = this.diagInfoMapper.selectHisDiagInfoList(sqlVo);
      } catch (Exception e) {
         this.log.error("查询PBHIS患者诊断数据出现异常：", e);
         throw new Exception(e);
      } finally {
         DruidUtil.clearDataSource();
      }

      return list;
   }

   public void deleteDiagInfoByPatientIds(List inpNoList) throws Exception {
      this.diagInfoMapper.deleteDiagInfoByPatientIds(inpNoList);
   }

   public List selectInHosDiagInFoByPatientId(String patientId) {
      return this.diagInfoMapper.selectInHosDiagInFoByPatientId(patientId);
   }

   public List selectPatientMainDiagByDiagTypeCdList(String patientId, Set stringSet) throws Exception {
      return this.diagInfoMapper.selectPatientMainDiagByDiagTypeCdList(patientId, stringSet);
   }
}
