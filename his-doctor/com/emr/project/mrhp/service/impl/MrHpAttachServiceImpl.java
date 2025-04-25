package com.emr.project.mrhp.service.impl;

import com.emr.project.mrhp.domain.MrHpAttach;
import com.emr.project.mrhp.domain.mris.TdCmAttachSave;
import com.emr.project.mrhp.domain.vo.MrHpAttachVo;
import com.emr.project.mrhp.mapper.MrHpAttachMapper;
import com.emr.project.mrhp.service.IMrHpAttachService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MrHpAttachServiceImpl implements IMrHpAttachService {
   @Autowired
   private MrHpAttachMapper mrHpAttachMapper;

   public MrHpAttach selectMrHpAttachById(String recordId) {
      return this.mrHpAttachMapper.selectMrHpAttachById(recordId);
   }

   public MrHpAttachVo selectMrHpAttachByPatientId(String patientId) {
      return this.mrHpAttachMapper.selectMrHpAttachByPatientId(patientId);
   }

   public List selectMrHpAttachList(MrHpAttach mrHpAttach) {
      return this.mrHpAttachMapper.selectMrHpAttachList(mrHpAttach);
   }

   public void insertMrHpAttach(MrHpAttach mrHpAttach) {
      this.mrHpAttachMapper.insertMrHpAttach(mrHpAttach);
   }

   public int updateMrHpAttach(MrHpAttach mrHpAttach) {
      return this.mrHpAttachMapper.updateMrHpAttach(mrHpAttach);
   }

   public int updateMrHpAttachNotNull(MrHpAttach mrHpAttach) throws Exception {
      return this.mrHpAttachMapper.updateMrHpAttachNotNull(mrHpAttach);
   }

   public int deleteMrHpAttachByIds(String[] recordIds) {
      return this.mrHpAttachMapper.deleteMrHpAttachByIds(recordIds);
   }

   public int deleteMrHpAttachById(String recordId) {
      return this.mrHpAttachMapper.deleteMrHpAttachById(recordId);
   }

   public TdCmAttachSave selectMrisHpAttachByPatientId(String patientId) {
      return this.mrHpAttachMapper.selectMrisHpAttachByPatientId(patientId);
   }
}
