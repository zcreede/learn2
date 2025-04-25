package com.emr.project.qc.controller;

import com.alibaba.fastjson.JSONObject;
import com.emr.common.utils.DateUtils;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.StringUtils;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.redis.RedisCache;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.emr.domain.Index;
import com.emr.project.emr.domain.SubfileIndex;
import com.emr.project.emr.domain.vo.EmrTaskInfoVo;
import com.emr.project.emr.service.IIndexService;
import com.emr.project.emr.service.ISubfileIndexService;
import com.emr.project.mrhp.domain.MrHp;
import com.emr.project.mrhp.service.IMrHpService;
import com.emr.project.pat.domain.vo.BackLogVo;
import com.emr.project.qc.domain.EmrQcList;
import com.emr.project.qc.domain.EmrQcRecord;
import com.emr.project.qc.domain.QcRuleManuCheck;
import com.emr.project.qc.domain.vo.EmrQcFlowVo;
import com.emr.project.qc.domain.vo.EmrQcListStatisticVo;
import com.emr.project.qc.domain.vo.EmrQcListVo;
import com.emr.project.qc.domain.vo.QcRuleManuCheckVo;
import com.emr.project.qc.service.IEmrQcListService;
import com.emr.project.qc.service.IEmrQcRecordService;
import com.emr.project.qc.service.IQcRuleManuCheckService;
import com.emr.project.qc.service.IQcRulesService;
import com.emr.project.system.domain.SysUser;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.HttpServletResponse;
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
@RequestMapping({"/system/list"})
public class EmrQcListController extends BaseController {
   @Autowired
   private IEmrQcListService emrQcListService;
   @Autowired
   private IIndexService indexService;
   @Autowired
   private ISubfileIndexService subfileIndexService;
   @Autowired
   private IMrHpService mrHpService;
   @Autowired
   private IQcRulesService qcRulesService;
   @Autowired
   private IQcRuleManuCheckService qcRuleManuCheckService;
   @Autowired
   private IEmrQcRecordService qcRecordService;
   @Autowired
   private RedisCache redisCache;

