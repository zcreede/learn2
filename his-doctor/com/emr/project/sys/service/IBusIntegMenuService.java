package com.emr.project.sys.service;

import com.emr.project.sys.domain.BusIntegMenu;
import com.emr.project.sys.domain.vo.BusIntegMenuSearchVo;
import com.emr.project.sys.domain.vo.BusIntegMenuVo;
import java.util.List;

public interface IBusIntegMenuService {
   BusIntegMenu selectBusIntegMenuById(Long id);

   List selectBusIntegMenuList(BusIntegMenu busIntegMenu);

   int insertBusIntegMenu(BusIntegMenu busIntegMenu);

   int updateBusIntegMenu(BusIntegMenu busIntegMenu);

   int deleteBusIntegMenuByIds(Long[] ids);

   int deleteBusIntegMenuById(Long id);

   BusIntegMenuVo busmenuTreeslist(String patientId) throws Exception;

   List busmenuTreeslist(BusIntegMenuSearchVo busIntegMenuSearchVo, String menuClass) throws Exception;

   List busmenuTreeslistMz(BusIntegMenuSearchVo busIntegMenuSearchVo, String menuClass, List mzMrFileInfoList) throws Exception;
}
