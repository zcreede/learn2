package com.emr.project.tmpl.service.impl;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.project.system.domain.SysUser;
import com.emr.project.tmpl.domain.SubTmplText;
import com.emr.project.tmpl.mapper.SubTmplTextMapper;
import com.emr.project.tmpl.service.ISubTmplTextService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubTmplTextServiceImpl implements ISubTmplTextService {
   @Autowired
   private SubTmplTextMapper subTmplTextMapper;

   public SubTmplText selectSubTmplTextById(Long id) {
      return this.subTmplTextMapper.selectSubTmplTextById(id);
   }

   public List selectSubTmplTextList(SubTmplText subTmplText) throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      if (subTmplText.getSubTempType().equals("1")) {
         subTmplText.setSubTempObject(sysUser.getBasEmployee().getEmplNumber());
      } else {
         subTmplText.setSubTempObject(sysUser.getDept().getDeptCode());
      }

      List<SubTmplText> list = this.subTmplTextMapper.selectSubTmplTextList(subTmplText);
      if (list != null && !list.isEmpty()) {
         for(SubTmplText subTmplText1 : list) {
            String text = subTmplText1.getSubTempText().replaceAll("？", "");
            subTmplText1.setSubTempText(text);
         }
      }

      return list;
   }

   public void insertSubTmplText(SubTmplText subTmplText) throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      subTmplText.setId(SnowIdUtils.uniqueLong());
      if (subTmplText.getSubTempType().equals("1")) {
         subTmplText.setSubTempObject(sysUser.getBasEmployee().getEmplNumber());
      } else {
         subTmplText.setSubTempObject(sysUser.getDept().getDeptCode());
      }

      subTmplText.setCrePerCode(sysUser.getBasEmployee().getEmplNumber());
      subTmplText.setCrePerName(sysUser.getBasEmployee().getEmplName());
      this.subTmplTextMapper.insertSubTmplText(subTmplText);
   }

   public void updateSubTmplText(SubTmplText subTmplText) throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      if (subTmplText.getSubTempType().equals("1")) {
         subTmplText.setSubTempObject(sysUser.getBasEmployee().getEmplNumber());
      } else {
         subTmplText.setSubTempObject(sysUser.getDept().getDeptCode());
      }

      this.subTmplTextMapper.updateSubTmplText(subTmplText);
   }

   public void deleteSubTmplTextByIds(Long[] ids) throws Exception {
      this.subTmplTextMapper.deleteSubTmplTextByIds(ids);
   }

   public int deleteSubTmplTextById(Long id) {
      return this.subTmplTextMapper.deleteSubTmplTextById(id);
   }
}
