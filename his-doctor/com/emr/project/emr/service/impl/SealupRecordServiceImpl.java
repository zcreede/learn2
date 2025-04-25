package com.emr.project.emr.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.emr.common.constant.CommonConstants;
import com.emr.common.utils.DateUtils;
import com.emr.common.utils.IPAddressUtil;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.project.emr.domain.Index;
import com.emr.project.emr.domain.SealupFile;
import com.emr.project.emr.domain.SealupRecord;
import com.emr.project.emr.domain.SubfileIndex;
import com.emr.project.emr.domain.SysEmrLog;
import com.emr.project.emr.domain.vo.IndexListVo;
import com.emr.project.emr.domain.vo.IndexStateVo;
import com.emr.project.emr.domain.vo.PasswordVo;
import com.emr.project.emr.domain.vo.SealupVo;
import com.emr.project.emr.mapper.SealupRecordMapper;
import com.emr.project.emr.mapper.SubfileIndexMapper;
import com.emr.project.emr.service.IIndexService;
import com.emr.project.emr.service.ISealupFileService;
import com.emr.project.emr.service.ISealupRecordService;
import com.emr.project.emr.service.ISubfileIndexService;
import com.emr.project.emr.service.ISysEmrLogService;
import com.emr.project.pat.domain.vo.VisitinfoPersonalVo;
import com.emr.project.system.domain.SysUser;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SealupRecordServiceImpl implements ISealupRecordService {
   @Autowired
   private SealupRecordMapper sealupRecordMapper;
   @Autowired
   private IIndexService indexService;
   @Autowired
   private SubfileIndexMapper subfileIndexMapper;
   @Autowired
   private ISubfileIndexService subfileIndexService;
   @Autowired
   private ISealupFileService sealupFileService;
   @Autowired
   private ISysEmrLogService sysEmrLogService;

   public SealupRecord selectSealupRecordById(Long id) {
      return this.sealupRecordMapper.selectSealupRecordById(id);
   }

   public List selectSealupRecordList(SealupRecord sealupRecord) {
      return this.sealupRecordMapper.selectSealupRecordList(sealupRecord);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void insertSealupRecord(PasswordVo passwordVo, HttpServletRequest httpServletRequest) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      SealupRecord sealupRecord = passwordVo.getSealupRecord();
      IndexListVo vo = this.updateIndexState(sealupRecord.getPatientId(), CommonConstants.SEALUP_RECORD_STATUS.STATUS_1, httpServletRequest);
      List<Index> indexList = vo.getIndexList();
      List<SubfileIndex> subfileIndexList = vo.getSubfileIndexList();
      this.updateSealupRecord(user, passwordVo, sealupRecord);
      sealupRecord.setId(SnowIdUtils.uniqueLong());
      sealupRecord.setSealPerson(user.getNickName());
      sealupRecord.setSealPersonCode(user.getUserName());
      sealupRecord.setCrePerCode(user.getUserName());
      sealupRecord.setCrePerName(user.getNickName());
      sealupRecord.setIsValid("1");
      this.sealupRecordMapper.insertSealupRecord(sealupRecord);
      this.addSealupFile(user, indexList, sealupRecord, subfileIndexList);
   }

   public int updateSealupRecord(SealupRecord sealupRecord) {
      return this.sealupRecordMapper.updateSealupRecord(sealupRecord);
   }

   public int deleteSealupRecordByIds(Long[] ids) {
      return this.sealupRecordMapper.deleteSealupRecordByIds(ids);
   }

   public int deleteSealupRecordById(Long id) {
      return this.sealupRecordMapper.deleteSealupRecordById(id);
   }

   public SealupRecord selectSealupRecordByInpNo(String inpNo) {
      return this.sealupRecordMapper.selectSealupRecordByInpNo(inpNo);
   }

   public SealupRecord selectSealupRecordByPatientId(String patientId) {
      return this.sealupRecordMapper.selectSealupRecordByPatientId(patientId);
   }

   public IndexStateVo indexSealupState(VisitinfoPersonalVo visitinfoPersonalVo) {
      IndexStateVo indexStateVo = new IndexStateVo();
      SealupRecord sealupRecord = this.sealupRecordMapper.selectSealupRecordByPatientId(visitinfoPersonalVo.getPatientId());
      if (sealupRecord == null) {
         indexStateVo.setIndexSealupState(CommonConstants.SEALUP_RECORD_STATUS.STATUS_0);
      } else {
         indexStateVo.setIndexSealupState(CommonConstants.SEALUP_RECORD_STATUS.STATUS_1);
      }

      indexStateVo.setVisitinfoPersonalVo(visitinfoPersonalVo);
      return indexStateVo;
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void againSealupRecord(PasswordVo passwordVo) throws Exception {
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void secureRecord(SealupRecord sealupRecord, HttpServletRequest httpServletRequest) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      this.updateIndexState(sealupRecord.getPatientId(), CommonConstants.SEALUP_RECORD_STATUS.STATUS_0, httpServletRequest);
      sealupRecord.setAltPerCode(user.getUserName());
      sealupRecord.setAltPerName(user.getNickName());
      sealupRecord.setStatus(CommonConstants.SEALUP_RECORD_STATUS.STATUS_0);
      sealupRecord.setNosealDate(DateUtils.getNowDate());
      sealupRecord.setUnsealPerson(user.getNickName());
      sealupRecord.setUnsealPersonCode(user.getUserName());
      this.sealupRecordMapper.updateSealupRecord(sealupRecord);
   }

   public List querySealupList(SealupVo sealupVo) {
      return this.sealupRecordMapper.querySealupList(sealupVo);
   }

   public Boolean addSealupFile(Index index, SubfileIndex subfileIndex) throws Exception {
      Boolean flag = false;
      String patientId = index.getPatientId();
      SealupRecord sealupRecord = this.selectSealupRecordByPatientId(patientId);
      if (sealupRecord != null) {
         flag = true;
         List<Index> indexList = null;
         List<SubfileIndex> subfileIndexList = null;
         if (subfileIndex != null && subfileIndex.getId() != null) {
            subfileIndexList = Arrays.asList(subfileIndex);
            subfileIndex.setLockState(1);
         } else {
            indexList = Arrays.asList(index);
            index.setLockState(1);
         }

         SysUser user = SecurityUtils.getLoginUser().getUser();
         this.addSealupFile(user, indexList, sealupRecord, subfileIndexList);
      }

      return flag;
   }

   public IndexListVo updateIndexState(String patientId, Integer lockState, HttpServletRequest httpServletRequest) throws Exception {
      IndexListVo indexListVo = new IndexListVo();
      Index index = new Index();
      index.setPatientId(patientId);
      index.setDelFlag("0");
      List<Index> allList = this.indexService.selectIndexList(index);
      index.setLockState(lockState);
      if (lockState == CommonConstants.SEALUP_RECORD_STATUS.STATUS_1) {
         index.setMrState("08");
      }

      this.indexService.updateIndexLockState(index);
      new ArrayList();
      List indexList;
      if (lockState == CommonConstants.SEALUP_RECORD_STATUS.STATUS_1) {
         indexList = (List)allList.stream().filter((s) -> !s.getMrType().equals("MAINFILE") && "08".equals(s.getMrState())).collect(Collectors.toList());
      } else {
         indexList = (List)allList.stream().filter((s) -> !s.getMrType().equals("MAINFILE")).collect(Collectors.toList());
      }

      List<Index> mainFileList = (List)allList.stream().filter((s) -> s.getMrType().equals("MAINFILE")).collect(Collectors.toList());
      List<SubfileIndex> subList = new ArrayList();
      if (mainFileList != null && mainFileList.size() > 0) {
         SubfileIndex subfileIndex = new SubfileIndex();
         subfileIndex.setMainId(((Index)mainFileList.get(0)).getId());
         subfileIndex.setLockState(lockState);
         if (lockState == CommonConstants.SEALUP_RECORD_STATUS.STATUS_1) {
            subfileIndex.setMrState("08");
         }

         this.subfileIndexService.updateSubFileLockState(subfileIndex);
         List var16 = this.subfileIndexMapper.selectSubfileIndexByMainId(((Index)mainFileList.get(0)).getId());
         subList = (List)var16.stream().filter((s) -> s.getMrState().equals("08")).collect(Collectors.toList());
      }

      indexListVo.setIndexList(indexList);
      indexListVo.setSubfileIndexList(subList);
      List<Object> all = new ArrayList();
      all.addAll(indexList);
      all.addAll(subList);
      List<SysEmrLog> sysEmrLogVoList = new ArrayList();
      if (all != null && all.size() > 0) {
         for(Object object : all) {
            Map map = JSONObject.parseObject(JSONObject.toJSONString(object));
            SysEmrLog sysEmrLog = new SysEmrLog();
            if (lockState == CommonConstants.SEALUP_RECORD_STATUS.STATUS_1) {
               sysEmrLog.setOptType("5");
               sysEmrLog.setOptTypeName("病历封存");
            } else {
               sysEmrLog.setOptType("6");
               sysEmrLog.setOptTypeName("病历启封");
            }

            sysEmrLog.setPatientId(patientId);
            sysEmrLog.setMrTypeCd(map.get("mrType").toString());
            sysEmrLog.setMrFileId(Long.parseLong(map.get("id").toString()));
            sysEmrLog.setMrFileName(map.get("mrFileShowName").toString());
            sysEmrLog.setIp(IPAddressUtil.getIPAddress(httpServletRequest));
            sysEmrLogVoList.add(sysEmrLog);
         }

         this.sysEmrLogService.insertSysEmrLog(sysEmrLogVoList);
      }

      return indexListVo;
   }

   public SealupRecord updateSealupRecord(SysUser user, PasswordVo passwordVo, SealupRecord sealupRecord) {
      sealupRecord.setStatus(CommonConstants.SEALUP_RECORD_STATUS.STATUS_1);
      String encryptHospital = SecurityUtils.encryptPassword(passwordVo.getHospital());
      String encryptPatient = SecurityUtils.encryptPassword(passwordVo.getPatient());
      String encryptWitness = SecurityUtils.encryptPassword(passwordVo.getWitness());
      sealupRecord.setSealPass(encryptHospital + "," + encryptPatient + "," + encryptWitness);
      return sealupRecord;
   }

   public void addSealupFile(SysUser user, List indexList, SealupRecord sealupRecord, List subfileIndexList) {
      List<SealupFile> fileList = new ArrayList();
      if (indexList != null && indexList.size() > 0) {
         for(Index param : indexList) {
            SealupFile sealupFile = new SealupFile();
            sealupFile.setId(SnowIdUtils.uniqueLong());
            sealupFile.setMainRecId(sealupRecord.getId());
            sealupFile.setFileId(param.getId());
            sealupFile.setCrePerCode(user.getUserName());
            sealupFile.setCrePerName(user.getNickName());
            fileList.add(sealupFile);
         }
      }

      if (subfileIndexList != null && subfileIndexList.size() > 0) {
         for(SubfileIndex param : subfileIndexList) {
            SealupFile sealupFile = new SealupFile();
            sealupFile.setId(SnowIdUtils.uniqueLong());
            sealupFile.setMainRecId(sealupRecord.getId());
            sealupFile.setFileId(param.getId());
            sealupFile.setCrePerCode(user.getUserName());
            sealupFile.setCrePerName(user.getNickName());
            fileList.add(sealupFile);
         }
      }

      if (fileList != null && fileList.size() > 0) {
         this.sealupFileService.insertSealupFileList(fileList);
      }

   }
}
