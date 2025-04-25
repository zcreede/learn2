package com.emr.project.docOrder.service.impl;

import com.emr.common.utils.DateUtils;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.project.common.service.ICommonService;
import com.emr.project.docOrder.domain.TdPaOrderItem;
import com.emr.project.docOrder.domain.TdPaOrderSignDetail;
import com.emr.project.docOrder.domain.TdPaOrderSignMain;
import com.emr.project.docOrder.domain.req.OrderSignTextReq;
import com.emr.project.docOrder.domain.resp.OrderSignTextOrderResp;
import com.emr.project.docOrder.domain.resp.OrderSignTextResp;
import com.emr.project.docOrder.domain.vo.OrderCommitVo;
import com.emr.project.docOrder.domain.vo.OrderSignCommitVo;
import com.emr.project.docOrder.domain.vo.OrderSignPicVo;
import com.emr.project.docOrder.domain.vo.OrderSignTextHandleVo;
import com.emr.project.docOrder.domain.vo.OrderSignTextVo;
import com.emr.project.docOrder.domain.vo.OrderStopSignVo;
import com.emr.project.docOrder.mapper.TdPaOrderSignMainMapper;
import com.emr.project.docOrder.service.ITdPaOrderSignDetailService;
import com.emr.project.docOrder.service.ITdPaOrderSignMainService;
import com.emr.project.pat.domain.Visitinfo;
import com.emr.project.system.domain.BasEmployee;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TdPaOrderSignMainServiceImpl implements ITdPaOrderSignMainService {
   @Autowired
   private TdPaOrderSignMainMapper tdPaOrderSignMainMapper;
   @Autowired
   private ICommonService commonService;
   @Autowired
   private ITdPaOrderSignDetailService tdPaOrderSignDetailService;

   public TdPaOrderSignMain selectTdPaOrderSignMainById(Long id) {
      return this.tdPaOrderSignMainMapper.selectTdPaOrderSignMainById(id);
   }

   public List selectTdPaOrderSignMainList(TdPaOrderSignMain tdPaOrderSignMain) {
      return this.tdPaOrderSignMainMapper.selectTdPaOrderSignMainList(tdPaOrderSignMain);
   }

   public int insertTdPaOrderSignMain(TdPaOrderSignMain tdPaOrderSignMain) {
      return this.tdPaOrderSignMainMapper.insertTdPaOrderSignMain(tdPaOrderSignMain);
   }

   public int updateTdPaOrderSignMain(TdPaOrderSignMain tdPaOrderSignMain) {
      return this.tdPaOrderSignMainMapper.updateTdPaOrderSignMain(tdPaOrderSignMain);
   }

   public int deleteTdPaOrderSignMainByIds(Long[] ids) {
      return this.tdPaOrderSignMainMapper.deleteTdPaOrderSignMainByIds(ids);
   }

   public int deleteTdPaOrderSignMainById(Long id) {
      return this.tdPaOrderSignMainMapper.deleteTdPaOrderSignMainById(id);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void addTdPaOrderOperationSign(List orderItemList, OrderCommitVo orderCommitVo, Visitinfo visitinfo, Integer operationType, String operationTypeName) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      TdPaOrderSignMain tdPaOrderSignMain = new TdPaOrderSignMain();
      tdPaOrderSignMain.setId(SnowIdUtils.uniqueLong());
      tdPaOrderSignMain.setSignCertificate(orderCommitVo.getSignCertificate());
      String encryptionType = "";
      tdPaOrderSignMain.setEncryptionType(encryptionType);
      String signMode = "";
      switch (operationType) {
         case 0:
            signMode = "住院号,医嘱编码,医嘱序号,序号,医嘱类型,医嘱类别,开嘱科室,开嘱医师,医嘱开始时间,医嘱项目编码,医嘱项目名称,规格,每次用量,用量单位,频率,用法,数量,数量单位,首日次数,特殊标志,草药剂数,医嘱说明,医师说明,部位,标本,医嘱提交医师,提交时间;";
            break;
         case 4:
            signMode = "住院号,医嘱编码,医嘱序号,序号,医嘱类型,医嘱类别,开嘱科室,开嘱医师,医嘱开始时间,医嘱项目编码,医嘱项目名称,规格,每次用量,用量单位,频率,用法,数量,数量单位,首日次数,特殊标志,草药剂数,医嘱说明,医师说明,部位,标本,医嘱提交医师,提交时间,停止医师,停止时间;";
            break;
         case 6:
            signMode = "住院号,医嘱编码,医嘱序号,序号,医嘱类型,医嘱类别,开嘱科室,开嘱医师,医嘱开始时间,医嘱项目编码,医嘱项目名称,规格,每次用量,用量单位,频率,用法,数量,数量单位,首日次数,特殊标志,草药剂数,医嘱说明,医师说明,部位,标本,医嘱提交医师,提交时间,停止医师,停止时间,取消医师,取消时间;";
      }

      tdPaOrderSignMain.setSignMode(signMode);
      tdPaOrderSignMain.setSignedText(orderCommitVo.getSignedText());
      tdPaOrderSignMain.setEncryptionInfo(orderCommitVo.getEncryptedInfo());
      tdPaOrderSignMain.setSignDesc(orderItemList.size() > 1 ? "打包签名" : "单条签名");
      tdPaOrderSignMain.setOperationType(operationType + "");
      tdPaOrderSignMain.setOperationName(operationTypeName);
      tdPaOrderSignMain.setSignTime(this.commonService.getDbSysdate());
      tdPaOrderSignMain.setOperatorCode(basEmployee.getEmplNumber());
      tdPaOrderSignMain.setOperatorName(basEmployee.getEmplName());
      List<TdPaOrderSignDetail> signDetailList = new ArrayList(orderItemList.size());

      for(TdPaOrderItem orderItem : orderItemList) {
         TdPaOrderSignDetail signDetail = new TdPaOrderSignDetail();
         signDetail.setId(SnowIdUtils.uniqueLong());
         signDetail.setAdmissionNo(visitinfo.getInpNo());
         signDetail.setPatientId(visitinfo.getPatientId());
         signDetail.setSignId(tdPaOrderSignMain.getId());
         signDetail.setOrderNo(orderItem.getOrderNo());
         signDetail.setOrderGroupNo(orderItem.getOrderGroupNo());
         signDetail.setOrderSortNumber(orderItem.getOrderSortNumber());
         signDetailList.add(signDetail);
      }

      this.tdPaOrderSignMainMapper.insertTdPaOrderSignMain(tdPaOrderSignMain);
      if (!signDetailList.isEmpty()) {
         this.tdPaOrderSignDetailService.insertList(signDetailList);
      }

   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void addTdPaOrderOperationSign(List orderItemList, OrderStopSignVo orderStopSignVo, Visitinfo visitinfo, Integer operationType, String operationTypeName) throws Exception {
      OrderCommitVo orderCommitVo = new OrderCommitVo();
      orderCommitVo.setPatientId(orderStopSignVo.getPatientId());
      orderCommitVo.setEncryptedInfo(orderStopSignVo.getEncryptedInfo());
      orderCommitVo.setSignCertificate(orderStopSignVo.getSignCertificate());
      orderCommitVo.setSignedText(orderStopSignVo.getSignedText());
      orderCommitVo.setSjc(orderStopSignVo.getSjc());
      this.addTdPaOrderOperationSign(orderItemList, orderCommitVo, visitinfo, operationType, operationTypeName);
   }

   public List selectOrderSignTextList(List orderNoList) throws Exception {
      List<OrderSignTextVo> list = new ArrayList(1);
      if (orderNoList != null && !orderNoList.isEmpty()) {
         list = this.tdPaOrderSignMainMapper.selectOrderSignTextList(orderNoList);
      }

      return list;
   }

   public List selectSignPicByOrderNos(List orderNoList, List operationTypeList) throws Exception {
      List<OrderSignPicVo> list = new ArrayList(1);
      if (orderNoList != null && !orderNoList.isEmpty()) {
         list = this.tdPaOrderSignMainMapper.selectSignPicByOrderNos(orderNoList, operationTypeList);
      }

      return list;
   }

   public List selectStaffSignPicAll() throws Exception {
      new ArrayList(1);
      List list = this.tdPaOrderSignMainMapper.selectStaffSignPicAll();
      return list;
   }

   public List queryOrderSignText(OrderSignTextReq req) throws Exception {
      List<OrderSignTextResp> list = new ArrayList();
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      String signType = req.getSignType();
      List<String> orderNoList = req.getOrderNoList();
      Date dbTime = this.commonService.getDbSysdate();
      List<OrderSignTextHandleVo> orderList = this.tdPaOrderSignMainMapper.selectOrderItemList(orderNoList);
      if (orderList != null && orderList.size() > 0) {
         Map<String, List<OrderSignTextHandleVo>> admissionNoListMap = (Map)orderList.stream().collect(Collectors.groupingBy(OrderSignTextHandleVo::getAdmissionNo));

         for(String key : admissionNoListMap.keySet()) {
            List<OrderSignTextHandleVo> textVos = (List)admissionNoListMap.get(key);
            StringBuffer signTextBuffer = new StringBuffer();
            OrderSignTextResp resp = new OrderSignTextResp();
            List<OrderSignTextOrderResp> orderSignList = new ArrayList();

            for(OrderSignTextHandleVo vo : textVos) {
               OrderSignTextOrderResp orderResp = new OrderSignTextOrderResp();
               BeanUtils.copyProperties(vo, orderResp);
               signTextBuffer.append(vo.getChargeName()).append(",");
               switch (signType) {
                  case "1":
                     signTextBuffer.append(vo.getSubmitDoctorNo()).append(",").append(vo.getSubmitTime()).append(",");
                     signTextBuffer.append(basEmployee.getEmplNumber()).append(",").append(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, dbTime)).append(";");
                     break;
                  case "2":
                     signTextBuffer.append(vo.getSubmitDoctorNo()).append(",").append(vo.getSubmitTime()).append(",");
                     signTextBuffer.append(vo.getAuditDoctorNo()).append(",").append(vo.getAuditTime()).append(",");
                     signTextBuffer.append(basEmployee.getEmplNumber()).append(",").append(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, dbTime)).append(";");
               }

               orderSignList.add(orderResp);
            }

            resp.setAdmissionNo(key);
            resp.setSignedText(signTextBuffer.toString());
            resp.setCurrTime(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, dbTime));
            resp.setOrderNoList(orderSignList);
            list.add(resp);
         }
      }

      return list;
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void addTdPaOrderOperationSignText(List orderSignCommitVoList, String signType) throws Exception {
      Date dbTime = this.commonService.getDbSysdate();
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      List<TdPaOrderSignDetail> signDetailList = new ArrayList();
      List<TdPaOrderSignMain> signMainList = new ArrayList();

      for(OrderSignCommitVo orderSignCommitVo : orderSignCommitVoList) {
         TdPaOrderSignMain tdPaOrderSignMain = new TdPaOrderSignMain();
         tdPaOrderSignMain.setId(SnowIdUtils.uniqueLong());
         tdPaOrderSignMain.setSignCertificate(orderSignCommitVo.getSignCertificate());
         String encryptionType = "";
         tdPaOrderSignMain.setEncryptionType(encryptionType);
         String signMode = "";
         String operationType = "";
         String operationTypeName = "";
         switch (signType) {
            case "1":
               signMode = "住院号,医嘱编码,医嘱序号,序号,医嘱类型,医嘱类别,开嘱科室,开嘱医师,医嘱开始时间,医嘱项目编码,医嘱项目名称,规格,每次用量,用量单位,频率,用法,数量,数量单位,首日次数,特殊标志,草药剂数,医嘱说明,医师说明,部位,标本,医嘱提交医师,提交时间;";
               operationType = "1";
               operationTypeName = "审核";
               break;
            case "2":
               signMode = "住院号,医嘱编码,医嘱序号,序号,医嘱类型,医嘱类别,开嘱科室,开嘱医师,医嘱开始时间,医嘱项目编码,医嘱项目名称,规格,每次用量,用量单位,频率,用法,数量,数量单位,首日次数,特殊标志,草药剂数,医嘱说明,医师说明,部位,标本,医嘱提交医师,提交时间,审核人,审核时间;";
               operationType = "3";
               operationTypeName = "执行";
         }

         tdPaOrderSignMain.setSignMode(signMode);
         tdPaOrderSignMain.setSignedText(orderSignCommitVo.getSignedText());
         tdPaOrderSignMain.setEncryptionInfo(orderSignCommitVo.getEncryptedInfo());
         tdPaOrderSignMain.setSignDesc("打包签名");
         tdPaOrderSignMain.setOperationType(operationType + "");
         tdPaOrderSignMain.setOperationName(operationTypeName);
         tdPaOrderSignMain.setSignTime(dbTime);
         tdPaOrderSignMain.setOperatorCode(basEmployee.getEmplNumber());
         tdPaOrderSignMain.setOperatorName(basEmployee.getEmplName());
         signMainList.add(tdPaOrderSignMain);

         for(OrderSignTextOrderResp orderItem : orderSignCommitVo.getOrderNoList()) {
            TdPaOrderSignDetail signDetail = new TdPaOrderSignDetail();
            signDetail.setId(SnowIdUtils.uniqueLong());
            signDetail.setAdmissionNo(orderSignCommitVo.getAdmissionNo());
            signDetail.setPatientId(orderSignCommitVo.getAdmissionNo());
            signDetail.setSignId(tdPaOrderSignMain.getId());
            signDetail.setOrderNo(orderItem.getOrderNo());
            signDetail.setOrderGroupNo(orderItem.getOrderGroupNo());
            signDetail.setOrderSortNumber(orderItem.getOrderSortNumber());
            signDetailList.add(signDetail);
         }
      }

      if (!signMainList.isEmpty()) {
         this.tdPaOrderSignMainMapper.insertList(signMainList);
      }

      if (!signDetailList.isEmpty()) {
         this.tdPaOrderSignDetailService.insertList(signDetailList);
      }

   }
}
