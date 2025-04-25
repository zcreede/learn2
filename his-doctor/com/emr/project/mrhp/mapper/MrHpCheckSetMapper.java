package com.emr.project.mrhp.mapper;

import com.emr.project.mrhp.domain.MrHpCheckSet;
import com.emr.project.mrhp.domain.vo.MrHpCheckSetVo;
import java.util.List;

public interface MrHpCheckSetMapper {
   MrHpCheckSetVo selectMrHpCheckSetById(String id);

   MrHpCheckSetVo selectMrHpCheckSetByCheckName(String checkName, Long id);

   List selectMrHpCheckSetVoList(MrHpCheckSetVo mrHpCheckSetVo);

   List selectMrHpCheckSetList(MrHpCheckSetVo mrHpCheckSetVo);

   void insertMrHpCheckSet(MrHpCheckSetVo mrHpCheckSetVo);

   void updateMrHpCheckSet(MrHpCheckSet mrHpCheckSet);

   void updateEditFlag(MrHpCheckSet mrHpCheckSet);

   int deleteMrHpCheckSetById(String id);

   int deleteMrHpCheckSetByIds(String[] ids);
}
