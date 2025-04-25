package com.emr.project.emr.service;

import com.alibaba.fastjson.JSONObject;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.project.dr.domain.vo.TdCaConsApplyVo;
import com.emr.project.emr.domain.EmrTaskInfo;
import com.emr.project.emr.domain.Index;
import com.emr.project.emr.domain.SubfileIndex;
import com.emr.project.emr.domain.SysEmrTypeLevel;
import com.emr.project.emr.domain.vo.AnalysisXmlDataReq;
import com.emr.project.emr.domain.vo.EmrBinaryVo;
import com.emr.project.emr.domain.vo.IndexHFInfoVo;
import com.emr.project.emr.domain.vo.IndexNoSignListVo;
import com.emr.project.emr.domain.vo.IndexSaveReturnVo;
import com.emr.project.emr.domain.vo.IndexSaveVo;
import com.emr.project.emr.domain.vo.IndexSecLevelVo;
import com.emr.project.emr.domain.vo.IndexSignVo;
import com.emr.project.emr.domain.vo.IndexVo;
import com.emr.project.emr.domain.vo.XmlElementParseConfigVo;
import com.emr.project.pat.domain.vo.VisitinfoVo;
import com.emr.project.qc.domain.vo.PatEventVo;
import com.emr.project.system.domain.SysUser;
import com.emr.project.tmpl.domain.TmplIndex;
import com.emr.project.tmpl.domain.vo.TempIndexSaveElemVo;
import com.emr.project.webEditor.zb.vo.IndexFileVo;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ibatis.annotations.Param;

public interface IIndexService {
   Index selectIndexById(Long id);

   IndexVo selectIndexVoById(Long id);

   Index selectIndexInfoById(Long id);

   List selectDelIndexPageList(IndexVo indexVo) throws Exception;

   List selectIndexList(Index index) throws Exception;

   int updateIndex(Index index) throws Exception;

   int updateIndexByIds(Long[] ids);

   int updateIndexById(Long id) throws Exception;

   void updateIndexSecLevel(IndexVo indexVo) throws Exception;

   boolean isDel(Index index, SubfileIndex subfileIndex) throws Exception;

   List editDelFlagById(Index index, SubfileIndex subfileIndex, String base64Str, String xmlStr, HttpServletRequest httpServletRequest) throws Exception;

   List editDelFlagOnly(Index index, SubfileIndex subfileIndex, HttpServletRequest httpServletRequest) throws Exception;

   void delIndexById(Long indexId) throws Exception;

   void changeDelFlag(Index index, SubfileIndex subfileIndex, HttpServletRequest httpServletRequest) throws Exception;

   List selectSealupIndexList(IndexVo indexVo) throws Exception;

   List selectTodoEmrList(IndexVo indexVo) throws Exception;

   List selectYetEmrList(IndexVo indexVo) throws Exception;

   List selectTodoListFromTask(IndexVo indexVo) throws Exception;

   List selectTodoSubList(IndexVo indexVo) throws Exception;

   Map selectSysUserDept(Long userId);

   void settingSecLevel(IndexSecLevelVo indexSecLevelVo) throws Exception;

   List selectOtherIndexTreeList(Index index) throws Exception;

   List selectSetPropElemList(String patientId, List elemGenderList) throws Exception;

   Map selectSetStructsTextIdList(TmplIndex tempIndex, String patientId, String testExamResultId, String babyId) throws Exception;

   Map elemDateKeyAndValue(List elemDateList) throws Exception;

   Map elemMacroKeyAndValue(String patientId, String testExamResultId, String babyId, List elemMacroList) throws Exception;

   Map quoteElemKeyAndValue(Long tempId, String patientId, List quoteList) throws Exception;

   List selectHisIndexTreeList(String patientId) throws Exception;

   IndexVo selectEmrInfoAndFileList(Long id, String secrecyBz, HttpServletRequest httpServletRequest) throws Exception;

   AjaxResult selectIsAuthUpdate(Index index, SubfileIndex subfileIndex, String newSubFileFlag, String mainFileCancelSignFlag, HttpServletRequest request, IndexVo indexVo) throws Exception;

   Boolean getIndexSaveAuth(Index index, SubfileIndex subfileIndex, HttpServletRequest request) throws Exception;

   Map selectEmrFirstOpenList(TmplIndex tmplIndex, IndexVo indexVo, Date nowDate, String indexName) throws Exception;

   Map emrElemXmlManage(TmplIndex tmplIndex, String patientId, String xmlStr, Date subRecoDate, IndexVo indexVo) throws Exception;

   IndexSaveReturnVo saveIndex(IndexSaveVo indexSaveVo, Index index, SubfileIndex subfileIndex, List elemAttriVoList, HttpServletRequest request) throws Exception;

   void saveIndexAsync(IndexSaveVo indexSaveVo, Index index, SubfileIndex subfileIndex, Date updateTime, SysUser updateUser) throws Exception;

   void saveIndexElemAsync(Index index, SubfileIndex subfileIndex, List elemAttriVoList, SysUser updateUser, Map elemBase64Map) throws Exception;

   void saveIndexElemSharingAsync(Index index, SubfileIndex subfileIndex, List elemAttriVoList, SysUser updateUser) throws Exception;

   void updateIndexMrState(Long id, String mrState, Date updateTime) throws Exception;

   void insertIndex(IndexSaveVo indexSaveVo, Index index, SubfileIndex subfileIndex) throws Exception;

   List selectElemMacroListByType(List indexTempIdList, Long mainId) throws Exception;

   List selectElemQuoteByType(TmplIndex tmplIndex) throws Exception;

