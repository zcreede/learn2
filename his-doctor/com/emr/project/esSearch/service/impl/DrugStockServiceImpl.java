package com.emr.project.esSearch.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.emr.common.utils.SnowIdUtils;
import com.emr.common.utils.StringUtils;
import com.emr.project.common.service.ICommonService;
import com.emr.project.docOrder.domain.vo.ItemIsOpenResVo;
import com.emr.project.esSearch.domain.DrugAndClin;
import com.emr.project.esSearch.domain.TmPaDrugPre;
import com.emr.project.esSearch.domain.TmPaDrugPreDetail;
import com.emr.project.esSearch.domain.TmPaFreeze;
import com.emr.project.esSearch.domain.req.DrugAndStockSearchReq;
import com.emr.project.esSearch.domain.req.DrugStockSearchReq;
import com.emr.project.esSearch.domain.req.DrugStockUpdateReq;
import com.emr.project.esSearch.domain.req.FreezeQueryReq;
import com.emr.project.esSearch.domain.vo.DrugAndClinSearchVo;
import com.emr.project.esSearch.mapper.DrugStockMapper;
import com.emr.project.esSearch.service.IDrugStockService;
import com.emr.project.his.domain.DrugCheck;
import com.emr.project.his.domain.vo.DrugDoseVo;
import com.emr.project.his.domain.vo.PharmacyVo;
import com.emr.project.system.domain.SysUser;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DrugStockServiceImpl implements IDrugStockService {
   protected final Logger log = LoggerFactory.getLogger(this.getClass());
   @Autowired
   private DrugStockMapper drugStockMapper;
   @Autowired
   private RocketMQTemplate rocketMQTemplate;
   @Autowired
   private ICommonService commonService;

   public List selectDrugList(List execDeptCdList, String herbalFlag) throws Exception {
      List<DrugAndClin> list = null;
      if (CollectionUtils.isNotEmpty(execDeptCdList)) {
         list = this.drugStockMapper.selectDrugList(execDeptCdList, herbalFlag);
      }

      return list;
   }

   public List selectFreezeList(FreezeQueryReq req) throws Exception {
      return this.drugStockMapper.selectFreezeList(req);
   }

   private DrugStockUpdateReq operDrugDoseVo(SysUser sysUser, List drugDoseVoList, List freezeListDb) throws Exception {
      Map<String, Map<String, DrugDoseVo>> orderNoTakeDrugListIdMapDrugCdMap = new HashMap(1);
      Map<String, List<DrugDoseVo>> orderNoTakeDrugListIdMap = (Map)drugDoseVoList.stream().collect(Collectors.groupingBy((t) -> t.getOrderNo() + (t.getTakeDrugListId() == null ? "" : t.getTakeDrugListId().toString())));

      for(String orderNoTakeDrugListId : orderNoTakeDrugListIdMap.keySet()) {
         List<DrugDoseVo> list = (List)orderNoTakeDrugListIdMap.get(orderNoTakeDrugListId);
         Map<String, List<DrugDoseVo>> drugCdListMap = (Map)list.stream().collect(Collectors.groupingBy((t) -> t.getYpbm() + "_" + t.getYfbm()));
         Map<String, DrugDoseVo> drugCdMap = new HashMap(1);

         for(String drugCd : drugCdListMap.keySet()) {
            List<DrugDoseVo> drugCdDeptList = (List)drugCdListMap.get(drugCd);
            BigDecimal orderDoseDrugCd = BigDecimal.ZERO;

            for(DrugDoseVo doseVo1 : drugCdDeptList) {
               orderDoseDrugCd = orderDoseDrugCd.add(doseVo1.getOrderDose());
            }

            DrugDoseVo doseVo = (DrugDoseVo)drugCdDeptList.get(0);
            doseVo.setOrderDose(orderDoseDrugCd);
            drugCdMap.put(drugCd, doseVo);
         }

         orderNoTakeDrugListIdMapDrugCdMap.put(orderNoTakeDrugListId, drugCdMap);
      }

      List<TmPaFreeze> freezeListDel = new ArrayList(1);
      List<TmPaFreeze> freezeListUpdateNum = new ArrayList(1);

      for(String orderNoTakeDrugListId : orderNoTakeDrugListIdMapDrugCdMap.keySet()) {
         Map<String, DrugDoseVo> drugCdMap = (Map)orderNoTakeDrugListIdMapDrugCdMap.get(orderNoTakeDrugListId);

         for(String drugCd : drugCdMap.keySet()) {
            DrugDoseVo drugDoseVo = (DrugDoseVo)drugCdMap.get(drugCd);
            List<TmPaFreeze> freezeListDbSub = new ArrayList(1);
            FreezeQueryReq freezeQueryReq = new FreezeQueryReq();
            freezeQueryReq.setOrgCd(sysUser.getHospital().getOrgCode());
            freezeQueryReq.setFreezeIdList(drugDoseVo.getTakeDrugListId() != null ? Arrays.asList(drugDoseVo.getTakeDrugListId()) : null);
            freezeQueryReq.setFreezeSerialList(Arrays.asList(drugDoseVo.getOrderNo()));
            freezeQueryReq.setDrugCd(drugDoseVo.getYpbm());
            List<TmPaFreeze> freezeListDbTemp = this.drugStockMapper.selectFreezeList(freezeQueryReq);
            if (CollectionUtils.isEmpty(freezeListDbTemp)) {
               freezeQueryReq.setFreezeIdList((List)null);
               freezeListDbTemp = this.drugStockMapper.selectFreezeList(freezeQueryReq);
            }

            freezeListDb.addAll(freezeListDbTemp);
            freezeListDbSub.addAll(freezeListDbTemp);
            int freezeNumSum = freezeListDbSub.stream().mapToInt((t) -> t.getFreezeNum()).sum();
            int orderDose = drugDoseVo.getOrderDose().abs().intValue();
            Integer minUnitRatio = ((TmPaFreeze)freezeListDbSub.get(0)).getMinUnitRatio();
            int minDose = (new BigDecimal(orderDose + "")).multiply(new BigDecimal(minUnitRatio.toString())).intValue();
            if (freezeNumSum < minDose) {
               throw new Exception("增加虚存，冻结数量小于增加虚存数量，异常数据！！！！！！");
            }

            for(TmPaFreeze freeze : freezeListDbSub) {
               int freezeNum = freeze.getFreezeNum();
               if (minDose >= freezeNum) {
                  minDose -= freezeNum;
                  freezeListDel.add(freeze);
               } else {
                  freezeNum -= minDose;
                  freeze.setFreezeNumNew(freezeNum);
                  freezeListUpdateNum.add(freeze);
                  minDose = 0;
               }

               if (minDose == 0) {
                  break;
               }
            }
         }
      }

      DrugStockUpdateReq updateReq = new DrugStockUpdateReq("0", (List)null);
      updateReq.setFreezeListDel(freezeListDel);
      updateReq.setFreezeListUpdateNum(freezeListUpdateNum);
      return updateReq;
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public List updateDrugDoseByOrderDose(SysUser sysUser, List drugDoseVoList, String subtractFlag, String ip) throws Exception {
      List<DrugDoseVo> resultList = new ArrayList(1);
      List<DrugDoseVo> drugDoseVoListSource = new ArrayList(1);
      List<DrugAndClin> updateList = new ArrayList(1);
      List<TmPaFreeze> freezeList = new ArrayList(1);
      List<TmPaFreeze> freezeListDb = null;
      DrugStockUpdateReq updateReq = null;
      if (CollectionUtils.isNotEmpty(drugDoseVoList)) {
         List<DrugAndClin> drugStockList = null;
         if (subtractFlag.equals("0")) {
            drugDoseVoListSource.addAll(drugDoseVoList);
            List var40 = new ArrayList(1);
            updateReq = this.operDrugDoseVo(sysUser, drugDoseVoList, var40);
            List<DrugDoseVo> drugDoseVoListNew = new ArrayList(1);
            if (CollectionUtils.isNotEmpty(var40)) {
               for(TmPaFreeze freeze : var40) {
                  DrugDoseVo doseVo = new DrugDoseVo();
                  doseVo.setYfbm(freeze.getWareCode());
                  doseVo.setYpbm(freeze.getDrugCd());
                  doseVo.setKcbh(freeze.getStockNo());
                  doseVo.setYpid(freeze.getOrgCd() + freeze.getDrugCd() + freeze.getWareCode() + freeze.getRetailPrice());
                  BigDecimal freezeNum = (new BigDecimal(freeze.getFreezeNum().toString())).divide(new BigDecimal(freeze.getMinUnitRatio().toString()));
                  doseVo.setOrderDose(freezeNum);
                  doseVo.setPrice(freeze.getRetailPrice());
                  drugDoseVoListNew.add(doseVo);
               }

               drugDoseVoList = drugDoseVoListNew;
            }
         }

         List<DrugStockSearchReq> drugStockParam = new ArrayList(1);

         for(DrugDoseVo doseVo : drugDoseVoList) {
            DrugStockSearchReq drugStockSearchReq = new DrugStockSearchReq(doseVo.getYfbm(), doseVo.getYpbm());
            drugStockParam.add(drugStockSearchReq);
         }

         drugStockList = this.drugStockMapper.selectDrugListByDeptDrug(drugStockParam, (String)null);
         Map<String, List<DrugAndClin>> performDepCodeListMap = (Map)drugStockList.stream().collect(Collectors.groupingBy((t) -> t.getPerformDepCode() + t.getCpNo()));
         Map<String, List<DrugDoseVo>> listMap = (Map)drugDoseVoList.stream().collect(Collectors.groupingBy((t) -> t.getYfbm() + "@" + t.getYpbm()));
         Date freezeDate = this.commonService.getDbSysdate();

         for(String key : listMap.keySet()) {
            for(DrugDoseVo doseVo : (List)listMap.get(key)) {
               BigDecimal doseTotal = doseVo.getOrderDose();
               if (doseTotal.compareTo(BigDecimal.ZERO) != 0) {
                  String[] keys = key.split("@");
                  String deptNoAndCpNo = keys[0] + keys[1];
                  List<DrugAndClin> drugStockListTemp = (List)performDepCodeListMap.get(deptNoAndCpNo);
                  if (StringUtils.isNotBlank(doseVo.getKcbh())) {
                     List<DrugAndClin> drugStockListTempSource = new ArrayList(1);
                     if (CollectionUtils.isNotEmpty(drugStockListTemp)) {
                        drugStockListTempSource.addAll(drugStockListTemp);
                        drugStockListTemp = CollectionUtils.isNotEmpty(drugStockListTemp) ? (List)drugStockListTemp.stream().filter((t) -> t.getStockNo().equals(doseVo.getKcbh())).collect(Collectors.toList()) : null;
                     }

                     if (CollectionUtils.isEmpty(drugStockListTempSource) || CollectionUtils.isNotEmpty(drugStockListTempSource) && CollectionUtils.isEmpty(drugStockListTemp)) {
                        drugStockList = this.drugStockMapper.selectDrugListByDeptDrug(drugStockParam, "1");
                        Map<String, List<DrugAndClin>> performDepCodeListMap2 = (Map)drugStockList.stream().collect(Collectors.groupingBy((t) -> t.getPerformDepCode() + t.getCpNo()));
                        drugStockListTemp = (List)performDepCodeListMap2.get(deptNoAndCpNo);
                        drugStockListTemp = CollectionUtils.isNotEmpty(drugStockListTemp) ? (List)drugStockListTemp.stream().filter((t) -> t.getStockNo().equals(doseVo.getKcbh())).collect(Collectors.toList()) : null;
                        if (CollectionUtils.isEmpty(drugStockListTemp)) {
                           this.log.error("未领药退药时，药品过期或者不可供，也要退掉，查不到库存编码一样的药品记录！库存编码：【" + doseVo.getKcbh() + "】");
                           continue;
                        }
                     }
                  } else {
                     List<DrugAndClin> drugStockListTempEqPrice = (List)drugStockListTemp.stream().filter((t) -> t.getPrice().compareTo(doseVo.getPrice()) == 0).collect(Collectors.toList());
                     List<DrugAndClin> drugStockListTempUnEqPrice = (List)drugStockListTemp.stream().filter((t) -> t.getPrice().compareTo(doseVo.getPrice()) != 0).collect(Collectors.toList());
                     drugStockListTempEqPrice = (List)drugStockListTempEqPrice.stream().sorted(Comparator.comparing(DrugAndClin::getStockNo)).collect(Collectors.toList());
                     drugStockListTempUnEqPrice = (List)drugStockListTempUnEqPrice.stream().sorted(Comparator.comparing(DrugAndClin::getStockNo)).collect(Collectors.toList());
                     drugStockListTemp = new ArrayList(1);
                     if (CollectionUtils.isNotEmpty(drugStockListTempEqPrice)) {
                        drugStockListTemp.addAll(drugStockListTempEqPrice);
                     }

                     if (CollectionUtils.isNotEmpty(drugStockListTempUnEqPrice)) {
                        drugStockListTemp.addAll(drugStockListTempUnEqPrice);
                     }
                  }

                  BigDecimal deptUnitRatio = new BigDecimal(((DrugAndClin)drugStockListTemp.get(0)).getDeptUnitRatio().toString());
                  doseTotal = doseTotal.multiply(deptUnitRatio);
                  if (doseTotal.compareTo(BigDecimal.ZERO) >= 0) {
                     List<DrugAndClin> otherList = (List)drugStockListTemp.stream().filter((t) -> !t.getStockNo().equals(doseVo.getKcbh())).collect(Collectors.toList());
                     List<DrugAndClin> otherListEqPrice = (List)otherList.stream().filter((t) -> t.getPrice().compareTo(doseVo.getPrice()) == 0).collect(Collectors.toList());
                     otherListEqPrice = (List)otherListEqPrice.stream().sorted(Comparator.comparing(DrugAndClin::getStockNo)).collect(Collectors.toList());
                     List<DrugAndClin> otherListUnEqPrice = (List)otherList.stream().filter((t) -> t.getPrice().compareTo(doseVo.getPrice()) != 0).collect(Collectors.toList());
                     otherListUnEqPrice = (List)otherListUnEqPrice.stream().sorted(Comparator.comparing(DrugAndClin::getStockNo)).collect(Collectors.toList());
                     otherList = new ArrayList(1);
                     if (CollectionUtils.isNotEmpty(otherListEqPrice)) {
                        otherList.addAll(otherListEqPrice);
                     }

                     if (CollectionUtils.isNotEmpty(otherListUnEqPrice)) {
                        otherList.addAll(otherListUnEqPrice);
                     }

                     List<DrugAndClin> drugList = (List)drugStockListTemp.stream().filter((t) -> t.getStockNo().equals(doseVo.getKcbh())).collect(Collectors.toList());
                     BigDecimal sl = BigDecimal.ZERO;
                     DrugAndClin drugAndClin = (DrugAndClin)drugList.get(0);
                     if (drugAndClin.getSl() != null) {
                        sl = drugAndClin.getSl();
                     }

                     BigDecimal xcsl = BigDecimal.ZERO;
                     if (drugAndClin.getXcsl() != null) {
                        xcsl = drugAndClin.getXcsl();
                     }

                     BigDecimal add = xcsl.add(doseTotal);
                     BigDecimal esXcsl = doseTotal.divide(deptUnitRatio);
                     if (sl.compareTo(BigDecimal.ZERO) != 0 && xcsl.compareTo(sl) <= 0) {
                        drugAndClin.setXcsl(add);
                        updateList.add(drugAndClin);
                        DrugDoseVo drugDoseVo = new DrugDoseVo(drugAndClin.getId(), drugAndClin.getPerformDepCode(), drugAndClin.getCpNo(), drugAndClin.getStockNo(), esXcsl, drugAndClin.getPrice());
                        resultList.add(drugDoseVo);
                     } else {
                        for(DrugAndClin drugClin : otherList) {
                           BigDecimal otherSl = drugClin.getSl();
                           BigDecimal otherXcsl = drugClin.getXcsl();
                           if (otherSl.compareTo(BigDecimal.ZERO) != 0 && otherSl.compareTo(otherXcsl) <= 0) {
                              drugClin.setXcsl(doseTotal.add(otherXcsl));
                              updateList.add(drugClin);
                              DrugDoseVo drugDoseVo = new DrugDoseVo(drugClin.getId(), drugClin.getPerformDepCode(), drugClin.getCpNo(), drugClin.getStockNo(), esXcsl, drugAndClin.getPrice());
                              resultList.add(drugDoseVo);
                              break;
                           }
                        }
                     }
                  } else {
                     for(DrugAndClin drugAndClin : drugStockListTemp) {
                        boolean breakFlag = false;
                        BigDecimal freezeNum = BigDecimal.ZERO;
                        BigDecimal xcsl = drugAndClin.getXcsl() != null ? drugAndClin.getXcsl() : BigDecimal.ZERO;
                        BigDecimal add = xcsl.add(doseTotal);
                        if (xcsl.compareTo(BigDecimal.ZERO) > 0) {
                           if (add.compareTo(BigDecimal.ZERO) >= 0) {
                              drugAndClin.setXcsl(add);
                              updateList.add(drugAndClin);
                              BigDecimal sl = doseTotal.divide(deptUnitRatio);
                              DrugDoseVo drugDoseVo = new DrugDoseVo(drugAndClin.getId(), drugAndClin.getPerformDepCode(), drugAndClin.getCpNo(), (String)null, sl, drugAndClin.getPrice());
                              resultList.add(drugDoseVo);
                              freezeNum = doseTotal;
                              breakFlag = true;
                           } else {
                              int a = xcsl.intValue() / deptUnitRatio.intValue();
                              if (a <= 0) {
                                 continue;
                              }

                              BigDecimal sl = new BigDecimal(a + "");
                              DrugDoseVo drugDoseVo1 = new DrugDoseVo(drugAndClin.getId(), drugAndClin.getPerformDepCode(), drugAndClin.getCpNo(), (String)null, sl.negate(), drugAndClin.getPrice());
                              resultList.add(drugDoseVo1);
                              freezeNum = sl.multiply(deptUnitRatio);
                              doseTotal = freezeNum.add(doseTotal);
                              xcsl = xcsl.subtract(freezeNum);
                              drugAndClin.setXcsl(xcsl);
                              updateList.add(drugAndClin);
                           }

                           TmPaFreeze freeze = new TmPaFreeze();
                           freeze.setId(SnowIdUtils.uniqueLong());
                           freeze.setFreezeId(doseVo.getTakeDrugListId());
                           freeze.setOrgCd(sysUser.getHospital().getOrgCode());
                           freeze.setFreezeSerial(doseVo.getOrderNo());
                           freeze.setWareCode(doseVo.getYfbm());
                           freeze.setDrugCd(doseVo.getYpbm());
                           freeze.setDrugName(drugAndClin.getCpName());
                           freeze.setFreezeNum(freezeNum.abs().intValue());
                           freeze.setMinUnit(drugAndClin.getMinUnit());
                           freeze.setStandard(drugAndClin.getStandard());
                           freeze.setManufacturer(drugAndClin.getSccj());
                           freeze.setPackUnits(drugAndClin.getDeptUnits());
                           freeze.setMinUnitRatio(drugAndClin.getDeptUnitRatio());
                           freeze.setPurPrice(drugAndClin.getPurPrice());
                           freeze.setRetailPrice(drugAndClin.getPrice());
                           freeze.setFreezeDate(freezeDate);
                           freeze.setFreezeSourceCd("zyyzkl");
                           freeze.setFreezeSourceName("住院医嘱开立");
                           freeze.setFreezeDept(sysUser.getDept().getDeptCode());
                           freeze.setFreezeOperCd(sysUser.getUserName());
                           freeze.setFreezeOperName(sysUser.getNickName());
                           freeze.setStockNo(drugAndClin.getStockNo());
                           freeze.setIp(ip);
                           freezeList.add(freeze);
                           if (breakFlag) {
                              break;
                           }
                        }
                     }

                     updateReq = new DrugStockUpdateReq("1", freezeList);
                  }
               }
            }
         }

         if (CollectionUtils.isNotEmpty(freezeList)) {
            this.drugStockMapper.batchInsert(freezeList);
         }

         if (updateReq == null) {
            List<String> orderNoList = (List)drugDoseVoList.stream().map((t) -> t.getOrderNo()).collect(Collectors.toList());
            String orderNoListStr = String.join(",", orderNoList);
            this.log.debug("医嘱相关药品扣药库库存发送消息无数据，操作类型(0加虚存 1减虚存)【：" + subtractFlag + "】，医嘱编码：【" + orderNoListStr + "】");
            throw new Exception("医嘱相关药品扣药库库存发送消息无数据，操作类型(0加虚存 1减虚存)【：" + subtractFlag + "】，医嘱编码：【" + orderNoListStr + "】");
         }

         String updateReqStr = JSONObject.toJSONString(updateReq);
         this.log.debug("消息发送信息：" + updateReqStr);
         SendResult sendResult = this.rocketMQTemplate.syncSend("ph_drug_update1", updateReqStr);
         this.log.debug("消息发送返回信息：" + sendResult.toString());
         if (!sendResult.getSendStatus().equals(SendStatus.SEND_OK)) {
            throw new Exception(sendResult.toString());
         }
      }

      return resultList;
   }

   public void toMsgDeleteFreezeAddStock(List delFreezeList) throws Exception {
      DrugStockUpdateReq updateReq = new DrugStockUpdateReq("0", (List)null);
      updateReq.setFreezeListDel(delFreezeList);
      if (updateReq != null) {
         String updateReqStr = JSONObject.toJSONString(updateReq);
         this.log.debug("消息发送信息：" + updateReqStr);
         SendResult sendResult = this.rocketMQTemplate.syncSend("ph_drug_update1", updateReqStr);
         this.log.debug("消息发送返回信息：" + sendResult.toString());
         if (!sendResult.getSendStatus().equals(SendStatus.SEND_OK)) {
            throw new Exception(sendResult.toString());
         }
      } else {
         List<String> orderNoList = (List)delFreezeList.stream().map((t) -> t.getFreezeSerial()).collect(Collectors.toList());
         String orderNoListStr = String.join(",", orderNoList);
         this.log.debug("医嘱相关药品扣药库库存发送消息无数据，操作类型(0加虚存 1减虚存)【：1】，医嘱编码：【" + orderNoListStr + "】");
         throw new Exception("医嘱相关药品扣药库库存发送消息无数据，操作类型(0加虚存 1减虚存)【：1】，医嘱编码：【" + orderNoListStr + "】");
      }
   }

   public ItemIsOpenResVo antiUseAimIsOpen(SysUser sysUser, String cpNo) throws Exception {
      DrugAndClin drugAndClin = this.drugStockMapper.selectDrugDictByCd(sysUser.getHospital().getOrgCode(), cpNo);
      String preAuth = drugAndClin != null ? drugAndClin.getPreAuth() : null;
      ItemIsOpenResVo res = null;
      if (StringUtils.isNotBlank(preAuth)) {
         res = new ItemIsOpenResVo();
         res.setResCode("1");
         TmPaDrugPre drugPre = this.drugStockMapper.selectDrugPreByCd(sysUser.getHospital().getOrgCode(), preAuth);
         List<TmPaDrugPreDetail> drugPreDetailList = this.drugStockMapper.selectDrugPreDetailListByCd(sysUser.getHospital().getOrgCode(), preAuth);
         if (drugPre != null && CollectionUtils.isNotEmpty(drugPreDetailList)) {
            List<String> detailItemNoList = (List)drugPreDetailList.stream().map((t) -> t.getItemNo()).collect(Collectors.toList());
            switch (drugPre.getDetType()) {
               case "01":
                  if (detailItemNoList.contains(sysUser.getTitle())) {
                     res.setResCode("1");
                     String drugClassCode = this.getDrugClassCode(drugAndClin.getDrugTumor(), drugAndClin.getControlClass(), drugAndClin.getDrugAnti());
                     res.setDrugClassCode(drugClassCode);
                     res.setCpNo(drugAndClin.getCpNo());
                     res.setDrugGradeCd(drugAndClin.getControlClass());
                     res.setDrugGradeName(drugAndClin.getControlClassName());
                  } else {
                     this.drugPreDetail(drugPre, res, drugAndClin);
                  }
                  break;
               case "02":
                  if (detailItemNoList.contains(sysUser.getUserName())) {
                     res.setResCode("1");
                     String drugClassCode = this.getDrugClassCode(drugAndClin.getDrugTumor(), drugAndClin.getControlClass(), drugAndClin.getDrugAnti());
                     res.setDrugClassCode(drugClassCode);
                     res.setCpNo(drugAndClin.getCpNo());
                     res.setDrugGradeCd(drugAndClin.getControlClass());
                     res.setDrugGradeName(drugAndClin.getControlClassName());
                  } else {
                     this.drugPreDetail(drugPre, res, drugAndClin);
                  }
                  break;
               case "03":
                  if (detailItemNoList.contains(sysUser.getDept().getDeptCode())) {
                     res.setResCode("1");
                     String drugClassCode = this.getDrugClassCode(drugAndClin.getDrugTumor(), drugAndClin.getControlClass(), drugAndClin.getDrugAnti());
                     res.setDrugClassCode(drugClassCode);
                     res.setCpNo(drugAndClin.getCpNo());
                     res.setDrugGradeCd(drugAndClin.getControlClass());
                     res.setDrugGradeName(drugAndClin.getControlClassName());
                  } else {
                     this.drugPreDetail(drugPre, res, drugAndClin);
                  }
            }
         }

         if (StringUtils.isNotBlank(res.getResMsg())) {
            String resMsg = res.getResMsg().replaceAll("@DrugName", drugAndClin.getCpName()).replaceAll("@deptCode", sysUser.getDept().getDeptCode()).replaceAll("@admissionNo", drugAndClin.getPatientId());
            res.setResMsg(resMsg);
         }
      } else {
         res = new ItemIsOpenResVo();
         res.setResCode("1");
         String drugClassCode = this.getDrugClassCode(drugAndClin.getDrugTumor(), drugAndClin.getControlClass(), drugAndClin.getDrugAnti());
         res.setDrugClassCode(drugClassCode);
         res.setCpNo(cpNo);
      }

      return res;
   }

   private void drugPreDetail(TmPaDrugPre drugPre, ItemIsOpenResVo itemIsOpenResVo, DrugAndClin drugAndClin) throws Exception {
      String resMsg = null;
      String msgFlag = null;
      String windowFlag = null;
      String windowCode = null;
      String resCode = "1";
      if (drugPre.getControl().equals("1")) {
         resCode = "1";
         msgFlag = "1";
         resMsg = drugPre.getPrompt();
      } else if (drugPre.getControl().equals("2")) {
         resCode = "0";
         msgFlag = "1";
         resMsg = drugPre.getPrompt();
      } else if (drugPre.getControl().equals("3")) {
         resCode = "1";
         msgFlag = "1";
         resMsg = drugPre.getPrompt();
         windowFlag = "1";
         windowCode = drugPre.getInnerInterface();
      }

      itemIsOpenResVo.setResCode(resCode);
      itemIsOpenResVo.setMsgFlag(msgFlag);
      itemIsOpenResVo.setResMsg(resMsg);
      itemIsOpenResVo.setWindowFlag(windowFlag);
      itemIsOpenResVo.setWindowCode(windowCode);
      String drugClassCode = this.getDrugClassCode(drugAndClin.getDrugTumor(), drugAndClin.getControlClass(), drugAndClin.getDrugAnti());
      itemIsOpenResVo.setDrugClassCode(drugClassCode);
      itemIsOpenResVo.setDrugGradeCd(drugAndClin.getControlClass());
      itemIsOpenResVo.setDrugGradeName(drugAndClin.getControlClassName());
      itemIsOpenResVo.setCpNo(drugAndClin.getCpNo());
   }

   public String getDrugClassCode(String drugTumor, String controlClass, String drugAnti) throws Exception {
      String drugClassCode = "00";
      if (StringUtils.isNotBlank(drugTumor) && !drugTumor.equals("00")) {
         drugClassCode = "02";
      }

      if (StringUtils.isNotBlank(controlClass) && !controlClass.equals("00")) {
         drugClassCode = "03";
      }

      if (StringUtils.isNotBlank(drugAnti) && !drugAnti.equals("00")) {
         drugClassCode = "01";
      }

      return drugClassCode;
   }

   public String getDrugClassName(String drugTumor, String controlClass, String drugAnti) throws Exception {
      String drugClassName = "正常药品";
      if (StringUtils.isNotBlank(drugTumor) && !drugTumor.equals("00")) {
         drugClassName = "抗肿瘤药物";
      }

      if (StringUtils.isNotBlank(controlClass) && !controlClass.equals("00")) {
         drugClassName = "毒麻药品";
      }

      if (StringUtils.isNotBlank(drugAnti) && !drugAnti.equals("00")) {
         drugClassName = "抗菌药物";
      }

      return drugClassName;
   }

   public List selectDrugCheckList(SysUser sysUser, List drugCdList) throws Exception {
      List<DrugCheck> resList = new ArrayList(1);

      for(DrugAndClin drugAndClin : this.drugStockMapper.selectDrugDictByCds(sysUser.getHospital().getOrgCode(), drugCdList)) {
         DrugCheck drugCheck = this.getDrugCheck(sysUser, drugAndClin);
         resList.add(drugCheck);
      }

      return resList;
   }

   private DrugCheck getDrugCheck(SysUser sysUser, DrugAndClin drugAndClin) throws Exception {
      DrugCheck drugCheck = new DrugCheck();
      String applyFlag = "0";
      String useEnabled = "1";
      drugCheck.setDrugCode(drugAndClin.getCpNo());
      drugCheck.setDrugName(drugAndClin.getCpName());
      drugCheck.setDrugSpec(drugAndClin.getStandard());
      String drugClassCode = this.getDrugClassCode(drugAndClin.getDrugTumor(), drugAndClin.getControlClass(), drugAndClin.getDrugAnti());
      String drugClassName = this.getDrugClassName(drugAndClin.getDrugTumor(), drugAndClin.getControlClass(), drugAndClin.getDrugAnti());
      drugCheck.setDrugClassCode(drugClassCode);
      drugCheck.setDrugClassName(drugClassName);
      drugCheck.setDrugGradeName(drugAndClin.getControlClassName());
      String preAuth = drugAndClin.getPreAuth();
      if (StringUtils.isNotBlank(preAuth)) {
         TmPaDrugPre drugPre = this.drugStockMapper.selectDrugPreByCd(sysUser.getHospital().getOrgCode(), preAuth);
         List<TmPaDrugPreDetail> drugPreDetailList = this.drugStockMapper.selectDrugPreDetailListByCd(sysUser.getHospital().getOrgCode(), preAuth);
         if (drugPre != null && CollectionUtils.isNotEmpty(drugPreDetailList)) {
            boolean flag = false;
            List<String> detailItemNoList = (List)drugPreDetailList.stream().map((t) -> t.getItemNo()).collect(Collectors.toList());
            switch (drugPre.getDetType()) {
               case "01":
                  if (!detailItemNoList.contains(sysUser.getTitle())) {
                     flag = true;
                  }
                  break;
               case "02":
                  if (!detailItemNoList.contains(sysUser.getUserName())) {
                     flag = true;
                  }
                  break;
               case "03":
                  if (!detailItemNoList.contains(sysUser.getDept().getDeptCode())) {
                     flag = true;
                  }
            }

            if (flag) {
               if (drugPre.getControl().equals("1")) {
                  applyFlag = "0";
                  useEnabled = "1";
               } else if (drugPre.getControl().equals("2")) {
                  applyFlag = "0";
                  useEnabled = "0";
               } else if (drugPre.getControl().equals("3")) {
                  applyFlag = "1";
                  useEnabled = "0";
               }
            }
         }
      }

      drugCheck.setApplyFlag(applyFlag);
      drugCheck.setUseEnabled(useEnabled);
      return drugCheck;
   }

   public List selectHisDrugStockList(List drugCodeList, String execDeptCd) throws Exception {
      List<DrugAndClin> list = new ArrayList(1);
      if (CollectionUtils.isNotEmpty(drugCodeList) && StringUtils.isNotBlank(execDeptCd)) {
         list = this.drugStockMapper.selectHisDrugStockList(drugCodeList, execDeptCd);
      }

      return list;
   }

   public List selectHisDrugStockByStockList(String execDeptCd, List list) throws Exception {
      return CollectionUtils.isNotEmpty(list) && StringUtils.isNotBlank(execDeptCd) ? this.drugStockMapper.selectHisDrugStockByStockList(execDeptCd, list) : null;
   }

   public DrugCheck antiUseAimIsOpenType(SysUser sysUser, String drugCd) throws Exception {
      DrugAndClin drugAndClin = this.drugStockMapper.selectDrugDictByCd(sysUser.getHospital().getOrgCode(), drugCd);
      DrugCheck drugCheck = this.getDrugCheck(sysUser, drugAndClin);
      return drugCheck;
   }

   public List selectPharmacyListByDept(String orgCd, String deptCode) throws Exception {
      List<PharmacyVo> list = null;
      if (StringUtils.isNotBlank(orgCd) && StringUtils.isNotBlank(deptCode)) {
         list = this.drugStockMapper.selectPharmacyListByDeptByFlag(orgCd, deptCode);
         if (list.isEmpty()) {
            list = this.drugStockMapper.selectPharmacyListByDept(orgCd, deptCode);
         }
      }

      return list;
   }

   public void batchInsertFreezes(List list) throws Exception {
      if (CollectionUtils.isNotEmpty(list)) {
         this.drugStockMapper.batchInsert(list);
      }

   }

   public void delFreezesByOrderNo(String orderNo) throws Exception {
      if (StringUtils.isNotBlank(orderNo)) {
         this.drugStockMapper.delFreezesByOrderNo(orderNo);
      }

   }

   public List selectNewOrderFreezeList(String orderNo) throws Exception {
      return StringUtils.isNotBlank(orderNo) ? this.drugStockMapper.selectNewOrderFreezeList(orderNo) : null;
   }

   public List selectDrugListForOperation(DrugAndClinSearchVo drugAndClinSearchVo) throws Exception {
      return StringUtils.isNotBlank(drugAndClinSearchVo.getPerformDepCode()) ? this.drugStockMapper.selectDrugListForOperation(drugAndClinSearchVo) : null;
   }

   public List selectDrugsCostType(String orgCd, List drugCdList) throws Exception {
      return StringUtils.isNotBlank(orgCd) && CollectionUtils.isNotEmpty(drugCdList) ? this.drugStockMapper.selectDrugsCostType(orgCd, drugCdList) : null;
   }

   public List selectFreezeListByTakeDrugListIds(List takeDrugListIds) throws Exception {
      return CollectionUtils.isNotEmpty(takeDrugListIds) ? this.drugStockMapper.selectFreezeListByTakeDrugListIds(takeDrugListIds) : null;
   }

   public void updateFreezeOrderNoByRearrange(List freezeListUpdate) throws Exception {
      if (CollectionUtils.isNotEmpty(freezeListUpdate)) {
         this.drugStockMapper.updateFreezeOrderNoByRearrange(freezeListUpdate);
      }

   }
}
