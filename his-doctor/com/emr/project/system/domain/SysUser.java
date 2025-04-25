package com.emr.project.system.domain;

import com.emr.common.utils.StringUtils;
import com.emr.framework.aspectj.lang.annotation.Excel;
import com.emr.framework.aspectj.lang.annotation.Excels;
import com.emr.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SysUser extends BaseEntity {
   private static final long serialVersionUID = 1L;
   @Excel(
      name = "用户序号",
      cellType = Excel.ColumnType.NUMERIC,
      prompt = "用户编号"
   )
   private Long userId;
   @Excel(
      name = "部门编号",
      type = Excel.Type.IMPORT
   )
   private Long deptId;
   @Excel(
      name = "登录名称"
   )
   private String userName;
   @Excel(
      name = "用户名称"
   )
   private String nickName;
   @Excel(
      name = "用户邮箱"
   )
   private String email;
   @Excel(
      name = "手机号码"
   )
   private String phonenumber;
   @Excel(
      name = "用户性别",
      readConverterExp = "0=男,1=女,2=未知"
   )
   private String sex;
   private String avatar;
   private String password;
   private String salt;
   @Excel(
      name = "帐号状态",
      readConverterExp = "0=正常,1=停用"
   )
   private String status;
   private String delFlag;
   @Excel(
      name = "最后登录IP",
      type = Excel.Type.EXPORT
   )
   private String loginIp;
   @Excel(
      name = "最后登录时间",
      width = (double)30.0F,
      dateFormat = "yyyy-MM-dd HH:mm:ss",
      type = Excel.Type.EXPORT
   )
   private Date loginDate;
   private Long employeeId;
   private Long loginType;
   private String certCode;
   private String healthCode;
   private String deptCode;
   @Excels({@Excel(
   name = "部门名称",
   targetAttr = "deptName",
   type = Excel.Type.EXPORT
), @Excel(
   name = "部门负责人",
   targetAttr = "leader",
   type = Excel.Type.EXPORT
)})
   private SysDept dept;
   private SysOrg hospital;
   private Long hospitalId;
   private List roles;
   private Long[] roleIds;
   private Long[] postIds;
   private Long roleId;
   private List depts;
   private List userRoleDepts;
   private Long[] deptIds;
   private String deptName;
   private String authorLevel;
   private String maxLevel;
   private Long authorRoleId;
   private BasEmployee basEmployee;
   private String title;
   private String titleName;
   private String inputstrphtc;
   private String staffType;
   private TmBsModule module;
   private List moduleList;
   private String certSn;
   private Date updatePasswordTime;

   public Date getUpdatePasswordTime() {
      return this.updatePasswordTime;
   }

   public void setUpdatePasswordTime(Date updatePasswordTime) {
      this.updatePasswordTime = updatePasswordTime;
   }

   public String getCertSn() {
      return this.certSn;
   }

   public void setCertSn(String certSn) {
      this.certSn = certSn;
   }

   public List getModuleList() {
      return this.moduleList;
   }

   public void setModuleList(List moduleList) {
      this.moduleList = moduleList;
   }

   public TmBsModule getModule() {
      return this.module;
   }

   public void setModule(TmBsModule module) {
      this.module = module;
   }

   public String getStaffType() {
      return this.staffType;
   }

   public void setStaffType(String staffType) {
      this.staffType = staffType;
   }

   public String getDeptCode() {
      return this.deptCode;
   }

   public void setDeptCode(String deptCode) {
      this.deptCode = deptCode;
   }

   public String getInputstrphtc() {
      return this.inputstrphtc;
   }

   public void setInputstrphtc(String inputstrphtc) {
      this.inputstrphtc = inputstrphtc;
   }

   public Long getLoginType() {
      return this.loginType;
   }

   public void setLoginType(Long loginType) {
      this.loginType = loginType;
   }

   public String getTitle() {
      return this.title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public String getTitleName() {
      return this.titleName;
   }

   public void setTitleName(String titleName) {
      this.titleName = titleName;
   }

   public BasEmployee getBasEmployee() {
      return this.basEmployee;
   }

   public void setBasEmployee(BasEmployee basEmployee) {
      this.basEmployee = basEmployee;
   }

   public String getDeptName() {
      return this.deptName;
   }

   public void setDeptName(String deptName) {
      this.deptName = deptName;
   }

   public SysOrg getHospital() {
      return this.hospital;
   }

   public void setHospital(SysOrg hospital) {
      this.hospital = hospital;
   }

   public Long getHospitalId() {
      return this.hospitalId;
   }

   public void setHospitalId(Long hospitalId) {
      this.hospitalId = hospitalId;
   }

   public List getDepts() {
      return this.depts;
   }

   public void setDepts(List depts) {
      this.depts = depts;
   }

   public Long[] getDeptIds() {
      return this.deptIds;
   }

   public void setDeptIds(Long[] deptIds) {
      this.deptIds = deptIds;
   }

   public SysUser() {
   }

   public SysUser(Long userId) {
      this.userId = userId;
   }

   public Long getUserId() {
      return this.userId;
   }

   public void setUserId(Long userId) {
      this.userId = userId;
   }

   public boolean isAdmin() {
      return isAdmin(this.userId);
   }

   public static boolean isAdmin(Long userId) {
      return userId != null && 1L == userId;
   }

   public static boolean isAdmin(String staffCode) {
      return StringUtils.isNotBlank(staffCode) && (staffCode.equals("sa") || staffCode.equals("admin"));
   }

   public Long getDeptId() {
      return this.deptId;
   }

   public void setDeptId(Long deptId) {
      this.deptId = deptId;
   }

   public @Size(
   min = 0,
   max = 30,
   message = "用户昵称长度不能超过30个字符"
) String getNickName() {
      return this.nickName;
   }

   public void setNickName(String nickName) {
      this.nickName = nickName;
   }

   public @NotBlank(
   message = "用户账号不能为空"
) @Size(
   min = 0,
   max = 30,
   message = "用户账号长度不能超过30个字符"
) String getUserName() {
      return this.userName;
   }

   public void setUserName(String userName) {
      this.userName = userName;
   }

   public @Email(
   message = "邮箱格式不正确"
) @Size(
   min = 0,
   max = 50,
   message = "邮箱长度不能超过50个字符"
) String getEmail() {
      return this.email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public @Size(
   min = 0,
   max = 11,
   message = "手机号码长度不能超过11个字符"
) String getPhonenumber() {
      return this.phonenumber;
   }

   public void setPhonenumber(String phonenumber) {
      this.phonenumber = phonenumber;
   }

   public String getSex() {
      return this.sex;
   }

   public void setSex(String sex) {
      this.sex = sex;
   }

   public String getAvatar() {
      return StringUtils.isEmpty(this.avatar) ? "" : this.avatar;
   }

   public void setAvatar(String avatar) {
      this.avatar = avatar;
   }

   @JsonIgnore
   @JsonProperty
   public String getPassword() {
      return this.password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public String getSalt() {
      return this.salt;
   }

   public void setSalt(String salt) {
      this.salt = salt;
   }

   public String getStatus() {
      return this.status;
   }

   public void setStatus(String status) {
      this.status = status;
   }

   public String getDelFlag() {
      return this.delFlag;
   }

   public void setDelFlag(String delFlag) {
      this.delFlag = delFlag;
   }

   public String getLoginIp() {
      return this.loginIp;
   }

   public void setLoginIp(String loginIp) {
      this.loginIp = loginIp;
   }

   public Date getLoginDate() {
      return this.loginDate;
   }

   public void setLoginDate(Date loginDate) {
      this.loginDate = loginDate;
   }

   public SysDept getDept() {
      return this.dept;
   }

   public void setDept(SysDept dept) {
      this.dept = dept;
   }

   public List getRoles() {
      return this.roles;
   }

   public void setRoles(List roles) {
      this.roles = roles;
   }

   public Long[] getRoleIds() {
      return this.roleIds;
   }

   public void setRoleIds(Long[] roleIds) {
      this.roleIds = roleIds;
   }

   public Long[] getPostIds() {
      return this.postIds;
   }

   public void setPostIds(Long[] postIds) {
      this.postIds = postIds;
   }

   public Long getRoleId() {
      return this.roleId;
   }

   public void setRoleId(Long roleId) {
      this.roleId = roleId;
   }

   public String getAuthorLevel() {
      return this.authorLevel;
   }

   public void setAuthorLevel(String authorLevel) {
      this.authorLevel = authorLevel;
   }

   public String getMaxLevel() {
      return this.maxLevel;
   }

   public void setMaxLevel(String maxLevel) {
      this.maxLevel = maxLevel;
   }

   public Long getAuthorRoleId() {
      return this.authorRoleId;
   }

   public void setAuthorRoleId(Long authorRoleId) {
      this.authorRoleId = authorRoleId;
   }

   public List getUserRoleDepts() {
      return this.userRoleDepts;
   }

   public void setUserRoleDepts(List userRoleDepts) {
      this.userRoleDepts = userRoleDepts;
   }

   public Long getEmployeeId() {
      return this.employeeId;
   }

   public void setEmployeeId(Long employeeId) {
      this.employeeId = employeeId;
   }

   public String getCertCode() {
      return this.certCode;
   }

   public void setCertCode(String certCode) {
      this.certCode = certCode;
   }

   public String getHealthCode() {
      return this.healthCode;
   }

   public void setHealthCode(String healthCode) {
      this.healthCode = healthCode;
   }

   public String toString() {
      return (new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)).append("userId", this.getUserId()).append("deptId", this.getDeptId()).append("userName", this.getUserName()).append("nickName", this.getNickName()).append("email", this.getEmail()).append("phonenumber", this.getPhonenumber()).append("sex", this.getSex()).append("avatar", this.getAvatar()).append("password", this.getPassword()).append("salt", this.getSalt()).append("status", this.getStatus()).append("delFlag", this.getDelFlag()).append("loginIp", this.getLoginIp()).append("loginDate", this.getLoginDate()).append("createBy", this.getCreateBy()).append("createTime", this.getCreateTime()).append("updateBy", this.getUpdateBy()).append("updateTime", this.getUpdateTime()).append("remark", this.getRemark()).append("dept", this.getDept()).append("employeeId", this.getEmployeeId()).append("loginType", this.getLoginType()).toString();
   }
}
