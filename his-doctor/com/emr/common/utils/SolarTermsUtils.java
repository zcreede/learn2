package com.emr.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class SolarTermsUtils {
   public static class LunarCalendarFestivalUtils {
      private String animal;
      private String ganZhiYear;
      private String lunarYear;
      private String lunarMonth;
      private String lunarDay;
      private String solarFestival;
      private String lunarFestival;
      private String lunarTerm;
      static final long[] lunarInfo = new long[]{19416L, 19168L, 42352L, 21717L, 53856L, 55632L, 91476L, 22176L, 39632L, 21970L, 19168L, 42422L, 42192L, 53840L, 119381L, 46400L, 54944L, 44450L, 38320L, 84343L, 18800L, 42160L, 46261L, 27216L, 27968L, 109396L, 11104L, 38256L, 21234L, 18800L, 25958L, 54432L, 59984L, 28309L, 23248L, 11104L, 100067L, 37600L, 116951L, 51536L, 54432L, 120998L, 46416L, 22176L, 107956L, 9680L, 37584L, 53938L, 43344L, 46423L, 27808L, 46416L, 86869L, 19872L, 42448L, 83315L, 21200L, 43432L, 59728L, 27296L, 44710L, 43856L, 19296L, 43748L, 42352L, 21088L, 62051L, 55632L, 23383L, 22176L, 38608L, 19925L, 19152L, 42192L, 54484L, 53840L, 54616L, 46400L, 46496L, 103846L, 38320L, 18864L, 43380L, 42160L, 45690L, 27216L, 27968L, 44870L, 43872L, 38256L, 19189L, 18800L, 25776L, 29859L, 59984L, 27480L, 21952L, 43872L, 38613L, 37600L, 51552L, 55636L, 54432L, 55888L, 30034L, 22176L, 43959L, 9680L, 37584L, 51893L, 43344L, 46240L, 47780L, 44368L, 21977L, 19360L, 42416L, 86390L, 21168L, 43312L, 31060L, 27296L, 44368L, 23378L, 19296L, 42726L, 42208L, 53856L, 60005L, 54576L, 23200L, 30371L, 38608L, 19415L, 19152L, 42192L, 118966L, 53840L, 54560L, 56645L, 46496L, 22224L, 21938L, 18864L, 42359L, 42160L, 43600L, 111189L, 27936L, 44448L};
      static final int[] solarMonths = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
      static final String[] animals = new String[]{"鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊", "猴", "鸡", "狗", "猪"};
      static final String[] tGan = new String[]{"甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸"};
      static final String[] dZhi = new String[]{"子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌", "亥"};
      static final String[] solarTerms = new String[]{"小寒", "大寒", "立春", "雨水", "惊蛰", "春分", "清明", "谷雨", "立夏", "小满", "芒种", "夏至", "小暑", "大暑", "立秋", "处暑", "白露", "秋分", "寒露", "霜降", "立冬", "小雪", "大雪", "冬至"};
      private static final double D = 0.2422;
      private static final Map INCREASE_OFFSETMAP = new HashMap();
      private static final Map DECREASE_OFFSETMAP = new HashMap();
      private static final double[][] CENTURY_ARRAY;
      static final String[] lunarNumber;
      static final String[] lunarYears;
      static final String[] chineseTen;
      static final String[] lunarHoliday;
      static final String[] solarHoliday;
      static SimpleDateFormat chineseDateFormat;
      static SimpleDateFormat solarDateFormat;

      public String getAnimal() {
         return this.animal;
      }

      public String getGanZhiYear() {
         return this.ganZhiYear;
      }

      public String getLunarYear() {
         return this.lunarYear;
      }

      public String getLunarMonth() {
         return this.lunarMonth;
      }

      public String getLunarDay() {
         return this.lunarDay;
      }

      public String getSolarFestival() {
         return this.solarFestival;
      }

      public String getLunarFestival() {
         return this.lunarFestival;
      }

      public String getLunarTerm() {
         return this.lunarTerm;
      }

      private int lunarYearDays(int y) {
         int sum = 348;

         for(int i = 32768; i > 8; i >>= 1) {
            sum += (lunarInfo[y - 1900] & (long)i) != 0L ? 1 : 0;
         }

         return sum + this.leapDays(y);
      }

      private int leapDays(int y) {
         if (this.leapMonth(y) != 0) {
            return (lunarInfo[y - 1900] & 65536L) != 0L ? 30 : 29;
         } else {
            return 0;
         }
      }

      private int leapMonth(int y) {
         return (int)(lunarInfo[y - 1900] & 15L);
      }

      private int monthDays(int y, int m) {
         return (lunarInfo[y - 1900] & (long)(65536 >> m)) != 0L ? 30 : 29;
      }

      private String getLunarYearString(String year) {
         int y1 = Integer.parseInt(year.charAt(0) + "");
         int y2 = Integer.parseInt(year.charAt(1) + "");
         int y3 = Integer.parseInt(year.charAt(2) + "");
         int y4 = Integer.parseInt(year.charAt(3) + "");
         return lunarYears[y1] + lunarYears[y2] + lunarYears[y3] + lunarYears[y4];
      }

      private String getLunarDayString(int day) {
         int n = day % 10 == 0 ? 9 : day % 10 - 1;
         if (day > 30) {
            return "";
         } else {
            return day == 10 ? "初十" : chineseTen[day / 10] + lunarNumber[n];
         }
      }

      private int specialYearOffset(int year, int n) {
         int offset = 0;
         offset += this.getOffset(DECREASE_OFFSETMAP, year, n, -1);
         offset += this.getOffset(INCREASE_OFFSETMAP, year, n, 1);
         return offset;
      }

      private int getOffset(Map map, int year, int n, int offset) {
         int off = 0;
         Integer[] years = (Integer[])map.get(n);
         if (null != years) {
            Integer[] var7 = years;
            int var8 = years.length;

            for(int var9 = 0; var9 < var8; ++var9) {
               int i = var7[var9];
               if (i == year) {
                  off = offset;
                  break;
               }
            }
         }

         return off;
      }

      private int sTerm(int year, int n) {
         double centuryValue = (double)0.0F;
         int centuryIndex = -1;
         if (year >= 1901 && year <= 2000) {
            centuryIndex = 0;
         } else {
            if (year < 2001 || year > 2100) {
               throw new RuntimeException("不支持此年份：" + year + "，目前只支持1901年到2100年的时间范围");
            }

            centuryIndex = 1;
         }

         centuryValue = CENTURY_ARRAY[centuryIndex][n];
         int dateNum = 0;
         int y = year % 100;
         if ((year % 4 == 0 && year % 100 != 0 || year % 400 == 0) && (n == 0 || n == 1 || n == 2 || n == 3)) {
            --y;
         }

         dateNum = (int)((double)y * 0.2422 + centuryValue) - y / 4;
         dateNum += this.specialYearOffset(year, n);
         return dateNum;
      }

      private String getMotherOrFatherDay(int year, int month, int day) {
         if (month != 5 && month != 6) {
            return null;
         } else if ((month != 5 || day >= 8 && day <= 14) && (month != 6 || day >= 15 && day <= 21)) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month - 1, 1);
            int weekDate = calendar.get(7);
            weekDate = weekDate == 1 ? 7 : weekDate - 1;
            switch (month) {
               case 5:
                  if (day == 15 - weekDate) {
                     return "母亲节";
                  }
                  break;
               case 6:
                  if (day == 22 - weekDate) {
                     return "父亲节";
                  }
            }

            return null;
         } else {
            return null;
         }
      }

      private String thanksgiving(int year, int month, int day) {
         if (month != 11) {
            return null;
         } else if (month != 11 || day >= 19 && day <= 28) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month - 1, 1);
            int weekDate = calendar.get(7);
            weekDate = weekDate == 1 ? 7 : weekDate - 1;
            switch (month) {
               case 11:
                  if (day == 29 - weekDate + 4) {
                     return "感恩节";
                  }
               default:
                  return null;
            }
         } else {
            return null;
         }
      }

      private String getEasterDay(int year, int month, int day) {
         int n = year - 1900;
         int a = n % 19;
         int q = n / 4;
         int b = (7 * a + 1) / 19;
         int m = (11 * a + 4 - b) % 29;
         int w = (n + q + 31 - m) % 7;
         int answer = 25 - m - w;
         String easterDay = "";
         if (answer > 0) {
            easterDay = year + "-" + 4 + "-" + answer;
         } else {
            easterDay = year + "-" + 3 + "-" + (31 + answer);
         }

         String searchDay = year + "-" + month + "-" + day;
         return searchDay.equals(easterDay) ? "复活节" : null;
      }

      public void initLunarCalendarInfo(String currentDate) {
         String[] splitDate = currentDate.split("-");
         int year = Integer.parseInt(splitDate[0]);
         this.animal = animals[(year - 4) % 12];
         int num = year - 1900 + 36;
         this.ganZhiYear = tGan[num % 10] + dZhi[num % 12];
         Date baseDate = null;
         Date nowaday = null;

         try {
            baseDate = chineseDateFormat.parse("1900年1月31日");
            nowaday = solarDateFormat.parse(currentDate);
         } catch (ParseException e) {
            e.printStackTrace();
         }

         int offset = (int)((nowaday.getTime() - baseDate.getTime()) / 86400000L);
         int daysOfYear = 0;

         int iYear;
         for(iYear = 1900; iYear < 10000 && offset > 0; ++iYear) {
            daysOfYear = this.lunarYearDays(iYear);
            offset -= daysOfYear;
         }

         if (offset < 0) {
            offset += daysOfYear;
            --iYear;
         }

         this.lunarYear = this.getLunarYearString(iYear + "");
         int leapMonth = this.leapMonth(iYear);
         boolean leap = false;
         int daysOfMonth = 0;

         int iMonth;
         for(iMonth = 1; iMonth < 13 && offset > 0; ++iMonth) {
            if (leapMonth > 0 && iMonth == leapMonth + 1 && !leap) {
               --iMonth;
               leap = true;
               daysOfMonth = this.leapDays(iYear);
            } else {
               daysOfMonth = this.monthDays(iYear, iMonth);
            }

            offset -= daysOfMonth;
            if (leap && iMonth == leapMonth + 1) {
               leap = false;
            }
         }

         if (offset == 0 && leapMonth > 0 && iMonth == leapMonth + 1) {
            if (leap) {
               leap = false;
            } else {
               leap = true;
               --iMonth;
            }
         }

         if (offset < 0) {
            offset += daysOfMonth;
            --iMonth;
         }

         this.lunarMonth = lunarNumber[iMonth - 1];
         if ("一".equals(this.lunarMonth)) {
            this.lunarMonth = "正";
         }

         if ("十二".equals(this.lunarMonth)) {
            this.lunarMonth = "腊";
         }

         if (leap) {
            this.lunarMonth = "闰" + this.lunarMonth;
         }

         int iDay = offset + 1;
         this.lunarDay = this.getLunarDayString(iDay);
         int month = Integer.parseInt(splitDate[1]);
         int day = Integer.parseInt(splitDate[2]);
         if (day == this.sTerm(year, (month - 1) * 2)) {
            this.lunarTerm = solarTerms[(month - 1) * 2];
         } else if (day == this.sTerm(year, (month - 1) * 2 + 1)) {
            this.lunarTerm = solarTerms[(month - 1) * 2 + 1];
         } else {
            this.lunarTerm = "";
         }

         String solarFestival = "";

         for(int i = 0; i < solarHoliday.length; ++i) {
            String sd = solarHoliday[i].split(" ")[0];
            String sdv = solarHoliday[i].split(" ")[1];
            String smonth_v = splitDate[1];
            String sday_v = splitDate[2];
            String smd = smonth_v + sday_v;
            if (sd.trim().equals(smd.trim())) {
               solarFestival = sdv;
               break;
            }
         }

         String motherOrFatherDay = this.getMotherOrFatherDay(year, month, day);
         if (motherOrFatherDay != null) {
            solarFestival = motherOrFatherDay;
         }

         String easterDay = this.getEasterDay(year, month, day);
         if (easterDay != null) {
            solarFestival = easterDay;
         }

         String thanksgiving = this.thanksgiving(year, month, day);
         if (thanksgiving != null) {
            solarFestival = thanksgiving;
         }

         this.solarFestival = solarFestival;
         String lunarFestival = "";

         for(int i = 0; i < lunarHoliday.length && !leap; ++i) {
            String ld = lunarHoliday[i].split(" ")[0];
            String ldv = lunarHoliday[i].split(" ")[1];
            String lmonth_v = iMonth + "";
            String lday_v = iDay + "";
            String lmd = "";
            if (iMonth < 10) {
               lmonth_v = "0" + iMonth;
            }

            if (iDay < 10) {
               lday_v = "0" + iDay;
            }

            lmd = lmonth_v + lday_v;
            if ("12".equals(lmonth_v) && (daysOfMonth == 29 && iDay == 29 || daysOfMonth == 30 && iDay == 30)) {
               lunarFestival = "除夕";
               break;
            }

            if (ld.trim().equals(lmd.trim())) {
               lunarFestival = ldv;
               break;
            }
         }

         if ("清明".equals(this.lunarTerm)) {
            lunarFestival = "清明节";
         }

         this.lunarFestival = lunarFestival;
      }

      public static void main(String[] args) {
         LunarCalendarFestivalUtils festival = new LunarCalendarFestivalUtils();
         festival.initLunarCalendarInfo("2021-06-25");
         System.out.println("农历" + festival.getLunarYear() + "年" + festival.getLunarMonth() + "月" + festival.getLunarDay() + "日");
         System.out.println(festival.getGanZhiYear() + "【" + festival.getAnimal() + "】年");
         System.out.println(festival.getLunarTerm());
         System.out.println(festival.getSolarFestival());
         System.out.println(festival.getLunarFestival());
      }

      static {
         INCREASE_OFFSETMAP.put(0, new Integer[]{1982});
         DECREASE_OFFSETMAP.put(0, new Integer[]{2019});
         INCREASE_OFFSETMAP.put(1, new Integer[]{2082});
         DECREASE_OFFSETMAP.put(3, new Integer[]{2026});
         INCREASE_OFFSETMAP.put(5, new Integer[]{2084});
         INCREASE_OFFSETMAP.put(9, new Integer[]{2008});
         INCREASE_OFFSETMAP.put(10, new Integer[]{1902});
         INCREASE_OFFSETMAP.put(11, new Integer[]{1928});
         INCREASE_OFFSETMAP.put(12, new Integer[]{1925, 2016});
         INCREASE_OFFSETMAP.put(13, new Integer[]{1922});
         INCREASE_OFFSETMAP.put(14, new Integer[]{2002});
         INCREASE_OFFSETMAP.put(16, new Integer[]{1927});
         INCREASE_OFFSETMAP.put(17, new Integer[]{1942});
         INCREASE_OFFSETMAP.put(19, new Integer[]{2089});
         INCREASE_OFFSETMAP.put(20, new Integer[]{2089});
         INCREASE_OFFSETMAP.put(21, new Integer[]{1978});
         INCREASE_OFFSETMAP.put(22, new Integer[]{1954});
         DECREASE_OFFSETMAP.put(23, new Integer[]{1918, 2021});
         CENTURY_ARRAY = new double[][]{{6.11, 20.84, 4.6295, 19.4599, 6.3826, 21.4155, 5.59, 20.888, 6.318, 21.86, (double)6.5F, 22.2, 7.928, 23.65, 8.35, 23.95, 8.44, 23.822, 9.098, 24.218, 8.218, 23.08, 7.9, 22.6}, {5.4055, 20.12, 3.87, 18.73, 5.63, 20.646, 4.81, 20.1, 5.52, 21.04, 5.678, 21.37, 7.108, 22.83, (double)7.5F, 23.13, 7.646, 23.042, 8.318, 23.438, 7.438, 22.36, 7.18, 21.94}};
         lunarNumber = new String[]{"一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二"};
         lunarYears = new String[]{"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
         chineseTen = new String[]{"初", "十", "廿", "卅"};
         lunarHoliday = new String[]{"0101 春节", "0115 元宵节", "0202 龙头节", "0505 端午节", "0707 七夕节", "0715 中元节", "0815 中秋节", "0909 重阳节", "1001 寒衣节", "1015 下元节", "1208 腊八节", "1223 小年"};
         solarHoliday = new String[]{"0101 元旦", "0214 情人节", "0308 妇女节", "0312 植树节", "0315 消费者权益日", "0401 愚人节", "0422 地球日", "0423 读书日", "0501 劳动节", "0504 青年节", "0512 护士节", "0518 博物馆日", "0519 旅游日", "0601 儿童节", "0701 建党节", "0801 建军节", "0910 教师节", "1001 国庆节", "1024 联合国日", "1204 宪法日", "1224 平安夜", "1225 圣诞节"};
         chineseDateFormat = new SimpleDateFormat("yyyy年MM月dd日", Locale.CHINA);
         solarDateFormat = new SimpleDateFormat("yyyy-MM-dd");
      }
   }
}
