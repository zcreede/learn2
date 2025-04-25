package com.emr.project.pat.service;

import com.emr.project.pat.domain.DrgMrGroup;
import com.emr.project.pat.domain.vo.DrgMrGroupVo;
import java.util.List;

public interface IDrgMrGroupService {
   DrgMrGroup selectDrgMrGroupById(Long id);

   List selectDrgMrGroupList(DrgMrGroup drgMrGroup);

   int insertDrgMrGroup(DrgMrGroup drgMrGroup);

   int updateDrgMrGroup(DrgMrGroup drgMrGroup) throws Exception;

   int deleteDrgMrGroupByIds(Long[] ids);

   int deleteDrgMrGroupById(Long id);

   DrgMrGroupVo selectInfo(DrgMrGroupVo drgMrGroupVo) throws Exception;
}
