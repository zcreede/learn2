package com.emr.project.docOrder.service;

import com.emr.project.docOrder.domain.TmPmOrderStatus;
import java.util.List;

public interface ITmPmOrderStatusService {
   TmPmOrderStatus selectTmPmOrderStatusById(String codeNo);

   List selectTmPmOrderStatusList(TmPmOrderStatus tmPmOrderStatus);

   int insertTmPmOrderStatus(TmPmOrderStatus tmPmOrderStatus);

   int updateTmPmOrderStatus(TmPmOrderStatus tmPmOrderStatus);

   int deleteTmPmOrderStatusByIds(String[] codeNos);

   int deleteTmPmOrderStatusById(String codeNo);
}
