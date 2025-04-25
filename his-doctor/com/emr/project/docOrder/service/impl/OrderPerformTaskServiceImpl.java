package com.emr.project.docOrder.service.impl;

import com.emr.project.docOrder.domain.InpatientOrderPerform;
import com.emr.project.docOrder.domain.InpatientOrderPerformDetail;
import com.emr.project.docOrder.domain.InpatientOrderPerformFirstBottle;
import com.emr.project.docOrder.mapper.InpatientOrderPerformDetailMapper;
import com.emr.project.docOrder.mapper.InpatientOrderPerformFirstBottleMapper;
import com.emr.project.docOrder.mapper.InpatientOrderPerformMapper;
import com.emr.project.docOrder.mapper.TdPaOrderPerformDetailHMapper;
import com.emr.project.docOrder.mapper.TdPaOrderPerformFirstHMapper;
import com.emr.project.docOrder.mapper.TdPaOrderPerformHMapper;
import com.emr.project.docOrder.service.OrderPerformTaskService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderPerformTaskServiceImpl implements OrderPerformTaskService {
   @Autowired
   private InpatientOrderPerformMapper orderPerformMapper;
   @Autowired
   private TdPaOrderPerformHMapper orderPerformHMapper;
   @Autowired
   private InpatientOrderPerformDetailMapper orderPerformDetailMapper;
   @Autowired
   private TdPaOrderPerformDetailHMapper orderPerformDetailHMapper;
   @Autowired
   private InpatientOrderPerformFirstBottleMapper orderPerformFirstBottleMapper;
   @Autowired
   private TdPaOrderPerformFirstHMapper orderPerformFirstHMapper;

   public int transferOrderPerform(List deptList) throws Exception {
      List<InpatientOrderPerform> orderPerformList = this.orderPerformMapper.selectHisPatientOrderPerformList(deptList);
      if (CollectionUtils.isNotEmpty(orderPerformList) && orderPerformList.size() > 0) {
         int maxCount = 500;
         if (orderPerformList.size() <= maxCount) {
            this.transferOrderPerformBegin(orderPerformList, maxCount);
         } else {
            int listSize = orderPerformList.size();
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

               List<InpatientOrderPerform> addSubList = orderPerformList.subList(beginIndex, endIndex);
               this.transferOrderPerformBegin(addSubList, maxCount);
            }
         }
      }

      return 1;
   }

   private void transferOrderPerformBegin(List orderPerformList, int maxCount) {
      List<InpatientOrderPerform> orderPerformHisList = new ArrayList(1);
      List<InpatientOrderPerform> orderPerformRedoList = new ArrayList(1);
      List<InpatientOrderPerform> orderPerformHList = this.orderPerformHMapper.selectTdPaOrderPerformHByPerformListNos(orderPerformList);
      if (CollectionUtils.isNotEmpty(orderPerformList) && orderPerformList.size() > 0) {
         for(InpatientOrderPerform orderPerform : orderPerformList) {
            List<InpatientOrderPerform> orderPerformRedos = new ArrayList();
            if (CollectionUtils.isNotEmpty(orderPerformHList) && orderPerformHList.size() > 0) {
               orderPerformRedos = (List)orderPerformHList.stream().filter((t) -> t.getHospitalCode().equals(orderPerform.getHospitalCode()) && t.getAdmissionNo().equals(orderPerform.getAdmissionNo()) && t.getHospitalizedCount().equals(orderPerform.getHospitalizedCount()) && t.getPerformListNo().equals(orderPerform.getPerformListNo()) && t.getOrderNo().equals(orderPerform.getOrderNo()) && t.getOrderSortNumber().equals(orderPerform.getOrderSortNumber())).collect(Collectors.toList());
            }

            if (CollectionUtils.isNotEmpty(orderPerformRedos) && orderPerformRedos.size() > 0) {
               orderPerformRedoList.add(orderPerform);
            } else {
               orderPerformHisList.add(orderPerform);
            }
         }
      }

      List<InpatientOrderPerformDetail> orderPerformDetailHisList = new ArrayList(1);
      List<InpatientOrderPerformDetail> orderPerformDetailRedoList = new ArrayList(1);
      if (CollectionUtils.isNotEmpty(orderPerformList) && orderPerformList.size() > 0) {
         List<String> performListNoList = (List)orderPerformList.stream().map((t) -> t.getPerformListNo()).distinct().collect(Collectors.toList());
         List<InpatientOrderPerformDetail> orderPerformDetailList = this.orderPerformDetailMapper.selectListByPerformListNoList(performListNoList);
         if (CollectionUtils.isNotEmpty(orderPerformDetailList) && orderPerformDetailList.size() > 0) {
            List<String> performListNoHList = (List)orderPerformDetailList.stream().map((t) -> t.getPerformListNo()).distinct().collect(Collectors.toList());
            List<InpatientOrderPerformDetail> orderPerformDetailHList = this.orderPerformDetailHMapper.selectListByPerformListNoList(performListNoHList);

            for(InpatientOrderPerformDetail orderPerformDetail : orderPerformDetailList) {
               List<InpatientOrderPerformDetail> orderPerformDetailRedos = new ArrayList();
               if (CollectionUtils.isNotEmpty(orderPerformDetailHList) && orderPerformDetailHList.size() > 0) {
                  orderPerformDetailRedos = (List)orderPerformDetailHList.stream().filter((t) -> t.getPerformListNo().equals(orderPerformDetail.getPerformListNo()) && t.getPerformListSortNumber().equals(orderPerformDetail.getPerformListSortNumber()) && t.getOrderSortNumber().equals(orderPerformDetail.getOrderSortNumber()) && t.getOrderGroupSortNumber().equals(orderPerformDetail.getOrderGroupSortNumber())).collect(Collectors.toList());
               }

               if (CollectionUtils.isNotEmpty(orderPerformDetailRedos) && orderPerformDetailRedos.size() > 0) {
                  orderPerformDetailRedoList.add(orderPerformDetail);
               } else {
                  orderPerformDetailHisList.add(orderPerformDetail);
               }
            }
         }
      }

      List<InpatientOrderPerformFirstBottle> orderPerformFirstHisList = new ArrayList(1);
      List<InpatientOrderPerformFirstBottle> orderPerformFirstHRedoList = new ArrayList(1);
      if (CollectionUtils.isNotEmpty(orderPerformList) && orderPerformList.size() > 0) {
         List<InpatientOrderPerformFirstBottle> orderPerformFirstList = this.orderPerformFirstBottleMapper.selectListByAdminssionNos(orderPerformList);
         if (CollectionUtils.isNotEmpty(orderPerformFirstList) && orderPerformFirstList.size() > 0) {
            List<Long> performFirstIdList = (List)orderPerformFirstList.stream().map((t) -> t.getId()).distinct().collect(Collectors.toList());
            List<InpatientOrderPerformFirstBottle> orderPerformFirstHList = new ArrayList();
            int listSize = performFirstIdList.size();
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

               List<Long> subList = performFirstIdList.subList(beginIndex, endIndex);
               Long[] performFirstIds = (Long[])subList.stream().toArray((x$0) -> new Long[x$0]);
               List<InpatientOrderPerformFirstBottle> orderPerformFirstHBatchList = this.orderPerformFirstHMapper.selectTdPaOrderPerformFirstHByIds(performFirstIds);
               orderPerformFirstHList.addAll(orderPerformFirstHBatchList);
            }

            for(InpatientOrderPerformFirstBottle orderPerformFirstBottle : orderPerformFirstList) {
               List<InpatientOrderPerformFirstBottle> orderPerformFirstRedos = new ArrayList();
               if (CollectionUtils.isNotEmpty(orderPerformFirstHList) && orderPerformFirstHList.size() > 0) {
                  orderPerformFirstRedos = (List)orderPerformFirstHList.stream().filter((t) -> t.getId().equals(orderPerformFirstBottle.getId())).collect(Collectors.toList());
               }

               if (CollectionUtils.isNotEmpty(orderPerformFirstRedos) && orderPerformFirstRedos.size() > 0) {
                  orderPerformFirstHRedoList.add(orderPerformFirstBottle);
               } else {
                  orderPerformFirstHisList.add(orderPerformFirstBottle);
               }
            }
         }
      }

      if (CollectionUtils.isNotEmpty(orderPerformHisList) && orderPerformHisList.size() > 0) {
         int listSize = orderPerformHisList.size();
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

            List<InpatientOrderPerform> subList = orderPerformHisList.subList(beginIndex, endIndex);
            this.orderPerformHMapper.batchInsertTdPaOrderPerformH(subList);
            this.orderPerformMapper.deletePatientOrderPerformByPerformListNos(subList);
         }
      }

      if (CollectionUtils.isNotEmpty(orderPerformRedoList) && orderPerformRedoList.size() > 0) {
         int listSize = orderPerformRedoList.size();
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

            List<InpatientOrderPerform> subList = orderPerformRedoList.subList(beginIndex, endIndex);
            this.orderPerformMapper.deletePatientOrderPerformByPerformListNos(subList);
         }
      }

      if (CollectionUtils.isNotEmpty(orderPerformDetailHisList) && orderPerformDetailHisList.size() > 0) {
         int maxCountDetail = 200;
         int listSize = orderPerformDetailHisList.size();
         int a = listSize / maxCountDetail;
         int b = listSize % maxCountDetail;
         if (b > 0) {
            ++a;
         }

         for(int i = 0; i < a; ++i) {
            int beginIndex = i * maxCountDetail;
            int endIndex = (i + 1) * maxCountDetail;
            if (i + 1 == a) {
               endIndex = listSize;
            }

            List<InpatientOrderPerformDetail> subList = orderPerformDetailHisList.subList(beginIndex, endIndex);
            this.orderPerformDetailHMapper.batchInsertTdPaOrderPerformDetailH(subList);
            this.orderPerformDetailMapper.deletePatientOrderPerformDetailByPerformListNos(subList);
         }
      }

      if (CollectionUtils.isNotEmpty(orderPerformDetailRedoList) && orderPerformDetailRedoList.size() > 0) {
         int maxCountDetail = 200;
         int listSize = orderPerformDetailRedoList.size();
         int a = listSize / maxCountDetail;
         int b = listSize % maxCountDetail;
         if (b > 0) {
            ++a;
         }

         for(int i = 0; i < a; ++i) {
            int beginIndex = i * maxCountDetail;
            int endIndex = (i + 1) * maxCountDetail;
            if (i + 1 == a) {
               endIndex = listSize;
            }

            List<InpatientOrderPerformDetail> subList = orderPerformDetailRedoList.subList(beginIndex, endIndex);
            this.orderPerformDetailMapper.deletePatientOrderPerformDetailByPerformListNos(subList);
         }
      }

      if (CollectionUtils.isNotEmpty(orderPerformFirstHisList) && orderPerformFirstHisList.size() > 0) {
         int listSize = orderPerformFirstHisList.size();
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

            List<InpatientOrderPerformFirstBottle> subList = orderPerformFirstHisList.subList(beginIndex, endIndex);
            this.orderPerformFirstHMapper.batchInsertTdPaOrderPerformFirstH(subList);
            List<Long> idList = (List)subList.stream().map((t) -> t.getId()).distinct().collect(Collectors.toList());
            Long[] ids = (Long[])idList.stream().toArray((x$0) -> new Long[x$0]);
            this.orderPerformFirstBottleMapper.deleteByids(ids);
         }
      }

      if (CollectionUtils.isNotEmpty(orderPerformFirstHRedoList) && orderPerformFirstHRedoList.size() > 0) {
         int listSize = orderPerformFirstHRedoList.size();
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

            List<InpatientOrderPerformFirstBottle> subList = orderPerformFirstHRedoList.subList(beginIndex, endIndex);
            List<Long> idList = (List)subList.stream().map((t) -> t.getId()).distinct().collect(Collectors.toList());
            Long[] ids = (Long[])idList.stream().toArray((x$0) -> new Long[x$0]);
            this.orderPerformFirstBottleMapper.deleteByids(ids);
         }
      }

   }
}
