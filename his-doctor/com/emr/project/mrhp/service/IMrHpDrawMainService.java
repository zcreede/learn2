package com.emr.project.mrhp.service;

import com.emr.project.mrhp.domain.MrHpDrawMain;
import com.emr.project.mrhp.domain.req.MrHpDrawMainReq;
import com.emr.project.mrhp.domain.resp.MrHpDrawMainResp;
import com.emr.project.webservice.domain.resp.WebServiceGeneralResp;
import java.util.List;

public interface IMrHpDrawMainService {
   MrHpDrawMainResp selectMrHpDrawMainById(Long id);

   List selectMrHpDrawMainList(MrHpDrawMain mrHpDrawMain);

   int insertMrHpDrawMain(MrHpDrawMain mrHpDrawMain);

   int addMrHpDrawMain(MrHpDrawMainReq mrHpDrawMainReq) throws Exception;

   int updateMrHpDrawMain(MrHpDrawMain mrHpDrawMain);

   int deleteMrHpDrawMainByIds(Long[] ids);

   int deleteMrHpDrawMainById(Long id);

   WebServiceGeneralResp testApiConn(MrHpDrawMainReq mrHpDrawMainReq) throws Exception;
}
