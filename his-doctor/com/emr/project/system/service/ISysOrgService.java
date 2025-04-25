package com.emr.project.system.service;

import com.emr.project.system.domain.SysDept;
import com.emr.project.system.domain.SysOrg;
import com.emr.project.system.domain.vo.SysOrgVo;
import java.util.List;

public interface ISysOrgService {
   SysOrg selectSysOrgById(Long id);

   List selectSysOrgList(SysOrg sysOrg);

   int insertSysOrg(SysOrg sysOrg);

   int updateSysOrg(SysOrg sysOrg);

   int deleteSysOrgByIds(Long[] ids);

   int deleteSysOrgById(Long id);

   List selectSysOrgDeptList(SysOrgVo sysOrgVo, boolean allDeptFlag);

   List buildOrgTreeSelect(List sysOrgList);

   List buildOrgTree(List sysOrgList);

   SysOrg getSysOrgByDept(SysDept dept);

   SysOrg checkOrgCodeUnique(String hospitalCode);
}
