package com.emr.project.pat.service.impl;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.project.pat.domain.BasDoctGroupMember;
import com.emr.project.pat.domain.vo.BasDoctGroupMemberVo;
import com.emr.project.pat.mapper.BasDoctGroupMemberMapper;
import com.emr.project.pat.service.IBasDoctGroupMemberService;
import com.emr.project.system.domain.SysOrg;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.domain.vo.BasEmployeeVo;
import com.emr.project.system.service.IBasEmployeeService;
import com.emr.project.system.service.ISysUserService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BasDoctGroupMemberServiceImpl implements IBasDoctGroupMemberService {
   @Autowired
   private BasDoctGroupMemberMapper basDoctGroupMemberMapper;
   @Resource
   private ISysUserService sysUserService;
   @Autowired
   private IBasEmployeeService basEmployeeService;

   public BasDoctGroupMember selectBasDoctGroupMemberById(Long id) {
      return this.basDoctGroupMemberMapper.selectBasDoctGroupMemberById(id);
   }

   public List selectBasDoctGroupMemberList(BasDoctGroupMember basDoctGroupMember) {
      return this.basDoctGroupMemberMapper.selectBasDoctGroupMemberList(basDoctGroupMember);
   }

   public int insertBasDoctGroupMember(BasDoctGroupMember basDoctGroupMember) {
      return this.basDoctGroupMemberMapper.insertBasDoctGroupMember(basDoctGroupMember);
   }

   public int updateBasDoctGroupMember(BasDoctGroupMember basDoctGroupMember) {
      return this.basDoctGroupMemberMapper.updateBasDoctGroupMember(basDoctGroupMember);
   }

   public int deleteBasDoctGroupMemberByIds(Long[] ids) throws Exception {
      return this.basDoctGroupMemberMapper.deleteBasDoctGroupMemberByIds(ids);
   }

   public int deleteBasDoctGroupMemberById(Long id) {
      return this.basDoctGroupMemberMapper.deleteBasDoctGroupMemberById(id);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void insertMembers(BasDoctGroupMemberVo basDoctGroupMemberVo) throws Exception {
      List<BasDoctGroupMember> list = basDoctGroupMemberVo.getList();
      String doct_level = basDoctGroupMemberVo.getDocLevel();
      SysUser user = SecurityUtils.getLoginUser().getUser();
      SysOrg sysOrg = user.getHospital();

      for(BasDoctGroupMember param : list) {
         param.setId(SnowIdUtils.uniqueLong());
         param.setOrgCd(sysOrg.getOrgCode());
         param.setGroupId(basDoctGroupMemberVo.getGroupId());
         param.setCrePerCode(user.getUserName());
         param.setCrePerName(user.getNickName());
         param.setDocLevel(doct_level);
         param.setIsValid("1");
      }

      this.basDoctGroupMemberMapper.insertBasDoctGroupMemberList(list);
   }

   public List unGroupMemberList(BasDoctGroupMemberVo basDoctGroupMemberVo) throws Exception {
      List<BasEmployeeVo> resultList = new ArrayList();
      List<BasEmployeeVo> list = this.basEmployeeService.selectDeptBasEmployeeList(basDoctGroupMemberVo.getDeptCd());
      List<String> codeList = this.basDoctGroupMemberMapper.selectGroupMemberListByDeptCd(basDoctGroupMemberVo.getDeptCd());

      for(BasEmployeeVo user : list) {
         if (!codeList.contains(user.getEmplNumber())) {
            resultList.add(user);
         }
      }

      return resultList;
   }

   public List getGroupMemberList(String doctCd, String orgCd, String deptCd) throws Exception {
      return this.basDoctGroupMemberMapper.selectGroupMemberListByDocCd(doctCd, orgCd, deptCd);
   }

   public List getUperDoctFromGroupMember(String doctCd, String orgCd, String deptCd) throws Exception {
      List<BasDoctGroupMember> uperDoctList = new ArrayList(1);
      List<BasDoctGroupMember> doctGroupMemberList = this.getGroupMemberList(doctCd, orgCd, deptCd);
      if (doctGroupMemberList != null && !doctGroupMemberList.isEmpty()) {
         BasDoctGroupMember currentDoctMember = (BasDoctGroupMember)((List)doctGroupMemberList.stream().filter((t) -> t.getDocCode().equals(doctCd)).collect(Collectors.toList())).get(0);
         Map<String, List<BasDoctGroupMember>> doctGroupMemberListMap = (Map)doctGroupMemberList.stream().collect(Collectors.groupingBy((t) -> t.getDocLevel()));
         String currentDoctMemberLevel = currentDoctMember.getDocLevel();
         String resDocLevel = this.getUperDocLevel(currentDoctMemberLevel);
         if (resDocLevel != null) {
            List<BasDoctGroupMember> tmplList = (List)doctGroupMemberListMap.get(resDocLevel);
            if (tmplList != null && !tmplList.isEmpty()) {
               uperDoctList = tmplList;
            }
         }
      }

      return uperDoctList;
   }

   private String getUperDocLevel(String docLevel) {
      String resDocLevel = null;
      switch (docLevel) {
         case "1":
            resDocLevel = "2";
            break;
         case "2":
            resDocLevel = "3";
      }

      return resDocLevel;
   }

   public BasDoctGroupMember selectGroupDocInfo(String doctCd, String orgCd, String deptCd) throws Exception {
      return this.basDoctGroupMemberMapper.selectGroupDocInfo(doctCd, orgCd, deptCd);
   }
}
