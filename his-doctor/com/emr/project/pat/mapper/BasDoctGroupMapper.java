package com.emr.project.pat.mapper;

import com.emr.project.pat.domain.BasDoctGroup;
import java.util.List;

public interface BasDoctGroupMapper {
   BasDoctGroup selectBasDoctGroupById(Long id);

   List selectBasDoctGroupList(BasDoctGroup basDoctGroup);

   int insertBasDoctGroup(BasDoctGroup basDoctGroup);

   int updateBasDoctGroup(BasDoctGroup basDoctGroup);

   int deleteBasDoctGroupById(Long id);

   int deleteBasDoctGroupByIds(Long[] ids);
}
