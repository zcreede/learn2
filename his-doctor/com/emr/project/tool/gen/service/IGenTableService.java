package com.emr.project.tool.gen.service;

import com.emr.project.tool.gen.domain.GenTable;
import java.util.List;
import java.util.Map;

public interface IGenTableService {
   List selectGenTableList(GenTable genTable);

   List selectDbTableList(GenTable genTable);

   List selectDbTableListByNames(String[] tableNames);

   List selectGenTableAll();

   GenTable selectGenTableById(Long id);

   void updateGenTable(GenTable genTable);

   void deleteGenTableByIds(Long[] tableIds);

   void importGenTable(List tableList);

   Map previewCode(Long tableId);

   byte[] downloadCode(String tableName);

   void generatorCode(String tableName);

   void synchDb(String tableName);

   byte[] downloadCode(String[] tableNames);

   void validateEdit(GenTable genTable);
}
