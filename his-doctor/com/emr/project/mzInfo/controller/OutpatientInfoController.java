package com.emr.project.mzInfo.controller;

import com.emr.common.utils.StringUtils;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.project.mzInfo.domain.OutpatientInfoVO;
import com.emr.project.mzInfo.domain.req.OutpatientInfoReq;
import com.emr.project.mzInfo.service.IOutpatientInfoService;
import com.emr.project.operation.domain.Medicalinformation;
import com.emr.project.operation.service.IMedicalinfoService;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/mz/patientInfo"})
public class OutpatientInfoController extends BaseController {
   @Autowired
   private IOutpatientInfoService outpatientInfoService;
   @Autowired
   private IMedicalinfoService medicalinfoService;

   @PreAuthorize("@ss.hasPermi('pat:info:emrAllList')")
   @GetMapping({"/list"})
   public AjaxResult list(OutpatientInfoReq req) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");
      if (StringUtils.isEmpty(req.getSource())) {
         ajaxResult = AjaxResult.error("请求来源不能为空！");
         return ajaxResult;
      } else {
         try {
            String source = req.getSource();
            List<OutpatientInfoVO> allList = new ArrayList();
            if ("zy".equals(source)) {
               String inpNo = req.getInpNo();
               if (StringUtils.isEmpty(req.getInpNo())) {
                  ajaxResult = AjaxResult.error("住院号不能为空！");
                  return ajaxResult;
               }

               Medicalinformation med = this.medicalinfoService.getHisMedicalByAdmissionNo(req.getInpNo());
               if (med != null) {
                  List<OutpatientInfoVO> inList = this.medicalinfoService.selectOutpatient(med.getCaseNo(), (String)null);
                  if (inList != null && inList.size() > 0) {
                     List<OutpatientInfoVO> inListCurr = (List)inList.stream().filter((t) -> t.getAdmissionNo().equals(req.getInpNo())).collect(Collectors.toList());
                     OutpatientInfoVO outpatientInfoVO = (OutpatientInfoVO)inListCurr.get(0);
                     allList.add(outpatientInfoVO);
                     String idCard = outpatientInfoVO.getIdCard();
                     if (StringUtils.isNotEmpty(idCard)) {
                        List<OutpatientInfoVO> inOtherList = this.medicalinfoService.selectOutpatient((String)null, idCard);
                        if (inOtherList != null && inOtherList.size() > 0) {
                           inOtherList.removeIf((t) -> t.getAdmissionNo().equals(inpNo));
                           if (!inOtherList.isEmpty()) {
                              allList.addAll(inOtherList);
                           }
                        }

                        List<OutpatientInfoVO> outList = this.outpatientInfoService.selectOutPatientInfoByIdCard((String)null, idCard);
                        if (outList != null && outList.size() > 0) {
                           allList.addAll(outList);
                        }
                     }
                  }
               }
            } else {
               String inpNo = req.getInpNo();
               String idCard = req.getIdCard();
               if (StringUtils.isEmpty(inpNo)) {
                  ajaxResult = AjaxResult.error("门诊id不能为空！");
                  return ajaxResult;
               }

               List<OutpatientInfoVO> patientInfoList = this.outpatientInfoService.selectOutPatientInfoByVisitNo(inpNo);
               if (CollectionUtils.isNotEmpty(patientInfoList) && patientInfoList.size() > 0) {
                  inpNo = ((OutpatientInfoVO)patientInfoList.get(0)).getAdmissionNo();
               }

               List<OutpatientInfoVO> outList = this.outpatientInfoService.selectOutPatientInfoByIdCard(inpNo, idCard);
               if (outList != null && outList.size() > 0) {
                  allList.addAll(outList);
               }

               if (StringUtils.isNotEmpty(idCard)) {
                  List<OutpatientInfoVO> inList = this.medicalinfoService.selectOutpatient((String)null, idCard);
                  if (inList != null && inList.size() > 0) {
                     allList.addAll(inList);
                  }
               }
            }

            allList = (List)allList.stream().sorted(Comparator.comparing(OutpatientInfoVO::getHospitalizedDate).reversed()).collect(Collectors.toList());
            ajaxResult.put("data", allList);
         } catch (Exception e) {
            this.log.error("查询患者就诊记录出现异常，请联系管理员！", e);
            ajaxResult = AjaxResult.error("查询患者就诊记录出现异常，请联系管理员！");
         }

         return ajaxResult;
      }
   }
}
