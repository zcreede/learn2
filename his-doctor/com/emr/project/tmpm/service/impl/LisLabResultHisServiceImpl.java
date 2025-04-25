package com.emr.project.tmpm.service.impl;

import com.emr.project.tmpm.domain.LisLabResultHis;
import com.emr.project.tmpm.mapper.LisLabResultHisMapper;
import com.emr.project.tmpm.service.ILisLabResultHisService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LisLabResultHisServiceImpl implements ILisLabResultHisService {
   @Autowired
   private LisLabResultHisMapper lisLabResultHisMapper;

   public LisLabResultHis selectLisLabResultHisById(Long id) {
      return this.lisLabResultHisMapper.selectLisLabResultHisById(id);
   }

   public List selectLisLabResultHisList(LisLabResultHis lisLabResultHis) {
      return this.lisLabResultHisMapper.selectLisLabResultHisList(lisLabResultHis);
   }

   public int insertLisLabResultHis(LisLabResultHis lisLabResultHis) {
      return this.lisLabResultHisMapper.insertLisLabResultHis(lisLabResultHis);
   }

   public int updateLisLabResultHis(LisLabResultHis lisLabResultHis) {
      return this.lisLabResultHisMapper.updateLisLabResultHis(lisLabResultHis);
   }

   public int deleteLisLabResultHisByIds(Long[] ids) {
      return this.lisLabResultHisMapper.deleteLisLabResultHisByIds(ids);
   }

   public int deleteLisLabResultHisById(Long id) {
      return this.lisLabResultHisMapper.deleteLisLabResultHisById(id);
   }
}
