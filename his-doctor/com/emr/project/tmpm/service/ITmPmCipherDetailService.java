package com.emr.project.tmpm.service;

import com.emr.project.esSearch.domain.vo.DrugAndClinSearchVo;
import com.emr.project.tmpm.domain.TmPmCipherDetail;
import com.emr.project.tmpm.domain.vo.TmPmCipherDetailVo;
import com.emr.project.tmpm.domain.vo.TmPmCipherMainVo;
import java.util.List;

public interface ITmPmCipherDetailService {
   TmPmCipherDetail selectTmPmCipherDetailById(Long id);

   List selectTmPmCipherDetailList(TmPmCipherDetail tmPmCipherDetail);

   int insertTmPmCipherDetail(TmPmCipherDetail tmPmCipherDetail);

   void insertTmPmCipherDetailList(List tmPmCipherDetailList) throws Exception;

   int updateTmPmCipherDetail(TmPmCipherDetail tmPmCipherDetail);

   int deleteTmPmCipherDetailByIds(Long[] ids);

   int deleteTmPmCipherDetailById(Long id);

   void deleteTmPmCipherDetailByCipherCd(String cipherCd) throws Exception;

   List selectTmPmCipherDetailByCdList(TmPmCipherDetailVo tmPmCipherDetail) throws Exception;

   List selectTmPmCipherDetailByCipherCd(String cipherCd) throws Exception;

   List selectDetailDrugList(TmPmCipherDetailVo tmPmCipherDetail) throws Exception;

   List selectCipAndGroupDetailList(DrugAndClinSearchVo param) throws Exception;

   void saveTmPmCipher(TmPmCipherMainVo tmPmCipherMain) throws Exception;
}
