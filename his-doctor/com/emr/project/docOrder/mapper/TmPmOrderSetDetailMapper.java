package com.emr.project.docOrder.mapper;

import com.emr.project.docOrder.domain.TmPmOrderSetDetail;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TmPmOrderSetDetailMapper {
   TmPmOrderSetDetail selectTmPmOrderSetDetailById(Long id);

   List selectTmPmOrderSetDetailList(TmPmOrderSetDetail tmPmOrderSetDetail);

   int insertTmPmOrderSetDetail(TmPmOrderSetDetail tmPmOrderSetDetail);

   void insertTmPmOrderSetDetailList(List tmPmOrderSetDetailList);

   int updateTmPmOrderSetDetail(TmPmOrderSetDetail tmPmOrderSetDetail);

   int deleteTmPmOrderSetDetailById(Long id);

   void deleteTmPmOrderSetDetailByIds(List idList);

   void deleteTmPmOrderSetDetailByGroupNos(List groupNoList, String setCd);

   List selectTmPmOrderSetDetailBySetCd(String setCd);

   Integer selectSetMaxGroupNo(String setCd);

   List selectOrderSetDetailByGroupNo(@Param("groupNo") Integer groupNo, @Param("setCd") String setCd);

   List selectSetDetailBySortAfterList(@Param("groupNo") Integer groupNo, @Param("setCd") String setCd);

   List selectSortAfterSonList(@Param("groupNo") Integer groupNo, @Param("setCd") String setCd);

   void updateTmPmOrderSetDetailList(List tmPmOrderSetDetailList);

   void deleteTmPmOrderSetDetailBySetCd(String setCd);

   List selectSetAsOrderList(String setCd);

   List selectSetAsOrderListBySets(List list);

   List selectTmPmOrderSetDetailByIds(List idList);

   List selectCheckDetailListBySetCd(String setCd);

   void updateGroupNos(List setDetailList);
}