   @PreAuthorize("@ss.hasAnyPermi('system:list:list,qc:rt:check,qc:rt:checked,qc:rt:dept,qc:rt:term,docOrder:list:long,docOrder:list:temp,docOrder:list:decoction')")
   @GetMapping({"/list"})
   public TableDataInfo list(EmrQcListVo emrQcList) {
      this.startPage();
      List<EmrQcListVo> list = null;

      try {
         if (emrQcList != null && StringUtils.isNotBlank(emrQcList.getQcType()) && StringUtils.isBlank(emrQcList.getQcSection())) {
            List<String> qcSectionList = new ArrayList();
            if (emrQcList.getQcType().equals("1")) {
               qcSectionList.add("01");
               qcSectionList.add("04");
            }

            if (emrQcList.getQcType().equals("2")) {
               qcSectionList.add("02");
               qcSectionList.add("03");
               qcSectionList.add("05");
            }

            if (StringUtils.isNotBlank(emrQcList.getQcSection()) && (emrQcList.getQcSection().equals("02") || emrQcList.getQcSection().equals("05"))) {
               emrQcList.setQcSection((String)null);
            }

            emrQcList.setQcSectionList(qcSectionList);
         }

         list = this.emrQcListService.selectEmrQcListList(emrQcList);
      } catch (Exception e) {
         this.log.error("查询病历缺陷明细列表出现异常:", e);
      }

      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasAnyPermi('system:list:list,qc:rt:check,qc:rt:checked,qc:rt:dept,qc:rt:term,docOrder:list:long,docOrder:list:temp,docOrder:list:decoction')")
   @GetMapping({"/selectEmrQcListById"})
   public AjaxResult selectEmrQcListById(EmrQcListVo emrQcList) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");
      Boolean flag = true;
      if (emrQcList == null) {
         flag = false;
         ajaxResult = AjaxResult.error("查询参数不能为空");
      }

      if (flag && emrQcList.getId() == null) {
         flag = false;
         ajaxResult = AjaxResult.error("病历缺陷id不能为空");
      }

      if (flag) {
         try {
            EmrQcListVo emrQcListVoRes = this.emrQcListService.selectEmrQcListById(emrQcList);
            ajaxResult.put("data", emrQcListVoRes);
         } catch (Exception e) {
            this.log.error("查询病历缺陷明细列表出现异常:", e);
         }
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('system:list:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.emrQcListService.selectEmrQcListById(id));
   }

   @PreAuthorize("@ss.hasAnyPermi('system:list:addone,qc:rt:check,qc:rt:dept,qc:rt:term')")
   @Log(
      title = "缺失病历反馈新增",
      businessType = BusinessType.INSERT
   )
   @PostMapping({"/addOne"})
   public AjaxResult addOne(@RequestBody EmrQcListVo emrQcListVo) {
      AjaxResult ajaxResult = AjaxResult.success("保存成功");
      boolean flag = true;

      try {
         if (flag && emrQcListVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (emrQcListVo.getMainId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("质控记录id不能为空");
         }

         if (StringUtils.isBlank(emrQcListVo.getPatientId())) {
            flag = false;
            ajaxResult = AjaxResult.error("：患者id不能为空");
         }

         if (emrQcListVo.getRuleId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("规则id不能为空");
         }

         if (StringUtils.isBlank(emrQcListVo.getRuleName())) {
            flag = false;
            ajaxResult = AjaxResult.error("规则名称不能为空");
         }

         if (StringUtils.isBlank(emrQcListVo.getFlawDesc())) {
            flag = false;
            ajaxResult = AjaxResult.error("缺陷描述不能为空");
         }

         if (StringUtils.isBlank(emrQcListVo.getDefeLevel())) {
            flag = false;
            ajaxResult = AjaxResult.error("缺陷严重程度不能为空");
         }

         if (StringUtils.isBlank(emrQcListVo.getQcSection())) {
            flag = false;
            ajaxResult = AjaxResult.error("质控环节不能为空");
         }

         if (StringUtils.isBlank(emrQcListVo.getMrType())) {
            flag = false;
            ajaxResult = AjaxResult.error("病历类型不能为空");
         }

         if (StringUtils.isBlank(emrQcListVo.getMrTypeName())) {
            flag = false;
            ajaxResult = AjaxResult.error("病历类型名称不能为空");
         }

         if (flag) {
            this.emrQcListService.insertEmrQcList(emrQcListVo);
         }
      } catch (Exception e) {
         this.log.error("新增病历缺陷明细出现异常：", e);
         ajaxResult = AjaxResult.error("新增病历缺陷明细出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('system:list:addone,system:list:list,qc:rt:check,qc:rt:checked,qc:rt:dept,qc:rt:term,docOrder:list:long,docOrder:list:temp,docOrder:list:decoction')")
   @Log(
      title = "缺失病历反馈新增",
      businessType = BusinessType.INSERT
   )
   @PostMapping({"/insertEmrQcListForZk"})
   public AjaxResult insertEmrQcListForZk(@RequestBody EmrQcListVo emrQcListVo) {
      AjaxResult ajaxResult = AjaxResult.success("保存成功");
      boolean flag = true;

      try {
         if (flag && emrQcListVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (emrQcListVo.getMainId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("质控记录id不能为空");
         }

         if (StringUtils.isBlank(emrQcListVo.getPatientId())) {
            flag = false;
            ajaxResult = AjaxResult.error("：患者id不能为空");
         }

         if (emrQcListVo.getRuleId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("规则id不能为空");
         }

         if (StringUtils.isBlank(emrQcListVo.getRuleName())) {
            flag = false;
            ajaxResult = AjaxResult.error("规则名称不能为空");
         }

         if (StringUtils.isBlank(emrQcListVo.getFlawDesc())) {
            flag = false;
            ajaxResult = AjaxResult.error("缺陷描述不能为空");
         }

         if (StringUtils.isBlank(emrQcListVo.getDefeLevel())) {
            flag = false;
            ajaxResult = AjaxResult.error("缺陷严重程度不能为空");
         }

         if (StringUtils.isBlank(emrQcListVo.getQcSection())) {
            flag = false;
            ajaxResult = AjaxResult.error("质控环节不能为空");
         }

         if (StringUtils.isBlank(emrQcListVo.getMrType())) {
            flag = false;
            ajaxResult = AjaxResult.error("病历类型不能为空");
         }

         if (StringUtils.isBlank(emrQcListVo.getMrTypeName())) {
            flag = false;
            ajaxResult = AjaxResult.error("病历类型名称不能为空");
         }

         if (emrQcListVo.getDedScoreSingle() != null && emrQcListVo.getDedScoreSingle() > (double)0.0F) {
            if (StringUtils.isBlank(emrQcListVo.getItemCd())) {
               flag = false;
               ajaxResult = AjaxResult.error("评分归类不能为空");
            }

            if (StringUtils.isBlank(emrQcListVo.getDedType())) {
               flag = false;
               ajaxResult = AjaxResult.error("评分标准不能为空");
            }
         }

         if (flag) {
            this.emrQcListService.insertEmrQcListForZk(emrQcListVo);
         }
      } catch (Exception e) {
         this.log.error("新增病历缺陷明细出现异常：", e);
         ajaxResult = AjaxResult.error("新增病历缺陷明细出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('system:list:addone,system:list:list,qc:rt:check,qc:rt:checked,qc:rt:dept,qc:rt:term,docOrder:list:long,docOrder:list:temp,docOrder:list:decoction')")
   @Log(
      title = "缺失病历反馈新增",
      businessType = BusinessType.INSERT
   )
   @PostMapping({"/updateEmrQcListForZk"})
   public AjaxResult updateEmrQcListForZk(@RequestBody EmrQcListVo emrQcListVo) {
      AjaxResult ajaxResult = AjaxResult.success("保存成功");
      boolean flag = true;

      try {
         if (flag && emrQcListVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (emrQcListVo.getId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("缺陷明细id不能为空");
         }

         if (emrQcListVo.getMainId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("质控记录id不能为空");
         }

         if (StringUtils.isBlank(emrQcListVo.getPatientId())) {
            flag = false;
            ajaxResult = AjaxResult.error("：患者id不能为空");
         }

         if (emrQcListVo.getRuleId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("规则id不能为空");
         }

         if (StringUtils.isBlank(emrQcListVo.getRuleName())) {
            flag = false;
            ajaxResult = AjaxResult.error("规则名称不能为空");
         }

         if (StringUtils.isBlank(emrQcListVo.getFlawDesc())) {
            flag = false;
            ajaxResult = AjaxResult.error("缺陷描述不能为空");
         }

         if (StringUtils.isBlank(emrQcListVo.getDefeLevel())) {
            flag = false;
            ajaxResult = AjaxResult.error("缺陷严重程度不能为空");
         }

         if (StringUtils.isBlank(emrQcListVo.getQcSection())) {
            flag = false;
            ajaxResult = AjaxResult.error("质控环节不能为空");
         }

         if (StringUtils.isBlank(emrQcListVo.getMrType())) {
            flag = false;
            ajaxResult = AjaxResult.error("病历类型不能为空");
         }

         if (StringUtils.isBlank(emrQcListVo.getMrTypeName())) {
            flag = false;
            ajaxResult = AjaxResult.error("病历类型名称不能为空");
         }

         if (emrQcListVo.getDedScoreSingle() != null && emrQcListVo.getDedScoreSingle() > (double)0.0F) {
            if (StringUtils.isBlank(emrQcListVo.getItemCd())) {
               flag = false;
               ajaxResult = AjaxResult.error("评分归类不能为空");
            }

            if (StringUtils.isBlank(emrQcListVo.getDedType())) {
               flag = false;
               ajaxResult = AjaxResult.error("评分标准不能为空");
            }
         }

         if (flag) {
            this.emrQcListService.updateEmrQcListForZk(emrQcListVo);
         }
      } catch (Exception e) {
         this.log.error("更新病历缺陷明细出现异常：", e);
         ajaxResult = AjaxResult.error("更新病历缺陷明细出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('system:list:addone,system:list:list,qc:rt:check,qc:rt:checked,qc:rt:dept,qc:rt:term,docOrder:list:long,docOrder:list:temp,docOrder:list:decoction')")
   @DeleteMapping({"/deleteEmrQcListByIdForZk"})
   public AjaxResult deleteEmrQcListByIdForZk(@RequestBody EmrQcListVo emrQcListVo) {
      AjaxResult ajaxResult = AjaxResult.success("删除成功");
      boolean flag = true;

      try {
         if (flag && emrQcListVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (emrQcListVo.getId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("缺陷明细id不能为空");
         }

         if (emrQcListVo.getId() != null) {
            EmrQcList emrQcList = this.emrQcListService.selectEmrQcListById(emrQcListVo.getId());
            if (emrQcList != null && !"0".equals(emrQcList.getQcState())) {
               flag = false;
               ajaxResult = AjaxResult.error("只有未修改的缺陷才能删除");
            }
         }

         if (flag) {
            this.emrQcListService.deleteEmrQcListByIdForZk(emrQcListVo);
         }
      } catch (Exception e) {
         this.log.error("删除病历缺陷明细出现异常：", e);
         ajaxResult = AjaxResult.error("删除病历缺陷明细出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('system:list:add,qc:rt:check,qc:rt:dept,qc:rt:term')")
   @Log(
      title = "病历缺陷明细",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody List emrQcListList) {
      AjaxResult ajaxResult = AjaxResult.success("保存成功");
      boolean flag = true;

      try {
         if (flag && (emrQcListList == null || emrQcListList != null && emrQcListList.isEmpty())) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag) {
            for(EmrQcListVo qcList : emrQcListList) {
               String flawDesc = qcList.getFlawDesc();
               if (qcList.getMainId() == null) {
                  flag = false;
                  ajaxResult = AjaxResult.error(flawDesc + "：质控id不能为空");
                  break;
               }

               if (StringUtils.isBlank(qcList.getPatientId())) {
                  flag = false;
                  ajaxResult = AjaxResult.error(flawDesc + "：患者id不能为空");
                  break;
               }

               if (qcList.getRuleId() == null) {
                  flag = false;
                  ajaxResult = AjaxResult.error(flawDesc + "：规则id不能为空");
                  break;
               }

               if (StringUtils.isBlank(qcList.getMrFileId())) {
                  flag = false;
                  ajaxResult = AjaxResult.error(flawDesc + "：病历id不能为空");
                  break;
               }

               if (StringUtils.isBlank(qcList.getRuleName())) {
                  flag = false;
                  ajaxResult = AjaxResult.error(flawDesc + "：规则名称不能为空");
                  break;
               }

               if (StringUtils.isBlank(qcList.getFlawDesc())) {
                  flag = false;
                  ajaxResult = AjaxResult.error(flawDesc + "：缺陷描述不能为空");
                  break;
               }

               if (StringUtils.isBlank(qcList.getEmrEleId())) {
                  flag = false;
                  ajaxResult = AjaxResult.error(flawDesc + "：病历元素不能为空");
                  break;
               }

               if (StringUtils.isBlank(qcList.getEmrEleName())) {
                  flag = false;
                  ajaxResult = AjaxResult.error(flawDesc + "：病历元素名称不能为空");
                  break;
               }

               if (StringUtils.isBlank(qcList.getDefeLevel())) {
                  flag = false;
                  ajaxResult = AjaxResult.error(flawDesc + "：缺陷严重程度不能为空");
                  break;
               }

               if (StringUtils.isBlank(qcList.getQcSection())) {
                  flag = false;
                  ajaxResult = AjaxResult.error(flawDesc + "：质控环节不能为空");
                  break;
               }
            }
         }

         SubfileIndex subfileIndex = null;
         MrHp mrHp = null;
         Index index = null;
         if (flag) {
            String indexId = ((EmrQcListVo)emrQcListList.get(0)).getMrFileId();
            index = this.indexService.selectIndexById(Long.parseLong(indexId));
            if (index == null) {
               subfileIndex = this.subfileIndexService.selectSubfileIndexById(Long.parseLong(indexId));
            }

            if (index == null && subfileIndex == null) {
               mrHp = this.mrHpService.selectMrHpById(indexId);
            }

            if (index == null && subfileIndex == null && mrHp == null) {
               flag = false;
               ajaxResult = AjaxResult.error("没有此病历文件记录");
            }
         }

         if (flag) {
            Long mainId = ((EmrQcListVo)emrQcListList.get(0)).getMainId();
            EmrQcRecord qcRecord = this.qcRecordService.selectEmrQcRecordById(mainId);
            if (qcRecord == null) {
               flag = false;
               ajaxResult = AjaxResult.error("没有此质控记录");
            }

            if (flag && (qcRecord.getState().equals("2") || qcRecord.getState().equals("3"))) {
               flag = false;
               ajaxResult = AjaxResult.error("当前质控已完成或已退回，不能再新增缺陷");
            }
         }

         if (flag) {
            this.emrQcListService.addQcDoctEmrQcLists(emrQcListList, mrHp, index, subfileIndex);
         }
      } catch (Exception e) {
         this.log.error("新增病历缺陷明细出现异常：", e);
         ajaxResult = AjaxResult.error("新增病历缺陷明细出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('system:list:add,qc:rt:checked,qc:rt:check')")
   @Log(
      title = "抽查新增超时病历缺陷",
      businessType = BusinessType.INSERT
   )
   @PostMapping({"/addOvertimeUnWriteList"})
   public AjaxResult addByOvertimeUnWriteList(@RequestBody List list) {
      AjaxResult ajaxResult = AjaxResult.success("保存成功");
      boolean flag = true;

      try {
         if (flag && list != null && !list.isEmpty()) {
            for(EmrTaskInfoVo e : list) {
               if (e.getId() == null) {
                  flag = false;
                  ajaxResult = AjaxResult.error("参数不能为空");
                  break;
               }
            }
         }

         if (flag && list != null && !list.isEmpty()) {
            this.emrQcListService.insertListsByOvertimeUnWrite(list);
         }
      } catch (Exception e) {
         this.log.error("抽查新增超时病历缺陷出现异常：", e);
         ajaxResult = AjaxResult.error("抽查新增超时病历缺陷出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('system:list:remove')")
   @Log(
      title = "病历缺陷明细",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.emrQcListService.deleteEmrQcListByIds(ids));
   }

   @PreAuthorize("@ss.hasAnyPermi('system:list:list,qc:rt:check,qc:rt:checked,qc:rt:dept,qc:rt:term')")
   @Log(
      title = "根据患者的质控环节查询质控医师列表",
      businessType = BusinessType.UPDATE
   )
   @GetMapping({"/qcDoctByPS"})
   public AjaxResult getQcDoctByPatientSection(EmrQcList emrQcList) {
      AjaxResult ajaxResult = AjaxResult.success();
      boolean flag = true;

      try {
         if (flag && emrQcList == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isBlank(emrQcList.getPatientId())) {
            flag = false;
            ajaxResult = AjaxResult.error("患者id不能为空");
         }

         if (flag) {
            List<EmrQcList> list = this.emrQcListService.selectQcDoctByPatientSection(emrQcList);
            ajaxResult = AjaxResult.success((Object)list);
         }
      } catch (Exception e) {
         this.log.error("根据患者的质控环节查询质控医师列表出现异常：", e);
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('system:list:changeState,qc:rt:check,qc:rt:checked,emr:index:save,qc:rt:dept,qc:rt:term,emr:index:list')")
   @Log(
      title = "缺陷状态修改",
      businessType = BusinessType.UPDATE
   )
   @PostMapping({"/changeState"})
   public AjaxResult changeState(Long id, String qcState) {
      AjaxResult ajaxResult = AjaxResult.success();
      boolean flag = true;

      try {
         if (flag && id == null) {
            flag = false;
            ajaxResult = AjaxResult.error("缺陷id不能为空");
         }

         if (flag && StringUtils.isBlank(qcState)) {
            flag = false;
            ajaxResult = AjaxResult.error("缺陷状态不能为空");
         }

         EmrQcList emrQcList = flag ? this.emrQcListService.selectEmrQcListById(id) : null;
         if (flag && emrQcList == null) {
            flag = false;
            ajaxResult = AjaxResult.error("没有这个缺陷记录");
         }

         if (flag) {
            SysUser user = SecurityUtils.getLoginUser().getUser();
            EmrQcList param = new EmrQcList();
            param.setId(id);
            param.setQcState(qcState);
            param.setMissingFileFlag(emrQcList.getMissingFileFlag());
            param.setMrFileId(emrQcList.getMrFileId());
            param.setPatientId(emrQcList.getPatientId());
            param.setQcSection(emrQcList.getQcSection());
            this.emrQcListService.updateEmrQcList(param, emrQcList.getMainId(), user);
         }
      } catch (Exception e) {
         this.log.error("缺陷状态修改出现异常：", e);
         ajaxResult = AjaxResult.error("缺陷状态修改出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('system:list:changeState,qc:rt:check,qc:rt:checked,emr:index:save,qc:rt:dept,qc:rt:term,emr:index:list')")
   @Log(
      title = "缺陷状态修改",
      businessType = BusinessType.UPDATE
   )
   @PostMapping({"/updateEmrQcListById"})
   public AjaxResult updateEmrQcListById(@RequestBody EmrQcList emrQcList) {
      AjaxResult ajaxResult = AjaxResult.success();
      boolean flag = true;

      try {
         if (flag && emrQcList == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && emrQcList.getId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("缺陷id不能为空");
         }

         if (flag && StringUtils.isBlank(emrQcList.getQcState())) {
            flag = false;
            ajaxResult = AjaxResult.error("缺陷状态不能为空");
         }

         EmrQcList emrQcList1 = flag ? this.emrQcListService.selectEmrQcListById(emrQcList.getId()) : null;
         if (flag && emrQcList1 == null) {
            flag = false;
            ajaxResult = AjaxResult.error("没有这个缺陷记录");
         }

         if (flag) {
            this.emrQcListService.updateEmrQcListById(emrQcList);
         }
      } catch (Exception e) {
         this.log.error("缺陷状态修改出现异常：", e);
         ajaxResult = AjaxResult.error("缺陷状态修改出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('system:list:rules,qc:rt:check,qc:rt:checked,qc:rt:dept,qc:rt:term')")
   @GetMapping({"/rules"})
   public AjaxResult getRulesByMrTypeElem(String indexId, String elemIds) {
      AjaxResult ajaxResult = AjaxResult.success();
      boolean flag = true;

      try {
         if (flag && StringUtils.isBlank(indexId)) {
            flag = false;
            ajaxResult = AjaxResult.error("病历文件id不能为空");
         }

         if (flag && StringUtils.isBlank(elemIds)) {
            flag = false;
            ajaxResult = AjaxResult.error("元素id集合不能为空");
         }

         SubfileIndex subfileIndex = null;
         MrHp mrHp = null;
         Index index = null;
         if (flag) {
            index = this.indexService.selectIndexById(Long.parseLong(indexId));
            if (index == null) {
               subfileIndex = this.subfileIndexService.selectSubfileIndexById(Long.parseLong(indexId));
            }

            if (index == null && subfileIndex == null) {
               mrHp = this.mrHpService.selectMrHpById(indexId);
            }

            if (index == null && subfileIndex == null && mrHp == null) {
               flag = false;
               ajaxResult = AjaxResult.error("没有此病历文件记录");
            }
         }

         if (flag) {
            String[] elemIdArr = elemIds.split(",");
            List<String> elemIdList = Arrays.asList(elemIdArr);
            List<QcRuleManuCheck> qcRules = this.qcRuleManuCheckService.getRulesByMrTypeElem(elemIdList, index, subfileIndex, mrHp);
            ajaxResult = AjaxResult.success((Object)qcRules);
         }
      } catch (Exception e) {
         this.log.error("根据病历类型、元素查询规则集合出现异常：", e);
         ajaxResult = AjaxResult.error("根据病历类型、元素查询规则集合出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('system:list:orderListrules,qc:rt:check,qc:rt:checked,qc:rt:dept,qc:rt:term')")
   @GetMapping({"/orderListrules"})
   public AjaxResult getOrderListRules() {
      AjaxResult ajaxResult = AjaxResult.success();

      try {
         QcRuleManuCheckVo param = new QcRuleManuCheckVo();
         param.setEmrTypeCode("64");
         List<QcRuleManuCheck> resList = this.qcRuleManuCheckService.getOrderListRules(param);
         ajaxResult = AjaxResult.success("查询成功", resList);
      } catch (Exception e) {
         ajaxResult = AjaxResult.error("查询医嘱本的质控规则出现异常");
         this.log.error("查询医嘱本的质控规则出现异常,", e);
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('emr:home:list')")
   @GetMapping({"/unUpdateEmr"})
   public TableDataInfo unUpdateEmr() {
      new TableDataInfo();

      TableDataInfo tableDataInfo;
      try {
         this.startPage();
         List<BackLogVo> list = this.emrQcListService.selectUnUpdateEmrList((String)null);
         tableDataInfo = this.getDataTable(list);
      } catch (Exception e) {
         this.log.error("待修改病历---待办事项出现异常：", e);
         tableDataInfo = new TableDataInfo(500, "待修改病历---待办事项出现异常");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasAnyPermi('system:list:add,qc:rt:check,qc:rt:dept,qc:rt:term')")
   @Log(
      title = "病历缺陷明细",
      businessType = BusinessType.INSERT
   )
   @PostMapping({"/orderQcSave"})
   public AjaxResult orderQcSave(@RequestBody EmrQcListVo emrQcList) {
      AjaxResult ajaxResult = AjaxResult.success("保存成功");
      boolean flag = true;

      try {
         if (flag && emrQcList == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag) {
            String flawDesc = emrQcList.getFlawDesc();
            if (emrQcList.getMainId() == null) {
               flag = false;
               ajaxResult = AjaxResult.error(flawDesc + "：质控id不能为空");
            }

            if (StringUtils.isBlank(emrQcList.getPatientId())) {
               flag = false;
               ajaxResult = AjaxResult.error(flawDesc + "：患者id不能为空");
            }

            if (emrQcList.getRuleId() == null) {
               flag = false;
               ajaxResult = AjaxResult.error(flawDesc + "：规则id不能为空");
            }

            if (StringUtils.isBlank(emrQcList.getMrFileId())) {
               flag = false;
               ajaxResult = AjaxResult.error(flawDesc + "：医嘱单类型不能为空");
            }

            if (StringUtils.isBlank(emrQcList.getRuleName())) {
               flag = false;
               ajaxResult = AjaxResult.error(flawDesc + "：规则名称不能为空");
            }

            if (StringUtils.isBlank(emrQcList.getFlawDesc())) {
               flag = false;
               ajaxResult = AjaxResult.error(flawDesc + "：缺陷描述不能为空");
            }

            if (StringUtils.isBlank(emrQcList.getEmrEleId())) {
               flag = false;
               ajaxResult = AjaxResult.error(flawDesc + "：医嘱编号不能为空");
            }

            if (StringUtils.isBlank(emrQcList.getEmrEleName())) {
               flag = false;
               ajaxResult = AjaxResult.error(flawDesc + "：医嘱内容不能为空");
            }

            if (StringUtils.isBlank(emrQcList.getDefeLevel())) {
               flag = false;
               ajaxResult = AjaxResult.error(flawDesc + "：缺陷严重程度不能为空");
            }

            if (StringUtils.isBlank(emrQcList.getQcSection())) {
               flag = false;
               ajaxResult = AjaxResult.error(flawDesc + "：质控环节不能为空");
            }
         }

         if (flag) {
            this.emrQcListService.addQcOrderEmrQcLists(emrQcList);
            ajaxResult = AjaxResult.success((Object)emrQcList);
         }
      } catch (Exception e) {
         this.log.error("新增医嘱本缺陷明细出现异常：", e);
         ajaxResult = AjaxResult.error("新增医嘱本缺陷明细出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('system:list:add,qc:rt:check,qc:rt:dept,qc:rt:term')")
   @Log(
      title = "新增人工质控缺陷",
      businessType = BusinessType.INSERT
   )
   @PostMapping({"/addArtFlaw"})
   public AjaxResult addArtFlaw(@RequestBody EmrQcListVo emrQcList) {
      AjaxResult ajaxResult = AjaxResult.success("保存成功");
      boolean flag = true;
      String indexFileKey = null;

      try {
         if (flag && emrQcList == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         String flawDesc = emrQcList.getFlawDesc();
         if (flag && emrQcList.getMainId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("质控id不能为空");
         }

         if (flag && StringUtils.isBlank(emrQcList.getPatientId())) {
            flag = false;
            ajaxResult = AjaxResult.error("患者id不能为空");
         }

         if (flag && StringUtils.isBlank(emrQcList.getMrFileId())) {
            flag = false;
            ajaxResult = AjaxResult.error("病历id不能为空");
         }

         if (flag && StringUtils.isBlank(emrQcList.getDefeLevel())) {
            flag = false;
            ajaxResult = AjaxResult.error("缺陷严重程度不能为空");
         }

         if (flag && StringUtils.isBlank(emrQcList.getFlawDesc())) {
            flag = false;
            ajaxResult = AjaxResult.error("缺陷描述不能为空");
         }

         if (flag && StringUtils.isBlank(emrQcList.getEmrEleId())) {
            flag = false;
            ajaxResult = AjaxResult.error("病历元素不能为空");
         }

         if (flag && StringUtils.isBlank(emrQcList.getEmrEleName())) {
            flag = false;
            ajaxResult = AjaxResult.error("病历元素名称不能为空");
         }

         if (flag && StringUtils.isBlank(emrQcList.getQcSection())) {
            flag = false;
            ajaxResult = AjaxResult.error("质控环节不能为空");
         }

         if (flag && emrQcList.getDedScoreSingle() != null && emrQcList.getDedScoreSingle() > (double)0.0F && StringUtils.isEmpty(emrQcList.getItemCd())) {
            flag = false;
            ajaxResult = AjaxResult.error("项目归类不能为空");
         }

         if (flag && StringUtils.isEmpty(emrQcList.getBase64Str())) {
            flag = false;
            ajaxResult = AjaxResult.error("病历base64不能为空");
         }

         if (flag && StringUtils.isEmpty(emrQcList.getXmlStr())) {
            flag = false;
            ajaxResult = AjaxResult.error("病历xml不能为空");
         }

         SubfileIndex subfileIndex = null;
         MrHp mrHp = null;
         Index index = null;
         if (flag) {
            String indexId = emrQcList.getMrFileId();
            index = this.indexService.selectIndexById(Long.parseLong(indexId));
            if (index == null) {
               subfileIndex = this.subfileIndexService.selectSubfileIndexById(Long.parseLong(indexId));
            }

            if (index == null && subfileIndex == null) {
               mrHp = this.mrHpService.selectMrHpById(indexId);
            }

            if (index == null && subfileIndex == null && mrHp == null) {
               flag = false;
               ajaxResult = AjaxResult.error("没有此病历文件记录");
            }

            Long mrFileId = subfileIndex == null ? index.getId() : subfileIndex.getMainId();
            if (flag && emrQcList.getFlawDate() != null && mrFileId != null) {
               indexFileKey = "index_file_read_write:" + mrFileId;
               String indexFileReadFlag = (String)this.redisCache.getCacheObject(indexFileKey);
               if (StringUtils.isNotBlank(indexFileReadFlag) && indexFileReadFlag.equals("1")) {
                  flag = false;
                  ajaxResult = AjaxResult.error("当前病历有正在保存的操作，不能继续保存，请稍后再试！");
                  indexFileKey = null;
               } else {
                  this.redisCache.setCacheObject(indexFileKey, "1", 10, TimeUnit.MINUTES);
               }
            }
         }

         if (flag) {
            this.emrQcListService.addQcDoctEmrQc(emrQcList, mrHp, index, subfileIndex);
            ajaxResult.put("emrQcList", emrQcList);
            if (StringUtils.isNotBlank(indexFileKey)) {
               this.redisCache.deleteObject(indexFileKey);
            }
         }
      } catch (Exception e) {
         this.log.error("新增病历缺陷明细出现异常：", e);
         ajaxResult = AjaxResult.error("新增病历缺陷明细出现异常");
         if (StringUtils.isNotBlank(indexFileKey)) {
            this.redisCache.deleteObject(indexFileKey);
         }
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('system:list:add,qc:rt:check,qc:rt:dept,qc:rt:term')")
   @Log(
      title = "修改人工质控缺陷",
      businessType = BusinessType.INSERT
   )
   @PutMapping({"/updateArtFlaw"})
   public AjaxResult updateArtFlaw(@RequestBody EmrQcListVo emrQcList) {
      AjaxResult ajaxResult = AjaxResult.success("保存成功");
      boolean flag = true;

      try {
         if (flag && emrQcList == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         String flawDesc = emrQcList.getFlawDesc();
         if (flag && emrQcList.getId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error(flawDesc + "：id不能为空");
         }

         if (flag && StringUtils.isBlank(emrQcList.getPatientId())) {
            flag = false;
            ajaxResult = AjaxResult.error(flawDesc + "：患者id不能为空");
         }

         if (flag && StringUtils.isBlank(emrQcList.getMrFileId())) {
            flag = false;
            ajaxResult = AjaxResult.error(flawDesc + "：病历id不能为空");
         }

         if (flag && StringUtils.isBlank(emrQcList.getFlawDesc())) {
            flag = false;
            ajaxResult = AjaxResult.error(flawDesc + "：缺陷描述不能为空");
         }

         if (flag && StringUtils.isBlank(emrQcList.getEmrEleId())) {
            flag = false;
            ajaxResult = AjaxResult.error(flawDesc + "：病历元素不能为空");
         }

         if (flag && StringUtils.isBlank(emrQcList.getEmrEleName())) {
            flag = false;
            ajaxResult = AjaxResult.error(flawDesc + "：病历元素名称不能为空");
         }

         if (flag && StringUtils.isBlank(emrQcList.getDefeLevel())) {
            flag = false;
            ajaxResult = AjaxResult.error(flawDesc + "：缺陷严重程度不能为空");
         }

         if (flag && StringUtils.isBlank(emrQcList.getQcSection())) {
            flag = false;
            ajaxResult = AjaxResult.error(flawDesc + "：质控环节不能为空");
         }

         if (flag && emrQcList.getDedScoreSingle() != null && emrQcList.getDedScoreSingle() > (double)0.0F && StringUtils.isEmpty(emrQcList.getItemCd())) {
            flag = false;
            ajaxResult = AjaxResult.error(flawDesc + "：项目归类不能为空");
         }

         if (flag) {
            EmrQcList emrQcList1 = this.emrQcListService.selectEmrQcListById(emrQcList.getId());
            if (!emrQcList1.getQcdoctCd().equals(SecurityUtils.getLoginUser().getUser().getBasEmployee().getEmplNumber())) {
               flag = false;
               ajaxResult = AjaxResult.error("不是创建人不可修改");
            }

            if (flag && !emrQcList1.getQcState().equals("0") && !emrQcList1.getQcState().equals("3")) {
               flag = false;
               ajaxResult = AjaxResult.error("缺陷已确认不可修改");
            }
         }

         if (flag) {
            this.emrQcListService.updateQcDoctEmrQc(emrQcList);
         }
      } catch (Exception e) {
         this.log.error("修改病历缺陷明细出现异常：", e);
         ajaxResult = AjaxResult.error("修改病历缺陷明细出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('system:list:add,qc:rt:check,qc:rt:dept,qc:rt:term')")
   @Log(
      title = "删除人工质控缺陷",
      businessType = BusinessType.INSERT
   )
   @DeleteMapping({"/deleteFlaw"})
   public AjaxResult deleteFlaw(@RequestBody EmrQcListVo emrQcList) {
      AjaxResult ajaxResult = AjaxResult.success("删除成功");
      boolean flag = true;

      try {
         if (flag && emrQcList == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         String flawDesc = emrQcList.getFlawDesc();
         if (flag && emrQcList.getId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error(flawDesc + "：id不能为空");
         }

         if (flag) {
            EmrQcList emrQcList1 = this.emrQcListService.selectEmrQcListById(emrQcList.getId());
            if (!emrQcList1.getQcdoctCd().equals(SecurityUtils.getLoginUser().getUser().getBasEmployee().getEmplNumber())) {
               flag = false;
               ajaxResult = AjaxResult.error("非本人反馈的缺陷不可删除");
            }

            if (flag && !emrQcList1.getQcState().equals("0") && !emrQcList1.getQcState().equals("3")) {
               flag = false;
               ajaxResult = AjaxResult.error("缺陷已确认不可删除");
            }
         }

         if (flag) {
            this.emrQcListService.deleteEmrQcList(emrQcList);
         }
      } catch (Exception e) {
         this.log.error("删除病历缺陷明细出现异常：", e);
         ajaxResult = AjaxResult.error("删除病历缺陷明细出现异常");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:statis:checkStatis')")
   @GetMapping({"/getEmrQcFlowStatistic"})
   public TableDataInfo getEmrQcFlowStatistic(EmrQcFlowVo emrQcFlowVo) {
      TableDataInfo tableDataInfo = new TableDataInfo();
      boolean flag = true;

      try {
         if (emrQcFlowVo == null) {
            flag = false;
            tableDataInfo = new TableDataInfo(500, "参数不能为空");
         }

         if (emrQcFlowVo.getOutHospitalTimeEnd() != null) {
            Date outTimeEnd = emrQcFlowVo.getOutHospitalTimeEnd();
            emrQcFlowVo.setOutHospitalTimeEnd(DateUtils.addDays(outTimeEnd, 1));
         }

         if (flag) {
            this.startPage();
            List<EmrQcListStatisticVo> emrQcFlowList = this.emrQcListService.getEmrQcFlowStatistic(emrQcFlowVo);
            tableDataInfo = this.getDataTable(emrQcFlowList);
            Object json = JSONObject.toJSON(emrQcFlowVo);
            tableDataInfo.setObject(json);
         }
      } catch (Exception e) {
         this.log.error("查询病历缺陷出现异常：", e);
         tableDataInfo = new TableDataInfo(500, "查询病历缺陷出现异常");
      }

      return tableDataInfo;
   }

   @PreAuthorize("@ss.hasAnyPermi('qc:statis:checkStatis')")
   @GetMapping({"/getQcCaseStatisticExport"})
   public AjaxResult getQcCaseStatisticExport(EmrQcFlowVo emrQcFlowVo, HttpServletResponse response) {
      AjaxResult ajaxResult = AjaxResult.success("导出成功");
      boolean flag = true;

      try {
         if (flag && emrQcFlowVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (emrQcFlowVo.getOutHospitalTimeEnd() != null) {
            Date outTimeEnd = emrQcFlowVo.getOutHospitalTimeEnd();
            emrQcFlowVo.setOutHospitalTimeEnd(DateUtils.addDays(outTimeEnd, 1));
         }

         if (flag) {
            return this.emrQcListService.getEmrQcFlowStatisticExport(emrQcFlowVo, response);
         }
      } catch (Exception e) {
         ajaxResult = AjaxResult.error("导出病历缺陷统计出现异常");
         this.log.error("导出病历缺陷统计出现异常，", e);
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('system:list:add,qc:rt:check,qc:rt:dept,qc:rt:term')")
   @PostMapping({"/mrHpQcSave"})
   public AjaxResult mrHpQcSave(@RequestBody EmrQcListVo emrQcList) {
      AjaxResult ajaxResult = AjaxResult.success("保存成功");
      boolean flag = true;

      try {
         if (flag && emrQcList == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag) {
            String flawDesc = emrQcList.getFlawDesc();
            if (emrQcList.getMainId() == null) {
               flag = false;
               ajaxResult = AjaxResult.error(flawDesc + "：质控id不能为空");
            }

            if (StringUtils.isBlank(emrQcList.getPatientId())) {
               flag = false;
               ajaxResult = AjaxResult.error(flawDesc + "：患者id不能为空");
            }

            if (emrQcList.getRuleId() == null) {
               flag = false;
               ajaxResult = AjaxResult.error(flawDesc + "：规则id不能为空");
            }

            if (StringUtils.isBlank(emrQcList.getMrFileId())) {
               flag = false;
               ajaxResult = AjaxResult.error(flawDesc + "：医嘱本类型不能为空");
            }

            if (StringUtils.isBlank(emrQcList.getRuleName())) {
               flag = false;
               ajaxResult = AjaxResult.error(flawDesc + "：规则名称不能为空");
            }

            if (StringUtils.isBlank(emrQcList.getFlawDesc())) {
               flag = false;
               ajaxResult = AjaxResult.error(flawDesc + "：缺陷描述不能为空");
            }

            if (StringUtils.isBlank(emrQcList.getEmrEleId())) {
               flag = false;
               ajaxResult = AjaxResult.error(flawDesc + "：字段名不能为空");
            }

            if (StringUtils.isBlank(emrQcList.getEmrEleName())) {
               flag = false;
               ajaxResult = AjaxResult.error(flawDesc + "：内容不能为空");
            }

            if (StringUtils.isBlank(emrQcList.getDefeLevel())) {
               flag = false;
               ajaxResult = AjaxResult.error(flawDesc + "：缺陷严重程度不能为空");
            }

            if (StringUtils.isBlank(emrQcList.getQcSection())) {
               flag = false;
               ajaxResult = AjaxResult.error(flawDesc + "：质控环节不能为空");
            }
         }

         if (flag) {
            this.emrQcListService.addQcMrHpEmrQcLists(emrQcList);
            ajaxResult = AjaxResult.success((Object)emrQcList);
         }
      } catch (Exception e) {
         this.log.error("新增病案首页缺陷明细出现异常：", e);
         ajaxResult = AjaxResult.error("新增病案首页缺陷明细出现异常");
      }

      return ajaxResult;
   }
}
