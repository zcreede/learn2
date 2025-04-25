package com.emr.project.emr.controller;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.poi.ExcelUtil;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.emr.domain.Index;
import com.emr.project.emr.domain.SealupRecord;
import com.emr.project.emr.domain.vo.IndexVo;
import com.emr.project.emr.domain.vo.PasswordVo;
import com.emr.project.emr.domain.vo.SealupRecordVo;
import com.emr.project.emr.domain.vo.SealupVo;
import com.emr.project.emr.service.IIndexService;
import com.emr.project.emr.service.ISealupRecordService;
import com.emr.project.pat.domain.Visitinfo;
import com.emr.project.pat.domain.vo.VisitinfoPersonalVo;
import com.emr.project.pat.service.IVisitinfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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

@Api(
   value = "SealupRecordController",
   tags = {"病历封存"}
)
@RestController
@RequestMapping({"/emr/record"})
public class SealupRecordController extends BaseController {
   @Autowired
   private ISealupRecordService sealupRecordService;
   @Resource
   private IIndexService indexService;
   @Resource
   private IVisitinfoService visitinfoService;

   @PreAuthorize("@ss.hasPermi('emr:record:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(SealupRecord sealupRecord) {
      this.startPage();
      List<SealupRecord> list = this.sealupRecordService.selectSealupRecordList(sealupRecord);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('emr:record:export')")
   @Log(
      title = "病历封存记录",
      businessType = BusinessType.EXPORT
   )
   @GetMapping({"/export"})
   public AjaxResult export(SealupRecord sealupRecord) {
      List<SealupRecord> list = this.sealupRecordService.selectSealupRecordList(sealupRecord);
      ExcelUtil<SealupRecord> util = new ExcelUtil(SealupRecord.class);
      return util.exportExcel(list, "病历封存记录数据");
   }

   @PreAuthorize("@ss.hasPermi('emr:record:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.sealupRecordService.selectSealupRecordById(id));
   }

   @PreAuthorize("@ss.hasPermi('emr:record:edit')")
   @Log(
      title = "病历封存记录",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult edit(@RequestBody SealupRecord sealupRecord) {
      return this.toAjax(this.sealupRecordService.updateSealupRecord(sealupRecord));
   }

   @PreAuthorize("@ss.hasPermi('emr:record:remove')")
   @Log(
      title = "病历封存记录",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.sealupRecordService.deleteSealupRecordByIds(ids));
   }

   @ApiOperation("查询患者病历封存状态")
   @PreAuthorize("@ss.hasPermi('emr:record:list')")
   @GetMapping({"/indexSealupState"})
   public AjaxResult indexSealupState(Visitinfo visitinfo) {
      AjaxResult ajaxResult = AjaxResult.success("查询患者信息成功");
      Boolean flag = true;

      try {
         if (flag && visitinfo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(visitinfo.getInpNo())) {
            flag = false;
            ajaxResult = AjaxResult.error("请输入住院号");
         }

         Visitinfo vo = this.visitinfoService.selectVisitinfoByInpNo(visitinfo.getInpNo());
         if (flag && vo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("查询不到此住院号患者信息，请确认住院号");
         }

         if (flag) {
            VisitinfoPersonalVo visitinfoPersonalVo = this.visitinfoService.selectVisitinfoPersonalById(vo.getPatientId());
            ajaxResult = AjaxResult.success((Object)this.sealupRecordService.indexSealupState(visitinfoPersonalVo));
         }
      } catch (Exception e) {
         this.log.error("查询患者信息出现异常", e);
      }

      return ajaxResult;
   }

   @ApiOperation("查询患者病历列表")
   @PreAuthorize("@ss.hasAnyPermi('emr:record:list,emr:record:queryList')")
   @GetMapping({"/indexList"})
   public TableDataInfo indexList(IndexVo indexVo) {
      List<Index> list = null;

      try {
         if (StringUtils.isNotBlank(indexVo.getPatientId())) {
            indexVo.setDelFlag("0");
            this.startPage();
            list = this.indexService.selectDelIndexPageList(indexVo);
         }
      } catch (Exception e) {
         this.log.error("查询患者病历出现异常", e);
      }

      return this.getDataTable(list);
   }

   @ApiOperation("病历封存")
   @PreAuthorize("@ss.hasPermi('emr:record:sealup')")
   @PostMapping({"/sealup"})
   public AjaxResult addOrEditSealupRecord(@RequestBody PasswordVo passwordVo, HttpServletRequest httpServletRequest) {
      AjaxResult ajaxResult = AjaxResult.success("封存成功");
      Boolean flag = true;

      try {
         if (flag && passwordVo.getSealupRecord().getOrgCd() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("机构编码不能为空");
         }

         if (flag && passwordVo.getSealupRecord().getInpNo() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("住院号不能为空");
         }

         if (flag && passwordVo.getSealupRecord().getPatientId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("患者id不能为空");
         }

         if (flag && passwordVo.getSealupRecord().getPatientName() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("患者姓名不能为空");
         }

         if (flag && passwordVo.getSealupRecord().getGender() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("性别不能为空");
         }

         if (flag && passwordVo.getSealupRecord().getAge() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("年龄不能为空");
         }

         if (flag && passwordVo.getSealupRecord().getResDocCode() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("医师编码不能为空");
         }

         if (flag && passwordVo.getSealupRecord().getResDocName() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("医师姓名不能为空");
         }

         if (flag && passwordVo.getSealupRecord().getDeptId() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("部门id不能为空");
         }

         if (flag && passwordVo.getSealupRecord().getDpetName() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("部门名称不能为空");
         }

         if (flag && passwordVo.getSealupRecord().getBedNo() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("床位号不能为空");
         }

         if (flag && passwordVo.getSealupRecord().getInhosTime() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("入院时间不能为空");
         }

         if (flag && passwordVo.getHospital() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("院方密码不能为空");
         }

         if (flag && passwordVo.getPatient() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("患者密码不能为空");
         }

         if (flag && passwordVo.getWitness() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("见证人密码不能为空");
         }

         if (flag) {
            this.sealupRecordService.insertSealupRecord(passwordVo, httpServletRequest);
         }
      } catch (Exception e) {
         this.log.error("封存病历出现异常,", e);
         ajaxResult = AjaxResult.error("封存病历出现异常");
      }

      return ajaxResult;
   }

   @ApiOperation("病历启封")
   @PreAuthorize("@ss.hasPermi('emr:record:secure')")
   @PutMapping({"/secure"})
   public AjaxResult secure(@RequestBody PasswordVo passwordVo, HttpServletRequest httpServletRequest) {
      AjaxResult ajaxResult = AjaxResult.success("启封成功");
      Boolean flag = true;
      SealupRecord sealupRecord = passwordVo.getSealupRecord();

      try {
         if (flag && sealupRecord == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && passwordVo.getHospital() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("院方密码不能为空");
         }

         if (flag && passwordVo.getPatient() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("患者密码不能为空");
         }

         if (flag && passwordVo.getWitness() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("见证人密码不能为空");
         }

         if (flag) {
            StringBuffer sb = new StringBuffer("");
            SealupRecord param = this.sealupRecordService.selectSealupRecordById(sealupRecord.getId());
            String[] seal = param.getSealPass().split(",");
            Boolean hospital = SecurityUtils.matchesPassword(passwordVo.getHospital(), seal[0]);
            Boolean patient = SecurityUtils.matchesPassword(passwordVo.getPatient(), seal[1]);
            Boolean witness = SecurityUtils.matchesPassword(passwordVo.getWitness(), seal[2]);
            if (!hospital) {
               flag = false;
               ajaxResult = AjaxResult.error(sb.append("院方密码错误请重试!").toString());
            }

            if (!patient) {
               flag = false;
               ajaxResult = AjaxResult.error(sb.append("患者密码错误请重试!").toString());
            }

            if (!witness) {
               flag = false;
               ajaxResult = AjaxResult.error(sb.append("见证人密码错误请重试!").toString());
            }

            if (flag) {
               this.sealupRecordService.secureRecord(param, httpServletRequest);
            }
         }
      } catch (Exception e) {
         this.log.error("病历启封出现异常, ", e);
         ajaxResult = AjaxResult.error("病历启封出现异常");
      }

      return ajaxResult;
   }

   @ApiOperation("病历封存记录列表")
   @PreAuthorize("@ss.hasPermi('emr:record:recordList')")
   @GetMapping({"/recordList"})
   public TableDataInfo recordList(SealupRecord sealupRecord) {
      List<SealupRecordVo> list = new ArrayList();

      try {
         List<SealupRecord> sealupRecordList = this.sealupRecordService.selectSealupRecordList(sealupRecord);
         this.startPage();
         if (sealupRecordList != null && sealupRecordList.size() > 0) {
            for(SealupRecord param : sealupRecordList) {
               SealupRecordVo sealupRecordVo = new SealupRecordVo();
               sealupRecordVo.setSealupRecord(param);
               Index index = new Index();
               index.setPatientId(param.getPatientId());
               index.setLockState(1);
               index.setDelFlag("1");
               List<Index> indexList = this.indexService.selectIndexList(index);
               if (indexList != null) {
                  sealupRecordVo.setNums(indexList.size());
               } else {
                  sealupRecordVo.setNums(0);
               }

               list.add(sealupRecordVo);
            }
         }
      } catch (Exception e) {
         this.log.error("查询病历封存记录列表出现异常：", e);
      }

      return this.getDataTable(list);
   }

   @ApiOperation("病历封存记录列表")
   @ApiImplicitParams({@ApiImplicitParam(
   name = "inpNo",
   value = "住院号",
   dataType = "String"
), @ApiImplicitParam(
   name = "pageNum",
   value = "当前记录起始索引",
   dataType = "Integer"
), @ApiImplicitParam(
   name = "pageSize",
   value = "每页显示记录数",
   dataType = "Integer"
)})
   @PreAuthorize("@ss.hasPermi('emr:record:queryList')")
   @GetMapping({"/queryList"})
   public TableDataInfo queryList(SealupVo sealupVo) {
      new TableDataInfo();

      TableDataInfo tableDataInfo;
      try {
         this.startPage();
         List<SealupVo> list = this.sealupRecordService.querySealupList(sealupVo);
         tableDataInfo = this.getDataTable(list);
      } catch (Exception var4) {
         this.log.error("查询病历封存记录出现异常");
         tableDataInfo = new TableDataInfo(500, "查询病历封存记录出现异常");
      }

      return tableDataInfo;
   }
}
