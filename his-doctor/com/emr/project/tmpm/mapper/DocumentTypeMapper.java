package com.emr.project.tmpm.mapper;

import com.emr.project.tmpm.domain.DocumentType;
import java.util.List;

public interface DocumentTypeMapper {
   DocumentType selectDocumentTypeById(String documentTypeCd);

   List selectDocumentTypeList(DocumentType documentType);

   int insertDocumentType(DocumentType documentType);

   int updateDocumentType(DocumentType documentType);

   int deleteDocumentTypeById(String hospitalCode);

   int deleteDocumentTypeByIds(String[] hospitalCodes);

   List selectDocumentListByType(String documentClass, String deptCd, String inputFormat);
}
