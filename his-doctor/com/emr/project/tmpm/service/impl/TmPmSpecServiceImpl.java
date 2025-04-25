package com.emr.project.tmpm.service.impl;

import com.emr.common.utils.StringUtils;
import com.emr.project.operation.domain.Baseinfomation;
import com.emr.project.operation.mapper.BaseinfomationMapper;
import com.emr.project.system.domain.SysDictData;
import com.emr.project.system.service.ISysDictDataService;
import com.emr.project.tmpm.domain.TmPmSpec;
import com.emr.project.tmpm.domain.vo.TmPmSpecVo;
import com.emr.project.tmpm.mapper.TmPmSpecMapper;
import com.emr.project.tmpm.service.ITmPmSpecService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TmPmSpecServiceImpl implements ITmPmSpecService {
   @Autowired
   private TmPmSpecMapper tmPmSpecMapper;
   @Autowired
   private BaseinfomationMapper baseinfomationMapper;
   @Autowired
   private ISysDictDataService sysDictDataService;

   public TmPmSpec selectTmPmSpecById(Long id) {
      return this.tmPmSpecMapper.selectTmPmSpecById(id);
   }

   public List selectTmPmSpecList(TmPmSpec tmPmSpec) {
      return this.tmPmSpecMapper.selectTmPmSpecList(tmPmSpec);
   }

   public int insertTmPmSpec(TmPmSpec tmPmSpec) {
      return this.tmPmSpecMapper.insertTmPmSpec(tmPmSpec);
   }

   public int updateTmPmSpec(TmPmSpec tmPmSpec) {
      return this.tmPmSpecMapper.updateTmPmSpec(tmPmSpec);
   }

   public int deleteTmPmSpecByIds(Long[] ids) {
      return this.tmPmSpecMapper.deleteTmPmSpecByIds(ids);
   }

   public int deleteTmPmSpecById(Long id) {
      return this.tmPmSpecMapper.deleteTmPmSpecById(id);
   }

   public List selectTmPmSpecListByVo(TmPmSpecVo tmPmSpec) throws Exception {
      String admissionNo = tmPmSpec.getAdmissionNo();
      List<String> examSex = new ArrayList();
      examSex.add("3");
      if (StringUtils.isNotEmpty(admissionNo)) {
         Baseinfomation baseinfomation = this.baseinfomationMapper.findBaseInfo(admissionNo);
         if (baseinfomation != null) {
            List<SysDictData> sexDataList = this.sysDictDataService.selectDictDataByType("s028");
            Map<String, List<SysDictData>> sexDictMap = (Map<String, List<SysDictData>>)(CollectionUtils.isNotEmpty(sexDataList) ? (Map)sexDataList.stream().collect(Collectors.groupingBy(SysDictData::getDictValue)) : new HashMap(1));
            String sex = baseinfomation.getSex();
            if (sexDictMap.containsKey(sex)) {
               SysDictData dictData = (SysDictData)((List)sexDictMap.get(sex)).get(0);
               if (StringUtils.isNotEmpty(dictData.getDictLabel())) {
                  if (dictData.getDictLabel().equals("男")) {
                     examSex.add("1");
                  } else if (dictData.getDictLabel().equals("女")) {
                     examSex.add("2");
                  }
               }
            }
         }
      }

      tmPmSpec.setList(examSex);
      return this.tmPmSpecMapper.selectTmPmSpecListByVo(tmPmSpec);
   }

   public List selectBySpecCdList(List specCdList) throws Exception {
      List<TmPmSpec> list = null;
      if (CollectionUtils.isNotEmpty(specCdList)) {
         list = this.tmPmSpecMapper.selectBySpecCdList(specCdList);
      }

      return list;
   }
}
