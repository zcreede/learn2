package com.emr.project.system.service;

import com.emr.project.system.domain.TdPmNotice;
import java.util.List;

public interface ITdPmNoticeService {
   TdPmNotice selectTdPmNoticeById(Long id);

   List selectTdPmNoticeList(TdPmNotice tdPmNotice);

   int insertTdPmNotice(TdPmNotice tdPmNotice);

   int updateTdPmNotice(TdPmNotice tdPmNotice);

   int deleteTdPmNoticeByIds(Long[] ids);

   int deleteTdPmNoticeById(Long id);

   List selectTopFiveList() throws Exception;
}
