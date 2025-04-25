package com.emr.project.qc.service.impl;

import com.emr.common.constant.CommonConstants;
import com.emr.common.utils.AgeUtil;
import com.emr.common.utils.Base64Util;
import com.emr.common.utils.DateUtils;
import com.emr.common.utils.ExcelUtils;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.common.utils.StringUtils;
import com.emr.framework.redis.RedisCache;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.project.borrowing.domain.EmrIndexBorrow;
import com.emr.project.borrowing.service.IEmrIndexBorrowService;
import com.emr.project.common.service.ICommonService;
import com.emr.project.emr.domain.EmrBinary;
import com.emr.project.emr.domain.Index;
import com.emr.project.emr.domain.SubfileIndex;
import com.emr.project.emr.domain.vo.EmrTaskInfoVo;
import com.emr.project.emr.service.IEmrBinaryService;
import com.emr.project.emr.service.IIndexService;
import com.emr.project.emr.service.ISubfileIndexService;
import com.emr.project.mrhp.domain.MrHp;
import com.emr.project.pat.domain.vo.BackLogVo;
import com.emr.project.pat.domain.vo.VisitinfoVo;
import com.emr.project.pat.service.IVisitinfoService;
import com.emr.project.qc.domain.EmrMessage;
import com.emr.project.qc.domain.EmrQcCommRecord;
import com.emr.project.qc.domain.EmrQcList;
import com.emr.project.qc.domain.EmrQcListScore;
import com.emr.project.qc.domain.EmrQcRecord;
import com.emr.project.qc.domain.QcScoreDedDetail;
import com.emr.project.qc.domain.QcScoreDedRule;
import com.emr.project.qc.domain.QcScoreItem;
import com.emr.project.qc.domain.vo.EmrMessageVo;
import com.emr.project.qc.domain.vo.EmrQcFlowVo;
import com.emr.project.qc.domain.vo.EmrQcListStatisticVo;
import com.emr.project.qc.domain.vo.EmrQcListVo;
import com.emr.project.qc.mapper.EmrMessageMapper;
import com.emr.project.qc.mapper.EmrQcListMapper;
import com.emr.project.qc.mapper.EmrQcListScoreMapper;
import com.emr.project.qc.mapper.QcScoreDedDetailMapper;
import com.emr.project.qc.mapper.QcScoreDedRuleMapper;
import com.emr.project.qc.mapper.QcScoreItemMapper;
import com.emr.project.qc.service.IEmrMessageService;
import com.emr.project.qc.service.IEmrQcCommRecordService;
import com.emr.project.qc.service.IEmrQcListScoreService;
import com.emr.project.qc.service.IEmrQcListService;
import com.emr.project.qc.service.IEmrQcRecordService;
import com.emr.project.qc.service.IQcRuleIterEmrService;
import com.emr.project.qc.util.IndexUtil;
import com.emr.project.system.domain.BasEmployee;
import com.emr.project.system.domain.SysDictData;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.service.ISysDictTypeService;
import com.emr.project.system.service.ISysEmrConfigService;
import com.google.common.collect.Maps;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmrQcListServiceImpl implements IEmrQcListService {
   private final Logger log = LoggerFactory.getLogger(EmrQcListServiceImpl.class);
   @Autowired
   private EmrQcListMapper emrQcListMapper;
   @Autowired
   private ISysDictTypeService sysDictTypeService;
   @Autowired
   private IEmrQcCommRecordService emrQcCommRecordService;
   @Autowired
   private IEmrQcRecordService emrQcRecordService;
   @Autowired
   private IVisitinfoService visitinfoService;
   @Autowired
   private ICommonService commonService;
   @Autowired
   private IQcRuleIterEmrService qcRuleIterEmrService;
   @Autowired
   private IEmrQcListScoreService emrQcListScoreService;
   @Autowired
   private IIndexService indexService;
   @Autowired
   private IEmrBinaryService emrBinaryService;
   @Autowired
   private ISubfileIndexService subfileIndexService;
   @Autowired
   private QcScoreDedRuleMapper qcScoreDedRuleMapper;
   @Autowired
   private QcScoreDedDetailMapper qcScoreDedDetailMapper;
   @Autowired
   private EmrQcListScoreMapper emrQcListScoreMapper;
   @Autowired
   private QcScoreItemMapper qcScoreItemMapper;
   @Autowired
   private EmrMessageMapper emrMessageMapper;
   @Autowired
   private IEmrIndexBorrowService emrIndexBorrowService;
   @Autowired
   private IEmrMessageService emrMessageService;
   @Autowired
   private RedisCache redisCache;
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;

   public EmrQcList selectEmrQcListById(Long id) {
      return this.emrQcListMapper.selectEmrQcListById(id);
   }

   public List selectEmrQcListList(EmrQcListVo emrQcList) throws Exception {
      if (StringUtils.isNotBlank(emrQcList.getQcType()) && emrQcList.getQcType().equals("2") && StringUtils.isNotBlank(emrQcList.getMrFileId())) {
         SubfileIndex subfileIndex = this.subfileIndexService.selectSubfileIndexById(Long.parseLong(emrQcList.getMrFileId()));
         Index index = subfileIndex != null ? this.indexService.selectIndexById(subfileIndex.getMainId()) : this.indexService.selectIndexById(Long.parseLong(emrQcList.getMrFileId()));
         if (index != null && index.getMrType().equals("MAINFILE")) {
            SubfileIndex subfileIndexParam = new SubfileIndex();
            subfileIndexParam.setMainId(index.getId());
            List<SubfileIndex> subfileIndexList = this.subfileIndexService.selectSubfileIndexList(subfileIndexParam);
            List<String> mrFileIdList = (List)subfileIndexList.stream().map((t) -> t.getId().toString()).collect(Collectors.toList());
            emrQcList.setMrFileId((String)null);
            emrQcList.setMrFileIdList(mrFileIdList);
         }
      }

      List<EmrQcListVo> list = this.emrQcListMapper.selectEmrqcListListIndexName(emrQcList);
      List<EmrQcListVo> resList = new ArrayList(list.size());
      List<Long> listIdList = (List)list.stream().map((t) -> t.getId()).collect(Collectors.toList());
      if (listIdList != null && !listIdList.isEmpty()) {
         List<SysDictData> dictDataList = this.sysDictTypeService.selectDictDataByType("s052");
         Map<String, String> dictDataMap = (Map)dictDataList.stream().collect(Collectors.toMap((t) -> t.getDictValue(), (t) -> t.getDictLabel()));
         List<EmrQcCommRecord> emrQcCommRecordList = this.emrQcCommRecordService.selectEmrQcCommRecordByMainId(listIdList);
         Map<Long, List<EmrQcCommRecord>> emrQcCommRecordListMap = (Map)emrQcCommRecordList.stream().collect(Collectors.groupingBy((t) -> t.getMainId()));

         for(EmrQcListVo qcList : list) {
            EmrQcListVo emrQcListVo = new EmrQcListVo();
            BeanUtils.copyProperties(qcList, emrQcListVo);
            List<EmrQcCommRecord> tmplList = (List)emrQcCommRecordListMap.get(qcList.getId());
            emrQcListVo.setQcCommRecordList(tmplList);
            emrQcListVo.setDefeLevelName(StringUtils.isNotBlank(emrQcListVo.getDefeLevel()) ? (String)dictDataMap.get(emrQcListVo.getDefeLevel()) : null);
            resList.add(emrQcListVo);
         }
      }

      return resList;
   }

   public EmrQcListVo selectEmrQcListById(EmrQcListVo emrQcList) throws Exception {
      EmrQcListVo emrQcListVo = this.emrQcListMapper.selectEmrqcListById(emrQcList);
      if (emrQcListVo != null) {
         List<SysDictData> dictDataList = this.sysDictTypeService.selectDictDataByType("s052");
         Map<String, String> dictDataMap = (Map)dictDataList.stream().collect(Collectors.toMap((t) -> t.getDictValue(), (t) -> t.getDictLabel()));
         emrQcListVo.setDefeLevelName(StringUtils.isNotBlank(emrQcListVo.getDefeLevel()) ? (String)dictDataMap.get(emrQcListVo.getDefeLevel()) : null);
      }

      return emrQcListVo;
   }

   public int insertEmrQcList(EmrQcListVo emrQcListVo) throws Exception {
      VisitinfoVo visitinfoVo = this.visitinfoService.selectVisitinfoById(emrQcListVo.getPatientId());
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      Long qcListId = SnowIdUtils.uniqueLong();
      emrQcListVo.setId(qcListId);
      emrQcListVo.setCrePerCode(basEmployee.getEmplNumber());
      emrQcListVo.setCrePerName(basEmployee.getEmplName());
      emrQcListVo.setQcdoctCd(basEmployee.getEmplNumber());
      emrQcListVo.setQcdoctName(basEmployee.getEmplName());
      emrQcListVo.setDoctCd(visitinfoVo.getResDocCd());
      emrQcListVo.setDoctName(visitinfoVo.getResDocName());
      emrQcListVo.setQcState("0");
      return this.emrQcListMapper.insertEmrQcList(emrQcListVo);
   }

   public int insertEmrQcListForZk(EmrQcListVo emrQcListVo) throws Exception {
      VisitinfoVo visitinfoVo = this.visitinfoService.selectVisitinfoById(emrQcListVo.getPatientId());
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      Long qcListId = SnowIdUtils.uniqueLong();
      emrQcListVo.setId(qcListId);
      emrQcListVo.setCrePerCode(basEmployee.getEmplNumber());
      emrQcListVo.setCrePerName(basEmployee.getEmplName());
      emrQcListVo.setQcdoctCd(basEmployee.getEmplNumber());
      emrQcListVo.setQcdoctName(basEmployee.getEmplName());
      emrQcListVo.setDoctCd(visitinfoVo.getResDocCd());
      emrQcListVo.setDoctName(visitinfoVo.getResDocName());
      emrQcListVo.setQcState("0");
      if (emrQcListVo.getDbEleId() == null) {
         emrQcListVo.setDbEleId(CommonConstants.EMR_QC_FEED.EMR_QC_FEED);
      }

      int add = this.emrQcListMapper.insertEmrQcList(emrQcListVo);
      if (add > 0) {
         QcScoreDedRule qcScoreDedRule = new QcScoreDedRule();
         qcScoreDedRule.setRuleId(emrQcListVo.getRuleId());
         qcScoreDedRule.setAppSeg(emrQcListVo.getQcSection());
         List<QcScoreDedRule> qcScoreDedRuleList = this.qcScoreDedRuleMapper.selectQcScoreDedByRuleAndPffaList(qcScoreDedRule);
         QcScoreDedRule qcScoreDedRuleRes = null;
         if (qcScoreDedRuleList != null && qcScoreDedRuleList.size() > 0) {
            qcScoreDedRuleRes = (QcScoreDedRule)qcScoreDedRuleList.get(0);
            QcScoreDedDetail qcScoreDedDetail = new QcScoreDedDetail();
            qcScoreDedDetail.setId(qcScoreDedRuleRes.getDedId());
            List<QcScoreDedDetail> qcScoreDedDetailList = this.qcScoreDedDetailMapper.selectDedDetailList(qcScoreDedDetail);
            QcScoreDedDetail qcScoreDedDetailRes = null;
            if (qcScoreDedDetailList != null && qcScoreDedDetailList.size() > 0) {
               qcScoreDedDetailRes = (QcScoreDedDetail)qcScoreDedDetailList.get(0);
            }

            EmrQcListScore emrQcListScore = new EmrQcListScore();
            emrQcListScore.setDedScoreDesc(qcScoreDedDetailRes.getDedScoreDesc());
            emrQcListScore.setDedId(qcScoreDedRuleRes.getDedId());
            emrQcListScore.setId(SnowIdUtils.uniqueLong());
            emrQcListScore.setQcListId(qcListId);
            emrQcListScore.setItemCd(qcScoreDedDetailRes.getItemCd());
            emrQcListScore.setItemName(qcScoreDedDetailRes.getItemName());
            emrQcListScore.setDedType(qcScoreDedDetailRes.getDedType());
            emrQcListScore.setDedScoreSingle(qcScoreDedDetailRes.getDedScoreSingle());
            emrQcListScore.setCreDate(new Date());
            emrQcListScore.setCrePerCode(basEmployee.getEmplNumber());
            emrQcListScore.setCrePerName(basEmployee.getEmplName());
            emrQcListScore.setParams(Maps.newHashMap());
            this.emrQcListScoreMapper.insertEmrQcListScore(emrQcListScore);
         } else {
            new QcScoreItem();
            EmrQcListScore emrQcListScore = new EmrQcListScore();
            if (StringUtils.isNotBlank(emrQcListVo.getItemCd())) {
               QcScoreItem qcScoreItem = this.qcScoreItemMapper.selectQcScoreItemById(Long.valueOf(emrQcListVo.getItemCd()));
               emrQcListScore.setDedScoreDesc(qcScoreItem.getItemDesc());
               emrQcListScore.setItemName(qcScoreItem.getItemName());
            }

            emrQcListScore.setId(SnowIdUtils.uniqueLong());
            emrQcListScore.setQcListId(qcListId);
            emrQcListScore.setItemCd(emrQcListVo.getItemCd());
            emrQcListScore.setDedType(emrQcListVo.getDedType());
            emrQcListScore.setDedScoreSingle(emrQcListVo.getDedScoreSingle());
            emrQcListScore.setCreDate(new Date());
            emrQcListScore.setCrePerCode(basEmployee.getEmplNumber());
            emrQcListScore.setCrePerName(basEmployee.getEmplName());
            emrQcListScore.setParams(Maps.newHashMap());
            this.emrQcListScoreMapper.insertEmrQcListScore(emrQcListScore);
         }
      }

      return 1;
   }

   public int updateEmrQcListForZk(EmrQcListVo emrQcListVo) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      emrQcListVo.setCrePerCode(basEmployee.getEmplNumber());
      emrQcListVo.setCrePerName(basEmployee.getEmplName());
      int update = this.emrQcListMapper.updateEmrQcList(emrQcListVo);
      if (update > 0) {
         QcScoreDedRule qcScoreDedRule = new QcScoreDedRule();
         qcScoreDedRule.setRuleId(emrQcListVo.getRuleId());
         qcScoreDedRule.setAppSeg(emrQcListVo.getQcSection());
         List<QcScoreDedRule> qcScoreDedRuleList = this.qcScoreDedRuleMapper.selectQcScoreDedByRuleAndPffaList(qcScoreDedRule);
         QcScoreDedRule qcScoreDedRuleRes = null;
         if (qcScoreDedRuleList != null && qcScoreDedRuleList.size() > 0) {
            qcScoreDedRuleRes = (QcScoreDedRule)qcScoreDedRuleList.get(0);
            QcScoreDedDetail qcScoreDedDetail = new QcScoreDedDetail();
            qcScoreDedDetail.setId(qcScoreDedRuleRes.getDedId());
            List<QcScoreDedDetail> qcScoreDedDetailList = this.qcScoreDedDetailMapper.selectDedDetailList(qcScoreDedDetail);
            QcScoreDedDetail qcScoreDedDetailRes = null;
            if (qcScoreDedDetailList != null && qcScoreDedDetailList.size() > 0) {
               qcScoreDedDetailRes = (QcScoreDedDetail)qcScoreDedDetailList.get(0);
            }

            EmrQcListScore emrQcListScore = new EmrQcListScore();
            emrQcListScore.setDedScoreDesc(qcScoreDedDetailRes.getDedScoreDesc());
            emrQcListScore.setDedId(qcScoreDedRuleRes.getDedId());
            emrQcListScore.setId(emrQcListVo.getListScoreId());
            emrQcListScore.setQcListId(emrQcListVo.getId());
            emrQcListScore.setItemCd(qcScoreDedDetailRes.getItemCd());
            emrQcListScore.setItemName(qcScoreDedDetailRes.getItemName());
            emrQcListScore.setDedType(qcScoreDedDetailRes.getDedType());
            emrQcListScore.setDedScoreSingle(qcScoreDedDetailRes.getDedScoreSingle());
            emrQcListScore.setCreDate(new Date());
            emrQcListScore.setCrePerCode(basEmployee.getEmplNumber());
            emrQcListScore.setCrePerName(basEmployee.getEmplName());
            emrQcListScore.setParams(Maps.newHashMap());
            if (emrQcListVo.getListScoreId() != null) {
               this.emrQcListScoreMapper.updateEmrQcListScore(emrQcListScore);
            } else {
               emrQcListScore.setId(SnowIdUtils.uniqueLong());
               this.emrQcListScoreMapper.insertEmrQcListScore(emrQcListScore);
            }
         } else {
            new QcScoreItem();
            EmrQcListScore emrQcListScore = new EmrQcListScore();
            if (StringUtils.isNotBlank(emrQcListVo.getItemCd())) {
               QcScoreItem qcScoreItem = this.qcScoreItemMapper.selectQcScoreItemById(Long.valueOf(emrQcListVo.getItemCd()));
               emrQcListScore.setDedScoreDesc(qcScoreItem.getItemDesc());
               emrQcListScore.setItemName(qcScoreItem.getItemName());
            } else {
               emrQcListScore.setItemName("");
            }

            emrQcListScore.setId(emrQcListVo.getListScoreId());
            emrQcListScore.setQcListId(emrQcListVo.getId());
            emrQcListScore.setItemCd(emrQcListVo.getItemCd());
            emrQcListScore.setDedType(emrQcListVo.getDedType());
            emrQcListScore.setDedScoreSingle(emrQcListVo.getDedScoreSingle());
            emrQcListScore.setCreDate(new Date());
            emrQcListScore.setCrePerCode(basEmployee.getEmplNumber());
            emrQcListScore.setCrePerName(basEmployee.getEmplName());
            emrQcListScore.setParams(Maps.newHashMap());
            if (emrQcListVo.getListScoreId() != null) {
               this.emrQcListScoreMapper.updateEmrQcListScore(emrQcListScore);
            } else {
               emrQcListScore.setId(SnowIdUtils.uniqueLong());
               this.emrQcListScoreMapper.insertEmrQcListScore(emrQcListScore);
            }
         }
      }

      return 1;
   }

   public int deleteEmrQcListByIdForZk(EmrQcListVo emrQcListVo) throws Exception {
      this.emrQcListMapper.deleteEmrQcListById(emrQcListVo.getId());
      if (emrQcListVo.getListScoreId() != null) {
         this.emrQcListScoreMapper.deleteEmrQcListScoreById(emrQcListVo.getListScoreId());
      }

      return 1;
   }

   public int insertEmrQcLists(List list) throws Exception {
      this.log.debug("saveIndex-insertEmrQcLists-start: " + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));

      for(EmrQcList emrQcList : list) {
         this.setTreatConInfo(emrQcList);
      }

      this.log.debug("saveIndex-insertEmrQcLists-end: " + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
      return this.emrQcListMapper.insertEmrQcLists(list);
   }

   public void insertListsByOvertimeUnWrite(List overtimeUnWriteList) throws Exception {
      String patientId = ((EmrTaskInfoVo)overtimeUnWriteList.get(0)).getPatientId();
      VisitinfoVo visitinfoVo = this.visitinfoService.selectVisitinfoByPatientId(patientId);
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      Date currDate = this.commonService.getDbSysdate();
      List<EmrQcList> list = new ArrayList(overtimeUnWriteList.size());

      for(EmrTaskInfoVo emrTaskInfoVo : overtimeUnWriteList) {
         EmrQcList temp = new EmrQcList();
         Long qcListId = SnowIdUtils.uniqueLong();
         temp.setId(qcListId);
         temp.setMainId(emrTaskInfoVo.getMainId());
         temp.setPatientId(emrTaskInfoVo.getPatientId());
         temp.setMrType(emrTaskInfoVo.getEmrTypeCode());
         temp.setMrTypeName(emrTaskInfoVo.getEmrTypeName());
         temp.setRuleName(emrTaskInfoVo.getRuleName());
         temp.setFlawDesc(emrTaskInfoVo.getMark());
         temp.setQcState("0");
         temp.setMrFileId(emrTaskInfoVo.getMrFileIdStr());
         if (StringUtils.isNotBlank(emrTaskInfoVo.getMrFileIdStr())) {
            temp.setQcState("5");
            temp.setConTime(currDate);
            temp.setConCd(basEmployee.getEmplNumber());
            temp.setConName(basEmployee.getEmplName());
            temp.setConResult("5");
         } else {
            temp.setMrFileId(String.valueOf(emrTaskInfoVo.getId()));
            temp.setDoctCd(visitinfoVo.getResDocCd());
            temp.setDoctName(visitinfoVo.getResDocName());
         }

         temp.setQcSection(emrTaskInfoVo.getQcSection());
         temp.setQcdoctCd(basEmployee.getEmplNumber());
         temp.setQcdoctName(basEmployee.getEmplName());
         temp.setQcDate(currDate);
         temp.setMissingFileFlag(1);
         temp.setCrePerCode(basEmployee.getEmplNumber());
         temp.setCrePerName(basEmployee.getEmplName());
         temp.setRuleId(emrTaskInfoVo.getRuleId());
         temp.setRuleName(emrTaskInfoVo.getRuleName());
         list.add(temp);
      }

      this.emrQcListMapper.insertEmrQcLists(list);
   }

   public void updateMissingFileListByMrFileId(String mrFileId) throws Exception {
      List<EmrQcList> list = this.emrQcListMapper.selectMissingFileListByMrFileId(mrFileId);
      if (list != null && !list.isEmpty()) {
         for(EmrQcList emrQcList : list) {
            emrQcList.setQcState("5");
            this.setTreatConInfo(emrQcList);
            this.emrQcListMapper.updateEmrQcList(emrQcList);
            this.randomCheckRecordToState31(emrQcList.getMainId());
         }
      }

   }

   public int updateEmrQcListById(EmrQcList emrQcList) throws Exception {
      return this.emrQcListMapper.updateEmrQcList(emrQcList);
   }

   public int updateEmrQcListByMrFileId(EmrQcList emrQcList) throws Exception {
      return this.emrQcListMapper.updateEmrQcListByMrFileId(emrQcList);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public int addQcDoctEmrQcLists(List list, MrHp mrHp, Index index, SubfileIndex subfileIndex) throws Exception {
      List<SysDictData> dictDataList = this.sysDictTypeService.selectDictDataByType("s004");
      Map<String, Object> indexInfoMap = IndexUtil.getIndexMrTypeAndName(dictDataList, mrHp, index, subfileIndex);
      String mrType = indexInfoMap.get("mrType").toString();
      String mrTypeName = indexInfoMap.get("mrTypeName").toString();
      String doctCd = indexInfoMap.get("doctCd").toString();
      String doctName = indexInfoMap.get("doctName").toString();
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      List<EmrQcList> addList = new ArrayList(list.size());
      String mrFileShowName = subfileIndex == null ? (index == null ? "病案首页" : index.getMrFileShowName()) : subfileIndex.getMrFileShowName();

      for(EmrQcListVo emrQcListVo : list) {
         emrQcListVo.setId(SnowIdUtils.uniqueLong());
         emrQcListVo.setCrePerCode(basEmployee.getEmplNumber());
         emrQcListVo.setCrePerName(basEmployee.getEmplName());
         emrQcListVo.setQcdoctCd(basEmployee.getEmplNumber());
         emrQcListVo.setQcdoctName(basEmployee.getEmplName());
         emrQcListVo.setMrType(mrType);
         emrQcListVo.setMrTypeName(mrTypeName);
         emrQcListVo.setDoctCd(doctCd);
         emrQcListVo.setDoctName(doctName);
         emrQcListVo.setQcState("0");
         emrQcListVo.setMrFileShowName(mrFileShowName);
         if (emrQcListVo.getMissingFileFlag() == null) {
            emrQcListVo.setMissingFileFlag(0);
         }

         addList.add(emrQcListVo);
      }

      List<EmrQcListVo> systemQcList = (List)list.stream().filter((t) -> t.getSystemQcId() != null).collect(Collectors.toList());
      if (systemQcList != null && !systemQcList.isEmpty()) {
         List<EmrQcList> systemQcUpdateList = new ArrayList(systemQcList.size());
         List<EmrQcCommRecord> qcCommRecordList = new ArrayList();

         for(EmrQcListVo emrQcListVo : systemQcList) {
            EmrQcList systemQc = new EmrQcList();
            systemQc.setId(emrQcListVo.getSystemQcId());
            systemQc.setQcState("2");
            systemQc.setAltPerName(basEmployee.getEmplName());
            systemQc.setAltPerCode(basEmployee.getEmplNumber());
            List<EmrQcCommRecord> tempList = emrQcListVo.getQcCommRecordList();
            List<EmrQcCommRecord> noIdTempList = (List)tempList.stream().filter((t) -> t.getId() == null).collect(Collectors.toList());
            if (noIdTempList != null && !noIdTempList.isEmpty()) {
               for(EmrQcCommRecord noIdTemp : noIdTempList) {
                  EmrQcCommRecord temp = new EmrQcCommRecord();
                  BeanUtils.copyProperties(noIdTemp, temp);
                  temp.setId(SnowIdUtils.uniqueLong());
                  temp.setMainId(emrQcListVo.getSystemQcId());
                  temp.setCrePerCode(basEmployee.getEmplNumber());
                  temp.setCrePerName(basEmployee.getEmplName());
                  temp.setFedbPerName(basEmployee.getEmplName());
                  temp.setFedbPerId(basEmployee.getEmplNumber());
                  qcCommRecordList.add(temp);
               }
            }

            if (tempList != null && !tempList.isEmpty()) {
               for(EmrQcCommRecord hasIdtemp : tempList) {
                  EmrQcCommRecord temp = new EmrQcCommRecord();
                  BeanUtils.copyProperties(hasIdtemp, temp);
                  temp.setId(SnowIdUtils.uniqueLong());
                  temp.setMainId(emrQcListVo.getId());
                  temp.setCrePerCode(basEmployee.getEmplNumber());
                  temp.setCrePerName(basEmployee.getEmplName());
                  temp.setFedbPerName(basEmployee.getEmplName());
                  temp.setFedbPerId(basEmployee.getEmplNumber());
                  qcCommRecordList.add(temp);
               }
            }

            emrQcListVo.setQconTimes(tempList.size());
            systemQc.setQconTimes(tempList.size());
            this.setTreatConInfo(systemQc);
            systemQcUpdateList.add(systemQc);
         }

         if (!qcCommRecordList.isEmpty()) {
            this.emrQcCommRecordService.insertList(qcCommRecordList);
         }

         this.emrQcListMapper.updateEmrQcLists(systemQcUpdateList);
      }

      return this.emrQcListMapper.insertEmrQcLists(addList);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void addQcDoctEmrQc(EmrQcListVo emrQcListVo, MrHp mrHp, Index index, SubfileIndex subfileIndex) throws Exception {
      Long mrFileId = subfileIndex == null ? index.getId() : subfileIndex.getMainId();
      List<SysDictData> dictDataList = this.sysDictTypeService.selectDictDataByType("s004");
      Map<String, Object> indexInfoMap = IndexUtil.getIndexMrTypeAndName(dictDataList, mrHp, index, subfileIndex);
      String mrType = indexInfoMap.get("mrType").toString();
      String mrTypeName = indexInfoMap.get("mrTypeName").toString();
      String doctCd = indexInfoMap.get("doctCd").toString();
      String doctName = indexInfoMap.get("doctName").toString();
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      List<EmrQcList> addList = new ArrayList();
      String mrFileShowName = subfileIndex == null ? (index == null ? "病案首页" : index.getMrFileShowName()) : subfileIndex.getMrFileShowName();
      Long id = SnowIdUtils.uniqueLong();
      emrQcListVo.setId(id);
      emrQcListVo.setCrePerCode(basEmployee.getEmplNumber());
      emrQcListVo.setCrePerName(basEmployee.getEmplName());
      emrQcListVo.setQcdoctCd(basEmployee.getEmplNumber());
      emrQcListVo.setQcdoctName(basEmployee.getEmplName());
      emrQcListVo.setMrType(mrType);
      emrQcListVo.setMrTypeName(mrTypeName);
      emrQcListVo.setDoctCd(doctCd);
      emrQcListVo.setDoctName(doctName);
      emrQcListVo.setQcState("0");
      emrQcListVo.setMrFileShowName(mrFileShowName);
      if ("NNNNNN".equals(emrQcListVo.getEmrEleName())) {
         emrQcListVo.setEmrEleName("人工反馈");
         emrQcListVo.setDbEleId(CommonConstants.EMR_QC_FEED.EMR_QC_FEED);
      } else {
         emrQcListVo.setEmrEleName(emrQcListVo.getEmrEleName() != null ? emrQcListVo.getEmrEleName() : "人工反馈");
         emrQcListVo.setDbEleId(emrQcListVo.getElemId() != null ? Long.valueOf(emrQcListVo.getElemId()) : CommonConstants.EMR_QC_FEED.EMR_QC_FEED);
      }

      if (emrQcListVo.getMissingFileFlag() == null) {
         emrQcListVo.setMissingFileFlag(0);
      }

      if (StringUtils.isNotEmpty(emrQcListVo.getItemCd())) {
         EmrQcListScore emrQcListScore = new EmrQcListScore();
         emrQcListScore.setId(SnowIdUtils.uniqueLong());
         emrQcListScore.setQcListId(id);
         emrQcListScore.setDedScoreSingle(emrQcListVo.getDedScoreSingle());
         emrQcListScore.setDedType(emrQcListVo.getDedType());
         emrQcListScore.setItemCd(emrQcListVo.getItemCd());
         emrQcListScore.setItemName(emrQcListVo.getItemName());
         emrQcListScore.setCreDate(this.commonService.getDbSysdate());
         emrQcListScore.setCrePerCode(basEmployee.getEmplNumber());
         emrQcListScore.setCrePerName(basEmployee.getEmplName());
         String desc = this.genDedScoreDesc(emrQcListScore);
         emrQcListScore.setDedScoreDesc(desc);
         this.emrQcListScoreService.insertEmrQcListScore(emrQcListScore);
      }

      List<EmrQcList> systemQcUpdateList = new ArrayList();
      if (emrQcListVo.getSystemQcId() != null) {
         EmrQcList systemQc = new EmrQcList();
         systemQc.setId(emrQcListVo.getSystemQcId());
         systemQc.setQcState("2");
         systemQc.setAltPerName(basEmployee.getEmplName());
         systemQc.setAltPerCode(basEmployee.getEmplNumber());
         List<EmrQcCommRecord> tempList = emrQcListVo.getQcCommRecordList();
         List<EmrQcCommRecord> qcCommRecordList = new ArrayList();
         if (tempList != null && !tempList.isEmpty()) {
            for(EmrQcCommRecord hasIdtemp : tempList) {
               EmrQcCommRecord temp = new EmrQcCommRecord();
               BeanUtils.copyProperties(hasIdtemp, temp);
               temp.setId(SnowIdUtils.uniqueLong());
               temp.setMainId(emrQcListVo.getId());
               temp.setCrePerCode(basEmployee.getEmplNumber());
               temp.setCrePerName(basEmployee.getEmplName());
               temp.setFedbPerName(basEmployee.getEmplName());
               temp.setFedbPerId(basEmployee.getEmplNumber());
               qcCommRecordList.add(temp);
            }
         }

         emrQcListVo.setQconTimes(tempList.size());
         systemQc.setQconTimes(tempList.size());
         this.setTreatConInfo(systemQc);
         systemQcUpdateList.add(systemQc);
         if (!qcCommRecordList.isEmpty()) {
            this.emrQcCommRecordService.insertList(qcCommRecordList);
         }

         this.emrQcListMapper.updateEmrQcLists(systemQcUpdateList);
      }

      addList.add(emrQcListVo);
      this.emrQcListMapper.insertEmrQcLists(addList);
      if (StringUtils.isNotEmpty(emrQcListVo.getFlawDate())) {
         EmrBinary emrBinaryUpdate = new EmrBinary();
         emrBinaryUpdate.setMrFileId(mrFileId);
         emrBinaryUpdate.setAltPerCode(basEmployee.getEmplNumber());
         emrBinaryUpdate.setAltPerName(basEmployee.getEmplName());
         String mrCon = this.indexService.getMrCon(emrQcListVo.getBase64Str(), emrQcListVo.getXmlStr(), (String)null, (String)null);
         emrBinaryUpdate.setMrCon(mrCon);
         emrBinaryUpdate.setAltDate(this.commonService.getDbSysdate());
         this.emrBinaryService.updateEmrBinary(emrBinaryUpdate);
         Base64Util.decode(emrQcListVo.getBase64Str(), "emrBase64/" + emrQcListVo.getPatientId(), mrFileId.toString() + ".txt");
         String fileDateStr = this.commonService.getSystimestamp();
         Base64Util.decode(emrQcListVo.getBase64Str(), "emrLogBase64/" + emrQcListVo.getPatientId(), mrFileId.toString() + "_" + fileDateStr + ".txt");
      }

   }

   private String genDedScoreDesc(EmrQcListScore emrQcListScore) {
      String dedScoreDesc = "";
      switch (emrQcListScore.getDedType()) {
         case "ITEM_SCORE":
            dedScoreDesc = emrQcListScore.getDedScoreSingle() + "分/项";
            break;
         case "TIME_SCORE":
            dedScoreDesc = emrQcListScore.getDedScoreSingle() + "分/次";
            break;
         case "VETO_CGRADE":
            dedScoreDesc = "单项否决(乙)" + emrQcListScore.getDedScoreSingle() + "分";
            break;
         case "VETO_BGRADE":
            dedScoreDesc = "单项否决(丙)" + emrQcListScore.getDedScoreSingle() + "分";
            break;
         case "ONE_TIME":
            dedScoreDesc = emrQcListScore.getDedScoreSingle() + "分";
            break;
         case "POINT_SCORE":
            dedScoreDesc = emrQcListScore.getDedScoreSingle() + "分/处";
      }

      return dedScoreDesc;
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void updateQcDoctEmrQc(EmrQcListVo emrQcListVo) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      this.emrQcListScoreService.deleteEmrQcListScoreByQcId(emrQcListVo.getId());
      if (StringUtils.isNotEmpty(emrQcListVo.getItemCd())) {
         EmrQcListScore emrQcListScore = new EmrQcListScore();
         emrQcListScore.setQcListId(emrQcListVo.getId());
         emrQcListScore.setId(SnowIdUtils.uniqueLong());
         emrQcListScore.setDedType(emrQcListVo.getDedType());
         emrQcListScore.setDedScoreSingle(emrQcListVo.getDedScoreSingle());
         emrQcListScore.setItemCd(emrQcListVo.getItemCd());
         emrQcListScore.setItemName(emrQcListScore.getItemName());
         emrQcListScore.setCreDate(this.commonService.getDbSysdate());
         emrQcListScore.setCrePerCode(basEmployee.getEmplNumber());
         emrQcListScore.setCrePerName(basEmployee.getEmplName());
         this.emrQcListScoreService.insertEmrQcListScore(emrQcListScore);
      }

      this.emrQcListMapper.updateEmrQcList(emrQcListVo);
   }

   private void setTreatConInfo(EmrQcList emrQcList) throws Exception {
      String qcStateName = null;
      switch (emrQcList.getQcState()) {
         case "0":
            qcStateName = "未修改";
            break;
         case "1":
            qcStateName = "质控有误";
            break;
         case "2":
            qcStateName = "已转人工";
            break;
         case "3":
            qcStateName = "驳回";
            break;
         case "4":
            qcStateName = "已修改";
            break;
         case "5":
            qcStateName = "确认已修改";
            break;
         case "6":
            qcStateName = "已确认有误";
      }

      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      Date currDate = this.commonService.getDbSysdate();
      if (emrQcList.getQcState().equals("1") || emrQcList.getQcState().equals("4") || emrQcList.getQcState().equals("5") && emrQcList.getMissingFileFlag().equals(1)) {
         emrQcList.setTreatTime(currDate);
         emrQcList.setTreatCd(basEmployee.getEmplNumber());
         emrQcList.setTreatName(basEmployee.getEmplName());
         emrQcList.setTreatFlag(emrQcList.getQcState());
         emrQcList.setTreatDesc(qcStateName);
      }

      if (emrQcList.getQcState().equals("2") || emrQcList.getQcState().equals("5") && emrQcList.getMissingFileFlag().equals(0) || emrQcList.getQcState().equals("6")) {
         emrQcList.setConTime(currDate);
         emrQcList.setConCd(basEmployee.getEmplNumber());
         emrQcList.setConName(basEmployee.getEmplName());
         emrQcList.setConResult(emrQcList.getQcState());
         emrQcList.setConDesc(qcStateName);
      }

   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void updateEmrQcList(EmrQcList emrQcList, Long mainId, SysUser user) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      emrQcList.setAltPerCode(basEmployee.getEmplNumber());
      emrQcList.setAltPerName(basEmployee.getEmplName());
      this.setTreatConInfo(emrQcList);
      this.emrQcListMapper.updateEmrQcList(emrQcList);
      this.randomCheckRecordToState31(mainId);
      EmrQcListVo emrQcWclList = new EmrQcListVo();
      emrQcWclList.setPatientId(emrQcList.getPatientId());
      List<String> qcStateList = new ArrayList();
      qcStateList.add("0");
      emrQcWclList.setQcStateList(qcStateList);
      List<String> qcSectionList = new ArrayList();
      qcSectionList.add(emrQcList.getQcSection());
      emrQcWclList.setQcSectionList(qcSectionList);
      emrQcWclList.setMrFileId(emrQcList.getMrFileId());
      List<EmrQcList> list = this.emrQcListMapper.selectEmrQcListList(emrQcWclList);
      if (list == null || list.size() <= 0) {
         EmrMessage emrMessage = new EmrMessage();
         emrMessage.setBusId(emrQcList.getMrFileId());
         emrMessage.setMsgState(1);
         emrMessage.setPatientId(emrQcList.getPatientId());
         EmrMessage emrMessageReq = new EmrMessage();
         emrMessageReq.setBusId(emrQcList.getMrFileId());
         emrMessageReq.setMsgState(0);
         emrMessageReq.setPatientId(emrQcList.getPatientId());
         List<EmrMessage> emrMessageList = this.emrMessageMapper.selectByBusId(emrMessageReq);
         if (emrMessageList != null && emrMessageList.size() > 0 && StringUtils.isNotBlank(emrQcList.getMrFileId())) {
            emrMessage.setAltDate(this.commonService.getDbSysdate());
            this.emrMessageMapper.updateEmrMessageByBusId(emrMessage);
            List<EmrMessageVo> messageList = this.emrMessageService.selectEmrMessageList(new EmrMessageVo());
            if (messageList != null && !messageList.isEmpty()) {
               String messageKey = "message_key:" + user.getUserName();
               this.redisCache.deleteObject(messageKey);
               this.redisCache.setCacheList(messageKey, messageList);
            }
         }
      }

   }

   private void randomCheckRecordToState31(Long mainId) {
      EmrQcRecord emrQcRecord = this.emrQcRecordService.selectEmrQcRecordById(mainId);
      if (emrQcRecord.getQcSection().equals("03") && emrQcRecord.getState().equals("3")) {
         EmrQcListVo param = new EmrQcListVo();
         param.setMainId(mainId);
         List<EmrQcList> list = this.emrQcListMapper.selectEmrQcListList(param);
         List<EmrQcList> tempList = (List)list.stream().filter((t) -> t.getQcState().equals("0") || t.getQcState().equals("3")).collect(Collectors.toList());
         if (tempList.isEmpty()) {
            EmrQcRecord emrQcRecordParam = new EmrQcRecord();
            emrQcRecordParam.setId(emrQcRecord.getId());
            emrQcRecordParam.setState("31");
            emrQcRecordParam.setStateName("已修改");
            this.emrQcRecordService.updateEmrQcRecord(emrQcRecordParam);
         }
      }

   }

   public int deleteEmrQcListByIds(Long[] ids) {
      return this.emrQcListMapper.deleteEmrQcListByIds(ids);
   }

   public int deleteEmrQcListById(Long id) {
      return this.emrQcListMapper.deleteEmrQcListById(id);
   }

   public List selectQcDoctByPatientSection(EmrQcList emrQcList) throws Exception {
      return this.emrQcListMapper.selectQcDoctByPatientSection(emrQcList);
   }

   public List selectNoFinishListByPatients(String patientId, Long emrQcRecordId) throws Exception {
      List<EmrQcList> list = new ArrayList(1);
      if (StringUtils.isNotBlank(patientId)) {
         String qcStateNofinishs = this.sysEmrConfigService.selectSysEmrConfigByKey("0021");
         String[] qcStateArr = StringUtils.isNotBlank(qcStateNofinishs) ? qcStateNofinishs.split(",") : null;
         List<String> qcStateList = qcStateArr != null ? Arrays.asList(qcStateArr) : null;
         list = this.emrQcListMapper.selectByPatientsAndQcState(patientId, qcStateList, emrQcRecordId);
      }

      return list;
   }

   public AjaxResult getClickFlagVisibleFLag(EmrQcRecord emrQcRecord) throws Exception {
      boolean clickFlag = true;
      boolean visibleFLag = false;
      String msg = "";
      EmrQcListVo param = new EmrQcListVo();
      param.setMainId(emrQcRecord.getId());
      List<EmrQcList> list = this.emrQcListMapper.selectEmrQcListList(param);
      if (emrQcRecord.getQcSection().equals("03")) {
         switch (emrQcRecord.getState()) {
            case "1":
               clickFlag = true;
               visibleFLag = true;
               break;
            case "2":
               clickFlag = true;
               visibleFLag = false;
               break;
            case "3":
               clickFlag = true;
               visibleFLag = false;
               break;
            case "31":
               List<EmrQcList> temp03 = (List)list.stream().filter((t) -> t.getQcState().equals("1") || t.getQcState().equals("4")).collect(Collectors.toList());
               clickFlag = true;
               if (temp03 != null && !temp03.isEmpty()) {
                  clickFlag = false;
                  msg = "有未核验完成的缺陷，请核验完成";
               }

               visibleFLag = true;
         }
      }

      if (emrQcRecord.getQcSection().equals("02")) {
         switch (emrQcRecord.getState()) {
            case "1":
               clickFlag = true;
               visibleFLag = true;
               break;
            case "2":
               clickFlag = true;
               visibleFLag = false;
               break;
            case "3":
               clickFlag = true;
               visibleFLag = false;
         }
      }

      if (emrQcRecord.getQcSection().equals("05")) {
         switch (emrQcRecord.getState()) {
            case "1":
               clickFlag = true;
               visibleFLag = true;
               break;
            case "2":
               clickFlag = true;
               visibleFLag = false;
               break;
            case "3":
               clickFlag = true;
               visibleFLag = false;
         }
      }

      AjaxResult ajaxResult = AjaxResult.success();
      if (!clickFlag) {
         ajaxResult = AjaxResult.error(msg);
      }

      ajaxResult.put("clickFlag", clickFlag);
      ajaxResult.put("visibleFlag", visibleFLag);
      return ajaxResult;
   }

   public void updateQconTimesById(Long id) throws Exception {
      this.emrQcListMapper.updateQconTimesById(id);
   }

   public void updateStateByComm(EmrQcCommRecord emrQcCommRecord, SysUser user) throws Exception {
      EmrQcList emrQcList = this.emrQcListMapper.selectEmrQcListById(emrQcCommRecord.getMainId());
      EmrQcList param = new EmrQcList();
      param.setId(emrQcCommRecord.getMainId());
      param.setMainId(emrQcList.getMainId());
      param.setQcSection(emrQcCommRecord.getQcSection());
      param.setPatientId(emrQcCommRecord.getPatientId());
      switch (emrQcList.getQcState()) {
         case "0":
            param.setQcState("1");
            break;
         case "1":
            param.setQcState("3");
            break;
         case "3":
            param.setQcState("1");
            break;
         case "4":
            param.setQcState("3");
      }

      this.updateEmrQcList(param, emrQcList.getMainId(), user);
   }

   public List selectByPatientsAndQcState(String patientId, List qcStateList, Long mainId) throws Exception {
      return this.emrQcListMapper.selectByPatientsAndQcState(patientId, qcStateList, mainId);
   }

   public List selectByPatientsAndQcStateAndRecordBz(String patientId, List qcStateList, Long mainId, String recordBz) throws Exception {
      return this.emrQcListMapper.selectByPatientsAndQcStateAndRecordBz(patientId, qcStateList, mainId, recordBz);
   }

   public List selectEmrSubFileQcList(String patientId, List qcStateList, String qcSection) throws Exception {
      return this.emrQcListMapper.selectEmrSubFileQcList(patientId, qcStateList, qcSection);
   }

   public List selectUnUpdateEmrList(String patientId) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      List<BackLogVo> resultList = new ArrayList();
      List<EmrQcListVo> list = this.emrQcListMapper.selectUnUpdateEmrList(basEmployee.getEmplNumber(), patientId);
      if (CollectionUtils.isNotEmpty(list)) {
         List<EmrQcListVo> listRecall = (List)list.stream().filter((t) -> StringUtils.isNotBlank(t.getMrState()) && ("16".equals(t.getMrState()) || "14".equals(t.getMrState()))).collect(Collectors.toList());
         List<String> patientIds = (List)listRecall.stream().map((t) -> t.getPatientId()).distinct().collect(Collectors.toList());
         EmrIndexBorrow emrIndexBorrow = new EmrIndexBorrow();
         emrIndexBorrow.setAdmissionNoList(patientIds);
         List<EmrIndexBorrow> emrIndexBorrowList = this.emrIndexBorrowService.selectEmrIndexBorrowValidList(emrIndexBorrow);
         List<String> borrowPatients = (List)emrIndexBorrowList.stream().map((t) -> t.getAdmissionNo()).distinct().collect(Collectors.toList());

         for(EmrQcListVo emrQcListVo : list) {
            if (!borrowPatients.contains(emrQcListVo.getPatientId()) && ("16".equals(emrQcListVo.getMrState()) || "14".equals(emrQcListVo.getMrState()))) {
               emrQcListVo.setIsRecallApply("0");
            }
         }

         Map<String, List<EmrQcListVo>> map = (Map)list.stream().filter((s) -> s.getMrFileId() != null).collect(Collectors.groupingBy((s) -> s.getMrFileId()));
         List<EmrQcListVo> emrQcListVoListQx = (List)list.stream().filter((s) -> s.getMrFileId() == null).collect(Collectors.toList());

         for(String mrFileId : map.keySet()) {
            List<EmrQcListVo> emrQcListVoList = (List)map.get(mrFileId);
            BackLogVo backLogVo = new BackLogVo();
            EmrQcListVo emrQcListVo = (EmrQcListVo)emrQcListVoList.get(0);
            String bedNo = emrQcListVo.getBedNo() == null ? "" : emrQcListVo.getBedNo();
            String patientName = emrQcListVo.getPatientName() == null ? "" : emrQcListVo.getPatientName();
            String sex = emrQcListVo.getSex() == null ? "" : emrQcListVo.getSex();
            String caseNo = emrQcListVo.getRecordNo() == null ? "" : emrQcListVo.getRecordNo();
            String mrTypeName = emrQcListVo.getMrTypeName() == null ? "" : emrQcListVo.getMrTypeName();
            String flawDesc = emrQcListVo.getFlawDesc() == null ? "" : emrQcListVo.getFlawDesc();
            backLogVo.setMessageStr("【" + bedNo + "床 " + patientName + " " + sex + " " + caseNo + "】 患者的 【" + mrTypeName + "】 有质控缺陷 【" + flawDesc + "】，请及时修改");
            backLogVo.setType("3");
            backLogVo.setInpNo(emrQcListVo.getInpNo());
            backLogVo.setEmrFileId(Long.parseLong(emrQcListVo.getMrFileId()));
            if (StringUtils.isNotBlank(emrQcListVo.getEmrMainId())) {
               backLogVo.setMainId(Long.parseLong(emrQcListVo.getEmrMainId()));
            }

            backLogVo.setPatientId(emrQcListVo.getPatientId());
            backLogVo.setPatientMainId(emrQcListVo.getPatientMainId());
            backLogVo.setIsRecallApply(emrQcListVo.getIsRecallApply());
            backLogVo.setPatientName(emrQcListVo.getPatientName());
            backLogVo.setVisitId(emrQcListVo.getVisitId());
            backLogVo.setDeptCd(emrQcListVo.getDeptCd());
            backLogVo.setDeptName(emrQcListVo.getDeptName());
            backLogVo.setResDocCd(emrQcListVo.getResDocCd());
            backLogVo.setResDocName(emrQcListVo.getResDocName());
            backLogVo.setOutTime(emrQcListVo.getOutTime());
            backLogVo.setRecordNo(emrQcListVo.getRecordNo());
            backLogVo.setAge(AgeUtil.getAgeStr(emrQcListVo.getAgeY(), emrQcListVo.getAgeM(), emrQcListVo.getAgeD(), emrQcListVo.getAgeH(), emrQcListVo.getAgeMi()));
            backLogVo.setMrType(emrQcListVo.getMrType());
            resultList.add(backLogVo);
         }

         if (emrQcListVoListQx != null && !emrQcListVoListQx.isEmpty()) {
            for(EmrQcListVo emrQcListVo : emrQcListVoListQx) {
               BackLogVo backLogVo = new BackLogVo();
               String bedNo = emrQcListVo.getBedNo() == null ? "" : emrQcListVo.getBedNo();
               String patientName = emrQcListVo.getPatientName() == null ? "" : emrQcListVo.getPatientName();
               String sex = emrQcListVo.getSex() == null ? "" : emrQcListVo.getSex();
               String caseNo = emrQcListVo.getRecordNo() == null ? "" : emrQcListVo.getRecordNo();
               String mrTypeName = emrQcListVo.getMrTypeName() == null ? "" : emrQcListVo.getMrTypeName();
               String flawDesc = emrQcListVo.getFlawDesc() == null ? "" : emrQcListVo.getFlawDesc();
               backLogVo.setMessageStr("【" + bedNo + "床 " + patientName + " " + sex + " " + caseNo + "】 患者的 【" + mrTypeName + "】 有质控缺陷 【" + flawDesc + "】，请及时修改");
               backLogVo.setType("3");
               backLogVo.setInpNo(emrQcListVo.getInpNo());
               backLogVo.setPatientId(emrQcListVo.getPatientId());
               backLogVo.setIsRecallApply(emrQcListVo.getIsRecallApply());
               backLogVo.setPatientName(emrQcListVo.getPatientName());
               backLogVo.setVisitId(emrQcListVo.getVisitId());
               backLogVo.setDeptCd(emrQcListVo.getDeptCd());
               backLogVo.setDeptName(emrQcListVo.getDeptName());
               backLogVo.setResDocCd(emrQcListVo.getResDocCd());
               backLogVo.setResDocName(emrQcListVo.getResDocName());
               backLogVo.setOutTime(emrQcListVo.getOutTime());
               backLogVo.setRecordNo(emrQcListVo.getRecordNo());
               backLogVo.setAge(AgeUtil.getAgeStr(emrQcListVo.getAgeY(), emrQcListVo.getAgeM(), emrQcListVo.getAgeD(), emrQcListVo.getAgeH(), emrQcListVo.getAgeMi()));
               backLogVo.setMrType(emrQcListVo.getMrType());
               resultList.add(backLogVo);
            }
         }
      }

      return resultList;
   }

   public void addQcOrderEmrQcLists(EmrQcListVo emrQcList) throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      BasEmployee basEmployee = sysUser.getBasEmployee();
      List<EmrQcList> insert = new ArrayList();
      Long id = SnowIdUtils.uniqueLong();
      emrQcList.setId(id);
      emrQcList.setMrType("64");
      emrQcList.setMrTypeName("医嘱本");
      emrQcList.setQconTimes(0);
      emrQcList.setQcState("0");
      emrQcList.setQcdoctCd(basEmployee.getEmplNumber());
      emrQcList.setQcdoctName(basEmployee.getEmplName());
      emrQcList.setQcDate(this.commonService.getDbSysdate());
      emrQcList.setCrePerCode(basEmployee.getEmplNumber());
      emrQcList.setCrePerName(basEmployee.getEmplName());
      emrQcList.setCreDate(this.commonService.getDbSysdate());
      emrQcList.setMissingFileFlag(0);
      insert.add(emrQcList);
      if (StringUtils.isNotEmpty(emrQcList.getItemCd())) {
         EmrQcListScore emrQcListScore = new EmrQcListScore();
         emrQcListScore.setId(SnowIdUtils.uniqueLong());
         emrQcListScore.setQcListId(id);
         emrQcListScore.setDedScoreSingle(emrQcList.getDedScoreSingle());
         emrQcListScore.setDedType(emrQcList.getDedType());
         emrQcListScore.setItemCd(emrQcList.getItemCd());
         emrQcListScore.setItemName(emrQcList.getItemName());
         emrQcListScore.setCreDate(this.commonService.getDbSysdate());
         emrQcListScore.setCrePerCode(basEmployee.getEmplNumber());
         emrQcListScore.setCrePerName(basEmployee.getEmplName());
         String desc = this.genDedScoreDesc(emrQcListScore);
         emrQcListScore.setDedScoreDesc(desc);
         this.emrQcListScoreService.insertEmrQcListScore(emrQcListScore);
      }

      this.emrQcListMapper.insertEmrQcLists(insert);
   }

   public void deleteEmrQcList(EmrQcListVo emrQcListVo) throws Exception {
      this.emrQcListMapper.deleteEmrQcListById(emrQcListVo.getId());
      this.emrQcListScoreService.deleteEmrQcListScoreByQcId(emrQcListVo.getId());
   }

   public List getEmrQcFlowStatistic(EmrQcFlowVo emrQcFlowVo) {
      List<EmrQcListStatisticVo> emrQcFlowList = this.emrQcListMapper.getQcFlowStatistic(emrQcFlowVo);
      if (emrQcFlowList != null && emrQcFlowList.size() > 0) {
         for(EmrQcListStatisticVo emrQcListStatisticVo : emrQcFlowList) {
            if ("0".equals(emrQcListStatisticVo.getQcState())) {
               emrQcListStatisticVo.setQcStateName("未修改");
            } else if ("1".equals(emrQcListStatisticVo.getQcState())) {
               emrQcListStatisticVo.setQcStateName("质控有误");
            } else if ("2".equals(emrQcListStatisticVo.getQcState())) {
               emrQcListStatisticVo.setQcStateName("已转人工");
            } else if ("3".equals(emrQcListStatisticVo.getQcState())) {
               emrQcListStatisticVo.setQcStateName("驳回");
            } else if ("4".equals(emrQcListStatisticVo.getQcState())) {
               emrQcListStatisticVo.setQcStateName("已修改");
            } else if ("5".equals(emrQcListStatisticVo.getQcState())) {
               emrQcListStatisticVo.setQcStateName("确认已修改");
            } else if ("6".equals(emrQcListStatisticVo.getQcState())) {
               emrQcListStatisticVo.setQcStateName("已确认有误");
            }

            if ("01".equals(emrQcListStatisticVo.getQcSection())) {
               emrQcListStatisticVo.setQcSectionName("实时质控");
            } else if ("02".equals(emrQcListStatisticVo.getQcSection())) {
               emrQcListStatisticVo.setQcSectionName("科室质控");
            } else if ("03".equals(emrQcListStatisticVo.getQcSection())) {
               emrQcListStatisticVo.setQcSectionName("抽查质控");
            } else if ("04".equals(emrQcListStatisticVo.getQcSection())) {
               emrQcListStatisticVo.setQcSectionName("离线质控");
            } else if ("05".equals(emrQcListStatisticVo.getQcSection())) {
               emrQcListStatisticVo.setQcSectionName("终末质控");
            }
         }
      }

      return emrQcFlowList;
   }

   public AjaxResult getEmrQcFlowStatisticExport(EmrQcFlowVo emrQcFlowVo, HttpServletResponse response) throws Exception {
      List<EmrQcListStatisticVo> emrQcFlowList = this.emrQcListMapper.getQcFlowStatistic(emrQcFlowVo);
      if (emrQcFlowList != null && emrQcFlowList.size() > 0) {
         for(EmrQcListStatisticVo emrQcListStatisticVo : emrQcFlowList) {
            if ("0".equals(emrQcListStatisticVo.getQcState())) {
               emrQcListStatisticVo.setQcStateName("未修改");
            } else if ("1".equals(emrQcListStatisticVo.getQcState())) {
               emrQcListStatisticVo.setQcStateName("质控有误");
            } else if ("2".equals(emrQcListStatisticVo.getQcState())) {
               emrQcListStatisticVo.setQcStateName("已转人工");
            } else if ("3".equals(emrQcListStatisticVo.getQcState())) {
               emrQcListStatisticVo.setQcStateName("驳回");
            } else if ("4".equals(emrQcListStatisticVo.getQcState())) {
               emrQcListStatisticVo.setQcStateName("已修改");
            } else if ("5".equals(emrQcListStatisticVo.getQcState())) {
               emrQcListStatisticVo.setQcStateName("确认已修改");
            } else if ("6".equals(emrQcListStatisticVo.getQcState())) {
               emrQcListStatisticVo.setQcStateName("已确认有误");
            }

            if ("01".equals(emrQcListStatisticVo.getQcSection())) {
               emrQcListStatisticVo.setQcSectionName("实时质控");
            } else if ("02".equals(emrQcListStatisticVo.getQcSection())) {
               emrQcListStatisticVo.setQcSectionName("科室质控");
            } else if ("03".equals(emrQcListStatisticVo.getQcSection())) {
               emrQcListStatisticVo.setQcSectionName("抽查质控");
            } else if ("04".equals(emrQcListStatisticVo.getQcSection())) {
               emrQcListStatisticVo.setQcSectionName("离线质控");
            } else if ("05".equals(emrQcListStatisticVo.getQcSection())) {
               emrQcListStatisticVo.setQcSectionName("终末质控");
            }
         }
      }

      if (emrQcFlowVo.getOutHospitalTimeBegin() != null) {
         emrQcFlowVo.setOutHospitalTimeBeginS(DateUtils.dateFormatS(emrQcFlowVo.getOutHospitalTimeBegin(), "yyyy-MM-dd"));
      }

      if (emrQcFlowVo.getOutHospitalTimeEnd() != null) {
         Date outTimeEnd = emrQcFlowVo.getOutHospitalTimeEnd();
         emrQcFlowVo.setOutHospitalTimeEnd(DateUtils.addDays(outTimeEnd, -1));
      }

      if (emrQcFlowVo.getOutHospitalTimeEnd() != null) {
         emrQcFlowVo.setOutHospitalTimeEndS(DateUtils.dateFormatS(emrQcFlowVo.getOutHospitalTimeEnd(), "yyyy-MM-dd"));
      }

      emrQcFlowList = (List)emrQcFlowList.stream().map((a) -> {
         EmrQcListStatisticVo emrQcListStatisticVo = new EmrQcListStatisticVo();
         BeanUtils.copyProperties(a, emrQcListStatisticVo);
         if (a.getInDeptTime() != null) {
            emrQcListStatisticVo.setInDeptTimeS(DateUtils.dateFormatS(a.getInDeptTime(), "yyyy-MM-dd HH:mm"));
         }

         if (a.getOutDeptTime() != null) {
            emrQcListStatisticVo.setOutDeptTimeS(DateUtils.dateFormatS(a.getOutDeptTime(), "yyyy-MM-dd HH:mm"));
         }

         if (a.getFeedbackQcTime() != null) {
            emrQcListStatisticVo.setFeedbackQcTimeS(DateUtils.dateFormatS(a.getFeedbackQcTime(), "yyyy-MM-dd HH:mm"));
         }

         if (a.getFeedbackQcTime() != null) {
            emrQcListStatisticVo.setFeedbackQcTimeS(DateUtils.dateFormatS(a.getFeedbackQcTime(), "yyyy-MM-dd HH:mm"));
         }

         return emrQcListStatisticVo;
      }).collect(Collectors.toList());
      List<LinkedHashMap<String, Object>> mapList = new ArrayList();
      List<EmrQcListStatisticVo> sizeList = new ArrayList();
      LinkedHashMap<String, Object> tableMap = new LinkedHashMap();
      if (emrQcFlowList != null && !emrQcFlowList.isEmpty()) {
         Map<Long, List<EmrQcListStatisticVo>> map = (Map)emrQcFlowList.stream().collect(Collectors.groupingBy(EmrQcListStatisticVo::getQcId, LinkedHashMap::new, Collectors.toList()));

         for(Long qcId : map.keySet()) {
            List<EmrQcListStatisticVo> voList = (List)map.get(qcId);
            if (sizeList.size() < voList.size()) {
               sizeList = voList;
            }
         }

         if (sizeList != null && !sizeList.isEmpty()) {
            for(Long qcId : map.keySet()) {
               List<EmrQcListStatisticVo> voList = (List)map.get(qcId);
               LinkedHashMap<String, Object> voMap = new LinkedHashMap();
               voMap.put("patientId", ((EmrQcListStatisticVo)voList.get(0)).getPatientId());
               voMap.put("patientName", ((EmrQcListStatisticVo)voList.get(0)).getPatientName());
               voMap.put("deptName", ((EmrQcListStatisticVo)voList.get(0)).getDeptName());
               voMap.put("resDocName", ((EmrQcListStatisticVo)voList.get(0)).getResDocName());
               voMap.put("inDeptTimeS", ((EmrQcListStatisticVo)voList.get(0)).getInDeptTimeS());
               voMap.put("outDeptTimeS", ((EmrQcListStatisticVo)voList.get(0)).getOutDeptTimeS());
               voMap.put("feedbackQcTimeS", ((EmrQcListStatisticVo)voList.get(0)).getFeedbackQcTimeS());
               voMap.put("qcSectionName", ((EmrQcListStatisticVo)voList.get(0)).getQcSectionName());
               voMap.put("doctName", ((EmrQcListStatisticVo)voList.get(0)).getDoctName());
               voMap.put("crePerName", ((EmrQcListStatisticVo)voList.get(0)).getCrePerName());
               voMap.put("dedScoreSingle", ((EmrQcListStatisticVo)voList.get(0)).getDedScoreSingle());
               voMap.put("mrTypeName", ((EmrQcListStatisticVo)voList.get(0)).getMrTypeName());
               voMap.put("qcStateName", ((EmrQcListStatisticVo)voList.get(0)).getQcStateName());
               voMap.put("flawDesc", ((EmrQcListStatisticVo)voList.get(0)).getFlawDesc());
               mapList.add(voMap);
            }
         }
      }

      tableMap.put("deptName", "科室名称");
      tableMap.put("resDocName", "管床医师名称");
      tableMap.put("patientName", "患者姓名");
      tableMap.put("patientId", "患者住院号");
      tableMap.put("inDeptTimeS", "入院日期");
      tableMap.put("outDeptTimeS", "出院日期");
      tableMap.put("feedbackQcTimeS", "缺陷反馈日期");
      tableMap.put("qcSectionName", "质控环节");
      tableMap.put("doctName", "质控医生");
      tableMap.put("crePerName", "创建医生");
      tableMap.put("dedScoreSingle", "扣分分数");
      tableMap.put("mrTypeName", "缺陷病历");
      tableMap.put("flawDesc", "缺陷描述");
      tableMap.put("qcStateName", "缺陷状态");
      Workbook wb = new SXSSFWorkbook(500);
      String tableName = "病历缺陷统计";
      ExcelUtils.getQcCaseStatisticExport(wb, mapList, tableMap, emrQcFlowList, emrQcFlowVo, tableName);
      String fileName = tableName + ".xlsx";
      response.setHeader("Content-disposition", "attachment;filename=" + fileName);
      OutputStream stream = new FileOutputStream(ExcelUtils.getAbsoluteFile(fileName));
      if (null != wb && null != stream) {
         wb.write(stream);
         stream.close();
      }

      return AjaxResult.success(fileName);
   }

   public List selectEmrQcListByMrFileId(Long mrFileId, List qcSectionList) {
      return this.emrQcListMapper.selectEmrQcListByMrFileId(mrFileId, qcSectionList);
   }

   public void addQcMrHpEmrQcLists(EmrQcListVo emrQcList) throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      BasEmployee basEmployee = sysUser.getBasEmployee();
      List<EmrQcList> insert = new ArrayList();
      Long id = SnowIdUtils.uniqueLong();
      emrQcList.setId(id);
      emrQcList.setMrType("61");
      emrQcList.setMrTypeName("病案首页");
      emrQcList.setQconTimes(0);
      emrQcList.setQcState("0");
      emrQcList.setQcdoctCd(basEmployee.getEmplNumber());
      emrQcList.setQcdoctName(basEmployee.getEmplName());
      emrQcList.setQcDate(this.commonService.getDbSysdate());
      emrQcList.setCrePerCode(basEmployee.getEmplNumber());
      emrQcList.setCrePerName(basEmployee.getEmplName());
      emrQcList.setCreDate(this.commonService.getDbSysdate());
      emrQcList.setMissingFileFlag(0);
      insert.add(emrQcList);
      if (StringUtils.isNotEmpty(emrQcList.getItemCd())) {
         EmrQcListScore emrQcListScore = new EmrQcListScore();
         emrQcListScore.setId(SnowIdUtils.uniqueLong());
         emrQcListScore.setQcListId(id);
         emrQcListScore.setDedScoreSingle(emrQcList.getDedScoreSingle());
         emrQcListScore.setDedType(emrQcList.getDedType());
         emrQcListScore.setItemCd(emrQcList.getItemCd());
         emrQcListScore.setItemName(emrQcList.getItemName());
         emrQcListScore.setCreDate(this.commonService.getDbSysdate());
         emrQcListScore.setCrePerCode(basEmployee.getEmplNumber());
         emrQcListScore.setCrePerName(basEmployee.getEmplName());
         String desc = this.genDedScoreDesc(emrQcListScore);
         emrQcListScore.setDedScoreDesc(desc);
         this.emrQcListScoreService.insertEmrQcListScore(emrQcListScore);
      }

      this.emrQcListMapper.insertEmrQcLists(insert);
   }
}
