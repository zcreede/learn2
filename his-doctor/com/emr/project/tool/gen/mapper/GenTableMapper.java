package com.emr.project.tool.gen.mapper;

import com.emr.project.tool.gen.domain.GenTable;
import java.util.List;

public interface GenTableMapper {
   List selectGenTableList(GenTable genTable);

   List selectDbTableList(GenTable genTable);

   List selectDbTableListByNames(String[] tableNames);

   List selectGenTableAll();

   GenTable selectGenTableById(Long id);

   GenTable selectGenTableByName(String tableName);

   long selectMenuId();

   int insertGenTable(GenTable genTable);

   int updateGenTable(GenTable genTable);

   int deleteGenTableByIds(Long[] ids);
}
