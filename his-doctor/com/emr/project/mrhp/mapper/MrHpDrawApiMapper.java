package com.emr.project.mrhp.mapper;

import com.emr.project.mrhp.domain.MrHpDrawApi;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MrHpDrawApiMapper {
   MrHpDrawApi selectMrHpDrawApiById(Long id);

   List selectMrHpDrawApiList(MrHpDrawApi mrHpDrawApi);

   int insertMrHpDrawApi(MrHpDrawApi mrHpDrawApi);

   int batchInsert(@Param("list") List mrHpDrawApiList);

   int updateMrHpDrawApi(MrHpDrawApi mrHpDrawApi);

   int deleteMrHpDrawApiById(Long id);

   int deleteMrHpDrawApiByMainId(Long mainId);

   int deleteMrHpDrawApiByIds(Long[] ids);
}
