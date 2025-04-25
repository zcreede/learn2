package com.emr.project.tmpm.mapper;

import com.emr.project.tmpm.domain.DocumentTypeDept;
import java.util.List;

public interface DocumentTypeDeptMapper {
   DocumentTypeDept selectDocumentTypeDeptById(Long id);

   List selectDocumentTypeDeptList(DocumentTypeDept documentTypeDept);

   int insertDocumentTypeDept(DocumentTypeDept documentTypeDept);

   int updateDocumentTypeDept(DocumentTypeDept documentTypeDept);

   int deleteDocumentTypeDeptById(Long id);

   int deleteDocumentTypeDeptByIds(Long[] ids);
}
