package com.emr.project.dr.service;

import com.emr.project.dr.domain.DrHandoverDetail;
import com.emr.project.dr.domain.vo.DrHandoverDetailVo;
import com.emr.project.dr.domain.vo.DrHandoverPrintListVo;
import com.emr.project.dr.domain.vo.DrHandoverPrintVo;
import java.util.List;
import java.util.Map;

public interface IDrHandoverDetailService {
   DrHandoverDetail selectDrHandoverDetailById(Long id);

   List selectDrHandoverDetailList(DrHandoverDetailVo drHandoverDetail);

   List selectListByType(DrHandoverDetail drHandoverDetail) throws Exception;

   Map saveDrHandoverDetail(List detailList, Long mainId) throws Exception;

   int insertDrHandoverDetail(DrHandoverDetail drHandoverDetail);

   int updateDrHandoverDetail(DrHandoverDetail drHandoverDetail);

   int deleteDrHandoverDetailByIds(Long[] ids);

   int deleteDrHandoverDetailById(Long id);

   Map deleteDrHandoverDetail(DrHandoverDetail drHandoverDetail) throws Exception;

   List selectDetailListByMainId(Long mainId) throws Exception;

   List selectIsSavePatientId(DrHandoverDetailVo drHandoverDetail) throws Exception;

   DrHandoverPrintListVo selectDrPrintList(DrHandoverDetailVo detail) throws Exception;

   DrHandoverPrintVo printDetail(DrHandoverDetailVo drHandoverDetailVo) throws Exception;
}
