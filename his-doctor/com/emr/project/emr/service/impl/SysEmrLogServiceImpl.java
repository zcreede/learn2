package com.emr.project.emr.service.impl;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.common.utils.StringUtils;
import com.emr.project.emr.domain.Index;
import com.emr.project.emr.domain.SubfileIndex;
import com.emr.project.emr.domain.SysEmrLog;
import com.emr.project.emr.domain.vo.InsertIndexLogVo;
import com.emr.project.emr.domain.vo.SysEmrLogVo;
import com.emr.project.emr.mapper.SysEmrLogMapper;
import com.emr.project.emr.service.IIndexService;
import com.emr.project.emr.service.ISubfileIndexService;
import com.emr.project.emr.service.ISysEmrLogService;
import com.emr.project.pat.domain.vo.VisitinfoPersonalVo;
import com.emr.project.pat.service.IVisitinfoService;
import com.emr.project.system.domain.BasEmployee;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.service.ISysDictDataService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SysEmrLogServiceImpl implements ISysEmrLogService {
   private final Logger log = LoggerFactory.getLogger(SysEmrLogServiceImpl.class);
   @Autowired
   private SysEmrLogMapper sysEmrLogMapper;
   @Autowired
   private IVisitinfoService visitinfoService;
   @Autowired
   private ISysDictDataService sysDictDataService;
   @Autowired
   private ISubfileIndexService subfileIndexService;
   @Autowired
   private IIndexService indexService;

   public SysEmrLog selectSysEmrLogById(Long id) {
      return this.sysEmrLogMapper.selectSysEmrLogById(id);
   }

   public List selectSysEmrLogByMrFileId(Long mrFileId, String[] optType) throws Exception {
      return this.sysEmrLogMapper.selectSysEmrLogByMrFileId(mrFileId, optType);
   }

   public List selectSysEmrLogList(SysEmrLogVo sysEmrLogVo) {
      return this.sysEmrLogMapper.selectSysEmrLogList(sysEmrLogVo);
   }

   public void insertSysEmrLog(List sysEmrLogList) throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();

      for(SysEmrLog sysEmrLog : sysEmrLogList) {
         VisitinfoPersonalVo visitinfoVo = this.visitinfoService.selectVisitinfoPersonalById(sysEmrLog.getPatientId());
         sysEmrLog.setInpNo(visitinfoVo.getInpNo());
         sysEmrLog.setPatientId(visitinfoVo.getPatientId());
         sysEmrLog.setPatientName(visitinfoVo.getPatientName());
         String label = this.sysDictDataService.selectDictLabel("s004", sysEmrLog.getMrTypeCd());
         sysEmrLog.setMrTypeName(label);
         sysEmrLog.setCrePerCode(sysUser.getUserName());
         sysEmrLog.setCrePerName(sysUser.getNickName());
         sysEmrLog.setId(SnowIdUtils.uniqueLong());
      }

      if (sysEmrLogList != null && !sysEmrLogList.isEmpty()) {
         this.sysEmrLogMapper.insertSysEmrLogList(sysEmrLogList);
      }

   }

   public void insertSysEmrLog(Index index, SubfileIndex subfileIndex, String optType, String optTypeName, String ip) throws Exception {
      VisitinfoPersonalVo visitinfoVo = this.visitinfoService.selectVisitinfoPersonalById(index.getPatientId());
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      SysEmrLog sysEmrLog = new SysEmrLog();
      sysEmrLog.setId(SnowIdUtils.uniqueLong());
      sysEmrLog.setInpNo(visitinfoVo.getInpNo());
      sysEmrLog.setPatientId(index.getPatientId());
      sysEmrLog.setPatientName(visitinfoVo.getPatientName());
      sysEmrLog.setMrFileId(subfileIndex != null && subfileIndex.getId() != null ? subfileIndex.getId() : index.getId());
      sysEmrLog.setMrFileName(subfileIndex != null && subfileIndex.getId() != null ? subfileIndex.getMrFileShowName() : index.getMrFileShowName());
      sysEmrLog.setMrTypeCd(subfileIndex != null && subfileIndex.getId() != null ? subfileIndex.getMrType() : index.getMrType());
      if (StringUtils.isNotEmpty(sysEmrLog.getMrTypeCd())) {
         String mrTypeName = this.sysDictDataService.selectDictLabel("s004", sysEmrLog.getMrTypeCd());
         sysEmrLog.setMrTypeName(mrTypeName);
      }

      sysEmrLog.setOptType(optType);
      sysEmrLog.setOptTypeName(optTypeName);
      sysEmrLog.setCrePerCode(basEmployee.getEmplNumber());
      sysEmrLog.setCrePerName(basEmployee.getEmplName());
      sysEmrLog.setIp(ip);

      try {
         this.sysEmrLogMapper.insertSysEmrLog(sysEmrLog);
      } catch (Exception e) {
         this.log.warn(sysEmrLog.toString());
         this.log.warn("病历日志记录出现异常：", e);
      }

   }

   public int updateSysEmrLog(SysEmrLog sysEmrLog) {
      return this.sysEmrLogMapper.updateSysEmrLog(sysEmrLog);
   }

   public int deleteSysEmrLogByIds(Long[] ids) {
      return this.sysEmrLogMapper.deleteSysEmrLogByIds(ids);
   }

   public int deleteSysEmrLogById(Long id) {
      return this.sysEmrLogMapper.deleteSysEmrLogById(id);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void insertSysLog(InsertIndexLogVo vo, String ipAddress) throws Exception {
      VisitinfoPersonalVo visitinfoVo = this.visitinfoService.selectVisitinfoPersonalById(vo.getPatientId());
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      SysEmrLog sysEmrLog = new SysEmrLog();
      sysEmrLog.setId(SnowIdUtils.uniqueLong());
      sysEmrLog.setInpNo(visitinfoVo.getInpNo());
      sysEmrLog.setPatientId(vo.getPatientId());
      sysEmrLog.setPatientName(visitinfoVo.getPatientName());
      sysEmrLog.setMrFileId(vo.getId());
      sysEmrLog.setMrFileName(vo.getIndexName());
      sysEmrLog.setMrTypeCd(vo.getEmrTypeCode());
      if (StringUtils.isNotEmpty(sysEmrLog.getMrTypeCd())) {
         String mrTypeName = this.sysDictDataService.selectDictLabel("s004", sysEmrLog.getMrTypeCd());
         sysEmrLog.setMrTypeName(mrTypeName);
      }

      String typeName = this.sysDictDataService.selectDictLabel("s013", vo.getTypeCode());
      sysEmrLog.setOptType(vo.getTypeCode());
      sysEmrLog.setOptTypeName(typeName);
      sysEmrLog.setCrePerCode(basEmployee.getEmplNumber());
      sysEmrLog.setCrePerName(basEmployee.getEmplName());
      sysEmrLog.setIp(ipAddress);
      this.sysEmrLogMapper.insertSysEmrLog(sysEmrLog);
   }
}
