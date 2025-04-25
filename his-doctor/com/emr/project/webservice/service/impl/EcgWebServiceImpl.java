package com.emr.project.webservice.service.impl;

import com.emr.common.utils.DateUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.common.utils.StringUtils;
import com.emr.framework.aspectj.lang.annotation.DataSource;
import com.emr.framework.aspectj.lang.enums.DataSourceType;
import com.emr.framework.datasource.DruidUtil;
import com.emr.project.docOrder.domain.vo.TdPaApplyFormVo;
import com.emr.project.docOrder.service.ITdPaApplyFormService;
import com.emr.project.mzpb.domain.ExamReportRecord;
import com.emr.project.mzpb.mapper.ExamReportRecordMapper;
import com.emr.project.operation.domain.Medicalinformation;
import com.emr.project.operation.service.IMedicalinfoService;
import com.emr.project.pat.domain.ExamItem;
import com.emr.project.pat.service.IExamItemService;
import com.emr.project.webservice.domain.req.EcgReq;
import com.emr.project.webservice.domain.resp.EcgRes;
import com.emr.project.webservice.service.EcgWebService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@WebService(
   serviceName = "EcgWebService",
   targetNamespace = "http://webservice.project.emr.com",
   name = "EcgWebService",
   endpointInterface = "com.emr.project.webservice.service.EcgWebService"
)
@SOAPBinding(
   style = Style.RPC
)
public class EcgWebServiceImpl implements EcgWebService {
   protected final Logger log = LoggerFactory.getLogger(EcgWebServiceImpl.class);
   private static IMedicalinfoService medicalinfoService;
   private static IExamItemService examItemService;
   private static ITdPaApplyFormService tdPaApplyFormService;
   private static ExamReportRecordMapper examReportRecordMapper;

   @Autowired
   public void setRepository(IMedicalinfoService medicalinfoService) {
      EcgWebServiceImpl.medicalinfoService = medicalinfoService;
   }

   @Autowired
   public void setRepository(IExamItemService examItemService) {
      EcgWebServiceImpl.examItemService = examItemService;
   }

   @Autowired
   public void setRepository(ITdPaApplyFormService tdPaApplyFormService) {
      EcgWebServiceImpl.tdPaApplyFormService = tdPaApplyFormService;
   }

   @Autowired
   public void setRepository(ExamReportRecordMapper examReportRecordMapper) {
      EcgWebServiceImpl.examReportRecordMapper = examReportRecordMapper;
   }

