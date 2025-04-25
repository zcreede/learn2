package com.emr.project.system.service;

import com.emr.project.system.domain.TdPmNoticeObject;
import java.util.List;

public interface ITdPmNoticeObjectService {
   TdPmNoticeObject selectTdPmNoticeObjectById(Long id);

   List selectTdPmNoticeObjectList(TdPmNoticeObject tdPmNoticeObject);

   int insertTdPmNoticeObject(TdPmNoticeObject tdPmNoticeObject);

   int updateTdPmNoticeObject(TdPmNoticeObject tdPmNoticeObject);

   int deleteTdPmNoticeObjectByIds(Long[] ids);

   int deleteTdPmNoticeObjectById(Long id);
}
