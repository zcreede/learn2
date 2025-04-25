package com.emr.project.mrhp.mapper;

import com.emr.project.mrhp.domain.MrHpAttach;
import com.emr.project.mrhp.domain.mris.TdCmAttachSave;
import com.emr.project.mrhp.domain.vo.MrHpAttachVo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MrHpAttachMapper {
   MrHpAttach selectMrHpAttachById(String recordId);

   MrHpAttachVo selectMrHpAttachByPatientId(String patientId);

   List selectMrHpAttachList(MrHpAttach mrHpAttach);

   int insertMrHpAttach(MrHpAttach mrHpAttach);

   int updateMrHpAttach(MrHpAttach mrHpAttach);

   int updateMrHpAttachNotNull(MrHpAttach mrHpAttach);

   int deleteMrHpAttachById(String recordId);

   int deleteMrHpAttachByIds(String[] recordIds);

   TdCmAttachSave selectMrisHpAttachByPatientId(@Param("patientId") String patientId);
}
