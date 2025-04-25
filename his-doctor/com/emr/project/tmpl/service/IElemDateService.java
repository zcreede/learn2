package com.emr.project.tmpl.service;

import com.emr.project.tmpl.domain.ElemDate;
import java.util.List;

public interface IElemDateService {
   ElemDate selectElemDateById(Long id);

   List selectElemDateList(ElemDate elemDate);

   List selectElemDateListByTempId(Long tempId);

   int insertElemDate(ElemDate elemDate);

   void insertElemDateList(List elemDateList) throws Exception;

   void insertElemDateStandardList(List elemDateList) throws Exception;

   int updateElemDate(ElemDate elemDate);

   int deleteElemDateByIds(Long[] ids);

   int deleteElemDateById(Long id);

   int deleteElemDateByTempId(Long tempId);

   int deleteElemDateStandardByTempId(Long tempId);
}
