package com.emr.project.operation.service.impl;

import com.emr.common.utils.DateUtils;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.StringUtils;
import com.emr.project.docOrder.domain.TdCaOperationApply;
import com.emr.project.docOrder.mapper.TdCaOperationApplyMapper;
import com.emr.project.operation.domain.TdCaOperationRoom;
import com.emr.project.operation.domain.req.OperPlanDetailReq;
import com.emr.project.operation.domain.req.OperPlanListReq;
import com.emr.project.operation.domain.req.OperPlanRejectReq;
import com.emr.project.operation.domain.req.OperationToBePlanReq;
import com.emr.project.operation.domain.req.RoomListReq;
import com.emr.project.operation.domain.resp.OperPlanCountResp;
import com.emr.project.operation.domain.resp.OperRoomInfoResp;
import com.emr.project.operation.mapper.OperationPlanMapper;
import com.emr.project.operation.mapper.TdCaOperationRoomMapper;
import com.emr.project.operation.service.OperationPlanService;
import com.emr.project.system.domain.SysUser;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperationPlanServiceImpl implements OperationPlanService {
   @Autowired
   private OperationPlanMapper operationPlanMapper;
   @Autowired
   private TdCaOperationApplyMapper tdCaOperationApplyMapper;
   @Autowired
   private TdCaOperationRoomMapper tdCaOperationRoomMapper;

   public List queryPlanList(OperationToBePlanReq req) throws Exception {
      List<TdCaOperationApply> listNew = new ArrayList(1);
      BigDecimal amount = BigDecimal.ZERO;
      List<TdCaOperationApply> tdCaOperationApplies = this.operationPlanMapper.queryPlanList(req);
      if (StringUtils.isNotBlank(req.getStatistType()) && req.getStatistType().equals("2")) {
         this.getGroupRoom(tdCaOperationApplies, listNew);
         return listNew;
      } else if (StringUtils.isNotBlank(req.getStatistType()) && req.getStatistType().equals("3")) {
         this.getGroupDepart(tdCaOperationApplies, listNew);
         return listNew;
      } else {
         return tdCaOperationApplies;
      }
   }

   private BigDecimal getGroupRoom(List list, List listNew) {
      BigDecimal amount = BigDecimal.ZERO;
      if (CollectionUtils.isNotEmpty(list)) {
         String operRoom = null;
         BigDecimal bd = new BigDecimal("1");
         BigDecimal total = BigDecimal.ZERO;

         for(int i = 0; i < list.size(); ++i) {
            TdCaOperationApply vo = (TdCaOperationApply)list.get(i);
            if (StringUtils.isNull(operRoom)) {
               operRoom = vo.getOperRoom();
            } else if (StringUtils.isNotBlank(operRoom) && !operRoom.equals(vo.getOperRoom())) {
               TdCaOperationApply voNew = new TdCaOperationApply();
               voNew.setOperRoom(operRoom + "手术间小计");
               voNew.setOperTable(total + "台手术");
               listNew.add(voNew);
               operRoom = vo.getOperRoom();
               total = BigDecimal.ZERO;
            }

            total = total.add(bd);
            listNew.add(vo);
            if (i == list.size() - 1) {
               TdCaOperationApply voNew = new TdCaOperationApply();
               voNew.setOperRoom(operRoom + "手术间小计");
               voNew.setOperTable(total + "台手术");
               listNew.add(voNew);
            }

            amount = amount.add(new BigDecimal(vo.getOperTable()));
         }
      }

      return amount;
   }

   private BigDecimal getGroupDepart(List list, List listNew) {
      BigDecimal amount = BigDecimal.ZERO;
      if (CollectionUtils.isNotEmpty(list)) {
         String departCurr = null;
         BigDecimal bd = new BigDecimal("1");
         BigDecimal total = BigDecimal.ZERO;

         for(int i = 0; i < list.size(); ++i) {
            TdCaOperationApply vo = (TdCaOperationApply)list.get(i);
            if (StringUtils.isNull(departCurr)) {
               departCurr = vo.getApplyDeptName();
            } else if (StringUtils.isNotBlank(departCurr) && !departCurr.equals(vo.getApplyDeptName())) {
               TdCaOperationApply voNew = new TdCaOperationApply();
               voNew.setOperRoom(departCurr + "小计");
               voNew.setOperTable(total + "台手术");
               listNew.add(voNew);
               departCurr = vo.getOperRoom();
               total = BigDecimal.ZERO;
            }

            total = total.add(bd);
            listNew.add(vo);
            if (i == list.size() - 1) {
               TdCaOperationApply voNew = new TdCaOperationApply();
               voNew.setOperRoom(departCurr + "小计");
               voNew.setOperTable(total + "台手术");
               listNew.add(voNew);
            }

            amount = amount.add(new BigDecimal(vo.getOperTable()));
         }
      }

      return amount;
   }

   public List selectOperPlanList(OperPlanListReq req) throws Exception {
      if (StringUtils.isNotEmpty(req.getStatus())) {
         req.setStatusList(Arrays.asList(req.getStatus().split(",")));
      }

      return this.operationPlanMapper.selectOperPlanList(req);
   }

   public int getOperTableByShiftDate(String shiftDate) throws Exception {
      return this.operationPlanMapper.getOperTableByShiftDate(shiftDate);
   }

   public int updateOperPlan(OperPlanRejectReq req) {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      TdCaOperationApply update = new TdCaOperationApply();
      update.setApplyFormNo(req.getApplyFormNo());
      update.setStatus(req.getStatus());
      update.setAltDate(DateUtils.getNowDate());
      update.setAltPerCode(user.getUserName());
      update.setAltPerName(user.getNickName());
      if ("1".equals(req.getType())) {
         update.setStatus(req.getStatus());
         this.tdCaOperationApplyMapper.updateTdCaOperationApply(update);
      } else if ("2".equals(req.getType())) {
         BeanUtils.copyProperties(req, update);
         update.setShiftDate(req.getOperDate());
         update.setOperDate((Date)null);
         update.setOperRoomCd(req.getOperRoomCd());
         update.setOperRoom(req.getOperRoom());
         update.setShiftNurseCd(user.getUserName());
         update.setShiftNurseName(user.getNickName());
         this.tdCaOperationApplyMapper.updateOperationApplyManage(update);
      } else if ("3".equals(req.getType())) {
         this.tdCaOperationApplyMapper.revokeTdCaOperationApply(update);
      } else if ("4".equals(req.getType())) {
         update.setOperTable(req.getOperTable());
         update.setOperBeginDate(req.getOperBeginDate());
         update.setInRoomTime(req.getInRoomTime());
         this.tdCaOperationApplyMapper.updateOperationApplyManage(update);
      } else if ("5".equals(req.getType())) {
         this.tdCaOperationApplyMapper.revokeInRoomOperationApply(update);
      } else if ("6".equals(req.getType())) {
         update.setOperBeginDate(req.getOperBeginDate());
         update.setOperEndDate(req.getOperEndDate());
         update.setOutRoomTime(req.getOutRoomTime());
         update.setAnestMethCd(req.getAnestMethCd());
         update.setAnestMethName(req.getAnestMethName());
         update.setAnestAidCd(req.getAnestAidCd());
         update.setAnestAidName(req.getAnestAidName());
         update.setAsaTypeCode(req.getAsaTypeCode());
         update.setAsaTypeName(req.getAsaTypeName());
         this.tdCaOperationApplyMapper.updateOperationApplyManage(update);
      }

      return 1;
   }

   public TdCaOperationApply selectOperPlanDetail(OperPlanDetailReq req) throws Exception {
      return this.tdCaOperationApplyMapper.selectTdCaOperationApplyById(req.getApplyFormNo());
   }

   public OperPlanCountResp selectOperPlanCount(OperPlanDetailReq req) throws Exception {
      req.setCurrentDate(DateUtils.getDate());
      return this.tdCaOperationApplyMapper.selectOperPlanCount(req);
   }

   public List selectOperRoomInfo(OperPlanDetailReq req) throws Exception {
      List<OperRoomInfoResp> roomInfoResps = this.tdCaOperationApplyMapper.selectOperRoomInfo(req);
      TdCaOperationRoom queryRoom = new TdCaOperationRoom();
      queryRoom.setStatus("1");
      List<TdCaOperationRoom> roomList = this.tdCaOperationRoomMapper.selectTdCaOperationRoomList(queryRoom);
      List<OperRoomInfoResp> respList = new ArrayList();
      Integer totalCount = roomInfoResps.stream().mapToInt(OperRoomInfoResp::getCount).sum();

      for(TdCaOperationRoom tdCaOperationRoom : roomList) {
         OperRoomInfoResp resp = new OperRoomInfoResp();
         resp.setSurgicalRoomCode(tdCaOperationRoom.getSurgicalRoomCode());
         resp.setSurgicalRoomName(tdCaOperationRoom.getSurgicalRoomName());

         for(OperRoomInfoResp item : roomInfoResps) {
            if (tdCaOperationRoom.getSurgicalRoomCode().equals(item.getSurgicalRoomCode())) {
               resp.setCount(item.getCount());
            }
         }

         respList.add(resp);
      }

      OperRoomInfoResp totalResp = new OperRoomInfoResp();
      totalResp.setSurgicalRoomName("所有");
      totalResp.setCount(totalCount);
      respList.add(0, totalResp);
      return respList;
   }

   public List selectRoomList(RoomListReq req) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      TdCaOperationRoom room = new TdCaOperationRoom();
      room.setSurgicalRoomName(req.getSearchValue());
      room.setHospitalCode(user.getHospital().getOrgCode());
      room.setStatus("1");
      return this.tdCaOperationRoomMapper.selectTdCaOperationRoomList(room);
   }
}