   public EcgRes receiveEcg(EcgReq req) throws Exception {
      EcgRes res = new EcgRes();
      res.setIsSuccess(1);
      Boolean flag = true;
      if (flag && StringUtils.isBlank(req.getApplyNo())) {
         flag = false;
         res.setIsSuccess(0);
         res.setMessage("申请单号不能为空！");
      }

      if (flag && StringUtils.isBlank(req.getPatientName())) {
         flag = false;
         res.setIsSuccess(0);
         res.setMessage("患者姓名不能为空！");
      }

      if (flag && StringUtils.isBlank(req.getInpatientNo())) {
         flag = false;
         res.setIsSuccess(0);
         res.setMessage("住院号不能为空！");
      }

      if (flag && StringUtils.isBlank(req.getOutpatientNo())) {
         flag = false;
         res.setIsSuccess(0);
         res.setMessage("门诊号不能为空！");
      }

      if (flag && StringUtils.isBlank(req.getCriticalId())) {
         flag = false;
         res.setIsSuccess(0);
         res.setMessage("消息id不能为空！");
      }

      if (flag && StringUtils.isBlank(req.getCriticalContent())) {
         flag = false;
         res.setIsSuccess(0);
         res.setMessage("内容不能为空！");
      }

      if (flag && StringUtils.isBlank(req.getSendDoctor())) {
         flag = false;
         res.setIsSuccess(0);
         res.setMessage("上报医生姓名不能为空！");
      }

      if (flag && StringUtils.isBlank(req.getSendDoctorId())) {
         flag = false;
         res.setIsSuccess(0);
         res.setMessage("医生his工号不能为空！");
      }

      if (flag && StringUtils.isBlank(req.getSendDate())) {
         flag = false;
         res.setIsSuccess(0);
         res.setMessage("上报时间不能为空！");
      }

      Medicalinformation medicalinformation = flag ? medicalinfoService.selectMedicalinfoByPatientId(req.getInpatientNo()) : null;
      if (flag && medicalinformation == null) {
         flag = false;
         res.setIsSuccess(0);
         res.setMessage("患者信息不存在！");
      }

      if (flag) {
         req.setSendDate(req.getSendDate().replaceAll("\"", ""));
         List<ExamItem> examItemList = examItemService.selectByAppCd(req.getApplyNo());
         if (CollectionUtils.isNotEmpty(examItemList) && examItemList.size() > 0) {
            for(ExamItem examItem : examItemList) {
               examItem.setRepDocCd(req.getSendDoctorId());
               examItem.setRepDocName(req.getSendDoctor());
               SimpleDateFormat sdf = new SimpleDateFormat(DateUtils.YYYY_MM_DD_HH_MM);
               examItem.setExamRepDate(sdf.parse(req.getSendDate()));
               examItem.setAlterFlag("1");
               examItemService.updateExamItem(examItem);
            }
         } else {
            TdPaApplyFormVo tdPaApplyFormVo = tdPaApplyFormService.selectTdPaApplyFormById(req.getApplyNo());
            if (tdPaApplyFormVo != null) {
               ExamItem examItem = new ExamItem();
               examItem.setRecDocCd("");
               examItem.setRecDate(new Date());
               examItem.setId(SnowIdUtils.uniqueLongHex());
               examItem.setOrgCd(medicalinformation.getHospitalCode());
               examItem.setPatientId(medicalinformation.getPatientId());
               examItem.setExamRepNo(medicalinformation.getHospitalCode());
               examItem.setAppCd(tdPaApplyFormVo.getApplyFormNo());
               examItem.setExamAppOrg("");
               examItem.setAppTime(tdPaApplyFormVo.getApplyTime());
               examItem.setAppDeptCd(tdPaApplyFormVo.getPhysicianDptNo());
               examItem.setAppDeptName(tdPaApplyFormVo.getPhysicianDptName());
               examItem.setAppDocCd(tdPaApplyFormVo.getOrderDoctorCode());
               examItem.setAppDocName(tdPaApplyFormVo.getOrderDoctorName());
               examItem.setExamTime(tdPaApplyFormVo.getApplyTime());
               examItem.setExamPos(tdPaApplyFormVo.getExamPart());
               examItem.setExamItemCd(tdPaApplyFormVo.getPurposeExamination());
               examItem.setExamItemName(tdPaApplyFormVo.getPurposeExamination());
               examItem.setExamResObj("");
               examItem.setExamResSub("");
               examItem.setExamResQua(0L);
               examItem.setExamResUnit("");
               examItem.setExamResCd("");
               examItem.setExamRemark("");
               examItem.setImageWay("");
               examItem.setExamRepOrg(medicalinformation.getHospitalCode());
               examItem.setExamRepDeptCd(tdPaApplyFormVo.getPerformDepCode());
               examItem.setExamRepDeptName(tdPaApplyFormVo.getPerformDepName());
               examItem.setExamDocCd("");
               examItem.setExamDocName("");
               examItem.setExamTecCd("");
               examItem.setExamTecName("");
               examItem.setExamRepDate((Date)null);
               examItem.setRepDocCd(tdPaApplyFormVo.getReportDoctor());
               examItem.setRepDocName(tdPaApplyFormVo.getReportDoctor());
               examItem.setExamWayCd(tdPaApplyFormVo.getExamMethodCode());
               examItem.setExamWayName(tdPaApplyFormVo.getExamMethodName());
               examItem.setExamSpecNo(tdPaApplyFormVo.getPurposeExamination());
               examItem.setExamSpecName(tdPaApplyFormVo.getPurposeExamination());
               examItem.setExamType("");
               examItem.setConDocCd(tdPaApplyFormVo.getReportDoctor());
               examItem.setConDocName(tdPaApplyFormVo.getReportDoctor());
               examItem.setSource("院内");
               examItem.setIdentify("住院");
               examItem.setCreDate(new Date());
               examItem.setCrePerCode("PACS");
               examItem.setCrePerName("PACS");
               examItem.setAltDate(new Date());
               examItem.setAltPerCode("PACS");
               examItem.setAltPerName("PACS");
               examItem.setAlterFlag("1");
               examItem.setAccnum(req.getCriticalId());
               examItem.setDiagClicName("");
               examItemService.insertExamItem(examItem);
            } else {
               res.setIsSuccess(0);
               res.setMessage("住院申请单不存在！");
            }
         }

         this.updateExamReportRecordMzPb(req);
      }

      return res;
   }

   @DataSource(DataSourceType.mzPb)
   public void updateExamReportRecordMzPb(EcgReq req) throws Exception {
      try {
         List<ExamReportRecord> examReportRecordList = examReportRecordMapper.selectExamReportRecordInfo(req.getApplyNo());
         if (CollectionUtils.isNotEmpty(examReportRecordList) && examReportRecordList.size() > 0) {
            examReportRecordMapper.updateByDjh(req);
         } else {
            List<ExamReportRecord> examReportRecordList1 = examReportRecordMapper.selectSqdByDjh(req.getApplyNo());
            if (CollectionUtils.isNotEmpty(examReportRecordList1) && examReportRecordList1.size() > 0) {
               examReportRecordMapper.insert(req);
            }
         }
      } catch (Exception e) {
         this.log.error("查询PBHIS患者危急值数据出现异常：", e);
         throw new Exception(e);
      } finally {
         DruidUtil.clearDataSource();
      }

   }
}
