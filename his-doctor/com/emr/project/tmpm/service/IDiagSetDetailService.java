package com.emr.project.tmpm.service;

import com.emr.project.tmpm.domain.DiagSetDetail;
import java.util.List;

public interface IDiagSetDetailService {
   DiagSetDetail selectDiagSetDetailById(Long id);

   List selectDiagSetDetailList(DiagSetDetail diagSetDetail);

   int insertDiagSetDetail(DiagSetDetail diagSetDetail);

   void insertDiagSetDetailList(List diagSetDetailList) throws Exception;

   int updateDiagSetDetail(DiagSetDetail diagSetDetail);

   int deleteDiagSetDetailByIds(Long[] ids);

   int deleteDiagSetDetailById(Long id);

   void deleteDiagSetDetailBySetCd(String setCd) throws Exception;

   List selectDetailListBySetCd(String setCd) throws Exception;

   void saveDiagSetDetail(List diagSetDetailList) throws Exception;
}
