package com.emr.project.tmpm.service;

import com.emr.project.tmpm.domain.LisLabResultHis;
import java.util.List;

public interface ILisLabResultHisService {
   LisLabResultHis selectLisLabResultHisById(Long id);

   List selectLisLabResultHisList(LisLabResultHis lisLabResultHis);

   int insertLisLabResultHis(LisLabResultHis lisLabResultHis);

   int updateLisLabResultHis(LisLabResultHis lisLabResultHis);

   int deleteLisLabResultHisByIds(Long[] ids);

   int deleteLisLabResultHisById(Long id);
}
