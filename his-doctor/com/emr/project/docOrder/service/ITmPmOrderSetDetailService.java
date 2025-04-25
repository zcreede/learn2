package com.emr.project.docOrder.service;

import com.emr.project.docOrder.domain.TmPmOrderSetDetail;
import com.emr.project.docOrder.domain.vo.TmPmOrderSetDetailVo;
import java.util.List;

public interface ITmPmOrderSetDetailService {
   TmPmOrderSetDetail selectTmPmOrderSetDetailById(Long id);

   List selectTmPmOrderSetDetailList(TmPmOrderSetDetailVo tmPmOrderSetDetail);

   void addTmPmOrderSetDetail(TmPmOrderSetDetailVo tmPmOrderSetDetail) throws Exception;

   void insertTmPmOrderSetDetail(TmPmOrderSetDetailVo tmPmOrderSetDetail) throws Exception;

   void insertTmPmOrderSetDetailList(List tmPmOrderSetDetailList) throws Exception;

   void updateTmPmOrderSetDetail(TmPmOrderSetDetailVo tmPmOrderSetDetail) throws Exception;

   void updateTmPmOrderSetDetailList(List tmPmOrderSetDetailList) throws Exception;

   void deleteTmPmOrderSetDetailByIds(Long[] ids) throws Exception;

   int deleteTmPmOrderSetDetailById(Long id);

   void deleteTmPmOrderSetDetailBySetCd(String setCd) throws Exception;

   List selectTmPmOrderSetDetailBySetCd(String setCd) throws Exception;

   Integer selectSetMaxGroupNo(String setCd) throws Exception;

   List selectSetAsOrderList(String setCd) throws Exception;

   List selectSetAsOrderListBySets(List setCd) throws Exception;

   List selectCheckDetailListBySetCd(String setCd) throws Exception;

   void updateGroupNos(List setDetailList) throws Exception;
}
