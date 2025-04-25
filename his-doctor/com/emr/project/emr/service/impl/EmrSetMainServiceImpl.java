package com.emr.project.emr.service.impl;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.common.utils.StringUtils;
import com.emr.project.common.service.ICommonService;
import com.emr.project.emr.domain.EmrSetMain;
import com.emr.project.emr.mapper.EmrSetMainMapper;
import com.emr.project.emr.service.IEmrSetDetailService;
import com.emr.project.emr.service.IEmrSetMainService;
import com.emr.project.system.domain.SysUser;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmrSetMainServiceImpl implements IEmrSetMainService {
   @Autowired
   private EmrSetMainMapper emrSetMainMapper;
   @Autowired
   private ICommonService commonService;
   @Autowired
   private IEmrSetDetailService emrSetDetailService;

   public EmrSetMain selectEmrSetMainById(String setCd) {
      return this.emrSetMainMapper.selectEmrSetMainById(setCd);
   }

   public List selectEmrSetMainList(EmrSetMain emrSetMain) throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      if (emrSetMain != null && StringUtils.isNotEmpty(emrSetMain.getShareType())) {
         if (emrSetMain.getShareType().equals("1")) {
            emrSetMain.setShareObject(sysUser.getDept().getDeptCode());
         } else if (emrSetMain.getShareType().equals("0")) {
            emrSetMain.setDocCode(sysUser.getBasEmployee().getEmplNumber());
            emrSetMain.setDeptCode(sysUser.getDept().getDeptCode());
            emrSetMain.setShareType((String)null);
         } else {
            emrSetMain.setShareObject(sysUser.getBasEmployee().getEmplNumber());
         }
      }

      return this.emrSetMainMapper.selectEmrSetMainList(emrSetMain);
   }

   public void insertEmrSetMain(EmrSetMain emrSetMain) throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      if (emrSetMain.getShareType().equals("1")) {
         emrSetMain.setShareObject(sysUser.getDept().getDeptCode());
      } else {
         emrSetMain.setShareObject(sysUser.getBasEmployee().getEmplNumber());
      }

      emrSetMain.setSetCd(SnowIdUtils.uniqueLongHex());
      emrSetMain.setCreDate(this.commonService.getDbSysdate());
      emrSetMain.setCrePerCode(sysUser.getBasEmployee().getEmplNumber());
      emrSetMain.setCrePerName(sysUser.getBasEmployee().getEmplName());
      this.emrSetMainMapper.insertEmrSetMain(emrSetMain);
   }

   public void updateEmrSetMain(EmrSetMain emrSetMain) throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      if (StringUtils.isNotEmpty(emrSetMain.getShareType())) {
         if (emrSetMain.getShareType().equals("1")) {
            emrSetMain.setShareObject(sysUser.getDept().getDeptCode());
         } else {
            emrSetMain.setShareObject(sysUser.getBasEmployee().getEmplNumber());
         }
      }

      emrSetMain.setAltDate(this.commonService.getDbSysdate());
      emrSetMain.setAltPerCode(sysUser.getBasEmployee().getEmplNumber());
      emrSetMain.setAltPerName(sysUser.getBasEmployee().getEmplName());
      this.emrSetMainMapper.updateEmrSetMain(emrSetMain);
   }

   public int deleteEmrSetMainByIds(String[] setCds) {
      return this.emrSetMainMapper.deleteEmrSetMainByIds(setCds);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void deleteEmrSetMainById(String setCd) throws Exception {
      this.emrSetMainMapper.deleteEmrSetMainById(setCd);
      this.emrSetDetailService.deleteEmrSetDetailSetCd(setCd);
   }
}
