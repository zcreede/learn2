package com.emr.project.mrhp.service.impl;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.project.mrhp.domain.TmRdDefine;
import com.emr.project.mrhp.mapper.TmRdDefineMapper;
import com.emr.project.mrhp.service.ITmRdDefineService;
import com.emr.project.system.domain.SysUser;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TmRdDefineServiceImpl implements ITmRdDefineService {
   protected final Logger logger = LoggerFactory.getLogger(this.getClass());
   @Autowired
   private TmRdDefineMapper tmRdDefineMapper;

   public TmRdDefine selectTmRdDefineById(Long id) {
      return this.tmRdDefineMapper.selectTmRdDefineById(id);
   }

   public List selectTmRdDefineList(TmRdDefine tmRdDefine) throws Exception {
      List<TmRdDefine> tmRdDefineList = new ArrayList();

      try {
         tmRdDefineList = this.tmRdDefineMapper.selectTmRdDefineList(tmRdDefine);
      } catch (Exception var4) {
         this.logger.error("查询数据上报定义出现异常");
      }

      return tmRdDefineList;
   }

   public int insertTmRdDefine(TmRdDefine tmRdDefine) {
      tmRdDefine.setId(SnowIdUtils.uniqueLong());
      SysUser user = SecurityUtils.getLoginUser().getUser();
      tmRdDefine.setCrePerCode(user.getUserName());
      tmRdDefine.setCrePerName(user.getNickName());
      return this.tmRdDefineMapper.insertTmRdDefine(tmRdDefine);
   }

   public int updateTmRdDefine(TmRdDefine tmRdDefine) {
      return this.tmRdDefineMapper.updateTmRdDefine(tmRdDefine);
   }

   public int deleteTmRdDefineByIds(Long[] ids) {
      return this.tmRdDefineMapper.deleteTmRdDefineByIds(ids);
   }

   public int deleteTmRdDefineById(Long id) {
      return this.tmRdDefineMapper.deleteTmRdDefineById(id);
   }
}
