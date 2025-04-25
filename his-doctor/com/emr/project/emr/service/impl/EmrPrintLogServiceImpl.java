package com.emr.project.emr.service.impl;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.project.emr.domain.EmrPrintLog;
import com.emr.project.emr.mapper.EmrPrintLogMapper;
import com.emr.project.emr.service.IEmrPrintLogService;
import com.emr.project.emr.service.IIndexService;
import com.emr.project.pat.domain.vo.VisitinfoVo;
import com.emr.project.pat.service.IVisitinfoService;
import com.emr.project.system.domain.BasEmployee;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmrPrintLogServiceImpl implements IEmrPrintLogService {
   @Autowired
   private EmrPrintLogMapper emrPrintLogMapper;
   @Autowired
   private IVisitinfoService visitinfoService;
   @Autowired
   private IIndexService indexService;

   public EmrPrintLog selectEmrPrintLogById(String id) {
      return this.emrPrintLogMapper.selectEmrPrintLogById(id);
   }

   public List selectEmrPrintLogList(EmrPrintLog emrPrintLog) {
      return this.emrPrintLogMapper.selectEmrPrintLogList(emrPrintLog);
   }

   public int insertEmrPrintLog(EmrPrintLog emrPrintLog) throws Exception {
      VisitinfoVo visitinfoVo = this.visitinfoService.selectVisitinfoById(emrPrintLog.getPatientId());
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      emrPrintLog.setId(SnowIdUtils.uniqueLong());
      emrPrintLog.setPatientName(visitinfoVo.getPatientName());
      emrPrintLog.setInpNo(visitinfoVo.getInpNo());
      emrPrintLog.setPrintPer(basEmployee.getEmplNumber());
      emrPrintLog.setPrintPerName(basEmployee.getEmplName());
      if (emrPrintLog.getFileType().equals("1")) {
         this.indexService.updatePrintInfo(Long.valueOf(emrPrintLog.getFileId()));
      }

      return this.emrPrintLogMapper.insertEmrPrintLog(emrPrintLog);
   }

   public Integer getBeginRow(String fileId) throws Exception {
      Integer beginRow = this.emrPrintLogMapper.getBeginRow(fileId);
      beginRow = beginRow == null ? 1 : beginRow;
      return beginRow;
   }

   public int updateEmrPrintLog(EmrPrintLog emrPrintLog) {
      return this.emrPrintLogMapper.updateEmrPrintLog(emrPrintLog);
   }

   public int deleteEmrPrintLogByIds(String[] ids) {
      return this.emrPrintLogMapper.deleteEmrPrintLogByIds(ids);
   }

   public int deleteEmrPrintLogById(String id) {
      return this.emrPrintLogMapper.deleteEmrPrintLogById(id);
   }
}
