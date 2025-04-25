package com.emr.project.emr.service;

import com.emr.project.emr.domain.EmrSetDetail;
import com.emr.project.emr.domain.vo.EmrSetDetailVo;
import java.util.List;

public interface IEmrSetDetailService {
   EmrSetDetail selectEmrSetDetailById(Long id);

   List selectEmrSetDetailList(EmrSetDetail emrSetDetail) throws Exception;

   int insertEmrSetDetail(EmrSetDetail emrSetDetail);

   void insertEmrSetDetailList(List emrSetDetailList) throws Exception;

   int updateEmrSetDetail(EmrSetDetail emrSetDetail);

   int deleteEmrSetDetailByIds(Long[] ids);

   void deleteEmrSetDetailById(Long id) throws Exception;

   void deleteEmrSetDetailSetCd(String setCd) throws Exception;

   void saveEmrSetDetailList(List details, String setCd) throws Exception;

   List selectEmrSetDetailTreeList(EmrSetDetailVo emrSetDetail) throws Exception;

   List selectEmrCheckedSetTree(EmrSetDetailVo emrSetDetail) throws Exception;
}
