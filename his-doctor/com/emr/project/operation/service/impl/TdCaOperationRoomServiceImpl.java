package com.emr.project.operation.service.impl;

import com.emr.common.utils.PinYinUtil;
import com.emr.common.utils.SecurityUtils;
import com.emr.project.common.service.ICommonService;
import com.emr.project.operation.domain.TdCaOperationRoom;
import com.emr.project.operation.mapper.TdCaOperationRoomMapper;
import com.emr.project.operation.service.ITdCaOperationRoomService;
import com.emr.project.system.domain.SysUser;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TdCaOperationRoomServiceImpl implements ITdCaOperationRoomService {
   @Autowired
   private TdCaOperationRoomMapper tdCaOperationRoomMapper;
   @Autowired
   private ICommonService commonService;

   public TdCaOperationRoom selectTdCaOperationRoomById(Long id) throws Exception {
      return this.tdCaOperationRoomMapper.selectTdCaOperationRoomById(id);
   }

   public List selectTdCaOperationRoomList(TdCaOperationRoom tdCaOperationRoom) throws Exception {
      return this.tdCaOperationRoomMapper.selectTdCaOperationRoomList(tdCaOperationRoom);
   }

   public int insertTdCaOperationRoom(TdCaOperationRoom tdCaOperationRoom) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      tdCaOperationRoom.setCreName(user.getNickName());
      tdCaOperationRoom.setCreCode(user.getUserName());
      tdCaOperationRoom.setHospitalCode(user.getHospital().getOrgCode());
      tdCaOperationRoom.setId(this.tdCaOperationRoomMapper.selectMaxId());
      String sjc = System.currentTimeMillis() + "";
      tdCaOperationRoom.setSurgicalRoomCode(user.getHospital().getOrgCode() + user.getDept().getDeptCode() + sjc);
      tdCaOperationRoom.setStatus("1");
      tdCaOperationRoom.setBelongingDepartmentCode(user.getDept().getDeptCode());
      tdCaOperationRoom.setBelongingDepartmentName(user.getDept().getDeptName());
      tdCaOperationRoom.setPinyinCode(PinYinUtil.getPinYinHeadChar(tdCaOperationRoom.getSurgicalRoomName()));
      Date currDate = this.commonService.getDbSysdate();
      tdCaOperationRoom.setCreDate(currDate);
      return this.tdCaOperationRoomMapper.insertTdCaOperationRoom(tdCaOperationRoom);
   }

   public int updateTdCaOperationRoom(TdCaOperationRoom tdCaOperationRoom) throws Exception {
      return this.tdCaOperationRoomMapper.updateTdCaOperationRoom(tdCaOperationRoom);
   }

   public int deleteTdCaOperationRoomByIds(Long[] ids) throws Exception {
      return this.tdCaOperationRoomMapper.deleteTdCaOperationRoomByIds(ids);
   }

   public int deleteTdCaOperationRoomById(Long id) throws Exception {
      return this.tdCaOperationRoomMapper.removeCaOperationRoomById(id);
   }

   public List selectTdCaOperationRoomListOfSearch(String orgCode, String searchValue) {
      return this.tdCaOperationRoomMapper.selectTdCaOperationRoomListOfSearch(orgCode, searchValue);
   }
}
