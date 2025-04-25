package com.emr.project.webservice.controller;

import com.alibaba.fastjson.JSONObject;
import com.emr.common.utils.StringUtils;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.project.operation.domain.Medicalinformation;
import com.emr.project.operation.service.IMedicalinfoService;
import com.emr.project.webservice.domain.EmrProjectMessage;
import com.emr.project.webservice.domain.req.InOutLCLJReq;
import com.emr.project.webservice.enums.RpcRmrExceptionEnum;
import com.emr.project.webservice.service.IEmrProjectMessageService;
import com.emr.project.webservice.utils.SignUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/rpc/pat"})
public class LCLJInOrOutController {
   protected final Logger log = LoggerFactory.getLogger(this.getClass());
   @Autowired
   private IEmrProjectMessageService emrProjectMessageService;
   @Autowired
   private IMedicalinfoService medicalinfoService;

   @PostMapping({"/inOutLCLJ"})
   public AjaxResult inOutLCLJ(HttpServletRequest request, @RequestBody InOutLCLJReq inOutLCLJReq) {
      this.log.warn("临床路径入径出径接口入参：：" + JSONObject.toJSONString(inOutLCLJReq));
      AjaxResult ajaxResult = AjaxResult.success("执行成功");
      String projectCode = inOutLCLJReq.getProjectCode();
      if (StringUtils.isEmpty(projectCode)) {
         return AjaxResult.error(RpcRmrExceptionEnum.PROJECT_CODE_ERROR.getCode(), RpcRmrExceptionEnum.PROJECT_CODE_ERROR.getMessage());
      } else {
         EmrProjectMessage message = this.emrProjectMessageService.selectEmrProjectMessageById(projectCode);
         if (message == null) {
            return AjaxResult.error(RpcRmrExceptionEnum.PROJECT_CODE_EXISTENT.getCode(), RpcRmrExceptionEnum.PROJECT_CODE_EXISTENT.getMessage());
         } else {
            String appKey = message.getProjectKey();
            this.log.warn("临床路径入径出径接口入参：：" + JSONObject.toJSONString(inOutLCLJReq));
            new ObjectMapper();
            String signString = SignUtil.encryptString((HashMap)JSONObject.parseObject(JSONObject.toJSONString(inOutLCLJReq), HashMap.class), appKey);
            this.log.warn("临床路径入径出径接口入参加密：：" + signString);
            if (StringUtils.isEmpty(signString)) {
               return AjaxResult.error(RpcRmrExceptionEnum.SIGN_NULL.getCode(), RpcRmrExceptionEnum.SIGN_NULL.getMessage());
            } else {
               String sign = inOutLCLJReq.getSign();
               if (StringUtils.isEmpty(sign)) {
                  return AjaxResult.error(RpcRmrExceptionEnum.SIGN_NULL.getCode(), RpcRmrExceptionEnum.SIGN_NULL.getMessage());
               } else if (!signString.equals(sign)) {
                  return AjaxResult.error(RpcRmrExceptionEnum.SIGN_ERROR.getCode(), RpcRmrExceptionEnum.SIGN_ERROR.getMessage());
               } else {
                  String admissionNo = inOutLCLJReq.getAdmissionNo();
                  if (StringUtils.isBlank(admissionNo)) {
                     return AjaxResult.error(RpcRmrExceptionEnum.PARAMETER_NULL.getCode(), RpcRmrExceptionEnum.PARAMETER_NULL.getMessage());
                  } else {
                     String inOutFlag = inOutLCLJReq.getInOutFlag();
                     if (StringUtils.isBlank(inOutFlag)) {
                        return AjaxResult.error(RpcRmrExceptionEnum.PARAMETER_NULL.getCode(), RpcRmrExceptionEnum.PARAMETER_NULL.getMessage());
                     } else {
                        String cpNo = inOutLCLJReq.getCpNo();
                        String cpName = inOutLCLJReq.getInOutFlag();
                        if (!StringUtils.isNotBlank(inOutFlag) || !inOutFlag.equals("1") || !StringUtils.isBlank(cpNo) && !StringUtils.isBlank(cpName)) {
                           Medicalinformation medicalinformation = this.medicalinfoService.queryByAdmissionNo(admissionNo);
                           if (medicalinformation == null) {
                              return AjaxResult.error(RpcRmrExceptionEnum.PARAMETER_INVALID.getCode(), RpcRmrExceptionEnum.PARAMETER_INVALID.getMessage());
                           } else if (medicalinformation != null && !medicalinformation.getHospitalMark().equals("1")) {
                              return AjaxResult.error(RpcRmrExceptionEnum.PATIENT_NOT_IN_DEPT.getCode(), RpcRmrExceptionEnum.PATIENT_NOT_IN_DEPT.getMessage());
                           } else {
                              try {
                                 Medicalinformation m = new Medicalinformation();
                                 m.setAdmissionNo(inOutLCLJReq.getAdmissionNo());
                                 m.setCpFlag(inOutLCLJReq.getInOutFlag().equals("1") ? "1" : "0");
                                 m.setCpNo(inOutLCLJReq.getCpNo());
                                 m.setCpName(inOutLCLJReq.getCpName());
                                 this.medicalinfoService.updateLCLJInfo(m);
                              } catch (Exception e) {
                                 this.log.error("临床路径调用", e);
                                 ajaxResult = AjaxResult.error(RpcRmrExceptionEnum.UNKNOWN_ERROR_MESSAGE.getCode(), RpcRmrExceptionEnum.UNKNOWN_ERROR_MESSAGE.getMessage());
                              }

                              return ajaxResult;
                           }
                        } else {
                           return AjaxResult.error(RpcRmrExceptionEnum.PARAMETER_NULL.getCode(), RpcRmrExceptionEnum.PARAMETER_NULL.getMessage());
                        }
                     }
                  }
               }
            }
         }
      }
   }
}
