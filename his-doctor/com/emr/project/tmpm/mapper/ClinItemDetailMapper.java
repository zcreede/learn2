package com.emr.project.tmpm.mapper;

import com.emr.project.tmpm.domain.ClinItemDetail;
import java.util.List;

public interface ClinItemDetailMapper {
   ClinItemDetail selectClinItemDetailById(Long id);

   List selectClinItemDetailList(ClinItemDetail clinItemDetail);

   int insertClinItemDetail(ClinItemDetail clinItemDetail);

   int updateClinItemDetail(ClinItemDetail clinItemDetail);

   int deleteClinItemDetailById(Long id);

   int deleteClinItemDetailByIds(Long[] ids);

   List selectClinItemDetailByItemCds(List itemCdList);

   List selectClinItemDetailByItemCd(String itemCd);

   List selectClinItemDetailBySetCd(String setCd);
}
