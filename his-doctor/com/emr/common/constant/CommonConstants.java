package com.emr.common.constant;

import java.util.HashMap;
import java.util.Map;

public class CommonConstants {
   public static final String EMPTY_STRING = "";
   public static final String VALID_FLAG_STR_1 = "1";
   public static final String VALID_FLAG_STR_2 = "2";
   public static final String VALID_FLAG_STR_3 = "3";
   public static final String VALID_FLAG_STR_4 = "4";
   public static final String VALID_FLAG_STR_5 = "5";
   public static final String VALID_FLAG_STR_6 = "6";
   public static final String VALID_FLAG_STR_0 = "0";
   public static final String SEVERE_CASE_FLAG_0 = "0";
   public static final String SEVERE_CASE_FLAG_1 = "1";
   public static final int VALID_FLAG_1 = 1;
   public static final int VALID_FLAG_0 = 0;
   public static final String ENABLE_FLAG_1 = "1";
   public static final String ENABLE_FLAG_0 = "0";
   public static final String ZERO_S = "0";
   public static final String FIRST_FLOOR = "-1";
   public static final String DEL_FLAG_1 = "1";
   public static final String DEL_FLAG_0 = "0";
   public static final String TRUE = "TRUE";
   public static final String FALSE = "FALSE";
   public static final Boolean BOOL_TRUE = true;
   public static final Boolean BOOL_FALSE = false;
   public static final String SA_CODE = "sa";
   public static final String SYS_CODE = "sys";
   public static final String SYS_NAME = "系统";
   public static final String EMR_BASE64_FILE_NAME = "emrBase64";
   public static final String EMR_BASE64_FILE_NAME_LOG = "emrLogBase64";
   public static final String EMR_RULE_FILE_NAME = "emrRuleFile";
   public static final String ORDER_DESC = "desc";
   public static final String ORDER_ASC = "asc";
   public static final String RELATION_CODE = "0";
   public static final String RELATION_CODE_1 = "1";
   public static final String OPE_MAIN_CODE_1 = "1";
   public static final String OPE_MAIN_CODE_2 = "2";
   public static final String JZ_TYPE_MZ = "mz";
   public static final String JZ_TYPE_MZ_NEW = "mz_new";
   public static final String JZ_TYPE_ZY = "zy";
   public static final String CHECK_KEY = "check_key:";
   public static final Map KEY_PATIENTS_MAP = new HashMap();
   public static final String SUFFIX_DRL = ".drl";

   static {
      KEY_PATIENTS_MAP.put("operCount", "o.oper_flag");
      KEY_PATIENTS_MAP.put("bloodCount", "o.blood_trans");
      KEY_PATIENTS_MAP.put("dyCount", "o.dy_flag");
      KEY_PATIENTS_MAP.put("illCount", "o.ill_flag");
      KEY_PATIENTS_MAP.put("rescueCount", "o.rescue_flag");
      KEY_PATIENTS_MAP.put("dieCount", "o.die_flag");
      KEY_PATIENTS_MAP.put("consCount", "o.cons_flag");
      KEY_PATIENTS_MAP.put("crisisCount", "o.crisis_flag");
      KEY_PATIENTS_MAP.put("antiCount", "o.anti_flag");
   }

   public interface SYSTEM {
      String ROLE_NO_GLY = "00010033";
      Long ORG_ID = 100L;
      String DEPT_TYPE_HOSPITAL = "1";
      Long ORG_DEPT_ROOT_ID = 100L;
      Long ORG_DEPT_ROOT_PARENT_ID = 0L;
      Long ROLE_ID_DOCTOR = 100L;
      Long ROLE_ID_NURSE = 101L;
      String ROLE_TYPE_DOCTOR = "c";
      String ROLE_TYPE_NURSE = "d";
      String DEPT_TYPE_1 = "1";
      String DEPT_TYPE_2 = "2";
      String DEPT_TYPE_3 = "3";
      String DEPT_TYPE_4 = "4";
      String DEPT_TYPE_5 = "5";
      String DEPT_TYPE_6 = "6";
      String DEPT_TYPE_7 = "7";
      String DEPT_TYPE_8 = "8";
      String ALL_DEPT = "000000";
      String ALL_DEPT_NAME = "全部科室";
      Long ALL_DEPT_ID = 10000L;
   }

   public interface EMR_MODIFY_APPLY {
      String CON_STATE_0 = "0";
      String CON_STATE_1 = "1";
      String CON_STATE_2 = "2";
      String DEADLINE_UNIT_DAY = "D";
      String DEADLINE_UNIT_HOUR = "H";
      int DEADLINE_DEFAULT = 24;
      Long APP_REASON_1 = 1L;
      Long APP_REASON_2 = 2L;
      Long APP_REASON_3 = 3L;
      Long APP_REASON_9 = 9L;
   }

   public interface EMR_TYPE_CODE {
      String EMR_TYPE_CODE_15 = "15";
      String EMR_TYPE_CODE_5 = "5";
      String EMR_TYPE_CODE_17 = "17";
      Long AGI_RULE_ID_SQTL = 340975960835624987L;
      Long AGI_RULE_ID_SSZZTYS = 340975960835624988L;
   }

   public interface AGI_RULE_ID {
      Long AGI_RULE_ID_SQTL = 340975960835624987L;
      Long AGI_RULE_ID_SSZZTYS = 340975960835624988L;
   }

   public interface EMR {
      String MR_STATE_01 = "01";
      String MR_STATE_03 = "03";
      String MR_STATE_05 = "05";
      String MR_STATE_07 = "07";
      String MR_STATE_08 = "08";
      String EMR_SEC_LEVEL_1 = "1";
      String EMR_SEC_LEVEL_2 = "2";
      String EMR_SEC_LEVEL_3 = "3";
      String TYPE_CLASS_01 = "01";
      String MR_TYPE_NAME = "病程记录";
      String PHYSIGN_NAME_TIW = "tiw";
      String PHYSIGN_NAME_MAIB = "maib";
      String PHYSIGN_NAME_HUXI = "huxi";
      String PHYSIGN_NAME_XY1 = "xy1";
      String PHYSIGN_NAME_TIZHONG = "tizhong";
      Long PHYSIGN_TIWEN = 100251L;
      Long PHYSIGN_MAIBO = 100232L;
      Long PHYSIGN_HUXI = 100226L;
      Long PHYSIGN_SSY = 100240L;
      Long PHYSIGN_SZY = 100245L;
      Long PHYSIGN_TIZHONG = 100254L;
      String MR_TYPE_MAINFILE = "MAINFILE";
      int MERGE_FLAG_0 = 0;
      int MERGE_FLAG_1 = 1;
      int MERGE_FLAG_2 = 2;
      String DEITSTATE_1 = "1";
      String DEITSTATE_0 = "0";
      String EMR_CON_XMLSTR = "xmlStr";
      String EMR_CON_BASE64STR = "base64";
      String EMR_CON_TEXTSTR = "textStr";
      String EMR_CON_SUBFILETEXTSTR = "subFileTextStr";
      String EMR_CON_OLDTEXT = "oldText";
      String SIGN_NEXT_NODE_1 = "1";
      String SIGN_NEXT_NODE_2 = "2";
      String SIGN_NEXT_NODE_3 = "3";
      String SIGN_NEXT_NODE_NAME_1 = "已完成";
      String SIGN_NEXT_NODE_NAME_2 = "上级审签";
      String SIGN_NEXT_NODE_NAME_3 = "其他审签";
      String UP_DIR_PATH = "D:/FILE";
      String XML_SUFFIX = ".xml";
      String BASE64_SUFFIX = ".btn";
      String HOST_NAME = "192.168.123.33";
      Integer PORT = 21;
      String USER_NAME = "lenovo";
      String PASSWORD = "1988";
      String FILE_TYPE_EMR = "EMR";
      String FILE_TYPE_PDF = "PDF";
      String FILE_TYPE_HTML = "HTML";
      String INEDEX_DOCTOR_TYPE_RESDOC = "resDoc";
      String INEDEX_DOCTOR_TYPE_ATTDOC = "attDoc";
      String INEDEX_DOCTOR_TYPE_ARCDOC = "arcDoc";
      String INEDEX_DOCTOR_TYPE_OTHER = "other";
      Long ELEM_UP_TITLE = 1011564943314518016L;
      Long ELEM_UP_DOCTOR = 1011564663801905152L;
      String UP_EMR_TYPE_45 = "45";
      String UP_EMR_TYPE_51 = "51";
      String EMR_TYPE_12 = "12";
      String EMR_TYPE_9 = "9";
      String EMR_TYPE_NAME_9 = "危急值病程记录";
      String V_TEST_EXAM_RESULT = "v_test_exam_result";
      String V_BABY_INFO = "v_baby_info";
      String ELEM_MACRO_DEPT = "dept_name";
      String ELEM_MACRO_BED = "bed_no";
   }

