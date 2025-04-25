package com.emr.project.dr.service.impl;

import com.emr.common.utils.AgeUtil;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.common.utils.StringUtils;
import com.emr.project.dr.domain.DrHandoverDetail;
import com.emr.project.dr.domain.DrHandoverMain;
import com.emr.project.dr.domain.DrHandoverPatientDel;
import com.emr.project.dr.domain.DrHandoverPatientType;
import com.emr.project.dr.domain.DrHandoverQuotElem;
import com.emr.project.dr.domain.vo.DrHandoverDetailPrintVo;
import com.emr.project.dr.domain.vo.DrHandoverDetailVo;
import com.emr.project.dr.domain.vo.DrHandoverMainPrintVo;
import com.emr.project.dr.domain.vo.DrHandoverPatientTypeVo;
import com.emr.project.dr.domain.vo.DrHandoverPrintListVo;
import com.emr.project.dr.domain.vo.DrHandoverPrintVo;
import com.emr.project.dr.mapper.DrHandoverDetailMapper;
import com.emr.project.dr.service.IDrHandoverDetailService;
import com.emr.project.dr.service.IDrHandoverMainService;
import com.emr.project.dr.service.IDrHandoverPatientDelService;
import com.emr.project.dr.service.IDrHandoverPatientTypeService;
import com.emr.project.dr.service.IDrHandoverQuotElemService;
import com.emr.project.emr.domain.EmrElemstoe;
import com.emr.project.emr.service.IEmrElemstoeService;
import com.emr.project.system.domain.BasEmployee;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DrHandoverDetailServiceImpl implements IDrHandoverDetailService {
   @Autowired
   private DrHandoverDetailMapper drHandoverDetailMapper;
   @Autowired
   private IDrHandoverPatientTypeService patientTypeService;
   @Autowired
   private IDrHandoverPatientDelService drHandoverPatientDelService;
   @Autowired
   private IDrHandoverPatientTypeService drHandoverPatientTypeService;
   @Autowired
   private IDrHandoverMainService drHandoverMainService;
   @Autowired
   private IDrHandoverQuotElemService drHandoverQuotElemService;
   @Autowired
   private IEmrElemstoeService emrElemstoeService;

   public DrHandoverDetail selectDrHandoverDetailById(Long id) {
      return this.drHandoverDetailMapper.selectDrHandoverDetailById(id);
   }

   public List selectDrHandoverDetailList(DrHandoverDetailVo drHandoverDetail) {
      List<DrHandoverDetailVo> detailList = this.drHandoverDetailMapper.selectDrHandoverDetailList(drHandoverDetail);
      if (detailList != null && !detailList.isEmpty()) {
         for(DrHandoverDetailVo drHandoverDetailVo : detailList) {
            drHandoverDetailVo.setSaveFlag("1");
         }
      }

      return detailList;
   }

   public List selectListByType(DrHandoverDetail drHandoverDetail) throws Exception {
      List<DrHandoverDetailVo> detailList = this.drHandoverDetailMapper.selectDetailListByMainId(drHandoverDetail.getMainId());
      List<DrHandoverDetailVo> list = new ArrayList();
      if (detailList != null) {
         DrHandoverMain drHandoverMain = this.drHandoverMainService.selectDrHandoverMainById(drHandoverDetail.getMainId());
         List<DrHandoverDetailVo> detailListByType = (List)detailList.stream().filter((s) -> s.getPatientTypeCode().contains(drHandoverDetail.getPatientTypeCode())).collect(Collectors.toList());
         if (detailListByType != null && !detailListByType.isEmpty()) {
            detailListByType.stream().forEach((d) -> {
               d.setSaveFlag("1");
               list.add(d);
            });
         }

         List<String> patientIdList = (List)detailList.stream().map((s) -> s.getPatientId()).collect(Collectors.toList());
         DrHandoverPatientType drHandoverPatientType = this.drHandoverPatientTypeService.selectDrHandoverPatientTypeById(Long.parseLong(drHandoverDetail.getPatientTypeCode()));
         if (drHandoverPatientType != null) {
            DrHandoverPatientTypeVo drHandoverPatientTypeVo = new DrHandoverPatientTypeVo();
            drHandoverPatientTypeVo.setDeptCd(drHandoverMain.getDeptCd());
            drHandoverPatientTypeVo.setBeginTime(drHandoverMain.getSchemeBegin());
            drHandoverPatientTypeVo.setEndTime(drHandoverMain.getSchemeEnd());
            drHandoverPatientTypeVo.setDataSql(drHandoverPatientType.getDataSql());
            List<DrHandoverDetailVo> unSavePatlist = this.drHandoverPatientTypeService.selectHandoverPatientListBySql(drHandoverPatientTypeVo);
            List<DrHandoverDetailVo> unPatientList = new ArrayList(1);

            for(DrHandoverDetailVo drHandoverDetailVo : unSavePatlist) {
               if (!patientIdList.contains(drHandoverDetailVo.getPatientId())) {
                  unPatientList.add(drHandoverDetailVo);
               }
            }

            if (unPatientList != null && !unPatientList.isEmpty()) {
               List<DrHandoverQuotElem> drHandoverQuotElemList = this.drHandoverQuotElemService.selectElemListByTypeCode(Long.parseLong(drHandoverDetail.getPatientTypeCode()));
               List<EmrElemstoe> emrElemstoeList = this.emrElemstoeService.selectEmrElemstoeList(new EmrElemstoe());
               Map<String, List<EmrElemstoe>> elemMap = (Map)emrElemstoeList.stream().collect(Collectors.groupingBy((s) -> s.getPatientId()));

               for(DrHandoverDetailVo drHandoverDetailVo : unPatientList) {
                  List<EmrElemstoe> elemList = (List)elemMap.get(drHandoverDetailVo.getPatientId());
                  String str = "";
                  if (elemList != null && !elemList.isEmpty()) {
                     for(DrHandoverQuotElem drHandoverQuotElem : drHandoverQuotElemList) {
                        List<EmrElemstoe> elemstoeList = (List)elemList.stream().filter((s) -> s.getElemId().equals(drHandoverQuotElem.getFromElemId()) && s.getMrTypeCd().equals(drHandoverQuotElem.getFromMrTypeCd())).collect(Collectors.toList());
                        str = str + (elemstoeList != null && !elemstoeList.isEmpty() ? ((EmrElemstoe)elemstoeList.get(0)).getElemCon() : "");
                     }
                  }

                  drHandoverDetailVo.setCondSesc(str);
                  drHandoverDetailVo.setSaveFlag("2");
                  String ageStr = AgeUtil.getAgeStr(drHandoverDetailVo.getAgeY(), drHandoverDetailVo.getAgeM(), drHandoverDetailVo.getAgeD(), drHandoverDetailVo.getAgeH(), drHandoverDetailVo.getAgeMi());
                  drHandoverDetailVo.setAge(ageStr);
                  drHandoverDetailVo.setPatientTypeCode(drHandoverPatientType.getTypeCode().toString());
                  drHandoverDetailVo.setPatientTypeName(drHandoverPatientType.getTypeName());
               }
            }

            list.addAll(unPatientList);
         }
      }

      return list;
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public Map saveDrHandoverDetail(List detailList, Long mainId) {
      Map<String, Integer> resultMap = new HashMap();
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      List<DrHandoverDetail> insertList = (List)detailList.stream().filter((s) -> s.getId() == null).collect(Collectors.toList());
      List<DrHandoverDetail> updateList = (List)detailList.stream().filter((s) -> s.getId() != null).collect(Collectors.toList());
      List<DrHandoverPatientType> typeList = this.drHandoverPatientTypeService.selectDrHandoverPatientTypeList(new DrHandoverPatientType());
      if (insertList != null && !insertList.isEmpty()) {
         for(DrHandoverDetail detail : insertList) {
            detail.setCrePerCode(basEmployee.getEmplNumber());
            detail.setCrePerName(basEmployee.getEmplName());
            detail.setId(SnowIdUtils.uniqueLong());
            detail.setMainId(mainId);
            detail.setDelFlag("0");
         }

         this.drHandoverDetailMapper.insertDrHandoverDetailList(insertList);

         for(DrHandoverDetail detail : insertList) {
            this.getPatientNum(typeList, resultMap, detail.getPatientTypeCode(), 1);
         }
      }

      if (updateList != null && !updateList.isEmpty()) {
         for(DrHandoverDetail detail : updateList) {
            DrHandoverDetail detail1 = this.drHandoverDetailMapper.selectDrHandoverDetailById(detail.getId());
            detail.setAltPerCode(basEmployee.getEmplNumber());
            detail.setAltPerName(basEmployee.getEmplName());
            this.updateDrHandoverDetail(detail);
            Map<String, List<DrHandoverPatientType>> typeMap = (Map)typeList.stream().collect(Collectors.groupingBy((s) -> s.getTypeCode().toString()));
            List<String> typeCodeStr1 = Arrays.asList(detail1.getPatientTypeCode().split(","));
            List<String> typeCodeStr = Arrays.asList(detail.getPatientTypeCode().split(","));
            if (typeCodeStr1.size() > typeCodeStr.size()) {
               for(int i = 0; i < typeCodeStr1.size(); ++i) {
                  if (!typeCodeStr.contains(typeCodeStr1.get(i))) {
                     List<DrHandoverPatientType> types = (List)typeMap.get(typeCodeStr1.get(i));
                     String key = StringUtils.toCamelCase(((DrHandoverPatientType)types.get(0)).getNumColumn());
                     Integer num = resultMap.get(key) == null ? -1 : Integer.parseInt(((Integer)resultMap.get(key)).toString()) - 1;
                     resultMap.put(key, num);
                  }
               }
            } else {
               for(int i = 0; i < typeCodeStr.size(); ++i) {
                  if (!typeCodeStr1.contains(typeCodeStr.get(i))) {
                     List<DrHandoverPatientType> types = (List)typeMap.get(typeCodeStr.get(i));
                     String key = StringUtils.toCamelCase(((DrHandoverPatientType)types.get(0)).getNumColumn());
                     Integer num = resultMap.get(key) == null ? 1 : Integer.parseInt(((Integer)resultMap.get(key)).toString()) + 1;
                     resultMap.put(key, num);
                  }
               }
            }
         }
      }

      return resultMap;
   }

   public int insertDrHandoverDetail(DrHandoverDetail drHandoverDetail) {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      drHandoverDetail.setId(SnowIdUtils.uniqueLong());
      drHandoverDetail.setCrePerCode(basEmployee.getEmplNumber());
      drHandoverDetail.setCrePerName(basEmployee.getEmplName());
      return this.drHandoverDetailMapper.insertDrHandoverDetail(drHandoverDetail);
   }

   public int updateDrHandoverDetail(DrHandoverDetail drHandoverDetail) {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      drHandoverDetail.setAltPerCode(basEmployee.getEmplNumber());
      drHandoverDetail.setAltPerName(basEmployee.getEmplName());
      return this.drHandoverDetailMapper.updateDrHandoverDetail(drHandoverDetail);
   }

   public int deleteDrHandoverDetailByIds(Long[] ids) {
      return this.drHandoverDetailMapper.deleteDrHandoverDetailByIds(ids);
   }

   public int deleteDrHandoverDetailById(Long id) {
      return this.drHandoverDetailMapper.deleteDrHandoverDetailById(id);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public Map deleteDrHandoverDetail(DrHandoverDetail drHandoverDetail) throws Exception {
      DrHandoverDetail detail = this.drHandoverDetailMapper.selectDetailByPatAndMainId(drHandoverDetail.getPatientId(), drHandoverDetail.getMainId());
      Map<String, Integer> resultMap = new HashMap();
      if (detail != null) {
         BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
         drHandoverDetail.setAltPerCode(basEmployee.getEmplNumber());
         drHandoverDetail.setAltPerName(basEmployee.getEmplName());
         this.drHandoverDetailMapper.updateDetailDelFlag(drHandoverDetail);
         DrHandoverPatientDel patientDel = new DrHandoverPatientDel(detail);
         this.drHandoverPatientDelService.insertDrHandoverPatientDel(patientDel);
         List<DrHandoverPatientType> typeList = this.drHandoverPatientTypeService.selectDrHandoverPatientTypeList(new DrHandoverPatientType());
         this.getPatientNum(typeList, resultMap, detail.getPatientTypeCode(), -1);
      }

      return resultMap;
   }

   private void getPatientNum(List typeList, Map map, String typeCode, Integer num) {
      Map<String, List<DrHandoverPatientType>> typeMap = (Map)typeList.stream().collect(Collectors.groupingBy((s) -> s.getTypeCode().toString()));
      String[] typeCodeStr = typeCode.split(",");

      for(int i = 0; i < typeCodeStr.length; ++i) {
         List<DrHandoverPatientType> types = (List)typeMap.get(typeCodeStr[i]);
         String key = StringUtils.toCamelCase(((DrHandoverPatientType)types.get(0)).getNumColumn());
         num = map.get(key) == null ? num : Integer.parseInt(((Integer)map.get(key)).toString()) + num;
         map.put(key, num);
      }

   }

   public List selectDetailListByMainId(Long mainId) throws Exception {
      return this.drHandoverDetailMapper.selectDetailListByMainId(mainId);
   }

   public List selectIsSavePatientId(DrHandoverDetailVo drHandoverDetail) throws Exception {
      List<DrHandoverDetailVo> drHandoverDetailVos = this.drHandoverDetailMapper.selectDrHandoverDetailList(drHandoverDetail);
      return drHandoverDetailVos;
   }

   public DrHandoverPrintListVo selectDrPrintList(DrHandoverDetailVo detail) throws Exception {
      DrHandoverPrintListVo printVo = new DrHandoverPrintListVo();
      List<DrHandoverDetailVo> detailList = this.drHandoverDetailMapper.selectDrHandoverDetailList(detail);
      List<DrHandoverDetailVo> list = new ArrayList();
      List<List<DrHandoverDetailVo>> pageList = new ArrayList();
      if (detailList != null && !detailList.isEmpty()) {
         for(DrHandoverDetailVo detailVo : detailList) {
            detailVo.setSaveFlag("1");
            String diaName = detailVo.getDiaName();
            String conDesc = detailVo.getCondSesc();
            Integer dia = StringUtils.isEmpty(diaName) ? 0 : (diaName.length() / 15 == 0 ? 1 : diaName.length() / 15);
            Integer con = StringUtils.isEmpty(conDesc) ? 0 : (conDesc.length() / 35 == 0 ? 1 : conDesc.length() / 35);
            Integer num = dia > con ? dia : con;
            String groupId = SnowIdUtils.uniqueLongHex();
            if (num == 1) {
               DrHandoverDetailVo drHandoverDetailVo = new DrHandoverDetailVo();
               BeanUtils.copyProperties(detailVo, drHandoverDetailVo);
               drHandoverDetailVo.setGroupId(groupId);
               drHandoverDetailVo.setDiaName(detailVo.getDiaName());
               drHandoverDetailVo.setCondSesc(detailVo.getCondSesc());
               list.add(drHandoverDetailVo);
            } else {
               for(int i = 0; i < num; ++i) {
                  DrHandoverDetailVo drHandoverDetailVo = new DrHandoverDetailVo();
                  BeanUtils.copyProperties(detailVo, drHandoverDetailVo);
                  drHandoverDetailVo.setGroupId(groupId);
                  if (i + 1 == dia) {
                     drHandoverDetailVo.setDiaName(detailVo.getDiaName().substring((i + 1) * 15));
                  } else if (i + 1 < dia) {
                     drHandoverDetailVo.setDiaName(detailVo.getDiaName().substring(i * 15, (i + 1) * 15));
                  } else {
                     drHandoverDetailVo.setDiaName("");
                  }

                  if (i + 1 == con) {
                     drHandoverDetailVo.setCondSesc(detailVo.getCondSesc().substring((i + 1) * 24));
                  } else if (i + 1 < con) {
                     drHandoverDetailVo.setCondSesc(detailVo.getCondSesc().substring(i * 24, (i + 1) * 24));
                  } else {
                     drHandoverDetailVo.setCondSesc("");
                  }

                  list.add(drHandoverDetailVo);
               }
            }
         }

         List<DrHandoverDetailVo> voList = new ArrayList();

         for(int t = 0; t < list.size(); ++t) {
            voList.add(list.get(t));
            if (t >= 13) {
               if (voList.size() >= 14) {
                  pageList.add(voList);
                  voList = new ArrayList();
               }
            } else if (voList.size() >= 18) {
               pageList.add(voList);
               voList = new ArrayList();
            }
         }

         if (voList.size() > 0) {
            pageList.add(voList);
         }

         printVo.setPageDataList(pageList);
         printVo.setTotal(list.size());
      }

      return printVo;
   }

   public DrHandoverPrintVo printDetail(DrHandoverDetailVo drHandoverDetailVo) throws Exception {
      DrHandoverPrintVo printVo = new DrHandoverPrintVo();
      List<DrHandoverDetailPrintVo> detailVoList = new ArrayList();
      List<DrHandoverDetailVo> detailList = this.drHandoverDetailMapper.selectDrHandoverDetailList(drHandoverDetailVo);
      if (detailList != null && detailList.size() > 0) {
         for(int i = 0; i < detailList.size(); ++i) {
            DrHandoverDetailVo vo = (DrHandoverDetailVo)detailList.get(i);
            DrHandoverDetailPrintVo printDetailVo = new DrHandoverDetailPrintVo();
            BeanUtils.copyProperties(vo, printDetailVo);
            printDetailVo.setIndex(i + 1);
            detailVoList.add(printDetailVo);
         }

         printVo.setDetailVoList(detailVoList);
      }

      List<DrHandoverMainPrintVo> mainList = new ArrayList();
      DrHandoverMainPrintVo mainVo = new DrHandoverMainPrintVo();
      DrHandoverMain drHandoverMain = this.drHandoverMainService.selectDrHandoverMainById(drHandoverDetailVo.getMainId());
      if (drHandoverMain != null) {
         BeanUtils.copyProperties(drHandoverMain, mainVo);
         mainVo.setOrgName(SecurityUtils.getLoginUser().getUser().getHospital().getOrgName());
         mainVo.setDeptName(SecurityUtils.getLoginUser().getUser().getDept().getDeptName());
         mainList.add(mainVo);
      }

      printVo.setMainVo(mainList);
      return printVo;
   }
}
