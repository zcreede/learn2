package com.emr.project.system.service.impl;

import com.emr.common.core.text.Convert;
import com.emr.common.exception.CustomException;
import com.emr.common.utils.PinYinUtil;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.common.utils.StringUtils;
import com.emr.framework.aspectj.lang.annotation.DataScope;
import com.emr.framework.web.domain.TreeSelect;
import com.emr.framework.web.service.ISyncService;
import com.emr.project.dr.domain.vo.TdCaConsApplyVo;
import com.emr.project.dr.service.ITdCaConsApplyService;
import com.emr.project.system.domain.BasEmployee;
import com.emr.project.system.domain.SysDept;
import com.emr.project.system.domain.SysEmployeeRoleDept;
import com.emr.project.system.domain.SysOrg;
import com.emr.project.system.domain.SysRole;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.domain.TmBsModule;
import com.emr.project.system.domain.vo.SelectHDIndexVo;
import com.emr.project.system.domain.vo.SqlVo;
import com.emr.project.system.domain.vo.SysDeptVo;
import com.emr.project.system.domain.vo.TmBsModuleVo;
import com.emr.project.system.mapper.SysDeptMapper;
import com.emr.project.system.mapper.SysRoleMapper;
import com.emr.project.system.service.IBasEmployeeService;
import com.emr.project.system.service.ISysDeptService;
import com.emr.project.system.service.ISysEmployeeRoleDeptService;
import com.emr.project.system.service.ISysOrgService;
import com.emr.project.system.service.ISysUserService;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SysDeptServiceImpl implements ISysDeptService, ISyncService {
   protected final Logger log = LoggerFactory.getLogger(SysDeptServiceImpl.class);
   @Autowired
   private SysDeptMapper deptMapper;
   @Autowired
   private SysRoleMapper roleMapper;
   @Autowired
   private ISysOrgService sysOrgService;
   @Autowired
   private IBasEmployeeService basEmployeeService;
   @Autowired
   private ISysUserService userService;
   @Autowired
   private ISysEmployeeRoleDeptService employeeRoleDeptService;
   @Autowired
   private ITdCaConsApplyService tdCaConsApplyService;

   @DataScope(
      deptAlias = "d"
   )
   public List selectDeptList(SysDept dept) {
      return this.deptMapper.selectDeptList(dept);
   }

   public List selectDeptPageList(SysDept dept) {
      return this.deptMapper.selectDeptPageList(dept);
   }

   public List queryUserDeptLeves(Long userId) throws Exception {
      List<SelectHDIndexVo> res = new ArrayList(1);
      SysOrg orgParam = new SysOrg();
      orgParam.setDelFlag("0");
      List<SysOrg> orgList = this.sysOrgService.selectSysOrgList(orgParam);
      SysUser user = this.userService.selectUserById(userId);
      BasEmployee basEmployee = this.basEmployeeService.selectBasEmployeeById(user.getEmployeeId());
      List<SysEmployeeRoleDept> employeeRoleDeptList = this.employeeRoleDeptService.selectDeptsByUserId(basEmployee.getId());
      if (employeeRoleDeptList != null && !employeeRoleDeptList.isEmpty()) {
         List<String> deptIdList = (List)employeeRoleDeptList.stream().map(SysEmployeeRoleDept::getDeptId).collect(Collectors.toList());
         List<TmBsModule> moduleList = SecurityUtils.getLoginUser().getUser().getModuleList();
         List<SysDept> deptList = this.deptMapper.selectByDeptIdsAndTypes(deptIdList);
         List<SysDept> parentDept = new ArrayList(1);

         for(SysDept dept : deptList) {
            for(SysOrg orgTemp : (List)orgList.stream().filter((t) -> dept.getOrgCode().equals(t.getOrgCode())).collect(Collectors.toList())) {
               SysDept orgDept = new SysDept();
               orgDept.setDeptId(orgTemp.getId());
               orgDept.setDeptCode(orgTemp.getOrgCode());
               orgDept.setDeptName(orgTemp.getOrgName());
               orgDept.setOrgCode(orgTemp.getOrgCode());
               parentDept.add(orgDept);
            }
         }

         if (!parentDept.isEmpty()) {
            for(SysDept dept : (List)parentDept.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet(Comparator.comparing(SysDept::getDeptId))), ArrayList::new))) {
               List<SysDept> tempList = (List)deptList.stream().filter((t) -> t.getOrgCode().equals(dept.getDeptCode())).collect(Collectors.toList());
               SelectHDIndexVo selectHDIndexVo = new SelectHDIndexVo(dept);
               selectHDIndexVo.setChildren(tempList);
               res.add(selectHDIndexVo);
            }
         }
      }

      return res;
   }

   public List filterDeptByModule(List selectHDIndexVoList, List moduleList) throws Exception {
      List<TmBsModuleVo> resList = new ArrayList(1);

      for(TmBsModule module : moduleList) {
         List<SelectHDIndexVo> selectHDIndexVoListTemp = new ArrayList(1);

         for(SelectHDIndexVo selectHDIndexVo : selectHDIndexVoList) {
            List<SysDept> deptListTemp = selectHDIndexVo.getChildren();
            List<SysDept> deptListTemp1 = null;
            if (module.getModuleCode().equals("0304")) {
               deptListTemp1 = (List)deptListTemp.stream().filter((t) -> StringUtils.isNotBlank(t.getDeptType()) && (t.getDeptType().equals("11") || t.getDeptType().equals("14"))).collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet(Comparator.comparing(SysDept::getDeptCode))), ArrayList::new));
            }

            if (module.getModuleCode().equals("0403")) {
               deptListTemp1 = (List)deptListTemp.stream().filter((t) -> StringUtils.isNotBlank(t.getDeptType()) && t.getDeptType().equals("05")).collect(Collectors.toList());
            }

            if (module.getModuleCode().equals("0506")) {
               deptListTemp1 = (List)deptListTemp.stream().filter((t) -> StringUtils.isNotBlank(t.getDeptType()) && t.getDeptType().equals("14")).collect(Collectors.toList());
            }

            if (CollectionUtils.isNotEmpty(deptListTemp1)) {
               deptListTemp1 = (List)deptListTemp1.stream().sorted(Comparator.comparing(SysDept::getDeptNamePym1)).collect(Collectors.toList());
               SelectHDIndexVo selectHDIndexVo1 = new SelectHDIndexVo();
               BeanUtils.copyProperties(selectHDIndexVo, selectHDIndexVo1);
               selectHDIndexVo1.setChildren(deptListTemp1);
               selectHDIndexVoListTemp.add(selectHDIndexVo1);
            }
         }

         if (CollectionUtils.isNotEmpty(selectHDIndexVoListTemp)) {
            TmBsModuleVo moduleVo = new TmBsModuleVo();
            BeanUtils.copyProperties(module, moduleVo);
            moduleVo.setSelectHDIndexVoList(selectHDIndexVoListTemp);
            resList.add(moduleVo);
         }
      }

      return resList;
   }

   public List buildDeptTree(List depts) {
      List<SysDept> returnList = new ArrayList();
      List<Long> tempList = new ArrayList();

      for(SysDept dept : depts) {
         tempList.add(dept.getDeptId());
      }

      for(SysDept dept : depts) {
         if (!tempList.contains(dept.getParentId())) {
            this.recursionFn(depts, dept);
            returnList.add(dept);
         }
      }

      if (returnList.isEmpty()) {
         returnList = depts;
      }

      return returnList;
   }

   public List buildDeptTreeSelect(List depts) {
      List<SysDept> deptTrees = this.buildDeptTree(depts);
      return (List)deptTrees.stream().map(TreeSelect::new).collect(Collectors.toList());
   }

   public List selectDeptListByRoleId(Long roleId) {
      SysRole role = this.roleMapper.selectRoleById(roleId);
      return this.deptMapper.selectDeptListByRoleId(roleId, role.isDeptCheckStrictly());
   }

   public SysDept selectDeptById(Long deptId) {
      return this.deptMapper.selectDeptById(deptId);
   }

   public int selectNormalChildrenDeptById(Long deptId) {
      return this.deptMapper.selectNormalChildrenDeptById(deptId);
   }

   public boolean hasChildByDeptId(Long deptId) {
      int result = this.deptMapper.hasChildByDeptId(deptId);
      return result > 0;
   }

   public boolean checkDeptExistUser(Long deptId) {
      int result = this.deptMapper.checkDeptExistUser(deptId);
      return result > 0;
   }

   public String checkDeptNameUnique(SysDept dept) {
      Long deptId = StringUtils.isNull(dept.getDeptId()) ? -1L : dept.getDeptId();
      SysDept info = this.deptMapper.checkDeptNameUnique(dept.getDeptName(), dept.getParentId());
      return StringUtils.isNotNull(info) && info.getDeptId() != deptId ? "1" : "0";
   }

   public String checkDeptCodeUnique(SysDept dept) {
      Long deptId = StringUtils.isNull(dept.getDeptId()) ? -1L : dept.getDeptId();
      SysDept info = this.deptMapper.checkDeptCodeUnique(dept.getDeptCode());
      return StringUtils.isNotNull(info) && info.getDeptId() != deptId ? "1" : "0";
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public int insertDept(SysDept dept) {
      SysOrg sysOrg = this.sysOrgService.selectSysOrgById(dept.getParentId());
      String ancestors = "";
      if (sysOrg != null) {
         ancestors = sysOrg.getParentId().toString();
      }

      SysDept info = this.deptMapper.selectDeptById(dept.getParentId());
      if (info != null) {
         ancestors = info.getAncestors();
         if (!"0".equals(info.getStatus())) {
            throw new CustomException("部门停用，不允许新增");
         }
      }

      dept.setAncestors(ancestors + "," + dept.getParentId());
      String searchCode = StringUtils.isNotBlank(dept.getDeptName()) ? PinYinUtil.getPinYinHeadChar(dept.getDeptName()) : null;
      dept.setSearchCode(searchCode);
      dept.setDeptId(SnowIdUtils.uniqueLong());
      return this.deptMapper.insertDept(dept);
   }

   public int updateDept(SysDept dept) {
      SysDept newParentDept = this.deptMapper.selectDeptById(dept.getParentId());
      SysDept oldDept = this.deptMapper.selectDeptById(dept.getDeptId());
      if (StringUtils.isNotNull(newParentDept) && StringUtils.isNotNull(oldDept)) {
         String newAncestors = newParentDept.getAncestors() + "," + newParentDept.getDeptId();
         String oldAncestors = oldDept.getAncestors();
         dept.setAncestors(newAncestors);
         this.updateDeptChildren(dept.getDeptId(), newAncestors, oldAncestors);
      }

      String searchCode = StringUtils.isNotBlank(dept.getDeptName()) ? PinYinUtil.getPinYinHeadChar(dept.getDeptName()) : null;
      dept.setSearchCode(searchCode);
      int result = this.deptMapper.updateDept(dept);
      if ("0".equals(dept.getStatus())) {
         this.updateParentDeptStatusNormal(dept);
      }

      return result;
   }

   private void updateParentDeptStatusNormal(SysDept dept) {
      String ancestors = dept.getAncestors();
      Long[] deptIds = Convert.toLongArray(ancestors);
      this.deptMapper.updateDeptStatusNormal(deptIds);
   }

   public void updateDeptChildren(Long deptId, String newAncestors, String oldAncestors) {
      List<SysDept> children = this.deptMapper.selectChildrenDeptById(deptId);

      for(SysDept child : children) {
         child.setAncestors(child.getAncestors().replaceFirst(oldAncestors, newAncestors));
      }

      if (children.size() > 0) {
         this.deptMapper.updateDeptChildren(children);
      }

   }

   public int deleteDeptById(Long deptId) {
      return this.deptMapper.deleteDeptById(deptId);
   }

   public List selectDeptByEmployeeId(String employeeId) {
      return this.deptMapper.selectDeptByEmployeeId(employeeId);
   }

   public List selectDeptByUserId(Long employeeId) {
      return this.deptMapper.selectDeptByUserId(employeeId);
   }

   public List selectSpecialTypeList(String sysFlag) throws Exception {
      return this.deptMapper.selectSpecialTypeList(sysFlag);
   }

   public List selectTmplDeptOrderList(SysDeptVo sysDeptVo) throws Exception {
      return this.deptMapper.selectTmplDeptOrderList(sysDeptVo);
   }

   public SysDept selectDeptByDeptCode(String deptCode, Long deptId) throws Exception {
      return this.deptMapper.selectDeptByDeptCode(deptCode, deptId);
   }

   public SysDept selectDeptByDeptName(String deptName, Long deptId) throws Exception {
      return this.deptMapper.selectDeptByDeptName(deptName, deptId);
   }

   private void recursionFn(List list, SysDept t) {
      List<SysDept> childList = this.getChildList(list, t);
      t.setChildren(childList);

      for(SysDept tChild : childList) {
         if (this.hasChild(list, tChild)) {
            this.recursionFn(list, tChild);
         }
      }

   }

   private List getChildList(List list, SysDept t) {
      List<SysDept> tlist = new ArrayList();

      for(SysDept n : list) {
         if (StringUtils.isNotNull(n.getParentId()) && n.getParentId() == t.getDeptId()) {
            tlist.add(n);
         }
      }

      return tlist;
   }

   private boolean hasChild(List list, SysDept t) {
      return this.getChildList(list, t).size() > 0;
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void syncData(List hisList) throws Exception {
      if (hisList != null && !hisList.isEmpty()) {
         this.deptMapper.deleteDept();
      }

      int i = 0;

      for(Map temp : hisList) {
         this.log.info("i-> {}", i++);

         try {
            this.deptMapper.insertMap(temp);
         } catch (Exception e) {
            for(String column : temp.keySet()) {
               this.log.info("column名称：{},  值：{}", column, temp.get(column));
            }

            this.log.error("", e.getMessage());
            throw new Exception(e.getMessage());
         }
      }

   }

   public void syncAddData(List list, SqlVo sqlVo) throws Exception {
      List<SysDept> emrDeptList = this.deptMapper.selectDeptList(new SysDept());
      List<String> deptCodeLsit = (List)emrDeptList.stream().map((s) -> s.getDeptCode()).collect(Collectors.toList());
      int i = 0;

      for(Map temp : list) {
         this.log.info("i-> {}", i++);

         try {
            if (deptCodeLsit.contains(temp.get("DEPT_CODE").toString())) {
               this.deptMapper.deleteDeptByDeptCode(temp.get("DEPT_CODE").toString());
            }

            this.deptMapper.insertMap(temp);
         } catch (Exception e) {
            for(String column : temp.keySet()) {
               this.log.info("column名称：{},  值：{}", column, temp.get(column));
            }

            this.log.error("", e.getMessage());
            throw new Exception(e.getMessage());
         }
      }

   }

   public void syncDataMap(List mapList, String tableName) throws Exception {
   }

   public List selectOrgDeptList(SysDept sysDept) throws Exception {
      SysOrg sysOrg = SecurityUtils.getLoginUser().getUser().getHospital();
      sysDept.setOrgCode(sysOrg.getOrgCode());
      return this.deptMapper.selectDeptList(sysDept);
   }

   public List selectDocDeptList(String emplNumber) throws Exception {
      return this.deptMapper.selectDocDeptList(emplNumber);
   }

   public List selectListByDeptCodeList(String hospitalCode, List deptCodeList) throws Exception {
      List<SysDept> list = new ArrayList(1);
      if (StringUtils.isNotBlank(hospitalCode) && deptCodeList != null && !deptCodeList.isEmpty()) {
         list = this.deptMapper.selectListByDeptCodeList(hospitalCode, deptCodeList);
      }

      return list;
   }

   public List orgHosDeptList(String patientId, String deptName) {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      SysDept param = new SysDept();
      param.setDeptName(deptName);
      param.setOrgCode(user.getHospital().getOrgCode());
      param.setDeptType("1");
      List<SysDept> list = this.selectDeptList(param);
      list = (List)list.stream().filter((t) -> !t.getDeptCode().equals(user.getDept().getDeptCode())).collect(Collectors.toList());
      if (StringUtils.isNotBlank(patientId)) {
         TdCaConsApplyVo caConsApplyparam = new TdCaConsApplyVo();
         caConsApplyparam.setPatientId(patientId);
         caConsApplyparam.setState("04");
         List<TdCaConsApplyVo> tdCaConsApplyVoList = this.tdCaConsApplyService.selectTdCaConsApplyList(caConsApplyparam);
         List<String> caConsApplyDeptCdList = (List<String>)(tdCaConsApplyVoList != null ? (List)tdCaConsApplyVoList.stream().map((t) -> t.getConsDeptCd()).distinct().collect(Collectors.toList()) : new ArrayList(1));
         List<SysDept> caConsApplyDeptList = (List)list.stream().filter((t) -> caConsApplyDeptCdList.contains(t.getDeptCode())).collect(Collectors.toList());
         List<SysDept> deptList = (List)list.stream().filter((t) -> !caConsApplyDeptCdList.contains(t.getDeptCode())).collect(Collectors.toList());
         caConsApplyDeptList.addAll(deptList);
         list = caConsApplyDeptList;
      }

      return list;
   }

   public Map getDeptMaps() {
      List<SysDept> deptList = this.deptMapper.selectAllDeptList();
      Map<String, String> deptMaps = (Map)deptList.stream().collect(Collectors.toMap(SysDept::getDeptCode, SysDept::getDeptName));
      return deptMaps;
   }

   public List selectdeptListByTypeCode(String typeCode) throws Exception {
      return this.deptMapper.selectdeptListByTypeCode(typeCode);
   }

   public List queryByDepCodeList(List depCode) {
      return this.deptMapper.selectListByDepCodeList(depCode);
   }

   public SysDept getOneByCode(String depCode) {
      return this.deptMapper.getOneByCode(depCode);
   }

   public List selectDeptZyList(SysDept dept) throws Exception {
      return this.deptMapper.selectDeptZyList(dept);
   }
}
