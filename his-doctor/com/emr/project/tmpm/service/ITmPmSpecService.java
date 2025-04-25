package com.emr.project.tmpm.service;

import com.emr.project.tmpm.domain.TmPmSpec;
import com.emr.project.tmpm.domain.vo.TmPmSpecVo;
import java.util.List;

public interface ITmPmSpecService {
   TmPmSpec selectTmPmSpecById(Long id);

   List selectTmPmSpecList(TmPmSpec tmPmSpec);

   int insertTmPmSpec(TmPmSpec tmPmSpec);

   int updateTmPmSpec(TmPmSpec tmPmSpec);

   int deleteTmPmSpecByIds(Long[] ids);

   int deleteTmPmSpecById(Long id);

   List selectTmPmSpecListByVo(TmPmSpecVo tmPmSpec) throws Exception;

   List selectBySpecCdList(List specCdList) throws Exception;
}
