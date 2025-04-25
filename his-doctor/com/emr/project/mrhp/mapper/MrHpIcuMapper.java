package com.emr.project.mrhp.mapper;

import com.emr.project.mrhp.domain.MrHpIcu;
import com.emr.project.system.domain.vo.SqlVo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MrHpIcuMapper {
   MrHpIcu selectMrHpIcuById(String icuId);

   List selectMrHpIcuList(MrHpIcu mrHpIcu);

   List selectMrHpIcuListByRecordId(String recordId);

   int insertMrHpIcu(MrHpIcu mrHpIcu);

   int updateMrHpIcu(MrHpIcu mrHpIcu);

   int deleteMrHpIcuById(String icuId);

   void deleteMrHpIcuByRecordId(String recordId);

   int deleteMrHpIcuByIds(String[] icuIds);

   List selectMrHpIcuDataSourceList(SqlVo sqlVo);

   void insertMrHpIcuList(List list);

   List selectMrisHpIcuListByRecordId(@Param("recordId") String recordId);
}
