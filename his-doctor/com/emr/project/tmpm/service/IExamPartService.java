package com.emr.project.tmpm.service;

import com.emr.project.tmpm.domain.ExamPart;
import com.emr.project.tmpm.domain.vo.ExamPartVo;
import java.util.List;
import java.util.Map;

public interface IExamPartService {
   ExamPart selectExamPartById(Long id);

   List selectExamPartList(ExamPart examPart) throws Exception;

   Map selectExamPartMapList(ExamPartVo examPart) throws Exception;

   List selectExamPartMainList() throws Exception;

   int insertExamPart(ExamPart examPart);

   int updateExamPart(ExamPart examPart);

   int deleteExamPartByIds(Long[] ids);

   int deleteExamPartById(Long id);

   List selectByExamPartCdList(List examPartCdList) throws Exception;
}
