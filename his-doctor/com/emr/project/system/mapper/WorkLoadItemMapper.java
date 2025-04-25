package com.emr.project.system.mapper;

import com.emr.project.system.domain.WorkLoadItem;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WorkLoadItemMapper {
   WorkLoadItem selectWorkLoadItemById(Long id);

   List selectWorkLoadItemList(WorkLoadItem workLoadItem);

   int insertWorkLoadItem(WorkLoadItem workLoadItem);

   int updateWorkLoadItem(WorkLoadItem workLoadItem);

   int deleteWorkLoadItemById(Long id);

   int deleteWorkLoadItemByIds(Long[] ids);

   String selectMaxCode(@Param("code") String code);

   List selectDsPreserveOutList(@Param("dataType") String dataType);

   Integer selectMaxSortNo();
}
