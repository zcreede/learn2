package com.emr.project.emr.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.common.utils.StringUtils;
import com.emr.project.emr.domain.EmrSearchCaseDetail;
import com.emr.project.emr.domain.EmrSearchHistory;
import com.emr.project.emr.domain.EmrSearchHistoryDetail;
import com.emr.project.emr.domain.vo.EmrSearchHistoryVo;
import com.emr.project.emr.mapper.EmrSearchHistoryMapper;
import com.emr.project.emr.service.IEmrSearchHistoryDetailService;
import com.emr.project.emr.service.IEmrSearchHistoryService;
import com.emr.project.esSearch.domain.vo.EmrFileSearchVo;
import com.emr.project.system.domain.BasEmployee;
import com.emr.project.system.domain.SysUser;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmrSearchHistoryServiceImpl implements IEmrSearchHistoryService {
   @Autowired
   private EmrSearchHistoryMapper emrSearchHistoryMapper;
   @Autowired
   private IEmrSearchHistoryDetailService emrSearchHistoryDetailService;

   public EmrSearchHistory selectEmrSearchHistoryById(Long id) {
      return this.emrSearchHistoryMapper.selectEmrSearchHistoryById(id);
   }

   public List selectEmrSearchHistoryList(EmrSearchHistory emrSearchHistory) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      emrSearchHistory.setEmplNumber(basEmployee.getEmplNumber());
      List<EmrSearchHistoryVo> list = this.emrSearchHistoryMapper.selectEmrSearchHistoryList(emrSearchHistory);
      EmrSearchHistoryDetail detail = new EmrSearchHistoryDetail();
      detail.setCrePerCode(basEmployee.getEmplNumber());
      List<EmrSearchHistoryDetail> detailList = this.emrSearchHistoryDetailService.selectEmrSearchHistoryDetailList(detail);
      if (list != null && !list.isEmpty()) {
         Map<Long, List<EmrSearchHistoryDetail>> detailMapList = (Map)detailList.stream().collect(Collectors.groupingBy((s) -> s.getHistoryId()));

         for(EmrSearchHistoryVo emrSearchHistoryVo : list) {
            List<EmrSearchHistoryDetail> detailList1 = (List)detailMapList.get(emrSearchHistoryVo.getId());
            if (detailList1 != null && !detailList1.isEmpty()) {
               Map<String, String> map = new HashMap();
               String keyStr = "";

               for(EmrSearchHistoryDetail historyDetail : detailList1) {
                  switch (historyDetail.getDetailCode()) {
                     case "6":
                        map.put(historyDetail.getDetailCode(), historyDetail.getNumMin() + "岁至" + historyDetail.getNumMax() + "岁 ");
                        break;
                     case "11":
                     case "12":
                     case "13":
                        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm");
                        map.put(historyDetail.getDetailCode(), "在" + sdf.format(historyDetail.getDateMin()) + "~" + sdf.format(historyDetail.getDateMax()) + "之间 ");
                        break;
                     case "1":
                        keyStr = keyStr + historyDetail.getText() + " ";
                        break;
                     default:
                        map.put(historyDetail.getDetailCode(), historyDetail.getText());
                  }
               }

               if (StringUtils.isNotEmpty(keyStr)) {
                  map.put("1", keyStr);
               }

               String str = JSONObject.toJSONString(map);
               emrSearchHistoryVo.setDescStr(str);
            }

            emrSearchHistoryVo.setDetailList(detailList1);
         }
      }

      return list;
   }

   public void insertEmrSearchHistory(EmrSearchHistory emrSearchHistory) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      emrSearchHistory.setEmplNumber(basEmployee.getEmplNumber());
      emrSearchHistory.setEmplName(basEmployee.getEmplName());
      emrSearchHistory.setCrePerCode(basEmployee.getEmplNumber());
      emrSearchHistory.setCrePerName(basEmployee.getEmplName());
      this.emrSearchHistoryMapper.insertEmrSearchHistory(emrSearchHistory);
   }

   private String getCaseDescStr(List emrSearchHistoryDetailList) {
      StringBuffer str = new StringBuffer("");

      for(EmrSearchCaseDetail detail : emrSearchHistoryDetailList) {
         str.append(detail.getDetailName() + ": ");
         switch (detail.getDetailCode()) {
            case "6":
               str.append(" " + detail.getNumMin() + "岁至" + detail.getNumMax() + "岁");
               break;
            case "11":
            case "12":
            case "13":
               SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm");
               str.append(" 在" + sdf.format(detail.getDateMin()) + "~" + sdf.format(detail.getDateMax()) + "之间");
               break;
            default:
               str.append(" " + detail.getText());
         }
      }

      return str.toString();
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void insertEmrSearchHistoryList(List emrSearchHistoryDetailList) throws Exception {
      Long id = SnowIdUtils.uniqueLong();
      EmrSearchHistory emrSearchHistory = new EmrSearchHistory();
      emrSearchHistory.setId(id);
      String desc = this.getCaseDescStr(emrSearchHistoryDetailList);
      emrSearchHistory.setCaseDesc(desc);
      this.insertEmrSearchHistory(emrSearchHistory);
      this.emrSearchHistoryDetailService.insertEmrSearchHistoryDetailList(emrSearchHistoryDetailList, id);
   }

   public int updateEmrSearchHistory(EmrSearchHistory emrSearchHistory) {
      return this.emrSearchHistoryMapper.updateEmrSearchHistory(emrSearchHistory);
   }

   public int deleteEmrSearchHistoryByIds(Long[] ids) {
      return this.emrSearchHistoryMapper.deleteEmrSearchHistoryByIds(ids);
   }

   public int deleteEmrSearchHistoryById(Long id) {
      return this.emrSearchHistoryMapper.deleteEmrSearchHistoryById(id);
   }

   public void saveEmrSearchHistory(EmrFileSearchVo emrFileSearchVo) throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      EmrSearchHistory emrSearchHistory = this.emrSearchHistoryMapper.selectEmrSearchHistoryLastInfo(sysUser.getUserName());
      if (StringUtils.isNotEmpty(emrFileSearchVo.getKeyWords())) {
         Long id = SnowIdUtils.uniqueLong();
         EmrSearchHistory insert = new EmrSearchHistory();
         String desc = this.getCaseDescStr(emrFileSearchVo.getDetailList());
         insert.setKeyWords(emrFileSearchVo.getKeyWords());
         insert.setCaseDesc(desc);
         if (!emrFileSearchVo.getKeyWords().equals(emrSearchHistory.getKeyWords())) {
            EmrSearchHistory firstInfo = this.emrSearchHistoryMapper.selectEmrSearchHistoryFirstInfo(sysUser.getUserName());
            EmrSearchHistory param = new EmrSearchHistory();
            param.setEmplNumber(sysUser.getUserName());
            List<EmrSearchHistoryVo> hisList = this.emrSearchHistoryMapper.selectEmrSearchHistoryList(param);
            if (hisList != null && hisList.size() >= 10) {
               this.emrSearchHistoryMapper.deleteEmrSearchHistoryById(firstInfo.getId());
               this.emrSearchHistoryDetailService.deleteEmrSearchHistoryDetailByHisId(firstInfo.getId());
            }

            insert.setId(id);
            this.insertEmrSearchHistory(insert);
         } else {
            id = emrSearchHistory.getId();
            insert.setId(id);
            this.updateEmrSearchHistory(insert);
            this.emrSearchHistoryDetailService.deleteEmrSearchHistoryDetailByHisId(id);
         }

         this.emrSearchHistoryDetailService.insertEmrSearchHistoryDetailList(emrFileSearchVo.getDetailList(), id);
      }

   }
}
