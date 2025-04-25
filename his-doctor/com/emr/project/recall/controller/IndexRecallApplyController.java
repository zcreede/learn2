package com.emr.project.recall.controller;

import com.emr.common.utils.StringUtils;
import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.borrowing.domain.req.BorrowReviewedListReq;
import com.emr.project.borrowing.domain.req.CheckMedicalRecordReq;
import com.emr.project.borrowing.domain.req.SaveApplyAgreeListReq;
import com.emr.project.borrowing.domain.req.SaveApplyAgreeReq;
import com.emr.project.operation.service.IMedicalinfoService;
import com.emr.project.recall.domain.IndexRecallApply;
import com.emr.project.recall.domain.vo.IndexRecallApplyVO;
import com.emr.project.recall.service.IIndexRecallApplyService;
import com.emr.project.webservice.domain.req.PaperlessCheckReq;
import com.emr.project.webservice.domain.resp.WebServiceResp;
import com.emr.project.webservice.service.PaperlessCheckService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/recall/apply"})
public class IndexRecallApplyController extends BaseController {
   @Autowired
   private IIndexRecallApplyService indexRecallApplyService;
   @Autowired
   private IMedicalinfoService medicalinfoService;
   @Autowired
   private PaperlessCheckService paperlessCheckService;

   @PreAuthorize("@ss.hasPermi('recall:apply:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(IndexRecallApply indexRecallApply) {
      this.startPage();
      List<IndexRecallApplyVO> list = this.indexRecallApplyService.selectIndexRecallApplyList(indexRecallApply);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('recall:apply:export')")
   @Log(
      title = "病历召回申请",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(IndexRecallApply indexRecallApply) {
      List<IndexRecallApplyVO> list = this.indexRecallApplyService.selectIndexRecallApplyList(indexRecallApply);
      ExcelUtil<IndexRecallApplyVO> util = new ExcelUtil(IndexRecallApplyVO.class);
      return util.exportExcel(list, "病历召回申请数据");
   }

   @PreAuthorize("@ss.hasPermi('recall:apply:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.indexRecallApplyService.selectIndexRecallApplyById(id));
   }

   @PreAuthorize("@ss.hasPermi('recall:apply:add')")
   @Log(
      title = "病历召回申请",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody IndexRecallApply indexRecallApply) {
      return this.toAjax(this.indexRecallApplyService.insertIndexRecallApply(indexRecallApply));
   }

   @PreAuthorize("@ss.hasPermi('recall:apply:edit')")
   @Log(
      title = "病历召回申请",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody IndexRecallApply indexRecallApply) {
      return this.toAjax(this.indexRecallApplyService.updateIndexRecallApply(indexRecallApply));
   }

   @PreAuthorize("@ss.hasPermi('recall:apply:remove')")
   @Log(
      title = "病历召回申请",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.indexRecallApplyService.deleteIndexRecallApplyByIds(ids));
   }

   @ResponseBody
   @PreAuthorize("@ss.hasAnyPermi('recall:apply:checkMedicalRecord')")
   @PostMapping({"/checkMedicalRecord"})
   public AjaxResult checkMedicalRecord(@RequestBody CheckMedicalRecordReq req) {
      AjaxResult resultMsg = AjaxResult.success("查询成功");
      if (StringUtils.isEmpty(req.getInpNo())) {
         resultMsg = AjaxResult.error("住院号不能为空！");
         return resultMsg;
      } else {
         try {
            WebServiceResp serviceResp = this.paperlessCheckService.getPaperlessCheckFlag(new PaperlessCheckReq(req.getInpNo()));
            if ("1".equals(serviceResp.getCode())) {
               return AjaxResult.error(serviceResp.getSource());
            }

            Map<String, Object> flag = this.indexRecallApplyService.checkMedicalRecord(req);
            resultMsg.put("data", flag);
         } catch (Exception e) {
            resultMsg = AjaxResult.error("查询患者病历状态,是否可以申请召回异常，请联系管理员");
            this.log.error("查询患者病历状态,是否可以申请召回异常：", e);
         }

         return resultMsg;
      }
   }

   @PreAuthorize("@ss.hasAnyPermi('recall:apply:reviewedList')")
   @GetMapping({"/reviewedList"})
   public TableDataInfo reviewedList(BorrowReviewedListReq req) {
      this.startPage();
      List<IndexRecallApplyVO> list = this.indexRecallApplyService.selectReviewedList(req);
      return this.getDataTable(list);
   }

   @ResponseBody
   @PreAuthorize("@ss.hasPermi('recall:apply:getApplyList')")
   @RequestMapping({"/getApplyList"})
   public AjaxResult getApplyList(IndexRecallApply indexRecallApply) {
      AjaxResult resultMsg = AjaxResult.success("查询成功");
      if (StringUtils.isEmpty(indexRecallApply.getAdmissionNo())) {
         resultMsg = AjaxResult.error("住院号不能为空！");
         return resultMsg;
      } else {
         try {
            List<IndexRecallApplyVO> list = this.indexRecallApplyService.getApplyList(indexRecallApply);
            resultMsg.put("data", list);
         } catch (Exception e) {
            resultMsg = AjaxResult.error("查询医师申请列表异常，请联系管理员");
            this.log.error("查询医师申请列表异常：", e);
         }

         return resultMsg;
      }
   }

   @ResponseBody
   @PreAuthorize("@ss.hasPermi('recall:apply:saveApplyAgree')")
   @RequestMapping({"/saveApplyAgree"})
   public AjaxResult saveApplyAgree(@RequestBody SaveApplyAgreeListReq req) {
      AjaxResult resultMsg = AjaxResult.success("保存成功");

      try {
         List<SaveApplyAgreeReq> list = req.getList();
         if (list == null || list.size() == 0) {
            resultMsg = AjaxResult.error("参数不能为空！");
            return resultMsg;
         }

         for(SaveApplyAgreeReq saveReq : list) {
            if (saveReq.getId() == null) {
               resultMsg = AjaxResult.error("参数ID不能为空！");
               return resultMsg;
            }

            if (StringUtils.isEmpty(saveReq.getAdmissionNo())) {
               resultMsg = AjaxResult.error("住院号不能为空！");
               return resultMsg;
            }

            IndexRecallApply indexRecallApply = this.indexRecallApplyService.selectIndexRecallApplyById(saveReq.getId());
            String appStatus = indexRecallApply.getAppStatus();
            if (!"0".equals(appStatus)) {
               resultMsg = AjaxResult.error("只有状态为申请中的才能通过审核！");
               return resultMsg;
            }

            WebServiceResp serviceResp = this.paperlessCheckService.getPaperlessCheckFlag(new PaperlessCheckReq(saveReq.getAdmissionNo()));
            if ("1".equals(serviceResp.getCode())) {
               return AjaxResult.error(serviceResp.getSource());
            }
         }

         this.indexRecallApplyService.updateStatus(req, "1");
      } catch (Exception e) {
         resultMsg = AjaxResult.error("通过待审批申请异常，请联系管理员");
         this.log.error("通过待审批申请异常：", e);
      }

      return resultMsg;
   }

   @ResponseBody
   @PreAuthorize("@ss.hasPermi('recall:apply:rejectApply')")
   @RequestMapping({"/rejectApply"})
   public AjaxResult rejectApply(@RequestBody SaveApplyAgreeListReq req) {
      AjaxResult resultMsg = AjaxResult.success("保存成功");
      List<SaveApplyAgreeReq> list = req.getList();
      if (list != null && list.size() != 0) {
         for(SaveApplyAgreeReq saveReq : list) {
            if (saveReq.getId() == null) {
               resultMsg = AjaxResult.error("参数ID不能为空！");
               return resultMsg;
            }

            IndexRecallApply indexRecallApply = this.indexRecallApplyService.selectIndexRecallApplyById(saveReq.getId());
            String appStatus = indexRecallApply.getAppStatus();
            if (!"0".equals(appStatus)) {
               resultMsg = AjaxResult.error("只有状态为申请中的才能驳回！");
               return resultMsg;
            }
         }

         try {
            this.indexRecallApplyService.updateStatus(req, "2");
         } catch (Exception e) {
            resultMsg = AjaxResult.error("通过待审批申请异常，请联系管理员");
            this.log.error("通过待审批申请异常：", e);
         }

         return resultMsg;
      } else {
         resultMsg = AjaxResult.error("参数不能为空！");
         return resultMsg;
      }
   }

   @ResponseBody
   @PreAuthorize("@ss.hasPermi('recall:apply:saveApply')")
   @RequestMapping({"/saveApply"})
   public AjaxResult saveApply(@RequestBody IndexRecallApply indexRecallApply) {
      AjaxResult resultMsg = AjaxResult.success("保存成功");
      if (indexRecallApply.getHospitalizedCount() == null) {
         resultMsg = AjaxResult.error("住院次数不能为空！");
         return resultMsg;
      } else if (StringUtils.isEmpty(indexRecallApply.getAdmissionNo())) {
         resultMsg = AjaxResult.error("住院号不能为空！");
         return resultMsg;
      } else if (StringUtils.isEmpty(indexRecallApply.getCaseNo())) {
         resultMsg = AjaxResult.error("病案号不能为空！");
         return resultMsg;
      } else if (StringUtils.isEmpty(indexRecallApply.getPatientName())) {
         resultMsg = AjaxResult.error("患者姓名不能为空！");
         return resultMsg;
      } else if (StringUtils.isEmpty(indexRecallApply.getRecallReason())) {
         resultMsg = AjaxResult.error("召回原因不能为空！");
         return resultMsg;
      } else if (indexRecallApply.getLeaveHospitalDate() == null) {
         resultMsg = AjaxResult.error("出科时间不能为空！");
         return resultMsg;
      } else if (indexRecallApply.getHospitalizedDate() == null) {
         resultMsg = AjaxResult.error("入科时间不能为空！");
         return resultMsg;
      } else if (StringUtils.isEmpty(indexRecallApply.getDischargeDepartmentNo())) {
         resultMsg = AjaxResult.error("出院科室编码不能为空！");
         return resultMsg;
      } else if (StringUtils.isEmpty(indexRecallApply.getDischargeDepartmentName())) {
         resultMsg = AjaxResult.error("出院科室不能为空！");
         return resultMsg;
      } else if (StringUtils.isEmpty(indexRecallApply.getResidentNo())) {
         resultMsg = AjaxResult.error("住院医师编码不能为空！");
         return resultMsg;
      } else if (StringUtils.isEmpty(indexRecallApply.getResidentName())) {
         resultMsg = AjaxResult.error("住院医师名称不能为空！");
         return resultMsg;
      } else if (indexRecallApply.getFileDate() == null) {
         resultMsg = AjaxResult.error("归档时间不能为空！");
         return resultMsg;
      } else {
         CheckMedicalRecordReq recordReq = new CheckMedicalRecordReq();
         recordReq.setInpNo(indexRecallApply.getAdmissionNo());

         try {
            Map<String, Object> stringBooleanMap = this.indexRecallApplyService.checkMedicalRecord(recordReq);
            Boolean flag = (Boolean)stringBooleanMap.get("flag");
            if (!flag) {
               resultMsg = AjaxResult.error("该患者病例未归档或未提交终末质控，暂不可提交召回申请！");
               return resultMsg;
            }

            Boolean isDoctor = (Boolean)stringBooleanMap.get("isDoctor");
            if (!isDoctor) {
               resultMsg = AjaxResult.error("您不是该患者的主治医师，不可提交召回申请！");
               return resultMsg;
            }

            Boolean status = this.indexRecallApplyService.checkApplyByAdmissionNo(indexRecallApply.getAdmissionNo());
            if (!status) {
               resultMsg = AjaxResult.error("该患者病例已经提交申请或正在审核中，不可重复申请！");
               return resultMsg;
            }

            WebServiceResp serviceResp = this.paperlessCheckService.getPaperlessCheckFlag(new PaperlessCheckReq(indexRecallApply.getAdmissionNo()));
            if ("1".equals(serviceResp.getCode())) {
               return AjaxResult.error(serviceResp.getSource());
            }

            this.indexRecallApplyService.saveApply(indexRecallApply);
         } catch (Exception e) {
            resultMsg = AjaxResult.error("保存申请异常，请联系管理员");
            this.log.error("保存申请异常，请联系管理员", e);
         }

         return resultMsg;
      }
   }

   @ResponseBody
   @PreAuthorize("@ss.hasPermi('recall:apply:updateApply')")
   @RequestMapping({"/updateApply"})
   public AjaxResult updateApply(@RequestBody IndexRecallApply req) {
      AjaxResult resultMsg = AjaxResult.success("保存成功");
      if (req.getId() == null) {
         resultMsg = AjaxResult.error("主键不能为空！");
         return resultMsg;
      } else if (StringUtils.isEmpty(req.getRecallReason())) {
         resultMsg = AjaxResult.error("召回原因不能为空！");
         return resultMsg;
      } else {
         IndexRecallApply indexRecallApply = this.indexRecallApplyService.selectIndexRecallApplyById(req.getId());
         String appStatus = indexRecallApply.getAppStatus();
         if (!appStatus.equals("0")) {
            resultMsg = AjaxResult.error("只有申请中的召回才能修改！");
            return resultMsg;
         } else {
            try {
               this.indexRecallApplyService.updateApply(req, "0");
            } catch (Exception e) {
               resultMsg = AjaxResult.error("修改申请异常，请联系管理员");
               this.log.error("修改申请异常，请联系管理员", e);
            }

            return resultMsg;
         }
      }
   }

   @ResponseBody
   @PreAuthorize("@ss.hasPermi('recall:apply:voidApply')")
   @RequestMapping({"/voidApply"})
   public AjaxResult voidApply(@RequestBody IndexRecallApply indexRecallApply) {
      AjaxResult resultMsg = AjaxResult.success("保存成功");
      if (indexRecallApply.getId() == null) {
         resultMsg = AjaxResult.error("主键不能为空！");
         return resultMsg;
      } else {
         IndexRecallApply recallApply = this.indexRecallApplyService.selectIndexRecallApplyById(indexRecallApply.getId());
         String appStatus = recallApply.getAppStatus();
         if (!appStatus.equals("0")) {
            resultMsg = AjaxResult.error("只有申请中的才能作废！");
            return resultMsg;
         } else {
            try {
               this.indexRecallApplyService.updateApply(indexRecallApply, "3");
            } catch (Exception e) {
               resultMsg = AjaxResult.error("申请作废异常，请联系管理员");
               this.log.error("申请作废异常，请联系管理员", e);
            }

            return resultMsg;
         }
      }
   }
}
