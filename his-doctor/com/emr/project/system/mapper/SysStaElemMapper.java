package com.emr.project.system.mapper;

import com.emr.project.system.domain.SysStaElem;
import com.emr.project.system.domain.vo.SysStaElemVo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysStaElemMapper {
   SysStaElem selectSysStaElemById(Long id);

   List selectSysStaElemList(SysStaElem sysStaElem);

   List selectSysStaElemTreeList(SysStaElem sysStaElem);

   List selectExpreElemList(SysStaElem sysStaElem);

   List selectQcElemList();

   int insertSysStaElem(SysStaElem sysStaElem);

   int updateSysStaElem(SysStaElem sysStaElem);

   int changeValidFlagElem(SysStaElem sysStaElem);

   int deleteSysStaElemById(Long id);

   int deleteSysStaElemByIds(Long[] ids);

   List selectElemLibraryList(SysStaElem sysStaElem);

   List selectSetViewList(String tableName);

   SysStaElem selectElemLibraryFirst();

   SysStaElemVo selectElemInfo(String id);

   List selectElemSignList(SysStaElem sysStaElem);

   List selectElemQueryList(SysStaElem sysStaElem);

   List selectTmplTypeRequElemList(String tempType);

   List updateElem(SysStaElemVo sysStaElemVo);

   List selectSysStaElemByIdList(@Param("list") List elemIdList);

   List selectEncryptInfo();

   List queryTertiaryList(String serarchValue);
}
