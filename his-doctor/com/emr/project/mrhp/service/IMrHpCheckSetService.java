package com.emr.project.mrhp.service;

import com.emr.project.mrhp.domain.MrHpCheckSet;
import com.emr.project.mrhp.domain.vo.MrHpCheckSetVo;
import java.util.List;

public interface IMrHpCheckSetService {
   MrHpCheckSetVo selectMrHpCheckSetById(String id) throws Exception;

   MrHpCheckSetVo selectMrHpCheckSetByCheckName(String checkName, Long id) throws Exception;

   List selectMrHpCheckSetVoList(MrHpCheckSetVo mrHpCheckSetVo) throws Exception;

   List selectMrHpCheckSetList(MrHpCheckSetVo mrHpCheckSetVo) throws Exception;

   void insertMrHpCheckSet(MrHpCheckSetVo mrHpCheckSetVo) throws Exception;

   void updateMrHpCheckSet(MrHpCheckSetVo mrHpCheckSetVo) throws Exception;

   int deleteMrHpCheckSetByIds(String[] ids);

   int deleteMrHpCheckSetById(String id);

   List selectTableField(MrHpCheckSetVo mrHpCheckSetVo) throws Exception;

   String createExpressionJson(MrHpCheckSetVo mrHpCheckSetVo) throws Exception;

   void updateEditFlag(MrHpCheckSet mrHpCheckSet) throws Exception;
}
