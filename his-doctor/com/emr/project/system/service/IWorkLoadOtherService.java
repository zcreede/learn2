package com.emr.project.system.service;

import com.emr.project.system.domain.WorkLoadOther;
import java.util.List;

public interface IWorkLoadOtherService {
   WorkLoadOther selectWorkLoadOtherById(Long id);

   List selectWorkLoadOtherList(WorkLoadOther workLoadOther);

   void insertWorkLoadOther(List otherList) throws Exception;

   void updateWorkLoadOther(List otherList) throws Exception;

   int deleteWorkLoadOtherByIds(Long[] ids);

   int deleteWorkLoadOtherById(Long id);
}
