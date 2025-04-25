package com.emr.project.docOrder.service.impl;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.project.common.service.ICommonService;
import com.emr.project.docOrder.domain.TdPaItemDocQuery;
import com.emr.project.docOrder.mapper.TdPaItemDocQueryMapper;
import com.emr.project.docOrder.service.ITdPaItemDocQueryService;
import com.emr.project.system.domain.BasEmployee;
import com.emr.project.system.domain.SysUser;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TdPaItemDocQueryServiceImpl implements ITdPaItemDocQueryService {
   @Autowired
   private TdPaItemDocQueryMapper tdPaItemDocQueryMapper;
   @Autowired
   private ICommonService commonService;

   public TdPaItemDocQuery selectTdPaItemDocQueryById(Long id) {
      return this.tdPaItemDocQueryMapper.selectTdPaItemDocQueryById(id);
   }

   public List selectTdPaItemDocQueryList(TdPaItemDocQuery tdPaItemDocQuery) {
      return this.tdPaItemDocQueryMapper.selectTdPaItemDocQueryList(tdPaItemDocQuery);
   }

   public void insertTdPaItemDocQuery(TdPaItemDocQuery tdPaItemDocQuery) throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      BasEmployee basEmployee = sysUser.getBasEmployee();
      TdPaItemDocQuery param = new TdPaItemDocQuery();
      if ("16".equals(tdPaItemDocQuery.getOrderFlag())) {
         param.setDocCd(sysUser.getDept().getDeptCode());
      } else {
         param.setDocCd(basEmployee.getEmplNumber());
      }

      param.setOrderFlag(tdPaItemDocQuery.getOrderFlag());
      List<TdPaItemDocQuery> list = this.selectTdPaItemDocQueryList(param);
      tdPaItemDocQuery.setAltDate(this.commonService.getDbSysdate());
      if (list != null && !list.isEmpty()) {
         tdPaItemDocQuery.setId(((TdPaItemDocQuery)list.get(0)).getId());
         this.tdPaItemDocQueryMapper.updateTdPaItemDocQuery(tdPaItemDocQuery);
      } else {
         tdPaItemDocQuery.setId(SnowIdUtils.uniqueLong());
         tdPaItemDocQuery.setOrgCd(sysUser.getHospital().getOrgCode());
         if ("16".equals(tdPaItemDocQuery.getOrderFlag())) {
            tdPaItemDocQuery.setDocCd(sysUser.getDept().getDeptCode());
            tdPaItemDocQuery.setDocName(sysUser.getDept().getDeptName());
         } else {
            tdPaItemDocQuery.setDocCd(basEmployee.getEmplNumber());
            tdPaItemDocQuery.setDocName(basEmployee.getEmplName());
         }

         this.tdPaItemDocQueryMapper.insertTdPaItemDocQuery(tdPaItemDocQuery);
      }

   }

   public int updateTdPaItemDocQuery(TdPaItemDocQuery tdPaItemDocQuery) {
      return this.tdPaItemDocQueryMapper.updateTdPaItemDocQuery(tdPaItemDocQuery);
   }

   public int deleteTdPaItemDocQueryByIds(Long[] ids) {
      return this.tdPaItemDocQueryMapper.deleteTdPaItemDocQueryByIds(ids);
   }

   public int deleteTdPaItemDocQueryById(Long id) {
      return this.tdPaItemDocQueryMapper.deleteTdPaItemDocQueryById(id);
   }
}
