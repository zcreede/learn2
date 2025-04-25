package com.emr.project.qc.controller;

import com.emr.common.utils.DateUtils;
import com.emr.common.utils.StringUtils;
import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.PageDomain;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.framework.web.page.TableSupport;
import com.emr.project.qc.domain.QcBillRecord;
import com.emr.project.qc.domain.vo.QcBillRecordVo;
import com.emr.project.qc.domain.vo.RunTimeQcCheckVo;
import com.emr.project.qc.service.IQcBillRecordService;
import com.emr.project.system.service.ISysEmrConfigService;
import java.util.Calendar;
import java.util.Date;
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
@RequestMapping({"/qc/bill"})
public class QcBillRecordController extends BaseController {
   @Autowired
   private IQcBillRecordService qcBillRecordService;
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;

   @PreAuthorize("@ss.hasAnyPermi('qc:record:list,qc:rt:checked')")
   @GetMapping({"/list"})
   public TableDataInfo list(QcBillRecordVo qcBillRecord) {
      TableDataInfo tableDataInfo = new TableDataInfo();
      List<QcBillRecordVo> list = null;

      try {
         Date qcDateEnd = qcBillRecord.getQcDateEnd();
         if (qcDateEnd != null) {
            qcDateEnd = DateUtils.addDays(qcDateEnd, 1);
            qcBillRecord.setQcDateEnd(qcDateEnd);
         }

         List<QcBillRecordVo> listAll = this.qcBillRecordService.selectQcBillRecordVoList(qcBillRecord);
         PageDomain pageDomain = TableSupport.buildPageRequest();
         Integer pageNum = pageDomain.getPageNum();
         Integer pageSize = pageDomain.getPageSize();
         list = this.qcBillRecordService.selectQcBillRecordVoListPage(listAll, pageNum, pageSize);
         tableDataInfo = this.getDataTable(list);
         tableDataInfo.setTotal((long)listAll.size());
      } catch (Exception e) {
         this.log.error("查询质控抽查单列表出现异常：", e);
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasPermi('qc:record:export')")
   @Log(
      title = "质控抽查单",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(QcBillRecord qcBillRecord) {
      List<QcBillRecord> list = this.qcBillRecordService.selectQcBillRecordList(qcBillRecord);
      ExcelUtil<QcBillRecord> util = new ExcelUtil(QcBillRecord.class);
      return util.exportExcel(list, "质控抽查单数据");
   }

   @PreAuthorize("@ss.hasPermi('qc:record:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.qcBillRecordService.selectQcBillRecordById(id));
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:record:add,qc:rt:check')")
   @Log(
      title = "质控抽查单",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody QcBillRecordVo qcBillRecordVo, @RequestBody RunTimeQcCheckVo runTimeQcCheckVo) {
      AjaxResult ajaxResult = AjaxResult.success();
      boolean flag = true;

      try {
         if (flag && StringUtils.isBlank(qcBillRecordVo.getCheckWay())) {
            flag = false;
            ajaxResult = AjaxResult.error("生成抽查方式不能为空");
         }

         if (flag && qcBillRecordVo.getSampleNum() == null && qcBillRecordVo.getSampleRate() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("指定数量或指定比例不能为空");
         }

         if (flag && qcBillRecordVo.getSampleNum() != null && qcBillRecordVo.getSampleNum() <= 0) {
            flag = false;
            ajaxResult = AjaxResult.error("指定数量的数值要大于0");
         }

         if (flag && qcBillRecordVo.getSampleRate() != null && (qcBillRecordVo.getSampleRate() <= (double)0.0F || qcBillRecordVo.getSampleRate() > (double)100.0F)) {
            flag = false;
            ajaxResult = AjaxResult.error("指定比例的数值要大于0小于等于100");
         }

         if (flag && StringUtils.isBlank(qcBillRecordVo.getPatientFlag())) {
            flag = false;
            ajaxResult = AjaxResult.error("样本患者类型不能为空");
         }

         if (flag) {
            if (StringUtils.isNotEmpty(runTimeQcCheckVo.getBeginDateTime()) && StringUtils.isNotEmpty(runTimeQcCheckVo.getEndDateTime())) {
               Calendar c = Calendar.getInstance();
               c.setTime(DateUtils.parseDate(runTimeQcCheckVo.getEndDateTime()));
               c.add(5, 1);
               runTimeQcCheckVo.setEndDateTime(DateUtils.parseDateToStr("yyyy-MM-dd", c.getTime()));
            }

            runTimeQcCheckVo.setCostSum(runTimeQcCheckVo.getCostSum() != null ? runTimeQcCheckVo.getCostSum() * (double)10000.0F : null);
            String inhosDayLong = this.sysEmrConfigService.selectSysEmrConfigByKey("0018");
            String costSumHigh = this.sysEmrConfigService.selectSysEmrConfigByKey("0019");
            if (runTimeQcCheckVo.getDayNum() == null) {
               runTimeQcCheckVo.setDayNum(Integer.parseInt(inhosDayLong));
            }

            if (runTimeQcCheckVo.getCostSum() == null) {
               runTimeQcCheckVo.setCostSum(Double.parseDouble(costSumHigh));
            }

            this.qcBillRecordService.insertQcBillRecord(qcBillRecordVo, runTimeQcCheckVo);
            ajaxResult = AjaxResult.success((Object)qcBillRecordVo);
         }
      } catch (Exception e) {
         this.log.error("新增质控抽查单出现异常：", e);
         ajaxResult = AjaxResult.error("新增质控抽查单出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('qc:record:edit')")
   @Log(
      title = "质控抽查单",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody QcBillRecord qcBillRecord) {
      return this.toAjax(this.qcBillRecordService.updateQcBillRecord(qcBillRecord));
   }

   @PreAuthorize("@ss.hasPermi('qc:record:remove')")
   @Log(
      title = "质控抽查单",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.qcBillRecordService.deleteQcBillRecordByIds(ids));
   }
}
