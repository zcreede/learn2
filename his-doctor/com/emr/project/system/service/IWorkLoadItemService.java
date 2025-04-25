package com.emr.project.system.service;

import com.emr.project.system.domain.WorkLoadItem;
import java.util.List;

public interface IWorkLoadItemService {
   WorkLoadItem selectWorkLoadItemById(Long id);

   List selectWorkLoadItemList(WorkLoadItem workLoadItem);

   int insertWorkLoadItem(WorkLoadItem workLoadItem) throws Exception;

   int updateWorkLoadItem(WorkLoadItem workLoadItem) throws Exception;

   int deleteWorkLoadItemByIds(Long[] ids);

   int deleteWorkLoadItemById(Long id);

   List selectDsPreserveOutList() throws Exception;
}
