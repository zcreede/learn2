package com.emr.project.docOrder.mapper;

import com.emr.project.docOrder.domain.TdPaItemDocQuery;
import java.util.List;

public interface TdPaItemDocQueryMapper {
   TdPaItemDocQuery selectTdPaItemDocQueryById(Long id);

   List selectTdPaItemDocQueryList(TdPaItemDocQuery tdPaItemDocQuery);

   int insertTdPaItemDocQuery(TdPaItemDocQuery tdPaItemDocQuery);

   int updateTdPaItemDocQuery(TdPaItemDocQuery tdPaItemDocQuery);

   int deleteTdPaItemDocQueryById(Long id);

   int deleteTdPaItemDocQueryByIds(Long[] ids);
}
