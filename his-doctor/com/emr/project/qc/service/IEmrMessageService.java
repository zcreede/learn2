package com.emr.project.qc.service;

import com.emr.project.qc.domain.EmrMessage;
import com.emr.project.qc.domain.EmrQcRecord;
import com.emr.project.qc.domain.vo.EmrMessageVo;
import java.util.List;

public interface IEmrMessageService {
   EmrMessage selectEmrMessageById(Long id);

   List selectEmrMessageList(EmrMessageVo emrMessageVo) throws Exception;

   List selectEmrMessageByDoctCd(EmrMessageVo emrMessageVo) throws Exception;

   int insertEmrMessage(EmrMessage emrMessage);

   int updateEmrMessage(EmrMessage emrMessage) throws Exception;

   void updateEmrMessageByBusId(EmrMessage emrMessage) throws Exception;

   int deleteEmrMessageByIds(List ids);

   int deleteEmrMessageById(Long id);

   void getSaveEmrMessageList(EmrQcRecord qcRecord, List list) throws Exception;

   void insertEmrMessageList(List emrMessageList) throws Exception;

   void updateEmrMessageList(List idList) throws Exception;

   List selectByBusId(EmrMessage emrMessage) throws Exception;
}
