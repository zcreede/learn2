package com.emr.project.pat.mapper;

import com.emr.project.pat.domain.OperatInfo;
import java.util.List;

public interface OperatInfoMapper {
   OperatInfo selectOperatInfoById(Long id);

   List selectOperatInfoList(OperatInfo operatInfo);

   int insertOperatInfo(OperatInfo operatInfo);

   int updateOperatInfo(OperatInfo operatInfo);

   int deleteOperatInfoById(Long id);

   int deleteOperatInfoByIds(Long[] ids);
}
