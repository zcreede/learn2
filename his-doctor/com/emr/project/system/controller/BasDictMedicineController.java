package com.emr.project.system.controller;

import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.project.system.domain.BasDictMedicine;
import com.emr.project.system.domain.SysDept;
import com.emr.project.system.domain.vo.BasDictMedicineVo;
import com.emr.project.system.mapper.SysDeptMapper;
import com.emr.project.system.service.IBasDictMedicineService;
import java.util.List;
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

@RestController
@RequestMapping({"/system/medicine"})
public class BasDictMedicineController extends BaseController {
   @Autowired
   private IBasDictMedicineService basDictMedicineService;
   @Autowired
   private SysDeptMapper sysDeptMapper;

   @PreAuthorize("@ss.hasAnyPermi('system:medicine:list,tmpl:index:add,tmpl:auditRecord:queryList,tmpl:index:edit')")
   @GetMapping({"/list"})
   public AjaxResult list(BasDictMedicine basDictMedicine) {
      AjaxResult ajaxResult = AjaxResult.success();

      try {
         List<BasDictMedicine> list = this.basDictMedicineService.selectBasDictMedicineTree(basDictMedicine);
         ajaxResult.put("treeList", list);
      } catch (Exception e) {
         this.log.error("查询专科字典树出现异常, ", e);
         ajaxResult = AjaxResult.error("查询专科字典树出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('system:medicine:list,tmpl:index:add,tmpl:auditRecord:queryList,tmpl:index:edit')")
   @GetMapping({"/listByDept"})
   public AjaxResult listByDept(BasDictMedicine basDictMedicine) {
      AjaxResult ajaxResult = AjaxResult.success();

      try {
         List<BasDictMedicine> list = this.basDictMedicineService.selectBasDictMedicineTreeByDept(basDictMedicine);
         ajaxResult.put("treeList", list);
      } catch (Exception e) {
         this.log.error("查询专科字典树出现异常, ", e);
         ajaxResult = AjaxResult.error("查询专科字典树出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('system:medicine:export')")
   @Log(
      title = "专科字典",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(BasDictMedicine basDictMedicine) {
      List<BasDictMedicine> list = this.basDictMedicineService.selectBasDictMedicineList(basDictMedicine);
      ExcelUtil<BasDictMedicine> util = new ExcelUtil(BasDictMedicine.class);
      return util.exportExcel(list, "专科字典数据");
   }

   @PreAuthorize("@ss.hasAnyPermi('system:medicine:query,system:medicine:edit')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.basDictMedicineService.selectBasDictMedicineById(id));
   }

   @PreAuthorize("@ss.hasPermi('system:medicine:add')")
   @Log(
      title = "专科字典",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody BasDictMedicineVo basDictMedicine) {
      AjaxResult ajaxResult = AjaxResult.success("新增成功");
      boolean flag = true;

      try {
         if (flag && basDictMedicine == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && basDictMedicine.getSoleDeptCodeList() != null && basDictMedicine.getSoleDeptCodeList().size() > 0 && !basDictMedicine.getDeptCodeList().contains("000000") && basDictMedicine.getLevelCd().equals("2")) {
            List<String> deptCodeList = basDictMedicine.getDeptCodeList();
            List<String> soleDeptCodeList = basDictMedicine.getSoleDeptCodeList();
            StringBuffer soleIndeptCodeS = new StringBuffer();
            StringBuffer soleDeptCodeYszS = new StringBuffer();
            boolean soleIndeptCode = false;
            boolean soleDeptCodeYsz = false;

            for(String soleDeptCode : soleDeptCodeList) {
               if (!deptCodeList.contains(soleDeptCode)) {
                  soleIndeptCode = true;
                  SysDept sysDept = this.sysDeptMapper.getOneByCode(soleDeptCode);
                  soleIndeptCodeS = soleIndeptCodeS.append(sysDept.getDeptName());
                  soleIndeptCodeS = soleIndeptCodeS.append("、");
               }
            }

            if (soleIndeptCode) {
               flag = false;
               ajaxResult = AjaxResult.error("选择的归属科室" + soleIndeptCodeS + "未包含在适用科室范围内");
            }

            if (flag) {
               for(String soleDeptCode : soleDeptCodeList) {
                  BasDictMedicine basDictMedicine1 = new BasDictMedicine();
                  basDictMedicine1.setSoleDeptCode(soleDeptCode);
                  basDictMedicine1.setSoleDeptCodeId(basDictMedicine.getId());
                  List<BasDictMedicine> basDictMedicineList = this.basDictMedicineService.selectBasDictMedicineList(basDictMedicine1);
                  if (basDictMedicineList != null && basDictMedicineList.size() > 0) {
                     soleDeptCodeYsz = true;
                     SysDept sysDept = this.sysDeptMapper.getOneByCode(soleDeptCode);
                     soleDeptCodeYszS = soleDeptCodeYszS.append(sysDept.getDeptName() + "[" + ((BasDictMedicine)basDictMedicineList.get(0)).getName() + "]");
                     soleDeptCodeYszS = soleDeptCodeYszS.append("、");
                  }
               }

               if (soleDeptCodeYsz) {
                  flag = false;
                  ajaxResult = AjaxResult.error("归属科室" + soleDeptCodeYszS + "已在二级专科中进行过设置，不允许重复设置");
               }
            }
         }

         if (flag && StringUtils.isBlank(basDictMedicine.getName())) {
            flag = false;
            ajaxResult = AjaxResult.error("请填写专科名称");
         }

         if (flag && StringUtils.isBlank(basDictMedicine.getInputstrphtc())) {
            flag = false;
            ajaxResult = AjaxResult.error("请填写专科名称拼音码");
         }

         if (flag && StringUtils.isBlank(basDictMedicine.getLevelCd())) {
            flag = false;
            ajaxResult = AjaxResult.error("请填写专科类别");
         }

         if (flag && StringUtils.isBlank(basDictMedicine.getValidFlag())) {
            flag = false;
            ajaxResult = AjaxResult.error("请填写专科启用状态");
         }

         if (flag && StringUtils.isNotBlank(basDictMedicine.getLevelCd()) && basDictMedicine.getLevelCd().equals("2") && CollectionUtils.isEmpty(basDictMedicine.getDeptCodeList())) {
            flag = false;
            ajaxResult = AjaxResult.error("请填写专科适用的科室");
         }

         if (flag && StringUtils.isNotBlank(basDictMedicine.getLevelCd()) && basDictMedicine.getLevelCd().equals("2") && CollectionUtils.isNotEmpty(basDictMedicine.getDeptCodeList()) && basDictMedicine.getCode().equals("1111") && basDictMedicine.getDeptCodeList().size() != 1 && !basDictMedicine.getDeptCodeList().contains("000000")) {
            flag = false;
            ajaxResult = AjaxResult.error("全院专科，适用科室只能选择全院科室");
         }

         if (flag) {
            BasDictMedicine basDictMedicine1 = this.basDictMedicineService.selectBasDictMedicineByName(basDictMedicine.getName());
            if (basDictMedicine1 != null) {
               flag = false;
               ajaxResult = AjaxResult.error("专科名称已存在");
            }
         }

         if (flag) {
            BasDictMedicine basDictMedicineRes = this.basDictMedicineService.insertBasDictMedicine(basDictMedicine);
            ajaxResult = AjaxResult.success("新增成功", basDictMedicineRes);
         }
      } catch (Exception e) {
         this.log.error("新增专科字典出现异常,", e);
         ajaxResult = AjaxResult.error("新增专科字典出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('system:medicine:edit')")
   @Log(
      title = "专科字典",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody BasDictMedicineVo basDictMedicine) {
      AjaxResult ajaxResult = AjaxResult.success("修改成功");
      boolean flag = true;

      try {
         if (flag && basDictMedicine == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && basDictMedicine.getId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && basDictMedicine.getSoleDeptCodeList() != null && basDictMedicine.getSoleDeptCodeList().size() > 0 && !basDictMedicine.getDeptCodeList().contains("000000") && basDictMedicine.getLevelCd().equals("2")) {
            List<String> deptCodeList = basDictMedicine.getDeptCodeList();
            List<String> soleDeptCodeList = basDictMedicine.getSoleDeptCodeList();
            StringBuffer soleIndeptCodeS = new StringBuffer();
            StringBuffer soleDeptCodeYszS = new StringBuffer();
            boolean soleIndeptCode = false;
            boolean soleDeptCodeYsz = false;

            for(String soleDeptCode : soleDeptCodeList) {
               if (!deptCodeList.contains(soleDeptCode)) {
                  soleIndeptCode = true;
                  SysDept sysDept = this.sysDeptMapper.getOneByCode(soleDeptCode);
                  soleIndeptCodeS = soleIndeptCodeS.append(sysDept.getDeptName());
                  soleIndeptCodeS = soleIndeptCodeS.append("、");
               }
            }

            if (soleIndeptCode) {
               flag = false;
               ajaxResult = AjaxResult.error("选择的归属科室" + soleIndeptCodeS + "未包含在适用科室范围内");
            }

            if (flag) {
               for(String soleDeptCode : soleDeptCodeList) {
                  BasDictMedicine basDictMedicine1 = new BasDictMedicine();
                  basDictMedicine1.setSoleDeptCode(soleDeptCode);
                  basDictMedicine1.setSoleDeptCodeId(basDictMedicine.getId());
                  List<BasDictMedicine> basDictMedicineList = this.basDictMedicineService.selectBasDictMedicineList(basDictMedicine1);
                  if (basDictMedicineList != null && basDictMedicineList.size() > 0) {
                     soleDeptCodeYsz = true;
                     SysDept sysDept = this.sysDeptMapper.getOneByCode(soleDeptCode);
                     soleDeptCodeYszS = soleDeptCodeYszS.append(sysDept.getDeptName() + "[" + ((BasDictMedicine)basDictMedicineList.get(0)).getName() + "]");
                     soleDeptCodeYszS = soleDeptCodeYszS.append("、");
                  }
               }

               if (soleDeptCodeYsz) {
                  flag = false;
                  ajaxResult = AjaxResult.error("归属科室" + soleDeptCodeYszS + "已在二级专科中进行过设置，不允许重复设置");
               }
            }
         }

         if (flag && StringUtils.isBlank(basDictMedicine.getCode())) {
            flag = false;
            ajaxResult = AjaxResult.error("请填写专科代码");
         }

         if (flag && StringUtils.isBlank(basDictMedicine.getName())) {
            flag = false;
            ajaxResult = AjaxResult.error("请填写专科名称");
         }

         if (flag && StringUtils.isBlank(basDictMedicine.getInputstrphtc())) {
            flag = false;
            ajaxResult = AjaxResult.error("请填写专科名称拼音码");
         }

         if (flag && StringUtils.isBlank(basDictMedicine.getLevelCd())) {
            flag = false;
            ajaxResult = AjaxResult.error("请填写专科类别");
         }

         if (flag && StringUtils.isBlank(basDictMedicine.getValidFlag())) {
            flag = false;
            ajaxResult = AjaxResult.error("请填写专科启用状态");
         }

         if (flag) {
            BasDictMedicine basDictMedicine1 = this.basDictMedicineService.selectBasDictMedicineByName(basDictMedicine.getName());
            if (basDictMedicine1 != null && !basDictMedicine1.getId().equals(basDictMedicine.getId())) {
               flag = false;
               ajaxResult = AjaxResult.error("专科名称已存在");
            }
         }

         if (flag) {
            this.basDictMedicineService.updateBasDictMedicine(basDictMedicine);
         }
      } catch (Exception e) {
         this.log.error("修改专科字典出现异常,", e);
         ajaxResult = AjaxResult.error("修改专科字典出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('system:medicine:remove')")
   @Log(
      title = "专科字典",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{id}"})
   public AjaxResult remove(@PathVariable Long id) {
      return this.toAjax(this.basDictMedicineService.deleteBasDictMedicineById(id));
   }
}
