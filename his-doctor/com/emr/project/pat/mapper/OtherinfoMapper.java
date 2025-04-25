package com.emr.project.pat.mapper;

import com.emr.project.pat.domain.Otherinfo;
import com.emr.project.pat.domain.req.KeyPatientsReq;
import java.util.List;

public interface OtherinfoMapper {
   Otherinfo selectOtherinfoById(Long patientId);

   List selectOtherinfoList(Otherinfo otherinfo);

   int insertOtherinfo(Otherinfo otherinfo);

   int updateOtherinfo(Otherinfo otherinfo);

   int deleteOtherinfoById(Long patientId);

   int deleteOtherinfoByIds(Long[] patientIds);

   List selectDeptTotalCount(KeyPatientsReq req);

   List selectOtherinfoListByDeptList(KeyPatientsReq req);

   List selectKeyPatientsDetail(KeyPatientsReq req);
}
