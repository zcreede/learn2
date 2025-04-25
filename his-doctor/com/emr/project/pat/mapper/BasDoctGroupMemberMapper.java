package com.emr.project.pat.mapper;

import com.emr.project.pat.domain.BasDoctGroupMember;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BasDoctGroupMemberMapper {
   BasDoctGroupMember selectBasDoctGroupMemberById(Long id);

   List selectBasDoctGroupMemberList(BasDoctGroupMember basDoctGroupMember);

   int insertBasDoctGroupMember(BasDoctGroupMember basDoctGroupMember);

   void insertBasDoctGroupMemberList(List basDoctGroupMemberList);

   int updateBasDoctGroupMember(BasDoctGroupMember basDoctGroupMember);

   int deleteBasDoctGroupMemberById(Long id);

   int deleteBasDoctGroupMemberByIds(Long[] ids);

   int deleteBasDoctGroupMemberByGroupId(Long groupId);

   List selectGroupMemberListByDocCd(@Param("docCode") String docCode, @Param("orgCd") String orgCd, @Param("deptCd") String deptCd);

   List selectGroupMemberListByDeptCd(String deptCd);

   BasDoctGroupMember selectGroupDocInfo(@Param("docCode") String docCode, @Param("orgCd") String orgCd, @Param("deptCd") String deptCd);
}
