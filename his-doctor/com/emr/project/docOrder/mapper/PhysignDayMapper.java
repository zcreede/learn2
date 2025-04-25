package com.emr.project.docOrder.mapper;

import com.emr.project.docOrder.domain.vo.PhysignDayVo;
import java.util.List;

public interface PhysignDayMapper {
   List selectDayList(PhysignDayVo physignDayVo);
}
