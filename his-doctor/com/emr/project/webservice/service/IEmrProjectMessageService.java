package com.emr.project.webservice.service;

import com.emr.project.webservice.domain.EmrProjectMessage;
import java.util.List;

public interface IEmrProjectMessageService {
   EmrProjectMessage selectEmrProjectMessageById(String projectCode);

   List selectEmrProjectMessageList(EmrProjectMessage emrProjectMessage);

   int insertEmrProjectMessage(EmrProjectMessage emrProjectMessage);

   int updateEmrProjectMessage(EmrProjectMessage emrProjectMessage);

   int deleteEmrProjectMessageByIds(String[] projectCodes);

   int deleteEmrProjectMessageById(String projectCode);
}
