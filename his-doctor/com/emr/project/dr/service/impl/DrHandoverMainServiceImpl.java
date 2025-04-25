package com.emr.project.dr.service.impl;

import com.emr.common.utils.DateUtils;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.project.common.service.ICommonService;
import com.emr.project.dr.domain.DrHandoverMain;
import com.emr.project.dr.domain.DrHandoverScheme;
import com.emr.project.dr.domain.vo.DrHandoverDetailVo;
import com.emr.project.dr.domain.vo.DrHandoverMainVo;
import com.emr.project.dr.mapper.DrHandoverMainMapper;
import com.emr.project.dr.service.IDrHandoverDetailService;
import com.emr.project.dr.service.IDrHandoverMainService;
import com.emr.project.dr.service.IDrHandoverSchemeService;
import com.emr.project.pat.domain.BabyInfo;
import com.emr.project.pat.service.IBabyInfoService;
import com.emr.project.system.domain.BasEmployee;
import com.emr.project.system.domain.SysUser;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DrHandoverMainServiceImpl implements IDrHandoverMainService {
   @Autowired
   private DrHandoverMainMapper drHandoverMainMapper;
   @Autowired
   private IDrHandoverSchemeService handoverSchemeService;
   @Autowired
   private ICommonService commonService;
   @Autowired
   private IDrHandoverDetailService drHandoverDetailService;
   @Autowired
   private IBabyInfoService babyInfoService;

   public DrHandoverMain selectDrHandoverMainById(Long id) {
      return this.drHandoverMainMapper.selectDrHandoverMainById(id);
   }

   public List selectDrHandoverMainList(DrHandoverMainVo drHandoverMainVo) {
      return this.drHandoverMainMapper.selectDrHandoverMainList(drHandoverMainVo);
   }

   public int insertDrHandoverMain(DrHandoverMain drHandoverMain) throws Exception {
      DrHandoverScheme drHandoverScheme = this.handoverSchemeService.selectDrHandoverSchemeById(drHandoverMain.getSchemeCd());
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      SysUser user = SecurityUtils.getLoginUser().getUser();
      drHandoverMain.setId(SnowIdUtils.uniqueLong());
      String handoverDateStr = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, drHandoverMain.getHandoverDate());
      String yesterdayStr = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, org.apache.commons.lang3.time.DateUtils.addDays(drHandoverMain.getHandoverDate(), -1));
      String schemeBeginStr = drHandoverScheme.getSchemeBeginDayFlag().equals(1) ? yesterdayStr + " " + drHandoverScheme.getSchemeBegin() + ":00" : handoverDateStr + " " + drHandoverScheme.getSchemeBegin() + ":00";
      String schemeEndStr = drHandoverScheme.getSchemeEndDayFlag().equals(1) ? yesterdayStr + " " + drHandoverScheme.getSchemeEnd() + ":00" : handoverDateStr + " " + drHandoverScheme.getSchemeEnd() + ":00";
      Date scheBegin = DateUtils.parseDate(schemeBeginStr, new String[]{DateUtils.YYYY_MM_DD_HH_MM_SS});
      Date schemeEnd = DateUtils.parseDate(schemeEndStr, new String[]{DateUtils.YYYY_MM_DD_HH_MM_SS});
      drHandoverMain.setSchemeBegin(scheBegin);
      drHandoverMain.setSchemeEnd(schemeEnd);
      drHandoverMain.setShiftDocCd(basEmployee.getEmplNumber());
      drHandoverMain.setShiftDocName(basEmployee.getEmplName());
      drHandoverMain.setState("0");
      drHandoverMain.setCrePerCode(basEmployee.getEmplNumber());
      drHandoverMain.setCrePerName(basEmployee.getEmplName());
      drHandoverMain.setDelFlag("0");
      BabyInfo babyInfo = new BabyInfo();
      babyInfo.setBeginTime(scheBegin);
      babyInfo.setEndTime(schemeEnd);
      babyInfo.setWardNo(user.getDept().getDeptCode());
      List<BabyInfo> babyInfoList = this.babyInfoService.selectBabyInfoByDeptCodeList(babyInfo);
      List<BabyInfo> groupByPat = this.babyInfoService.selectBabyInfoByDeptCodeListGroupByPat(babyInfo);
      Integer babyNum = babyInfoList == null ? 0 : babyInfoList.size();
      Integer partNum = groupByPat == null ? 0 : groupByPat.size();
      drHandoverMain.setBabyNum(babyNum);
      drHandoverMain.setPartNum(partNum);
      DrHandoverMain mainParam = new DrHandoverMain();
      mainParam.setDeptCd(drHandoverMain.getDeptCd());
      mainParam.setState("1");
      DrHandoverMainVo drHandoverMainVo = this.drHandoverMainMapper.selectDeptLastDrHandoverMain(mainParam);
      Integer beforeNum = 0;
      if (drHandoverMainVo != null) {
         List<DrHandoverDetailVo> drHandoverDetailList = this.drHandoverDetailService.selectDetailListByMainId(drHandoverMainVo.getId());
         beforeNum = drHandoverDetailList != null ? drHandoverDetailList.size() : 0;
      }

      drHandoverMain.setOriginalNum(beforeNum);
      drHandoverMain.setNowNum(beforeNum);
      return this.drHandoverMainMapper.insertDrHandoverMain(drHandoverMain);
   }

   public int updateDrHandoverMain(DrHandoverMain drHandoverMain) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      drHandoverMain.setAltPerCode(basEmployee.getEmplNumber());
      drHandoverMain.setAltPerName(basEmployee.getEmplName());
      return this.drHandoverMainMapper.updateDrHandoverMain(drHandoverMain);
   }

   public int deleteDrHandoverMainByIds(Long[] ids) throws Exception {
      return this.drHandoverMainMapper.deleteDrHandoverMainByIds(ids);
   }

   public int deleteDrHandoverMainById(Long id) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      DrHandoverMain drHandoverMain = new DrHandoverMain();
      drHandoverMain.setId(id);
      drHandoverMain.setAltPerCode(basEmployee.getEmplNumber());
      drHandoverMain.setAltPerName(basEmployee.getEmplName());
      return this.drHandoverMainMapper.deleteDrHandoverMain(drHandoverMain);
   }

   public DrHandoverMainVo selectHandoverByInfo(String deptCd, Date handoverDate, Long schemeCd) throws Exception {
      DrHandoverMainVo res = new DrHandoverMainVo();
      DrHandoverMain param = new DrHandoverMain();
      param.setDeptCd(deptCd);
      param.setSchemeCd(schemeCd);
      param.setHandoverDate(handoverDate);
      DrHandoverMain drHandoverMain = this.drHandoverMainMapper.selectHandoverByInfo(param);
      if (drHandoverMain != null) {
         BeanUtils.copyProperties(drHandoverMain, res);
         res.setStateName(res.getState().equals("0") ? "未交班" : "已交班");
      } else {
         res.setStateName("未创建");
         res.setState("2");
      }

      return res;
   }

   public void updateState(DrHandoverMain drHandoverMain) throws Exception {
      this.drHandoverMainMapper.updateState(drHandoverMain);
   }

   public DrHandoverMainVo selectLastDrHandoverMain(String deptCd) throws Exception {
      DrHandoverMain mainParam = new DrHandoverMain();
      mainParam.setDeptCd(deptCd);
      mainParam.setState("0");
      DrHandoverMainVo drHandoverMain = this.drHandoverMainMapper.selectDeptLastDrHandoverMain(mainParam);
      if (drHandoverMain == null) {
         Date currDate = this.commonService.getDbSysdate();
         Date handoverDate = DateUtils.parseDate(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, currDate), new String[]{DateUtils.YYYY_MM_DD});
         DrHandoverMainVo param = new DrHandoverMainVo();
         param.setDeptCd(deptCd);
         List<DrHandoverMainVo> drHandoverMainList = this.drHandoverMainMapper.selectDrHandoverMainList(param);
         if (drHandoverMainList != null && !drHandoverMainList.isEmpty()) {
            drHandoverMain = (DrHandoverMainVo)drHandoverMainList.get(0);
            drHandoverMain.setStateName("已交班");
         } else {
            drHandoverMain = new DrHandoverMainVo();
            List<DrHandoverScheme> schemeList = this.handoverSchemeService.selectListByCurrDept(deptCd);
            if (schemeList != null && !schemeList.isEmpty()) {
               drHandoverMain.setSchemeCd(((DrHandoverScheme)schemeList.get(0)).getSchemeCd());
               drHandoverMain.setSchemeName(((DrHandoverScheme)schemeList.get(0)).getSchemeName());
            }

            drHandoverMain.setStateName("未创建");
            drHandoverMain.setState("2");
            drHandoverMain.setHandoverDate(handoverDate);
         }
      } else {
         drHandoverMain.setStateName("未交班");
      }

      return drHandoverMain;
   }

   public List selectNoDrHandoverMain(String deptCd) throws Exception {
      DrHandoverMain param = new DrHandoverMain();
      param.setDeptCd(deptCd);
      param.setState("0");
      List<DrHandoverMain> list = this.drHandoverMainMapper.selectDeptNoDrHandoverMain(param);
      return list;
   }

   public List selectRecentRecordList(DrHandoverMain drHandoverMain) throws Exception {
      List<DrHandoverMainVo> handoverMainVoList = this.drHandoverMainMapper.selectRecentRecordList(drHandoverMain);
      List<DrHandoverScheme> drHandoverSchemeList = this.handoverSchemeService.selectDrHandoverSchemeList(new DrHandoverScheme());
      if (handoverMainVoList != null && !handoverMainVoList.isEmpty()) {
         for(DrHandoverMainVo drHandoverMainVo : handoverMainVoList) {
            List<DrHandoverScheme> handoverSchemes = (List)drHandoverSchemeList.stream().filter((s) -> s.getSchemeCd().equals(drHandoverMainVo.getSchemeCd())).collect(Collectors.toList());
            if (handoverSchemes != null && !handoverSchemes.isEmpty()) {
               String schemeName = this.handoverSchemeService.getschemeNameStr((DrHandoverScheme)handoverSchemes.get(0));
               drHandoverMainVo.setSchemeNameStr(schemeName);
            }

            if (drHandoverMainVo.getState().equals("0")) {
               drHandoverMainVo.setStateName("未交班");
            } else if (drHandoverMainVo.getState().equals("1")) {
               drHandoverMainVo.setStateName("已交班");
            }
         }
      }

      return handoverMainVoList;
   }

   public List selectDoverMainRecordList(DrHandoverMainVo drHandoverMainVo) throws Exception {
      drHandoverMainVo.setDelFlag("0");
      List<DrHandoverMainVo> list = this.drHandoverMainMapper.selectDrHandoverMainList(drHandoverMainVo);
      return list;
   }

   public Boolean checkCancel(DrHandoverMain drHandoverMain) {
      drHandoverMain.setDelFlag("0");
      drHandoverMain.setState("1");
      drHandoverMain.setDeptCd(SecurityUtils.getLoginUser().getUser().getDept().getDeptCode());
      Integer count = this.drHandoverMainMapper.checkCancel(drHandoverMain);
      return count > 0 ? Boolean.TRUE : Boolean.FALSE;
   }

   public List selectDrHandoverMainBySchemeCd(Long schemeCd, String deptCd, Date handoverDate) {
      DrHandoverMainVo drHandoverMain = new DrHandoverMainVo();
      drHandoverMain.setDelFlag("0");
      drHandoverMain.setSchemeCd(schemeCd);
      drHandoverMain.setDeptCd(deptCd);
      drHandoverMain.setHandoverDateTime(handoverDate);
      return this.drHandoverMainMapper.selectDrHandoverMainList(drHandoverMain);
   }
}
