package com.emr.project.webservice.service;

import com.emr.project.pat.domain.Visitinfo;
import com.emr.project.webservice.domain.vo.DSRParamVo;
import java.util.List;

public interface IDSRService {
   DSRParamVo judgeWhetherOpen(List icds, Visitinfo visitinfo) throws Exception;
}
