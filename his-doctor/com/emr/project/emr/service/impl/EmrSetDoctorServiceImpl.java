package com.emr.project.emr.service.impl;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.project.emr.domain.EmrSetDetail;
import com.emr.project.emr.domain.EmrSetDoctor;
import com.emr.project.emr.domain.SysEmrTypeConfig;
import com.emr.project.emr.domain.vo.EmrSetDetailVo;
import com.emr.project.emr.domain.vo.EmrSetDoctorVo;
import com.emr.project.emr.domain.vo.IndexVo;
import com.emr.project.emr.mapper.EmrSetDoctorMapper;
import com.emr.project.emr.service.IEmrSetDoctorService;
import com.emr.project.emr.service.IIndexService;
import com.emr.project.emr.service.ISysEmrTypeConfigService;
import com.emr.project.system.domain.SysUser;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmrSetDoctorServiceImpl implements IEmrSetDoctorService {
   @Autowired
   private EmrSetDoctorMapper emrSetDoctorMapper;
   @Autowired
   private IIndexService indexService;
   @Autowired
   private ISysEmrTypeConfigService sysEmrTypeConfigService;

   public EmrSetDoctor selectEmrSetDoctorById(Long id) {
      return this.emrSetDoctorMapper.selectEmrSetDoctorById(id);
   }

   public List selectEmrSetDoctorList(EmrSetDoctor emrSetDoctor) {
      return this.emrSetDoctorMapper.selectEmrSetDoctorList(emrSetDoctor);
   }

   public int insertEmrSetDoctor(EmrSetDoctor emrSetDoctor) {
      return this.emrSetDoctorMapper.insertEmrSetDoctor(emrSetDoctor);
   }

   public int updateEmrSetDoctor(EmrSetDoctor emrSetDoctor) {
      return this.emrSetDoctorMapper.updateEmrSetDoctor(emrSetDoctor);
   }

   public int deleteEmrSetDoctorByIds(Long[] ids) {
      return this.emrSetDoctorMapper.deleteEmrSetDoctorByIds(ids);
   }

   public int deleteEmrSetDoctorById(Long id) {
      return this.emrSetDoctorMapper.deleteEmrSetDoctorById(id);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public Boolean saveEmrSetDoctorList(EmrSetDoctorVo emrSetDoctorVo) throws Exception {
      Boolean flag = true;
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      this.emrSetDoctorMapper.deleteEmrSetDoctorBySetCdAndPatientId(emrSetDoctorVo.getSetCd(), sysUser.getBasEmployee().getEmplNumber(), emrSetDoctorVo.getPatientId());
      List<EmrSetDoctor> resultList = new ArrayList();
      if (emrSetDoctorVo.getDetailList() != null && !emrSetDoctorVo.getDetailList().isEmpty()) {
         for(EmrSetDetail emrSetDetail : emrSetDoctorVo.getDetailList()) {
            EmrSetDoctor emrSetDoctor = new EmrSetDoctor();
            emrSetDoctor.setSetCd(emrSetDoctorVo.getSetCd());
            emrSetDoctor.setSetDetailId(emrSetDetail.getId());
            emrSetDoctor.setId(SnowIdUtils.uniqueLong());
            emrSetDoctor.setDocCode(sysUser.getBasEmployee().getEmplNumber());
            emrSetDoctor.setDocName(sysUser.getBasEmployee().getEduName());
            emrSetDoctor.setCrePerCode(sysUser.getBasEmployee().getEmplNumber());
            emrSetDoctor.setCrePerName(sysUser.getBasEmployee().getEmplName());
            emrSetDoctor.setAdmissionNo(emrSetDoctorVo.getPatientId());
            resultList.add(emrSetDoctor);
         }

         if (resultList != null && !resultList.isEmpty()) {
            this.emrSetDoctorMapper.insertEmrSetDoctorList(resultList);
         }

         Long tempId = ((EmrSetDetailVo)emrSetDoctorVo.getDetailList().get(0)).getTempId();
         String emrTypeCode = ((EmrSetDetailVo)emrSetDoctorVo.getDetailList().get(0)).getEmrTypeId();
         SysEmrTypeConfig sysEmrTypeConfig = this.sysEmrTypeConfigService.selectSysEmrTypeConfigByTypeId(emrTypeCode);
         IndexVo index = new IndexVo();
         index.setPatientId(emrSetDoctorVo.getPatientId());
         List<IndexVo> indexVos = this.indexService.selectEmrListByPat(index);
         List<IndexVo> voList = this.indexService.selectSubEmrListByPat(emrSetDoctorVo.getPatientId());
         List<IndexVo> indexVos1 = indexVos != null && !indexVos.isEmpty() ? (List)indexVos.stream().filter((s) -> s.getTempId().equals(tempId)).collect(Collectors.toList()) : null;
         if (voList != null && !voList.isEmpty()) {
            List var16 = (List)voList.stream().filter((s) -> s.getTempId().equals(tempId)).collect(Collectors.toList());
         } else {
            Object var10000 = null;
         }

         if (!"01".equals(sysEmrTypeConfig.getEmrClassCode()) && CollectionUtils.isNotEmpty(indexVos1)) {
            flag = false;
         }

         if ("01".equals(sysEmrTypeConfig.getEmrClassCode()) && CollectionUtils.isNotEmpty(voList)) {
            flag = false;
         }

         if ("01".equals(sysEmrTypeConfig.getEmrClassCode()) && CollectionUtils.isEmpty(voList) && !emrTypeCode.equals("12")) {
            flag = false;
         }
      } else {
         flag = false;
      }

      return flag;
   }

   public void deleteEmrSetDoctorBySetCd(String setCd) throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      this.emrSetDoctorMapper.deleteEmrSetDoctorBySetCd(setCd, sysUser.getBasEmployee().getEmplNumber());
   }
}
