package com.emr.project.emr.service.impl;

import com.emr.project.emr.domain.EmrSignData;
import com.emr.project.emr.domain.vo.EmrSignDataVo;
import com.emr.project.emr.mapper.EmrSignDataMapper;
import com.emr.project.emr.service.IEmrSignDataService;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmrSignDataServiceImpl implements IEmrSignDataService {
   @Autowired
   private EmrSignDataMapper emrSignDataMapper;

   public EmrSignData selectEmrSignDataById(Long id) {
      return this.emrSignDataMapper.selectEmrSignDataById(id);
   }

   public List selectEmrSignDataList(EmrSignData emrSignData) {
      return this.emrSignDataMapper.selectEmrSignDataList(emrSignData);
   }

   public List selectEmrSignDataListByMrFileIds(EmrSignDataVo emrSignDataVo) {
      return this.emrSignDataMapper.selectEmrSignDataListByMrFileIds(emrSignDataVo);
   }

   public List selectEmrSignDataForFreeMoveList(EmrSignData emrSignData) {
      return this.emrSignDataMapper.selectEmrSignDataForFreeMoveList(emrSignData);
   }

   public int insertEmrSignData(EmrSignData emrSignData) {
      return this.emrSignDataMapper.insertEmrSignData(emrSignData);
   }

   public int updateEmrSignData(EmrSignData emrSignData) {
      return this.emrSignDataMapper.updateEmrSignData(emrSignData);
   }

   public int updateEmrSignDataForMove(EmrSignData emrSignData) {
      return this.emrSignDataMapper.updateEmrSignDataForMove(emrSignData);
   }

   public int updateEmrSignDataByFileId(EmrSignData emrSignData) {
      return this.emrSignDataMapper.updateEmrSignDataByFileId(emrSignData);
   }

   public int deleteEmrSignDataByIds(Long[] ids) {
      return this.emrSignDataMapper.deleteEmrSignDataByIds(ids);
   }

   public int deleteEmrSignDataById(Long id) {
      return this.emrSignDataMapper.deleteEmrSignDataById(id);
   }

   public void addList(List signDataList) {
      this.emrSignDataMapper.insertList(signDataList);
   }

   public void batchAddFreeMoveList(List signDataList) {
      this.emrSignDataMapper.batchAddFreeMoveList(signDataList);
   }

   public void addPatList(List signDataList) {
      this.emrSignDataMapper.insertPatList(signDataList);
   }

   public void updateCancelSign(EmrSignDataVo emrSignDataVo) throws Exception {
      if (CollectionUtils.isNotEmpty(emrSignDataVo.getIdList())) {
         this.emrSignDataMapper.updateCancelSign(emrSignDataVo);
      }

   }

   public void updateCancelSignForFreeMove(EmrSignDataVo emrSignDataVo) throws Exception {
      if (CollectionUtils.isNotEmpty(emrSignDataVo.getIdList())) {
         this.emrSignDataMapper.updateCancelSignForFreeMove(emrSignDataVo);
      }

   }

   public void updateCancelSignByFile(Long signFileId) throws Exception {
      if (signFileId != null) {
         this.emrSignDataMapper.updateCancelSignByFile(signFileId);
      }

   }

   public void updateCancelSignByFreeMove(Long signFileId, String docCode) throws Exception {
      this.emrSignDataMapper.updateCancelSignByFreeMove(signFileId, docCode);
   }
}
