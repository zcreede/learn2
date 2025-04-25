package com.emr.project.mrhp.mapper;

import com.emr.project.mrhp.domain.MrHpDrawMain;
import java.util.List;

public interface MrHpDrawMainMapper {
   MrHpDrawMain selectMrHpDrawMainById(Long id);

   List selectMrHpDrawMainList(MrHpDrawMain mrHpDrawMain);

   int insertMrHpDrawMain(MrHpDrawMain mrHpDrawMain);

   int updateMrHpDrawMain(MrHpDrawMain mrHpDrawMain);

   int deleteMrHpDrawMainById(Long id);

   int deleteMrHpDrawMainByIds(Long[] ids);
}
