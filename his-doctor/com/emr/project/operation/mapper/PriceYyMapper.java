package com.emr.project.operation.mapper;

import com.emr.project.operation.domain.vo.PriceYyJzXmVo;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface PriceYyMapper {
   List getPriceList(Map param);

   Integer getPriceListCount(Map param);

   List selectListByItemNoList(List list);

   List getByCpNo(@Param("chargeNo") String chargeNo, @Param("subjectFlag") Integer subjectFlag);

   PriceYyJzXmVo getByCode(Map param);

   PriceYyJzXmVo getByStandardCode(Map priceYyParamMap);
}
