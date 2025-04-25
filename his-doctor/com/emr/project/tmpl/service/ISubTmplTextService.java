package com.emr.project.tmpl.service;

import com.emr.project.tmpl.domain.SubTmplText;
import java.util.List;

public interface ISubTmplTextService {
   SubTmplText selectSubTmplTextById(Long id);

   List selectSubTmplTextList(SubTmplText subTmplText) throws Exception;

   void insertSubTmplText(SubTmplText subTmplText) throws Exception;

   void updateSubTmplText(SubTmplText subTmplText) throws Exception;

   void deleteSubTmplTextByIds(Long[] ids) throws Exception;

   int deleteSubTmplTextById(Long id);
}
