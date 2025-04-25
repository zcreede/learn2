package com.emr.project.pat.service.impl;

import com.emr.common.utils.StringUtils;
import com.emr.framework.aspectj.lang.enums.DataSourceType;
import com.emr.framework.datasource.DruidUtil;
import com.emr.framework.web.service.ISyncService;
import com.emr.project.pat.domain.Transinfo;
import com.emr.project.pat.domain.vo.DeptBEDateVo;
import com.emr.project.pat.domain.vo.TransinfoVo;
import com.emr.project.pat.domain.vo.VisitinfoVo;
import com.emr.project.pat.mapper.TransinfoMapper;
import com.emr.project.pat.service.ITransinfoService;
import com.emr.project.pat.service.IVisitinfoService;
import com.emr.project.system.domain.SyncDatasource;
import com.emr.project.system.domain.SysDept;
import com.emr.project.system.domain.vo.SqlVo;
import com.emr.project.system.service.ISyncDatasourceService;
import com.google.common.base.Joiner;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransinfoServiceImpl implements ITransinfoService, ISyncService {
   private final Logger log = LoggerFactory.getLogger(TransinfoServiceImpl.class);
   @Autowired
   private TransinfoMapper transinfoMapper;
   @Autowired
   private IVisitinfoService visitinfoService;
   @Autowired
   private ISyncDatasourceService syncDatasourceService;

   public Transinfo selectTransinfoById(Long trId) {
      return this.transinfoMapper.selectTransinfoById(trId);
   }

   public List selectTransinfoList(Transinfo transinfo) {
      return this.transinfoMapper.selectTransinfoList(transinfo);
   }

   public int insertTransinfo(Transinfo transinfo) {
      return this.transinfoMapper.insertTransinfo(transinfo);
   }

   public int updateTransinfo(Transinfo transinfo) {
      return this.transinfoMapper.updateTransinfo(transinfo);
   }

   public int deleteTransinfoByIds(Long[] trIds) {
      return this.transinfoMapper.deleteTransinfoByIds(trIds);
   }

   public int deleteTransinfoById(Long trId) {
      return this.transinfoMapper.deleteTransinfoById(trId);
   }

   public List selectTransinfoByPatientId(String patientId) throws Exception {
      return this.transinfoMapper.selectTransinfoByPatientId(patientId);
   }

   public SysDept selectDeptByDate(TransinfoVo transinfoVo) throws Exception {
      SysDept sysDept = new SysDept();
      VisitinfoVo visitinfo = this.visitinfoService.selectVisitinfoByPatientId(transinfoVo.getPatientId());
      if (visitinfo != null) {
         sysDept.setDeptCode(visitinfo.getDeptCd());
         sysDept.setDeptName(visitinfo.getDeptName());
         List<Transinfo> transinfoList = this.transinfoMapper.selectTransinfosByPatientId(transinfoVo.getPatientId());
         if (transinfoList != null && transinfoList.size() > 0) {
            List<Transinfo> beforeList = (List)transinfoList.stream().filter((o) -> o.getToTime().before(transinfoVo.getDate())).collect(Collectors.toList());
            beforeList.stream().sorted(Comparator.comparing(Transinfo::getToTime).reversed());
            List<Transinfo> afterList = (List)transinfoList.stream().filter((o) -> o.getToTime().after(transinfoVo.getDate())).collect(Collectors.toList());
            afterList.stream().sorted(Comparator.comparing(Transinfo::getToTime));
            if (beforeList != null && beforeList.size() > 0) {
               sysDept.setDeptCode(((Transinfo)beforeList.get(0)).getTiDeptCd());
               sysDept.setDeptName(((Transinfo)beforeList.get(0)).getTiDeptName());
            } else if (afterList != null && afterList.size() > 0) {
               sysDept.setDeptCode(((Transinfo)afterList.get(0)).getToDeptCd());
               sysDept.setDeptName(((Transinfo)afterList.get(0)).getToDeptName());
            }
         }
      }

      return sysDept;
   }

   public List selectDetpDate(String patientId) throws Exception {
      List<DeptBEDateVo> list = new ArrayList(1);
      VisitinfoVo visitinfoVo = this.visitinfoService.selectVisitinfoById(patientId);
      List<Transinfo> transinfoList = this.transinfoMapper.selectTransinfosByPatientId(patientId);
      transinfoList.stream().sorted(Comparator.comparing(Transinfo::getToTime));
      Date preDate = visitinfoVo.getInhosTime();
      if (visitinfoVo.getOutTime() == null) {
         new Date();
      } else {
         visitinfoVo.getOutTime();
      }

      if (transinfoList != null && !transinfoList.isEmpty()) {
         for(int i = 0; i < transinfoList.size(); ++i) {
            Transinfo transinfo = (Transinfo)transinfoList.get(i);
            Date endDateTemp = transinfo.getTiTime() == null ? transinfo.getToTime() : transinfo.getTiTime();
            DeptBEDateVo deptBEDateVo = new DeptBEDateVo(transinfo.getTrId(), transinfo.getToDeptCd(), transinfo.getToDeptName(), transinfo.getTiBedNo(), preDate, endDateTemp);
            if (transinfo.getServiceTypeCode().equals("01") && transinfo.getTranInState().equals("2")) {
               String deptCode = StringUtils.isNotBlank(deptBEDateVo.getDeptCode()) ? deptBEDateVo.getDeptCode() : transinfo.getTiDeptCd();
               String deptName = StringUtils.isNotBlank(deptBEDateVo.getDeptName()) ? deptBEDateVo.getDeptName() : transinfo.getTiDeptName();
               String bedNo = StringUtils.isNotBlank(deptBEDateVo.getBedNo()) ? deptBEDateVo.getBedNo() : transinfo.getToBedNo();
               deptBEDateVo.setDeptCode(deptCode);
               deptBEDateVo.setDeptName(deptName);
               deptBEDateVo.setBedNo(bedNo);
            } else if (transinfo.getServiceTypeCode().equals("01") && transinfo.getTranInState().equals("1")) {
               deptBEDateVo.setDeptCode(transinfo.getTiDeptCd());
               deptBEDateVo.setDeptName(transinfo.getTiDeptName());
            }

            preDate = transinfo.getToTime();
            list.add(deptBEDateVo);
         }
      }

      return list;
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void syncData(List hisList) throws Exception {
      this.transinfoMapper.deleteTransinfo();
      this.insertTranInfo(hisList);
   }

   public void insertTranInfo(List list) throws Exception {
      int i = 0;

      for(Map temp : list) {
         this.log.info("i-> {}", i++);

         try {
            this.transinfoMapper.insertMap(temp);
         } catch (Exception e) {
            this.log.info("对象数值=====  {}", temp.toString());
            this.log.error("同步转科信息出现异常", e.getMessage());
            throw new Exception(e.getMessage());
         }
      }

   }

   public void syncAddData(List list, SqlVo sqlVo) throws Exception {
      this.insertTranInfo(list);
   }

   public void syncDataMap(List mapList, String tableName) throws Exception {
   }

   public List syncHisTransInfoLisT(List inpNoList) throws Exception {
      List<Map<String, Object>> list = null;

      try {
         SqlVo sqlVo = new SqlVo();
         String inpNo = Joiner.on(",").join(inpNoList);
         sqlVo.setInpNo(inpNo);
         SyncDatasource syncDatasource = this.syncDatasourceService.selectSyncDatasourceByCode(DataSourceType.hisTransinfoAdd.toString());
         sqlVo.setSqlStr(syncDatasource.getQuerySql());
         DruidUtil.switchDB(syncDatasource);
         list = this.transinfoMapper.selectHisTransInfo(sqlVo);
      } catch (Exception e) {
         this.log.error("查询PBHIS患者转科数据出现异常：", e);
         throw new Exception(e);
      } finally {
         DruidUtil.clearDataSource();
      }

      return list;
   }

   public void deleteTransinfoByInpNoList(List inpNoList) throws Exception {
      this.transinfoMapper.deleteTransinfoByInpNoList(inpNoList);
   }
}
