package com.emr.project.tmpm.service;

import com.emr.project.docOrder.domain.vo.HerbSaveVo;
import com.emr.project.tmpm.domain.TmPmCipherMain;
import com.emr.project.tmpm.domain.vo.TmPmCipherMainVo;
import java.util.List;

public interface ITmPmCipherMainService {
   TmPmCipherMainVo selectTmPmCipherMainById(String cipherCd);

   List selectTmPmCipherMainList(TmPmCipherMainVo tmPmCipherMain);

   List selectCipherMainList(TmPmCipherMain tmPmCipherMain) throws Exception;

   void insertTmPmCipherMain(TmPmCipherMain tmPmCipherMain) throws Exception;

   void updateTmPmCipherMain(TmPmCipherMain tmPmCipherMain) throws Exception;

   int deleteTmPmCipherMainByIds(String[] cipherCds);

   void deleteTmPmCipherMainById(String cipherCd) throws Exception;

   void saveTmPmCipherMain(HerbSaveVo herbSaveVo) throws Exception;

   TmPmCipherMainVo selectCipHerInfoByCd(TmPmCipherMainVo tmPmCipherMainVo) throws Exception;

   void updateCipherMainFlag(TmPmCipherMain tmPmCipherMain) throws Exception;

   TmPmCipherMain saveAsCipherInfo(TmPmCipherMainVo tmPmCipherMain) throws Exception;
}
