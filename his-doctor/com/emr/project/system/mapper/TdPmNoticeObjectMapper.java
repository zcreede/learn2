package com.emr.project.system.mapper;

import com.emr.project.system.domain.TdPmNoticeObject;
import java.util.List;

public interface TdPmNoticeObjectMapper {
   TdPmNoticeObject selectTdPmNoticeObjectById(Long id);

   List selectTdPmNoticeObjectList(TdPmNoticeObject tdPmNoticeObject);

   int insertTdPmNoticeObject(TdPmNoticeObject tdPmNoticeObject);

   int updateTdPmNoticeObject(TdPmNoticeObject tdPmNoticeObject);

   int deleteTdPmNoticeObjectById(Long id);

   int deleteTdPmNoticeObjectByIds(Long[] ids);
}
