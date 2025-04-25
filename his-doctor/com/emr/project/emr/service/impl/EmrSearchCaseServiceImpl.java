package com.emr.project.emr.service.impl;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.common.utils.StringUtils;
import com.emr.project.emr.domain.EmrSearchCase;
import com.emr.project.emr.domain.EmrSearchCaseDetail;
import com.emr.project.emr.domain.vo.EmrSearchCaseVo;
import com.emr.project.emr.mapper.EmrSearchCaseMapper;
import com.emr.project.emr.service.IEmrSearchCaseDetailService;
import com.emr.project.emr.service.IEmrSearchCaseService;
import com.emr.project.system.domain.BasEmployee;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmrSearchCaseServiceImpl implements IEmrSearchCaseService {
   @Autowired
   private EmrSearchCaseMapper emrSearchCaseMapper;
   @Autowired
   private IEmrSearchCaseDetailService emrSearchCaseDetailService;

   public EmrSearchCase selectEmrSearchCaseById(Long id) {
      return this.emrSearchCaseMapper.selectEmrSearchCaseById(id);
   }

   public List selectEmrSearchCaseList(EmrSearchCase emrSearchCase) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      emrSearchCase.setEmplNumber(basEmployee.getEmplNumber());
      return this.emrSearchCaseMapper.selectEmrSearchCaseList(emrSearchCase);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void insertEmrSearchCase(EmrSearchCaseVo emrSearchCaseVo) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      Long id = SnowIdUtils.uniqueLong();
      EmrSearchCase emrSearchCase = new EmrSearchCase();
      emrSearchCase.setCrePerCode(basEmployee.getEmplNumber());
      emrSearchCase.setCrePerName(basEmployee.getEmplName());
      emrSearchCase.setEmplNumber(basEmployee.getEmplNumber());
      emrSearchCase.setEmplName(basEmployee.getEmplName());
      emrSearchCase.setCaseName(emrSearchCaseVo.getCaseName());
      emrSearchCase.setId(id);
      String caseDescStr = this.getCaseDescStr(emrSearchCaseVo.getDetailList());
      emrSearchCase.setCaseDesc(caseDescStr);
      List<EmrSearchCaseDetail> filterList = (List)emrSearchCaseVo.getDetailList().stream().filter((t) -> !"1".equals(t.getDetailCode()) || StringUtils.isNotEmpty(t.getKeyWordCode())).collect(Collectors.toList());
      this.emrSearchCaseDetailService.insertEmrSearchCaseDetailList(filterList, id);
      this.emrSearchCaseMapper.insertEmrSearchCase(emrSearchCase);
   }

   private String getCaseDescStr(List detailList) {
      StringBuffer str = new StringBuffer("");

      for(EmrSearchCaseDetail detail : detailList) {
         str.append(detail.getDetailName() + ": ");
         switch (detail.getDetailCode()) {
            case "6":
               str.append(detail.getNumMin() + "岁至" + detail.getNumMax() + "岁 ");
               break;
            case "11":
            case "12":
            case "13":
               SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm");
               str.append("在" + sdf.format(detail.getDateMin()) + "~" + sdf.format(detail.getDateMax()) + "之间 ");
               break;
            default:
               str.append(detail.getText());
         }
      }

      return str.toString();
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void updateEmrSearchCase(EmrSearchCaseVo emrSearchCaseVo) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      this.emrSearchCaseDetailService.deleteEmrSearchCaseDetailByCaseId(emrSearchCaseVo.getId());
      emrSearchCaseVo.setAltPerCode(basEmployee.getEmplNumber());
      emrSearchCaseVo.setAltPerName(basEmployee.getEmplName());
      String caseDescStr = this.getCaseDescStr(emrSearchCaseVo.getDetailList());
      emrSearchCaseVo.setCaseDesc(caseDescStr);
      this.emrSearchCaseDetailService.insertEmrSearchCaseDetailList(emrSearchCaseVo.getDetailList(), emrSearchCaseVo.getId());
      this.emrSearchCaseMapper.updateEmrSearchCase(emrSearchCaseVo);
   }

   public int deleteEmrSearchCaseByIds(Long[] ids) {
      return this.emrSearchCaseMapper.deleteEmrSearchCaseByIds(ids);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void deleteEmrSearchCaseById(Long id) throws Exception {
      this.emrSearchCaseDetailService.deleteEmrSearchCaseDetailByCaseId(id);
      this.emrSearchCaseMapper.deleteEmrSearchCaseById(id);
   }
}
