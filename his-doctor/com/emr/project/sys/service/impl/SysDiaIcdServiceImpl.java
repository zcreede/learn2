package com.emr.project.sys.service.impl;

import com.emr.common.constant.CommonConstants;
import com.emr.common.utils.SecurityUtils;
import com.emr.project.sys.domain.SysDiaIcd;
import com.emr.project.sys.domain.vo.SysDiaIcdVo;
import com.emr.project.sys.mapper.SysDiaIcdMapper;
import com.emr.project.sys.service.ISysDiaIcdService;
import com.emr.project.system.domain.BasEmployee;
import com.emr.project.tmpm.domain.ClinItemCollection;
import com.emr.project.tmpm.service.IClinItemCollectionService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysDiaIcdServiceImpl implements ISysDiaIcdService {
   @Autowired
   private SysDiaIcdMapper sysDiaIcdMapper;
   @Autowired
   private IClinItemCollectionService clinItemCollectionService;

   public SysDiaIcd selectSysDiaIcdById(Long icdId) {
      return this.sysDiaIcdMapper.selectSysDiaIcdById(icdId);
   }

   public List selectSysDiaIcdList(SysDiaIcd sysDiaIcd) throws Exception {
      return this.sysDiaIcdMapper.selectSysDiaIcdList(sysDiaIcd);
   }

   public List selectSysDiaIcdListByType(SysDiaIcd sysDiaIcd) throws Exception {
      List<SysDiaIcdVo> list = this.sysDiaIcdMapper.selectSysDiaIcdListByType(sysDiaIcd);
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      if (list != null && !list.isEmpty()) {
         ClinItemCollection clinItemCollection = new ClinItemCollection();
         clinItemCollection.setItemClassCd("50");
         clinItemCollection.setDocCd(basEmployee.getEmplNumber());
         List<ClinItemCollection> clinList = this.clinItemCollectionService.selectClinItemCollectionList(clinItemCollection);

         for(SysDiaIcdVo sysDiaIcdVo : list) {
            if (clinList != null && !clinList.isEmpty()) {
               List<String> itemList = (List)clinList.stream().map((s) -> s.getItemCd()).collect(Collectors.toList());
               if (itemList.contains(sysDiaIcdVo.getIcdCd())) {
                  sysDiaIcdVo.setCollectFlag(CommonConstants.BOOL_TRUE);
               } else {
                  sysDiaIcdVo.setCollectFlag(CommonConstants.BOOL_FALSE);
               }
            } else {
               sysDiaIcdVo.setCollectFlag(CommonConstants.BOOL_FALSE);
            }
         }
      }

      return list;
   }

   public int insertSysDiaIcd(SysDiaIcd sysDiaIcd) {
      return this.sysDiaIcdMapper.insertSysDiaIcd(sysDiaIcd);
   }

   public int updateSysDiaIcd(SysDiaIcd sysDiaIcd) {
      return this.sysDiaIcdMapper.updateSysDiaIcd(sysDiaIcd);
   }

   public int deleteSysDiaIcdByIds(Long[] icdIds) {
      return this.sysDiaIcdMapper.deleteSysDiaIcdByIds(icdIds);
   }

   public int deleteSysDiaIcdById(Long icdId) {
      return this.sysDiaIcdMapper.deleteSysDiaIcdById(icdId);
   }

   public List selectSysDiaIcdAndOpeIcd(SysDiaIcdVo sysDiaIcdVo) {
      return this.sysDiaIcdMapper.selectSysDiaIcdAndOpeIcd(sysDiaIcdVo);
   }

   public List selectSysDiaIcdByIcdCdList(List icdCdList) {
      return this.sysDiaIcdMapper.selectSysDiaIcdByIcdCdList(icdCdList);
   }

   public List selectDiagCommonList(SysDiaIcdVo sysDiaIcd) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      sysDiaIcd.setDocCd(basEmployee.getEmplNumber());
      List<SysDiaIcdVo> sysDiaIcdVoList = this.sysDiaIcdMapper.selectDiagCommonList(sysDiaIcd);

      for(SysDiaIcdVo sysDiaIcdVo : sysDiaIcdVoList) {
         sysDiaIcdVo.setCollectFlag(CommonConstants.BOOL_TRUE);
      }

      return sysDiaIcdVoList;
   }
}
