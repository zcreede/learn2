package com.emr.project.mrhp.controller;

import com.emr.common.utils.SecurityUtils;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.mrhp.domain.TmDsPreserveOut;
import com.emr.project.mrhp.domain.req.TmDsPreserveOutReq;
import com.emr.project.mrhp.service.ITmDsPreserveOutService;
import com.emr.project.system.domain.SysUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(
   tags = {"MRIS0067-外部数据源维护"}
)
@RestController
@RequestMapping({"/tmpa/out"})
public class TmDsPreserveOutController extends BaseController {
   @Autowired
   private ITmDsPreserveOutService tmDsPreserveOutService;

   @ApiOperation(
      value = "查询外部数据源维护列表",
      notes = "tmpa:out:list"
   )
   @PreAuthorize("@ss.hasPermi('tmpa:out:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(String databaseDesc) {
      TableDataInfo tableDataInfo = new TableDataInfo();

      try {
         this.startPage();
         TmDsPreserveOut tmDsPreserveOut = new TmDsPreserveOut();
         tmDsPreserveOut.setDatabaseDesc(databaseDesc);
         List<TmDsPreserveOut> list = this.tmDsPreserveOutService.selectTmDsPreserveOutList(tmDsPreserveOut);
         tableDataInfo = this.getDataTable(list);
      } catch (Exception e) {
         this.log.error("查询外部数据源维护列表出现异常，", e);
      }

      return tableDataInfo;
   }

   @ApiOperation(
      value = "查询外部数据源维护列表",
      notes = "tmpa:out:list"
   )
   @PreAuthorize("@ss.hasPermi('tmpa:out:list')")
   @GetMapping({"/allList"})
   public AjaxResult allList(String databaseDesc) {
      AjaxResult ajaxResult = AjaxResult.success();

      try {
         this.startPage();
         TmDsPreserveOut tmDsPreserveOut = new TmDsPreserveOut();
         tmDsPreserveOut.setDatabaseDesc(databaseDesc);
         List<TmDsPreserveOut> list = this.tmDsPreserveOutService.selectTmDsPreserveOutList(tmDsPreserveOut);
         ajaxResult = AjaxResult.success((Object)list);
      } catch (Exception e) {
         this.log.error("查询外部数据源维护列表出现异常，", e);
         ajaxResult = AjaxResult.error("查询外部数据源维护列表出现异常");
      }

      return ajaxResult;
   }

   @ApiOperation(
      value = "查询外部数据源维护详细信息",
      notes = "tmpa:out:query"
   )
   @PreAuthorize("@ss.hasPermi('tmpa:out:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      AjaxResult res = AjaxResult.success();

      try {
         TmDsPreserveOut preserveOut = this.tmDsPreserveOutService.selectTmDsPreserveOutById(id);
         res = AjaxResult.success((Object)preserveOut);
      } catch (Exception e) {
         this.log.error("查询外部数据源维护详细信息出现异常，", e);
         res = AjaxResult.error("查询外部数据源维护详细信息出现异常，请联系管理员");
      }

      return res;
   }

   @ApiOperation(
      value = "新增外部数据源",
      notes = "tmpa:out:add"
   )
   @PreAuthorize("@ss.hasPermi('tmpa:out:add')")
   @Log(
      title = "外部数据源维护",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody TmDsPreserveOut tmDsPreserveOut) {
      AjaxResult res = AjaxResult.success();
      boolean flag = true;

      try {
         if (flag && tmDsPreserveOut == null) {
            flag = false;
            res = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isBlank(tmDsPreserveOut.getDataSourceTag())) {
            flag = false;
            res = AjaxResult.error("数据源名称不能为空");
         }

         if (flag && StringUtils.isBlank(tmDsPreserveOut.getDatabaseType())) {
            flag = false;
            res = AjaxResult.error("数据源类型不能为空");
         }

         if (flag && StringUtils.isBlank(tmDsPreserveOut.getDatabaseDesc())) {
            flag = false;
            res = AjaxResult.error("数据源描述不能为空");
         }

         if (flag && StringUtils.isBlank(tmDsPreserveOut.getDriverClass())) {
            flag = false;
            res = AjaxResult.error("数据源驱动类名不能为空");
         }

         if (flag && StringUtils.isBlank(tmDsPreserveOut.getDatabaseUrl())) {
            flag = false;
            res = AjaxResult.error("数据源链接URL不能为空");
         }

         if (flag && StringUtils.isBlank(tmDsPreserveOut.getServerAddress())) {
            flag = false;
            res = AjaxResult.error("数据源服务器IP不能为空");
         }

         if (flag && StringUtils.isBlank(tmDsPreserveOut.getDatabaseName())) {
            flag = false;
            res = AjaxResult.error("数据库名称不能为空");
         }

         if (flag && StringUtils.isBlank(tmDsPreserveOut.getUserName())) {
            flag = false;
            res = AjaxResult.error("数据库用户名不能为空");
         }

         if (flag && StringUtils.isBlank(tmDsPreserveOut.getPassward())) {
            flag = false;
            res = AjaxResult.error("数据库密码不能为空");
         }

         if (flag && StringUtils.isBlank(tmDsPreserveOut.getState())) {
            flag = false;
            res = AjaxResult.error("数据源状态不能为空");
         }

         SysUser user = SecurityUtils.getLoginUser().getUser();
         TmDsPreserveOut param = new TmDsPreserveOut();
         param.setOrgCd(user.getHospital().getOrgCode());
         param.setDataSourceTag(tmDsPreserveOut.getDataSourceTag());
         param.setState("1");
         List<TmDsPreserveOut> list = flag ? this.tmDsPreserveOutService.selectTmDsPreserveOutList(param) : null;
         if (flag && CollectionUtils.isNotEmpty(list)) {
            flag = false;
            res = AjaxResult.error("同一院区数据源名称不能重复");
         }

         if (flag) {
            this.tmDsPreserveOutService.insertTmDsPreserveOut(tmDsPreserveOut);
         }
      } catch (Exception e) {
         this.log.error("新增外部数据源出现异常，", e);
         res = AjaxResult.error("新增外部数据源出现异常，请联系管理员");
      }

      return res;
   }

