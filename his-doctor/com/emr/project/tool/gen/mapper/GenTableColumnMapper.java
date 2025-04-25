package com.emr.project.tool.gen.mapper;

import com.emr.project.tool.gen.domain.GenTableColumn;
import java.util.List;

public interface GenTableColumnMapper {
   List selectDbTableColumnsByName(String tableName);

   List selectGenTableColumnListByTableId(Long tableId);

   int insertGenTableColumn(GenTableColumn genTableColumn);

   int updateGenTableColumn(GenTableColumn genTableColumn);

   int deleteGenTableColumns(List genTableColumns);

   int deleteGenTableColumnByIds(Long[] ids);
}
