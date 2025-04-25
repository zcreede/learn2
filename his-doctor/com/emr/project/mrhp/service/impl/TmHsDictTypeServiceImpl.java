package com.emr.project.mrhp.service.impl;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.project.mrhp.domain.TmHsDictType;
import com.emr.project.mrhp.mapper.TmHsDictTypeMapper;
import com.emr.project.mrhp.service.ITmHsDictDataService;
import com.emr.project.mrhp.service.ITmHsDictTypeService;
import com.emr.project.system.domain.SysUser;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TmHsDictTypeServiceImpl implements ITmHsDictTypeService {
   @Autowired
   private TmHsDictTypeMapper tmHsDictTypeMapper;
   @Autowired
   private ITmHsDictDataService tmHsDictDataService;

   public TmHsDictType selectTmHsDictTypeById(Long id) {
      return this.tmHsDictTypeMapper.selectTmHsDictTypeById(id);
   }

   public List selectTmHsDictTypeList(TmHsDictType tmHsDictType) {
      return this.tmHsDictTypeMapper.selectTmHsDictTypeList(tmHsDictType);
   }

   public int insertTmHsDictType(TmHsDictType tmHsDictType) {
      return this.tmHsDictTypeMapper.insertTmHsDictType(tmHsDictType);
   }

   public int updateTmHsDictType(TmHsDictType tmHsDictType) {
      return this.tmHsDictTypeMapper.updateTmHsDictType(tmHsDictType);
   }

   public int deleteTmHsDictTypeByIds(Long[] ids) {
      return this.tmHsDictTypeMapper.deleteTmHsDictTypeByIds(ids);
   }

   public int deleteTmHsDictTypeById(Long id) {
      return this.tmHsDictTypeMapper.deleteTmHsDictTypeById(id);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void insertData(List list) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      List<TmHsDictType> insertList = (List)list.stream().filter((t) -> t.getId() == null).collect(Collectors.toList());
      if (!insertList.isEmpty()) {
         for(TmHsDictType type : insertList) {
            type.setId(SnowIdUtils.uniqueLong());
            type.setState("0");
            type.setCrePerName(user.getNickName());
         }

         this.tmHsDictTypeMapper.insertList(insertList);
         List<String> typeList = (List)insertList.stream().map(TmHsDictType::getDictType).collect(Collectors.toList());
         this.tmHsDictDataService.insertDataByTypeList(typeList);
      }

   }
}
