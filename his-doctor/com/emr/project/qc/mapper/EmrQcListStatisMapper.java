package com.emr.project.qc.mapper;

import com.emr.project.qc.domain.EmrQcFlowStatis;
import java.util.List;

public interface EmrQcListStatisMapper {
   List selectQcListByMrType(EmrQcFlowStatis emrQcFlowStatis);
}
