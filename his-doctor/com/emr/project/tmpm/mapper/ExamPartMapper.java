package com.emr.project.tmpm.mapper;

import com.emr.project.tmpm.domain.ExamPart;
import com.emr.project.tmpm.domain.vo.ExamPartVo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExamPartMapper {
   ExamPart selectExamPartById(Long id);

   List selectExamPartList(ExamPart examPart);

   int insertExamPart(ExamPart examPart);

   int updateExamPart(ExamPart examPart);

   int deleteExamPartById(Long id);

   int deleteExamPartByIds(Long[] ids);

   List selectExamPartMainList();

   List selectExamPartListBySexList(ExamPartVo examPart);

   List selectByExamPartCdList(@Param("examPartCdList") List examPartCdList);
}
