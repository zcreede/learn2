package com.emr.project.emr.service;

import com.emr.project.emr.domain.EmrElemstoe;
import com.emr.project.emr.domain.Index;
import com.emr.project.emr.domain.vo.IndexVo;
import com.emr.project.system.domain.SysUser;
import java.util.List;
import java.util.Map;

public interface IEmrElemstoeService {
   EmrElemstoe selectEmrElemstoeById(Long id);

   List selectEmrElemstoeList(EmrElemstoe emrElemstoe);

   int insertEmrElemstoe(EmrElemstoe emrElemstoe);

   int updateEmrElemstoe(EmrElemstoe emrElemstoe);

   int deleteEmrElemstoeByIds(Long[] ids);

   int deleteEmrElemstoeById(Long id);

   void deleteEmrElemstoeByMrFileId(Long mrFileId);

   void addList(List emrElemstoeList);

   void indexSaveEmrElemstoes(String mrType, Long mrFileId, Index index, SysUser user, List elemAttriVoList, Map elemBase64Map) throws Exception;

   List selectListByDate(String beginDate, String endDate) throws Exception;

   List selectEmrElemstoeListByPatientIdAndTypeList(String patientId, List typeList);

   List selectTertiaryIndexList(IndexVo indexVo) throws Exception;
}
