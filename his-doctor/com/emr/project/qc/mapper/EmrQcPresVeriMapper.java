package com.emr.project.qc.mapper;

import com.emr.project.qc.domain.EmrQcPresVeri;
import java.util.List;

public interface EmrQcPresVeriMapper {
   EmrQcPresVeri selectEmrQcPresVeriById(Long id);

   List selectEmrQcPresVeriList(EmrQcPresVeri emrQcPresVeri);

   int insertEmrQcPresVeri(EmrQcPresVeri emrQcPresVeri);

   int updateEmrQcPresVeri(EmrQcPresVeri emrQcPresVeri);

   int deleteEmrQcPresVeriById(Long id);

   int deleteEmrQcPresVeriByIds(Long[] ids);

   void insertList(List list);
}
