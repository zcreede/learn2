package com.emr.project.mrhp.service.impl;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.project.common.service.ICommonService;
import com.emr.project.mrhp.domain.MrHpDrawApi;
import com.emr.project.mrhp.domain.MrHpDrawField;
import com.emr.project.mrhp.domain.MrHpDrawMain;
import com.emr.project.mrhp.domain.req.MrHpDrawMainReq;
import com.emr.project.mrhp.domain.resp.MrHpDrawMainResp;
import com.emr.project.mrhp.mapper.MrHpDrawMainMapper;
import com.emr.project.mrhp.service.IMrHpDrawApiService;
import com.emr.project.mrhp.service.IMrHpDrawFieldService;
import com.emr.project.mrhp.service.IMrHpDrawMainService;
import com.emr.project.system.domain.SysUser;
import com.emr.project.webservice.domain.resp.WebServiceGeneralResp;
import com.emr.project.webservice.service.GeneralWebService;
import java.util.Date;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MrHpDrawMainServiceImpl implements IMrHpDrawMainService {
   @Autowired
   private MrHpDrawMainMapper mrHpDrawMainMapper;
   @Autowired
   private IMrHpDrawFieldService mrHpDrawFieldService;
   @Autowired
   private IMrHpDrawApiService mrHpDrawApiService;
   @Autowired
   private ICommonService commonService;
   @Autowired
   private GeneralWebService generalWebService;

   public MrHpDrawMainResp selectMrHpDrawMainById(Long id) {
      MrHpDrawMainResp resp = new MrHpDrawMainResp();
      MrHpDrawMain mrHpDrawMain = this.mrHpDrawMainMapper.selectMrHpDrawMainById(id);
      if (mrHpDrawMain != null) {
         BeanUtils.copyProperties(mrHpDrawMain, resp);
         MrHpDrawField mrHpDrawField = new MrHpDrawField();
         mrHpDrawField.setMainId(id);
         List<MrHpDrawField> mrHpDrawFieldList = this.mrHpDrawFieldService.selectMrHpDrawFieldList(mrHpDrawField);
         resp.setMrHpDrawFieldList(mrHpDrawFieldList);
         MrHpDrawApi mrHpDrawApi = new MrHpDrawApi();
         mrHpDrawApi.setMainId(id);
         List<MrHpDrawApi> mrHpDrawApiList = this.mrHpDrawApiService.selectMrHpDrawApiList(mrHpDrawApi);
         resp.setMrHpDrawApiList(mrHpDrawApiList);
      }

      return resp;
   }

   public List selectMrHpDrawMainList(MrHpDrawMain mrHpDrawMain) {
      return this.mrHpDrawMainMapper.selectMrHpDrawMainList(mrHpDrawMain);
   }

   public int insertMrHpDrawMain(MrHpDrawMain mrHpDrawMain) {
      return this.mrHpDrawMainMapper.insertMrHpDrawMain(mrHpDrawMain);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public int addMrHpDrawMain(MrHpDrawMainReq mrHpDrawMainReq) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      Date curDate = this.commonService.getDbSysdate();
      List<MrHpDrawApi> mrHpDrawApiList = mrHpDrawMainReq.getMrHpDrawApiList();
      List<MrHpDrawField> mrHpDrawFieldList = mrHpDrawMainReq.getMrHpDrawFieldList();
      if (mrHpDrawMainReq.getId() != null) {
         this.mrHpDrawApiService.deleteMrHpDrawApiById(mrHpDrawMainReq.getId());
         this.mrHpDrawFieldService.deleteMrHpDrawFieldByMainId(mrHpDrawMainReq.getId());
         if (mrHpDrawApiList != null && CollectionUtils.isNotEmpty(mrHpDrawApiList) && mrHpDrawApiList.size() > 0) {
            for(MrHpDrawApi api : mrHpDrawApiList) {
               api.setId(SnowIdUtils.uniqueLong());
               api.setMainId(mrHpDrawMainReq.getId());
            }

            this.mrHpDrawApiService.batchInsert(mrHpDrawApiList);
         }

         if (mrHpDrawFieldList != null && CollectionUtils.isNotEmpty(mrHpDrawFieldList) && mrHpDrawFieldList.size() > 0) {
            for(MrHpDrawField field : mrHpDrawFieldList) {
               field.setId(SnowIdUtils.uniqueLong());
               field.setMainId(mrHpDrawMainReq.getId());
            }

            this.mrHpDrawFieldService.batchInsert(mrHpDrawFieldList);
         }

         MrHpDrawMain mrHpDrawMain = new MrHpDrawMain();
         BeanUtils.copyProperties(mrHpDrawMainReq, mrHpDrawMain);
         mrHpDrawMain.setAltDate(curDate);
         mrHpDrawMain.setAltPerCode(user.getUserName());
         mrHpDrawMain.setAltPerName(user.getNickName());
         this.mrHpDrawMainMapper.updateMrHpDrawMain(mrHpDrawMainReq);
      } else {
         Long id = SnowIdUtils.uniqueLong();
         if (mrHpDrawApiList != null && CollectionUtils.isNotEmpty(mrHpDrawApiList) && mrHpDrawApiList.size() > 0) {
            for(MrHpDrawApi api : mrHpDrawApiList) {
               api.setId(SnowIdUtils.uniqueLong());
               api.setMainId(id);
            }

            this.mrHpDrawApiService.batchInsert(mrHpDrawApiList);
         }

         if (mrHpDrawFieldList != null && CollectionUtils.isNotEmpty(mrHpDrawFieldList) && mrHpDrawFieldList.size() > 0) {
            for(MrHpDrawField field : mrHpDrawFieldList) {
               field.setId(SnowIdUtils.uniqueLong());
               field.setMainId(id);
            }

            this.mrHpDrawFieldService.batchInsert(mrHpDrawFieldList);
         }

         MrHpDrawMain mrHpDrawMain = new MrHpDrawMain();
         BeanUtils.copyProperties(mrHpDrawMainReq, mrHpDrawMain);
         mrHpDrawMain.setId(id);
         mrHpDrawMain.setCreDate(curDate);
         mrHpDrawMain.setCrePerCode(user.getUserName());
         mrHpDrawMain.setCrePerName(user.getNickName());
         this.mrHpDrawMainMapper.insertMrHpDrawMain(mrHpDrawMain);
      }

      return 1;
   }

   public int updateMrHpDrawMain(MrHpDrawMain mrHpDrawMain) {
      return this.mrHpDrawMainMapper.updateMrHpDrawMain(mrHpDrawMain);
   }

   public int deleteMrHpDrawMainByIds(Long[] ids) {
      return this.mrHpDrawMainMapper.deleteMrHpDrawMainByIds(ids);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public int deleteMrHpDrawMainById(Long id) {
      this.mrHpDrawMainMapper.deleteMrHpDrawMainById(id);
      this.mrHpDrawApiService.deleteMrHpDrawApiByMainId(id);
      this.mrHpDrawFieldService.deleteMrHpDrawFieldByMainId(id);
      return 1;
   }

   public WebServiceGeneralResp testApiConn(MrHpDrawMainReq mrHpDrawMainReq) throws Exception {
      WebServiceGeneralResp webServiceGeneralResp = this.generalWebService.getVteInfoWebApi(mrHpDrawMainReq);
      return webServiceGeneralResp;
   }
}
