package com.emr.project.pat.service;

import com.emr.project.pat.domain.OperatInfo;
import java.util.List;

public interface IOperatInfoService {
   OperatInfo selectOperatInfoById(Long id);

   List selectOperatInfoList(OperatInfo operatInfo) throws Exception;

   int insertOperatInfo(OperatInfo operatInfo);

   int updateOperatInfo(OperatInfo operatInfo);

   int deleteOperatInfoByIds(Long[] ids);

   int deleteOperatInfoById(Long id);
}
