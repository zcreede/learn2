package com.emr.project.tmpm.service;

import com.emr.project.tmpm.domain.PrintTmplInfo;
import java.util.List;

public interface IPrintTmplInfoService {
   PrintTmplInfo selectTmPmPrintTmplInfoByCode(String typeCode) throws Exception;

   List selectTmPmPrintTmplInfoByCodes(List typeCodeList) throws Exception;

   List selectListByParentTypeCode(String parentTypeCode) throws Exception;
}
