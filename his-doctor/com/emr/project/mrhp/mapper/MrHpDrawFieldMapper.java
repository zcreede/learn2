package com.emr.project.mrhp.mapper;

import com.emr.project.mrhp.domain.MrHpDrawField;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MrHpDrawFieldMapper {
   MrHpDrawField selectMrHpDrawFieldById(Long id);

   List selectMrHpDrawFieldList(MrHpDrawField mrHpDrawField);

   int insertMrHpDrawField(MrHpDrawField mrHpDrawField);

   int batchInsert(@Param("list") List mrHpDrawField);

   int updateMrHpDrawField(MrHpDrawField mrHpDrawField);

   int deleteMrHpDrawFieldById(Long id);

   int deleteMrHpDrawFieldByIds(Long[] ids);

   int deleteMrHpDrawFieldByMainId(Long mainId);

   List selectFieldByMainIdList(@Param("list") List mainIdList);
}
