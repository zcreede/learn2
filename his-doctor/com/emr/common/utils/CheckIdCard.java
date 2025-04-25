package com.emr.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CheckIdCard {
   private static final String BIRTH_DATE_FORMAT = "yyyyMMdd";
   private static final Date MINIMAL_BIRTH_DATE = new Date(-2209017600000L);
   private static final int NEW_CARD_NUMBER_LENGTH = 18;
   private static final int OLD_CARD_NUMBER_LENGTH = 15;
   private static final char[] VERIFY_CODE = new char[]{'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};
   private static final int[] VERIFY_CODE_WEIGHT = new int[]{7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
   private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

   public static boolean check(String cardNumber) {
      if (null != cardNumber) {
         cardNumber = cardNumber.trim();
         if (15 == cardNumber.length()) {
            cardNumber = contertToNewCardNumber(cardNumber);
         }

         return validate(cardNumber);
      } else {
         return false;
      }
   }

   public static Map identityCard(String cardNumber, Date inHosTime) throws Exception {
      Map<String, Object> map = new HashMap();
      if (null != cardNumber) {
         cardNumber = cardNumber.trim();
         if (15 == cardNumber.length()) {
            map = identityCard15(cardNumber, inHosTime);
         } else {
            map = identityCard18(cardNumber, inHosTime);
         }
      }

      return map;
   }

   public static String getBirthDate(String cardNumber) {
      String birthDate = null;
      if (null != cardNumber) {
         cardNumber = cardNumber.trim();
         if (15 == cardNumber.length()) {
            cardNumber = contertToNewCardNumber(cardNumber);
         }

         birthDate = getBirthDayPart(cardNumber);
      }

      return birthDate;
   }

   private static boolean validate(String cardNumber) {
      boolean result = true;
      result = result && null != cardNumber;
      result = result && 18 == cardNumber.length();

      for(int i = 0; result && i < 17; ++i) {
         char ch = cardNumber.charAt(i);
         result = result && ch >= '0' && ch <= '9';
      }

      result = result && calculateVerifyCode(cardNumber) == cardNumber.charAt(17);

      try {
         Date birthDate = (new SimpleDateFormat("yyyyMMdd")).parse(getBirthDayPart(cardNumber));
         result = result && null != birthDate;
         result = result && birthDate.before(new Date());
         result = result && birthDate.after(MINIMAL_BIRTH_DATE);
         String birthdayPart = getBirthDayPart(cardNumber);
         String realBirthdayPart = (new SimpleDateFormat("yyyyMMdd")).format(birthDate);
         result = result && birthdayPart.equals(realBirthdayPart);
      } catch (Exception var5) {
         result = false;
      }

      return result;
   }

   private static String getBirthDayPart(String cardNumber) {
      return cardNumber.substring(6, 14);
   }

   private static char calculateVerifyCode(CharSequence cardNumber) {
      int sum = 0;

      for(int i = 0; i < 17; ++i) {
         char ch = cardNumber.charAt(i);
         sum += (ch - 48) * VERIFY_CODE_WEIGHT[i];
      }

      return VERIFY_CODE[sum % 11];
   }

   private static String contertToNewCardNumber(String oldCardNumber) {
      StringBuilder buf = new StringBuilder(18);
      buf.append(oldCardNumber.substring(0, 6));
      buf.append("19");
      buf.append(oldCardNumber.substring(6));
      buf.append(calculateVerifyCode(buf));
      return buf.toString();
   }

   private static Map identityCard18(String CardCode, Date inHosTime) throws Exception {
      Map<String, Object> map = new HashMap();
      String year = CardCode.substring(6).substring(0, 4);
      String month = CardCode.substring(10).substring(0, 2);
      String sex;
      if (Integer.parseInt(CardCode.substring(16).substring(0, 1)) % 2 == 0) {
         sex = "2";
      } else {
         sex = "1";
      }

      String currentYear = format.format(inHosTime).substring(0, 4);
      String currentMonth = format.format(inHosTime).substring(5, 7);
      int age = 0;
      if (Integer.parseInt(month) <= Integer.parseInt(currentMonth)) {
         age = Integer.parseInt(currentYear) - Integer.parseInt(year) + 1;
      } else {
         age = Integer.parseInt(currentYear) - Integer.parseInt(year);
      }

      map.put("sex", sex);
      map.put("age", age);
      return map;
   }

   private static Map identityCard15(String card, Date inHosTime) throws Exception {
      Map<String, Object> map = new HashMap();
      String year = "19" + card.substring(6, 8);
      String yue = card.substring(8, 10);
      String sex;
      if (Integer.parseInt(card.substring(14, 15)) % 2 == 0) {
         sex = "2";
      } else {
         sex = "1";
      }

      String currentYear = format.format(inHosTime).substring(0, 4);
      String currentMonth = format.format(inHosTime).substring(5, 7);
      int age = 0;
      if (Integer.parseInt(yue) <= Integer.parseInt(currentMonth)) {
         age = Integer.parseInt(currentYear) - Integer.parseInt(year) + 1;
      } else {
         age = Integer.parseInt(currentYear) - Integer.parseInt(year);
      }

      map.put("sex", sex);
      map.put("age", age);
      return map;
   }
}
