package com.emr.project.tmpm.mapper;

import com.emr.project.tmpm.domain.ClinItemMain;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ClinItemMainMapper {
   ClinItemMain selectClinItemMainById(String itemCd);

   List selectClinItemMainByItemCds(List itemCdList);

   List selectClinItemMainList(ClinItemMain clinItemMain);

   int insertClinItemMain(ClinItemMain clinItemMain);

   int updateClinItemMain(ClinItemMain clinItemMain);

   int deleteClinItemMainById(String itemCd);

   int deleteClinItemMainByIds(String[] itemCds);

   List selectClinList(@Param("hospitalCode") String hospitalCode, @Param("deptCode") String deptCode, @Param("docCd") String docCd, @Param("itemFlagCd") String itemFlagCd);

   List selectClinItemMainByItemCdList(@Param("list") List cpNoList);

   Integer selectCountByItemFlagCd(@Param("itemFlagCd") String itemFlagCd);
}
