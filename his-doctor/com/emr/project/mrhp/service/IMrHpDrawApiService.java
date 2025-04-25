package com.emr.project.mrhp.service;

import com.emr.project.mrhp.domain.MrHpDrawApi;
import java.util.List;

public interface IMrHpDrawApiService {
   MrHpDrawApi selectMrHpDrawApiById(Long id);

   List selectMrHpDrawApiList(MrHpDrawApi mrHpDrawApi);

   int insertMrHpDrawApi(MrHpDrawApi mrHpDrawApi);

   int batchInsert(List mrHpDrawApiList);

   int updateMrHpDrawApi(MrHpDrawApi mrHpDrawApi);

   int deleteMrHpDrawApiByIds(Long[] ids);

   int deleteMrHpDrawApiById(Long id);

   int deleteMrHpDrawApiByMainId(Long mainId);
}
