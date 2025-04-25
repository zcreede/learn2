package com.emr.project.system.service;

import com.emr.project.system.domain.WorkLoadMain;
import com.emr.project.system.domain.req.WorkLoadNewBuiltReq;
import com.emr.project.system.domain.req.WorkLoadReportReq;
import com.emr.project.system.domain.req.WorkLoadSaveReq;
import java.util.Date;
import java.util.List;

public interface IWorkLoadMainService {
   WorkLoadMain selectWorkLoadMainById(Long id);

   List selectWorkLoadMainList(WorkLoadMain workLoadMain);

   int insertWorkLoadMain(WorkLoadMain workLoadMain);

   int updateWorkLoadMain(WorkLoadMain workLoadMain);

   int deleteWorkLoadMainByIds(Long[] ids);

   int deleteWorkLoadMainById(Long id);

   WorkLoadMain queryMain(String dateTime) throws Exception;

   Integer selectCountByDeptCode(String deptCode, String status) throws Exception;

   WorkLoadMain newBuilt(WorkLoadNewBuiltReq req) throws Exception;

   Integer selectLastNowNum(String dateTime) throws Exception;

   void save(WorkLoadSaveReq req) throws Exception;

   WorkLoadMain selectMainByUpdate(Long mainId) throws Exception;

   List workLoadReport(WorkLoadReportReq req) throws Exception;

   Integer selectCountBySubminDate(Date sumDate, String deptCode);

   void deleteMain(Long id) throws Exception;

   void updateStatusByTask();

   Integer selectCancelCountBySubminDate(Date sumDate, String deptCode);

   WorkLoadMain cancel(WorkLoadMain main) throws Exception;
}
