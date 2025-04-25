package com.emr.project.mrhp.controller;

import com.emr.common.utils.StringUtils;
import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.mrhp.domain.MrHpDrawMain;
import com.emr.project.mrhp.domain.req.MrHpDrawMainReq;
import com.emr.project.mrhp.domain.resp.MrHpDrawMainResp;
import com.emr.project.mrhp.domain.vo.MrHpDrawMainTableVo;
import com.emr.project.mrhp.service.IMrHpDrawMainService;
import com.emr.project.webservice.domain.resp.WebServiceGeneralResp;
import io.swagger.annotations.ApiOperation;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/mrhp/main"})
public class MrHpDrawMainController extends BaseController {
   @Autowired
   private IMrHpDrawMainService mrHpDrawMainService;
   @Value("${spring.datasource.type}")
   private String type;
   @Value("${spring.datasource.druid.master.url}")
   private String url;
   @Value("${spring.datasource.druid.master.username}")
   private String user;
   @Value("${spring.datasource.druid.master.password}")
   private String password;

   @PreAuthorize("@ss.hasPermi('mrhp:main:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(MrHpDrawMain mrHpDrawMain) {
      TableDataInfo tableDataInfo = new TableDataInfo();

      try {
         this.startPage();
         List<MrHpDrawMain> list = this.mrHpDrawMainService.selectMrHpDrawMainList(mrHpDrawMain);
         tableDataInfo = this.getDataTable(list);
      } catch (Exception var4) {
         this.log.error("列表查询出现异常");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasPermi('mrhp:main:list')")
   @GetMapping({"/tableFieldList"})
   public AjaxResult tableList(String tableName) {
      AjaxResult ajaxResult = AjaxResult.success();
      Boolean flag = true;
      if (StringUtils.isBlank(tableName)) {
         flag = false;
         ajaxResult = AjaxResult.error("请传入表名");
      }

      Connection connection = null;
      ResultSet resultSet = null;

      try {
         if (flag) {
            List<MrHpDrawMainTableVo> mrHpDrawMainTableVoList = new ArrayList();
            Class.forName(this.type);
            connection = DriverManager.getConnection(this.url, this.user, this.password);
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            resultSet = databaseMetaData.getColumns((String)null, (String)null, tableName, "%");

            while(resultSet.next()) {
               MrHpDrawMainTableVo mrHpDrawMainTableVo = new MrHpDrawMainTableVo();
               String columnName = resultSet.getString("COLUMN_NAME");
               mrHpDrawMainTableVo.setFieldCd(columnName);
               String columnType = resultSet.getString("TYPE_NAME");
               String columnComment = resultSet.getString("REMARKS");
               mrHpDrawMainTableVo.setFieldName(columnComment);
               mrHpDrawMainTableVoList.add(mrHpDrawMainTableVo);
            }

            ajaxResult = AjaxResult.success((Object)mrHpDrawMainTableVoList);
         }
      } catch (SQLException | ClassNotFoundException e) {
         ((Exception)e).printStackTrace();
      } finally {
         try {
            if (resultSet != null) {
               resultSet.close();
            }

            if (connection != null) {
               connection.close();
            }
         } catch (SQLException e) {
            e.printStackTrace();
         }

      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('mrhp:main:export')")
   @Log(
      title = "病案提取配置主",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(MrHpDrawMain mrHpDrawMain) {
      List<MrHpDrawMain> list = this.mrHpDrawMainService.selectMrHpDrawMainList(mrHpDrawMain);
      ExcelUtil<MrHpDrawMain> util = new ExcelUtil(MrHpDrawMain.class);
      return util.exportExcel(list, "病案提取配置主数据");
   }

   @PreAuthorize("@ss.hasPermi('mrhp:main:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      AjaxResult ajaxResult = AjaxResult.success();

      try {
         MrHpDrawMainResp resp = this.mrHpDrawMainService.selectMrHpDrawMainById(id);
         ajaxResult = AjaxResult.success((Object)resp);
      } catch (Exception var4) {
         this.log.error("查询详情出现异常");
         ajaxResult = AjaxResult.error("查询详情出现异常，请联系系统管理员");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('mrhp:main:add')")
   @Log(
      title = "病案提取配置主",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody MrHpDrawMainReq mrHpDrawMainReq) {
      AjaxResult ajaxResult = AjaxResult.success("更新成功");
      Boolean flag = true;
      if (mrHpDrawMainReq == null) {
         flag = false;
         ajaxResult = AjaxResult.error("参数不能为空");
      }

      if (flag && StringUtils.isBlank(mrHpDrawMainReq.getInterfaceName())) {
         flag = false;
         ajaxResult = AjaxResult.error("接口名称不能为空");
      }

      if (flag && StringUtils.isBlank(mrHpDrawMainReq.getHisTableName())) {
         flag = false;
         ajaxResult = AjaxResult.error("HIS数据库表不能为空");
      }

      if (flag && StringUtils.isBlank(mrHpDrawMainReq.getInterfaceType())) {
         flag = false;
         ajaxResult = AjaxResult.error("接口类型不能为空");
      }

      if (flag && StringUtils.isBlank(mrHpDrawMainReq.getDatasourceSyncCode())) {
         flag = false;
         ajaxResult = AjaxResult.error("数据源业务编码不能为空");
      }

      if ("2".equals(mrHpDrawMainReq.getInterfaceType())) {
         if (flag && StringUtils.isBlank(mrHpDrawMainReq.getDatasourceSyncName())) {
            flag = false;
            ajaxResult = AjaxResult.error("数据源业务名称不能为空");
         }

         if (flag && StringUtils.isBlank(mrHpDrawMainReq.getDataFrom())) {
            flag = false;
            ajaxResult = AjaxResult.error("数据来源不能为空");
         }
      }

      if ("1".equals(mrHpDrawMainReq.getInterfaceType()) && flag && StringUtils.isBlank(mrHpDrawMainReq.getReqUrl())) {
         flag = false;
         ajaxResult = AjaxResult.error("接口请求地址不能为空");
      }

      if (flag && StringUtils.isBlank(mrHpDrawMainReq.getStatus())) {
         flag = false;
         ajaxResult = AjaxResult.error("状态不能为空");
      }

      try {
         if (flag) {
            this.mrHpDrawMainService.addMrHpDrawMain(mrHpDrawMainReq);
         }
      } catch (Exception var5) {
         this.log.error("病案提取配置新增出现异常");
         ajaxResult = AjaxResult.error("病案提取配置新增出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('mrhp:main:edit')")
   @Log(
      title = "病案提取配置主",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody MrHpDrawMain mrHpDrawMain) {
      return this.toAjax(this.mrHpDrawMainService.updateMrHpDrawMain(mrHpDrawMain));
   }

   @PreAuthorize("@ss.hasPermi('mrhp:main:remove')")
   @Log(
      title = "病案提取配置主",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.mrHpDrawMainService.deleteMrHpDrawMainByIds(ids));
   }

   @PreAuthorize("@ss.hasPermi('mrhp:main:remove')")
   @Log(
      title = "病案提取配置主",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/delete/{id}"})
   public AjaxResult delete(@PathVariable Long id) {
      return this.toAjax(this.mrHpDrawMainService.deleteMrHpDrawMainById(id));
   }

   @ApiOperation(
      value = "API接口调用",
      notes = "tmpa:out:add 或者 tmpa:out:edit"
   )
   @PreAuthorize("@ss.hasPermi('tmpa:out:add')")
   @PostMapping({"/testApiConn"})
   public AjaxResult testApiConn(@RequestBody MrHpDrawMainReq req) {
      AjaxResult res = AjaxResult.success();
      boolean flag = true;

      try {
         if (flag && req == null) {
            flag = false;
            res = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isBlank(req.getReqUrl())) {
            flag = false;
            res = AjaxResult.error("第三方接口地址不能为空");
         }

         if (flag && StringUtils.isBlank(req.getRequestMethod())) {
            flag = false;
            res = AjaxResult.error("请求方式不能为空");
         }

         if (flag) {
            new ArrayList();
            List<String> fieldList = new ArrayList();
            WebServiceGeneralResp webServiceGeneralResp = this.mrHpDrawMainService.testApiConn(req);
            if (webServiceGeneralResp != null && webServiceGeneralResp.getData() != null) {
               List e = (List)webServiceGeneralResp.getData();
               if (CollectionUtils.isNotEmpty(e) && e.size() > 0) {
                  fieldList = (List)e.stream().flatMap((map) -> map.keySet().stream()).distinct().collect(Collectors.toList());
               }
            }

            res = AjaxResult.success(webServiceGeneralResp.getData());
            res.put("fieldList", fieldList);
         }
      } catch (Exception e) {
         this.log.error("测试api链接出现异常，", e);
         res = AjaxResult.error("测试api链接出现异常，请联系管理员");
      }

      return res;
   }
}
