package com.emr.project.system.service.impl;

import com.emr.common.utils.DateUtils;
import com.emr.project.system.domain.TmBsDeptTypeContrast;
import com.emr.project.system.domain.vo.TmBsDeptTypeContrastVo;
import com.emr.project.system.mapper.TmBsDeptTypeContrastMapper;
import com.emr.project.system.service.ITmBsDeptTypeContrastService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TmBsDeptTypeContrastServiceImpl implements ITmBsDeptTypeContrastService {
   @Autowired
   private TmBsDeptTypeContrastMapper tmBsDeptTypeContrastMapper;

   public List selectTmBsDeptTypeContrastById(String deptCode) {
      return this.tmBsDeptTypeContrastMapper.selectTmBsDeptTypeContrastById(deptCode);
   }

   public List selectTmBsDeptTypeContrastList(TmBsDeptTypeContrast tmBsDeptTypeContrast) {
      return this.tmBsDeptTypeContrastMapper.selectTmBsDeptTypeContrastList(tmBsDeptTypeContrast);
   }

   public List selectTmBsDeptTypeContrastVoList(TmBsDeptTypeContrastVo tmBsDeptTypeContrastVo) {
      List<TmBsDeptTypeContrastVo> aaa = this.tmBsDeptTypeContrastMapper.selectTmBsDeptTypeContrastVoList(tmBsDeptTypeContrastVo);
      return aaa;
   }

   public int insertTmBsDeptTypeContrast(TmBsDeptTypeContrast tmBsDeptTypeContrast) {
      tmBsDeptTypeContrast.setCreateTime(DateUtils.getNowDate());
      return this.tmBsDeptTypeContrastMapper.insertTmBsDeptTypeContrast(tmBsDeptTypeContrast);
   }

   public int updateTmBsDeptTypeContrast(TmBsDeptTypeContrast tmBsDeptTypeContrast) {
      tmBsDeptTypeContrast.setUpdateTime(DateUtils.getNowDate());
      return this.tmBsDeptTypeContrastMapper.updateTmBsDeptTypeContrast(tmBsDeptTypeContrast);
   }

   public int deleteTmBsDeptTypeContrastByIds(String[] deptCodes) {
      return this.tmBsDeptTypeContrastMapper.deleteTmBsDeptTypeContrastByIds(deptCodes);
   }

   public int deleteTmBsDeptTypeContrastById(String deptCode) {
      return this.tmBsDeptTypeContrastMapper.deleteTmBsDeptTypeContrastById(deptCode);
   }
}
