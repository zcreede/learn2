package com.emr.project.emr.mapper;

import com.emr.project.emr.domain.EmrSetDetail;
import com.emr.project.emr.domain.vo.EmrSetDetailVo;
import java.util.List;

public interface EmrSetDetailMapper {
   EmrSetDetail selectEmrSetDetailById(Long id);

   List selectEmrSetDetailList(EmrSetDetail emrSetDetail);

   int insertEmrSetDetail(EmrSetDetail emrSetDetail);

   int updateEmrSetDetail(EmrSetDetail emrSetDetail);

   void deleteEmrSetDetailById(Long id);

   int deleteEmrSetDetailByIds(Long[] ids);

   void deleteEmrSetDetailSetCd(String setCd);

   void insertEmrSetDetailList(List emrSetDetailList);

   List selectEmrSetDetailCheckedList(EmrSetDetailVo emrSetDetail);

   List selectEmrCheckedSetTree(EmrSetDetailVo emrSetDetail);
}
