package com.emr.project.system.controller;

import com.emr.common.constant.CommonConstants;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.StringUtils;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.domain.TreeSelect;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.esSearch.service.IDrugStockService;
import com.emr.project.his.domain.vo.PharmacyVo;
import com.emr.project.system.domain.SysDept;
import com.emr.project.system.domain.SysOrg;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.domain.TmBsDeptTypeContrast;
import com.emr.project.system.domain.vo.SysDeptVo;
import com.emr.project.system.domain.vo.SysOrgVo;
import com.emr.project.system.service.ISysDeptService;
import com.emr.project.system.service.ISysOrgService;
import com.emr.project.system.service.ITmBsDeptTypeContrastService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.apache.commons.lang3.ArrayUtils;
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

@Api("部门信息管理")
@RestController
@RequestMapping({"/system/dept"})
public class SysDeptController extends BaseController {
   @Autowired
   private ISysDeptService deptService;
   @Resource
   private ISysOrgService sysOrgService;
   @Autowired
   private IDrugStockService drugStockService;
   @Autowired
   private ITmBsDeptTypeContrastService tmBsDeptTypeContrastService;

   @ApiOperation("获取部门列表")
   @PreAuthorize("@ss.hasPermi('system:dept:list')")
   @GetMapping({"/list"})
   public AjaxResult list(SysDept dept) {
      SysOrg orgParam = new SysOrg();
      orgParam.setDelFlag("0");
      List<SysOrg> orgList = this.sysOrgService.selectSysOrgList(orgParam);
      List<SysDept> parentDept = new ArrayList(orgList.size());

      for(SysOrg orgTemp : orgList) {
         SysDept orgDept = new SysDept();
         orgDept.setDeptId(orgTemp.getId());
         orgDept.setDeptName(orgTemp.getOrgName());
         orgDept.setParentId(orgTemp.getParentId());
         if (orgTemp.getParentId() == null) {
            orgDept.setAncestors(CommonConstants.SYSTEM.ORG_DEPT_ROOT_PARENT_ID + "");
         } else {
            orgDept.setAncestors(CommonConstants.SYSTEM.ORG_DEPT_ROOT_PARENT_ID + "," + CommonConstants.SYSTEM.ORG_DEPT_ROOT_ID);
         }

         parentDept.add(orgDept);
      }

      List<SysDept> depts = this.deptService.selectDeptList(dept);
      parentDept.addAll(depts);
      return AjaxResult.success((Object)parentDept);
   }

   @ApiOperation("获取部门分页列表")
   @PreAuthorize("@ss.hasAnyPermi('system:dept:pageList,tmpl:auditRecord:queryList,qc:dept:list')")
   @GetMapping({"/pageList"})
   public TableDataInfo pageList(SysDept dept) {
      new TableDataInfo();

      TableDataInfo tableDataInfo;
      try {
         this.startPage();
         List<SysDept> depts = this.deptService.selectDeptPageList(dept);
         tableDataInfo = this.getDataTable(depts);
      } catch (Exception e) {
         this.log.error("获取部门分页列表异常", e);
         tableDataInfo = new TableDataInfo(500, "获取部门分页列表异常");
      }

      return tableDataInfo;
   }

   @ApiOperation("查询部门列表")
   @ApiImplicitParam(
      name = "deptId",
      value = "部门D",
      required = false,
      dataType = "Long",
      paramType = "path"
   )
   @PreAuthorize("@ss.hasPermi('system:dept:list')")
   @GetMapping({"/list/exclude/{deptId}"})
   public AjaxResult excludeChild(@PathVariable(value = "deptId",required = false) Long deptId) {
      List<SysDept> depts = this.deptService.selectDeptList(new SysDept());
      Iterator<SysDept> it = depts.iterator();

      while(it.hasNext()) {
         SysDept d = (SysDept)it.next();
         if ((long)d.getDeptId().intValue() == deptId || ArrayUtils.contains(StringUtils.split(d.getAncestors(), ","), deptId + "")) {
            it.remove();
         }
      }

      return AjaxResult.success((Object)depts);
   }

   @ApiOperation("根据部门编号获取详细信息")
   @ApiImplicitParam(
      name = "deptId",
      value = "部门D",
      required = true,
      dataType = "Long",
      paramType = "path"
   )
   @PreAuthorize("@ss.hasPermi('system:dept:query')")
   @GetMapping({"/{deptId}"})
   public AjaxResult getInfo(@PathVariable Long deptId) {
      return AjaxResult.success((Object)this.deptService.selectDeptById(deptId));
   }

