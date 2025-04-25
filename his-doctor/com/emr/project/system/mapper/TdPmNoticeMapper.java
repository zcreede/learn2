package com.emr.project.system.mapper;

import com.emr.project.system.domain.TdPmNotice;
import java.util.List;

public interface TdPmNoticeMapper {
   TdPmNotice selectTdPmNoticeById(Long id);

   List selectTdPmNoticeList(TdPmNotice tdPmNotice);

   int insertTdPmNotice(TdPmNotice tdPmNotice);

   int updateTdPmNotice(TdPmNotice tdPmNotice);

   int deleteTdPmNoticeById(Long id);

   int deleteTdPmNoticeByIds(Long[] ids);

   List selectTopFiveList(String docCd, String deptCode);
}
