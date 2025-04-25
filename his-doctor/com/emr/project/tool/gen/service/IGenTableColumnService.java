package com.emr.project.tool.gen.service;

import com.emr.project.tool.gen.domain.GenTableColumn;
import java.util.List;

public interface IGenTableColumnService {
   List selectDbTableColumnsByName(String tableName);

   List selectGenTableColumnListByTableId(Long tableId);

   int insertGenTableColumn(GenTableColumn genTableColumn);

   int updateGenTableColumn(GenTableColumn genTableColumn);

   int deleteGenTableColumnByIds(String ids);
}
