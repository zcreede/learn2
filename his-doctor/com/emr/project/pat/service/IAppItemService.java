package com.emr.project.pat.service;

import com.emr.project.pat.domain.AppItem;
import java.util.Date;
import java.util.List;

public interface IAppItemService {
   AppItem selectAppItemById(String appCd);

   List selectAppItemList(AppItem appItem);

   int insertAppItem(AppItem appItem);

   int updateAppItem(AppItem appItem);

   int deleteAppItemByIds(String[] appCds);

   int deleteAppItemById(String appCd);

   void updateStateDateByApp(String appCd, String clinItemCd, String itemState, Date clinRepDate);
}
