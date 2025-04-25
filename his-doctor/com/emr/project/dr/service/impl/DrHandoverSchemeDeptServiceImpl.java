package com.emr.project.dr.service.impl;

import com.emr.project.dr.domain.DrHandoverSchemeDept;
import com.emr.project.dr.mapper.DrHandoverSchemeDeptMapper;
import com.emr.project.dr.service.IDrHandoverSchemeDeptService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DrHandoverSchemeDeptServiceImpl implements IDrHandoverSchemeDeptService {
   @Autowired
   private DrHandoverSchemeDeptMapper drHandoverSchemeDeptMapper;

   public DrHandoverSchemeDept selectDrHandoverSchemeDeptById(Long id) {
      return this.drHandoverSchemeDeptMapper.selectDrHandoverSchemeDeptById(id);
   }

   public List selectDrHandoverSchemeDeptList(DrHandoverSchemeDept drHandoverSchemeDept) {
      return this.drHandoverSchemeDeptMapper.selectDrHandoverSchemeDeptList(drHandoverSchemeDept);
   }

   public int insertDrHandoverSchemeDept(DrHandoverSchemeDept drHandoverSchemeDept) {
      return this.drHandoverSchemeDeptMapper.insertDrHandoverSchemeDept(drHandoverSchemeDept);
   }

   public int updateDrHandoverSchemeDept(DrHandoverSchemeDept drHandoverSchemeDept) {
      return this.drHandoverSchemeDeptMapper.updateDrHandoverSchemeDept(drHandoverSchemeDept);
   }

   public int deleteDrHandoverSchemeDeptByIds(Long[] ids) {
      return this.drHandoverSchemeDeptMapper.deleteDrHandoverSchemeDeptByIds(ids);
   }

   public int deleteDrHandoverSchemeDeptById(Long id) {
      return this.drHandoverSchemeDeptMapper.deleteDrHandoverSchemeDeptById(id);
   }

   public void deleteBySchemeCdList(List schemeCdList) throws Exception {
      this.drHandoverSchemeDeptMapper.deleteBySchemeCdList(schemeCdList);
   }

   public List selectBySchemeCdList(List schemeCdList) throws Exception {
      return this.drHandoverSchemeDeptMapper.selectBySchemeCdList(schemeCdList);
   }

   public void insertList(List list) throws Exception {
      this.drHandoverSchemeDeptMapper.insertList(list);
   }

   public List selectByDeptCdList(List deptCdList) throws Exception {
      return this.drHandoverSchemeDeptMapper.selectByDeptCdList(deptCdList);
   }
}
