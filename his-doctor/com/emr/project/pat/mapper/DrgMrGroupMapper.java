package com.emr.project.pat.mapper;

import com.emr.project.pat.domain.DrgMrGroup;
import com.emr.project.pat.domain.vo.DrgMrGroupVo;
import java.util.List;

public interface DrgMrGroupMapper {
   DrgMrGroup selectDrgMrGroupById(Long id);

   List selectDrgMrGroupList(DrgMrGroup drgMrGroup);

   int insertDrgMrGroup(DrgMrGroup drgMrGroup);

   int updateDrgMrGroup(DrgMrGroup drgMrGroup);

   int deleteDrgMrGroupById(Long id);

   int deleteDrgMrGroupByIds(Long[] ids);

   DrgMrGroupVo selectInfo(DrgMrGroupVo drgMrGroupVo);
}
