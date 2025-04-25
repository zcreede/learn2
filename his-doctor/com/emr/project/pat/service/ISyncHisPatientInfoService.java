package com.emr.project.pat.service;

import java.util.List;

public interface ISyncHisPatientInfoService {
   void syncHisPatient(List inpNoList) throws Exception;

   void selectHisPatList(List tranList, List diagList, List alleInfoList, List babyList, List inpNoList) throws Exception;
}
