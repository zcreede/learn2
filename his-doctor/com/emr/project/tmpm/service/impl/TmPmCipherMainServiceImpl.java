package com.emr.project.tmpm.service.impl;

import com.emr.common.constant.CommonConstants;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.common.utils.StringUtils;
import com.emr.project.common.service.ICommonService;
import com.emr.project.docOrder.domain.TdPaOrderDetail;
import com.emr.project.docOrder.domain.vo.HerbSaveVo;
import com.emr.project.docOrder.util.OrderUtil;
import com.emr.project.system.domain.BasEmployee;
import com.emr.project.system.domain.SysUser;
import com.emr.project.tmpm.domain.ClinItemCollection;
import com.emr.project.tmpm.domain.TmPmCipherDetail;
import com.emr.project.tmpm.domain.TmPmCipherMain;
import com.emr.project.tmpm.domain.vo.TmPmCipherDetailVo;
import com.emr.project.tmpm.domain.vo.TmPmCipherMainVo;
import com.emr.project.tmpm.mapper.TmPmCipherMainMapper;
import com.emr.project.tmpm.service.IClinItemCollectionService;
import com.emr.project.tmpm.service.ITmPmCipherDetailService;
import com.emr.project.tmpm.service.ITmPmCipherMainService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TmPmCipherMainServiceImpl implements ITmPmCipherMainService {
   @Autowired
   private TmPmCipherMainMapper tmPmCipherMainMapper;
   @Autowired
   private ICommonService commonService;
   @Autowired
   private ITmPmCipherDetailService tmPmCipherDetailService;
   @Autowired
   private IClinItemCollectionService clinItemCollectionService;

   public TmPmCipherMainVo selectTmPmCipherMainById(String cipherCd) {
      return this.tmPmCipherMainMapper.selectTmPmCipherMainById(cipherCd);
   }

   public List selectTmPmCipherMainList(TmPmCipherMainVo tmPmCipherMain) {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      if (StringUtils.isNotBlank(tmPmCipherMain.getShareType())) {
         if (tmPmCipherMain.getShareType().equals("2")) {
            tmPmCipherMain.setShareObject(sysUser.getDept().getDeptCode());
         } else if (tmPmCipherMain.getShareType().equals("3")) {
            tmPmCipherMain.setShareObject(sysUser.getBasEmployee().getEmplNumber());
         }
      } else {
         tmPmCipherMain.setDeptCode(sysUser.getDept().getDeptCode());
         tmPmCipherMain.setDocCode(sysUser.getBasEmployee().getEmplNumber());
      }

      List<TmPmCipherMainVo> mainList = this.tmPmCipherMainMapper.selectTmPmCipherMainList(tmPmCipherMain);
      if (mainList != null && !mainList.isEmpty()) {
         BasEmployee basEmployee = sysUser.getBasEmployee();
         ClinItemCollection collection = new ClinItemCollection();
         collection.setDocCd(basEmployee.getEmplNumber());
         collection.setItemClassCd("09");
         List<ClinItemCollection> collectionList = this.clinItemCollectionService.selectClinItemCollectionList(collection);
         if (collectionList != null && !collectionList.isEmpty()) {
            List<String> cdList = (List)collectionList.stream().map((s) -> s.getItemCd()).collect(Collectors.toList());

            for(TmPmCipherMainVo tmPmCipherMainVo : mainList) {
               if (cdList.contains(tmPmCipherMainVo.getCipherCd())) {
                  tmPmCipherMainVo.setCollectFlag(CommonConstants.BOOL_TRUE);
               } else {
                  tmPmCipherMainVo.setCollectFlag(CommonConstants.BOOL_FALSE);
               }
            }
         } else {
            for(TmPmCipherMainVo tmPmCipherMainVo : mainList) {
               tmPmCipherMainVo.setCollectFlag(CommonConstants.BOOL_FALSE);
            }
         }
      }

      return mainList;
   }

   public List selectCipherMainList(TmPmCipherMain tmPmCipherMain) throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      if (tmPmCipherMain.getShareType().equals("2")) {
         tmPmCipherMain.setShareObject(sysUser.getDept().getDeptCode());
      } else if (tmPmCipherMain.getShareType().equals("3")) {
         tmPmCipherMain.setShareObject(sysUser.getBasEmployee().getEmplNumber());
      }

      return this.tmPmCipherMainMapper.selectTmPmCipherMainList(tmPmCipherMain);
   }

   public void insertTmPmCipherMain(TmPmCipherMain tmPmCipherMain) throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      BasEmployee basEmployee = sysUser.getBasEmployee();
      if (tmPmCipherMain.getShareType().equals("2")) {
         tmPmCipherMain.setShareObject(sysUser.getDept().getDeptCode());
      } else if (tmPmCipherMain.getShareType().equals("3")) {
         tmPmCipherMain.setShareObject(sysUser.getBasEmployee().getEmplNumber());
      } else {
         tmPmCipherMain.setShareObject("000000");
      }

      tmPmCipherMain.setHospitalCode(sysUser.getHospital().getOrgCode());
      tmPmCipherMain.setCipherCd(SnowIdUtils.uniqueLongHex());
      tmPmCipherMain.setCreDate(this.commonService.getDbSysdate());
      tmPmCipherMain.setCrePerCode(basEmployee.getEmplNumber());
      tmPmCipherMain.setCrePerName(basEmployee.getEmplName());
      this.tmPmCipherMainMapper.insertTmPmCipherMain(tmPmCipherMain);
   }

   public void updateTmPmCipherMain(TmPmCipherMain tmPmCipherMain) throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      BasEmployee basEmployee = sysUser.getBasEmployee();
      if (tmPmCipherMain.getShareType().equals("2")) {
         tmPmCipherMain.setShareObject(sysUser.getDept().getDeptCode());
      } else if (tmPmCipherMain.getShareType().equals("3")) {
         tmPmCipherMain.setShareObject(sysUser.getBasEmployee().getEmplNumber());
      } else {
         tmPmCipherMain.setShareObject("000000");
      }

      tmPmCipherMain.setAltDate(this.commonService.getDbSysdate());
      tmPmCipherMain.setAltPerCode(basEmployee.getEmplNumber());
      tmPmCipherMain.setAltPerName(basEmployee.getEmplName());
      this.tmPmCipherMainMapper.updateTmPmCipherMain(tmPmCipherMain);
   }

   public int deleteTmPmCipherMainByIds(String[] cipherCds) {
      return this.tmPmCipherMainMapper.deleteTmPmCipherMainByIds(cipherCds);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void deleteTmPmCipherMainById(String cipherCd) throws Exception {
      this.tmPmCipherDetailService.deleteTmPmCipherDetailByCipherCd(cipherCd);
      this.tmPmCipherMainMapper.deleteTmPmCipherMainById(cipherCd);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void saveTmPmCipherMain(HerbSaveVo herbSaveVo) throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      BasEmployee basEmployee = sysUser.getBasEmployee();
      TmPmCipherMain tmPmCipherMain = new TmPmCipherMain(herbSaveVo);
      String id = SnowIdUtils.uniqueLongHex();
      tmPmCipherMain.setCipherCd(id);
      tmPmCipherMain.setHospitalCode(sysUser.getHospital().getOrgCode());
      if (tmPmCipherMain.getShareType().equals("2")) {
         tmPmCipherMain.setShareObject(sysUser.getDept().getDeptCode());
      } else if (tmPmCipherMain.getShareType().equals("3")) {
         tmPmCipherMain.setShareObject(sysUser.getBasEmployee().getEmplNumber());
      } else {
         tmPmCipherMain.setShareObject("000000");
      }

      tmPmCipherMain.setEnabled("1");
      tmPmCipherMain.setCrePerCode(basEmployee.getEmplNumber());
      tmPmCipherMain.setCrePerName(basEmployee.getEmplName());
      List<TdPaOrderDetail> orderDetailList = herbSaveVo.getOrderDetailList();
      List<TmPmCipherDetail> detailList = new ArrayList();
      int sort = 1;

      for(TdPaOrderDetail tdPaOrderDetail : orderDetailList) {
         TmPmCipherDetail tmPmCipherDetail = new TmPmCipherDetail(tdPaOrderDetail);
         tmPmCipherDetail.setId(SnowIdUtils.uniqueLong());
         tmPmCipherDetail.setCipherCd(id);
         tmPmCipherDetail.setCipherSort(OrderUtil.getNumStr(sort++));
         tmPmCipherDetail.setDocNote(tdPaOrderDetail.getDoctorInstructions());
         tmPmCipherDetail.setExecDeptCd(herbSaveVo.getPerformDepCode());
         tmPmCipherDetail.setExecDeptName(herbSaveVo.getPerformDepName());
         tmPmCipherDetail.setCrePerCode(basEmployee.getEmplNumber());
         tmPmCipherDetail.setCrePerName(basEmployee.getEmplName());
         tmPmCipherDetail.setCreDate(this.commonService.getDbSysdate());
         detailList.add(tmPmCipherDetail);
      }

      this.tmPmCipherMainMapper.insertTmPmCipherMain(tmPmCipherMain);
      if (detailList != null && !detailList.isEmpty()) {
         this.tmPmCipherDetailService.insertTmPmCipherDetailList(detailList);
      }

   }

   public TmPmCipherMainVo selectCipHerInfoByCd(TmPmCipherMainVo tmPmCipherMainVo) throws Exception {
      TmPmCipherMainVo resultVo = this.tmPmCipherMainMapper.selectTmPmCipherMainById(tmPmCipherMainVo.getCipherCd());
      List<TmPmCipherDetailVo> detailVos = this.tmPmCipherDetailService.selectTmPmCipherDetailByCipherCd(tmPmCipherMainVo.getCipherCd());
      if (detailVos != null && !detailVos.isEmpty()) {
         List<Long> idList = (List)detailVos.stream().map((s) -> s.getId()).collect(Collectors.toList());
         TmPmCipherDetailVo param = new TmPmCipherDetailVo();
         param.setIdList(idList);
         param.setExecDeptCd(tmPmCipherMainVo.getDeptCode());
         detailVos = this.tmPmCipherDetailService.selectDetailDrugList(param);
      }

      resultVo.setDetailList(detailVos);
      return resultVo;
   }

   public void updateCipherMainFlag(TmPmCipherMain tmPmCipherMain) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      tmPmCipherMain.setAltDate(this.commonService.getDbSysdate());
      tmPmCipherMain.setAltPerCode(basEmployee.getEmplNumber());
      tmPmCipherMain.setAltPerName(basEmployee.getEmplName());
      this.tmPmCipherMainMapper.updateTmPmCipherMain(tmPmCipherMain);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public TmPmCipherMain saveAsCipherInfo(TmPmCipherMainVo tmPmCipherMainVo) throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      BasEmployee basEmployee = sysUser.getBasEmployee();
      List<TmPmCipherDetail> detailList = tmPmCipherMainVo.getInsertList();
      String id = SnowIdUtils.uniqueLongHex();
      TmPmCipherMain tmPmCipherMain = tmPmCipherMainVo.getSaveAsInfo();
      tmPmCipherMain.setCipherCd(id);
      tmPmCipherMain.setCreDate(this.commonService.getDbSysdate());
      tmPmCipherMain.setCrePerCode(basEmployee.getEmplNumber());
      tmPmCipherMain.setCrePerName(basEmployee.getEmplName());
      tmPmCipherMain.setCipherName(tmPmCipherMainVo.getCipherName());
      tmPmCipherMain.setInputstrphtc(tmPmCipherMainVo.getInputstrphtc());
      tmPmCipherMain.setShareType(tmPmCipherMainVo.getShareType());
      if (tmPmCipherMain.getShareType().equals("2")) {
         tmPmCipherMain.setShareObject(sysUser.getDept().getDeptCode());
      } else if (tmPmCipherMain.getShareType().equals("3")) {
         tmPmCipherMain.setShareObject(basEmployee.getEmplNumber());
      } else {
         tmPmCipherMain.setShareObject("000000");
      }

      tmPmCipherMain.setEnabled(tmPmCipherMainVo.getEnabled());
      this.tmPmCipherMainMapper.insertTmPmCipherMain(tmPmCipherMain);
      if (detailList != null && !detailList.isEmpty()) {
         Integer sort = 1;

         for(TmPmCipherDetail tmPmCipherDetail : detailList) {
            tmPmCipherDetail.setId(SnowIdUtils.uniqueLong());
            tmPmCipherDetail.setCipherCd(id);
            Integer var10 = sort;
            sort = sort + 1;
            tmPmCipherDetail.setCipherSort(OrderUtil.getNumStr(var10));
            tmPmCipherDetail.setCreDate(this.commonService.getDbSysdate());
            tmPmCipherDetail.setCrePerCode(basEmployee.getEmplNumber());
            tmPmCipherDetail.setCrePerName(basEmployee.getEmplName());
         }

         this.tmPmCipherDetailService.insertTmPmCipherDetailList(detailList);
      }

      return tmPmCipherMain;
   }
}
