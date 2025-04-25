package com.emr.project.emr.controller;

import com.alibaba.fastjson.JSONObject;
import com.emr.common.constant.CommonConstants;
import com.emr.common.utils.DateUtils;
import com.emr.common.utils.IPAddressUtil;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.redis.RedisCache;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.domain.TreeSelect;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.common.service.ICommonService;
import com.emr.project.dr.domain.TdCaConsApply;
import com.emr.project.dr.service.ITdCaConsApplyService;
import com.emr.project.emr.domain.EditState;
import com.emr.project.emr.domain.EmrBinary;
import com.emr.project.emr.domain.EmrTaskInfo;
import com.emr.project.emr.domain.Index;
import com.emr.project.emr.domain.SealupRecord;
import com.emr.project.emr.domain.SubfileIndex;
import com.emr.project.emr.domain.SysEmrTypeConfig;
import com.emr.project.emr.domain.SysEmrTypeLevel;
import com.emr.project.emr.domain.resp.TertiaryIndexResp;
import com.emr.project.emr.domain.vo.AnalysisXmlDataReq;
import com.emr.project.emr.domain.vo.EmrBinaryVo;
import com.emr.project.emr.domain.vo.EmrTaskInfoVo;
import com.emr.project.emr.domain.vo.HisIndexVo;
import com.emr.project.emr.domain.vo.IndexDelVo;
import com.emr.project.emr.domain.vo.IndexHFInfoResultVo;
import com.emr.project.emr.domain.vo.IndexHFInfoVo;
import com.emr.project.emr.domain.vo.IndexMzVo;
import com.emr.project.emr.domain.vo.IndexNoSignListVo;
import com.emr.project.emr.domain.vo.IndexSaveReturnVo;
import com.emr.project.emr.domain.vo.IndexSaveVo;
import com.emr.project.emr.domain.vo.IndexSecLevelVo;
import com.emr.project.emr.domain.vo.IndexSignVo;
import com.emr.project.emr.domain.vo.IndexVo;
import com.emr.project.emr.domain.vo.SubFileIndexHFInfoVo;
import com.emr.project.emr.domain.vo.SubfileIndexVo;
import com.emr.project.emr.domain.vo.SysEmrTypeConfigVo;
import com.emr.project.emr.domain.vo.XmlElementParseConfigVo;
import com.emr.project.emr.service.IEditStateService;
import com.emr.project.emr.service.IEmrBinaryService;
import com.emr.project.emr.service.IEmrElemstoeService;
import com.emr.project.emr.service.IEmrIndexMMenuService;
import com.emr.project.emr.service.IEmrTaskInfoService;
import com.emr.project.emr.service.IIndexService;
import com.emr.project.emr.service.IModifyApplService;
import com.emr.project.emr.service.ISubfileIndexService;
import com.emr.project.emr.service.ISysEmrLogService;
import com.emr.project.emr.service.ISysEmrTypeConfigService;
import com.emr.project.operation.domain.Baseinfomation;
import com.emr.project.other.domain.BasCertInfo;
import com.emr.project.other.domain.GrantOutDoctor;
import com.emr.project.other.service.IBasCertInfoService;
import com.emr.project.other.service.IGrantOutDoctorService;
import com.emr.project.pat.domain.BabyInfo;
import com.emr.project.pat.domain.vo.BackLogVo;
import com.emr.project.pat.domain.vo.VisitinfoVo;
import com.emr.project.pat.service.IBabyInfoService;
import com.emr.project.pat.service.IVisitinfoService;
import com.emr.project.qc.domain.EmrQcFlow;
import com.emr.project.qc.domain.vo.EmrQcListVo;
import com.emr.project.qc.domain.vo.PatEventVo;
import com.emr.project.qc.service.IEmrQcFlowService;
import com.emr.project.qc.service.IEmrQcListService;
import com.emr.project.sys.domain.vo.QuoteElemVo;
import com.emr.project.sys.service.IQuoteElemService;
import com.emr.project.system.domain.BasEmployee;
import com.emr.project.system.domain.SysDictData;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.service.ISysDictTypeService;
import com.emr.project.system.service.ISysEmrConfigService;
import com.emr.project.system.service.ISysUserService;
import com.emr.project.tmpl.domain.ElemAttri;
import com.emr.project.tmpl.domain.ElemMacro;
import com.emr.project.tmpl.domain.ElemSign;
import com.emr.project.tmpl.domain.TmplIndex;
import com.emr.project.tmpl.domain.vo.ElemAttriVo;
import com.emr.project.tmpl.domain.vo.TempIndexSaveElemVo;
import com.emr.project.tmpl.domain.vo.TmplElemLinkVo;
import com.emr.project.tmpl.service.IElemAttriService;
import com.emr.project.tmpl.service.IElemMacroService;
import com.emr.project.tmpl.service.IElemSignService;
import com.emr.project.tmpl.service.ITmplElemLinkService;
import com.emr.project.tmpl.service.ITmplIndexService;
import com.emr.project.webEditor.util.XmlElementParseUtil;
import com.emr.project.webEditor.zb.vo.IndexFileVo;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/emr/index"})
public class IndexController extends BaseController {
   @Autowired
   private IIndexService indexService;
   @Autowired
   private ISysDictTypeService dictTypeService;
   @Autowired
   private ITmplIndexService tmplIndexService;
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;
   @Autowired
   private IVisitinfoService visitinfoService;
   @Autowired
   private ISysEmrLogService sysEmrLogService;
   @Autowired
   private IGrantOutDoctorService grantOutDoctorService;
   @Autowired
   private IEmrTaskInfoService emrTaskInfoService;
   @Autowired
   public IEmrBinaryService emrBinaryService;
   @Autowired
   public IEmrQcListService emrQcListService;
   @Autowired
   private IEmrQcFlowService emrQcFlowService;
   @Autowired
   private IElemAttriService elemAttriService;
   @Autowired
   private IElemSignService elemSignService;
   @Autowired
   private IBasCertInfoService basCertInfoService;
   @Autowired
   private IModifyApplService modifyApplService;
   @Autowired
   private ISysEmrTypeConfigService sysEmrTypeConfigService;
   @Autowired
   private IEmrIndexMMenuService emrIndexMMenuService;
   @Autowired
   private ITmplElemLinkService tmplElemLinkService;
   @Autowired
   private ICommonService commonService;
   @Autowired
   private IEditStateService editStateService;
   @Autowired
   private RedisCache redisCache;
   @Autowired
   private ITdCaConsApplyService tdCaConsApplyService;
   @Autowired
   private IQuoteElemService quoteElemService;
   @Autowired
   private IElemMacroService elemMacroService;
   @Autowired
   private IBabyInfoService babyInfoService;
   @Autowired
   private ISysUserService sysUserService;
   @Autowired
   private IEmrElemstoeService emrElemstoeService;
   @Autowired
   private ISubfileIndexService subfileIndexService;

