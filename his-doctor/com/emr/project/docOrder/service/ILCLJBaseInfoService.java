package com.emr.project.docOrder.service;

import com.emr.project.docOrder.domain.req.StageListReq;
import com.emr.project.docOrder.domain.vo.StageDataList;
import java.util.List;

public interface ILCLJBaseInfoService {
   List getVaryTree() throws Exception;

   StageDataList getStageList(StageListReq req) throws Exception;
}
