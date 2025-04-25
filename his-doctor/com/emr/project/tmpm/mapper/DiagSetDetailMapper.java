package com.emr.project.tmpm.mapper;

import com.emr.project.tmpm.domain.DiagSetDetail;
import java.util.List;

public interface DiagSetDetailMapper {
   DiagSetDetail selectDiagSetDetailById(Long id);

   List selectDiagSetDetailList(DiagSetDetail diagSetDetail);

   int insertDiagSetDetail(DiagSetDetail diagSetDetail);

   int updateDiagSetDetail(DiagSetDetail diagSetDetail);

   int deleteDiagSetDetailById(Long id);

   int deleteDiagSetDetailByIds(Long[] ids);

   void insertDiagSetDetailList(List diagSetDetailList);

   List selectDetailListBySetCd(String setCd);

   void deleteDiagSetDetailBySetCd(String setCd);
}
