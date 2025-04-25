package com.emr.project.mrhp.service.impl;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.StringUtils;
import com.emr.framework.aspectj.lang.annotation.DataSource;
import com.emr.framework.aspectj.lang.enums.DataSourceType;
import com.emr.project.mrhp.domain.MrHpIcu;
import com.emr.project.mrhp.domain.mris.TdCmHpLineVo;
import com.emr.project.mrhp.domain.mris.TdCmIcuSave;
import com.emr.project.mrhp.domain.vo.MrHpAttachVo;
import com.emr.project.mrhp.mapper.MrHpIcuMapper;
import com.emr.project.mrhp.service.IMrHpIcuService;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.domain.vo.SqlVo;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MrHpIcuServiceImpl implements IMrHpIcuService {
   @Autowired
   private MrHpIcuMapper mrHpIcuMapper;

   public MrHpIcu selectMrHpIcuById(String icuId) {
      return this.mrHpIcuMapper.selectMrHpIcuById(icuId);
   }

   public List selectMrHpIcuList(MrHpIcu mrHpIcu) {
      return this.mrHpIcuMapper.selectMrHpIcuList(mrHpIcu);
   }

   public List selectMrHpIcuListByRecordId(String recordId) {
      return this.mrHpIcuMapper.selectMrHpIcuListByRecordId(recordId);
   }

   public int insertMrHpIcu(MrHpIcu mrHpIcu) {
      return this.mrHpIcuMapper.insertMrHpIcu(mrHpIcu);
   }

   public int updateMrHpIcu(MrHpIcu mrHpIcu) {
      return this.mrHpIcuMapper.updateMrHpIcu(mrHpIcu);
   }

   public int deleteMrHpIcuByIds(String[] recordIds) {
      return this.mrHpIcuMapper.deleteMrHpIcuByIds(recordIds);
   }

   public int deleteMrHpIcuById(String icuId) {
      return this.mrHpIcuMapper.deleteMrHpIcuById(icuId);
   }

   @DataSource(DataSourceType.icuPatientInout)
   public List selectMrHpIcuDataSourceList(SqlVo sqlVo) {
      return this.mrHpIcuMapper.selectMrHpIcuDataSourceList(sqlVo);
   }

   public void insertMrHpIcuList(MrHpAttachVo mrHpAttachVo) {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      List<MrHpIcu> list = mrHpAttachVo.getMrHpIcuList();
      List<MrHpIcu> listRes = new ArrayList();

      for(MrHpIcu mrHpIcu : list) {
         if (StringUtils.isNotBlank(String.valueOf(mrHpIcu.getIcuInTime())) && StringUtils.isNotBlank(mrHpIcu.getIcuCode())) {
            mrHpIcu.setRecordId(mrHpAttachVo.getRecordId());
            mrHpIcu.setPatientId(mrHpAttachVo.getPatientId());
            mrHpIcu.setIcuId(mrHpAttachVo.getRecordId() + mrHpIcu.getIcuNo());
            mrHpIcu.setCrePerCode(sysUser.getUserName());
            mrHpIcu.setCrePerName(sysUser.getNickName());
            listRes.add(mrHpIcu);
         }
      }

      if (listRes.size() > 0) {
         this.mrHpIcuMapper.deleteMrHpIcuByRecordId(mrHpAttachVo.getRecordId());
         this.mrHpIcuMapper.insertMrHpIcuList(listRes);
      }

   }

   public List selectMrisHpIcuListByRecordId(String recordId) {
      return this.mrHpIcuMapper.selectMrisHpIcuListByRecordId(recordId);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void insertMrisHpIcuList(TdCmHpLineVo mrHpVo) {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      List<TdCmIcuSave> tdCmIcuList = mrHpVo.getTdCmIcuList();
      List<MrHpIcu> listRes = new ArrayList();

      for(TdCmIcuSave icuSave : tdCmIcuList) {
         if (StringUtils.isNotBlank(String.valueOf(icuSave.getIcu_in_time())) && StringUtils.isNotBlank(icuSave.getIcu_code())) {
            MrHpIcu mrHpIcu = new MrHpIcu();
            mrHpIcu.setRecordId(mrHpVo.getRecord_id());
            mrHpIcu.setPatientId(mrHpVo.getPatient_id());
            mrHpIcu.setIcuId(mrHpVo.getRecord_id() + icuSave.getIcu_seq());
            mrHpIcu.setCrePerCode(sysUser.getUserName());
            mrHpIcu.setCrePerName(sysUser.getNickName());
            mrHpIcu.setIcuNo(icuSave.getIcu_seq());
            mrHpIcu.setIcuCode(icuSave.getIcu_code());
            mrHpIcu.setIcuName(icuSave.getIcu_name());
            mrHpIcu.setIcuInTime(icuSave.getIcu_in_time());
            mrHpIcu.setIcuOutTime(icuSave.getIcu_out_time());
            mrHpIcu.setIcuHour(icuSave.getIcu_hour());
            listRes.add(mrHpIcu);
         }
      }

      if (listRes.size() > 0) {
         this.mrHpIcuMapper.deleteMrHpIcuByRecordId(mrHpVo.getRecord_id());
         this.mrHpIcuMapper.insertMrHpIcuList(listRes);
      }

   }
}
