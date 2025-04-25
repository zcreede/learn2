package com.emr.project.operation.mapper;

import com.emr.project.operation.domain.TmPmHsxm;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;

public interface TmPmHsxmMapper {
   List selectByConn(TmPmHsxm record);

   List getThreeLevelCodeList();

   List selectAll();

   String getByIcd(@PathVariable("icd") String icd);
}
