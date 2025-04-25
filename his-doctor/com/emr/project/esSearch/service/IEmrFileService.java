package com.emr.project.esSearch.service;

import com.emr.project.esSearch.domain.EmrFile;
import com.emr.project.esSearch.domain.vo.EmrFileSearchVo;
import java.util.List;
import org.zxp.esclientrhl.repository.PageList;

public interface IEmrFileService {
   void createEmrFileIndex() throws Exception;

   void dropEmrFileIndex() throws Exception;

   boolean syncEmrFileAllToEs() throws Exception;

   void syncEmrFileAddToEs(String beginDate, String endDate) throws Exception;

   void syncEmrFileAddToEs(String patiengId) throws Exception;

   List queryAll(EmrFile emrFile) throws Exception;

   PageList queryHighLight(EmrFileSearchVo emrFileSearchVo, int currentPage, int pageSize) throws Exception;

   List queryHighLightExport(EmrFileSearchVo emrFileSearchVo) throws Exception;

   PageList queryHighLight(Long caseId, int currentPage, int pageSize) throws Exception;

   void deleteEmrFileElemAddToEs(String beginDate, String endDate) throws Exception;

   void deleteEmrFileElemAllToEs() throws Exception;
}
