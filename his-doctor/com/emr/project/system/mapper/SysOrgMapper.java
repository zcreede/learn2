package com.emr.project.system.mapper;

import com.emr.project.system.domain.SysOrg;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysOrgMapper {
   SysOrg selectSysOrgById(Long id);

   List selectSysOrgList(SysOrg sysOrg);

   int insertSysOrg(SysOrg sysOrg);

   int updateSysOrg(SysOrg sysOrg);

   int deleteSysOrgById(Long id);

   int deleteSysOrgByIds(Long[] ids);

   SysOrg checkOrgNameUnique(@Param("orgName") String orgName, @Param("parentId") Long parentId);

   SysOrg checkOrgCodeUnique(@Param("orgCode") String orgCode);

   int hasChildById(Long id);

   int selectNormalChildrenById(Long id);

   List selectSysDept(@Param("parentId") String parentId, @Param("orgName") String orgName, @Param("deptFlag") String deptFlag);

   SysOrg selectSysOrgByOrgNo(@Param("hospitalNo") String orgCode);
}
