package com.emr.project.tmpm.service;

import com.emr.project.tmpm.domain.LisLabItemHis;
import java.util.List;

public interface ILisLabItemHisService {
   LisLabItemHis selectLisLabItemHisById(Long id);

   List selectLisLabItemHisList(LisLabItemHis lisLabItemHis);

   int insertLisLabItemHis(LisLabItemHis lisLabItemHis);

   int updateLisLabItemHis(LisLabItemHis lisLabItemHis);

   int deleteLisLabItemHisByIds(Long[] ids);

   int deleteLisLabItemHisById(Long id);
}
