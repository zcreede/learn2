package com.emr.project.emr.service;

import com.emr.project.emr.domain.vo.IndexMzVo;
import com.emr.project.emr.domain.vo.IndexVo;
import java.util.List;

public interface IEmrIndexMMenuService {
   List selectPatEmrPrintList(IndexVo indexVo) throws Exception;

   IndexMzVo selectIndexMzVoList(String patientId);
}