   @ApiOperation("获取部门下拉树列表")
   @ApiImplicitParams({@ApiImplicitParam(
   name = "deptId",
   value = "部门ID",
   dataType = "Long"
), @ApiImplicitParam(
   name = "parentId",
   value = "父部门ID",
   dataType = "Long"
), @ApiImplicitParam(
   name = "status",
   value = "部门状态:0正常,1停用",
   dataType = "String"
), @ApiImplicitParam(
   name = "deptName",
   value = "部门名称",
   dataType = "String"
)})
   @GetMapping({"/treeselect"})
   @PreAuthorize("@ss.hasAnyPermi('system:dept:treeselect,tmpl:index:add,tmpl:index:queryList,tmpl:auditRecord:queryList,tmpl:index:edit,pat:record:queryList,emr:index:add,system:employee:list,handover:scheme:list,handover:scheme:add,handover:scheme:edit,td:apply:add,pat:visitinfo:allList,pat:visitinfo:emrAllList,qc:rt:checkpatientList,qc:flow:term,oper:room:feeDetailList,other:temp:list')")
   public AjaxResult treeselects(SysOrgVo org) {
      List<SysOrg> depts = this.sysOrgService.selectSysOrgDeptList(org, true);
      List<TreeSelect> treeSelectList = this.sysOrgService.buildOrgTreeSelect(depts);
      return AjaxResult.success((Object)treeSelectList);
   }

   public AjaxResult treeselect(SysDept dept) {
      List<SysDept> depts = this.deptService.selectDeptList(dept);
      return AjaxResult.success((Object)this.deptService.buildDeptTreeSelect(depts));
   }

   @PreAuthorize("@ss.hasPermi('system:dept:add')")
   @Log(
      title = "部门管理",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody SysDept dept) {
      AjaxResult ajaxResult = AjaxResult.success("新增成功");
      boolean flag = true;

      try {
         if (dept == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag) {
            if (StringUtils.isNotEmpty(dept.getDeptName())) {
               SysDept sysDept = this.deptService.selectDeptByDeptName(dept.getDeptName(), (Long)null);
               if (sysDept != null) {
                  flag = false;
                  ajaxResult = AjaxResult.error("部门名称已存在");
               }
            } else {
               flag = false;
               ajaxResult = AjaxResult.error("部门名称不能为空");
            }
         }

         if (flag) {
            if (StringUtils.isNotEmpty(dept.getDeptCode())) {
               SysDept sysDept = this.deptService.selectDeptByDeptCode(dept.getDeptCode(), (Long)null);
               if (sysDept != null) {
                  flag = false;
                  ajaxResult = AjaxResult.error("部门编码已存在");
               }
            } else {
               flag = false;
               ajaxResult = AjaxResult.error("部门编码不能为空");
            }
         }

         if (flag && dept.getParentId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("上级部门不能为空");
         }

         if (flag) {
            if (CommonConstants.SYSTEM.ORG_ID == dept.getParentId()) {
               ajaxResult = AjaxResult.error("不允许添加机构信息");
            } else {
               dept.setCreateBy(SecurityUtils.getUsername());
               this.deptService.insertDept(dept);
            }
         }
      } catch (Exception e) {
         this.log.error("新增部门管理出现异常", e);
         ajaxResult = AjaxResult.error("新增部门管理出现异常");
      }

      return ajaxResult;
   }

   @ApiOperation("加载对应角色部门列表树")
   @ApiImplicitParam(
      name = "roleId",
      value = "角色ID",
      required = true,
      dataType = "Long",
      paramType = "path"
   )
   @GetMapping({"/roleDeptTreeselect/{roleId}"})
   public AjaxResult roleDeptTreeselect(@PathVariable("roleId") Long roleId) {
      List<SysDept> depts = this.deptService.selectDeptList(new SysDept());
      AjaxResult ajax = AjaxResult.success();
      ajax.put("checkedKeys", this.deptService.selectDeptListByRoleId(roleId));
      ajax.put("depts", this.deptService.buildDeptTreeSelect(depts));
      return ajax;
   }

