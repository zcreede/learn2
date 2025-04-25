package com.emr.project.tool.gen.util;

import com.alibaba.fastjson.JSONObject;
import com.emr.common.utils.DateUtils;
import com.emr.common.utils.StringUtils;
import com.emr.project.tool.gen.domain.GenTable;
import com.emr.project.tool.gen.domain.GenTableColumn;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import org.apache.velocity.VelocityContext;

public class VelocityUtils {
   private static final String PROJECT_PATH = "main/java";
   private static final String MYBATIS_PATH = "main/resources/mybatis";
   private static final String DEFAULT_PARENT_MENU_ID = "3";

   public static VelocityContext prepareContext(GenTable genTable) {
      String moduleName = genTable.getModuleName();
      String businessName = genTable.getBusinessName();
      String packageName = genTable.getPackageName();
      String tplCategory = genTable.getTplCategory();
      String functionName = genTable.getFunctionName();
      VelocityContext velocityContext = new VelocityContext();
      velocityContext.put("tplCategory", genTable.getTplCategory());
      velocityContext.put("tableName", genTable.getTableName());
      velocityContext.put("functionName", StringUtils.isNotEmpty(functionName) ? functionName : "【请填写功能名称】");
      velocityContext.put("ClassName", genTable.getClassName());
      velocityContext.put("className", StringUtils.uncapitalize(genTable.getClassName()));
      velocityContext.put("moduleName", genTable.getModuleName());
      velocityContext.put("BusinessName", StringUtils.capitalize(genTable.getBusinessName()));
      velocityContext.put("businessName", genTable.getBusinessName());
      velocityContext.put("basePackage", getPackagePrefix(packageName));
      velocityContext.put("packageName", packageName);
      velocityContext.put("author", genTable.getFunctionAuthor());
      velocityContext.put("datetime", DateUtils.getDate());
      velocityContext.put("pkColumn", genTable.getPkColumn());
      velocityContext.put("importList", getImportList(genTable));
      velocityContext.put("permissionPrefix", getPermissionPrefix(moduleName, businessName));
      velocityContext.put("columns", genTable.getColumns());
      velocityContext.put("table", genTable);
      setMenuVelocityContext(velocityContext, genTable);
      if ("tree".equals(tplCategory)) {
         setTreeVelocityContext(velocityContext, genTable);
      }

      if ("sub".equals(tplCategory)) {
         setSubVelocityContext(velocityContext, genTable);
      }

      return velocityContext;
   }

   public static void setMenuVelocityContext(VelocityContext context, GenTable genTable) {
      String options = genTable.getOptions();
      JSONObject paramsObj = JSONObject.parseObject(options);
      String parentMenuId = getParentMenuId(paramsObj);
      context.put("parentMenuId", parentMenuId);
   }

   public static void setTreeVelocityContext(VelocityContext context, GenTable genTable) {
      String options = genTable.getOptions();
      JSONObject paramsObj = JSONObject.parseObject(options);
      String treeCode = getTreecode(paramsObj);
      String treeParentCode = getTreeParentCode(paramsObj);
      String treeName = getTreeName(paramsObj);
      context.put("treeCode", treeCode);
      context.put("treeParentCode", treeParentCode);
      context.put("treeName", treeName);
      context.put("expandColumn", getExpandColumn(genTable));
      if (paramsObj.containsKey("treeParentCode")) {
         context.put("tree_parent_code", paramsObj.getString("treeParentCode"));
      }

      if (paramsObj.containsKey("treeName")) {
         context.put("tree_name", paramsObj.getString("treeName"));
      }

   }

   public static void setSubVelocityContext(VelocityContext context, GenTable genTable) {
      GenTable subTable = genTable.getSubTable();
      String subTableName = genTable.getSubTableName();
      String subTableFkName = genTable.getSubTableFkName();
      String subClassName = genTable.getSubTable().getClassName();
      String subTableFkClassName = StringUtils.convertToCamelCase(subTableFkName);
      context.put("subTable", subTable);
      context.put("subTableName", subTableName);
      context.put("subTableFkName", subTableFkName);
      context.put("subTableFkClassName", subTableFkClassName);
      context.put("subTableFkclassName", StringUtils.uncapitalize(subTableFkClassName));
      context.put("subClassName", subClassName);
      context.put("subclassName", StringUtils.uncapitalize(subClassName));
      context.put("subImportList", getImportList(genTable.getSubTable()));
   }

   public static List getTemplateList(String tplCategory) {
      List<String> templates = new ArrayList();
      templates.add("vm/java/domain.java.vm");
      templates.add("vm/java/mapper.java.vm");
      templates.add("vm/java/service.java.vm");
      templates.add("vm/java/serviceImpl.java.vm");
      templates.add("vm/java/controller.java.vm");
      templates.add("vm/xml/mapper.xml.vm");
      templates.add("vm/sql/sql.vm");
      templates.add("vm/js/api.js.vm");
      if ("crud".equals(tplCategory)) {
         templates.add("vm/vue/index.vue.vm");
      } else if ("tree".equals(tplCategory)) {
         templates.add("vm/vue/index-tree.vue.vm");
      } else if ("sub".equals(tplCategory)) {
         templates.add("vm/vue/index.vue.vm");
         templates.add("vm/java/sub-domain.java.vm");
      }

      return templates;
   }

