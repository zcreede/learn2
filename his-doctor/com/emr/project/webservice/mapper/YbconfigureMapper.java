package com.emr.project.webservice.mapper;

import com.emr.project.webservice.domain.Ybconfigure;
import java.util.List;

public interface YbconfigureMapper {
   Ybconfigure selectYbconfigureById(Long id);

   List selectYbconfigureList(Ybconfigure ybconfigure);

   int insertYbconfigure(Ybconfigure ybconfigure);

   int batchInsert(List list);

   int updateYbconfigure(Ybconfigure ybconfigure);

   int batchUpdate(List list);

   int deleteYbconfigureById(Long id);

   int deleteYbconfigureByIds(Long[] ids);
}
