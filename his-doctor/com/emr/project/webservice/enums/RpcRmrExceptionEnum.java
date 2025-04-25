package com.emr.project.webservice.enums;

public enum RpcRmrExceptionEnum {
   PARAMETER_NULL("参数为空", 1000),
   PARAMETER_INVALID("参数非法", 1001),
   SIGN_NULL("签名串为空", 1002),
   SIGN_ERROR("验签失败", 1003),
   OPERATION_FAILED("操作失败", 1004),
   OTHER_ERROR_MESSAGE("其他错误", 1005),
   UNKNOWN_ERROR_MESSAGE("发现未知错误", 1006),
   PROJECT_CODE_ERROR("项目编码不能为空", 1007),
   PROJECT_CODE_EXISTENT("项目编码不存在", 1008),
   PATIENT_ID_NULL("患者id不能为空", 2001),
   QC_TYPE_NULL("病历操作类型不能为空", 2002),
   PATIENT_NOT_OUT("患者暂未出院，不能操作病历", 2003),
   PATIENT_NO_QC_FLOW("患者无质控记录", 2004),
   QC_FILE("患者的病历已归档，不能重复归档", 2005),
   QC_NO_FILE("患者的病历未归档，不能撤销归档", 2006),
   QC_NO_SIGN("病案首页还未完成签名，不能归档病历", 2007),
   QC_TYPE_ERROR("类型错误", 2008),
   OPE_STATUS_ERROR("手术状态不能为空", 2008),
   PATIENT_NOT_IN_DEPT("患者不是在科状态", 2010);

   String message;
   int code;

   public String getMessage() {
      return this.message;
   }

   public void setMessage(String message) {
      this.message = message;
   }

   public int getCode() {
      return this.code;
   }

   public void setCode(int code) {
      this.code = code;
   }

   private RpcRmrExceptionEnum(String message, int code) {
      this.message = message;
      this.code = code;
   }
}
