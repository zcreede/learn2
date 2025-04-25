package com.emr.project.emr.service.impl;

import com.emr.project.emr.domain.SealupFile;
import com.emr.project.emr.mapper.SealupFileMapper;
import com.emr.project.emr.service.ISealupFileService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SealupFileServiceImpl implements ISealupFileService {
   @Autowired
   private SealupFileMapper sealupFileMapper;

   public SealupFile selectSealupFileById(Long id) {
      return this.sealupFileMapper.selectSealupFileById(id);
   }

   public List selectSealupFileList(SealupFile sealupFile) {
      return this.sealupFileMapper.selectSealupFileList(sealupFile);
   }

   public int insertSealupFile(SealupFile sealupFile) {
      return this.sealupFileMapper.insertSealupFile(sealupFile);
   }

   public void insertSealupFileList(List sealupFileList) {
      this.sealupFileMapper.insertSealupFileList(sealupFileList);
   }

   public int updateSealupFile(SealupFile sealupFile) {
      return this.sealupFileMapper.updateSealupFile(sealupFile);
   }

   public int deleteSealupFileByIds(Long[] ids) {
      return this.sealupFileMapper.deleteSealupFileByIds(ids);
   }

   public int deleteSealupFileById(Long id) {
      return this.sealupFileMapper.deleteSealupFileById(id);
   }
}