   EmrBinaryVo selectSubfileIndexList(String patientId) throws Exception;

   List selectEmrSignList(Index index, SubfileIndex subfileIndex, String xmlStr) throws Exception;

   List selectEmrNoSignList(Index index, SubfileIndex subfileIndex, String xmlStr) throws Exception;

   IndexSaveReturnVo signIndex(IndexSignVo indexSignVo, Index index, SubfileIndex subfileIndex, SysEmrTypeLevel saveTypeLevel) throws Exception;

   IndexSaveReturnVo signfileForFreeMove(IndexSignVo indexSignVo, Index index, SubfileIndex subfileIndex, SysEmrTypeLevel saveTypeLevel) throws Exception;

   Index selectIndexByPatientId(String patientId);

   boolean isEmrTypeMainFile(TmplIndex tmplIndex);

   Map selectElemAnewList(IndexVo indexVo, Boolean isMainFile) throws Exception;

   Map selectElemSmartQuote(IndexVo indexVo) throws Exception;

   IndexFileVo insertFile(IndexVo indexVo) throws Exception;

   Boolean insertFtpFile(IndexFileVo indexFileVo, IndexVo indexVo) throws Exception;

   IndexNoSignListVo getIndexSignInfo(Index index, SubfileIndex subfileIndex, String xmlStr, IndexVo indexVo, EmrTaskInfo targetTask) throws Exception;

   List getIndexHFInfo(IndexHFInfoVo indexHFInfoVo, VisitinfoVo visitinfoVo) throws Exception;

   List getIndexHFInfoResultVo(String xmlStr, String patientId) throws Exception;

   void emrCancelEdit(IndexVo indexVo) throws Exception;

   List getIndexSecLevelByEmplNumber(String emplNumber, String patientId) throws Exception;

   void updateIndexLockState(Index index) throws Exception;

   List getElemMacroListFromXml(String xmlStr) throws Exception;

   List getQcErrorList(IndexSaveVo indexSaveVo) throws Exception;

   List emrQualityCheck(IndexSaveVo indexSaveVo, List elemAttriList, List qcExcepationList) throws Exception;

   void saveEmrCheckFlaw(IndexSaveVo indexSaveVo) throws Exception;

   List selectEmrUnFinishList(String patientId) throws Exception;

   List selectEmrUnUpdateList(String patientId) throws Exception;

   List selectUnSignEmrList(String patientId) throws Exception;

   List selectPatIndexList(PatEventVo patEventVo) throws Exception;

   void updatePrintInfo(@Param("id") Long id) throws Exception;

   List selectOvertimeWriteList(String patientId) throws Exception;

   void updateLastFinishTimeList(List list) throws Exception;

   Boolean betweenInhosTimeAndNow(String patientId, Date recoDate) throws Exception;

   JSONObject setUserInfo() throws Exception;

   IndexSaveReturnVo saveConsIndex(TdCaConsApplyVo tdCaConsApplyVo, HttpServletRequest request) throws Exception;

   void saveConsIndexAsync(TdCaConsApplyVo tdCaConsApplyVo, Index index, Date updateTime) throws Exception;

   void saveConsIndexElemAsync(TdCaConsApplyVo tdCaConsApplyVo, Index index) throws Exception;

   Map selectPatCondSescString(String patientId, TmplIndex tmplIndex) throws Exception;

   List selectOpeIndexByEmrType(String patientId, List emrTypeCodeList) throws Exception;

   List selectOpeIndexByOrderNo(String applyFormNo) throws Exception;

   void updateEmrOrderNo(String orderNo) throws Exception;

   void updateOrderNoEmptyByIdList(List mrFileIdList) throws Exception;

   void updateEmrOrderNoByIdList(String orderNo, List mrFileIdList) throws Exception;

   void updateOrderNoByOrderNo(String oldOrderNo, String newOrderNo) throws Exception;

   String getMrCon(String base64Str, String xmlStr, String textStr, String subFileTextStr) throws Exception;

   List selectPatEmrPrintList(IndexVo indexVo) throws Exception;

   AjaxResult isCancelSign(Index index, SubfileIndex subfileIndex, String xmlStr) throws Exception;

   void saveBaseStr(IndexVo indexVo) throws Exception;

   void filterEmrPdf(String ips, String fileNames) throws Exception;

   void saveEmrPdf(IndexSaveVo indexSaveVo) throws Exception;

   List selectUnSavePdfEmr() throws Exception;

   String selectEmrBase64(Long id) throws Exception;

   List selectEmrListByPat(IndexVo indexVo) throws Exception;

   List selectSubEmrListByPat(String patientId) throws Exception;

   List selectAllIndexList(IndexVo indexVo) throws Exception;

   XmlElementParseConfigVo getXmlElementParseConfigs() throws Exception;

   List selectIndexListByTmplList(List elemList, String patientId) throws Exception;

   Index selectIndexByTmplId(String patientId, Long tempId) throws Exception;

   Map selectChangeAlertElems(String patientId, TempIndexSaveElemVo tempIndexSaveElemVo, List elemAttriList, String babyId) throws Exception;

   Map selectAlertElems(String patientId, String babyId, Long tempId, TempIndexSaveElemVo tempIndexSaveElemVo, List elemAttriList) throws Exception;

   List selectElemListByMrFileId(AnalysisXmlDataReq req) throws Exception;

   List selectOrderNoByIdList(List idList);

   String queryEmrBase64FromRedis(Long id) throws Exception;

   SysUser currUserByBJCAs(String signFlag, String signCertSn) throws Exception;

   SysUser getUserBySignCertSn(String signCertSn) throws Exception;

   int deleteByIds(List idList) throws Exception;
}