   @ApiOperation(
      value = "测试外部数据源链接",
      notes = "tmpa:out:add 或者 tmpa:out:edit"
   )
   @PreAuthorize("@ss.hasPermi('tmpa:out:add')")
   @Log(
      title = "测试外部数据源链接",
      businessType = BusinessType.INSERT
   )
   @PostMapping({"/testConn"})
   public AjaxResult testConn(@RequestBody TmDsPreserveOutReq tmDsPreserveOut) {
      AjaxResult res = AjaxResult.success();
      boolean flag = true;

      try {
         if (flag && tmDsPreserveOut == null) {
            flag = false;
            res = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isBlank(tmDsPreserveOut.getDataSourceTag())) {
            flag = false;
            res = AjaxResult.error("数据源名称不能为空");
         }

         if (flag && StringUtils.isBlank(tmDsPreserveOut.getDatabaseType())) {
            flag = false;
            res = AjaxResult.error("数据源类型不能为空");
         }

         if (flag && StringUtils.isBlank(tmDsPreserveOut.getDatabaseDesc())) {
            flag = false;
            res = AjaxResult.error("数据源描述不能为空");
         }

         if (flag && StringUtils.isBlank(tmDsPreserveOut.getDriverClass())) {
            flag = false;
            res = AjaxResult.error("数据源驱动类名不能为空");
         }

         if (flag && StringUtils.isBlank(tmDsPreserveOut.getDatabaseUrl())) {
            flag = false;
            res = AjaxResult.error("数据源链接URL不能为空");
         }

         if (flag && StringUtils.isBlank(tmDsPreserveOut.getServerAddress())) {
            flag = false;
            res = AjaxResult.error("数据源服务器IP不能为空");
         }

         if (flag && StringUtils.isBlank(tmDsPreserveOut.getDatabaseName())) {
            flag = false;
            res = AjaxResult.error("数据库名称不能为空");
         }

         if (flag && StringUtils.isBlank(tmDsPreserveOut.getUserName())) {
            flag = false;
            res = AjaxResult.error("数据库用户名不能为空");
         }

         if (flag && StringUtils.isBlank(tmDsPreserveOut.getPassward())) {
            flag = false;
            res = AjaxResult.error("数据库密码不能为空");
         }

         if (flag && StringUtils.isBlank(tmDsPreserveOut.getState())) {
            flag = false;
            res = AjaxResult.error("数据源状态不能为空");
         }

         if (flag) {
            List<Map<Object, Object>> mapList = this.tmDsPreserveOutService.testConn(tmDsPreserveOut);
            if (mapList != null) {
               res = AjaxResult.success((Object)mapList);
               String sqlStr = tmDsPreserveOut.getSqlStr();
               int indexFirst = sqlStr.indexOf("select");
               if (indexFirst > -1) {
                  sqlStr = sqlStr.substring(indexFirst + 6, sqlStr.length());
                  int indexTwo = sqlStr.indexOf("from");
                  if (indexTwo > -1) {
                     sqlStr = sqlStr.substring(0, indexTwo);
                     sqlStr = sqlStr.replaceAll(" ", "");
                     List<String> fieldList = Arrays.asList(sqlStr.split(","));
                     res.put("fieldList", fieldList);
                  }
               }
            } else {
               res = AjaxResult.error("连接失败");
            }
         }
      } catch (Exception e) {
         this.log.error("测试外部数据源链接出现异常，", e);
         res = AjaxResult.error("测试外部数据源链接出现异常，请联系管理员");
      }

      return res;
   }