   public interface SEALUP_RECORD_STATUS {
      Integer STATUS_0 = 0;
      Integer STATUS_1 = 1;
   }

   public interface BAS_EMR_ACCE_AUTHOR {
      Integer OBJECT_TYPE_1 = 1;
      Integer OBJECT_TYPE_2 = 2;
   }

   public interface MR_HP {
      String MR_TYPE_XY = "XY";
      String MR_TYPE_ZY = "ZY";
      Long REG_LEVEL_1 = 1L;
      Long REG_LEVEL_2 = 2L;
      Long REG_LEVEL_3 = 3L;
      String MR_STATE_01 = "01";
      String MR_STATE_02 = "02";
      String MR_STATE_03 = "03";
      String MR_STATE_04 = "04";
      String DIA_CLASS_01 = "01";
      String DIA_CLASS_02 = "02";
      String DIA_CLASS_12 = "12";
      String DIA_CLASS_11 = "11";
      String DIA_TYPE_01 = "01";
      String DIA_TYPE_02 = "02";
      String DIA_TYPE_03 = "03";
      String DIA_TYPE_04 = "04";
      String DIA_TYPE_05 = "05";
      Long DRUG_ALLE_FLAG_1 = 1L;
      Long DRUG_ALLE_FLAG_2 = 2L;
      String OPR_HEAL_CODE_0 = "0";
      String OPR_HEAL_CODE_1 = "1";
      String OPR_HEAL_CODE_2 = "2";
      String OPR_HEAL_CODE_3 = "3";
      String OPR_INCI_CODE_1 = "1";
      String OPR_INCI_CODE_2 = "2";
      String OPR_INCI_CODE_3 = "3";
      String OPR_INCI_CODE_4 = "4";
      String CHECK_TYPE_NO_0101 = "0101";
      String CHECK_TYPE_NO_0102 = "0102";
      String CHECK_TYPE_NO_0201 = "0201";
      String CHECK_TYPE_NO_0202 = "0202";
      String CHECK_TYPE_NO_0203 = "0203";
      String CHECK_TYPE_NO_0204 = "0204";
      String CHECK_TYPE_NO_0301 = "0301";
      String CHECK_TYPE_NO_0302 = "0302";
      String CHECK_TYPE_NO_0303 = "0303";
      String CHECK_TYPE_NO_0401 = "0401";
      String CHECK_TYPE_NO_0402 = "0402";
      String CHECK_TYPE_NO_0501 = "0501";
      String CHECK_TYPE_NO_0502 = "0502";
      String CHECK_TYPE_NO_0601 = "0601";
      String CHECK_TYPE_NAME_0101 = "无条件非空校验";
      String CHECK_TYPE_NAME_0102 = "有条件非空校验";
      String CHECK_TYPE_NAME_0201 = "字典";
      String CHECK_TYPE_NAME_0202 = "枚举";
      String CHECK_TYPE_NAME_0203 = "取值范围";
      String CHECK_TYPE_NAME_0204 = "表达式";
      String CHECK_TYPE_NAME_0301 = "条件校验";
      String CHECK_TYPE_NAME_0302 = "逻辑校验";
      String CHECK_TYPE_NAME_0303 = "费用校验";
      String CHECK_TYPE_NAME_0401 = "首页内容与诊断逻辑校验";
      String CHECK_TYPE_NAME_0402 = "诊断之间逻辑校验";
      String CHECK_TYPE_NAME_0501 = "首页内容与手术逻辑校验";
      String CHECK_TYPE_NAME_0502 = "手术之间逻辑校验";
      String CHECK_TYPE_NAME_0601 = "程序固化";
      String OPERATOR_LIST = "operatorList";
      String CODE_OPERATOR_LIST = "codeOperatorList";
      String OPP_CODE_OPERATOR_LIST = "oppCodeOperatorList";
      String OPERATOR = "operator";
      String CODE_OPERATOR = "codeOperator";
      String OPP_CODE_OPERATOR = "oppCodeOperator";
      String FIELD_TABLE = "fieldTable";
      String FIELD_COLUMN = "fieldColumn";
      String FIELD_DICT_TYPE = "fieldDictType";
      String FIEL_DDICT_DATA_PROP = "fieldDictDataProp";
      String CHECK_CLASS_NO_01 = "01";
      String CHECK_CLASS_NO_02 = "02";
      String CHECK_CLASS_NO_03 = "03";
      String CHECK_CLASS_NO_04 = "04";
      String CHECK_CLASS_NO_05 = "05";
      String CHECK_CLASS_NO_06 = "06";
      String CHECK_EXPRESSION_SHOW = "checkExpression";
      String CHECK_EXPRESSION_LIST = "checkExpressionVoList";
      String CHECK_EXPRESSION_CODE = "codeCheckExpression";
      String CHECK_EXPRESSION_OBJ = "objCheckExpression";
      String CHECK_EXPRESSION_OFFSET = "offsetCheckExpression";
      String FIRST_DIAG_FLAG_0 = "0";
      String FIRST_DIAG_FLAG_1 = "1";
      String DEATH_DIAG_FLAG_0 = "0";
      String DEATH_DIAG_FLAG_1 = "1";
      String OUTCOMECD_1 = "1";
      String OUTCOMECD_2 = "2";
      String OUTCOMECD_3 = "3";
      String OUTCOMECD_4 = "4";
      String OUTCOMECD_5 = "5";
      String FIELD_CD = "fieldCd";
      String FIELD_NAME = "fieldName";
      String FIELD_PROP = "fieldProp";
      String ERROR_COLUMN = "errorColumn";
      String ERROR_MSG = "errorMsg";
      String ERROR_COLUMN_ID = "errorColumnId";
      String FEE_TYPE_CODE = "feeTypeCode";
      String FEE_TYPE_NAME = "feeTypeName";
      String CHECK_EVENT_NO_1 = "1";
      String CHECK_EVENT_NO_2 = "2";
      String CHECK_EVENT_NO_3 = "3";
      String CHECK_EVENT_NO_4 = "4";
      String COLUMN_DATA_TYPE_NUMBER = "number";
      String COLUMN_DATA_TYPE_DATE = "date";
      String COLUMN_DATA_TYPE_VARCHAR = "varchar";
      String COLUMN_DATA_TYPE_CHAR = "char";
      String COLUMN_DATA_TYPE_VARCHAR2 = "varchar2";
      String COLUMN_DATA_TYPE_NVARCHAR = "nvarchar";
      String SPLIT_01 = "\\|";
      String SPLIT_02 = ",";
   }

