package com.emr.project.tmpl.service;

import com.emr.project.tmpl.domain.TmplAuditRecord;
import java.util.List;

public interface ITmplAuditRecordService {
   TmplAuditRecord selectTmplAuditRecordById(Long id);

   List selectTmplAuditRecordList(TmplAuditRecord tmplAuditRecord);

   List selectTmplAuditRecordQueryList(TmplAuditRecord tmplAuditRecord) throws Exception;

   int insertTmplAuditRecord(TmplAuditRecord tmplAuditRecord);

   int updateTmplAuditRecord(TmplAuditRecord tmplAuditRecord);

   int deleteTmplAuditRecordByIds(Long[] ids);

   int deleteTmplAuditRecordById(Long id);
}
