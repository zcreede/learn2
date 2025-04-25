package com.emr.project.tmpm.service;

import com.emr.project.tmpm.domain.ClinItemDetail;
import com.emr.project.tmpm.domain.vo.ClinItemDetailVo;
import java.util.List;

public interface IClinItemDetailService {
   ClinItemDetail selectClinItemDetailById(Long id);

   List selectClinItemDetailList(ClinItemDetail clinItemDetail);

   int insertClinItemDetail(ClinItemDetail clinItemDetail);

   int updateClinItemDetail(ClinItemDetail clinItemDetail);

   int deleteClinItemDetailByIds(Long[] ids);

   int deleteClinItemDetailById(Long id);

   List selectClinItemDetailByItemCds(List itemCdList) throws Exception;

   List selectClinItemDetailByItemCd(String itemCd) throws Exception;

   List selectOrderClinDetailList(ClinItemDetailVo clinItemDetailVo) throws Exception;
}
