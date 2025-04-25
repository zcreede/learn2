package com.emr.project.system.service.impl;

import com.emr.project.system.domain.TmplMedicineDept;
import com.emr.project.system.mapper.TmplMedicineDeptMapper;
import com.emr.project.system.service.ITmplMedicineDeptService;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TmplMedicineDeptServiceImpl implements ITmplMedicineDeptService {
   @Autowired
   private TmplMedicineDeptMapper tmplMedicineDeptMapper;

   public TmplMedicineDept selectTmplMedicineDeptById(Long id) {
      return this.tmplMedicineDeptMapper.selectTmplMedicineDeptById(id);
   }

   public List selectTmplMedicineDeptList(TmplMedicineDept tmplMedicineDept) {
      return this.tmplMedicineDeptMapper.selectTmplMedicineDeptList(tmplMedicineDept);
   }

   public int insertTmplMedicineDept(TmplMedicineDept tmplMedicineDept) {
      return this.tmplMedicineDeptMapper.insertTmplMedicineDept(tmplMedicineDept);
   }

   public int updateTmplMedicineDept(TmplMedicineDept tmplMedicineDept) {
      return this.tmplMedicineDeptMapper.updateTmplMedicineDept(tmplMedicineDept);
   }

   public int deleteTmplMedicineDeptByIds(Long[] ids) {
      return this.tmplMedicineDeptMapper.deleteTmplMedicineDeptByIds(ids);
   }

   public int deleteTmplMedicineDeptById(Long id) {
      return this.tmplMedicineDeptMapper.deleteTmplMedicineDeptById(id);
   }

   public void deleteTmplMedicineDeptByMedicineId(Long id) throws Exception {
      if (id != null) {
         this.tmplMedicineDeptMapper.deleteTmplMedicineDeptByMedicineId(id);
      }

   }

   public void insertList(List list) throws Exception {
      if (CollectionUtils.isNotEmpty(list)) {
         this.tmplMedicineDeptMapper.insertList(list);
      }

   }
}
