package com.emr.project.tmpl.mapper;

import com.emr.project.tmpl.domain.SubTmplText;
import java.util.List;

public interface SubTmplTextMapper {
   SubTmplText selectSubTmplTextById(Long id);

   List selectSubTmplTextList(SubTmplText subTmplText);

   int insertSubTmplText(SubTmplText subTmplText);

   int updateSubTmplText(SubTmplText subTmplText);

   int deleteSubTmplTextById(Long id);

   int deleteSubTmplTextByIds(Long[] ids);
}
