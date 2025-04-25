package com.emr.project.docOrder.mapper;

import com.emr.project.docOrder.domain.vo.InpatientOrderCheckVo;
import java.util.List;

public interface InpatientOrderCheckMapper {
   List getCheckInpatientOrderList2(InpatientOrderCheckVo inpatientOrderCheckVo);
}
