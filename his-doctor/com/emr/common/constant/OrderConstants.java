package com.emr.common.constant;

public class OrderConstants {
   public interface CDSS {
      String DATA_ALARM_LEVEL_0 = "0";
      String DATA_ALARM_LEVEL_4 = "4";
      String DATA_ALARM_LEVEL_9 = "9";
   }

   public interface CHECK {
      String APPLYSTATUS_0 = "0";
      String APPLYSTATUS_1 = "1";
      String APPLYSTATUS_2 = "2";
      String APPLYSTATUS_30 = "30";
      String APPLYSTATUS_31 = "31";
      String APPLYSTATUS_4 = "4";
      String APPLYSTATUS_5 = "5";
      String APPLYSTATUS_6 = "6";
      String APPLYSTATUS_7 = "7";
      String APPLYSTATUS_8 = "8";
      String APPLYSTATUS_9 = "9";
      String APPLYSTATUS_99 = "99";
      String PRINT_FLAG_0 = "0";
      String PRINT_FLAG_1 = "1";
      String PRINT_FLAG_2 = "2";
   }

   public interface CHECK_NAME {
      String APPLYSTATUS_NAME_0 = "开立";
      String APPLYSTATUS_NAME_1 = "已提交";
      String APPLYSTATUS_NAME_2 = "已撤销";
      String APPLYSTATUS_NAME_30 = "未记账";
      String APPLYSTATUS_NAME_31 = "已记账";
      String APPLYSTATUS_NAME_4 = "已欠费";
      String APPLYSTATUS_NAME_5 = "已申请退费";
      String APPLYSTATUS_NAME_6 = "已退费";
      String APPLYSTATUS_NAME_7 = "医技科室确认";
      String APPLYSTATUS_NAME_8 = "上级确认";
      String APPLYSTATUS_NAME_9 = "报告完成";
      String APPLYSTATUS_NAME_99 = "已删除";
   }

   public interface CLIN_ITEM_USE_LOG {
      String ITEM_CLASS_CD_20 = "20";
      String ITEM_CLASS_CD_30 = "30";
      String ITEM_CLASS_CD_40 = "40";
      String ITEM_CLASS_CD_50 = "50";
      String ITEM_CLASS_CD_60 = "60";
      String ITEM_CLASS_NAME_20 = "协定处方";
      String ITEM_CLASS_NAME_30 = "频次";
      String ITEM_CLASS_NAME_40 = "用法";
      String ITEM_CLASS_NAME_50 = "诊断";
      String ITEM_CLASS_NAME_60 = "手术";
   }

   public interface DRUG {
      String DRUG_TYPE_0 = "0";
      String DRUG_TYPE_1 = "1";
      String DRUG_TYPE_2 = "2";
   }

   public interface DRUG_AND_CLIN {
      String ES_INDEX_NAME_PRE = "drugandclin_zy_";
      String ITEM_FLAG_CD_00 = "00";
      String ITEM_FLAG_CD_01 = "01";
      String NNNNNN = "NNNNNN";
      String PAGE_TYPE_1 = "1";
      String PAGE_TYPE_2 = "2";
      String PAGE_TYPE_3 = "3";
   }

   public interface DRUG_CHECK {
      String RES_CODE_0 = "0";
      String RES_CODE_1 = "1";
      String WINDOW_CODE_1 = "1";
      String WINDOW_CODE_2 = "2";
      String WINDOW_CODE_3 = "3";
      String WINDOW_CODE_4 = "4";
      String DRUG_CLASS_CODE_00 = "00";
      String DRUG_CLASS_CODE_01 = "01";
      String DRUG_CLASS_CODE_02 = "02";
      String DRUG_CLASS_CODE_03 = "03";
      String DRUG_CLASS_CODE_04 = "04";
      String DRUG_CLASS_CODE_05 = "05";
      String DRUG_CLASS_NAME_00 = "正常药品";
      String DRUG_CLASS_NAME_01 = "抗菌药物";
      String DRUG_CLASS_NAME_02 = "抗肿瘤药物";
      String DRUG_CLASS_NAME_03 = "毒麻药品";
      String psychotropicDrug = "精神药品";
      String psychotropicDrug2 = "精神药品2";
      String anaesthesiaDrug = "麻醉药品";
      String CIP_TYPE_4 = "4";
      String CIP_TYPE_5 = "5";
      String CIP_TYPE_6 = "6";
      String DET_TYPE_01 = "01";
      String DET_TYPE_02 = "02";
      String DET_TYPE_03 = "03";
   }

