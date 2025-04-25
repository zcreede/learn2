package com.emr.project.docOrder.service;

import com.emr.project.docOrder.domain.vo.InpatientOrderCheckVo;
import com.emr.project.docOrder.domain.vo.OrderDoHandleDrugDoseVo;
import com.emr.project.pat.domain.vo.PatFeeVo;
import com.emr.project.system.domain.SysDept;
import com.emr.project.system.domain.SysUser;
import com.emr.project.tmpa.domain.OrderFreq;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IInpatientOrderOperationService {
   List inpatientOrderHandleSearch(InpatientOrderCheckVo inpatientOrderCheckVo) throws Exception;

   List inpatientOrderHandlePageData(InpatientOrderCheckVo inpatientOrderCheckVo, List listAll, Integer pageSize, Integer pageNum) throws Exception;

   boolean isHandleDay(String orderNo, String orderSortNumber, OrderFreq orderFreq, Date handleTime) throws Exception;

   OrderDoHandleDrugDoseVo inpatientOrderDoHandle(List inpatientOrderDTOList, PatFeeVo patFeeVo, BigDecimal patFeeAll, List drugAndClinList, List handleUplist, List printList, Map pharmacyNoApplyNoMap, String ip) throws Exception;

   OrderDoHandleDrugDoseVo inpatientOrderDoHandleMore(List inpatientOrderDTOList, SysUser sysUser, SysDept dept, PatFeeVo patFeeVo, BigDecimal patFeeAll, List drugAndClinList, List handleUplist, List printList, Map pharmacyNoApplyNoMap) throws Exception;
}
