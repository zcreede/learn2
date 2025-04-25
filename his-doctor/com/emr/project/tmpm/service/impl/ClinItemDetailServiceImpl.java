package com.emr.project.tmpm.service.impl;

import com.emr.project.tmpm.domain.ClinItemDetail;
import com.emr.project.tmpm.domain.vo.ClinItemDetailVo;
import com.emr.project.tmpm.mapper.ClinItemDetailMapper;
import com.emr.project.tmpm.service.IClinItemDetailService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClinItemDetailServiceImpl implements IClinItemDetailService {
   @Autowired
   private ClinItemDetailMapper clinItemDetailMapper;

   public ClinItemDetail selectClinItemDetailById(Long id) {
      return this.clinItemDetailMapper.selectClinItemDetailById(id);
   }

   public List selectClinItemDetailList(ClinItemDetail clinItemDetail) {
      return this.clinItemDetailMapper.selectClinItemDetailList(clinItemDetail);
   }

   public int insertClinItemDetail(ClinItemDetail clinItemDetail) {
      return this.clinItemDetailMapper.insertClinItemDetail(clinItemDetail);
   }

   public int updateClinItemDetail(ClinItemDetail clinItemDetail) {
      return this.clinItemDetailMapper.updateClinItemDetail(clinItemDetail);
   }

   public int deleteClinItemDetailByIds(Long[] ids) {
      return this.clinItemDetailMapper.deleteClinItemDetailByIds(ids);
   }

   public int deleteClinItemDetailById(Long id) {
      return this.clinItemDetailMapper.deleteClinItemDetailById(id);
   }

   public List selectClinItemDetailByItemCds(List itemCdList) throws Exception {
      List<ClinItemDetail> list = new ArrayList(1);
      if (itemCdList != null && !itemCdList.isEmpty()) {
         list = this.clinItemDetailMapper.selectClinItemDetailByItemCds(itemCdList);
      }

      return list;
   }

   public List selectClinItemDetailByItemCd(String itemCd) throws Exception {
      return this.clinItemDetailMapper.selectClinItemDetailByItemCd(itemCd);
   }

   public List selectOrderClinDetailList(ClinItemDetailVo clinItemDetailVo) throws Exception {
      new ArrayList();
      List resultList;
      if (clinItemDetailVo.getItemClassCode().equals("00")) {
         resultList = this.clinItemDetailMapper.selectClinItemDetailBySetCd(clinItemDetailVo.getItemCd());
      } else {
         resultList = this.clinItemDetailMapper.selectClinItemDetailByItemCd(clinItemDetailVo.getItemCd());
      }

      return resultList;
   }
}