   public interface DRUG_STOCK {
      String FREEZE_SOURCE_CD_ORDER_ADD = "zyyzkl";
      String FREEZE_SOURCE_NAME_ORDER_ADD = "住院医嘱开立";
   }

   public interface OPE_APPLY {
      String OPE_STATUS_01 = "01";
      String OPE_STATUS_02 = "02";
      String OPE_STATUS_03 = "03";
      String OPE_STATUS_04 = "04";
      String OPE_STATUS_05 = "05";
      String OPE_STATUS_06 = "06";
      String OPE_STATUS_99 = "99";
      String OPE_TYPE_1 = "1";
      String OPE_TYPE_2 = "2";
   }

   public interface ORDER {
      String ORDER_DETAIL_GROUP_SORT_NUMBER_MAIN = "01";
      String ORDERSTATUS_F1 = "-1";
      String ORDERSTATUS_0 = "0";
      String ORDERSTATUS_1 = "1";
      String ORDERSTATUS_2 = "2";
      String ORDERSTATUS_8 = "8";
      String ORDERSTATUS_3 = "3";
      String ORDERSTATUS_4 = "4";
      String ORDERSTATUS_5 = "5";
      String ORDERSTATUS_6 = "6";
      String ORDERSTATUS_7 = "7";
      String ORDERSTATUS_10 = "10";
      String ORDERSTATUS_11 = "11";
      String ORDERSTATUS_12 = "12";
      String ORDER_CLASS_CODE_0 = "00";
      String ORDER_CLASS_CODE_1 = "01";
      String ORDER_CLASS_CODE_2 = "02";
      String ORDER_CLASS_CODE_3 = "03";
      String ORDER_CLASS_CODE_4 = "04";
      String ORDER_CLASS_CODE_5 = "05";
      String ORDER_CLASS_CODE_6 = "06";
      String ORDER_CLASS_CODE_7 = "07";
      String ORDER_CLASS_CODE_8 = "08";
      String ORDER_CLASS_CODE_9 = "09";
      String ORDER_CLASS_CODE_99 = "99";
      String ORDER_CLASS_CODE_30 = "30";
      String ORDER_CLASS_CODE_40 = "40";
      String ORDER_CLASS_CODE_50 = "50";
      String ORDER_CLASS_NAME_0 = "组套";
      String ORDER_CLASS_NAME_1 = "药疗";
      String ORDER_CLASS_NAME_2 = "检查";
      String ORDER_CLASS_NAME_3 = "检验";
      String ORDER_CLASS_NAME_4 = "手术";
      String ORDER_CLASS_NAME_5 = "处置";
      String ORDER_CLASS_NAME_6 = "材料";
      String ORDER_CLASS_NAME_7 = "嘱托";
      String ORDER_CLASS_NAME_8 = "输血";
      String ORDER_CLASS_NAME_9 = "协方";
      String ORDER_CLASS_NAME_99 = "其他";
      String ORDER_CLASS_NAME_30 = "频次";
      String ORDER_CLASS_NAME_40 = "用法";
      String ORDER_CLASS_NAME_50 = "诊断";
      String ORDER_CLASS_NAME_ALL = "所有项目";
      String ORDERTYPE_1 = "1";
      String ORDERTYPE_2 = "2";
      String OPERATORTYPE_1 = "1";
      String OPERATORTYPE_2 = "2";
      int OPERTYPE_F5 = -5;
      int OPERTYPE_F4 = -4;
      int OPERTYPE_F3 = -3;
      int OPERTYPE_F2 = -2;
      int OPERTYPE_F1 = -1;
      int OPERTYPE_0 = 0;
      int OPERTYPE_1 = 1;
      int OPERTYPE_2 = 2;
      int OPERTYPE_4 = 4;
      int OPERTYPE_5 = 5;
      int OPERTYPE_6 = 6;
      int OPERTYPE_7 = 7;
      int OPERTYPE_11 = 11;
      int OPERTYPE_12 = 12;
      int OPERTYPE_3 = 3;
      int OPERTYPE_8 = 8;
      int OPERTYPE_10 = 10;
      String OPERTYPE_NAME_F5 = "异常";
      String OPERTYPE_NAME_F4 = "回退";
      String OPERTYPE_NAME_F3 = "删除";
      String OPERTYPE_NAME_F2 = "撤销提交";
      String OPERTYPE_NAME_F1 = "保存";
      String OPERTYPE_NAME_0 = "提交";
      String OPERTYPE_NAME_1 = "审核";
      String OPERTYPE_NAME_2 = "处理";
      String OPERTYPE_NAME_4 = "停止";
      String OPERTYPE_NAME_5 = "停止确认";
      String OPERTYPE_NAME_6 = "取消";
      String OPERTYPE_NAME_7 = "取消确认";
      String OPERTYPE_NAME_11 = "作废";
      String OPERTYPE_NAME_12 = "作废确认";
      String OPERTYPE_NAME_3 = "在执行";
      String OPERTYPE_NAME_8 = "已执行";
      String OPERTYPE_NAME_10 = "挂起";
      String SET_SHARE_TYPE_1 = "1";
      String SET_SHARE_TYPE_2 = "2";
      String SET_SHARE_TYPE_3 = "3";
      String SET_SHARE_TYPE_NAME_1 = "全院共享";
      String SET_SHARE_TYPE_NAME_2 = "科室";
      String SET_SHARE_TYPE_NAME_3 = "个人";
      String HERBALFLAG_0 = "0";
      String HERBALFLAG_1 = "1";
      String ORDER_ITEM_FLAG_1 = "1";
      String ORDER_ITEM_FLAG_2 = "2";
      String ORDER_ITEM_FLAG_3 = "3";
      String ORDER_ITEM_FLAG_4 = "4";
      String ORDER_ITEM_FLAG_5 = "5";
      String ORDER_ITEM_FLAG_6 = "6";
      String ORDER_FLAG_CD_00 = "00";
      String ORDER_FLAG_CD_01 = "01";
      String ORDER_FLAG_CD_02 = "02";
      String ORDER_FLAG_CD_03 = "03";
      String ORDER_FLAG_CD_04 = "04";
      String ORDER_FLAG_CD_05 = "05";
      String ORDER_FLAG_CD_06 = "06";
      String ORDER_FLAG_CD_07 = "07";
      String ORDER_FLAG_CD_08 = "08";
      String ORDER_FLAG_CD_09 = "09";
      String ORDER_FLAG_NAME_00 = "正常医嘱";
      String ORDER_FLAG_NAME_04 = "重整医嘱";
      String ORDER_FLAG_NAME_05 = "自由录入医嘱";
      String ORDER_FLAG_NAME_07 = "中草药医嘱";
      String ORDER_FLAG_NAME_08 = "护理级别";
      String ORDER_FLAG_NAME_09 = "患者病情";
      String ORDER_PERFORM_FLAG_0 = "0";
      String ORDER_PERFORM_FLAG_1 = "1";
      String ORDER_PERFORM_FLAG_2 = "2";
      String ORDER_PERFORM_FLAG_3 = "3";
      String ORDER_NO_PRE = "";
      String APPLY_FORM_NO_PRE = "";
      String OPERATION_APPLY_NO_PRE = "";
      String ORDER_ITEM_DRUG_CD = "01";
      String ORDER_ITEM_DRUG_NAME = "药疗项目";
      String SHARE_OBJECT_1 = "000000";
      String PERFORM_STAFF_FLAG_0 = "0";
      String PERFORM_STAFF_FLAG_1 = "1";
      String ORDER_ITEM_TYPE_1 = "1";
      String ORDER_ITEM_TYPE_2 = "2";
      String ORDER_ITEM_TYPE_3 = "3";
      String MASTER_ORDER_1 = "1";
      String MASTER_ORDER_2 = "2";
      String SET_TYPE_1 = "1";
      String SET_TYPE_2 = "2";
      String TAKEDRUGMODE_1 = "1";
      String TAKEDRUGMODE_2 = "2";
      String SKIN_TEST_RESULTS_1 = "(-)";
      String SKIN_TEST_RESULTS_2 = "(+)";
      String SKIN_TEST_RESULTS_3 = "(++)";
      String SKIN_TEST_RESULTS_4 = "(+++)";
      String ORDERTYPE_NAME_1 = "长期";
      String ORDERTYPE_NAME_2 = "临时";
      String OPERATION_TYPE_1 = "1";
      String OPERATION_TYPE_2 = "2";
      String OPERATION_TYPE_3 = "3";
      String ORDER_PERFORM_PROCESS_1 = "开嘱";
      String ORDER_PERFORM_PROCESS_2 = "审核";
      String ORDER_PERFORM_PROCESS_3 = "首次执行";
      String ORDER_PERFORM_PROCESS_4 = "执行";
      String ORDER_PERFORM_PROCESS_5 = "停止";
      String ORDER_PERFORM_PROCESS_6 = "停止确认";
      String ORDER_PERFORM_PROCESS_7 = "取消";
      String ORDER_PERFORM_PROCESS_8 = "取消确认";
      String ORDER_PERFORM_PROCESS_9 = "转抄";
      String ORDER_PERFORM_PROCESS_10 = "医技登记";
      String ORDER_PERFORM_PROCESS_11 = "报告已出";
      String ORDER_PERFORM_PROCESS_12 = "报告发布";
      String ORDER_PERFORM_PROCESS_13 = "条码打印";
      String ORDER_PERFORM_PROCESS_14 = "标本采集";
      String ORDER_PERFORM_PROCESS_15 = "标本送出";
      String ORDER_PERFORM_PROCESS_16 = "标本上机";
      String ORDER_PERFORM_PROCESS_17 = "报告发布";
      String DEFAULT_UNIT = "次";
      String DEFAULT_ACTUAL_USAGE = "1";
   }

