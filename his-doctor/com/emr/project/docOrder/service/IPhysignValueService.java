package com.emr.project.docOrder.service;

import com.emr.project.docOrder.domain.vo.PhysignDayVo;
import com.emr.project.docOrder.domain.vo.PhysignValueVo;
import java.util.List;

public interface IPhysignValueService {
   List getValueList(PhysignValueVo physignValueVo) throws Exception;

   List getDayList(PhysignDayVo physignDayVo) throws Exception;

   List getValueRangeList(PhysignValueVo physignValueVo) throws Exception;
}
