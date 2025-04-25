package com.emr.project.dr.service;

import com.emr.framework.web.domain.AjaxResult;
import com.emr.project.dr.domain.TdCaConsApply;
import com.emr.project.dr.domain.vo.TdCaConsApplyVo;
import com.emr.project.emr.domain.Index;
import com.emr.project.emr.domain.vo.IndexSaveReturnVo;
import com.emr.project.emr.domain.vo.IndexSignVo;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public interface ITdCaConsApplyService {
   TdCaConsApply selectTdCaConsApplyById(Long id);

   TdCaConsApply selectTdCaConsApplyByMrFileId(Long id);

   List selectTdCaConsApplyList(TdCaConsApplyVo tdCaConsApplyVo);

   List selectTdCaConsApplyStatisList(TdCaConsApplyVo tdCaConsApplyVo);

   List selectUnFinishInviteList(TdCaConsApplyVo tdCaConsApply) throws Exception;

   List selectFinishInviteList(TdCaConsApplyVo tdCaConsApply) throws Exception;

   List selectUnFinishApplyList(TdCaConsApplyVo tdCaConsApply) throws Exception;

   List selectFinishApplyList(TdCaConsApplyVo tdCaConsApply) throws Exception;

   void insertTdCaConsApply(TdCaConsApplyVo tdCaConsApplyVo) throws Exception;

   void updateTdCaConsApply(TdCaConsApplyVo tdCaConsApplyVo) throws Exception;

   int deleteTdCaConsApplyByIds(Long[] ids);

   void deleteTdCaConsApplyById(Long id) throws Exception;

   void deleteTdCaConsApply(TdCaConsApplyVo tdCaConsApplyVo) throws Exception;

   IndexSaveReturnVo saveTdCaConsApply(TdCaConsApplyVo tdCaConsApplyVo, HttpServletRequest request) throws Exception;

   void signTdCaConsApply(TdCaConsApplyVo tdCaConsApplyVo, IndexSignVo indexSignVo, Date updateTime, HttpServletRequest request) throws Exception;

   IndexSaveReturnVo cancleSignTdCaConsApply(Index index, TdCaConsApplyVo tdCaConsApplyVo, HttpServletRequest request, AjaxResult ajaxResult) throws Exception;

   List selectUnFinishConsList(String patientId) throws Exception;

   String selectIsConsOrApply(Long id) throws Exception;

   void acceptApplyUpdate(TdCaConsApplyVo tdCaConsApplyVo) throws Exception;

   void returnApplyUpdate(TdCaConsApplyVo tdCaConsApplyVo) throws Exception;

   IndexSaveReturnVo updateCancelApply(TdCaConsApplyVo tdCaConsApplyVo, AjaxResult ajaxResult, HttpServletRequest request) throws Exception;

   Map getConsElemValue(TdCaConsApply tdCaConsApply) throws Exception;

   List selectConsFlowChart(Long id) throws Exception;

   List selectConsStatisList(TdCaConsApplyVo tdCaConsApplyVo) throws Exception;

   List selectListByIds(List idList) throws Exception;

   void updateStatusByIds(List idList, String state) throws Exception;

   void updateStateById(TdCaConsApply tdCaConsApply) throws Exception;
}
