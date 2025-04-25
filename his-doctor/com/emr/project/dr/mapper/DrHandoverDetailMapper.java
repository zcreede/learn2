package com.emr.project.dr.mapper;

import com.emr.project.dr.domain.DrHandoverDetail;
import com.emr.project.dr.domain.vo.DrHandoverDetailVo;
import java.util.List;

public interface DrHandoverDetailMapper {
   DrHandoverDetail selectDrHandoverDetailById(Long id);

   List selectDrHandoverDetailList(DrHandoverDetailVo drHandoverDetail);

   int insertDrHandoverDetail(DrHandoverDetail drHandoverDetail);

   int insertDrHandoverDetailList(List drHandoverDetailList);

   int updateDrHandoverDetail(DrHandoverDetail drHandoverDetail);

   int deleteDrHandoverDetailById(Long id);

   int deleteDrHandoverDetailByIds(Long[] ids);

   void updateDetailDelFlag(DrHandoverDetail drHandoverDetail);

   DrHandoverDetail selectDetailByPatAndMainId(String patientId, Long mainId);

   List selectDetailListByMainId(Long mainId);
}
