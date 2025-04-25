package com.emr.project.emr.service;

import com.emr.project.emr.domain.EditState;
import com.emr.project.emr.domain.vo.EditStateVo;
import com.emr.project.pat.domain.vo.VisitinfoVo;
import java.util.List;

public interface IEditStateService {
   EditState selectEditStateById(Long id);

   List selectDeitEditStateList(EditStateVo editStateVo);

   List selectEditStateList(EditState editState);

   int insertEditState(EditState editState);

   void insertEditState(VisitinfoVo visitinfoVo, Long mrFileId, String fileName, String ip) throws Exception;

   int updateEditState(EditState editState);

   void updateEditState(Long mrFileId) throws Exception;

   int deleteEditStateByIds(Long[] ids);

   int deleteEditStateById(Long id);

   EditState selectEditStateByEmrId(Long emrId) throws Exception;

   EditState selectEditStateLastByEmrId(Long emrId) throws Exception;

   void updateCloseEdit(int mine) throws Exception;
}
