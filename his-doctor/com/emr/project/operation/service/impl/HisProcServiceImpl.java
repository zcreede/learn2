package com.emr.project.operation.service.impl;

import com.emr.common.utils.SecurityUtils;
import com.emr.project.operation.domain.TdPmDjh;
import com.emr.project.operation.domain.TmPmDjcdDefine;
import com.emr.project.operation.mapper.HisProcMapper;
import com.emr.project.operation.mapper.TdPmDjhMapper;
import com.emr.project.operation.mapper.TmPmDjcdDefineMapper;
import com.emr.project.operation.service.HisProcService;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HisProcServiceImpl implements HisProcService {
   private static Logger log = LoggerFactory.getLogger(HisProcServiceImpl.class);
   @Autowired
   private HisProcMapper hisProcMapper;
   @Autowired
   private TmPmDjcdDefineMapper tmPmDjcdDefineMapper;
   @Autowired
   private TdPmDjhMapper tdPmDjhMapper;

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public String getDocumentOrBillNo(String staffCode, int type) throws Exception {
      Map<String, Object> param = new HashMap();
      param.put("czybh", staffCode);
      param.put("i_djlx", type);
      param.put("s_djh_new", "");
      param.put("s_msg", "");
      String s_djh_new = this.getDjbh(param);
      return s_djh_new;
   }

   public String getDjbh(Map param) throws Exception {
      Integer djlx = (Integer)param.get("i_djlx");
      TmPmDjcdDefine tmPmDjcdDefine = this.tmPmDjcdDefineMapper.getDjcdDefineByDjlx(djlx);
      int djLength = 0;
      if (tmPmDjcdDefine != null) {
         BigDecimal djLength_ = tmPmDjcdDefine.getDjLength();
         djLength = djLength_.intValue();
      }

      TdPmDjh tdPmDjh = new TdPmDjh();
      tdPmDjh.setDjlx(new BigDecimal(djlx));
      this.tdPmDjhMapper.insertDjh(tdPmDjh);
      param.put("id", tdPmDjh.getId());
      param.put("len", djLength);
      param.put("czybh", SecurityUtils.getLoginUser().getUser().getUserName());
      String newId = this.tdPmDjhMapper.getNewId(param);
      return newId;
   }

   public Map savePatFeeAndDetail(String prescription, String admissionNo, int hospitalizedCount, String flag, String operator) throws Exception {
      log.info("savePatFeeAndDetail：：prescription： " + prescription + " ;admissionNo:   " + admissionNo + " ;hospitalizedCount:  " + hospitalizedCount + " ;flag： " + flag + " ;operator: " + operator);
      Map<String, Object> param = new HashMap();
      param.put("prescription", prescription);
      param.put("admissionNo", admissionNo);
      param.put("hospitalizedCount", hospitalizedCount);
      param.put("flag", flag);
      param.put("operator", operator);
      param.put("resCode", 0);
      param.put("resMsg", "11");
      Map<String, Object> procResult = this.hisProcMapper.pro_0203_insert_fymx(param);
      Integer procResultCode = (Integer)param.get("resCode");
      if (param != null && param.get("resCode") != null && (Integer)param.get("resCode") != 0) {
         return procResult;
      } else {
         throw new Exception("存储过程失败");
      }
   }
}
