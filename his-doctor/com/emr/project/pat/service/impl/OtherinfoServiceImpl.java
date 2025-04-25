package com.emr.project.pat.service.impl;

import com.emr.common.utils.AgeUtil;
import com.emr.common.utils.StringUtils;
import com.emr.project.pat.domain.Otherinfo;
import com.emr.project.pat.domain.req.KeyPatientsReq;
import com.emr.project.pat.domain.resp.KeyPatientsResp;
import com.emr.project.pat.domain.resp.OtherInfoDeptResp;
import com.emr.project.pat.domain.resp.keyPatientsDetailResp;
import com.emr.project.pat.mapper.OtherinfoMapper;
import com.emr.project.pat.service.IOtherinfoService;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OtherinfoServiceImpl implements IOtherinfoService {
   @Autowired
   private OtherinfoMapper otherinfoMapper;

   public Otherinfo selectOtherinfoById(Long patientId) {
      return this.otherinfoMapper.selectOtherinfoById(patientId);
   }

   public List selectOtherinfoList(Otherinfo otherinfo) {
      return this.otherinfoMapper.selectOtherinfoList(otherinfo);
   }

   public int insertOtherinfo(Otherinfo otherinfo) {
      return this.otherinfoMapper.insertOtherinfo(otherinfo);
   }

   public int updateOtherinfo(Otherinfo otherinfo) {
      return this.otherinfoMapper.updateOtherinfo(otherinfo);
   }

   public int deleteOtherinfoByIds(Long[] patientIds) {
      return this.otherinfoMapper.deleteOtherinfoByIds(patientIds);
   }

   public int deleteOtherinfoById(Long patientId) {
      return this.otherinfoMapper.deleteOtherinfoById(patientId);
   }

   public List selectKeyPatientsList(KeyPatientsReq req) throws Exception {
      List<KeyPatientsResp> list = this.otherinfoMapper.selectDeptTotalCount(req);
      if (!list.isEmpty()) {
         List<String> deptList = (List)list.stream().map(KeyPatientsResp::getDeptCode).collect(Collectors.toList());
         req.setDeptList(deptList);
         List<OtherInfoDeptResp> otherInfoList = this.otherinfoMapper.selectOtherinfoListByDeptList(req);
         if (!otherInfoList.isEmpty()) {
            Map<String, List<OtherInfoDeptResp>> deptListMap = (Map)otherInfoList.stream().collect(Collectors.groupingBy(OtherInfoDeptResp::getDeptCode));

            for(KeyPatientsResp resp : list) {
               String deptCode = resp.getDeptCode();
               if (deptListMap.containsKey(deptCode)) {
                  for(OtherInfoDeptResp infoDeptResp : (List)deptListMap.get(deptCode)) {
                     if (StringUtils.isNotEmpty(infoDeptResp.getOperFlag()) && "1".equals(infoDeptResp.getOperFlag())) {
                        resp.setOperCount(resp.getOperCount() + 1);
                     }

                     if (StringUtils.isNotEmpty(infoDeptResp.getBloodTrans()) && "1".equals(infoDeptResp.getBloodTrans())) {
                        resp.setBloodCount(resp.getBloodCount() + 1);
                     }

                     if (StringUtils.isNotEmpty(infoDeptResp.getDyFlag()) && "1".equals(infoDeptResp.getDyFlag())) {
                        resp.setDyCount(resp.getDyCount() + 1);
                     }

                     if (StringUtils.isNotEmpty(infoDeptResp.getIllFlag()) && "1".equals(infoDeptResp.getIllFlag())) {
                        resp.setIllCount(resp.getIllCount() + 1);
                     }

                     if (StringUtils.isNotEmpty(infoDeptResp.getRescueFlag()) && "1".equals(infoDeptResp.getRescueFlag())) {
                        resp.setRescueCount(resp.getRescueCount() + 1);
                     }

                     if (StringUtils.isNotEmpty(infoDeptResp.getDieFlag()) && "1".equals(infoDeptResp.getDieFlag())) {
                        resp.setDieCount(resp.getDieCount() + 1);
                     }

                     if (StringUtils.isNotEmpty(infoDeptResp.getConsFlag()) && "1".equals(infoDeptResp.getConsFlag())) {
                        resp.setConsCount(resp.getConsCount() + 1);
                     }

                     if (StringUtils.isNotEmpty(infoDeptResp.getCrisisFlag()) && "1".equals(infoDeptResp.getCrisisFlag())) {
                        resp.setCrisisCount(resp.getCrisisCount() + 1);
                     }

                     if (StringUtils.isNotEmpty(infoDeptResp.getAntiFlag()) && "1".equals(infoDeptResp.getAntiFlag())) {
                        resp.setAntiCount(resp.getAntiCount() + 1);
                     }
                  }
               }
            }
         }
      }

      return list;
   }

   public List selectKeyPatientsDetail(KeyPatientsReq req) throws Exception {
      List<keyPatientsDetailResp> list = this.otherinfoMapper.selectKeyPatientsDetail(req);
      if (!list.isEmpty()) {
         for(keyPatientsDetailResp resp : list) {
            String ageStr = AgeUtil.getAgeStr(resp.getAgeY(), resp.getAgeMonth(), resp.getAgeDay(), resp.getAgeHour(), resp.getAgeBranch());
            resp.setPersonAge(ageStr);
         }
      }

      return list;
   }
}
