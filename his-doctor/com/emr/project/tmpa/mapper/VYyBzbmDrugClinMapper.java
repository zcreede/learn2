package com.emr.project.tmpa.mapper;

import com.emr.project.tmpa.domain.VYyBzbmDrugClin;
import java.util.List;

public interface VYyBzbmDrugClinMapper {
   VYyBzbmDrugClin selectVYyBzbmDrugClinById(String xmbh);

   List selectVYyBzbmDrugClinList(VYyBzbmDrugClin vYyBzbmDrugClin);

   int insertVYyBzbmDrugClin(VYyBzbmDrugClin vYyBzbmDrugClin);

   int updateVYyBzbmDrugClin(VYyBzbmDrugClin vYyBzbmDrugClin);

   int deleteVYyBzbmDrugClinById(String xmbh);

   int deleteVYyBzbmDrugClinByIds(String[] xmbhs);

   List selectBzbmDrugClinListByCpNoList(List cpNoList);
}
