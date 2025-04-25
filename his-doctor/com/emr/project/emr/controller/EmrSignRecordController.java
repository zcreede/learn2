package com.emr.project.emr.controller;

import com.emr.common.utils.MessageUtils;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.dr.domain.TdCaConsApply;
import com.emr.project.dr.service.ITdCaConsApplyService;
import com.emr.project.emr.domain.EmrSignRecord;
import com.emr.project.emr.domain.EmrTaskInfo;
import com.emr.project.emr.domain.Index;
import com.emr.project.emr.domain.SubfileIndex;
import com.emr.project.emr.domain.vo.EmrSignRecordVo;
import com.emr.project.emr.domain.vo.IndexSignCancelVo;
import com.emr.project.emr.service.IEditStateService;
import com.emr.project.emr.service.IEmrSignRecordService;
import com.emr.project.emr.service.IEmrTaskInfoService;
import com.emr.project.emr.service.IIndexService;
import com.emr.project.emr.service.ISubfileIndexService;
import com.emr.project.other.domain.BasCertInfo;
import com.emr.project.other.service.IBasCertInfoService;
import com.emr.project.pat.domain.vo.VisitinfoVo;
import com.emr.project.pat.service.IVisitinfoService;
import com.emr.project.qc.domain.EmrQcFlow;
import com.emr.project.qc.domain.EmrQcList;
import com.emr.project.qc.service.IEmrQcFlowService;
import com.emr.project.qc.service.IEmrQcListService;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.service.ISysEmrConfigService;
import com.emr.project.system.service.ISysUserService;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
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
@RequestMapping({"/emr/sign"})
public class EmrSignRecordController extends BaseController {
   @Autowired
   private IEmrSignRecordService emrSignRecordService;
   @Autowired
   private IIndexService indexService;
   @Autowired
   private ISubfileIndexService subfileIndexService;
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;
   @Autowired
   private IVisitinfoService visitinfoService;
   @Autowired
   private IEmrQcFlowService emrQcFlowService;
   @Autowired
   private ITdCaConsApplyService tdCaConsApplyService;
   @Autowired
   private IEmrTaskInfoService emrTaskInfoService;
   @Autowired
   private IEmrQcListService emrQcListService;
   @Autowired
   private IEditStateService editStateService;
   @Autowired
   private IBasCertInfoService basCertInfoService;
   @Autowired
   private ISysUserService sysUserService;

