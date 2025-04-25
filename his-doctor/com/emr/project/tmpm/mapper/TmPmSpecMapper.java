package com.emr.project.tmpm.mapper;

import com.emr.project.tmpm.domain.TmPmSpec;
import com.emr.project.tmpm.domain.vo.TmPmSpecVo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TmPmSpecMapper {
   TmPmSpec selectTmPmSpecById(Long id);

   List selectTmPmSpecList(TmPmSpec tmPmSpec);

   int insertTmPmSpec(TmPmSpec tmPmSpec);

   int updateTmPmSpec(TmPmSpec tmPmSpec);

   int deleteTmPmSpecById(Long id);

   int deleteTmPmSpecByIds(Long[] ids);

   List selectTmPmSpecListByVo(TmPmSpecVo tmPmSpec);

   List selectBySpecCdList(@Param("specCdList") List specCdList);
}
