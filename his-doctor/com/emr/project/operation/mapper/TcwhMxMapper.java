package com.emr.project.operation.mapper;

import com.emr.project.operation.domain.TcwhMx;
import java.util.List;
import java.util.Map;

public interface TcwhMxMapper {
   int deleteByPrimaryKey(Long id);

   int insert(TcwhMx record);

   int insertSelective(TcwhMx record);

   TcwhMx selectByPrimaryKey(Long id);

   int updateByPrimaryKeySelective(TcwhMx record);

   int updateByPrimaryKey(TcwhMx record);

   List listPage(Map params);

   int listPageCount(Map params);

   int insertList(List list);

   int updateList(List list);

   int delByIdList(List list);

   List selectListByConn(TcwhMx record);

   List selectListByNo(TcwhMx record);
}
