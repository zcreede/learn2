package com.emr.project.emr.mapper;

import com.emr.project.emr.domain.EmrSetMain;
import java.util.List;

public interface EmrSetMainMapper {
   EmrSetMain selectEmrSetMainById(String setCd);

   List selectEmrSetMainList(EmrSetMain emrSetMain);

   void insertEmrSetMain(EmrSetMain emrSetMain);

   int updateEmrSetMain(EmrSetMain emrSetMain);

   int deleteEmrSetMainById(String setCd);

   int deleteEmrSetMainByIds(String[] setCds);
}
