package com.emr.project.pdf.service;

import com.emr.project.pdf.domain.TArMedicalinformationPdf;
import java.util.List;

public interface ITArMedicalinformationPdfService {
   TArMedicalinformationPdf selectTArMedicalinformationPdfById(Long id);

   List selectTArMedicalinformationPdfList(TArMedicalinformationPdf tArMedicalinformationPdf);

   List selectUnGenerateOrderPdfList(TArMedicalinformationPdf medicalinformationPdf) throws Exception;

   List selectUnGenerateFeePdfList(TArMedicalinformationPdf medicalinformationPdf) throws Exception;

   TArMedicalinformationPdf selectMedByPatientId(String patientId) throws Exception;

   int insertTArMedicalinformationPdf(TArMedicalinformationPdf tArMedicalinformationPdf);

   int batchInsert(List tArMedicalinformationPdfList);

   int updateTArMedicalinformationPdf(TArMedicalinformationPdf tArMedicalinformationPdf);

   int deleteTArMedicalinformationPdfByIds(Long[] ids);

   int deleteTArMedicalinformationPdfById(Long id);

   TArMedicalinformationPdf lastLeaveHosDate(String genType) throws Exception;
}
