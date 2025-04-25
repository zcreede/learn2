package com.emr.project.docOrder.mapper;

import com.emr.project.docOrder.domain.Doctorder;
import com.emr.project.docOrder.domain.vo.DoctorderVo;
import com.emr.project.docOrder.domain.vo.OrderSearchVo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DoctorderMapper {
   Doctorder selectDoctorderById(String id);

   List selectDoctorderList(Doctorder doctorder);

   int insertDoctorder(Doctorder doctorder);

   int updateDoctorder(Doctorder doctorder);

   int deleteDoctorderById(String id);

   int deleteDoctorderByIds(String[] ids);

   List selectHisDocorderParentList(@Param("doctorderVo") DoctorderVo doctorderVo, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

   Integer selectHisDocorderParentListCount(DoctorderVo doctorderVo);

   List selectHisDocorderChildList(DoctorderVo doctorderVo);

   List selectHisDocorderVoList(OrderSearchVo queryParam);

   List selectHisSubDocorderVoList(OrderSearchVo queryParam);
}
