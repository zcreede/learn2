package com.emr.project.tmpm.service.impl;

import com.emr.common.utils.StringUtils;
import com.emr.framework.config.EMRConfig;
import com.emr.project.tmpm.domain.PrintTmplInfo;
import com.emr.project.tmpm.mapper.PrintTmplInfoMapper;
import com.emr.project.tmpm.service.IPrintTmplInfoService;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrintTmplInfoServiceImpl implements IPrintTmplInfoService {
   @Autowired
   private PrintTmplInfoMapper printTmplInfoMapper;

   public PrintTmplInfo selectTmPmPrintTmplInfoByCode(String typeCode) throws Exception {
      PrintTmplInfo printTmplInfo = null;
      if (StringUtils.isNotBlank(typeCode)) {
         printTmplInfo = this.printTmplInfoMapper.selectTmPmPrintTmplInfoByCode(typeCode);
      }

      if (printTmplInfo != null && StringUtils.isNotBlank(printTmplInfo.getTmplFilePath())) {
         String reOld = EMRConfig.getProfile();
         String reNew = EMRConfig.getNfsPdfurl();
         String path = printTmplInfo.getTmplFilePath().replace(reOld, reNew);
         printTmplInfo.setTmplFilePath(path);
      }

      return printTmplInfo;
   }

   public List selectTmPmPrintTmplInfoByCodes(List typeCodeList) throws Exception {
      List<PrintTmplInfo> list = null;
      if (CollectionUtils.isNotEmpty(typeCodeList)) {
         list = this.printTmplInfoMapper.selectTmPmPrintTmplInfoByCodes(typeCodeList);
      }

      if (CollectionUtils.isNotEmpty(list)) {
         for(PrintTmplInfo printTmplInfo : list) {
            String reOld = EMRConfig.getProfile();
            String reNew = EMRConfig.getNfsPdfurl();
            String path = printTmplInfo.getTmplFilePath().replace(reOld, reNew);
            printTmplInfo.setTmplFilePath(path);
         }
      }

      return list;
   }

   public List selectListByParentTypeCode(String parentTypeCode) throws Exception {
      List<PrintTmplInfo> resList = null;
      if (StringUtils.isNotBlank(parentTypeCode)) {
         resList = this.printTmplInfoMapper.selectListByParentTypeCode(parentTypeCode);
      }

      if (CollectionUtils.isNotEmpty(resList)) {
         for(PrintTmplInfo printTmplInfo : resList) {
            String reOld = EMRConfig.getProfile();
            String reNew = EMRConfig.getNfsPdfurl();
            String path = printTmplInfo.getTmplFilePath().replace(reOld, reNew);
            printTmplInfo.setTmplFilePath(path);
         }
      }

      return resList;
   }
}
