package com.emr.project.emr.service.impl;

import com.alibaba.fastjson.JSON;
import com.emr.common.constant.CommonConstants;
import com.emr.common.utils.Base64Util;
import com.emr.common.utils.DateUtils;
import com.emr.common.utils.SecurityUtils;
import com.emr.project.emr.domain.EmrBinary;
import com.emr.project.emr.domain.Index;
import com.emr.project.emr.domain.SubfileIndex;
import com.emr.project.emr.domain.SysEmrTypeConfig;
import com.emr.project.emr.domain.vo.IndexHFInfoPageVo;
import com.emr.project.emr.domain.vo.IndexVo;
import com.emr.project.emr.domain.vo.SubfileIndexVo;
import com.emr.project.emr.mapper.SubfileIndexMapper;
import com.emr.project.emr.service.IEmrBinaryService;
import com.emr.project.emr.service.IIndexService;
import com.emr.project.emr.service.ISubfileIndexService;
import com.emr.project.emr.service.ISysEmrTypeConfigService;
import com.emr.project.pat.domain.TestReport;
import com.emr.project.pat.domain.vo.DeptBEDateVo;
import com.emr.project.pat.domain.vo.ExamItemVo;
import com.emr.project.pat.domain.vo.TestReportVo;
import com.emr.project.pat.domain.vo.VisitinfoVo;
import com.emr.project.pat.service.IExamItemService;
import com.emr.project.pat.service.ITestReportService;
import com.emr.project.pat.service.ITransinfoService;
import com.emr.project.pat.service.IVisitinfoService;
import com.emr.project.qc.domain.vo.PatEventVo;
import com.emr.project.system.domain.BasEmployee;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.service.ISysEmrConfigService;
import com.emr.project.tmpl.domain.ElemAttri;
import com.emr.project.tmpl.domain.ElemDate;
import com.emr.project.tmpl.domain.ElemGender;
import com.emr.project.tmpl.domain.ElemMacro;
import com.emr.project.tmpl.domain.ElemSign;
import com.emr.project.tmpl.domain.TmplIndex;
import com.emr.project.tmpl.domain.vo.TempIndexSaveElemVo;
import com.emr.project.tmpl.domain.vo.TmplElemLinkVo;
import com.emr.project.tmpl.domain.vo.TmplIndexVo;
import com.emr.project.tmpl.mapper.TmplIndexMapper;
import com.emr.project.tmpl.service.IElemAttriService;
import com.emr.project.tmpl.service.IElemGenderService;
import com.emr.project.tmpl.service.ITmplElemLinkService;
import com.emr.project.tmpl.service.ITmplIndexService;
import com.emr.project.webEditor.util.XmlElementParseUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubfileIndexServiceImpl implements ISubfileIndexService {
   private final Logger log = LoggerFactory.getLogger(SubfileIndexServiceImpl.class);
   @Autowired
   private SubfileIndexMapper subfileIndexMapper;
   @Autowired
   private IIndexService indexService;
   @Autowired
   private ITmplIndexService tmplIndexService;
   @Autowired
   private ISysEmrTypeConfigService sysEmrTypeConfigService;
   @Autowired
   private IElemAttriService elemAttriService;
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;
   @Autowired
   private IVisitinfoService visitinfoService;
   @Autowired
   private ISysEmrConfigService emrConfigService;
   @Autowired
   private TmplIndexMapper tmplIndexMapper;
   @Autowired
   private IElemGenderService elemGenderService;
   @Autowired
   private IExamItemService examItemService;
   @Autowired
   private ITestReportService testReportService;
   @Autowired
   private ITransinfoService transinfoService;
   @Autowired
   private IEmrBinaryService emrBinaryService;
   @Autowired
   private ITmplElemLinkService tmplElemLinkService;

   public SubfileIndex selectSubfileIndexById(Long id) {
      return this.subfileIndexMapper.selectSubfileIndexById(id);
   }

   public SubfileIndexVo selectSubfileIndexVoById(Long id) {
      return this.subfileIndexMapper.selectSubfileIndexVoById(id);
   }

   public List selectSubfileIndexList(SubfileIndex subfileIndex) {
      return this.subfileIndexMapper.selectSubfileIndexList(subfileIndex);
   }

   public int insertSubfileIndex(SubfileIndex subfileIndex) {
      return this.subfileIndexMapper.insertSubfileIndex(subfileIndex);
   }

   public int updateSubfileIndex(SubfileIndex subfileIndex) throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      subfileIndex.setAltPerCode(sysUser.getUserName());
      subfileIndex.setAltPerName(sysUser.getNickName());
      return this.subfileIndexMapper.updateSubfileIndex(subfileIndex);
   }

   public void updateSubfileIndexSecLevel(SubfileIndexVo subfileIndexVo) throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      subfileIndexVo.setAltPerCode(sysUser.getUserName());
      subfileIndexVo.setAltPerName(sysUser.getNickName());
      this.subfileIndexMapper.updateSubfileIndexSecLevel(subfileIndexVo);
   }

   public void updateSubfileIndexMrState(Long id, String mrState, Date updateDate, Date recoDate) {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      SubfileIndex subfileIndex = new SubfileIndex();
      subfileIndex.setAltPerCode(sysUser.getUserName());
      subfileIndex.setAltPerName(sysUser.getNickName());
      subfileIndex.setId(id);
      subfileIndex.setMrState(mrState);
      subfileIndex.setAltDate(updateDate);
      subfileIndex.setRecoDate(recoDate);
      this.subfileIndexMapper.updateSubfileIndex(subfileIndex);
   }

   public int deleteSubfileIndexByIds(Long[] ids) {
      return this.subfileIndexMapper.deleteSubfileIndexByIds(ids);
   }

   public int deleteSubfileIndexById(Long id) {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      SubfileIndex param = new SubfileIndex();
      param.setId(id);
      param.setAltPerCode(basEmployee.getEmplNumber());
      param.setAltPerName(basEmployee.getEmplName());
      param.setDelFlag("1");
      return this.subfileIndexMapper.updateIndexDelFlag(param);
   }

   public List selectSubfileIndexByMainId(Long mainId) {
      return this.subfileIndexMapper.selectSubfileIndexByMainId(mainId);
   }

   public List selectSubfileIndexVoByMainId(Long mainId) {
      return this.subfileIndexMapper.selectSubfileIndexVoByMainId(mainId);
   }

   public SubfileIndexVo selectIsAllowInsert(SubfileIndex subfileIndex) throws Exception {
      SubfileIndexVo subfileIndexVo = new SubfileIndexVo();
      Index index = this.indexService.selectIndexById(subfileIndex.getMainId());
      if (index != null) {
         subfileIndexVo.setMsg("允许插入病程");
         subfileIndexVo.setMergeState(CommonConstants.BOOL_TRUE);
         if ("MAINFILE".equals(index.getMrType())) {
            TmplIndex tmplIndex = this.tmplIndexService.selectTmplIndexById(subfileIndex.getTempId());
            SysEmrTypeConfig sysEmrTypeConfig = this.sysEmrTypeConfigService.selectSysEmrTypeConfigByTypeId(tmplIndex.getTempType());
            if (0L == sysEmrTypeConfig.getMergeFlag()) {
               subfileIndexVo.setMergeState(CommonConstants.BOOL_FALSE);
               subfileIndexVo.setMsg("该病历为不需要合并打印的病历，请单独新建病历");
            }

            subfileIndexVo.setMergeFlag(sysEmrTypeConfig.getMergeFlag().toString());
         } else {
            subfileIndexVo.setMergeState(CommonConstants.BOOL_FALSE);
            subfileIndexVo.setMergeFlag("");
            subfileIndexVo.setMsg("当前病历文件不是病程记录类型");
         }
      }

      return subfileIndexVo;
   }

   public Map insertFile(Index index, SubfileIndex subfileIndex, TmplIndex tmplIndex, String testExamResultId, String babyId) throws Exception {
      Map<String, Object> map = new HashMap();
      VisitinfoVo visitinfoVo = this.visitinfoService.selectVisitinfoByPatientId(index.getPatientId());
      String jsonStr = "";
      if (StringUtils.isNotEmpty(tmplIndex.getTempFile())) {
         Map<String, String> jsonMap = JSON.parseObject(tmplIndex.getTempFile());
         jsonStr = (String)jsonMap.get("base64");
      }

      List<ElemGender> elemGenderList = this.elemGenderService.selectElemGenderQuaList(subfileIndex.getTempId());
      List<ElemGender> setPropElemList = this.indexService.selectSetPropElemList(index.getPatientId(), elemGenderList);
      map.put("setPropElemList", setPropElemList);
      Map<String, List<String>> list = this.indexService.selectSetStructsTextIdList(tmplIndex, index.getPatientId(), testExamResultId, babyId);
      List<String> idList = (List)list.get("setStructsTextIdList");
      List<String> valueList = (List)list.get("setStructsTextValueList");
      tmplIndex.setTempFile(jsonStr);
      map.put("tmplIndex", tmplIndex);
      if ((tmplIndex.getTempType().equals("45") || tmplIndex.getTempType().equals("51")) && (StringUtils.isNotEmpty(subfileIndex.getUperTitleName()) || StringUtils.isNotEmpty(subfileIndex.getUperDoctName()))) {
         ElemAttri param = new ElemAttri();
         param.setTempId(tmplIndex.getId());
         List<ElemAttri> elemAttris = this.elemAttriService.selectElemAttriList(param);
         if (elemAttris != null && !elemAttris.isEmpty()) {
            for(ElemAttri elemAttri : elemAttris) {
               if (elemAttri.getElemId().equals(CommonConstants.EMR.ELEM_UP_DOCTOR) && StringUtils.isNotEmpty(subfileIndex.getUperDoctName())) {
                  idList.add(elemAttri.getContElemName());
                  valueList.add(subfileIndex.getUperDoctName());
               }

               if (elemAttri.getElemId().equals(CommonConstants.EMR.ELEM_UP_TITLE) && StringUtils.isNotEmpty(subfileIndex.getUperTitleName())) {
                  idList.add(elemAttri.getContElemName());
                  valueList.add(subfileIndex.getUperTitleName());
               }
            }
         }
      }

      this.setSubFileIndexRecDate(subfileIndex.getTempId(), (List)null, subfileIndex.getRecoDate(), idList, valueList);
      map.put("setStructsTextIdList", idList);
      map.put("setStructsTextValueList", valueList);
      List<SubfileIndexVo> subfileIndexVoList = this.subfileIndexMapper.selectListByMainIdOrderRecoDate(subfileIndex);
      List<SubfileIndexVo> afterList = this.subfileIndexMapper.selectListByMainIdAfterRecoDate(subfileIndex);
      Date recoDate = afterList != null && !afterList.isEmpty() ? ((SubfileIndexVo)afterList.get(0)).getRecoDate() : visitinfoVo.getInhosTime();
      if (CollectionUtils.isEmpty(afterList) && CollectionUtils.isNotEmpty(subfileIndexVoList)) {
         map.put("oldContElemName", ((SubfileIndexVo)subfileIndexVoList.get(0)).getContElemName());
         map.put("oldSubFileIndex", subfileIndexVoList.get(0));
      }

      if (CollectionUtils.isNotEmpty(afterList) && CollectionUtils.isNotEmpty(subfileIndexVoList)) {
         map.put("afterContElemName", ((SubfileIndexVo)afterList.get(0)).getContElemName());
         map.put("afterSubFileIndex", afterList.get(0));
      }

      if (CollectionUtils.isNotEmpty(afterList) && CollectionUtils.isEmpty(subfileIndexVoList)) {
         map.put("afterContElemName", ((SubfileIndexVo)afterList.get(0)).getContElemName());
         map.put("afterSubFileIndex", afterList.get(0));
      }

      String[] patientIds = new String[]{index.getPatientId()};
      ExamItemVo examItemVo = new ExamItemVo();
      examItemVo.setPatientId(index.getPatientId());
      examItemVo.setStartDate(recoDate);
      examItemVo.setEndDate(subfileIndex.getRecoDate());
      List<ExamItemVo> examItemVoList = this.examItemService.selectItemListByDate(examItemVo);
      map.put("examReportList", examItemVoList);
      TestReportVo testReportVo = new TestReportVo();
      testReportVo.setPatientId(index.getPatientId());
      testReportVo.setStartDate(recoDate);
      testReportVo.setEndDate(subfileIndex.getRecoDate());
      List<TestReportVo> testReportVoList = this.testReportService.selectTestReportVoListByDate(testReportVo);
      List<TestReportVo> reportList = new ArrayList();
      Map<String, List<TestReportVo>> mapMaItemList = (Map)testReportVoList.stream().collect(Collectors.groupingBy(TestReport::getId));

      for(String key : mapMaItemList.keySet()) {
         TestReportVo report = new TestReportVo();
         List<TestReportVo> reports = (List)mapMaItemList.get(key);
         if (reports != null && reports.size() > 0) {
            report.setClinItemName(((TestReportVo)reports.get(0)).getClinItemName());
            report.setSpecName(((TestReportVo)reports.get(0)).getSpecName());
            report.setRepDate(((TestReportVo)reports.get(0)).getRepDate());
            String normalSign2 = "M";

            for(int i = 0; i < reports.size(); ++i) {
               if (!((TestReportVo)reports.get(i)).getNormalSign2().equals(normalSign2)) {
                  normalSign2 = ((TestReportVo)reports.get(i)).getNormalSign2();
                  break;
               }
            }

            report.setNormalSign2(normalSign2);
            reportList.add(report);
         }
      }

      map.put("testReportList", reportList);
      if ((examItemVoList == null || examItemVoList.size() <= 0) && (reportList == null || reportList.size() <= 0)) {
         map.put("reportMessage", "");
      } else {
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
         String beforeRecoDateStr = sdf.format(recoDate);
         map.put("reportMessage", "在" + beforeRecoDateStr + "~" + sdf.format(subfileIndex.getRecoDate()) + "期间有新的检查、检验报告生成，建议书写病历时进行报告结果分析。");
      }

      String xmlStr = this.emrBinaryService.selectIndexXmlStrById(index.getId());
      DeptBEDateVo deptBEDateVoCurn = new DeptBEDateVo((Long)null, SecurityUtils.getLoginUser().getUser().getDept().getDeptCode(), SecurityUtils.getLoginUser().getUser().getDept().getDeptName(), visitinfoVo.getBedNo(), (Date)null, (Date)null);
      List<IndexHFInfoPageVo> indexHFInfoPageVoList = this.getIndexHFInfoPageVoList(xmlStr, index.getPatientId(), deptBEDateVoCurn);
      map.put("indexHFInfoPageVoList", indexHFInfoPageVoList);
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
      if ((tmplIndex.getTempType().equals("45") || tmplIndex.getTempType().equals("51")) && StringUtils.isNotEmpty(subfileIndex.getUperDoctName())) {
         map.put("subFileIndexName", subfileIndex.getUperDoctName() + subfileIndex.getUperTitleName() + "查房记录" + sdf.format(new Date()));
      } else {
         map.put("subFileIndexName", tmplIndex.getShowName() + sdf.format(new Date()));
      }

      map.put("deptBEDateVoCurnId", deptBEDateVoCurn == null ? null : deptBEDateVoCurn.getId());
      List<TmplElemLinkVo> tmplElemLinkVoList = this.tmplElemLinkService.selectTmplElemLinkVoByTempId(tmplIndex.getId());
      map.put("tmplElemLinkVoList", tmplElemLinkVoList);
      return map;
   }

   public DeptBEDateVo getDeptBEDateVoCurn(String patientId, SubfileIndex subfileIndex) throws Exception {
      DeptBEDateVo deptBEDateVoCurn = null;
      List<DeptBEDateVo> deptBEDateVoList = this.transinfoService.selectDetpDate(patientId);
      deptBEDateVoList = (List)deptBEDateVoList.stream().sorted(Comparator.comparing(DeptBEDateVo::getEndDate)).collect(Collectors.toList());

      for(int i = deptBEDateVoList.size() - 1; i > -1; --i) {
         DeptBEDateVo deptBEDateVo = (DeptBEDateVo)deptBEDateVoList.get(i);
         if (deptBEDateVo.getEndDate().compareTo(subfileIndex.getRecoDate()) <= 0) {
            subfileIndex.setChangeDepBedId(deptBEDateVo.getId());
            deptBEDateVoCurn = deptBEDateVo;
            break;
         }
      }

      return deptBEDateVoCurn;
   }

   public List getIndexHFInfoPageVoList(String xmlStr, String patientId, DeptBEDateVo deptBEDateVoCurn) throws Exception {
      List<IndexHFInfoPageVo> indexHFInfoPageVoList = this.indexService.getIndexHFInfoResultVo(xmlStr, patientId);
      List<IndexHFInfoPageVo> indexHFInfoPageVoListNew = new ArrayList(indexHFInfoPageVoList.size());
      VisitinfoVo visitinfoVo = this.visitinfoService.selectVisitinfoById(patientId);
      String deptContElemName = "dept_name";
      String bedNoContElemName = "bed_no";

      for(IndexHFInfoPageVo indexHFInfoPageVo : indexHFInfoPageVoList) {
         IndexHFInfoPageVo infoPageVoTemp = new IndexHFInfoPageVo();
         BeanUtils.copyProperties(indexHFInfoPageVo, infoPageVoTemp);
         if (infoPageVoTemp.getValue().equals(deptContElemName)) {
            infoPageVoTemp.setText(deptBEDateVoCurn == null ? visitinfoVo.getDeptName() : deptBEDateVoCurn.getDeptName());
         }

         if (infoPageVoTemp.getValue().equals(bedNoContElemName)) {
            infoPageVoTemp.setText(deptBEDateVoCurn == null ? visitinfoVo.getBedNo() : deptBEDateVoCurn.getBedNo());
         }

         indexHFInfoPageVoListNew.add(infoPageVoTemp);
      }

      return indexHFInfoPageVoListNew;
   }

   public void setSubFileIndexRecDate(Long tempId, List elemDateList, Date recoDate, List idList, List valueList) throws Exception {
      String elemId = this.sysEmrConfigService.selectSysEmrConfigByKey("0028");
      if (StringUtils.isNotBlank(elemId)) {
         if (tempId == null) {
            for(ElemDate elem : (List)elemDateList.stream().filter((t) -> String.valueOf(t.getElemId()).equals(elemId)).collect(Collectors.toList())) {
               idList.add(elem.getContElemName());
               SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
               valueList.add(sdf.format(recoDate));
            }
         } else {
            ElemAttri elemAttri = new ElemAttri();
            elemAttri.setTempId(tempId);
            elemAttri.setElemId(Long.parseLong(elemId));

            for(ElemAttri elem : this.elemAttriService.selectElemAttriList(elemAttri)) {
               idList.add(elem.getContElemName());
               SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
               valueList.add(sdf.format(recoDate));
            }
         }
      }

   }

   public void updateSubFileLockState(SubfileIndex subfileIndex) throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      subfileIndex.setAltPerCode(sysUser.getUserName());
      subfileIndex.setAltPerName(sysUser.getNickName());
      this.subfileIndexMapper.updateSubFileLockState(subfileIndex);
   }

   public List selectPatIndexList(PatEventVo patEventVo) throws Exception {
      return this.subfileIndexMapper.selectPatIndexList(patEventVo);
   }

   public void updateLastFinishTimeList(List list) throws Exception {
      this.subfileIndexMapper.updateLastFinishTimeList(list);
   }

   public void updateEmrOrderNo(String orderNo) throws Exception {
      this.subfileIndexMapper.updateEmrOrderNo(orderNo);
   }

   public void updateOrderNoEmptyByIdList(List mrFileIdList) throws Exception {
      this.subfileIndexMapper.updateOrderNoEmptyByIdList(mrFileIdList);
   }

   public void updateEmrOrderNoByIdList(String orderNo, List mrFileIdList) throws Exception {
      this.subfileIndexMapper.updateEmrOrderNoByIdList(orderNo, mrFileIdList);
   }

   public void updateOrderNoByOrderNo(String oldOrderNo, String newOrderNo) throws Exception {
      this.subfileIndexMapper.updateOrderNoByOrderNo(oldOrderNo, newOrderNo);
   }

   public List selectSubFileByPat(IndexVo indexVo) throws Exception {
      return this.subfileIndexMapper.selectSubFileByPat(indexVo);
   }

   public List selectSubFileDelList(SubfileIndexVo subfileIndex) throws Exception {
      return this.subfileIndexMapper.selectSubFileDelList(subfileIndex);
   }

   public Map selectSubFileRegain(SubfileIndexVo subfileIndexVo) throws Exception {
      SubfileIndex subfileIndex = this.subfileIndexMapper.selectSubfileIndexById(subfileIndexVo.getId());
      Map<String, Object> map = new HashMap();
      VisitinfoVo visitinfoVo = this.visitinfoService.selectVisitinfoByPatientId(subfileIndexVo.getPatientId());
      List<SubfileIndexVo> subfileIndexVoList = this.subfileIndexMapper.selectListByMainIdOrderRecoDate(subfileIndex);
      List<SubfileIndexVo> afterList = this.subfileIndexMapper.selectListByMainIdAfterRecoDate(subfileIndex);
      if (afterList != null && !afterList.isEmpty()) {
         map.put("afterContElemName", ((SubfileIndexVo)afterList.get(0)).getContElemName());
         map.put("afterSubFileIndex", afterList.get(0));
      }

      if ((afterList == null || afterList.isEmpty()) && subfileIndexVoList != null && !subfileIndexVoList.isEmpty()) {
         map.put("oldContElemName", ((SubfileIndexVo)subfileIndexVoList.get(0)).getContElemName());
         map.put("oldSubFileIndex", subfileIndexVoList.get(0));
      }

      subfileIndexVo.setMainId(subfileIndex.getMainId());
      List<SubfileIndex> subList = this.subfileIndexMapper.selectSubfileIndexList(subfileIndexVo);
      if (subList != null && !subList.isEmpty()) {
         List<SubfileIndex> dislist = (List)subList.stream().filter((s) -> DateUtils.parseDateToStr("yyyy-MM-dd HH:mm", s.getRecoDate()).equals(DateUtils.parseDateToStr("yyyy-MM-dd HH:mm", subfileIndex.getRecoDate()))).collect(Collectors.toList());
         if (dislist != null && !dislist.isEmpty()) {
            if (((SubfileIndex)dislist.get(0)).getCreDate().compareTo(subfileIndex.getCreDate()) < 0) {
               map.put("afterContElemName", ((SubfileIndex)dislist.get(0)).getContElemName());
               map.put("afterSubFileIndex", dislist.get(0));
            }

            if (((SubfileIndex)dislist.get(0)).getCreDate().compareTo(subfileIndex.getCreDate()) > 0 && afterList != null && !afterList.isEmpty()) {
               map.put("afterContElemName", ((SubfileIndexVo)afterList.get(0)).getContElemName());
               map.put("afterSubFileIndex", afterList.get(0));
            }
         }
      }

      map.put("regainSubFileInfo", subfileIndex);
      String base64 = "";

      try {
         base64 = Base64Util.getFileBase64("emrBase64/" + subfileIndexVo.getPatientId(), subfileIndex.getId().toString() + ".txt");
         this.log.warn("selectSubFileRegain : 病历恢复读取病历内容-文件服务器 : " + subfileIndex.getId().toString() + " : " + base64);
      } catch (Exception var14) {
         this.log.error("未找到文件服务器中该病历的base64文件");
         EmrBinary emrBinary = this.emrBinaryService.selectEmrBinaryById(subfileIndex.getId());
         if (emrBinary != null && StringUtils.isNotEmpty(emrBinary.getMrCon())) {
            Map<String, String> mapStr = (Map)JSON.parse(emrBinary.getMrCon());
            base64 = (String)mapStr.get("base64");
            this.log.warn("selectSubFileRegain : 病历恢复读取病历内容-emrBinary : " + subfileIndex.getId().toString() + " : " + base64);
         }
      }

      map.put("base64Str", base64);
      map.put("regainSaveFlag", "TRUE");
      SysEmrTypeConfig sysEmrTypeConfig = this.sysEmrTypeConfigService.selectSysEmrTypeConfigByTypeId(subfileIndex.getMrType());
      map.put("mergeFlag", sysEmrTypeConfig.getMergeFlag().toString());
      DeptBEDateVo deptBEDateVoCurn = new DeptBEDateVo((Long)null, SecurityUtils.getLoginUser().getUser().getDept().getDeptCode(), SecurityUtils.getLoginUser().getUser().getDept().getDeptName(), visitinfoVo.getBedNo(), (Date)null, (Date)null);
      String emrFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0050");
      if (emrFlag.equals("0")) {
         deptBEDateVoCurn = this.getDeptBEDateVoCurn(subfileIndexVo.getPatientId(), subfileIndex);
      }

      String xmlStr = this.emrBinaryService.selectIndexXmlStrById(subfileIndex.getMainId());
      List<IndexHFInfoPageVo> indexHFInfoPageVoList = this.getIndexHFInfoPageVoList(xmlStr, subfileIndexVo.getPatientId(), deptBEDateVoCurn);
      map.put("indexHFInfoPageVoList", indexHFInfoPageVoList);
      return map;
   }

   public List selectSubFileByMrType(String patientId, String mrType) throws Exception {
      return this.subfileIndexMapper.selectSubFileByMrType(patientId, mrType);
   }

   public SubfileIndexVo getSubFileInfo(String subFileXmlStr) throws Exception {
      SubfileIndexVo subfileIndexVo = new SubfileIndexVo();
      String config94 = this.emrConfigService.selectSysEmrConfigByKey("0094");
      if (StringUtils.isNotBlank(config94)) {
         TmplIndexVo tmplIndexVo94 = this.tmplIndexMapper.selectTmplById(Long.valueOf(config94));
         if (tmplIndexVo94 != null && StringUtils.isNotBlank(tmplIndexVo94.getTempFile())) {
            Map<String, String> jsonMap = JSON.parseObject(tmplIndexVo94.getTempFile());
            String jsonStr = (String)jsonMap.get("base64");
            subfileIndexVo.setHeaderBase64Str(jsonStr);
         }
      }

      String config95 = this.emrConfigService.selectSysEmrConfigByKey("0095");
      if (StringUtils.isNotBlank(config95)) {
         TmplIndexVo tmplIndexVo95 = this.tmplIndexMapper.selectTmplById(Long.valueOf(config95));
         if (tmplIndexVo95 != null && StringUtils.isNotBlank(tmplIndexVo95.getTempFile())) {
            Map<String, String> jsonMap = JSON.parseObject(tmplIndexVo95.getTempFile());
            String jsonStr = (String)jsonMap.get("base64");
            subfileIndexVo.setFooterBase64Str(jsonStr);
         }
      }

      String editorType = this.sysEmrConfigService.selectSysEmrConfigByKey("0000");
      TempIndexSaveElemVo tempIndexSaveElemVo = XmlElementParseUtil.getSaveElemFromXml(subFileXmlStr, editorType);
      if (tempIndexSaveElemVo != null) {
         List<ElemSign> elemSignList = tempIndexSaveElemVo.getElemSignList();
         if (!elemSignList.isEmpty()) {
            List<String> signList = (List)elemSignList.stream().map(ElemSign::getContElemName).collect(Collectors.toList());
            subfileIndexVo.setSignList(signList);
         }

         List<ElemMacro> elemMacroList = tempIndexSaveElemVo.getElemMacroList();
         if (!elemMacroList.isEmpty()) {
            List<String> contElemNameList = (List)elemMacroList.stream().map(ElemMacro::getContElemName).collect(Collectors.toList());
            subfileIndexVo.setSetStructsTextIdList(contElemNameList);
         }
      }

      return subfileIndexVo;
   }

   public SubfileIndex selectIndexByTmplId(String patientId, Long tempId) throws Exception {
      return this.subfileIndexMapper.selectIndexByTmplId(patientId, tempId);
   }

   public List selectOrderNoByIdList(List idList) {
      return this.subfileIndexMapper.selectOrderNoByIdList(idList);
   }
}
