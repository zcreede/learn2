package com.emr.project.emr.service.impl;

import com.emr.common.utils.SnowIdUtils;
import com.emr.project.emr.domain.EmrSharing;
import com.emr.project.emr.domain.Index;
import com.emr.project.emr.mapper.EmrSharingMapper;
import com.emr.project.emr.service.IEmrSharingService;
import com.emr.project.emr.service.IIndexService;
import com.emr.project.system.domain.SysShareElem;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.service.ISysShareElemService;
import com.emr.project.tmpl.domain.vo.ElemAttriVo;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmrSharingServiceImpl implements IEmrSharingService {
   @Autowired
   private EmrSharingMapper emrSharingMapper;
   @Autowired
   private ISysShareElemService sysShareElemService;
   @Autowired
   private IIndexService indexService;

   public EmrSharing selectEmrSharingById(Long id) {
      return this.emrSharingMapper.selectEmrSharingById(id);
   }

   public List selectEmrSharingList(EmrSharing emrSharing) {
      return this.emrSharingMapper.selectEmrSharingList(emrSharing);
   }

   public int insertEmrSharing(EmrSharing emrSharing) {
      return this.emrSharingMapper.insertEmrSharing(emrSharing);
   }

   public int updateEmrSharing(EmrSharing emrSharing) {
      return this.emrSharingMapper.updateEmrSharing(emrSharing);
   }

   public int deleteEmrSharingByIds(Long[] ids) {
      return this.emrSharingMapper.deleteEmrSharingByIds(ids);
   }

   public int deleteEmrSharingById(Long id) {
      return this.emrSharingMapper.deleteEmrSharingById(id);
   }

   public void insertList(List list) {
      this.emrSharingMapper.insertList(list);
   }

   public Boolean judgeEmrSharing() {
      return null;
   }

   @Async
   public void indexSaveEmrSharingElems(String mrType, Index index, SysUser updateUser, List elemAttriVoList) {
      List<Long> patientEmrSharingElemIdDelList = new ArrayList(1);
      List<EmrSharing> emrSharingList = new ArrayList(1);
      SysShareElem shareElemParam = new SysShareElem();
      shareElemParam.setTempType(mrType);
      List<SysShareElem> shareElemList = this.sysShareElemService.selectSysShareElemList(shareElemParam);
      List<Long> shareElemIdList = (List)shareElemList.stream().map((t) -> t.getElemId()).distinct().collect(Collectors.toList());
      List<ElemAttriVo> emrShareElemAttriVoList = (List)elemAttriVoList.stream().filter((t) -> shareElemIdList.contains(t.getElemId())).collect(Collectors.toList());
      EmrSharing emrSharingParam = new EmrSharing();
      emrSharingParam.setPatientId(index.getPatientId());
      List<EmrSharing> patientEmrSharingList = this.selectEmrSharingList(emrSharingParam);
      List<Long> patientEmrSharingElemIdList = (List)patientEmrSharingList.stream().map((t) -> t.getElemId()).distinct().collect(Collectors.toList());
      if (emrShareElemAttriVoList != null && !emrShareElemAttriVoList.isEmpty()) {
         for(ElemAttriVo elemAttriVo : emrShareElemAttriVoList) {
            EmrSharing emrSharing = new EmrSharing();
            emrSharing.setId(SnowIdUtils.uniqueLong());
            emrSharing.setOrgCd(index.getOrgCd());
            emrSharing.setPatientId(index.getPatientId());
            emrSharing.setElemId(elemAttriVo.getElemId());
            emrSharing.setElemName(elemAttriVo.getElemName());
            emrSharing.setElemCd(elemAttriVo.getElemCd());
            emrSharing.setElemTypeCd(elemAttriVo.getElemTypeCd());
            emrSharing.setElemCon(elemAttriVo.getElemCon());
            emrSharing.setElemConText(elemAttriVo.getElemConText());
            emrSharing.setCrePerCode(updateUser.getUserName());
            emrSharing.setCrePerName(updateUser.getNickName());
            emrSharingList.add(emrSharing);
            if (patientEmrSharingElemIdList.contains(elemAttriVo.getElemId())) {
               patientEmrSharingElemIdDelList.add(elemAttriVo.getElemId());
            }
         }
      }

      if (!patientEmrSharingElemIdDelList.isEmpty()) {
         Long[] patientEmrSharingElemIdDelArr = (Long[])patientEmrSharingElemIdDelList.toArray(new Long[patientEmrSharingElemIdDelList.size()]);
         this.emrSharingMapper.deleteEmrSharingByPatientIdElemIds(patientEmrSharingElemIdDelArr, index.getPatientId());
      }

      if (emrSharingList != null && !emrSharingList.isEmpty()) {
         this.insertList(emrSharingList);
      }

   }

   public List selectEmrSharingListByPatientId(String patientId) {
      return this.emrSharingMapper.selectEmrSharingListByPatientId(patientId);
   }
}
