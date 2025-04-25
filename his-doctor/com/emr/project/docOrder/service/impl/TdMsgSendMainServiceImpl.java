package com.emr.project.docOrder.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.emr.common.utils.DateUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.common.utils.http.HttpUtils;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.project.common.service.ICommonService;
import com.emr.project.docOrder.domain.TdMsgSendMain;
import com.emr.project.docOrder.domain.TdMsgSendReceiver;
import com.emr.project.docOrder.domain.TdPaOrder;
import com.emr.project.docOrder.domain.req.OperMsgsReq;
import com.emr.project.docOrder.domain.req.RevokeMsgReq;
import com.emr.project.docOrder.domain.req.TdMsgSendMainAddReq;
import com.emr.project.docOrder.service.ITdMsgSendMainService;
import com.emr.project.pat.domain.Visitinfo;
import com.emr.project.system.domain.SysUser;
import com.emr.project.webservice.utils.SignUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TdMsgSendMainServiceImpl implements ITdMsgSendMainService {
   protected final Logger log = LoggerFactory.getLogger(this.getClass());
   @Autowired
   private ICommonService commonService;
   @Value("${msgm.url}")
   private String msgmUrl;

   public void addMsgs(List orderList, Visitinfo visitinfo, int operationType, String operationTypeName) throws Exception {
      List<TdMsgSendMain> sendMainList = new ArrayList(1);
      String msgContent = this.getMsgContent(visitinfo, operationType, operationTypeName);

      for(TdPaOrder order : orderList) {
         TdMsgSendMain sendMain = new TdMsgSendMain();
         sendMain.setId(SnowIdUtils.uniqueLong());
         sendMain.setOrgCd(order.getHospitalCode());
         sendMain.setSendAppId("HIS");
         sendMain.setMsgId("0001");
         sendMain.setMsgType("3");
         sendMain.setVisitType("zy");
         sendMain.setVisitNo(order.getAdmissionNo());
         sendMain.setBusId(order.getOrderNo());
         sendMain.setMesContent(msgContent);
         Date currDate = this.commonService.getDbSysdate();
         Date ExecDate = DateUtils.addHours(currDate, 24);
         sendMain.setExecDate(ExecDate);
         sendMain.setStatus("0");
         sendMain.setCreDeptCd(order.getOrderDoctorDepCode());
         sendMain.setCreDeptName(order.getOrderDoctorDepName());
         sendMain.setCrePerCode(order.getOrderDoctorCode());
         TdMsgSendReceiver sendReceiver = new TdMsgSendReceiver();
         sendReceiver.setId(SnowIdUtils.uniqueLong());
         sendReceiver.setSendId(sendMain.getId());
         sendReceiver.setRecStaffType("d");
         String recDeptCd = order.getOrderPerformFlag().equals("3") ? order.getPerformDepCode() : order.getPatientDepCode();
         sendReceiver.setRecDeptCd(recDeptCd);
         sendReceiver.setStatus("0");
         List<TdMsgSendReceiver> receiverList = new ArrayList(1);
         receiverList.add(sendReceiver);
         String jsonObjectStr = JSONObject.toJSONString(receiverList);
         sendMain.setReceiveCode(jsonObjectStr);
         sendMainList.add(sendMain);
      }

      TdMsgSendMainAddReq req = new TdMsgSendMainAddReq();
      req.setMainList(sendMainList);
      String sign = SignUtil.encryptString((HashMap)JSONObject.parseObject(JSONObject.toJSONString(req), HashMap.class), "2361BC388A854C72939E73BD7217AFA0");
      req.setSign(sign);
      String paramStr = JSONObject.toJSONString(req);
      String resStr = HttpUtils.sendPost(this.msgmUrl + "/send/main/addMsgs", paramStr, "application/json");
      AjaxResult res = (AjaxResult)JSONObject.parseObject(resStr, AjaxResult.class);
      if (!res.get("code").equals(200)) {
         this.log.error("==========发送医嘱相关消息到消息平台失败===========" + res.get("msg"));
      }

   }

   public void operMsgs(List orderNoList, SysUser user) throws Exception {
      OperMsgsReq req = new OperMsgsReq();
      req.setThirdFlag("2");
      req.setBusIdList(orderNoList);
      req.setStatus("4");
      req.setExecUserCd(user.getUserName());
      req.setExecUserName(user.getNickName());
      String sign = SignUtil.encryptString((HashMap)JSONObject.parseObject(JSONObject.toJSONString(req), HashMap.class), "2361BC388A854C72939E73BD7217AFA0");
      req.setSign(sign);
      String paramStr = JSONObject.toJSONString(req);
      String resStr = HttpUtils.sendPost(this.msgmUrl + "/send/main/operMsgsByBus", paramStr, "application/json");
      AjaxResult res = (AjaxResult)JSONObject.parseObject(resStr, AjaxResult.class);
      if (!res.get("code").equals(200)) {
         this.log.error("==========处理医嘱相关消息到消息平台失败===========" + res.get("msg") + "");
      }

   }

   public void revokeMsg(List orderNoList, SysUser user) throws Exception {
      RevokeMsgReq req = new RevokeMsgReq();
      req.setThirdFlag("2");
      req.setBusIdList(orderNoList);
      req.setCancelPerCode(user.getUserName());
      req.setCancelPerName(user.getNickName());
      String sign = SignUtil.encryptString((HashMap)JSONObject.parseObject(JSONObject.toJSONString(req), HashMap.class), "2361BC388A854C72939E73BD7217AFA0");
      req.setSign(sign);
      String paramStr = JSONObject.toJSONString(req);
      String resStr = HttpUtils.sendPost(this.msgmUrl + "/send/main/revokeMsgs", paramStr, "application/json");
      AjaxResult res = (AjaxResult)JSONObject.parseObject(resStr, AjaxResult.class);
      if (!res.get("code").equals(200)) {
         this.log.error("==========处理医嘱相关消息到消息平台失败===========" + res.get("msg") + "");
      }

   }

   private String getMsgContent(Visitinfo visitinfo, int operationType, String operationTypeName) {
      StringBuffer msgContent = new StringBuffer("[");
      msgContent.append(visitinfo.getBedNo());
      msgContent.append(",");
      msgContent.append(visitinfo.getPatientName());
      msgContent.append("]");
      msgContent.append("有一条");
      msgContent.append(operationType == 0 ? "新开" : operationTypeName);
      msgContent.append("的医嘱");
      return msgContent.toString();
   }
}
