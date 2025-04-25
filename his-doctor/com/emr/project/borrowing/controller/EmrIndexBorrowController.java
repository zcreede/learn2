package com.emr.project.borrowing.controller;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.StringUtils;
import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.borrowing.domain.EmrIndexBorrow;
import com.emr.project.borrowing.domain.req.BorrowReviewedListReq;
import com.emr.project.borrowing.domain.req.CheckMedicalRecordReq;
import com.emr.project.borrowing.domain.req.PatientOutReq;
import com.emr.project.borrowing.domain.req.SaveApplyAgreeListReq;
import com.emr.project.borrowing.domain.req.SaveApplyAgreeReq;
import com.emr.project.borrowing.domain.req.UpdateApplyDetailReq;
import com.emr.project.borrowing.domain.resp.PatientOutResp;
import com.emr.project.borrowing.domain.vo.EmrIndexBorrowVO;
import com.emr.project.borrowing.domain.vo.SysDictDataVO;
import com.emr.project.borrowing.service.IEmrIndexBorrowService;
import com.emr.project.common.service.ICommonService;
import com.emr.project.system.domain.SysUser;
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
@RequestMapping({"/borrowing/borrow"})
public class EmrIndexBorrowController extends BaseController {
   @Autowired
   private IEmrIndexBorrowService emrIndexBorrowService;
   @Autowired
   private ICommonService commonService;

   @PreAuthorize("@ss.hasAnyPermi('borrowing:borrow:reviewedList,borrowing:borrow:reviewedApplyList,borrowing:borrow:reviewedApprovalList')")
   @GetMapping({"/reviewedList"})
   public TableDataInfo reviewedList(BorrowReviewedListReq req) {
      new TableDataInfo();

      TableDataInfo dataTable;
      try {
         Boolean qcDept = this.commonService.isQCDept();
         SysUser user = SecurityUtils.getLoginUser().getUser();
         String deptCode = user.getDept().getDeptCode();
         if (qcDept) {
            deptCode = null;
         }

         req.setDeptCode(deptCode);
         this.startPage();
         List<EmrIndexBorrowVO> list = this.emrIndexBorrowService.selectReviewedList(req);
         dataTable = this.getDataTable(list);
      } catch (Exception e) {
         this.log.error("查询病历借阅列表异常", e);
         dataTable = new TableDataInfo(500, "查询病历借阅列表异常");
      }

      return dataTable;
   }

   @PreAuthorize("@ss.hasPermi('borrowing:borrow:export')")
   @Log(
      title = "病历借阅",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(EmrIndexBorrow emrIndexBorrow) {
      List<EmrIndexBorrow> list = this.emrIndexBorrowService.selectEmrIndexBorrowList(emrIndexBorrow);
      ExcelUtil<EmrIndexBorrow> util = new ExcelUtil(EmrIndexBorrow.class);
      return util.exportExcel(list, "病历借阅数据");
   }

   @PreAuthorize("@ss.hasPermi('borrowing:borrow:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.emrIndexBorrowService.selectEmrIndexBorrowById(id));
   }

   @PreAuthorize("@ss.hasPermi('borrowing:borrow:add')")
   @Log(
      title = "病历借阅",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody EmrIndexBorrow emrIndexBorrow) {
      return this.toAjax(this.emrIndexBorrowService.insertEmrIndexBorrow(emrIndexBorrow));
   }

   @PreAuthorize("@ss.hasPermi('borrowing:borrow:edit')")
   @Log(
      title = "病历借阅",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody EmrIndexBorrow emrIndexBorrow) {
      return this.toAjax(this.emrIndexBorrowService.updateEmrIndexBorrow(emrIndexBorrow));
   }

   @PreAuthorize("@ss.hasPermi('borrowing:borrow:remove')")
   @Log(
      title = "病历借阅",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.emrIndexBorrowService.deleteEmrIndexBorrowByIds(ids));
   }

   @ResponseBody
   @PreAuthorize("@ss.hasAnyPermi('borrowing:borrow:checkMedicalRecord,pat:visitinfo:allList')")
   @PostMapping({"/checkMedicalRecord"})
   public AjaxResult checkMedicalRecord(@RequestBody CheckMedicalRecordReq req) {
      AjaxResult resultMsg = AjaxResult.success("查询成功");
      if (StringUtils.isEmpty(req.getInpNo())) {
         resultMsg = AjaxResult.error("住院号不能为空！");
         return resultMsg;
      } else {
         try {
            Map<String, Boolean> flag = this.emrIndexBorrowService.checkMedicalRecord(req);
            resultMsg.put("data", flag);
         } catch (Exception e) {
            resultMsg = AjaxResult.error("查询患者病例是否归档异常，请联系管理员");
            this.log.error("查询患者病例是否归档异常：", e);
         }

         return resultMsg;
      }
   }

