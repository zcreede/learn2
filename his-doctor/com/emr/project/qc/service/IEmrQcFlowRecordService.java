package com.emr.project.qc.service;

import com.emr.project.qc.domain.EmrQcFlow;
import com.emr.project.qc.domain.EmrQcFlowRecord;
import com.emr.project.qc.domain.vo.EmrQcFlowRecordVo;
import com.emr.project.qc.domain.vo.EmrQcFlowVo;
import com.emr.project.system.domain.BasEmployee;
import java.util.List;

public interface IEmrQcFlowRecordService {
   EmrQcFlowRecord selectEmrQcFlowRecordById(Long id);

   List selectEmrQcFlowRecordList(EmrQcFlowRecord emrQcFlowRecord);

   List selectEmrQcFlowRecordVoList(EmrQcFlowRecordVo emrQcFlowRecordVo) throws Exception;

   List selectEmrQcFlowRecordByQcSns(List list);

   int insertEmrQcFlowRecord(EmrQcFlowRecord emrQcFlowRecord);

   void insertEmrQcFlowRecord(EmrQcFlowVo emrQcFlowVo, String operTypeCd, String operTypeName, String operReason);

   void insertEmrQcFlowRecord(EmrQcFlow emrQcFlow, String operTypeCd, String operTypeName, String ip, BasEmployee basEmployee);

   int updateEmrQcFlowRecord(EmrQcFlowRecord emrQcFlowRecord);

   int deleteEmrQcFlowRecordByIds(Long[] ids);

   int deleteEmrQcFlowRecordById(Long id);
}
