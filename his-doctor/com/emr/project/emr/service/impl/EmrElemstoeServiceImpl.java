package com.emr.project.emr.service.impl;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.project.emr.domain.EmrElemstoe;
import com.emr.project.emr.domain.Index;
import com.emr.project.emr.domain.vo.IndexVo;
import com.emr.project.emr.mapper.EmrElemstoeMapper;
import com.emr.project.emr.service.IEmrElemstoeService;
import com.emr.project.system.domain.SysElemStrstore;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.service.ISysElemStrstoreService;
import com.emr.project.tmpl.domain.vo.ElemAttriVo;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmrElemstoeServiceImpl implements IEmrElemstoeService {
   @Autowired
   private EmrElemstoeMapper emrElemstoeMapper;
   @Autowired
   private ISysElemStrstoreService sysElemStrstoreService;

   public EmrElemstoe selectEmrElemstoeById(Long id) {
      return this.emrElemstoeMapper.selectEmrElemstoeById(id);
   }

   public List selectEmrElemstoeList(EmrElemstoe emrElemstoe) {
      return this.emrElemstoeMapper.selectEmrElemstoeList(emrElemstoe);
   }

   public int insertEmrElemstoe(EmrElemstoe emrElemstoe) {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      emrElemstoe.setId(SnowIdUtils.uniqueLong());
      emrElemstoe.setCrePerCode(user.getUserName());
      emrElemstoe.setCrePerName(user.getNickName());
      return this.emrElemstoeMapper.insertEmrElemstoe(emrElemstoe);
   }

   public int updateEmrElemstoe(EmrElemstoe emrElemstoe) {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      emrElemstoe.setAltPerCode(user.getUserName());
      emrElemstoe.setCrePerName(user.getNickName());
      return this.emrElemstoeMapper.updateEmrElemstoe(emrElemstoe);
   }

   public int deleteEmrElemstoeByIds(Long[] ids) {
      return this.emrElemstoeMapper.deleteEmrElemstoeByIds(ids);
   }

   public int deleteEmrElemstoeById(Long id) {
      return this.emrElemstoeMapper.deleteEmrElemstoeById(id);
   }

   public void deleteEmrElemstoeByMrFileId(Long mrFileId) {
      this.emrElemstoeMapper.deleteEmrElemstoeByMrFileId(mrFileId);
   }

   public void addList(List emrElemstoeList) {
      this.emrElemstoeMapper.insertList(emrElemstoeList);
   }

   @Async
   public void indexSaveEmrElemstoes(String mrType, Long mrFileId, Index index, SysUser updateUser, List elemAttriVoList, Map elemBase64Map) throws Exception {
      List<EmrElemstoe> emrElemstoeList = new ArrayList(1);
      SysElemStrstore sysElemStrstoreParam = new SysElemStrstore();
      sysElemStrstoreParam.setTempType(mrType);
      List<SysElemStrstore> sysElemStrstoreList = this.sysElemStrstoreService.selectSysElemStrstoreList(sysElemStrstoreParam);
      List<Long> sysElemStrstoreElemIdList = (List)sysElemStrstoreList.stream().map((t) -> t.getElemId()).distinct().collect(Collectors.toList());
      List<ElemAttriVo> strstoreElemAttriVoList = (List)elemAttriVoList.stream().filter((t) -> sysElemStrstoreElemIdList.contains(t.getElemId())).collect(Collectors.toList());
      if (strstoreElemAttriVoList != null && !strstoreElemAttriVoList.isEmpty()) {
         for(ElemAttriVo elemAttriVo : strstoreElemAttriVoList) {
            EmrElemstoe elemstoe = new EmrElemstoe();
            elemstoe.setId(SnowIdUtils.uniqueLong());
            elemstoe.setOrgCd(index.getOrgCd());
            elemstoe.setOrgName(index.getOrgName());
            elemstoe.setPatientId(index.getPatientId());
            elemstoe.setMrFileId(mrFileId);
            elemstoe.setMrTypeCd(mrType);
            elemstoe.setPrioElemCd(elemAttriVo.getParentElemCd());
            elemstoe.setPrioElemId(elemAttriVo.getParentElemId());
            elemstoe.setPrioElemName(elemAttriVo.getParentElemName());
            elemstoe.setPrioFileElemid(elemAttriVo.getParentContElemName());
            elemstoe.setElemId(elemAttriVo.getElemId());
            elemstoe.setElemCd(elemAttriVo.getElemCd());
            elemstoe.setElemName(elemAttriVo.getElemName());
            elemstoe.setFileElemId(elemAttriVo.getContElemName());
            elemstoe.setElemCon(elemAttriVo.getElemConText());
            elemstoe.setUnitId(elemAttriVo.getUnitId());
            elemstoe.setCrePerCode(updateUser.getUserName());
            elemstoe.setCrePerName(updateUser.getNickName());
            elemstoe.setElemConCode(elemAttriVo.getElemContTextCode());
            String elemConBase64 = elemBase64Map != null && elemBase64Map.get(elemAttriVo.getContElemName()) != null ? (String)elemBase64Map.get(elemAttriVo.getContElemName()) : null;
            elemstoe.setElemConBase64(elemConBase64);
            emrElemstoeList.add(elemstoe);
         }
      }

      this.deleteEmrElemstoeByMrFileId(mrFileId);
      if (emrElemstoeList != null && !emrElemstoeList.isEmpty()) {
         this.addList(emrElemstoeList);
      }

   }

   public List selectListByDate(String beginDate, String endDate) throws Exception {
      return this.emrElemstoeMapper.selectListByDate(beginDate, endDate);
   }

   public List selectEmrElemstoeListByPatientIdAndTypeList(String patientId, List typeList) {
      return this.emrElemstoeMapper.selectEmrElemstoeListByPatientIdAndTypeList(patientId, typeList);
   }

   public List selectTertiaryIndexList(IndexVo indexVo) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      indexVo.setOrgCd(user.getDept().getOrgCode());
      return this.emrElemstoeMapper.selectTertiaryIndexList(indexVo);
   }
}