   @ResponseBody
   @PreAuthorize("@ss.hasPermi('borrowing:borrow:getApplyList')")
   @RequestMapping({"/getApplyList"})
   public AjaxResult getApplyList(EmrIndexBorrow emrIndexBorrow) {
      AjaxResult resultMsg = AjaxResult.success("查询成功");
      if (StringUtils.isEmpty(emrIndexBorrow.getAdmissionNo())) {
         resultMsg = AjaxResult.error("住院号不能为空！");
         return resultMsg;
      } else {
         try {
            List<EmrIndexBorrowVO> list = this.emrIndexBorrowService.getApplyList(emrIndexBorrow);
            resultMsg.put("data", list);
         } catch (Exception e) {
            resultMsg = AjaxResult.error("查询医师申请列表异常，请联系管理员");
            this.log.error("查询医师申请列表异常：", e);
         }

         return resultMsg;
      }
   }

   @ResponseBody
   @PreAuthorize("@ss.hasPermi('borrowing:borrow:borrowPurposeList')")
   @GetMapping({"/getBorrowPurposeList"})
   public AjaxResult getBorrowPurposeList() {
      AjaxResult resultMsg = AjaxResult.success("查询成功");

      try {
         List<SysDictDataVO> list = this.emrIndexBorrowService.getBorrowPurposeList();
         resultMsg.put("data", list);
      } catch (Exception e) {
         resultMsg = AjaxResult.error("借阅用途查询异常，请联系管理员");
         this.log.error("借阅用途查询出现异常：", e);
      }

      return resultMsg;
   }

