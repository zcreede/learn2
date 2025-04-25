package com.emr.project.report.controller;

import com.emr.framework.web.domain.AjaxResult;
import com.emr.project.report.domain.vo.ReportGrfReqParamVo;
import com.emr.project.report.service.IReportGrfService;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/reportGrf"})
public class ReportGrfController {
   private static Logger log = LoggerFactory.getLogger(ReportGrfController.class);
   @Autowired
   private IReportGrfService reportGrfService;

   @PreAuthorize("@ss.hasAnyPermi('reportGrf:fee:list')")
   @PostMapping({"/feeSummaryDayData"})
   public AjaxResult feeSummaryDayData(@RequestBody ReportGrfReqParamVo reportGrfReqParamVo) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         List<Map<String, Object>> data = this.reportGrfService.feeSummaryDayDataList(reportGrfReqParamVo);
         Map<String, Object> json = new LinkedHashMap();
         json.put("table", data);
         return AjaxResult.success((Object)json);
      } catch (Exception e) {
         log.error("查询患者费用汇总报表数据出现异常：", e);
         ajaxResult = AjaxResult.error("查询患者费用汇总报表数据出现异常");
         return ajaxResult;
      }
   }

   private StringBuffer getXmlText(List xmlData) {
      StringBuffer XmlText = new StringBuffer("<xml>\n");
      if (xmlData.size() > 0) {
         for(int i = 0; i < xmlData.size(); ++i) {
            XmlText.append("<row>");

            for(String key : ((Map)xmlData.get(i)).keySet()) {
               XmlText.append("<");
               XmlText.append(key);
               XmlText.append(">");
               XmlText.append(((Map)xmlData.get(i)).get(key));
               XmlText.append("</");
               XmlText.append(key);
               XmlText.append(">");
            }

            XmlText.append("</row>");
         }
      }

      XmlText.append("</xml>\n");
      return XmlText;
   }
}
