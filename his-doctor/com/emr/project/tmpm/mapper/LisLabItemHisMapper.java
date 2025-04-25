package com.emr.project.tmpm.mapper;

import com.emr.project.tmpm.domain.LisLabItemHis;
import java.util.List;

public interface LisLabItemHisMapper {
   LisLabItemHis selectLisLabItemHisById(Long id);

   List selectLisLabItemHisList(LisLabItemHis lisLabItemHis);

   int insertLisLabItemHis(LisLabItemHis lisLabItemHis);

   int updateLisLabItemHis(LisLabItemHis lisLabItemHis);

   int deleteLisLabItemHisById(Long id);

   int deleteLisLabItemHisByIds(Long[] ids);
}
