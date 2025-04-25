package com.emr.project.tmpm.service.impl;

import com.emr.common.utils.StringUtils;
import com.emr.project.operation.domain.Baseinfomation;
import com.emr.project.operation.mapper.BaseinfomationMapper;
import com.emr.project.system.domain.SysDictData;
import com.emr.project.system.service.ISysDictDataService;
import com.emr.project.tmpm.domain.ExamPart;
import com.emr.project.tmpm.domain.vo.ExamPartVo;
import com.emr.project.tmpm.mapper.ExamPartMapper;
import com.emr.project.tmpm.service.IExamPartService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExamPartServiceImpl implements IExamPartService {
   @Autowired
   private ExamPartMapper examPartMapper;
   @Autowired
   private BaseinfomationMapper baseinfomationMapper;
   @Autowired
   private ISysDictDataService sysDictDataService;

   public ExamPart selectExamPartById(Long id) {
      return this.examPartMapper.selectExamPartById(id);
   }

   public List selectExamPartList(ExamPart examPart) throws Exception {
      return this.examPartMapper.selectExamPartList(examPart);
   }

   public Map selectExamPartMapList(ExamPartVo examPart) throws Exception {
      String admissionNo = examPart.getAdmissionNo();
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

      examPart.setList(examSex);
      Map<String, List<ExamPart>> resultMap = new HashMap();
      List<ExamPart> list = this.examPartMapper.selectExamPartListBySexList(examPart);
      if (list != null && !list.isEmpty()) {
         List<ExamPart> emptyList = (List)list.stream().filter((s) -> StringUtils.isEmpty(s.getPartClassName())).collect(Collectors.toList());
         List<ExamPart> examList = (List)list.stream().filter((s) -> StringUtils.isNotEmpty(s.getPartClassName())).collect(Collectors.toList());
         if (examList != null && !examList.isEmpty()) {
            Map<String, List<ExamPart>> map = (Map)examList.stream().collect(Collectors.groupingBy((s) -> s.getPartClassName()));
            resultMap.putAll(map);
         }

         resultMap.put("全部", emptyList);
      }

      return resultMap;
   }

   public List selectExamPartMainList() throws Exception {
      return this.examPartMapper.selectExamPartMainList();
   }

   public int insertExamPart(ExamPart examPart) {
      return this.examPartMapper.insertExamPart(examPart);
   }

   public int updateExamPart(ExamPart examPart) {
      return this.examPartMapper.updateExamPart(examPart);
   }

   public int deleteExamPartByIds(Long[] ids) {
      return this.examPartMapper.deleteExamPartByIds(ids);
   }

   public int deleteExamPartById(Long id) {
      return this.examPartMapper.deleteExamPartById(id);
   }

   public List selectByExamPartCdList(List examPartCdList) throws Exception {
      List<ExamPart> list = null;
      if (CollectionUtils.isNotEmpty(examPartCdList)) {
         list = this.examPartMapper.selectByExamPartCdList(examPartCdList);
      }

      return list;
   }
}
