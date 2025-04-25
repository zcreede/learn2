package com.emr.project.docOrder.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.emr.common.exception.YbException;
import com.emr.common.utils.DateUtils;
import com.emr.common.utils.HttpPostGetUtil;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.common.utils.StringUtils;
import com.emr.project.docOrder.domain.req.FsiDiagnoseDto;
import com.emr.project.docOrder.domain.req.FsiEncounterDto;
import com.emr.project.docOrder.domain.req.FsiOperationDto;
import com.emr.project.docOrder.domain.req.FsiOrderDto;
import com.emr.project.docOrder.domain.req.ItemYbInfo;
import com.emr.project.docOrder.domain.req.RuleAnalysisDataReq;
import com.emr.project.docOrder.domain.req.RuleAnalysisDataResp;
import com.emr.project.docOrder.domain.req.RuleAnalysisPatientDto;
import com.emr.project.docOrder.domain.req.RuleAnalysisQueryResp;
import com.emr.project.docOrder.domain.req.RuleAnalysisResp;
import com.emr.project.docOrder.domain.req.RuleAnalysisVo;
import com.emr.project.docOrder.domain.vo.OrderSaveVo;
import com.emr.project.docOrder.mapper.RuleAnalysisMapper;
import com.emr.project.docOrder.service.IRuleAnalysisService;
import com.emr.project.pat.domain.Visitinfo;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.service.ISysEmrConfigService;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RuleAnalysisServiceImpl implements IRuleAnalysisService {
   private final Logger logger = LoggerFactory.getLogger(this.getClass());
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;
   @Autowired
   private RuleAnalysisMapper ruleAnalysisMapper;

   public RuleAnalysisVo ruleAnalysis(List orderSaveVoList, Visitinfo visitinfo) throws Exception {
      RuleAnalysisVo vo = new RuleAnalysisVo();
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      String url = this.sysEmrConfigService.selectSysEmrConfigByKey("013901");
      RuleAnalysisDataReq dataReq = new RuleAnalysisDataReq();
      dataReq.setJzlx("2");
      dataReq.setMsgid(String.valueOf(SnowIdUtils.uniqueLong()));
      RuleAnalysisPatientDto patientDto = this.ruleAnalysisMapper.selectPatientInfo(visitinfo.getPatientId());
      FsiEncounterDto fsiEncounterDto = this.ruleAnalysisMapper.selectVisitInfo(visitinfo.getPatientId());
      fsiEncounterDto.setJzid(patientDto.getCurr_jzid());
      fsiEncounterDto.setDr_codg(sysUser.getUserName());
      fsiEncounterDto.setAdm_dept_codg(sysUser.getDept().getDeptCode());
      fsiEncounterDto.setAdm_dept_name(sysUser.getDept().getDeptName());
      fsiEncounterDto.setDscg_dept_codg(sysUser.getDept().getDeptCode());
      fsiEncounterDto.setDscg_dept_name(sysUser.getDept().getDeptName());
      BigDecimal total = (BigDecimal)orderSaveVoList.stream().map(OrderSaveVo::getOrderTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
      fsiEncounterDto.setMedfee_sumamt(total);
      fsiEncounterDto.setOwnpay_amt(total);
      fsiEncounterDto.setSelfpay_amt(total);
      List<FsiDiagnoseDto> fsi_diagnose_dtos = this.ruleAnalysisMapper.selectDiagInfo(visitinfo.getPatientId());
      if (CollectionUtils.isNotEmpty(fsi_diagnose_dtos)) {
         FsiDiagnoseDto fsiDiagnoseDto = (FsiDiagnoseDto)fsi_diagnose_dtos.get(0);
         fsiEncounterDto.setDscg_main_dise_codg(fsiDiagnoseDto.getDise_codg());
         fsiEncounterDto.setDscg_main_dise_name(fsiDiagnoseDto.getDise_name());
      }

      fsiEncounterDto.setFsi_diagnose_dtos(fsi_diagnose_dtos);
      List<String> cpNoList = (List)orderSaveVoList.stream().map(OrderSaveVo::getCpNo).collect(Collectors.toList());
      List<ItemYbInfo> itemYbInfoList = this.ruleAnalysisMapper.selectYbInfo(cpNoList);
      List<FsiOrderDto> fsi_order_dtos = new ArrayList();

      for(OrderSaveVo orderSaveVo : orderSaveVoList) {
         FsiOrderDto orderDto = new FsiOrderDto();
         orderDto.setRx_id(String.valueOf(SnowIdUtils.uniqueLong()));
         orderDto.setRxno(SnowIdUtils.uniqueLongHex());
         orderDto.setGrpno(String.valueOf(orderSaveVo.getOrderGroupNo()));
         orderDto.setLong_drord_flag("1".equals(orderSaveVo.getOrderType()) ? "1" : "0");
         if (orderSaveVo.getOrderClassCode().equals("01")) {
            orderDto.setHilist_type("101");
            orderDto.setChrg_type("09");
            orderDto.setXmlx("1");
            orderDto.setSfxmlbFp("01");
         } else {
            orderDto.setHilist_type("201");
            orderDto.setChrg_type("03");
            orderDto.setXmlx("2");
            orderDto.setSfxmlbFp("05");
         }

         orderDto.setDrord_bhvr("3");
         ItemYbInfo itemYbInfo = (ItemYbInfo)itemYbInfoList.stream().filter((t) -> t.getCpNo().equals(orderSaveVo.getCpNo())).findFirst().orElse((Object)null);
         if (itemYbInfo != null) {
            orderDto.setHilist_code(itemYbInfo.getYbCode());
            orderDto.setHilist_name(itemYbInfo.getYbName());
            orderDto.setHilist_lv(StringUtils.isEmpty(itemYbInfo.getYbLv()) ? "2" : itemYbInfo.getYbLv());
            orderDto.setYbzfbl(itemYbInfo.getYbZfbl());
         } else {
            orderDto.setHilist_code(orderSaveVo.getCpNo());
            orderDto.setHilist_name(orderSaveVo.getCpName());
            orderDto.setHilist_lv("2");
            orderDto.setYbzfbl(BigDecimal.ZERO);
         }

         orderDto.setHilist_pric(orderSaveVo.getPrice());
         orderDto.setHosplist_code(orderSaveVo.getCpNo());
         orderDto.setHosplist_name(orderSaveVo.getCpName());
         orderDto.setCnt(orderSaveVo.getOrderDose());
         orderDto.setPric(orderSaveVo.getPrice());
         orderDto.setSumamt(orderSaveVo.getOrderTotal());
         orderDto.setOwnpay_amt(orderSaveVo.getOrderTotal());
         orderDto.setSelfpay_amt(orderSaveVo.getOrderTotal());
         orderDto.setSpec(StringUtils.isEmpty(orderSaveVo.getStandard()) ? "次" : orderSaveVo.getStandard());
         orderDto.setSpec_unt(StringUtils.isEmpty(orderSaveVo.getUnit()) ? "次" : orderSaveVo.getUnit());
         orderDto.setDrord_begn_date(DateUtils.dateFormat(orderSaveVo.getOrderStartTime(), "yyyy-MM-dd HH:mm:ss"));
         orderDto.setDrord_dept_codg(sysUser.getDept().getDeptCode());
         orderDto.setDrord_dept_name(sysUser.getDept().getDeptName());
         orderDto.setDrord_dr_codg(sysUser.getUserName());
         orderDto.setDrord_dr_name(sysUser.getNickName());
         orderDto.setDrord_dr_profttl("234");
         orderDto.setCurr_drord_flag("1");
         fsi_order_dtos.add(orderDto);
      }

      fsiEncounterDto.setFsi_order_dtos(fsi_order_dtos);
      List<FsiOperationDto> fsi_operation_dtos = new ArrayList();
      fsiEncounterDto.setFsi_operation_dtos(fsi_operation_dtos);
      patientDto.setFsi_encounter_dtos(fsiEncounterDto);
      dataReq.setPatient_dtos(patientDto);
      String reqjson = JSONObject.toJSONString(dataReq);
      this.logger.info("发起事前审核请求参数，{}", reqjson);
      JSONObject js = JSONObject.parseObject(reqjson);
      String body = HttpPostGetUtil.sendPost2(url, js, StandardCharsets.UTF_8.name());
      this.logger.info("发起事前审核三方返回值，{}", body);
      RuleAnalysisResp resp = (RuleAnalysisResp)JSONObject.parseObject(body, RuleAnalysisResp.class);
      if (resp == null) {
         throw new YbException("发起事前审核三方返回数据为空，" + body);
      } else {
         String code = resp.getCode();
         if (!code.equals("200")) {
            throw new YbException("发起事前审核三方返回失败，" + resp.getMsg());
         } else {
            List<RuleAnalysisDataResp> data = resp.getData();
            String respUrl = resp.getUrl();
            if (!data.isEmpty() && StringUtils.isNotEmpty(respUrl)) {
               vo.setUrl(respUrl);
               vo.setMsgid(dataReq.getMsgid());
            }

            return vo;
         }
      }
   }

   public void ruleAnalysisQuery(String msgid) throws Exception {
      String url = this.sysEmrConfigService.selectSysEmrConfigByKey("013902") + msgid;
      this.logger.info("查询审核反馈结果url,{}", url);
      String result = HttpPostGetUtil.sendGet(url);
      this.logger.info("查询审核反馈结果三方返回值，{}", result);
      if (StringUtils.isEmpty(result)) {
         throw new YbException("查询审核反馈结果三方返回值为空！");
      } else {
         RuleAnalysisQueryResp resp = (RuleAnalysisQueryResp)JSONObject.parseObject(result, RuleAnalysisQueryResp.class);
         if (resp == null) {
            throw new YbException("查询审核反馈结果三方返回值为空！");
         } else {
            String code = resp.getCode();
            if (!"0".equals(code)) {
               throw new YbException("查询审核反馈结果三方返回异常！" + resp.getMsg());
            } else {
               String data = resp.getData();
               if (!"1".equals(data)) {
                  throw new YbException("查询审核反馈结果三方返回异常！" + resp.getMsg());
               }
            }
         }
      }
   }
}
