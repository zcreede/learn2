package com.emr.project.tmpm.service;

import com.emr.project.tmpm.domain.DocumentType;
import java.util.List;

public interface IDocumentTypeService {
   DocumentType selectDocumentTypeById(String hospitalCode);

   List selectDocumentTypeList(DocumentType documentType);

   int insertDocumentType(DocumentType documentType);

   int updateDocumentType(DocumentType documentType);

   int deleteDocumentTypeByIds(String[] hospitalCodes);

   int deleteDocumentTypeById(String hospitalCode);

   List selectDocumentListByType(String documentTypeCd, String inputFormat) throws Exception;
}
