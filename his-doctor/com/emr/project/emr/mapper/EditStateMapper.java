package com.emr.project.emr.mapper;

import com.emr.project.emr.domain.EditState;
import com.emr.project.emr.domain.vo.EditStateVo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EditStateMapper {
   EditState selectEditStateById(Long id);

   List selectDeitEditStateList(EditStateVo editStateVo);

   List selectEditStateList(EditState editState);

   int insertEditState(EditState editState);

   int updateEditState(EditState editState);

   int deleteEditStateById(Long id);

   int deleteEditStateByIds(Long[] ids);

   EditState selectEditStateByEmrId(Long emrId);

   EditState selectEditStateLastByEmrId(Long emrId);

   void updateCloseEdit(@Param("mine") int mine);
}
