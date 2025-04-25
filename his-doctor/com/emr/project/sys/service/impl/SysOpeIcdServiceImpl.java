package com.emr.project.sys.service.impl;

import com.emr.common.constant.CommonConstants;
import com.emr.common.utils.SecurityUtils;
import com.emr.project.sys.domain.OpeTypeEnum;
import com.emr.project.sys.domain.SysOpeIcd;
import com.emr.project.sys.domain.vo.SysOpeIcdVo;
import com.emr.project.sys.mapper.SysOpeIcdMapper;
import com.emr.project.sys.service.ISysOpeIcdService;
import com.emr.project.system.domain.BasEmployee;
import com.emr.project.tmpm.domain.ClinItemCollection;
import com.emr.project.tmpm.service.IClinItemCollectionService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysOpeIcdServiceImpl implements ISysOpeIcdService {
   @Autowired
   private SysOpeIcdMapper sysOpeIcdMapper;
   @Autowired
   private IClinItemCollectionService clinItemCollectionService;

   public SysOpeIcd selectSysOpeIcdById(Long icdId) {
      return this.sysOpeIcdMapper.selectSysOpeIcdById(icdId);
   }

   public List selectSysOpeIcdList(SysOpeIcd sysOpeIcd) throws Exception {
      return this.sysOpeIcdMapper.selectSysOpeIcdList(sysOpeIcd);
   }

   public List selectOpeIcdList(SysOpeIcd sysOpeIcd) throws Exception {
      return this.sysOpeIcdMapper.selectOpeIcdList(sysOpeIcd);
   }

   public int insertSysOpeIcd(SysOpeIcd sysOpeIcd) {
      return this.sysOpeIcdMapper.insertSysOpeIcd(sysOpeIcd);
   }

   public int updateSysOpeIcd(SysOpeIcd sysOpeIcd) {
      return this.sysOpeIcdMapper.updateSysOpeIcd(sysOpeIcd);
   }

   public int deleteSysOpeIcdByIds(Long[] icdIds) {
      return this.sysOpeIcdMapper.deleteSysOpeIcdByIds(icdIds);
   }

   public int deleteSysOpeIcdById(Long icdId) {
      return this.sysOpeIcdMapper.deleteSysOpeIcdById(icdId);
   }

   public List selectOpeIcdListByIcdCdList(List icdCdList) throws Exception {
      return this.sysOpeIcdMapper.selectOpeIcdListByIcdCdList(icdCdList);
   }

   public List selectDiagCommonList(SysOpeIcdVo sysOpeIcd) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      sysOpeIcd.setDocCd(basEmployee.getEmplNumber());
      List<SysOpeIcdVo> sysOpeIcdVoList = this.sysOpeIcdMapper.selectOpeCommonList(sysOpeIcd);

      for(SysOpeIcdVo sysOpeIcdVo : sysOpeIcdVoList) {
         String opeType = sysOpeIcdVo.getOpeType();
         sysOpeIcdVo.setOpeTypeName(OpeTypeEnum.getTypeNameByCode(opeType));
         sysOpeIcdVo.setCollectFlag(CommonConstants.BOOL_TRUE);
      }

      return sysOpeIcdVoList;
   }

   public List selectOpeIcdAllList(SysOpeIcdVo sysOpeIcd) throws Exception {
      List<SysOpeIcdVo> list = this.sysOpeIcdMapper.selectOpeIcdAllList(sysOpeIcd);
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      if (list != null && !list.isEmpty()) {
         ClinItemCollection clinItemCollection = new ClinItemCollection();
         clinItemCollection.setItemClassCd("60");
         clinItemCollection.setDocCd(basEmployee.getEmplNumber());
         List<ClinItemCollection> clinList = this.clinItemCollectionService.selectClinItemCollectionList(clinItemCollection);

         for(SysOpeIcdVo sysOpeIcdVo : list) {
            String opeType = sysOpeIcdVo.getOpeType();
            sysOpeIcdVo.setOpeTypeName(OpeTypeEnum.getTypeNameByCode(opeType));
            if (clinList != null && !clinList.isEmpty()) {
               List<String> itemList = (List)clinList.stream().map(ClinItemCollection::getItemCd).collect(Collectors.toList());
               if (itemList.contains(sysOpeIcdVo.getIcdCd())) {
                  sysOpeIcdVo.setCollectFlag(CommonConstants.BOOL_TRUE);
               } else {
                  sysOpeIcdVo.setCollectFlag(CommonConstants.BOOL_FALSE);
               }
            } else {
               sysOpeIcdVo.setCollectFlag(CommonConstants.BOOL_FALSE);
            }
         }
      }

      return list;
   }
}
