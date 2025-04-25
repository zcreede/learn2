package com.emr.project.emr.service.impl;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.project.common.service.ICommonService;
import com.emr.project.emr.domain.EditState;
import com.emr.project.emr.domain.vo.EditStateVo;
import com.emr.project.emr.mapper.EditStateMapper;
import com.emr.project.emr.service.IEditStateService;
import com.emr.project.emr.service.IIndexService;
import com.emr.project.pat.domain.vo.VisitinfoVo;
import com.emr.project.pat.service.IPersonalinfoService;
import com.emr.project.system.domain.SysUser;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditStateServiceImpl implements IEditStateService {
   @Autowired
   private EditStateMapper editStateMapper;
   @Autowired
   private IIndexService indexService;
   @Autowired
   private IPersonalinfoService personalinfoService;
   @Autowired
   private ICommonService commonService;

   public EditState selectEditStateById(Long id) {
      return this.editStateMapper.selectEditStateById(id);
   }

   public List selectDeitEditStateList(EditStateVo editStateVo) {
      return this.editStateMapper.selectDeitEditStateList(editStateVo);
   }

   public List selectEditStateList(EditState editState) {
      return this.editStateMapper.selectEditStateList(editState);
   }

   public int insertEditState(EditState editState) {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      editState.setId(SnowIdUtils.uniqueLong());
      editState.setEditPersonCd(sysUser.getUserName());
      editState.setEditPersonName(sysUser.getNickName());
      editState.setDeitState("1");
      editState.setCrePerCode(sysUser.getUserName());
      editState.setCrePerName(sysUser.getNickName());
      return this.editStateMapper.insertEditState(editState);
   }

   public void insertEditState(VisitinfoVo visitinfoVo, Long mrFileId, String fileName, String ip) throws Exception {
      EditState editStateAdd = new EditState();
      editStateAdd.setInpNo(visitinfoVo.getInpNo());
      editStateAdd.setDeptId(visitinfoVo.getDeptCd());
      editStateAdd.setDeptName(visitinfoVo.getDeptName());
      editStateAdd.setPatientName(visitinfoVo.getPatientName());
      editStateAdd.setMrFileId(mrFileId);
      editStateAdd.setMrFileName(fileName);
      editStateAdd.setBeginTime(this.commonService.getDbSysdate());
      editStateAdd.setUpdateTime(editStateAdd.getBeginTime());
      editStateAdd.setIp(ip);
      this.insertEditState(editStateAdd);
   }

   public int updateEditState(EditState editState) {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      editState.setAltPerCode(sysUser.getUserName());
      editState.setAltPerName(sysUser.getNickName());
      return this.editStateMapper.updateEditState(editState);
   }

   public void updateEditState(Long mrFileId) throws Exception {
      EditState editState = this.selectEditStateByEmrId(mrFileId);
      EditState editStateUpdate = new EditState();
      editStateUpdate.setId(editState.getId());
      editStateUpdate.setEndTime(this.commonService.getDbSysdate());
      editStateUpdate.setDeitState("0");
      this.updateEditState(editStateUpdate);
   }

   public int deleteEditStateByIds(Long[] ids) {
      return this.editStateMapper.deleteEditStateByIds(ids);
   }

   public int deleteEditStateById(Long id) {
      return this.editStateMapper.deleteEditStateById(id);
   }

   public EditState selectEditStateByEmrId(Long emrId) throws Exception {
      return this.editStateMapper.selectEditStateByEmrId(emrId);
   }

   public EditState selectEditStateLastByEmrId(Long emrId) throws Exception {
      return this.editStateMapper.selectEditStateLastByEmrId(emrId);
   }

   public void updateCloseEdit(int mine) throws Exception {
      this.editStateMapper.updateCloseEdit(mine);
   }
}
