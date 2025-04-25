package com.emr.project.emr.service;

import com.emr.project.emr.domain.EmrSetMain;
import java.util.List;

public interface IEmrSetMainService {
   EmrSetMain selectEmrSetMainById(String setCd);

   List selectEmrSetMainList(EmrSetMain emrSetMain) throws Exception;

   void insertEmrSetMain(EmrSetMain emrSetMain) throws Exception;

   void updateEmrSetMain(EmrSetMain emrSetMain) throws Exception;

   int deleteEmrSetMainByIds(String[] setCds);

   void deleteEmrSetMainById(String setCd) throws Exception;
}
