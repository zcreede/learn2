package com.emr.project.emr.service;

import com.emr.project.emr.domain.EmrBinary;
import com.emr.project.emr.domain.Index;
import com.emr.project.system.domain.SysUser;
import com.emr.project.tmpl.domain.TmplIndex;
import java.util.Date;
import java.util.List;

public interface IEmrBinaryService {
   EmrBinary selectEmrBinaryById(Long mrFileId);

   List selectEmrBinaryList(EmrBinary emrBinary);

   void insertEmrBinary(EmrBinary emrBinary);

   void updateEmrBinary(EmrBinary emrBinary);

   int deleteEmrBinaryByIds(Long[] mrFileIds);

   int deleteEmrBinaryById(Long mrFileId);

   String selectIndexXmlStrById(Long mrFileId);

   String selectTempXmlStr(TmplIndex tmplIndexRes);

   List selectListByIds(List mrFileIds) throws Exception;

   void addOrUpdateEmrBinary(Long mrFileId, Index index, SysUser updateUser, String mrCon, Date updateTime);
}
