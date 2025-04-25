package com.emr.project.emr.service.impl;

import com.emr.common.constant.CommonConstants;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.common.utils.StringUtils;
import com.emr.project.common.service.ICommonService;
import com.emr.project.emr.domain.EmrSetDetail;
import com.emr.project.emr.domain.vo.EmrSetDetailVo;
import com.emr.project.emr.domain.vo.IndexVo;
import com.emr.project.emr.mapper.EmrSetDetailMapper;
import com.emr.project.emr.mapper.EmrSetDoctorMapper;
import com.emr.project.emr.service.IEmrSetDetailService;
import com.emr.project.emr.service.IIndexService;
import com.emr.project.system.domain.SysUser;
import com.emr.project.tmpl.domain.TmplIndex;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmrSetDetailServiceImpl implements IEmrSetDetailService {
   @Autowired
   private EmrSetDetailMapper emrSetDetailMapper;
   @Autowired
   private ICommonService commonService;
   @Autowired
   private IIndexService indexService;
   @Autowired
   private EmrSetDoctorMapper emrSetDoctorMapper;

   public EmrSetDetail selectEmrSetDetailById(Long id) {
      return this.emrSetDetailMapper.selectEmrSetDetailById(id);
   }

   public List selectEmrSetDetailList(EmrSetDetail emrSetDetail) throws Exception {
      return this.emrSetDetailMapper.selectEmrSetDetailList(emrSetDetail);
   }

   public int insertEmrSetDetail(EmrSetDetail emrSetDetail) {
      return this.emrSetDetailMapper.insertEmrSetDetail(emrSetDetail);
   }

   public void insertEmrSetDetailList(List emrSetDetailList) throws Exception {
      this.emrSetDetailMapper.insertEmrSetDetailList(emrSetDetailList);
   }

   public int updateEmrSetDetail(EmrSetDetail emrSetDetail) {
      return this.emrSetDetailMapper.updateEmrSetDetail(emrSetDetail);
   }

   public int deleteEmrSetDetailByIds(Long[] ids) {
      return this.emrSetDetailMapper.deleteEmrSetDetailByIds(ids);
   }

   public void deleteEmrSetDetailById(Long id) throws Exception {
      this.emrSetDetailMapper.deleteEmrSetDetailById(id);
   }

   public void deleteEmrSetDetailSetCd(String setCd) throws Exception {
      this.emrSetDetailMapper.deleteEmrSetDetailSetCd(setCd);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void saveEmrSetDetailList(List details, String setCd) throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      this.emrSetDetailMapper.deleteEmrSetDetailSetCd(setCd);
      if (details != null && !details.isEmpty()) {
         Date date = this.commonService.getDbSysdate();
         int index = 0;

         for(EmrSetDetail emrSetDetail : details) {
            emrSetDetail.setSetCd(setCd);
            emrSetDetail.setCreDate(date);
            emrSetDetail.setCrePerCode(sysUser.getBasEmployee().getEmplNumber());
            emrSetDetail.setCrePerName(sysUser.getBasEmployee().getEmplName());
            Long id = SnowIdUtils.uniqueLong();
            if (emrSetDetail.getId() == null) {
               emrSetDetail.setId(id);
            }

            ++index;
            emrSetDetail.setSort(index);
            emrSetDetail.setHospitalCode(sysUser.getHospital().getOrgCode());
         }

         this.emrSetDetailMapper.insertEmrSetDetailList(details);
      }

   }

   private static Predicate distinctByKeys(Function... keyExtractors) {
      Map<List<?>, Boolean> seen = new ConcurrentHashMap();
      return (t) -> {
         List<?> keys = (List)Arrays.stream(keyExtractors).map((ke) -> ke.apply(t)).collect(Collectors.toList());
         return seen.putIfAbsent(keys, Boolean.TRUE) == null;
      };
   }

   public List selectEmrSetDetailTreeList(EmrSetDetailVo emrSetDetail) throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      List<EmrSetDetailVo> resultList = new ArrayList();
      emrSetDetail.setDocCd(sysUser.getBasEmployee().getEmplNumber());
      List<EmrSetDetailVo> list = this.emrSetDetailMapper.selectEmrSetDetailCheckedList(emrSetDetail);
      if (list != null && !list.isEmpty()) {
         LinkedHashMap<String, List<EmrSetDetailVo>> mapList = (LinkedHashMap)list.stream().collect(Collectors.groupingBy(EmrSetDetailVo::getEmrTypeName, LinkedHashMap::new, Collectors.toList()));

         for(String emrTypeName : mapList.keySet()) {
            EmrSetDetailVo emrSetDetailVo = new EmrSetDetailVo();
            emrSetDetailVo.setTempName(emrTypeName);
            emrSetDetailVo.setId(Long.valueOf(((EmrSetDetailVo)((List)mapList.get(emrTypeName)).get(0)).getEmrTypeId()));
            List<EmrSetDetailVo> clist = (List)mapList.get(emrTypeName);
            emrSetDetailVo.setChildList(clist);
            resultList.add(emrSetDetailVo);
         }
      }

      return resultList;
   }

   public List selectEmrCheckedSetTree(EmrSetDetailVo emrSetDetail) throws Exception {
      SysUser sysUser = SecurityUtils.getLoginUser().getUser();
      emrSetDetail.setDocCd(sysUser.getBasEmployee().getEmplNumber());
      List<EmrSetDetailVo> resultList = new ArrayList();
      List<IndexVo> indexList = new ArrayList();
      new ArrayList();
      List subList = this.indexService.selectSubEmrListByPat(emrSetDetail.getPatientId());
      if (StringUtils.isNotEmpty(emrSetDetail.getMrType()) && emrSetDetail.getMrType().equals("MAINFILE")) {
         indexList.addAll(subList);
      } else {
         IndexVo indexVo = new IndexVo();
         indexVo.setPatientId(emrSetDetail.getPatientId());
         indexList = this.indexService.selectEmrListByPat(indexVo);
         List<IndexVo> voList = (List<IndexVo>)(subList != null && !subList.isEmpty() ? (List)subList.stream().filter((s) -> s.getMrType().equals("12")).collect(Collectors.toList()) : new ArrayList());
         indexList.addAll(voList);
      }

      List<EmrSetDetailVo> list = this.emrSetDetailMapper.selectEmrCheckedSetTree(emrSetDetail);
      if (list != null && !list.isEmpty()) {
         LinkedHashMap<String, List<EmrSetDetailVo>> map = (LinkedHashMap)list.stream().collect(Collectors.groupingBy(EmrSetDetail::getSetCd, LinkedHashMap::new, Collectors.toList()));

         for(String setCd : map.keySet()) {
            EmrSetDetailVo emrSetDetailVo = new EmrSetDetailVo();
            List<EmrSetDetailVo> voList = (List)map.get(setCd);
            List<EmrSetDetailVo> childList = new ArrayList();
            emrSetDetailVo.setTempName(((EmrSetDetailVo)voList.get(0)).getSetName());
            emrSetDetailVo.setSetCd(((EmrSetDetailVo)voList.get(0)).getSetCd());

            for(EmrSetDetailVo detailVo : voList) {
               if (!StringUtils.isEmpty(emrSetDetail.getMrType()) && emrSetDetail.getMrType().equals("MAINFILE") || !detailVo.getMrType().equals("12") || subList == null || subList.isEmpty()) {
                  detailVo.setSetCd(detailVo.getTempId().toString());
                  List<IndexVo> indexVos = (List<IndexVo>)(indexList != null && !indexList.isEmpty() ? (List)indexList.stream().filter((s) -> s.getTempId().toString().equals(detailVo.getTempId().toString())).collect(Collectors.toList()) : new ArrayList());
                  if (indexVos != null && !indexVos.isEmpty()) {
                     detailVo.setIsCreateFlag(CommonConstants.BOOL_TRUE);
                  } else {
                     detailVo.setIsCreateFlag(CommonConstants.BOOL_FALSE);
                  }

                  TmplIndex tmplIndex = new TmplIndex();
                  tmplIndex.setTempType(detailVo.getMrType());
                  tmplIndex.setTempClass(detailVo.getTempClass());
                  if (this.indexService.isEmrTypeMainFile(tmplIndex)) {
                     detailVo.setIsMainFile("1");
                  }

                  childList.add(detailVo);
               }
            }

            if (CollectionUtils.isNotEmpty(childList)) {
               emrSetDetailVo.setChildList(childList);
               resultList.add(emrSetDetailVo);
            }
         }
      }

      return resultList;
   }
}
