package com.emr.project.webservice.controller;

import com.alibaba.fastjson.JSONObject;
import com.emr.common.utils.StringUtils;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.project.docOrder.domain.TdCaOperationApply;
import com.emr.project.operation.domain.Medicalinformation;
import com.emr.project.operation.service.IMedicalinfoService;
import com.emr.project.qc.domain.PatEvent;
import com.emr.project.qc.service.IPatEventService;
import com.emr.project.webservice.domain.EmrProjectMessage;
import com.emr.project.webservice.domain.vo.OperationInfoVo;
import com.emr.project.webservice.enums.RpcRmrExceptionEnum;
import com.emr.project.webservice.service.IEmrProjectMessageService;
import com.emr.project.webservice.service.OperationService;
import com.emr.project.webservice.utils.SignUtil;
import java.util.Date;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/rpc/oper"})
public class OperationRpcController {
   protected final Logger logger = LoggerFactory.getLogger(this.getClass());
   @Autowired
   private OperationService operationService;
   @Autowired
   private IEmrProjectMessageService emrProjectMessageService;
   @Autowired
   private IPatEventService patEventService;
   @Autowired
   private IMedicalinfoService medicalinfoService;

   @ResponseBody
   @PostMapping({"/updateOperationInfo"})
   public AjaxResult updateOperationInfo(@RequestBody OperationInfoVo operationInfoVo) {
      AjaxResult ajaxResult = AjaxResult.success("执行成功");
      this.logger.info("手麻回调开始 1入参，----->{}", JSONObject.toJSONString(operationInfoVo));
      String projectCode = operationInfoVo.getProjectCode();
      if (StringUtils.isEmpty(projectCode)) {
         return AjaxResult.error(RpcRmrExceptionEnum.PROJECT_CODE_ERROR.getCode(), RpcRmrExceptionEnum.PROJECT_CODE_ERROR.getMessage());
      } else {
         EmrProjectMessage message = this.emrProjectMessageService.selectEmrProjectMessageById(projectCode);
         if (message == null) {
            return AjaxResult.error(RpcRmrExceptionEnum.PROJECT_CODE_EXISTENT.getCode(), RpcRmrExceptionEnum.PROJECT_CODE_EXISTENT.getMessage());
         } else {
            String appKey = message.getProjectKey();
            String signString = SignUtil.encryptString((HashMap)JSONObject.parseObject(JSONObject.toJSONString(operationInfoVo), HashMap.class), appKey);
            if (StringUtils.isEmpty(signString)) {
               return AjaxResult.error(RpcRmrExceptionEnum.SIGN_NULL.getCode(), RpcRmrExceptionEnum.SIGN_NULL.getMessage());
            } else {
               String applyFormNo = operationInfoVo.getApplyFormNo();
               String sign = operationInfoVo.getSign();
               this.logger.info("手麻回调2 校验秘钥 入参秘钥{}，系统生成秘钥{}", sign, signString);
               if (StringUtils.isEmpty(sign)) {
                  return AjaxResult.error(RpcRmrExceptionEnum.SIGN_NULL.getCode(), RpcRmrExceptionEnum.SIGN_NULL.getMessage());
               } else if (!signString.equals(sign)) {
                  return AjaxResult.error(RpcRmrExceptionEnum.SIGN_ERROR.getCode(), RpcRmrExceptionEnum.SIGN_ERROR.getMessage());
               } else if (StringUtils.isEmpty(applyFormNo)) {
                  return AjaxResult.error(RpcRmrExceptionEnum.PARAMETER_NULL.getCode(), RpcRmrExceptionEnum.PARAMETER_NULL.getMessage());
               } else {
                  TdCaOperationApply operationApply = this.operationService.selectTdCaOperationApplyById(applyFormNo);
                  if (operationApply == null) {
                     return AjaxResult.error(RpcRmrExceptionEnum.PARAMETER_INVALID.getCode(), RpcRmrExceptionEnum.PARAMETER_INVALID.getMessage());
                  } else {
                     try {
                        this.logger.info("手麻回调3 处理业务逻辑开始----->START");
                        this.operationService.updateOperationInfo(operationInfoVo);
                        this.logger.info("手麻回调4 处理业务逻辑结束----->END");
                        if (StringUtils.isNotEmpty(operationInfoVo.getStatus())) {
                           this.logger.info("手麻回调5 后续处理---记录事件,手术状态{}----->START", operationInfoVo.getStatus());
                           if (operationInfoVo.getStatus().equals("06")) {
                              Medicalinformation baseInfo = this.medicalinfoService.selectMedicalinfoByPatientId(operationApply.getAdmissionNo());
                              if (baseInfo != null) {
                                 PatEvent patEvent = new PatEvent(operationApply.getHospitalCode(), operationApply.getAdmissionNo(), operationApply.getPatientId(), operationApply.getPatientName(), baseInfo.getResidentCode(), baseInfo.getResidentName(), "06", "手术", new Date(), (Date)null);
                                 this.patEventService.insertPatEvent(patEvent);
                                 this.logger.info("手麻回调6 后续处理---记录事件完成----->END");
                              }
                           }
                        }

                        this.logger.info("手麻回调7 手麻回调结束----->END");
                     } catch (Exception e) {
                        ajaxResult = AjaxResult.error(RpcRmrExceptionEnum.UNKNOWN_ERROR_MESSAGE.getCode(), RpcRmrExceptionEnum.UNKNOWN_ERROR_MESSAGE.getMessage());
                        this.logger.error("手麻回调-1 ----手麻回调出现异常！", e);
                     }

                     return ajaxResult;
                  }
               }
            }
         }
      }
   }

   @ResponseBody
   @PostMapping({"/getSignString"})
   public String getSignString(@RequestBody OperationInfoVo operationInfoVo) {
      String projectCode = operationInfoVo.getProjectCode();
      EmrProjectMessage message = this.emrProjectMessageService.selectEmrProjectMessageById(projectCode);
      String appKey = message.getProjectKey();
      String signString = SignUtil.encryptString((HashMap)JSONObject.parseObject(JSONObject.toJSONString(operationInfoVo), HashMap.class), appKey);
      return signString;
   }
}
