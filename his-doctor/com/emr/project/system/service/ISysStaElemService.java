package com.emr.project.system.service;

import com.emr.project.system.domain.SysStaElem;
import com.emr.project.system.domain.vo.SysStaElemVo;
import java.util.List;
import java.util.Map;

public interface ISysStaElemService {
   SysStaElem selectSysStaElemById(Long id);

   List selectSysStaElemList(SysStaElem sysStaElem);

   List selectExpreElemList(SysStaElem sysStaElem) throws Exception;

   List selectQcElemList();

   void insertSysStaElem(SysStaElemVo sysStaElemVo) throws Exception;

   void updateSysStaElem(SysStaElemVo sysStaElemVo) throws Exception;

   int changeValidFlagElem(SysStaElem sysStaElem) throws Exception;

   int deleteSysStaElemByIds(Long[] ids);

   int deleteSysStaElemById(Long id);

   List queryElemLibTree() throws Exception;

   List selectElementTree(SysStaElem sysStaElem) throws Exception;

   List selectElemLibraryList(SysStaElem sysStaElem) throws Exception;

   SysStaElem generateUniqueCode(String str) throws Exception;

   SysStaElemVo selectElemInfo(String id) throws Exception;

   List selectElemSignList(SysStaElem sysStaElem) throws Exception;

   Map getSetViewVoList(String setName) throws Exception;

   List selectElemQueryList(SysStaElem sysStaElem, String type) throws Exception;

   List selectTmplTypeRequElemList(String tempType) throws Exception;

   void saveElemVo(SysStaElemVo sysStaElemVo) throws Exception;

   void updateElem(SysStaElemVo sysStaElemVo) throws Exception;

   List queryTertiaryList(String serarchValue) throws Exception;
}
