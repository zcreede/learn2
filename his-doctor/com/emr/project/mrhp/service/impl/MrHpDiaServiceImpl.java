package com.emr.project.mrhp.service.impl;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.common.utils.StringUtils;
import com.emr.project.mrhp.domain.MrHpDia;
import com.emr.project.mrhp.domain.mris.TdCmDiagSave;
import com.emr.project.mrhp.domain.mris.TdCmHpLineVo;
import com.emr.project.mrhp.domain.vo.MrHpVo;
import com.emr.project.mrhp.mapper.MrHpDiaMapper;
import com.emr.project.mrhp.service.IMrHpDiaService;
import com.emr.project.pat.domain.DiagInfo;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.domain.vo.SqlVo;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MrHpDiaServiceImpl implements IMrHpDiaService {
   @Autowired
   private MrHpDiaMapper mrHpDiaMapper;

   public MrHpDia selectMrHpDiaById(String diaId) {
      return this.mrHpDiaMapper.selectMrHpDiaById(diaId);
   }

   public List selectMrHpDiaList(MrHpDia mrHpDia) {
      return this.mrHpDiaMapper.selectMrHpDiaList(mrHpDia);
   }

   public List selectTdCmDiagListLineByRecordId(String recordId, String diagType) {
      return this.mrHpDiaMapper.selectTdCmDiagListLineByRecordId(recordId, diagType);
   }

   public int insertMrHpDia(MrHpDia mrHpDia) {
      return this.mrHpDiaMapper.insertMrHpDia(mrHpDia);
   }

   public int updateMrHpDia(MrHpDia mrHpDia) {
      return this.mrHpDiaMapper.updateMrHpDia(mrHpDia);
   }

   public int deleteMrHpDiaByIds(String[] diaIds) {
      return this.mrHpDiaMapper.deleteMrHpDiaByIds(diaIds);
   }

   public int deleteMrHpDiaById(String diaId) {
      return this.mrHpDiaMapper.deleteMrHpDiaById(diaId);
   }

   public void deleteMrHpDiaByRecordId(String recordId) {
      this.mrHpDiaMapper.deleteMrHpDiaByRecordId(recordId);
   }

   public List selectMrHpDiaByRescordId(String rescordId) throws Exception {
      return this.mrHpDiaMapper.selectMrHpDiaByRescordId(rescordId);
   }

   public List selectDiagInfoByPatientId(DiagInfo diagInfo) throws Exception {
      return this.mrHpDiaMapper.selectDiagInfoByPatientId(diagInfo);
   }

   public void insertMrHpDiaList(MrHpVo mrHpVo) {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      this.mrHpDiaMapper.deleteMrHpDiaByRecordId(mrHpVo.getRecordId());
      List<MrHpDia> mrHpDiaList = new ArrayList(1);
      mrHpDiaList.addAll(mrHpVo.getMrHpDiaXYList());
      mrHpDiaList.addAll(mrHpVo.getMrHpDiaZYList());
      List<MrHpDia> list = new ArrayList();

      for(MrHpDia mrHpDia : mrHpDiaList) {
         if (StringUtils.isNotEmpty(mrHpDia.getDiaName())) {
            mrHpDia.setCrePerCode(sysUser.getUserName());
            mrHpDia.setCrePerName(sysUser.getNickName());
            mrHpDia.setRecordId(mrHpVo.getRecordId());
            list.add(mrHpDia);
         }
      }

      if (list != null && list.size() > 0) {
         this.mrHpDiaMapper.insertMrHpDiaList(list);
      }

   }

   public List selectHisPbMrDiaList(SqlVo sqlVo) throws Exception {
      return this.mrHpDiaMapper.selectHisPbMrDiaList(sqlVo);
   }

   public List selectMrisDiagInfoByPatientId(DiagInfo diagInfo) throws Exception {
      return this.mrHpDiaMapper.selectMrisDiagInfoByPatientId(diagInfo);
   }

   public List selectMrisMrHpDiaList(MrHpDia mrHpDia) throws Exception {
      return this.mrHpDiaMapper.selectMrisMrHpDiaList(mrHpDia);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void insertMrisHpDiaList(TdCmHpLineVo mrHpVo) throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      this.mrHpDiaMapper.deleteMrHpDiaByRecordId(mrHpVo.getRecord_id());
      List<TdCmDiagSave> mrHpDiaList = new ArrayList(1);
      mrHpDiaList.addAll(mrHpVo.getTdCmDiagXyList());
      mrHpDiaList.addAll(mrHpVo.getTdCmDiagZyList());
      List<MrHpDia> list = new ArrayList();

      for(TdCmDiagSave diagSave : mrHpDiaList) {
         if (StringUtils.isNotEmpty(diagSave.getDiag_name())) {
            MrHpDia mrHpDia = new MrHpDia();
            mrHpDia.setDiaId(String.valueOf(SnowIdUtils.uniqueLong()));
            mrHpDia.setCrePerCode(sysUser.getUserName());
            mrHpDia.setCrePerName(sysUser.getNickName());
            mrHpDia.setRecordId(mrHpVo.getRecord_id());
            mrHpDia.setDiaType(diagSave.getDiag_type());
            mrHpDia.setDiaNo(diagSave.getDiag_seq());
            mrHpDia.setDiaName(diagSave.getDiag_name());
            mrHpDia.setDiaCd(diagSave.getDiag_cd());
            mrHpDia.setDiaExCd(diagSave.getDiag_ex_cd());
            mrHpDia.setInhosCond(diagSave.getInhos_cond());
            mrHpDia.setInhosCondCd(diagSave.getInhos_cond_cd());
            mrHpDia.setDiaClass(diagSave.getDiag_class());
            mrHpDia.setOutcome(diagSave.getOutcome());
            mrHpDia.setOutcomeCd(diagSave.getOutcome_cd());
            mrHpDia.setDiaItem(diagSave.getDiag_item());
            mrHpDia.setDiaRem(diagSave.getDiag_rem());
            list.add(mrHpDia);
         }
      }

      if (list.size() > 0) {
         this.mrHpDiaMapper.insertMrHpDiaList(list);
      }

   }
}
