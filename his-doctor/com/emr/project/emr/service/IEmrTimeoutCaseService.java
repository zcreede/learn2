package com.emr.project.emr.service;

import com.emr.project.emr.domain.vo.EmrTimeoutCaseVo;
import java.util.List;

public interface IEmrTimeoutCaseService {
   List getTimeoutCaseList(EmrTimeoutCaseVo emrTimeoutCaseVo);
}
