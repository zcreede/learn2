package com.emr.project.his.service.impl;

import com.emr.common.utils.AgeUtil;
import com.emr.common.utils.SecurityUtils;
import com.emr.framework.aspectj.lang.enums.DataSourceType;
import com.emr.framework.datasource.DruidUtil;
import com.emr.project.his.domain.vo.DocInHospitalVo;
import com.emr.project.his.mapper.DocInHospitalMapper;
import com.emr.project.his.service.IDocInHospitalService;
import com.emr.project.system.domain.SyncDatasource;
import com.emr.project.system.domain.vo.SqlVo;
import com.emr.project.system.service.ISyncDatasourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocInHospitalServiceImpl implements IDocInHospitalService {
   @Autowired
   private DocInHospitalMapper docInHospitalMapper;
   @Autowired
   private ISyncDatasourceService syncDatasourceService;

   public DocInHospitalVo selectDocInHospitalByInpNo(SqlVo sqlVo) throws Exception {
      SyncDatasource syncDatasource = this.syncDatasourceService.selectSyncDatasourceByCode(DataSourceType.docInHospital.toString());
      sqlVo.setSqlStr(syncDatasource.getQuerySql());
      DocInHospitalVo docInHospitalVo = null;

      try {
         DruidUtil.switchDB(syncDatasource);
         docInHospitalVo = this.docInHospitalMapper.selectDocInHospitalByInpNo(sqlVo);
      } finally {
         DruidUtil.clearDataSource();
      }

      if (docInHospitalVo != null) {
         String ageStr = AgeUtil.getAgeStr(docInHospitalVo.getAgeY(), docInHospitalVo.getAgeM(), docInHospitalVo.getAgeD(), docInHospitalVo.getAgeH(), docInHospitalVo.getAgeMin());
         docInHospitalVo.setAge(ageStr);
      } else {
         docInHospitalVo = new DocInHospitalVo();
      }

      docInHospitalVo.setOrgName(SecurityUtils.getLoginUser().getUser().getHospital().getOrgName());
      docInHospitalVo.setInpNo(sqlVo.getInpNo());
      return docInHospitalVo;
   }
}
