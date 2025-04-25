package com.emr.project.his.controller;

import com.emr.common.utils.StringUtils;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.project.his.domain.vo.DocInHospitalVo;
import com.emr.project.his.service.IDocInHospitalService;
import com.emr.project.system.domain.vo.SqlVo;
import com.emr.project.tmpm.domain.PrintTmplInfo;
import com.emr.project.tmpm.service.IPrintTmplInfoService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/his/docInHos"})
public class DocInHospitalController extends BaseController {
   @Autowired
   private IDocInHospitalService docInHospitalService;
   @Autowired
   private IPrintTmplInfoService printTmplInfoService;

   @PreAuthorize("@ss.hasAnyPermi('pat:visitinfo:emrPrintList,pat:visitinfo:emrAllList,his:docInHos:patData,qc:flow:term,pat:info:emrAllList,qc:statis:checkCase')")
   @GetMapping({"/patData"})
   public AjaxResult getDataByInpNo(String inpNo) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");
      boolean flag = true;

      try {
         if (StringUtils.isBlank(inpNo)) {
            flag = false;
            ajaxResult = AjaxResult.error("住院号不能为空");
         }

         if (flag) {
            SqlVo sqlVo = new SqlVo();
            sqlVo.setInpNo(inpNo);
            DocInHospitalVo docInHospitalVo = this.docInHospitalService.selectDocInHospitalByInpNo(sqlVo);
            ajaxResult = AjaxResult.success("查询成功", docInHospitalVo);
            List<String> typeCodeList = new ArrayList();
            typeCodeList.add("100023001");
            List<PrintTmplInfo> printTmplInfos = this.printTmplInfoService.selectTmPmPrintTmplInfoByCodes(typeCodeList);
            ajaxResult.put("printTmplList", printTmplInfos);
         }
      } catch (Exception e) {
         ajaxResult = AjaxResult.error("查询入院证数据出现异常，请联系管理员！");
         this.log.error("查询入院证数据出现异常：", e);
      }

      return ajaxResult;
   }
}
