package com.emr.project.pat.service.impl;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.project.pat.domain.BasDoctGroup;
import com.emr.project.pat.domain.BasDoctGroupMember;
import com.emr.project.pat.mapper.BasDoctGroupMapper;
import com.emr.project.pat.mapper.BasDoctGroupMemberMapper;
import com.emr.project.pat.service.IBasDoctGroupService;
import com.emr.project.system.domain.SysOrg;
import com.emr.project.system.domain.SysUser;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BasDoctGroupServiceImpl implements IBasDoctGroupService {
   @Autowired
   private BasDoctGroupMapper basDoctGroupMapper;
   @Resource
   private BasDoctGroupMemberMapper basDoctGroupMemberMapper;

   public BasDoctGroup selectBasDoctGroupById(Long id) {
      return this.basDoctGroupMapper.selectBasDoctGroupById(id);
   }

   public List selectBasDoctGroupList(BasDoctGroup basDoctGroup) {
      return this.basDoctGroupMapper.selectBasDoctGroupList(basDoctGroup);
   }

   public int insertBasDoctGroup(BasDoctGroup basDoctGroup) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      SysOrg sysOrg = user.getHospital();
      basDoctGroup.setOrgCd(sysOrg.getOrgCode());
      basDoctGroup.setCrePerCode(user.getUserName());
      basDoctGroup.setCrePerName(user.getNickName());
      basDoctGroup.setId(SnowIdUtils.uniqueLong());
      basDoctGroup.setIsValid("1");
      return this.basDoctGroupMapper.insertBasDoctGroup(basDoctGroup);
   }

   public int updateBasDoctGroup(BasDoctGroup basDoctGroup) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      basDoctGroup.setAltPerCode(user.getUserName());
      basDoctGroup.setAltPerName(user.getNickName());
      return this.basDoctGroupMapper.updateBasDoctGroup(basDoctGroup);
   }

   public int deleteBasDoctGroupByIds(Long[] ids) {
      return this.basDoctGroupMapper.deleteBasDoctGroupByIds(ids);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public int deleteBasDoctGroupById(Long id) throws Exception {
      this.basDoctGroupMemberMapper.deleteBasDoctGroupMemberByGroupId(id);
      return this.basDoctGroupMapper.deleteBasDoctGroupById(id);
   }

   public List selectGroupList(BasDoctGroup basDoctGroup) {
      List<BasDoctGroup> groupList = this.basDoctGroupMapper.selectBasDoctGroupList(basDoctGroup);
      List<BasDoctGroupMember> memberList = this.basDoctGroupMemberMapper.selectBasDoctGroupMemberList(new BasDoctGroupMember());
      Map<Long, Long> map = (Map)memberList.stream().collect(Collectors.groupingBy(BasDoctGroupMember::getGroupId, Collectors.counting()));
      List<Long> longList = (List)memberList.stream().map((t) -> t.getGroupId()).distinct().collect(Collectors.toList());

      for(BasDoctGroup basDoctGroup1 : groupList) {
         if (longList.contains(basDoctGroup1.getId())) {
            Integer num = Integer.parseInt(String.valueOf(map.get(basDoctGroup1.getId())));
            basDoctGroup1.setNums(num);
         } else {
            basDoctGroup1.setNums(0);
         }
      }

      return groupList;
   }
}
