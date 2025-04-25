package com.emr.project.docOrder.service;

import com.emr.project.docOrder.domain.Doctorder;
import com.emr.project.docOrder.domain.vo.DoctorderVo;
import com.emr.project.docOrder.domain.vo.OrderSearchVo;
import java.util.List;

public interface IDoctorderService {
   Doctorder selectDoctorderById(String id);

   List selectDoctorderList(Doctorder doctorder);

   List selectHisDocorderList(DoctorderVo doctorderVo, Integer pageNum, Integer pageSize) throws Exception;

   Integer selectHisDocorderListCount(DoctorderVo doctorderVo) throws Exception;

   List selectHisDocorderChildList(List parentList, DoctorderVo doctorderVo) throws Exception;

   int insertDoctorder(Doctorder doctorder);

   int updateDoctorder(Doctorder doctorder);

   int deleteDoctorderByIds(String[] ids);

   int deleteDoctorderById(String id);

   List selectBabyInfoByPatientId(String patientId);

   List selectHisDocorderVoList(OrderSearchVo queryParam) throws Exception;

   List selectHisSubDocorderVoList(OrderSearchVo queryParam, List mainList) throws Exception;
}
