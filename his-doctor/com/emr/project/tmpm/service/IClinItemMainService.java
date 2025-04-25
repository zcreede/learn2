package com.emr.project.tmpm.service;

import com.emr.framework.web.domain.AjaxResult;
import com.emr.project.tmpm.domain.ClinItemMain;
import java.util.List;

public interface IClinItemMainService {
   ClinItemMain selectClinItemMainById(String itemCd);

   List selectClinItemMainByItemCds(List itemCdList) throws Exception;

   List selectClinItemMainList(ClinItemMain clinItemMain);

   int insertClinItemMain(ClinItemMain clinItemMain);

   int updateClinItemMain(ClinItemMain clinItemMain);

   int deleteClinItemMainByIds(String[] itemCds);

   int deleteClinItemMainById(String itemCd);

   List selectClinList(String hospitalCode, String deptCode, String docCd, String itemFlagCd) throws Exception;

   AjaxResult verifyExtendedAttri(String patientId, List orderSaveVoList, List drugAndClinList, AjaxResult ajaxResult) throws Exception;

   Integer selectCountByItemFlagCd(String itemFlagCd);
}
