package com.emr.project.emr.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.emr.project.emr.domain.EmrBinary;
import com.emr.project.emr.domain.Index;
import com.emr.project.emr.mapper.EmrBinaryMapper;
import com.emr.project.emr.service.IEmrBinaryService;
import com.emr.project.system.domain.SysUser;
import com.emr.project.tmpl.domain.TmplIndex;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmrBinaryServiceImpl implements IEmrBinaryService {
   @Autowired
   private EmrBinaryMapper emrBinaryMapper;

   public EmrBinary selectEmrBinaryById(Long mrFileId) {
      return this.emrBinaryMapper.selectEmrBinaryById(mrFileId);
   }

   public List selectEmrBinaryList(EmrBinary emrBinary) {
      return this.emrBinaryMapper.selectEmrBinaryList(emrBinary);
   }

   @Async
   public void insertEmrBinary(EmrBinary emrBinary) {
      this.emrBinaryMapper.insertEmrBinary(emrBinary);
   }

   @Async
   public void updateEmrBinary(EmrBinary emrBinary) {
      this.emrBinaryMapper.updateEmrBinary(emrBinary);
   }

   public int deleteEmrBinaryByIds(Long[] mrFileIds) {
      return this.emrBinaryMapper.deleteEmrBinaryByIds(mrFileIds);
   }

   public int deleteEmrBinaryById(Long mrFileId) {
      return this.emrBinaryMapper.deleteEmrBinaryById(mrFileId);
   }

   public String selectIndexXmlStrById(Long mrFileId) {
      EmrBinary emrBinary = this.selectEmrBinaryById(mrFileId);
      String mrCon = null;
      if (emrBinary != null && StringUtils.isNotEmpty(emrBinary.getMrCon())) {
         Map<String, String> map = (Map)JSON.parse(emrBinary.getMrCon());
         mrCon = (String)map.get("xmlStr");
      }

      return mrCon;
   }

   public String selectTempXmlStr(TmplIndex tmplIndexRes) {
      String tempFile = tmplIndexRes.getTempEditFile();
      String mrCon = null;
      if (StringUtils.isNotEmpty(tempFile)) {
         Map<String, String> map = (Map)JSON.parse(tempFile);
         mrCon = (String)map.get("xmlStr");
      }

      return mrCon;
   }

   public List selectListByIds(List mrFileIds) throws Exception {
      List<EmrBinary> list = mrFileIds != null && !mrFileIds.isEmpty() ? this.emrBinaryMapper.selectListByIds(mrFileIds) : null;
      return list;
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void addOrUpdateEmrBinary(Long mrFileId, Index index, SysUser updateUser, String mrCon, Date updateTime) {
      JSONObject jsonObject = new JSONObject();
      String mrContent = null;
      if (StringUtils.isNotBlank(mrCon)) {
         jsonObject = JSONObject.parseObject(mrCon);
      }

      if (StringUtils.isNotBlank(jsonObject.get("textStr").toString())) {
         mrContent = jsonObject.get("textStr").toString();
      } else if (StringUtils.isNotBlank(jsonObject.get("subFileTextStr").toString())) {
         mrContent = jsonObject.get("subFileTextStr").toString();
      }

      EmrBinary emrBinary = this.emrBinaryMapper.selectEmrBinaryById(mrFileId);
      if (emrBinary != null) {
         this.emrBinaryMapper.deleteEmrBinaryById(mrFileId);
         emrBinary.setAltPerCode(updateUser.getUserName());
         emrBinary.setAltPerName(updateUser.getNickName());
         emrBinary.setMrCon(mrCon);
         emrBinary.setAltDate(updateTime);
         emrBinary.setMrContent(mrContent);
         this.emrBinaryMapper.insertEmrBinary(emrBinary);
      } else {
         EmrBinary emrBinaryAdd = new EmrBinary();
         emrBinaryAdd.setMrFileId(mrFileId);
         emrBinaryAdd.setOrgCd(index.getOrgCd());
         emrBinaryAdd.setOrgName(index.getOrgName());
         emrBinaryAdd.setPatientId(index.getPatientId());
         emrBinaryAdd.setMrCon(mrCon);
         emrBinaryAdd.setMrContent(mrContent);
         emrBinaryAdd.setCrePerCode(updateUser.getUserName());
         emrBinaryAdd.setCrePerName(updateUser.getNickName());
         emrBinaryAdd.setCreDate(updateTime);
         this.emrBinaryMapper.insertEmrBinary(emrBinaryAdd);
      }

   }
}
