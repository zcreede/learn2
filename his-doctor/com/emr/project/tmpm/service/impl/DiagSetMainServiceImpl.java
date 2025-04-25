package com.emr.project.tmpm.service.impl;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.common.utils.StringUtils;
import com.emr.project.common.service.ICommonService;
import com.emr.project.system.domain.BasEmployee;
import com.emr.project.system.domain.SysUser;
import com.emr.project.tmpm.domain.DiagSetDetail;
import com.emr.project.tmpm.domain.DiagSetMain;
import com.emr.project.tmpm.domain.vo.DiagSetMainVo;
import com.emr.project.tmpm.mapper.DiagSetMainMapper;
import com.emr.project.tmpm.service.IDiagSetDetailService;
import com.emr.project.tmpm.service.IDiagSetMainService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DiagSetMainServiceImpl implements IDiagSetMainService {
   @Autowired
   private DiagSetMainMapper diagSetMainMapper;
   @Autowired
   private ICommonService commonService;
   @Autowired
   private IDiagSetDetailService diagSetDetailService;

   public DiagSetMain selectDiagSetMainById(String setCd) {
      return this.diagSetMainMapper.selectDiagSetMainById(setCd);
   }

   public List selectDiagSetMainList(DiagSetMain diagSetMain) {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      if (StringUtils.isNotBlank(diagSetMain.getShareType())) {
         if (diagSetMain.getShareType().equals("2")) {
            diagSetMain.setShareObject(sysUser.getDept().getDeptCode());
         } else if (diagSetMain.getShareType().equals("3")) {
            diagSetMain.setShareObject(sysUser.getBasEmployee().getEmplNumber());
         }
      }

      return this.diagSetMainMapper.selectDiagSetMainList(diagSetMain);
   }

   public void insertDiagSetMain(DiagSetMainVo diagSetMain) throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      BasEmployee basEmployee = sysUser.getBasEmployee();
      diagSetMain.setSetCd(SnowIdUtils.uniqueLongHex());
      diagSetMain.setHospitalCode(sysUser.getHospital().getOrgCode());
      if (diagSetMain.getShareType().equals("2")) {
         diagSetMain.setShareObject(sysUser.getDept().getDeptCode());
      } else if (diagSetMain.getShareType().equals("3")) {
         diagSetMain.setShareObject(sysUser.getBasEmployee().getEmplNumber());
      } else {
         diagSetMain.setShareObject("000000");
      }

      Integer sort = this.diagSetMainMapper.selectSetMainMaxNumber();
      sort = sort == null ? 1 : sort + 1;
      diagSetMain.setSortNumber(sort);
      diagSetMain.setCreDate(this.commonService.getDbSysdate());
      diagSetMain.setCrePerCode(basEmployee.getEmplNumber());
      diagSetMain.setCrePerName(basEmployee.getEmplName());
      this.diagSetMainMapper.insertDiagSetMain(diagSetMain);
   }

   public void updateDiagSetMain(DiagSetMain diagSetMain) throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      BasEmployee basEmployee = sysUser.getBasEmployee();
      if (diagSetMain.getShareType().equals("2")) {
         diagSetMain.setShareObject(sysUser.getDept().getDeptCode());
      } else if (diagSetMain.getShareType().equals("3")) {
         diagSetMain.setShareObject(basEmployee.getEmplNumber());
      } else {
         diagSetMain.setShareObject("000000");
      }

      diagSetMain.setAltDate(this.commonService.getDbSysdate());
      diagSetMain.setAltPerCode(basEmployee.getEmplNumber());
      diagSetMain.setAltPerName(basEmployee.getEmplName());
      this.diagSetMainMapper.updateDiagSetMain(diagSetMain);
   }

   public int deleteDiagSetMainByIds(String[] setCds) {
      return this.diagSetMainMapper.deleteDiagSetMainByIds(setCds);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void deleteDiagSetMainById(String setCd) throws Exception {
      this.diagSetDetailService.deleteDiagSetDetailBySetCd(setCd);
      this.diagSetMainMapper.deleteDiagSetMainById(setCd);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void saveAsDiagSet(DiagSetMainVo diagSetMainVo) throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      BasEmployee basEmployee = sysUser.getBasEmployee();
      String setCd = SnowIdUtils.uniqueLongHex();
      Integer sort = this.diagSetMainMapper.selectSetMainMaxNumber();
      sort = sort != null ? sort + 1 : 1;
      diagSetMainVo.setSetCd(setCd);
      if (diagSetMainVo.getShareType().equals("3")) {
         diagSetMainVo.setShareObject(basEmployee.getEmplNumber());
      } else if (diagSetMainVo.getShareType().equals("1")) {
         diagSetMainVo.setShareObject("000000");
      }

      diagSetMainVo.setHospitalCode(sysUser.getHospital().getOrgCode());
      diagSetMainVo.setCreDate(this.commonService.getDbSysdate());
      diagSetMainVo.setCrePerCode(basEmployee.getEmplNumber());
      diagSetMainVo.setCrePerName(basEmployee.getEmplName());
      diagSetMainVo.setSortNumber(sort);
      this.diagSetMainMapper.insertDiagSetMain(diagSetMainVo);
      if (diagSetMainVo.getDetailList() != null && !diagSetMainVo.getDetailList().isEmpty()) {
         List<DiagSetDetail> detailList = diagSetMainVo.getDetailList();

         for(DiagSetDetail detail : detailList) {
            detail.setId(SnowIdUtils.uniqueLong());
            detail.setSetCd(setCd);
            detail.setCreDate(this.commonService.getDbSysdate());
            detail.setCrePerCode(basEmployee.getEmplNumber());
            detail.setCrePerName(basEmployee.getEmplName());
         }

         this.diagSetDetailService.insertDiagSetDetailList(detailList);
      }

   }

   public Integer selectSetMainMaxNumber() throws Exception {
      return this.diagSetMainMapper.selectSetMainMaxNumber();
   }

   public void updateSetMainFlag(DiagSetMain diagSetMain) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      diagSetMain.setAltDate(this.commonService.getDbSysdate());
      diagSetMain.setAltPerCode(basEmployee.getEmplNumber());
      diagSetMain.setCrePerName(basEmployee.getEmplName());
      this.diagSetMainMapper.updateDiagSetMain(diagSetMain);
   }
}
