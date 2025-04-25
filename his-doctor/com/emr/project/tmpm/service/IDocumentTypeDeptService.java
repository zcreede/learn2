package com.emr.project.tmpm.service;

import com.emr.project.tmpm.domain.DocumentTypeDept;
import java.util.List;

public interface IDocumentTypeDeptService {
   DocumentTypeDept selectDocumentTypeDeptById(Long id);

   List selectDocumentTypeDeptList(DocumentTypeDept documentTypeDept);

   int insertDocumentTypeDept(DocumentTypeDept documentTypeDept);

   int updateDocumentTypeDept(DocumentTypeDept documentTypeDept);

   int deleteDocumentTypeDeptByIds(Long[] ids);

   int deleteDocumentTypeDeptById(Long id);
}
