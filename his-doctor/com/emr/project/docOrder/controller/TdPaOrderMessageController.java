package com.emr.project.docOrder.controller;

import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.docOrder.domain.TdPaOrderMessage;
import com.emr.project.docOrder.service.ITdPaOrderMessageService;
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
@RequestMapping({"/docOrder/message"})
public class TdPaOrderMessageController extends BaseController {
   @Autowired
   private ITdPaOrderMessageService tdPaOrderMessageService;

   @PreAuthorize("@ss.hasPermi('docOrder:message:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(TdPaOrderMessage tdPaOrderMessage) {
      this.startPage();
      List<TdPaOrderMessage> list = this.tdPaOrderMessageService.selectTdPaOrderMessageList(tdPaOrderMessage);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('docOrder:message:export')")
   @Log(
      title = "医嘱消息提醒",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(TdPaOrderMessage tdPaOrderMessage) {
      List<TdPaOrderMessage> list = this.tdPaOrderMessageService.selectTdPaOrderMessageList(tdPaOrderMessage);
      ExcelUtil<TdPaOrderMessage> util = new ExcelUtil(TdPaOrderMessage.class);
      return util.exportExcel(list, "医嘱消息提醒数据");
   }

   @PreAuthorize("@ss.hasPermi('docOrder:message:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.tdPaOrderMessageService.selectTdPaOrderMessageById(id));
   }

   @PreAuthorize("@ss.hasPermi('docOrder:message:add')")
   @Log(
      title = "医嘱消息提醒",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody TdPaOrderMessage tdPaOrderMessage) {
      return this.toAjax(this.tdPaOrderMessageService.insertTdPaOrderMessage(tdPaOrderMessage));
   }

   @PreAuthorize("@ss.hasPermi('docOrder:message:edit')")
   @Log(
      title = "医嘱消息提醒",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody TdPaOrderMessage tdPaOrderMessage) {
      return this.toAjax(this.tdPaOrderMessageService.updateTdPaOrderMessage(tdPaOrderMessage));
   }

   @PreAuthorize("@ss.hasPermi('docOrder:message:remove')")
   @Log(
      title = "医嘱消息提醒",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.tdPaOrderMessageService.deleteTdPaOrderMessageByIds(ids));
   }
}
