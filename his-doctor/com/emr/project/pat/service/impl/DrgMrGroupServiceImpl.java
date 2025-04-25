package com.emr.project.pat.service.impl;

import com.emr.common.utils.DateUtils;
import com.emr.project.common.service.ICommonService;
import com.emr.project.pat.domain.DrgMrGroup;
import com.emr.project.pat.domain.vo.DrgMrGroupVo;
import com.emr.project.pat.mapper.DrgMrGroupMapper;
import com.emr.project.pat.service.IDrgMrGroupService;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DrgMrGroupServiceImpl implements IDrgMrGroupService {
   @Autowired
   private DrgMrGroupMapper drgMrGroupMapper;
   @Autowired
   private ICommonService commonService;

   public DrgMrGroup selectDrgMrGroupById(Long id) {
      return this.drgMrGroupMapper.selectDrgMrGroupById(id);
   }

   public List selectDrgMrGroupList(DrgMrGroup drgMrGroup) {
      return this.drgMrGroupMapper.selectDrgMrGroupList(drgMrGroup);
   }

   public int insertDrgMrGroup(DrgMrGroup drgMrGroup) {
      drgMrGroup.setCreateTime(DateUtils.getNowDate());
      return this.drgMrGroupMapper.insertDrgMrGroup(drgMrGroup);
   }

   public int updateDrgMrGroup(DrgMrGroup drgMrGroup) throws Exception {
      drgMrGroup.setUpdateTime(this.commonService.getDbSysdate());
      return this.drgMrGroupMapper.updateDrgMrGroup(drgMrGroup);
   }

   public int deleteDrgMrGroupByIds(Long[] ids) {
      return this.drgMrGroupMapper.deleteDrgMrGroupByIds(ids);
   }

   public int deleteDrgMrGroupById(Long id) {
      return this.drgMrGroupMapper.deleteDrgMrGroupById(id);
   }

   public DrgMrGroupVo selectInfo(DrgMrGroupVo drgMrGroupVo) throws Exception {
      DrgMrGroupVo drgMrGroupInfo = this.drgMrGroupMapper.selectInfo(drgMrGroupVo);
      if (drgMrGroupInfo != null && drgMrGroupInfo.getCostSum() != null && drgMrGroupInfo.getPaymentStandard() != null) {
         BigDecimal b1 = new BigDecimal(Double.toString(drgMrGroupInfo.getCostSum()));
         BigDecimal b2 = new BigDecimal(Double.toString(drgMrGroupInfo.getPaymentStandard()));
         drgMrGroupInfo.setLoss(b2.subtract(b1).doubleValue());
      }

      return drgMrGroupInfo;
   }
}
