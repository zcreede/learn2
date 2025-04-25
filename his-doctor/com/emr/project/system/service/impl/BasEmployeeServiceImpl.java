package com.emr.project.system.service.impl;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.common.utils.StringUtils;
import com.emr.common.utils.sql.SqlUtil;
import com.emr.framework.web.page.PageDomain;
import com.emr.framework.web.page.TableSupport;
import com.emr.framework.web.service.ISyncService;
import com.emr.project.system.domain.BasEmployee;
import com.emr.project.system.domain.SysDept;
import com.emr.project.system.domain.SysDictData;
import com.emr.project.system.domain.SysEmployeeRoleDept;
import com.emr.project.system.domain.SysOrg;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.domain.vo.BasEmployeeSearchVo;
import com.emr.project.system.domain.vo.BasEmployeeVo;
import com.emr.project.system.domain.vo.SqlVo;
import com.emr.project.system.domain.vo.SysEmployeeRoleDeptVo;
import com.emr.project.system.mapper.BasEmployeeMapper;
import com.emr.project.system.service.IBasEmployeeService;
import com.emr.project.system.service.ISysDeptService;
import com.emr.project.system.service.ISysDictDataService;
import com.emr.project.system.service.ISysEmployeeRoleDeptService;
import com.emr.project.system.service.ISysOrgService;
import com.emr.project.system.service.ISysPostService;
import com.emr.project.system.service.ISysUserService;
import com.github.pagehelper.PageHelper;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BasEmployeeServiceImpl implements IBasEmployeeService, ISyncService {
   private static final Logger log = LoggerFactory.getLogger(BasEmployeeServiceImpl.class);
   @Autowired
   private BasEmployeeMapper basEmployeeMapper;
   @Autowired
   private ISysDeptService deptService;
   @Autowired
   private ISysOrgService sysOrgService;
   @Autowired
   private ISysDictDataService sysDictDataService;
   @Autowired
   private ISysEmployeeRoleDeptService sysEmployeeRoleDeptService;
   @Autowired
   private ISysUserService sysUserService;
   @Autowired
   private ISysPostService sysPostService;

   public BasEmployeeVo selectBasEmployeeById(Long id) throws Exception {
      BasEmployeeVo basEmployeeVo = this.basEmployeeMapper.selectBasEmployeeById(id);
      SysEmployeeRoleDept sysEmployeeRoleDept = new SysEmployeeRoleDept();
      sysEmployeeRoleDept.setEmployeeId(basEmployeeVo.getEmplNumber());
      List<SysEmployeeRoleDeptVo> roleDeptVoList = this.sysEmployeeRoleDeptService.selectRoleDeptList(sysEmployeeRoleDept);
      basEmployeeVo.setUserRoleDepts(roleDeptVoList);
      return basEmployeeVo;
   }

   public List selectBasEmployeeList(BasEmployee basEmployee) throws Exception {
      basEmployee.setDelFlag("0");
      List<BasEmployeeVo> list = this.basEmployeeMapper.selectBasEmployeeList(basEmployee);
      String[] dictTypeArr = new String[]{"RC035", "s035"};
      List<SysDictData> dictDataList = this.sysDictDataService.selectDictDataListByType(dictTypeArr);
      Map<String, List<SysDictData>> dictDataListMap = (Map)dictDataList.stream().collect(Collectors.groupingBy((t) -> t.getDictType()));
      List<SysDictData> rc035 = (List)dictDataListMap.get("RC035");
      Map<String, SysDictData> rc035Map = (Map)rc035.stream().collect(Collectors.toMap((t) -> t.getDictValue(), Function.identity()));
      List<SysDictData> s035 = (List)dictDataListMap.get("s035");
      Map<String, SysDictData> s035Map = (Map)s035.stream().collect(Collectors.toMap((t) -> t.getDictValue(), Function.identity()));
      List<SysEmployeeRoleDeptVo> deptList = this.sysEmployeeRoleDeptService.selectDeptNameGroupByUser();
      List<SysEmployeeRoleDeptVo> roleDeptVoList = this.sysEmployeeRoleDeptService.selectRoleDeptList(new SysEmployeeRoleDept());
      Map<String, List<SysEmployeeRoleDeptVo>> roleDeptMap = (Map)roleDeptVoList.stream().collect(Collectors.groupingBy((i) -> i.getEmployeeId()));
      Map<String, List<SysEmployeeRoleDeptVo>> deptMap = new HashMap();
      if (deptList != null && deptList.size() > 0) {
         deptMap = (Map)deptList.stream().collect(Collectors.groupingBy((i) -> i.getEmployeeId()));
      }

      for(BasEmployeeVo basEmployeeVo : list) {
         if (deptMap != null && deptMap.size() > 0) {
            List<SysEmployeeRoleDeptVo> dept = (List)deptMap.get(basEmployeeVo.getId());
            if (dept != null && dept.size() > 0) {
               basEmployeeVo.setDeptName(((SysEmployeeRoleDeptVo)dept.get(0)).getDeptName());
            }
         }

         if (roleDeptMap != null && roleDeptMap.size() > 0) {
            basEmployeeVo.setUserRoleDepts((List)roleDeptMap.get(basEmployeeVo.getId()));
         }
      }

      list.forEach((t) -> {
         t.setNationName(StringUtils.isBlank(t.getNation()) ? null : ((SysDictData)rc035Map.get(t.getNation())).getDictLabel());
         t.setMariStatusName(StringUtils.isBlank(t.getMariStatus()) ? null : ((SysDictData)s035Map.get(t.getMariStatus())).getDictLabel());
      });
      return list;
   }

   public List searchList(BasEmployeeSearchVo basEmployee) throws Exception {
      List<BasEmployee> basEmployeeList = this.basEmployeeMapper.searchList(basEmployee);
      if (basEmployeeList != null && !basEmployeeList.isEmpty()) {
         basEmployeeList = (List)basEmployeeList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet(Comparator.comparing(BasEmployee::getId))), ArrayList::new));
      }

      if (StringUtils.isNotBlank(basEmployee.getDeptId())) {
         for(BasEmployee basEmployee1 : basEmployeeList) {
            basEmployee1.setDeptCode(basEmployee.getDeptCode());
            basEmployee1.setDeptName(basEmployee.getDeptName());
         }
      }

      return basEmployeeList;
   }

   public List searchListAll(BasEmployeeSearchVo basEmployee) throws Exception {
      List<BasEmployee> basEmployeeList = this.basEmployeeMapper.searchListAll(basEmployee);
      if (basEmployeeList != null && !basEmployeeList.isEmpty() && StringUtils.isBlank(basEmployee.getDeptId())) {
         basEmployeeList = (List)basEmployeeList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet(Comparator.comparing(BasEmployee::getId))), ArrayList::new));
      }

      if (StringUtils.isNotBlank(basEmployee.getDeptId())) {
         for(BasEmployee basEmployee1 : basEmployeeList) {
            basEmployee1.setDeptCode(basEmployee.getDeptCode());
            basEmployee1.setDeptName(basEmployee.getDeptName());
         }
      }

      return basEmployeeList;
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public int insertBasEmployee(BasEmployeeVo basEmployeeVo) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      Long id = SnowIdUtils.uniqueLong();
      basEmployeeVo.setId(id);
      basEmployeeVo.setCrePerCode(user.getUserName());
      basEmployeeVo.setCrePerName(user.getNickName());
      basEmployeeVo.setDelFlag("0");
      basEmployeeVo.setStatus("1");
      this.sysUserService.insertUserPost(basEmployeeVo);
      this.sysEmployeeRoleDeptService.delByUserId(basEmployeeVo.getId());
      List<SysEmployeeRoleDeptVo> userRoleDepts = basEmployeeVo.getUserRoleDepts();
      if (userRoleDepts != null && !userRoleDepts.isEmpty()) {
         userRoleDepts.forEach((t) -> t.setEmployeeId(user.getUserName()));
         this.basEmployeeMapper.batchUserRoleDept(userRoleDepts);
      }

      return this.basEmployeeMapper.insertBasEmployee(basEmployeeVo);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public int updateBasEmployee(BasEmployeeVo basEmployeeVo) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      basEmployeeVo.setAltPerCode(user.getUserName());
      basEmployeeVo.setAltPerName(user.getNickName());
      this.sysEmployeeRoleDeptService.delByUserId(basEmployeeVo.getId());
      this.sysPostService.deleteUserPostByUserId(basEmployeeVo.getId());
      this.sysUserService.insertUserPost(basEmployeeVo);
      List<SysEmployeeRoleDeptVo> userRoleDepts = basEmployeeVo.getUserRoleDepts();
      if (userRoleDepts != null && !userRoleDepts.isEmpty()) {
         userRoleDepts.forEach((t) -> t.setEmployeeId(basEmployeeVo.getEmplNumber()));
         this.basEmployeeMapper.batchUserRoleDept(userRoleDepts);
      }

      return this.basEmployeeMapper.updateBasEmployee(basEmployeeVo);
   }

   public void deleteBasEmployeeByIds(Long[] ids) throws Exception {
      this.basEmployeeMapper.updateDelFlagByIds(ids);
   }

   public void deleteBasEmployeeById(Long id) throws Exception {
      Long[] ids = new Long[]{id};
      this.basEmployeeMapper.updateDelFlagByIds(ids);
   }

   public BasEmployee selectByEmplNumber(String emplNumber) {
      BasEmployee param = new BasEmployee();
      param.setEmplNumber(emplNumber);
      param.setDelFlag("0");
      return this.basEmployeeMapper.selectBasEmployee(param);
   }

   protected void startPage() {
      PageDomain pageDomain = TableSupport.buildPageRequest();
      Integer pageNum = pageDomain.getPageNum();
      Integer pageSize = pageDomain.getPageSize();
      if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize)) {
         String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
         PageHelper.startPage(pageNum, pageSize, orderBy);
      }

   }

   public List selectAuthorizeList(BasEmployeeVo vo) throws Exception {
      List<BasEmployeeVo> list = this.basEmployeeMapper.selectAuthorizeList(vo);
      List<BasEmployeeVo> roleList = this.basEmployeeMapper.selectRoleEmployeeList();
      List<SysEmployeeRoleDeptVo> deptList = this.sysEmployeeRoleDeptService.selectDeptNameGroupByUserVO(vo);
      Map<String, List<BasEmployeeVo>> map = new HashMap();
      Map<String, List<SysEmployeeRoleDeptVo>> deptMap = new HashMap();
      if (roleList != null && roleList.size() > 0) {
         map = (Map)roleList.stream().collect(Collectors.groupingBy((i) -> i.getEmployeeId()));
      }

      if (deptList != null && deptList.size() > 0) {
         deptMap = (Map)deptList.stream().collect(Collectors.groupingBy((i) -> i.getEmployeeId()));
      }

      for(BasEmployeeVo basEmployeeVo : list) {
         if (map != null && map.size() > 0) {
            int level = 1;
            List<BasEmployeeVo> role = (List)map.get(basEmployeeVo.getEmplNumber());
            if (role != null && role.size() > 0) {
               for(BasEmployeeVo roleVo : role) {
                  if (StringUtils.isNotEmpty(roleVo.getAuthorLevel()) && level < Integer.parseInt(roleVo.getAuthorLevel())) {
                     level = Integer.parseInt(roleVo.getAuthorLevel());
                  }
               }
            }

            basEmployeeVo.setHighaAuthor(String.valueOf(level));
         }

         if (deptMap != null && deptMap.size() > 0) {
            List<SysEmployeeRoleDeptVo> dept = (List)deptMap.get(basEmployeeVo.getEmplNumber());
            if (dept != null && dept.size() > 0) {
               basEmployeeVo.setDeptName(((SysEmployeeRoleDeptVo)dept.get(0)).getDeptName());
            }
         }
      }

      return list;
   }

   public void batchUserRoleDept(List list) throws Exception {
      this.basEmployeeMapper.batchUserRoleDept(list);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void syncData(List hisList) throws Exception {
      List<SysOrg> orgList = this.sysOrgService.selectSysOrgList(new SysOrg());
      Map<Long, SysOrg> orgMap = (Map)orgList.stream().collect(Collectors.toMap((tx) -> tx.getId(), Function.identity()));
      List<SysDept> deptList = this.deptService.selectDeptList(new SysDept());
      deptList = (List)deptList.stream().filter((tx) -> StringUtils.isNotBlank(tx.getDeptCode())).collect(Collectors.toList());
      Map<String, List<SysDept>> deptMap = (Map)deptList.stream().collect(Collectors.groupingBy((tx) -> tx.getDeptCode()));
      int i = 0;

      for(Map t : hisList) {
         Long id = SnowIdUtils.uniqueLong();
         t.put("ID", id);
         t.put("CRE_PER_CODE", "admin");
         t.put("CRE_PER_NAME", "admin");
         List<SysDept> tempList = (List)deptMap.get(t.get("DEPT_CODE"));
         String deptName = tempList != null && !tempList.isEmpty() ? ((SysDept)tempList.get(0)).getDeptName() : null;
         t.put("DEPT_NAME", deptName);
         log.info("i-> {}", i++);
         SysEmployeeRoleDept sysEmployeeRoleDept = new SysEmployeeRoleDept();

         try {
            BasEmployee basEmployee = this.selectByEmplNumber(t.get("EMPL_NUMBER").toString());
            if (basEmployee == null) {
               this.basEmployeeMapper.insertMap(t);
            } else {
               sysEmployeeRoleDept.setEmployeeId(basEmployee.getEmplNumber());
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

   public void syncAddData(List list, SqlVo sqlVo) throws Exception {
   }

   public void syncDataMap(List mapList, String tableName) throws Exception {
   }

   public List selectDoctors(BasEmployeeVo basEmployeeVo) throws Exception {
      basEmployeeVo.setRoleType("c");
      return this.basEmployeeMapper.selectUserLiseByRoleType(basEmployeeVo);
   }

   public List selectDeptBasEmployeeList(String deptCode) throws Exception {
      return this.basEmployeeMapper.selectDeptBasEmployeeList(deptCode);
   }

   public List selectBasEmployeeAllList() throws Exception {
      return this.basEmployeeMapper.selectBasEmployeeAllList();
   }
}
