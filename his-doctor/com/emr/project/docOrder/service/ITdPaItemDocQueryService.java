package com.emr.project.docOrder.service;

import com.emr.project.docOrder.domain.TdPaItemDocQuery;
import java.util.List;

public interface ITdPaItemDocQueryService {
   TdPaItemDocQuery selectTdPaItemDocQueryById(Long id);

   List selectTdPaItemDocQueryList(TdPaItemDocQuery tdPaItemDocQuery);

   void insertTdPaItemDocQuery(TdPaItemDocQuery tdPaItemDocQuery) throws Exception;

   int updateTdPaItemDocQuery(TdPaItemDocQuery tdPaItemDocQuery);

   int deleteTdPaItemDocQueryByIds(Long[] ids);

   int deleteTdPaItemDocQueryById(Long id);
}
