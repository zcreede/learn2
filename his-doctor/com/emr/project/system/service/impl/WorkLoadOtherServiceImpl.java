package com.emr.project.system.service.impl;

import com.emr.project.system.domain.WorkLoadOther;
import com.emr.project.system.mapper.WorkLoadOtherMapper;
import com.emr.project.system.service.IWorkLoadOtherService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkLoadOtherServiceImpl implements IWorkLoadOtherService {
   @Autowired
   private WorkLoadOtherMapper workLoadOtherMapper;

   public WorkLoadOther selectWorkLoadOtherById(Long id) {
      return this.workLoadOtherMapper.selectWorkLoadOtherById(id);
   }

   public List selectWorkLoadOtherList(WorkLoadOther workLoadOther) {
      return this.workLoadOtherMapper.selectWorkLoadOtherList(workLoadOther);
   }

   public void insertWorkLoadOther(List otherList) throws Exception {
      List<WorkLoadOther> insertList = (List)otherList.stream().filter((t) -> t.getId() == null).collect(Collectors.toList());
      List<WorkLoadOther> updateList = (List)otherList.stream().filter((t) -> t.getId() != null).collect(Collectors.toList());
      if (!insertList.isEmpty()) {
         this.workLoadOtherMapper.insertWorkLoadOtherList(otherList);
      }

      if (!updateList.isEmpty()) {
         this.workLoadOtherMapper.updateWorkLoadOtherList(otherList);
      }

   }

   public void updateWorkLoadOther(List otherList) throws Exception {
      this.workLoadOtherMapper.updateWorkLoadOtherList(otherList);
   }

   public int deleteWorkLoadOtherByIds(Long[] ids) {
      return this.workLoadOtherMapper.deleteWorkLoadOtherByIds(ids);
   }

   public int deleteWorkLoadOtherById(Long id) {
      return this.workLoadOtherMapper.deleteWorkLoadOtherById(id);
   }
}
