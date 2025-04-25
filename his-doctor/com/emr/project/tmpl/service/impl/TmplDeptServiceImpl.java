package com.emr.project.tmpl.service.impl;

import com.emr.project.tmpl.domain.TmplDept;
import com.emr.project.tmpl.mapper.TmplDeptMapper;
import com.emr.project.tmpl.service.ITmplDeptService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TmplDeptServiceImpl implements ITmplDeptService {
   @Autowired
   private TmplDeptMapper tmplDeptMapper;

   public TmplDept selectTmplDeptById(Long id) {
      return this.tmplDeptMapper.selectTmplDeptById(id);
   }

   public List selectTmplDeptList(TmplDept tmplDept) {
      return this.tmplDeptMapper.selectTmplDeptList(tmplDept);
   }

   public int insertTmplDept(TmplDept tmplDept) {
      return this.tmplDeptMapper.insertTmplDept(tmplDept);
   }

   public int updateTmplDept(TmplDept tmplDept) {
      return this.tmplDeptMapper.updateTmplDept(tmplDept);
   }

   public int deleteTmplDeptByIds(Long[] ids) {
      return this.tmplDeptMapper.deleteTmplDeptByIds(ids);
   }

   public int deleteTmplDeptById(Long id) {
      return this.tmplDeptMapper.deleteTmplDeptById(id);
   }

   public int deleteTmplDeptByTempId(Long tempId) {
      return this.tmplDeptMapper.deleteTmplDeptByTempId(tempId);
   }

   public int insertTmplDeptList(List tmplDeptList) {
      return this.tmplDeptMapper.insertTmplDeptList(tmplDeptList);
   }

   public List getTempIdsByDepts(Long medicineId) {
      List<Long> ids = this.tmplDeptMapper.getTempIdsByDepts(medicineId);
      return ids;
   }
}
