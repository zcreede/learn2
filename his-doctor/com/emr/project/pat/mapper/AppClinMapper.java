package com.emr.project.pat.mapper;

import com.emr.project.pat.domain.AppClin;
import java.util.List;
import java.util.Map;

public interface AppClinMapper {
   AppClin selectAppClinById(Long id);

   List selectAppClinList(AppClin appClin);

   int insertAppClin(AppClin appClin);

   int updateAppClin(AppClin appClin);

   int deleteAppClinById(Long id);

   int deleteAppClinByIds(Long[] ids);

   void insertMap(Map map);

   void updateStateDateByApp(AppClin appClin);
}
