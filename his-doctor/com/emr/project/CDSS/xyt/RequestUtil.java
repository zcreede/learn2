package com.emr.project.CDSS.xyt;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class RequestUtil {
   public static String COMMON_PAGE_URL = "/api/Common/PageWithUrl";
   public static String PAGE_INDEX = "Index";
   public static String PAGE_ENTRYINDEX = "EntryIndex";
   public static String PAGE_REFERENCEINDEX = "ReferenceIndex";
   public static String PAGE_DRUGCLASS = "DrugClass";
   public static String PAGE_EXAMINEINDEX = "ExamineIndex";
   public static String EXAM_DATA_URL = "/api/DecisionSupport/AlarmContentList";
   public static String EXAM_PAGE_URL = "/api/DecisionSupport/DecisionSupportWithUrl";
   public static String TEST_DATA_URL = "/api/DecisionSupport/AlarmContentList";
   public static String TEST_PAGE_URL = "/api/DecisionSupport/DecisionSupportWithUrl";
   public static String DIP_QC_PAGE = "/api/qc/page";
   public static String DIP_CF_PAGE = "/api/cf/page";

   public static Map getHeaderMap(String bhtKey, String doctorId, String doctorName, String doctorDeptName) {
      Map<String, String> headers = new HashMap();
      headers.put("BHT_KEY", bhtKey);
      headers.put("DOCTOR_ID", Base64.getEncoder().encodeToString(doctorId.getBytes(StandardCharsets.UTF_8)));
      headers.put("DOCTOR_NAME", Base64.getEncoder().encodeToString(doctorName.getBytes(StandardCharsets.UTF_8)));
      headers.put("DOCTOR_DEPT", Base64.getEncoder().encodeToString(doctorDeptName.getBytes(StandardCharsets.UTF_8)));
      return headers;
   }
}
