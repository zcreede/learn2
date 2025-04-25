package com.emr.project.docOrder.service.impl;

import com.emr.project.docOrder.service.EmrTaskInfoTaskService;
import com.emr.project.emr.domain.EmrTaskInfo;
import com.emr.project.emr.mapper.EmrTaskInfoHistoryMapper;
import com.emr.project.emr.mapper.EmrTaskInfoMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmrTaskInfoTaskServiceImpl implements EmrTaskInfoTaskService {
   @Autowired
   private EmrTaskInfoMapper emrTaskInfoMapper;
   @Autowired
   private EmrTaskInfoHistoryMapper emrTaskInfoHistoryMapper;

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public int transferEmrTaskInfo() throws Exception {
      List<EmrTaskInfo> emrTaskInfoList = this.emrTaskInfoMapper.selectEmrTaskInfoListFiled();
      if (CollectionUtils.isNotEmpty(emrTaskInfoList) && emrTaskInfoList.size() > 0) {
         List<Long> ids = (List)emrTaskInfoList.stream().map((t) -> t.getId()).collect(Collectors.toList());
         int maxCount = 500;
         if (ids.size() <= maxCount) {
            this.transferEmrTaskInfoBegin(ids);
         } else {
            int listSize = emrTaskInfoList.size();
            int a = listSize / maxCount;
            int b = listSize % maxCount;
            if (b > 0) {
               ++a;
            }

            for(int i = 0; i < a; ++i) {
               int beginIndex = i * maxCount;
               int endIndex = (i + 1) * maxCount;
               if (i + 1 == a) {
                  endIndex = listSize;
               }

               List<EmrTaskInfo> addSubList = emrTaskInfoList.subList(beginIndex, endIndex);
               List<Long> subIds = (List)addSubList.stream().map((t) -> t.getId()).collect(Collectors.toList());
               this.transferEmrTaskInfoBegin(subIds);
            }
         }
      }

      return 1;
   }

   private void transferEmrTaskInfoBegin(List ids) {
      List<Long> idHisList = new ArrayList(1);
      List<Long> idRedoList = new ArrayList(1);
      List<EmrTaskInfo> emrTaskInfoHList = this.emrTaskInfoHistoryMapper.selectEmrTaskInfoByIds(ids);
      if (CollectionUtils.isNotEmpty(emrTaskInfoHList) && emrTaskInfoHList.size() > 0) {
         List<Long> idsH = (List)emrTaskInfoHList.stream().map((t) -> t.getId()).collect(Collectors.toList());

         for(Long id : ids) {
            if (idsH.contains(id)) {
               idRedoList.add(id);
            } else {
               idHisList.add(id);
            }
         }
      } else {
         idHisList = ids;
      }

      if (CollectionUtils.isNotEmpty(idHisList) && idHisList.size() > 0) {
         Long[] idsL = (Long[])idHisList.stream().toArray((x$0) -> new Long[x$0]);
         this.emrTaskInfoHistoryMapper.batchInsert(idsL);
         this.emrTaskInfoMapper.deleteEmrTaskInfoByIds(idsL);
      }

      if (CollectionUtils.isNotEmpty(idRedoList) && idRedoList.size() > 0) {
         Long[] redoIds = (Long[])idRedoList.stream().toArray((x$0) -> new Long[x$0]);
         this.emrTaskInfoMapper.deleteEmrTaskInfoByIds(redoIds);
      }

   }
}
