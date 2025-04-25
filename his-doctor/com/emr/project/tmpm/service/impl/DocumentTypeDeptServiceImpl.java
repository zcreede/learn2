package com.emr.project.tmpm.service.impl;

import com.emr.project.tmpm.domain.DocumentTypeDept;
import com.emr.project.tmpm.mapper.DocumentTypeDeptMapper;
import com.emr.project.tmpm.service.IDocumentTypeDeptService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentTypeDeptServiceImpl implements IDocumentTypeDeptService {
   @Autowired
   private DocumentTypeDeptMapper documentTypeDeptMapper;

   public DocumentTypeDept selectDocumentTypeDeptById(Long id) {
      return this.documentTypeDeptMapper.selectDocumentTypeDeptById(id);
   }

   public List selectDocumentTypeDeptList(DocumentTypeDept documentTypeDept) {
      return this.documentTypeDeptMapper.selectDocumentTypeDeptList(documentTypeDept);
   }

   public int insertDocumentTypeDept(DocumentTypeDept documentTypeDept) {
      return this.documentTypeDeptMapper.insertDocumentTypeDept(documentTypeDept);
   }

   public int updateDocumentTypeDept(DocumentTypeDept documentTypeDept) {
      return this.documentTypeDeptMapper.updateDocumentTypeDept(documentTypeDept);
   }

   public int deleteDocumentTypeDeptByIds(Long[] ids) {
      return this.documentTypeDeptMapper.deleteDocumentTypeDeptByIds(ids);
   }

   public int deleteDocumentTypeDeptById(Long id) {
      return this.documentTypeDeptMapper.deleteDocumentTypeDeptById(id);
   }
}