   @PreAuthorize("@ss.hasAnyPermi('emr:index:delList,emr:index:editDelFlag')")
   @GetMapping({"/delList"})
   public TableDataInfo delList(IndexVo indexVo) {
      List<Index> list = new ArrayList(1);

      try {
         this.startPage();
         String altDateBegin = indexVo.getAltDateBegin();
         String altDateEnd = indexVo.getAltDateEnd();
         if (StringUtils.isNotBlank(altDateBegin)) {
            altDateBegin = altDateBegin + " 00:00:00";
            indexVo.setAltDateBegin(altDateBegin);
         }

         if (StringUtils.isNotBlank(altDateEnd)) {
            Date endDate = DateUtils.parseDate(altDateEnd, new String[]{DateUtils.YYYY_MM_DD});
            endDate = DateUtils.addDays(endDate, 1);
            altDateEnd = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, endDate);
            altDateEnd = altDateEnd + " 00:00:00";
            indexVo.setAltDateEnd(altDateEnd);
         }

         indexVo.setDelFlag("1");
         list = this.indexService.selectDelIndexPageList(indexVo);
      } catch (Exception e) {
         this.log.error("查询删除病历列表出现异常, ", e);
      }

      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('emr:index:delList')")
   @GetMapping({"/mrTypeList"})
   public AjaxResult mrTypeList() {
      AjaxResult ajaxResult = AjaxResult.success();

      try {
         List<SysDictData> mrTypeList = this.dictTypeService.selectDictDataByType("s004");
         ajaxResult.put("mrTypeList", mrTypeList);
      } catch (Exception e) {
         this.log.error("查询病历类型列表出现异常,", e);
         ajaxResult = AjaxResult.error("查询病历类型列表出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('emr:index:list,emr:record:queryList,pat:visitinfo:personalList')")
   @GetMapping({"/list"})
   public TableDataInfo list(IndexVo indexVo) {
      new TableDataInfo();

      TableDataInfo tableDataInfo;
      try {
         indexVo.setDelFlag("0");
         this.startPage();
         List<Index> list = this.indexService.selectDelIndexPageList(indexVo);
         tableDataInfo = this.getDataTable(list);
      } catch (Exception e) {
         this.log.error("查询病历列表出现异常", e);
         tableDataInfo = new TableDataInfo(500, "查询病历列表出现异常");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasAnyPermi('emr:index:list')")
   @GetMapping({"/todoEmrList"})
   public TableDataInfo todoEmrList(IndexVo indexVo) {
      new TableDataInfo();

      TableDataInfo tableDataInfo;
      try {
         if (StringUtils.isEmpty(indexVo.getPatientId())) {
            tableDataInfo = new TableDataInfo(500, "患者id不能为空");
         } else {
            List<String> secLevelList = this.indexService.getIndexSecLevelByEmplNumber(SecurityUtils.getLoginUser().getUser().getBasEmployee().getEmplNumber(), indexVo.getPatientId());
            indexVo.setSecLevelList(secLevelList);
            String noFinishState = this.sysEmrConfigService.selectSysEmrConfigByKey("0021");
            if (StringUtils.isNotBlank(noFinishState)) {
               List<String> noFinishStateList = Arrays.asList(noFinishState.split(","));
               indexVo.setNoFinishStateList(noFinishStateList);
            }

            this.startPage();
            List<IndexVo> list = this.indexService.selectTodoEmrList(indexVo);
            tableDataInfo = this.getDataTable(list);
         }
      } catch (Exception e) {
         this.log.error("查询待书写病历列表出现异常", e);
         tableDataInfo = new TableDataInfo(500, "查询待书写病历列表出现异常");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasAnyPermi('emr:index:list')")
   @GetMapping({"/selectTodoListFromTask"})
   public TableDataInfo selectTodoListFromTask(IndexVo indexVo) {
      new TableDataInfo();

      TableDataInfo tableDataInfo;
      try {
         if (StringUtils.isEmpty(indexVo.getPatientId())) {
            tableDataInfo = new TableDataInfo(500, "患者id不能为空");
         } else if (StringUtils.isBlank(indexVo.getTaskType())) {
            tableDataInfo = new TableDataInfo(500, "任务类型为空");
         } else {
            this.startPage();
            List<IndexVo> list = this.indexService.selectTodoListFromTask(indexVo);
            tableDataInfo = this.getDataTable(list);
         }
      } catch (Exception e) {
         this.log.error("根据患者住院号查询患者超时病历信息异常", e);
         tableDataInfo = new TableDataInfo(500, "根据患者住院号查询患者超时病历信息异常");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasAnyPermi('emr:index:list')")
   @GetMapping({"/yetEmrList"})
   public TableDataInfo yetEmrList(IndexVo indexVo) {
      new TableDataInfo();

      TableDataInfo tableDataInfo;
      try {
         if (StringUtils.isEmpty(indexVo.getPatientId())) {
            tableDataInfo = new TableDataInfo(500, "患者id不能为空");
         } else {
            List<String> secLevelList = this.indexService.getIndexSecLevelByEmplNumber(SecurityUtils.getLoginUser().getUser().getBasEmployee().getEmplNumber(), indexVo.getPatientId());
            indexVo.setSecLevelList(secLevelList);
            this.startPage();
            List<IndexVo> list = this.indexService.selectYetEmrList(indexVo);
            tableDataInfo = this.getDataTable(list);
         }
      } catch (Exception e) {
         this.log.error("查询已书写病历列表出现异常", e);
         tableDataInfo = new TableDataInfo(500, "查询已书写病历列表出现异常");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasAnyPermi('emr:index:list')")
   @GetMapping({"/todoEmrSubList"})
   public TableDataInfo todoEmrSubList(IndexVo indexVo) {
      new TableDataInfo();

      TableDataInfo tableDataInfo;
      try {
         if (StringUtils.isEmpty(indexVo.getPatientId())) {
            tableDataInfo = new TableDataInfo(500, "患者id不能为空");
         } else {
            this.startPage();
            List<IndexVo> list = this.indexService.selectTodoSubList(indexVo);
            tableDataInfo = this.getDataTable(list);
         }
      } catch (Exception e) {
         this.log.error("查询插入病程待书写病历列表出现异常", e);
         tableDataInfo = new TableDataInfo(500, "查询插入病程待书写病历列表出现异常");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasPermi('emr:index:export')")
   @Log(
      title = "病历索引",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(Index index) {
      List<Index> list = null;

      try {
         list = this.indexService.selectIndexList(index);
      } catch (Exception e) {
         this.log.error("导出病历索引列表出现异常：", e);
      }

      ExcelUtil<Index> util = new ExcelUtil(Index.class);
      return util.exportExcel(list, "病历索引数据");
   }

   @PreAuthorize("@ss.hasAnyPermi('emr:index:query,emr:index:helper')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.indexService.selectIndexById(id));
   }

   @PreAuthorize("@ss.hasPermi('emr:index:add')")
   @PostMapping
   public AjaxResult add(@RequestBody IndexVo indexVo) {
      AjaxResult ajaxResult = AjaxResult.success("创建成功");

      try {
         boolean flag = true;
         if (indexVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         TmplIndex tmplIndex = null;
         if (flag && indexVo.getTempId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("模板id参数不能为空");
         }

         if (flag && indexVo.getTempId() != null) {
            tmplIndex = this.tmplIndexService.selectTmplIndexById(indexVo.getTempId());
            if (tmplIndex == null) {
               flag = false;
               ajaxResult = AjaxResult.error("当前选中模板不存在");
            }
         }

         if (flag && StringUtils.isEmpty(indexVo.getPatientId())) {
            flag = false;
            ajaxResult = AjaxResult.error("患者就诊id不能为空");
         }

         if (flag) {
            SysUser sysUser = SecurityUtils.getLoginUser().getUser();
            EmrQcFlow emrQcFlow = this.emrQcFlowService.selectEmrQcFlowById(sysUser.getHospital().getOrgCode(), indexVo.getPatientId());
            if (emrQcFlow != null) {
               if (StringUtils.isNotBlank(emrQcFlow.getMrState()) && emrQcFlow.getMrState().equals("10")) {
                  flag = false;
                  ajaxResult = AjaxResult.error("当前患者已提交科室质控，不可新建病历，请科室质控退回后再试!");
               }

               if (StringUtils.isNotBlank(emrQcFlow.getMrState()) && emrQcFlow.getMrState().equals("12")) {
                  flag = false;
                  ajaxResult = AjaxResult.error("当前患者已提交终末质控，不可新建病历，请终末质控退回后再试！");
               }

               if (StringUtils.isNotBlank(emrQcFlow.getMrState()) && emrQcFlow.getMrState().equals("14")) {
                  flag = false;
                  ajaxResult = AjaxResult.error("当前患者已归档，不可新建病历！");
               }

               if (StringUtils.isNotBlank(emrQcFlow.getMrState()) && emrQcFlow.getMrState().equals("16")) {
                  flag = false;
                  ajaxResult = AjaxResult.error("当前患者已归档，不可新建病历！");
               }
            }
         }

         Boolean isMainFile = flag ? this.indexService.isEmrTypeMainFile(tmplIndex) : null;
         if (flag) {
            Index index = this.indexService.selectIndexByPatientId(indexVo.getPatientId());
            if (flag && isMainFile && index != null && index != null) {
               flag = false;
               ajaxResult = AjaxResult.error("病程记录已创建,请打开病程记录，插入此模板类型的病程记录");
            }

            if (isMainFile && index == null) {
               if (indexVo.getSubRecoDate() == null) {
                  flag = false;
                  ajaxResult = AjaxResult.error("首次创建病程记录，记录日期必须填写！");
               } else {
                  Boolean bool = this.indexService.betweenInhosTimeAndNow(indexVo.getPatientId(), indexVo.getSubRecoDate());
                  if (!bool) {
                     flag = false;
                     ajaxResult = AjaxResult.error("记录日期必须在患者入院时间和当前时间之间！");
                  }
               }
            }

            SysEmrTypeConfig emrTypeConfig = flag ? this.sysEmrTypeConfigService.selectSysEmrTypeConfigByTypeId(tmplIndex.getTempType()) : null;
            if (flag && emrTypeConfig != null && StringUtils.isNotBlank(emrTypeConfig.getLimitWriteFlag()) && emrTypeConfig.getLimitWriteFlag().equals("1") && emrTypeConfig.getLimitWriteNum() != null) {
               List<IndexVo> indexVoList = this.indexService.selectOpeIndexByEmrType(indexVo.getPatientId(), Arrays.asList(tmplIndex.getTempType()));
               if (indexVoList.size() >= emrTypeConfig.getLimitWriteNum()) {
                  flag = Boolean.FALSE;
                  ajaxResult = AjaxResult.error("病历类型【" + ((IndexVo)indexVoList.get(0)).getEmrTypeName() + "】每个患者只允许书写【" + emrTypeConfig.getLimitWriteNum() + "】个，不允许重复创建！");
               }
            }

            if (flag && (!isMainFile || index == null && isMainFile)) {
               if (indexVo.getTaskId() == null) {
                  List<EmrTaskInfoVo> taskList = this.emrTaskInfoService.selectPatientNoCreIndexType(indexVo.getPatientId(), tmplIndex.getTempType(), (Long)null);
                  if (flag && CollectionUtils.isNotEmpty(taskList)) {
                     flag = false;
                     List<Long> taskIdList = (List)taskList.stream().map((t) -> t.getId()).distinct().collect(Collectors.toList());
                     if (taskIdList.size() == 1) {
                        ajaxResult = AjaxResult.error("当前病历类型存在超时病历缺陷，请到文档中心选中对应病历类型的缺陷后再创建此病历！");
                        List<EmrTaskInfo> taskListTemp1 = (List)taskList.stream().filter((t) -> t.getApplId() != null && !t.getConState().equals("2")).collect(Collectors.toList());
                        List<EmrTaskInfo> taskListTemp2 = (List)taskList.stream().filter((t) -> t.getApplId() != null && t.getConState().equals("1")).collect(Collectors.toList());
                        if (CollectionUtils.isEmpty(taskListTemp1)) {
                           ajaxResult.put("modifyApplFlag", true);
                        }

                        if (CollectionUtils.isNotEmpty(taskListTemp1) && CollectionUtils.isEmpty(taskListTemp2)) {
                           ajaxResult.put("msg", "当前缺失病历缺陷的【超时书写申请】还未申请通过，【超时书写申请】通过后才能创建病历");
                        } else {
                           ajaxResult.put("taskId", ((EmrTaskInfoVo)taskList.get(0)).getId());
                        }
                     } else {
                        ajaxResult = AjaxResult.error("当前病历类型存在超时病历缺陷，请到文档中心选中对应病历类型的缺陷后再创建此病历！");
                     }
                  }
               } else if (StringUtils.isNotBlank(indexVo.getDataType()) && indexVo.getDataType().equals("3")) {
                  List<EmrTaskInfoVo> taskList = this.emrTaskInfoService.selectPatientNoCreIndexType(indexVo.getPatientId(), (String)null, indexVo.getTaskId());
                  if (taskList != null && (taskList == null || !taskList.isEmpty())) {
                     List<EmrTaskInfo> taskListTemp1 = (List)taskList.stream().filter((t) -> t.getApplId() != null).collect(Collectors.toList());
                     List<EmrTaskInfo> taskListTemp2 = (List)taskList.stream().filter((t) -> t.getApplId() != null && t.getConState().equals("1")).collect(Collectors.toList());
                     if (taskListTemp1 != null && (taskListTemp1 == null || !taskListTemp1.isEmpty())) {
                        if (taskListTemp1 != null && !taskListTemp1.isEmpty() && (taskListTemp2 == null || taskListTemp2 != null && taskListTemp2.isEmpty())) {
                           flag = false;
                           ajaxResult = AjaxResult.error("当前缺失病历缺陷的【超时书写申请】还未申请通过，【超时书写申请】通过后才能创建病历");
                        }
                     } else {
                        flag = false;
                        ajaxResult = AjaxResult.error("当前选中的未完成病历记录，请先创建【超时书写申请】");
                        ajaxResult.put("modifyApplFlag", true);
                     }
                  } else {
                     flag = false;
                     ajaxResult = AjaxResult.error("未找到未完成病历中选中的记录，请重新选择");
                  }
               }
            }
         }

         String patNewIndexKey = null;
         Date nowDate = this.commonService.getDbSysdate();
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
         String indexName = flag ? tmplIndex.getShowName() + sdf.format(nowDate) : null;
         String patNewIndexName = null;
         if (flag && !isMainFile) {
            patNewIndexKey = "pat_new_index_name:" + indexVo.getPatientId() + indexName;
            patNewIndexName = (String)this.redisCache.getCacheObject(patNewIndexKey);
            if (StringUtils.isNotBlank(patNewIndexName) && patNewIndexName.equals("1")) {
               flag = false;
               ajaxResult = AjaxResult.error("当前患者已有相同名称的病历，请1分钟后再试！");
            }
         }

         if (flag) {
            String config96 = this.sysEmrConfigService.selectSysEmrConfigByKey("0096");
            if (StringUtils.isNotBlank(config96)) {
               String[] split = config96.split(",");
               List<Long> elemList = new ArrayList();

               for(String key : split) {
                  elemList.add(Long.parseLong(key));
               }

               if (!elemList.contains(indexVo.getTempId())) {
                  Baseinfomation baseinfomation = this.commonService.findBaseInfo(indexVo.getPatientId());
                  if (baseinfomation != null) {
                     Integer personAge = baseinfomation.getPersonAge();
                     if (personAge != null && personAge >= 40) {
                        List<Index> list = this.indexService.selectIndexListByTmplList(elemList, indexVo.getPatientId());
                        if (list.isEmpty()) {
                           flag = Boolean.FALSE;
                           ajaxResult = AjaxResult.error("因患者年龄大于等于40岁，未创建病历'脑卒中患者危险因素评估记录'，不可创建新病历！");
                        }
                     }
                  }
               }
            }
         }

         ElemMacro elemMacro = new ElemMacro();
         elemMacro.setTempId(indexVo.getTempId());
         elemMacro.setSourTable("v_baby_info");
         List<ElemMacro> elemMacroList = this.elemMacroService.selectElemMacroList(elemMacro);
         List<BabyInfo> babyInfoList = this.babyInfoService.selectBabyInfoListByPatientId(indexVo.getPatientId());
         if (flag && CollectionUtils.isNotEmpty(elemMacroList) && CollectionUtils.isNotEmpty(babyInfoList) && babyInfoList.size() > 1 && StringUtils.isBlank(indexVo.getBabyId())) {
            flag = Boolean.FALSE;
            ajaxResult = AjaxResult.error("当前患者有多个新生儿信息，请选择新生儿后再创建病历");
         }

         if (flag) {
            Map<String, Object> map = this.indexService.selectEmrFirstOpenList(tmplIndex, indexVo, nowDate, indexName);
            map.put("tempName", tmplIndex.getTempName());
            map.put("showName", tmplIndex.getShowName());
            map.put("tempMajor", tmplIndex.getTempMajor());
            map.put("tempDisease", tmplIndex.getTempDisease());
            ajaxResult = AjaxResult.success((Object)map);
            if (StringUtils.isNotBlank(patNewIndexKey)) {
               this.redisCache.setCacheObject(patNewIndexKey, "1", 80, TimeUnit.SECONDS);
            }
         }

         if (flag) {
            TmplIndex tmplIndexRes = this.tmplIndexService.selectTmplIndexById(indexVo.getTempId());
            String editorType = this.sysEmrConfigService.selectSysEmrConfigByKey("0000");
            String xmlStr = this.emrBinaryService.selectTempXmlStr(tmplIndexRes);
            TempIndexSaveElemVo tempIndexSaveElemVo = XmlElementParseUtil.getSaveElemFromXml(xmlStr, editorType);
            Map<String, Object> resMap = this.indexService.selectAlertElems(indexVo.getPatientId(), indexVo.getBabyId(), indexVo.getTempId(), tempIndexSaveElemVo, (List)null);
            ajaxResult.put("AllElems", resMap);
         }
      } catch (Exception e) {
         this.log.error("创建病历出现异常", e);
         ajaxResult = AjaxResult.error("创建病历异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('emr:index:tempsave,emr:index:save')")
   @PutMapping
   public AjaxResult edit(@RequestBody IndexSaveVo indexSaveVo, HttpServletRequest request) {
      this.log.debug("saveIndex-111111111111111111: " + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
      AjaxResult ajaxResult = AjaxResult.success("保存成功");
      Boolean flag = true;
      String indexFileKey = null;
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();

      try {
         if (flag && indexSaveVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && (StringUtils.isEmpty(indexSaveVo.getPatientId()) || StringUtils.isBlank(indexSaveVo.getPatientId()))) {
            flag = false;
            ajaxResult = AjaxResult.error("患者id不能为空");
         }

         if (flag && (StringUtils.isEmpty(indexSaveVo.getSaveType()) || StringUtils.isBlank(indexSaveVo.getSaveType()))) {
            flag = false;
            ajaxResult = AjaxResult.error("保存类型不能为空");
         }

         if (flag && (StringUtils.isEmpty(indexSaveVo.getBase64Str()) || StringUtils.isBlank(indexSaveVo.getBase64Str()))) {
            flag = false;
            ajaxResult = AjaxResult.error("病历文件base64不能为空");
         }

         if (flag && (StringUtils.isEmpty(indexSaveVo.getXmlStr()) || StringUtils.isBlank(indexSaveVo.getXmlStr()))) {
            flag = false;
            ajaxResult = AjaxResult.error("病历文件xmlStr不能为空");
         }

         if (flag && indexSaveVo.getId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("病历文件id不能为空");
         }

         if (flag && StringUtils.isNotEmpty(indexSaveVo.getMrType()) && StringUtils.isNotBlank(indexSaveVo.getMrType()) && indexSaveVo.equals("MAINFILE")) {
            if (flag && (StringUtils.isEmpty(indexSaveVo.getSubFileBase64Str()) || StringUtils.isBlank(indexSaveVo.getSubFileBase64Str()))) {
               flag = false;
               ajaxResult = AjaxResult.error("子病历base64不能为空");
            }

            if (flag && (StringUtils.isEmpty(indexSaveVo.getSubFileXmlStr()) || StringUtils.isBlank(indexSaveVo.getSubFileXmlStr()))) {
               flag = false;
               ajaxResult = AjaxResult.error("子病历xmlStr不能为空");
            }
         }

         Index index = null;
         SubfileIndex subfileIndex = null;
         boolean addChildIndex = false;
         String emrTypeCode = null;
         if (flag) {
            index = this.indexService.selectIndexById(indexSaveVo.getId());
         }

         if (flag && index == null) {
            if (flag && indexSaveVo.getTempId() == null) {
               flag = false;
               ajaxResult = AjaxResult.error("病历模板ID不能为空");
            }

            if (flag && indexSaveVo.getCreDate() == null) {
               flag = false;
               ajaxResult = AjaxResult.error("病历创建时间不能为空");
            }

            if (flag && (StringUtils.isEmpty(indexSaveVo.getIndexName()) || StringUtils.isBlank(indexSaveVo.getIndexName()))) {
               flag = false;
               ajaxResult = AjaxResult.error("病历名称不能为空");
            }

            if (flag && StringUtils.isNotEmpty(indexSaveVo.getMrType()) && StringUtils.isNotBlank(indexSaveVo.getMrType()) && indexSaveVo.getMrType().equals("MAINFILE")) {
               Index indexTemp = this.indexService.selectIndexByPatientId(indexSaveVo.getPatientId());
               if (flag && indexTemp != null && "MAINFILE".equals(indexTemp.getMrType())) {
                  flag = false;
                  ajaxResult = AjaxResult.error("病程记录已创建，不能重复创建");
               }

               if (flag && (StringUtils.isEmpty(indexSaveVo.getSubIndexName()) || StringUtils.isBlank(indexSaveVo.getSubIndexName()))) {
                  flag = false;
                  ajaxResult = AjaxResult.error("病历子文件名称不能为空");
               }

               if (flag && (StringUtils.isEmpty(indexSaveVo.getSubIndexContElemName()) || StringUtils.isBlank(indexSaveVo.getSubIndexContElemName()))) {
                  flag = false;
                  ajaxResult = AjaxResult.error("病历子文件所在区域标识不能为空");
               }
            }

            emrTypeCode = indexSaveVo.getEmrTypeCode();
         } else {
            if (flag) {
               emrTypeCode = index != null ? index.getMrType() : emrTypeCode;
            }

            if (flag && index.getMrType().equals("MAINFILE")) {
               if (indexSaveVo.getSubFileIndexId() == null) {
                  addChildIndex = true;
                  if (flag && (StringUtils.isEmpty(indexSaveVo.getSubIndexContElemName()) || StringUtils.isBlank(indexSaveVo.getSubIndexContElemName()))) {
                     flag = false;
                     ajaxResult = AjaxResult.error("病历子文件所在区域标识不能为空");
                  }

                  emrTypeCode = indexSaveVo.getEmrTypeCode();
               } else {
                  if (flag && indexSaveVo.getSubFileIndexId() == null) {
                     flag = false;
                     ajaxResult = AjaxResult.error("子病历id不能为空");
                  }

                  if (flag && (StringUtils.isEmpty(indexSaveVo.getSubIndexName()) || StringUtils.isBlank(indexSaveVo.getSubIndexName()))) {
                     flag = false;
                     ajaxResult = AjaxResult.error("病历子文件名称不能为空");
                  }

                  subfileIndex = this.subfileIndexService.selectSubfileIndexById(indexSaveVo.getSubFileIndexId());
                  if (flag && subfileIndex == null) {
                     flag = false;
                     ajaxResult = AjaxResult.error("没有这个子病历文档记录");
                  }

                  String regainSaveFlag = indexSaveVo.getRegainSaveFlag();
                  if (flag && subfileIndex.getDelFlag().equals("1") && (StringUtils.isBlank(regainSaveFlag) || StringUtils.isNotBlank(regainSaveFlag) && regainSaveFlag.equals("FALSE"))) {
                     flag = false;
                     ajaxResult = AjaxResult.error("当前子病历文档记录已被删除，不能保存");
                  }

                  emrTypeCode = subfileIndex != null ? subfileIndex.getMrType() : null;
               }
            }
         }

         if (flag && index == null) {
            SysEmrTypeConfig emrTypeConfig = flag ? this.sysEmrTypeConfigService.selectSysEmrTypeConfigByTypeId(indexSaveVo.getEmrTypeCode()) : null;
            if (emrTypeConfig != null && StringUtils.isNotBlank(emrTypeConfig.getLimitWriteFlag()) && emrTypeConfig.getLimitWriteFlag().equals("1") && emrTypeConfig.getLimitWriteNum() != null) {
               List<IndexVo> indexVoList = this.indexService.selectOpeIndexByEmrType(indexSaveVo.getPatientId(), Arrays.asList(emrTypeConfig.getEmrTypeCode()));
               if (indexVoList.size() >= emrTypeConfig.getLimitWriteNum()) {
                  flag = Boolean.FALSE;
                  ajaxResult = AjaxResult.error("病历类型【" + ((IndexVo)indexVoList.get(0)).getEmrTypeName() + "】每个患者只允许书写【" + emrTypeConfig.getLimitWriteNum() + "】个，不允许重复创建！");
               }
            }
         }

         if (flag && index != null) {
            EditState editState = this.editStateService.selectEditStateByEmrId(index.getId());
            String ip = IPAddressUtil.getIPAddress(request);
            if (editState == null) {
               flag = false;
               ajaxResult = AjaxResult.error("没有启用编辑不能保存");
            }

            if (flag && index.getMrType().equals("MAINFILE") && editState != null && !editState.getIp().equals(ip) && sysUser.getUserName().equals(editState.getEditPersonCd())) {
               flag = false;
               ajaxResult = AjaxResult.error("【" + editState.getEditPersonName() + "】正在编辑该病历，请拷贝新录入内容，重新打开病历后再试！");
            }

            if (flag && !index.getMrType().equals("MAINFILE") && editState != null && (!editState.getIp().equals(ip) || !sysUser.getUserName().equals(editState.getEditPersonCd()))) {
               flag = false;
               ajaxResult = AjaxResult.error("【" + editState.getEditPersonName() + "】正在编辑该病历，请拷贝新录入内容，重新打开病历后再试！");
            }
         }

         if (flag && index != null) {
            String mrState = subfileIndex != null ? subfileIndex.getMrState() : index.getMrState();
            mrState = addChildIndex ? null : mrState;
            Boolean mrStateIsNull = StringUtils.isEmpty(mrState) || StringUtils.isBlank(mrState);
            if (indexSaveVo.getSaveType().equals("01") && !mrStateIsNull && !mrState.equals("01")) {
               flag = false;
               ajaxResult = AjaxResult.error("病历文件当前的状态不允许操作【暂存】");
            }
         }

         List<EmrQcListVo> qcExcepationList = this.indexService.getQcErrorList(indexSaveVo);
         String xmlStr = StringUtils.isNotBlank(indexSaveVo.getSubFileXmlStr()) ? indexSaveVo.getSubFileXmlStr() : indexSaveVo.getXmlStr();
         String qcMrState = index == null ? "03" : index.getMrState();
         XmlElementParseConfigVo configVo = this.indexService.getXmlElementParseConfigs();
         List<ElemAttriVo> elemAttriList = XmlElementParseUtil.getElemAttriVoListFromXml(xmlStr, configVo);
         if (flag && (indexSaveVo.getAutoSaveFlag() == null || indexSaveVo.getAutoSaveFlag() != null && !indexSaveVo.getAutoSaveFlag()) && (StringUtils.isEmpty(indexSaveVo.getRegainSaveFlag()) || !indexSaveVo.getRegainSaveFlag().equals("TRUE")) && indexSaveVo.getSaveType().equals("03") && !qcMrState.equals("05") && !qcMrState.equals("07") && !qcMrState.equals("08") && elemAttriList != null && !elemAttriList.isEmpty()) {
            indexSaveVo.setEmrTypeCode(emrTypeCode);
            this.log.debug("saveIndex-emrQualityCheck-22222222222222: " + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
            List<EmrQcListVo> list = this.indexService.emrQualityCheck(indexSaveVo, elemAttriList, qcExcepationList);
            this.log.debug("saveIndex-emrQualityCheck--22222222222222: " + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
            if (list != null && list.size() > 0) {
               flag = false;
               ajaxResult = AjaxResult.error("当前病历有缺陷，请处理完成后再试");
            }

            list.addAll(qcExcepationList);
            indexSaveVo.setEmrQcListVoList(list);
            ajaxResult.put("emrQcList", list);
         }

         if (flag && indexSaveVo.getTaskId() != null) {
            EmrTaskInfo emrTaskInfo = this.emrTaskInfoService.selectEmrTaskInfoById(indexSaveVo.getTaskId());
            if (emrTaskInfo == null) {
               flag = false;
               ajaxResult = AjaxResult.error("没有这个未完成病历的记录，请检查后再创建");
            }
         }

         if (flag && index != null) {
            indexFileKey = "index_file_read_write:" + indexSaveVo.getId();
            String indexFileReadFlag = (String)this.redisCache.getCacheObject(indexFileKey);
            if (StringUtils.isNotBlank(indexFileReadFlag) && indexFileReadFlag.equals("1")) {
               flag = false;
               ajaxResult = AjaxResult.error("当前病历有正在保存的操作，不能继续保存，请稍后再试！");
               indexFileKey = null;
            } else {
               this.redisCache.setCacheObject(indexFileKey, "1", 10, TimeUnit.MINUTES);
            }
         }

         if (flag) {
            String saveTypeMsg = indexSaveVo.getSaveType().equals("01") ? "暂存" : "保存";
            this.log.debug("saveIndex-22222222222222: " + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
            String mrState = index != null && !index.getMrState().equals("01") && !index.getMrState().equals("03") ? index.getMrState() : indexSaveVo.getSaveType();
            if (subfileIndex != null && subfileIndex.getId() != null) {
               mrState = subfileIndex != null && !subfileIndex.getMrState().equals("01") && !subfileIndex.getMrState().equals("03") ? subfileIndex.getMrState() : indexSaveVo.getSaveType();
            }

            indexSaveVo.setSaveType(mrState);
            IndexSaveReturnVo indexSaveReturnVo = this.indexService.saveIndex(indexSaveVo, index, subfileIndex, elemAttriList, request);
            this.indexService.saveIndexElemAsync(indexSaveReturnVo.getIndex(), indexSaveReturnVo.getSubfileIndex(), elemAttriList, indexSaveReturnVo.getUpdateUser(), indexSaveVo.getElemBase64Map());
            this.indexService.saveIndexElemSharingAsync(indexSaveReturnVo.getIndex(), indexSaveReturnVo.getSubfileIndex(), elemAttriList, indexSaveReturnVo.getUpdateUser());
            this.log.debug("saveIndex-2020202020202020202020: " + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
            ajaxResult = AjaxResult.success("病历" + saveTypeMsg + "成功");
            ajaxResult.put("mrState", mrState);
            ajaxResult.put("index", indexSaveReturnVo.getIndex());
            ajaxResult.put("currMenuTreeList", indexSaveReturnVo.getCurrMenuTreeList());
            ajaxResult.put("subIndexFileList", indexSaveReturnVo.getSubfileIndexList());
            if (StringUtils.isNotBlank(indexFileKey)) {
               this.redisCache.deleteObject(indexFileKey);
            }
         }

         if (flag) {
            String editorType = this.sysEmrConfigService.selectSysEmrConfigByKey("0000");
            TempIndexSaveElemVo tempIndexSaveElemVo = XmlElementParseUtil.getSaveElemFromXml(xmlStr, editorType);
            Map<String, Object> resMap = this.indexService.selectAlertElems(indexSaveVo.getPatientId(), indexSaveVo.getBabyId(), indexSaveVo.getTempId(), tempIndexSaveElemVo, (List)null);
            ajaxResult.put("AllElems", resMap);
         }
      } catch (Exception e) {
         ajaxResult = AjaxResult.error("保存病历文件出现异常，请联系管理员");
         this.log.error("保存病历文件出现异常：", e);
         if (StringUtils.isNotBlank(indexFileKey)) {
            this.redisCache.deleteObject(indexFileKey);
         }
      }

      return ajaxResult;
   }

   @Log(
      title = "病历恢复",
      businessType = BusinessType.UPDATE
   )
   @PreAuthorize("@ss.hasPermi('emr:index:changeDelFlag')")
   @PostMapping({"/changeDelFlag"})
   public AjaxResult changeDelFlag(Long id, HttpServletRequest httpServletRequest) {
      AjaxResult ajaxResult = AjaxResult.success("病历恢复成功");
      boolean flag = true;

      try {
         if (id == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         Index index = this.indexService.selectIndexById(id);
         SubfileIndex subfileIndex = this.subfileIndexService.selectSubfileIndexById(id);
         if (flag && index == null && subfileIndex == null) {
            flag = false;
            ajaxResult = AjaxResult.error("没有此病历文件");
         }

         if (index != null && "17".equals(index.getMrType())) {
            TdCaConsApply tdCaConsApply = this.tdCaConsApplyService.selectTdCaConsApplyByMrFileId(index.getId());
            if (tdCaConsApply != null) {
               flag = false;
               ajaxResult = AjaxResult.error("该病历对应的会诊申请已作废，病历无法恢复，请重新发起会诊");
            }
         }

         String altPreCode = subfileIndex != null && subfileIndex.getAltPerCode() != null ? subfileIndex.getAltPerCode() : index.getAltPerCode();
         if (!altPreCode.equals(SecurityUtils.getUsername())) {
            flag = false;
            ajaxResult = AjaxResult.error("不是您删除的病历不可恢复");
         }

         if (flag) {
            Integer lockState = subfileIndex != null ? subfileIndex.getLockState() : index.getLockState();
            if (lockState != null && lockState.equals(CommonConstants.SEALUP_RECORD_STATUS.STATUS_1)) {
               flag = false;
               ajaxResult = AjaxResult.error("当前病历已封存，不可恢复");
            }
         }

         EmrQcFlow emrQcFlow = flag ? this.emrQcFlowService.selectEmrQcFlowById(SecurityUtils.getLoginUser().getUser().getHospital().getOrgCode(), index.getPatientId()) : null;
         if (flag && emrQcFlow != null && emrQcFlow.getMrState().equals("16")) {
            flag = false;
            ajaxResult = AjaxResult.error("当前病历已归档，不可恢复");
         }

         SysEmrTypeConfig emrTypeConfig = flag ? this.sysEmrTypeConfigService.selectSysEmrTypeConfigByTypeId(index.getMrType()) : null;
         if (flag && emrTypeConfig != null && StringUtils.isNotBlank(emrTypeConfig.getLimitWriteFlag()) && emrTypeConfig.getLimitWriteFlag().equals("1") && emrTypeConfig.getLimitWriteNum() != null) {
            List<IndexVo> indexVoList = this.indexService.selectOpeIndexByEmrType(index.getPatientId(), Arrays.asList(index.getMrType()));
            if (indexVoList.size() >= emrTypeConfig.getLimitWriteNum()) {
               flag = Boolean.FALSE;
               ajaxResult = AjaxResult.error("病历类型【" + ((IndexVo)indexVoList.get(0)).getEmrTypeName() + "】每个患者只允许书写【" + emrTypeConfig.getLimitWriteNum() + "】个，已有此类型病历，不允许再恢复！");
            }
         }

         if (flag) {
            this.indexService.changeDelFlag(index, subfileIndex, httpServletRequest);
         }
      } catch (Exception e) {
         this.log.error("病历恢复出现异常", e);
         ajaxResult = AjaxResult.error("病历恢复出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('emr:index:editDelFlag')")
   @Log(
      title = "删除病历",
      businessType = BusinessType.UPDATE
   )
   @PutMapping({"/{id}"})
   public AjaxResult update(@PathVariable Long id, HttpServletRequest httpServletRequest) {
      AjaxResult ajaxResult = AjaxResult.success("删除成功");
      boolean flag = true;
      String indexFileKey = null;

      try {
         EmrTaskInfo emrTaskInfo = new EmrTaskInfo();
         emrTaskInfo.setId(id);
         emrTaskInfo.setTaskType("1");
         emrTaskInfo.setTreatFlag("0");
         List<EmrTaskInfo> emrTaskInfoList = this.emrTaskInfoService.selectEmrTaskInfoList(emrTaskInfo);
         if (CollectionUtils.isNotEmpty(emrTaskInfoList)) {
            this.emrTaskInfoService.updateTaskInfoById(id);
            ajaxResult = AjaxResult.success("删除成功");
         } else {
            Index index = this.indexService.selectIndexById(id);
            SubfileIndex subfileIndex = this.subfileIndexService.selectSubfileIndexById(id);
            if (index == null && subfileIndex == null) {
               flag = false;
               ajaxResult = AjaxResult.error("未查询到当前病历信息");
            }

            if (flag && subfileIndex != null) {
               flag = false;
               ajaxResult = AjaxResult.error("病程文件请在打开病历后删除");
            }

            if (flag) {
               Integer lockState = subfileIndex != null ? subfileIndex.getLockState() : index.getLockState();
               if (lockState != null && lockState.equals(CommonConstants.SEALUP_RECORD_STATUS.STATUS_1)) {
                  flag = false;
                  ajaxResult = AjaxResult.error("当前病历已封存，不可删除");
               }
            }

            EmrQcFlow emrQcFlow = flag ? this.emrQcFlowService.selectEmrQcFlowById(SecurityUtils.getLoginUser().getUser().getHospital().getOrgCode(), index.getPatientId()) : null;
            if (flag && emrQcFlow != null && emrQcFlow.getMrState().equals("16")) {
               flag = false;
               ajaxResult = AjaxResult.error("当前病历已归档，不可删除");
            }

            if (index != null && "17".equals(index.getMrType())) {
               TdCaConsApply tdCaConsApply = this.tdCaConsApplyService.selectTdCaConsApplyByMrFileId(index.getId());
               if (tdCaConsApply != null && !"01".equals(tdCaConsApply.getState()) && !"02".equals(tdCaConsApply.getState())) {
                  flag = false;
                  ajaxResult = AjaxResult.error("该病历对应的会诊医师已接受邀请，不允许作废该病历");
               }
            }

            if (flag) {
               String mrState = subfileIndex == null ? index.getMrState() : subfileIndex.getMrState();
               String creCode = subfileIndex == null ? index.getCrePerCode() : subfileIndex.getCrePerCode();
               SysUser sysUser = SecurityUtils.getLoginUser().getUser();
               boolean creCodeFlag = sysUser.getBasEmployee().getEmplNumber().equals(creCode);
               if (!creCodeFlag && ("01".equals(mrState) || "03".equals(mrState))) {
                  flag = false;
                  ajaxResult = AjaxResult.error("只有此病历的创建人才能删除此病历");
               }
            }

            boolean bool = flag ? this.indexService.isDel(index, subfileIndex) : flag;
            if (flag && !bool) {
               flag = false;
               ajaxResult = AjaxResult.error("当前病历不可删除，请创建人申请后再删除");
            }

            if (flag && index != null) {
               indexFileKey = "index_file_read_write:" + index.getId();
               String indexFileReadFlag = (String)this.redisCache.getCacheObject(indexFileKey);
               if (StringUtils.isNotBlank(indexFileReadFlag) && indexFileReadFlag.equals("1")) {
                  flag = false;
                  ajaxResult = AjaxResult.error("当前病历有正在保存的操作，不能继续保存，请稍后再试！");
                  indexFileKey = null;
               } else {
                  this.redisCache.setCacheObject(indexFileKey, "1", 10, TimeUnit.MINUTES);
               }
            }

            if (flag) {
               this.indexService.editDelFlagById(index, subfileIndex, "", "", httpServletRequest);
               if (index != null && "17".equals(index.getMrType())) {
                  TdCaConsApply tdCaConsApply = this.tdCaConsApplyService.selectTdCaConsApplyByMrFileId(index.getId());
                  ajaxResult.put("tdCaConsApply", tdCaConsApply);
               }

               if (StringUtils.isNotBlank(indexFileKey)) {
                  this.redisCache.deleteObject(indexFileKey);
               }
            }
         }
      } catch (Exception e) {
         this.log.error("删除病历索引出现异常", e);
         ajaxResult = AjaxResult.error("删除病历索引出现异常");
         if (StringUtils.isNotBlank(indexFileKey)) {
            this.redisCache.deleteObject(indexFileKey);
         }
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('emr:index:editDelFlag')")
   @Log(
      title = "修改病历删除状态",
      businessType = BusinessType.UPDATE
   )
   @PutMapping({"/editDelFlagOnly"})
   public AjaxResult editDelFlagOnly(@RequestBody IndexDelVo indexDelVo, HttpServletRequest httpServletRequest) {
      AjaxResult ajaxResult = AjaxResult.success("删除成功");
      boolean flag = true;

      try {
         if (flag && indexDelVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && indexDelVo.getId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("病历id参数未填写");
         }

         Index index = null;
         if (flag) {
            index = this.indexService.selectIndexById(indexDelVo.getId());
            if (index == null) {
               flag = false;
               ajaxResult = AjaxResult.error("没有此病历记录");
            }
         }

         SubfileIndex subfileIndex = null;
         if (flag && indexDelVo.getSubfileIndexId() != null) {
            subfileIndex = this.subfileIndexService.selectSubfileIndexById(indexDelVo.getSubfileIndexId());
            if (subfileIndex == null) {
               flag = false;
               ajaxResult = AjaxResult.error("没有此病程记录");
            }
         }

         if (flag) {
            boolean bool = this.indexService.isDel(index, subfileIndex);
            if (bool) {
               if (index.getLockState() != null && CommonConstants.SEALUP_RECORD_STATUS.STATUS_1.equals(index.getLockState())) {
                  flag = false;
                  ajaxResult = AjaxResult.error("当前病历已封存，不可删除");
               }

               EmrQcFlow emrQcFlow = flag ? this.emrQcFlowService.selectEmrQcFlowById(SecurityUtils.getLoginUser().getUser().getHospital().getOrgCode(), index.getPatientId()) : null;
               if (flag && emrQcFlow != null && emrQcFlow.getMrState().equals("16")) {
                  flag = false;
                  ajaxResult = AjaxResult.error("当前病历已归档，不可删除");
               }
            } else {
               flag = false;
               ajaxResult = AjaxResult.error("当前病历不可删除，请申请后再删除");
            }
         }

         if (flag) {
            List<SubfileIndex> subfileIndexList = this.indexService.editDelFlagOnly(index, subfileIndex, httpServletRequest);
            if (subfileIndex != null && (subfileIndexList == null || subfileIndexList != null && subfileIndexList.isEmpty())) {
               ajaxResult.put("MAINFILE_lastFile", true);
            }
         }
      } catch (Exception e) {
         this.log.error("删除病历出现异常", e);
         ajaxResult = AjaxResult.error("删除病历出现异常，请重新打开再操作删除");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('emr:index:editDelFlag')")
   @Log(
      title = "修改病历删除状态",
      businessType = BusinessType.UPDATE
   )
   @PutMapping({"/editDelFlag"})
   public AjaxResult editDelFlag(@RequestBody IndexDelVo indexDelVo, HttpServletRequest httpServletRequest) {
      AjaxResult ajaxResult = AjaxResult.success("删除成功");
      boolean flag = true;
      String indexFileKey = null;

      try {
         if (flag && indexDelVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && indexDelVo.getId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("病历id参数未填写");
         }

         Index index = null;
         if (flag) {
            index = this.indexService.selectIndexById(indexDelVo.getId());
            if (index == null) {
               flag = false;
               ajaxResult = AjaxResult.error("没有此病历记录");
            }
         }

         SubfileIndex subfileIndex = null;
         if (flag && indexDelVo.getSubfileIndexId() != null) {
            subfileIndex = this.subfileIndexService.selectSubfileIndexById(indexDelVo.getSubfileIndexId());
            if (subfileIndex == null) {
               flag = false;
               ajaxResult = AjaxResult.error("没有此病程记录");
            }

            if (flag && StringUtils.isBlank(indexDelVo.getBase64())) {
               flag = false;
               ajaxResult = AjaxResult.error("病历的base64不能为空");
            }

            if (flag && StringUtils.isBlank(indexDelVo.getXmlStr())) {
               flag = false;
               ajaxResult = AjaxResult.error("病历的xmlStr不能为空");
            }
         }

         if (flag) {
            boolean bool = this.indexService.isDel(index, subfileIndex);
            if (bool) {
               if (index.getLockState() != null && CommonConstants.SEALUP_RECORD_STATUS.STATUS_1.equals(index.getLockState())) {
                  flag = false;
                  ajaxResult = AjaxResult.error("当前病历已封存，不可删除");
               }
            } else {
               flag = false;
               ajaxResult = AjaxResult.error("当前病历不可删除，请申请后再删除");
            }
         }

         if (flag && index != null) {
            indexFileKey = "index_file_read_write:" + index.getId();
            String indexFileReadFlag = (String)this.redisCache.getCacheObject(indexFileKey);
            if (StringUtils.isNotBlank(indexFileReadFlag) && indexFileReadFlag.equals("1")) {
               flag = false;
               ajaxResult = AjaxResult.error("当前病历有正在保存的操作，不能继续签名保存，请稍后再试！");
               indexFileKey = null;
            } else {
               this.redisCache.setCacheObject(indexFileKey, "1", 10, TimeUnit.MINUTES);
            }
         }

         if (flag) {
            Long changeDeptBedId = subfileIndex.getChangeDepBedId();
            List<SubfileIndex> subfileIndexList = this.indexService.editDelFlagById(index, subfileIndex, indexDelVo.getBase64(), indexDelVo.getXmlStr(), httpServletRequest);
            if (subfileIndex != null && (subfileIndexList == null || subfileIndexList != null && subfileIndexList.isEmpty())) {
               ajaxResult.put("MAINFILE_lastFile", true);
            }
         }

         if (StringUtils.isNotBlank(indexFileKey)) {
            this.redisCache.deleteObject(indexFileKey);
         }
      } catch (Exception e) {
         this.log.error("删除病历出现异常", e);
         ajaxResult = AjaxResult.error("删除病历出现异常，请重新打开再操作删除");
         if (StringUtils.isNotBlank(indexFileKey)) {
            this.redisCache.deleteObject(indexFileKey);
         }
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('emr:index:sealupList')")
   @GetMapping({"/sealupList"})
   public TableDataInfo sealupList(@RequestBody SealupRecord sealupRecord) {
      new ArrayList();
      new TableDataInfo();

      TableDataInfo tableDataInfo;
      try {
         IndexVo indexVo = new IndexVo();
         indexVo.setPatientId(sealupRecord.getPatientId());
         indexVo.setLockState(1);
         this.startPage();
         List list = this.indexService.selectSealupIndexList(indexVo);
         tableDataInfo = this.getDataTable(list);
      } catch (Exception e) {
         this.log.error("查询患者封锁病历列表出现异常,", e);
         tableDataInfo = new TableDataInfo(500, "查询封锁病历列表");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasPermi('emr:index:getSysUserDept')")
   @GetMapping({"/getSysUserDept"})
   public AjaxResult getSysUserDept() {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");
      SysUser user = SecurityUtils.getLoginUser().getUser();

      try {
         ajaxResult = AjaxResult.success((Object)this.indexService.selectSysUserDept(user.getUserId()));
      } catch (Exception e) {
         this.log.error("查询当前登录用户科室列表出现异常", e);
         ajaxResult = AjaxResult.error("查询当前登录用户科室列表出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('emr:index:settingSecLevel')")
   @Log(
      title = "设置病历密集",
      businessType = BusinessType.UPDATE
   )
   @PutMapping({"/settingSecLevel"})
   public AjaxResult settingSecLevel(@RequestBody IndexSecLevelVo indexSecLevelVo) {
      AjaxResult ajaxResult = AjaxResult.success("设置病历密级成功");
      Boolean flag = true;

      try {
         if (flag && indexSecLevelVo.getSecLevel() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag) {
            if (indexSecLevelVo.getIds() != null && indexSecLevelVo.getIds().length > 0) {
               this.indexService.settingSecLevel(indexSecLevelVo);
            } else {
               ajaxResult = AjaxResult.error("参数不能为空");
            }
         }
      } catch (Exception e) {
         this.log.error("设置病历密级出现异常,", e);
         ajaxResult = AjaxResult.error("设置病历密级出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('emr:index:otherIndexTree,emr:index:helper')")
   @GetMapping({"/otherIndexTree"})
   public AjaxResult otherIndexTree(Index index) {
      AjaxResult ajaxResult = new AjaxResult();
      Boolean flag = true;

      try {
         if (flag && StringUtils.isBlank(index.getPatientId())) {
            flag = false;
            ajaxResult = AjaxResult.error("患者id不能为空");
         }

         if (flag) {
            List<TreeSelect> treeSelect = this.indexService.selectOtherIndexTreeList(index);
            ajaxResult = AjaxResult.success((Object)treeSelect);
         }
      } catch (Exception e) {
         this.log.error("查询其他病历文档树出现异常", e);
         ajaxResult = AjaxResult.error("查询其他病历文档树出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('emr:index:hisIndexTree,emr:index:helper')")
   @GetMapping({"/hisIndexTree"})
   public AjaxResult hisIndexTree(String patientId) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         if (StringUtils.isBlank(patientId)) {
            ajaxResult = AjaxResult.error("患者id不能为空");
         } else {
            List<HisIndexVo> treeSelect = this.indexService.selectHisIndexTreeList(patientId);
            ajaxResult = AjaxResult.success((Object)treeSelect);
         }
      } catch (Exception e) {
         this.log.error("查询其他病历文档树出现异常");
         ajaxResult = AjaxResult.error("查询其他病历文档树出现异常", e);
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('emr:index:selectEmrInfo,qc:rt:check,qc:rt:checked,qc:flow:term,pat:info:emrAllList,qc:statis:checkCase')")
   @GetMapping({"/selectEmrInfo"})
   public AjaxResult selectEmrInfo(Long id, String secrecyBz, HttpServletRequest httpServletRequest) {
      AjaxResult ajaxResult = new AjaxResult();
      boolean flag = true;
      String indexFileKey = null;
      SysUser user = SecurityUtils.getLoginUser().getUser();

      try {
         if (flag && id == null) {
            flag = false;
            ajaxResult = AjaxResult.error("病历id不能为空");
         }

         if (flag) {
            indexFileKey = "index_file_read_write:" + id;
            String indexFileReadFlag = (String)this.redisCache.getCacheObject(indexFileKey);
            if (StringUtils.isNotBlank(indexFileReadFlag) && indexFileReadFlag.equals("1")) {
               flag = false;
               ajaxResult = AjaxResult.error("当前病历有正在保存的操作，不能读取，请稍后再试！");
            }
         }

         if (flag) {
            IndexVo indexVo = this.indexService.selectEmrInfoAndFileList(id, secrecyBz, httpServletRequest);
            if (indexVo != null && indexVo.getSubFileIndexList() != null && indexVo.getSubFileIndexList().size() > 0) {
               for(SubfileIndexVo subfileIndexVo : indexVo.getSubFileIndexList()) {
                  EmrTaskInfoVo emrTaskInfoVo = new EmrTaskInfoVo();
                  emrTaskInfoVo.setMrFileId(subfileIndexVo.getId());
                  emrTaskInfoVo.setDocCd(user.getUserName());
                  emrTaskInfoVo.setBusSection("22");
                  List<EmrTaskInfo> emrTaskInfoList = this.emrTaskInfoService.selectInfoByOdcsAndIndex(emrTaskInfoVo);
                  if (emrTaskInfoList != null && emrTaskInfoList.size() > 0 && "08".equals(subfileIndexVo.getMrState())) {
                     subfileIndexVo.setFreeMoveType("22");
                  }
               }
            } else {
               EmrTaskInfoVo emrTaskInfoVo = new EmrTaskInfoVo();
               emrTaskInfoVo.setMrFileId(id);
               emrTaskInfoVo.setDocCd(user.getUserName());
               emrTaskInfoVo.setBusSection("22");
               List<EmrTaskInfo> emrTaskInfoList = this.emrTaskInfoService.selectInfoByOdcsAndIndex(emrTaskInfoVo);
               if (emrTaskInfoList != null && emrTaskInfoList.size() > 0 && "08".equals(indexVo.getMrState())) {
                  indexVo.setFreeMoveType("22");
               }
            }

            ajaxResult = AjaxResult.success((Object)indexVo);
         }
      } catch (Exception e) {
         this.log.error("查询某个病历信息异常", e);
         ajaxResult = AjaxResult.error("查询某个病历信息异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('emr:index:authUpdate')")
   @PostMapping({"/authUpdate"})
   public AjaxResult authUpdate(@RequestBody IndexVo indexVo, HttpServletRequest request) {
      this.log.debug("authUpdate-111111111111111111: " + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
      AjaxResult ajaxResult = AjaxResult.success();
      String indexFileKey = null;
      String indexAuthKey = null;

      try {
         boolean flag = true;
         if (indexVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && indexVo.getId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("病历id不能为空");
         }

         Index index = null;
         if (flag) {
            index = this.indexService.selectIndexById(indexVo.getId());
            if (index == null) {
               flag = false;
               ajaxResult = AjaxResult.error("当前病历文件不存在");
            }
         }

         SubfileIndex subfileIndex = null;
         Boolean mainFileRefresh = false;
         if (flag && "MAINFILE".equals(index.getMrType()) && indexVo.getSubFileIndexId() != null) {
            subfileIndex = this.subfileIndexService.selectSubfileIndexById(indexVo.getSubFileIndexId());
            if (subfileIndex == null || subfileIndex != null && subfileIndex.getDelFlag().equals("1")) {
               flag = false;
               ajaxResult = AjaxResult.error("当前病程记录已被删除");
               mainFileRefresh = true;
               ajaxResult.put("mainFileRefresh", mainFileRefresh);
            }
         }

         if (flag && "MAINFILE".equals(index.getMrType()) && StringUtils.isNotBlank(indexVo.getNewSubFileFlag()) && indexVo.getNewSubFileFlag().equals("1")) {
            mainFileRefresh = true;
            ajaxResult.put("mainFileRefresh", mainFileRefresh);
         }

         if (flag && index.getId() != null) {
            indexFileKey = "index_file_read_write:" + index.getId();
            String indexFileReadFlag = (String)this.redisCache.getCacheObject(indexFileKey);
            if (com.emr.common.utils.StringUtils.isNotBlank(indexFileReadFlag) && indexFileReadFlag.equals("1")) {
               flag = false;
               ajaxResult = AjaxResult.error("当前病历有正在保存的操作，不能开启编辑，请稍后再试！");
            }
         }

         if (flag && index.getId() != null) {
            indexAuthKey = "index_file_auth:" + index.getId();
            String indexFileAuthFlag = (String)this.redisCache.getCacheObject(indexAuthKey);
            if (com.emr.common.utils.StringUtils.isNotBlank(indexFileAuthFlag) && indexFileAuthFlag.equals("1")) {
               flag = false;
               ajaxResult = AjaxResult.error("当前病历有正在开启编辑的操作，不能重复开启编辑，请稍后再试！");
            }
         }

         if (flag) {
            this.redisCache.setCacheObject(indexAuthKey, "1", 5, TimeUnit.MINUTES);
            EditState editStateBefore = this.editStateService.selectEditStateLastByEmrId(index.getId());
            this.log.debug("authUpdate-2222222222222222222222222: " + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
            index.setFreeMoveType(indexVo.getFreeMoveType());
            mainFileRefresh = (Boolean)ajaxResult.get("mainFileRefresh");
            ajaxResult = this.indexService.selectIsAuthUpdate(index, subfileIndex, indexVo.getNewSubFileFlag(), indexVo.getMainFileCancelSignFlag(), request, indexVo);
            ajaxResult.put("mainFileRefresh", mainFileRefresh);
            boolean editFlag = Boolean.parseBoolean(ajaxResult.get("editFlag").toString());
            Long mrFileId = subfileIndex == null ? index.getId() : subfileIndex.getId();
            String xmlStr = editFlag ? this.emrBinaryService.selectIndexXmlStrById(mrFileId) : null;
            String editorType = this.sysEmrConfigService.selectSysEmrConfigByKey("0000");
            TempIndexSaveElemVo tempIndexSaveElemVo = null;
            String xmlStrFull = indexVo.getXmlStr();
            if (editFlag && (StringUtils.isBlank(indexVo.getNewSubFileFlag()) || StringUtils.isNotBlank(indexVo.getNewSubFileFlag()) && indexVo.getNewSubFileFlag().equals("0"))) {
               String mrState = subfileIndex == null ? index.getMrState() : subfileIndex.getMrState();
               if (mrState.equals("05") || mrState.equals("07") || mrState.equals("08")) {
                  ajaxResult.put("switchRecension", true);
                  JSONObject userInfo = this.indexService.setUserInfo();
                  ajaxResult.put("UserInfo", userInfo.toJSONString());
               }

               this.log.debug("authUpdate-99999999999999111111: " + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
               List<ElemSign> noSignList = StringUtils.isNotBlank(xmlStr) ? this.indexService.selectEmrNoSignList(index, subfileIndex, xmlStr) : null;
               ajaxResult.put("noSignList", noSignList);
               this.log.debug("authUpdate-99999999999999222222222: " + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
               String patientIdSub = null;
               if (index == null) {
                  PatEventVo patEventVo = new PatEventVo();
                  patEventVo.setId(mrFileId);
                  List<Index> indexList = this.indexService.selectPatIndexList(patEventVo);
                  if (CollectionUtils.isNotEmpty(indexList)) {
                     patientIdSub = ((Index)indexList.get(0)).getPatientId();
                  }
               }

               String patientId = index.getPatientId() != null ? index.getPatientId() : patientIdSub;
               if (StringUtils.isNotBlank(patientId) && StringUtils.isNotBlank(xmlStrFull)) {
                  List<TmplElemLinkVo> tmplElemLinkVoList = this.tmplElemLinkService.selectTmplElemLinkVoByPatientId(patientId);
                  tempIndexSaveElemVo = XmlElementParseUtil.getSaveElemFromXmlAll(xmlStrFull, editorType);
                  if (tempIndexSaveElemVo != null) {
                     List<ElemAttri> elemAttriList = tempIndexSaveElemVo.getElemAttriList();
                     tmplElemLinkVoList = this.tmplElemLinkService.selectTmplElemLinkVoByXmlStr(tmplElemLinkVoList, elemAttriList, indexVo);
                  }

                  ajaxResult.put("tmplElemLinkVoList", tmplElemLinkVoList);
               } else {
                  Long tempId = "MAINFILE".equals(index.getMrType()) ? subfileIndex.getTempId() : index.getTempId();
                  List<TmplElemLinkVo> tmplElemLinkVoList = this.tmplElemLinkService.selectTmplElemLinkVoByTempId(tempId);
                  ajaxResult.put("tmplElemLinkVoList", tmplElemLinkVoList);
               }

               this.log.debug("authUpdate-9999999999999933333333333: " + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
            }

            if (editFlag) {
               EditState editStateCurr = this.editStateService.selectEditStateByEmrId(index.getId());
               editStateBefore = ajaxResult.get("editStateBefore") != null ? (EditState)ajaxResult.get("editStateBefore") : editStateBefore;
               if (editStateBefore != null && editStateBefore.getDeitState().equals("0")) {
                  if (editStateCurr != null && editStateBefore.getIp().equals(editStateCurr.getIp()) && editStateBefore.getEditPersonCd().equals(editStateCurr.getEditPersonCd())) {
                     int minu = DateUtils.getDateMinutes(editStateCurr.getUpdateTime(), editStateBefore.getUpdateTime());
                     if (minu > 5) {
                        ajaxResult.put("mainFileRefresh", true);
                     }
                  } else {
                     ajaxResult.put("mainFileRefresh", true);
                  }
               }
            }

            if (editFlag) {
               tempIndexSaveElemVo = tempIndexSaveElemVo == null ? XmlElementParseUtil.getSaveElemFromXml(xmlStr, editorType) : tempIndexSaveElemVo;
               XmlElementParseConfigVo configVo = new XmlElementParseConfigVo();
               configVo.setEditorType(editorType);
               List<ElemAttriVo> elemAttriList = XmlElementParseUtil.getElemAttriVoListFromXml(xmlStr, configVo);
               Map<String, Object> resMap = this.indexService.selectChangeAlertElems(index.getPatientId(), tempIndexSaveElemVo, elemAttriList, index.getBabyId());
               boolean changeAlertFlag = resMap != null && !resMap.isEmpty();
               ajaxResult.put("changeAlertFlag", changeAlertFlag);
               if (changeAlertFlag) {
                  ajaxResult.put("setStructsTextIdList", resMap.get("setStructsTextIdList"));
                  ajaxResult.put("setStructsTextValueList", resMap.get("setStructsTextValueList"));
                  ajaxResult.put("changeAlertMsg", resMap.get("changeAlertMsg"));
               }

               if (StringUtils.isNotBlank(xmlStrFull)) {
                  List<ElemAttriVo> linkDataList = XmlElementParseUtil.getLinkDataListFromXml(xmlStrFull, configVo);
                  if (CollectionUtils.isNotEmpty(linkDataList)) {
                     ajaxResult.put("linkDataList", linkDataList);
                  }
               }
            }

            if (editFlag) {
               Long tempId = null;
               if ("MAINFILE".equals(index.getMrType())) {
                  if (subfileIndex != null) {
                     tempId = subfileIndex.getTempId();
                  }
               } else {
                  tempId = index.getTempId();
               }

               if (tempId != null && StringUtils.isNotBlank(xmlStrFull)) {
                  TempIndexSaveElemVo indexElemVo = XmlElementParseUtil.getSaveElemFromXmlAll(xmlStrFull, editorType);
                  Map<String, Object> resMap = this.indexService.selectAlertElems(index.getPatientId(), index.getBabyId(), tempId, indexElemVo, (List)null);
                  ajaxResult.put("AllElems", resMap);
               }
            }

            boolean newSubFileFlag = "MAINFILE".equals(index.getMrType()) && StringUtils.isNotBlank(indexVo.getNewSubFileFlag()) && indexVo.getNewSubFileFlag().equals("1");
            if (editFlag && (!"MAINFILE".equals(index.getMrType()) || !newSubFileFlag)) {
               String mrType = "MAINFILE".equals(index.getMrType()) ? subfileIndex.getMrType() : index.getMrType();
               tempIndexSaveElemVo = tempIndexSaveElemVo == null ? XmlElementParseUtil.getSaveElemFromXml(xmlStr, editorType) : tempIndexSaveElemVo;
               List<QuoteElemVo> elemForBase64 = this.quoteElemService.selectFromQuoteElemForBase64(mrType);
               List<Long> elemIdList2 = (List<Long>)(CollectionUtils.isNotEmpty(elemForBase64) ? (List)elemForBase64.stream().map((t) -> Long.valueOf(t.getElemId())).collect(Collectors.toList()) : new ArrayList(1));
               List<QuoteElemVo> tmplQuoteElemBase64List = XmlElementParseUtil.getSysQuoteElem(tempIndexSaveElemVo.getElemAttriList(), elemIdList2);
               List<String> tmplQuoteElemBase64NameList = CollectionUtils.isNotEmpty(tmplQuoteElemBase64List) ? (List)tmplQuoteElemBase64List.stream().map((t) -> t.getContElemName()).collect(Collectors.toList()) : null;
               ajaxResult.put("tmplQuoteElemBase64List", tmplQuoteElemBase64NameList);
            }

            this.log.debug("authUpdate-end1111111111111111111: " + DateUtils.parseDateToStr("yyyy.MM.dd HH:mm:ss.SSS", new Date()));
            this.redisCache.deleteObject(indexAuthKey);
         }
      } catch (Exception e) {
         this.log.error("病历编辑权限判断异常", e);
         ajaxResult = AjaxResult.error("病历编辑权限判断异常");
         if (indexAuthKey != null) {
            this.redisCache.deleteObject(indexAuthKey);
         }
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('emr:index:authUpdate')")
   @PutMapping({"/cancelEdit"})
   public AjaxResult cancelEdit(@RequestBody IndexVo indexVo) {
      AjaxResult ajaxResult = AjaxResult.success();

      try {
         boolean flag = true;
         if (indexVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && indexVo.getId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("病历id不能为空");
         }

         if (flag) {
            this.indexService.emrCancelEdit(indexVo);
         }
      } catch (Exception e) {
         this.log.error("取消编辑异常", e);
         ajaxResult = AjaxResult.error("取消编辑异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('emr:index:authUpdate')")
   @PutMapping({"/closeEdit"})
   public AjaxResult closeEdit(@RequestBody IndexVo indexVo, HttpServletRequest request) {
      AjaxResult ajaxResult = AjaxResult.success("病程记录结束编辑成功，其他医生可以编辑此病历");

      try {
         boolean flag = true;
         if (indexVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && indexVo.getId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("病历id不能为空");
         }

         EditState editState = flag ? this.editStateService.selectEditStateByEmrId(indexVo.getId()) : null;
         String userCode = SecurityUtils.getLoginUser().getUser().getUserName();
         if (StringUtils.isNotBlank(indexVo.getCloseEditIgnoreIp()) && indexVo.getCloseEditIgnoreIp().equals("1")) {
            if (flag && editState != null && StringUtils.isNotBlank(editState.getEditPersonCd()) && !editState.getEditPersonCd().equals(userCode)) {
               flag = false;
               ajaxResult = AjaxResult.error("只能" + editState.getEditPersonName() + "医师才能结束编辑");
            }
         } else {
            String ip = IPAddressUtil.getIPAddress(request);
            if (flag && editState != null) {
               if (StringUtils.isNotBlank(editState.getEditPersonCd()) && editState.getEditPersonCd().equals(userCode) && StringUtils.isNotBlank(editState.getIp()) && editState.getIp().equals(ip)) {
                  flag = true;
               } else {
                  flag = false;
               }
            }
         }

         if (flag) {
            this.indexService.emrCancelEdit(indexVo);
         }
      } catch (Exception e) {
         this.log.error("病程记录结束编辑异常", e);
         ajaxResult = AjaxResult.error("病程记录结束编辑异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('emr:index:getElemMacroList,emr:index:helper')")
   @GetMapping({"/getElemMacroList"})
   public AjaxResult getElemMacroList(IndexVo indexVo) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");
      new ArrayList();
      List<Long> indexTempIdList = new ArrayList(1);

      try {
         boolean flag = true;
         if (indexVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         List<Long> subIndexTempIdList = null;
         if (flag && indexVo.getMainId() != null) {
            List<SubfileIndex> subfileIndexList = this.subfileIndexService.selectSubfileIndexByMainId(indexVo.getMainId());
            subIndexTempIdList = CollectionUtils.isNotEmpty(subfileIndexList) ? (List)subfileIndexList.stream().map((t) -> t.getTempId()).distinct().collect(Collectors.toList()) : subIndexTempIdList;
            if (CollectionUtils.isNotEmpty(subIndexTempIdList)) {
               indexTempIdList.addAll(subIndexTempIdList);
            }

            if (CollectionUtils.isEmpty(subIndexTempIdList)) {
               if (indexVo.getTempId() == null) {
                  flag = false;
                  ajaxResult = AjaxResult.error("模板id不能为空");
               }

               if (flag) {
                  indexTempIdList.add(indexVo.getTempId());
               }
            }
         }

         if (flag && indexVo.getMainId() == null && indexVo.getTempId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("模板id或病程主文件不能为空");
         }

         if (flag && indexVo.getTempId() != null) {
            indexTempIdList.add(indexVo.getTempId());
         }

         if (flag) {
            List list = this.indexService.selectElemMacroListByType(indexTempIdList, indexVo.getMainId());
            ajaxResult.put("rows", list);
         }
      } catch (Exception e) {
         this.log.error("查询宏元素列表列表出现异常, ", e);
         ajaxResult = AjaxResult.error("查询宏元素列表列表出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('emr:index:getQuoteElemList,emr:index:helper')")
   @GetMapping({"/getQuoteElemList"})
   public AjaxResult getQuoteElemList(IndexVo indexVo) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");
      new ArrayList();

      try {
         boolean flag = true;
         if (indexVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && indexVo.getTempId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("模板id不能为空");
         }

         if (flag) {
            TmplIndex tmplIndex = this.tmplIndexService.selectTmplIndexById(indexVo.getTempId());
            if (tmplIndex != null) {
               List list = this.indexService.selectElemQuoteByType(tmplIndex);
               ajaxResult.put("rows", list);
            } else {
               ajaxResult = AjaxResult.error("查询不到模板信息");
            }
         }
      } catch (Exception e) {
         this.log.error("查询自动引用元素列表异常", e);
         ajaxResult = AjaxResult.error("查询自动引用元素列表异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('emr:index:emrElemXmlManage,emrSubfile:index:add')")
   @PostMapping({"/emrElemXmlManage"})
   public AjaxResult emrElemXmlManage(@RequestBody IndexVo indexVo) {
      AjaxResult ajaxResult = new AjaxResult();

      try {
         boolean flag = true;
         if (indexVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         TmplIndex tmplIndex = null;
         if (flag && indexVo.getTempId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("模板id参数不能为空");
         }

         if (flag && indexVo.getTempId() != null) {
            tmplIndex = this.tmplIndexService.selectTmplIndexById(indexVo.getTempId());
            if (tmplIndex == null) {
               flag = false;
               ajaxResult = AjaxResult.error("当前选中模板不存在");
            }
         }

         if (flag && StringUtils.isEmpty(indexVo.getPatientId())) {
            flag = false;
            ajaxResult = AjaxResult.error("患者就诊id不能为空");
         }

         if (flag && StringUtils.isEmpty(indexVo.getXmlStr())) {
            flag = false;
            ajaxResult = AjaxResult.error("病历文件xml字符串不能为空");
         }

         if (flag && indexVo.getSubRecoDate() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("记录日期不能为空");
         }

         if (flag) {
            Map<String, Object> map = this.indexService.emrElemXmlManage(tmplIndex, indexVo.getPatientId(), indexVo.getXmlStr(), indexVo.getSubRecoDate(), indexVo);
            String editorType = this.sysEmrConfigService.selectSysEmrConfigByKey("0000");
            String xmlStr = indexVo.getXmlStr();
            TempIndexSaveElemVo tempIndexSaveElemVo = XmlElementParseUtil.getSaveElemFromXml(xmlStr, editorType);
            Map<String, Object> resMap = this.indexService.selectAlertElems(indexVo.getPatientId(), indexVo.getBabyId(), indexVo.getTempId(), tempIndexSaveElemVo, (List)null);
            map.put("AllElems", resMap);
            ajaxResult = AjaxResult.success((Object)map);
         }
      } catch (Exception e) {
         this.log.error("根据病历文件xml处理元素异常", e);
         ajaxResult = AjaxResult.error("根据病历文件xml处理元素异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('emr:index:getSubIndexList,emr:index:helper')")
   @GetMapping({"/getSubIndexList"})
   public AjaxResult getSubIndexList(IndexVo indexVo) {
      AjaxResult ajaxResult = AjaxResult.success("查询病程记录成功");

      try {
         boolean flag = true;
         if (indexVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (StringUtils.isBlank(indexVo.getPatientId())) {
            flag = false;
            ajaxResult = AjaxResult.error("患者就诊id不能为空");
         }

         if (flag) {
            EmrBinaryVo emrBinaryVo = this.indexService.selectSubfileIndexList(indexVo.getPatientId());
            ajaxResult.put("emrBinaryVo", emrBinaryVo);
         }
      } catch (Exception e) {
         this.log.error("查询病程记录出现异常", e);
         ajaxResult = AjaxResult.error("查询病程记录出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('emr:sign:signList,emr:index:sign')")
   @GetMapping({"/signList"})
   public AjaxResult getSignList(IndexVo indexVo) {
      AjaxResult ajaxResult = new AjaxResult();

      try {
         boolean flag = true;
         if (indexVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         Index index = null;
         if (flag && indexVo.getId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("病历id不能为空");
         }

         if (flag) {
            index = this.indexService.selectIndexById(indexVo.getId());
            if (index == null) {
               flag = false;
               ajaxResult = AjaxResult.error("当前病历文件不存在");
            }
         }

         SubfileIndex subfileIndex = null;
         if (flag && "MAINFILE".equals(index.getMrType())) {
            if (indexVo.getSubFileIndexId() == null) {
               flag = false;
               ajaxResult = AjaxResult.error("病历子文件id不能为空");
            }

            if (flag) {
               subfileIndex = this.subfileIndexService.selectSubfileIndexById(indexVo.getSubFileIndexId());
               if (subfileIndex == null) {
                  flag = false;
                  ajaxResult = AjaxResult.error("当前病历子文件不存在");
               }
            }

            if (flag && StringUtils.isBlank(indexVo.getXmlStr())) {
               flag = false;
               ajaxResult = AjaxResult.error("子病历的xml信息不能为空");
            }
         }

         if (flag) {
            ajaxResult = this.indexService.isCancelSign(index, subfileIndex, indexVo.getXmlStr());
         }
      } catch (Exception e) {
         this.log.error("查询病历已签名元素异常", e);
         ajaxResult = AjaxResult.error("查询病历已签名元素异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('emr:sign:noSignList,emr:index:sign')")
   @PostMapping({"/noSignList"})
   public AjaxResult getNoSignList(@RequestBody IndexVo indexVo) {
      AjaxResult ajaxResult = new AjaxResult();
      SysUser user = SecurityUtils.getLoginUser().getUser();

      try {
         boolean flag = true;
         if (indexVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         Index index = null;
         if (flag && indexVo.getId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("病历id不能为空");
         }

         if (flag) {
            index = this.indexService.selectIndexById(indexVo.getId());
            if (index == null) {
               flag = false;
               ajaxResult = AjaxResult.error("当前病历文件不存在");
            }

            if (index != null && "17".equals(index.getMrType())) {
               TdCaConsApply tdCaConsApply = this.tdCaConsApplyService.selectTdCaConsApplyByMrFileId(indexVo.getId());
               if (tdCaConsApply != null && !user.getUserName().equals(tdCaConsApply.getInvDocCd())) {
                  flag = false;
                  ajaxResult = AjaxResult.error("会诊申请二级审签只能应邀医师签名");
               }
            }
         }

         SubfileIndex subfileIndex = null;
         if (flag && "MAINFILE".equals(index.getMrType())) {
            if (indexVo.getSubFileIndexId() == null) {
               flag = false;
               ajaxResult = AjaxResult.error("病历子文件id不能为空");
            }

            if (flag) {
               subfileIndex = this.subfileIndexService.selectSubfileIndexById(indexVo.getSubFileIndexId());
               if (subfileIndex == null) {
                  flag = false;
                  ajaxResult = AjaxResult.error("当前病历子文件不存在");
               }
            }
         }

         if (flag && StringUtils.isEmpty(indexVo.getXmlStr())) {
            flag = false;
            ajaxResult = AjaxResult.error("病历文件xml字符串不能为空");
         }

         TmplIndex tempIndex = this.tmplIndexService.selectTmplIndexById(index.getTempId());
         boolean isMainFile = this.indexService.isEmrTypeMainFile(tempIndex);
         new ArrayList();
         List emrTaskInfoListZylz;
         if (flag && isMainFile) {
            String subfileIndexMrState = subfileIndex.getMrState();
            EmrTaskInfoVo emrTaskInfoVo = new EmrTaskInfoVo();
            emrTaskInfoVo.setMrFileId(indexVo.getSubFileIndexId());
            emrTaskInfoVo.setDocCd(user.getUserName());
            emrTaskInfoVo.setBusSection("22");
            emrTaskInfoListZylz = this.emrTaskInfoService.selectInfoByOdcsAndIndex(emrTaskInfoVo);
            if (StringUtils.isNotBlank(subfileIndexMrState) && "08".equals(subfileIndexMrState) && (!CollectionUtils.isNotEmpty(emrTaskInfoListZylz) || !"22".equals(((EmrTaskInfo)emrTaskInfoListZylz.get(0)).getBusSection()))) {
               flag = false;
               ajaxResult = AjaxResult.error("病程文件已完成，不能再次签名！");
            }
         } else {
            EmrTaskInfoVo emrTaskInfoVo = new EmrTaskInfoVo();
            emrTaskInfoVo.setMrFileId(indexVo.getId());
            emrTaskInfoVo.setDocCd(user.getUserName());
            emrTaskInfoVo.setBusSection("22");
            emrTaskInfoListZylz = this.emrTaskInfoService.selectInfoByOdcsAndIndex(emrTaskInfoVo);
         }

         if (CollectionUtils.isNotEmpty(emrTaskInfoListZylz)) {
            index.setFreeMoveType("22");
         }

         if (flag && !CollectionUtils.isNotEmpty(emrTaskInfoListZylz)) {
            BasEmployee basEmployee = user.getBasEmployee();
            String titleCode = basEmployee.getTitleCode();
            if (StringUtils.isBlank(titleCode)) {
               flag = false;
               ajaxResult = AjaxResult.error("当前医师的职称为空，不能签名");
            }
         }

         new IndexNoSignListVo();
         EmrTaskInfo targetTask = null;
         if (flag) {
            EmrTaskInfo emrTaskInfo = new EmrTaskInfo();
            emrTaskInfo.setTaskType("3");
            emrTaskInfo.setTreatFlag("0");
            emrTaskInfo.setBusId(subfileIndex != null ? subfileIndex.getId().toString() : index.getId().toString());
            List<EmrTaskInfo> emrTaskInfoList = this.emrTaskInfoService.selectEmrTaskInfoList(emrTaskInfo);
            SysUser sysUser = SecurityUtils.getLoginUser().getUser();
            String currSignUserName = this.indexService.currUserByBJCAs(indexVo.getSignFlag(), indexVo.getSignCertSn()).getUserName();
            if (CollectionUtils.isNotEmpty(emrTaskInfoList) && !((EmrTaskInfo)emrTaskInfoList.get(0)).getDocCd().equals(currSignUserName)) {
               flag = false;
               ajaxResult = AjaxResult.error("当前病历待审签医师是【" + ((EmrTaskInfo)emrTaskInfoList.get(0)).getDocName() + "】！");
            } else {
               targetTask = CollectionUtils.isNotEmpty(emrTaskInfoList) ? (EmrTaskInfo)emrTaskInfoList.get(0) : null;
            }
         }

         if (flag) {
            IndexNoSignListVo var20 = this.indexService.getIndexSignInfo(index, subfileIndex, indexVo.getXmlStr(), indexVo, targetTask);
            if (StringUtils.isNotBlank(var20.getDoctorType()) && var20.getDoctorType().equals("other") && !"22".equals(index.getFreeMoveType())) {
               ajaxResult = AjaxResult.error("当前登录用户不能签名");
            } else {
               ajaxResult = AjaxResult.success((Object)var20);
            }
         }
      } catch (Exception e) {
         this.log.error("查询病历待签名元素异常", e);
         ajaxResult = AjaxResult.error("查询病历待签名元素异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('emr:index:anewQuote,emr:index:helper')")
   @PostMapping({"/anewQuote"})
   public AjaxResult anewQuote(@RequestBody IndexVo indexVo) {
      AjaxResult ajaxResult = new AjaxResult();

      try {
         boolean flag = true;
         if (indexVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && indexVo.getTempId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("模板id不能为空");
         }

         if (flag && StringUtils.isBlank(indexVo.getXmlStr())) {
            flag = false;
            ajaxResult = AjaxResult.error("xml字符串不能为空");
         }

         if (flag && indexVo.getMacroList() == null && indexVo.getQuoteList() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("选中引用元素不能为空");
         }

         TmplIndex tmplIndex = flag ? this.tmplIndexService.selectTmplIndexById(indexVo.getTempId()) : null;
         Boolean isMainFile = flag && tmplIndex != null ? this.indexService.isEmrTypeMainFile(tmplIndex) : null;
         if (flag && isMainFile && CollectionUtils.isNotEmpty(indexVo.getQuoteList()) && StringUtils.isBlank(indexVo.getSubFileXmlStr())) {
            flag = false;
            ajaxResult = AjaxResult.error("病程记录自动引用，正在编辑的子病程xml字符串不能为空");
         }

         if (flag) {
            Map map = this.indexService.selectElemAnewList(indexVo, isMainFile);
            ajaxResult = AjaxResult.success((Object)map);
         }
      } catch (Exception e) {
         this.log.error("重新引用元素出现异常", e);
         ajaxResult = AjaxResult.error("重新引用元素出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('emr:index:uploadFile')")
   @GetMapping({"/uploadFile"})
   public AjaxResult uploadFile(IndexVo indexVo) {
      new AjaxResult();

      AjaxResult ajaxResult;
      try {
         IndexFileVo indexFileVo = this.indexService.insertFile(indexVo);
         Boolean flag = this.indexService.insertFtpFile(indexFileVo, indexVo);
         ajaxResult = AjaxResult.success((Object)flag);
      } catch (Exception e) {
         this.log.error("保存文件出现异常", e);
         ajaxResult = AjaxResult.error("保存文件出现异常");
      }

      return ajaxResult;
   }

   @Log(
      title = "病历文件签名",
      businessType = BusinessType.SIGN
   )
   @PreAuthorize("@ss.hasAnyPermi('emr:index:sign')")
   @PostMapping({"/isEmrSign"})
   public AjaxResult isEmrSign(@RequestBody IndexSignVo indexSignVo, HttpServletRequest request) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         SysUser sysUser = SecurityUtils.getLoginUser().getUser();
         String caFlagStr = this.sysEmrConfigService.selectSysEmrConfigByKey("0001");
         boolean flag = true;
         if (flag && indexSignVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && indexSignVo.getId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("病历文件ID不能为空");
         }

         Index index = null;
         if (flag) {
            index = this.indexService.selectIndexById(indexSignVo.getId());
            if (index == null) {
               flag = false;
               ajaxResult = AjaxResult.error("不存在此病历文件记录,请先保存");
            }
         }

         SubfileIndex subfileIndex = null;
         if (flag && index.getMrType().equals("MAINFILE")) {
            if (indexSignVo.getSubFileIndexId() == null) {
               flag = false;
               ajaxResult = AjaxResult.error("病历子文件ID不能为空");
            }

            if (flag) {
               subfileIndex = this.subfileIndexService.selectSubfileIndexById(indexSignVo.getSubFileIndexId());
               if (subfileIndex == null) {
                  flag = false;
                  ajaxResult = AjaxResult.error("不存在此病历子文件记录");
               }
            }
         }

         if (flag && index != null) {
            EditState editState = this.editStateService.selectEditStateByEmrId(index.getId());
            String ip = IPAddressUtil.getIPAddress(request);
            Long id = indexSignVo.getSubFileIndexId() != null ? indexSignVo.getSubFileIndexId() : indexSignVo.getId();
            SysUser user = SecurityUtils.getLoginUser().getUser();
            EmrTaskInfoVo emrTaskInfoVo = new EmrTaskInfoVo();
            emrTaskInfoVo.setMrFileId(id);
            emrTaskInfoVo.setDocCd(user.getUserName());
            emrTaskInfoVo.setBusSection("22");
            List<EmrTaskInfo> emrTaskInfoList1 = this.emrTaskInfoService.selectInfoByOdcsAndIndex(emrTaskInfoVo);
            if ((emrTaskInfoList1 == null || emrTaskInfoList1.size() <= 0) && editState == null) {
               flag = false;
               ajaxResult = AjaxResult.error("没有启用编辑不能保存");
            }

            if (flag && index.getMrType().equals("MAINFILE") && editState != null && !editState.getIp().equals(ip) && sysUser.getUserName().equals(editState.getEditPersonCd())) {
               flag = false;
               ajaxResult = AjaxResult.error("【" + editState.getEditPersonName() + "】正在编辑该病历，请拷贝新录入内容，重新打开病历后再试！");
            }

            if (flag && !index.getMrType().equals("MAINFILE") && editState != null && (!editState.getIp().equals(ip) || !sysUser.getUserName().equals(editState.getEditPersonCd()))) {
               flag = false;
               ajaxResult = AjaxResult.error("【" + editState.getEditPersonName() + "】正在编辑该病历，请拷贝新录入内容，重新打开病历后再试！");
            }
         }

         SysUser user = SecurityUtils.getLoginUser().getUser();
         if (flag) {
            GrantOutDoctor grantOutDoctor = this.grantOutDoctorService.selectGrantOutDoctorBySubNo(user.getUserName());
            if (grantOutDoctor != null) {
               flag = false;
               ajaxResult = AjaxResult.error("外来医师不能对病历进行签名");
            }
         }

         if (flag) {
            ajaxResult.put("saveFlag", CommonConstants.BOOL_TRUE);
         } else {
            ajaxResult.put("saveFlag", CommonConstants.BOOL_FALSE);
         }
      } catch (Exception e) {
         this.log.error("病历文件签名前判断出现异常：", e);
         ajaxResult = AjaxResult.error("病历文件签名前判断出现异常,请联系管理员。");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('emr:index:sign')")
   @PostMapping({"/signfile"})
   public AjaxResult signIndexFile(@RequestBody IndexSignVo indexSignVo, HttpServletRequest request) {
      AjaxResult ajaxResult = AjaxResult.success("签名成功");
      String indexFileKey = null;

      try {
         String caFlagStr = this.sysEmrConfigService.selectSysEmrConfigByKey("0001");
         Boolean caFlag = caFlagStr.equals("1");
         boolean flag = true;
         if (flag && indexSignVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && indexSignVo.getId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("病历文件ID不能为空");
         }

         Index index = null;
         if (flag) {
            index = this.indexService.selectIndexById(indexSignVo.getId());
            if (index == null) {
               flag = false;
               ajaxResult = AjaxResult.error("不存在此病历文件记录,请先保存");
            }
         }

         SubfileIndex subfileIndex = null;
         if (flag && index.getMrType().equals("MAINFILE")) {
            if (indexSignVo.getSubFileIndexId() == null) {
               flag = false;
               ajaxResult = AjaxResult.error("病历子文件ID不能为空");
            }

            if (flag) {
               subfileIndex = this.subfileIndexService.selectSubfileIndexById(indexSignVo.getSubFileIndexId());
               if (subfileIndex == null) {
                  flag = false;
                  ajaxResult = AjaxResult.error("不存在此病历子文件记录");
               }
            }

            if (StringUtils.isNotEmpty(indexSignVo.getEmrIsUpdate()) && indexSignVo.getEmrIsUpdate().equals("1")) {
               if (flag && (StringUtils.isEmpty(indexSignVo.getSubFileBase64Str()) || StringUtils.isBlank(indexSignVo.getSubFileBase64Str()))) {
                  flag = false;
                  ajaxResult = AjaxResult.error("病历子文件内容不能为空");
               }

               if (flag && (StringUtils.isEmpty(indexSignVo.getXmlStr()) || StringUtils.isBlank(indexSignVo.getXmlStr()))) {
                  flag = false;
                  ajaxResult = AjaxResult.error("病历子文件内容不能为空");
               }
            }
         }

         if (caFlag) {
            if (flag && (StringUtils.isEmpty(indexSignVo.getSignText()) || StringUtils.isBlank(indexSignVo.getSignText()))) {
               flag = false;
               ajaxResult = AjaxResult.error("签名后字符串不能为空");
            }

            if (flag && (StringUtils.isEmpty(indexSignVo.getCertSn()) || StringUtils.isBlank(indexSignVo.getCertSn()))) {
               flag = false;
               ajaxResult = AjaxResult.error("签名证书序列号不能为空");
            }
         }

         SysEmrTypeLevel saveTypeLevel = this.getMrStateBySignConfig(index, subfileIndex, indexSignVo);
         if (flag && StringUtils.isNotBlank(indexSignVo.getType()) && !indexSignVo.getType().equals("1")) {
            if (flag && StringUtils.isEmpty(indexSignVo.getNextDocCd()) && StringUtils.isBlank(indexSignVo.getNextDocCd())) {
               flag = false;
               ajaxResult = AjaxResult.error("签名流转医师编码不能为空");
            }

            if (flag && StringUtils.isEmpty(indexSignVo.getNextDocCd()) && StringUtils.isBlank(indexSignVo.getNextDocCd())) {
               flag = false;
               ajaxResult = AjaxResult.error("签名流转医师编码不能为空");
            }

            if (flag && StringUtils.isEmpty(indexSignVo.getNextDeptCd()) && StringUtils.isBlank(indexSignVo.getNextDeptCd())) {
               flag = false;
               ajaxResult = AjaxResult.error("签名流转医师科室编码不能为空");
            }

            if (flag && StringUtils.isEmpty(indexSignVo.getNextDeptName()) && StringUtils.isBlank(indexSignVo.getNextDeptName())) {
               flag = false;
               ajaxResult = AjaxResult.error("签名流转医师科室名称不能为空");
            }
         }

         if (flag) {
            boolean saveAuthFlag = this.indexService.getIndexSaveAuth(index, subfileIndex, request);
            if (StringUtils.isBlank(indexSignVo.getFreeMoveType()) && !saveAuthFlag) {
               flag = false;
               ajaxResult = AjaxResult.error("没有启用编辑不能保存");
            }
         }

         SysUser user = SecurityUtils.getLoginUser().getUser();
         if (flag) {
            GrantOutDoctor grantOutDoctor = this.grantOutDoctorService.selectGrantOutDoctorBySubNo(user.getUserName());
            if (grantOutDoctor != null) {
               flag = false;
               ajaxResult = AjaxResult.error("外来医师不能对病历进行签名");
            }
         }

         if (flag && saveTypeLevel.getBusSection().equals("1") && StringUtils.isEmpty(indexSignVo.getEmrFileUrl())) {
            flag = false;
            ajaxResult = AjaxResult.error("签名完成文件pdf不能为空");
         }

         EmrTaskInfoVo emrTaskInfoVo = new EmrTaskInfoVo();
         if (indexSignVo.getSubFileIndexId() != null && indexSignVo.getSubFileIndexId() != 0L) {
            emrTaskInfoVo.setMrFileId(indexSignVo.getSubFileIndexId());
         } else {
            emrTaskInfoVo.setMrFileId(indexSignVo.getId());
         }

         emrTaskInfoVo.setDocCd(user.getUserName());
         emrTaskInfoVo.setBusSection("22");
         List<EmrTaskInfo> emrTaskInfoList = this.emrTaskInfoService.selectInfoByOdcsAndIndex(emrTaskInfoVo);
         if ("22".equals(indexSignVo.getFreeMoveType()) && emrTaskInfoList.size() <= 0) {
            flag = false;
            ajaxResult = AjaxResult.error("已签名，不允许重复签名");
         }

         if (flag && index != null) {
            indexFileKey = "index_file_read_write:" + index.getId();
            String indexFileReadFlag = (String)this.redisCache.getCacheObject(indexFileKey);
            if (StringUtils.isNotBlank(indexFileReadFlag) && indexFileReadFlag.equals("1")) {
               flag = false;
               ajaxResult = AjaxResult.error("当前病历有正在保存的操作，不能继续签名保存，请稍后再试！");
               indexFileKey = null;
            } else {
               this.redisCache.setCacheObject(indexFileKey, "1", 10, TimeUnit.MINUTES);
            }
         }

         if (flag) {
            IndexSaveVo indexSaveVo = new IndexSaveVo(indexSignVo);
            String saveType = saveTypeLevel.getMrState();
            indexSignVo.setSaveType(saveType);
            if (StringUtils.isNotEmpty(indexSignVo.getEmrIsUpdate()) && indexSignVo.getEmrIsUpdate().equals("1")) {
               String mrType = subfileIndex != null ? subfileIndex.getMrType() : index.getMrType();
               String xmlStr = subfileIndex != null ? indexSignVo.getSubFileXmlStr() : indexSignVo.getXmlStr();
               if (StringUtils.isBlank(indexSaveVo.getTextStr()) && StringUtils.isBlank(indexSaveVo.getSubFileTextStr())) {
                  indexSaveVo.setSubFileTextStr(indexSignVo.getOldText());
               }

               indexSaveVo.setEmrTypeCode(mrType);
               indexSaveVo.setPatientId(index.getPatientId());
               XmlElementParseConfigVo configVo = this.indexService.getXmlElementParseConfigs();
               List<ElemAttriVo> elemAttriVoList = XmlElementParseUtil.getElemAttriVoListFromXml(xmlStr, configVo);
               indexSaveVo.setSaveType(saveType);
               indexSaveVo.setSignSaveFlag("1");
               indexSaveVo.setEmrFileUrl(indexSignVo.getEmrFileUrl());
               indexSaveVo.setFreeMoveType(indexSignVo.getFreeMoveType());
               IndexSaveReturnVo indexSaveReturnVo = this.indexService.saveIndex(indexSaveVo, index, subfileIndex, elemAttriVoList, request);
               Map<String, String> elemBase64Map = indexSignVo.getElemBase64Map();
               this.indexService.saveIndexElemAsync(index, subfileIndex, elemAttriVoList, indexSaveReturnVo.getUpdateUser(), elemBase64Map);
               this.indexService.saveIndexElemSharingAsync(index, subfileIndex, elemAttriVoList, indexSaveReturnVo.getUpdateUser());
            } else if (StringUtils.isBlank(indexSignVo.getFreeMoveType()) && indexSaveVo.getKeepEdit() != null && !indexSaveVo.getKeepEdit()) {
               this.editStateService.updateEditState(index.getId());
            }

            index = this.indexService.selectIndexById(indexSignVo.getId());
            subfileIndex = subfileIndex != null ? this.subfileIndexService.selectSubfileIndexById(indexSignVo.getSubFileIndexId()) : null;
            new IndexSaveReturnVo();
            IndexSaveReturnVo var29;
            if ("22".equals(indexSignVo.getFreeMoveType())) {
               var29 = this.indexService.signfileForFreeMove(indexSignVo, index, subfileIndex, saveTypeLevel);
               ajaxResult.put("data", "08");
               Long id = indexSignVo.getId() != null ? indexSignVo.getId() : indexSignVo.getSubFileIndexId();
               EditState editState = this.editStateService.selectEditStateByEmrId(id);
               if (editState != null) {
                  this.editStateService.updateEditState(id);
               }
            } else {
               var29 = this.indexService.signIndex(indexSignVo, index, subfileIndex, saveTypeLevel);
               ajaxResult = AjaxResult.success("签名成功", saveType);
               if (var29.getMrState().equals("05") || var29.getMrState().equals("07")) {
                  EmrTaskInfo emrTaskInfo = new EmrTaskInfo();
                  emrTaskInfo.setTaskType("3");
                  emrTaskInfo.setTreatFlag("0");
                  emrTaskInfo.setBusId(subfileIndex != null ? subfileIndex.getId().toString() : index.getId().toString());
                  emrTaskInfoList = this.emrTaskInfoService.selectEmrTaskInfoList(emrTaskInfo);
                  ajaxResult.put("dutyDocName", CollectionUtils.isNotEmpty(emrTaskInfoList) ? ((EmrTaskInfo)emrTaskInfoList.get(0)).getDocName() : null);
                  if (CollectionUtils.isNotEmpty(emrTaskInfoList)) {
                     BasCertInfo basCertInfo = this.basCertInfoService.selectBasCertInfoByEmployeenumber(((EmrTaskInfo)emrTaskInfoList.get(0)).getDocCd());
                     ajaxResult.put("signCertSn", basCertInfo != null ? basCertInfo.getCertSn() : null);
                  }
               }
            }

            String[] staffCode = new String[]{user.getUserName()};
            this.sysUserService.addUseCount(user.getDept().getDeptCode(), staffCode);
            ajaxResult.put("index", var29.getIndex());
            ajaxResult.put("currMenuTreeList", var29.getCurrMenuTreeList());
            ajaxResult.put("subIndexFileList", var29.getSubfileIndexList());
            if (StringUtils.isNotBlank(indexFileKey)) {
               this.redisCache.deleteObject(indexFileKey);
            }
         }
      } catch (Exception e) {
         this.log.error("病历文件签名出现异常：", e);
         ajaxResult = AjaxResult.error("病历文件签名出现异常,请联系管理员。");
         if (StringUtils.isNotBlank(indexFileKey)) {
            this.redisCache.deleteObject(indexFileKey);
         }
      }

      return ajaxResult;
   }

   private SysEmrTypeLevel getMrStateBySignConfig(Index index, SubfileIndex subfileIndex, IndexSignVo indexSignVo) {
      SysEmrTypeLevel sysEmrTypeLevel = new SysEmrTypeLevel();
      TmplIndex tempIndex = this.tmplIndexService.selectTmplIndexById(index.getTempId());
      if (subfileIndex != null) {
         tempIndex = this.tmplIndexService.selectTmplIndexById(subfileIndex.getTempId());
      }

      SysEmrTypeConfigVo sysEmrTypeConfigVo = this.sysEmrTypeConfigService.selectSysEmrTypeConfigByTypeId(tempIndex.getTempType());
      String mrState = index.getMrState();
      String mrType = index.getMrType();
      if (mrType.equals("MAINFILE")) {
         mrState = subfileIndex.getMrState();
      }

      if (sysEmrTypeConfigVo != null) {
         String reviewedLevel = sysEmrTypeConfigVo.getReviewedLevel();
         String secondDocCode = sysEmrTypeConfigVo.getSecondDocCode();
         String thirdDocCode = sysEmrTypeConfigVo.getThirdDocCode();
         BeanUtils.copyProperties(sysEmrTypeConfigVo, sysEmrTypeLevel);
         if (!"1".equals(reviewedLevel) && (!"2".equals(reviewedLevel) || !mrState.equals("05")) && (!"3".equals(reviewedLevel) || !mrState.equals("07"))) {
            if ("2".equals(reviewedLevel)) {
               if (mrState.equals("03")) {
                  sysEmrTypeLevel.setBusSection("2");
                  sysEmrTypeLevel.setBusSectionName("上级审签");
                  sysEmrTypeLevel.setMrState("05");
                  if (StringUtils.isEmpty(secondDocCode)) {
                     sysEmrTypeLevel.setSecondDeptCode(indexSignVo.getNextDeptCd());
                     sysEmrTypeLevel.setSecondDeptName(indexSignVo.getNextDeptName());
                     sysEmrTypeLevel.setSecondDocName(indexSignVo.getNextDocName());
                     sysEmrTypeLevel.setSecondDocCode(indexSignVo.getNextDocCd());
                  }
               } else {
                  sysEmrTypeLevel.setBusSection("1");
                  sysEmrTypeLevel.setBusSectionName("已完成");
                  sysEmrTypeLevel.setMrState("08");
               }
            } else if (mrState.equals("03")) {
               sysEmrTypeLevel.setMrState("05");
               sysEmrTypeLevel.setBusSection("2");
               sysEmrTypeLevel.setBusSectionName("上级审签");
               if (StringUtils.isBlank(secondDocCode)) {
                  sysEmrTypeLevel.setSecondDeptCode(indexSignVo.getNextDeptCd());
                  sysEmrTypeLevel.setSecondDeptName(indexSignVo.getNextDeptName());
                  sysEmrTypeLevel.setSecondDocName(indexSignVo.getNextDocName());
                  sysEmrTypeLevel.setSecondDocCode(indexSignVo.getNextDocCd());
               }
            } else if (mrState.equals("05")) {
               sysEmrTypeLevel.setMrState("07");
               sysEmrTypeLevel.setBusSection("2");
               sysEmrTypeLevel.setBusSectionName("上级审签");
               if (StringUtils.isBlank(thirdDocCode)) {
                  sysEmrTypeLevel.setThirdDeptCode(indexSignVo.getNextDeptCd());
                  sysEmrTypeLevel.setThirdDeptName(indexSignVo.getNextDeptName());
                  sysEmrTypeLevel.setThirdDocName(indexSignVo.getNextDocName());
                  sysEmrTypeLevel.setThirdDocCode(indexSignVo.getNextDocCd());
               }
            } else {
               sysEmrTypeLevel.setMrState("08");
               sysEmrTypeLevel.setBusSection("1");
               sysEmrTypeLevel.setBusSectionName("已完成");
            }
         } else {
            sysEmrTypeLevel.setMrState("08");
            sysEmrTypeLevel.setBusSection("1");
            sysEmrTypeLevel.setBusSectionName("已完成");
         }
      }

      SysUser user = SecurityUtils.getLoginUser().getUser();
      if (StringUtils.isBlank(sysEmrTypeLevel.getSecondDeptCode())) {
         sysEmrTypeLevel.setSecondDeptCode(user.getDept().getDeptCode());
         sysEmrTypeLevel.setSecondDeptName(user.getDept().getDeptName());
      }

      if (StringUtils.isBlank(sysEmrTypeLevel.getThirdDeptCode())) {
         sysEmrTypeLevel.setThirdDeptCode(user.getDept().getDeptCode());
         sysEmrTypeLevel.setThirdDeptName(user.getDept().getDeptName());
      }

      return sysEmrTypeLevel;
   }

   @PreAuthorize("@ss.hasAnyPermi('tmpl:index:addCopy')")
   @PostMapping({"/addCopyMacroList"})
   public AjaxResult getElemMacroListFromXml(String xmlStr) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");
      boolean flag = false;

      try {
         if (flag && StringUtils.isBlank(xmlStr)) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag) {
            List<String> list = this.indexService.getElemMacroListFromXml(xmlStr);
            ajaxResult = AjaxResult.success("查询成功", list);
         }
      } catch (Exception e) {
         this.log.error("病历文件另存为模板时需要清空宏元素中的内容，查询宏元素 contElemName 集合，出现异常：", e);
         ajaxResult = AjaxResult.error("病历文件另存为模板，查询宏元素 contElemName 集合出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('emr:index:smartQuote,emr:index:helper')")
   @PostMapping({"/smartQuote"})
   public AjaxResult smartQuote(@RequestBody IndexVo indexVo) {
      AjaxResult ajaxResult = new AjaxResult();

      try {
         boolean flag = true;
         if (indexVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(indexVo.getPatientId())) {
            flag = false;
            ajaxResult = AjaxResult.error("患者就诊id不能为空");
         }

         if (flag && StringUtils.isBlank(indexVo.getXmlStr())) {
            flag = false;
            ajaxResult = AjaxResult.error("xml字符串不能为空");
         }

         if (flag && indexVo.getQuoteList() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("选中元素不能为空");
         }

         if (flag) {
            Map map = this.indexService.selectElemSmartQuote(indexVo);
            ajaxResult = AjaxResult.success((Object)map);
         }
      } catch (Exception e) {
         this.log.error("重新引用元素出现异常", e);
         ajaxResult = AjaxResult.error("重新引用元素出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('emr:index:mainHead')")
   @PostMapping({"/hfinfo"})
   public AjaxResult getHeaderFooterInfo(@RequestBody IndexHFInfoVo indexHFInfoVo) {
      AjaxResult ajaxResult = AjaxResult.success();
      boolean flag = true;

      try {
         if (flag && indexHFInfoVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isBlank(indexHFInfoVo.getPatientId())) {
            flag = false;
            ajaxResult = AjaxResult.error("患者id不能为空");
         }

         VisitinfoVo visitinfoVo = null;
         if (flag) {
            visitinfoVo = this.visitinfoService.selectVisitinfoById(indexHFInfoVo.getPatientId());
            if (visitinfoVo == null) {
               flag = false;
               ajaxResult = AjaxResult.error("没有此患者记录");
            }
         }

         if (flag && indexHFInfoVo.getId() == null) {
            flag = false;
            ajaxResult = AjaxResult.success();
         }

         Index index = flag ? this.indexService.selectIndexById(indexHFInfoVo.getId()) : null;
         if (flag && index == null) {
            flag = false;
            ajaxResult = AjaxResult.error("没有这个病历记录");
         }

         if (flag && !index.getMrType().equals("MAINFILE")) {
            flag = false;
            ajaxResult = AjaxResult.error("不是病程记录");
         }

         if (flag && (indexHFInfoVo.getSubFileIndexList() == null || indexHFInfoVo.getSubFileIndexList().isEmpty())) {
            flag = false;
            ajaxResult = AjaxResult.error("子病历信息不能为空");
         }

         if (flag) {
            int subFileIndexIdNull = 0;

            for(SubFileIndexHFInfoVo subFileIndexHFInfoVo : indexHFInfoVo.getSubFileIndexList()) {
               if (subFileIndexHFInfoVo.getSubFileIndexId() == null) {
                  ++subFileIndexIdNull;
               }

               if (subFileIndexHFInfoVo.getRecoDate() == null) {
                  flag = false;
                  ajaxResult = AjaxResult.error("子病历的记录日期不能为空");
                  break;
               }

               if (StringUtils.isBlank(subFileIndexHFInfoVo.getRegionName())) {
                  flag = false;
                  ajaxResult = AjaxResult.error("子病历的区域标识不能为空");
                  break;
               }
            }

            if (subFileIndexIdNull > 1) {
               flag = false;
               ajaxResult = AjaxResult.error("子病历的id只允许有一个为空");
            }
         }

         if (flag) {
            List<IndexHFInfoResultVo> resultVoList = this.indexService.getIndexHFInfo(indexHFInfoVo, visitinfoVo);
            ajaxResult = AjaxResult.success((Object)resultVoList);
         }
      } catch (Exception e) {
         this.log.error("病程记录处理页眉页脚出现异常,", e);
         ajaxResult = AjaxResult.error("病程记录处理页眉页脚出现异常,请联系管理员");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('emr:index:mainHead')")
   @PostMapping({"/savehfinfo"})
   public AjaxResult saveHeaderFooterInfo(@RequestBody IndexSaveVo indexSaveVo) {
      AjaxResult ajaxResult = AjaxResult.success();
      boolean flag = true;

      try {
         if (flag && indexSaveVo.getId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("病程记录主文件id不能为空！");
         }

         if (flag && StringUtils.isBlank(indexSaveVo.getBase64Str())) {
            flag = false;
            ajaxResult = AjaxResult.error("病程记录主文件内容不能为空！");
         }

         if (flag && StringUtils.isBlank(indexSaveVo.getXmlStr())) {
            flag = false;
            ajaxResult = AjaxResult.error("病程记录主文件内容不能为空！");
         }

         Index index = flag ? this.indexService.selectIndexById(indexSaveVo.getId()) : null;
         if (flag && index == null) {
            flag = false;
            ajaxResult = AjaxResult.error("没有这个病历文件记录！");
         }

         EmrBinary indexEmrBinary = flag ? this.emrBinaryService.selectEmrBinaryById(indexSaveVo.getId()) : null;
         if (flag && indexEmrBinary == null) {
            flag = false;
            ajaxResult = AjaxResult.error("没有这个病历文件记录！");
         }

         if (flag) {
            EmrBinary emrBinary = new EmrBinary();
            emrBinary.setMrFileId(indexSaveVo.getId());
            String mrCon = this.indexService.getMrCon(indexSaveVo.getBase64Str(), indexSaveVo.getXmlStr(), (String)null, (String)null);
            emrBinary.setMrCon(mrCon);
            this.emrBinaryService.updateEmrBinary(emrBinary);
         }
      } catch (Exception e) {
         ajaxResult = AjaxResult.error("病程记录一键修复页眉保存出现异常，请联系管理员");
         this.log.error("病程记录一键修复页眉保存出现异常：", e);
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('emr:index:unFinishList,emr:index:qcApp')")
   @GetMapping({"/unFinishList"})
   public AjaxResult unFinishList(String patientId) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");
      boolean flag = true;

      try {
         if (flag && StringUtils.isBlank(patientId)) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag) {
            List<IndexVo> list = this.indexService.selectEmrUnFinishList(patientId);
            ajaxResult = AjaxResult.success("查询成功", list);
         }
      } catch (Exception e) {
         this.log.error("未完成病历列表出现异常：", e);
         ajaxResult = AjaxResult.error("未完成病历列表出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('emr:index:unUpdateList,emr:index:qcApp')")
   @GetMapping({"/unUpdateList"})
   public AjaxResult unUpdateList(String patientId) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");
      boolean flag = true;

      try {
         if (flag && StringUtils.isBlank(patientId)) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag) {
            List<EmrQcListVo> list = this.indexService.selectEmrUnUpdateList(patientId);
            ajaxResult = AjaxResult.success("查询成功", list);
         }
      } catch (Exception e) {
         this.log.error("查询缺陷未修改病历出现异常：", e);
         ajaxResult = AjaxResult.error("查询缺陷未修改病历出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('emr:index:checkQcFlaw')")
   @PostMapping({"/checkQcFlaw"})
   public AjaxResult checkQcFlaw(@RequestBody IndexSaveVo indexSaveVo) {
      AjaxResult ajaxResult = AjaxResult.success("检查成功");
      boolean flag = true;

      try {
         if (indexSaveVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isBlank(indexSaveVo.getPatientId())) {
            flag = false;
            ajaxResult = AjaxResult.error("患者id不能为空");
         }

         if (flag && indexSaveVo.getTempId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("模板id不能为空");
         }

         if (flag && (StringUtils.isEmpty(indexSaveVo.getXmlStr()) || StringUtils.isBlank(indexSaveVo.getXmlStr()))) {
            flag = false;
            ajaxResult = AjaxResult.error("病历文件xmlStr不能为空");
         }

         Index index = null;
         if (flag && indexSaveVo.getId() != null) {
            index = this.indexService.selectIndexById(indexSaveVo.getId());
         }

         if (index != null && (index.getMrState().equals("05") || index.getMrState().equals("07") || index.getMrState().equals("08"))) {
            flag = false;
            ajaxResult = AjaxResult.error("病历已签名，不在检查缺陷");
         }

         if (flag) {
            List<EmrQcListVo> qcExcepationList = this.indexService.getQcErrorList(indexSaveVo);
            String xmlStr = index != null && indexSaveVo.getMrType().equals("MAINFILE") ? indexSaveVo.getSubFileXmlStr() : indexSaveVo.getXmlStr();
            XmlElementParseConfigVo configVo = this.indexService.getXmlElementParseConfigs();
            List<ElemAttriVo> elemAttriVoList = XmlElementParseUtil.getElemAttriVoListFromXml(xmlStr, configVo);
            TmplIndex tmplIndex = this.tmplIndexService.selectTmplIndexById(indexSaveVo.getTempId());
            indexSaveVo.setEmrTypeCode(tmplIndex.getTempType());
            List<EmrQcListVo> list = this.indexService.emrQualityCheck(indexSaveVo, elemAttriVoList, qcExcepationList);
            if (list != null && list.size() > 0) {
               qcExcepationList.addAll(list);
            }

            ajaxResult.put("emrQcList", qcExcepationList);
         }
      } catch (Exception e) {
         this.log.error("病历书写缺陷检查按钮出现异常：", e);
         ajaxResult = AjaxResult.error("病历书写缺陷检查按钮出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('emr:index:unSignEmr,emr:home:list')")
   @GetMapping({"/unSignEmr"})
   public TableDataInfo unSignEmr() {
      new TableDataInfo();

      TableDataInfo tableDataInfo;
      try {
         this.startPage();
         List<BackLogVo> list = this.indexService.selectUnSignEmrList((String)null);
         tableDataInfo = this.getDataTable(list);
      } catch (Exception e) {
         this.log.error("待办事项---待签名病历出现异常：", e);
         tableDataInfo = new TableDataInfo(500, "待办事项---待签名病历出现异常");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasAnyPermi('emr:index:editDelFlag')")
   @GetMapping({"/isDel"})
   public AjaxResult isDel(IndexVo indexVo) {
      AjaxResult ajaxResult = AjaxResult.success("可以删除");

      try {
         boolean flag = true;
         if (indexVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         Index index = null;
         if (flag && indexVo.getId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("病历id不能为空");
         }

         if (flag) {
            index = this.indexService.selectIndexById(indexVo.getId());
            if (index == null) {
               flag = false;
               ajaxResult = AjaxResult.error("当前病历文件不存在");
            }
         }

         SubfileIndex subfileIndex = null;
         if (flag && "MAINFILE".equals(index.getMrType())) {
            if (indexVo.getSubFileIndexId() == null) {
               flag = false;
               ajaxResult = AjaxResult.error("病历子文件id不能为空");
            }

            if (flag) {
               subfileIndex = this.subfileIndexService.selectSubfileIndexById(indexVo.getSubFileIndexId());
               if (subfileIndex == null) {
                  flag = false;
                  ajaxResult = AjaxResult.error("当前病历子文件不存在");
               }
            }
         }

         if (flag) {
            String mrState = subfileIndex == null ? index.getMrState() : subfileIndex.getMrState();
            String creCode = subfileIndex == null ? index.getCrePerCode() : subfileIndex.getCrePerCode();
            SysUser sysUser = SecurityUtils.getLoginUser().getUser();
            boolean creCodeFlag = sysUser.getBasEmployee().getEmplNumber().equals(creCode);
            if (!creCodeFlag && ("01".equals(mrState) || "03".equals(mrState))) {
               flag = false;
               ajaxResult = AjaxResult.error("只有此病历的创建人才能删除此病历");
            }
         }

         if (flag) {
            flag = this.indexService.isDel(index, subfileIndex);
            if (flag) {
               if (index.getLockState() != null && CommonConstants.SEALUP_RECORD_STATUS.STATUS_1.equals(index.getLockState())) {
                  flag = false;
                  ajaxResult = AjaxResult.error("当前病历已封存，不可删除");
               }

               EmrQcFlow emrQcFlow = flag ? this.emrQcFlowService.selectEmrQcFlowById(SecurityUtils.getLoginUser().getUser().getHospital().getOrgCode(), index.getPatientId()) : null;
               if (flag && emrQcFlow != null && emrQcFlow.getMrState().equals("16")) {
                  flag = false;
                  ajaxResult = AjaxResult.error("当前病历已归档，不可删除");
               }
            } else {
               flag = false;
               ajaxResult = AjaxResult.error("当前病历不可删除，请创建人申请后再删除");
            }
         }

         ajaxResult.put("delFlag", flag);
      } catch (Exception e) {
         this.log.error("判断病程是否可以删除出现异常：", e);
         ajaxResult = AjaxResult.error("判断病程是否可以删除出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('td:apply:add')")
   @PostMapping({"/addCons"})
   public AjaxResult addCons(@RequestBody IndexVo indexVo) {
      AjaxResult ajaxResult = AjaxResult.success("创建成功");

      try {
         boolean flag = true;
         if (indexVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(indexVo.getPatientId())) {
            flag = false;
            ajaxResult = AjaxResult.error("患者就诊id不能为空");
         }

         String tempId = this.sysEmrConfigService.selectSysEmrConfigByKey("0032");
         TmplIndex tmplIndex = this.tmplIndexService.selectTmplIndexById(Long.parseLong(tempId));
         if (tmplIndex == null) {
            flag = false;
            ajaxResult = AjaxResult.error("请先添加会诊记录模板");
         }

         Date nowDate = this.commonService.getDbSysdate();
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
         String indexName = flag ? tmplIndex.getShowName() + sdf.format(nowDate) : null;
         if (flag) {
            indexVo.setSubRecoDate(nowDate);
            Map<String, Object> map = this.indexService.selectEmrFirstOpenList(tmplIndex, indexVo, nowDate, indexName);
            ajaxResult = AjaxResult.success((Object)map);
            String caFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0001");
            String caType = this.sysEmrConfigService.selectSysEmrConfigByKey("0002");
            ajaxResult.put("caFlag", caFlag);
            ajaxResult.put("caType", caType);
            List<ElemSign> elemSigns = this.elemSignService.selectElemSignByTempId(Long.parseLong(tempId));
            String applyElem = this.sysEmrConfigService.selectSysEmrConfigByKey("0034");
            String consElem = this.sysEmrConfigService.selectSysEmrConfigByKey("0035");

            for(ElemSign elem : elemSigns) {
               if (elem.getContElemName().equals(applyElem)) {
                  ajaxResult.put("applyElem", elem);
               } else if (elem.getContElemName().equals(consElem)) {
                  ajaxResult.put("consElem", elem);
               }
            }

            Map<String, StringBuffer> mapStr = this.indexService.selectPatCondSescString(indexVo.getPatientId(), tmplIndex);
            ajaxResult.put("quoteElems", mapStr);
            if (caFlag.equals("2")) {
               BasCertInfo basCertInfo = this.basCertInfoService.selectBasCertInfoByEmployeenumber(SecurityUtils.getLoginUser().getUser().getBasEmployee().getEmplNumber());
               if (basCertInfo != null) {
                  String certPic = basCertInfo.getCertPic();
                  ajaxResult.put("certPic", certPic);
               }
            }
         }
      } catch (Exception e) {
         this.log.error("创建患者会诊记录病历出现异常", e);
         ajaxResult = AjaxResult.error("创建患者会诊记录病历异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('pat:visitinfo:emrPrintList,pat:visitinfo:emrAllList,qc:flow:term,pat:info:emrAllList,pat:otherinfo:keyPatientsDetail,qc:statis:checkCase,borrowing:borrow:reviewedList,borrowing:borrow:reviewedApplyList,borrowing:borrow:reviewedApprovalList')")
   @GetMapping({"/emrPrint"})
   public AjaxResult emrPrint(IndexVo indexVo) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");
      boolean flag = true;

      try {
         if (indexVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(indexVo.getPatientId())) {
            flag = false;
            ajaxResult = AjaxResult.error("患者就诊id不能为空");
         }

         if (flag) {
            new ArrayList();
            List e;
            if (indexVo.getPrint() != null && indexVo.getPrint()) {
               e = this.indexService.selectPatEmrPrintList(indexVo);
            } else {
               IndexMzVo indexMzVo = this.emrIndexMMenuService.selectIndexMzVoList(indexVo.getPatientId());
               if (indexMzVo != null) {
                  indexVo.setProofNo(indexMzVo.getProofNo());
                  indexVo.setFileId(indexMzVo.getFileId());
                  indexVo.setProofId(indexMzVo.getProofId());
               }

               e = this.emrIndexMMenuService.selectPatEmrPrintList(indexVo);
            }

            ajaxResult = AjaxResult.success((Object)e);
         }
      } catch (Exception e) {
         this.log.error("患者病历树查询出现异常：", e);
         ajaxResult = AjaxResult.error("患者病历树查询出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:rt:check,qc:rt:checked,qc:rt:dept,qc:rt:term')")
   @GetMapping({"/selectUserInfo"})
   public AjaxResult selectUserInfo(IndexVo indexVo) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");
      boolean flag = true;

      try {
         JSONObject userInfo = this.indexService.setUserInfo();
         ajaxResult.put("UserInfo", userInfo.toJSONString());
      } catch (Exception e) {
         this.log.error("查询修订用户基本信息出现异常：", e);
         ajaxResult = AjaxResult.error("查询修订用户基本信息出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:rt:check,qc:rt:checked,qc:rt:dept,qc:rt:term')")
   @PostMapping({"/saveBaseStr"})
   public AjaxResult saveBaseStr(@RequestBody IndexVo indexVo) {
      AjaxResult ajaxResult = AjaxResult.success("保存成功");
      boolean flag = true;
      String indexFileKey = null;

      try {
         if (indexVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && indexVo.getId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("病历id不能为空");
         }

         if (flag && StringUtils.isEmpty(indexVo.getPatientId())) {
            flag = false;
            ajaxResult = AjaxResult.error("患者id不能为空");
         }

         if (flag) {
            indexFileKey = "index_file_read_write:" + indexVo.getId();
            String indexFileReadFlag = (String)this.redisCache.getCacheObject(indexFileKey);
            if (StringUtils.isNotBlank(indexFileReadFlag) && indexFileReadFlag.equals("1")) {
               flag = false;
               ajaxResult = AjaxResult.error("当前病历有正在保存的操作，不能继续保存，请稍后再试！");
               indexFileKey = null;
            } else {
               this.redisCache.setCacheObject(indexFileKey, "1", 10, TimeUnit.MINUTES);
            }
         }

         if (flag) {
            this.indexService.saveBaseStr(indexVo);
            if (StringUtils.isNotBlank(indexFileKey)) {
               this.redisCache.deleteObject(indexFileKey);
            }
         }
      } catch (Exception e) {
         this.log.error("保存base64文件出现异常：", e);
         ajaxResult = AjaxResult.error("保存base64文件出现异常");
         if (StringUtils.isNotBlank(indexFileKey)) {
            this.redisCache.deleteObject(indexFileKey);
         }
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:rt:check,qc:rt:checked,qc:rt:dept,qc:rt:term')")
   @GetMapping({"/emrPdf"})
   public AjaxResult emrPdf(String ips, String fileNames) {
      AjaxResult ajaxResult = AjaxResult.success();

      try {
         this.indexService.filterEmrPdf(ips, fileNames);
      } catch (Exception e) {
         this.log.error("病历pdf文件过滤出现异常：", e);
         ajaxResult = AjaxResult.error("病历pdf文件过滤出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('emr:index:tempsave,emr:index:save,emr:index:updatePdf')")
   @PutMapping({"/saveEmrPdf"})
   public AjaxResult saveEmrPdf(@RequestBody IndexSaveVo indexSaveVo) {
      AjaxResult ajaxResult = AjaxResult.success();
      boolean flag = true;

      try {
         if (indexSaveVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && indexSaveVo.getId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("病历id不能为空");
         }

         if (flag) {
            Index index = this.indexService.selectIndexById(indexSaveVo.getId());
            if (index == null) {
               flag = false;
               ajaxResult = AjaxResult.error("未查询到当前病历文件");
            } else {
               indexSaveVo.setPatientId(index.getPatientId());
            }
         }

         if (flag && StringUtils.isEmpty(indexSaveVo.getEmrFileUrl())) {
            flag = false;
            ajaxResult = AjaxResult.error("pdf文件路径不能为空");
         }

         if (flag) {
            this.indexService.saveEmrPdf(indexSaveVo);
         }
      } catch (Exception e) {
         this.log.error("保存病历pdf文件异常：", e);
         ajaxResult = AjaxResult.error("保存病历pdf文件异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('emr:index:updatePdf')")
   @GetMapping({"/unSavePdfEmr"})
   public AjaxResult unSavePdfEmr() {
      AjaxResult ajaxResult = AjaxResult.success();

      try {
         List<Long> idList = this.indexService.selectUnSavePdfEmr();
         ajaxResult = AjaxResult.success((Object)idList);
      } catch (Exception e) {
         this.log.error("查询未保存pdf的已完成病历异常：", e);
         ajaxResult = AjaxResult.error("查询未保存pdf的已完成病历异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('emr:index:updatePdf')")
   @GetMapping({"/emrBase64"})
   public AjaxResult emrBase64(Long id) {
      AjaxResult ajaxResult = AjaxResult.success();

      try {
         String base64 = this.indexService.selectEmrBase64(id);
         ajaxResult = AjaxResult.success(base64);
      } catch (Exception e) {
         this.log.error("查询病历文件的base64异常：", e);
         ajaxResult = AjaxResult.error("查询病历文件的base64异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('emr:index:add,emrSubfile:index:add')")
   @GetMapping({"/creTime"})
   public AjaxResult getStartTime() {
      AjaxResult ajaxResult = AjaxResult.success();

      try {
         Date startTime = this.commonService.getDbSysdate();
         String startTimeStr = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM, startTime);
         ajaxResult = AjaxResult.success("查询成功", startTimeStr);
         ajaxResult.put("creTimeStr", startTimeStr);
      } catch (Exception e) {
         this.log.error("查询病历记录时间出现异常", e);
         ajaxResult = AjaxResult.error("查询病历记录时间出现异常");
      }

      return ajaxResult;
   }

   @PostMapping({"/queryIndex"})
   public AjaxResult queryIndex(@RequestBody IndexVo indexVo) {
      AjaxResult ajaxResult = AjaxResult.success();
      String patientId = indexVo.getPatientId();
      if (StringUtils.isBlank(patientId)) {
         ajaxResult = AjaxResult.error("患者就诊号不能为空！");
         return ajaxResult;
      } else {
         Long tempId = indexVo.getTempId();
         if (tempId == null) {
            ajaxResult = AjaxResult.error("模板id不能为空！");
            return ajaxResult;
         } else {
            TmplIndex tmplIndex = this.tmplIndexService.selectTmplIndexById(tempId);
            if (tmplIndex == null) {
               ajaxResult = AjaxResult.error("模板不存在！");
               return ajaxResult;
            } else {
               String tempClass = tmplIndex.getTempClass();

               try {
                  HashMap<String, Object> data = new HashMap();
                  if (StringUtils.isNotBlank(tempClass) && "01".equals(tempClass)) {
                     SubfileIndex subfileIndex = this.subfileIndexService.selectIndexByTmplId(patientId, tempId);
                     if (subfileIndex != null) {
                        data.put("indexId", subfileIndex.getId());
                        data.put("mrType", subfileIndex.getMrType());
                        data.put("flag", Boolean.TRUE);
                     } else {
                        data.put("indexId", (Object)null);
                        data.put("mrType", tmplIndex.getTempType());
                        data.put("flag", Boolean.FALSE);
                     }
                  } else {
                     Index index = this.indexService.selectIndexByTmplId(patientId, tempId);
                     if (index != null) {
                        data.put("indexId", index.getId());
                        data.put("mrType", index.getMrType());
                        data.put("flag", Boolean.TRUE);
                     } else {
                        data.put("indexId", (Object)null);
                        data.put("mrType", tmplIndex.getTempType());
                        data.put("flag", Boolean.FALSE);
                     }
                  }

                  ajaxResult.put("data", data);
               } catch (Exception e) {
                  ajaxResult = AjaxResult.error("根据模板id查询病历文件出现异常！");
                  this.log.error("根据模板id查询病历文件出现异常！", e);
               }

               return ajaxResult;
            }
         }
      }
   }

   @PreAuthorize("@ss.hasAnyPermi('emr:index:tempsave,emr:index:save')")
   @PostMapping({"/analysisXmlData"})
   public AjaxResult analysisXmlData(@RequestBody AnalysisXmlDataReq req) {
      AjaxResult ajaxResult = AjaxResult.success();
      if (!StringUtils.isBlank(req.getFromEmrTypeCode()) && !StringUtils.isBlank(req.getFromXmlStr()) && !StringUtils.isBlank(req.getToEmrTypeCode()) && !StringUtils.isBlank(req.getToXmlStr())) {
         try {
            List<ElemAttriVo> elemList = this.indexService.selectElemListByMrFileId(req);
            if (!elemList.isEmpty()) {
               List<String> contElemNameList = (List)elemList.stream().map(ElemAttri::getContElemName).collect(Collectors.toList());
               List<String> contElemTextList = (List)elemList.stream().map(ElemAttriVo::getElemConText).collect(Collectors.toList());
               ajaxResult.put("contElemNameList", contElemNameList);
               ajaxResult.put("contElemTextList", contElemTextList);
            }
         } catch (Exception e) {
            ajaxResult = AjaxResult.error("解析xml 返回评分元素及评分出现异常！请联系管理员！");
            this.log.error("解析xml 返回评分元素及评分出现异常", e);
         }

         return ajaxResult;
      } else {
         ajaxResult = AjaxResult.error("请求参数不能为空！");
         return ajaxResult;
      }
   }

   @PreAuthorize("@ss.hasAnyPermi('emr:index:selectEmrInfo')")
   @GetMapping({"/emrBase64Rd"})
   public AjaxResult queryEmrBase64FromRedis(Long mrFileId) {
      AjaxResult ajaxResult = AjaxResult.success();

      try {
         String base64 = this.indexService.queryEmrBase64FromRedis(mrFileId);
         ajaxResult = AjaxResult.success(base64);
      } catch (Exception e) {
         this.log.error("查询病历文件的base64异常：", e);
         ajaxResult = AjaxResult.error("查询病历文件的base64异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('emr:index:selectEmrInfo')")
   @GetMapping({"/setFirstPrintTime"})
   public AjaxResult setFirstPrintTime(Long mrFileId) {
      AjaxResult ajaxResult = AjaxResult.success();
      boolean flag = true;

      try {
         if (flag && mrFileId == null) {
            flag = false;
            ajaxResult = AjaxResult.error("病历id不能为空");
         }

         Index index = flag ? this.indexService.selectIndexById(mrFileId) : null;
         if (flag && index == null) {
            flag = false;
            ajaxResult = AjaxResult.error("没有这个病历记录");
         }

         if (flag && index.getFirstPrintTime() == null) {
            Index indexU = new Index();
            indexU.setId(mrFileId);
            Date currDate = this.commonService.getDbSysdate();
            indexU.setFirstPrintTime(currDate);
            this.indexService.updateIndex(indexU);
         }
      } catch (Exception e) {
         this.log.error("设置病历首次打印时间出现异常：", e);
         ajaxResult = AjaxResult.error("设置病历首次打印时间出现异常，请联系管理员！");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('emr:index:list')")
   @GetMapping({"/tertiaryIndexList"})
   public TableDataInfo tertiaryIndexList(IndexVo indexVo) {
      TableDataInfo tableDataInfo = new TableDataInfo();
      Boolean flag = true;

      try {
         if (flag && StringUtils.isBlank(indexVo.getPatientId())) {
            tableDataInfo = new TableDataInfo(500, "患者id不能为空");
         }

         if (flag) {
            indexVo.setDelFlag("0");
            this.startPage();
            List<TertiaryIndexResp> list = this.emrElemstoeService.selectTertiaryIndexList(indexVo);
            tableDataInfo = this.getDataTable(list);
         }
      } catch (Exception e) {
         this.log.error("病历助手-三甲指标查询出现异常", e);
         tableDataInfo = new TableDataInfo(500, "病历助手-三甲指标查询出现异常");
      }

      return tableDataInfo;
   }
}
