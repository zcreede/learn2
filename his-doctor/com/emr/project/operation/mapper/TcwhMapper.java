package com.emr.project.operation.mapper;

import com.emr.project.operation.domain.Tcwh;
import java.util.List;
import java.util.Map;

public interface TcwhMapper {
   int deleteByPrimaryKey(Long id);

   int insert(Tcwh record);

   int insertSelective(Tcwh record);

   Tcwh selectByPrimaryKey(Long id);

   int updateByPrimaryKeySelective(Tcwh record);

   int updateByPrimaryKey(Tcwh record);

   List listPage(Map params);

   int listPageCount(Map params);

   List selectListByConn(Tcwh record);

   int insertList(List list);

   int updateList(List list);

   int delByIdList(List list);

   List selectListFromMx();

   List selectByShareClass(Tcwh record);
}
