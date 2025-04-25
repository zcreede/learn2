package com.emr.project.qc.service.impl;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.project.emr.domain.Index;
import com.emr.project.emr.domain.SubfileIndex;
import com.emr.project.mrhp.domain.MrHp;
import com.emr.project.qc.domain.EmrQcCommRecord;
import com.emr.project.qc.mapper.EmrQcCommRecordMapper;
import com.emr.project.qc.service.IEmrQcCommRecordService;
import com.emr.project.qc.service.IEmrQcListService;
import com.emr.project.qc.util.IndexUtil;
import com.emr.project.system.domain.BasEmployee;
import com.emr.project.system.domain.SysDictData;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.service.ISysDictTypeService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmrQcCommRecordServiceImpl implements IEmrQcCommRecordService {
   @Autowired
   private EmrQcCommRecordMapper emrQcCommRecordMapper;
   @Autowired
   private ISysDictTypeService sysDictTypeService;
   @Autowired
   private IEmrQcListService emrQcListService;

   public EmrQcCommRecord selectEmrQcCommRecordById(Long id) {
      return this.emrQcCommRecordMapper.selectEmrQcCommRecordById(id);
   }

   public List selectEmrQcCommRecordByMainId(List mainIdList) throws Exception {
      return this.emrQcCommRecordMapper.selectEmrQcCommRecordByMainIdList(mainIdList);
   }

   public List selectEmrQcCommRecordList(EmrQcCommRecord emrQcCommRecord) {
      return this.emrQcCommRecordMapper.selectEmrQcCommRecordList(emrQcCommRecord);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void insertEmrQcCommRecord(EmrQcCommRecord emrQcCommRecord, MrHp mrHp, Index index, SubfileIndex subfileIndex, SysUser user) throws Exception {
      List<SysDictData> dictDataList = this.sysDictTypeService.selectDictDataByType("s004");
      Map<String, Object> indexInfoMap = IndexUtil.getIndexMrTypeAndName(dictDataList, mrHp, index, subfileIndex);
      String mrType = indexInfoMap.get("mrType").toString();
      String mrTypeName = indexInfoMap.get("mrTypeName").toString();
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      emrQcCommRecord.setMrType(mrType);
      emrQcCommRecord.setMrTypeName(mrTypeName);
      emrQcCommRecord.setFedbPerId(basEmployee.getEmplNumber());
      emrQcCommRecord.setFedbPerName(basEmployee.getEmplName());
      emrQcCommRecord.setCrePerCode(basEmployee.getEmplNumber());
      emrQcCommRecord.setCrePerName(basEmployee.getEmplName());
      emrQcCommRecord.setId(SnowIdUtils.uniqueLong());
      this.emrQcListService.updateQconTimesById(emrQcCommRecord.getMainId());
      this.emrQcListService.updateStateByComm(emrQcCommRecord, user);
      this.emrQcCommRecordMapper.insertEmrQcCommRecord(emrQcCommRecord);
   }

   public void insertOrderQcCommRecord(EmrQcCommRecord emrQcCommRecord, SysUser user) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      emrQcCommRecord.setMrType("64");
      emrQcCommRecord.setMrTypeName("医嘱本");
      emrQcCommRecord.setFedbPerId(basEmployee.getEmplNumber());
      emrQcCommRecord.setFedbPerName(basEmployee.getEmplName());
      emrQcCommRecord.setCrePerCode(basEmployee.getEmplNumber());
      emrQcCommRecord.setCrePerName(basEmployee.getEmplName());
      emrQcCommRecord.setId(SnowIdUtils.uniqueLong());
      this.emrQcListService.updateQconTimesById(emrQcCommRecord.getMainId());
      this.emrQcListService.updateStateByComm(emrQcCommRecord, user);
      this.emrQcCommRecordMapper.insertEmrQcCommRecord(emrQcCommRecord);
   }

   public void insertList(List list) throws Exception {
      this.emrQcCommRecordMapper.insertList(list);
   }

   public int updateEmrQcCommRecord(EmrQcCommRecord emrQcCommRecord) {
      return this.emrQcCommRecordMapper.updateEmrQcCommRecord(emrQcCommRecord);
   }

   public int deleteEmrQcCommRecordByIds(Long[] ids) {
      return this.emrQcCommRecordMapper.deleteEmrQcCommRecordByIds(ids);
   }

   public int deleteEmrQcCommRecordById(Long id) {
      return this.emrQcCommRecordMapper.deleteEmrQcCommRecordById(id);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void insertEmrQcListByMrHp(EmrQcCommRecord emrQcCommRecord, SysUser user) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      emrQcCommRecord.setMrType("61");
      emrQcCommRecord.setMrTypeName("病案首页");
      emrQcCommRecord.setFedbPerId(basEmployee.getEmplNumber());
      emrQcCommRecord.setFedbPerName(basEmployee.getEmplName());
      emrQcCommRecord.setCrePerCode(basEmployee.getEmplNumber());
      emrQcCommRecord.setCrePerName(basEmployee.getEmplName());
      emrQcCommRecord.setId(SnowIdUtils.uniqueLong());
      this.emrQcListService.updateQconTimesById(emrQcCommRecord.getMainId());
      this.emrQcListService.updateStateByComm(emrQcCommRecord, user);
      this.emrQcCommRecordMapper.insertEmrQcCommRecord(emrQcCommRecord);
   }
}
