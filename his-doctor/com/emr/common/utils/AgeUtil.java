package com.emr.common.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AgeUtil {
   public static boolean isChild(Long ageY) {
      boolean isChild = false;
      if (ageY.compareTo(14L) < 0) {
         isChild = true;
      }

      return isChild;
   }

   public static String getAgeStr(Long ageY, Long ageM, Long ageD, Long ageH, Long ageMi) {
      StringBuffer ageStrSB = new StringBuffer();
      ageStrSB.append(ageY != null && ageY.compareTo(0L) > 0 ? ageY + "岁" : "");
      ageStrSB.append(ageM != null && ageM.compareTo(0L) > 0 ? ageM + "个月" : "");
      ageStrSB.append(ageD != null && ageD.compareTo(0L) > 0 ? ageD + "天" : "");
      ageStrSB.append(ageH != null && ageH.compareTo(0L) > 0 ? ageH + "小时" : "");
      ageStrSB.append(ageMi != null && ageMi.compareTo(0L) > 0 ? ageMi + "分钟" : "");
      return ageStrSB.toString();
   }

   public static Map getBirAgeSex(String certificateNo) {
      Map<String, String> map = new HashMap();
      String birthday = "";
      String ageY = "";
      String ageM = "";
      String ageD = "";
      String sexCode = "";
      int year = Calendar.getInstance().get(1);
      char[] number = certificateNo.toCharArray();
      boolean flag = true;
      if (number.length == 15) {
         for(int x = 0; x < number.length; ++x) {
            if (!flag) {
               return new HashMap();
            }

            flag = Character.isDigit(number[x]);
         }
      } else if (number.length == 18) {
         for(int x = 0; x < number.length - 1; ++x) {
            if (!flag) {
               return new HashMap();
            }

            flag = Character.isDigit(number[x]);
         }
      }

      if (flag && certificateNo.length() == 15) {
         birthday = "19" + certificateNo.substring(6, 8) + "-" + certificateNo.substring(8, 10) + "-" + certificateNo.substring(10, 12);
         sexCode = Integer.parseInt(certificateNo.substring(certificateNo.length() - 3, certificateNo.length())) % 2 == 0 ? "2" : "1";
         Period per = Period.between(LocalDate.parse(birthday), LocalDate.now());
         ageY = String.valueOf(per.getYears());
         ageM = String.valueOf(per.getMonths());
         ageD = String.valueOf(per.getDays());
      } else if (flag && certificateNo.length() == 18) {
         birthday = certificateNo.substring(6, 10) + "-" + certificateNo.substring(10, 12) + "-" + certificateNo.substring(12, 14);
         sexCode = Integer.parseInt(certificateNo.substring(certificateNo.length() - 4, certificateNo.length() - 1)) % 2 == 0 ? "2" : "1";
         Period per = Period.between(LocalDate.parse(birthday), LocalDate.now());
         ageY = String.valueOf(per.getYears());
         ageM = String.valueOf(per.getMonths());
         ageD = String.valueOf(per.getDays());
      }

      map.put("birthday", birthday);
      map.put("ageY", ageY);
      map.put("ageM", ageM);
      map.put("ageD", ageD);
      map.put("sexCode", sexCode);
      return map;
   }

   public static Map getBirAgeSexByInhosDate(String certificateNo, Date inHosDate) {
      Map<String, String> map = new HashMap();
      String birthday = "";
      String ageY = "";
      String ageM = "";
      String ageD = "";
      String sexCode = "";
      Instant instant = inHosDate.toInstant();
      ZoneId zoneId = ZoneId.systemDefault();
      LocalDate localInHosDate = instant.atZone(zoneId).toLocalDate();
      int year = Calendar.getInstance().get(1);
      char[] number = certificateNo.toCharArray();
      boolean flag = true;
      if (number.length == 15) {
         for(int x = 0; x < number.length; ++x) {
            if (!flag) {
               return new HashMap();
            }

            flag = Character.isDigit(number[x]);
         }
      } else if (number.length == 18) {
         for(int x = 0; x < number.length - 1; ++x) {
            if (!flag) {
               return new HashMap();
            }

            flag = Character.isDigit(number[x]);
         }
      }

      if (flag && certificateNo.length() == 15) {
         birthday = "19" + certificateNo.substring(6, 8) + "-" + certificateNo.substring(8, 10) + "-" + certificateNo.substring(10, 12);
         sexCode = Integer.parseInt(certificateNo.substring(certificateNo.length() - 3, certificateNo.length())) % 2 == 0 ? "2" : "1";
         Period per = Period.between(LocalDate.parse(birthday), localInHosDate);
         ageY = String.valueOf(per.getYears());
         ageM = String.valueOf(per.getMonths());
         ageD = String.valueOf(per.getDays());
      } else if (flag && certificateNo.length() == 18) {
         birthday = certificateNo.substring(6, 10) + "-" + certificateNo.substring(10, 12) + "-" + certificateNo.substring(12, 14);
         sexCode = Integer.parseInt(certificateNo.substring(certificateNo.length() - 4, certificateNo.length() - 1)) % 2 == 0 ? "2" : "1";
         Period per = Period.between(LocalDate.parse(birthday), localInHosDate);
         ageY = String.valueOf(per.getYears());
         ageM = String.valueOf(per.getMonths());
         ageD = String.valueOf(per.getDays());
      }

      map.put("birthday", birthday);
      map.put("ageY", ageY);
      map.put("ageM", ageM);
      map.put("ageD", ageD);
      map.put("sexCode", sexCode);
      return map;
   }

   public static String getFormatAge(Integer ageY, Integer ageM, Integer ageD, Integer ageH, Integer ageMi) {
      String age = null;
      if (ageY != null && ageY >= 14) {
         age = ageY + "岁";
      } else if (ageY != null && ageY < 14 && ageY >= 1) {
         if (ageM != null && ageM > 0) {
            age = ageY + "岁" + ageM + "月";
         } else {
            age = ageY + "岁";
         }
      } else if ((ageY == null || ageY == 0) && ageM != null && ageM >= 1) {
         if (ageD != null && ageD > 0) {
            age = ageM + "月" + ageD + "天";
         } else {
            age = ageM + "月";
         }
      } else if ((ageM == null || ageM == 0) && ageD != null && ageD >= 1) {
         if (ageH != null && ageH > 0) {
            age = ageD + "天" + ageH + "小时";
         } else {
            age = ageD + "天";
         }
      } else if ((ageD == null || ageD == 0) && ageH != null && ageH > 0) {
         if (ageMi != 0) {
            age = ageH + "小时" + ageMi + "分钟";
         } else {
            age = ageD + "小时";
         }
      }

      return age;
   }
}
