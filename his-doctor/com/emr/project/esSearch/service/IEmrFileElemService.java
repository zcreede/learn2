package com.emr.project.esSearch.service;

import com.emr.project.esSearch.domain.EmrFileElem;
import java.util.List;

public interface IEmrFileElemService {
   void createEmrFileElemIndex() throws Exception;

   void dropEmrFileElemIndex() throws Exception;

   boolean syncEmrFileElemAllToEs() throws Exception;

   void syncEmrFileElemAddToEs(String beginDate, String endDate) throws Exception;

   List getMrFileIdByElem(EmrFileElem elem) throws Exception;

   List getDiasByElem(EmrFileElem param) throws Exception;
}
