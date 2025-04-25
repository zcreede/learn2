package com.emr.common.utils;

import com.emr.project.docOrder.domain.TdPaOrderItem;
import com.emr.project.docOrder.domain.vo.InpatientOrderDTO;
import java.io.BufferedReader;
import java.io.Reader;
import java.sql.Clob;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonUtils {
   public static String getTenNumberStr(int number) {
      String sortNumberStr = number + "";
      int length = sortNumberStr.length();
      StringBuffer bb = new StringBuffer();

      for(int i = length; i < 2; ++i) {
         bb.append("0");
      }

      return bb.toString() + sortNumberStr;
   }

   public static Map getSameOrder(List inpatientOrderDTOList) {
      Map<String, List<InpatientOrderDTO>> listMap = new HashMap();

      for(InpatientOrderDTO inpatientOrderDTO : inpatientOrderDTOList) {
         if (!inpatientOrderDTO.getOrderClassCode().equals("02") && !inpatientOrderDTO.getOrderClassCode().equals("03")) {
            if (listMap.containsKey(inpatientOrderDTO.getAdmissionNo() + inpatientOrderDTO.getOrderGroupNo())) {
               List<InpatientOrderDTO> inpatientOrderDTOS = (List)listMap.get(inpatientOrderDTO.getAdmissionNo() + inpatientOrderDTO.getOrderGroupNo());
               inpatientOrderDTOS.add(inpatientOrderDTO);
            } else {
               List<InpatientOrderDTO> inpatientOrderDTOS = new ArrayList();
               inpatientOrderDTOS.add(inpatientOrderDTO);
               listMap.put(inpatientOrderDTO.getAdmissionNo() + inpatientOrderDTO.getOrderGroupNo(), inpatientOrderDTOS);
            }
         } else if (listMap.containsKey(inpatientOrderDTO.getAdmissionNo() + inpatientOrderDTO.getOrderNo())) {
            List<InpatientOrderDTO> inpatientOrderDTOS = (List)listMap.get(inpatientOrderDTO.getAdmissionNo() + inpatientOrderDTO.getOrderNo());
            inpatientOrderDTOS.add(inpatientOrderDTO);
         } else {
            List<InpatientOrderDTO> inpatientOrderDTOS = new ArrayList();
            inpatientOrderDTOS.add(inpatientOrderDTO);
            listMap.put(inpatientOrderDTO.getAdmissionNo() + inpatientOrderDTO.getOrderNo(), inpatientOrderDTOS);
         }
      }

      return listMap;
   }

   public static boolean getOrderStatusIsEffective(TdPaOrderItem inpatientOrderItem, int flag, Date nowDate) {
      String inpatientOrderStatus = inpatientOrderItem.getOrderStatus();
      boolean mark = false;
      switch (flag) {
         case 1:
            if ("0".equals(inpatientOrderStatus) || "4".equals(inpatientOrderStatus) || "6".equals(inpatientOrderStatus)) {
               mark = true;
            }
            break;
         case 3:
            long time = 0L;
            Date date = null;
            if ("2".equals(inpatientOrderStatus)) {
               date = inpatientOrderItem.getOrderDealTime();
            }

            if ("4".equals(inpatientOrderStatus)) {
               date = inpatientOrderItem.getOrderStopTime();
            }

            if (date != null) {
               time = date.getTime() - nowDate.getTime();
            }

            if ("0".equals(inpatientOrderStatus) || "4".equals(inpatientOrderStatus) || "6".equals(inpatientOrderStatus) || "11".equals(inpatientOrderStatus) || "1".equals(inpatientOrderStatus) || "3".equals(inpatientOrderStatus) || "2".equals(inpatientOrderStatus) && time < 0L || "4".equals(inpatientOrderStatus) && time > 0L) {
               mark = true;
            }
            break;
         case 7:
            if (!"4".equals(inpatientOrderStatus) && !"5".equals(inpatientOrderStatus)) {
               if ("3".equals(inpatientOrderStatus) || "2".equals(inpatientOrderStatus) || "8".equals(inpatientOrderStatus)) {
                  mark = true;
               }
            } else if (nowDate.before(inpatientOrderItem.getOrderStopTime())) {
               mark = true;
            }
      }

      return mark;
   }

   public static String getSex(String sex) {
      if (sex.isEmpty()) {
         return "";
      } else if ("1".equals(sex)) {
         return "男";
      } else if ("2".equals(sex)) {
         return "女";
      } else if ("0".equals(sex)) {
         return "未知的性别";
      } else if ("9".equals(sex)) {
         return "未说明的性别";
      } else if ("5".equals(sex)) {
         return "女性改（变）为男性";
      } else {
         return "6".equals(sex) ? "男性改（变）为女性" : "未知";
      }
   }

   public static String getOrderNoHandlingMsg(int orderNoHandlingValue) {
      String msg = null;
      switch (orderNoHandlingValue) {
         case -1:
            msg = "医生正在删除此医嘱，请稍后再操作";
            break;
         case 0:
            msg = "医生正在撤销提交此医嘱或护士正在回退此医嘱，请稍后再操作";
            break;
         case 1:
            msg = "医生正在提交此医嘱，请稍后再操作";
            break;
         case 2:
            msg = "护士正在核对此医嘱，请稍后再操作";
            break;
         case 3:
            msg = "护士正在执行此医嘱，请稍后再操作";
            break;
         case 4:
            msg = "医生正在停止此医嘱，请稍后再操作";
            break;
         case 5:
            msg = "医生正在重整此医嘱，请稍后再操作";
            break;
         case 6:
            msg = "医生正在取消此医嘱，请稍后再操作";
            break;
         case 7:
            msg = "护士正在核对此医嘱，请稍后再操作";
         case 8:
         case 9:
         default:
            break;
         case 10:
            msg = "护士正在挂起启用此医嘱，请稍后再操作";
            break;
         case 11:
            msg = "医生正在作废此医嘱，请稍后再操作";
            break;
         case 12:
            msg = "护士正在核对此医嘱，请稍后再操作";
      }

      return msg;
   }

   public static String ClobToString(Clob clob) throws Exception {
      String reString = "";
      Reader is = clob.getCharacterStream();
      BufferedReader br = new BufferedReader(is);
      String s = br.readLine();

      StringBuffer sb;
      for(sb = new StringBuffer(); s != null; s = br.readLine()) {
         sb.append(s);
      }

      reString = sb.toString();
      return reString;
   }

   public static String getContendInfo() {
      Object temp = null;
      String str = null;
      if (null != temp) {
         if (temp instanceof String) {
            return (String)temp;
         }

         if (temp instanceof Clob) {
            Reader inStream = null;
            char[] c = null;
            if (null != temp) {
               try {
                  inStream = ((Clob)temp).getCharacterStream();
                  c = new char[(int)((Clob)temp).length()];
                  inStream.read(c);
               } catch (Exception e) {
                  e.printStackTrace();
               }

               str = new String(c);
            }

            return str;
         }
      }

      return null;
   }
}