   @ApiOperation(
      value = "修改外部数据源",
      notes = "tmpa:out:edit"
   )
   @PreAuthorize("@ss.hasPermi('tmpa:out:edit')")
   @PutMapping
   public AjaxResult edit(@RequestBody TmDsPreserveOut tmDsPreserveOut) {
      AjaxResult res = AjaxResult.success();
      boolean flag = true;

      try {
         if (flag && tmDsPreserveOut == null) {
            flag = false;
            res = AjaxResult.error("参数不能为空");
         }

         if (flag && tmDsPreserveOut.getId() == null) {
            flag = false;
            res = AjaxResult.error("外部数据源id不能为空");
         }

         if (flag && StringUtils.isBlank(tmDsPreserveOut.getDataSourceTag())) {
            flag = false;
            res = AjaxResult.error("数据源名称不能为空");
         }

         if (flag && StringUtils.isBlank(tmDsPreserveOut.getDatabaseType())) {
            flag = false;
            res = AjaxResult.error("数据源类型不能为空");
         }

         if (flag && StringUtils.isBlank(tmDsPreserveOut.getDatabaseDesc())) {
            flag = false;
            res = AjaxResult.error("数据源描述不能为空");
         }

         if (flag && StringUtils.isBlank(tmDsPreserveOut.getDriverClass())) {
            flag = false;
            res = AjaxResult.error("数据源驱动类名不能为空");
         }

         if (flag && StringUtils.isBlank(tmDsPreserveOut.getDatabaseUrl())) {
            flag = false;
            res = AjaxResult.error("数据源链接URL不能为空");
         }

         if (flag && StringUtils.isBlank(tmDsPreserveOut.getServerAddress())) {
            flag = false;
            res = AjaxResult.error("数据源服务器IP不能为空");
         }

         if (flag && StringUtils.isBlank(tmDsPreserveOut.getDatabaseName())) {
            flag = false;
            res = AjaxResult.error("数据库名称不能为空");
         }

         if (flag && StringUtils.isBlank(tmDsPreserveOut.getUserName())) {
            flag = false;
            res = AjaxResult.error("数据库用户名不能为空");
         }

         if (flag && StringUtils.isBlank(tmDsPreserveOut.getPassward())) {
            flag = false;
            res = AjaxResult.error("数据库密码不能为空");
         }

         if (flag && StringUtils.isBlank(tmDsPreserveOut.getState())) {
            flag = false;
            res = AjaxResult.error("数据源状态不能为空");
         }

         TmDsPreserveOut preserveOutDb = flag ? this.tmDsPreserveOutService.selectTmDsPreserveOutById(tmDsPreserveOut.getId()) : null;
         if (flag && preserveOutDb == null) {
            flag = false;
            res = AjaxResult.error("查询不到此外部数据源记录，不能修改");
         }

         if (flag) {
            SysUser user = SecurityUtils.getLoginUser().getUser();
            TmDsPreserveOut param = new TmDsPreserveOut();
            param.setOrgCd(user.getHospital().getOrgCode());
            param.setDataSourceTag(tmDsPreserveOut.getDataSourceTag());
            param.setState("1");
            List<TmDsPreserveOut> list = this.tmDsPreserveOutService.selectTmDsPreserveOutList(param);
            list = (List)list.stream().filter((t) -> !t.getId().equals(tmDsPreserveOut.getId())).collect(Collectors.toList());
            if (flag && CollectionUtils.isNotEmpty(list)) {
               flag = false;
               res = AjaxResult.error("同一院区数据源名称不能重复");
            }
         }

         if (flag) {
            this.tmDsPreserveOutService.updateTmDsPreserveOut(tmDsPreserveOut);
         }
      } catch (Exception e) {
         this.log.error("新增外部数据源出现异常，", e);
         res = AjaxResult.error("新增外部数据源出现异常，请联系管理员");
      }

      return res;
   }

   @ApiOperation(
      value = "删除外部数据源",
      notes = "tmpa:out:remove"
   )
   @PreAuthorize("@ss.hasPermi('tmpa:out:remove')")
   @Log(
      title = "外部数据源维护",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{id}"})
   public AjaxResult remove(@PathVariable Long id) {
      AjaxResult res = AjaxResult.success();
      boolean flag = true;

      try {
         TmDsPreserveOut preserveOutDb = flag ? this.tmDsPreserveOutService.selectTmDsPreserveOutById(id) : null;
         if (flag && preserveOutDb == null) {
            flag = false;
            res = AjaxResult.error("查询不到此外部数据源记录，不能删除");
         }

         if (flag) {
            this.tmDsPreserveOutService.deleteTmDsPreserveOutById(id);
         }
      } catch (Exception e) {
         this.log.error("新增外部数据源出现异常，", e);
         res = AjaxResult.error("新增外部数据源出现异常，请联系管理员");
      }

      return res;
   }
}
