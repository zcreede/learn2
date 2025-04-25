package com.emr.project.emr.controller;

import com.emr.common.constant.CommonConstants;
import com.emr.common.utils.DateUtils;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.redis.RedisCache;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.common.service.ICommonService;
import com.emr.project.emr.domain.EmrTaskInfo;
import com.emr.project.emr.domain.Index;
import com.emr.project.emr.domain.ModifyAppl;
import com.emr.project.emr.domain.SubfileIndex;
import com.emr.project.emr.domain.SysEmrTypeConfig;
import com.emr.project.emr.domain.vo.EmrTaskInfoVo;
import com.emr.project.emr.domain.vo.IndexVo;
import com.emr.project.emr.domain.vo.ModifyApplVo;
import com.emr.project.emr.domain.vo.SubfileIndexVo;
import com.emr.project.emr.service.IEmrBinaryService;
import com.emr.project.emr.service.IEmrTaskInfoService;
import com.emr.project.emr.service.IIndexService;
import com.emr.project.emr.service.IModifyApplService;
import com.emr.project.emr.service.ISubfileIndexService;
import com.emr.project.emr.service.ISysEmrTypeConfigService;
import com.emr.project.qc.domain.EmrQcFlow;
import com.emr.project.qc.service.IEmrQcFlowService;
import com.emr.project.qc.service.IEmrQcListService;
import com.emr.project.system.service.ISysEmrConfigService;
import com.emr.project.tmpl.domain.TmplIndex;
import com.emr.project.tmpl.service.ITmplIndexService;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/emrSubfile/index"})
public class SubfileIndexController extends BaseController {
   @Autowired
   private ISubfileIndexService subfileIndexService;
   @Autowired
   private IEmrTaskInfoService emrTaskInfoService;
   @Autowired
   public IEmrQcListService emrQcListService;
   @Autowired
   public ICommonService commonService;
   @Autowired
   public IModifyApplService modifyApplService;
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;
   @Autowired
   private IEmrQcFlowService emrQcFlowService;
   @Autowired
   private RedisCache redisCache;
   @Autowired
   public IEmrBinaryService emrBinaryService;
   @Autowired
   public ISysEmrTypeConfigService sysEmrTypeConfigService;
   @Autowired
   private IIndexService indexService;
   @Autowired
   private ITmplIndexService tmplIndexService;

