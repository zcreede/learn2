package com.emr.project.operation.service;

import com.emr.project.operation.domain.TmNaTcwh;
import com.emr.project.operation.domain.req.TcwhListReq;
import java.util.List;

public interface ITmNaTcwhService {
   TmNaTcwh selectTmNaTcwhById(Long id) throws Exception;

   List selectTmNaTcwhList(TcwhListReq req) throws Exception;

   int insertTmNaTcwh(TmNaTcwh tmNaTcwh) throws Exception;

   int save(List tmNaTcwhs) throws Exception;

   int updateTmNaTcwh(TmNaTcwh tmNaTcwh) throws Exception;

   int deleteTmNaTcwhByIds(Long[] ids) throws Exception;

   int deleteTmNaTcwhById(Long id) throws Exception;

   List selectListFromMx();
}
