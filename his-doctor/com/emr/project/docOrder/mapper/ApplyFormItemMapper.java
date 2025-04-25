package com.emr.project.docOrder.mapper;

import com.emr.project.docOrder.domain.ApplyFormItem;
import com.emr.project.docOrder.domain.ApplyFormItemKey;
import java.util.List;

public interface ApplyFormItemMapper {
   int deleteByPrimaryKey(ApplyFormItemKey key);

   int insert(ApplyFormItem record);

   int insertSelective(ApplyFormItem record);

   ApplyFormItem selectByPrimaryKey(ApplyFormItemKey key);

   List selectByApplyFormNo(String applyFormNo);

   int updateByPrimaryKeySelective(ApplyFormItem record);

   int updateByPrimaryKey(ApplyFormItem record);

   void updateStatusList(List list);

   void updateBarCodeNoList(List list);
}
