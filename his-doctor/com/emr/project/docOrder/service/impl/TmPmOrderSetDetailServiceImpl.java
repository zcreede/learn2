package com.emr.project.docOrder.service.impl;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.project.common.service.ICommonService;
import com.emr.project.docOrder.domain.TmPmOrderSetDetail;
import com.emr.project.docOrder.domain.vo.TmPmOrderSetDetailVo;
import com.emr.project.docOrder.mapper.TmPmOrderSetDetailMapper;
import com.emr.project.docOrder.service.ITmPmOrderSetDetailService;
import com.emr.project.docOrder.util.OrderUtil;
import com.emr.project.esSearch.domain.DrugAndClin;
import com.emr.project.system.domain.BasEmployee;
import com.emr.project.system.domain.SysDept;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.service.ISysDeptService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TmPmOrderSetDetailServiceImpl implements ITmPmOrderSetDetailService {
   @Autowired
   private TmPmOrderSetDetailMapper tmPmOrderSetDetailMapper;
   @Autowired
   private ICommonService commonService;
   @Autowired
   private ISysDeptService sysDeptService;

   public TmPmOrderSetDetail selectTmPmOrderSetDetailById(Long id) {
      return this.tmPmOrderSetDetailMapper.selectTmPmOrderSetDetailById(id);
   }

   public List selectTmPmOrderSetDetailList(TmPmOrderSetDetailVo tmPmOrderSetDetail) {
      return this.tmPmOrderSetDetailMapper.selectTmPmOrderSetDetailList(tmPmOrderSetDetail);
   }

   public void addTmPmOrderSetDetail(TmPmOrderSetDetailVo tmPmOrderSetDetail) throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      BasEmployee basEmployee = sysUser.getBasEmployee();
      Integer groupNo = this.tmPmOrderSetDetailMapper.selectSetMaxGroupNo(tmPmOrderSetDetail.getSetCd());
      groupNo = groupNo != null && groupNo != 0 ? Integer.valueOf(groupNo + 1) : 1;
      Integer groupSort = 1;
      if (tmPmOrderSetDetail.getMasterOrder().equals("2")) {
         TmPmOrderSetDetail rowDetail = this.tmPmOrderSetDetailMapper.selectTmPmOrderSetDetailById(tmPmOrderSetDetail.getRowId());
         List<TmPmOrderSetDetail> rowDetailList = this.tmPmOrderSetDetailMapper.selectOrderSetDetailByGroupNo(rowDetail.getGroupNo(), tmPmOrderSetDetail.getSetCd());
         groupNo = rowDetail.getGroupNo();
         groupSort = rowDetailList != null ? rowDetailList.size() + 1 : 1;
      }

      tmPmOrderSetDetail.setGroupNo(groupNo);
      tmPmOrderSetDetail.setGroupSort(OrderUtil.getNumStr(groupSort));
      tmPmOrderSetDetail.setId(SnowIdUtils.uniqueLong());
      tmPmOrderSetDetail.setCreDate(this.commonService.getDbSysdate());
      tmPmOrderSetDetail.setCrePerCode(basEmployee.getEmplNumber());
      tmPmOrderSetDetail.setCrePerName(basEmployee.getEmplName());
      tmPmOrderSetDetail.setHospitalCode(sysUser.getHospital().getOrgCode());
      SysDept sysDept = this.sysDeptService.selectDeptByDeptCode(tmPmOrderSetDetail.getExecDeptCd(), (Long)null);
      tmPmOrderSetDetail.setExecDeptName(sysDept == null ? "" : sysDept.getDeptName());
      this.tmPmOrderSetDetailMapper.insertTmPmOrderSetDetail(tmPmOrderSetDetail);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void insertTmPmOrderSetDetail(TmPmOrderSetDetailVo tmPmOrderSetDetail) throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      BasEmployee basEmployee = sysUser.getBasEmployee();
      Integer groupSort = 1;
      TmPmOrderSetDetail rowDetail = this.tmPmOrderSetDetailMapper.selectTmPmOrderSetDetailById(tmPmOrderSetDetail.getRowId());
      Integer groupNo = rowDetail.getGroupNo();
      List<TmPmOrderSetDetail> updateList = new ArrayList();
      if (tmPmOrderSetDetail.getMasterOrder().equals("1")) {
         updateList = this.tmPmOrderSetDetailMapper.selectSetDetailBySortAfterList(rowDetail.getGroupNo(), rowDetail.getSetCd());

         for(TmPmOrderSetDetail detail : updateList) {
            detail.setGroupNo(detail.getGroupNo() + 1);
         }
      }

      if (tmPmOrderSetDetail.getMasterOrder().equals("2")) {
         List<TmPmOrderSetDetail> detailList = this.tmPmOrderSetDetailMapper.selectSortAfterSonList(rowDetail.getGroupNo(), rowDetail.getSetCd());
         groupSort = Integer.parseInt(rowDetail.getGroupSort());
         if (rowDetail.getMasterOrder().equals("2")) {
            groupSort = Integer.parseInt(rowDetail.getGroupSort());

            for(TmPmOrderSetDetail detail : detailList) {
               Integer sort = Integer.parseInt(detail.getGroupSort());
               if (sort >= groupSort) {
                  detail.setGroupSort(OrderUtil.getNumStr(sort + 1));
               }

               updateList.add(detail);
            }
         } else {
            for(TmPmOrderSetDetail detail : detailList) {
               Integer sort = Integer.parseInt(detail.getGroupSort());
               if (sort > groupSort) {
                  detail.setGroupSort(OrderUtil.getNumStr(sort + 1));
               }

               updateList.add(detail);
            }

            groupSort = groupSort + 1;
         }
      }

      tmPmOrderSetDetail.setGroupNo(groupNo);
      tmPmOrderSetDetail.setGroupSort(OrderUtil.getNumStr(groupSort));
      tmPmOrderSetDetail.setId(SnowIdUtils.uniqueLong());
      tmPmOrderSetDetail.setCreDate(this.commonService.getDbSysdate());
      tmPmOrderSetDetail.setCrePerCode(basEmployee.getEmplNumber());
      tmPmOrderSetDetail.setCrePerName(basEmployee.getEmplName());
      tmPmOrderSetDetail.setHospitalCode(sysUser.getHospital().getOrgCode());
      SysDept sysDept = this.sysDeptService.selectDeptByDeptCode(tmPmOrderSetDetail.getExecDeptCd(), (Long)null);
      tmPmOrderSetDetail.setExecDeptName(sysDept.getDeptName());
      this.tmPmOrderSetDetailMapper.insertTmPmOrderSetDetail(tmPmOrderSetDetail);
      if (updateList != null && !updateList.isEmpty()) {
         this.tmPmOrderSetDetailMapper.updateTmPmOrderSetDetailList(updateList);
      }

   }

   public void insertTmPmOrderSetDetailList(List tmPmOrderSetDetailList) throws Exception {
      this.tmPmOrderSetDetailMapper.insertTmPmOrderSetDetailList(tmPmOrderSetDetailList);
   }

   public void updateTmPmOrderSetDetail(TmPmOrderSetDetailVo tmPmOrderSetDetail) throws Exception {
      TmPmOrderSetDetail detail = this.tmPmOrderSetDetailMapper.selectTmPmOrderSetDetailById(tmPmOrderSetDetail.getId());
      List<TmPmOrderSetDetail> updateList = new ArrayList();
      Integer groupNo = this.tmPmOrderSetDetailMapper.selectSetMaxGroupNo(tmPmOrderSetDetail.getSetCd());
      groupNo = groupNo == null ? 1 : groupNo + 1;
      Integer sort = 1;
      if (tmPmOrderSetDetail.getMasterOrder().equals("1")) {
         if (detail.getMasterOrder().equals("1")) {
            groupNo = detail.getGroupNo();
         }

         tmPmOrderSetDetail.setGroupSort(OrderUtil.getNumStr(sort));
         if (tmPmOrderSetDetail.getUpdateList() != null && !tmPmOrderSetDetail.getUpdateList().isEmpty()) {
            for(TmPmOrderSetDetail orderSetDetail : tmPmOrderSetDetail.getUpdateList()) {
               orderSetDetail.setGroupNo(groupNo);
               orderSetDetail.setGroupSort(OrderUtil.getNumStr(sort = sort + 1));
               orderSetDetail.setItemOrderUsageWay(tmPmOrderSetDetail.getItemOrderUsageWay());
               orderSetDetail.setFreqCd(tmPmOrderSetDetail.getFreqCd());
               orderSetDetail.setFreqName(tmPmOrderSetDetail.getFreqName());
               orderSetDetail.setDrippingSpeed(tmPmOrderSetDetail.getDrippingSpeed());
               orderSetDetail.setItemFlag(tmPmOrderSetDetail.getItemFlag());
               orderSetDetail.setItemOrderUsageWayName(tmPmOrderSetDetail.getItemOrderUsageWayName());
               updateList.add(orderSetDetail);
            }
         }
      } else if (detail.getMasterOrder().equals("1")) {
         TmPmOrderSetDetail details = (TmPmOrderSetDetail)tmPmOrderSetDetail.getUpdateList().get(0);
         groupNo = details.getGroupNo();
         Integer number = Integer.parseInt(details.getGroupSort()) + 1;
         tmPmOrderSetDetail.setGroupSort(OrderUtil.getNumStr(number));
         updateList.add(details);
      } else {
         tmPmOrderSetDetail.setGroupSort(detail.getGroupSort());
         groupNo = detail.getGroupNo();
      }

      SysDept sysDept = this.sysDeptService.selectDeptByDeptCode(tmPmOrderSetDetail.getExecDeptCd(), (Long)null);
      tmPmOrderSetDetail.setExecDeptName(sysDept.getDeptName());
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      tmPmOrderSetDetail.setAltDate(this.commonService.getDbSysdate());
      tmPmOrderSetDetail.setAltPerCode(basEmployee.getEmplNumber());
      tmPmOrderSetDetail.setAltPerName(basEmployee.getEmplName());
      tmPmOrderSetDetail.setGroupNo(groupNo);
      updateList.add(tmPmOrderSetDetail);
      this.tmPmOrderSetDetailMapper.updateTmPmOrderSetDetailList(updateList);
   }

   public void updateTmPmOrderSetDetailList(List tmPmOrderSetDetailList) throws Exception {
      this.tmPmOrderSetDetailMapper.updateTmPmOrderSetDetailList(tmPmOrderSetDetailList);
   }

   public void deleteTmPmOrderSetDetailByIds(Long[] ids) throws Exception {
      List<TmPmOrderSetDetail> detailList = this.tmPmOrderSetDetailMapper.selectTmPmOrderSetDetailByIds(Arrays.asList(ids));
      if (detailList != null && !detailList.isEmpty()) {
         List<Long> delIdList = new ArrayList();
         List<Integer> delGroupNoList = new ArrayList();

         for(int i = 0; i < ids.length; ++i) {
            Long id = ids[i];
            List<TmPmOrderSetDetail> idList = (List)detailList.stream().filter((s) -> s.getId().equals(id)).collect(Collectors.toList());
            if (((TmPmOrderSetDetail)idList.get(0)).getMasterOrder().equals("1")) {
               delGroupNoList.add(((TmPmOrderSetDetail)idList.get(0)).getGroupNo());
            } else {
               delIdList.add(id);
            }
         }

         if (delIdList != null && !delIdList.isEmpty()) {
            this.tmPmOrderSetDetailMapper.deleteTmPmOrderSetDetailByIds(delIdList);
         }

         if (delGroupNoList != null && !delGroupNoList.isEmpty()) {
            this.tmPmOrderSetDetailMapper.deleteTmPmOrderSetDetailByGroupNos(delGroupNoList, ((TmPmOrderSetDetail)detailList.get(0)).getSetCd());
         }
      }

   }

   public int deleteTmPmOrderSetDetailById(Long id) {
      return this.tmPmOrderSetDetailMapper.deleteTmPmOrderSetDetailById(id);
   }

   public void deleteTmPmOrderSetDetailBySetCd(String setCd) throws Exception {
      this.tmPmOrderSetDetailMapper.deleteTmPmOrderSetDetailBySetCd(setCd);
   }

   public List selectTmPmOrderSetDetailBySetCd(String setCd) throws Exception {
      return this.tmPmOrderSetDetailMapper.selectTmPmOrderSetDetailBySetCd(setCd);
   }

   public Integer selectSetMaxGroupNo(String setCd) throws Exception {
      return this.tmPmOrderSetDetailMapper.selectSetMaxGroupNo(setCd);
   }

   public List selectSetAsOrderList(String setCd) throws Exception {
      return this.tmPmOrderSetDetailMapper.selectSetAsOrderList(setCd);
   }

   public List selectSetAsOrderListBySets(List setCdList) throws Exception {
      List<TmPmOrderSetDetail> resList = null;
      if (CollectionUtils.isNotEmpty(setCdList)) {
         resList = this.tmPmOrderSetDetailMapper.selectSetAsOrderListBySets(setCdList);
      }

      return resList;
   }

   public List selectCheckDetailListBySetCd(String setCd) throws Exception {
      List<DrugAndClin> list = this.tmPmOrderSetDetailMapper.selectCheckDetailListBySetCd(setCd);
      return list;
   }

   public void updateGroupNos(List setDetailList) throws Exception {
      if (CollectionUtils.isNotEmpty(setDetailList)) {
         this.tmPmOrderSetDetailMapper.updateGroupNos(setDetailList);
      }

   }
}
