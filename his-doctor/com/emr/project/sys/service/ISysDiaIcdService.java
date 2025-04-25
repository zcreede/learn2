package com.emr.project.sys.service;

import com.emr.project.sys.domain.SysDiaIcd;
import com.emr.project.sys.domain.vo.SysDiaIcdVo;
import java.util.List;

public interface ISysDiaIcdService {
   SysDiaIcd selectSysDiaIcdById(Long icdId);

   List selectSysDiaIcdList(SysDiaIcd sysDiaIcd) throws Exception;

   List selectSysDiaIcdListByType(SysDiaIcd sysDiaIcd) throws Exception;

   int insertSysDiaIcd(SysDiaIcd sysDiaIcd);

   int updateSysDiaIcd(SysDiaIcd sysDiaIcd);

   int deleteSysDiaIcdByIds(Long[] icdIds);

   int deleteSysDiaIcdById(Long icdId);

   List selectSysDiaIcdAndOpeIcd(SysDiaIcdVo sysDiaIcdVo);

   List selectSysDiaIcdByIcdCdList(List icdCdList);

   List selectDiagCommonList(SysDiaIcdVo sysDiaIcd) throws Exception;
}