   public interface ORDER_FREQ {
      String FREQ_TYPE_0 = "0";
      String FREQ_TYPE_1 = "1";
      String FREQ_TYPE_3 = "3";
   }

   public interface ORDER_LIST {
      String PATIENT_SELF_DRUG_STR = "(自)";
      String ORDER_CANCEL_TIME = "{time}";
      String ORDER_CANCEL_STR = "(取消 {time})";
      String SKIN_TEST_RESULT_POSITIVE = "(+)";
      String SKIN_TEST_RESULT_NEGATIVE = "(-)";
      String DOCTOR_INSTRUCTIONS_STR = "({})";
      String DOCTOR_INSTRUCTIONS = "{}";
      String OUT_HAVA_DRUG_FLAG_PRE = "(带)";
      String OUT_HAVA_DRUG_FLAG_SUFFIX = "({standard})";
      String OUT_HAVA_DRUG_FLAG_STANDARD = "{standard}";
      String ADDITIONAL_FLAG_1 = "(补)";
      int PRINT_TYPE_1 = 1;
      int PRINT_TYPE_2 = 2;
      int PRINT_TYPE_3 = 3;
      int PRINT_TYPE_4 = 4;
      int PRINT_TYPE_5 = 5;
      int PRINT_TYPE_6 = 6;
      String ORDERLISTTYPE_1 = "1";
      String ORDERLISTTYPE_2 = "2";
      String ORDERLISTTYPE_3 = "3";
   }

