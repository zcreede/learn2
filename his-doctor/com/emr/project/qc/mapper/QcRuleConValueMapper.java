package com.emr.project.qc.mapper;

import com.emr.project.qc.domain.QcRuleConValue;
import com.emr.project.qc.domain.vo.QcRuleConValueVo;
import java.util.List;

public interface QcRuleConValueMapper {
   QcRuleConValue selectQcRuleConValueById(Long id);

   List selectQcRuleConValueList(QcRuleConValueVo qcRuleConValueVo);

   int insertQcRuleConValue(QcRuleConValue qcRuleConValue);

   int updateQcRuleConValue(QcRuleConValue qcRuleConValue);

   int deleteQcRuleConValueById(Long id);

   int deleteQcRuleConValueByIds(Long[] ids);
}
