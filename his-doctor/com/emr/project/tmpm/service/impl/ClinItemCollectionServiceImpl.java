package com.emr.project.tmpm.service.impl;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.project.system.domain.BasEmployee;
import com.emr.project.system.domain.SysUser;
import com.emr.project.tmpm.domain.ClinItemCollection;
import com.emr.project.tmpm.mapper.ClinItemCollectionMapper;
import com.emr.project.tmpm.service.IClinItemCollectionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClinItemCollectionServiceImpl implements IClinItemCollectionService {
   @Autowired
   private ClinItemCollectionMapper clinItemCollectionMapper;

   public ClinItemCollection selectClinItemCollectionById(Long id) {
      return this.clinItemCollectionMapper.selectClinItemCollectionById(id);
   }

   public List selectClinItemCollectionList(ClinItemCollection clinItemCollection) {
      return this.clinItemCollectionMapper.selectClinItemCollectionList(clinItemCollection);
   }

   public void insertClinItemCollection(ClinItemCollection clinItemCollection) throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      BasEmployee basEmployee = sysUser.getBasEmployee();
      clinItemCollection.setId(SnowIdUtils.uniqueLong());
      clinItemCollection.setHospitalCode(sysUser.getHospital().getOrgCode());
      clinItemCollection.setDocCd(basEmployee.getEmplNumber());
      clinItemCollection.setDocName(basEmployee.getEmplName());
      clinItemCollection.setUsageTimes(0);
      ClinItemCollection param = new ClinItemCollection();
      param.setDocCd(basEmployee.getEmplNumber());
      param.setItemClassCd(clinItemCollection.getItemClassCd());
      List<ClinItemCollection> clinList = this.clinItemCollectionMapper.selectClinItemCollectionList(param);
      clinItemCollection.setSort(clinList != null && !clinList.isEmpty() ? clinList.size() + 1 : 1);
      clinItemCollection.setCrePerCode(basEmployee.getEmplNumber());
      clinItemCollection.setCrePerName(basEmployee.getEmplName());
      this.clinItemCollectionMapper.insertClinItemCollection(clinItemCollection);
   }

   public int updateClinItemCollection(ClinItemCollection clinItemCollection) {
      return this.clinItemCollectionMapper.updateClinItemCollection(clinItemCollection);
   }

   public int deleteClinItemCollectionByIds(Long[] ids) {
      return this.clinItemCollectionMapper.deleteClinItemCollectionByIds(ids);
   }

   public int deleteClinItemCollectionById(Long id) {
      return this.clinItemCollectionMapper.deleteClinItemCollectionById(id);
   }

   public List selectCommonDrugList(ClinItemCollection clinItemCollection) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      clinItemCollection.setDocCd(basEmployee.getEmplNumber());
      return this.clinItemCollectionMapper.selectCommonDrugList(clinItemCollection);
   }

   public List selectSetItemList(ClinItemCollection clinItemCollection) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      clinItemCollection.setDocCd(basEmployee.getEmplNumber());
      clinItemCollection.setHerbalFlag("0");
      clinItemCollection.setItemClassCd("00");
      return this.clinItemCollectionMapper.selectCommonDrugList(clinItemCollection);
   }

   public List selectHerbList(ClinItemCollection clinItemCollection) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      clinItemCollection.setDocCd(basEmployee.getEmplNumber());
      clinItemCollection.setHerbalFlag("1");
      clinItemCollection.setItemClassCd("01");
      return this.clinItemCollectionMapper.selectCommonDrugList(clinItemCollection);
   }

   public List selectRecipeList(ClinItemCollection clinItemCollection) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      clinItemCollection.setDocCd(basEmployee.getEmplNumber());
      clinItemCollection.setHerbalFlag("0");
      clinItemCollection.setItemClassCd("09");
      return this.clinItemCollectionMapper.selectCommonDrugList(clinItemCollection);
   }

   public List selectUnDrugItemList(ClinItemCollection clinItemCollection) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      clinItemCollection.setDocCd(basEmployee.getEmplNumber());
      clinItemCollection.setHerbalFlag("0");
      return this.clinItemCollectionMapper.selectUnDrugItemList(clinItemCollection);
   }

   public void deleteClinItemCollectionByItemCd(ClinItemCollection clinItemCollection) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      clinItemCollection.setDocCd(basEmployee.getEmplNumber());
      this.clinItemCollectionMapper.deleteClinItemCollectionByItemCd(clinItemCollection);
   }
}
