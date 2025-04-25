package com.emr.project.operation.service;

import com.emr.project.operation.domain.TmNaTcwhMx;
import com.emr.project.operation.domain.req.TcwhMxReq;
import java.util.List;

public interface ITmNaTcwhMxService {
   TmNaTcwhMx selectTmNaTcwhMxById(Long id) throws Exception;

   List selectTmNaTcwhMxList(TmNaTcwhMx tmNaTcwhMx) throws Exception;

   List selectTmNaTcwhMxInfoList(TcwhMxReq req) throws Exception;

   int insertTmNaTcwhMx(TmNaTcwhMx tmNaTcwhMx) throws Exception;

   int save(List tmNaTcwhMxs) throws Exception;

   int updateTmNaTcwhMx(TmNaTcwhMx tmNaTcwhMx) throws Exception;

   int deleteTmNaTcwhMxByIds(Long[] ids) throws Exception;

   int deleteTmNaTcwhMxById(Long id) throws Exception;
}
