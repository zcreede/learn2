package com.emr.project.emr.service;

import com.emr.project.emr.domain.BasEmrAcceAuthor;
import com.emr.project.emr.domain.vo.AcceRoleVo;
import com.emr.project.emr.domain.vo.EmrAcceVo;
import com.emr.project.system.domain.SysUser;
import java.util.List;

public interface IBasEmrAcceAuthorService {
   BasEmrAcceAuthor selectBasEmrAcceAuthorById(Long id);

   BasEmrAcceAuthor selectBasEmrAcceAuthorByObjectId(String objectId);

   List selectBasEmrAcceAuthorByObjectIds(List list);

   List selectBasEmrAcceAuthorList(BasEmrAcceAuthor basEmrAcceAuthor);

   int insertBasEmrAcceAuthor(BasEmrAcceAuthor basEmrAcceAuthor);

   int updateBasEmrAcceAuthor(BasEmrAcceAuthor basEmrAcceAuthor);

   int deleteBasEmrAcceAuthorByIds(Long[] ids);

   int deleteBasEmrAcceAuthorById(Long id);

   void editAcce(EmrAcceVo emrAcceVo) throws Exception;

   List selectRoleAcceList(AcceRoleVo acceRoleVo);

   List selectUserAcceList(SysUser sysUser) throws Exception;
}
