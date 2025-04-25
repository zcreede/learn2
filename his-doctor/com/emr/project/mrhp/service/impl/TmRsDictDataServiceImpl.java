package com.emr.project.mrhp.service.impl;

import com.emr.common.utils.PinYinUtil;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.common.utils.StringUtils;
import com.emr.project.mrhp.domain.TmRsDictData;
import com.emr.project.mrhp.mapper.TmRsDictDataMapper;
import com.emr.project.mrhp.service.ITmRsDictDataService;
import com.emr.project.system.domain.SysUser;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TmRsDictDataServiceImpl implements ITmRsDictDataService {
   @Autowired
   private TmRsDictDataMapper tmRsDictDataMapper;

   public TmRsDictData selectTmRsDictDataById(Long id) {
      return this.tmRsDictDataMapper.selectTmRsDictDataById(id);
   }

   public List selectTmRsDictDataList(TmRsDictData tmRsDictData) {
      return this.tmRsDictDataMapper.selectTmRsDictDataList(tmRsDictData);
   }

   public int insertTmRsDictData(TmRsDictData tmRsDictData) {
      tmRsDictData.setId(SnowIdUtils.uniqueLong());
      SysUser user = SecurityUtils.getLoginUser().getUser();
      tmRsDictData.setCrePerName(user.getNickName());
      if (StringUtils.isEmpty(tmRsDictData.getInputstrphtc())) {
         tmRsDictData.setInputstrphtc(PinYinUtil.getPinYinHeadChar(tmRsDictData.getDataName()));
      }

      return this.tmRsDictDataMapper.insertTmRsDictData(tmRsDictData);
   }

   public int updateTmRsDictData(TmRsDictData tmRsDictData) {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      tmRsDictData.setAltPerName(user.getNickName());
      if (StringUtils.isEmpty(tmRsDictData.getInputstrphtc())) {
         tmRsDictData.setInputstrphtc(PinYinUtil.getPinYinHeadChar(tmRsDictData.getDataName()));
      }

      return this.tmRsDictDataMapper.updateTmRsDictData(tmRsDictData);
   }

   public int deleteTmRsDictDataByIds(Long[] ids) {
      return this.tmRsDictDataMapper.deleteTmRsDictDataByIds(ids);
   }

   public int deleteTmRsDictDataById(Long id) {
      return this.tmRsDictDataMapper.deleteTmRsDictDataById(id);
   }
}
