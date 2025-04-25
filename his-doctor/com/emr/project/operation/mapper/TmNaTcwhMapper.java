package com.emr.project.operation.mapper;

import com.emr.project.operation.domain.TmNaTcwh;
import com.emr.project.operation.domain.req.TcwhListReq;
import java.util.List;

public interface TmNaTcwhMapper {
   TmNaTcwh selectTmNaTcwhById(Long id);

   List selectTmNaTcwhList(TmNaTcwh tmNaTcwh);

   List selectTmNaTcwhInfoList(TcwhListReq req);

   List selectListFromMx();

   Long selectMaxId();

   int insertTmNaTcwh(TmNaTcwh tmNaTcwh);

   int batchInsert(List list);

   int updateTmNaTcwh(TmNaTcwh tmNaTcwh);

   int batchUpdate(List list);

   int deleteTmNaTcwhById(Long id);

   int deleteTmNaTcwhByIds(Long[] ids);
}
