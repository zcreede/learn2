package com.emr.project.webservice.service.impl;

import com.emr.project.webservice.domain.EmrProjectMessage;
import com.emr.project.webservice.mapper.EmrProjectMessageMapper;
import com.emr.project.webservice.service.IEmrProjectMessageService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmrProjectMessageServiceImpl implements IEmrProjectMessageService {
   @Autowired
   private EmrProjectMessageMapper emrProjectMessageMapper;

   public EmrProjectMessage selectEmrProjectMessageById(String projectCode) {
      return this.emrProjectMessageMapper.selectEmrProjectMessageById(projectCode);
   }

   public List selectEmrProjectMessageList(EmrProjectMessage emrProjectMessage) {
      return this.emrProjectMessageMapper.selectEmrProjectMessageList(emrProjectMessage);
   }

   public int insertEmrProjectMessage(EmrProjectMessage emrProjectMessage) {
      return this.emrProjectMessageMapper.insertEmrProjectMessage(emrProjectMessage);
   }

   public int updateEmrProjectMessage(EmrProjectMessage emrProjectMessage) {
      return this.emrProjectMessageMapper.updateEmrProjectMessage(emrProjectMessage);
   }

   public int deleteEmrProjectMessageByIds(String[] projectCodes) {
      return this.emrProjectMessageMapper.deleteEmrProjectMessageByIds(projectCodes);
   }

   public int deleteEmrProjectMessageById(String projectCode) {
      return this.emrProjectMessageMapper.deleteEmrProjectMessageById(projectCode);
   }
}
