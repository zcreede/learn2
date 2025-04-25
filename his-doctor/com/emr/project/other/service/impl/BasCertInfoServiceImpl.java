package com.emr.project.other.service.impl;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.framework.web.service.ISyncService;
import com.emr.project.other.domain.BasCertInfo;
import com.emr.project.other.mapper.BasCertInfoMapper;
import com.emr.project.other.service.IBasCertInfoService;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.domain.vo.SqlVo;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BasCertInfoServiceImpl implements IBasCertInfoService, ISyncService {
   protected final Logger log = LoggerFactory.getLogger(BasCertInfoServiceImpl.class);
   @Autowired
   private BasCertInfoMapper basCertInfoMapper;

   public BasCertInfo selectBasCertInfoById(Long id) {
      return this.basCertInfoMapper.selectBasCertInfoById(id);
   }

   public BasCertInfo selectBasCertInfoByCerSn(String cerSn) {
      return this.basCertInfoMapper.selectBasCertInfoByCerSn(cerSn);
   }

   public BasCertInfo selectBasCertInfoByEmployeenumber(String employeenumber) {
      BasCertInfo param = new BasCertInfo();
      param.setEmployeenumber(employeenumber);
      return this.basCertInfoMapper.selectBasCertInfo(param);
   }

   public List selectBasCertInfoByEmployeenumberList(String[] employeenumbers) {
      return this.basCertInfoMapper.selectBasCertInfoListByCodes(employeenumbers);
   }

   public List selectBasCertInfoList(BasCertInfo basCertInfo) {
      return this.basCertInfoMapper.selectBasCertInfoList(basCertInfo);
   }

   public int insertBasCertInfo(BasCertInfo basCertInfo) {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      basCertInfo.setId(SnowIdUtils.uniqueLong());
      basCertInfo.setCrePerCode(user.getUserName());
      basCertInfo.setCrePerName(user.getNickName());
      basCertInfo.setIsValid("1");
      return this.basCertInfoMapper.insertBasCertInfo(basCertInfo);
   }

   public int updateBasCertInfo(BasCertInfo basCertInfo) {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      basCertInfo.setAltPerCode(user.getUserName());
      basCertInfo.setAltPerName(user.getNickName());
      return this.basCertInfoMapper.updateBasCertInfo(basCertInfo);
   }

   public int deleteBasCertInfoByIds(Long[] ids) {
      return this.basCertInfoMapper.deleteBasCertInfoByIds(ids);
   }

   public int deleteBasCertInfoById(Long id) {
      return this.basCertInfoMapper.deleteBasCertInfoById(id);
   }

   public void updateCertPicSn(String employeenumber, String certPic, String certSn) {
      BasCertInfo basCertInfo = new BasCertInfo();
      basCertInfo.setCertSn(certSn);
      basCertInfo.setCertPic(certPic);
      basCertInfo.setEmployeenumber(employeenumber);
      this.basCertInfoMapper.updateCertPicSn(basCertInfo);
   }

   public void syncData(List hisList) throws Exception {
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void syncAddData(List list, SqlVo sqlVo) throws Exception {
      List<BasCertInfo> emrList = this.basCertInfoMapper.selectBasCertInfoList(new BasCertInfo());
      List<String> docCodeList = emrList != null && !emrList.isEmpty() ? (List)emrList.stream().map((s) -> s.getEmployeenumber()).collect(Collectors.toList()) : null;
      int i = 0;

      for(Map temp : list) {
         this.log.info("i-> {}", i++);

         try {
            if (temp.get("CERT_SN") != null && temp.get("EMPLOYEENUMBER") != null && temp.get("EMPLOYEENAME") != null && temp.get("CERT_PIC") != null) {
               if (docCodeList != null && docCodeList.contains(temp.get("EMPLOYEENUMBER").toString())) {
                  this.basCertInfoMapper.deleteBasCertInfoByDocCd(temp.get("EMPLOYEENUMBER").toString());
               }

               temp.put("ID", SnowIdUtils.uniqueLong());
               this.basCertInfoMapper.insertMap(temp);
            }
         } catch (Exception e) {
            for(String column : temp.keySet()) {
               this.log.info("column名称：{},  值：{}", column, temp.get(column));
            }

            this.log.error("", e.getMessage());
            throw new Exception(e.getMessage());
         }
      }

   }

   public void syncDataMap(List mapList, String tableName) throws Exception {
   }
}
