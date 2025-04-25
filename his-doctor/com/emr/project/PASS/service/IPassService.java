package com.emr.project.PASS.service;

import com.emr.project.PASS.domain.vo.mk.CheckInfoVo;
import com.emr.project.docOrder.domain.vo.HerbSaveVo;
import java.util.List;

public interface IPassService {
   CheckInfoVo getCheckInfoVo_mk(String patientId, List orderSaveVoList, HerbSaveVo herbSaveVo) throws Exception;

   CheckInfoVo getCheckInfoForZcyVo_mk(String patientId, List orderSaveVoList, HerbSaveVo herbSaveVo) throws Exception;

   CheckInfoVo getCheckInfoForDrug_mk(String patientId, List orderSaveVoList, HerbSaveVo herbSaveVo, String orgCd) throws Exception;

   CheckInfoVo getCheckInfo_mk(String patientId, List orderSaveVoList, HerbSaveVo herbSaveVo) throws Exception;

   List checkOrderFirst(List orderSaveVoList, String orgCd) throws Exception;
}
