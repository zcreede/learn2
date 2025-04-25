package com.emr.project.sys.mapper;

import com.emr.project.sys.domain.SysDiaIcd;
import com.emr.project.sys.domain.vo.SysDiaIcdVo;
import java.util.List;

public interface SysDiaIcdMapper {
   SysDiaIcd selectSysDiaIcdById(Long icdId);

   List selectSysDiaIcdList(SysDiaIcd sysDiaIcd);

   List selectSysDiaIcdListByType(SysDiaIcd sysDiaIcd);

   List selectSysDiaIcdAndOpeIcd(SysDiaIcdVo sysDiaIcdVo);

   int insertSysDiaIcd(SysDiaIcd sysDiaIcd);

   int updateSysDiaIcd(SysDiaIcd sysDiaIcd);

   int deleteSysDiaIcdById(Long icdId);

   int deleteSysDiaIcdByIds(Long[] icdIds);

   List selectSysDiaIcdByIcdCdList(List list);

   List selectDiagCommonList(SysDiaIcd sysDiaIcd);
}
