package com.emr.project.tmpa.service;

import com.emr.project.tmpa.domain.TmPaOrderUsageFee;
import java.util.List;

public interface ITmPaOrderUsageFeeService {
   TmPaOrderUsageFee selectTmPaOrderUsageFeeById(Long no);

   List selectTmPaOrderUsageFeeList(TmPaOrderUsageFee tmPaOrderUsageFee);

   int insertTmPaOrderUsageFee(TmPaOrderUsageFee tmPaOrderUsageFee);

   int updateTmPaOrderUsageFee(TmPaOrderUsageFee tmPaOrderUsageFee);

   int deleteTmPaOrderUsageFeeByIds(Long[] nos);

   int deleteTmPaOrderUsageFeeById(Long no);
}
