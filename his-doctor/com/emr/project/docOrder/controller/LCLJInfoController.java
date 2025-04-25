package com.emr.project.docOrder.controller;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.StringUtils;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.project.docOrder.domain.req.LcljInfoLocalReq;
import com.emr.project.docOrder.domain.resp.LcljBaseInfo;
import com.emr.project.docOrder.domain.resp.LcljDetailInfo;
import com.emr.project.docOrder.domain.resp.LcljItemInfo;
import com.emr.project.docOrder.domain.vo.OrderSearchVo;
import com.emr.project.docOrder.service.ILCLJInfoService;
import com.emr.project.docOrder.service.ITdPaOrderService;
import com.emr.project.operation.domain.Medicalinformation;
import com.emr.project.operation.service.IMedicalinfoService;
import com.emr.project.system.service.ISysEmrConfigService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/docOrder/lclj"})
public class LCLJInfoController {
   protected final Logger log = LoggerFactory.getLogger(this.getClass());
   @Autowired
   private ILCLJInfoService lcljInfoService;
   @Autowired
   private ITdPaOrderService tdPaOrderService;
   @Autowired
   private IMedicalinfoService medicalinfoService;
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;

   @PreAuthorize("@ss.hasPermi('docOrder:order:list')")
   @GetMapping({"/baseInfo"})
   public AjaxResult queryLcljBaseInfo(String admissionNo) {
      AjaxResult ajaxResult = AjaxResult.success();
      boolean flag = true;

      try {
         if (flag && StringUtils.isBlank(admissionNo)) {
            flag = false;
            ajaxResult = AjaxResult.error("患者住院号不能为空");
         }

         if (flag) {
            LcljBaseInfo lcljBaseInfo = this.lcljInfoService.getLcljBaseInfo(admissionNo);
            ajaxResult = AjaxResult.success((Object)lcljBaseInfo);
         }
      } catch (Exception e) {
         this.log.error("查询患者临床路径基本信息出现异常,", e);
         ajaxResult = AjaxResult.error("查询患者临床路径基本信息出现异常，请联系管理员");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('docOrder:order:list')")
   @GetMapping({"/itemInfo"})
   public AjaxResult queryLcljItemInfo(LcljInfoLocalReq lcljInfoLocalReq) {
      AjaxResult ajaxResult = AjaxResult.success();
      boolean flag = true;

      try {
         if (flag && lcljInfoLocalReq == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isBlank(lcljInfoLocalReq.getAdmissionNo())) {
            flag = false;
            ajaxResult = AjaxResult.error("患者住院号不能为空");
         }

         if (flag && StringUtils.isBlank(lcljInfoLocalReq.getCpNo())) {
            flag = false;
            ajaxResult = AjaxResult.error("患者临床路径编码不能为空");
         }

         if (flag && StringUtils.isBlank(lcljInfoLocalReq.getStageCode())) {
            flag = false;
            ajaxResult = AjaxResult.error("患者临床路径阶段编码不能为空");
         }

         if (flag) {
            List<LcljItemInfo> itemInfoList = this.lcljInfoService.getLcljItemInfo(lcljInfoLocalReq);
            ajaxResult = AjaxResult.success((Object)itemInfoList);
         }
      } catch (Exception e) {
         this.log.error("查询患者临床路径基本信息出现异常,", e);
         ajaxResult = AjaxResult.error("查询患者临床路径基本信息出现异常，请联系管理员");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('docOrder:order:list')")
   @GetMapping({"/detailInfo"})
   public AjaxResult queryLcljDetailInfo(LcljInfoLocalReq lcljInfoLocalReq) {
      AjaxResult ajaxResult = AjaxResult.success();
      boolean flag = true;

      try {
         if (flag && lcljInfoLocalReq == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isBlank(lcljInfoLocalReq.getAdmissionNo())) {
            flag = false;
            ajaxResult = AjaxResult.error("患者住院号不能为空");
         }

         if (flag && StringUtils.isBlank(lcljInfoLocalReq.getCpNo())) {
            flag = false;
            ajaxResult = AjaxResult.error("患者临床路径编码不能为空");
         }

         if (flag && StringUtils.isBlank(lcljInfoLocalReq.getStageCode())) {
            flag = false;
            ajaxResult = AjaxResult.error("患者临床路径阶段编码不能为空");
         }

         if (flag) {
            List<LcljDetailInfo> detailInfoList = this.lcljInfoService.queryLcljDetailInfos(lcljInfoLocalReq);
            ajaxResult = AjaxResult.success((Object)detailInfoList);
         }
      } catch (Exception e) {
         this.log.error("查询患者临床路径基本信息出现异常,", e);
         ajaxResult = AjaxResult.error("查询患者临床路径基本信息出现异常，请联系管理员");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('docOrder:order:add')")
   @PostMapping
   public AjaxResult add(String patientId, HttpServletRequest request) {
      AjaxResult ajaxResult = AjaxResult.success();
      boolean flag = true;

      try {
         if (flag) {
            OrderSearchVo queryParam = new OrderSearchVo();
            queryParam.setPatientId(patientId);
            queryParam.setCommitFlag("0");
            queryParam.setOrderStatus("-1");
            queryParam.setOrderDoctorDepCode(SecurityUtils.getLoginUser().getUser().getDept().getDeptCode());
            List<OrderSearchVo> list = this.tdPaOrderService.selectOrderSearchVoList(queryParam);
            List<OrderSearchVo> pageList = this.tdPaOrderService.selectSubOrderSearchVoList(list);
            this.lcljInfoService.saveLcljOrderList(pageList, (List)null);
         }
      } catch (Exception e) {
         this.log.error("查询患者临床路径基本信息出现异常,", e);
         ajaxResult = AjaxResult.error("查询患者临床路径基本信息出现异常，请联系管理员");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('docOrder:order:list')")
   @GetMapping({"/ptCpFlag"})
   public AjaxResult getPatCpFlag(String admissionNo) {
      AjaxResult ajaxResult = AjaxResult.success();

      try {
         if (StringUtils.isNotBlank(admissionNo)) {
            Medicalinformation medicalinformation = this.medicalinfoService.selectMedicalinfoByPatientId(admissionNo);
            ajaxResult.put("cpFlag", medicalinformation.getCpFlag());
            String lcljFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0076");
            ajaxResult.put("lcljFlag", lcljFlag);
         } else {
            ajaxResult = AjaxResult.error("患者住院号不能为空！");
         }
      } catch (Exception e) {
         this.log.error("查询患者是否在径出现异常，", e);
         ajaxResult = AjaxResult.error("查询患者是否在径出现异常，请联系管理员");
      }

      return ajaxResult;
   }
}
