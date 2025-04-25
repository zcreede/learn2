package com.emr.project.esSearch.mapper;

import com.emr.project.esSearch.domain.vo.EmrFileVo;
import java.util.List;

public interface EmrFileMapper {
   List selectList(EmrFileVo emrFileVo);

   List selectRevokeEmrAllIds(String beginDate, String endDate);
}
