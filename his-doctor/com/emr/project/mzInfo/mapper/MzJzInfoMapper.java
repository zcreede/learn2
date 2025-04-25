package com.emr.project.mzInfo.mapper;

import com.emr.project.mzInfo.domain.MzOrderDetail;
import java.util.List;

public interface MzJzInfoMapper {
   List selectMzOrderDetail(MzOrderDetail param);
}
