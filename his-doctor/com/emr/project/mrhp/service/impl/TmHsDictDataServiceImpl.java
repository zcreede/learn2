package com.emr.project.mrhp.service.impl;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.project.mrhp.domain.TmHsDictData;
import com.emr.project.mrhp.mapper.TmHsDictDataMapper;
import com.emr.project.mrhp.service.ITmHsDictDataService;
import com.emr.project.system.domain.SysUser;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TmHsDictDataServiceImpl implements ITmHsDictDataService {
   @Autowired
   private TmHsDictDataMapper tmHsDictDataMapper;

   public TmHsDictData selectTmHsDictDataById(Long id) {
      return this.tmHsDictDataMapper.selectTmHsDictDataById(id);
   }

   public List selectTmHsDictDataList(TmHsDictData tmHsDictData) {
      return this.tmHsDictDataMapper.selectTmHsDictDataList(tmHsDictData);
   }

   public int insertTmHsDictData(TmHsDictData tmHsDictData) {
      return this.tmHsDictDataMapper.insertTmHsDictData(tmHsDictData);
   }

   public int updateTmHsDictData(TmHsDictData tmHsDictData) {
      return this.tmHsDictDataMapper.updateTmHsDictData(tmHsDictData);
   }

   public int deleteTmHsDictDataByIds(Long[] ids) {
      return this.tmHsDictDataMapper.deleteTmHsDictDataByIds(ids);
   }

   public int deleteTmHsDictDataById(Long id) {
      return this.tmHsDictDataMapper.deleteTmHsDictDataById(id);
   }

   public void deleteByTypeList(List dictTypeList) {
      this.tmHsDictDataMapper.deleteByTypeList(dictTypeList);
   }

   public void insertDataByTypeList(List typeList) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      List<TmHsDictData> dataList = this.tmHsDictDataMapper.selectDataByTypeList(typeList);

      for(TmHsDictData data : dataList) {
         data.setId(SnowIdUtils.uniqueLong());
         data.setCrePerName(user.getNickName());
      }

      this.tmHsDictDataMapper.insertAllList(dataList);
   }
}
