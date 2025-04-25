package com.emr.project.system.service.impl;

import com.emr.common.utils.DateUtils;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.common.utils.StringUtils;
import com.emr.framework.datasource.DruidUtil;
import com.emr.project.system.domain.SyncDatasource;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.domain.WorkLoadItem;
import com.emr.project.system.domain.WorkLoadMain;
import com.emr.project.system.domain.WorkLoadOther;
import com.emr.project.system.domain.WorkLoadPatient;
import com.emr.project.system.domain.req.WorkLoadNewBuiltReq;
import com.emr.project.system.domain.req.WorkLoadReportReq;
import com.emr.project.system.domain.req.WorkLoadSaveReq;
import com.emr.project.system.domain.resp.WorkLoadReportResp;
import com.emr.project.system.domain.vo.WorkLoadPatientVo;
import com.emr.project.system.mapper.SyncDatasourceMapper;
import com.emr.project.system.mapper.WorkLoadItemMapper;
import com.emr.project.system.mapper.WorkLoadMainMapper;
import com.emr.project.system.mapper.WorkLoadOtherMapper;
import com.emr.project.system.mapper.WorkLoadPatientMapper;
import com.emr.project.system.service.IWorkLoadMainService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WorkLoadMainServiceImpl implements IWorkLoadMainService {
   @Autowired
   private WorkLoadMainMapper workLoadMainMapper;
   @Autowired
   private WorkLoadItemMapper workLoadItemMapper;
   @Autowired
   private WorkLoadPatientMapper workLoadPatientMapper;
   @Autowired
   private SyncDatasourceMapper syncDatasourceMapper;
   @Autowired
   private WorkLoadOtherMapper workLoadOtherMapper;

   public WorkLoadMain selectWorkLoadMainById(Long id) {
      return this.workLoadMainMapper.selectWorkLoadMainById(id);
   }

   public List selectWorkLoadMainList(WorkLoadMain workLoadMain) {
      return this.workLoadMainMapper.selectWorkLoadMainList(workLoadMain);
   }

   public int insertWorkLoadMain(WorkLoadMain workLoadMain) {
      return this.workLoadMainMapper.insertWorkLoadMain(workLoadMain);
   }

   public int updateWorkLoadMain(WorkLoadMain workLoadMain) {
      SysUser loginUser = SecurityUtils.getLoginUser().getUser();
      workLoadMain.setAltPerCode(loginUser.getUserName());
      workLoadMain.setAltPerName(loginUser.getNickName());
      return this.workLoadMainMapper.updateWorkLoadMain(workLoadMain);
   }

   public int deleteWorkLoadMainByIds(Long[] ids) {
      return this.workLoadMainMapper.deleteWorkLoadMainByIds(ids);
   }

   public int deleteWorkLoadMainById(Long id) {
      return this.workLoadMainMapper.deleteWorkLoadMainById(id);
   }

   public WorkLoadMain queryMain(String dateTime) throws Exception {
      SysUser loginUser = SecurityUtils.getLoginUser().getUser();
      String deptCode = loginUser.getDept().getDeptCode();
      return this.workLoadMainMapper.queryMain(deptCode, dateTime);
   }

   public Integer selectCountByDeptCode(String deptCode, String status) throws Exception {
      return this.workLoadMainMapper.selectCountByDeptCode(deptCode, status);
   }

   public WorkLoadMain newBuilt(WorkLoadNewBuiltReq req) throws Exception {
      String dateTime = req.getDateTime();
      Integer originalNum = req.getOriginalNum();
      Date date = DateUtils.parseDate(dateTime);
      SysUser loginUser = SecurityUtils.getLoginUser().getUser();
      String deptCode = loginUser.getDept().getDeptCode();
      WorkLoadMain main = new WorkLoadMain();
      main.setId(SnowIdUtils.uniqueLong());
      main.setOrgCd(loginUser.getHospital().getOrgCode());
      main.setStatus("0");
      main.setDeptCode(deptCode);
      main.setDeptName(loginUser.getDept().getDeptName());
      main.setSumDate(date);
      main.setCrePerCode(loginUser.getUserName());
      main.setCrePerName(loginUser.getNickName());
      main.setOriginalNum(originalNum);
      List<WorkLoadPatient> patientList = new ArrayList();
      List<WorkLoadOther> otherList = new ArrayList();
      WorkLoadItem item = new WorkLoadItem();
      item.setStatus("1");
      List<WorkLoadItem> loadItems = this.workLoadItemMapper.selectWorkLoadItemList(item);
      if (!loadItems.isEmpty()) {
         for(WorkLoadItem workLoadItem : loadItems) {
            String sqlScript = workLoadItem.getSqlScript();
            Long dataSourceId = workLoadItem.getDataSourceId();
            if (dataSourceId != null && StringUtils.isNotEmpty(sqlScript)) {
               if (workLoadItem.getSourceFromType().equals("1")) {
                  sqlScript = sqlScript.replaceAll("@dateTime", dateTime);
                  sqlScript = sqlScript.replaceAll("@deptCode", deptCode);
                  SyncDatasource syncDatasource = this.syncDatasourceMapper.selectSyncDatasourceById(dataSourceId);
                  if (syncDatasource != null) {
                     try {
                        DruidUtil.switchDB(syncDatasource);
                        List<WorkLoadPatient> loadPatientList = this.workLoadPatientMapper.selectWorkLoadBySql(sqlScript);
                        if (!loadPatientList.isEmpty()) {
                           for(WorkLoadPatient patient : loadPatientList) {
                              patient.setId(SnowIdUtils.uniqueLong());
                              patient.setMainId(main.getId());
                              patient.setOrgCd(main.getOrgCd());
                              patient.setItemCode(workLoadItem.getItemCode());
                              patient.setItemName(workLoadItem.getItemName());
                              patient.setItemTypeCode(workLoadItem.getItemTypeCode());
                              patient.setItemTypeName(workLoadItem.getItemTypeName());
                              patientList.add(patient);
                           }
                        }
                     } finally {
                        DruidUtil.clearDataSource();
                     }
                  }
               } else {
                  sqlScript = sqlScript.replaceAll("@dateTime", dateTime);
                  sqlScript = sqlScript.replaceAll("@deptCode", deptCode);
                  SyncDatasource syncDatasource = this.syncDatasourceMapper.selectSyncDatasourceById(dataSourceId);
                  if (syncDatasource != null) {
                     try {
                        DruidUtil.switchDB(syncDatasource);
                        Integer count = this.workLoadOtherMapper.selectOtherInit(sqlScript);
                        if (count != null) {
                           WorkLoadOther workLoadOther = new WorkLoadOther();
                           workLoadOther.setId(SnowIdUtils.uniqueLong());
                           workLoadOther.setOrgCd(loginUser.getHospital().getOrgCode());
                           workLoadOther.setMainId(main.getId());
                           workLoadOther.setItemCode(workLoadItem.getItemCode());
                           workLoadOther.setItemName(workLoadItem.getItemName());
                           workLoadOther.setItemNum(count);
                           otherList.add(workLoadOther);
                        }
                     } finally {
                        DruidUtil.clearDataSource();
                     }
                  }
               }
            }
         }
      }

      if (!patientList.isEmpty()) {
         List<WorkLoadPatient> inList = (List)patientList.stream().filter((t) -> t.getItemTypeCode().equals("1")).collect(Collectors.toList());
         List<WorkLoadPatient> outList = (List)patientList.stream().filter((t) -> t.getItemTypeCode().equals("2")).collect(Collectors.toList());
         int inCount = 0;
         if (!inList.isEmpty()) {
            inCount = inList.size();
         }

         int outCount = 0;
         if (!outList.isEmpty()) {
            outCount = outList.size();
         }

         main.setNowNum(req.getOriginalNum() + inCount - outCount);
         this.workLoadPatientMapper.insertAllList(patientList);
      } else {
         main.setNowNum(main.getOriginalNum());
      }

      if (!otherList.isEmpty()) {
         this.workLoadOtherMapper.insertWorkLoadOtherList(otherList);
      }

      this.workLoadMainMapper.insertWorkLoadMain(main);
      return main;
   }

   public Integer selectLastNowNum(String dateTime) throws Exception {
      SysUser loginUser = SecurityUtils.getLoginUser().getUser();
      String deptCode = loginUser.getDept().getDeptCode();
      return this.workLoadMainMapper.selectLastNowNum(deptCode, dateTime);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void save(WorkLoadSaveReq req) throws Exception {
      SysUser loginUser = SecurityUtils.getLoginUser().getUser();
      Long mainId = req.getMainId();
      WorkLoadMain main = this.workLoadMainMapper.selectWorkLoadMainById(mainId);
      Long id = main.getId();
      List<WorkLoadPatient> patientList = this.workLoadPatientMapper.selectWorkLoadByMainId(id);
      this.workLoadPatientMapper.deleteWorkByMainId(id);
      this.workLoadOtherMapper.deleteWorkByMainId(id);
      List<WorkLoadPatient> oldInList = (List)patientList.stream().filter((t) -> t.getItemTypeCode().equals("1")).collect(Collectors.toList());
      List<WorkLoadPatient> oldOutList = (List)patientList.stream().filter((t) -> t.getItemTypeCode().equals("2")).collect(Collectors.toList());
      int oldIn = oldInList.size();
      int oldOut = oldOutList.size();
      int oldCount = oldIn - oldOut;
      int newIn = 0;
      int newOut = 0;
      List<WorkLoadPatient> patients = req.getPatientList();
      if (!patients.isEmpty()) {
         for(WorkLoadPatient patient : patients) {
            if (patient.getId() == null) {
               patient.setId(SnowIdUtils.uniqueLong());
               patient.setOrgCd(loginUser.getHospital().getOrgCode());
               patient.setMainId(id);
            }
         }

         this.workLoadPatientMapper.insertAllList(patients);
         List<WorkLoadPatient> newInList = (List)patients.stream().filter((t) -> t.getItemTypeCode().equals("1")).collect(Collectors.toList());
         newIn = newInList.size();
         List<WorkLoadPatient> newOutList = (List)patients.stream().filter((t) -> t.getItemTypeCode().equals("2")).collect(Collectors.toList());
         newOut = newOutList.size();
      }

      int newCount = newIn - newOut;
      int i = newCount - oldCount;
      if (newCount != oldCount) {
         this.workLoadMainMapper.updateOtherMain(main.getSumDate(), i, main.getDeptCode());
      }

      List<WorkLoadOther> others = req.getOtherList();
      if (!others.isEmpty()) {
         for(WorkLoadOther other : others) {
            if (other.getId() == null) {
               other.setId(SnowIdUtils.uniqueLong());
               other.setMainId(id);
               other.setOrgCd(loginUser.getHospital().getOrgCode());
            }
         }

         this.workLoadOtherMapper.insertWorkLoadOtherList(others);
      }

   }

   public WorkLoadMain selectMainByUpdate(Long mainId) throws Exception {
      WorkLoadMain workLoadMain = this.workLoadMainMapper.selectWorkLoadMainById(mainId);
      WorkLoadPatient workLoadPatient = new WorkLoadPatient();
      workLoadPatient.setMainId(mainId);
      List<WorkLoadPatientVo> list = this.workLoadPatientMapper.selectWorkLoadPatientList(workLoadPatient);
      if (!list.isEmpty()) {
         List<WorkLoadPatient> inList = (List)list.stream().filter((t) -> t.getItemTypeCode().equals("1")).collect(Collectors.toList());
         List<WorkLoadPatient> outList = (List)list.stream().filter((t) -> t.getItemTypeCode().equals("2")).collect(Collectors.toList());
         int inCount = 0;
         if (!inList.isEmpty()) {
            inCount = inList.size();
         }

         int outCount = 0;
         if (!outList.isEmpty()) {
            outCount = outList.size();
         }

         workLoadMain.setNowNum(workLoadMain.getOriginalNum() + inCount - outCount);
      } else {
         workLoadMain.setNowNum(workLoadMain.getOriginalNum());
      }

      this.workLoadMainMapper.updateWorkLoadMain(workLoadMain);
      return workLoadMain;
   }

   public List workLoadReport(WorkLoadReportReq req) throws Exception {
      List<WorkLoadReportResp> list = new ArrayList();
      SysUser loginUser = SecurityUtils.getLoginUser().getUser();
      List<WorkLoadReportResp> patientList = this.workLoadPatientMapper.workLoadReport(req);
      if (!patientList.isEmpty()) {
         list.addAll(patientList);
      }

      List<WorkLoadReportResp> otherList = this.workLoadOtherMapper.workLoadReport(req);
      if (!otherList.isEmpty()) {
         list.addAll(otherList);
      }

      List<WorkLoadReportResp> originalNumberList = this.workLoadPatientMapper.selectOriginalNumber(req);
      if (!originalNumberList.isEmpty()) {
         list.addAll(originalNumberList);
      }

      List<WorkLoadReportResp> nowNumberList = this.workLoadPatientMapper.selectNumNumber(req);
      if (!nowNumberList.isEmpty()) {
         list.addAll(nowNumberList);
      }

      List<WorkLoadReportResp> bedList = this.workLoadPatientMapper.selectBedNumber(req);
      if (!bedList.isEmpty()) {
         list.addAll(bedList);
      }

      if (!list.isEmpty()) {
         List<String> deptCodeList = (List)list.stream().map(WorkLoadReportResp::getDeptCode).distinct().collect(Collectors.toList());
         List<WorkLoadReportResp> bedOpenList = this.workLoadPatientMapper.selectBedOpenList(deptCodeList);
         if (!bedOpenList.isEmpty()) {
            list.addAll(bedOpenList);
         }
      }

      if (!list.isEmpty()) {
         for(WorkLoadReportResp resp : list) {
            resp.setStartDate(req.getStartDate());
            resp.setEndDate(req.getEndDate());
            resp.setNickName(loginUser.getNickName());
            resp.setOrgName(loginUser.getHospital().getOrgName());
         }
      }

      return list;
   }

   public Integer selectCountBySubminDate(Date sumDate, String deptCode) {
      return this.workLoadMainMapper.selectCountBySubminDate(sumDate, deptCode);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void deleteMain(Long id) throws Exception {
      this.workLoadMainMapper.deleteWorkLoadMainById(id);
      this.workLoadPatientMapper.deleteWorkByMainId(id);
      this.workLoadOtherMapper.deleteWorkByMainId(id);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void updateStatusByTask() {
      this.workLoadMainMapper.updateStatusByTask();
   }

   public Integer selectCancelCountBySubminDate(Date sumDate, String deptCode) {
      return this.workLoadMainMapper.selectCancelCountBySubminDate(sumDate, deptCode);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public WorkLoadMain cancel(WorkLoadMain main) throws Exception {
      SysUser loginUser = SecurityUtils.getLoginUser().getUser();
      main.setStatus("-1");
      main.setAltPerCode(loginUser.getUserName());
      main.setAltPerName(loginUser.getNickName());
      this.workLoadMainMapper.updateWorkLoadMain(main);
      return main;
   }
}
