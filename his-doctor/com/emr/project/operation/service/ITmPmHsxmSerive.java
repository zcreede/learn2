package com.emr.project.operation.service;

import com.emr.project.operation.domain.TmPmHsxm;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public interface ITmPmHsxmSerive {
   List selectByConn(TmPmHsxm tmPmHsxm) throws Exception;

   List getAttrList(TmPmHsxm tmPmHsxm, Function mapper);

   List getThreeLevelCodeList();

   Map getZdmcMaps();
}
