package com.emr.common.constant;

public class Constants {
   public static final String UTF8 = "UTF-8";
   public static final String GBK = "GBK";
   public static final String HTTP = "http://";
   public static final String HTTPS = "https://";
   public static final String SUCCESS = "0";
   public static final String FAIL = "1";
   public static final String LOGIN_SUCCESS = "Success";
   public static final String LOGOUT = "Logout";
   public static final String LOGIN_FAIL = "Error";
   public static final String CAPTCHA_CODE_KEY = "captcha_codes:";
   public static final String LOGIN_TOKEN_KEY = "login_tokens:";
   public static final String REPEAT_SUBMIT_KEY = "repeat_submit:";
   public static final String LOGIN_ERROR_KEY = "login_error:";
   public static final Integer LOGIN_ERROR_EXPIRATION = 5;
   public static final Integer LOGIN_ERROR_CAPTCHA_NUM = 3;
   public static final Integer CAPTCHA_EXPIRATION = 2;
   public static final String TOKEN = "token";
   public static final String TOKEN_PREFIX = "Bearer ";
   public static final String LOGIN_USER_KEY = "login_user_key";
   public static final String JWT_USERID = "userid";
   public static final String JWT_USERNAME = "sub";
   public static final String JWT_AVATAR = "avatar";
   public static final String JWT_CREATED = "created";
   public static final String JWT_AUTHORITIES = "authorities";
   public static final String SYS_CONFIG_KEY = "sys_config:";
   public static final String SYS_DICT_KEY = "sys_dict:";
   public static final String SYS_EMR_CONFIG_KEY = "sys_emr_config:";
   public static final String SYS_EMR_CONFIG_KEY_BAK = "sys_emr_config_bak:%s:%s";
   public static final String PUBLIC_ORG_CODE = "0000";
   public static final String SYS_EMR_CONFIG_CHILDREN_KEY = "sys_emr_config_children:";
   public static final String RANDOM_CHECKING = "random_checking:";
   public static final String RANDOM_CHECK = "random_check:";
   public static final String RANDOM_CHECK_PAGE = "random_check_page:";
   public static final String RANDOM_VERIFYING = "random_verifyint:";
   public static final String RANDOM_VERIFY = "random_verify:";
   public static final String RANDOM_VERIFY_PAGE = "random_verify_page:";
   public static final String ES_MR_FILE_ID_LIST = "es_mr_file_id_list:";
   public static final String ES_MR_FILE_ID_LIST2 = "es_mr_file_id_list2:";
   public static final String ES_SEARCH_TYPE_1 = "es_search_type_1:";
   public static final Integer ES_EXPIRATION = 50;
   public static final String PATIENT_SAVE_ORDER = "patient_save_order:";
   public static final String RESOURCE_PREFIX = "/profile";
   public static final String LOOKUP_RMI = "rmi://";
   public static final String DB2 = "DB2";
   public static final String SQLServer = "SQLServer";
   public static final String MySQL = "MySQL";
   public static final String Ingres = "Ingres";
   public static final String H2 = "H2";
   public static final String SQLite = "SQLite";
   public static final String HSqlDB = "HSqlDB";
   public static final String PostgreSQL = "PostgreSQL";
   public static final String Informix = "Informix";
   public static final String DEL_FLAG_0 = "0";
   public static final String DEL_FLAG_2 = "2";
   public static final String MESSAGE_KEY = "message_key:";
   public static final String HIS_PAT_ADD_DATA = "his_pat_add_data:";
   public static final String LIS_PACS_ALERT_KEY = "lis_pacs_alert_key:";
   public static final String INPATIENT_ORDER_HANDLING_KEY = "inpatientOrderHandling:";
   public static final String INPATIENT_ORDER_NO_HANDLING_KEY = "inpatientOrderNoHandling:";
   public static final String INPATIENT_ORDER_PERFORMING_KEY = "inpatientOrderPerforming:";
   public static final String INPATIENT_ORDER_PERFORM_KEY = "inpatientOrderPerform:";
   public static final String INPATIENT_ORDER_PERFORM_CANCEL_KEY = "inpatientOrderPerformCancel:";
   public static final String OPE_DEPART_ACCOUNT_SUBMIT_KEY = "opeDepartAccountSubmit:";
   public static final String OPE_DEPART_ACCOUNT_DEL_KEY = "opeDepartAccountDel:";
   public static final String DIAG_INFO_KEY = "diag_info_key:";
   public static final String INDEX_BASE64_KEY = "index_base64_key:";
   public static final String EMR_FILE_SYNES_TIME = "emr_file_synEs_time";
   public static final String PAT_EVENT_TIME = "pat_event_time";
   public static final String GENERATE_PDF_ORDER_TIME = "generate_pdf_order_time";
   public static final String GENERATE_PDF_FEE_TIME = "generate_pdf_fee_time";
   public static final String DIAG_CLASS_ZY = "ZY";
   public static final String DIAG_CLASS_XY = "XY";
   public static final String DIAG_TYPE_CLASS_02 = "02";
   public static final String OPE_ADD_DRUG = "opeAddDrug";
   public static final String INDEX_FILE_READ_WRITE = "index_file_read_write:";
   public static final String PAT_NEW_INDEX_NAME = "pat_new_index_name:";
   public static final String INDEX_FILE_AUTH = "index_file_auth:";
   public static final String EMR_QC_PAT_SECTION = "emr_qc_pat_section:";

   public interface EXAM_SEX_TYPE {
      String EXAM_SEX_TYPE_1 = "1";
      String EXAM_SEX_TYPE_1_NAME = "男";
      String EXAM_SEX_TYPE_2 = "2";
      String EXAM_SEX_TYPE_2_NAME = "女";
      String EXAM_SEX_TYPE_3 = "3";
      String EXAM_SEX_TYPE_3_NAME = "通用";
      String EXAM_SEX_TYPE_OTHER_NAME = "未知";
   }

   public interface REVIEWED_LEVEL_TYPE {
      String LEVEL_TYPE_1 = "1";
      String LEVEL_TYPE_2 = "2";
      String LEVEL_TYPE_3 = "3";
      String DICT_TYPE_CODE = "s092";
   }
}