   public interface ELEM {
      String CONT_TYPE_1 = "1";
      String CONT_TYPE_2 = "2";
      String CONT_TYPE_3 = "3";
      String CONT_TYPE_4 = "4";
      String CONT_TYPE_5 = "5";
      String CONT_TYPE_6 = "6";
      String CONT_TYPE_7 = "7";
      String CONT_TYPE_8 = "8";
      String CONT_TYPE_9 = "9";
      String CONT_TYPE_10 = "10";
      String CONT_TYPE_11 = "11";
      String CONT_TYPE_12 = "12";
      String CONT_TYPE_13 = "13";
      String CONT_TYPE_A1 = "A1";
      String CONT_TYPE_A2 = "A2";
      String CONT_TYPE_A3 = "A3";
      String CONT_TYPE_A4 = "A4";
      String CONT_TYPE_A5 = "A5";
      String CONT_TYPE_A6 = "A6";
      String CONT_TYPE_A7 = "A7";
      String CONT_TYPE_100 = "100";
      String CONT_TYPE_0 = "0";
      String CONT_TYPE_NAME_1 = "TextBox";
      String CONT_TYPE_NAME_2 = "NumberBox";
      String CONT_TYPE_NAME_3 = "ListBox";
      String CONT_TYPE_NAME_4 = "MultiListBox";
      String CONT_TYPE_NAME_5 = "Combox";
      String CONT_TYPE_NAME_6 = "MultiCombox";
      String CONT_TYPE_NAME_7 = "DateTimeBox";
      String CONT_TYPE_NAME_8 = "CheckBox";
      String CONT_TYPE_NAME_100 = "REGION";
      String CONT_TYPE_NAME_0 = "SECTION";
      String CONT_TYPE_NAME_NEWCTRL = "NEWCTRL";
      String TYPE_FLAG_1 = "1";
      String TYPE_FLAG_2 = "2";
      String TYPE_FLAG_3 = "3";
      String TYPE_FLAG_A = "A";
      String SOUR_TYPE_1 = "1";
      String SOUR_TYPE_2 = "2";
      String SOUR_TYPE_3 = "3";
      String ELEM_JSON_KEY_ELEMS = "elems";
      String ELEM_JSON_KEY_ELEM = "elem";
      String ELEM_JSON_KEY_ELEMATTRARR = "elemAttrArr";
      String ELEM_INHOSTIME = "inhosTime";
      String ELEM_OUTTIME = "outTime";
      String DROP_DOWN_DATA_SOURCE_1 = "1";
      String DROP_DOWN_DATA_SOURCE_2 = "2";
      String SIGN_HELP_TIP = "签名元素";
      String CONT_TYPE_NAME_11 = "医师签名";
      String CONT_TYPE_NAME_12 = "患者签名";
      Long PHYSIGN_TEMPERATURE = 100251L;
      Long PHYSIGN_PULSE = 100232L;
      Long PHYSIGN_RR = 100226L;
      Long PHYSIGN_NBPS = 100240L;
      Long PHYSIGN_NBPD = 100245L;
      Long PHYSIGN_WEIGHT = 100254L;
      Long MAIN_SUIT = 100205L;
      Long DIS_HIS = 100179L;
      Long PHYSIGN_ALL = 101012L;
      String ELEM_MACRO_TYPE_FALSE = "FALSE";
      String ELEM_MACRO_TYPE_TRUE = "TRUE";
      String ELEM_MACRO_TYPE_SOLAR = "SOLAR";
   }

