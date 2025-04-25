package com.emr.project.emr.service;

import com.emr.project.emr.domain.SealupFile;
import java.util.List;

public interface ISealupFileService {
   SealupFile selectSealupFileById(Long id);

   List selectSealupFileList(SealupFile sealupFile);

   int insertSealupFile(SealupFile sealupFile);

   void insertSealupFileList(List sealupFileList);

   int updateSealupFile(SealupFile sealupFile);

   int deleteSealupFileByIds(Long[] ids);

   int deleteSealupFileById(Long id);
}
