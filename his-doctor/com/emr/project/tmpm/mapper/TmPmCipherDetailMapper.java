package com.emr.project.tmpm.mapper;

import com.emr.project.tmpm.domain.TmPmCipherDetail;
import java.util.List;

public interface TmPmCipherDetailMapper {
   TmPmCipherDetail selectTmPmCipherDetailById(Long id);

   List selectTmPmCipherDetailList(TmPmCipherDetail tmPmCipherDetail);

   int insertTmPmCipherDetail(TmPmCipherDetail tmPmCipherDetail);

   void insertTmPmCipherDetailList(List tmPmCipherDetailList);

   int updateTmPmCipherDetail(TmPmCipherDetail tmPmCipherDetail);

   int deleteTmPmCipherDetailById(Long id);

   void deleteTmPmCipherDetailByCipherCd(String cipher);

   int deleteTmPmCipherDetailByIds(Long[] ids);

   List selectTmPmCipherDetailByCipherCd(String cipherCd);

   List selectDetailDrugListByIds(List idList);
}