   public interface QC {
      String QC_SECTION_01 = "01";
      String QC_SECTION_02 = "02";
      String QC_SECTION_03 = "03";
      String QC_SECTION_04 = "04";
      String QC_SECTION_05 = "05";
      String QC_SECTION_NAME_01 = "实时质控";
      String QC_SECTION_NAME_02 = "科室质控";
      String QC_SECTION_NAME_03 = "抽查质控";
      String QC_SECTION_NAME_04 = "离线质控";
      String QC_SECTION_NAME_05 = "终末质控";
      String REVOKE_TYPE_1 = "1";
      String REVOKE_TYPE_2 = "2";
      String QC_STATE_0 = "0";
      String QC_STATE_1 = "1";
      String QC_STATE_2 = "2";
      String QC_STATE_3 = "3";
      String QC_STATE_4 = "4";
      String QC_STATE_5 = "5";
      String QC_STATE_6 = "6";
      String QC_STATE_7 = "7";
      String QC_STATE_NAME_0 = "未修改";
      String QC_STATE_NAME_0_1 = "未处理";
      String QC_STATE_NAME_1 = "质控有误";
      String QC_STATE_NAME_2 = "已转人工";
      String QC_STATE_NAME_3 = "驳回";
      String QC_STATE_NAME_4 = "已修改";
      String QC_STATE_NAME_5 = "确认已修改";
      String QC_STATE_NAME_6 = "已确认有误";
      String ADD_METH_1 = "1";
      String ADD_METH_2 = "2";
      String RULE_TYPE_1 = "1";
      String RULE_TYPE_2 = "2";
      String CODE = "code";
      String NAME = "name";
      String RULE_AGE_CODE_1 = "1";
      String RULE_AGE_CODE_2 = "2";
      String RULE_AGE_CODE_3 = "3";
      String RULE_AGE_CODE_4 = "4";
      String AGE_GEN_DICT_TYPE_1 = "1";
      String AGE_GEN_DICT_TYPE_2 = "2";
      String AGE_GEN_DICT_TYPE_3 = "3";
      String AGE_GEN_DICT_TYPE_4 = "4";
      String AGE_GEN_DICT_TYPE_5 = "5";
      String DATA_RANGE_1 = "1";
      String DATA_RANGE_2 = "2";
      String DATA_RANGE_3 = "3";
      String DATA_RANGE_4 = "4";
      String DATA_RANGE_5 = "5";
      String DATA_RANGE_6 = "6";
      String DATA_RANGE_7 = "7";
      String QC_GRADE_CD_1 = "1";
      String QC_GRADE_CD_2 = "2";
      String QC_GRADE_CD_3 = "3";
      String OPER_FLAG = "operFlag";
      String CONS_FLAG = "consFlag";
      String DIE_FLAG = "dieFlag";
      String BLOOD_TRANS = "bloodTrans";
      String CHANGE_FLAG = "changeFlag";
      String INFECT_FLAG = "infectFlag";
      String RESCUE_FLAG = "rescueFlag";
      String DAYNUM_FLAG = "dayNumFlag";
      String COSTSUM_FLAG = "costSumFlag";
      String ALERT_FLAG = "alertFlag";
      String RUNTIME_QC_PLANTYPE = "4";
      String ELEM_CONTAINS_ONE = "containsOne";
      String ELEM_CONTAINS_ALL = "containsAll";
      String ELEM_EXPRESSION_SHOW = "elemExpression";
      String ELEM_EXPRESSION_LIST = "elemExpressionVoList";
      String ELEM_EXPRESSION_CODE = "codeElemExpression";
      String QC_DED_TYPE_ITEM_SCORE = "ITEM_SCORE";
      String QC_DED_TYPE_TIME_SCORE = "TIME_SCORE";
      String QC_DED_TYPE_C = "VETO_CGRADE";
      String QC_DED_TYPE_D = "VETO_BGRADE";
      String QC_DED_TYPE_ONE_TIME = "ONE_TIME";
      String QC_DED_TYPE_POINT_SCORE = "POINT_SCORE";
      String QC_DED_TYPE_ITEM_SCORE_NAME = "每项扣分";
      String QC_DED_TYPE_TIME_SCORE_NAME = "每次扣分";
      String QC_DED_TYPE_C_NAME = "单项否决(乙)";
      String QC_DED_TYPE_D_NAME = "单项否决(丙)";
      String QC_DED_TYPE_ONE_TIME_NAME = "单次扣分";
      String QC_DED_TYPE_POINT_SCORE_NAME = "每处扣分";
      String SCORE_GRADE_CODE_1 = "1";
      String SCORE_GRADE_NAME_1 = "甲级病历";
      String SCORE_GRADE_CODE_2 = "2";
      String SCORE_GRADE_NAME_2 = "乙级病历";
      String SCORE_GRADE_CODE_3 = "3";
      String SCORE_GRADE_NAME_3 = "丙级病历";
      String QC_RECORD_STATE_CODE_0 = "0";
      String QC_RECORD_STATE_NAME_0 = "未质控";
      String QC_RECORD_STATE_CODE_1 = "1";
      String QC_RECORD_STATE_NAME_1 = "质控中";
      String QC_RECORD_STATE_CODE_2 = "2";
      String QC_RECORD_STATE_NAME_2 = "质控完成";
      String QC_RECORD_STATE_CODE_3 = "3";
      String QC_RECORD_STATE_NAME_3 = "已退回";
      String QC_RECORD_STATE_CODE_31 = "31";
      String QC_RECORD_STATE_NAME_31 = "已修改";
      String INDEX_TYPE_INDEX = "index";
      String INDEX_TYPE_MRHP = "mrhp";
      String INDEX_TYPE_ORDERLIST = "orderList";
      Integer RULE_TYPE_CODE_1 = 1;
      Integer RULE_TYPE_CODE_2 = 2;
      Integer RULE_TYPE_CODE_3 = 3;
      Integer RULE_TYPE_CODE_4 = 4;
      Integer RULE_TYPE_CODE_5 = 5;
      Integer RULE_TYPE_CODE_6 = 6;
      Integer RULE_TYPE_CODE_7 = 7;
      Integer RULE_TYPE_CODE_8 = 8;
      String QC_BILL_PATIENTFLAG_0 = "0";
      String QC_BILL_PATIENTFLAG_1 = "1";
      String QC_BILL_PATIENTFLAG_2 = "2";
      String QC_BILL_CHECKWAY_1 = "1";
      String QC_BILL_CHECKWAY_2 = "2";
      String QC_BILL_CHECKWAY_NAME_1 = "指定数量";
      String QC_BILL_CHECKWAY_NAME_2 = "指定比例";
      String QC_COMM_RECORD_ROLECODE_1 = "1";
      String QC_COMM_RECORD_ROLECODE_2 = "2";
      String FLOW_MRSTATE_00 = "00";
      String FLOW_MRSTATE_10 = "10";
      String FLOW_MRSTATE_11 = "11";
      String FLOW_MRSTATE_12 = "12";
      String FLOW_MRSTATE_13 = "13";
      String FLOW_MRSTATE_14 = "14";
      String FLOW_MRSTATE_15 = "15";
      String FLOW_MRSTATE_16 = "16";
      String FLOW_MRSTATE_17 = "17";
      String FLOW_MRSTATE_NAME_00 = "未提交质控";
      String FLOW_MRSTATE_NAME_10 = "提交科室质控";
      String FLOW_MRSTATE_NAME_11 = "科室退回";
      String FLOW_MRSTATE_NAME_12 = "申请归档";
      String FLOW_MRSTATE_NAME_13 = "终末质控退回";
      String FLOW_MRSTATE_NAME_14 = "提交病案室";
      String FLOW_MRSTATE_NAME_15 = "归档退回";
      String FLOW_MRSTATE_NAME_16 = "病历归档";
      String FLOW_MRSTATE_NAME_17 = "取消归档";
      String MANU_RULE_TYPE_1 = "1";
      String MANU_RULE_TYPE_NAME_1 = "元素缺陷";
      String MANU_RULE_TYPE_2 = "2";
      String MANU_RULE_TYPE_NAME_2 = "病历类型缺陷";
      String MANU_RULE_TYPE_3 = "3";
      String MANU_RULE_TYPE_NAME_3 = "缺失病历缺陷";
      String ELEM_QC_RANGE_1 = "1";
      String ELEM_QC_RANGE_NAME_1 = "全部元素";
      String ELEM_QC_RANGE_2 = "2";
      String ELEM_QC_RANGE_NAME_2 = "指定元素";
      String ORDER_BILL_TYPE_01 = "01";
      String ORDER_BILL_TYPE_02 = "02";
      String ORDER_BILL_TYPE_03 = "03";
      String ORDER_BILL_TYPE_NAME_01 = "临时医嘱";
      String ORDER_BILL_TYPE_NAME_02 = "长期医嘱";
      String ORDER_BILL_TYPE_NAME_03 = "汤剂医嘱";
      String ORDER_MR_TYPE_CODE = "64";
      String ORDER_MR_TYPE_NAME = "医嘱本";
      String MR_INDEX_TYPE_CODE = "61";
      String MR_INDEX_TYPE_NAME = "病案首页";
      String QC_TYPE_1 = "1";
      String QC_TYPE_2 = "2";
   }

   public interface EMR_QC_PRES_VERI {
      Integer VERI_RESULT_0 = 0;
      Integer VERI_RESULT_1 = 1;
      Integer VERI_RESULT_2 = 2;
   }

   public interface EMR_QC_FEED {
      Long EMR_QC_FEED = 999999L;
      String EMR_QC_FEED_NAME = "人工反馈";
   }

   public interface EMR_QC_MESSAGE {
      Integer MSG_TYPE_1 = 1;
      Integer MSG_TYPE_2 = 2;
      Integer MSG_TYPE_3 = 3;
      Integer MSG_TYPE_4 = 4;
      Integer BUS_TYPE_1 = 1;
      Integer BUS_TYPE_2 = 2;
      Integer BUS_TYPE_3 = 3;
      Integer BUS_TYPE_4 = 4;
      Integer BUS_TYPE_5 = 5;
      Integer BUS_TYPE_6 = 6;
      String MSG_WARN_TYPE_1 = "1";
      String MSG_WARN_TYPE_2 = "2";
   }

   public interface HANDOVER {
      String STATE_0 = "0";
      String STATE_NAME_0 = "未交班";
      String STATE_1 = "1";
      String STATE_NAME_1 = "已交班";
      String STATE_2 = "2";
      String STATE_NAME_2 = "未创建";
      Integer SCHEME_0 = 0;
      String SCHEME_NAME_0 = "今日";
      Integer SCHEME_1 = 1;
      String SCHEME_NAME_1 = "昨日";
   }

