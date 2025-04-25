package com.emr.project.qc.service.impl;

import com.emr.project.qc.domain.QcAgiEven;
import com.emr.project.qc.mapper.QcAgiEvenMapper;
import com.emr.project.qc.service.IQcAgiEvenService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QcAgiEvenServiceImpl implements IQcAgiEvenService {
   @Autowired
   private QcAgiEvenMapper qcAgiEvenMapper;

   public QcAgiEven selectQcAgiEvenById(Long id) {
      return this.qcAgiEvenMapper.selectQcAgiEvenById(id);
   }

   public List selectQcAgiEvenList(QcAgiEven qcAgiEven) {
      return this.qcAgiEvenMapper.selectQcAgiEvenList(qcAgiEven);
   }

   public List selectListByEvenCodes(List evenCodeList) throws Exception {
      List<QcAgiEven> list = new ArrayList(1);
      if (evenCodeList != null && !evenCodeList.isEmpty()) {
         list = this.qcAgiEvenMapper.selectListByEvenCodes(evenCodeList);
      }

      return list;
   }

   public int insertQcAgiEven(QcAgiEven qcAgiEven) {
      return this.qcAgiEvenMapper.insertQcAgiEven(qcAgiEven);
   }

   public int updateQcAgiEven(QcAgiEven qcAgiEven) {
      return this.qcAgiEvenMapper.updateQcAgiEven(qcAgiEven);
   }

   public int deleteQcAgiEvenByIds(Long[] ids) {
      return this.qcAgiEvenMapper.deleteQcAgiEvenByIds(ids);
   }

   public int deleteQcAgiEvenById(Long id) {
      return this.qcAgiEvenMapper.deleteQcAgiEvenById(id);
   }
}
