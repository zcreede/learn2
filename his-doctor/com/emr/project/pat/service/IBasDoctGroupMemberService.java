package com.emr.project.pat.service;

import com.emr.project.pat.domain.BasDoctGroupMember;
import com.emr.project.pat.domain.vo.BasDoctGroupMemberVo;
import java.util.List;

public interface IBasDoctGroupMemberService {
   BasDoctGroupMember selectBasDoctGroupMemberById(Long id);

   List selectBasDoctGroupMemberList(BasDoctGroupMember basDoctGroupMember);

   int insertBasDoctGroupMember(BasDoctGroupMember basDoctGroupMember);

   int updateBasDoctGroupMember(BasDoctGroupMember basDoctGroupMember);

   int deleteBasDoctGroupMemberByIds(Long[] ids) throws Exception;

   int deleteBasDoctGroupMemberById(Long id);

   void insertMembers(BasDoctGroupMemberVo basDoctGroupMemberVo) throws Exception;

   List unGroupMemberList(BasDoctGroupMemberVo basDoctGroupMemberVo) throws Exception;

   List getGroupMemberList(String doctCd, String orgCd, String deptCd) throws Exception;

   List getUperDoctFromGroupMember(String doctCd, String orgCd, String deptCd) throws Exception;

   BasDoctGroupMember selectGroupDocInfo(String doctCd, String orgCd, String deptCd) throws Exception;
}