   @ApiOperation("修改部门")
   @PreAuthorize("@ss.hasPermi('system:dept:edit')")
   @Log(
      title = "部门管理",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody SysDept dept) {
      AjaxResult ajaxResult = AjaxResult.success("修改成功");
      boolean flag = true;

      try {
         if (dept == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && dept.getDeptId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("部门id不能为空");
         }

         if (flag) {
            if (StringUtils.isNotEmpty(dept.getDeptName())) {
               SysDept sysDept = this.deptService.selectDeptByDeptName(dept.getDeptName(), dept.getDeptId());
               if (sysDept != null) {
                  flag = false;
                  ajaxResult = AjaxResult.error("部门名称已存在");
               }
            } else {
               flag = false;
               ajaxResult = AjaxResult.error("部门名称不能为空");
            }
         }

         if (flag) {
            if (StringUtils.isNotEmpty(dept.getDeptCode())) {
               SysDept sysDept = this.deptService.selectDeptByDeptCode(dept.getDeptCode(), dept.getDeptId());
               if (sysDept != null) {
                  flag = false;
                  ajaxResult = AjaxResult.error("部门编码已存在");
               }
            } else {
               flag = false;
               ajaxResult = AjaxResult.error("部门编码不能为空");
            }
         }

         if (flag && dept.getParentId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("上级部门不能为空");
         }

         if (flag && StringUtils.equals("1", dept.getStatus()) && this.deptService.selectNormalChildrenDeptById(dept.getDeptId()) > 0) {
            flag = false;
            ajaxResult = AjaxResult.error("有未停用的子部门，不可修改为停用状态");
         }

         if (flag) {
            dept.setUpdateBy(SecurityUtils.getUsername());
            this.deptService.updateDept(dept);
         }
      } catch (Exception e) {
         this.log.error("修改部门信息出现异常", e);
         ajaxResult = AjaxResult.error("修改部门信息出现异常");
      }

      return ajaxResult;
   }

