package com.emr.project.system.service.impl;

import com.emr.common.constant.CommonConstants;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.StringUtils;
import com.emr.common.utils.bean.BeanUtils;
import com.emr.framework.web.domain.TreeSelect;
import com.emr.project.system.domain.SysDept;
import com.emr.project.system.domain.SysOrg;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.domain.vo.SysOrgVo;
import com.emr.project.system.mapper.SysDeptMapper;
import com.emr.project.system.mapper.SysOrgMapper;
import com.emr.project.system.service.ISysOrgService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysOrgServiceImpl implements ISysOrgService {
   @Autowired
   private SysOrgMapper sysOrgMapper;
   @Resource
   private SysDeptMapper sysDeptMapper;

   public SysOrg selectSysOrgById(Long id) {
      return this.sysOrgMapper.selectSysOrgById(id);
   }

   public List selectSysOrgList(SysOrg sysOrg) {
      return this.sysOrgMapper.selectSysOrgList(sysOrg);
   }

   public int insertSysOrg(SysOrg sysOrg) {
      return this.sysOrgMapper.insertSysOrg(sysOrg);
   }

   public int updateSysOrg(SysOrg sysOrg) {
      return this.sysOrgMapper.updateSysOrg(sysOrg);
   }

   public int deleteSysOrgByIds(Long[] ids) {
      return this.sysOrgMapper.deleteSysOrgByIds(ids);
   }

   public int deleteSysOrgById(Long id) {
      return this.sysOrgMapper.deleteSysOrgById(id);
   }

   public List selectSysOrgDeptList(SysOrgVo sysOrgVo, boolean allDeptFlag) {
      String orgName = sysOrgVo.getOrgName();
      sysOrgVo.setOrgName((String)null);
      SysOrg org = new SysOrg();
      if (sysOrgVo != null) {
         BeanUtils.copyProperties(sysOrgVo, org);
      }

      List<SysOrg> list = this.sysOrgMapper.selectSysOrgList(org);
      List<SysOrg> orgList = new ArrayList();
      if (list != null && list.size() > 0) {
         for(SysOrg param : list) {
            if (param.getId().equals(CommonConstants.SYSTEM.ORG_ID)) {
               orgList.add(param);
            }

            List<SysOrg> deptList = this.sysOrgMapper.selectSysDept(param.getOrgCode(), orgName, sysOrgVo.getDeptFlag());
            if (CollectionUtils.isNotEmpty(deptList) && StringUtils.isNotBlank(sysOrgVo.getCurrentDeptFlag()) && "2".equals(sysOrgVo.getCurrentDeptFlag())) {
               SysUser user = SecurityUtils.getLoginUser().getUser();
               String deptCode = user.getDept().getDeptCode();
               deptList = (List)deptList.stream().filter((t) -> !deptCode.equals(t.getOrgCode())).collect(Collectors.toList());
            }

            if (deptList != null && deptList.size() > 0) {
               orgList.add(param);
               orgList.addAll(deptList);
            }
         }

         if (allDeptFlag) {
            SysOrg sysOrg = new SysOrg();
            sysOrg.setId(9999L);
            sysOrg.setParentId(100L);
            sysOrg.setParentCode("0000");
            sysOrg.setOrgCode("0000000");
            sysOrg.setOrgName("全部院区");
            orgList.add(1, sysOrg);
            SysOrg dept = new SysOrg();
            dept.setId(CommonConstants.SYSTEM.ALL_DEPT_ID);
            dept.setParentId(9999L);
            dept.setParentCode("0000000");
            dept.setOrgCode("000000");
            dept.setOrgName("全部科室");
            orgList.add(dept);
         }
      }

      return orgList;
   }

   public List buildOrgTreeSelect(List sysOrgList) {
      List<SysOrg> orgTrees = this.buildOrgTree(sysOrgList);
      return (List)orgTrees.stream().map(TreeSelect::new).collect(Collectors.toList());
   }

   public List buildOrgTree(List sysOrgList) {
      List<SysOrg> returnList = new ArrayList();
      List<String> tempList = new ArrayList();

      for(SysOrg org : sysOrgList) {
         tempList.add(org.getOrgCode());
      }

      for(SysOrg org : sysOrgList) {
         if (!tempList.contains(org.getParentCode())) {
            this.recursionFn(sysOrgList, org);
            returnList.add(org);
         }
      }

      if (returnList.isEmpty()) {
         returnList = sysOrgList;
      }

      return returnList;
   }

   public SysOrg getSysOrgByDept(SysDept dept) {
      SysOrg orgParam = new SysOrg();
      orgParam.setDelFlag("0");
      List<SysOrg> orgList = this.selectSysOrgList(orgParam);
      List<SysOrg> orgListTemp = (List)orgList.stream().filter((t) -> dept.getOrgCode().equals(t.getOrgCode())).collect(Collectors.toList());
      SysOrg res = orgListTemp != null && !orgListTemp.isEmpty() ? (SysOrg)orgListTemp.get(0) : null;
      return res;
   }

   public SysOrg checkOrgCodeUnique(String hospitalCode) {
      return this.sysOrgMapper.checkOrgCodeUnique(hospitalCode);
   }

   private void recursionFn(List list, SysOrg t) {
      List<SysOrg> childList = this.getChildList(list, t);
      t.setChildren(childList);

      for(SysOrg tChild : childList) {
         if (this.hasChild(list, tChild)) {
            this.recursionFn(list, tChild);
         }
      }

   }

   private List getChildList(List list, SysOrg t) {
      List<SysOrg> tlist = new ArrayList();

      for(SysOrg n : list) {
         if (StringUtils.isNotEmpty(n.getParentCode()) && n.getParentCode().equals(t.getOrgCode())) {
            tlist.add(n);
         }
      }

      return tlist;
   }

   private boolean hasChild(List list, SysOrg t) {
      return this.getChildList(list, t).size() > 0;
   }
}
