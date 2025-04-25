package com.emr.project.docOrder.mapper;

import com.emr.project.docOrder.domain.TmPmOrderStatus;
import java.util.List;

public interface TmPmOrderStatusMapper {
   TmPmOrderStatus selectTmPmOrderStatusById(String codeNo);

   List selectTmPmOrderStatusList(TmPmOrderStatus tmPmOrderStatus);

   int insertTmPmOrderStatus(TmPmOrderStatus tmPmOrderStatus);

   int updateTmPmOrderStatus(TmPmOrderStatus tmPmOrderStatus);

   int deleteTmPmOrderStatusById(String codeNo);

   int deleteTmPmOrderStatusByIds(String[] codeNos);
}
