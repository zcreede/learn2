package com.emr.project.tmpl.service.impl;

import com.emr.project.tmpl.domain.TmplAuditRecord;
import com.emr.project.tmpl.mapper.TmplAuditRecordMapper;
import com.emr.project.tmpl.service.ITmplAuditRecordService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TmplAuditRecordServiceImpl implements ITmplAuditRecordService {
   @Autowired
   private TmplAuditRecordMapper tmplAuditRecordMapper;

   public TmplAuditRecord selectTmplAuditRecordById(Long id) {
      return this.tmplAuditRecordMapper.selectTmplAuditRecordById(id);
   }

   public List selectTmplAuditRecordList(TmplAuditRecord tmplAuditRecord) {
      return this.tmplAuditRecordMapper.selectTmplAuditRecordList(tmplAuditRecord);
   }

   public List selectTmplAuditRecordQueryList(TmplAuditRecord tmplAuditRecord) throws Exception {
      return this.tmplAuditRecordMapper.selectTmplAuditRecordByTempId(tmplAuditRecord);
   }

   public int insertTmplAuditRecord(TmplAuditRecord tmplAuditRecord) {
      return this.tmplAuditRecordMapper.insertTmplAuditRecord(tmplAuditRecord);
   }

   public int updateTmplAuditRecord(TmplAuditRecord tmplAuditRecord) {
      return this.tmplAuditRecordMapper.updateTmplAuditRecord(tmplAuditRecord);
   }

   public int deleteTmplAuditRecordByIds(Long[] ids) {
      return this.tmplAuditRecordMapper.deleteTmplAuditRecordByIds(ids);
   }

   public int deleteTmplAuditRecordById(Long id) {
      return this.tmplAuditRecordMapper.deleteTmplAuditRecordById(id);
   }
}
