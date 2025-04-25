package com.emr.project.docOrder.service;

import com.emr.project.docOrder.domain.req.LcljInfoLocalReq;
import com.emr.project.docOrder.domain.resp.LcljBaseInfo;
import java.util.List;

public interface ILCLJInfoService {
   LcljBaseInfo getLcljBaseInfo(String admissionNo) throws Exception;

   List getLcljItemInfo(LcljInfoLocalReq lcljInfoLocalReq) throws Exception;

   List queryLcljDetailInfos(LcljInfoLocalReq lcljInfoLocalReq) throws Exception;

   List saveLcljOrderList(List orderSaveVoList, List orderCpVaryList) throws Exception;
}