   public interface AGI_EVEN {
      String EVEN_CODE_CONDITION = "01,18,21";
      String EVEN_CODE_01 = "01";
      String EVEN_CODE_18 = "18";
      String EVEN_CODE_21 = "21";
      String EVEN_CODE_03 = "03";
      String EVEN_CODE_02 = "02";
      String EVEN_CODE_04 = "04";
      String EVEN_CODE_05 = "05";
      String EVEN_CODE_06 = "06";
      String EVEN_CODE_07 = "07";
      String EVEN_CODE_08 = "08";
      String EVEN_CODE_09 = "09";
      String EVEN_CODE_10 = "10";
      String EVEN_CODE_11 = "11";
      String EVEN_CODE_12 = "12";
      String EVEN_CODE_13 = "13";
      String EVEN_CODE_14 = "14";
      String EVEN_CODE_15 = "15";
      String EVEN_CODE_16 = "16";
      String EVEN_CODE_17 = "17";
      String EVEN_CODE_19 = "19";
      String EVEN_CODE_20 = "20";
      String EVEN_CODE_22 = "22";
      String EVEN_BQ_NAME_01 = "一般病情";
      String EVEN_BQ_NAME_18 = "病危患者";
      String EVEN_BQ_NAME_21 = "病重患者";
      String EVEN_NAME_01 = "入院";
      String EVEN_NAME_02 = "出院";
      String EVEN_NAME_03 = "死亡";
      String EVEN_NAME_04 = "24小时出院";
      String EVEN_NAME_05 = "24小时死亡";
      String EVEN_NAME_06 = "手术";
      String EVEN_NAME_07 = "会诊";
      String EVEN_NAME_08 = "输血";
      String EVEN_NAME_09 = "感染";
      String EVEN_NAME_10 = "自费记账";
      String EVEN_NAME_11 = "特殊治疗";
      String EVEN_NAME_12 = "特殊检查";
      String EVEN_NAME_13 = "转科";
      String EVEN_NAME_14 = "危急值";
      String EVEN_NAME_15 = "更换住院医师";
      String EVEN_NAME_16 = "抢救";
      String EVEN_NAME_17 = "手术预约";
      String EVEN_NAME_19 = "入科";
      String EVEN_NAME_20 = "有创操作";
      String EVEN_NAME_22 = "自由流转";
   }

   public interface AGI_RULE {
      String CODE_R001 = "R001";
      String CODE_R002 = "R002";
      String CODE_R003 = "R003";
      String CODE_R004 = "R004";
      String CODE_R005 = "R005";
      String CODE_R006 = "R006";
      String CODE_R007 = "R007";
      String CODE_R008 = "R008";
      String CODE_R009 = "R009";
      String CODE_R010 = "R010";
      String CODE_R011 = "R011";
      String CODE_R012 = "R012";
      String CODE_R013 = "R013";
      String CODE_R014 = "R014";
      String CODE_R015 = "R015";
      String CODE_R016 = "R016";
      String CODE_R017 = "R017";
      String CODE_R018 = "R018";
      String CODE_R019 = "R019";
      String CODE_R020 = "R020";
      String CODE_R021 = "R021";
      String CODE_R022 = "R022";
      String CODE_R023 = "R023";
      String CODE_R024 = "R024";
      String CODE_R025 = "R025";
      String CODE_R026 = "R026";
      String CODE_R027 = "R027";
      String CODE_R028 = "R028";
      String CODE_R029 = "R029";
      String CODE_R030 = "R030";
      String CODE_R031 = "R031";
      String CODE_R032 = "R032";
      String CODE_R033 = "R033";
      String CODE_R034 = "R034";
      String CODE_R035 = "R035";
      String CODE_R036 = "R036";
      String CODE_R037 = "R037";
      String CODE_R038 = "R038";
      String CODE_R039 = "R039";
      String CODE_R040 = "R040";
      String TASK_FINISH_FLAG_1 = "1";
      String TASK_FINISH_FLAG_2 = "2";
      String TASK_FINISH_FLAG_3 = "3";
   }

   public interface APP_ITEM {
      String ITEM_STATE_9 = "9";
      String ITEM_STATE_9_NAME = "报告完成";
   }

   public interface BAS_DICT_MEDICINE {
      String LEVEL_1 = "1";
      String LEVEL_2 = "2";
      String COMMON_CODE = "1111";
      String COMMON_FIRST_CODE = "111111";
      String COMMON_SECOND_CODE = "1111";
   }

   public interface BORROW_STATUS {
      String BORROW_STATUS_0 = "0";
      String BORROW_STATUS_1 = "1";
      String BORROW_STATUS_2 = "2";
      String BORROW_STATUS_3 = "3";
      String BORROW_STATUS_4 = "4";
      String BORROW_STATUS_TYPE_1 = "1";
      String BORROW_STATUS_TYPE_0 = "0";
      String BORROW_STATUS_TYPE_2 = "2";
   }

   public interface BUS_INTEG_MENU {
      String MENU_CLASS_1 = "1";
      String MENU_CLASS_2 = "2";
      String MENU_CLASS_3 = "3";
      String MENU_CLASS_4 = "4";
      String MENU_CLASS_5 = "5";
      String MENU_CLASS_6 = "6";
      String MENU_CLASS_7 = "7";
      String MR_DOC_CODE = "mr_doc";
      String BABY_MR_DOC_CODE = "baby_mr_doc";
      String IN_PAPERS_MZNEW = "inPapersMzNew";
      String MR_DOC_ROUTE_PATH = "mouldNotes";
      String EM_HP_ROUTE_PATH = "em_hp";
      String TEMP_LIST_PATH = "temp_list";
      String TYPE_CODE_1 = "1";
      String TYPE_CODE_2 = "2";
      String TYPE_CODE_3 = "3";
      String TYPE_CODE_4 = "4";
   }

   public interface CHANGE_DEP_BED {
      String SERVICE_TYPE_CODE_01 = "01";
      String SERVICE_TYPE_CODE_02 = "02";
      String SERVICE_TYPE_CODE_03 = "03";
      String SERVICE_TYPE_CODE_04 = "04";
      String SERVICE_TYPE_CODE_05 = "05";
      String SERVICE_TYPE_NAME_01 = "转科业务";
      String SERVICE_TYPE_NAME_02 = "床位调整";
      String SERVICE_TYPE_NAME_03 = "医生调整";
      String SERVICE_TYPE_NAME_04 = "护士调整";
      String SERVICE_TYPE_NAME_05 = "护理级别调整";
      String FLAG_0 = "0";
      String FLAG_1 = "1";
      String FLAG_2 = "2";
      String FLAG_3 = "3";
   }

   public interface CONS_APPLY {
      String CONS_TYPE_1 = "1";
      String CONS_TYPE_2 = "2";
      String CONS_STATE_00 = "00";
      String CONS_STATE_01 = "01";
      String CONS_STATE_02 = "02";
      String CONS_STATE_03 = "03";
      String CONS_STATE_04 = "04";
      String CONS_STATE_05 = "05";
      String APPLY_SATI_1 = "1";
      String APPLY_SATI_2 = "2";
      String APPLY_SATI_3 = "3";
      String QUOTE_100585 = "100585";
      String QUOTE_100379 = "100379";
   }

   public interface DEPT_TYPE_CONTRAST {
      String DEPT_TYPE_01 = "01";
      String DEPT_TYPE_02 = "02";
      String DEPT_TYPE_03 = "03";
      String DEPT_TYPE_04 = "04";
      String DEPT_TYPE_05 = "05";
      String DEPT_TYPE_06 = "06";
      String DEPT_TYPE_07 = "07";
      String DEPT_TYPE_08 = "08";
      String DEPT_TYPE_09 = "09";
      String DEPT_TYPE_10 = "10";
      String DEPT_TYPE_11 = "11";
      String DEPT_TYPE_12 = "12";
      String DEPT_TYPE_13 = "13";
   }

   public interface DICT {
      String CODE_S001 = "s001";
      String CODE_S002 = "s002";
      String CODE_S003 = "s003";
      String CODE_S004 = "s004";
      String CODE_RC035 = "RC035";
      String CODE_S035 = "s035";
      String CODE_S052 = "s052";
      String CODE_S059 = "s059";
      String CODE_S063 = "s063";
      String CODE_S069 = "s069";
      String CODE_S068 = "s068";
      String CODE_S071 = "s071";
      String CODE_S070 = "s070";
      String CODE_S074 = "s074";
      String CODE_S028 = "s028";
      String CODE_S013 = "s013";
      String CODE_S102 = "s102";
      String CODE_S103 = "s103";
      String CODE_S107 = "s107";
      String CODE_S097 = "s097";
   }

