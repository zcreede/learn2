package com.emr.project.tmpm.mapper;

import com.emr.project.tmpm.domain.PrintTmplInfo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PrintTmplInfoMapper {
   PrintTmplInfo selectTmPmPrintTmplInfoByCode(@Param("typeCode") String typeCode);

   List selectTmPmPrintTmplInfoByCodes(List typeCodeList);

   List selectListByParentTypeCode(@Param("parentTypeCode") String parentTypeCode);
}
