package com.emr.project.other.service.impl;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.project.other.domain.GrantOutDoctor;
import com.emr.project.other.domain.vo.GrantOutDoctorVo;
import com.emr.project.other.mapper.GrantOutDoctorMapper;
import com.emr.project.other.service.IGrantOutDoctorService;
import com.emr.project.system.domain.SysDept;
import com.emr.project.system.domain.SysRole;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.domain.SysUserDept;
import com.emr.project.system.domain.SysUserRole;
import com.emr.project.system.mapper.SysUserRoleMapper;
import com.emr.project.system.service.ISysUserDeptService;
import com.emr.project.system.service.ISysUserService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GrantOutDoctorServiceImpl implements IGrantOutDoctorService {
   @Autowired
   private GrantOutDoctorMapper grantOutDoctorMapper;
   @Autowired
   private ISysUserService sysUserService;
   @Autowired
   private ISysUserDeptService sysUserDeptService;
   @Autowired
   private SysUserRoleMapper userRoleMapper;

   public GrantOutDoctor selectGrantOutDoctorById(Long id) {
      return this.grantOutDoctorMapper.selectGrantOutDoctorById(id);
   }

   public List selectGrantOutDoctorList(GrantOutDoctorVo grantOutDoctorVo) {
      return this.grantOutDoctorMapper.selectGrantOutDoctorList(grantOutDoctorVo);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public int insertGrantOutDoctor(GrantOutDoctor grantOutDoctor) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      grantOutDoctor.setId(SnowIdUtils.uniqueLong());
      String newUserName = this.genSubNo(user.getBasEmployee().getEmplNumber());
      grantOutDoctor.setSubNo(newUserName);
      grantOutDoctor.setDocCode(user.getBasEmployee().getEmplNumber());
      grantOutDoctor.setDocName(user.getBasEmployee().getEmplName());
      grantOutDoctor.setOrgCd(user.getHospital().getOrgCode());
      grantOutDoctor.setDeptCd(user.getDept().getDeptCode());
      grantOutDoctor.setDeptName(user.getDept().getDeptName());
      grantOutDoctor.setCrePerCode(user.getBasEmployee().getEmplNumber());
      grantOutDoctor.setCrePerName(user.getBasEmployee().getEmplName());
      SysUser newUser = new SysUser();
      newUser.setUserName(newUserName);
      newUser.setNickName(grantOutDoctor.getSubName());
      newUser.setPassword(SecurityUtils.encryptPassword("1234emr"));
      newUser.setSex(grantOutDoctor.getGender());
      newUser.setStatus("0");
      newUser.setDelFlag("0");
      newUser.setEmployeeId(user.getBasEmployee().getId());
      List<SysRole> roles = user.getRoles();
      List<SysDept> depts = user.getDepts();
      List<String> roleIds = roles != null && !roles.isEmpty() ? (List)roles.stream().map((t) -> t.getRoleKey()).collect(Collectors.toList()) : null;

      for(String deptCode : depts != null && !depts.isEmpty() ? (List)depts.stream().map((t) -> t.getDeptCode()).collect(Collectors.toList()) : null) {
         SysUserDept sysUserDept = new SysUserDept();
         sysUserDept.setUserId(newUserName);
         sysUserDept.setDeptId(deptCode);
         sysUserDept.setFlag("0");
         this.sysUserDeptService.insertSysUserDept(sysUserDept);
      }

      List<SysUserRole> roleList = new ArrayList();

      for(String roleNo : roleIds) {
         SysUserRole sysUserRole = new SysUserRole();
         sysUserRole.setRoleId(roleNo);
         sysUserRole.setUserId(newUserName);
         roleList.add(sysUserRole);
      }

      if (roleList != null && !roleList.isEmpty()) {
         this.userRoleMapper.batchUserRole(roleList);
      }

      this.sysUserService.insertUser(newUser);
      return this.grantOutDoctorMapper.insertGrantOutDoctor(grantOutDoctor);
   }

   private String genSubNo(String userName) {
      GrantOutDoctorVo param = new GrantOutDoctorVo();
      param.setDocCode(userName);
      Integer maxNum = this.grantOutDoctorMapper.selectSubNameMaxNumByDocCode(userName);
      int num = maxNum != null ? maxNum + 1 : 1;
      String subNo = userName + num;
      return subNo;
   }

   public int updateGrantOutDoctor(GrantOutDoctor grantOutDoctor) {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      grantOutDoctor.setAltPerName(user.getNickName());
      grantOutDoctor.setAltPerCode(user.getUserName());
      return this.grantOutDoctorMapper.updateGrantOutDoctor(grantOutDoctor);
   }

   public int deleteGrantOutDoctorByIds(Long[] ids) {
      return this.grantOutDoctorMapper.deleteGrantOutDoctorByIds(ids);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public int deleteGrantOutDoctorById(Long id) throws Exception {
      GrantOutDoctor grantOutDoctor = this.grantOutDoctorMapper.selectGrantOutDoctorById(id);
      SysUser sysUser = this.sysUserService.selectUserByUserName(grantOutDoctor.getSubNo());
      this.sysUserService.deleteUserById(sysUser.getUserId());
      return this.grantOutDoctorMapper.deleteGrantOutDoctorById(id);
   }

   public GrantOutDoctor selectGrantOutDoctorBySubNo(String subNo) {
      GrantOutDoctorVo param = new GrantOutDoctorVo();
      param.setSubNo(subNo);
      List<GrantOutDoctor> list = this.grantOutDoctorMapper.selectGrantOutDoctorList(param);
      return list != null && !list.isEmpty() ? (GrantOutDoctor)list.get(0) : null;
   }

   public Boolean checkIsOutDoctor(String userName) {
      Integer count = this.grantOutDoctorMapper.checkIsOutDoctor(userName);
      return count != 0 ? Boolean.TRUE : Boolean.FALSE;
   }
}
