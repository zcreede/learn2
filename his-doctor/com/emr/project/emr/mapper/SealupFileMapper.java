package com.emr.project.emr.mapper;

import com.emr.project.emr.domain.SealupFile;
import java.util.List;

public interface SealupFileMapper {
   SealupFile selectSealupFileById(Long id);

   List selectSealupFileList(SealupFile sealupFile);

   int insertSealupFile(SealupFile sealupFile);

   void insertSealupFileList(List sealupFileList);

   int updateSealupFile(SealupFile sealupFile);

   int deleteSealupFileById(Long id);

   int deleteSealupFileByIds(Long[] ids);

   int deleteSealupFileByMainRecId(Long mainRecId);
}
