package com.emr.project.system.mapper;

import com.emr.project.system.domain.ConfigureDept;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ConfigureDeptMapper {
   int deleteByPrimaryKey(Long id);

   int insert(ConfigureDept record);

   int insertSelective(ConfigureDept record);

   ConfigureDept selectByPrimaryKey(Long id);

   int updateByPrimaryKeySelective(ConfigureDept record);

   int updateByPrimaryKey(ConfigureDept record);

   List selectListByConn(ConfigureDept record);

   ConfigureDept selectByConn(ConfigureDept record);

   String selectArrearsByDeptCode(@Param("patientDepCode") String patientDepCode);
}
