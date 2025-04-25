package com.emr.project.docOrder.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.http.HttpUtils;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.project.docOrder.domain.TdPaOrderDetail;
import com.emr.project.docOrder.domain.req.LCLJOrderVary;
import com.emr.project.docOrder.domain.req.LcljInfoLocalReq;
import com.emr.project.docOrder.domain.req.LcljInfoReq;
import com.emr.project.docOrder.domain.req.LcljSaveDetailInfo;
import com.emr.project.docOrder.domain.req.LcljSaveParam;
import com.emr.project.docOrder.domain.resp.LcljBaseInfo;
import com.emr.project.docOrder.domain.resp.LcljDetailInfo;
import com.emr.project.docOrder.domain.resp.LcljItemInfo;
import com.emr.project.docOrder.domain.vo.OrderSearchVo;
import com.emr.project.docOrder.domain.vo.TdPaOrderDetailVo;
import com.emr.project.docOrder.service.ILCLJInfoService;
import com.emr.project.docOrder.service.ITdPaOrderDetailService;
import com.emr.project.operation.domain.Medicalinformation;
import com.emr.project.operation.service.IMedicalinfoService;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.service.ISysEmrConfigService;
import com.emr.project.tmpm.domain.ExamPart;
import com.emr.project.tmpm.domain.TmPmSpec;
import com.emr.project.tmpm.service.IExamPartService;
import com.emr.project.tmpm.service.ITmPmSpecService;
import com.emr.project.webservice.utils.SignUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LCLJInfoServiceImpl implements ILCLJInfoService {
   private static final Logger log = LoggerFactory.getLogger(LCLJInfoServiceImpl.class);
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;
   @Autowired
   private ITdPaOrderDetailService tdPaOrderDetailService;
   @Autowired
   private IMedicalinfoService medicalinfoService;
   @Autowired
   private ITmPmSpecService tmPmSpecService;
   @Autowired
   private IExamPartService examPartService;

   public LcljBaseInfo getLcljBaseInfo(String admissionNo) throws Exception {
      LcljBaseInfo lcljBaseInfo = null;
      String lcljFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0076");
      String lcljCj = this.sysEmrConfigService.selectSysEmrConfigByKey("007601");
      if (StringUtils.isNotBlank(lcljFlag) && lcljFlag.equals("1") && StringUtils.isNotBlank(lcljCj) && lcljCj.equals("LB")) {
         String urlBaseInfo = this.sysEmrConfigService.selectSysEmrConfigByKey("007607");
         LcljInfoReq lcljInfoReq = new LcljInfoReq();
         lcljInfoReq.setZyh(admissionNo);
         lcljInfoReq.setProjectCode("HIS");
         lcljInfoReq.setSign("372C4DCE634A45A184E8CACAD37A8547");
         HashMap<String, Object> paramMap = (HashMap)JSONObject.parseObject(JSONObject.toJSONString(lcljInfoReq), HashMap.class);
         String sign = SignUtil.encryptString(paramMap, lcljInfoReq.getSign());
         lcljInfoReq.setSign(sign);
         paramMap = (HashMap)JSONObject.parseObject(JSONObject.toJSONString(lcljInfoReq), HashMap.class);
         String urlBaseInfo2 = urlBaseInfo + "?" + SignUtil.mapToString(paramMap);
         String resStr = HttpUtils.sendPost(urlBaseInfo2, JSONObject.toJSONString(lcljInfoReq), "application/json");
         AjaxResult res = (AjaxResult)JSONObject.parseObject(resStr, AjaxResult.class);
         if (res.get("code").equals(200)) {
            JSONObject resOb = (JSONObject)res.get("data");
            lcljBaseInfo = new LcljBaseInfo();
            lcljBaseInfo.setStageCode(resOb.getString("cpPatientLjjd"));
            lcljBaseInfo.setStageName(resOb.getString("cpPatientLjjdMc"));
            lcljBaseInfo.setCurrDay(resOb.getString("rjts"));
            lcljBaseInfo.setCpNo(resOb.getString("cpPatientLjbm"));
            lcljBaseInfo.setCpName(resOb.getString("cpPatientLjmc"));
            lcljBaseInfo.setSubCpNo(resOb.getString("cpPatientZljbm"));
            lcljBaseInfo.setSubCpName(resOb.getString("cpPatientZljmc"));
         }
      }

      return lcljBaseInfo;
   }

   public List getLcljItemInfo(LcljInfoLocalReq lcljInfoLocalReq) throws Exception {
      List<LcljItemInfo> list = new ArrayList(1);
      String lcljFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0076");
      String lcljCj = this.sysEmrConfigService.selectSysEmrConfigByKey("007601");
      if (StringUtils.isNotBlank(lcljFlag) && lcljFlag.equals("1") && StringUtils.isNotBlank(lcljCj) && lcljCj.equals("LB")) {
         String urlBaseInfo = this.sysEmrConfigService.selectSysEmrConfigByKey("007603");
         LcljInfoReq lcljInfoReq = new LcljInfoReq();
         lcljInfoReq.setProjectCode("HIS");
         lcljInfoReq.setSign("372C4DCE634A45A184E8CACAD37A8547");
         lcljInfoReq.setStageBmchr(lcljInfoLocalReq.getStageCode());
         lcljInfoReq.setStageGsljchr(lcljInfoLocalReq.getCpNo());
         lcljInfoReq.setStageGszljchr(lcljInfoLocalReq.getSubCpNo());
         lcljInfoReq.setType("0");
         HashMap<String, Object> paramMap = (HashMap)JSONObject.parseObject(JSONObject.toJSONString(lcljInfoReq), HashMap.class);
         String sign = SignUtil.encryptString(paramMap, lcljInfoReq.getSign());
         lcljInfoReq.setSign(sign);
         paramMap = (HashMap)JSONObject.parseObject(JSONObject.toJSONString(lcljInfoReq), HashMap.class);
         String urlBaseInfo2 = urlBaseInfo + "?" + SignUtil.mapToString(paramMap);
         String resStr = HttpUtils.sendPost(urlBaseInfo2, JSONObject.toJSONString(lcljInfoReq), "application/json");
         AjaxResult res = (AjaxResult)JSONObject.parseObject(resStr, AjaxResult.class);
         if (res.get("code").equals(200)) {
            JSONArray resArr = (JSONArray)res.get("data");

            for(int i = 0; i < resArr.size(); ++i) {
               JSONObject resObj = (JSONObject)resArr.get(i);
               LcljItemInfo itemInfo = new LcljItemInfo();
               itemInfo.setItemCd(resObj.getString("ljmxBmchr"));
               itemInfo.setItemName(resObj.getString("ljmxMcchr"));
               list.add(itemInfo);
            }
         }
      }

      return list;
   }

   public List queryLcljDetailInfos(LcljInfoLocalReq lcljInfoLocalReq) throws Exception {
      List<LcljDetailInfo> resList = this.getDetailInfoListFromLCLJ(lcljInfoLocalReq);
      List<String> lcljCpNoList = (List)resList.stream().map((t) -> t.getLcljCpNo()).collect(Collectors.toList());
      Map<String, List<LcljDetailInfo>> resItemGroupMap = (Map)resList.stream().collect(Collectors.groupingBy((t) -> t.getCpStageCode() + t.getCpItemCode() + t.getOrderGroupNo()));
      TdPaOrderDetailVo param = new TdPaOrderDetailVo();
      param.setLcljCpNoList(lcljCpNoList);
      param.setPatientId(lcljInfoLocalReq.getAdmissionNo());
      List<TdPaOrderDetail> orderDetailList = this.tdPaOrderDetailService.selectTdPaOrderDetailListByPatientId(param);
      List<String> lcljCpNoListOrder = (List<String>)(CollectionUtils.isNotEmpty(orderDetailList) ? (List)orderDetailList.stream().map((t) -> t.getCpNo()).collect(Collectors.toList()) : new ArrayList(1));
      List<LcljDetailInfo> resList2 = new ArrayList(1);

      for(LcljDetailInfo detailInfo : resList) {
         if (lcljCpNoListOrder.contains(detailInfo.getLcljCpNo())) {
            if (lcljInfoLocalReq.getViewFlag().equals("2")) {
               detailInfo.setUsedFlag("1");
               resList2.add(detailInfo);
            }
         } else {
            boolean itemGroupFlag = false;
            List<LcljDetailInfo> itemGroupList = (List)resItemGroupMap.get(detailInfo.getCpStageCode() + detailInfo.getCpItemCode() + detailInfo.getOrderGroupNo());
            if (CollectionUtils.isNotEmpty(itemGroupList)) {
               for(LcljDetailInfo detailInfo1 : itemGroupList) {
                  if (lcljCpNoListOrder.contains(detailInfo1.getLcljCpNo())) {
                     itemGroupFlag = true;
                     break;
                  }
               }
            }

            if (itemGroupFlag) {
               if (lcljInfoLocalReq.getViewFlag().equals("2")) {
                  detailInfo.setUsedFlag("1");
                  resList2.add(detailInfo);
               }
            } else {
               resList2.add(detailInfo);
            }
         }
      }

      if (CollectionUtils.isNotEmpty(resList2)) {
         List<String> sampleNoList = (List)resList2.stream().filter((t) -> StringUtils.isNotBlank(t.getSpecCd())).map((t) -> t.getSpecCd()).collect(Collectors.toList());
         List<String> examPartCdList = (List)resList2.stream().filter((t) -> StringUtils.isNotBlank(t.getExamPartCd())).map((t) -> t.getExamPartCd()).collect(Collectors.toList());
         List<TmPmSpec> specList = (List<TmPmSpec>)(CollectionUtils.isNotEmpty(sampleNoList) ? this.tmPmSpecService.selectBySpecCdList(sampleNoList) : new ArrayList(1));
         List<ExamPart> examPartList = (List<ExamPart>)(CollectionUtils.isNotEmpty(examPartCdList) ? this.examPartService.selectByExamPartCdList(examPartCdList) : new ArrayList(1));
         Map<String, TmPmSpec> specMap = (Map)specList.stream().collect(Collectors.toMap((t) -> t.getSpecCd(), Function.identity()));
         Map<String, ExamPart> examPartMap = (Map)examPartList.stream().collect(Collectors.toMap((t) -> t.getExamPartCd(), Function.identity()));

         for(LcljDetailInfo detailInfo : resList2) {
            if (StringUtils.isNotBlank(detailInfo.getSpecCd())) {
               TmPmSpec spec = (TmPmSpec)specMap.get(detailInfo.getSpecCd());
               detailInfo.setSpecName(spec != null ? spec.getSpecName() : null);
            }

            if (StringUtils.isNotBlank(detailInfo.getExamPartCd())) {
               ExamPart examPart = (ExamPart)examPartMap.get(detailInfo.getExamPartCd());
               detailInfo.setExamPartName(examPart != null ? examPart.getExamPartName() : null);
            }
         }
      }

      return resList2;
   }

   private List getDetailInfoListFromLCLJ(LcljInfoLocalReq lcljInfoLocalReq) throws Exception {
      List<LcljDetailInfo> resList = new ArrayList(1);
      String lcljFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0076");
      String lcljCj = this.sysEmrConfigService.selectSysEmrConfigByKey("007601");
      if (StringUtils.isNotBlank(lcljFlag) && lcljFlag.equals("1") && StringUtils.isNotBlank(lcljCj) && lcljCj.equals("LB")) {
         String urlBaseInfo = this.sysEmrConfigService.selectSysEmrConfigByKey("007602");
         LcljInfoReq lcljInfoReq = new LcljInfoReq();
         lcljInfoReq.setProjectCode("HIS");
         lcljInfoReq.setSign("372C4DCE634A45A184E8CACAD37A8547");
         lcljInfoReq.setStageGsljchr(lcljInfoLocalReq.getCpNo());
         lcljInfoReq.setStageBmchr(lcljInfoLocalReq.getItemCode());
         lcljInfoReq.setStageGszljchr(lcljInfoLocalReq.getSubCpNo());
         lcljInfoReq.setJdbh(lcljInfoLocalReq.getStageCode());
         lcljInfoReq.setType("by");
         HashMap<String, Object> paramMap = (HashMap)JSONObject.parseObject(JSONObject.toJSONString(lcljInfoReq), HashMap.class);
         String sign = SignUtil.encryptString(paramMap, lcljInfoReq.getSign());
         lcljInfoReq.setSign(sign);
         paramMap = (HashMap)JSONObject.parseObject(JSONObject.toJSONString(lcljInfoReq), HashMap.class);
         String urlBaseInfo2 = urlBaseInfo + "?" + SignUtil.mapToString(paramMap);
         String resStr = HttpUtils.sendPost(urlBaseInfo2, JSONObject.toJSONString(lcljInfoReq), "application/json");
         AjaxResult res = (AjaxResult)JSONObject.parseObject(resStr, AjaxResult.class);
         if (res.get("code").equals(200)) {
            JSONArray resArr = (JSONArray)res.get("data");

            for(int i = 0; i < resArr.size(); ++i) {
               JSONObject resObj = (JSONObject)resArr.get(i);
               LcljDetailInfo detailInfo = new LcljDetailInfo();
               detailInfo.setOrderType(resObj.getString("ljyzYzlxchr").trim());
               detailInfo.setOrderClassCode(resObj.getString("ljyzYzlbchr").trim());
               detailInfo.setOrderGroupNo(resObj.getInteger("ljyzYzzhchr"));
               detailInfo.setMasterOrder(resObj.getString("ljyzSubchr").trim().equals("1") ? "2" : "1");
               detailInfo.setCpNo(resObj.getString("ljyzLcxmbhchr").trim());
               detailInfo.setCpName(resObj.getString("ljyzLcxmmcchr").trim());
               detailInfo.setStandard(StringUtils.isNotBlank(resObj.getString("ljyzGgchr")) ? resObj.getString("ljyzGgchr").trim() : resObj.getString("ljyzGgchr"));
               detailInfo.setOrderActualUsage(resObj.getBigDecimal("ljyzYl"));
               detailInfo.setUsageUnit(StringUtils.isNotBlank(resObj.getString("ljyzYldw")) ? resObj.getString("ljyzYldw").trim() : resObj.getString("ljyzYldw"));
               detailInfo.setOrderUsageWay(StringUtils.isNotBlank(resObj.getString("ljyzYfbhchr")) ? resObj.getString("ljyzYfbhchr").trim() : resObj.getString("ljyzYfbhchr"));
               detailInfo.setOrderUsageWayName(StringUtils.isNotBlank(resObj.getString("ljyzYfmcchr")) ? resObj.getString("ljyzYfmcchr").trim() : resObj.getString("ljyzYfmcchr"));
               detailInfo.setOrderFreq(StringUtils.isNotBlank(resObj.getString("ljyzPlbhchr")) ? resObj.getString("ljyzPlbhchr").trim() : resObj.getString("ljyzPlbhchr"));
               detailInfo.setOrderFreqName(StringUtils.isNotBlank(resObj.getString("ljyzPlmcchr")) ? resObj.getString("ljyzPlmcchr").trim() : resObj.getString("ljyzPlmcchr"));
               detailInfo.setOrderUsageDays(resObj.getBigDecimal(""));
               detailInfo.setOrderDose(resObj.getBigDecimal("ljyzFaint"));
               detailInfo.setUnit(StringUtils.isNotBlank(resObj.getString("ljyzDwchr")) ? resObj.getString("ljyzDwchr").trim() : resObj.getString("ljyzDwchr"));
               detailInfo.setPrice(resObj.getBigDecimal("ljyzDjdec"));
               detailInfo.setOrderTotal(resObj.getBigDecimal("ljyzZje"));
               detailInfo.setRequiredFlag(resObj.getString("ljyzBxztchr"));
               detailInfo.setDrugClassCode(StringUtils.isNotBlank(resObj.getString("ljyzYplx")) ? resObj.getString("ljyzYplx").trim() : resObj.getString("ljyzYplx"));
               detailInfo.setPerformDepCode(StringUtils.isNotBlank(resObj.getString("ljyzZxkschr")) ? resObj.getString("ljyzZxkschr").trim() : resObj.getString("ljyzZxkschr"));
               detailInfo.setPerformDepName(StringUtils.isNotBlank(resObj.getString("ljyzZxksmcchr")) ? resObj.getString("ljyzZxksmcchr").trim() : resObj.getString("ljyzZxksmcchr"));
               detailInfo.setDetailPerformDepCode(detailInfo.getPerformDepCode());
               detailInfo.setDetailPerformDepName(detailInfo.getPerformDepName());
               detailInfo.setLcljItemName(resObj.getString("ljyzLjmcchr"));
               String flagFirst = resObj.getString("flagFirst");
               flagFirst = StringUtils.isNotBlank(flagFirst) && flagFirst.trim().equals("1") ? "2" : "1";
               detailInfo.setTakeDrugMode(flagFirst);
               detailInfo.setDoctorInstructions(resObj.getString("ljyzYzsmchr"));
               String outHavaDrugFlag = StringUtils.isNotBlank(resObj.getString("zbypbz")) && resObj.getString("zbypbz").trim().equals("1") ? "3" : resObj.getString("zbypbz");
               detailInfo.setOutHavaDrugFlag(outHavaDrugFlag);
               detailInfo.setExamPartCd(resObj.getString("ljyzJcbw"));
               detailInfo.setExamPartName(resObj.getString(""));
               detailInfo.setSpecCd(resObj.getString("ljyzJybb"));
               detailInfo.setSpecName(resObj.getString(""));
               detailInfo.setDistributionFlag(resObj.getString("ljyzPsbz"));
               detailInfo.setLcljCpNo(resObj.getString("ljyzBhchr"));
               detailInfo.setLcljCpName(resObj.getString("ljyzLcxmmcchr").trim());
               detailInfo.setStockNo(StringUtils.isNotBlank(resObj.getString("ljyzKcbh")) ? Long.valueOf(resObj.getString("ljyzKcbh")) : null);
               detailInfo.setCpStageCode(resObj.getString("ljyzLjjdchr"));
               detailInfo.setCpItemCode(resObj.getString("ljyzGsmxchr"));
               resList.add(detailInfo);
            }
         }
      }

      return resList;
   }

   public List saveLcljOrderList(List orderSaveVoList, List orderCpVaryList) throws Exception {
      List<OrderSearchVo> resList = null;
      SysUser user = SecurityUtils.getLoginUser().getUser();
      String lcljFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0076");
      String lcljCj = this.sysEmrConfigService.selectSysEmrConfigByKey("007601");
      Medicalinformation medicalinformation = this.medicalinfoService.selectMedicalinfoByPatientId(((OrderSearchVo)orderSaveVoList.get(0)).getPatientId());
      if (StringUtils.isNotBlank(lcljFlag) && lcljFlag.equals("1") && StringUtils.isNotBlank(medicalinformation.getCpFlag()) && medicalinformation.getCpFlag().equals("1") && CollectionUtils.isNotEmpty(orderSaveVoList) && StringUtils.isNotBlank(lcljCj) && lcljCj.equals("LB")) {
         String urlBaseInfo = this.sysEmrConfigService.selectSysEmrConfigByKey("007604");
         List<LcljSaveDetailInfo> paramObjList = this.orderToLcljSave(orderSaveVoList, orderCpVaryList, medicalinformation);
         LcljSaveParam lcljSaveParam = new LcljSaveParam();
         lcljSaveParam.setSign("372C4DCE634A45A184E8CACAD37A8547");
         lcljSaveParam.setProjectCode("HIS");
         lcljSaveParam.setOperCode(user.getUserName());
         lcljSaveParam.setZyh(((OrderSearchVo)orderSaveVoList.get(0)).getPatientId());
         lcljSaveParam.setCpPatientYzgxb(paramObjList);
         HashMap<String, Object> paramMap = new HashMap(1);
         paramMap.put("operCode", lcljSaveParam.getOperCode());
         paramMap.put("zyh", ((OrderSearchVo)orderSaveVoList.get(0)).getPatientId());
         String sign = SignUtil.encryptString(paramMap, lcljSaveParam.getSign());
         lcljSaveParam.setSign(sign);
         log.info("sendPost - {}", JSONArray.toJSONString(lcljSaveParam));
         String resStr = HttpUtils.sendPost(urlBaseInfo, JSONArray.toJSONString(lcljSaveParam), "application/json");
         AjaxResult res = (AjaxResult)JSONObject.parseObject(resStr, AjaxResult.class);
         if (res.get("code").equals(200) && res.get("data") != null) {
            JSONArray array = JSONArray.parseArray(res.get("data").toString());
            List<LcljSaveDetailInfo> list = array.toJavaList(LcljSaveDetailInfo.class);
            resList = new ArrayList(list.size());

            for(LcljSaveDetailInfo detailInfo : list) {
               OrderSearchVo orderSearchVo = new OrderSearchVo();
               orderSearchVo.setOrderType(detailInfo.getBrljYzgxYzlxchr());
               orderSearchVo.setCpName(detailInfo.getBrljYzgxNr());
               String brljYzgxXhchr = detailInfo.getBrljYzgxXhchr();
               String[] strArr = brljYzgxXhchr.split("_");
               orderSearchVo.setOrderNo(strArr[0]);
               orderSearchVo.setOrderGroupNo(strArr[1]);
               orderSearchVo.setOrderGroupSortNumber(strArr[2]);
               orderSearchVo.setStandard(detailInfo.getBrljYzgxGgchr());
               resList.add(orderSearchVo);
            }
         }
      }

      return resList;
   }

   private List orderToLcljSave(List orderSaveVoList, List orderCpVaryList, Medicalinformation medicalinformation) {
      Map<String, OrderSearchVo> orderCpVaryMap = (Map<String, OrderSearchVo>)(CollectionUtils.isNotEmpty(orderCpVaryList) ? (Map)orderCpVaryList.stream().collect(Collectors.toMap((t) -> t.getOrderNo() + "_" + t.getOrderGroupNo() + "_" + t.getOrderGroupSortNumber(), Function.identity())) : new HashMap(1));
      List<LcljSaveDetailInfo> lcljSaveDetailInfos = new ArrayList(orderSaveVoList.size());

      for(OrderSearchVo orderSaveVo : orderSaveVoList) {
         LcljSaveDetailInfo detailInfo = new LcljSaveDetailInfo();
         detailInfo.setZyh(orderSaveVo.getPatientId());
         detailInfo.setBrljYzgxXhchr(orderSaveVo.getOrderNo() + "_" + orderSaveVo.getOrderGroupNo() + "_" + orderSaveVo.getOrderGroupSortNumber());
         detailInfo.setBrljYzgxLjbmchr(medicalinformation.getCpNo());
         detailInfo.setBrljYzgxLjmcchr(medicalinformation.getCpName());
         detailInfo.setBrljYzgxLjjdchr(orderSaveVo.getCpStageCode());
         detailInfo.setBrljYzgxLjmxbm(orderSaveVo.getCpItemCode());
         detailInfo.setBrljYzgxBhchr(orderSaveVo.getLcljCpNo());
         detailInfo.setBrljYzgxYzbhchr(orderSaveVo.getCpNo());
         detailInfo.setBrljYzgxYzlxchr(orderSaveVo.getOrderType());
         detailInfo.setBrljYzgxYzxhchr(orderSaveVo.getOrderSortNumber());
         detailInfo.setBrljYzgxYzxhXhchr(orderSaveVo.getOrderGroupSortNumber());
         detailInfo.setBrljYzgxTs(orderSaveVo.getOrderUsageDays() != null ? orderSaveVo.getOrderUsageDays().toString() : null);
         detailInfo.setBrljYzgxGgchr(orderSaveVo.getStandard());
         detailInfo.setBrljYzgxDwchr(orderSaveVo.getUnit());
         detailInfo.setBrljYzgxPlchr(orderSaveVo.getOrderFreq());
         detailInfo.setBrljYzgxJlchr(orderSaveVo.getOrderActualUsage() != null ? orderSaveVo.getOrderActualUsage().toString() : null);
         detailInfo.setBrljYzgxYfchr(orderSaveVo.getOrderUsageWay());
         detailInfo.setBrljYzgxNr(orderSaveVo.getCpName());
         detailInfo.setBrljYzgxYzlbchr(orderSaveVo.getOrderClassCode());
         detailInfo.setBrljYzgxZxks(orderSaveVo.getDetailPerformDepCode());
         OrderSearchVo orderCpVary = (OrderSearchVo)orderCpVaryMap.get(detailInfo.getBrljYzgxXhchr());
         if (orderCpVary != null) {
            LCLJOrderVary lcljOrderVary = new LCLJOrderVary();
            lcljOrderVary.setBrljById(orderSaveVo.getPatientId());
            lcljOrderVary.setBrljByLjjd(orderSaveVo.getCpStageCode());
            lcljOrderVary.setBrljByZdfl("1");
            lcljOrderVary.setBrljByBm(orderCpVary.getChangeReason());
            lcljOrderVary.setBrljByMc(orderCpVary.getChangeReasonName());
            lcljOrderVary.setBrljByGsby(orderCpVary.getChangeClass());
            lcljOrderVary.setBrljByGlxh("Z");
            lcljOrderVary.setBrljByYzbh(orderSaveVo.getOrderNo() + orderSaveVo.getOrderGroupSortNumber());
            lcljOrderVary.setBrljByYzMC(orderSaveVo.getCpName());
            detailInfo.setPatientOrderVary(lcljOrderVary);
         }

         lcljSaveDetailInfos.add(detailInfo);
      }

      return lcljSaveDetailInfos;
   }
}
