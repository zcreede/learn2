package com.emr.project.tool.gen.controller;

import com.emr.common.core.text.Convert;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.tool.gen.domain.GenTable;
import com.emr.project.tool.gen.domain.GenTableColumn;
import com.emr.project.tool.gen.service.IGenTableColumnService;
import com.emr.project.tool.gen.service.IGenTableService;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/tool/gen"})
public class GenController extends BaseController {
   @Autowired
   private IGenTableService genTableService;
   @Autowired
   private IGenTableColumnService genTableColumnService;

   @PreAuthorize("@ss.hasPermi('tool:gen:list')")
   @GetMapping({"/list"})
   public TableDataInfo genList(GenTable genTable) {
      this.startPage();
      List<GenTable> list = this.genTableService.selectGenTableList(genTable);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('tool:gen:query')")
   @GetMapping({"/{talbleId}"})
   public AjaxResult getInfo(@PathVariable Long talbleId) {
      GenTable table = this.genTableService.selectGenTableById(talbleId);
      List<GenTable> tables = this.genTableService.selectGenTableAll();
      List<GenTableColumn> list = this.genTableColumnService.selectGenTableColumnListByTableId(talbleId);
      Map<String, Object> map = new HashMap();
      map.put("info", table);
      map.put("rows", list);
      map.put("tables", tables);
      return AjaxResult.success((Object)map);
   }

   @PreAuthorize("@ss.hasPermi('tool:gen:list')")
   @GetMapping({"/db/list"})
   public TableDataInfo dataList(GenTable genTable) {
      this.startPage();
      List<GenTable> list = this.genTableService.selectDbTableList(genTable);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('tool:gen:list')")
   @GetMapping({"/column/{talbleId}"})
   public TableDataInfo columnList(Long tableId) {
      TableDataInfo dataInfo = new TableDataInfo();
      List<GenTableColumn> list = this.genTableColumnService.selectGenTableColumnListByTableId(tableId);
      dataInfo.setRows(list);
      dataInfo.setTotal((long)list.size());
      return dataInfo;
   }

   @PreAuthorize("@ss.hasPermi('tool:gen:import')")
   @Log(
      title = "代码生成",
      businessType = BusinessType.IMPORT
   )
   @PostMapping({"/importTable"})
   public AjaxResult importTableSave(String tables) {
      String[] tableNames = Convert.toStrArray(tables);
      List<GenTable> tableList = this.genTableService.selectDbTableListByNames(tableNames);
      this.genTableService.importGenTable(tableList);
      return AjaxResult.success();
   }

   @PreAuthorize("@ss.hasPermi('tool:gen:edit')")
   @Log(
      title = "代码生成",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult editSave(@Validated @RequestBody GenTable genTable) {
      this.genTableService.validateEdit(genTable);
      this.genTableService.updateGenTable(genTable);
      return AjaxResult.success();
   }

   @PreAuthorize("@ss.hasPermi('tool:gen:remove')")
   @Log(
      title = "代码生成",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{tableIds}"})
   public AjaxResult remove(@PathVariable Long[] tableIds) {
      this.genTableService.deleteGenTableByIds(tableIds);
      return AjaxResult.success();
   }

   @PreAuthorize("@ss.hasPermi('tool:gen:preview')")
   @GetMapping({"/preview/{tableId}"})
   public AjaxResult preview(@PathVariable("tableId") Long tableId) throws IOException {
      Map<String, String> dataMap = this.genTableService.previewCode(tableId);
      return AjaxResult.success((Object)dataMap);
   }

   @PreAuthorize("@ss.hasPermi('tool:gen:code')")
   @Log(
      title = "代码生成",
      businessType = BusinessType.GENCODE
   )
   @GetMapping({"/download/{tableName}"})
   public void download(HttpServletResponse response, @PathVariable("tableName") String tableName) throws IOException {
      byte[] data = this.genTableService.downloadCode(tableName);
      this.genCode(response, data);
   }

   @PreAuthorize("@ss.hasPermi('tool:gen:code')")
   @Log(
      title = "代码生成",
      businessType = BusinessType.GENCODE
   )
   @GetMapping({"/genCode/{tableName}"})
   public AjaxResult genCode(@PathVariable("tableName") String tableName) {
      this.genTableService.generatorCode(tableName);
      return AjaxResult.success();
   }

   @PreAuthorize("@ss.hasPermi('tool:gen:edit')")
   @Log(
      title = "代码生成",
      businessType = BusinessType.UPDATE
   )
   @GetMapping({"/synchDb/{tableName}"})
   public AjaxResult synchDb(@PathVariable("tableName") String tableName) {
      this.genTableService.synchDb(tableName);
      return AjaxResult.success();
   }

   @PreAuthorize("@ss.hasPermi('tool:gen:code')")
   @Log(
      title = "代码生成",
      businessType = BusinessType.GENCODE
   )
   @GetMapping({"/batchGenCode"})
   public void batchGenCode(HttpServletResponse response, String tables) throws IOException {
      String[] tableNames = Convert.toStrArray(tables);
      byte[] data = this.genTableService.downloadCode(tableNames);
      this.genCode(response, data);
   }

   private void genCode(HttpServletResponse response, byte[] data) throws IOException {
      response.reset();
      response.addHeader("Access-Control-Allow-Origin", "*");
      response.addHeader("Access-Control-Expose-Headers", "Content-Disposition");
      response.setHeader("Content-Disposition", "attachment; filename=\"ruoyi.zip\"");
      response.addHeader("Content-Length", "" + data.length);
      response.setContentType("application/octet-stream; charset=UTF-8");
      IOUtils.write(data, response.getOutputStream());
   }
}
