package com.emr.project.dr.service.impl;

import com.emr.common.constant.CommonConstants;
import com.emr.common.utils.DateUtils;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.framework.web.domain.TreeSelect;
import com.emr.project.dr.domain.DrHandoverScheme;
import com.emr.project.dr.domain.DrHandoverSchemeDept;
import com.emr.project.dr.domain.vo.DrHandoverSchemeVo;
import com.emr.project.dr.mapper.DrHandoverSchemeMapper;
import com.emr.project.dr.service.IDrHandoverSchemeDeptService;
import com.emr.project.dr.service.IDrHandoverSchemeService;
import com.emr.project.system.domain.BasEmployee;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DrHandoverSchemeServiceImpl implements IDrHandoverSchemeService {
   @Autowired
   private DrHandoverSchemeMapper drHandoverSchemeMapper;
   @Autowired
   private IDrHandoverSchemeDeptService handoverSchemeDeptService;

   public DrHandoverScheme selectDrHandoverSchemeById(Long schemeCd) {
      return this.drHandoverSchemeMapper.selectDrHandoverSchemeById(schemeCd);
   }

   public List selectDrHandoverSchemeList(DrHandoverScheme drHandoverScheme) {
      return this.drHandoverSchemeMapper.selectDrHandoverSchemeList(drHandoverScheme);
   }

   public List selectDrHandoverSchemeVoList(DrHandoverSchemeVo drHandoverSchemeVo) throws Exception {
      List<DrHandoverSchemeVo> list = this.drHandoverSchemeMapper.selectDrHandoverSchemeVoList(drHandoverSchemeVo);
      if (list != null && !list.isEmpty()) {
         List<Long> schemeCdList = (List)list.stream().map((t) -> t.getSchemeCd()).distinct().collect(Collectors.toList());
         List<DrHandoverSchemeDept> schemeDeptList = this.handoverSchemeDeptService.selectBySchemeCdList(schemeCdList);
         Map<Long, List<DrHandoverSchemeDept>> schemeDeptListMap = (Map)schemeDeptList.stream().collect(Collectors.groupingBy((t) -> t.getSchemeCd()));

         for(DrHandoverSchemeVo schemeVo : list) {
            Long schemeCd = schemeVo.getSchemeCd();
            List<DrHandoverSchemeDept> schemeDeptListTemp = (List)schemeDeptListMap.get(schemeCd);
            List<String> deptNameList = (List)schemeDeptListTemp.stream().map((t) -> t.getDeptName()).collect(Collectors.toList());
            String deptNames = String.join(",", deptNameList);
            schemeVo.setDeptNames(deptNames);
            schemeVo.setDepts(schemeDeptListTemp);
         }
      }

      return list;
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void insertDrHandoverScheme(DrHandoverSchemeVo drHandoverSchemeVo) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      drHandoverSchemeVo.setSchemeCd(SnowIdUtils.uniqueLong());
      drHandoverSchemeVo.setCrePerCode(basEmployee.getEmplNumber());
      drHandoverSchemeVo.setCrePerName(basEmployee.getEmplName());
      this.drHandoverSchemeMapper.insertDrHandoverScheme(drHandoverSchemeVo);
      List<TreeSelect> deptList = drHandoverSchemeVo.getDeptList();
      List<DrHandoverSchemeDept> schemeDeptList = new ArrayList(deptList.size());

      for(TreeSelect dept : deptList) {
         DrHandoverSchemeDept schemeDept = new DrHandoverSchemeDept();
         schemeDept.setId(SnowIdUtils.uniqueLong());
         schemeDept.setSchemeCd(drHandoverSchemeVo.getSchemeCd());
         schemeDept.setSchemeName(drHandoverSchemeVo.getSchemeName());
         schemeDept.setCrePerCode(basEmployee.getEmplNumber());
         schemeDept.setCrePerName(basEmployee.getEmplName());
         schemeDept.setDeptCd(dept.getCode());
         schemeDept.setDeptName(dept.getLabel());
         schemeDeptList.add(schemeDept);
      }

      if (!schemeDeptList.isEmpty()) {
         this.handoverSchemeDeptService.insertList(schemeDeptList);
      }

   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void updateDrHandoverScheme(DrHandoverSchemeVo drHandoverSchemeVo) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      drHandoverSchemeVo.setAltPerCode(basEmployee.getEmplNumber());
      drHandoverSchemeVo.setAltPerName(basEmployee.getEmplName());
      this.drHandoverSchemeMapper.updateDrHandoverScheme(drHandoverSchemeVo);
      List<Long> schemeCdList = Arrays.asList(drHandoverSchemeVo.getSchemeCd());
      this.handoverSchemeDeptService.deleteBySchemeCdList(schemeCdList);
      List<TreeSelect> deptList = drHandoverSchemeVo.getDeptList();
      List<DrHandoverSchemeDept> schemeDeptList = new ArrayList(deptList.size());

      for(TreeSelect dept : deptList) {
         DrHandoverSchemeDept schemeDept = new DrHandoverSchemeDept();
         schemeDept.setId(SnowIdUtils.uniqueLong());
         schemeDept.setSchemeCd(drHandoverSchemeVo.getSchemeCd());
         schemeDept.setSchemeName(drHandoverSchemeVo.getSchemeName());
         schemeDept.setCrePerCode(basEmployee.getEmplNumber());
         schemeDept.setCrePerName(basEmployee.getEmplName());
         schemeDept.setDeptCd(dept.getCode());
         schemeDept.setDeptName(dept.getLabel());
         schemeDeptList.add(schemeDept);
      }

      if (!schemeDeptList.isEmpty()) {
         this.handoverSchemeDeptService.insertList(schemeDeptList);
      }

   }

   public int deleteDrHandoverSchemeByIds(Long[] schemeCds) throws Exception {
      List<Long> schemeCdList = Arrays.asList(schemeCds);
      this.handoverSchemeDeptService.deleteBySchemeCdList(schemeCdList);
      return this.drHandoverSchemeMapper.deleteDrHandoverSchemeByIds(schemeCds);
   }

   public int deleteDrHandoverSchemeById(Long schemeCd) {
      return this.drHandoverSchemeMapper.deleteDrHandoverSchemeById(schemeCd);
   }

   public List selectListByCurrDept(String deptCd) throws Exception {
      DrHandoverSchemeVo drHandoverSchemeVo = new DrHandoverSchemeVo();
      drHandoverSchemeVo.setDeptCd(deptCd);
      return this.drHandoverSchemeMapper.selectListByDept(drHandoverSchemeVo);
   }

   public DrHandoverScheme getCurrHandoverScheme(String deptCd, Date currDate) throws Exception {
      DrHandoverScheme res = null;
      String currDateStr = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, currDate);
      String currDateHHStr = currDateStr.substring(11, 13);
      int currDateHH = Integer.valueOf(currDateHHStr);

      for(DrHandoverScheme handoverScheme : this.selectListByCurrDept(deptCd)) {
         String schemeBegin = handoverScheme.getSchemeBegin();
         String schemeEnd = handoverScheme.getSchemeEnd();
         String schemeBeginHHStr = schemeBegin.substring(0, 2);
         String schemeEndHHStr = schemeEnd.substring(0, 2);
         int schemeBeginHH = Integer.parseInt(schemeBeginHHStr);
         int schemeEndHH = Integer.parseInt(schemeEndHHStr);
         Boolean schemeBeginDayFlag = handoverScheme.getSchemeBeginDayFlag() != null && handoverScheme.getSchemeBeginDayFlag() == 1;
         Boolean schemeEndDayFlag = handoverScheme.getSchemeEndDayFlag() != null && handoverScheme.getSchemeEndDayFlag() == 1;
         if ((schemeBeginDayFlag && schemeEndDayFlag || !schemeBeginDayFlag && !schemeEndDayFlag) && currDateHH >= schemeBeginHH && currDateHH < schemeEndHH) {
            res = handoverScheme;
            break;
         }

         if (!schemeBeginDayFlag && schemeEndDayFlag) {
         }

         if (schemeBeginDayFlag && !schemeEndDayFlag && (currDateHH >= schemeBeginHH && currDateHH <= 24 || currDateHH >= 0 && currDateHH < schemeEndHH)) {
            res = handoverScheme;
            break;
         }
      }

      return res;
   }

   public String getschemeNameStr(DrHandoverScheme drHandoverScheme) throws Exception {
      String schemeName = "";
      schemeName = schemeName + (drHandoverScheme.getSchemeBeginDayFlag() == CommonConstants.HANDOVER.SCHEME_0 ? "今日" : "昨日");
      schemeName = schemeName + drHandoverScheme.getSchemeBegin() + "~";
      schemeName = schemeName + (drHandoverScheme.getSchemeEndDayFlag() == CommonConstants.HANDOVER.SCHEME_0 ? "今日" : "昨日");
      schemeName = schemeName + drHandoverScheme.getSchemeEnd() + " ";
      schemeName = schemeName + drHandoverScheme.getSchemeName();
      return schemeName;
   }
}
