package com.emr.project.system.mapper;

import com.emr.project.system.domain.ConfigureHospital;
import java.util.List;

public interface ConfigureHospitalMapper {
   int deleteByPrimaryKey(Long id);

   int insert(ConfigureHospital record);

   int insertSelective(ConfigureHospital record);

   ConfigureHospital selectByPrimaryKey(Long id);

   int updateByPrimaryKeySelective(ConfigureHospital record);

   int updateByPrimaryKey(ConfigureHospital record);

   ConfigureHospital selectByConn(ConfigureHospital record);

   List selectListByConn(ConfigureHospital record);
}