   @PreAuthorize("@ss.hasPermi('emrSubfile:index:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(SubfileIndex subfileIndex) {
      this.startPage();
      List<SubfileIndex> list = this.subfileIndexService.selectSubfileIndexList(subfileIndex);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('emrSubfile:index:export')")
   @Log(
      title = "病程记录索引",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(SubfileIndex subfileIndex) {
      List<SubfileIndex> list = this.subfileIndexService.selectSubfileIndexList(subfileIndex);
      ExcelUtil<SubfileIndex> util = new ExcelUtil(SubfileIndex.class);
      return util.exportExcel(list, "病程记录索引数据");
   }

   @PreAuthorize("@ss.hasPermi('emrSubfile:index:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.subfileIndexService.selectSubfileIndexById(id));
   }

   @PreAuthorize("@ss.hasPermi('emrSubfile:index:add')")
   @Log(
      title = "病程记录索引",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody SubfileIndex subfileIndex) {
      return this.toAjax(this.subfileIndexService.insertSubfileIndex(subfileIndex));
   }

   @PreAuthorize("@ss.hasPermi('emrSubfile:index:edit')")
   @Log(
      title = "病程记录索引",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody SubfileIndex subfileIndex) {
      new AjaxResult();

      AjaxResult ajaxResult;
      try {
         if (subfileIndex.getId() != null) {
            this.subfileIndexService.updateSubfileIndex(subfileIndex);
            ajaxResult = AjaxResult.success("修改成功");
         } else {
            ajaxResult = AjaxResult.error("病程记录id不能为空");
         }
      } catch (Exception e) {
         this.log.error("修改病程记录索引出现异常", e);
         ajaxResult = AjaxResult.error("修改病程记录索引出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('emrSubfile:index:remove')")
   @Log(
      title = "病程记录索引",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.subfileIndexService.deleteSubfileIndexByIds(ids));
   }

   @PreAuthorize("@ss.hasAnyPermi('emrSubfile:index:updateFileName,emrSubfile:index:add')")
   @Log(
      title = "病程记录索引",
      businessType = BusinessType.UPDATE
   )
   @PutMapping({"/updateFileName"})
   public AjaxResult updateFileName(@RequestBody SubfileIndex subfileIndex) {
      new AjaxResult();

      AjaxResult ajaxResult;
      try {
         if (subfileIndex != null && subfileIndex.getId() != null) {
            if (subfileIndex.getMrFileName() != null) {
               this.subfileIndexService.updateSubfileIndex(subfileIndex);
               ajaxResult = AjaxResult.success("更新成功");
            } else {
               ajaxResult = AjaxResult.error("区域id不能为空");
            }
         } else {
            ajaxResult = AjaxResult.error("病程记录id不能为空");
         }
      } catch (Exception e) {
         this.log.error("更新病程记录子文件的区域id出现异常", e);
         ajaxResult = AjaxResult.error("更新病程记录子文件的区域id出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('emrSubfile:index:isAllowInsert,emrSubfile:index:add')")
   @GetMapping({"/isAllowInsert"})
   public AjaxResult isAllowInsert(SubfileIndex subfileIndex) {
      AjaxResult ajaxResult = new AjaxResult();
      boolean flag = true;

      try {
         if (subfileIndex == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && subfileIndex.getMainId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("病历id不能为空");
         }

         if (flag && subfileIndex.getTempId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("模板id不能为空");
         }

         if (flag) {
            SubfileIndexVo subfileIndexVo = this.subfileIndexService.selectIsAllowInsert(subfileIndex);
            if (subfileIndexVo != null) {
               ajaxResult.put("msg", subfileIndexVo.getMsg());
               ajaxResult.put("mergeFlag", subfileIndexVo.getMergeFlag());
               ajaxResult.put("mergeState", subfileIndexVo.getMergeState());
            }
         }
      } catch (Exception e) {
         this.log.error("允许插入病程判断异常", e);
         ajaxResult = AjaxResult.error("允许插入病程判断异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('emrSubfile:index:insertFile,emrSubfile:index:add')")
   @GetMapping({"/insertFile"})
   public AjaxResult insertFile(SubfileIndexVo subfileIndex) {
      AjaxResult ajaxResult = AjaxResult.success();
      boolean flag = true;
      Map<String, Object> map = new HashMap();
      String testExamResultId = subfileIndex.getTestExamResultId();

      try {
         if (subfileIndex == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && subfileIndex.getMainId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("病历id不能为空");
         }

         Index index = null;
         if (flag) {
            index = this.indexService.selectIndexById(subfileIndex.getMainId());
            if (index == null) {
               flag = false;
               ajaxResult = AjaxResult.error("当前病历文件不存在");
            }
         }

         if (index.getLockState() != null && CommonConstants.SEALUP_RECORD_STATUS.STATUS_1.equals(index.getLockState())) {
            flag = false;
            ajaxResult = AjaxResult.error("当前病历已封存，不可插入病程记录");
         }

         EmrQcFlow emrQcFlow = flag ? this.emrQcFlowService.selectEmrQcFlowById(SecurityUtils.getLoginUser().getUser().getHospital().getOrgCode(), index.getPatientId()) : null;
         if (flag && emrQcFlow != null && emrQcFlow.getMrState().equals("16")) {
            flag = false;
            ajaxResult = AjaxResult.error("当前病历已归档，不可插入病程记录");
         }

         if (flag && subfileIndex.getTempId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("所选模板id 不能为空");
         }

         TmplIndex tmplIndex = null;
         if (flag) {
            tmplIndex = this.tmplIndexService.selectTmplIndexById(subfileIndex.getTempId());
            if (tmplIndex == null) {
               flag = false;
               ajaxResult = AjaxResult.error("未查询到当前模板信息");
            }
         }

         if (flag) {
            if (subfileIndex.getRecoDate() == null) {
               flag = false;
               ajaxResult = AjaxResult.error("记录日期不能为空");
            } else {
               Boolean bool = this.indexService.betweenInhosTimeAndNow(index.getPatientId(), subfileIndex.getRecoDate());
               if (!bool) {
                  flag = false;
                  ajaxResult = AjaxResult.error("记录日期必须在患者入院时间和当前时间之间！");
               }
            }
         }

         SysEmrTypeConfig emrTypeConfig = flag ? this.sysEmrTypeConfigService.selectSysEmrTypeConfigByTypeId(tmplIndex.getTempType()) : null;
         if (flag && emrTypeConfig != null && StringUtils.isNotBlank(emrTypeConfig.getLimitWriteFlag()) && emrTypeConfig.getLimitWriteFlag().equals("1") && emrTypeConfig.getLimitWriteNum() != null) {
            List<IndexVo> indexVoList = this.indexService.selectOpeIndexByEmrType(index.getPatientId(), Arrays.asList(tmplIndex.getTempType()));
            if (indexVoList.size() >= emrTypeConfig.getLimitWriteNum()) {
               flag = Boolean.FALSE;
               ajaxResult = AjaxResult.error("病历类型【" + ((IndexVo)indexVoList.get(0)).getEmrTypeName() + "】每个患者只允许书写【" + emrTypeConfig.getLimitWriteNum() + "】个，不允许重复创建！");
            }
         }

         SubfileIndexVo subfileIndexVo = new SubfileIndexVo();
         if (flag) {
            subfileIndexVo = this.subfileIndexService.selectIsAllowInsert(subfileIndex);
            if (subfileIndexVo.getMergeState().equals("FALSE")) {
               flag = false;
            }

            ajaxResult.put("msg", subfileIndexVo.getMsg());
            ajaxResult.put("mergeFlag", subfileIndexVo.getMergeFlag());
            ajaxResult.put("mergeState", subfileIndexVo.getMergeState());
         }

         if (flag) {
            SubfileIndexVo param = new SubfileIndexVo();
            param.setPatientId(index.getPatientId());
            param.setMainId(index.getId());
            List<SubfileIndex> subList = this.subfileIndexService.selectSubfileIndexList(param);
            if (subList != null && !subList.isEmpty()) {
               List<SubfileIndex> dislist = (List)subList.stream().filter((s) -> DateUtils.parseDateToStr("yyyy-MM-dd HH:mm", s.getRecoDate()).equals(DateUtils.parseDateToStr("yyyy-MM-dd HH:mm", subfileIndex.getRecoDate()))).collect(Collectors.toList());
               if (dislist != null && !dislist.isEmpty()) {
                  flag = false;
                  ajaxResult = AjaxResult.error("已有相同记录日期的病程记录，请修改记录日期后重新插入！");
               }
            }
         }

         if (flag) {
            if (subfileIndex.getTaskId() == null) {
               List<EmrTaskInfoVo> taskList = this.emrTaskInfoService.selectPatientNoCreIndexType(subfileIndex.getPatientId(), tmplIndex.getTempType(), (Long)null);
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

               String subFileQcFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0063");
               if (StringUtils.isNotBlank(subFileQcFlag) && subFileQcFlag.equals("1")) {
                  Date currDate = this.commonService.getDbSysdate();
                  Date recoDate = subfileIndex.getRecoDate();
                  String currDateStr = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, currDate);
                  String recoDateStr = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, recoDate);
                  currDate = DateUtils.parseDate(currDateStr, new String[]{DateUtils.YYYY_MM_DD});
                  recoDate = DateUtils.parseDate(recoDateStr, new String[]{DateUtils.YYYY_MM_DD});
                  String recoDateTimeStr = DateUtils.parseDateToStr(DateUtils.YYYYMMDDHHMM, recoDate);
                  if (flag && recoDate.compareTo(currDate) < 0) {
                     ModifyApplVo modifyParam = new ModifyApplVo();
                     modifyParam.setPatientId(subfileIndex.getPatientId());
                     modifyParam.setRecoDate(subfileIndex.getRecoDate());
                     List<ModifyAppl> modifyApplList = this.modifyApplService.selectSubFileAppls(modifyParam);
                     if (CollectionUtils.isEmpty(modifyApplList)) {
                        flag = false;
                        ajaxResult = AjaxResult.error("当前病历类型存在超时病历缺陷，请到文档中心选中对应病历类型的缺陷后再创建此病历！");
                        ajaxResult.put("modifyApplFlag", true);
                        ajaxResult.put("modifyApplMrFileId", recoDateTimeStr);
                     } else if (CollectionUtils.isNotEmpty(modifyApplList)) {
                        List<ModifyAppl> modifyApplList1 = (List)modifyApplList.stream().filter((t) -> t.getConState() == null || StringUtils.isNotBlank(t.getConState()) && t.getConState().equals("0")).collect(Collectors.toList());
                        List<ModifyAppl> modifyApplList3 = (List)modifyApplList.stream().filter((t) -> StringUtils.isNotBlank(t.getConState()) && t.getConState().equals("1")).collect(Collectors.toList());
                        if (CollectionUtils.isNotEmpty(modifyApplList1)) {
                           flag = false;
                           ajaxResult = AjaxResult.error("当前日期病程记录的超时创建申请还未审批或未审批通过，请通过后再创建！");
                        }
                     }
                  }
               }
            } else {
               List<EmrTaskInfoVo> taskList = this.emrTaskInfoService.selectPatientNoCreIndexType(index.getPatientId(), (String)null, subfileIndex.getTaskId());
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

         if (flag) {
            map = this.subfileIndexService.insertFile(index, subfileIndex, tmplIndex, testExamResultId, (String)null);
            map.put("mergeFlag", subfileIndexVo.getMergeFlag());
         }

         ajaxResult.put("data", map);
      } catch (Exception e) {
         this.log.error("插入病程异常", e);
         ajaxResult = AjaxResult.error("插入病程异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('emrSubfile:index:isAllowInsert,emrSubfile:index:add,emrSubfile:index:selectDelFile')")
   @GetMapping({"/selectDelSubFileList"})
   public AjaxResult selectDelSubFileList(Long mainId, String mrFileShowName, Date endDate, Date startDate) {
      AjaxResult ajaxResult = new AjaxResult();
      boolean flag = true;

      try {
         SubfileIndexVo subfileIndex = new SubfileIndexVo();
         subfileIndex.setMainId(mainId);
         subfileIndex.setMrFileShowName(mrFileShowName);
         subfileIndex.setEndDate(endDate);
         subfileIndex.setStartDate(startDate);
         if (subfileIndex == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && subfileIndex.getMainId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("病历主文件id不能为空");
         }

         if (flag) {
            if (subfileIndex.getEndDate() != null && subfileIndex.getStartDate() != null) {
               Calendar c = Calendar.getInstance();
               c.setTime(subfileIndex.getEndDate());
               c.add(5, 1);
               subfileIndex.setEndDate(c.getTime());
            }

            List<IndexVo> fileList = this.subfileIndexService.selectSubFileDelList(subfileIndex);
            ajaxResult = AjaxResult.success((Object)fileList);
         }
      } catch (Exception e) {
         this.log.error("查询患者已删除病程列表异常", e);
         ajaxResult = AjaxResult.error("查询患者已删除病程列表异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('emrSubfile:index:isAllowInsert,emrSubfile:index:add,emrSubfile:index:subfileRegain,emr:index:save')")
   @GetMapping({"/subfileRegain"})
   public AjaxResult subfileRegain(SubfileIndexVo subfileIndex) {
      AjaxResult ajaxResult = AjaxResult.success();
      boolean flag = true;
      String indexFileKey = null;

      try {
         if (subfileIndex == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && subfileIndex.getId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("病程id不能为空");
         }

         if (flag && StringUtils.isEmpty(subfileIndex.getPatientId())) {
            flag = false;
            ajaxResult = AjaxResult.error("患者id不能为空");
         }

         SubfileIndex subfileIndex1 = flag ? this.subfileIndexService.selectSubfileIndexVoById(subfileIndex.getId()) : null;
         if (flag) {
            if (subfileIndex1 == null) {
               flag = false;
               ajaxResult = AjaxResult.error("未查询到病程记录");
            } else {
               String altPreCode = subfileIndex != null && subfileIndex.getAltPerCode() != null ? subfileIndex.getAltPerCode() : subfileIndex1.getAltPerCode();
               if (!altPreCode.equals(SecurityUtils.getUsername())) {
                  flag = false;
                  ajaxResult = AjaxResult.error("不是您删除的病历不可恢复");
               }

               if (flag && subfileIndex1.getDelFlag().equals("0")) {
                  flag = false;
                  ajaxResult = AjaxResult.error("此病历文件已经被恢复了，不能重复恢复");
               }
            }
         }

         Index index = flag ? this.indexService.selectIndexById(subfileIndex1.getMainId()) : null;
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

         SysEmrTypeConfig emrTypeConfig = flag ? this.sysEmrTypeConfigService.selectSysEmrTypeConfigByTypeId(subfileIndex1.getMrType()) : null;
         if (flag && emrTypeConfig != null && StringUtils.isNotBlank(emrTypeConfig.getLimitWriteFlag()) && emrTypeConfig.getLimitWriteFlag().equals("1") && emrTypeConfig.getLimitWriteNum() != null) {
            List<IndexVo> indexVoList = this.indexService.selectOpeIndexByEmrType(subfileIndex.getPatientId(), Arrays.asList(subfileIndex1.getMrType()));
            if (indexVoList.size() >= emrTypeConfig.getLimitWriteNum()) {
               flag = Boolean.FALSE;
               ajaxResult = AjaxResult.error("病历类型【" + ((IndexVo)indexVoList.get(0)).getEmrTypeName() + "】每个患者只允许书写【" + emrTypeConfig.getLimitWriteNum() + "】个，当前病程记录已有此类型病历，不允许再恢复！");
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
            Map<String, Object> map = this.subfileIndexService.selectSubFileRegain(subfileIndex);
            ajaxResult = AjaxResult.success((Object)map);
            if (StringUtils.isNotBlank(indexFileKey)) {
               this.redisCache.deleteObject(indexFileKey);
            }
         }
      } catch (Exception e) {
         this.log.error("病程恢复接口异常", e);
         ajaxResult = AjaxResult.error("病程恢复接口异常");
         if (StringUtils.isNotBlank(indexFileKey)) {
            this.redisCache.deleteObject(indexFileKey);
         }
      }

      return ajaxResult;
   }

   @PostMapping({"/getSubFileInfo"})
   public AjaxResult getSubFileInfo(@RequestBody SubfileIndexVo subfileIndex) {
      AjaxResult ajaxResult = AjaxResult.success();

      try {
         if (subfileIndex == null) {
            ajaxResult = AjaxResult.error("参数不能为空");
            return ajaxResult;
         }

         if (StringUtils.isBlank(subfileIndex.getSubFileXmlStr())) {
            ajaxResult = AjaxResult.error("病程XML文件不能为空");
            return ajaxResult;
         }

         SubfileIndexVo vo = this.subfileIndexService.getSubFileInfo(subfileIndex.getSubFileXmlStr());
         ajaxResult = AjaxResult.success((Object)vo);
      } catch (Exception e) {
         this.log.error("获取子病程 页眉及 宏替换元素等数据接口异常", e);
         ajaxResult = AjaxResult.error("获取子病程 页眉及 宏替换元素等数据接口异常，请联系管理员");
      }

      return ajaxResult;
   }
}
