package com.emr.project.sys.mapper;

import com.emr.project.sys.domain.BusIntegMenu;
import java.util.List;

public interface BusIntegMenuMapper {
   BusIntegMenu selectBusIntegMenuById(Long id);

   List selectBusIntegMenuList(BusIntegMenu busIntegMenu);

   int insertBusIntegMenu(BusIntegMenu busIntegMenu);

   int updateBusIntegMenu(BusIntegMenu busIntegMenu);

   int deleteBusIntegMenuById(Long id);

   int deleteBusIntegMenuByIds(Long[] ids);
}
