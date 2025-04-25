package com.emr.project.pat.mapper;

import com.emr.project.pat.domain.AppItem;
import java.util.List;
import java.util.Map;

public interface AppItemMapper {
   AppItem selectAppItemById(String appCd);

   List selectAppItemList(AppItem appItem);

   int insertAppItem(AppItem appItem);

   int updateAppItem(AppItem appItem);

   int deleteAppItemById(String appCd);

   int deleteAppItemByIds(String[] appCds);

   void insertMap(Map map);
}
