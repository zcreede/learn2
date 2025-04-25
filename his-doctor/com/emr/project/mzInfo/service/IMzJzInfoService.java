package com.emr.project.mzInfo.service;

import java.util.List;

public interface IMzJzInfoService {
   List selectMzOrderDetail(String visitNo, String mzh, String orderClassCode) throws Exception;
}
