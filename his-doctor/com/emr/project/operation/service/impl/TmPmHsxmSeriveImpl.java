package com.emr.project.operation.service.impl;

import com.emr.project.operation.domain.TmPmHsxm;
import com.emr.project.operation.mapper.TmPmHsxmMapper;
import com.emr.project.operation.service.ITmPmHsxmSerive;
import com.emr.project.report.domain.YyPDbzwh;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TmPmHsxmSeriveImpl implements ITmPmHsxmSerive {
   @Autowired
   private TmPmHsxmMapper tmPmHsxmMapper;

   public List selectByConn(TmPmHsxm tmPmHsxm) throws Exception {
      return this.tmPmHsxmMapper.selectByConn(tmPmHsxm);
   }

   public List getAttrList(TmPmHsxm tmPmHsxm, Function mapper) {
      List<TmPmHsxm> list = this.tmPmHsxmMapper.selectByConn(tmPmHsxm);
      List<String> codeList = Lists.newArrayList();
      if (list != null && list.size() > 0) {
         codeList = (List)list.stream().map(mapper).collect(Collectors.toList());
      }

      return codeList;
   }

   public List getThreeLevelCodeList() {
      return this.tmPmHsxmMapper.getThreeLevelCodeList();
   }

   public Map getZdmcMaps() {
      List<YyPDbzwh> dbzwhList = this.tmPmHsxmMapper.selectAll();
      Map<String, String> dbzwhMaps = (Map)dbzwhList.stream().collect(Collectors.toMap(YyPDbzwh::getIcd, YyPDbzwh::getMc));
      return dbzwhMaps;
   }
}
