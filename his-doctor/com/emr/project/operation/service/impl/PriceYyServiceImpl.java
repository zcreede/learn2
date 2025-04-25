package com.emr.project.operation.service.impl;

import com.emr.common.utils.StringUtils;
import com.emr.project.operation.domain.vo.PriceYyJzXmVo;
import com.emr.project.operation.mapper.PriceYyMapper;
import com.emr.project.operation.service.IPriceYyService;
import com.emr.project.system.service.ISysDeptService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@Service
public class PriceYyServiceImpl implements IPriceYyService {
   private static Logger log = LoggerFactory.getLogger(PriceYyServiceImpl.class);
   @Autowired
   private ISysDeptService deptService;
   @Autowired
   private PriceYyMapper priceYyMapper;

   public List getPriceList(Map param) throws Exception {
      List<Map<String, Object>> resList = new ArrayList(1);
      log.info("==========sql数据库查询==========");
      List<Map<String, Object>> list = this.priceYyMapper.getPriceList(param);
      Map<String, String> deptMaps = this.deptService.getDeptMaps();

      for(int i = 0; i < list.size(); ++i) {
         Map<String, Object> prictTemp = (Map)list.get(i);
         String depExecNo = (String)prictTemp.get("DEP_EXEC_NO");
         if (StringUtils.isNotEmpty(depExecNo)) {
            String depName = StringUtils.defaultString((String)deptMaps.get(depExecNo));
            prictTemp.put("dep_name", depName);
         }

         resList.add(prictTemp);
      }

      return resList;
   }

   public Integer getPriceListCount(Map param) throws Exception {
      return this.priceYyMapper.getPriceListCount(param);
   }

   @CacheEvict({"priceList"})
   public void updateCache(Map param) {
      log.info("==========手动更新缓存==========入参：" + param);
   }

   public List queryByItemCodeList(List itemNoList) {
      return this.priceYyMapper.selectListByItemNoList(itemNoList);
   }

   public List getByCpNo(String chargeNo, Integer subjectFlag) {
      return this.priceYyMapper.getByCpNo(chargeNo, subjectFlag);
   }

   public PriceYyJzXmVo getByCode(Map param) {
      PriceYyJzXmVo map = this.priceYyMapper.getByCode(param);
      Map<String, String> deptMaps = this.deptService.getDeptMaps();
      String depExecNo = map.getDepExecNo();
      if (StringUtils.isNotEmpty(depExecNo)) {
         String deptName = StringUtils.defaultString((String)deptMaps.get(depExecNo));
         map.setDepName(deptName);
      }

      return map;
   }

   public PriceYyJzXmVo getByStandardCode(Map priceYyParamMap) {
      return this.priceYyMapper.getByStandardCode(priceYyParamMap);
   }
}