   @ResponseBody
   @PreAuthorize("@ss.hasPermi('borrowing:borrow:saveApplyAgree')")
   @RequestMapping({"/saveApplyAgree"})
   public AjaxResult saveApplyAgree(@RequestBody SaveApplyAgreeListReq req) {
      AjaxResult resultMsg = AjaxResult.success("保存成功");
      List<SaveApplyAgreeReq> list = req.getList();
      if (list != null && list.size() != 0) {
         for(SaveApplyAgreeReq saveReq : list) {
            if (saveReq.getId() == null) {
               resultMsg = AjaxResult.error("参数ID不能为空！");
               return resultMsg;
            }

            EmrIndexBorrow emrIndexBorrow = this.emrIndexBorrowService.selectEmrIndexBorrowById(saveReq.getId());
            String appStatus = emrIndexBorrow.getAppStatus();
            if (!"0".equals(appStatus)) {
               resultMsg = AjaxResult.error("只有状态为申请中的才能通过审核！");
               return resultMsg;
            }
         }

         try {
            this.emrIndexBorrowService.updateStatus(req, "1");
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
   @PreAuthorize("@ss.hasPermi('borrowing:borrow:rejectApply')")
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

            EmrIndexBorrow emrIndexBorrow = this.emrIndexBorrowService.selectEmrIndexBorrowById(saveReq.getId());
            String appStatus = emrIndexBorrow.getAppStatus();
            if (!"0".equals(appStatus)) {
               resultMsg = AjaxResult.error("只有状态为申请中的才能驳回！");
               return resultMsg;
            }
         }

         try {
            this.emrIndexBorrowService.updateStatus(req, "2");
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
   @PreAuthorize("@ss.hasPermi('borrowing:borrow:saveApply')")
   @RequestMapping({"/saveApply"})
   public AjaxResult saveApply(@RequestBody EmrIndexBorrow emrIndexBorrow) {
      AjaxResult resultMsg = AjaxResult.success("保存成功");
      if (emrIndexBorrow.getHospitalizedCount() == null) {
         resultMsg = AjaxResult.error("住院次数不能为空！");
         return resultMsg;
      } else if (emrIndexBorrow.getBorrowPeriod() == null) {
         resultMsg = AjaxResult.error("借阅期限不能为空！");
         return resultMsg;
      } else if (StringUtils.isEmpty(emrIndexBorrow.getAdmissionNo())) {
         resultMsg = AjaxResult.error("住院号不能为空！");
         return resultMsg;
      } else if (StringUtils.isEmpty(emrIndexBorrow.getCaseNo())) {
         resultMsg = AjaxResult.error("病案号不能为空！");
         return resultMsg;
      } else if (StringUtils.isEmpty(emrIndexBorrow.getPatientName())) {
         resultMsg = AjaxResult.error("患者姓名不能为空！");
         return resultMsg;
      } else if (StringUtils.isEmpty(emrIndexBorrow.getBorrowPurpose())) {
         resultMsg = AjaxResult.error("借阅用途不能为空！");
         return resultMsg;
      } else if (emrIndexBorrow.getLeaveHospitalDateTime() == null) {
         resultMsg = AjaxResult.error("出院时间不能为空！");
         return resultMsg;
      } else if (StringUtils.isEmpty(emrIndexBorrow.getDischargeDepartmentNo())) {
         resultMsg = AjaxResult.error("出院科室编码不能为空！");
         return resultMsg;
      } else if (StringUtils.isEmpty(emrIndexBorrow.getDischargeDepartmentName())) {
         resultMsg = AjaxResult.error("出院科室不能为空！");
         return resultMsg;
      } else if (StringUtils.isEmpty(emrIndexBorrow.getResidentNo())) {
         resultMsg = AjaxResult.error("住院医师编码不能为空！");
         return resultMsg;
      } else if (StringUtils.isEmpty(emrIndexBorrow.getResidentName())) {
         resultMsg = AjaxResult.error("住院医师名称不能为空！");
         return resultMsg;
      } else {
         CheckMedicalRecordReq recordReq = new CheckMedicalRecordReq();
         recordReq.setInpNo(emrIndexBorrow.getAdmissionNo());
         Map<String, Boolean> stringBooleanMap = this.emrIndexBorrowService.checkMedicalRecord(recordReq);
         Boolean flag = (Boolean)stringBooleanMap.get("flag");
         if (!flag) {
            resultMsg = AjaxResult.error("该患者病例未归档，暂不可借阅！");
            return resultMsg;
         } else {
            Boolean status = this.emrIndexBorrowService.checkApplyByAdmissionNo(emrIndexBorrow.getAdmissionNo());
            if (!status) {
               resultMsg = AjaxResult.error("该患者病例已经提交申请或正在借阅中，不可重复申请！");
               return resultMsg;
            } else {
               try {
                  Map<String, Object> map = this.emrIndexBorrowService.saveApply(emrIndexBorrow);
                  resultMsg.put("data", map);
               } catch (Exception e) {
                  resultMsg = AjaxResult.error("保存借阅申请异常，请联系管理员");
                  this.log.error("保存借阅申请异常，请联系管理员", e);
               }

               return resultMsg;
            }
         }
      }
   }

   @ResponseBody
   @PreAuthorize("@ss.hasPermi('borrowing:borrow:updateApply')")
   @RequestMapping({"/updateApply"})
   public AjaxResult updateApply(@RequestBody UpdateApplyDetailReq req) {
      AjaxResult resultMsg = AjaxResult.success("保存成功");
      if (req.getId() == null) {
         resultMsg = AjaxResult.error("主键不能为空！");
         return resultMsg;
      } else if (req.getBorrowPeriod() != null && req.getBorrowPeriod() != 0L) {
         if (StringUtils.isEmpty(req.getBorrowPurpose())) {
            resultMsg = AjaxResult.error("借阅用途不能为空！");
            return resultMsg;
         } else {
            EmrIndexBorrow emrIndexBorrow = this.emrIndexBorrowService.selectEmrIndexBorrowById(req.getId());
            String appStatus = emrIndexBorrow.getAppStatus();
            if (!appStatus.equals("0")) {
               resultMsg = AjaxResult.error("只有申请中的借阅才能修改！");
               return resultMsg;
            } else {
               try {
                  this.emrIndexBorrowService.updateApply(req, "0", emrIndexBorrow);
               } catch (Exception e) {
                  resultMsg = AjaxResult.error("保存借阅申请修改异常，请联系管理员");
                  this.log.error("保存借阅申请修改异常，请联系管理员", e);
               }

               return resultMsg;
            }
         }
      } else {
         resultMsg = AjaxResult.error("借阅期限参数错误！");
         return resultMsg;
      }
   }

   @ResponseBody
   @PreAuthorize("@ss.hasPermi('borrowing:borrow:voidApply')")
   @RequestMapping({"/voidApply"})
   public AjaxResult voidApply(@RequestBody UpdateApplyDetailReq req) {
      AjaxResult resultMsg = AjaxResult.success("保存成功");
      if (req.getId() == null) {
         resultMsg = AjaxResult.error("主键不能为空！");
         return resultMsg;
      } else {
         EmrIndexBorrow emrIndexBorrow = this.emrIndexBorrowService.selectEmrIndexBorrowById(req.getId());
         String appStatus = emrIndexBorrow.getAppStatus();
         if (!appStatus.equals("0")) {
            resultMsg = AjaxResult.error("只有申请中的借阅才能作废！");
            return resultMsg;
         } else {
            try {
               this.emrIndexBorrowService.updateApply(req, "3", emrIndexBorrow);
            } catch (Exception e) {
               resultMsg = AjaxResult.error("保存借阅申请作废异常，请联系管理员");
               this.log.error("保存借阅申请作废异常，请联系管理员", e);
            }

            return resultMsg;
         }
      }
   }

   @PreAuthorize("@ss.hasAnyPermi('borrowing:borrow:patientOutList')")
   @GetMapping({"/patientOutList"})
   public TableDataInfo patientOutList(PatientOutReq req) {
      new TableDataInfo();

      TableDataInfo tableDataInfo;
      try {
         this.startPage();
         List<PatientOutResp> list = this.emrIndexBorrowService.patientOutList(req);
         tableDataInfo = this.getDataTable(list);
      } catch (Exception e) {
         tableDataInfo = new TableDataInfo(500, "查询出院患者列表出现异常");
         this.log.error("查询出院患者列表出现异常，请联系管理员", e);
      }

      return tableDataInfo;
   }
}
