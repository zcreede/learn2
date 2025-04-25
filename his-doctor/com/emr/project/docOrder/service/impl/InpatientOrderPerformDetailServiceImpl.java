package com.emr.project.docOrder.service.impl;

import com.emr.project.docOrder.domain.InpatientOrderPerformDetail;
import com.emr.project.docOrder.mapper.InpatientOrderPerformDetailMapper;
import com.emr.project.docOrder.service.InpatientOrderPerformDetailService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InpatientOrderPerformDetailServiceImpl implements InpatientOrderPerformDetailService {
   @Autowired
   public InpatientOrderPerformDetailMapper inpatientOrderPerformDetailMapper;

   public InpatientOrderPerformDetail selectByPrimaryKey(InpatientOrderPerformDetail inpd) {
      return this.inpatientOrderPerformDetailMapper.selectByPrimaryKey(inpd);
   }

   public List selectListByNo(Map map) {
      return this.inpatientOrderPerformDetailMapper.selectListByNo(map);
   }

   public int addList(List detailList) throws Exception {
      return this.inpatientOrderPerformDetailMapper.insertList(detailList);
   }
}
