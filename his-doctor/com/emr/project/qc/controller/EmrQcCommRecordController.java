package com.emr.project.qc.controller;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.StringUtils;
import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.docOrder.domain.TdPaOrder;
import com.emr.project.docOrder.service.ITdPaOrderService;
import com.emr.project.emr.domain.Index;
import com.emr.project.emr.domain.SubfileIndex;
import com.emr.project.emr.service.IIndexService;
import com.emr.project.emr.service.ISubfileIndexService;
import com.emr.project.mrhp.domain.MrHp;
import com.emr.project.mrhp.service.IMrHpService;
import com.emr.project.qc.domain.EmrQcCommRecord;
import com.emr.project.qc.domain.EmrQcList;
import com.emr.project.qc.domain.vo.EmrQcListVo;
import com.emr.project.qc.service.IEmrQcCommRecordService;
import com.emr.project.qc.service.IEmrQcListService;
import com.emr.project.system.domain.SysUser;
import java.util.List;
import org.springframework.beans.BeanUtils;
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
@RequestMapping({"/system/comm"})
public class EmrQcCommRecordController extends BaseController {
   @Autowired
   private IEmrQcCommRecordService emrQcCommRecordService;
   @Autowired
   private IIndexService indexService;
   @Autowired
   private ISubfileIndexService subfileIndexService;
   @Autowired
   private IMrHpService mrHpService;
   @Autowired
   private IEmrQcListService emrQcListService;
   @Autowired
   private ITdPaOrderService tdPaOrderService;

