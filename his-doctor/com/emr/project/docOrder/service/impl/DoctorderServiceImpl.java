package com.emr.project.docOrder.service.impl;

import com.emr.common.utils.StringUtils;
import com.emr.common.utils.sql.SqlUtil;
import com.emr.framework.aspectj.lang.annotation.DataSource;
import com.emr.framework.aspectj.lang.enums.DataSourceType;
import com.emr.framework.web.page.PageDomain;
import com.emr.framework.web.page.TableSupport;
import com.emr.project.docOrder.domain.Doctorder;
import com.emr.project.docOrder.domain.vo.DoctorderVo;
import com.emr.project.docOrder.domain.vo.OrderSearchVo;
import com.emr.project.docOrder.mapper.DoctorderMapper;
import com.emr.project.docOrder.service.IDoctorderService;
import com.emr.project.pat.domain.BabyInfo;
import com.emr.project.pat.service.IBabyInfoService;
import com.github.pagehelper.PageHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorderServiceImpl implements IDoctorderService {
   @Autowired
   private DoctorderMapper doctorderMapper;
   @Autowired
   private IBabyInfoService babyInfoService;

   public Doctorder selectDoctorderById(String id) {
      return this.doctorderMapper.selectDoctorderById(id);
   }

   public List selectDoctorderList(Doctorder doctorder) {
      return this.doctorderMapper.selectDoctorderList(doctorder);
   }

   @DataSource(DataSourceType.searchHisDocorder)
   public List selectHisDocorderList(DoctorderVo doctorderVo, Integer pageNum, Integer pageSize) throws Exception {
      List<DoctorderVo> list = this.doctorderMapper.selectHisDocorderParentList(doctorderVo, pageNum, pageSize);
      return list;
   }

   @DataSource(DataSourceType.searchHisDocorder)
   public Integer selectHisDocorderListCount(DoctorderVo doctorderVo) throws Exception {
      return this.doctorderMapper.selectHisDocorderParentListCount(doctorderVo);
   }

   @DataSource(DataSourceType.searchHisDocorder)
   public List selectHisDocorderChildList(List parentList, DoctorderVo doctorderVo) throws Exception {
      List<DoctorderVo> resList = new ArrayList(parentList.size());
      List<String> maNoList = (List)parentList.stream().map((t) -> t.getMaNo()).collect(Collectors.toList());
      doctorderVo.setMaNoList(maNoList);
      List<DoctorderVo> childList = this.doctorderMapper.selectHisDocorderChildList(doctorderVo);
      Map<String, List<DoctorderVo>> childListMap = (Map)childList.stream().collect(Collectors.groupingBy((t) -> t.getMaNo()));

      for(DoctorderVo temp : parentList) {
         List<DoctorderVo> childListTemp = (List)childListMap.get(temp.getMaNo());
         resList.add(temp);
         if (childListTemp != null) {
            resList.addAll(childListTemp);
         }
      }

      return resList;
   }

   public int insertDoctorder(Doctorder doctorder) {
      return this.doctorderMapper.insertDoctorder(doctorder);
   }

   public int updateDoctorder(Doctorder doctorder) {
      return this.doctorderMapper.updateDoctorder(doctorder);
   }

   public int deleteDoctorderByIds(String[] ids) {
      return this.doctorderMapper.deleteDoctorderByIds(ids);
   }

   public int deleteDoctorderById(String id) {
      return this.doctorderMapper.deleteDoctorderById(id);
   }

   public List selectBabyInfoByPatientId(String patientId) {
      BabyInfo param = new BabyInfo();
      param.setPatientId(patientId);
      List<BabyInfo> babyInfoList = this.babyInfoService.selectBabyInfoList(param);
      BabyInfo temp = new BabyInfo();
      temp.setPatientId(patientId);
      temp.setPatBabyId(patientId);
      temp.setBabyName("本患者");
      babyInfoList.add(0, temp);
      BabyInfo temp2 = new BabyInfo();
      temp2.setPatientId("1");
      temp2.setPatBabyId("1");
      temp2.setBabyName("全部");
      babyInfoList.add(0, temp2);
      return babyInfoList;
   }

   @DataSource(DataSourceType.searchHisDocorder)
   public List selectHisDocorderVoList(OrderSearchVo queryParam) throws Exception {
      PageDomain pageDomain = TableSupport.buildPageRequest();
      Integer pageNum = pageDomain.getPageNum();
      Integer pageSize = pageDomain.getPageSize();
      if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize)) {
         String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
         PageHelper.startPage(pageNum, pageSize, orderBy);
      }

      return this.doctorderMapper.selectHisDocorderVoList(queryParam);
   }

   @DataSource(DataSourceType.searchHisDocorder)
   public List selectHisSubDocorderVoList(OrderSearchVo queryParam, List mainList) throws Exception {
      List<OrderSearchVo> allList = new ArrayList(1);
      List<String> orderGroupNoList = CollectionUtils.isNotEmpty(mainList) ? (List)mainList.stream().map((t) -> t.getOrderGroupNo()).collect(Collectors.toList()) : null;
      if (CollectionUtils.isNotEmpty(orderGroupNoList)) {
         queryParam.setMainOrderNoList(orderGroupNoList);
         List<OrderSearchVo> subList = this.doctorderMapper.selectHisSubDocorderVoList(queryParam);
         if (CollectionUtils.isNotEmpty(subList)) {
            Map<String, List<OrderSearchVo>> listSubMap = (Map<String, List<OrderSearchVo>>)(subList != null ? (Map)subList.stream().collect(Collectors.groupingBy((t) -> t.getOrderGroupNo())) : new HashMap(1));
            mainList.stream().forEach((t) -> {
               t.setOrderNo(t.getOrderGroupNo());
               allList.add(t);
               List<OrderSearchVo> listSubTemp = (List)listSubMap.get(t.getOrderGroupNo());
               if (listSubTemp != null && !listSubTemp.isEmpty()) {
                  t.setGroupStr("┓");

                  for(int i = 0; i < listSubTemp.size(); ++i) {
                     OrderSearchVo temp = (OrderSearchVo)listSubTemp.get(i);
                     temp.setOrderNo(temp.getOrderGroupNo());
                     temp.setGroupStr("┃");
                     if (i == listSubTemp.size() - 1) {
                        temp.setGroupStr("┛");
                     }
                  }

                  allList.addAll(listSubTemp);
               }

            });
         } else {
            allList.addAll(mainList);
         }
      }

      return allList;
   }
}
