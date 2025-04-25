package com.emr.project.pdf.mapper;

import com.emr.project.pdf.domain.TArMedicalinformationPdf;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TArMedicalinformationPdfMapper {
   TArMedicalinformationPdf selectTArMedicalinformationPdfById(Long id);

   List selectTArMedicalinformationPdfList(TArMedicalinformationPdf tArMedicalinformationPdf);

   List selectUnGenerateOrderPdfList(TArMedicalinformationPdf medicalinformationPdf);

   List selectUnGenerateFeePdfList(TArMedicalinformationPdf medicalinformationPdf);

   TArMedicalinformationPdf selectMedByPatientId(String patientId);

   int insertTArMedicalinformationPdf(TArMedicalinformationPdf tArMedicalinformationPdf);

   int batchInsert(@Param("list") List tArMedicalinformationPdfList);

   int updateTArMedicalinformationPdf(TArMedicalinformationPdf tArMedicalinformationPdf);

   int batchUpdate(@Param("list") List tArMedicalinformationPdfList);

   int deleteTArMedicalinformationPdfById(Long id);

   int deleteTArMedicalinformationPdfByIds(Long[] ids);

   TArMedicalinformationPdf lastLeaveHosDate(String genType);
}
