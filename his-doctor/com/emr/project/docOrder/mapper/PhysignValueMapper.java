package com.emr.project.docOrder.mapper;

import com.emr.project.docOrder.domain.vo.PhysignValueVo;
import java.util.List;

public interface PhysignValueMapper {
   List selectValueList(PhysignValueVo physignValueVo);

   List getValueRangeList(PhysignValueVo physignValueVo);
}