   public interface DICT_DATA {
      String CODE_S004_DICT_VALUE_MRHP = "61";
      String CODE_S004_DICT_LABEL_MRHP = "病案首页";
      String CODE_S004_DICT_VALUE_CONS = "17";
      String CODE_S004_DICT_LABEL_CONS = "会诊记录";
      String DICT_CARD_TYPE_S022_1 = "s022_1";
      String DICT_LXR_RC033 = "RC033";
      String CODE_S004_DICT_VALUE_ORDERLIST = "64";
      String CODE_S004_DICT_LABEL_ORDERLIST = "医嘱本";
      String CODE_RC019_1 = "1";
      String CODE_RC019_2 = "2";
      String CODE_RC019_3 = "3";
      String CODE_RC019_4 = "4";
      String CODE_RC019_5 = "5";
      String CODE_RC019_6 = "6";
      String CODE_RC013 = "RC013";
   }

   public interface DIP {
      String QUTY_TYPE_CYZK = "cyzk";
      String QUTY_TYPE_BASZK = "baszk";
   }

   public interface DOC_TYPE {
      String DOC_TYPE_1 = "1";
      String DOC_TYPE_2 = "2";
   }

   public interface DRUG_AND_CLIN {
      String SETTING_CREATION_DATE = "index.creation_date";
   }

   public interface DRUG_APPLY {
      String STATE_0 = "0";
      String STATE_1 = "1";
      String STATE_2 = "2";
   }

   public interface EDITSTATE {
      String DEIT_STATE_0 = "0";
      String DEIT_STATE_1 = "1";
   }

   public interface EMPLOYEE {
      String EMPL_TYPE_CODE_1 = "1";
      String EMPL_TYPE_CODE_2 = "2";
      String EMPL_TYPE_NAME_1 = "医生";
      String EMPL_TYPE_NAME_2 = "护士";
   }

   public interface EMR_CASE {
      String DETAIL_CODE_1 = "1";
      String DETAIL_CODE_NAME_1 = "关键词";
      String DETAIL_CODE_2 = "2";
      String DETAIL_CODE_NAME_2 = "患者性别";
      String DETAIL_CODE_3 = "3";
      String DETAIL_CODE_NAME_3 = "科室";
      String DETAIL_CODE_4 = "4";
      String DETAIL_CODE_NAME_4 = "入院病情";
      String DETAIL_CODE_5 = "5";
      String DETAIL_CODE_NAME_5 = "入院方式";
      String DETAIL_CODE_6 = "6";
      String DETAIL_CODE_NAME_6 = "患者年龄";
      String DETAIL_CODE_7 = "7";
      String DETAIL_CODE_NAME_7 = "手术级别";
      String DETAIL_CODE_8 = "8";
      String DETAIL_CODE_NAME_8 = "患者病情";
      String DETAIL_CODE_9 = "9";
      String DETAIL_CODE_NAME_9 = "治疗类别";
      String DETAIL_CODE_10 = "10";
      String DETAIL_CODE_NAME_10 = "护理级别";
      String DETAIL_CODE_11 = "11";
      String DETAIL_CODE_NAME_11 = "出院时间";
      String DETAIL_CODE_12 = "12";
      String DETAIL_CODE_NAME_12 = "归档时间";
      String DETAIL_CODE_13 = "13";
      String DETAIL_CODE_NAME_13 = "入院时间";
      String KEY_WORD_CODE_0 = "0";
      String KEY_WORD_CODE_1 = "1";
      String KEY_WORD_CODE_NAME_1 = "患者基本信息";
      String KEY_WORD_CODE_2 = "2";
      String KEY_WORD_CODE_NAME_2 = "主诉";
      String KEY_WORD_CODE_3 = "3";
      String KEY_WORD_CODE_NAME_3 = "诊断";
      String KEY_WORD_CODE_4 = "4";
      String KEY_WORD_CODE_NAME_4 = "医嘱";
      String KEY_WORD_CODE_5 = "5";
      String KEY_WORD_CODE_NAME_5 = "指定病历";
      String KEY_WORD_CODE_6 = "6";
      String KEY_WORD_CODE_NAME_6 = "指定元素";
      String KEY_WORD_CODE_7 = "7";
      String KEY_WORD_CODE_NAME_7 = "主要诊断";
      String KEY_WORD_CONDITION_1 = "1";
      String KEY_WORD_CONDITION_2 = "2";
   }

   public interface EMR_SIGN_DATA {
      String TYPE_CD_1 = "1";
      String TYPE_CD_2 = "2";
      String TYPE_CD_3 = "3";
      String CERT_TYPE_1 = "1";
      String CERT_TYPE_2 = "2";
   }

   public interface EMR_SIGN_RECORD {
      String SIGN_CANC_FLAG_0 = "0";
      String SIGN_CANC_FLAG_1 = "1";
   }

   public interface EMR_TASK_INFO {
      String TASK_TYPE_1 = "1";
      String TASK_TYPE_2 = "2";
      String TASK_TYPE_3 = "3";
      String TASK_TYPE_4 = "4";
      String TASK_TYPE_5 = "5";
      String TASK_TYPE_6 = "6";
      String TASK_TYPE_NAME_1 = "病历书写";
      String TASK_TYPE_NAME_2 = "病历签名";
      String TASK_TYPE_NAME_3 = "上级审签";
      String TASK_TYPE_NAME_4 = "缺陷修改";
      String TASK_TYPE_NAME_5 = "病历归档";
      String TASK_TYPE_NAME_6 = "危急值处理";
      String TREAT_FLAG_0 = "0";
      String TREAT_FLAG_1 = "1";
      String LIMIT_TIME_UNIT_H = "H";
      String LIMIT_TIME_UNIT_D = "D";
   }

   public interface GRANT_OUT_DOCTOR {
      String new_user_password = "1234emr";
   }

   public interface GROUP_MEMBER {
      String DOC_LEVEL_0 = "0";
      String DOC_LEVEL_1 = "1";
      String DOC_LEVEL_2 = "2";
      String DOC_LEVEL_3 = "3";
   }

   public interface HOLIDAY_TYPE {
      String HOLIDAY_TYPE_1 = "1";
      String HOLIDAY_TYPE_2 = "2";
   }

   public interface HOME {
      String HOME_BACKLOG_TYPE_1 = "1";
      String HOME_BACKLOG_TYPE_2 = "2";
      String HOME_BACKLOG_TYPE_3 = "3";
      String HOME_BACKLOG_TYPE_4 = "4";
      String HOME_BACKLOG_TYPE_5 = "5";
      String HOME_BACKLOG_TYPE_6 = "6";
      String HOME_BACKLOG_TYPE_7 = "7";
   }

   public interface IDCARE_TYPE_BJ {
      String IDCARE_TYPE_BJ_0 = "0";
      String IDCARE_TYPE_BJ_1 = "1";
      String IDCARE_TYPE_BJ_2 = "2";
      String IDCARE_TYPE_BJ_3 = "3";
      String IDCARE_TYPE_BJ_4 = "4";
      String IDCARE_TYPE_BJ_5 = "5";
      String IDCARE_TYPE_BJ_6 = "6";
      String IDCARE_TYPE_BJ_7 = "7";
   }

   public interface IDCARE_TYPE_PERSON {
      String IDCARE_TYPE_PERSON_1 = "1";
      String IDCARE_TYPE_PERSON_2 = "2";
      String IDCARE_TYPE_PERSON_3 = "3";
      String IDCARE_TYPE_PERSON_4 = "4";
      String IDCARE_TYPE_PERSON_5 = "5";
      String IDCARE_TYPE_PERSON_6 = "6";
      String IDCARE_TYPE_PERSON_7 = "7";
      String IDCARE_TYPE_PERSON_8 = "8";
      String IDCARE_TYPE_PERSON_9 = "9";
   }

