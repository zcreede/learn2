package com.emr.project.qc.mapper;

import com.emr.project.qc.domain.EmrMessage;
import com.emr.project.qc.domain.vo.EmrMessageVo;
import java.util.List;

public interface EmrMessageMapper {
   EmrMessage selectEmrMessageById(Long id);

   List selectEmrMessageList(EmrMessageVo emrMessageVo);

   int insertEmrMessage(EmrMessage emrMessage);

   int updateEmrMessage(EmrMessage emrMessage);

   void updateEmrMessageByBusId(EmrMessage emrMessage);

   int deleteEmrMessageById(Long id);

   int deleteEmrMessageByIds(List list);

   void insertEmrMessageList(List emrMessageList);

   void updateEmrMessageList(List idList);

   List selectByBusId(EmrMessage emrMessage);
}
