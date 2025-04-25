package com.emr.project.webservice.mapper;

import com.emr.project.webservice.domain.EmrProjectMessage;
import java.util.List;

public interface EmrProjectMessageMapper {
   EmrProjectMessage selectEmrProjectMessageById(String projectCode);

   List selectEmrProjectMessageList(EmrProjectMessage emrProjectMessage);

   int insertEmrProjectMessage(EmrProjectMessage emrProjectMessage);

   int updateEmrProjectMessage(EmrProjectMessage emrProjectMessage);

   int deleteEmrProjectMessageById(String projectCode);

   int deleteEmrProjectMessageByIds(String[] projectCodes);
}
