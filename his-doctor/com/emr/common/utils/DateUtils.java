package com.emr.common.utils;

import com.emr.project.holiday.domain.TmBsHoliday;
import java.lang.management.ManagementFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.lang3.time.DateFormatUtils;

public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
   public static String YYYY = "yyyy";
   public static String YYYY_MM = "yyyy-MM";
   public static String YYYY_MM_DD = "yyyy-MM-dd";
   public static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
   public static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
   public static String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
   public static String YY_MM_DD_HH_MM = "yy.MM.dd HH:mm";
   public static String MM_DD = "MM.dd";
   public static String YYYYMMDDHHMM = "yyyyMMddHHmm";
   public static String HH_MM = "HH:mm";
   private static String[] parsePatterns = new String[]{"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM", "yyyy-MM-dd HH:mm:ss.s", "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM", "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM", "yyyy年MM月dd日", "YYYY年M月D日", "yyyy年MM月", "HH时mm分ss秒", "HH时mm分", "mm分ss秒", "yyyy年MM月dd日 HH时mm分ss秒", "yyyy年MM月dd日 HH时mm分", "yyyy年MM月dd日 HH时", "yyyy年MM月dd日HH时mm分ss秒", "yyyy年MM月dd日HH时mm分", "yyyy年MM月dd日HH时", "YYYY年M月D日 HH时mm分ss秒", "YYYY年M月D日 HH时mm分", "YYYY年M月D日 HH时", "YYYY年M月D日HH时mm分ss秒", "YYYY年M月D日HH时mm分", "YYYY年M月D日HH时"};

   public static Date getNowDate() {
      return new Date();
   }

   public static String getDate() {
      return dateTimeNow(YYYY_MM_DD);
   }

   public static final String getTime() {
      return dateTimeNow(YYYY_MM_DD_HH_MM_SS);
   }

   public static final String dateTimeNow() {
      return dateTimeNow(YYYYMMDDHHMMSS);
   }

   public static final String dateTimeNow(final String format) {
      return parseDateToStr(format, new Date());
   }

   public static final String dateTime(final Date date) {
      return parseDateToStr(YYYY_MM_DD, date);
   }

   public static final String parseDateToStr(final String format, final Date date) {
      return (new SimpleDateFormat(format)).format(date);
   }

   public static final Date dateTime(final String format, final String ts) {
      try {
         return (new SimpleDateFormat(format)).parse(ts);
      } catch (ParseException e) {
         throw new RuntimeException(e);
      }
   }

   public static final String datePath() {
      Date now = new Date();
      return DateFormatUtils.format(now, "yyyy/MM/dd");
   }

   public static final String dateTime() {
      Date now = new Date();
      return DateFormatUtils.format(now, "yyyyMMdd");
   }

   public static Date parseDate(Object str) {
      if (str == null) {
         return null;
      } else {
         try {
            return parseDate(str.toString(), parsePatterns);
         } catch (ParseException var2) {
            return null;
         }
      }
   }

   public static Date getServerStartDate() {
      long time = ManagementFactory.getRuntimeMXBean().getStartTime();
      return new Date(time);
   }

   public static String getDatePoor(Date endDate, Date nowDate) {
      long nd = 86400000L;
      long nh = 3600000L;
      long nm = 60000L;
      long diff = endDate.getTime() - nowDate.getTime();
      long day = diff / nd;
      long hour = diff % nd / nh;
      long min = diff % nd % nh / nm;
      return (day > 0L ? day + "天" : "") + (hour > 0L ? hour + "小时" : "") + (min > 0L ? min + "分钟" : "");
   }

   public static int getDiffDay(Date endDate, Date nowDate) {
      long nd = 86400000L;
      long nh = 3600000L;
      long nm = 60000L;
      long diff = endDate.getTime() - nowDate.getTime();
      Long day = diff / nd;
      return day.intValue();
   }

   public static int getDateHours(Date endDate, Date nowDate) {
      long nd = 86400000L;
      long nh = 3600000L;
      long nm = 60000L;
      long diff = endDate.getTime() - nowDate.getTime();
      long var10000 = diff / nd;
      long hour = diff / nh;
      return Integer.parseInt(String.valueOf(hour));
   }

   public static int getDateMinutes(Date endDate, Date nowDate) {
      long nd = 86400000L;
      long nh = 3600000L;
      long nm = 60000L;
      long diff = endDate.getTime() - nowDate.getTime();
      long minute = diff / nm;
      return Integer.parseInt(String.valueOf(minute));
   }

   public static int getInHosDays(Date startDate, Date endDate) {
      Calendar startCall = Calendar.getInstance();
      startCall.setTime(startDate);
      Calendar endCal = Calendar.getInstance();
      endCal.setTime(endDate);
      int day1 = startCall.get(6);
      int day2 = endCal.get(6);
      int year1 = startCall.get(1);
      int year2 = endCal.get(1);
      if (year1 == year2) {
         System.out.println("判断day2 - day1 : " + (day2 - day1));
         return day2 - day1;
      } else {
         int timeDistance = 0;

         for(int i = year1; i < year2; ++i) {
            if ((i % 4 != 0 || i % 100 == 0) && i % 400 != 0) {
               timeDistance += 365;
            } else {
               timeDistance += 366;
            }
         }

         return timeDistance + (day2 - day1);
      }
   }

   public static boolean isTheSameDay(Date d1, Date d2) {
      Calendar c1 = Calendar.getInstance();
      Calendar c2 = Calendar.getInstance();
      c1.setTime(d1);
      c2.setTime(d2);
      return c1.get(1) == c2.get(1) && c1.get(2) == c2.get(2) && c1.get(5) == c2.get(5);
   }

   public static synchronized String getDateStr(String type) {
      String resStr = type + parseDateToStr("yyMMddHHmmssSSS", new Date());

      try {
         Thread.sleep(1L);
      } catch (InterruptedException e) {
         e.printStackTrace();
      }

      return resStr;
   }

   public static int dayForWeek(Date pTime) throws Exception {
      Calendar c = Calendar.getInstance();
      c.setTime(pTime);
      int dayForWeek = 0;
      if (c.get(7) == 1) {
         dayForWeek = 7;
      } else {
         dayForWeek = c.get(7) - 1;
      }

      return dayForWeek;
   }

   public static String dateFormat(Date date, String format) {
      return (new SimpleDateFormat(format)).format(date);
   }

   public static String dateFormatS(Date date, String format) {
      SimpleDateFormat sdf = new SimpleDateFormat(format);
      String dateS = sdf.format(date);
      return dateS;
   }

   public static String formatYMD(Date date) {
      return date != null && !date.equals("") ? dateFormat(date, "yyyy-MM-dd") : "";
   }

   public static Date addDay(Date date, int day) {
      Calendar calendar = Calendar.getInstance();
      long millis = getMillis(date) + (long)day * 24L * 3600L * 1000L;
      calendar.setTimeInMillis(millis);
      return calendar.getTime();
   }

   public static String addDay(String strDate, int days) {
      SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
      Date date = null;

      try {
         date = df.parse(strDate);
      } catch (ParseException e) {
         e.printStackTrace();
      }

      Calendar cal = Calendar.getInstance();
      cal.setTime(date);
      cal.add(5, days);
      return df.format(cal.getTime());
   }

   public static long getMillis(Date date) {
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(date);
      return calendar.getTimeInMillis();
   }

   public static int countFilingDays(Date outDeptTime, Date fileTime, List bsHolidayList) {
      List<TmBsHoliday> tmBsHolidayJjrList = new ArrayList();
      List<TmBsHoliday> tmBsHolidayTxList = new ArrayList();
      if (bsHolidayList != null && bsHolidayList.size() > 0) {
         tmBsHolidayJjrList = (List)bsHolidayList.stream().filter((t) -> "1".equals(t.getHolidayType())).collect(Collectors.toList());
         tmBsHolidayTxList = (List)bsHolidayList.stream().filter((t) -> "2".equals(t.getHolidayType())).collect(Collectors.toList());
      }

      Date outDeptDate = null;
      Calendar instance = Calendar.getInstance();
      if (outDeptTime != null) {
         if (tmBsHolidayJjrList != null && tmBsHolidayJjrList.size() > 0) {
            for(TmBsHoliday tmBsHolidayJjr : tmBsHolidayJjrList) {
               if (outDeptTime.getTime() >= tmBsHolidayJjr.getStartDate().getTime() && outDeptTime.getTime() <= tmBsHolidayJjr.getEndDate().getTime()) {
                  instance.setTime(tmBsHolidayJjr.getEndDate());
                  instance.add(5, 1);
                  outDeptDate = instance.getTime();
               } else {
                  outDeptDate = outDeptTime;
               }
            }
         } else {
            outDeptDate = outDeptTime;
         }

         instance.setTime(outDeptDate);
         int week = instance.get(7);
         if (week == 7) {
            instance.setTime(outDeptDate);
            Boolean weekdayTx = false;
            if (tmBsHolidayTxList != null && tmBsHolidayTxList.size() > 0) {
               for(TmBsHoliday tmBsHolidayTx : tmBsHolidayTxList) {
                  outDeptDate = instance.getTime();
                  if (outDeptDate.getTime() >= tmBsHolidayTx.getStartDate().getTime() && outDeptDate.getTime() <= tmBsHolidayTx.getStartDate().getTime()) {
                     weekdayTx = true;
                  }
               }
            }

            if (weekdayTx) {
               outDeptDate = instance.getTime();
            } else {
               instance.add(5, 2);
               outDeptDate = instance.getTime();
            }
         } else if (week == 1) {
            instance.setTime(outDeptDate);
            Boolean weekdayTx = false;
            if (tmBsHolidayTxList != null && tmBsHolidayTxList.size() > 0) {
               for(TmBsHoliday tmBsHolidayTx : tmBsHolidayTxList) {
                  outDeptDate = instance.getTime();
                  if (outDeptDate.getTime() >= tmBsHolidayTx.getStartDate().getTime() && outDeptDate.getTime() <= tmBsHolidayTx.getStartDate().getTime()) {
                     weekdayTx = true;
                  }
               }
            }

            if (weekdayTx) {
               outDeptDate = instance.getTime();
            } else {
               instance.add(5, 1);
               outDeptDate = instance.getTime();
            }
         }
      }

      Date fileDate = null;
      if (fileTime != null) {
         if (tmBsHolidayJjrList != null && tmBsHolidayJjrList.size() > 0) {
            for(TmBsHoliday tmBsHolidayJjr : tmBsHolidayJjrList) {
               if (fileTime.getTime() >= tmBsHolidayJjr.getStartDate().getTime() && fileTime.getTime() <= tmBsHolidayJjr.getEndDate().getTime()) {
                  instance.setTime(tmBsHolidayJjr.getEndDate());
                  instance.add(5, 1);
                  fileDate = instance.getTime();
               } else {
                  fileDate = fileTime;
               }
            }
         } else {
            fileDate = fileTime;
         }

         instance.setTime(fileDate);
         int week = instance.get(7);
         if (week == 7) {
            instance.setTime(fileDate);
            Boolean weekdayTx = false;
            if (tmBsHolidayTxList != null && tmBsHolidayTxList.size() > 0) {
               for(TmBsHoliday tmBsHolidayTx : tmBsHolidayTxList) {
                  fileDate = instance.getTime();
                  if (fileDate.getTime() >= tmBsHolidayTx.getStartDate().getTime() && fileDate.getTime() <= tmBsHolidayTx.getStartDate().getTime()) {
                     weekdayTx = true;
                  }
               }
            }

            if (weekdayTx) {
               fileDate = instance.getTime();
            } else {
               instance.add(5, 2);
               fileDate = instance.getTime();
            }
         } else if (week == 1) {
            instance.setTime(fileDate);
            Boolean weekdayTx = false;
            if (tmBsHolidayTxList != null && tmBsHolidayTxList.size() > 0) {
               for(TmBsHoliday tmBsHolidayTx : tmBsHolidayTxList) {
                  fileDate = instance.getTime();
                  if (fileDate.getTime() >= tmBsHolidayTx.getStartDate().getTime() && fileDate.getTime() <= tmBsHolidayTx.getStartDate().getTime()) {
                     weekdayTx = true;
                  }
               }
            }

            if (weekdayTx) {
               fileDate = instance.getTime();
            } else {
               instance.add(5, 1);
               fileDate = instance.getTime();
            }
         }
      }

      int filingDays = 1;
      Date daysNew = null;
      instance.setTime(outDeptDate);
      if (fileDate != null && outDeptDate != null) {
         for(int i = 0; (long)i <= (fileDate.getTime() - outDeptDate.getTime()) / 86400000L + 1L; ++i) {
            instance.add(5, 1);
            daysNew = instance.getTime();
            Boolean plusOne = false;
            if (daysNew.getTime() <= fileDate.getTime()) {
               if (tmBsHolidayJjrList != null && tmBsHolidayJjrList.size() > 0) {
                  for(TmBsHoliday tmBsHolidayJjr : tmBsHolidayJjrList) {
                     if (daysNew.getTime() >= tmBsHolidayJjr.getStartDate().getTime() && daysNew.getTime() <= tmBsHolidayJjr.getEndDate().getTime()) {
                        plusOne = false;
                        break;
                     }

                     plusOne = true;
                  }
               } else {
                  plusOne = true;
               }

               instance.setTime(daysNew);
               int week = instance.get(7);
               if (week == 7) {
                  if (tmBsHolidayTxList != null && tmBsHolidayTxList.size() > 0) {
                     for(TmBsHoliday tmBsHolidayTx : tmBsHolidayTxList) {
                        daysNew = instance.getTime();
                        if (daysNew.getTime() >= tmBsHolidayTx.getStartDate().getTime() && daysNew.getTime() <= tmBsHolidayTx.getStartDate().getTime()) {
                           plusOne = true;
                        } else {
                           plusOne = false;
                        }
                     }
                  }
               } else if (week == 1) {
                  instance.setTime(daysNew);
                  if (tmBsHolidayTxList != null && tmBsHolidayTxList.size() > 0) {
                     for(TmBsHoliday tmBsHolidayTx : tmBsHolidayTxList) {
                        daysNew = instance.getTime();
                        if (daysNew.getTime() >= tmBsHolidayTx.getStartDate().getTime() && daysNew.getTime() <= tmBsHolidayTx.getStartDate().getTime()) {
                           plusOne = true;
                        } else {
                           plusOne = false;
                        }
                     }
                  }
               }

               if (plusOne) {
                  ++filingDays;
               }
            }
         }
      }

      return filingDays;
   }

   public static Map getAge(Date birthday, Date currDay) throws Exception {
      Map<String, Integer> ageMap = new HashMap(1);
      Calendar ryrqC = Calendar.getInstance();
      ryrqC.setTime(currDay);
      Calendar b = Calendar.getInstance();
      b.setTime(birthday);
      int year = ryrqC.get(1) - b.get(1);
      int month = ryrqC.get(2) - b.get(2);
      int day = ryrqC.get(5) - b.get(5);
      if (month <= 0) {
         month = 12 - b.get(2) + ryrqC.get(2);
         --year;
      }

      if (day <= 0) {
         day = b.getMaximum(5) - b.get(5) + ryrqC.get(5);
         --month;
      }

      ageMap.put("N", year);
      ageMap.put("Y", month);
      ageMap.put("R", day);
      return ageMap;
   }

   public static void main(String[] args) {
      try {
         Date birthday = parseDate("2019-08-06");
         Date currDay = parseDate("2023-08-01");
         getAge(birthday, currDay);
      } catch (Exception e) {
         e.printStackTrace();
      }

   }

   public static Date getEndTime(Date currDate, Integer finishLimitTime, String limitTimeUnit) {
      Date resDate = null;
      switch (limitTimeUnit) {
         case "H":
            resDate = addHours(currDate, finishLimitTime);
            break;
         case "D":
            resDate = addDays(currDate, finishLimitTime);
      }

      return resDate;
   }

   public static boolean isValidDateFormat(String dateStr) {
      if (StringUtils.isEmpty(dateStr)) {
         return false;
      } else {
         try {
            parseDate(dateStr, parsePatterns);
            return true;
         } catch (Exception var2) {
            return false;
         }
      }
   }
}
