package com.emr.project.operation.service;

import com.emr.project.operation.domain.TmBsAccountThird;
import com.emr.project.operation.domain.vo.TmBsAccountThirdProjectVo;
import java.util.List;

public interface TmBsAccountThirdService {
   List selectThirdAndProjectByCodeList(List list) throws Exception;

   TmBsAccountThirdProjectVo selectThirdAndProjectByCode(String code) throws Exception;

   List queryTmBsAccountThirdList(TmBsAccountThird tmBsAccountThird) throws Exception;
}
