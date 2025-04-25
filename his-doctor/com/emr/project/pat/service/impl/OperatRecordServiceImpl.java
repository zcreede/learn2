package com.emr.project.pat.service.impl;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.project.pat.domain.OperatRecord;
import com.emr.project.pat.domain.vo.OperatRecordVo;
import com.emr.project.pat.mapper.OperatRecordMapper;
import com.emr.project.pat.service.IOperatRecordService;
import com.emr.project.system.domain.SysUser;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperatRecordServiceImpl implements IOperatRecordService {
   @Autowired
   private OperatRecordMapper operatRecordMapper;

   public OperatRecord selectOperatRecordById(String oprId) {
      return this.operatRecordMapper.selectOperatRecordById(oprId);
   }

   public List selectOperatRecordList(OperatRecord operatRecord) {
      return this.operatRecordMapper.selectOperatRecordList(operatRecord);
   }

   public int insertOperatRecord(OperatRecord operatRecord) {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      operatRecord.setOprId(SnowIdUtils.uniqueLongHex());
      operatRecord.setCrePerCode(user.getUserName());
      operatRecord.setCrePerName(user.getNickName());
      return this.operatRecordMapper.insertOperatRecord(operatRecord);
   }

   public int updateOperatRecord(OperatRecord operatRecord) {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      operatRecord.setAltPerCode(user.getUserName());
      operatRecord.setAltPerName(user.getNickName());
      return this.operatRecordMapper.updateOperatRecord(operatRecord);
   }

   public int deleteOperatRecordByIds(String[] oprIds) {
      return this.operatRecordMapper.deleteOperatRecordByIds(oprIds);
   }

   public int deleteOperatRecordById(String oprId) {
      return this.operatRecordMapper.deleteOperatRecordById(oprId);
   }

   public List selectOperatRecordByDate(OperatRecordVo operatRecordVo) {
      return this.operatRecordMapper.selectOperatRecordByDate(operatRecordVo);
   }
}
