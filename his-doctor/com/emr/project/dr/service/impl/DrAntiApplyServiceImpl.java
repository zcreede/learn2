package com.emr.project.dr.service.impl;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.project.common.service.ICommonService;
import com.emr.project.dr.domain.DrAntiApply;
import com.emr.project.dr.domain.vo.DrAntiApplyVo;
import com.emr.project.dr.mapper.DrAntiApplyMapper;
import com.emr.project.dr.service.IDrAntiApplyService;
import com.emr.project.system.domain.BasEmployee;
import com.emr.project.system.domain.SysUser;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DrAntiApplyServiceImpl implements IDrAntiApplyService {
   @Autowired
   private DrAntiApplyMapper drAntiApplyMapper;
   @Autowired
   private ICommonService iCommonService;

   public DrAntiApply selectDrAntiApplyById(Long id) {
      return this.drAntiApplyMapper.selectDrAntiApplyById(id);
   }

   public List selectDrAntiApplyList(DrAntiApplyVo drAntiApplyVo) throws Exception {
      return this.drAntiApplyMapper.selectDrAntiApplyList(drAntiApplyVo);
   }

   public int insertDrAntiApply(DrAntiApply drAntiApply) throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      BasEmployee basEmployee = sysUser.getBasEmployee();
      drAntiApply.setCrePerCode(basEmployee.getEmplNumber());
      drAntiApply.setCrePerName(basEmployee.getEmplName());
      drAntiApply.setId(SnowIdUtils.uniqueLong());
      drAntiApply.setUseFlag("0");
      drAntiApply.setOrgCd(sysUser.getHospital().getOrgCode());
      return this.drAntiApplyMapper.insertDrAntiApply(drAntiApply);
   }

   public int updateDrAntiApply(DrAntiApply drAntiApply) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      drAntiApply.setAltPerCode(basEmployee.getEmplNumber());
      drAntiApply.setAltPerName(basEmployee.getEmplName());
      return this.drAntiApplyMapper.updateDrAntiApply(drAntiApply);
   }

   public int deleteDrAntiApplyByIds(Long[] ids) {
      return this.drAntiApplyMapper.deleteDrAntiApplyByIds(ids);
   }

   public int deleteDrAntiApplyById(Long id) throws Exception {
      return this.drAntiApplyMapper.deleteDrAntiApplyById(id);
   }

   public List selectDrAntiDrugVoList(DrAntiApplyVo drAntiApplyVo) throws Exception {
      return this.drAntiApplyMapper.selectSqlStrList(drAntiApplyVo);
   }

   public void saveDrAntiApply(DrAntiApply drAntiApply) throws Exception {
      if (drAntiApply.getId() == null) {
         this.insertDrAntiApply(drAntiApply);
      } else {
         this.updateDrAntiApply(drAntiApply);
      }

   }

   public List selectAuditList(DrAntiApplyVo drAntiApplyVo, Boolean flag) throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      if (!flag) {
         drAntiApplyVo.setDeptCode(sysUser.getDept().getDeptCode());
      }

      drAntiApplyVo.setState("0");
      List<DrAntiApplyVo> list = this.drAntiApplyMapper.selectDrAntiApplyList(drAntiApplyVo);
      return list;
   }

   public void updateAuditState(DrAntiApplyVo drAntiApplyVo) throws Exception {
      Date date = this.iCommonService.getDbSysdate();
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      drAntiApplyVo.setAltPerCode(basEmployee.getEmplNumber());
      drAntiApplyVo.setAltPerName(basEmployee.getEmplName());
      drAntiApplyVo.setApprDocCode(basEmployee.getEmplNumber());
      drAntiApplyVo.setApprDocName(basEmployee.getEmplName());
      drAntiApplyVo.setApprTitleCode(basEmployee.getTitleCode());
      drAntiApplyVo.setApprTitleName(basEmployee.getTitleName());
      drAntiApplyVo.setApprDocDate(date);
      this.drAntiApplyMapper.updateAuditState(drAntiApplyVo);
   }

   public List selectYetAuditList(DrAntiApplyVo drAntiApplyVo, Boolean flag) throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      if (!flag) {
         drAntiApplyVo.setDeptCode(sysUser.getDept().getDeptCode());
      }

      List<String> stateList = new ArrayList();
      stateList.add("1");
      stateList.add("2");
      drAntiApplyVo.setStateList(stateList);
      List<DrAntiApplyVo> list = this.drAntiApplyMapper.selectDrAntiApplyList(drAntiApplyVo);
      return list;
   }

   public List selectByPatientAndDrugCode(DrAntiApply drAntiApply) throws Exception {
      return this.drAntiApplyMapper.selectByPatientAndDrugCode(drAntiApply);
   }

   public List selectByPatientAndDrugCodes(String patientId, List drugCodeList) throws Exception {
      return this.drAntiApplyMapper.selectByPatientAndDrugCodes(patientId, drugCodeList);
   }
}
