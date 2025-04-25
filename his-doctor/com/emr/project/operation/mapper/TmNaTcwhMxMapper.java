package com.emr.project.operation.mapper;

import com.emr.project.operation.domain.TmNaTcwhMx;
import com.emr.project.operation.domain.req.TcwhMxReq;
import java.util.List;

public interface TmNaTcwhMxMapper {
   TmNaTcwhMx selectTmNaTcwhMxById(Long id);

   List selectTmNaTcwhMxList(TmNaTcwhMx tmNaTcwhMx);

   Long selectMaxId();

   List selectTmNaTcwhMxInfoList(TcwhMxReq req);

   int insertTmNaTcwhMx(TmNaTcwhMx tmNaTcwhMx);

   int batchInsert(List list);

   int updateTmNaTcwhMx(TmNaTcwhMx tmNaTcwhMx);

   int batchUpdate(List list);

   int deleteTmNaTcwhMxById(Long id);

   int deleteTmNaTcwhMxByIds(Long[] ids);
}
