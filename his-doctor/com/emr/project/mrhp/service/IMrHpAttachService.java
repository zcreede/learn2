package com.emr.project.mrhp.service;

import com.emr.project.mrhp.domain.MrHpAttach;
import com.emr.project.mrhp.domain.mris.TdCmAttachSave;
import com.emr.project.mrhp.domain.vo.MrHpAttachVo;
import java.util.List;

public interface IMrHpAttachService {
   MrHpAttach selectMrHpAttachById(String recordId);

   MrHpAttachVo selectMrHpAttachByPatientId(String patientId);

   List selectMrHpAttachList(MrHpAttach mrHpAttach);

   void insertMrHpAttach(MrHpAttach mrHpAttach);

   int updateMrHpAttach(MrHpAttach mrHpAttach);

   int updateMrHpAttachNotNull(MrHpAttach mrHpAttach) throws Exception;

   int deleteMrHpAttachByIds(String[] recordIds);

   int deleteMrHpAttachById(String recordId);

   TdCmAttachSave selectMrisHpAttachByPatientId(String patientId);
}
