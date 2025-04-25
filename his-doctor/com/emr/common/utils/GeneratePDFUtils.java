package com.emr.common.utils;

import com.emr.grid.enums.ExportType;
import com.emr.grid.enums.GridVersion;
import com.emr.grid.service.RubylongCore;
import com.emr.grid.service.impl.RubylongCoreJacobImp;
import com.emr.project.tmpm.domain.PrintTmplInfo;
import com.emr.project.tmpm.service.IPrintTmplInfoService;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GeneratePDFUtils {
   private static final Logger log = LoggerFactory.getLogger(GeneratePDFUtils.class);
   private RubylongCore rlc;
   @Value("${spring.grid.path}")
   private String generatePdfUrl;
   @Autowired
   private IPrintTmplInfoService printTmplInfoService;

   public String generateReportPdf(String patientId, String typeCode, List xmlData, String pdfFile, Boolean isBaby) {
      String filePath = null;

      try {
         if (CollectionUtils.isNotEmpty(xmlData) && xmlData.size() > 0) {
            this.rlc = RubylongCoreJacobImp.getRubylongCore(GridVersion.V6_5);
            CountDownLatch countDownLatch = new CountDownLatch(1);

            try {
               countDownLatch.countDown();
               PrintTmplInfo printTemplate = this.printTmplInfoService.selectTmPmPrintTmplInfoByCode(typeCode);
               if (CollectionUtils.isNotEmpty(xmlData) && xmlData.size() > 0) {
                  String dataXml = this.getDataXml(xmlData, printTemplate);
                  String grfUrl = printTemplate.getTmplFilePath();
                  this.rlc.fnLoadFromFile(grfUrl);
                  this.rlc.fnLoadDataFromXML(dataXml);
                  String typeName = printTemplate.getTypeName();
                  if (isBaby) {
                     typeName = "儿童" + typeName;
                  }

                  filePath = this.generatePdfUrl + "\\" + pdfFile + "\\" + patientId + "\\" + typeName + DateUtils.dateFormatS(DateUtils.getNowDate(), DateUtils.YYYYMMDDHHMMSS) + ".pdf";
                  this.rlc.fnExportDirect(ExportType.gretPDF, filePath, false, false);
               }
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      } catch (Exception e) {
         e.printStackTrace();
         System.out.println("生成pdf失败，msg--->" + e.getMessage());
         log.info("生成pdf失败，msg--->" + e.getMessage());
      }

      return filePath;
   }

   public String getDataXml(List xmlData, PrintTmplInfo printTemplate) {
      StringBuffer XmlText = new StringBuffer("<xml>");
      String fielStr = "";
      if (xmlData.size() > 0) {
         for(int i = 0; i < xmlData.size(); ++i) {
            XmlText.append("<row>");

            for(String key : ((Map)xmlData.get(i)).keySet()) {
               if (((Map)xmlData.get(i)).get(key) != null && !"null".equals(((Map)xmlData.get(i)).get(key)) && !"".equals(((Map)xmlData.get(i)).get(key))) {
                  fielStr = ((Map)xmlData.get(i)).get(key).toString().replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
                  XmlText.append("<");
                  XmlText.append(key);
                  XmlText.append(">");
                  XmlText.append(fielStr);
                  XmlText.append("</");
                  XmlText.append(key);
                  XmlText.append(">");
                  fielStr = "";
               }
            }

            XmlText.append("</row>");
         }
      }

      XmlText.append("</xml>");
      return XmlText.toString();
   }
}