   @PreAuthorize("@ss.hasPermi('emr:sign:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(EmrSignRecord emrSignRecord) {
      this.startPage();
      List<EmrSignRecord> list = this.emrSignRecordService.selectEmrSignRecordList(emrSignRecord);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('emr:sign:export')")
   @Log(
      title = "病历签名记录",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(EmrSignRecord emrSignRecord) {
      List<EmrSignRecord> list = this.emrSignRecordService.selectEmrSignRecordList(emrSignRecord);
      ExcelUtil<EmrSignRecord> util = new ExcelUtil(EmrSignRecord.class);
      return util.exportExcel(list, "病历签名记录数据");
   }

   @PreAuthorize("@ss.hasPermi('emr:sign:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.emrSignRecordService.selectEmrSignRecordById(id));
   }

   @PreAuthorize("@ss.hasPermi('emr:sign:add')")
   @Log(
      title = "病历签名记录",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody EmrSignRecord emrSignRecord) {
      return this.toAjax(this.emrSignRecordService.insertEmrSignRecord(emrSignRecord));
   }

   @PreAuthorize("@ss.hasPermi('emr:sign:edit')")
   @Log(
      title = "病历签名记录",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody EmrSignRecord emrSignRecord) {
      return this.toAjax(this.emrSignRecordService.updateEmrSignRecord(emrSignRecord));
   }

   @PreAuthorize("@ss.hasPermi('emr:sign:remove')")
   @Log(
      title = "病历签名记录",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.emrSignRecordService.deleteEmrSignRecordByIds(ids));
   }

   @PreAuthorize("@ss.hasAnyPermi('emr:sign:judgeCancelSign,emr:index:sign')")
   @PostMapping({"/judgeCancelSign"})
   public AjaxResult judgeCancelSign(@RequestBody IndexSignCancelVo indexSignCancelVo, HttpServletRequest request) {
      AjaxResult ajaxResult = AjaxResult.success();

      try {
         boolean flag = true;
         if (indexSignCancelVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && indexSignCancelVo.getId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error(MessageUtils.message("emr.index.sign.cancel.false"));
         }

         Index index = null;
         if (flag) {
            index = this.indexService.selectIndexById(indexSignCancelVo.getId());
            if (index == null) {
               flag = false;
               ajaxResult = AjaxResult.error("当前病历文件记录不存在,不能取消签名");
            }
         }

         TdCaConsApply tdCaConsApply = flag ? this.tdCaConsApplyService.selectTdCaConsApplyByMrFileId(indexSignCancelVo.getId()) : null;
         if (flag && tdCaConsApply != null) {
            flag = false;
            ajaxResult = AjaxResult.error("申请会诊创建的病历,不能取消签名");
         }

         SubfileIndex subfileIndex = null;
         if (flag && "MAINFILE".equals(index.getMrType())) {
            if (indexSignCancelVo.getSubFileIndexId() == null) {
               flag = false;
               ajaxResult = AjaxResult.error("当前子病历文件记录不存在,不能取消签名");
            }

            if (flag) {
               subfileIndex = this.subfileIndexService.selectSubfileIndexById(indexSignCancelVo.getSubFileIndexId());
               if (subfileIndex == null) {
                  flag = false;
                  ajaxResult = AjaxResult.error("当前病历子文件不存在,不能取消签名");
               }
            }

            if (flag && StringUtils.isBlank(indexSignCancelVo.getXmlStr())) {
               flag = false;
               ajaxResult = AjaxResult.error("子病历的xml信息不能为空");
            }
         }

         if (flag) {
            Long mrFileId = indexSignCancelVo.getSubFileIndexId() == null ? indexSignCancelVo.getId() : indexSignCancelVo.getSubFileIndexId();
            List<String> qcSectionList = Arrays.asList("05");
            List<EmrQcList> qcLists = this.emrQcListService.selectEmrQcListByMrFileId(mrFileId, qcSectionList);
            if (!qcLists.isEmpty()) {
               flag = Boolean.FALSE;
               ajaxResult = AjaxResult.error("该病历已被质控，不允许取消签名！请在病历上留痕修改。");
            }
         }

         if (flag) {
            VisitinfoVo visitinfoVo = this.visitinfoService.selectVisitinfoById(index.getPatientId());
            EmrQcFlow emrQcFlow = this.emrQcFlowService.selectEmrQcFlowById(visitinfoVo.getOrgCd(), visitinfoVo.getPatientId());
            if (emrQcFlow != null && (emrQcFlow.getMrState().equals("10") || emrQcFlow.getMrState().equals("12") || emrQcFlow.getMrState().equals("14") || emrQcFlow.getMrState().equals("16") || emrQcFlow.getMrState().equals("13"))) {
               flag = false;
               String mrStateName = "";
               switch (emrQcFlow.getMrState()) {
                  case "10":
                     mrStateName = "提交科室质控";
                     break;
                  case "12":
                     mrStateName = "申请归档";
                     break;
                  case "14":
                     mrStateName = "提交病案室";
                     break;
                  case "16":
                     mrStateName = "病历归档";
                     break;
                  case "13":
                     mrStateName = "终末质控退回";
               }

               ajaxResult = AjaxResult.error("当前患者的病历状态是" + mrStateName + "，不能取消签名");
            }
         }

         if (flag) {
            index.setFreeMoveType(indexSignCancelVo.getFreeMoveType());
            ajaxResult = this.indexService.isCancelSign(index, subfileIndex, indexSignCancelVo.getXmlStr());
            String caFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0001");
            String caType = this.sysEmrConfigService.selectSysEmrConfigByKey("0002");
            ajaxResult.put("caFlag", caFlag);
            ajaxResult.put("caType", caType);
         }
      } catch (Exception e) {
         this.log.error("病历文件允许取消签名判断异常", e);
         ajaxResult = AjaxResult.error("病历文件允许取消签名判断异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('emr:sign:cancleEmrSign,emr:index:sign')")
   @GetMapping({"/cancleEmrSign"})
   public AjaxResult cancleEmrSign(IndexSignCancelVo indexSignCancelVo, HttpServletRequest request) {
      AjaxResult ajaxResult = AjaxResult.success();
      Long id = indexSignCancelVo.getSubFileIndexId() == null ? indexSignCancelVo.getId() : indexSignCancelVo.getSubFileIndexId();

      try {
         boolean flag = true;
         if (flag && indexSignCancelVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && indexSignCancelVo.getId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("病历文件ID不能为空");
         }

         Index index = null;
         if (flag) {
            index = this.indexService.selectIndexById(indexSignCancelVo.getId());
            if (index == null) {
               flag = false;
               ajaxResult = AjaxResult.error("不存在此病历文件记录");
            }
         }

         SubfileIndex subfileIndex = null;
         if (flag && index.getMrType().equals("MAINFILE")) {
            if (indexSignCancelVo.getSubFileIndexId() == null) {
               flag = false;
               ajaxResult = AjaxResult.error("病历子文件ID不能为空");
            }

            if (flag) {
               subfileIndex = this.subfileIndexService.selectSubfileIndexById(indexSignCancelVo.getSubFileIndexId());
               if (subfileIndex == null) {
                  flag = false;
                  ajaxResult = AjaxResult.error("不存在此病历子文件记录");
               }
            }
         }

         if (flag) {
            VisitinfoVo visitinfoVo = this.visitinfoService.selectVisitinfoById(index.getPatientId());
            EmrQcFlow emrQcFlow = this.emrQcFlowService.selectEmrQcFlowById(visitinfoVo.getOrgCd(), visitinfoVo.getPatientId());
            if (emrQcFlow != null && (emrQcFlow.getMrState().equals("10") || emrQcFlow.getMrState().equals("12") || emrQcFlow.getMrState().equals("14") || emrQcFlow.getMrState().equals("16"))) {
               flag = false;
               String mrStateName = "";
               switch (emrQcFlow.getMrState()) {
                  case "10":
                     mrStateName = "提交科室质控";
                     break;
                  case "12":
                     mrStateName = "申请归档";
                     break;
                  case "14":
                     mrStateName = "提交病案室";
                     break;
                  case "16":
                     mrStateName = "病历归档";
               }

               ajaxResult = AjaxResult.error("当前患者的病历状态是" + mrStateName + "，不能取消签名");
            }
         }

         if (flag) {
            new EmrSignRecordVo();
            SysUser sysUser = SecurityUtils.getLoginUser().getUser();
            EmrSignRecord emrSignRecord = new EmrSignRecord();
            emrSignRecord.setMrFileId(id);
            emrSignRecord.setSignCancFlag("0");
            emrSignRecord.setDocCode(sysUser.getUserName());
            List<EmrSignRecord> emrSignRecordList = this.emrSignRecordService.selectEmrSignRecordForFeeMoveList(emrSignRecord);
            EmrSignRecordVo var16;
            if (emrSignRecordList != null && emrSignRecordList.size() > 0) {
               var16 = this.emrSignRecordService.cancleEmrSignForFreeMove(index, indexSignCancelVo);
            } else {
               var16 = this.emrSignRecordService.cancleEmrSign(index, subfileIndex, indexSignCancelVo.getSignElemList());
               if (var16.getEmrState().equals("05") || var16.getEmrState().equals("07")) {
                  EmrTaskInfo emrTaskInfo = new EmrTaskInfo();
                  emrTaskInfo.setTaskType("3");
                  emrTaskInfo.setTreatFlag("0");
                  emrTaskInfo.setBusId(subfileIndex != null ? subfileIndex.getId().toString() : index.getId().toString());
                  List<EmrTaskInfo> emrTaskInfoList = this.emrTaskInfoService.selectEmrTaskInfoList(emrTaskInfo);
                  ajaxResult.put("dutyDocName", CollectionUtils.isNotEmpty(emrTaskInfoList) ? ((EmrTaskInfo)emrTaskInfoList.get(0)).getDocName() : null);
                  if (CollectionUtils.isNotEmpty(emrTaskInfoList)) {
                     BasCertInfo basCertInfo = this.basCertInfoService.selectBasCertInfoByEmployeenumber(((EmrTaskInfo)emrTaskInfoList.get(0)).getDocCd());
                     ajaxResult.put("signCertSn", basCertInfo != null ? basCertInfo.getCertSn() : null);
                  }
               }
            }

            String[] staffCodes = new String[]{sysUser.getUserName()};
            this.sysUserService.reduceUseCount(sysUser.getDept().getDeptCode(), staffCodes);
            ajaxResult.put("emrSignRecord", var16.getList());
            ajaxResult.put("mrState", var16.getEmrState());
            ajaxResult.put("freeMoveType", var16.getFreeMoveType());
            if (var16.getEmrState().equals("05") || var16.getEmrState().equals("07") || var16.getEmrState().equals("08") || "22".equals(indexSignCancelVo.getFreeMoveType())) {
               ajaxResult.put("switchRecension", true);
            }
         }
      } catch (Exception e) {
         this.log.error("病历文件取消签名异常", e);
         ajaxResult = AjaxResult.error("病历文件取消签名异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('emr:sign:verifyPassword')")
   @GetMapping({"/verifyPassword"})
   public AjaxResult verifyPassword(String password) {
      AjaxResult ajaxResult = AjaxResult.success("密码正确");
      SysUser user = SecurityUtils.getLoginUser().getUser();
      Boolean flag = true;

      try {
         String truePassword = user.getPassword();
         if (!SecurityUtils.matchesPassword(password, truePassword)) {
            flag = false;
            ajaxResult = AjaxResult.error("密码错误，验证失败");
         }
      } catch (Exception e) {
         this.log.error("当前用户密码验证异常", e);
         ajaxResult = AjaxResult.error("当前用户密码验证异常");
      }

      return ajaxResult;
   }
}
