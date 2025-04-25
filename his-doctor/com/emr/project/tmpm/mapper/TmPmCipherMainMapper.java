package com.emr.project.tmpm.mapper;

import com.emr.project.tmpm.domain.TmPmCipherMain;
import com.emr.project.tmpm.domain.vo.TmPmCipherMainVo;
import java.util.List;

public interface TmPmCipherMainMapper {
   TmPmCipherMainVo selectTmPmCipherMainById(String cipherCd);

   List selectTmPmCipherMainList(TmPmCipherMain tmPmCipherMain);

   void insertTmPmCipherMain(TmPmCipherMain tmPmCipherMain);

   void updateTmPmCipherMain(TmPmCipherMain tmPmCipherMain);

   void deleteTmPmCipherMainById(String cipherCd);

   int deleteTmPmCipherMainByIds(String[] cipherCds);
}
