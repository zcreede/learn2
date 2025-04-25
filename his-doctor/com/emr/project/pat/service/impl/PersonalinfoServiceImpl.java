package com.emr.project.pat.service.impl;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.StringUtils;
import com.emr.framework.aspectj.lang.enums.DataSourceType;
import com.emr.framework.datasource.DruidUtil;
import com.emr.framework.web.service.ISyncService;
import com.emr.project.pat.domain.Personalinfo;
import com.emr.project.pat.domain.Visitinfo;
import com.emr.project.pat.domain.vo.PatFeeVo;
import com.emr.project.pat.domain.vo.PersonalinfoForSxVo;
import com.emr.project.pat.domain.vo.PersonalinfoVo;
import com.emr.project.pat.domain.vo.VisitinfoVo;
import com.emr.project.pat.mapper.PersonalinfoMapper;
import com.emr.project.pat.service.IDrgMrGroupService;
import com.emr.project.pat.service.IPatFeeService;
import com.emr.project.pat.service.IPersonalinfoService;
import com.emr.project.pat.service.IVisitinfoService;
import com.emr.project.system.domain.SyncDatasource;
import com.emr.project.system.domain.SysDictData;
import com.emr.project.system.domain.SysOrg;
import com.emr.project.system.domain.vo.SqlVo;
import com.emr.project.system.mapper.SysOrgMapper;
import com.emr.project.system.service.ISyncDatasourceService;
import com.emr.project.system.service.ISysDictDataService;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonalinfoServiceImpl implements IPersonalinfoService, ISyncService {
   protected final Logger log = LoggerFactory.getLogger(PersonalinfoServiceImpl.class);
   @Autowired
   private PersonalinfoMapper personalinfoMapper;
   @Autowired
   private IPatFeeService iPatFeeService;
   @Autowired
   private ISyncDatasourceService syncDatasourceService;
   @Autowired
   private IDrgMrGroupService iDrgMrGroupService;
   @Autowired
   private IVisitinfoService visitinfoService;
   @Autowired
   private ISysDictDataService sysDictDataService;
   @Autowired
   private SysOrgMapper sysOrgMapper;

   public PersonalinfoVo selectAllList(PersonalinfoVo personalinfoVo) throws Exception {
      PersonalinfoVo infoVo = this.personalinfoMapper.selectPersonalinfoVoById(personalinfoVo.getPatientId());
      if (infoVo != null) {
         SysOrg sysOrg = this.sysOrgMapper.checkOrgCodeUnique(SecurityUtils.getLoginUser().getUser().getHospital().getOrgCode());
         String nowAddrPro = infoVo.getNowAddrPro();
         if (StringUtils.isEmpty(infoVo.getNowAddrPro())) {
            infoVo.setNowAddrPro(sysOrg.getAddressSheng());
         }

         if (StringUtils.isEmpty(nowAddrPro) && StringUtils.isEmpty(infoVo.getNowAddrFlagty())) {
            infoVo.setNowAddrFlagty(sysOrg.getAddressShi());
         }

         if (StringUtils.isEmpty(nowAddrPro) && StringUtils.isEmpty(infoVo.getNowAddrCou())) {
            infoVo.setNowAddrCou(sysOrg.getAddressXian());
         }

         if (StringUtils.isEmpty(nowAddrPro) && StringUtils.isEmpty(infoVo.getNowAddrTown())) {
            infoVo.setNowAddrTown(sysOrg.getAddressXiang());
         }

         String citAddrPro = infoVo.getCitAddrPro();
         if (StringUtils.isEmpty(citAddrPro)) {
            infoVo.setCitAddrPro(sysOrg.getAddressSheng());
         }

         if (StringUtils.isEmpty(citAddrPro) && StringUtils.isEmpty(infoVo.getCitAddrFlagty())) {
            infoVo.setCitAddrFlagty(sysOrg.getAddressShi());
         }

         if (StringUtils.isEmpty(citAddrPro) && StringUtils.isEmpty(infoVo.getCitAddrCou())) {
            infoVo.setCitAddrCou(sysOrg.getAddressXian());
         }

         if (StringUtils.isEmpty(citAddrPro) && StringUtils.isEmpty(infoVo.getCitAddrTown())) {
            infoVo.setCitAddrTown(sysOrg.getAddressXiang());
         }

         String rprAddrPro = infoVo.getRprAddrPro();
         if (StringUtils.isEmpty(rprAddrPro)) {
            infoVo.setRprAddrPro(sysOrg.getAddressSheng());
         }

         if (StringUtils.isEmpty(rprAddrPro) && StringUtils.isEmpty(infoVo.getRprAddrFlagty())) {
            infoVo.setRprAddrFlagty(sysOrg.getAddressShi());
         }

         if (StringUtils.isEmpty(rprAddrPro) && StringUtils.isEmpty(infoVo.getRprAddrCou())) {
            infoVo.setRprAddrCou(sysOrg.getAddressXian());
         }

         if (StringUtils.isEmpty(rprAddrPro) && StringUtils.isEmpty(infoVo.getRprAddrTown())) {
            infoVo.setRprAddrTown(sysOrg.getAddressXiang());
         }

         String birAddrPro = infoVo.getBirAddrPro();
         if (StringUtils.isEmpty(birAddrPro)) {
            infoVo.setBirAddrPro(sysOrg.getAddressSheng());
         }

         if (StringUtils.isEmpty(birAddrPro) && StringUtils.isEmpty(infoVo.getBirAddrPlagty())) {
            infoVo.setBirAddrPlagty(sysOrg.getAddressShi());
         }

         if (StringUtils.isEmpty(birAddrPro) && StringUtils.isEmpty(infoVo.getBirAddrCou())) {
            infoVo.setBirAddrCou(sysOrg.getAddressXian());
         }

         String workAddrPro = infoVo.getWorkAddrPro();
         if (StringUtils.isEmpty(workAddrPro)) {
            infoVo.setWorkAddrPro(sysOrg.getAddressSheng());
         }

         if (StringUtils.isEmpty(workAddrPro) && StringUtils.isEmpty(infoVo.getWorkAddrFlagty())) {
            infoVo.setWorkAddrFlagty(sysOrg.getAddressShi());
         }

         if (StringUtils.isEmpty(workAddrPro) && StringUtils.isEmpty(infoVo.getWorkAddrCou())) {
            infoVo.setWorkAddrCou(sysOrg.getAddressXian());
         }

         if (StringUtils.isEmpty(workAddrPro) && StringUtils.isEmpty(infoVo.getWorkAddrTown())) {
            infoVo.setWorkAddrTown(sysOrg.getAddressXiang());
         }

         String[] dictStr = new String[]{"RC035", "s035", "RC003", "s016"};
         List<SysDictData> dictList = this.sysDictDataService.selectDictDataListByType(dictStr);

         for(SysDictData sysDictData : (List)dictList.stream().filter((s) -> s.getDictType().equals("RC035")).collect(Collectors.toList())) {
            if (StringUtils.isNotBlank(infoVo.getNationCd()) && sysDictData.getDictValue().equals(infoVo.getNationCd())) {
               infoVo.setNation(sysDictData.getDictLabel());
            }
         }

         for(SysDictData sysDictData : (List)dictList.stream().filter((s) -> s.getDictType().equals("s035")).collect(Collectors.toList())) {
            if (StringUtils.isNotBlank(infoVo.getMarStaCd()) && sysDictData.getDictValue().equals(infoVo.getMarStaCd())) {
               infoVo.setMarSta(sysDictData.getDictLabel());
            }
         }

         for(SysDictData sysDictData : (List)dictList.stream().filter((s) -> s.getDictType().equals("RC003")).collect(Collectors.toList())) {
            if (StringUtils.isNotBlank(infoVo.getProTypeCd()) && sysDictData.getDictValue().equals(infoVo.getProTypeCd())) {
               infoVo.setProTypeName(sysDictData.getDictLabel());
            }
         }

         personalinfoVo.getPatFeeVo().setInpNo(personalinfoVo.getInpNo());
         infoVo.setPatFeeVo(this.iPatFeeService.selectAmountList(personalinfoVo.getPatFeeVo()));
         PatFeeVo patFeeVo = new PatFeeVo();
         patFeeVo.setInpNo(personalinfoVo.getInpNo());
         infoVo.setFeeList(this.iPatFeeService.selectFeeList(patFeeVo));
         personalinfoVo.getDrgMrGroupVo().setPatientId(personalinfoVo.getPatientId());
         infoVo.setDrgMrGroupVo(this.iDrgMrGroupService.selectInfo(personalinfoVo.getDrgMrGroupVo()));
         VisitinfoVo visitinfoVo = this.visitinfoService.selectVisitinfoById(personalinfoVo.getPatientId());
         if (StringUtils.isEmpty(visitinfoVo.getNowAddrPro())) {
            visitinfoVo.setNowAddrPro(sysOrg.getAddressSheng());
         }

         if (StringUtils.isEmpty(visitinfoVo.getNowAddrPro()) && StringUtils.isEmpty(visitinfoVo.getNowAddrFlagty())) {
            visitinfoVo.setNowAddrFlagty(sysOrg.getAddressShi());
         }

         if (StringUtils.isEmpty(visitinfoVo.getNowAddrPro()) && StringUtils.isEmpty(visitinfoVo.getNowAddrCou())) {
            visitinfoVo.setNowAddrCou(sysOrg.getAddressXian());
         }

         if (StringUtils.isEmpty(visitinfoVo.getNowAddrPro()) && StringUtils.isEmpty(visitinfoVo.getNowAddrTown())) {
            visitinfoVo.setNowAddrTown(sysOrg.getAddressXiang());
         }

         for(SysDictData sysDictData : (List)dictList.stream().filter((s) -> s.getDictType().equals("s016")).collect(Collectors.toList())) {
            if (sysDictData.getDictValue().equals(visitinfoVo.getInhosCondCd())) {
               visitinfoVo.setInhosCond(sysDictData.getDictLabel());
            }
         }

         infoVo.setVisitinfoVo(visitinfoVo);
      }

      return infoVo;
   }

   public void updateModiFlag(List list) {
      if (list != null && !list.isEmpty()) {
         this.personalinfoMapper.updateModiFlag(list);
      }

   }

   public int updatePersonSave(Personalinfo personalinfo) throws Exception {
      return this.personalinfoMapper.updatePersonSave(personalinfo);
   }

   public Personalinfo selectPersonalinfoById(String patientId) {
      return this.personalinfoMapper.selectPersonalinfoById(patientId);
   }

   public List selectPersonalinfoList(Personalinfo personalinfo) {
      return this.personalinfoMapper.selectPersonalinfoList(personalinfo);
   }

   public int insertPersonalinfo(Personalinfo personalinfo) {
      return this.personalinfoMapper.insertPersonalinfo(personalinfo);
   }

   public int updatePersonalinfo(Personalinfo personalinfo) throws Exception {
      return this.personalinfoMapper.updatePersonalinfo(personalinfo);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void updatePersonAndVisit(PersonalinfoVo personalinfoVo) throws Exception {
      Personalinfo personalinfo = new Personalinfo();
      BeanUtils.copyProperties(personalinfoVo, personalinfo);
      this.personalinfoMapper.updatePersonalinfo(personalinfo);
      Visitinfo visitinfo = new Visitinfo();
      visitinfo.setPatTypeCd(personalinfoVo.getPatTypeCd());
      visitinfo.setPatTypeName(personalinfoVo.getPatTypeName());
      visitinfo.setHeight(personalinfoVo.getHeight());
      visitinfo.setWeight(personalinfoVo.getWeight());
      visitinfo.setPatientId(personalinfoVo.getPatientId());
      this.visitinfoService.updateVisitinfo(visitinfo);
   }

   public int deletePersonalinfoByIds(String[] patientIds) {
      return this.personalinfoMapper.deletePersonalinfoByIds(patientIds);
   }

   public int deletePersonalinfoById(String patientId) {
      return this.personalinfoMapper.deletePersonalinfoById(patientId);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void syncData(List hisDataList) throws Exception {
      int i = 0;

      for(Personalinfo temp : hisDataList) {
         this.log.info("i-> {}", i++);

         try {
            this.personalinfoMapper.insertPersonalinfo(temp);
         } catch (Exception e) {
            this.log.info("对象数值=====  {}", temp.toString());
            this.log.error("", e.getMessage());
            throw new Exception(e.getMessage());
         }
      }

   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void syncDataMap(List mapList, String tableName) throws Exception {
      if (mapList != null && !mapList.isEmpty()) {
         this.personalinfoMapper.deletePersonalinfo(tableName, (String)null);

         for(Map map : mapList) {
            map.put("tableName", tableName);
            this.personalinfoMapper.insertMapList(map);
         }
      }

   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void syncAddData(List hisDataList, SqlVo sqlVo) throws Exception {
      if (!"T_AR_BASEINFOMATION".equals(sqlVo.getTableName()) && !"T_AR_BASEINFOMATION_H".equals(sqlVo.getTableName())) {
         if ("T_AR_BASEINFOMATION_DAY".equals(sqlVo.getTableName())) {
            this.personalinfoMapper.deletePersonalinfo(sqlVo.getTableName(), (String)null);

            for(Map map : hisDataList) {
               map.put("tableName", sqlVo.getTableName());
               this.personalinfoMapper.insertMapList(map);
            }
         }
      } else {
         for(Map map : hisDataList) {
            this.personalinfoMapper.deletePersonalinfo(sqlVo.getTableName(), map.get("admission_no").toString());
            map.put("tableName", sqlVo.getTableName());
            this.personalinfoMapper.insertMapList(map);
         }
      }

      if (!"T_AR_BASEINFOMATION".equals(sqlVo.getTableName())) {
         sqlVo.setTableName("T_AR_BASEINFOMATION");
         List<Personalinfo> emrData = this.personalinfoMapper.selectSyncPersonalInfoList(sqlVo);
         List<String> allIdList = (List)emrData.stream().map((s) -> s.getInpNo()).collect(Collectors.toList());

         for(Map map : hisDataList) {
            if (allIdList.contains(map.get("admission_no").toString())) {
               this.personalinfoMapper.deletePersonalinfo("T_AR_BASEINFOMATION", map.get("admission_no").toString());
            }
         }
      }

   }

   public List selectPersonListByPatientId(String patientId) throws Exception {
      return this.personalinfoMapper.selectPersonListByPatientId(patientId);
   }

   public PersonalinfoVo selectPersonalinfoVoById(String patientId) throws Exception {
      return this.personalinfoMapper.selectPersonalinfoVoById(patientId);
   }

   public Personalinfo selectPersonalinfoByInpNo(String inpNo) throws Exception {
      return this.personalinfoMapper.selectPersonalinfoByInpNo(inpNo);
   }

   public List setSyncPersonalInfoList(SqlVo deptVo) throws Exception {
      List<Map<String, Object>> deptList;
      try {
         SyncDatasource syncDatasource = this.syncDatasourceService.selectSyncDatasourceByCode(DataSourceType.hisPersonalInfoList.toString());
         deptVo.setSqlStr(syncDatasource.getQuerySql());
         DruidUtil.switchDB(syncDatasource);
         deptList = this.personalinfoMapper.selectHisPersonalInfoList(deptVo);
      } finally {
         DruidUtil.clearDataSource();
      }

      return deptList;
   }

   public List getPatientInfoForSx(PersonalinfoForSxVo personalinfoForSxVo) throws Exception {
      List<Map<String, Object>> deptList = this.personalinfoMapper.getPatientInfoForSx(personalinfoForSxVo);
      return deptList;
   }

   public void deletePersonalinfo(String tableName, String inpNo) throws Exception {
      this.personalinfoMapper.deletePersonalinfo(tableName, inpNo);
   }

   public void deletePersonalinfoByInpNo(String tableName, List inpNoList) throws Exception {
      this.personalinfoMapper.deletePersonalinfoByInpNo(tableName, inpNoList);
   }

   public void insertMapList(Map map) throws Exception {
      this.personalinfoMapper.insertMapList(map);
   }

   public Boolean checkIdCardRepeat(String patientId, String idCard) {
      Integer count = this.personalinfoMapper.checkIdCardRepeat(patientId, idCard);
      return count > 0 ? Boolean.FALSE : Boolean.TRUE;
   }
}
