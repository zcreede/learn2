package com.emr.project.operation.service;

import com.emr.project.operation.domain.vo.PriceYyJzXmVo;
import java.util.List;
import java.util.Map;

public interface IPriceYyService {
   List getPriceList(Map param) throws Exception;

   Integer getPriceListCount(Map param) throws Exception;

   void updateCache(Map param);

   List queryByItemCodeList(List itemNoList);

   List getByCpNo(String chargeNo, Integer subjectFlag);

   PriceYyJzXmVo getByCode(Map param);

   PriceYyJzXmVo getByStandardCode(Map priceYyParamMap);
}
