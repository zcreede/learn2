package com.emr.project.emr.mapper;

import com.emr.project.emr.domain.vo.EmrTimeoutCaseVo;
import java.util.List;

public interface EmrTimeoutCaseMapper {
   List getTimeoutCaseList(EmrTimeoutCaseVo emrTimeoutCaseVo);
}
