package com.emr.project.tmpm.service.impl;

import com.emr.common.utils.SecurityUtils;
import com.emr.project.system.domain.SysUser;
import com.emr.project.tmpm.domain.DocumentType;
import com.emr.project.tmpm.mapper.DocumentTypeMapper;
import com.emr.project.tmpm.service.IDocumentTypeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentTypeServiceImpl implements IDocumentTypeService {
   @Autowired
   private DocumentTypeMapper documentTypeMapper;

   public DocumentType selectDocumentTypeById(String documentTypeCd) {
      return this.documentTypeMapper.selectDocumentTypeById(documentTypeCd);
   }

   public List selectDocumentTypeList(DocumentType documentType) {
      return this.documentTypeMapper.selectDocumentTypeList(documentType);
   }

   public int insertDocumentType(DocumentType documentType) {
      return this.documentTypeMapper.insertDocumentType(documentType);
   }

   public int updateDocumentType(DocumentType documentType) {
      return this.documentTypeMapper.updateDocumentType(documentType);
   }

   public int deleteDocumentTypeByIds(String[] hospitalCodes) {
      return this.documentTypeMapper.deleteDocumentTypeByIds(hospitalCodes);
   }

   public int deleteDocumentTypeById(String hospitalCode) {
      return this.documentTypeMapper.deleteDocumentTypeById(hospitalCode);
   }

   public List selectDocumentListByType(String documentClass, String inputFormat) throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      return this.documentTypeMapper.selectDocumentListByType(documentClass, sysUser.getDept().getDeptCode(), inputFormat);
   }
}
