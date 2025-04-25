package com.emr.project.tmpm.service.impl;

import com.emr.project.tmpm.domain.LisLabItemHis;
import com.emr.project.tmpm.mapper.LisLabItemHisMapper;
import com.emr.project.tmpm.service.ILisLabItemHisService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LisLabItemHisServiceImpl implements ILisLabItemHisService {
   @Autowired
   private LisLabItemHisMapper lisLabItemHisMapper;

   public LisLabItemHis selectLisLabItemHisById(Long id) {
      return this.lisLabItemHisMapper.selectLisLabItemHisById(id);
   }

   public List selectLisLabItemHisList(LisLabItemHis lisLabItemHis) {
      return this.lisLabItemHisMapper.selectLisLabItemHisList(lisLabItemHis);
   }

   public int insertLisLabItemHis(LisLabItemHis lisLabItemHis) {
      return this.lisLabItemHisMapper.insertLisLabItemHis(lisLabItemHis);
   }

   public int updateLisLabItemHis(LisLabItemHis lisLabItemHis) {
      return this.lisLabItemHisMapper.updateLisLabItemHis(lisLabItemHis);
   }

   public int deleteLisLabItemHisByIds(Long[] ids) {
      return this.lisLabItemHisMapper.deleteLisLabItemHisByIds(ids);
   }

   public int deleteLisLabItemHisById(Long id) {
      return this.lisLabItemHisMapper.deleteLisLabItemHisById(id);
   }
}
