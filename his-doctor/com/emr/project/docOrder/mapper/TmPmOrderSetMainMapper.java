package com.emr.project.docOrder.mapper;

import com.emr.project.docOrder.domain.TmPmOrderSetMain;
import com.emr.project.docOrder.domain.vo.TmPmOrderSetMainVo;
import java.util.List;

public interface TmPmOrderSetMainMapper {
   TmPmOrderSetMain selectTmPmOrderSetMainById(String setCd);

   List selectTmPmOrderSetMainList(TmPmOrderSetMain tmPmOrderSetMain);

   String selectMaxgroupSort();

   void insertTmPmOrderSetMain(TmPmOrderSetMainVo tmPmOrderSetMainVo);

   int updateTmPmOrderSetMain(TmPmOrderSetMain tmPmOrderSetMain);

   int batchUpdate(List list);

   void deleteTmPmOrderSetMainById(String setCd);

   int deleteTmPmOrderSetMainByIds(String[] setCds);

   List selectOrderSetListByClassCd(TmPmOrderSetMainVo tmPmOrderSetMainVo);

   List selectDetailListByType(TmPmOrderSetMain tmPmOrderSetMain);

   List selectEsItemSetList(TmPmOrderSetMain tmPmOrderSetMain);
}