   public static String getFileName(String template, GenTable genTable) {
      String fileName = "";
      String packageName = genTable.getPackageName();
      String moduleName = genTable.getModuleName();
      String className = genTable.getClassName();
      String businessName = genTable.getBusinessName();
      String javaPath = "main/java/" + StringUtils.replace(packageName, ".", "/");
      String mybatisPath = "main/resources/mybatis/" + moduleName;
      String vuePath = "vue";
      if (template.contains("domain.java.vm")) {
         fileName = StringUtils.format("{}/domain/{}.java", javaPath, className);
      }

      if (template.contains("sub-domain.java.vm") && StringUtils.equals("sub", genTable.getTplCategory())) {
         fileName = StringUtils.format("{}/domain/{}.java", javaPath, genTable.getSubTable().getClassName());
      } else if (template.contains("mapper.java.vm")) {
         fileName = StringUtils.format("{}/mapper/{}Mapper.java", javaPath, className);
      } else if (template.contains("service.java.vm")) {
         fileName = StringUtils.format("{}/service/I{}Service.java", javaPath, className);
      } else if (template.contains("serviceImpl.java.vm")) {
         fileName = StringUtils.format("{}/service/impl/{}ServiceImpl.java", javaPath, className);
      } else if (template.contains("controller.java.vm")) {
         fileName = StringUtils.format("{}/controller/{}Controller.java", javaPath, className);
      } else if (template.contains("mapper.xml.vm")) {
         fileName = StringUtils.format("{}/{}Mapper.xml", mybatisPath, className);
      } else if (template.contains("sql.vm")) {
         fileName = businessName + "Menu.sql";
      } else if (template.contains("api.js.vm")) {
         fileName = StringUtils.format("{}/api/{}/{}.js", vuePath, moduleName, businessName);
      } else if (template.contains("index.vue.vm")) {
         fileName = StringUtils.format("{}/views/{}/{}/index.vue", vuePath, moduleName, businessName);
      } else if (template.contains("index-tree.vue.vm")) {
         fileName = StringUtils.format("{}/views/{}/{}/index.vue", vuePath, moduleName, businessName);
      }

      return fileName;
   }

   public static String getPackagePrefix(String packageName) {
      int lastIndex = packageName.lastIndexOf(".");
      String basePackage = StringUtils.substring(packageName, 0, lastIndex);
      return basePackage;
   }

   public static HashSet getImportList(GenTable genTable) {
      List<GenTableColumn> columns = genTable.getColumns();
      GenTable subGenTable = genTable.getSubTable();
      HashSet<String> importList = new HashSet();
      if (StringUtils.isNotNull(subGenTable)) {
         importList.add("java.util.List");
      }

      for(GenTableColumn column : columns) {
         if (!column.isSuperColumn() && "Date".equals(column.getJavaType())) {
            importList.add("java.util.Date");
            importList.add("com.fasterxml.jackson.annotation.JsonFormat");
         } else if (!column.isSuperColumn() && "BigDecimal".equals(column.getJavaType())) {
            importList.add("java.math.BigDecimal");
         }
      }

      return importList;
   }

   public static String getPermissionPrefix(String moduleName, String businessName) {
      return StringUtils.format("{}:{}", moduleName, businessName);
   }

   public static String getParentMenuId(JSONObject paramsObj) {
      return StringUtils.isNotEmpty((Map)paramsObj) && paramsObj.containsKey("parentMenuId") ? paramsObj.getString("parentMenuId") : "3";
   }

   public static String getTreecode(JSONObject paramsObj) {
      return paramsObj.containsKey("treeCode") ? StringUtils.toCamelCase(paramsObj.getString("treeCode")) : "";
   }

   public static String getTreeParentCode(JSONObject paramsObj) {
      return paramsObj.containsKey("treeParentCode") ? StringUtils.toCamelCase(paramsObj.getString("treeParentCode")) : "";
   }

   public static String getTreeName(JSONObject paramsObj) {
      return paramsObj.containsKey("treeName") ? StringUtils.toCamelCase(paramsObj.getString("treeName")) : "";
   }

   public static int getExpandColumn(GenTable genTable) {
      String options = genTable.getOptions();
      JSONObject paramsObj = JSONObject.parseObject(options);
      String treeName = paramsObj.getString("treeName");
      int num = 0;

      for(GenTableColumn column : genTable.getColumns()) {
         if (column.isList()) {
            ++num;
            String columnName = column.getColumnName();
            if (columnName.equals(treeName)) {
               break;
            }
         }
      }

      return num;
   }
}
