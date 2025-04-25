package com.emr.project.mzInfo.controller;

import com.emr.common.utils.StringUtils;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.project.his.domain.vo.DocInHospitalVo;
import com.emr.project.his.service.IDocInHospitalService;
import com.emr.project.mzInfo.domain.MzjzInfoBl;
import com.emr.project.mzInfo.service.IMzjzInforBlService;
import com.emr.project.other.domain.BasCertInfo;
import com.emr.project.other.service.IBasCertInfoService;
import com.emr.project.system.domain.vo.SqlVo;
import com.emr.project.system.service.ISysEmrConfigService;
import com.emr.project.tmpm.domain.PrintTmplInfo;
import com.emr.project.tmpm.service.IPrintTmplInfoService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/mzjzInfoBl"})
public class MzjzInfoBlController extends BaseController {
   @Autowired
   private IMzjzInforBlService mzjzInforRecordService;
   @Autowired
   private IPrintTmplInfoService printTmplInfoService;
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;
   @Autowired
   private IBasCertInfoService basCertInfoService;
   @Autowired
   private IDocInHospitalService docInHospitalService;

   @PreAuthorize("@ss.hasAnyPermi('pat:info:emrAllList')")
   @GetMapping({"/selectMzjzInfoBlList"})
   public AjaxResult selectMzjzInfoBlList(String mzh) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");
      new ArrayList();
      boolean flag = true;

      try {
         if (flag && StringUtils.isBlank(mzh)) {
            flag = false;
            ajaxResult = AjaxResult.error("门诊号不能为空");
         }

         if (flag) {
            List mzjzInfoBlList = this.mzjzInforRecordService.selectMzjzInfoBlList(mzh);
            String caFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0001");
            if (StringUtils.isNotBlank(caFlag) && ("1".equals(caFlag) || "2".equals(caFlag)) && mzjzInfoBlList != null && mzjzInfoBlList.size() > 0) {
               for(MzjzInfoBl mzjzInfoBl : mzjzInfoBlList) {
                  if (StringUtils.isNotBlank(mzjzInfoBl.getYsbm())) {
                     BasCertInfo basCertInfo = new BasCertInfo();
                     basCertInfo.setEmployeenumber(mzjzInfoBl.getYsbm());
                     List<BasCertInfo> basCertInfoList = this.basCertInfoService.selectBasCertInfoList(basCertInfo);
                     if (basCertInfoList != null && basCertInfoList.size() > 0) {
                        mzjzInfoBl.setCertPic(((BasCertInfo)basCertInfoList.get(0)).getCertPic());
                     }
                  }
               }
            }

            ajaxResult.put("data", mzjzInfoBlList);
            List<String> typeCodeList = new ArrayList(Arrays.asList("100026001"));
            List<PrintTmplInfo> printTmplList = this.printTmplInfoService.selectTmPmPrintTmplInfoByCodes(typeCodeList);
            ajaxResult.put("printTmplList", printTmplList);
         }
      } catch (Exception e) {
         this.log.error("根据门诊号查询门诊病历信息出现异常：", e);
         ajaxResult = AjaxResult.error("根据门诊号查询门诊病历信息出现异常！");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('pat:info:emrAllList,qc:statis:checkCase')")
   @GetMapping({"/getMzblByPatientId"})
   public AjaxResult getMzblByPatientId(String patientId) throws Exception {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");
      Boolean flag = true;

      try {
         if (!StringUtils.isNotBlank(patientId)) {
            flag = false;
            ajaxResult = AjaxResult.error("住院号不能为空");
         }

         if (flag) {
            SqlVo sqlVo = new SqlVo();
            sqlVo.setInpNo(patientId);
            DocInHospitalVo docInHospitalVo = this.docInHospitalService.selectDocInHospitalByInpNo(sqlVo);
            if (docInHospitalVo != null && StringUtils.isNotBlank(docInHospitalVo.getMzh())) {
               ajaxResult = this.selectMzjzInfoBlList(docInHospitalVo.getMzh());
            } else {
               List<MzjzInfoBl> mzjzInfoBlList = new ArrayList();
               MzjzInfoBl mzjzInfoBl = new MzjzInfoBl();
               mzjzInfoBlList.add(mzjzInfoBl);
               ajaxResult.put("data", mzjzInfoBlList);
               List<String> typeCodeList = new ArrayList(Arrays.asList("100026001"));
               List<PrintTmplInfo> printTmplList = this.printTmplInfoService.selectTmPmPrintTmplInfoByCodes(typeCodeList);
               ajaxResult.put("printTmplList", printTmplList);
            }
         }
      } catch (Exception e) {
         this.log.error("根据住院号查询门诊病历信息出现异常：", e);
         ajaxResult = AjaxResult.error("根据住院号查询门诊病历信息出现异常！");
      }

      return ajaxResult;
   }
}
