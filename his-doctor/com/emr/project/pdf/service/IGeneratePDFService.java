package com.emr.project.pdf.service;

import com.emr.project.docOrder.domain.vo.OrderListPrintVo;
import com.emr.project.report.domain.vo.ReportGrfReqParamVo;
import java.util.Date;

public interface IGeneratePDFService {
   void generateOrderPDF(OrderListPrintVo orderListPrintVo, Date lastLeaveHosDate) throws Exception;

   void generateFeePDF(ReportGrfReqParamVo paramVo, Date lastLeaveHosDate) throws Exception;

   Date lastLeaveHosDate(String genType) throws Exception;
}
