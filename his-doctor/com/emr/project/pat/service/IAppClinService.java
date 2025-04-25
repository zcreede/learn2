package com.emr.project.pat.service;

import com.emr.project.pat.domain.AppClin;
import java.util.Date;
import java.util.List;

public interface IAppClinService {
   AppClin selectAppClinById(Long id);

   List selectAppClinList(AppClin appClin);

   int insertAppClin(AppClin appClin);

   int updateAppClin(AppClin appClin);

   void updateStateDateByApp(String appCd, String clinItemCd, String itemState, Date clinRepDate);

   int deleteAppClinByIds(Long[] ids);

   int deleteAppClinById(Long id);
}
