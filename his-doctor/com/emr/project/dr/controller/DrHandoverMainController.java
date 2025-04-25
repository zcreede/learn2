package com.emr.project.dr.controller;

import com.emr.common.constant.CommonConstants;
import com.emr.common.utils.DateUtils;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.StringUtils;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.dr.domain.DrHandoverMain;
import com.emr.project.dr.domain.vo.DrHandoverMainVo;
import com.emr.project.dr.service.IDrHandoverDetailService;
import com.emr.project.dr.service.IDrHandoverMainService;
import com.emr.project.system.domain.BasEmployee;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.service.ISysOrgService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/handover/main"})
public class DrHandoverMainController extends BaseController {
   @Autowired
   private IDrHandoverMainService drHandoverMainService;
   @Autowired
   private ISysOrgService sysOrgService;
   @Autowired
   private IDrHandoverDetailService drHandoverDetailService;

   @PreAuthorize("@ss.hasPermi('handover:main:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(DrHandoverMainVo drHandoverMainVo) {
      this.startPage();
      List<DrHandoverMainVo> list = null;

      try {
         list = this.drHandoverMainService.selectDrHandoverMainList(drHandoverMainVo);
      } catch (Exception e) {
         this.log.error("查询交班信息出现异常，", e);
      }

      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('handover:main:info')")
   @GetMapping({"/init"})
   public AjaxResult init(String deptCd) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         if (StringUtils.isNotBlank(deptCd)) {
            DrHandoverMain drHandoverMain = this.drHandoverMainService.selectLastDrHandoverMain(deptCd);
            ajaxResult = AjaxResult.success("查询成功", drHandoverMain);
         }
      } catch (Exception e) {
         this.log.error("打开录入交接班调用的方法出现异常,", e);
         ajaxResult = AjaxResult.success("打开录入交接班调用的方法出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('handover:main:info')")
   @GetMapping({"/info"})
   public AjaxResult getInfo(String deptCd, String handoverDate, Long schemeCd) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");
      boolean flag = true;

      try {
         if (flag && StringUtils.isBlank(deptCd)) {
            flag = false;
            ajaxResult = AjaxResult.error("科室不能为空，请先选择交接班科室。");
         }

         if (flag && StringUtils.isBlank(handoverDate)) {
            flag = false;
            ajaxResult = AjaxResult.error("交接日期不能为空，请先选择交接日期。");
         }

         if (flag && schemeCd == null) {
            flag = false;
            ajaxResult = AjaxResult.error("班次不能为空，请先选择班次。");
         }

         if (flag) {
            Date handoverDateD = DateUtils.parseDate(handoverDate, new String[]{DateUtils.YYYY_MM_DD});
            DrHandoverMainVo drHandoverMainVo = this.drHandoverMainService.selectHandoverByInfo(deptCd, handoverDateD, schemeCd);
            ajaxResult = AjaxResult.success("查询成功", drHandoverMainVo);
         }
      } catch (Exception e) {
         this.log.error("获取【指定科室、日期、班次的交班】详细信息出现异常：", e);
         ajaxResult = AjaxResult.error("获取【指定科室、日期、班次的交班】详细信息出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('handover:main:add')")
   @Log(
      title = "新增交班",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody DrHandoverMain drHandoverMain) {
      AjaxResult ajaxResult = AjaxResult.success();
      Boolean flag = true;

      try {
         if (flag && StringUtils.isBlank(drHandoverMain.getDeptCd())) {
            flag = false;
            ajaxResult = AjaxResult.error("科室编码不能为空，请选择交接班科室");
         }

         List<DrHandoverMain> list = flag ? this.drHandoverMainService.selectNoDrHandoverMain(drHandoverMain.getDeptCd()) : null;
         if (flag && list != null && !list.isEmpty()) {
            flag = false;
            ajaxResult = AjaxResult.error("当前科室有未交班的交班记录，不能新建交班");
         }

         if (flag) {
            List<DrHandoverMainVo> mainList = this.drHandoverMainService.selectDrHandoverMainBySchemeCd(drHandoverMain.getSchemeCd(), drHandoverMain.getDeptCd(), drHandoverMain.getHandoverDate());
            if (mainList.size() >= 1) {
               flag = Boolean.FALSE;
               ajaxResult = AjaxResult.error("该交班班次中当前时间当前科室已有交班记录，不能重复新建交班");
            }
         }

         if (flag && StringUtils.isBlank(drHandoverMain.getDeptName())) {
            flag = false;
            ajaxResult = AjaxResult.error("科室编码不能为空，请选择交接班科室");
         }

         if (flag && drHandoverMain.getHandoverDate() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("交班日期不能为空，请选择交班日期");
         }

         if (flag && drHandoverMain.getSchemeCd() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("班次编码不能为空，请选择班次");
         }

         if (flag && StringUtils.isBlank(drHandoverMain.getSchemeName())) {
            flag = false;
            ajaxResult = AjaxResult.error("班次名称不能为空，请选择班次");
         }

         if (flag) {
            this.drHandoverMainService.insertDrHandoverMain(drHandoverMain);
            ajaxResult = AjaxResult.success("新建交班成功", drHandoverMain);
         }
      } catch (Exception e) {
         this.log.error("新增交班出现异常，", e);
         ajaxResult = AjaxResult.error("新增交班出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('handover:main:edit')")
   @Log(
      title = "【请填写功能名称】",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody DrHandoverMain main) {
      AjaxResult ajaxResult = AjaxResult.success("交班成功");
      Boolean flag = true;

      try {
         if (flag && main.getId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("交班id不能为空，请选择交班信息");
         }

         if (flag && StringUtils.isBlank(main.getJoinDocCd())) {
            flag = false;
            ajaxResult = AjaxResult.error("接班医师不能为空，请选择接班医师");
         }

         if (flag && StringUtils.isBlank(main.getJoinDocName())) {
            flag = false;
            ajaxResult = AjaxResult.error("接班医师不能为空，请选择接班医师");
         }

         DrHandoverMain drHandoverMain = flag ? this.drHandoverMainService.selectDrHandoverMainById(main.getId()) : null;
         if (flag && drHandoverMain == null) {
            flag = false;
            ajaxResult = AjaxResult.error("没有这个交班信息，请选择交班信息");
         }

         if (flag && drHandoverMain.getState().equals("1")) {
            flag = false;
            ajaxResult = AjaxResult.error("当前交班信息已交班，不能重复交班");
         }

         if (flag) {
            DrHandoverMain param = new DrHandoverMain();
            param.setId(main.getId());
            param.setState("1");
            param.setJoinDocCd(main.getJoinDocCd());
            param.setJoinDocName(main.getJoinDocName());
            this.drHandoverMainService.updateState(param);
            DrHandoverMain mainInfo = this.drHandoverMainService.selectDrHandoverMainById(main.getId());
            ajaxResult.put("mainInfo", mainInfo);
         }
      } catch (Exception e) {
         this.log.error("交班出现异常,", e);
         ajaxResult = AjaxResult.error("交班出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('handover:main:cancel')")
   @Log(
      title = "【请填写功能名称】",
      businessType = BusinessType.UPDATE
   )
   @PutMapping({"/cancel"})
   public AjaxResult cancel(@RequestBody DrHandoverMain main) {
      AjaxResult ajaxResult = AjaxResult.success("取消交班成功");
      Boolean flag = true;

      try {
         if (flag && main.getId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("交班id不能为空，请选择交班信息");
         }

         DrHandoverMain drHandoverMain = flag ? this.drHandoverMainService.selectDrHandoverMainById(main.getId()) : null;
         if (flag && drHandoverMain == null) {
            flag = false;
            ajaxResult = AjaxResult.error("没有这个交班信息，请选择交班信息");
         }

         if (flag && drHandoverMain.getState().equals("0")) {
            flag = false;
            ajaxResult = AjaxResult.error("当前交班信息不是交班状态，不能取消交班");
         }

         Boolean isCancel = this.drHandoverMainService.checkCancel(drHandoverMain);
         if (flag && isCancel) {
            flag = false;
            ajaxResult = AjaxResult.error("当前交班之后日期有交班记录，本次交班无法取消!");
         }

         SysUser sysUser = SecurityUtils.getLoginUser().getUser();
         if (flag && !drHandoverMain.getShiftDocCd().equals(sysUser.getBasEmployee().getEmplNumber())) {
            flag = false;
            ajaxResult = AjaxResult.error("非交班医师不能取消交班记录");
         }

         if (flag) {
            DrHandoverMain param = new DrHandoverMain();
            param.setId(main.getId());
            param.setState("0");
            this.drHandoverMainService.updateDrHandoverMain(param);
         }
      } catch (Exception e) {
         this.log.error("取消交班出现异常,", e);
         ajaxResult = AjaxResult.error("取消交班出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('handover:main:remove')")
   @Log(
      title = "删除交班信息",
      businessType = BusinessType.DELETE
   )
   @PutMapping({"/{id}"})
   public AjaxResult remove(@PathVariable Long id) {
      AjaxResult ajaxResult = AjaxResult.success("删除成功");
      Boolean flag = true;

      try {
         DrHandoverMain drHandoverMain = this.drHandoverMainService.selectDrHandoverMainById(id);
         if (flag && drHandoverMain.getState().equals("1")) {
            flag = false;
            ajaxResult = AjaxResult.error("当前交班信息已是交班状态，不能删除交班记录");
         }

         BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
         if (flag && !drHandoverMain.getShiftDocCd().equals(basEmployee.getEmplNumber())) {
            flag = false;
            ajaxResult = AjaxResult.error("不能删除非本人创建的交班记录");
         }

         if (flag) {
            ajaxResult = this.toAjax(this.drHandoverMainService.deleteDrHandoverMainById(id));
         }
      } catch (Exception e) {
         this.log.error("删除交班信息出现异常", e);
         ajaxResult = AjaxResult.error("删除交班信息出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('handover:main:saveNum')")
   @Log(
      title = "保存交班的患者信息",
      businessType = BusinessType.UPDATE
   )
   @PostMapping({"/saveNum"})
   public AjaxResult savePatientNum(@RequestBody DrHandoverMain drHandoverMain) {
      AjaxResult ajaxResult = AjaxResult.success();
      Boolean flag = true;

      try {
         if (flag && drHandoverMain.getId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("交班id不能为空，请选择交班信息");
         }

         DrHandoverMain drHandover = flag ? this.drHandoverMainService.selectDrHandoverMainById(drHandoverMain.getId()) : null;
         if (flag && drHandover == null) {
            flag = false;
            ajaxResult = AjaxResult.error("没有这个交班信息，请选择交班信息");
         }

         if (flag && drHandover.getState().equals("1")) {
            flag = false;
            ajaxResult = AjaxResult.error("当前记录已交班，不能修改患者人数");
         }

         if (flag) {
            this.drHandoverMainService.updateDrHandoverMain(drHandoverMain);
         }
      } catch (Exception e) {
         this.log.error("保存交班的患者信息出现异常,", e);
         ajaxResult = AjaxResult.error("保存交班的患者信息出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('handover:main:info')")
   @GetMapping({"/recentRecord"})
   public AjaxResult recentRecord(DrHandoverMain drHandoverMain) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");
      boolean flag = true;

      try {
         if (drHandoverMain == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(drHandoverMain.getDeptCd())) {
            flag = false;
            ajaxResult = AjaxResult.error("科室编码不能为空");
         }

         if (flag) {
            List<DrHandoverMainVo> list = this.drHandoverMainService.selectRecentRecordList(drHandoverMain);
            ajaxResult = AjaxResult.success((Object)list);
         }
      } catch (Exception e) {
         this.log.error("查询近七天交接班记录出现异常", e);
         ajaxResult = AjaxResult.error("查询近七天交接班记录出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('handover:main:recordList')")
   @GetMapping({"/recordList"})
   public TableDataInfo recordList(DrHandoverMainVo drHandoverMainVo) {
      new TableDataInfo();

      TableDataInfo tableDataInfo;
      try {
         this.startPage();
         List<DrHandoverMainVo> list = this.drHandoverMainService.selectDoverMainRecordList(drHandoverMainVo);
         tableDataInfo = this.getDataTable(list);
         tableDataInfo.setResult(this.sysOrgService.selectSysOrgById(CommonConstants.SYSTEM.ORG_ID).getOrgName());
      } catch (Exception e) {
         this.log.error("查询交接班记录出现异常", e);
         tableDataInfo = new TableDataInfo(500, "查询交接班记录出现异常");
      }

      return tableDataInfo;
   }
}
