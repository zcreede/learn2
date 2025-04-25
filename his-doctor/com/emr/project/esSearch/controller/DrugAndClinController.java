package com.emr.project.esSearch.controller;

import com.alibaba.fastjson.JSONArray;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.StringUtils;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.docOrder.domain.vo.DrugAndClinPatientVo;
import com.emr.project.docOrder.domain.vo.ItemIsOpenResVo;
import com.emr.project.docOrder.domain.vo.OrderSearchVo;
import com.emr.project.docOrder.service.ITdPaOrderItemService;
import com.emr.project.esSearch.domain.DrugAndClin;
import com.emr.project.esSearch.domain.vo.DrugAndClinExtendVo;
import com.emr.project.esSearch.domain.vo.DrugAndClinSearchVo;
import com.emr.project.esSearch.service.IDrugAndClinService;
import com.emr.project.esSearch.service.IDrugStockService;
import com.emr.project.his.domain.vo.DrugDoseVo;
import com.emr.project.his.domain.vo.PharmacyVo;
import com.emr.project.pat.domain.vo.VisitinfoPersonalVo;
import com.emr.project.pat.service.IVisitinfoService;
import com.emr.project.system.domain.SysUser;
import com.emr.project.tmpa.service.IVYyBzbmDrugClinService;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zxp.esclientrhl.repository.PageList;

@RestController
@RequestMapping({"/es/durgclin"})
public class DrugAndClinController extends BaseController {
   @Autowired
   private IDrugAndClinService drugAndClinService;
   @Autowired
   private IVisitinfoService visitinfoService;
   @Autowired
   private IVYyBzbmDrugClinService bzbmDrugClinService;
   @Autowired
   private IDrugStockService drugStockService;
   @Autowired
   private ITdPaOrderItemService tdPaOrderItemService;

