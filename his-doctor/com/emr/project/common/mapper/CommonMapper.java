package com.emr.project.common.mapper;

import com.emr.project.sys.domain.TmBsDefineConfigureP;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CommonMapper {
   String getSysdate();

   String getSystimestamp();

   TmBsDefineConfigureP selectDefineConfigureByCode(@Param("code") String code);

   List selectNursingLevelList();
}
