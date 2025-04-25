package com.emr.project.pat.service;

import com.emr.project.pat.domain.Otherinfo;
import com.emr.project.pat.domain.req.KeyPatientsReq;
import java.util.List;

public interface IOtherinfoService {
   Otherinfo selectOtherinfoById(Long patientId);

   List selectOtherinfoList(Otherinfo otherinfo);

   int insertOtherinfo(Otherinfo otherinfo);

   int updateOtherinfo(Otherinfo otherinfo);

   int deleteOtherinfoByIds(Long[] patientIds);

   int deleteOtherinfoById(Long patientId);

   List selectKeyPatientsList(KeyPatientsReq req) throws Exception;

   List selectKeyPatientsDetail(KeyPatientsReq req) throws Exception;
}
