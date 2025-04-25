package com.emr.project.emr.mapper;

import com.emr.project.emr.domain.EmrBinary;
import java.util.List;

public interface EmrBinaryMapper {
   EmrBinary selectEmrBinaryById(Long mrFileId);

   List selectEmrBinaryList(EmrBinary emrBinary);

   int insertEmrBinary(EmrBinary emrBinary);

   int updateEmrBinary(EmrBinary emrBinary);

   int deleteEmrBinaryById(Long mrFileId);

   int deleteEmrBinaryByIds(Long[] mrFileIds);

   List selectListByIds(List mrFileIds);
}
