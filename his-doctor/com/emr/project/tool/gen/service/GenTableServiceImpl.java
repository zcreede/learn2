package com.emr.project.tool.gen.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.emr.common.exception.CustomException;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.common.utils.StringUtils;
import com.emr.project.tool.gen.domain.GenTable;
import com.emr.project.tool.gen.domain.GenTableColumn;
import com.emr.project.tool.gen.mapper.GenTableColumnMapper;
import com.emr.project.tool.gen.mapper.GenTableMapper;
import com.emr.project.tool.gen.util.GenUtils;
import com.emr.project.tool.gen.util.VelocityInitializer;
import com.emr.project.tool.gen.util.VelocityUtils;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GenTableServiceImpl implements IGenTableService {
   private static final Logger log = LoggerFactory.getLogger(GenTableServiceImpl.class);
   @Autowired
   private GenTableMapper genTableMapper;
   @Autowired
   private GenTableColumnMapper genTableColumnMapper;

   public GenTable selectGenTableById(Long id) {
      GenTable genTable = this.genTableMapper.selectGenTableById(id);
      this.setTableFromOptions(genTable);
      return genTable;
   }

   public List selectGenTableList(GenTable genTable) {
      return this.genTableMapper.selectGenTableList(genTable);
   }

   public List selectDbTableList(GenTable genTable) {
      return this.genTableMapper.selectDbTableList(genTable);
   }

   public List selectDbTableListByNames(String[] tableNames) {
      return this.genTableMapper.selectDbTableListByNames(tableNames);
   }

   public List selectGenTableAll() {
      return this.genTableMapper.selectGenTableAll();
   }

   @Transactional
   public void updateGenTable(GenTable genTable) {
      String options = JSON.toJSONString(genTable.getParams());
      genTable.setOptions(options);
      int row = this.genTableMapper.updateGenTable(genTable);
      if (row > 0) {
         for(GenTableColumn cenTableColumn : genTable.getColumns()) {
            this.genTableColumnMapper.updateGenTableColumn(cenTableColumn);
         }
      }

   }

   @Transactional
   public void deleteGenTableByIds(Long[] tableIds) {
      this.genTableMapper.deleteGenTableByIds(tableIds);
      this.genTableColumnMapper.deleteGenTableColumnByIds(tableIds);
   }

   @Transactional
   public void importGenTable(List tableList) {
      String operName = SecurityUtils.getUsername();

      try {
         for(GenTable table : tableList) {
            String tableName = table.getTableName();
            GenUtils.initTable(table, operName);
            table.setTableId(SnowIdUtils.uniqueLong());
            int row = this.genTableMapper.insertGenTable(table);
            if (row > 0) {
               for(GenTableColumn column : this.genTableColumnMapper.selectDbTableColumnsByName(tableName)) {
                  GenUtils.initColumnField(column, table);
                  this.genTableColumnMapper.insertGenTableColumn(column);
               }
            }
         }

      } catch (Exception e) {
         throw new CustomException("导入失败：" + e.getMessage());
      }
   }

   public Map previewCode(Long tableId) {
      Map<String, String> dataMap = new LinkedHashMap();
      GenTable table = this.genTableMapper.selectGenTableById(tableId);
      this.setSubTable(table);
      this.setPkColumn(table);
      VelocityInitializer.initVelocity();
      VelocityContext context = VelocityUtils.prepareContext(table);

      for(String template : VelocityUtils.getTemplateList(table.getTplCategory())) {
         StringWriter sw = new StringWriter();
         Template tpl = Velocity.getTemplate(template, "UTF-8");
         tpl.merge(context, sw);
         dataMap.put(template, sw.toString());
      }

      return dataMap;
   }

   public byte[] downloadCode(String tableName) {
      ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
      ZipOutputStream zip = new ZipOutputStream(outputStream);
      this.generatorCode(tableName, zip);
      IOUtils.closeQuietly(zip);
      return outputStream.toByteArray();
   }

   public void generatorCode(String tableName) {
      GenTable table = this.genTableMapper.selectGenTableByName(tableName);
      long menuId = this.genTableMapper.selectMenuId();
      table.setMenuId(menuId);
      this.setSubTable(table);
      this.setPkColumn(table);
      VelocityInitializer.initVelocity();
      VelocityContext context = VelocityUtils.prepareContext(table);

      for(String template : VelocityUtils.getTemplateList(table.getTplCategory())) {
         if (!StringUtils.containsAny(template, new CharSequence[]{"sql.vm", "api.js.vm", "index.vue.vm", "index-tree.vue.vm"})) {
            StringWriter sw = new StringWriter();
            Template tpl = Velocity.getTemplate(template, "UTF-8");
            tpl.merge(context, sw);

            try {
               String path = getGenPath(table, template);
               FileUtils.writeStringToFile(new File(path), sw.toString(), "UTF-8");
            } catch (IOException var12) {
               throw new CustomException("渲染模板失败，表名：" + table.getTableName());
            }
         }
      }

   }

   @Transactional
   public void synchDb(String tableName) {
      GenTable table = this.genTableMapper.selectGenTableByName(tableName);
      List<GenTableColumn> tableColumns = table.getColumns();
      List<String> tableColumnNames = (List)tableColumns.stream().map(GenTableColumn::getColumnName).collect(Collectors.toList());
      List<GenTableColumn> dbTableColumns = this.genTableColumnMapper.selectDbTableColumnsByName(tableName);
      if (StringUtils.isEmpty((Collection)dbTableColumns)) {
         throw new CustomException("同步数据失败，原表结构不存在");
      } else {
         List<String> dbTableColumnNames = (List)dbTableColumns.stream().map(GenTableColumn::getColumnName).collect(Collectors.toList());
         dbTableColumns.forEach((column) -> {
            if (!tableColumnNames.contains(column.getColumnName())) {
               GenUtils.initColumnField(column, table);
               this.genTableColumnMapper.insertGenTableColumn(column);
            }

         });
         List<GenTableColumn> delColumns = (List)tableColumns.stream().filter((column) -> !dbTableColumnNames.contains(column.getColumnName())).collect(Collectors.toList());
         if (StringUtils.isNotEmpty((Collection)delColumns)) {
            this.genTableColumnMapper.deleteGenTableColumns(delColumns);
         }

      }
   }

   public byte[] downloadCode(String[] tableNames) {
      ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
      ZipOutputStream zip = new ZipOutputStream(outputStream);

      for(String tableName : tableNames) {
         this.generatorCode(tableName, zip);
      }

      IOUtils.closeQuietly(zip);
      return outputStream.toByteArray();
   }

   private void generatorCode(String tableName, ZipOutputStream zip) {
      GenTable table = this.genTableMapper.selectGenTableByName(tableName);
      long menuId = this.genTableMapper.selectMenuId();
      table.setMenuId(menuId);
      this.setSubTable(table);
      this.setPkColumn(table);
      VelocityInitializer.initVelocity();
      VelocityContext context = VelocityUtils.prepareContext(table);

      for(String template : VelocityUtils.getTemplateList(table.getTplCategory())) {
         StringWriter sw = new StringWriter();
         Template tpl = Velocity.getTemplate(template, "UTF-8");
         tpl.merge(context, sw);

         try {
            zip.putNextEntry(new ZipEntry(VelocityUtils.getFileName(template, table)));
            IOUtils.write(sw.toString(), zip, "UTF-8");
            IOUtils.closeQuietly(sw);
            zip.flush();
            zip.closeEntry();
         } catch (IOException e) {
            log.error("渲染模板失败，表名：" + table.getTableName(), e);
         }
      }

   }

   public void validateEdit(GenTable genTable) {
      if ("tree".equals(genTable.getTplCategory())) {
         String options = JSON.toJSONString(genTable.getParams());
         JSONObject paramsObj = JSONObject.parseObject(options);
         if (StringUtils.isEmpty(paramsObj.getString("treeCode"))) {
            throw new CustomException("树编码字段不能为空");
         }

         if (StringUtils.isEmpty(paramsObj.getString("treeParentCode"))) {
            throw new CustomException("树父编码字段不能为空");
         }

         if (StringUtils.isEmpty(paramsObj.getString("treeName"))) {
            throw new CustomException("树名称字段不能为空");
         }
      } else if ("sub".equals(genTable.getTplCategory())) {
         if (StringUtils.isEmpty(genTable.getSubTableName())) {
            throw new CustomException("关联子表的表名不能为空");
         }

         if (StringUtils.isEmpty(genTable.getSubTableFkName())) {
            throw new CustomException("子表关联的外键名不能为空");
         }
      }

   }

   public void setPkColumn(GenTable table) {
      for(GenTableColumn column : table.getColumns()) {
         if (column.isPk()) {
            table.setPkColumn(column);
            break;
         }
      }

      if (StringUtils.isNull(table.getPkColumn())) {
         table.setPkColumn((GenTableColumn)table.getColumns().get(0));
      }

      if ("sub".equals(table.getTplCategory())) {
         for(GenTableColumn column : table.getSubTable().getColumns()) {
            if (column.isPk()) {
               table.getSubTable().setPkColumn(column);
               break;
            }
         }

         if (StringUtils.isNull(table.getSubTable().getPkColumn())) {
            table.getSubTable().setPkColumn((GenTableColumn)table.getSubTable().getColumns().get(0));
         }
      }

   }

   public void setSubTable(GenTable table) {
      String subTableName = table.getSubTableName();
      if (StringUtils.isNotEmpty(subTableName)) {
         table.setSubTable(this.genTableMapper.selectGenTableByName(subTableName));
      }

   }

   public void setTableFromOptions(GenTable genTable) {
      JSONObject paramsObj = JSONObject.parseObject(genTable.getOptions());
      if (StringUtils.isNotNull(paramsObj)) {
         String treeCode = paramsObj.getString("treeCode");
         String treeParentCode = paramsObj.getString("treeParentCode");
         String treeName = paramsObj.getString("treeName");
         String parentMenuId = paramsObj.getString("parentMenuId");
         String parentMenuName = paramsObj.getString("parentMenuName");
         genTable.setTreeCode(treeCode);
         genTable.setTreeParentCode(treeParentCode);
         genTable.setTreeName(treeName);
         genTable.setParentMenuId(parentMenuId);
         genTable.setParentMenuName(parentMenuName);
      }

   }

   public static String getGenPath(GenTable table, String template) {
      String genPath = table.getGenPath();
      return StringUtils.equals(genPath, "/") ? System.getProperty("user.dir") + File.separator + "src" + File.separator + VelocityUtils.getFileName(template, table) : genPath + File.separator + VelocityUtils.getFileName(template, table);
   }
}
