package com.emr.project.revoke.service;

import com.emr.project.revoke.domain.EmrIndexRevokeLog;
import com.emr.project.revoke.domain.req.EmrIndexRevokeLogReq;
import java.util.List;

public interface IEmrIndexRevokeLogService {
   EmrIndexRevokeLog selectEmrIndexRevokeLogById(Long id);

   List selectEmrIndexRevokeLogList(EmrIndexRevokeLogReq req);

   int insertEmrIndexRevokeLog(EmrIndexRevokeLog emrIndexRevokeLog);

   int updateEmrIndexRevokeLog(EmrIndexRevokeLog emrIndexRevokeLog);

   int deleteEmrIndexRevokeLogByIds(Long[] ids);

   int deleteEmrIndexRevokeLogById(Long id);

   void insertLogList(List revokeLog);
}
