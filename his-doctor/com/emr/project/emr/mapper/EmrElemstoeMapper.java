package com.emr.project.emr.mapper;

import com.emr.project.emr.domain.EmrElemstoe;
import com.emr.project.emr.domain.vo.IndexVo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EmrElemstoeMapper {
   EmrElemstoe selectEmrElemstoeById(Long id);

   List selectEmrElemstoeList(EmrElemstoe emrElemstoe);

   int insertEmrElemstoe(EmrElemstoe emrElemstoe);

   int updateEmrElemstoe(EmrElemstoe emrElemstoe);

   int deleteEmrElemstoeById(Long id);

   int deleteEmrElemstoeByIds(Long[] ids);

   void deleteEmrElemstoeByMrFileId(Long mrFileId);

   void insertList(List list);

   List selectListByDate(String beginDate, String endDate);

   List selectEmrElemstoeListByPatientIdAndTypeList(@Param("patientId") String patientId, @Param("list") List typeList);

   List selectTertiaryIndexList(IndexVo indexVo);
}
