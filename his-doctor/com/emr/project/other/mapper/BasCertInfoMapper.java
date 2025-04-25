package com.emr.project.other.mapper;

import com.emr.project.other.domain.BasCertInfo;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface BasCertInfoMapper {
   BasCertInfo selectBasCertInfoById(Long id);

   BasCertInfo selectBasCertInfoByCerSn(@Param("cerSn") String cerSn);

   BasCertInfo selectBasCertInfo(BasCertInfo basCertInfo);

   List selectBasCertInfoList(BasCertInfo basCertInfo);

   List selectBasCertInfoListByCodes(String[] employeenumbers);

   int insertBasCertInfo(BasCertInfo basCertInfo);

   int updateBasCertInfo(BasCertInfo basCertInfo);

   void updateCertPicSn(BasCertInfo basCertInfo);

   int deleteBasCertInfoById(Long id);

   int deleteBasCertInfoByIds(Long[] ids);

   void deleteBasCertInfoByDocCd(String docCd);

   void insertMap(Map map);

   List selectBasCertInfoByList(@Param("list") List staffCodeList);
}
