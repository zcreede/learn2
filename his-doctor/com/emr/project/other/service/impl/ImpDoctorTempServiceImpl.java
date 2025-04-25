package com.emr.project.other.service.impl;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.project.other.domain.ImpDoctorTemp;
import com.emr.project.other.domain.vo.ImpDoctorTempVo;
import com.emr.project.other.mapper.ImpDoctorTempMapper;
import com.emr.project.other.service.IImpDoctorTempService;
import com.emr.project.system.domain.BasEmployee;
import com.emr.project.system.domain.SysOrg;
import com.emr.project.system.domain.SysUser;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImpDoctorTempServiceImpl implements IImpDoctorTempService {
   @Autowired
   private ImpDoctorTempMapper impDoctorTempMapper;

   public ImpDoctorTemp selectImpDoctorTempById(Long id) {
      return this.impDoctorTempMapper.selectImpDoctorTempById(id);
   }

   public List selectImpDoctorTempList(ImpDoctorTempVo impDoctorTempVo) {
      return this.impDoctorTempMapper.selectImpDoctorTempList(impDoctorTempVo);
   }

   public int insertImpDoctorTemp(ImpDoctorTemp impDoctorTemp) {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      impDoctorTemp.setId(SnowIdUtils.uniqueLong());
      impDoctorTemp.setDocCode(user.getUserName());
      impDoctorTemp.setDocName(user.getNickName());
      impDoctorTemp.setCrePerCode(user.getUserName());
      impDoctorTemp.setCrePerName(user.getNickName());
      SysOrg hospital = user.getHospital();
      impDoctorTemp.setOrgCd(hospital.getOrgCode());
      impDoctorTemp.setValidFlag("1");
      return this.impDoctorTempMapper.insertImpDoctorTemp(impDoctorTemp);
   }

   public int batchAddImpDoctorTemp(List impDoctorTempList) {
      SysUser user = SecurityUtils.getLoginUser().getUser();

      for(ImpDoctorTemp impDoctorTemp : impDoctorTempList) {
         impDoctorTemp.setId(SnowIdUtils.uniqueLong());
         impDoctorTemp.setDocCode(user.getUserName());
         impDoctorTemp.setDocName(user.getNickName());
         impDoctorTemp.setCrePerCode(user.getUserName());
         impDoctorTemp.setCrePerName(user.getNickName());
         SysOrg hospital = user.getHospital();
         impDoctorTemp.setOrgCd(hospital.getOrgCode());
         impDoctorTemp.setValidFlag("1");
      }

      return this.impDoctorTempMapper.batchAddImpDoctorTemp(impDoctorTempList);
   }

   public int insertImpDoctor(ImpDoctorTemp impDoctorTemp) {
      return this.impDoctorTempMapper.insertImpDoctorTemp(impDoctorTemp);
   }

   public int updateImpDoctorTemp(ImpDoctorTemp impDoctorTemp) {
      return this.impDoctorTempMapper.updateImpDoctorTemp(impDoctorTemp);
   }

   public int updateImpDoctorTempForMove(ImpDoctorTemp impDoctorTemp) {
      return this.impDoctorTempMapper.updateImpDoctorTempForMove(impDoctorTemp);
   }

   public int deleteImpDoctorTempByIds(Long[] ids) {
      return this.impDoctorTempMapper.deleteImpDoctorTempByIds(ids);
   }

   public int deleteImpDoctorTempById(Long id) {
      return this.impDoctorTempMapper.deleteImpDoctorTempById(id);
   }

   public List selectDocTempImpowerList(String docCd, String deptCd) throws Exception {
      return this.impDoctorTempMapper.selectDocTempImpowerList(docCd, deptCd);
   }

   public List selectTmpByPatAndDoc(String patientId, String impDocCd, String impType) throws Exception {
      return this.impDoctorTempMapper.selectTmpByPatAndDoc(patientId, impDocCd, impType);
   }

   public void updateImpPatAndDoc(String patientId, String impDocCd, String impType) throws Exception {
      this.impDoctorTempMapper.updateImpPatAndDoc(patientId, impDocCd, impType);
   }

   public Boolean selectImpDocPatBool(String patientId) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      Boolean flag = true;
      List<ImpDoctorTemp> list = this.impDoctorTempMapper.selectTmpByPatAndDoc(patientId, basEmployee.getEmplNumber(), (String)null);
      if (list == null || list.isEmpty()) {
         flag = false;
      }

      return flag;
   }

   public void updateImpDoctorTempByThird(String userName, String patientId) {
      this.impDoctorTempMapper.updateImpDoctorTempByThird(userName, patientId);
   }

   public List selectImpDoctorTemp(String userName, String patientId) {
      return this.impDoctorTempMapper.selectImpDoctorTemp(userName, patientId);
   }

   public List selectTmpByPatAndDocOrDept(String patientId, String docCd, String deptCd) throws Exception {
      List<ImpDoctorTemp> list = null;
      return StringUtils.isNotBlank(docCd) && StringUtils.isNotBlank(deptCd) ? this.impDoctorTempMapper.selectTmpByPatAndDocOrDept(patientId, docCd, deptCd) : list;
   }
}