   public interface IMP {
      String IMP_TYPE_1 = "1";
      String IMP_TYPE_2 = "2";
      String IMP_TYPE_3 = "3";
   }

   public interface IMP_RANGE {
      String IMP_RANGE_0 = "0";
      String IMP_RANGE_1 = "1";
      String IMP_RANGE_2 = "2";
   }

   public interface IMP_TYPE {
      String IMP_TYPE_1 = "1";
      String IMP_TYPE_2 = "2";
      String IMP_TYPE_3 = "3";
   }

   public interface ITEM_DOC_QUERY {
      String ORDER_FLAG_1 = "1";
      String ORDER_FLAG_2 = "2";
      String ORDER_FLAG_8 = "8";
      String ORDER_FLAG_9 = "9";
      String ORDER_FLAG_12 = "12";
      String ORDER_FLAG_16 = "16";
      String ORDER_FLAG_18 = "18";
      String ORDER_FLAG_13 = "13";
      String ORDER_FLAG_10 = "10-";
      String ORDER_FLAG_SPLIT = "-";
      String ORDER_FLAG_19 = "19";
   }

   public interface JOB_RECORD {
      int STATUS_0 = 0;
      int STATUS_1 = 1;
      int STATUS_2 = 2;
      String JOB_TYPE_SYNC = "sync";
      String PERSONALINFO = "T_AR_BASEINFOMATION";
      String PERSONALINFO_DAY = "T_AR_BASEINFOMATION_DAY";
      String PERSONALINFO_H = "T_AR_BASEINFOMATION_H";
      String VISITINFO = "T_AR_MEDICALINFORMATION";
      String VISITINFO_DAY = "T_AR_MEDICALINFORMATION_DAY";
      String VISITINFO_H = "T_AR_MEDICALINFORMATION_H";
      String PERSONALINFO_ADD = "hisPersonalInfoAdd";
      String PERSONALINFO_DAY_ADD = "hisPersonalInfoDayAdd";
      String PERSONALINFO_H_ADD = "hisPersonalInfoHAdd";
      String VISITINFO_ADD = "hisVisitinfoAdd";
      String VISITINFO_DAY_ADD = "hisVisitinfoDayAdd";
      String VISITINFO_H_ADD = "hisVisitinfoHAdd";
      String TAG_STR_ADD = "syncDatasourceServiceImpl.syncAddDataBySqlVoAndCode";
      String HOSPITAL_MARK_0 = "0";
      String HOSPITAL_MARK_1 = "1";
      String HOSPITAL_MARK_3 = "3";
      String HOSPITAL_MARK_4 = "4";
   }

   public interface LCLJ_INFO {
      String IN_OUT_FLAG_1 = "1";
      String IN_OUT_FLAG_2 = "2";
      String MEDICALINFO_CP_FLAG_0 = "0";
      String MEDICALINFO_CP_FLAG_1 = "1";
   }

   public interface MK_JYLX {
      String JYLX_1 = "1";
      String JYLX_2 = "2";
      String JYLX_3 = "3";
      String JYLX_4 = "4";
      String JYLX_5 = "5";
      String JYLX_6 = "6";
      String JYLX_7 = "7";
   }

   public interface MK_PAY_CLASS {
      String PAY_CLASS_1 = "1";
      String PAY_CLASS_2 = "2";
      String PAY_CLASS_0 = "0";
   }

   public interface MODULE {
      String MODULE_CODE_0304 = "0304";
      String MODULE_CODE_0403 = "0403";
      String MODULE_CODE_0506 = "0506";
   }

   public interface OBJECTIVE {
      String OBJECTIVE_1 = "1";
      String OBJECTIVE_2 = "2";
      String OBJECTIVE_3 = "3";
   }

   public interface OPER {
      String OPER_STATUS_1 = "01";
      String OPER_STATUS_2 = "02";
      String OPER_STATUS_3 = "03";
      String OPER_STATUS_4 = "04";
      String OPER_STATUS_5 = "05";
      String OPER_STATUS_6 = "06";
      String OPER_STATUS_99 = "99";
      String OPER_LEVEL_CODE_1 = "1";
      String OPER_LEVEL_CODE_2 = "2";
      String OPER_LEVEL_CODE_3 = "3";
      String OPER_LEVEL_CODE_4 = "4";
   }

   public interface OPT_TYPE {
      String OPT_TYPE_1 = "1";
      String OPT_TYPE_2 = "2";
      String OPT_TYPE_3 = "3";
      String OPT_TYPE_4 = "4";
      String OPT_TYPE_5 = "5";
      String OPT_TYPE_6 = "6";
      String OPT_TYPE_7 = "7";
      String OPT_TYPE_8 = "8";
      String OPT_TYPE_NAME_1 = "异常解锁";
      String OPT_TYPE_NAME_2 = "病历恢复";
      String OPT_TYPE_NAME_3 = "病历删除";
      String OPT_TYPE_NAME_4 = "病历修改";
      String OPT_TYPE_NAME_5 = "病历封存";
      String OPT_TYPE_NAME_6 = "病历启封";
      String OPT_TYPE_NAME_7 = "病历浏览";
      String OPT_TYPE_NAME_8 = "新建病历";
   }

   public interface PATIENT_TROWS_TYPE {
      String PATIENT_TROWS_TYPE_1 = "1";
      String PATIENT_TROWS_TYPE_2 = "2";
      String PATIENT_TROWS_TYPE_3 = "3";
      String PATIENT_TROWS_TYPE_4 = "4";
      String PATIENT_TROWS_TYPE_5 = "5";
      String PATIENT_TROWS_TYPE_6 = "6";
   }

   public interface PAT_EVENT {
      String EVENT_SOURCE_HIS = "HIS";
   }

   public interface PERSONAL_INFO {
      String MODI_FLAG_0 = "0";
      String MODI_FLAG_1 = "1";
      String SEX_NAME_1 = "1";
      String SEX_NAME_2 = "2";
      String DOC_POWER_TYPE_1 = "1";
      String DOC_POWER_TYPE_2 = "2";
      String COLOR_TYPE_1 = "1";
      String COLOR_TYPE_2 = "2";
      String COLOR_TYPE_3 = "3";
      String DICT_NATION = "RC035";
      String DICT_OCCUPATION = "RC003";
      String DICT_MARRIAGE = "s035";
      String DICT_COND = "s016";
      String DICT_NATIONALITY = "RC038";
      String DICT_PAY_TYPE = "RC032";
      String DICT_CARD_TYPE = "s022";
      String QUERY_TYPE_1 = "1";
      String QUERY_TYPE_2 = "2";
      String QUERY_TYPE_3 = "3";
   }

   public interface PHYSIGN {
      String TYPE_OUT = "out";
      String TYPE_IN = "in";
      String TYPE_STOOL = "stool";
      String TYPE_URINATE = "urinate";
      String TYPE_BLOODPRESSURE1 = "bloodpressure1";
      String TYPE_BLOODPRESSURE2 = "bloodpressure2";
      String TYPE_BLOODPRESSURE3 = "bloodpressure3";
      String TYPE_BLOODPRESSURE4 = "bloodpressure4";
      String TYPE_BREATHING = "breathing";
      String TYPE_PULSE = "pulse";
      String TYPE_TEMPERATURE = "temperature";
      String TYPE_WEIGHT = "weight";
   }

   public interface PRINT_LOG {
      String PRINT_TYPE_1 = "1";
      String PRINT_TYPE_2 = "2";
      String PRINT_TYPE_3 = "3";
      String FILE_TYPE_1 = "1";
      String FILE_TYPE_2 = "2";
      String FILE_TYPE_3 = "3";
      String FILE_TYPE_9 = "9";
   }

