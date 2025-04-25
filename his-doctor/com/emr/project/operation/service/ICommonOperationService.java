package com.emr.project.operation.service;

import com.emr.project.operation.domain.Medicalinformation;
import java.math.BigDecimal;
import java.util.Date;

public interface ICommonOperationService {
   boolean checkPatientArrears(String zyh, String zycs, BigDecimal jzje) throws Exception;

   Date getDbDate();

   boolean checkPatientIsIn(String admissionNo, String hospitalized_count) throws Exception;

   String getCrbz(Medicalinformation medicalinformation);

   int checkUserPassWord(String userCode, String passWord);
}
