package com.emr.common.utils;

import com.alibaba.druid.support.json.JSONUtils;
import com.emr.project.emr.domain.vo.RuleVo;
import com.emr.project.mrhp.domain.MrHpDia;
import com.emr.project.mrhp.domain.MrHpFee;
import com.emr.project.mrhp.domain.MrHpOpe;
import com.emr.project.mrhp.domain.mris.TdCmDiagSave;
import com.emr.project.mrhp.domain.mris.TdCmOperSave;
import com.emr.project.tmpl.domain.vo.ElemAttriVo;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;

public class DroolsUtil {
   private static final int CHINA_ID_MIN_LENGTH = 15;
   private static final int CHINA_ID_MAX_LENGTH = 18;
   private static final String[] cityCode = new String[]{"11", "12", "13", "14", "15", "21", "22", "23", "31", "32", "33", "34", "35", "36", "37", "41", "42", "43", "44", "45", "46", "50", "51", "52", "53", "54", "61", "62", "63", "64", "65", "71", "81", "82", "91"};
   private static final int[] power = new int[]{7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
   private static final String[] verifyCode = new String[]{"1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"};
   private static final int MIN = 1930;
   private static Map cityCodes = new HashMap();
   private static Map twFirstCode = new HashMap();
   private static Map hkFirstCode = new HashMap();

   public static boolean validateCard(String idCard) {
      String card = idCard.trim();
      if (validateIdCard18(card)) {
         return true;
      } else if (validateIdCard15(card)) {
         return true;
      } else {
         String[] cardval = validateIdCard10(card);
         return cardval != null && cardval[2].equals("true");
      }
   }

   public static int getAgeByIdCard(String idCard) {
      int iAge = 0;
      if (idCard.length() == 15) {
         idCard = conver15CardTo18(idCard);
      }

      String year = idCard.substring(6, 10);
      Calendar cal = Calendar.getInstance();
      int iCurrYear = cal.get(1);
      iAge = iCurrYear - Integer.valueOf(year);
      return iAge;
   }

   public static String getBirthByIdCard(String idCard) {
      Integer len = idCard.length();
      if (len < 15) {
         return null;
      } else {
         if (len == 15) {
            idCard = conver15CardTo18(idCard);
         }

         return idCard.substring(6, 14);
      }
   }

   public static Short getYearByIdCard(String idCard) {
      Integer len = idCard.length();
      if (len < 15) {
         return null;
      } else {
         if (len == 15) {
            idCard = conver15CardTo18(idCard);
         }

         return Short.valueOf(idCard.substring(6, 10));
      }
   }

   public static Short getMonthByIdCard(String idCard) {
      Integer len = idCard.length();
      if (len < 15) {
         return null;
      } else {
         if (len == 15) {
            idCard = conver15CardTo18(idCard);
         }

         return Short.valueOf(idCard.substring(10, 12));
      }
   }

   public static Short getDateByIdCard(String idCard) {
      Integer len = idCard.length();
      if (len < 15) {
         return null;
      } else {
         if (len == 15) {
            idCard = conver15CardTo18(idCard);
         }

         return Short.valueOf(idCard.substring(12, 14));
      }
   }

   public static String getGenderByIdCard(String idCard) {
      String sGender = "N";
      if (idCard.length() == 15) {
         idCard = conver15CardTo18(idCard);
      }

      String sCardNum = idCard.substring(16, 17);
      if (Integer.parseInt(sCardNum) % 2 != 0) {
         sGender = "M";
      } else {
         sGender = "F";
      }

      return sGender;
   }

   public static String getProvinceByIdCard(String idCard) {
      int len = idCard.length();
      String sProvince = null;
      String sProvinNum = "";
      if (len == 15 || len == 18) {
         sProvinNum = idCard.substring(0, 2);
      }

      sProvince = (String)cityCodes.get(sProvinNum);
      return sProvince;
   }

   private static String conver15CardTo18(String idCard) {
      String idCard18 = "";
      if (idCard.length() != 15) {
         return null;
      } else if (isNum(idCard)) {
         String birthday = idCard.substring(6, 12);
         Date birthDate = null;

         try {
            birthDate = (new SimpleDateFormat("yyMMdd")).parse(birthday);
         } catch (ParseException e) {
            e.printStackTrace();
         }

         Calendar cal = Calendar.getInstance();
         if (birthDate != null) {
            cal.setTime(birthDate);
         }

         String sYear = String.valueOf(cal.get(1));
         idCard18 = idCard.substring(0, 6) + sYear + idCard.substring(8);
         char[] cArr = idCard18.toCharArray();
         if (cArr != null) {
            int[] iCard = converCharToInt(cArr);
            int iSum17 = getPowerSum(iCard);
            String sVal = getCheckCode18(iSum17);
            if (sVal.length() <= 0) {
               return null;
            }

            idCard18 = idCard18 + sVal;
         }

         return idCard18;
      } else {
         return null;
      }
   }

   private static boolean validateIdCard18(String idCard) {
      boolean bTrue = false;
      if (idCard.length() == 18) {
         String code17 = idCard.substring(0, 17);
         String code18 = idCard.substring(17, 18);
         if (isNum(code17)) {
            char[] cArr = code17.toCharArray();
            if (cArr != null) {
               int[] iCard = converCharToInt(cArr);
               int iSum17 = getPowerSum(iCard);
               String val = getCheckCode18(iSum17);
               if (val.length() > 0 && val.equalsIgnoreCase(code18)) {
                  bTrue = true;
               }
            }
         }
      }

      return bTrue;
   }

   private static boolean validateIdCard15(String idCard) {
      if (idCard.length() != 15) {
         return false;
      } else if (isNum(idCard)) {
         String proCode = idCard.substring(0, 2);
         if (cityCodes.get(proCode) == null) {
            return false;
         } else {
            String birthCode = idCard.substring(6, 12);
            Date birthDate = null;

            try {
               birthDate = (new SimpleDateFormat("yy")).parse(birthCode.substring(0, 2));
            } catch (ParseException e) {
               e.printStackTrace();
            }

            Calendar cal = Calendar.getInstance();
            if (birthDate != null) {
               cal.setTime(birthDate);
            }

            return valiDate(cal.get(1), Integer.valueOf(birthCode.substring(2, 4)), Integer.valueOf(birthCode.substring(4, 6)));
         }
      } else {
         return false;
      }
   }

   private static String[] validateIdCard10(String idCard) {
      String[] info = new String[3];
      String card = idCard.replaceAll("[\\(|\\)]", "");
      if (card.length() != 8 && card.length() != 9 && idCard.length() != 10) {
         return null;
      } else {
         if (idCard.matches("^[a-zA-Z][0-9]{9}$")) {
            info[0] = "台湾";
            String char2 = idCard.substring(1, 2);
            if (char2.equals("1")) {
               info[1] = "M";
            } else {
               if (!char2.equals("2")) {
                  info[1] = "N";
                  info[2] = "false";
                  return info;
               }

               info[1] = "F";
            }

            info[2] = validateTWCard(idCard) ? "true" : "false";
         } else if (idCard.matches("^[1|5|7][0-9]{6}\\(?[0-9A-Z]\\)?$")) {
            info[0] = "澳门";
            info[1] = "N";
         } else {
            if (!idCard.matches("^[A-Z]{1,2}[0-9]{6}\\(?[0-9A]\\)?$")) {
               return null;
            }

            info[0] = "香港";
            info[1] = "N";
            info[2] = validateHKCard(idCard) ? "true" : "false";
         }

         return info;
      }
   }

   private static boolean validateTWCard(String idCard) {
      String start = idCard.substring(0, 1);
      String mid = idCard.substring(1, 9);
      String end = idCard.substring(9, 10);
      Integer iStart = (Integer)twFirstCode.get(start);
      Integer sum = iStart / 10 + iStart % 10 * 9;
      char[] chars = mid.toCharArray();
      Integer iflag = 8;

      for(char c : chars) {
         sum = sum + Integer.valueOf(c + "") * iflag;
         iflag = iflag - 1;
      }

      return (sum % 10 == 0 ? 0 : 10 - sum % 10) == Integer.valueOf(end);
   }

   private static boolean validateHKCard(String idCard) {
      String card = idCard.replaceAll("[\\(|\\)]", "");
      Integer sum = 0;
      if (card.length() == 9) {
         sum = (Integer.valueOf(card.substring(0, 1).toUpperCase().toCharArray()[0]) - 55) * 9 + (Integer.valueOf(card.substring(1, 2).toUpperCase().toCharArray()[0]) - 55) * 8;
         card = card.substring(1, 9);
      } else {
         sum = 522 + (Integer.valueOf(card.substring(0, 1).toUpperCase().toCharArray()[0]) - 55) * 8;
      }

      String mid = card.substring(1, 7);
      String end = card.substring(7, 8);
      char[] chars = mid.toCharArray();
      Integer iflag = 7;

      for(char c : chars) {
         sum = sum + Integer.valueOf(c + "") * iflag;
         iflag = iflag - 1;
      }

      if (end.toUpperCase().equals("A")) {
         sum = sum + 10;
      } else {
         sum = sum + Integer.valueOf(end);
      }

      return sum % 11 == 0;
   }

   private static int[] converCharToInt(char[] ca) {
      int len = ca.length;
      int[] iArr = new int[len];

      try {
         for(int i = 0; i < len; ++i) {
            iArr[i] = Integer.parseInt(String.valueOf(ca[i]));
         }
      } catch (NumberFormatException e) {
         e.printStackTrace();
      }

      return iArr;
   }

   private static int getPowerSum(int[] iArr) {
      int iSum = 0;
      if (power.length == iArr.length) {
         for(int i = 0; i < iArr.length; ++i) {
            for(int j = 0; j < power.length; ++j) {
               if (i == j) {
                  iSum += iArr[i] * power[j];
               }
            }
         }
      }

      return iSum;
   }

   private static String getCheckCode18(int iSum) {
      String sCode = "";
      switch (iSum % 11) {
         case 0:
            sCode = "1";
            break;
         case 1:
            sCode = "0";
            break;
         case 2:
            sCode = "x";
            break;
         case 3:
            sCode = "9";
            break;
         case 4:
            sCode = "8";
            break;
         case 5:
            sCode = "7";
            break;
         case 6:
            sCode = "6";
            break;
         case 7:
            sCode = "5";
            break;
         case 8:
            sCode = "4";
            break;
         case 9:
            sCode = "3";
            break;
         case 10:
            sCode = "2";
      }

      return sCode;
   }

   private static boolean isNum(String val) {
      return val != null && !"".equals(val) ? val.matches("^[0-9]*$") : false;
   }

   private static boolean valiDate(int iYear, int iMonth, int iDate) {
      Calendar cal = Calendar.getInstance();
      int year = cal.get(1);
      if (iYear >= 1930 && iYear < year) {
         if (iMonth >= 1 && iMonth <= 12) {
            int datePerMonth;
            switch (iMonth) {
               case 2:
                  boolean dm = (iYear % 4 == 0 && iYear % 100 != 0 || iYear % 400 == 0) && iYear > 1930 && iYear < year;
                  datePerMonth = dm ? 29 : 28;
                  break;
               case 3:
               case 5:
               case 7:
               case 8:
               case 10:
               default:
                  datePerMonth = 31;
                  break;
               case 4:
               case 6:
               case 9:
               case 11:
                  datePerMonth = 30;
            }

            return iDate >= 1 && iDate <= datePerMonth;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   public static Boolean after(Date date1, Date date2) {
      return date1.after(date2);
   }

   public static int dateCompareTo(Date date1, Date date2) {
      return date1.compareTo(date2);
   }

   public static int dateCompareTo(String date1, Date date2) {
      Date date = new Date();

      try {
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
         date = sdf.parse(date1);
      } catch (Exception e) {
         e.printStackTrace();
      }

      return date.compareTo(date2);
   }

   public static int dateCompareTo(Date date1, String date2) {
      Date date = new Date();

      try {
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
         date = sdf.parse(date2);
      } catch (Exception e) {
         e.printStackTrace();
      }

      return date1.compareTo(date);
   }

   public static int dateCompareTo(String date1, String date2) {
      Date dateStr1 = new Date();
      Date dateStr2 = new Date();

      try {
         dateStr1 = DateUtils.parseDate(date1);
         dateStr2 = DateUtils.parseDate(date2);
      } catch (Exception e) {
         e.printStackTrace();
      }

      return dateStr1.compareTo(dateStr2);
   }

   public static Boolean mrHpVoInhosWeight(Long InhosWeight, Long ageY, Long ageM, Long ageD) {
      if (InhosWeight != null && InhosWeight != 0L) {
         if (ageY != null && ageY > 0L || ageM != null && ageM > 0L || ageD != null && ageD > 28L) {
            return true;
         }
      } else if ((ageY == null || ageY == 0L) && (ageM == null || ageM == 0L) && (ageD == null || ageD <= 28L)) {
         return true;
      }

      return false;
   }

   public static Boolean checkSex(String Sex, String cardTypeNo) {
      if (StringUtils.isEmpty(Sex)) {
         return true;
      } else {
         String sexCode = getGenderByIdCard(cardTypeNo);
         boolean chexkSex = false;
         switch (sexCode) {
            case "M":
               if ("2".equals(Sex)) {
                  chexkSex = true;
               }
               break;
            case "F":
               if ("1".equals(Sex)) {
                  chexkSex = true;
               }
         }

         return chexkSex;
      }
   }

   public static Boolean matchesContTel(String tel) {
      String reg = "^1(?:3\\d|4[4-9]|5[0-35-9]|6[67]|7[013-8]|8\\d|9\\d)\\d{8}$";
      return !Pattern.matches(reg, tel);
   }

   public static Boolean chexkRealInhosDays(Integer day, Date outTime, Date inhosTime) {
      if (outTime != null && inhosTime != null) {
         Calendar cal = Calendar.getInstance();
         cal.setTime(outTime);
         Calendar cal1 = Calendar.getInstance();
         cal1.setTime(inhosTime);
         if (cal.get(1) == cal1.get(1) && cal.get(2) == cal1.get(2) && cal.get(5) == cal1.get(5)) {
            if (day != 1) {
               return true;
            }
         } else {
            long between_days = (cal.getTimeInMillis() - cal1.getTimeInMillis()) / 86400000L;
            if (between_days + 1L != (long)day) {
               return true;
            }
         }

         return false;
      } else {
         return false;
      }
   }

   public static Boolean isNull(Object object) {
      if (object == null) {
         return true;
      } else {
         return object instanceof String ? StringUtils.isEmpty(String.valueOf(object)) : false;
      }
   }

   public static Boolean checkAge(Long ageY, Date birDate, Date inhosTime) {
      ageY = ageY == null ? 0L : ageY;
      if (inhosTime != null && birDate != null) {
         if (birDate.after(inhosTime)) {
            return true;
         } else {
            Calendar cal = Calendar.getInstance();
            cal.setTime(birDate);
            int birYear = cal.get(1);
            int birMonth = cal.get(2);
            cal.setTime(inhosTime);
            int inhosYear = cal.get(1);
            int inhosMonth = cal.get(2);
            if (inhosYear == birYear) {
               return ageY != 0L;
            } else {
               int age = inhosYear - birYear;
               if (birMonth > inhosMonth) {
                  --age;
               }

               return (long)age != ageY;
            }
         }
      } else {
         return true;
      }
   }

   public static Boolean checkBirth(Date birDate, String cardTypeNo) {
      if (!StringUtils.isEmpty(cardTypeNo) && birDate != null) {
         String birStr = getBirthByIdCard(cardTypeNo);
         if (birStr == null) {
            return true;
         } else {
            return birStr.equals(DateUtils.parseDateToStr("yyyyMMdd", birDate)) ? false : true;
         }
      } else {
         return true;
      }
   }

   public static boolean checkFee(List mrHpFeeList, String amountCd, String offSet, String logicCheckRule, String... fees) {
      Map<String, BigDecimal> feeMap = new HashMap();

      for(MrHpFee mrHpFee : mrHpFeeList) {
         feeMap.put(mrHpFee.getFeeCd(), BigDecimal.valueOf(mrHpFee.getAmount()));
      }

      BigDecimal amount = feeMap.get(amountCd) == null ? BigDecimal.ZERO : (BigDecimal)feeMap.get(amountCd);
      BigDecimal offSetBig = BigDecimal.ZERO;
      if (StringUtils.isNotEmpty(offSet)) {
         offSetBig = new BigDecimal(offSet);
      }

      BigDecimal feeAmount = BigDecimal.ZERO;
      if (fees != null && fees.length > 0) {
         for(String feeCd : fees) {
            BigDecimal fee = (BigDecimal)feeMap.get(feeCd);
            if (fee == null) {
               fee = BigDecimal.ZERO;
            }

            feeAmount = feeAmount.add(fee);
         }
      }

      boolean flag = false;
      switch (logicCheckRule) {
         case ">":
            flag = amount.subtract(feeAmount).abs().compareTo(offSetBig) > 0;
            break;
         case ">=":
            flag = amount.subtract(feeAmount).abs().compareTo(offSetBig) >= 0;
            break;
         case "<":
            flag = amount.subtract(feeAmount).abs().compareTo(offSetBig) < 0;
            break;
         case "<=":
            flag = amount.subtract(feeAmount).abs().compareTo(offSetBig) <= 0;
            break;
         case "==":
            flag = amount.subtract(feeAmount).abs().compareTo(offSetBig) == 0;
            break;
         case "!=":
            flag = amount.subtract(feeAmount).abs().compareTo(offSetBig) != 0;
      }

      return flag;
   }

   public static Boolean checkDate(Date date1, Date date2, String logicCheckRule) {
      return false;
   }

   public static Boolean checkRuleVo(RuleVo ruleVo) {
      return ruleVo != null && !StringUtils.isEmpty(ruleVo.getErrorMsg()) && !StringUtils.isEmpty(ruleVo.getErrorColumn()) && !StringUtils.isEmpty(ruleVo.getCheckVo()) ? false : true;
   }

   public static String getErrorMsg(Object object, String errorMsg) {
      Map<String, Object> map = new HashMap();
      if (object instanceof MrHpDia) {
         MrHpDia mrHpDia = (MrHpDia)object;
         map.put("errorColumn", mrHpDia.getDiaId());
      } else if (object instanceof MrHpOpe) {
         MrHpOpe mrHpOpe = (MrHpOpe)object;
         map.put("errorColumn", mrHpOpe.getOpeId());
      } else if (object instanceof MrHpFee) {
         MrHpFee mrHpFee = (MrHpFee)object;
         map.put("errorColumn", mrHpFee.getFeeId());
      }

      map.put("errorMsg", errorMsg);
      return JSONUtils.toJSONString(map);
   }

   public static String checkX(List mrHpDiaXYList) {
      return ((MrHpDia)mrHpDiaXYList.get(0)).getDiaId();
   }

   public static Boolean includeOne(String value, List elemAttriVoList, String elemId) {
      Boolean flag = false;
      String[] values = value.split("#");
      List<ElemAttriVo> tempList = elemAttriVoList != null && !elemAttriVoList.isEmpty() ? (List)elemAttriVoList.stream().filter((t) -> t.getElemId() != null && t.getElemId().toString().equals(elemId)).collect(Collectors.toList()) : null;
      if (tempList != null && !tempList.isEmpty()) {
         for(ElemAttriVo elemAttriVo : tempList) {
            if (elemAttriVo.getElemId().toString().equals(elemId)) {
               for(int i = 0; i < values.length; ++i) {
                  if (!flag && StringUtils.isNotEmpty(elemAttriVo.getElemConText()) && elemAttriVo.getElemConText().contains(values[i])) {
                     flag = true;
                  }
               }
            }
         }
      }

      return flag;
   }

   public static Boolean includeOneSecond(String value, List elemAttriVoList, String elemId) {
      Boolean flag = false;
      String[] values = value.split("#");
      List<ElemAttriVo> tempList = elemAttriVoList != null && !elemAttriVoList.isEmpty() ? (List)elemAttriVoList.stream().filter((t) -> t.getElemId() != null && t.getElemId().toString().equals(elemId)).collect(Collectors.toList()) : null;
      if (tempList != null && !tempList.isEmpty()) {
         for(ElemAttriVo elemAttriVo : tempList) {
            if (elemAttriVo.getElemId().toString().equals(elemId)) {
               for(int i = 0; i < values.length; ++i) {
                  if (!flag && StringUtils.isNotEmpty(elemAttriVo.getElemConText()) && elemAttriVo.getElemConText().contains(values[i])) {
                     flag = true;
                  }
               }
            }
         }
      } else {
         flag = true;
      }

      return flag;
   }

   public static Boolean includeOneOpp(String value, String elemConText) {
      Boolean flag = true;
      String[] values = value.split("#");

      for(int i = 0; i < values.length; ++i) {
         if (StringUtils.isNotEmpty(elemConText) && elemConText.contains(values[i])) {
            flag = false;
         }
      }

      return flag;
   }

   public static Boolean includeAll(String value, List elemAttriVoList, String elemId) {
      Boolean flag = false;
      String[] values = value.split("#");
      List<ElemAttriVo> tempList = elemAttriVoList != null && !elemAttriVoList.isEmpty() ? (List)elemAttriVoList.stream().filter((t) -> t.getElemId() != null && t.getElemId().equals(elemId)).collect(Collectors.toList()) : null;
      if (tempList != null && !tempList.isEmpty()) {
         for(ElemAttriVo elemAttriVo : tempList) {
            if (elemAttriVo.getElemId().toString().equals(elemId)) {
               boolean bool = true;

               for(int i = 0; i < values.length; ++i) {
                  if (bool && !elemAttriVo.getElemConText().contains(values[i])) {
                     bool = false;
                  }
               }

               if (bool) {
                  flag = true;
               }
            }
         }
      }

      return flag;
   }

   public static Boolean includeAllOpp(String value, String elemConText) {
      Boolean flag = false;
      String[] values = value.split("#");

      for(int i = 0; i < values.length; ++i) {
         if (StringUtils.isNotEmpty(elemConText) && !elemConText.contains(values[i])) {
            flag = true;
         }
      }

      return flag;
   }

   public static Boolean elemIsNull(List elemAttriVoList, String elemId) {
      Boolean flag = true;
      List<ElemAttriVo> tempList = elemAttriVoList != null && !elemAttriVoList.isEmpty() ? (List)elemAttriVoList.stream().filter((t) -> t.getElemId() != null && t.getElemId().toString().equals(elemId)).collect(Collectors.toList()) : null;
      if (tempList != null && !tempList.isEmpty()) {
         for(ElemAttriVo elemAttriVo : tempList) {
            if (elemAttriVo.getElemId().toString().equals(elemId) && StringUtils.isNotEmpty(elemAttriVo.getElemConText())) {
               flag = false;
            }
         }
      }

      return flag;
   }

   public static Boolean bigTo(String value, List elemAttriVoList, String elemId) {
      Boolean flag = false;
      List<ElemAttriVo> tempList = elemAttriVoList != null && !elemAttriVoList.isEmpty() ? (List)elemAttriVoList.stream().filter((t) -> t.getElemId() != null && t.getElemId().toString().equals(elemId)).collect(Collectors.toList()) : null;
      if (tempList != null && !tempList.isEmpty()) {
         for(ElemAttriVo elemAttriVo : tempList) {
            if (elemAttriVo.getContType().equals("7")) {
               flag = dateCompareTo(elemAttriVo.getElemConText(), value) > 0;
            }

            if (elemAttriVo.getContType().equals("2")) {
               Double elemValue = StringUtils.isNotBlank(elemAttriVo.getElemConText()) ? Double.parseDouble(elemAttriVo.getElemConText()) : null;
               Double doubleValue = Double.parseDouble(value);
               if (elemValue != null && doubleValue != null && elemValue > doubleValue) {
                  flag = true;
               }
            }
         }
      }

      return flag;
   }

   public static Boolean bigToOpp(String value, String elemConText) {
      Boolean flag = true;
      if (StringUtils.isNotEmpty(elemConText)) {
         Double elemValue = Double.parseDouble(elemConText);
         Double doubleValue = Double.parseDouble(value);
         if (elemValue > doubleValue) {
            flag = false;
         }
      }

      return flag;
   }

   public static Boolean bigEqualsTo(String value, List elemAttriVoList, String elemId) {
      Boolean flag = false;
      List<ElemAttriVo> tempList = elemAttriVoList != null && !elemAttriVoList.isEmpty() ? (List)elemAttriVoList.stream().filter((t) -> t.getElemId() != null && t.getElemId().toString().equals(elemId)).collect(Collectors.toList()) : null;
      if (tempList != null && !tempList.isEmpty()) {
         for(ElemAttriVo elemAttriVo : tempList) {
            if (elemAttriVo.getContType().equals("7")) {
               flag = dateCompareTo(elemAttriVo.getElemConText(), value) >= 0;
            }

            if (elemAttriVo.getContType().equals("2")) {
               Double elemValue = StringUtils.isNotBlank(elemAttriVo.getElemConText()) ? Double.parseDouble(elemAttriVo.getElemConText()) : null;
               Double doubleValue = Double.parseDouble(value);
               if (elemValue != null && doubleValue != null && elemValue > doubleValue) {
                  flag = true;
               }
            }
         }
      }

      return flag;
   }

   public static Boolean bigEqualsToOpp(String value, String elemConText) {
      Boolean flag = true;
      if (StringUtils.isNotEmpty(elemConText)) {
         Double elemValue = Double.parseDouble(elemConText);
         Double doubleValue = Double.parseDouble(value);
         if (elemValue >= doubleValue) {
            flag = false;
         }
      }

      return flag;
   }

   public static Boolean smallTo(String value, List elemAttriVoList, String elemId) {
      Boolean flag = false;
      List<ElemAttriVo> tempList = elemAttriVoList != null && !elemAttriVoList.isEmpty() ? (List)elemAttriVoList.stream().filter((t) -> t.getElemId() != null && t.getElemId().toString().equals(elemId)).collect(Collectors.toList()) : null;
      if (tempList != null && !tempList.isEmpty()) {
         for(ElemAttriVo elemAttriVo : tempList) {
            if (elemAttriVo.getContType().equals("7")) {
               flag = dateCompareTo(elemAttriVo.getElemConText(), value) < 0;
            }

            if (elemAttriVo.getContType().equals("2")) {
               Double elemValue = StringUtils.isNotBlank(elemAttriVo.getElemConText()) ? Double.parseDouble(elemAttriVo.getElemConText()) : null;
               Double doubleValue = Double.parseDouble(value);
               if (elemValue != null && doubleValue != null && elemValue > doubleValue) {
                  flag = true;
               }
            }
         }
      }

      return flag;
   }

   public static Boolean smallToOpp(String value, String elemConText) {
      Boolean flag = true;
      if (StringUtils.isNotEmpty(elemConText)) {
         Double elemValue = Double.parseDouble(elemConText);
         Double doubleValue = Double.parseDouble(value);
         if (elemValue < doubleValue) {
            flag = false;
         }
      }

      return flag;
   }

   public static Boolean smallEqualsTo(String value, List elemAttriVoList, String elemId) {
      Boolean flag = false;
      List<ElemAttriVo> tempList = elemAttriVoList != null && !elemAttriVoList.isEmpty() ? (List)elemAttriVoList.stream().filter((t) -> t.getElemId() != null && t.getElemId().toString().equals(elemId)).collect(Collectors.toList()) : null;
      if (tempList != null && !tempList.isEmpty()) {
         for(ElemAttriVo elemAttriVo : tempList) {
            if (elemAttriVo.getContType().equals("7")) {
               flag = dateCompareTo(elemAttriVo.getElemConText(), value) <= 0;
            }

            if (elemAttriVo.getContType().equals("2")) {
               Double elemValue = StringUtils.isNotBlank(elemAttriVo.getElemConText()) ? Double.parseDouble(elemAttriVo.getElemConText()) : null;
               Double doubleValue = Double.parseDouble(value);
               if (elemValue != null && doubleValue != null && elemValue > doubleValue) {
                  flag = true;
               }
            }
         }
      }

      return flag;
   }

   public static Boolean smallEqualsToOpp(String value, String elemConText) {
      Boolean flag = true;
      if (StringUtils.isNotEmpty(elemConText)) {
         Double elemValue = Double.parseDouble(elemConText);
         Double doubleValue = Double.parseDouble(value);
         if (elemValue <= doubleValue) {
            flag = false;
         }
      }

      return flag;
   }

   public static Boolean equalsTo(String value, List elemAttriVoList, String elemId) {
      Boolean flag = false;
      List<ElemAttriVo> tempList = elemAttriVoList != null && !elemAttriVoList.isEmpty() ? (List)elemAttriVoList.stream().filter((t) -> t.getElemId() != null && t.getElemId().toString().equals(elemId)).collect(Collectors.toList()) : null;
      if (tempList != null && !tempList.isEmpty()) {
         for(ElemAttriVo elemAttriVo : tempList) {
            if (elemAttriVo.getContType().equals("7")) {
               flag = dateCompareTo(elemAttriVo.getElemConText(), value) == 0;
            }

            if (elemAttriVo.getContType().equals("2")) {
               Double elemValue = StringUtils.isNotBlank(elemAttriVo.getElemConText()) ? Double.parseDouble(elemAttriVo.getElemConText()) : null;
               Double doubleValue = Double.parseDouble(value);
               if (elemValue != null && doubleValue != null && elemValue > doubleValue) {
                  flag = true;
               }
            }
         }
      }

      return flag;
   }

   public static Boolean equalsToOpp(String value, String elemConText) {
      Boolean flag = true;
      if (StringUtils.isNotEmpty(elemConText)) {
         Double elemValue = Double.parseDouble(elemConText);
         Double doubleValue = Double.parseDouble(value);
         if (elemValue == doubleValue) {
            flag = false;
         }
      }

      return flag;
   }

   public static boolean outDiagnosisContains(List tdCmDiagXyList, String content, String mainDiagnosis) {
      boolean flag = false;
      if (CollectionUtils.isNotEmpty(tdCmDiagXyList)) {
         if ("1".equals(mainDiagnosis)) {
            List<TdCmDiagSave> collect = (List)tdCmDiagXyList.stream().filter((t) -> t.getDiag_cd().equals("01")).collect(Collectors.toList());
            TdCmDiagSave tdCmDiagSave = (TdCmDiagSave)collect.get(0);
            if (tdCmDiagSave != null && StringUtils.isNotBlank(content)) {
               String[] split = content.split(",");

               for(String icd : split) {
                  if (tdCmDiagSave.getDiag_cd().contains(icd)) {
                     flag = true;
                     break;
                  }
               }
            }
         } else {
            for(TdCmDiagSave diagSave : tdCmDiagXyList) {
               if (StringUtils.isNotBlank(diagSave.getDiag_cd())) {
                  String[] split = content.split(",");

                  for(String icd : split) {
                     if (diagSave.getDiag_cd().contains(icd)) {
                        flag = true;
                        break;
                     }
                  }
               }
            }
         }
      }

      return flag;
   }

   public static boolean operationDiagnosisContains(List tdCmOperList, String content, String mainDiagnosis) {
      boolean flag = false;
      if (CollectionUtils.isNotEmpty(tdCmOperList)) {
         if ("1".equals(mainDiagnosis)) {
            List<TdCmOperSave> collect = (List)tdCmOperList.stream().filter((t) -> t.getOper_main().equals("1")).collect(Collectors.toList());
            TdCmOperSave tdCmOperSave = (TdCmOperSave)collect.get(0);
            if (tdCmOperSave != null && StringUtils.isNotBlank(content)) {
               String[] split = content.split(",");

               for(String icd : split) {
                  if (tdCmOperSave.getOper_icd().contains(icd)) {
                     flag = true;
                     break;
                  }
               }
            }
         } else {
            for(TdCmOperSave operSave : tdCmOperList) {
               if (StringUtils.isNotBlank(operSave.getOper_icd())) {
                  String[] split = content.split(",");

                  for(String icd : split) {
                     if (operSave.getOper_icd().contains(icd)) {
                        flag = true;
                        break;
                     }
                  }
               }
            }
         }
      }

      return flag;
   }

   static {
      cityCodes.put("11", "北京");
      cityCodes.put("12", "天津");
      cityCodes.put("13", "河北");
      cityCodes.put("14", "山西");
      cityCodes.put("15", "内蒙古");
      cityCodes.put("21", "辽宁");
      cityCodes.put("22", "吉林");
      cityCodes.put("23", "黑龙江");
      cityCodes.put("31", "上海");
      cityCodes.put("32", "江苏");
      cityCodes.put("33", "浙江");
      cityCodes.put("34", "安徽");
      cityCodes.put("35", "福建");
      cityCodes.put("36", "江西");
      cityCodes.put("37", "山东");
      cityCodes.put("41", "河南");
      cityCodes.put("42", "湖北");
      cityCodes.put("43", "湖南");
      cityCodes.put("44", "广东");
      cityCodes.put("45", "广西");
      cityCodes.put("46", "海南");
      cityCodes.put("50", "重庆");
      cityCodes.put("51", "四川");
      cityCodes.put("52", "贵州");
      cityCodes.put("53", "云南");
      cityCodes.put("54", "西藏");
      cityCodes.put("61", "陕西");
      cityCodes.put("62", "甘肃");
      cityCodes.put("63", "青海");
      cityCodes.put("64", "宁夏");
      cityCodes.put("65", "新疆");
      cityCodes.put("71", "台湾");
      cityCodes.put("81", "香港");
      cityCodes.put("82", "澳门");
      cityCodes.put("91", "国外");
      twFirstCode.put("A", 10);
      twFirstCode.put("B", 11);
      twFirstCode.put("C", 12);
      twFirstCode.put("D", 13);
      twFirstCode.put("E", 14);
      twFirstCode.put("F", 15);
      twFirstCode.put("G", 16);
      twFirstCode.put("H", 17);
      twFirstCode.put("J", 18);
      twFirstCode.put("K", 19);
      twFirstCode.put("L", 20);
      twFirstCode.put("M", 21);
      twFirstCode.put("N", 22);
      twFirstCode.put("P", 23);
      twFirstCode.put("Q", 24);
      twFirstCode.put("R", 25);
      twFirstCode.put("S", 26);
      twFirstCode.put("T", 27);
      twFirstCode.put("U", 28);
      twFirstCode.put("V", 29);
      twFirstCode.put("X", 30);
      twFirstCode.put("Y", 31);
      twFirstCode.put("W", 32);
      twFirstCode.put("Z", 33);
      twFirstCode.put("I", 34);
      twFirstCode.put("O", 35);
      hkFirstCode.put("A", 1);
      hkFirstCode.put("B", 2);
      hkFirstCode.put("C", 3);
      hkFirstCode.put("R", 18);
      hkFirstCode.put("U", 21);
      hkFirstCode.put("Z", 26);
      hkFirstCode.put("X", 24);
      hkFirstCode.put("W", 23);
      hkFirstCode.put("O", 15);
      hkFirstCode.put("N", 14);
   }
}