   @PreAuthorize("@ss.hasPermi('system:comm:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(EmrQcCommRecord emrQcCommRecord) {
      this.startPage();
      List<EmrQcCommRecord> list = this.emrQcCommRecordService.selectEmrQcCommRecordList(emrQcCommRecord);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('system:comm:export')")
   @Log(
      title = "病历质控I沟通记录",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(EmrQcCommRecord emrQcCommRecord) {
      List<EmrQcCommRecord> list = this.emrQcCommRecordService.selectEmrQcCommRecordList(emrQcCommRecord);
      ExcelUtil<EmrQcCommRecord> util = new ExcelUtil(EmrQcCommRecord.class);
      return util.exportExcel(list, "病历质控I沟通记录数据");
   }

   @PreAuthorize("@ss.hasPermi('system:comm:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.emrQcCommRecordService.selectEmrQcCommRecordById(id));
   }

   @PreAuthorize("@ss.hasAnyPermi('system:comm:add,qc:rt:check,qc:rt:checked,emr:index:selectEmrInfo,system:list:changeState')")
   @Log(
      title = "病历质控I沟通记录",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody EmrQcCommRecord emrQcCommRecord) {
      AjaxResult ajaxResult = AjaxResult.success("新增病历质控沟通记录成功");
      boolean flag = true;

      try {
         if (flag && emrQcCommRecord == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && emrQcCommRecord.getMainId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("缺陷id不能为空");
         }

         if (flag && StringUtils.isBlank(emrQcCommRecord.getPatientId())) {
            flag = false;
            ajaxResult = AjaxResult.error("患者id不能为空");
         }

         if (flag && StringUtils.isBlank(emrQcCommRecord.getMrFileId())) {
            flag = false;
            ajaxResult = AjaxResult.error("病历id不能为空");
         }

         if (flag && StringUtils.isBlank(emrQcCommRecord.getQcSection())) {
            flag = false;
            ajaxResult = AjaxResult.error("质控环节不能为空");
         }

         if (flag && StringUtils.isBlank(emrQcCommRecord.getFedbDesc())) {
            flag = false;
            ajaxResult = AjaxResult.error("反馈沟通内容不能为空");
         }

         if (flag && StringUtils.isBlank(emrQcCommRecord.getEmrEleId())) {
            flag = false;
            ajaxResult = AjaxResult.error("缺陷绑定元素id不能为空");
         }

         if (flag && StringUtils.isBlank(emrQcCommRecord.getEmrEleName())) {
            flag = false;
            ajaxResult = AjaxResult.error("缺陷绑定元素名称不能为空");
         }

         if (flag && StringUtils.isBlank(emrQcCommRecord.getFlawDesc())) {
            flag = false;
            ajaxResult = AjaxResult.error("缺陷描述不能为空");
         }

         if (flag && StringUtils.isBlank(emrQcCommRecord.getRoleCode())) {
            flag = false;
            ajaxResult = AjaxResult.error("反馈人角色不能为空");
         }

         EmrQcList emrQcList = flag ? this.emrQcListService.selectEmrQcListById(emrQcCommRecord.getMainId()) : null;
         if (flag && emrQcList == null) {
            flag = false;
            ajaxResult = AjaxResult.error("没有这个缺陷信息");
         }

         SubfileIndex subfileIndex = null;
         MrHp mrHp = null;
         Index index = null;
         if (flag) {
            String indexId = emrQcCommRecord.getMrFileId();
            index = this.indexService.selectIndexById(Long.parseLong(indexId));
            if (index == null) {
               subfileIndex = this.subfileIndexService.selectSubfileIndexById(Long.parseLong(indexId));
            }

            if (index == null && subfileIndex == null) {
               mrHp = this.mrHpService.selectMrHpById(indexId);
            }

            if (index == null && subfileIndex == null && mrHp == null) {
               flag = false;
               ajaxResult = AjaxResult.error("没有此病历文件记录");
            }
         }

         if (flag) {
            SysUser user = SecurityUtils.getLoginUser().getUser();
            this.emrQcCommRecordService.insertEmrQcCommRecord(emrQcCommRecord, mrHp, index, subfileIndex, user);
            emrQcList = this.emrQcListService.selectEmrQcListById(emrQcCommRecord.getMainId());
            EmrQcListVo emrQcListVo = new EmrQcListVo();
            BeanUtils.copyProperties(emrQcList, emrQcListVo);
            EmrQcCommRecord param = new EmrQcCommRecord();
            param.setMainId(emrQcListVo.getId());
            List<EmrQcCommRecord> emrQcCommRecordList = this.emrQcCommRecordService.selectEmrQcCommRecordList(param);
            emrQcListVo.setQcCommRecordList(emrQcCommRecordList);
            ajaxResult = AjaxResult.success("新增病历质控沟通记录成功", emrQcListVo);
         }
      } catch (Exception e) {
         this.log.error("新增病历质控沟通记录出现异常：", e);
         ajaxResult = AjaxResult.error("新增病历质控沟通记录出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('system:comm:edit')")
   @Log(
      title = "病历质控I沟通记录",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody EmrQcCommRecord emrQcCommRecord) {
      return this.toAjax(this.emrQcCommRecordService.updateEmrQcCommRecord(emrQcCommRecord));
   }

   @PreAuthorize("@ss.hasPermi('system:comm:remove')")
   @Log(
      title = "病历质控I沟通记录",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.emrQcCommRecordService.deleteEmrQcCommRecordByIds(ids));
   }

   @PreAuthorize("@ss.hasAnyPermi('system:comm:add,qc:rt:check,qc:rt:checked,docOrder:list:long,docOrder:list:temp,docOrder:list:decoction')")
   @Log(
      title = "病历质控I沟通记录",
      businessType = BusinessType.INSERT
   )
   @PostMapping({"/orderAdd"})
   public AjaxResult orderAdd(@RequestBody EmrQcCommRecord emrQcCommRecord) {
      AjaxResult ajaxResult = AjaxResult.success("新增医嘱本质控沟通记录成功");
      boolean flag = true;

      try {
         if (flag && emrQcCommRecord == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && emrQcCommRecord.getMainId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("缺陷id不能为空");
         }

         if (flag && StringUtils.isBlank(emrQcCommRecord.getPatientId())) {
            flag = false;
            ajaxResult = AjaxResult.error("患者id不能为空");
         }

         if (flag && StringUtils.isBlank(emrQcCommRecord.getMrFileId())) {
            flag = false;
            ajaxResult = AjaxResult.error("病历id不能为空");
         }

         if (flag && StringUtils.isBlank(emrQcCommRecord.getQcSection())) {
            flag = false;
            ajaxResult = AjaxResult.error("质控环节不能为空");
         }

         if (flag && StringUtils.isBlank(emrQcCommRecord.getFedbDesc())) {
            flag = false;
            ajaxResult = AjaxResult.error("反馈沟通内容不能为空");
         }

         if (flag && StringUtils.isBlank(emrQcCommRecord.getEmrEleId())) {
            flag = false;
            ajaxResult = AjaxResult.error("缺陷绑定元素id不能为空");
         }

         if (flag && StringUtils.isBlank(emrQcCommRecord.getEmrEleName())) {
            flag = false;
            ajaxResult = AjaxResult.error("缺陷绑定元素名称不能为空");
         }

         if (flag && StringUtils.isBlank(emrQcCommRecord.getFlawDesc())) {
            flag = false;
            ajaxResult = AjaxResult.error("缺陷描述不能为空");
         }

         if (flag && StringUtils.isBlank(emrQcCommRecord.getRoleCode())) {
            flag = false;
            ajaxResult = AjaxResult.error("反馈人角色不能为空");
         }

         EmrQcList emrQcList = flag ? this.emrQcListService.selectEmrQcListById(emrQcCommRecord.getMainId()) : null;
         if (flag && emrQcList == null) {
            flag = false;
            ajaxResult = AjaxResult.error("没有这个缺陷信息");
         }

         TdPaOrder tdPaOrder = null;
         if (flag) {
            String orderNo = emrQcCommRecord.getEmrEleId();
            tdPaOrder = this.tdPaOrderService.selectTdPaOrderById(orderNo);
            if (tdPaOrder == null) {
               flag = false;
               ajaxResult = AjaxResult.error("没有此医嘱记录");
            }
         }

         if (flag) {
            SysUser user = SecurityUtils.getLoginUser().getUser();
            this.emrQcCommRecordService.insertOrderQcCommRecord(emrQcCommRecord, user);
            emrQcList = this.emrQcListService.selectEmrQcListById(emrQcCommRecord.getMainId());
            EmrQcListVo emrQcListVo = new EmrQcListVo();
            BeanUtils.copyProperties(emrQcList, emrQcListVo);
            EmrQcCommRecord param = new EmrQcCommRecord();
            param.setMainId(emrQcListVo.getId());
            List<EmrQcCommRecord> emrQcCommRecordList = this.emrQcCommRecordService.selectEmrQcCommRecordList(param);
            emrQcListVo.setQcCommRecordList(emrQcCommRecordList);
            ajaxResult = AjaxResult.success("新增病历质控沟通记录成功", emrQcListVo);
         }
      } catch (Exception e) {
         this.log.error("新增医嘱本质控沟通记录出现异常：", e);
         ajaxResult = AjaxResult.error("新增医嘱本质控沟通记录出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('system:comm:add,qc:rt:check,qc:rt:checked,emr:index:selectEmrInfo,system:list:changeState')")
   @PostMapping({"/updateMrHp"})
   public AjaxResult updateMrHp(@RequestBody EmrQcCommRecord emrQcCommRecord) {
      AjaxResult ajaxResult = AjaxResult.success("修改成功！");
      boolean flag = true;

      try {
         if (flag && emrQcCommRecord == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && emrQcCommRecord.getMainId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("缺陷id不能为空");
         }

         if (flag && StringUtils.isBlank(emrQcCommRecord.getPatientId())) {
            flag = false;
            ajaxResult = AjaxResult.error("患者id不能为空");
         }

         if (flag && StringUtils.isBlank(emrQcCommRecord.getQcSection())) {
            flag = false;
            ajaxResult = AjaxResult.error("质控环节不能为空");
         }

         if (flag && StringUtils.isBlank(emrQcCommRecord.getFedbDesc())) {
            flag = false;
            ajaxResult = AjaxResult.error("反馈沟通内容不能为空");
         }

         if (flag && StringUtils.isBlank(emrQcCommRecord.getEmrEleId())) {
            flag = false;
            ajaxResult = AjaxResult.error("缺陷绑定字段不能为空");
         }

         if (flag && StringUtils.isBlank(emrQcCommRecord.getEmrEleName())) {
            flag = false;
            ajaxResult = AjaxResult.error("缺陷绑定字段名称不能为空");
         }

         if (flag && StringUtils.isBlank(emrQcCommRecord.getFlawDesc())) {
            flag = false;
            ajaxResult = AjaxResult.error("缺陷描述不能为空");
         }

         if (flag && StringUtils.isBlank(emrQcCommRecord.getRoleCode())) {
            flag = false;
            ajaxResult = AjaxResult.error("反馈人角色不能为空");
         }

         EmrQcList emrQcList = flag ? this.emrQcListService.selectEmrQcListById(emrQcCommRecord.getMainId()) : null;
         if (flag && emrQcList == null) {
            flag = false;
            ajaxResult = AjaxResult.error("没有这个缺陷信息");
         }

         if (flag) {
            SysUser user = SecurityUtils.getLoginUser().getUser();
            this.emrQcCommRecordService.insertEmrQcListByMrHp(emrQcCommRecord, user);
            emrQcList = this.emrQcListService.selectEmrQcListById(emrQcCommRecord.getMainId());
            EmrQcListVo emrQcListVo = new EmrQcListVo();
            BeanUtils.copyProperties(emrQcList, emrQcListVo);
            EmrQcCommRecord param = new EmrQcCommRecord();
            param.setMainId(emrQcListVo.getId());
            List<EmrQcCommRecord> emrQcCommRecordList = this.emrQcCommRecordService.selectEmrQcCommRecordList(param);
            emrQcListVo.setQcCommRecordList(emrQcCommRecordList);
            ajaxResult = AjaxResult.success("新增病历质控沟通记录成功", emrQcListVo);
         }
      } catch (Exception e) {
         this.log.error("新增病历质控沟通记录出现异常：", e);
         ajaxResult = AjaxResult.error("新增病历质控沟通记录出现异常");
      }

      return ajaxResult;
   }
}
