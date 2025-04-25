package com.emr.project.tmpl.mapper;

import com.emr.project.tmpl.domain.TmplAuditRecord;
import java.util.List;

public interface TmplAuditRecordMapper {
   TmplAuditRecord selectTmplAuditRecordById(Long id);

   List selectTmplAuditRecordList(TmplAuditRecord tmplAuditRecord);

   List selectTmplAuditRecordByTempId(TmplAuditRecord tmplAuditRecord);

   int insertTmplAuditRecord(TmplAuditRecord tmplAuditRecord);

   int updateTmplAuditRecord(TmplAuditRecord tmplAuditRecord);

   int deleteTmplAuditRecordById(Long id);

   int deleteTmplAuditRecordByIds(Long[] ids);
}
