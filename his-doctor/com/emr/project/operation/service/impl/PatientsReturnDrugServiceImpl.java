package com.emr.project.operation.service.impl;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.StringUtils;
import com.emr.project.operation.domain.TakeDrugReturn;
import com.emr.project.operation.domain.req.ReturnApplyPageReq;
import com.emr.project.operation.domain.req.UnTakeDrugReq;
import com.emr.project.operation.domain.resp.UnTakeDrugResp;
import com.emr.project.operation.mapper.PatientsReturnDrugMapper;
import com.emr.project.operation.mapper.TakeDrugReturnMapper;
import com.emr.project.operation.service.IPatientsReturnDrugService;
import com.emr.project.system.domain.SysUser;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientsReturnDrugServiceImpl implements IPatientsReturnDrugService {
   @Autowired
   private PatientsReturnDrugMapper patientsReturnDrugMapper;
   @Autowired
   private TakeDrugReturnMapper takeDrugReturnMapper;

   public List unTakeDrugList(UnTakeDrugReq req) {
      String admissionNo = req.getAdmissionNo();
      String deptCode = req.getDeptCode();
      String startTime = req.getStartTime();
      if (StringUtils.isNotEmpty(startTime)) {
         startTime = startTime + " 00:00:00";
      }

      String endTime = req.getEndTime();
      if (StringUtils.isNotEmpty(endTime)) {
         endTime = endTime + " 23:59:59";
      }

      String drugName = req.getDrugName();
      SysUser user = SecurityUtils.getLoginUser().getUser();
      String deptCode1 = user.getDept().getDeptCode();
      List<UnTakeDrugResp> list = this.patientsReturnDrugMapper.unTakeDrugList(admissionNo, deptCode, startTime, endTime, drugName, deptCode1);
      List<UnTakeDrugResp> resultList = new ArrayList();
      Map<String, List<UnTakeDrugResp>> listMap = (Map)list.stream().collect(Collectors.groupingBy((s) -> s.getOrderNo() + "_" + s.getOrderSortNumber() + "_" + s.getOrderGroupNo()));

      for(String key : listMap.keySet()) {
         List<UnTakeDrugResp> drugRespList = (List)listMap.get(key);
         if (drugRespList != null && drugRespList.size() > 0) {
            if (drugRespList.size() == 1) {
               UnTakeDrugResp unTakeDrugResp = (UnTakeDrugResp)drugRespList.get(0);
               unTakeDrugResp.setOrderTypeStr("1".equals(unTakeDrugResp.getOrderType()) ? "长期" : "临时");
               unTakeDrugResp.setPrice((new BigDecimal(unTakeDrugResp.getPrice())).stripTrailingZeros().toPlainString());
               if (StringUtils.isNotEmpty(unTakeDrugResp.getTotal())) {
                  unTakeDrugResp.setTotal((new BigDecimal(unTakeDrugResp.getTotal())).stripTrailingZeros().toPlainString());
               } else {
                  BigDecimal total = (new BigDecimal(unTakeDrugResp.getPrice())).multiply(new BigDecimal(unTakeDrugResp.getOrderDose()));
                  unTakeDrugResp.setTotal(total.stripTrailingZeros().toPlainString());
               }

               String takeDrugStatus = unTakeDrugResp.getTakeDrugStatus();
               if (StringUtils.isNotEmpty(takeDrugStatus)) {
                  if ("1".equals(takeDrugStatus)) {
                     unTakeDrugResp.setStatus("未领药");
                  } else if ("2".equals(takeDrugStatus)) {
                     unTakeDrugResp.setStatus("欠费");
                  } else if ("3".equals(takeDrugStatus)) {
                     unTakeDrugResp.setStatus("病房库存不足");
                  } else if ("4".equals(takeDrugStatus)) {
                     unTakeDrugResp.setStatus("退药申请");
                  } else if ("5".equals(takeDrugStatus)) {
                     unTakeDrugResp.setStatus("退药");
                  } else if ("10".equals(takeDrugStatus)) {
                     unTakeDrugResp.setStatus("药房库存不足");
                  } else if ("11".equals(takeDrugStatus)) {
                     unTakeDrugResp.setStatus("药房欠费");
                  } else if ("0".equals(takeDrugStatus)) {
                     unTakeDrugResp.setStatus("未发送");
                  }
               }

               resultList.add(unTakeDrugResp);
            } else {
               for(int i = 0; i < drugRespList.size(); ++i) {
                  if (i == 0) {
                     ((UnTakeDrugResp)drugRespList.get(i)).setIcon("┓");
                  } else if (i == drugRespList.size() - 1) {
                     ((UnTakeDrugResp)drugRespList.get(i)).setIcon("┛");
                  } else {
                     ((UnTakeDrugResp)drugRespList.get(i)).setIcon("┃");
                  }

                  ((UnTakeDrugResp)drugRespList.get(i)).setOrderTypeStr("1".equals(((UnTakeDrugResp)drugRespList.get(i)).getOrderType()) ? "长期" : "临时");
                  ((UnTakeDrugResp)drugRespList.get(i)).setPrice((new BigDecimal(((UnTakeDrugResp)drugRespList.get(i)).getPrice())).stripTrailingZeros().toPlainString());
                  if (StringUtils.isNotEmpty(((UnTakeDrugResp)drugRespList.get(i)).getTotal())) {
                     ((UnTakeDrugResp)drugRespList.get(i)).setTotal((new BigDecimal(((UnTakeDrugResp)drugRespList.get(i)).getTotal())).stripTrailingZeros().toPlainString());
                  } else {
                     BigDecimal total = (new BigDecimal(((UnTakeDrugResp)drugRespList.get(i)).getPrice())).multiply(new BigDecimal(((UnTakeDrugResp)drugRespList.get(i)).getOrderDose()));
                     ((UnTakeDrugResp)drugRespList.get(i)).setTotal(total.stripTrailingZeros().toPlainString());
                  }

                  String takeDrugStatus = ((UnTakeDrugResp)drugRespList.get(i)).getTakeDrugStatus();
                  if (StringUtils.isNotEmpty(takeDrugStatus)) {
                     if ("1".equals(takeDrugStatus)) {
                        ((UnTakeDrugResp)drugRespList.get(i)).setStatus("未领药");
                     } else if ("2".equals(takeDrugStatus)) {
                        ((UnTakeDrugResp)drugRespList.get(i)).setStatus("欠费");
                     } else if ("3".equals(takeDrugStatus)) {
                        ((UnTakeDrugResp)drugRespList.get(i)).setStatus("病房库存不足");
                     } else if ("4".equals(takeDrugStatus)) {
                        ((UnTakeDrugResp)drugRespList.get(i)).setStatus("退药申请");
                     } else if ("5".equals(takeDrugStatus)) {
                        ((UnTakeDrugResp)drugRespList.get(i)).setStatus("退药");
                     } else if ("10".equals(takeDrugStatus)) {
                        ((UnTakeDrugResp)drugRespList.get(i)).setStatus("药房库存不足");
                     } else if ("11".equals(takeDrugStatus)) {
                        ((UnTakeDrugResp)drugRespList.get(i)).setStatus("药房欠费");
                     } else if ("0".equals(takeDrugStatus)) {
                        ((UnTakeDrugResp)drugRespList.get(i)).setStatus("未发送");
                     }
                  }

                  resultList.add(drugRespList.get(i));
               }
            }
         }
      }

      return resultList;
   }

   public List queryPageList(ReturnApplyPageReq req) {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      String deptCode = user.getDept().getDeptCode();
      String searchStr = req.getSearchStr();
      String startTime = req.getStartTime();
      if (StringUtils.isNotEmpty(startTime)) {
         startTime = startTime + " 00:00:00";
      }

      String endTime = req.getEndTime();
      if (StringUtils.isNotEmpty(endTime)) {
         endTime = endTime + " 23:59:59";
      }

      List<TakeDrugReturn> list = this.takeDrugReturnMapper.selectPageList(searchStr, startTime, endTime, deptCode);

      for(TakeDrugReturn takeDrugReturn : list) {
         takeDrugReturn.setOrderDose(takeDrugReturn.getDose());
         Date operatorDate = takeDrugReturn.getOperatorDate();
         if (operatorDate != null) {
            takeDrugReturn.setOperatorDateTime(DateFormatUtils.format(operatorDate, "yyyy-MM-dd HH:mm"));
         }

         Date operatorNewDate = takeDrugReturn.getOperatorNewDate();
         if (operatorNewDate != null) {
            takeDrugReturn.setOperatorNewDateTime(DateFormatUtils.format(operatorNewDate, "yyyy-MM-dd HH:mm"));
         }

         String orderTypeStr = takeDrugReturn.getOrderTypeStr();
         if (StringUtils.isNotEmpty(orderTypeStr)) {
            takeDrugReturn.setOrderTypeStr("1".equals(orderTypeStr) ? "长期" : "临时");
         }
      }

      return list;
   }
}
