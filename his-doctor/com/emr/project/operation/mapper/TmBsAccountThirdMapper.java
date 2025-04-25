package com.emr.project.operation.mapper;

import com.emr.project.operation.domain.TmBsAccountThird;
import com.emr.project.operation.domain.vo.TmBsAccountThirdProjectVo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TmBsAccountThirdMapper {
   int insert(TmBsAccountThird record);

   int insertSelective(TmBsAccountThird record);

   List selectThirdAndProjectByCodeList(List list);

   TmBsAccountThirdProjectVo selectThirdAndProjectByCode(@Param("code") String code);

   List selectTmBsAccountThirdList(TmBsAccountThird tmBsAccountThird);

   List selectThirdCodeByFirst();
}
