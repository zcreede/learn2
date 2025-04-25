package com.emr.project.tmpm.mapper;

import com.emr.project.tmpm.domain.LisLabResultHis;
import java.util.List;

public interface LisLabResultHisMapper {
   LisLabResultHis selectLisLabResultHisById(Long id);

   List selectLisLabResultHisList(LisLabResultHis lisLabResultHis);

   int insertLisLabResultHis(LisLabResultHis lisLabResultHis);

   int updateLisLabResultHis(LisLabResultHis lisLabResultHis);

   int deleteLisLabResultHisById(Long id);

   int deleteLisLabResultHisByIds(Long[] ids);
}
