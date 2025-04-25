package com.emr.project.pat.service;

import com.emr.project.pat.domain.BasDoctGroup;
import java.util.List;

public interface IBasDoctGroupService {
   BasDoctGroup selectBasDoctGroupById(Long id);

   List selectBasDoctGroupList(BasDoctGroup basDoctGroup);

   int insertBasDoctGroup(BasDoctGroup basDoctGroup) throws Exception;

   int updateBasDoctGroup(BasDoctGroup basDoctGroup) throws Exception;

   int deleteBasDoctGroupByIds(Long[] ids);

   int deleteBasDoctGroupById(Long id) throws Exception;

   List selectGroupList(BasDoctGroup basDoctGroup);
}
