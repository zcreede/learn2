package com.emr.project.docOrder.service;

import com.emr.project.docOrder.domain.TmPmOrderSetMain;
import com.emr.project.docOrder.domain.vo.TmPmOrderSetMainVo;
import java.util.List;
import java.util.Map;

public interface ITmPmOrderSetMainService {
   TmPmOrderSetMain selectTmPmOrderSetMainById(String setCd);

   List selectTmPmOrderSetMainList(TmPmOrderSetMain tmPmOrderSetMain) throws Exception;

   void insertTmPmOrderSetMain(TmPmOrderSetMainVo tmPmOrderSetMainVo) throws Exception;

   void insertSetMain(TmPmOrderSetMainVo tmPmOrderSetMainVo) throws Exception;

   void updateTmPmOrderSetMain(TmPmOrderSetMain tmPmOrderSetMain) throws Exception;

   int deleteTmPmOrderSetMainByIds(String[] setCds);

   void deleteTmPmOrderSetMainById(String setCd) throws Exception;

   List selectOrderSetListByClassCd(TmPmOrderSetMainVo tmPmOrderSetMainVo) throws Exception;

   Map selectSetMainDictList() throws Exception;

   List selectDetailListByType(TmPmOrderSetMain tmPmOrderSetMain) throws Exception;

   void saveAsOrderSet(TmPmOrderSetMainVo tmPmOrderSetMainVo) throws Exception;

   List selectEsItemSetList(TmPmOrderSetMain tmPmOrderSetMain) throws Exception;

   int updateGroupNos(List updateMainList) throws Exception;
}