   public interface PRINT_TMPL {
      String TYPE_CODE_100001001 = "100001001";
      String TYPE_CODE_100001002 = "100001002";
      String TYPE_CODE_100001003 = "100001003";
      String TYPE_CODE_100011001 = "100011001";
      String TYPE_CODE_100011002 = "100011002";
      String TYPE_CODE_100011003 = "100011003";
      String TYPE_CODE_100011004 = "100011004";
      String TYPE_CODE_100016001 = "100016001";
      String TYPE_CODE_100016002 = "100016002";
      String TYPE_CODE_100016003 = "100016003";
      String TYPE_CODE_10001901 = "10001901";
      String TYPE_CODE_100017 = "100017";
      String TYPE_CODE_100023001 = "100023001";
      String TYPE_CODE_100025001 = "100025001";
      String TYPE_CODE_100024001 = "100024001";
      String TYPE_CODE_100020001 = "100020001";
      String TYPE_CODE_100020002 = "100020002";
      String TYPE_CODE_100019002 = "100019002";
      String TYPE_CODE_100026001 = "100026001";
      String TYPE_CODE_100032001 = "100032001";
      String TYPE_CODE_100032002 = "100032002";
      String TYPE_CODE_100032003 = "100032003";
      String TYPE_CODE_100032004 = "100032004";
      String TYPE_CODE_100032005 = "100032005";
      String TYPE_CODE_100032006 = "100032006";
      String TYPE_CODE_100031001 = "100031001";
      String TYPE_CODE_100031002 = "100031002";
      String TYPE_CODE_100031003 = "100031003";
      String TYPE_CODE_100009002 = "100009002";
   }

   public interface QC_OPER_TYPE {
      String QC_OPER_TYPE_00 = "00";
      String QC_OPER_TYPE_10 = "10";
      String QC_OPER_TYPE_16 = "16";
   }

   public interface RECALL_STATUS {
      String RECALL_STATUS_0 = "0";
      String RECALL_STATUS_1 = "1";
      String RECALL_STATUS_2 = "2";
      String RECALL_STATUS_3 = "3";
   }

   public interface REP_COM {
      String DATA_SOURCE_1 = "1";
      String DATA_SOURCE_2 = "2";
      String DATA_SOURCE_3 = "3";
      String DATA_SOURCE_4 = "4";
      int RETURN_DATA_TYPE_1 = 1;
      int RETURN_DATA_TYPE_2 = 2;
      int RETURN_DATA_TYPE_3 = 3;
      int RETURN_DATA_TYPE_4 = 4;
   }

   public interface STAFF_TYPE {
      String STAFF_TYPE_BMY = "bmy";
   }

   public interface SYSTEM_ROLE {
      String ROLE_KEY_DOCTOR = "c";
      String ROLE_KEY_NURSE = "d";
      String ROLE_KEY_NURSEMANGER = "nurseManger";
      String ROLE_KEY_ADMIN = "admin";
      String ROLE_KEY_COMMON = "common";
      String ROLE_KEY_TECHNICIAN = "k";
   }

   public interface SYS_CUSTOM_SET {
      String TYPE_NAME_0 = "0";
      String TYPE_NAME_1 = "1";
   }

   public interface SYS_DEPT {
      String DEPT_TYPE_1 = "1";
      String DEPT_TYPE_2 = "2";
      String DEPT_FLAG_0 = "0";
      String DEPT_FLAG_1 = "1";
      String DEPT_FLAG_2 = "2";
      String DEPT_FLAG_3 = "3";
      String DEPT_FLAG_4 = "4";
      String DEPT_FLAG_5 = "5";
      String DEPT_FLAG_6 = "6";
      String DEPT_FLAG_7 = "7";
      String DEPT_FLAG_8 = "8";
      String DEPT_TYPE_01 = "01";
      String DEPT_TYPE_02 = "02";
      String DEPT_TYPE_03 = "03";
      String DEPT_TYPE_04 = "04";
      String DEPT_TYPE_05 = "05";
      String DEPT_TYPE_06 = "06";
      String DEPT_TYPE_07 = "07";
      String DEPT_TYPE_08 = "08";
      String DEPT_TYPE_09 = "09";
      String DEPT_TYPE_10 = "10";
      String DEPT_TYPE_11 = "11";
      String DEPT_TYPE_12 = "12";
      String DEPT_TYPE_13 = "13";
      String DEPT_TYPE_14 = "14";
      String ACCOUNTS_MODE_0 = "0";
      String ACCOUNTS_MODE_1 = "1";
      String ARREARS_FLAG_0 = "0";
      String ARREARS_FLAG_1 = "1";
   }

   public interface SYS_EMR_TYPE_CONFIG {
      String EDIT_MODE_2 = "2";
      String EDIT_MODE_7 = "7";
   }

   public interface TEST_RESULT_NORMAL_SIGN {
      String NORMAL_SIGN_L = "L";
      String NORMAL_SIGN_M = "M";
      String NORMAL_SIGN_H = "H";
   }

   public interface TIME_TYPE {
      String TIME_TYPE_1 = "1";
      String TIME_TYPE_2 = "2";
      String TIME_TYPE_3 = "3";
   }

   public interface TMPL {
      String TMPL_TYPE_HEADER = "200";
      String TMPL_TYPE_FOOTER = "300";
      String TMPL_TYPE_HEADER_NAME = "页眉";
      String TMPL_TYPE_FOOTER_NAME = "页脚";
      String TMPL_STATE_1 = "1";
      String TMPL_STATE_2 = "2";
      String TMPL_STATE_3 = "3";
      String TMPL_STATE_4 = "4";
      String TMPL_STATE_5 = "5";
      int TMPL_TREE_TYPE_1 = 1;
      int TMPL_TREE_TYPE_2 = 2;
      int TMPL_TREE_TYPE_3 = 3;
      int TMPL_TREE_TYPE_4 = 4;
      String USE_SEX_1 = "1";
      String USE_SEX_2 = "2";
      String USE_SEX_3 = "3";
      String USE_AGE_1 = "1";
      String USE_AGE_2 = "2";
      String USE_AGE_3 = "3";
      String EDIT_TYPE_1 = "1";
      String EDIT_TYPE_2 = "2";
      String EMR_TYPE_CODE_45 = "45";
      String EMR_TYPE_CODE_51 = "51";
   }

   public interface TMPL_ELEM_LINK {
      String LINK_TYPE_1 = "1";
      String LINK_TYPE_2 = "2";
      String LINK_TYPE_3 = "3";
   }

   public interface VTE_NAME {
      String VTE_NAME_NTKWH = "NTKWH";
   }

   public interface WORK_LOAD {
      String DICT_S150 = "s150";
      String DICT_S151 = "s151";
      String ITEM_01 = "XM00000001";
      String ITEM_02 = "XM00000002";
      String ITEM_03 = "XM00000003";
      String ITEM_04 = "XM00000004";
      String ITEM_05 = "XM00000005";
      String TYPE_CODE_100029001 = "100029001";
   }

   public interface YBSC_RULE_IDS {
      String RULE_ID_3101 = "3101";
   }

   public interface YBSC_TRIG_SCEN {
      String YBSC_TRIG_SCEN_5 = "5";
   }

   public interface YBSTANDARD_ZYLX {
      String ZYLX_21 = "21";
      String ZYLX_22 = "22";
      String ZYLX_23 = "23";
      String ZYLX_24 = "24";
      String ZYLX_26 = "26";
      String ZYLX_28 = "28";
      String ZYLX_52 = "52";
      String ZYLX_53 = "53";
      String ZYLX_92 = "92";
      String ZYLX_210101 = "210101";
      String ZYLX_990401 = "990401";
   }
}
