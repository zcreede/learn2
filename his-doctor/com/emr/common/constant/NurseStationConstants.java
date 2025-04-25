package com.emr.common.constant;

public class NurseStationConstants {
   public static final String SYSTEM_STAFF_CODE = "system";
   public static final String SYSTEM_PASSWORD = "123456";
   public static final String SOURCE_PROGRAM = "java";
   public static final String SOURCE_PROGRAM_1 = "诊疗费录入";
   public static final Boolean IS_SYNC_TO_HIS = false;
   public static final String PROC_CODE_1 = "1";
   public static final String PROC_CODE_0 = "0";
   public static final String VALID_STR_1 = "1";
   public static final String VALID_STR_0 = "0";
   public static final int VALID_INT_1 = 1;
   public static final int VALID_INT_0 = 0;
   public static final String CRBZ_0 = "0";
   public static final String CRBZ_1 = "1";

   public interface BEDS {
      String BEDSTATUS_USED = "01";
      String BEDSTATUS_NOTUSED = "02";
      String BEDCLASSIFY_NORMAL = "01";
      String BEDCLASSIFY_ADD = "02";
      String BEDCLASSIFY_BABY = "03";
      String WRAPPEDSTATUS_YES = "1";
      String WRAPPEDSTATUS_NO = "0";
      String ENABLEFLAG_YES = "1";
      String ENABLEFLAG_NO = "0";
      String AIRFEE = "airfee";
      String HEATFEE = "heatfee";
      String WASTERFEE = "wasterfee";
      String EXAMFEE = "examfee";
      String OTHERFEE1 = "otherfee1";
      String OTHERFEE2 = "otherfee2";
   }

   public interface CONFIGURE {
      String MZZY_CODE = "6035";
   }

   public interface CROSSDEPAPPLY {
      int OPERATION_TYPE_1 = 1;
      int OPERATION_TYPE_9 = 9;
      String MARK_0 = "0";
      String MARK_1 = "1";
      String MARK_2 = "2";
   }

   public interface DJCD_DEFINE {
      int TYPE_1 = 1;
      int TYPE_2 = 2;
      int TYPE_3 = 3;
      int TYPE_4 = 4;
   }

   public interface MEDINFO {
      String SEX_MAN = "1";
      String SEX_WOMAN = "2";
   }

   public interface OPERATION_PATFEE {
      String FEE_TYPE_0303 = "0303";
      String FEE_TYPE_0303_2 = "0303_2";
   }

   public interface PATFEE {
      String FEE_TYPE_0203 = "0203";
      String FEE_TYPE_0204 = "0204";
      String FEE_TYPE_0205 = "0205";
      String FEE_TYPE_0205_2 = "0205_2";
      String FEE_TYPE_0206 = "0206";
      String FEE_TYPE_0207 = "0207";
   }

   public interface PAT_FEE {
      String FIRST_CODE_1 = "1";
      String FIRST_CODE_3 = "3";
   }

   public interface REPORT {
      int REPORT_STATUS_ADD = 0;
      int REPORT_STATUS_AUDIT = 1;
      int REPORT_STATUS_FILE = 2;
   }

   public interface SIGN_TYPE {
      String SIGN_TYPE_1 = "1";
      String SIGN_TYPE_2 = "2";
      String SIGN_DESC_1 = "单条签名";
      String SIGN_DESC_2 = "打包签名";
      String SIGN_MODE_EXAMINE = "住院号,医嘱编码,医嘱序号,序号,医嘱类型,医嘱类别,开嘱科室,开嘱医师,医嘱开始时间,医嘱项目编码,医嘱项目名称,规格,每次用量,用量单位,频率,用法,数量,数量单位,首日次数,特殊标志,草药剂数,医嘱说明,医师说明,部位,标本,医嘱提交医师,提交时间;";
      String SIGN_MODE_PERFORM = "住院号,医嘱编码,医嘱序号,序号,医嘱类型,医嘱类别,开嘱科室,开嘱医师,医嘱开始时间,医嘱项目编码,医嘱项目名称,规格,每次用量,用量单位,频率,用法,数量,数量单位,首日次数,特殊标志,草药剂数,医嘱说明,医师说明,部位,标本,医嘱提交医师,提交时间,审核人,审核时间;";
   }
}
