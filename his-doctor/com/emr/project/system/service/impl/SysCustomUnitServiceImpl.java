package com.emr.project.system.service.impl;

import com.emr.common.utils.PinYinUtil;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.framework.security.LoginUser;
import com.emr.project.system.domain.SysCustomSet;
import com.emr.project.system.domain.SysCustomUnit;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.mapper.SysCustomSetMapper;
import com.emr.project.system.mapper.SysCustomUnitMapper;
import com.emr.project.system.service.ISysCustomUnitService;
import com.emr.project.tool.gen.domain.GenTableColumn;
import com.emr.project.tool.gen.service.IGenTableColumnService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysCustomUnitServiceImpl implements ISysCustomUnitService {
   @Autowired
   private SysCustomUnitMapper sysCustomUnitMapper;
   @Autowired
   private IGenTableColumnService genTableColumnService;
   @Autowired
   private SysCustomSetMapper sysCustomSetMapper;

   public SysCustomUnit selectSysCustomUnitById(Long id) {
      return this.sysCustomUnitMapper.selectSysCustomUnitById(id);
   }

   public List selectSysCustomUnitList(SysCustomUnit sysCustomUnit) {
      return this.sysCustomUnitMapper.selectSysCustomUnitList(sysCustomUnit);
   }

   public List selectSysCustomUnitListBySetId(Long setId) {
      List<SysCustomUnit> sysCustomUnitList = this.sysCustomUnitMapper.selectSysCustomUnitListBySetId(setId);
      return sysCustomUnitList;
   }

   public List updateCustomUnitList(Long setId) {
      LoginUser loginUser = SecurityUtils.getLoginUser();
      SysUser user = loginUser.getUser();
      List<SysCustomUnit> customUnitAddList = new ArrayList();
      List<SysCustomUnit> customUnitEditList = new ArrayList();
      new ArrayList();
      SysCustomSet sysCustomSet = this.sysCustomSetMapper.selectSysCustomSetById(setId);
      if (sysCustomSet != null) {
         List<GenTableColumn> genTableColumnList = this.genTableColumnService.selectDbTableColumnsByName(sysCustomSet.getSetName());
         List<String> tableColumnList = (List)genTableColumnList.stream().filter((t) -> StringUtils.isNotBlank(t.getColumnName())).map((t) -> t.getColumnName()).collect(Collectors.toList());
         List<SysCustomUnit> sysCustomUnitList = this.sysCustomUnitMapper.selectSysCustomUnitListBySetId(setId);
         List<String> customUnitList = (List)sysCustomUnitList.stream().filter((t) -> StringUtils.isNotBlank(t.getFieldCd())).map((t) -> t.getFieldCd()).collect(Collectors.toList());
         if (genTableColumnList != null && sysCustomUnitList != null) {
            for(String tableColumn : tableColumnList) {
               List<GenTableColumn> genTableColumns = (List)genTableColumnList.stream().filter((t) -> tableColumn.equals(t.getColumnName())).collect(Collectors.toList());
               List<SysCustomUnit> sysCustomUnits = (List)sysCustomUnitList.stream().filter((t) -> tableColumn.equals(t.getFieldCd())).collect(Collectors.toList());
               if (customUnitList.contains(tableColumn)) {
                  if (genTableColumns != null && genTableColumns.size() > 0 && sysCustomUnits != null && sysCustomUnits.size() > 0 && (!((GenTableColumn)genTableColumns.get(0)).getColumnType().equals(((SysCustomUnit)sysCustomUnits.get(0)).getDataType()) || 0 != Long.compare(((GenTableColumn)genTableColumns.get(0)).getDataLength(), ((SysCustomUnit)sysCustomUnits.get(0)).getDataLong()))) {
                     SysCustomUnit customUnit = new SysCustomUnit();
                     customUnit.setId(((SysCustomUnit)sysCustomUnits.get(0)).getId());
                     customUnit.setDataType(((GenTableColumn)genTableColumns.get(0)).getColumnType());
                     customUnit.setDataLong(((GenTableColumn)genTableColumns.get(0)).getDataLength());
                     customUnit.setAltPerName(user.getNickName());
                     customUnit.setAltPerCode(user.getUserName());
                     customUnitEditList.add(customUnit);
                  }
               } else {
                  SysCustomUnit customUnit = new SysCustomUnit();
                  customUnit.setId(SnowIdUtils.uniqueLong());
                  customUnit.setSetId(sysCustomSet.getId());
                  customUnit.setFieldCd(((GenTableColumn)genTableColumns.get(0)).getColumnName());
                  customUnit.setFieldName(StringUtils.isNotBlank(((GenTableColumn)genTableColumns.get(0)).getColumnComment()) ? ((GenTableColumn)genTableColumns.get(0)).getColumnComment() : "");
                  customUnit.setInputstrphtc(PinYinUtil.getPinYinHeadChar(((GenTableColumn)genTableColumns.get(0)).getColumnName()));
                  customUnit.setDataType(((GenTableColumn)genTableColumns.get(0)).getColumnType());
                  customUnit.setDataLong(((GenTableColumn)genTableColumns.get(0)).getDataLength());
                  customUnit.setValidFlag("1");
                  customUnit.setCrePerName(user.getNickName());
                  customUnit.setCrePerCode(user.getUserName());
                  customUnitAddList.add(customUnit);
               }
            }

            for(String customUnit : customUnitList) {
               if (!tableColumnList.contains(customUnit)) {
                  List<SysCustomUnit> sysCustomUnits = (List)sysCustomUnitList.stream().filter((t) -> customUnit.equals(t.getFieldCd())).collect(Collectors.toList());
                  SysCustomUnit customUnitReq = new SysCustomUnit();
                  customUnitReq.setId(((SysCustomUnit)sysCustomUnits.get(0)).getId());
                  customUnitReq.setValidFlag("0");
                  customUnitReq.setAltPerName(user.getNickName());
                  customUnitReq.setAltPerCode(user.getUserName());
                  customUnitEditList.add(customUnitReq);
               }
            }
         } else if (genTableColumnList != null && sysCustomUnitList == null) {
            for(GenTableColumn genTableColumn : genTableColumnList) {
               SysCustomUnit customUnit = new SysCustomUnit();
               customUnit.setId(SnowIdUtils.uniqueLong());
               customUnit.setSetId(sysCustomSet.getId());
               customUnit.setFieldCd(genTableColumn.getColumnName());
               customUnit.setFieldName(StringUtils.isNotBlank(genTableColumn.getColumnComment()) ? genTableColumn.getColumnComment() : "");
               customUnit.setInputstrphtc(PinYinUtil.getPinYinHeadChar(genTableColumn.getColumnName()));
               customUnit.setDataType(genTableColumn.getColumnType());
               customUnit.setDataLong(genTableColumn.getDataLength());
               customUnit.setValidFlag("1");
               customUnit.setCrePerName(user.getNickName());
               customUnit.setCrePerCode(user.getUserName());
               customUnitAddList.add(customUnit);
            }
         }
      }

      if (customUnitAddList != null && customUnitAddList.size() > 0) {
         this.sysCustomUnitMapper.batchSysCustomUnit(customUnitAddList);
      }

      if (customUnitEditList != null && customUnitEditList.size() > 0) {
         this.sysCustomUnitMapper.batchEditSysCustomUnit(customUnitEditList);
      }

      List<SysCustomUnit> sysCustomUnitListRes = this.sysCustomUnitMapper.selectSysCustomUnitListBySetId(setId);
      return sysCustomUnitListRes;
   }

   public SysCustomUnit selectSysCustomUnitBySetIdAndFieldCd(Long setId, String fieldCd) {
      return this.sysCustomUnitMapper.selectSysCustomUnitBySetIdAndFieldCd(setId, fieldCd);
   }

   public int insertSysCustomUnit(SysCustomUnit sysCustomUnit) {
      return this.sysCustomUnitMapper.insertSysCustomUnit(sysCustomUnit);
   }

   public int updateSysCustomUnit(SysCustomUnit sysCustomUnit) {
      LoginUser loginUser = SecurityUtils.getLoginUser();
      SysUser user = loginUser.getUser();
      sysCustomUnit.setAltPerCode(user.getUserName());
      sysCustomUnit.setAltPerName(user.getNickName());
      return this.sysCustomUnitMapper.updateSysCustomUnit(sysCustomUnit);
   }

   public int deleteSysCustomUnitByIds(Long[] ids) {
      return this.sysCustomUnitMapper.deleteSysCustomUnitByIds(ids);
   }

   public int deleteSysCustomUnitById(Long id) {
      return this.sysCustomUnitMapper.deleteSysCustomUnitById(id);
   }

   public void deleteSysCustomUnitBySetId(Long setId) {
      this.sysCustomUnitMapper.deleteSysCustomUnitBySetId(setId);
   }

   public void addSysCustomUnit(SysCustomSet sysCustomSet) throws Exception {
      LoginUser loginUser = SecurityUtils.getLoginUser();
      SysUser user = loginUser.getUser();
      List<GenTableColumn> genTableColumns = this.genTableColumnService.selectDbTableColumnsByName(sysCustomSet.getSetName());
      List<SysCustomUnit> customUnitList = new ArrayList(genTableColumns.size());

      for(GenTableColumn genTableColumn : genTableColumns) {
         SysCustomUnit customUnit = new SysCustomUnit();
         customUnit.setId(SnowIdUtils.uniqueLong());
         customUnit.setSetId(sysCustomSet.getId());
         customUnit.setFieldCd(genTableColumn.getColumnName());
         customUnit.setFieldName(StringUtils.isNotBlank(genTableColumn.getColumnComment()) ? genTableColumn.getColumnComment() : "");
         customUnit.setInputstrphtc(PinYinUtil.getPinYinHeadChar(genTableColumn.getColumnName()));
         customUnit.setDataType(genTableColumn.getColumnType());
         customUnit.setDataLong(genTableColumn.getDataLength());
         customUnit.setValidFlag("1");
         customUnit.setCrePerName(user.getNickName());
         customUnit.setCrePerCode(user.getUserName());
         customUnitList.add(customUnit);
      }

      if (!customUnitList.isEmpty()) {
         this.sysCustomUnitMapper.batchSysCustomUnit(customUnitList);
      }

   }

   public void changeValidFlag(Long id, String validFlag) throws Exception {
      LoginUser loginUser = SecurityUtils.getLoginUser();
      SysUser user = loginUser.getUser();
      SysCustomUnit sysCustomUnit = new SysCustomUnit();
      sysCustomUnit.setId(id);
      sysCustomUnit.setValidFlag(validFlag);
      sysCustomUnit.setAltPerCode(user.getUserName());
      sysCustomUnit.setAltPerName(user.getNickName());
      this.sysCustomUnitMapper.updateSysCustomUnit(sysCustomUnit);
   }
}
