package com.emr.project.mrhp.service;

import com.emr.project.mrhp.domain.MrHpIcu;
import com.emr.project.mrhp.domain.mris.TdCmHpLineVo;
import com.emr.project.mrhp.domain.vo.MrHpAttachVo;
import com.emr.project.system.domain.vo.SqlVo;
import java.util.List;

public interface IMrHpIcuService {
   MrHpIcu selectMrHpIcuById(String icuId);

   List selectMrHpIcuList(MrHpIcu mrHpIcu);

   List selectMrHpIcuListByRecordId(String recordId);

   int insertMrHpIcu(MrHpIcu mrHpIcu);

   int updateMrHpIcu(MrHpIcu mrHpIcu);

   int deleteMrHpIcuByIds(String[] recordIds);

   int deleteMrHpIcuById(String recordId);

   List selectMrHpIcuDataSourceList(SqlVo sqlVo);

   void insertMrHpIcuList(MrHpAttachVo mrHpAttachVo);

   List selectMrisHpIcuListByRecordId(String recordId);

   void insertMrisHpIcuList(TdCmHpLineVo mrHpVo);
}
