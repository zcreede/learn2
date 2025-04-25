package com.emr.project.revoke.mapper;

import com.emr.project.revoke.domain.EmrIndexRevokeLog;
import com.emr.project.revoke.domain.req.EmrIndexRevokeLogReq;
import java.util.List;

public interface EmrIndexRevokeLogMapper {
   EmrIndexRevokeLog selectEmrIndexRevokeLogById(Long id);

   List selectEmrIndexRevokeLogList(EmrIndexRevokeLogReq req);

   int insertEmrIndexRevokeLog(EmrIndexRevokeLog emrIndexRevokeLog);

   int updateEmrIndexRevokeLog(EmrIndexRevokeLog emrIndexRevokeLog);

   int deleteEmrIndexRevokeLogById(Long id);

   int deleteEmrIndexRevokeLogByIds(Long[] ids);

   void insertList(List list);
}
