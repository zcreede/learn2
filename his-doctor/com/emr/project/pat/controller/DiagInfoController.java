package com.emr.project.pat.controller;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.StringUtils;
import com.emr.framework.redis.RedisCache;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.common.service.ICommonService;
import com.emr.project.docOrder.domain.TdPaItemDocQuery;
import com.emr.project.docOrder.service.ITdPaItemDocQueryService;
import com.emr.project.mrhp.domain.MrHp;
import com.emr.project.mrhp.service.IMrHpService;
import com.emr.project.operation.domain.Baseinfomation;
import com.emr.project.pat.domain.DiagInfo;
import com.emr.project.pat.domain.resp.DsrReturnResp;
import com.emr.project.pat.domain.vo.DiagInfoErrorVo;
import com.emr.project.pat.service.DSRInTerFaceService;
import com.emr.project.pat.service.IDiagInfoService;
import com.emr.project.pat.service.IVisitinfoService;
import com.emr.project.qc.domain.EmrQcFlow;
import com.emr.project.qc.service.IEmrQcFlowService;
import com.emr.project.sys.domain.SysDiaIcd;
import com.emr.project.sys.service.ISysDiaIcdService;
import com.emr.project.system.domain.BasEmployee;
import com.emr.project.system.service.ISysEmrConfigService;
import com.emr.project.webservice.domain.req.ClinicalPathwayReq;
import com.emr.project.webservice.domain.req.FoodborneReq;
import com.emr.project.webservice.domain.resp.WebServiceFoodborneResp;
import com.emr.project.webservice.domain.resp.WebServiceResp;
import com.emr.project.webservice.service.IClinicalPathwayService;
import com.emr.project.webservice.service.IFoodborneDiseaseService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/pat/diag"})
public class DiagInfoController extends BaseController {
   @Autowired
   private IDiagInfoService diagInfoService;
   @Autowired
   private IVisitinfoService visitinfoService;
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;
   @Autowired
   private ITdPaItemDocQueryService tdPaItemDocQueryService;
   @Autowired
   private IEmrQcFlowService emrQcFlowService;
   @Autowired
   private RedisCache redisCache;
   @Autowired
   private IClinicalPathwayService clinicalPathwayService;
   @Autowired
   private IFoodborneDiseaseService foodborneDiseaseService;
   @Autowired
   private IMrHpService mrHpService;
   @Autowired
   private ISysDiaIcdService diaIcdService;
   @Autowired
   private ICommonService commonService;
   @Autowired
   private DSRInTerFaceService dsrInTerFaceService;

