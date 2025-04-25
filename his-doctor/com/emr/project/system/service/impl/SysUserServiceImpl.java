package com.emr.project.system.service.impl;

import com.emr.common.exception.CustomException;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.common.utils.StringUtils;
import com.emr.framework.aspectj.lang.annotation.DataScope;
import com.emr.framework.web.service.ISyncService;
import com.emr.project.system.domain.BasEmployee;
import com.emr.project.system.domain.BsStaff;
import com.emr.project.system.domain.SysDept;
import com.emr.project.system.domain.SysPost;
import com.emr.project.system.domain.SysRole;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.domain.SysUserDept;
import com.emr.project.system.domain.SysUserPost;
import com.emr.project.system.domain.SysUserRole;
import com.emr.project.system.domain.vo.BasEmployeeVo;
import com.emr.project.system.domain.vo.SqlVo;
import com.emr.project.system.mapper.BsStaffMapper;
import com.emr.project.system.mapper.SysDeptMapper;
import com.emr.project.system.mapper.SysPostMapper;
import com.emr.project.system.mapper.SysRoleMapper;
import com.emr.project.system.mapper.SysUserDeptMapper;
import com.emr.project.system.mapper.SysUserMapper;
import com.emr.project.system.mapper.SysUserPostMapper;
import com.emr.project.system.mapper.SysUserRoleMapper;
import com.emr.project.system.service.IBasEmployeeService;
import com.emr.project.system.service.ISysConfigService;
import com.emr.project.system.service.ISysUserService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SysUserServiceImpl implements ISysUserService, ISyncService {
   private static final Logger log = LoggerFactory.getLogger(SysUserServiceImpl.class);
   @Autowired
   private SysUserMapper userMapper;
   @Autowired
   private SysRoleMapper roleMapper;
   @Autowired
   private SysPostMapper postMapper;
   @Autowired
   private SysUserRoleMapper userRoleMapper;
   @Autowired
   private SysUserPostMapper userPostMapper;
   @Autowired
   public SysUserDeptMapper sysUserDeptMapper;
   @Autowired
   private ISysConfigService configService;
   @Autowired
   private SysDeptMapper sysDeptMapper;
   @Autowired
   private IBasEmployeeService basEmployeeService;
   @Autowired
   private BsStaffMapper staffMapper;

   @DataScope(
      deptAlias = "d",
      userAlias = "u"
   )
   public List selectUserList(SysUser user) {
      return this.userMapper.selectUserList(user);
   }

   public List selectUserListByDeptId(SysUser user) throws Exception {
      List<SysUser> list = this.userMapper.selectUserListByDeptId(user);
      return list;
   }

   @DataScope(
      deptAlias = "d",
      userAlias = "u"
   )
   public List selectAllocatedList(SysUser user) {
      return this.userMapper.selectAllocatedList(user);
   }

   @DataScope(
      deptAlias = "d",
      userAlias = "u"
   )
   public List selectUnallocatedList(SysUser user) {
      return this.userMapper.selectUnallocatedList(user);
   }

   public SysUser selectUserByUserName(String userName) {
      return this.userMapper.selectUserByUserName(userName);
   }

   public SysUser selectUserById(Long userId) {
      return this.userMapper.selectUserById(userId);
   }

   public String selectUserRoleGroup(String userName) {
      List<SysRole> list = this.roleMapper.selectRolesByUserName(userName);
      StringBuffer idsStr = new StringBuffer();

      for(SysRole role : list) {
         idsStr.append(role.getRoleName()).append(",");
      }

      return StringUtils.isNotEmpty(idsStr.toString()) ? idsStr.substring(0, idsStr.length() - 1) : idsStr.toString();
   }

   public String selectUserPostGroup(String userName) {
      List<SysPost> list = this.postMapper.selectPostsByUserName(userName);
      StringBuffer idsStr = new StringBuffer();

      for(SysPost post : list) {
         idsStr.append(post.getPostName()).append(",");
      }

      return StringUtils.isNotEmpty(idsStr.toString()) ? idsStr.substring(0, idsStr.length() - 1) : idsStr.toString();
   }

   public String checkUserNameUnique(String userName) {
      int count = this.userMapper.checkUserNameUnique(userName);
      return count > 0 ? "1" : "0";
   }

   public String checkPhoneUnique(SysUser user) {
      Long userId = StringUtils.isNull(user.getUserId()) ? -1L : user.getUserId();
      SysUser info = this.userMapper.checkPhoneUnique(user.getPhonenumber());
      return StringUtils.isNotNull(info) && info.getUserId() != userId ? "1" : "0";
   }

   public String checkEmailUnique(SysUser user) {
      Long userId = StringUtils.isNull(user.getUserId()) ? -1L : user.getUserId();
      SysUser info = this.userMapper.checkEmailUnique(user.getEmail());
      return StringUtils.isNotNull(info) && info.getUserId() != userId ? "1" : "0";
   }

   public void checkUserAllowed(SysUser user) {
      if (StringUtils.isNotNull(user.getUserId()) && user.isAdmin()) {
         throw new CustomException("不允许操作超级管理员用户");
      }
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public int insertUser(SysUser user) throws Exception {
      BasEmployee basEmployee = this.basEmployeeService.selectByEmplNumber(user.getUserName());
      if (basEmployee != null) {
         user.setEmployeeId(basEmployee.getId());
      }

      user.setUserId(SnowIdUtils.uniqueLong());
      int rows = this.userMapper.insertUser(user);
      return rows;
   }

   @Transactional
   public int updateUser(SysUser user) throws Exception {
      return this.userMapper.updateUser(user);
   }

   @Transactional
   public void insertUserAuth(Long userId, Long[] roleIds) {
      this.userRoleMapper.deleteUserRoleByUserId(userId);
      this.insertUserRole(userId, roleIds);
   }

   public int updateUserStatus(SysUser user) {
      return this.userMapper.updateUser(user);
   }

   public int updateUserProfile(SysUser user) {
      return this.userMapper.updateUser(user);
   }

   public boolean updateUserAvatar(String userName, String avatar) {
      return this.userMapper.updateUserAvatar(userName, avatar) > 0;
   }

   public int resetPwd(SysUser user) {
      return this.userMapper.updateUser(user);
   }

   public int resetUserPwd(String userName, String password) {
      return this.userMapper.resetUserPwd(userName, password);
   }

   public void insertUserRole(SysUser user) {
      Long[] roles = user.getRoleIds();
      if (StringUtils.isNotNull(roles)) {
         List<SysUserRole> list = new ArrayList();

         for(Long var10000 : roles) {
            SysUserRole ur = new SysUserRole();
            list.add(ur);
         }

         if (list.size() > 0) {
            this.userRoleMapper.batchUserRole(list);
         }
      }

   }

   public void insertUserPost(BasEmployeeVo basEmployeeVo) throws Exception {
      Long[] posts = basEmployeeVo.getPostIds();
      if (StringUtils.isNotNull(posts)) {
         List<SysUserPost> list = new ArrayList();

         for(Long postId : posts) {
            SysUserPost up = new SysUserPost();
            up.setUserId(basEmployeeVo.getId());
            up.setPostId(postId);
            list.add(up);
         }

         if (list.size() > 0) {
            this.userPostMapper.batchUserPost(list);
         }
      }

   }

   public SysUser selectUserByCertSn(String certSn) throws Exception {
      return this.userMapper.selectUserByCertSn(certSn);
   }

   public void addUseCount(String deptCode, String[] staffCodes) {
      this.sysUserDeptMapper.addUseCount(deptCode, staffCodes);
   }

   public void reduceUseCount(String deptCode, String[] staffCodes) {
      this.sysUserDeptMapper.reduceUseCount(deptCode, staffCodes);
   }

   public void insertUserDept(SysUser user) {
      Long[] depts = user.getDeptIds();
      if (StringUtils.isNotNull(depts)) {
         List<SysUserDept> list = new ArrayList();

         for(Long var10000 : depts) {
            SysUserDept up = new SysUserDept();
            list.add(up);
         }

         if (list.size() > 0) {
            this.sysUserDeptMapper.batchUserDept(list);
         }
      }

   }

   public void insertUserRole(Long userId, Long[] roleIds) {
      if (StringUtils.isNotNull(roleIds)) {
         List<SysUserRole> list = new ArrayList();

         for(Long var10000 : roleIds) {
            SysUserRole ur = new SysUserRole();
            list.add(ur);
         }

         if (list.size() > 0) {
            this.userRoleMapper.batchUserRole(list);
         }
      }

   }

   @Transactional
   public int deleteUserById(Long userId) {
      this.userRoleMapper.deleteUserRoleByUserId(userId);
      this.userPostMapper.deleteUserPostByUserId(userId);
      return this.userMapper.deleteUserById(userId);
   }

   @Transactional
   public int deleteUserByIds(Long[] userIds) {
      for(Long userId : userIds) {
         this.checkUserAllowed(new SysUser(userId));
      }

      this.userRoleMapper.deleteUserRole(userIds);
      this.userPostMapper.deleteUserPost(userIds);
      return this.userMapper.deleteUserByIds(userIds);
   }

   public String importUser(List userList, Boolean isUpdateSupport, String operName) {
      if (!StringUtils.isNull(userList) && userList.size() != 0) {
         int successNum = 0;
         int failureNum = 0;
         StringBuilder successMsg = new StringBuilder();
         StringBuilder failureMsg = new StringBuilder();
         String password = this.configService.selectConfigByKey("sys.user.initPassword");

         for(SysUser user : userList) {
            try {
               SysUser u = this.userMapper.selectUserByUserName(user.getUserName());
               if (StringUtils.isNull(u)) {
                  user.setPassword(SecurityUtils.encryptPassword(password));
                  user.setCreateBy(operName);
                  this.insertUser(user);
                  ++successNum;
                  successMsg.append("<br/>" + successNum + "、账号 " + user.getUserName() + " 导入成功");
               } else if (isUpdateSupport) {
                  user.setUpdateBy(operName);
                  this.updateUser(user);
                  ++successNum;
                  successMsg.append("<br/>" + successNum + "、账号 " + user.getUserName() + " 更新成功");
               } else {
                  ++failureNum;
                  failureMsg.append("<br/>" + failureNum + "、账号 " + user.getUserName() + " 已存在");
               }
            } catch (Exception e) {
               ++failureNum;
               String msg = "<br/>" + failureNum + "、账号 " + user.getUserName() + " 导入失败：";
               failureMsg.append(msg + e.getMessage());
               log.error(msg, e);
            }
         }

         if (failureNum > 0) {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new CustomException(failureMsg.toString());
         } else {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
            return successMsg.toString();
         }
      } else {
         throw new CustomException("导入用户数据不能为空！");
      }
   }

   public List selectDoctors(String deptId, String status) {
      return this.userMapper.selectUserLiseByRoleType("c", deptId, status);
   }

   public List doctorsAndTechnician(String deptId, String staffName, String status) {
      String[] staffTypes = new String[]{"c", "k"};
      return this.userMapper.doctorsAndTechnician(staffTypes, deptId, staffName, status);
   }

   public List selectUserLiseByRoleNo() {
      return this.userMapper.selectUserLiseByRoleNo("00010033");
   }

   public List getStaffTypeBmy(SysUser sysUser) {
      List<SysUser> sysUserList = this.userMapper.getStaffTypeBmy(sysUser);
      return sysUserList;
   }

   public List selectDoctorsByName(String deptId, String staffName, String status) {
      return this.userMapper.selectUserLiseByRoleTypeAndName("c", deptId, staffName, status);
   }

   public List selectNurses(String deptId, String status) {
      return this.userMapper.selectUserLiseByRoleType("d", deptId, status);
   }

   public BsStaff queryByStaffNo(String staffNo) throws Exception {
      BsStaff param = new BsStaff();
      param.setStaffNo(staffNo);
      return this.staffMapper.selectByConn(param);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void syncData(List hisList) throws Exception {
      List<SysDept> deptList = this.sysDeptMapper.selectDeptList(new SysDept());
      deptList = (List)deptList.stream().filter((tx) -> StringUtils.isNotBlank(tx.getDeptCode())).collect(Collectors.toList());
      Map<String, List<SysDept>> deptMap = (Map)deptList.stream().collect(Collectors.groupingBy((tx) -> tx.getDeptCode()));
      int i = 0;

      for(Map t : hisList) {
         Long userId = SnowIdUtils.uniqueLong();
         t.put("USER_ID", userId);
         String roleType = t.get("ROLT_TYPE").toString();
         SysUserRole userRole = null;
         if (roleType.equals("c")) {
            userRole = new SysUserRole(t.get("USER_NAME").toString(), "doctor");
         }

         List<SysDept> tempList = (List)deptMap.get(t.get("DEPT_CODE"));
         String deptId = tempList != null && !tempList.isEmpty() ? ((SysDept)tempList.get(0)).getDeptCode() : null;
         log.info("i-> {}", i++);
         t.put("CREATE_BY", "admin");
         t.put("PASSWORD", SecurityUtils.encryptPassword("1234emr"));
         t.put("ORG_CODE", "0001");

         try {
            this.userMapper.insertMap(t);
            if (userRole != null) {
               List<SysUserRole> userRoleList = new ArrayList(1);
               userRoleList.add(userRole);
               this.userRoleMapper.batchUserRole(userRoleList);
            }

            if (deptId != null) {
               SysUserDept userDept = new SysUserDept(t.get("USER_NAME").toString(), deptId);
               userDept.setFlag("0");
               this.sysUserDeptMapper.insertSysUserDept(userDept);
            }
         } catch (Exception e) {
            for(String column : t.keySet()) {
               log.info("column名称：{},  值：{}", column, t.get(column));
            }

            log.error("", e.getMessage());
            throw new Exception(e.getMessage());
         }
      }

   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void syncAddData(List list, SqlVo sqlVo) throws Exception {
      List<SysDept> deptList = this.sysDeptMapper.selectDeptList(new SysDept());
      deptList = (List)deptList.stream().filter((tx) -> StringUtils.isNotBlank(tx.getDeptCode())).collect(Collectors.toList());
      Map<String, List<SysDept>> deptMap = (Map)deptList.stream().collect(Collectors.groupingBy((tx) -> tx.getDeptCode()));
      List<SysUser> emrUserList = this.userMapper.selctStaffList();
      List<String> staffCodeList = (List)emrUserList.stream().map((s) -> s.getUserName()).collect(Collectors.toList());
      Map<String, List<Map<String, Object>>> hisUserListMap = new HashMap();

      for(Map t : list) {
         String userName = t.get("USER_NAME").toString();
         List<Map<String, Object>> tempList = (List)hisUserListMap.get(userName);
         if (tempList == null) {
            tempList = new ArrayList(1);
         }

         tempList.add(t);
         hisUserListMap.put(userName, tempList);
      }

      int i = 0;

      for(String userName : hisUserListMap.keySet()) {
         List<Map<String, Object>> temphisUserList = (List)hisUserListMap.get(userName);
         Map<String, Object> t = (Map)temphisUserList.get(0);
         String roleType = t.get("ROLT_TYPE").toString();
         SysUserRole userRole = null;
         if (staffCodeList.contains(userName)) {
            List<SysUser> sysUserList = (List)emrUserList.stream().filter((s) -> s.getUserName().equals(userName)).collect(Collectors.toList());
            SysUser sysUser = CollectionUtils.isNotEmpty(sysUserList) ? (SysUser)sysUserList.get(0) : null;
            if (sysUser != null && roleType.equals("d")) {
               t.put("ROLT_TYPE", sysUser.getStaffType());
               t.put("TITLE", sysUser.getTitleName());
            }

            this.userMapper.deleteUserByUserName(userName);
            this.sysUserDeptMapper.deleteUserDeptByStaffCode(userName);
            t.put("PASSWORD", sysUser.getPassword());
         } else {
            if (roleType.equals("c")) {
               userRole = new SysUserRole(userName, "doctor");
            } else {
               userRole = new SysUserRole(userName, "lookEmr");
            }

            t.put("PASSWORD", SecurityUtils.encryptPassword("1234emr"));
         }

         Long userId = SnowIdUtils.uniqueLong();
         t.put("USER_ID", userId);
         log.info("i-> {}", i++);
         t.put("CREATE_BY", "admin");
         t.put("ORG_CODE", "0001");

         try {
            this.userMapper.insertMap(t);
            if (userRole != null) {
               List<SysUserRole> userRoleList = new ArrayList(1);
               userRoleList.add(userRole);
               this.userRoleMapper.batchUserRole(userRoleList);
            }

            List<SysUserDept> userDeptList = new ArrayList(1);

            for(Map temphisUser : temphisUserList) {
               List<SysDept> tempList = (List)deptMap.get(temphisUser.get("DEPT_CODE"));
               String deptId = tempList != null && !tempList.isEmpty() ? ((SysDept)tempList.get(0)).getDeptCode() : null;
               if (StringUtils.isNotBlank(deptId)) {
                  SysUserDept userDept = new SysUserDept(t.get("USER_NAME").toString(), deptId);
                  userDept.setFlag("0");
                  userDeptList.add(userDept);
               }
            }

            if (CollectionUtils.isNotEmpty(userDeptList)) {
               this.sysUserDeptMapper.batchUserDept(userDeptList);
            }
         } catch (Exception e) {
            for(String column : t.keySet()) {
               log.info("column名称：{},  值：{}", column, t.get(column));
            }

            log.error("", e.getMessage());
            throw new Exception(e.getMessage());
         }
      }

   }

   public void syncDataMap(List mapList, String tableName) throws Exception {
   }
}
