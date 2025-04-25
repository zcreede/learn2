package com.emr.project.system.controller;

import com.emr.common.utils.SnowIdUtils;
import com.emr.common.utils.StringUtils;
import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.domain.TreeSelectElem;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.system.domain.SysStaElem;
import com.emr.project.system.domain.vo.SysStaElemVo;
import com.emr.project.system.service.ISysStaElemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.Map;
import java.util.Random;
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
   value = "SysStaElemController",
   tags = {"元素库管理"}
)
@RestController
@RequestMapping({"/system/elem"})
public class SysStaElemController extends BaseController {
   @Autowired
   private ISysStaElemService sysStaElemService;

   @ApiOperation("查询元素库树状结构")
   @PreAuthorize("@ss.hasPermi('system:elem:list')")
   @GetMapping({"/libtree"})
   public AjaxResult queryElemLibTree() {
      AjaxResult ajaxResult = AjaxResult.success();

      try {
         ajaxResult.put("elemLibs", this.sysStaElemService.queryElemLibTree());
      } catch (Exception e) {
         ajaxResult = AjaxResult.error("查询元素库树状结构出现异常");
         this.log.error("查询元素库树状结构出现异常, ", e);
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('system:elem:list,qc:check:list')")
   @GetMapping({"/queryList"})
   public AjaxResult queryList(SysStaElem sysStaElem) {
      AjaxResult ajaxResult = AjaxResult.success();

      try {
         ajaxResult = AjaxResult.success((Object)this.sysStaElemService.selectElemQueryList(sysStaElem, "0017"));
      } catch (Exception e) {
         ajaxResult = AjaxResult.error("查询元素库树状结构出现异常");
         this.log.error("查询元素库树状结构出现异常, ", e);
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('system:elem:list,qc:check:list')")
   @GetMapping({"/queryRelaList"})
   public AjaxResult queryRelaList(SysStaElem sysStaElem) {
      AjaxResult ajaxResult = AjaxResult.success();

      try {
         ajaxResult = AjaxResult.success((Object)this.sysStaElemService.selectElemQueryList(sysStaElem, "0016"));
      } catch (Exception e) {
         ajaxResult = AjaxResult.error("查询元素库树状结构出现异常");
         this.log.error("查询元素库树状结构出现异常, ", e);
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('system:elem:list,qc:check:list')")
   @GetMapping({"/expreElemList"})
   public TableDataInfo expreElemList(SysStaElem sysStaElem) {
      new TableDataInfo();

      TableDataInfo tableDataInfo;
      try {
         this.startPage();
         tableDataInfo = this.getDataTable(this.sysStaElemService.selectExpreElemList(sysStaElem));
      } catch (Exception e) {
         tableDataInfo = new TableDataInfo(500, "查询元素库树状结构出现异常");
         this.log.error("查询元素库树状结构出现异常, ", e);
      }

      return tableDataInfo;
   }

   @ApiOperation("查询病历元素列表-分页")
   @ApiImplicitParams({@ApiImplicitParam(
   name = "elemName",
   value = "元素名称",
   dataType = "String"
), @ApiImplicitParam(
   name = "sourFlag",
   value = "元素类型",
   dataType = "String"
), @ApiImplicitParam(
   name = "pageNum",
   value = "页码",
   dataType = "String"
), @ApiImplicitParam(
   name = "pageSize",
   value = "每页展示条数",
   dataType = "String"
), @ApiImplicitParam(
   name = "orderByColumn",
   value = "排序列名称",
   dataType = "String"
), @ApiImplicitParam(
   name = "isAsc",
   value = "是否正序",
   dataType = "String"
)})
   @PreAuthorize("@ss.hasAnyPermi('system:elem:list,sys:quoteElem:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(SysStaElem sysStaElem) {
      new TableDataInfo();

      TableDataInfo tableDataInfo;
      try {
         this.startPage();
         List<SysStaElem> list = this.sysStaElemService.selectSysStaElemList(sysStaElem);
         tableDataInfo = this.getDataTable(list);
      } catch (Exception e) {
         this.log.error("查询病历元素列表出现异常", e);
         tableDataInfo = new TableDataInfo(500, "查询病历元素列表出现异常");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasPermi('system:elem:export')")
   @Log(
      title = "病历元素",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(SysStaElem sysStaElem) {
      List<SysStaElem> list = this.sysStaElemService.selectSysStaElemList(sysStaElem);
      ExcelUtil<SysStaElem> util = new ExcelUtil(SysStaElem.class);
      return util.exportExcel(list, "病历元素数据");
   }

   @PreAuthorize("@ss.hasPermi('system:elem:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.sysStaElemService.selectSysStaElemById(id));
   }

   @PreAuthorize("@ss.hasAnyPermi('system:elem:add,tmpl:index:createElem')")
   @Log(
      title = "病历元素",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody SysStaElemVo sysStaElemVo) {
      AjaxResult ajaxResult = AjaxResult.success("新增成功");
      boolean flag = true;

      try {
         if (flag && sysStaElemVo.getElemId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("元素id参数不能为空");
         }

         if (flag && StringUtils.isEmpty(sysStaElemVo.getSourFlag())) {
            flag = false;
            ajaxResult = AjaxResult.error("来源标志参数不能为空");
         } else if (flag && "1".equals(sysStaElemVo.getSourFlag()) && StringUtils.isEmpty(sysStaElemVo.getUnitId())) {
            flag = false;
            ajaxResult = AjaxResult.error("数据元id参数不能为空");
         }

         if (flag && StringUtils.isEmpty(sysStaElemVo.getElemCd())) {
            flag = false;
            ajaxResult = AjaxResult.error("元素编码参数不能为空");
         }

         if (flag && StringUtils.isEmpty(sysStaElemVo.getElemName())) {
            flag = false;
            ajaxResult = AjaxResult.error("元素名称参数不能为空");
         }

         if (flag && StringUtils.isEmpty(sysStaElemVo.getInputstrphtc())) {
            flag = false;
            ajaxResult = AjaxResult.error("拼音码参数不能为空");
         }

         if (flag && StringUtils.isEmpty(sysStaElemVo.getValidFlag())) {
            flag = false;
            ajaxResult = AjaxResult.error("启用标识参数不能为空");
         }

         if (flag && StringUtils.isEmpty(sysStaElemVo.getTypeFlag())) {
            flag = false;
            ajaxResult = AjaxResult.error("元素类型参数不能为空");
            if (("1".equals(sysStaElemVo.getTypeFlag()) || "A".equals(sysStaElemVo.getTypeFlag())) && flag && StringUtils.isEmpty(sysStaElemVo.getContType())) {
               flag = false;
               ajaxResult = AjaxResult.error("控件类型参数不能为空");
            }
         }

         if (flag && StringUtils.isEmpty(sysStaElemVo.getClasId())) {
            flag = false;
            ajaxResult = AjaxResult.error("元素分类id参数不能为空");
         }

         if (flag && StringUtils.isEmpty(sysStaElemVo.getClasName())) {
            flag = false;
            ajaxResult = AjaxResult.error("元素分类名称参数不能为空");
         }

         if (flag) {
            this.sysStaElemService.insertSysStaElem(sysStaElemVo);
            SysStaElem sysStaElem = this.sysStaElemService.generateUniqueCode("ZD");
            ajaxResult.put("newElemId", sysStaElem);
            SysStaElem sysElem = this.sysStaElemService.selectSysStaElemById(sysStaElemVo.getElemId());
            ajaxResult.put("elem", sysElem);
         }
      } catch (Exception e) {
         this.log.error("新增病历元素出现异常", e);
         ajaxResult = AjaxResult.error("新增病历元素出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('system:elem:edit')")
   @Log(
      title = "病历元素",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody SysStaElemVo sysStaElemVo) {
      AjaxResult ajaxResult = AjaxResult.success("修改成功");
      boolean flag = true;

      try {
         if (flag && sysStaElemVo.getElemId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("元素id参数不能为空");
         }

         if (flag && StringUtils.isEmpty(sysStaElemVo.getSourFlag())) {
            flag = false;
            ajaxResult = AjaxResult.error("来源标志参数不能为空");
         } else if (flag && "1".equals(sysStaElemVo.getSourFlag()) && StringUtils.isEmpty(sysStaElemVo.getUnitId())) {
            flag = false;
            ajaxResult = AjaxResult.error("数据元id参数不能为空");
         }

         if (flag && StringUtils.isEmpty(sysStaElemVo.getElemCd())) {
            flag = false;
            ajaxResult = AjaxResult.error("元素编码参数不能为空");
         }

         if (flag && StringUtils.isEmpty(sysStaElemVo.getElemName())) {
            flag = false;
            ajaxResult = AjaxResult.error("元素名称参数不能为空");
         }

         if (flag && StringUtils.isEmpty(sysStaElemVo.getInputstrphtc())) {
            flag = false;
            ajaxResult = AjaxResult.error("拼音码参数不能为空");
         }

         if (flag && StringUtils.isEmpty(sysStaElemVo.getValidFlag())) {
            flag = false;
            ajaxResult = AjaxResult.error("启用标识参数不能为空");
         }

         if (flag && StringUtils.isEmpty(sysStaElemVo.getTypeFlag())) {
            flag = false;
            ajaxResult = AjaxResult.error("元素类型参数不能为空");
            if (("1".equals(sysStaElemVo.getTypeFlag()) || "A".equals(sysStaElemVo.getTypeFlag())) && flag && StringUtils.isEmpty(sysStaElemVo.getContType())) {
               flag = false;
               ajaxResult = AjaxResult.error("控件类型参数不能为空");
            }
         }

         if (flag && StringUtils.isEmpty(sysStaElemVo.getClasId())) {
            flag = false;
            ajaxResult = AjaxResult.error("元素分类id参数不能为空");
         }

         if (flag && StringUtils.isEmpty(sysStaElemVo.getClasName())) {
            flag = false;
            ajaxResult = AjaxResult.error("元素分类名称参数不能为空");
         }

         if (flag) {
            this.sysStaElemService.updateSysStaElem(sysStaElemVo);
            SysStaElem sysStaElem = this.sysStaElemService.generateUniqueCode("ZD");
            ajaxResult.put("newElemId", sysStaElem);
            SysStaElem sysElem = this.sysStaElemService.selectSysStaElemById(sysStaElemVo.getElemId());
            ajaxResult.put("elem", sysElem);
         }
      } catch (Exception e) {
         this.log.error("修改病历元素出现异常", e);
         ajaxResult = AjaxResult.error("修改病历元素出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('system:elem:add,tmpl:index:createElem')")
   @PutMapping({"/addTmplElem"})
   public AjaxResult addTmplElem(@RequestBody SysStaElemVo sysStaElemVo) {
      AjaxResult ajaxResult = AjaxResult.success("同步成功");
      boolean flag = true;

      try {
         if (flag && sysStaElemVo.getElemId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("元素id参数不能为空");
         }

         if (flag && StringUtils.isEmpty(sysStaElemVo.getSourFlag())) {
            flag = false;
            ajaxResult = AjaxResult.error("来源标志参数不能为空");
         } else if (flag && "1".equals(sysStaElemVo.getSourFlag()) && StringUtils.isEmpty(sysStaElemVo.getUnitId())) {
            flag = false;
            ajaxResult = AjaxResult.error("数据元id参数不能为空");
         }

         if (flag && StringUtils.isEmpty(sysStaElemVo.getElemCd())) {
            flag = false;
            ajaxResult = AjaxResult.error("元素编码参数不能为空");
         }

         if (flag && StringUtils.isEmpty(sysStaElemVo.getElemName())) {
            flag = false;
            ajaxResult = AjaxResult.error("元素名称参数不能为空");
         }

         if (flag && StringUtils.isEmpty(sysStaElemVo.getInputstrphtc())) {
            flag = false;
            ajaxResult = AjaxResult.error("拼音码参数不能为空");
         }

         if (flag && StringUtils.isEmpty(sysStaElemVo.getValidFlag())) {
            flag = false;
            ajaxResult = AjaxResult.error("启用标识参数不能为空");
         }

         if (flag && StringUtils.isEmpty(sysStaElemVo.getTypeFlag())) {
            flag = false;
            ajaxResult = AjaxResult.error("元素类型参数不能为空");
            if (("1".equals(sysStaElemVo.getTypeFlag()) || "A".equals(sysStaElemVo.getTypeFlag())) && flag && StringUtils.isEmpty(sysStaElemVo.getContType())) {
               flag = false;
               ajaxResult = AjaxResult.error("控件类型参数不能为空");
            }
         }

         if (flag && StringUtils.isEmpty(sysStaElemVo.getClasId())) {
            flag = false;
            ajaxResult = AjaxResult.error("元素分类id参数不能为空");
         }

         if (flag && StringUtils.isEmpty(sysStaElemVo.getClasName())) {
            flag = false;
            ajaxResult = AjaxResult.error("元素分类名称参数不能为空");
         }

         if (flag && StringUtils.isNotBlank(sysStaElemVo.getTertiarySign()) && "TRUE".equals(sysStaElemVo.getTertiarySign())) {
            sysStaElemVo.setClasId("TERTIARY");
            sysStaElemVo.setClasName("三甲指标");
         }

         if (flag) {
            this.sysStaElemService.saveElemVo(sysStaElemVo);
            SysStaElem sysElem = this.sysStaElemService.selectSysStaElemById(sysStaElemVo.getElemId());
            ajaxResult.put("elem", sysElem);
         }
      } catch (Exception e) {
         this.log.error("新增病历元素出现异常", e);
         ajaxResult = AjaxResult.error("新增病历元素出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('system:elem:remove')")
   @Log(
      title = "病历元素",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.sysStaElemService.deleteSysStaElemByIds(ids));
   }

   @ApiOperation("查询元素树")
   @PreAuthorize("@ss.hasAnyPermi('system:elem:elementTree,tmpl:index:queryAall,tmpl:index:pageInfo,tmpl:subtempl:list')")
   @GetMapping({"/elementTree"})
   public AjaxResult selectElementTree(SysStaElem sysStaElem) {
      new AjaxResult();

      AjaxResult ajaxResult;
      try {
         List<TreeSelectElem> treeSelectList = this.sysStaElemService.selectElementTree(sysStaElem);
         ajaxResult = AjaxResult.success((Object)treeSelectList);
      } catch (Exception e) {
         ajaxResult = AjaxResult.error("查询元素树出现异常");
         this.log.error("查询元素树出现异常, ", e);
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('system:elem:libraryList')")
   @GetMapping({"/libraryList"})
   public TableDataInfo libraryList(SysStaElem sysStaElem) {
      new TableDataInfo();

      TableDataInfo tableDataInfo;
      try {
         this.startPage();
         List<SysStaElemVo> list = this.sysStaElemService.selectElemLibraryList(sysStaElem);
         tableDataInfo = this.getDataTable(list);
      } catch (Exception e) {
         this.log.error("查询元素列表出现异常", e);
         tableDataInfo = new TableDataInfo(500, "查询元素列表出现异常");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasAnyPermi('system:elem:signList,tmpl:index:queryAall')")
   @GetMapping({"/signList"})
   public TableDataInfo signList(SysStaElem sysStaElem) {
      new TableDataInfo();

      TableDataInfo tableDataInfo;
      try {
         if (StringUtils.isNotEmpty(sysStaElem.getContType())) {
            this.startPage();
            List<SysStaElemVo> list = this.sysStaElemService.selectElemSignList(sysStaElem);
            tableDataInfo = this.getDataTable(list);
         } else {
            tableDataInfo = new TableDataInfo(500, "控件类型不能为空");
         }
      } catch (Exception e) {
         this.log.error("查询医师签名列表出现异常", e);
         tableDataInfo = new TableDataInfo(500, "查询医师签名列表出现异常");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasPermi('system:elem:changeValidFlag')")
   @Log(
      title = "修改病历元素启用状态",
      businessType = BusinessType.UPDATE
   )
   @PutMapping({"changeValidFlag"})
   public AjaxResult changeValidFlag(@RequestBody SysStaElemVo sysStaElemVo) {
      AjaxResult ajaxResult = AjaxResult.success("修改成功");

      try {
         if (sysStaElemVo.getId() == null) {
            ajaxResult = AjaxResult.error("元素id参数不能为空");
         } else if (StringUtils.isEmpty(sysStaElemVo.getValidFlag())) {
            ajaxResult = AjaxResult.error("启用标识不能为空");
         } else {
            this.sysStaElemService.changeValidFlagElem(sysStaElemVo);
         }
      } catch (Exception e) {
         this.log.error("修改病历元素启用状态出现异常", e);
         ajaxResult = AjaxResult.error("修改病历元素启用状态出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('system:elem:getCode,system:elem:add')")
   @GetMapping({"/getCode"})
   public AjaxResult getCode() {
      new AjaxResult();

      AjaxResult ajaxResult;
      try {
         SysStaElem sysStaElem = this.sysStaElemService.generateUniqueCode("ZD");
         ajaxResult = AjaxResult.success((Object)sysStaElem);
      } catch (Exception e) {
         this.log.error("生成元素id和元素编码出现异常", e);
         ajaxResult = AjaxResult.error("生成元素id和元素编码出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('system:elem:getCode,system:elem:add,tmpl:index:add,tmpl:index:edit')")
   @GetMapping({"/getTmplCode"})
   public AjaxResult getTmplCode() {
      new AjaxResult();

      AjaxResult ajaxResult;
      try {
         SysStaElem sysStaElem = new SysStaElem();
         sysStaElem.setId(SnowIdUtils.uniqueLong());
         Random random = new Random();
         String code = "MD" + String.format("%09d", random.nextInt(1000000000));
         sysStaElem.setElemCd(code);
         ajaxResult = AjaxResult.success((Object)sysStaElem);
      } catch (Exception e) {
         this.log.error("生成元素id和元素编码出现异常", e);
         ajaxResult = AjaxResult.error("生成元素id和元素编码出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('system:elem:elemInfo,tmpl:index:pageInfo')")
   @GetMapping({"/elemInfo"})
   public AjaxResult elemInfo(String id) {
      new AjaxResult();

      AjaxResult ajaxResult;
      try {
         if (StringUtils.isEmpty(id)) {
            ajaxResult = AjaxResult.error("id不能为空");
         } else {
            SysStaElemVo sysStaElem = this.sysStaElemService.selectElemInfo(id);
            ajaxResult = AjaxResult.success((Object)sysStaElem);
         }
      } catch (Exception e) {
         this.log.error("查询单个元素具体信息出现异常", e);
         ajaxResult = AjaxResult.error("查询单个元素具体信息出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('system:elem:range,tmpl:index:pageInfo')")
   @GetMapping({"/range"})
   public AjaxResult range(String setName) {
      new AjaxResult();

      AjaxResult ajaxResult;
      try {
         if (StringUtils.isEmpty(setName)) {
            ajaxResult = AjaxResult.error("setName不能为空");
         } else {
            Map<String, List<String>> map = this.sysStaElemService.getSetViewVoList(setName);
            ajaxResult = AjaxResult.success((Object)map);
         }
      } catch (Exception e) {
         this.log.error("获取值域code，value集合出现异常", e);
         ajaxResult = AjaxResult.error("获取值域code，value集合出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('pat:personalinfo:operRoom,system:elem:add,tmpl:index:add,tmpl:index:edit')")
   @PostMapping({"/updateElem"})
   public AjaxResult updateTmpl(@RequestBody SysStaElemVo sysStaElemVo) {
      AjaxResult ajaxResult = AjaxResult.success();

      try {
         boolean flag = true;
         if (sysStaElemVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(sysStaElemVo.getNewStr())) {
            flag = false;
            ajaxResult = AjaxResult.error("newStr参数不能为空");
         }

         if (flag && StringUtils.isEmpty(sysStaElemVo.getOldStr())) {
            flag = false;
            ajaxResult = AjaxResult.error("oldStr参数不能为空");
         }

         if (flag && StringUtils.isEmpty(sysStaElemVo.getContType())) {
            flag = false;
            ajaxResult = AjaxResult.error("contType参数不能为空");
         }

         if (flag) {
            this.sysStaElemService.updateElem(sysStaElemVo);
         }
      } catch (Exception e) {
         this.log.error("批量修改元素库数据异常", e);
         ajaxResult = AjaxResult.error("批量修改元素库数据异常");
      }

      return ajaxResult;
   }
}