   @PreAuthorize("@ss.hasAnyPermi('es:durgclin:list')")
   @GetMapping({"/sync"})
   public AjaxResult syncDrugAndClinAllToEs() {
      AjaxResult ajaxResult = AjaxResult.success();

      try {
         SysUser user = SecurityUtils.getLoginUser().getUser();
         (new StringBuilder()).append("drugandclin_zy_").append(user.getUserName()).toString();
         List<String> execDeptCdList = Arrays.asList("01,02,03");
         this.drugAndClinService.syncDrugAndClinAllToEs(execDeptCdList, "", "0", "01");
      } catch (Exception e) {
         this.log.error("创建当前患者的医嘱开立项目索引出现异常，", e);
         ajaxResult = AjaxResult.error("创建当前患者的医嘱开立项目索引出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('es:durgclin:syncReload,docOrder:order:add,ope:drug:requisition')")
   @GetMapping({"/syncReload"})
   public AjaxResult syncDrugAndClinAllToEsReload(String patientId) {
      AjaxResult ajaxResult = AjaxResult.success();

      try {
         if (StringUtils.isBlank(patientId)) {
            ajaxResult = AjaxResult.error("参数不能为空");
         } else {
            SysUser sysUser = SecurityUtils.getLoginUser().getUser();
            List<PharmacyVo> pharmacyList = this.drugStockService.selectPharmacyListByDept(sysUser.getHospital().getOrgCode(), sysUser.getDept().getDeptCode());
            if (pharmacyList != null && pharmacyList.size() > 0) {
               List<String> execDeptCdList = (List)pharmacyList.stream().map((t) -> t.getPharmacyCode()).collect(Collectors.toList());
               VisitinfoPersonalVo visitinfoPersonalVo = this.visitinfoService.selectVisitinfoPersonalById(patientId);
               String expenseCategoryNo = visitinfoPersonalVo.getPayTypeCd();
               this.drugAndClinService.syncDrugAndClinAllToEsReload(execDeptCdList, expenseCategoryNo);
            }
         }
      } catch (Exception e) {
         this.log.error("创建当前患者的医嘱开立项目索引出现异常，", e);
         ajaxResult = AjaxResult.error("创建当前患者的医嘱开立项目索引出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('es:durgclin:list')")
   @PostMapping({"/updateXcsl"})
   public AjaxResult updateDurgXcsl(@RequestBody List drugDoseVoList) {
      AjaxResult ajaxResult = AjaxResult.success("更新es中药品的虚存数量成功");

      try {
         if (drugDoseVoList != null && !drugDoseVoList.isEmpty()) {
            this.drugAndClinService.updateDurgXcsl(drugDoseVoList);
         }
      } catch (Exception e) {
         this.log.error("更新es中药品的虚存数量出现异常，", e);
         ajaxResult = AjaxResult.error("更新es中药品的虚存数量出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('es:durgclin:list,ope:drug:requisition')")
   @GetMapping({"/list"})
   public AjaxResult queryDrugAndClinList(DrugAndClinSearchVo drugAndClinSearchVo) {
      PageList<DrugAndClinExtendVo> extendVoPageList = null;

      try {
         PageList<DrugAndClin> list = null;
         if (StringUtils.isNotBlank(drugAndClinSearchVo.getOpeFlag()) && drugAndClinSearchVo.getOpeFlag().equals("1")) {
            String keyWordUpper = StringUtils.isNotBlank(drugAndClinSearchVo.getKeyWord()) ? drugAndClinSearchVo.getKeyWord().toUpperCase() : null;
            drugAndClinSearchVo.setKeyWordUpper(keyWordUpper);
            this.startPage();
            List<DrugAndClin> drugAndClinList = this.drugStockService.selectDrugListForOperation(drugAndClinSearchVo);
            TableDataInfo tableDataInfo = this.getDataTable(drugAndClinList);
            if (drugAndClinList != null) {
               List<DrugAndClin> drugAndClinListTemp = tableDataInfo.getRows();
               List<DrugAndClinExtendVo> extendVoList = this.bzbmDrugClinService.queryDrugAndClinExtendInfo(drugAndClinListTemp);
               extendVoPageList = new PageList();
               extendVoPageList.setCurrentPage(drugAndClinSearchVo.getPageNum());
               extendVoPageList.setPageSize(drugAndClinSearchVo.getPageSize());
               extendVoPageList.setSortValues((Object[])null);
               extendVoPageList.setList(extendVoList);
               extendVoPageList.setTotalElements(tableDataInfo.getTotal());
               long a = tableDataInfo.getTotal() % Long.valueOf(drugAndClinSearchVo.getPageSize() + "");
               long b = tableDataInfo.getTotal() / Long.valueOf(drugAndClinSearchVo.getPageSize() + "");
               b = a == 0L ? b : b + 1L;
               int c = Integer.valueOf(b + "");
               extendVoPageList.setTotalPages(c);
            }
         } else {
            list = this.drugAndClinService.queryDrugAndClinPageList(drugAndClinSearchVo, drugAndClinSearchVo.getPageNum(), drugAndClinSearchVo.getPageSize());
            if (list != null) {
               List<DrugAndClinExtendVo> extendVoList = this.bzbmDrugClinService.queryDrugAndClinExtendInfo(list.getList());
               extendVoPageList = new PageList();
               extendVoPageList.setCurrentPage(list.getCurrentPage());
               extendVoPageList.setPageSize(list.getPageSize());
               extendVoPageList.setSortValues(list.getSortValues());
               extendVoPageList.setList(extendVoList);
               extendVoPageList.setTotalElements(list.getTotalElements());
               extendVoPageList.setTotalPages(list.getTotalPages());
            }
         }
      } catch (Exception e) {
         this.log.error("查询出现异常", e);
         return AjaxResult.error("查询出现异常");
      }

      return AjaxResult.success("查询成功", extendVoPageList);
   }

   @PreAuthorize("@ss.hasAnyPermi('es:durgclin:list,docOrder:check:list')")
   @GetMapping({"/checkList"})
   public AjaxResult queryCheckList(HttpServletRequest request, DrugAndClinSearchVo param) {
      PageList<DrugAndClin> list = null;

      try {
         list = this.drugAndClinService.selectQueryCheckList(param, param.getPageNum(), param.getPageSize());
      } catch (Exception e) {
         this.log.error("查询检查检验项目集合出现异常", e);
         return AjaxResult.error("查询检查检验项目集合出现异常");
      }

      return AjaxResult.success("查询成功", list);
   }

   @PreAuthorize("@ss.hasAnyPermi('docOrder:herb:list,docOrder:order:list')")
   @GetMapping({"/drugInfoByCd"})
   public AjaxResult drugInfoByCd(DrugAndClinPatientVo drugAndClin) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");
      boolean flag = true;

      try {
         if (drugAndClin == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(drugAndClin.getCpNo())) {
            flag = false;
            ajaxResult = AjaxResult.error("药品编码不能为空");
         }

         if (flag && StringUtils.isEmpty(drugAndClin.getPatientId())) {
            flag = false;
            ajaxResult = AjaxResult.error("患者id不能为空");
         }

         if (flag) {
            DrugAndClin result = this.drugAndClinService.selectDrugInfoByCd(drugAndClin);
            ajaxResult = AjaxResult.success((Object)result);
            if (StringUtils.isNotEmpty(result.getCpNo())) {
               DrugAndClinPatientVo drugAndClinPatientVo = new DrugAndClinPatientVo();
               BeanUtils.copyProperties(result, drugAndClinPatientVo);
               drugAndClinPatientVo.setPatientId(drugAndClin.getPatientId());
               drugAndClinPatientVo.setOrderStartTime(drugAndClin.getOrderStartTime());
               ItemIsOpenResVo itemIsOpenResVo = this.tdPaOrderItemService.selectItemIsOpen(drugAndClinPatientVo);
               ajaxResult.put("itemIsOpenResVo", itemIsOpenResVo);
            }
         }
      } catch (Exception e) {
         this.log.error("根据药品编码和领药药房查询药品信息出现异常", e);
         ajaxResult = AjaxResult.error("根据药品编码和领药药房查询药品信息出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('docOrder:order:list')")
   @PostMapping({"/drugInfoByIds"})
   public AjaxResult drugInfoByIds(@RequestBody List orderSearchVoList) {
      AjaxResult ajaxResult = AjaxResult.success();
      boolean flag = true;

      try {
         if (flag && (orderSearchVoList == null || orderSearchVoList != null && orderSearchVoList.isEmpty())) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         for(int i = 0; flag && i < orderSearchVoList.size(); ++i) {
            OrderSearchVo orderSearchVo = (OrderSearchVo)orderSearchVoList.get(i);
            if (StringUtils.isBlank(orderSearchVo.getCpNo())) {
               flag = false;
               ajaxResult = AjaxResult.error("查询【" + orderSearchVo.getCpName() + "】的虚存数量/当前项目信息，项目编号不能为空");
               break;
            }

            if (StringUtils.isBlank(orderSearchVo.getOrderClassCode())) {
               flag = false;
               ajaxResult = AjaxResult.error("查询【" + orderSearchVo.getCpName() + "】的虚存数量/当前项目信息，医嘱类别不能为空");
               break;
            }

            if (orderSearchVo.getOrderClassCode().equals("01") && StringUtils.isBlank(orderSearchVo.getDetailPerformDepCode())) {
               flag = false;
               ajaxResult = AjaxResult.error("查询【" + orderSearchVo.getCpName() + "】的虚存数量，执行科室不能为空");
               break;
            }

            if (orderSearchVo.getOrderDose() == null) {
               flag = false;
               ajaxResult = AjaxResult.error("查询【" + orderSearchVo.getCpName() + "】的虚存数量/当前项目信息，数量不能为空");
               break;
            }
         }

         if (flag) {
            JSONArray resArr = this.drugAndClinService.drugInfoByIds(orderSearchVoList);
            ajaxResult = AjaxResult.success((Object)resArr);
         }
      } catch (Exception e) {
         this.log.error("查询药品虚存信息/当前项目信息出现异常", e);
         ajaxResult = AjaxResult.error("查询药品虚存信息/当前项目信息出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('docOrder:order:list')")
   @PostMapping({"/getIndexSting"})
   public AjaxResult getIndexSting(String index, String setting) {
      AjaxResult ajaxResult = AjaxResult.success();
      boolean flag = true;

      try {
         if (flag && StringUtils.isBlank(index)) {
            flag = false;
            ajaxResult = AjaxResult.error("索引名称不能为空");
         }

         if (flag && StringUtils.isBlank(setting)) {
            flag = false;
            ajaxResult = AjaxResult.error("索引信息不能为空");
         }

         if (flag) {
            String settingStr = this.drugAndClinService.getSetting(index, setting);
            ajaxResult.put("settingStr", settingStr);
         }
      } catch (Exception e) {
         this.log.error("查询索引设置出现异常", e);
         ajaxResult = AjaxResult.error("查询索引设置出现异常");
      }

      return ajaxResult;
   }
}
