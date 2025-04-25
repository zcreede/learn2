package com.emr.project.mrhp.service;

import com.emr.project.mrhp.domain.MrHpDrawField;
import java.util.List;

public interface IMrHpDrawFieldService {
   MrHpDrawField selectMrHpDrawFieldById(Long id);

   List selectMrHpDrawFieldList(MrHpDrawField mrHpDrawField);

   int insertMrHpDrawField(MrHpDrawField mrHpDrawField);

   int batchInsert(List mrHpDrawField);

   int updateMrHpDrawField(MrHpDrawField mrHpDrawField);

   int deleteMrHpDrawFieldByIds(Long[] ids);

   int deleteMrHpDrawFieldById(Long id);

   int deleteMrHpDrawFieldByMainId(Long mainId);

   List selectFieldByMainIdList(List mainIdList);
}