   public interface ORDER_MSG {
      String MSG_STATUS_0 = "0";
      String MSG_STATUS_1 = "1";
      String MSG_STATUS_F1 = "-1";
      String MSG_TYPE_1 = "1";
      String ORDER_STATUS_F4 = "-4";
   }

   public interface ORDER_SIG {
      String STANDARD_CD_1 = "1";
      String STANDARD_CD_2 = "2";
      String STANDARD_CD_3 = "3";
      String STANDARD_CD_4 = "4";
      String STANDARD_CD_5 = "5";
   }

   public interface ORDER_SIGN {
      String SIGN_TYPE_0 = "0";
      String SIGN_TYPE_1 = "1";
      String SIGN_TYPE_2 = "2";
      String SIGN_TYPE_3 = "3";
      String SIGN_TYPE_4 = "4";
      String SIGN_DESC_1 = "单条签名";
      String SIGN_DESC_2 = "打包签名";
      String SIGN_MODE_SUBMIT = "住院号,医嘱编码,医嘱序号,序号,医嘱类型,医嘱类别,开嘱科室,开嘱医师,医嘱开始时间,医嘱项目编码,医嘱项目名称,规格,每次用量,用量单位,频率,用法,数量,数量单位,首日次数,特殊标志,草药剂数,医嘱说明,医师说明,部位,标本,医嘱提交医师,提交时间;";
      String SIGN_MODE_STOP = "住院号,医嘱编码,医嘱序号,序号,医嘱类型,医嘱类别,开嘱科室,开嘱医师,医嘱开始时间,医嘱项目编码,医嘱项目名称,规格,每次用量,用量单位,频率,用法,数量,数量单位,首日次数,特殊标志,草药剂数,医嘱说明,医师说明,部位,标本,医嘱提交医师,提交时间,停止医师,停止时间;";
      String SIGN_MODE_CANCEL = "住院号,医嘱编码,医嘱序号,序号,医嘱类型,医嘱类别,开嘱科室,开嘱医师,医嘱开始时间,医嘱项目编码,医嘱项目名称,规格,每次用量,用量单位,频率,用法,数量,数量单位,首日次数,特殊标志,草药剂数,医嘱说明,医师说明,部位,标本,医嘱提交医师,提交时间,停止医师,停止时间,取消医师,取消时间;";
   }

   public interface PERFORM_STATUS {
      int STATUS_0 = 0;
      int STATUS_1 = 1;
      int STATUS_2 = 2;
      int STATUS_3 = 3;
      int STATUS_4 = 4;
   }
}
