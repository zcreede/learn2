package com.emr.project.mrhp.service.impl;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.project.mrhp.domain.TmRsDictTypt;
import com.emr.project.mrhp.mapper.TmRsDictTyptMapper;
import com.emr.project.mrhp.service.ITmRsDictTyptService;
import com.emr.project.system.domain.SysUser;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TmRsDictTyptServiceImpl implements ITmRsDictTyptService {
   @Autowired
   private TmRsDictTyptMapper tmRsDictTyptMapper;

   public TmRsDictTypt selectTmRsDictTyptById(Long id) {
      return this.tmRsDictTyptMapper.selectTmRsDictTyptById(id);
   }

   public List selectTmRsDictTyptList(TmRsDictTypt tmRsDictTypt) {
      return this.tmRsDictTyptMapper.selectTmRsDictTyptList(tmRsDictTypt);
   }

   public int insertTmRsDictTypt(TmRsDictTypt tmRsDictTypt) {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      tmRsDictTypt.setCrePerName(user.getNickName());
      tmRsDictTypt.setId(SnowIdUtils.uniqueLong());
      return this.tmRsDictTyptMapper.insertTmRsDictTypt(tmRsDictTypt);
   }

   public int updateTmRsDictTypt(TmRsDictTypt tmRsDictTypt) {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      tmRsDictTypt.setAltPerName(user.getNickName());
      return this.tmRsDictTyptMapper.updateTmRsDictTypt(tmRsDictTypt);
   }

   public int deleteTmRsDictTyptByIds(Long[] ids) {
      return this.tmRsDictTyptMapper.deleteTmRsDictTyptByIds(ids);
   }

   public int deleteTmRsDictTyptById(Long id) {
      return this.tmRsDictTyptMapper.deleteTmRsDictTyptById(id);
   }

   public Boolean checkDefineRemove(Long id) {
      List<TmRsDictTypt> list = this.tmRsDictTyptMapper.selectListByDefineId(id);
      return !list.isEmpty() ? Boolean.FALSE : Boolean.TRUE;
   }

   public List selectDictTypeByTypeCode(String dictTypeCode) {
      return this.tmRsDictTyptMapper.selectDictTypeByTypeCode(dictTypeCode);
   }
}
