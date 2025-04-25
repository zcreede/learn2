package com.emr.project.emr.service.impl;

import com.emr.common.constant.CommonConstants;
import com.emr.common.utils.DateUtils;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.project.emr.domain.BasEmrAcceAuthor;
import com.emr.project.emr.domain.vo.AcceRoleVo;
import com.emr.project.emr.domain.vo.EmrAcceVo;
import com.emr.project.emr.mapper.BasEmrAcceAuthorMapper;
import com.emr.project.emr.service.IBasEmrAcceAuthorService;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.mapper.SysRoleMapper;
import com.emr.project.system.mapper.SysUserMapper;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BasEmrAcceAuthorServiceImpl implements IBasEmrAcceAuthorService {
   @Autowired
   private BasEmrAcceAuthorMapper basEmrAcceAuthorMapper;
   @Resource
   private SysUserMapper sysUserMapper;
   @Resource
   private SysRoleMapper sysRoleMapper;

   public BasEmrAcceAuthor selectBasEmrAcceAuthorById(Long id) {
      return this.basEmrAcceAuthorMapper.selectBasEmrAcceAuthorById(id);
   }

   public BasEmrAcceAuthor selectBasEmrAcceAuthorByObjectId(String objectId) {
      return this.basEmrAcceAuthorMapper.selectBasEmrAcceAuthorByObjectId(objectId);
   }

   public List selectBasEmrAcceAuthorByObjectIds(List list) {
      return this.basEmrAcceAuthorMapper.selectBasEmrAcceAuthorByObjectIds(list);
   }

   public List selectBasEmrAcceAuthorList(BasEmrAcceAuthor basEmrAcceAuthor) {
      return this.basEmrAcceAuthorMapper.selectBasEmrAcceAuthorList(basEmrAcceAuthor);
   }

   public int insertBasEmrAcceAuthor(BasEmrAcceAuthor basEmrAcceAuthor) {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      basEmrAcceAuthor.setId(SnowIdUtils.uniqueLong());
      basEmrAcceAuthor.setCrePerCode(user.getUserName());
      basEmrAcceAuthor.setCrePerName(user.getNickName());
      basEmrAcceAuthor.setAltPerCode(user.getUserName());
      basEmrAcceAuthor.setAltPerName(user.getNickName());
      return this.basEmrAcceAuthorMapper.insertBasEmrAcceAuthor(basEmrAcceAuthor);
   }

   public int updateBasEmrAcceAuthor(BasEmrAcceAuthor basEmrAcceAuthor) {
      return this.basEmrAcceAuthorMapper.updateBasEmrAcceAuthor(basEmrAcceAuthor);
   }

   public int deleteBasEmrAcceAuthorByIds(Long[] ids) {
      return this.basEmrAcceAuthorMapper.deleteBasEmrAcceAuthorByIds(ids);
   }

   public int deleteBasEmrAcceAuthorById(Long id) {
      return this.basEmrAcceAuthorMapper.deleteBasEmrAcceAuthorById(id);
   }

   public void editAcce(EmrAcceVo emrAcceVo) throws Exception {
      Integer roleOrUser = emrAcceVo.getRoleOrUser();
      String[] ids = emrAcceVo.getIds();
      String authorLevel = emrAcceVo.getAuthorLevel();
      SysUser user = SecurityUtils.getLoginUser().getUser();

      for(String id : ids) {
         BasEmrAcceAuthor beaa = this.basEmrAcceAuthorMapper.selectBasEmrAcceAuthorByObjectId(id);
         if (beaa == null) {
            BasEmrAcceAuthor author = new BasEmrAcceAuthor();
            author.setId(SnowIdUtils.uniqueLong());
            author.setCrePerCode(user.getUserName());
            author.setCrePerName(user.getNickName());
            author.setCreDate(DateUtils.getNowDate());
            author.setAltPerCode(user.getUserName());
            author.setAltPerName(user.getNickName());
            author.setAltDate(DateUtils.getNowDate());
            author.setAuthorLevel(authorLevel);
            author.setObjectId(id);
            author.setDelFlag("0");
            if (roleOrUser.equals(1)) {
               author.setObjectType(CommonConstants.BAS_EMR_ACCE_AUTHOR.OBJECT_TYPE_1);
            }

            if (roleOrUser.equals(2)) {
               author.setObjectType(CommonConstants.BAS_EMR_ACCE_AUTHOR.OBJECT_TYPE_2);
            }

            this.basEmrAcceAuthorMapper.insertBasEmrAcceAuthor(author);
         }

         if (beaa != null) {
            beaa.setAltPerCode(user.getUserName());
            beaa.setAltPerName(user.getNickName());
            beaa.setAltDate(DateUtils.getNowDate());
            beaa.setAuthorLevel(authorLevel);
            this.basEmrAcceAuthorMapper.updateBasEmrAcceAuthor(beaa);
         }
      }

   }

   public List selectRoleAcceList(AcceRoleVo acceRoleVo) {
      return this.sysRoleMapper.selectRoleAcceList(acceRoleVo);
   }

   public List selectUserAcceList(SysUser sysUser) throws Exception {
      return this.sysUserMapper.selectUserAuthorList(sysUser);
   }
}
