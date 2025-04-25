package com.emr.project.tmpl.mapper;

import com.emr.project.tmpl.domain.ElemDate;
import java.util.List;

public interface ElemDateMapper {
   ElemDate selectElemDateById(Long id);

   List selectElemDateList(ElemDate elemDate);

   List selectElemDateListByTempId(Long tempId);

   int insertElemDate(ElemDate elemDate);

   void insertElemDateList(List elemDateList);

   void insertElemDateStandardList(List elemDateList);

   int updateElemDate(ElemDate elemDate);

   int deleteElemDateById(Long id);

   int deleteElemDateByIds(Long[] ids);

   int deleteElemDateByTempId(Long tempId);

   int deleteElemDateStandardByTempId(Long tempId);
}
