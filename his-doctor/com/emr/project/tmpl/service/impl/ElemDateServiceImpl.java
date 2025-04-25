package com.emr.project.tmpl.service.impl;

import com.emr.project.tmpl.domain.ElemDate;
import com.emr.project.tmpl.mapper.ElemDateMapper;
import com.emr.project.tmpl.service.IElemDateService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class ElemDateServiceImpl implements IElemDateService {
   @Autowired
   private ElemDateMapper elemDateMapper;

   public ElemDate selectElemDateById(Long id) {
      return this.elemDateMapper.selectElemDateById(id);
   }

   public List selectElemDateList(ElemDate elemDate) {
      return this.elemDateMapper.selectElemDateList(elemDate);
   }

   public List selectElemDateListByTempId(Long tempId) {
      return this.elemDateMapper.selectElemDateListByTempId(tempId);
   }

   public int insertElemDate(ElemDate elemDate) {
      return this.elemDateMapper.insertElemDate(elemDate);
   }

   @Async
   public void insertElemDateList(List elemDateList) throws Exception {
      this.elemDateMapper.insertElemDateList(elemDateList);
   }

   @Async
   public void insertElemDateStandardList(List elemDateList) throws Exception {
      this.elemDateMapper.insertElemDateStandardList(elemDateList);
   }

   public int updateElemDate(ElemDate elemDate) {
      return this.elemDateMapper.updateElemDate(elemDate);
   }

   public int deleteElemDateByIds(Long[] ids) {
      return this.elemDateMapper.deleteElemDateByIds(ids);
   }

   public int deleteElemDateById(Long id) {
      return this.elemDateMapper.deleteElemDateById(id);
   }

   public int deleteElemDateByTempId(Long tempId) {
      return this.elemDateMapper.deleteElemDateByTempId(tempId);
   }

   public int deleteElemDateStandardByTempId(Long tempId) {
      return this.elemDateMapper.deleteElemDateStandardByTempId(tempId);
   }
}
