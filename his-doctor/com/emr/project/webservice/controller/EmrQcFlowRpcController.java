package com.emr.project.webservice.controller;

import com.emr.common.utils.StringUtils;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.project.mrhp.domain.vo.MrHpVo;
import com.emr.project.mrhp.service.IMrHpService;
import com.emr.project.pat.domain.vo.VisitinfoVo;
import com.emr.project.pat.service.IVisitinfoService;
import com.emr.project.qc.domain.EmrQcFlow;
import com.emr.project.qc.domain.EmrQcRecord;
import com.emr.project.qc.service.IEmrQcFlowService;
import com.emr.project.qc.service.IEmrQcRecordService;
import com.emr.project.system.service.ISysEmrConfigService;
import com.emr.project.webservice.domain.EmrQcFlowReq;
import com.emr.project.webservice.enums.RpcRmrExceptionEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/rpc/qc"})
public class EmrQcFlowRpcController {
   protected final Logger log = LoggerFactory.getLogger(EmrQcFlowRpcController.class);
   @Autowired
   private IEmrQcFlowService emrQcFlowService;
   @Autowired
   private IVisitinfoService visitinfoService;
   @Autowired
   private IMrHpService mrHpService;
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;
   @Autowired
   private IEmrQcRecordService emrQcRecordService;

   @ResponseBody
   @PostMapping({"/updateQcFlow"})
   public AjaxResult updateQcFlow(@RequestBody EmrQcFlowReq req) {
      AjaxResult ajaxResult = AjaxResult.success("执行成功");
      if (StringUtils.isEmpty(req.getPatientId())) {
         ajaxResult = AjaxResult.error(RpcRmrExceptionEnum.PATIENT_ID_NULL.getCode(), RpcRmrExceptionEnum.PATIENT_ID_NULL.getMessage());
         return ajaxResult;
      } else if (StringUtils.isEmpty(req.getType())) {
         ajaxResult = AjaxResult.error(RpcRmrExceptionEnum.QC_TYPE_NULL.getCode(), RpcRmrExceptionEnum.QC_TYPE_NULL.getMessage());
         return ajaxResult;
      } else {
         try {
            VisitinfoVo visitInfoVo = this.visitinfoService.selectVisitinfoByPatientId(req.getPatientId());
            if (visitInfoVo == null || visitInfoVo.getOutTime() == null) {
               ajaxResult = AjaxResult.error(RpcRmrExceptionEnum.PATIENT_NOT_OUT.getCode(), RpcRmrExceptionEnum.PATIENT_NOT_OUT.getMessage());
               return ajaxResult;
            }

            EmrQcFlow emrQcFlow = this.emrQcFlowService.selectEmrQcFlowById(visitInfoVo.getOrgCd(), req.getPatientId());
            if (emrQcFlow == null) {
               ajaxResult = AjaxResult.error(RpcRmrExceptionEnum.PATIENT_NO_QC_FLOW.getCode(), RpcRmrExceptionEnum.PATIENT_NO_QC_FLOW.getMessage());
               return ajaxResult;
            }

            EmrQcRecord emrQcRecord = null;
            if (req.getType().equals("1")) {
               label72: {
                  if (!emrQcFlow.getMrState().equals("16") && !emrQcFlow.getMrState().equals("14")) {
                     MrHpVo mrHpVo = this.mrHpService.selectMrHpByPatientId(req.getPatientId());
                     String orderFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0050");
                     if (!orderFlag.equals("0") || mrHpVo != null && !StringUtils.isEmpty(mrHpVo.getMrState()) && mrHpVo.getMrState().equals("03")) {
                        break label72;
                     }

                     ajaxResult = AjaxResult.error(RpcRmrExceptionEnum.QC_NO_SIGN.getCode(), RpcRmrExceptionEnum.QC_NO_SIGN.getMessage());
                     return ajaxResult;
                  }

                  ajaxResult = AjaxResult.error(RpcRmrExceptionEnum.QC_FILE.getCode(), RpcRmrExceptionEnum.QC_FILE.getMessage());
                  return ajaxResult;
               }
            } else {
               if (!req.getType().equals("2")) {
                  ajaxResult = AjaxResult.error(RpcRmrExceptionEnum.QC_TYPE_ERROR.getCode(), RpcRmrExceptionEnum.QC_TYPE_ERROR.getMessage());
                  return ajaxResult;
               }

               if (!emrQcFlow.getMrState().equals("16") && !emrQcFlow.getMrState().equals("14")) {
                  ajaxResult = AjaxResult.error(RpcRmrExceptionEnum.QC_NO_FILE.getCode(), RpcRmrExceptionEnum.QC_NO_FILE.getMessage());
                  return ajaxResult;
               }

               emrQcRecord = this.emrQcRecordService.selectEmrQcRecordByQcSection("05", req.getPatientId());
               if (emrQcRecord == null) {
                  ajaxResult = AjaxResult.error(RpcRmrExceptionEnum.PATIENT_NO_QC_FLOW.getCode(), RpcRmrExceptionEnum.PATIENT_NO_QC_FLOW.getMessage());
                  return ajaxResult;
               }
            }

            this.emrQcFlowService.updateQcFlowByType(visitInfoVo, emrQcFlow, req, emrQcRecord);
         } catch (Exception e) {
            this.log.error("调用操作病历归档接口出现异常，请联系管理员!", e);
            ajaxResult = AjaxResult.error(RpcRmrExceptionEnum.UNKNOWN_ERROR_MESSAGE.getCode(), RpcRmrExceptionEnum.UNKNOWN_ERROR_MESSAGE.getMessage());
         }

         return ajaxResult;
      }
   }
}
