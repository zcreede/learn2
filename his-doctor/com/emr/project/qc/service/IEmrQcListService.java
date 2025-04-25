package com.emr.project.qc.service;

import com.emr.framework.web.domain.AjaxResult;
import com.emr.project.emr.domain.Index;
import com.emr.project.emr.domain.SubfileIndex;
import com.emr.project.mrhp.domain.MrHp;
import com.emr.project.qc.domain.EmrQcCommRecord;
import com.emr.project.qc.domain.EmrQcList;
import com.emr.project.qc.domain.EmrQcRecord;
import com.emr.project.qc.domain.vo.EmrQcFlowVo;
import com.emr.project.qc.domain.vo.EmrQcListVo;
import com.emr.project.system.domain.SysUser;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

public interface IEmrQcListService {
   EmrQcList selectEmrQcListById(Long id);

   List selectEmrQcListList(EmrQcListVo emrQcList) throws Exception;

   EmrQcListVo selectEmrQcListById(EmrQcListVo emrQcList) throws Exception;

   int insertEmrQcList(EmrQcListVo emrQcListVo) throws Exception;

   int insertEmrQcListForZk(EmrQcListVo emrQcListVo) throws Exception;

   int updateEmrQcListForZk(EmrQcListVo emrQcListVo) throws Exception;

   int deleteEmrQcListByIdForZk(EmrQcListVo emrQcListVo) throws Exception;

   int insertEmrQcLists(List list) throws Exception;

   void insertListsByOvertimeUnWrite(List overtimeUnWriteList) throws Exception;

   void updateMissingFileListByMrFileId(String mrFileId) throws Exception;

   int updateEmrQcListById(EmrQcList emrQcList) throws Exception;

   int updateEmrQcListByMrFileId(EmrQcList emrQcList) throws Exception;

   int addQcDoctEmrQcLists(List list, MrHp mrHp, Index index, SubfileIndex subfileIndex) throws Exception;

   void addQcDoctEmrQc(EmrQcListVo emrQcListVo, MrHp mrHp, Index index, SubfileIndex subfileIndex) throws Exception;

   void updateQcDoctEmrQc(EmrQcListVo emrQcListVo) throws Exception;

   void updateEmrQcList(EmrQcList emrQcList, Long mainId, SysUser user) throws Exception;

   int deleteEmrQcListByIds(Long[] ids);

   int deleteEmrQcListById(Long id);

   List selectQcDoctByPatientSection(EmrQcList emrQcList) throws Exception;

   List selectNoFinishListByPatients(String patientId, Long emrQcRecordId) throws Exception;

   AjaxResult getClickFlagVisibleFLag(EmrQcRecord emrQcRecord) throws Exception;

   void updateQconTimesById(Long id) throws Exception;

   void updateStateByComm(EmrQcCommRecord emrQcCommRecord, SysUser user) throws Exception;

   List selectByPatientsAndQcState(String patientId, List qcStateList, Long mainId) throws Exception;

   List selectByPatientsAndQcStateAndRecordBz(String patientId, List qcStateList, Long mainId, String recordBz) throws Exception;

   List selectEmrSubFileQcList(String patientId, List qcStateList, String qcSection) throws Exception;

   List selectUnUpdateEmrList(String patientId) throws Exception;

   void addQcOrderEmrQcLists(EmrQcListVo emrQcList) throws Exception;

   void deleteEmrQcList(EmrQcListVo emrQcListVo) throws Exception;

   List getEmrQcFlowStatistic(EmrQcFlowVo emrQcFlowVo);

   AjaxResult getEmrQcFlowStatisticExport(EmrQcFlowVo emrQcFlowVo, HttpServletResponse response) throws Exception;

   List selectEmrQcListByMrFileId(Long mrFileId, List qcSectionList);

   void addQcMrHpEmrQcLists(EmrQcListVo emrQcList) throws Exception;
}
