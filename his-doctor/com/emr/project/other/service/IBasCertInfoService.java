package com.emr.project.other.service;

import com.emr.project.other.domain.BasCertInfo;
import java.util.List;

public interface IBasCertInfoService {
   BasCertInfo selectBasCertInfoById(Long id);

   BasCertInfo selectBasCertInfoByCerSn(String cerSn);

   BasCertInfo selectBasCertInfoByEmployeenumber(String employeenumber);

   List selectBasCertInfoByEmployeenumberList(String[] employeenumbers);

   List selectBasCertInfoList(BasCertInfo basCertInfo);

   int insertBasCertInfo(BasCertInfo basCertInfo);

   int updateBasCertInfo(BasCertInfo basCertInfo);

   int deleteBasCertInfoByIds(Long[] ids);

   int deleteBasCertInfoById(Long id);

   void updateCertPicSn(String employeenumber, String certPic, String certSn);
}
