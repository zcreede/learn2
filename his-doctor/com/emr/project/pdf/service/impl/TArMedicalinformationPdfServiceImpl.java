package com.emr.project.pdf.service.impl;

import com.emr.project.pdf.domain.TArMedicalinformationPdf;
import com.emr.project.pdf.mapper.TArMedicalinformationPdfMapper;
import com.emr.project.pdf.service.ITArMedicalinformationPdfService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TArMedicalinformationPdfServiceImpl implements ITArMedicalinformationPdfService {
   @Autowired
   private TArMedicalinformationPdfMapper tArMedicalinformationPdfMapper;

   public TArMedicalinformationPdf selectTArMedicalinformationPdfById(Long id) {
      return this.tArMedicalinformationPdfMapper.selectTArMedicalinformationPdfById(id);
   }

   public List selectTArMedicalinformationPdfList(TArMedicalinformationPdf tArMedicalinformationPdf) {
      return this.tArMedicalinformationPdfMapper.selectTArMedicalinformationPdfList(tArMedicalinformationPdf);
   }

   public List selectUnGenerateOrderPdfList(TArMedicalinformationPdf medicalinformationPdf) throws Exception {
      return this.tArMedicalinformationPdfMapper.selectUnGenerateOrderPdfList(medicalinformationPdf);
   }

   public List selectUnGenerateFeePdfList(TArMedicalinformationPdf medicalinformationPdf) throws Exception {
      return this.tArMedicalinformationPdfMapper.selectUnGenerateFeePdfList(medicalinformationPdf);
   }

   public TArMedicalinformationPdf selectMedByPatientId(String patientId) throws Exception {
      return this.tArMedicalinformationPdfMapper.selectMedByPatientId(patientId);
   }

   public int insertTArMedicalinformationPdf(TArMedicalinformationPdf tArMedicalinformationPdf) {
      return this.tArMedicalinformationPdfMapper.insertTArMedicalinformationPdf(tArMedicalinformationPdf);
   }

   public int batchInsert(List tArMedicalinformationPdfList) {
      return this.tArMedicalinformationPdfMapper.batchInsert(tArMedicalinformationPdfList);
   }

   public int updateTArMedicalinformationPdf(TArMedicalinformationPdf tArMedicalinformationPdf) {
      return this.tArMedicalinformationPdfMapper.updateTArMedicalinformationPdf(tArMedicalinformationPdf);
   }

   public int deleteTArMedicalinformationPdfByIds(Long[] ids) {
      return this.tArMedicalinformationPdfMapper.deleteTArMedicalinformationPdfByIds(ids);
   }

   public int deleteTArMedicalinformationPdfById(Long id) {
      return this.tArMedicalinformationPdfMapper.deleteTArMedicalinformationPdfById(id);
   }

   public TArMedicalinformationPdf lastLeaveHosDate(String genType) throws Exception {
      return this.tArMedicalinformationPdfMapper.lastLeaveHosDate(genType);
   }
}
