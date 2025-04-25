package com.emr.project.mrhp.service.impl;

import com.alibaba.fastjson.JSON;
import com.emr.framework.aspectj.lang.annotation.DataSource;
import com.emr.framework.aspectj.lang.enums.DataSourceType;
import com.emr.project.mrhp.domain.MrHpFee;
import com.emr.project.mrhp.domain.vo.MrHpFeePbVo;
import com.emr.project.mrhp.domain.vo.TdCmFeeVo;
import com.emr.project.mrhp.mapper.MrHpFeeMapper;
import com.emr.project.mrhp.service.IMrHpFeeService;
import com.emr.project.system.domain.vo.SqlVo;
import com.emr.project.system.mapper.SyncDatasourceMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MrHpFeeServiceImpl implements IMrHpFeeService {
   @Autowired
   private MrHpFeeMapper mrHpFeeMapper;
   @Autowired
   private SyncDatasourceMapper syncDatasourceMapper;

   public MrHpFee selectMrHpFeeById(String feeId) {
      return this.mrHpFeeMapper.selectMrHpFeeById(feeId);
   }

   public TdCmFeeVo selectMrHpFeeByRecordId(String recordId) {
      return this.mrHpFeeMapper.selectMrHpFeeByRecordId(recordId);
   }

   public List selectMrHpFeeList(MrHpFee mrHpFee) {
      return this.mrHpFeeMapper.selectMrHpFeeList(mrHpFee);
   }

   public List selectMrHpFeeListByProc(Map param) throws Exception {
      this.mrHpFeeMapper.selectMrHpFeeListByProc(param);
      List<MrHpFee> list = (List)param.get("mrHpFee");
      return list;
   }

   public int insertMrHpFee(MrHpFee mrHpFee) {
      return this.mrHpFeeMapper.insertMrHpFee(mrHpFee);
   }

   public int updateMrHpFee(MrHpFee mrHpFee) {
      return this.mrHpFeeMapper.updateMrHpFee(mrHpFee);
   }

   public int deleteMrHpFeeByIds(String[] feeIds) {
      return this.mrHpFeeMapper.deleteMrHpFeeByIds(feeIds);
   }

   public int deleteMrHpFeeById(String feeId) {
      return this.mrHpFeeMapper.deleteMrHpFeeById(feeId);
   }

   @DataSource(DataSourceType.hisMrHpAmount)
   public List selectMrHpHisList(SqlVo sqlVo) throws Exception {
      List<MrHpFee> feelist = new ArrayList();

      for(Map map1 : this.syncDatasourceMapper.selectMapList(sqlVo)) {
         String str = JSON.toJSONString(map1);
         MrHpFee mrHpFee = (MrHpFee)JSON.parseObject(str, MrHpFee.class);
         feelist.add(mrHpFee);
      }

      return feelist;
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void saveHpFeeList(List mrHpFeeList) throws Exception {
      if (mrHpFeeList.size() > 0) {
         this.mrHpFeeMapper.deleteMrHpFeeByRescordId(((MrHpFee)mrHpFeeList.get(0)).getRecordId());
         this.mrHpFeeMapper.insertMrHpFeeList(mrHpFeeList);
      }

   }

   public List selectHisPbMrHpFeeList(SqlVo sqlVo) throws Exception {
      return this.mrHpFeeMapper.selectHisPbMrHpFeeList(sqlVo);
   }

   public MrHpFeePbVo selectHisPbMrHpFeeKJList(SqlVo sqlVo) throws Exception {
      return this.mrHpFeeMapper.selectHisPbMrHpFeeKJList(sqlVo);
   }

   public MrHpFeePbVo selectHisPbMrHpFeeZFList(SqlVo sqlVo) throws Exception {
      return this.mrHpFeeMapper.selectHisPbMrHpFeeZFList(sqlVo);
   }
}
