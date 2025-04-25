package com.emr.project.webservice.controller;

import com.emr.common.utils.StringUtils;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.project.pat.domain.vo.VisitinfoVo;
import com.emr.project.pat.service.IVisitinfoService;
import com.emr.project.webservice.domain.vo.DSRParamVo;
import com.emr.project.webservice.service.IDSRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/dsr"})
public class DSRController extends BaseController {
   @Autowired
   private IDSRService dsrService;
   @Autowired
   private IVisitinfoService visitinfoService;

   @PostMapping({"/judgeWhetherOpen"})
   public AjaxResult dsrJudgeWhetherOpen(@RequestBody DSRParamVo dsrParamVo) {
      AjaxResult ajaxResult = AjaxResult.success();
      Boolean flag = true;

      try {
         if (flag && dsrParamVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isBlank(dsrParamVo.getPatientId())) {
            flag = false;
            ajaxResult = AjaxResult.error("患者id不能为空");
         }

         if (flag && (dsrParamVo.getIcds() == null || dsrParamVo.getIcds().isEmpty())) {
            flag = false;
            ajaxResult = AjaxResult.error("icd编码不能为空");
         }

         VisitinfoVo visitinfoVo = flag ? this.visitinfoService.selectVisitinfoByPatientId(dsrParamVo.getPatientId()) : null;
         if (flag && visitinfoVo == null) {
            flag = false;
            ajaxResult = AjaxResult.error("没有此患者的信息");
         }

         if (flag) {
            DSRParamVo result = this.dsrService.judgeWhetherOpen(dsrParamVo.getIcds(), visitinfoVo);
            ajaxResult = AjaxResult.success("查询成功", result);
         }
      } catch (Exception e) {
         this.log.error("请求不良事件接口出现异常：", e);
         ajaxResult = AjaxResult.error("请求不良事件接口出现异常");
      }

      return ajaxResult;
   }
}