   @ApiOperation("删除部门")
   @ApiImplicitParam(
      name = "deptId",
      value = "部门ID",
      required = true,
      dataType = "Long",
      paramType = "path"
   )
   @PreAuthorize("@ss.hasPermi('system:dept:remove')")
   @Log(
      title = "部门管理",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{deptId}"})
   public AjaxResult remove(@PathVariable Long deptId) {
      if (this.deptService.hasChildByDeptId(deptId)) {
         return AjaxResult.error("存在下级部门,不允许删除");
      } else {
         return this.deptService.checkDeptExistUser(deptId) ? AjaxResult.error("部门存在用户,不允许删除") : this.toAjax(this.deptService.deleteDeptById(deptId));
      }
   }

   @ApiOperation("查询特殊类型部门下拉列表")
   @GetMapping({"/specialTypeList/{sysFlag}"})
   @PreAuthorize("@ss.hasAnyPermi('system:dept:specialTypeList,tmpl:index:add,tmpl:index:edit,mrhp:hp:mrHpDetail,pat:visitinfo:mrhp')")
   public AjaxResult specialTypeList(@PathVariable String sysFlag) {
      try {
         List<SysDept> list = this.deptService.selectSpecialTypeList(sysFlag);
         return AjaxResult.success((Object)list);
      } catch (Exception e) {
         this.log.error("查询特殊类型部门下拉出现异常", e);
         return AjaxResult.error("查询特殊类型部门下拉出现异常");
      }
   }

   @ApiOperation("查询科室")
   @GetMapping({"/tmplDeptOrderList"})
   @PreAuthorize("@ss.hasAnyPermi('system:dept:tmplDeptOrderList,tmpl:auditRecord:queryList,tmpl:index:edit')")
   public AjaxResult selectTmplDeptOrderList(SysDeptVo sysDeptVo) {
      new AjaxResult();

      AjaxResult ajaxResult;
      try {
         if (StringUtils.isEmpty(sysDeptVo.getTempId())) {
            ajaxResult = AjaxResult.error("模板id参数不能为空");
         } else {
            this.startPage();
            List<SysDept> list = this.deptService.selectTmplDeptOrderList(sysDeptVo);
            ajaxResult = AjaxResult.success((Object)list);
         }
      } catch (Exception e) {
         this.log.error("查询特殊类型部门下拉出现异常", e);
         ajaxResult = AjaxResult.error("查询特殊类型部门下拉出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('system:dept:orgDeptList,qc:sche:list')")
   @GetMapping({"/orgDeptList"})
   public AjaxResult orgDeptList(SysDept sysDept) {
      try {
         List<SysDept> list = this.deptService.selectOrgDeptList(sysDept);
         return AjaxResult.success((Object)list);
      } catch (Exception e) {
         this.log.error("查询部门下拉出现异常", e);
         return AjaxResult.error("查询部门下拉出现异常");
      }
   }

   @PreAuthorize("@ss.hasAnyPermi('system:dept:orgDeptList,qc:sche:list')")
   @GetMapping({"/docDeptList/{emplNumber}"})
   public AjaxResult docDeptList(@PathVariable String emplNumber) {
      try {
         List<SysDept> list = this.deptService.selectDocDeptList(emplNumber);
         return AjaxResult.success((Object)list);
      } catch (Exception e) {
         this.log.error("获取某个医师的科室列表出现异常", e);
         return AjaxResult.error("获取某个医师的科室列表出现异常");
      }
   }

   @PreAuthorize("@ss.hasAnyPermi('system:dept:orgDeptList,qc:sche:list,handover:main:info')")
   @GetMapping({"/docDeptList"})
   public AjaxResult docDeptList() {
      try {
         SysUser user = SecurityUtils.getLoginUser().getUser();
         List<SysDept> list = user.getDepts();
         return AjaxResult.success((Object)list);
      } catch (Exception e) {
         this.log.error("获取当前登录账号的科室列表出现异常", e);
         return AjaxResult.error("获取当前登录账号的科室列表出现异常");
      }
   }

   @PreAuthorize("@ss.hasAnyPermi('system:dept:orgHosDeptList,docOrder:order:list')")
   @GetMapping({"/orgHosDeptList"})
   public AjaxResult orgHosDeptList(String patientId, String deptName) {
      try {
         List<SysDept> list = this.deptService.orgHosDeptList(patientId, deptName);
         list = list.subList(0, list.size() > 20 ? 20 : list.size());
         return AjaxResult.success((Object)list);
      } catch (Exception e) {
         this.log.error("获取当前登录账号的科室列表出现异常", e);
         return AjaxResult.error("获取当前登录账号的科室列表出现异常");
      }
   }

   @PreAuthorize("@ss.hasAnyPermi('system:dept:patientDeptList,reportHtml:patient:list')")
   @GetMapping({"/staffDeptList"})
   public AjaxResult staffDeptList(SysOrgVo sysOrgVo) {
      AjaxResult ajaxResult = AjaxResult.success();

      try {
         SysDept dept = SecurityUtils.getLoginUser().getUser().getDept();
         List<TmBsDeptTypeContrast> deptTypeContrastList = this.tmBsDeptTypeContrastService.selectTmBsDeptTypeContrastById(dept.getDeptCode());
         List<String> deptTypeList = (List)deptTypeContrastList.stream().map((t) -> t.getDeptType()).collect(Collectors.toList());
         if (deptTypeList.contains("11")) {
            List<SysDept> list = SecurityUtils.getLoginUser().getUser().getDepts();
            list = list.subList(0, list.size() > 20 ? 20 : list.size());
            ajaxResult.put("data", list);
            ajaxResult.put("dataType", "list");
         } else {
            List<SysOrg> depts = this.sysOrgService.selectSysOrgDeptList(sysOrgVo, false);
            List<TreeSelect> treeSelectList = this.sysOrgService.buildOrgTreeSelect(depts);
            ajaxResult.put("data", treeSelectList);
            ajaxResult.put("dataType", "tree");
         }
      } catch (Exception e) {
         this.log.error("获取当前登录账号的科室列表出现异常", e);
         ajaxResult = AjaxResult.error("获取当前登录账号的科室列表出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('oper:room:takeDrugList,operation:room:refund:deptList,ope:drug:requisition')")
   @GetMapping({"/deptlistByType/{typeCode}"})
   public AjaxResult orgHosDeptList(@PathVariable String typeCode) {
      AjaxResult ajaxResult = AjaxResult.success();

      try {
         List<SysDept> list = this.deptService.selectdeptListByTypeCode(typeCode);
         ajaxResult = AjaxResult.success((Object)list);
      } catch (Exception e) {
         this.log.error("根据科室类别编码查询科室列表出现异常", e);
         ajaxResult = AjaxResult.error("根据科室类别编码查询科室列表出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('system:dept:deptPharmacyList,ope:drug:requisition')")
   @GetMapping({"/deptPharmacyList"})
   public AjaxResult deptPharmacyList() {
      AjaxResult ajaxResult = AjaxResult.success();

      try {
         SysUser sysUser = SecurityUtils.getLoginUser().getUser();
         List<PharmacyVo> pharmacyList = this.drugStockService.selectPharmacyListByDept(sysUser.getHospital().getOrgCode(), sysUser.getDept().getDeptCode());
         ajaxResult = AjaxResult.success((Object)pharmacyList);
      } catch (Exception e) {
         this.log.error("查询某个科室的领药药房列表出现异常", e);
         ajaxResult = AjaxResult.error("查询某个科室的领药药房列表出现异常");
      }

      return ajaxResult;
   }
}
