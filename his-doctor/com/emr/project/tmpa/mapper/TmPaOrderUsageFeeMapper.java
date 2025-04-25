package com.emr.project.tmpa.mapper;

import com.emr.project.tmpa.domain.TmPaOrderUsageFee;
import java.util.List;

public interface TmPaOrderUsageFeeMapper {
   TmPaOrderUsageFee selectTmPaOrderUsageFeeById(Long no);

   List selectTmPaOrderUsageFeeList(TmPaOrderUsageFee tmPaOrderUsageFee);

   int insertTmPaOrderUsageFee(TmPaOrderUsageFee tmPaOrderUsageFee);

   int updateTmPaOrderUsageFee(TmPaOrderUsageFee tmPaOrderUsageFee);

   int deleteTmPaOrderUsageFeeById(Long no);

   int deleteTmPaOrderUsageFeeByIds(Long[] nos);
}
