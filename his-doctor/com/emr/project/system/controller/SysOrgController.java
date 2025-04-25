package com.emr.project.system.controller;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.system.domain.SysOrg;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.domain.resp.RegionInfoDefaultResp;
import com.emr.project.system.service.ISysOrgService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import java.util.List;
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

@RestController
@RequestMapping({"/system/org"})
public class SysOrgController extends BaseController {
   @Autowired
   private ISysOrgService sysOrgService;

   @PreAuthorize("@ss.hasPermi('system:org:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(SysOrg sysOrg) {
      this.startPage();
      List<SysOrg> list = this.sysOrgService.selectSysOrgList(sysOrg);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('system:org:export')")
   @Log(
      title = "机构信息",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(SysOrg sysOrg) {
      List<SysOrg> list = this.sysOrgService.selectSysOrgList(sysOrg);
      ExcelUtil<SysOrg> util = new ExcelUtil(SysOrg.class);
      return util.exportExcel(list, "机构信息数据");
   }

   @PreAuthorize("@ss.hasPermi('system:org:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.sysOrgService.selectSysOrgById(id));
   }

   @PreAuthorize("@ss.hasPermi('system:org:add')")
   @Log(
      title = "机构信息",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody SysOrg sysOrg) {
      return this.toAjax(this.sysOrgService.insertSysOrg(sysOrg));
   }

   @PreAuthorize("@ss.hasPermi('system:org:edit')")
   @Log(
      title = "机构信息",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody SysOrg sysOrg) {
      return this.toAjax(this.sysOrgService.updateSysOrg(sysOrg));
   }

   @PreAuthorize("@ss.hasPermi('system:org:remove')")
   @Log(
      title = "机构信息",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.sysOrgService.deleteSysOrgByIds(ids));
   }

   @ApiOperation("获取部门下拉树列表")
   @ApiImplicitParams({@ApiImplicitParam(
   name = "id",
   value = "部门ID",
   dataType = "Long"
), @ApiImplicitParam(
   name = "parentId",
   value = "父部门ID",
   dataType = "Long"
), @ApiImplicitParam(
   name = "orgName",
   value = "部门名称",
   dataType = "String"
)})
   @GetMapping({"/treeselect"})
   @PreAuthorize("@ss.hasAnyPermi('system:org:treeselect,tmpl:index:add,tmpl:index:queryList,pat:record:queryList,emr:index:add')")
   public AjaxResult treeselect(SysOrg org) {
      return null;
   }

   @GetMapping({"/defaultInfo"})
   public AjaxResult defaultInfo() {
      AjaxResult ajaxResult = AjaxResult.success();
      RegionInfoDefaultResp resData = new RegionInfoDefaultResp();

      try {
         SysUser user = SecurityUtils.getLoginUser().getUser();
         SysOrg sysOrg = this.sysOrgService.checkOrgCodeUnique(user.getHospital().getOrgCode());
         if (sysOrg != null) {
            resData.setAddressShengCode(sysOrg.getAddressShengCode());
            resData.setAddressSheng(sysOrg.getAddressSheng());
            resData.setAddressShiCode(sysOrg.getAddressShiCode());
            resData.setAddressShi(sysOrg.getAddressShi());
            resData.setAddressXianCode(sysOrg.getAddressXianCode());
            resData.setAddressXian(sysOrg.getAddressXian());
            resData.setAddressXiangCode(sysOrg.getAddressXiangCode());
            resData.setAddressXian(sysOrg.getAddressXian());
            ajaxResult = AjaxResult.success((Object)resData);
         }
      } catch (Exception e) {
         this.log.error("查询医院默认地市出现异常，", e);
         ajaxResult = AjaxResult.error("查询医院默认地市出现异常，请联系管理员！");
      }

      return ajaxResult;
   }
}
