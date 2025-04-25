package com.emr.project.qc.mapper;

import com.emr.project.qc.domain.QcRuleIterEmr;
import com.emr.project.qc.domain.vo.QcRuleIterEmrVo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface QcRuleIterEmrMapper {
   QcRuleIterEmr selectQcRuleIterEmrById(Long id);

   List selectQcRuleIterEmrList(QcRuleIterEmrVo qcRuleIterEmrVo);

   void insertQcRuleIterEmr(QcRuleIterEmr qcRuleIterEmr);

   void updateQcRuleIterEmr(QcRuleIterEmr qcRuleIterEmr);

   void deleteQcRuleIterEmrById(Long id);

   int deleteQcRuleIterEmrByIds(Long[] ids);

   List selectListByEmrTypeList(@Param("emrTypeCodeList") List emrTypeCodeList);
}
