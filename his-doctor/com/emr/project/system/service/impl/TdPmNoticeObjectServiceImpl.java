package com.emr.project.system.service.impl;

import com.emr.project.system.domain.TdPmNoticeObject;
import com.emr.project.system.mapper.TdPmNoticeObjectMapper;
import com.emr.project.system.service.ITdPmNoticeObjectService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TdPmNoticeObjectServiceImpl implements ITdPmNoticeObjectService {
   @Autowired
   private TdPmNoticeObjectMapper tdPmNoticeObjectMapper;

   public TdPmNoticeObject selectTdPmNoticeObjectById(Long id) {
      return this.tdPmNoticeObjectMapper.selectTdPmNoticeObjectById(id);
   }

   public List selectTdPmNoticeObjectList(TdPmNoticeObject tdPmNoticeObject) {
      return this.tdPmNoticeObjectMapper.selectTdPmNoticeObjectList(tdPmNoticeObject);
   }

   public int insertTdPmNoticeObject(TdPmNoticeObject tdPmNoticeObject) {
      return this.tdPmNoticeObjectMapper.insertTdPmNoticeObject(tdPmNoticeObject);
   }

   public int updateTdPmNoticeObject(TdPmNoticeObject tdPmNoticeObject) {
      return this.tdPmNoticeObjectMapper.updateTdPmNoticeObject(tdPmNoticeObject);
   }

   public int deleteTdPmNoticeObjectByIds(Long[] ids) {
      return this.tdPmNoticeObjectMapper.deleteTdPmNoticeObjectByIds(ids);
   }

   public int deleteTdPmNoticeObjectById(Long id) {
      return this.tdPmNoticeObjectMapper.deleteTdPmNoticeObjectById(id);
   }
}
