package com.emr.project.mzInfo.service.impl;

import com.emr.common.enums.OrderItemStatusEnum;
import com.emr.framework.aspectj.lang.annotation.DataSource;
import com.emr.framework.aspectj.lang.enums.DataSourceType;
import com.emr.project.mzInfo.domain.MzOrderDetail;
import com.emr.project.mzInfo.mapper.MzJzInfoMapper;
import com.emr.project.mzInfo.service.IMzJzInfoService;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MzJzInfoServiceImpl implements IMzJzInfoService {
   @Autowired
   private MzJzInfoMapper mzJzInfoMapper;

   @DataSource(DataSourceType.mzdb)
   public List selectMzOrderDetail(String visitNo, String mzh, String orderClassCode) throws Exception {
      MzOrderDetail param = new MzOrderDetail();
      param.setMzh(mzh);
      param.setVisitNo(visitNo);
      param.setOrderClassCode(orderClassCode);
      List<MzOrderDetail> mzOrderDetailList = this.mzJzInfoMapper.selectMzOrderDetail(param);
      if (CollectionUtils.isNotEmpty(mzOrderDetailList) && mzOrderDetailList.size() > 0) {
         for(MzOrderDetail mzOrderDetail : mzOrderDetailList) {
            mzOrderDetail.setOrderStatus(OrderItemStatusEnum.getOrderItemStatusName(mzOrderDetail.getOrderStatus()));
         }
      }

      return mzOrderDetailList;
   }
}
