package com.emr.project.tool.gen.service;

import com.emr.common.core.text.Convert;
import com.emr.project.tool.gen.domain.GenTableColumn;
import com.emr.project.tool.gen.mapper.GenTableColumnMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenTableColumnServiceImpl implements IGenTableColumnService {
   @Autowired
   private GenTableColumnMapper genTableColumnMapper;

   public List selectDbTableColumnsByName(String tableName) {
      return this.genTableColumnMapper.selectDbTableColumnsByName(tableName);
   }

   public List selectGenTableColumnListByTableId(Long tableId) {
      return this.genTableColumnMapper.selectGenTableColumnListByTableId(tableId);
   }

   public int insertGenTableColumn(GenTableColumn genTableColumn) {
      return this.genTableColumnMapper.insertGenTableColumn(genTableColumn);
   }

   public int updateGenTableColumn(GenTableColumn genTableColumn) {
      return this.genTableColumnMapper.updateGenTableColumn(genTableColumn);
   }

   public int deleteGenTableColumnByIds(String ids) {
      return this.genTableColumnMapper.deleteGenTableColumnByIds(Convert.toLongArray(ids));
   }
}
