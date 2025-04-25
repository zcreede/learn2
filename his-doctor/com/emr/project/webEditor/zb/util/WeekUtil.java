package com.emr.project.webEditor.zb.util;

import com.emr.common.utils.DateUtils;
import com.emr.common.utils.StringUtils;
import com.emr.project.webEditor.zb.vo.DayVo;
import com.emr.project.webEditor.zb.vo.SevenDayVo;
import com.emr.project.webEditor.zb.vo.WeeklyVo;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WeekUtil {
   protected final Logger log = LoggerFactory.getLogger(WeekUtil.class);

   public static List getWeekList(String startTimeStr, String endTimeStr) throws Exception {
      List<WeeklyVo> list = new ArrayList();
      Date startTime = DateUtils.parseDate(startTimeStr, new String[]{DateUtils.YYYY_MM_DD});
      Date endTime = DateUtils.parseDate(endTimeStr, new String[]{DateUtils.YYYY_MM_DD});
      Date timeTemp = startTime;
      Integer weekly = 1;
      Integer dayNum = 1;
      List<DayVo> dayVoList = new ArrayList(7);

      for(WeeklyVo weeklyVo = new WeeklyVo(); timeTemp.compareTo(endTime) <= 0; timeTemp = DateUtils.addDays(timeTemp, 1)) {
         String timeTempStr = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, timeTemp);
         if (dayNum == 1) {
            weeklyVo.setStartTime(timeTempStr);
         }

         if (dayNum <= 7) {
            DayVo dayVo = new DayVo(timeTempStr, dayNum);
            dayVoList.add(dayVo);
         }

         if (timeTemp.compareTo(endTime) == 0 && dayNum < 7) {
            weeklyVo.setWeekly(weekly);
            weeklyVo.setDayVoList(dayVoList);
            list.add(weeklyVo);
         } else if (dayNum == 7) {
            weeklyVo.setEndTime(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, timeTemp));
            weeklyVo.setWeekly(weekly);
            weeklyVo.setDayVoList(dayVoList);
            list.add(weeklyVo);
            dayNum = 1;
            weekly = weekly + 1;
            dayVoList = new ArrayList(7);
            weeklyVo = new WeeklyVo();
         } else {
            dayNum = dayNum + 1;
         }
      }

      if (!list.isEmpty()) {
         WeeklyVo weeklyVoLast = (WeeklyVo)list.get(list.size() - 1);
         List<DayVo> dayVoListLast = weeklyVoLast.getDayVoList();
         int weeklyVoLastSize = dayVoListLast.size();
         String lastDateStr = ((DayVo)dayVoListLast.get(dayVoListLast.size() - 1)).getDayDate();
         Date lastDate = DateUtils.parseDate(lastDateStr, new String[]{DateUtils.YYYY_MM_DD});

         for(int i = weeklyVoLastSize + 1; i < 8; ++i) {
            Date temp = DateUtils.addDays(lastDate, i - weeklyVoLastSize);
            DayVo dayVo = new DayVo(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, temp), i);
            dayVoListLast.add(dayVo);
         }

         weeklyVoLast.setEndTime(((DayVo)dayVoListLast.get(dayVoListLast.size() - 1)).getDayDate());
      }

      return list;
   }

   public static List getWeek(String startTime, String endTime) {
      List<WeeklyVo> list = new ArrayList();

      try {
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
         long week = 604800000L;
         long day = 86400000L;
         if (StringUtils.isBlank(endTime)) {
            endTime = sdf.format(DateUtils.getNowDate());
         }

         Date sDate = sdf.parse(startTime);
         Date eDate = sdf.parse(endTime);
         Long diff = eDate.getTime() - sDate.getTime();
         if (diff < 0L) {
            return null;
         } else {
            long diffDays = diff / 86400000L + 1L;
            long endWeekly = diffDays / 7L == 0L ? diffDays / 7L : diffDays / 7L + 1L;
            if (diffDays > 0L & diffDays <= 7L) {
               endWeekly = 1L;
               WeeklyVo dateWeekly = new WeeklyVo();
               dateWeekly.setWeekly((new Long(endWeekly)).intValue());
               dateWeekly.setStartTime(sdf.format(sDate));
               dateWeekly.setEndTime(sdf.format(eDate));
               list.add(dateWeekly);
            } else {
               for(int i = 1; (long)i <= endWeekly; ++i) {
                  WeeklyVo dateWeekly = new WeeklyVo();
                  String eDay = null;
                  long s = (long)i * week;
                  long e = (long)(i - 1) * week;
                  long sdt = e + sDate.getTime();
                  String sDay = sdf.format(sdt);
                  if (i == (new Long(endWeekly)).intValue()) {
                     eDay = sdf.format(eDate);
                  } else {
                     long edt = s + sDate.getTime() - day;
                     eDay = sdf.format(edt);
                  }

                  dateWeekly.setWeekly(i);
                  dateWeekly.setStartTime(sDay);
                  dateWeekly.setEndTime(eDay);
                  list.add(i - 1, dateWeekly);
               }
            }

            return list;
         }
      } catch (Exception e) {
         e.printStackTrace();
         return list;
      }
   }

   public static List getDays(WeeklyVo weeklyVo) {
      String startTime = weeklyVo.getStartTime();
      String endTime = weeklyVo.getEndTime();
      List<DayVo> dayVoList = new ArrayList();
      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

      try {
         Date start = dateFormat.parse(startTime);
         Date end = dateFormat.parse(endTime);
         Calendar tempStart = Calendar.getInstance();
         tempStart.setTime(start);
         Calendar tempEnd = Calendar.getInstance();
         tempEnd.setTime(end);
         tempEnd.add(5, 1);
         int i = 0;

         while(tempStart.before(tempEnd)) {
            DayVo dayVo = new DayVo();
            ++i;
            dayVo.setDayDate(dateFormat.format(tempStart.getTime()));
            dayVo.setDayNum((weeklyVo.getWeekly() - 1) * 7 + i);
            dayVoList.add(dayVo);
            tempStart.add(6, 1);
         }
      } catch (ParseException e) {
         e.printStackTrace();
      }

      return dayVoList;
   }

   public static SevenDayVo getSevenDay(String startTime, String endTime) {
      SevenDayVo sevenDayVo = new SevenDayVo();
      List<DayVo> dayVoList = new ArrayList();
      new ArrayList();
      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

      try {
         Date start = dateFormat.parse(startTime);
         Date end = dateFormat.parse(endTime);
         Calendar tempStart = Calendar.getInstance();
         tempStart.setTime(start);
         Calendar tempEnd = Calendar.getInstance();
         tempEnd.setTime(end);
         tempEnd.add(5, 1);
         int i = 0;

         while(tempStart.before(tempEnd)) {
            DayVo dayVo = new DayVo();
            ++i;
            dayVo.setDayDate(dateFormat.format(tempStart.getTime()));
            dayVo.setDayNum(i);
            dayVoList.add(dayVo);
            tempStart.add(6, 1);
         }

         List dayVos = dayVoList.subList(Math.max(dayVoList.size() - 7, 0), dayVoList.size());
         sevenDayVo.setStartTime(((DayVo)dayVos.get(0)).getDayDate());
         sevenDayVo.setEndTime(((DayVo)dayVos.get(dayVos.size() - 1)).getDayDate());
         sevenDayVo.setDayVoList(dayVos);
      } catch (ParseException e) {
         e.printStackTrace();
      }

      return sevenDayVo;
   }
}
