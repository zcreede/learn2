package com.emr.project.docOrder.service.impl;

import com.emr.common.constant.CommonConstants;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.common.utils.StringUtils;
import com.emr.project.common.service.ICommonService;
import com.emr.project.docOrder.domain.TmPmOrderSetDetail;
import com.emr.project.docOrder.domain.TmPmOrderSetMain;
import com.emr.project.docOrder.domain.vo.OrderSearchVo;
import com.emr.project.docOrder.domain.vo.TmPmOrderSetMainVo;
import com.emr.project.docOrder.mapper.TmPmOrderSetMainMapper;
import com.emr.project.docOrder.service.ITmPmOrderSetDetailService;
import com.emr.project.docOrder.service.ITmPmOrderSetMainService;
import com.emr.project.docOrder.util.OrderUtil;
import com.emr.project.system.domain.BasEmployee;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.service.ISysEmrConfigService;
import com.emr.project.tmpa.domain.OrderFreq;
import com.emr.project.tmpa.domain.OrderSig;
import com.emr.project.tmpa.service.IOrderFreqService;
import com.emr.project.tmpa.service.IOrderSigService;
import com.emr.project.tmpm.domain.ClinItemCollection;
import com.emr.project.tmpm.domain.ExamPart;
import com.emr.project.tmpm.domain.TmPmSpec;
import com.emr.project.tmpm.service.IClinItemCollectionService;
import com.emr.project.tmpm.service.IExamPartService;
import com.emr.project.tmpm.service.ITmPmSpecService;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TmPmOrderSetMainServiceImpl implements ITmPmOrderSetMainService {
   @Autowired
   private TmPmOrderSetMainMapper tmPmOrderSetMainMapper;
   @Autowired
   private ICommonService commonService;
   @Autowired
   private ITmPmOrderSetDetailService tmPmOrderSetDetailService;
   @Autowired
   private IOrderFreqService orderFreqService;
   @Autowired
   private IOrderSigService orderSigService;
   @Autowired
   private IExamPartService examPartService;
   @Autowired
   private ITmPmSpecService tmPmSpecService;
   @Autowired
   private IClinItemCollectionService clinItemCollectionService;
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;

   public TmPmOrderSetMain selectTmPmOrderSetMainById(String setCd) {
      return this.tmPmOrderSetMainMapper.selectTmPmOrderSetMainById(setCd);
   }

   public List selectTmPmOrderSetMainList(TmPmOrderSetMain tmPmOrderSetMain) throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      if (StringUtils.isNotBlank(tmPmOrderSetMain.getShareType())) {
         if (tmPmOrderSetMain.getShareType().equals("2")) {
            tmPmOrderSetMain.setShareObject(sysUser.getDept().getDeptCode());
         } else if (tmPmOrderSetMain.getShareType().equals("3")) {
            tmPmOrderSetMain.setShareObject(sysUser.getBasEmployee().getEmplNumber());
         }
      } else {
         tmPmOrderSetMain.setDeptCode(sysUser.getDept().getDeptCode());
         tmPmOrderSetMain.setDocCode(sysUser.getBasEmployee().getEmplNumber());
      }

      List<TmPmOrderSetMainVo> list = this.tmPmOrderSetMainMapper.selectTmPmOrderSetMainList(tmPmOrderSetMain);
      return list;
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void insertTmPmOrderSetMain(TmPmOrderSetMainVo tmPmOrderSetMainVo) throws Exception {
      String id = SnowIdUtils.uniqueLongHex();
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      BasEmployee basEmployee = sysUser.getBasEmployee();
      List<TmPmOrderSetDetail> setDetailList = new ArrayList();
      List<OrderSearchVo> orderList = tmPmOrderSetMainVo.getOrderSaveList();
      Integer groupNo = 0;
      int orderSortNumber = 1;
      int orderGroupSortNumber = 1;
      List<String> pageOrderGroupNoList = new ArrayList(orderList.size());

      for(int i = 0; i < orderList.size(); ++i) {
         Boolean subFlag = false;
         OrderSearchVo orderSearchVo = (OrderSearchVo)orderList.get(i);
         pageOrderGroupNoList.add(orderSearchVo.getOrderGroupNo());
         if (i > 0) {
            String prepageOrderGroupNo = (String)pageOrderGroupNoList.get(i - 1);
            if (!prepageOrderGroupNo.equals(orderSearchVo.getOrderGroupNo())) {
               groupNo = groupNo + 1;
               ++orderSortNumber;
               orderGroupSortNumber = 1;
            } else {
               ++orderGroupSortNumber;
               subFlag = true;
            }
         }

         orderSearchVo.setOrderGroupNo(groupNo.toString());
         orderSearchVo.setOrderGroupSortNumber(OrderUtil.getNumStr(orderGroupSortNumber));
      }

      Map<String, List<OrderSearchVo>> orderMap = (Map)orderList.stream().collect(Collectors.groupingBy((s) -> s.getOrderGroupNo()));

      for(String key : orderMap.keySet()) {
         List<OrderSearchVo> orderSearchVos = (List)orderMap.get(key);
         groupNo = groupNo + 1;

         for(OrderSearchVo orderSearchVo : orderSearchVos) {
            TmPmOrderSetDetail tmPmOrderSetDetail = new TmPmOrderSetDetail();
            tmPmOrderSetDetail.setId(SnowIdUtils.uniqueLong());
            tmPmOrderSetDetail.setSetCd(id);
            tmPmOrderSetDetail.setHospitalCode(sysUser.getHospital().getOrgCode());
            tmPmOrderSetDetail.setItemClassCd(orderSearchVo.getOrderClassCode());
            tmPmOrderSetDetail.setGroupNo(Integer.parseInt(orderSearchVo.getOrderGroupNo()));
            tmPmOrderSetDetail.setGroupSort(orderSearchVo.getOrderGroupSortNumber());
            tmPmOrderSetDetail.setMasterOrder(orderSearchVo.getMasterOrder());
            tmPmOrderSetDetail.setItemCd(orderSearchVo.getCpNo());
            tmPmOrderSetDetail.setItemName(orderSearchVo.getCpName());
            tmPmOrderSetDetail.setStandard(orderSearchVo.getStandard());
            tmPmOrderSetDetail.setActualUseage(orderSearchVo.getOrderActualUsage());
            tmPmOrderSetDetail.setUsageUnit(orderSearchVo.getUsageUnit());
            tmPmOrderSetDetail.setItemOrderUsageWay(orderSearchVo.getOrderUsageWay());
            tmPmOrderSetDetail.setFreqCd(orderSearchVo.getOrderFreq());
            tmPmOrderSetDetail.setFreqName(orderSearchVo.getOrderFreqName());
            tmPmOrderSetDetail.setUsageDays(orderSearchVo.getOrderUsageDays());
            tmPmOrderSetDetail.setDrugForm(orderSearchVo.getDrugForm());
            tmPmOrderSetDetail.setDrugClassCode(orderSearchVo.getDrugType());
            tmPmOrderSetDetail.setPurposeAntimicrobialUse(orderSearchVo.getPurposeAntimicrobialUse());
            tmPmOrderSetDetail.setDrippingSpeed(orderSearchVo.getDrippingSpeed());
            tmPmOrderSetDetail.setExecDeptCd(orderSearchVo.getDetailPerformDepCode());
            tmPmOrderSetDetail.setExecDeptName(orderSearchVo.getDetailPerformDepName());
            tmPmOrderSetDetail.setItemFlag(orderSearchVo.getOrderItemFlag());
            tmPmOrderSetDetail.setDoctorInstructions(orderSearchVo.getDoctorInstructions());
            tmPmOrderSetDetail.setItemOrderUsageWayName(orderSearchVo.getOrderUsageWayName());
            tmPmOrderSetDetail.setStockNo(orderSearchVo.getStockNo());
            tmPmOrderSetDetail.setDocumentTypeNo(orderSearchVo.getDocumentTypeNo());
            tmPmOrderSetDetail.setPrice(orderSearchVo.getPrice());
            tmPmOrderSetDetail.setCreDate(this.commonService.getDbSysdate());
            tmPmOrderSetDetail.setCrePerCode(sysUser.getBasEmployee().getEmplNumber());
            tmPmOrderSetDetail.setCrePerName(sysUser.getBasEmployee().getEmplName());
            tmPmOrderSetDetail.setExamPartCd(orderSearchVo.getExamPartCd());
            tmPmOrderSetDetail.setExamPartName(orderSearchVo.getExamPartName());
            tmPmOrderSetDetail.setSpecCd(orderSearchVo.getSpecCd());
            tmPmOrderSetDetail.setSpecName(orderSearchVo.getSpecName());
            setDetailList.add(tmPmOrderSetDetail);
         }
      }

      this.tmPmOrderSetDetailService.insertTmPmOrderSetDetailList(setDetailList);
      tmPmOrderSetMainVo.setSetCd(id);
      tmPmOrderSetMainVo.setHospitalCode(sysUser.getHospital().getOrgCode());
      tmPmOrderSetMainVo.setSetType("1");
      switch (tmPmOrderSetMainVo.getShareType()) {
         case "1":
            tmPmOrderSetMainVo.setShareObject("000000");
            break;
         case "2":
            tmPmOrderSetMainVo.setShareObject(sysUser.getDept().getDeptCode());
            break;
         case "3":
            tmPmOrderSetMainVo.setShareObject(basEmployee.getEmplNumber());
      }

      tmPmOrderSetMainVo.setCreDate(this.commonService.getDbSysdate());
      tmPmOrderSetMainVo.setCrePerCode(sysUser.getBasEmployee().getEmplNumber());
      tmPmOrderSetMainVo.setCrePerName(sysUser.getBasEmployee().getEmplName());
      maxGroupSort = this.tmPmOrderSetMainMapper.selectMaxgroupSort();
      if (maxGroupSort == null) {
         maxGroupSort = "1";
      }

      tmPmOrderSetMainVo.setGroupSort(maxGroupSort);
      this.tmPmOrderSetMainMapper.insertTmPmOrderSetMain(tmPmOrderSetMainVo);
   }

   public void insertSetMain(TmPmOrderSetMainVo tmPmOrderSetMainVo) throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      BasEmployee basEmployee = sysUser.getBasEmployee();
      tmPmOrderSetMainVo.setCreDate(this.commonService.getDbSysdate());
      tmPmOrderSetMainVo.setCrePerCode(basEmployee.getEmplNumber());
      tmPmOrderSetMainVo.setCrePerName(basEmployee.getEmplName());
      tmPmOrderSetMainVo.setHospitalCode(sysUser.getHospital().getOrgCode());
      tmPmOrderSetMainVo.setSetCd(SnowIdUtils.uniqueLongHex());
      switch (tmPmOrderSetMainVo.getShareType()) {
         case "1":
            tmPmOrderSetMainVo.setShareObject("000000");
            break;
         case "2":
            tmPmOrderSetMainVo.setShareObject(sysUser.getDept().getDeptCode());
            break;
         case "3":
            tmPmOrderSetMainVo.setShareObject(basEmployee.getEmplNumber());
      }

      maxGroupSort = this.tmPmOrderSetMainMapper.selectMaxgroupSort();
      if (maxGroupSort == null) {
         maxGroupSort = "1";
      }

      tmPmOrderSetMainVo.setGroupSort(maxGroupSort);
      this.tmPmOrderSetMainMapper.insertTmPmOrderSetMain(tmPmOrderSetMainVo);
   }

   public void updateTmPmOrderSetMain(TmPmOrderSetMain tmPmOrderSetMain) throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      BasEmployee basEmployee = sysUser.getBasEmployee();
      tmPmOrderSetMain.setAltDate(this.commonService.getDbSysdate());
      tmPmOrderSetMain.setAltPerCode(basEmployee.getEmplNumber());
      tmPmOrderSetMain.setAltPerName(basEmployee.getEmplName());
      this.tmPmOrderSetMainMapper.updateTmPmOrderSetMain(tmPmOrderSetMain);
   }

   public int deleteTmPmOrderSetMainByIds(String[] setCds) {
      return this.tmPmOrderSetMainMapper.deleteTmPmOrderSetMainByIds(setCds);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void deleteTmPmOrderSetMainById(String setCd) throws Exception {
      this.tmPmOrderSetMainMapper.deleteTmPmOrderSetMainById(setCd);
      this.tmPmOrderSetDetailService.deleteTmPmOrderSetDetailBySetCd(setCd);
      ClinItemCollection collection = new ClinItemCollection();
      collection.setItemCd(setCd);
      this.clinItemCollectionService.deleteClinItemCollectionByItemCd(collection);
   }

   public List selectOrderSetListByClassCd(TmPmOrderSetMainVo tmPmOrderSetMainVo) throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      if (tmPmOrderSetMainVo.getShareType().equals("2")) {
         tmPmOrderSetMainVo.setShareObject(sysUser.getDept().getDeptCode());
      } else if (tmPmOrderSetMainVo.getShareType().equals("3")) {
         tmPmOrderSetMainVo.setShareObject(sysUser.getBasEmployee().getEmplNumber());
      }

      List<TmPmOrderSetMainVo> mainList = this.tmPmOrderSetMainMapper.selectOrderSetListByClassCd(tmPmOrderSetMainVo);
      if (mainList != null && !mainList.isEmpty()) {
         BasEmployee basEmployee = sysUser.getBasEmployee();
         ClinItemCollection collection = new ClinItemCollection();
         collection.setDocCd(basEmployee.getEmplNumber());
         collection.setItemClassCd("00");
         List<ClinItemCollection> collectionList = this.clinItemCollectionService.selectClinItemCollectionList(collection);
         if (collectionList != null && !collectionList.isEmpty()) {
            List<String> cdList = (List)collectionList.stream().map((s) -> s.getItemCd()).collect(Collectors.toList());

            for(TmPmOrderSetMainVo setMainVo : mainList) {
               if (cdList.contains(setMainVo.getSetCd())) {
                  setMainVo.setCollectFlag(CommonConstants.BOOL_TRUE);
               } else {
                  setMainVo.setCollectFlag(CommonConstants.BOOL_FALSE);
               }
            }
         } else {
            for(TmPmOrderSetMainVo setMainVo : mainList) {
               setMainVo.setCollectFlag(CommonConstants.BOOL_FALSE);
            }
         }
      }

      return mainList;
   }

   public Map selectSetMainDictList() throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      Map<String, Object> result = new HashMap();
      OrderFreq orderFreq = new OrderFreq();
      orderFreq.setDocCd(user.getUserName());
      List<OrderFreq> freqList = this.orderFreqService.selectUseTimeOrderFreqList(orderFreq);
      result.put("freqList", freqList);
      result.put("freqListc", freqList.stream().filter((t) -> t.getDrugType().equals("0") || t.getDrugType().equals("2")).collect(Collectors.toList()));
      OrderSig orderSig = new OrderSig();
      orderSig.setDocCd(user.getUserName());
      List<OrderSig> sigList = this.orderSigService.selectUseTimeOrderSigList(orderSig);
      result.put("sigList", sigList);
      result.put("sigListc", sigList.stream().filter((t) -> t.getDrugType().equals("0") || t.getDrugType().equals("2")).collect(Collectors.toList()));
      List<TmPmSpec> specList = this.tmPmSpecService.selectTmPmSpecList(new TmPmSpec());
      result.put("specList", specList);
      List<ExamPart> examParts = this.examPartService.selectExamPartList(new ExamPart());
      result.put("examPart", examParts);
      String drugFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0073");
      result.put("drugFlag", drugFlag);
      return result;
   }

   public List selectDetailListByType(TmPmOrderSetMain tmPmOrderSetMain) throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      if (tmPmOrderSetMain.getShareType().equals("2")) {
         tmPmOrderSetMain.setShareObject(sysUser.getDept().getDeptCode());
      } else if (tmPmOrderSetMain.getShareType().equals("3")) {
         tmPmOrderSetMain.setShareObject(sysUser.getBasEmployee().getEmplNumber());
      }

      return this.tmPmOrderSetMainMapper.selectDetailListByType(tmPmOrderSetMain);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void saveAsOrderSet(TmPmOrderSetMainVo tmPmOrderSetMainVo) throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      BasEmployee basEmployee = sysUser.getBasEmployee();
      List<TmPmOrderSetDetail> detailtList = this.tmPmOrderSetDetailService.selectTmPmOrderSetDetailBySetCd(tmPmOrderSetMainVo.getSetCd());
      List<TmPmOrderSetDetail> insertList = new ArrayList();
      Integer groupNo = 0;
      if (StringUtils.isEmpty(tmPmOrderSetMainVo.getCopySetCd())) {
         this.insertSetMain(tmPmOrderSetMainVo);
      } else {
         Integer maxGroupNo = this.tmPmOrderSetDetailService.selectSetMaxGroupNo(tmPmOrderSetMainVo.getCopySetCd());
         if (maxGroupNo != null) {
            groupNo = maxGroupNo;
         }

         tmPmOrderSetMainVo.setSetCd(tmPmOrderSetMainVo.getCopySetCd());
      }

      if (detailtList != null && !detailtList.isEmpty()) {
         Map<Integer, List<TmPmOrderSetDetail>> detailMap = (Map)detailtList.stream().collect(Collectors.groupingBy((s) -> s.getGroupNo()));
         Integer count = 0;

         for(Integer no : detailMap.keySet()) {
            Integer sort = 0;
            count = count + 1;
            List<TmPmOrderSetDetail> details = (List)detailMap.get(no);
            details.stream().sorted(Comparator.comparing(TmPmOrderSetDetail::getGroupSort));

            for(TmPmOrderSetDetail tmPmOrderSetDetail : details) {
               tmPmOrderSetDetail.setSetCd(tmPmOrderSetMainVo.getSetCd());
               tmPmOrderSetDetail.setId(SnowIdUtils.uniqueLong());
               tmPmOrderSetDetail.setGroupNo(groupNo + count);
               tmPmOrderSetDetail.setGroupSort(OrderUtil.getNumStr(sort = sort + 1));
               tmPmOrderSetDetail.setCrePerCode(basEmployee.getEmplNumber());
               tmPmOrderSetDetail.setCrePerName(basEmployee.getEmplName());
               tmPmOrderSetDetail.setCreDate(this.commonService.getDbSysdate());
               insertList.add(tmPmOrderSetDetail);
            }
         }

         this.tmPmOrderSetDetailService.insertTmPmOrderSetDetailList(insertList);
      }

   }

   public List selectEsItemSetList(TmPmOrderSetMain tmPmOrderSetMain) throws Exception {
      return this.tmPmOrderSetMainMapper.selectEsItemSetList(tmPmOrderSetMain);
   }

   public int updateGroupNos(List updateMainList) throws Exception {
      return this.tmPmOrderSetMainMapper.batchUpdate(updateMainList);
   }
}
