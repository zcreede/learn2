package com.emr.project.mrhp.service.impl;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.StringUtils;
import com.emr.project.mrhp.domain.MrHpOpe;
import com.emr.project.mrhp.domain.mris.TdCmHpLineVo;
import com.emr.project.mrhp.domain.mris.TdCmOperSave;
import com.emr.project.mrhp.domain.vo.MrHpOpeVo;
import com.emr.project.mrhp.domain.vo.MrHpVo;
import com.emr.project.mrhp.mapper.MrHpOpeMapper;
import com.emr.project.mrhp.service.IMrHpOpeService;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.domain.vo.SqlVo;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MrHpOpeServiceImpl implements IMrHpOpeService {
   @Autowired
   private MrHpOpeMapper mrHpOpeMapper;

   public MrHpOpe selectMrHpOpeById(String opeId) {
      return this.mrHpOpeMapper.selectMrHpOpeById(opeId);
   }

   public List selectMrHpOpeByRecordId(String recordId) {
      return this.mrHpOpeMapper.selectMrHpOpeByRecordId(recordId);
   }

   public List selectMrHpOpeList(MrHpOpe mrHpOpe) {
      return this.mrHpOpeMapper.selectMrHpOpeList(mrHpOpe);
   }

   public int insertMrHpOpe(MrHpOpe mrHpOpe) {
      return this.mrHpOpeMapper.insertMrHpOpe(mrHpOpe);
   }

   public int updateMrHpOpe(MrHpOpe mrHpOpe) {
      return this.mrHpOpeMapper.updateMrHpOpe(mrHpOpe);
   }

   public int deleteMrHpOpeByIds(String[] opeIds) {
      return this.mrHpOpeMapper.deleteMrHpOpeByIds(opeIds);
   }

   public int deleteMrHpOpeById(String opeId) {
      return this.mrHpOpeMapper.deleteMrHpOpeById(opeId);
   }

   public void deleteMrHpOpeByRecordId(String recordId) {
      this.mrHpOpeMapper.deleteMrHpOpeByRecordId(recordId);
   }

   public List selectMrHpOpeByRescordId(String rescordId) throws Exception {
      return this.mrHpOpeMapper.selectMrHpOpeByRescordId(rescordId);
   }

   public List selectOperatRecordByPatientId(String patientId) throws Exception {
      return this.mrHpOpeMapper.selectOperatRecordByPatientId(patientId);
   }

   public void insertMrHpOpeList(MrHpVo mrHpVo) throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      this.mrHpOpeMapper.deleteMrHpOpeByRecordId(mrHpVo.getRecordId());
      List<MrHpOpeVo> mrHpOpeList = mrHpVo.getMrHpOpeList();
      List<MrHpOpe> list = new ArrayList();

      for(MrHpOpe mrHpOpe : mrHpOpeList) {
         if (StringUtils.isNotEmpty(mrHpOpe.getOprName())) {
            mrHpOpe.setCrePerCode(sysUser.getUserName());
            mrHpOpe.setCrePerName(sysUser.getNickName());
            mrHpOpe.setRecordId(mrHpVo.getRecordId());
            list.add(mrHpOpe);
         }
      }

      if (list != null && list.size() > 0) {
         this.mrHpOpeMapper.insertMrHpOpeList(list);
      }

   }

   public List selectPbHisMrHpOpe(SqlVo sqlVo) {
      return this.mrHpOpeMapper.selectPbHisMrHpOpe(sqlVo);
   }

   public List selectMrisOperatRecordByPatientId(String patientId) throws Exception {
      return this.mrHpOpeMapper.selectMrisOperatRecordByPatientId(patientId);
   }

   public List selectMrisMrHpOpeByRescordId(String recordId) throws Exception {
      return this.mrHpOpeMapper.selectMrisMrHpOpeByRescordId(recordId);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void insertMrisHpOpeList(TdCmHpLineVo mrHpVo) throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      this.mrHpOpeMapper.deleteMrHpOpeByRecordId(mrHpVo.getRecord_id());
      List<TdCmOperSave> mrHpOpeList = mrHpVo.getTdCmOperList();
      List<MrHpOpe> list = new ArrayList();

      for(TdCmOperSave operSave : mrHpOpeList) {
         if (StringUtils.isNotEmpty(operSave.getOper_name())) {
            MrHpOpe mrHpOpe = new MrHpOpe();
            mrHpOpe.setCrePerCode(sysUser.getUserName());
            mrHpOpe.setCrePerName(sysUser.getNickName());
            mrHpOpe.setRecordId(mrHpVo.getRecord_id());
            mrHpOpe.setOpeId(mrHpVo.getRecord_id() + operSave.getOper_seq());
            mrHpOpe.setOpeNo(operSave.getOper_seq());
            mrHpOpe.setOprIcd(operSave.getOper_icd());
            mrHpOpe.setOprName(operSave.getOper_name());
            mrHpOpe.setOprBeginDatetime(operSave.getOper_begin_dt());
            mrHpOpe.setOprEndDatetime(operSave.getOper_end_dt());
            mrHpOpe.setOprLevel(operSave.getOper_level_name());
            mrHpOpe.setOprLevelCode(operSave.getOper_level_code());
            mrHpOpe.setOpeCode(operSave.getOper_doc_code());
            mrHpOpe.setOpeName(operSave.getOper_doc_name());
            mrHpOpe.setAid1Code(operSave.getAid1_code());
            mrHpOpe.setAid2Code(operSave.getAid2_code());
            mrHpOpe.setAid1Name(operSave.getAid1_name());
            mrHpOpe.setAid2Name(operSave.getAid2_name());
            mrHpOpe.setOprInciCode(operSave.getOper_inci_code());
            mrHpOpe.setOprInciName(operSave.getOper_inci_name());
            mrHpOpe.setOprHealCode(operSave.getOper_heal_code());
            mrHpOpe.setOprHealName(operSave.getOper_heal_name());
            mrHpOpe.setAnestBeginTime(operSave.getAnest_begin_time());
            mrHpOpe.setAnestEndTime(operSave.getAnest_end_time());
            mrHpOpe.setAnestMethCode(operSave.getAnest_meth_code());
            mrHpOpe.setAnestAsa(operSave.getAnest_asa());
            mrHpOpe.setAnestCode(operSave.getAnest_code());
            mrHpOpe.setAnestName(operSave.getAnest_name());
            mrHpOpe.setAnestMethName(operSave.getAnest_meth_name());
            mrHpOpe.setOprSiteCode(operSave.getOper_site_code());
            mrHpOpe.setOprSiteName(operSave.getOper_site_name());
            mrHpOpe.setOprNnis(operSave.getOper_nnis());
            mrHpOpe.setOpeMain(operSave.getOper_main());
            mrHpOpe.setOpeType(operSave.getOper_type());
            list.add(mrHpOpe);
         }
      }

      if (list.size() > 0) {
         this.mrHpOpeMapper.insertMrHpOpeList(list);
      }

   }
}
