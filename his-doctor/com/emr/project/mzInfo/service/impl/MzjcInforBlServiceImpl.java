package com.emr.project.mzInfo.service.impl;

import com.emr.framework.aspectj.lang.annotation.DataSource;
import com.emr.framework.aspectj.lang.enums.DataSourceType;
import com.emr.project.mzInfo.domain.MzjzInfoBl;
import com.emr.project.mzInfo.mapper.MzJzInfoBlMapper;
import com.emr.project.mzInfo.service.IMzjzInforBlService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MzjcInforBlServiceImpl implements IMzjzInforBlService {
   @Autowired
   private MzJzInfoBlMapper mzJzInfoBlMapper;

   @DataSource(DataSourceType.lbMzdb)
   public List selectMzjzInfoBlList(String mzh) throws Exception {
      List<MzjzInfoBl> mzjzInfoBlList = this.mzJzInfoBlMapper.selectMzjzInfoBldList(mzh);
      return mzjzInfoBlList;
   }
}