   @PreAuthorize("@ss.hasAnyPermi('pat:visitinfo:allList,docOrder:check:add')")
   @GetMapping({"/list"})
   public TableDataInfo list(DiagInfo diagInfo) {
      List<DiagInfo> list = new ArrayList();

      try {
         this.startPage();
         list = this.diagInfoService.selectDiagInfoList(diagInfo);
      } catch (Exception e) {
         this.log.error("查询诊断信息列表出现异常", e);
      }

      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasAnyPermi('pat:visitinfo:allList,docOrder:check:add')")
   @GetMapping({"/queryList"})
   public AjaxResult queryList(DiagInfo diagInfo) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         List<DiagInfo> list = this.diagInfoService.selectDiagInfoList(diagInfo);
         ajaxResult = AjaxResult.success((Object)list);
      } catch (Exception e) {
         this.log.error("查询诊断信息列表出现异常", e);
         ajaxResult = AjaxResult.error("查询诊断信息列表出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('pat:diag:info')")
   @GetMapping({"/info"})
   public AjaxResult info(DiagInfo diagInfo) {
      return AjaxResult.success((Object)this.diagInfoService.selectDiagInfo(diagInfo));
   }

   @PreAuthorize("@ss.hasPermi('pat:visitinfo:allList')")
   @GetMapping({"/historyList"})
   public TableDataInfo historyList(DiagInfo diagInfo) {
      new ArrayList();
      new TableDataInfo();

      TableDataInfo tableDataInfo;
      try {
         if (StringUtils.isEmpty(diagInfo.getPatientId())) {
            tableDataInfo = new TableDataInfo(500, "患者就诊id参数不能为空");
         } else if (StringUtils.isEmpty(diagInfo.getPatientMainId())) {
            tableDataInfo = new TableDataInfo(500, "患者主索引id参数不能为空");
         } else {
            this.startPage();
            List list = this.diagInfoService.selectHistoryList(diagInfo);
            tableDataInfo = this.getDataTable(list);
         }
      } catch (Exception e) {
         this.log.error("查询历史诊断信息列表异常", e);
         tableDataInfo = new TableDataInfo(500, "查询历史诊断信息列表异常");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasAnyPermi('pat:diag:write,mrhp:hp:mrHpDetail,pat:visitinfo:mrhp')")
   @GetMapping({"/writeZYList"})
   public AjaxResult writeZYList(DiagInfo diagInfo) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");
      boolean flag = true;

      try {
         if (diagInfo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(diagInfo.getPatientId())) {
            flag = false;
            ajaxResult = AjaxResult.error("患者id不能为空");
         }

         if (flag) {
            diagInfo.setDiagClass("ZY");
            List<DiagInfo> ZYList = this.diagInfoService.selectDiagInfoList(diagInfo);
            ajaxResult = AjaxResult.success((Object)ZYList);
         }
      } catch (Exception e) {
         this.log.error("查询患者诊断信息中医列表出现异常", e);
         ajaxResult = AjaxResult.error("查询患者诊断信息中医列表出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('pat:diag:write,mrhp:hp:mrHpDetail,pat:visitinfo:mrhp')")
   @GetMapping({"/writeXYList"})
   public AjaxResult writeXYList(DiagInfo diagInfo) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");
      boolean flag = true;

      try {
         if (diagInfo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(diagInfo.getPatientId())) {
            flag = false;
            ajaxResult = AjaxResult.error("患者id不能为空");
         }

         if (flag) {
            diagInfo.setDiagClass("XY");
            List<DiagInfo> XYList = this.diagInfoService.selectDiagInfoList(diagInfo);
            ajaxResult = AjaxResult.success((Object)XYList);
            String dsrflag = this.sysEmrConfigService.selectSysEmrConfigByKey("0045");
            ajaxResult.put("DSRFlag", dsrflag);
            TdPaItemDocQuery tdPaItemDocQuery = new TdPaItemDocQuery();
            tdPaItemDocQuery.setDocCd(SecurityUtils.getLoginUser().getUser().getBasEmployee().getEmplNumber());
            tdPaItemDocQuery.setOrderFlag("9");
            List<TdPaItemDocQuery> queryList = this.tdPaItemDocQueryService.selectTdPaItemDocQueryList(tdPaItemDocQuery);
            ajaxResult.put("icdFlag", queryList != null && !queryList.isEmpty() ? ((TdPaItemDocQuery)queryList.get(0)).getQueryStatus() : "0");
            DiagInfo info = new DiagInfo();
            info.setPatientId(diagInfo.getPatientId());
            info.setDiagTypeCd("05");
            info.setDiagClassCd("01");
            DiagInfo diaPat = this.diagInfoService.selectDiagInfo(info);
            if (diaPat != null) {
               String diagSite = diaPat.getDiagSite();
               ajaxResult.put("patNo", diagSite);
            }
         }
      } catch (Exception e) {
         this.log.error("查询患者诊断信息西医列表出现异常", e);
         ajaxResult = AjaxResult.error("查询患者诊断信息西医列表出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('pat:diag:add')")
   @PostMapping({"/add"})
   public AjaxResult add(@RequestBody List diagInfoList) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      AjaxResult ajaxResult = AjaxResult.success("保存成功");
      boolean flag = true;
      boolean redisDeptCodeFlag = false;
      String patientId = null;

      try {
         if (diagInfoList == null || diagInfoList.isEmpty()) {
            flag = false;
            ajaxResult = AjaxResult.error("参数集合不能为空");
         }

         patientId = ((DiagInfo)diagInfoList.get(0)).getPatientId();
         String redisDeptCode = (String)this.redisCache.getCacheObject("diag_info_key:" + patientId);
         if (StringUtils.isNotBlank(redisDeptCode) && redisDeptCode.equals("1")) {
            flag = false;
            ajaxResult = AjaxResult.error("有医师正在对当前患者进行诊断录入，请稍后再试");
         } else {
            redisDeptCodeFlag = true;
            this.redisCache.setCacheObject("diag_info_key:" + patientId, "1", 60, TimeUnit.SECONDS);
         }

         Boolean isInMainDiag = false;
         Boolean isFoodborne = false;
         StringBuffer foodborneSB = new StringBuffer();
         ClinicalPathwayReq clinicalPathwayReq = new ClinicalPathwayReq();
         if (flag) {
            for(DiagInfo diagInfo : diagInfoList) {
               if (flag && StringUtils.isEmpty(diagInfo.getPatientId())) {
                  flag = false;
                  ajaxResult = AjaxResult.error("患者id不能为空");
               }

               if (flag && StringUtils.isEmpty(diagInfo.getDiagTypeCd())) {
                  flag = false;
                  ajaxResult = AjaxResult.error("诊断类型编码不能为空");
               }

               if (flag && StringUtils.isEmpty(diagInfo.getDiagTypeName())) {
                  flag = false;
                  ajaxResult = AjaxResult.error("诊断类型名称不能为空");
               }

               if (flag && StringUtils.isEmpty(diagInfo.getDiagClass())) {
                  flag = false;
                  ajaxResult = AjaxResult.error("诊断分类不能为空");
               }

               if (flag && StringUtils.isEmpty(diagInfo.getDiagClassCd())) {
                  flag = false;
                  ajaxResult = AjaxResult.error("诊断标识编码不能为空");
               }

               if (flag && StringUtils.isEmpty(diagInfo.getDiagClassName())) {
                  flag = false;
                  ajaxResult = AjaxResult.error("诊断标识名称不能为空");
               }

               if (flag && StringUtils.isEmpty(diagInfo.getDiagNo())) {
                  flag = false;
                  ajaxResult = AjaxResult.error("诊断序号不能为空");
               }

               if (flag && StringUtils.isEmpty(diagInfo.getDiagCd())) {
                  flag = false;
                  ajaxResult = AjaxResult.error("疾病诊断编码不能为空");
               }

               if (flag && StringUtils.isEmpty(diagInfo.getDiagName())) {
                  flag = false;
                  ajaxResult = AjaxResult.error("疾病诊断名称不能为空");
               }

               if (flag && diagInfo.getDiagTypeCd().equals("03") && (StringUtils.isEmpty(diagInfo.getCrePerCode()) || diagInfo.getCrePerCode().equals(basEmployee.getEmplNumber()))) {
                  isFoodborne = true;
                  foodborneSB = foodborneSB.append(diagInfo.getDiagName());
                  foodborneSB = foodborneSB.append(",");
                  if (StringUtils.isEmpty(diagInfo.getInhosCondCd())) {
                     flag = false;
                     ajaxResult = AjaxResult.error("出院诊断入院病情不能为空");
                  }

                  if (StringUtils.isEmpty(diagInfo.getOutcomeCd())) {
                     flag = false;
                     ajaxResult = AjaxResult.error("治疗疗效编码不能为空");
                  }

                  if (StringUtils.isEmpty(diagInfo.getOutcomeName())) {
                     flag = false;
                     ajaxResult = AjaxResult.error("治疗疗效名称不能为空");
                  }
               }

               if ("02".equals(diagInfo.getDiagTypeCd()) && "01".equals(diagInfo.getDiagClassCd())) {
                  DiagInfo diagInfoReq = new DiagInfo();
                  diagInfoReq.setDiagCd(diagInfo.getDiagCd());
                  diagInfoReq.setPatientId(diagInfo.getPatientId());
                  diagInfoReq.setDiagTypeCd(diagInfo.getDiagTypeCd());
                  diagInfoReq.setDiagClassCd(diagInfo.getDiagClassCd());
                  DiagInfo diagInfoRes = this.diagInfoService.selectDiagInfo(diagInfoReq);
                  if (diagInfoRes == null) {
                     isInMainDiag = true;
                     clinicalPathwayReq.setIcd(diagInfo.getDiagCd());
                     clinicalPathwayReq.setZyh(diagInfo.getPatientId());
                     clinicalPathwayReq.setAdmissionNo(diagInfo.getPatientId());
                  }
               }

               if ("03".equals(diagInfo.getDiagTypeCd()) && "01".equals(diagInfo.getDiagClassCd()) && (diagInfo.getDiagCd().startsWith("S") || diagInfo.getDiagCd().startsWith("s") || diagInfo.getDiagCd().startsWith("T") || diagInfo.getDiagCd().startsWith("t"))) {
                  boolean dicMark = diagInfoList.stream().anyMatch((info) -> "04".equals(info.getDiagTypeCd()) && "01".equals(info.getDiagClassCd()));
                  if (!dicMark) {
                     flag = false;
                     ajaxResult = AjaxResult.error("需要录入损伤中毒主要诊断信息");
                  }
               }

               if ("03".equals(diagInfo.getDiagTypeCd()) && "01".equals(diagInfo.getDiagClassCd()) && diagInfo.getDiaDate() == null) {
                  flag = false;
                  ajaxResult = AjaxResult.error("录入出院主诊断时，确诊日期不能为空！");
               }
            }
         }

         EmrQcFlow emrQcFlow = flag ? this.emrQcFlowService.selectEmrQcFlowById(SecurityUtils.getLoginUser().getUser().getHospital().getOrgCode(), ((DiagInfo)diagInfoList.get(0)).getPatientId()) : null;
         String lbMrHpFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0086");
         String mrisFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("000301");
         if (flag && emrQcFlow != null && emrQcFlow.getMrState().equals("16") && lbMrHpFlag.equals("0")) {
            flag = false;
            ajaxResult = AjaxResult.error("当前患者的病历已归档，不能再修改诊断信息");
         }

         MrHp mrHp = this.mrHpService.selectMrHpByPatientId(((DiagInfo)diagInfoList.get(0)).getPatientId());
         if (flag && emrQcFlow != null && emrQcFlow.getMrState().equals("16") && lbMrHpFlag.equals("1") && mrisFlag.equals("1")) {
            flag = false;
            ajaxResult = AjaxResult.error("当前患者的病历已归档，不能再修改诊断信息");
         }

         if (flag && mrHp != null && (mrHp.getMrState().equals("03") || mrHp.getMrState().equals("04")) && lbMrHpFlag.equals("1") && mrisFlag.equals("2")) {
            flag = false;
            ajaxResult = AjaxResult.error("当前患者的病案首页已签名，不能再修改诊断信息，请先取消病案首页签名后再修改");
         }

         if (flag) {
            List<DiagInfo> insertList = (List)diagInfoList.stream().filter((s) -> StringUtils.isEmpty(s.getId())).collect(Collectors.toList());
            if (!insertList.isEmpty()) {
               Map<String, List<DiagInfo>> mainDiagMap = (Map)insertList.stream().collect(Collectors.groupingBy(DiagInfo::getDiagClassCd));
               if (mainDiagMap != null) {
                  List<DiagInfo> mainDiagInfos = (List)mainDiagMap.get("01");
                  if (mainDiagInfos != null) {
                     Map<String, List<DiagInfo>> mainMap = (Map)mainDiagInfos.stream().collect(Collectors.groupingBy(DiagInfo::getDiagTypeCd));
                     if (mainMap != null) {
                        Set<String> stringSet = mainMap.keySet();
                        List<DiagInfo> list = this.diagInfoService.selectPatientMainDiagByDiagTypeCdList(patientId, stringSet);
                        if (list != null && list.size() > 0) {
                           flag = Boolean.FALSE;
                           ajaxResult = AjaxResult.error("当前患者已存在主要诊断，请刷新页面后重试！");
                        }
                     }
                  }
               }
            }
         }

         String admissionNo = "";
         if (flag) {
            List<DiagInfoErrorVo> diagInfoErrorVoList = new ArrayList();
            Baseinfomation baseinfomation = this.commonService.findBaseInfo(patientId);
            admissionNo = baseinfomation.getAdmissionNo();
            String sex = baseinfomation.getSex();
            int age = baseinfomation.getPersonAge();
            List<String> icdList = (List)diagInfoList.stream().map((t) -> t.getDiagCd()).collect(Collectors.toList());
            List<SysDiaIcd> diaIcdList = this.diaIcdService.selectSysDiaIcdByIcdCdList(icdList);
            if (CollectionUtils.isNotEmpty(diaIcdList) && diaIcdList.size() > 0) {
               List<SysDiaIcd> diaIcdListSex = (List)diaIcdList.stream().filter((t) -> StringUtils.isNotBlank(t.getSex()) && !sex.equals(t.getSex())).collect(Collectors.toList());
               if (CollectionUtils.isNotEmpty(diaIcdListSex) && diaIcdListSex.size() > 0) {
                  for(SysDiaIcd diaIcd : diaIcdListSex) {
                     DiagInfoErrorVo diagInfoErrorVo = new DiagInfoErrorVo();
                     BeanUtils.copyProperties(diaIcd, diagInfoErrorVo);
                     if ("1".equals(sex)) {
                        diagInfoErrorVo.setErrMessage("男性患者不能有女性诊断，请修改后再提交");
                     } else {
                        diagInfoErrorVo.setErrMessage("女性患者不能有男性诊断，请修改后再提交");
                     }

                     diagInfoErrorVoList.add(diagInfoErrorVo);
                  }
               }

               List<SysDiaIcd> diaIcdListAge = (List)diaIcdList.stream().filter((t) -> t.getStartAge() != null && t.getEndAge() != null && (t.getStartAge() > age || t.getEndAge() < age)).collect(Collectors.toList());
               if (CollectionUtils.isNotEmpty(diaIcdListAge) && diaIcdListAge.size() > 0) {
                  for(SysDiaIcd diaIcd : diaIcdListAge) {
                     DiagInfoErrorVo diagInfoErrorVo = new DiagInfoErrorVo();
                     BeanUtils.copyProperties(diaIcd, diagInfoErrorVo);
                     diagInfoErrorVo.setErrMessage("该年龄段不能开此诊断，请修改后再提交");
                     diagInfoErrorVoList.add(diagInfoErrorVo);
                  }
               }
            }

            if (CollectionUtils.isNotEmpty(diagInfoErrorVoList) && diagInfoErrorVoList.size() > 0) {
               flag = false;
               ajaxResult = AjaxResult.error("有不符合规范的诊断，请修改后再提交！");
               ajaxResult.put("diagInfoErrorVoList", diagInfoErrorVoList);
            }
         }

         if (flag) {
            this.diagInfoService.saveDiagInfo(diagInfoList);
            this.redisCache.deleteObject("diag_info_key:" + patientId);
            if (isInMainDiag) {
               WebServiceResp resp = this.clinicalPathwayService.getIsNeedPathway(clinicalPathwayReq);
               if (resp != null && "0".equals(resp.getCode())) {
                  WebServiceResp respReq = this.clinicalPathwayService.getPathway(clinicalPathwayReq);
                  if (resp != null && "0".equals(respReq.getCode())) {
                     ajaxResult.put("clinicalPatUrl", respReq.getSource());
                  }
               }
            }

            if (isFoodborne) {
               foodborneSB.deleteCharAt(foodborneSB.lastIndexOf(","));
               String foodborne = foodborneSB.toString();
               FoodborneReq foodborneReq = new FoodborneReq();
               foodborneReq.setDiagnosisName(foodborne);
               foodborneReq.setPatientId(patientId);
               WebServiceFoodborneResp resp = this.foodborneDiseaseService.getFoodborneDiseaseUrl(foodborneReq);
               if (resp != null) {
                  ajaxResult.put("foodborneUrl", resp.getFoodborneUrl());
               }
            }

            DsrReturnResp dsrReturnResp = this.dsrInTerFaceService.dsrReport(admissionNo);
            if (dsrReturnResp != null) {
               ajaxResult.put("dsrReturnResp", dsrReturnResp);
            }
         } else if (redisDeptCodeFlag && StringUtils.isNotBlank(patientId)) {
            this.redisCache.deleteObject("diag_info_key:" + patientId);
         }
      } catch (Exception e) {
         this.log.error("诊断录入统一保存接口出现异常", e);
         ajaxResult = AjaxResult.error("诊断录入统一保存接口出现异常");
         if (redisDeptCodeFlag && StringUtils.isNotBlank(patientId)) {
            this.redisCache.deleteObject("diag_info_key:" + patientId);
         }
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('pat:diag:write')")
   @GetMapping({"/hisPatDiagList"})
   public AjaxResult hisPatDiagList(String patientId) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         if (StringUtils.isEmpty(patientId)) {
            ajaxResult = AjaxResult.error("患者id不能为空");
         } else {
            ajaxResult = AjaxResult.success((Object)this.visitinfoService.selectHisVisitinfoListByPatientId(patientId));
         }
      } catch (Exception e) {
         this.log.error("根据患者id查询历史就诊列表出现异常", e);
         ajaxResult = AjaxResult.error("根据患者id查询历史就诊列表出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('pat:diag:write')")
   @GetMapping({"/isUpdateDiag"})
   public AjaxResult isUpdateDiag() {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         String str = this.sysEmrConfigService.selectSysEmrConfigByKey("0038");
         ajaxResult.put("isUpdate", str);
      } catch (Exception e) {
         this.log.error("查询是否可以修改疾病名称出现异常", e);
         ajaxResult = AjaxResult.error("查询是否可以修改疾病名称出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('pat:diag:remove')")
   @DeleteMapping({"/{id}"})
   public AjaxResult delete(@PathVariable("id") String id) {
      AjaxResult ajaxResult = AjaxResult.success("删除成功");
      boolean flag = true;

      try {
         BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
         DiagInfo diagInfo = this.diagInfoService.selectDiagInfoById(id);
         if (diagInfo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("未查询到诊断信息，请刷新后重试");
         }

         EmrQcFlow emrQcFlow = flag ? this.emrQcFlowService.selectEmrQcFlowById(SecurityUtils.getLoginUser().getUser().getHospital().getOrgCode(), diagInfo.getPatientId()) : null;
         String lbMrHpFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0086");
         String mrisFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("000301");
         if (flag && emrQcFlow != null && emrQcFlow.getMrState().equals("16") && lbMrHpFlag.equals("0")) {
            flag = false;
            ajaxResult = AjaxResult.error("当前患者的病历已归档，不能再修改诊断信息");
         }

         MrHp mrHp = this.mrHpService.selectMrHpByPatientId(diagInfo.getPatientId());
         if (flag && emrQcFlow != null && emrQcFlow.getMrState().equals("16") && lbMrHpFlag.equals("1") && mrisFlag.equals("1")) {
            flag = false;
            ajaxResult = AjaxResult.error("当前患者的病历已归档，不能再修改诊断信息");
         }

         if (flag && mrHp != null && (mrHp.getMrState().equals("03") || mrHp.getMrState().equals("04")) && lbMrHpFlag.equals("1") && mrisFlag.equals("2")) {
            flag = false;
            ajaxResult = AjaxResult.error("当前患者的病案首页已签名，不能再修改诊断信息，请先取消病案首页签名后再修改");
         }

         if (flag && !basEmployee.getEmplNumber().equals(diagInfo.getDiaDocCd())) {
            flag = false;
            ajaxResult = AjaxResult.error("不是创建人不可删除");
         }

         if (flag) {
            this.diagInfoService.deleteDiagInfoById(id);
         }
      } catch (Exception e) {
         this.log.error("根据id删除诊断信息出现异常", e);
         ajaxResult = AjaxResult.error("根据id删除诊断信息出现异常");
      }

      return ajaxResult;
   }
}
