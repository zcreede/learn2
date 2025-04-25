package com.emr.project.sys.service.impl;

import com.emr.common.utils.DateUtils;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.framework.aspectj.lang.annotation.DataSource;
import com.emr.framework.aspectj.lang.enums.DataSourceType;
import com.emr.project.dr.domain.vo.TdCaConsApplyVo;
import com.emr.project.dr.service.ITdCaConsApplyService;
import com.emr.project.emr.domain.Index;
import com.emr.project.emr.domain.SubfileIndex;
import com.emr.project.emr.domain.vo.IndexMzVo;
import com.emr.project.emr.domain.vo.IndexVo;
import com.emr.project.emr.mapper.IndexMapper;
import com.emr.project.emr.service.IEmrIndexMMenuService;
import com.emr.project.emr.service.IIndexService;
import com.emr.project.emr.service.ISubfileIndexService;
import com.emr.project.mrhp.domain.MrHp;
import com.emr.project.mrhp.service.IMrHpService;
import com.emr.project.mzInfo.domain.MzMrFileInfo;
import com.emr.project.pat.domain.vo.VisitinfoVo;
import com.emr.project.pat.service.IVisitinfoService;
import com.emr.project.qc.domain.EmrQcList;
import com.emr.project.qc.service.IEmrQcListService;
import com.emr.project.sys.domain.BusIntegMenu;
import com.emr.project.sys.domain.vo.BusIntegMenuSearchVo;
import com.emr.project.sys.domain.vo.BusIntegMenuTreeVo;
import com.emr.project.sys.domain.vo.BusIntegMenuVo;
import com.emr.project.sys.mapper.BusIntegMenuMapper;
import com.emr.project.sys.service.IBusIntegMenuService;
import com.emr.project.system.domain.SysDictData;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.service.ISysDictTypeService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusIntegMenuServiceImpl implements IBusIntegMenuService {
   @Autowired
   private BusIntegMenuMapper busIntegMenuMapper;
   @Autowired
   private IIndexService indexService;
   @Autowired
   private ISysDictTypeService dictTypeService;
   @Autowired
   private IVisitinfoService visitinfoService;
   @Autowired
   private IEmrQcListService emrQcListService;
   @Autowired
   private IMrHpService mrHpService;
   @Autowired
   private ISubfileIndexService subfileIndexService;
   @Autowired
   private ITdCaConsApplyService tdCaConsApplyService;
   @Autowired
   private IndexMapper indexMapper;
   @Autowired
   private IEmrIndexMMenuService emrIndexMMenuService;

   public BusIntegMenu selectBusIntegMenuById(Long id) {
      return this.busIntegMenuMapper.selectBusIntegMenuById(id);
   }

   public List selectBusIntegMenuList(BusIntegMenu busIntegMenu) {
      return this.busIntegMenuMapper.selectBusIntegMenuList(busIntegMenu);
   }

   public int insertBusIntegMenu(BusIntegMenu busIntegMenu) {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      busIntegMenu.setId(SnowIdUtils.uniqueLong());
      busIntegMenu.setCrePerCode(user.getUserName());
      busIntegMenu.setCrePerName(user.getNickName());
      busIntegMenu.setCreDate(DateUtils.getNowDate());
      return this.busIntegMenuMapper.insertBusIntegMenu(busIntegMenu);
   }

   public int updateBusIntegMenu(BusIntegMenu busIntegMenu) {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      busIntegMenu.setCrePerCode(user.getUserName());
      busIntegMenu.setCrePerName(user.getNickName());
      busIntegMenu.setCreDate(DateUtils.getNowDate());
      return this.busIntegMenuMapper.updateBusIntegMenu(busIntegMenu);
   }

   public int deleteBusIntegMenuByIds(Long[] ids) {
      return this.busIntegMenuMapper.deleteBusIntegMenuByIds(ids);
   }

   public int deleteBusIntegMenuById(Long id) {
      return this.busIntegMenuMapper.deleteBusIntegMenuById(id);
   }

   public BusIntegMenuVo busmenuTreeslist(String patientId) throws Exception {
      BusIntegMenu param = new BusIntegMenu();
      param.setIsValid("1");
      List<BusIntegMenu> busIntegMenuList = this.busIntegMenuMapper.selectBusIntegMenuList(param);
      List<BusIntegMenu> currMenuList = (List)busIntegMenuList.stream().filter((t) -> t.getMenuClass().equals("1") && t.getParentId() == null).collect(Collectors.toList());
      List<BusIntegMenu> hisMenuList = (List)busIntegMenuList.stream().filter((t) -> t.getMenuClass().equals("2") && t.getParentId() == null).collect(Collectors.toList());
      List<BusIntegMenu> childMenuList = (List)busIntegMenuList.stream().filter((t) -> t.getParentId() != null).collect(Collectors.toList());
      List<BusIntegMenuTreeVo> childTreeMenuList = new ArrayList(childMenuList.size());
      childMenuList.forEach((t) -> childTreeMenuList.add(new BusIntegMenuTreeVo(t)));
      Map<Long, List<BusIntegMenuTreeVo>> childMenuListMap = (Map)childTreeMenuList.stream().collect(Collectors.groupingBy((t) -> t.getParentId()));
      List<BusIntegMenuTreeVo> currMenuTreeList = new ArrayList(currMenuList.size());
      List<VisitinfoVo> visitinfoVos = this.visitinfoService.selectVisitinfosById(patientId);
      VisitinfoVo currVisitInfo = (VisitinfoVo)((List)visitinfoVos.stream().filter((t) -> t.getPatientId().equals(patientId)).collect(Collectors.toList())).get(0);
      this.genMenuList(currMenuTreeList, currVisitInfo, currMenuList, childMenuListMap, (String)null, new BusIntegMenuSearchVo(patientId));
      List<BusIntegMenuTreeVo> hisMenuTreeList = new ArrayList(currMenuList.size());
      if (visitinfoVos != null && !visitinfoVos.isEmpty()) {
         for(VisitinfoVo visitinfoVo : (List)visitinfoVos.stream().filter((t) -> !t.getPatientId().equals(patientId)).collect(Collectors.toList())) {
            String suffixStr = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, visitinfoVo.getInhosTime());
            suffixStr = suffixStr.substring(2);
            List<BusIntegMenuTreeVo> currMenuTreeListTemp = new ArrayList(hisMenuList.size());
            this.genMenuList(currMenuTreeListTemp, visitinfoVo, hisMenuList, childMenuListMap, suffixStr, new BusIntegMenuSearchVo(visitinfoVo.getPatientId()));
            BusIntegMenuTreeVo menuTreeVo = new BusIntegMenuTreeVo();
            menuTreeVo.setCode(visitinfoVo.getPatientId());
            menuTreeVo.setName(visitinfoVo.getInDeptName() + " " + DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, visitinfoVo.getInhosTime()));
            menuTreeVo.setChildren(currMenuTreeListTemp);
            hisMenuTreeList.add(menuTreeVo);
         }
      }

      BusIntegMenuVo busIntegMenuVo = new BusIntegMenuVo(currMenuTreeList, hisMenuTreeList);
      return busIntegMenuVo;
   }

   public List busmenuTreeslist(BusIntegMenuSearchVo busIntegMenuSearchVo, String menuClass) throws Exception {
      List<String> indexStateList = busIntegMenuSearchVo.getIndexStateList();
      BusIntegMenu param = new BusIntegMenu();
      param.setIsValid("1");
      param.setMenuClass(menuClass);
      List<BusIntegMenu> busIntegMenuList = this.busIntegMenuMapper.selectBusIntegMenuList(param);
      List<BusIntegMenu> menuList = (List)busIntegMenuList.stream().filter((t) -> t.getParentId() == null).collect(Collectors.toList());
      List<String> mrHpStateList = busIntegMenuSearchVo.getMrHpStateList();
      if (mrHpStateList != null && !mrHpStateList.isEmpty()) {
         MrHp mrHp = this.mrHpService.selectMrHpByPatientId(busIntegMenuSearchVo.getPatientId());
         if (mrHp == null || mrHp != null && !mrHpStateList.contains(mrHp.getMrState())) {
            menuList = (List)menuList.stream().filter((t) -> !t.getCode().equals("em_hp")).collect(Collectors.toList());
         }
      }

      List<BusIntegMenu> childMenuList = (List)busIntegMenuList.stream().filter((t) -> t.getParentId() != null).collect(Collectors.toList());
      List<BusIntegMenuTreeVo> childTreeMenuList = new ArrayList(childMenuList.size());
      childMenuList.forEach((t) -> childTreeMenuList.add(new BusIntegMenuTreeVo(t)));
      Map<Long, List<BusIntegMenuTreeVo>> childMenuListMap = (Map)childTreeMenuList.stream().collect(Collectors.groupingBy((t) -> t.getParentId()));
      List<BusIntegMenuTreeVo> menuTreeList = new ArrayList(menuList.size());
      List<VisitinfoVo> visitinfoVos = this.visitinfoService.selectVisitinfosById(busIntegMenuSearchVo.getPatientId());
      VisitinfoVo currVisitInfo = (VisitinfoVo)((List)visitinfoVos.stream().filter((t) -> t.getPatientId().equals(busIntegMenuSearchVo.getPatientId())).collect(Collectors.toList())).get(0);
      this.genMenuList(menuTreeList, currVisitInfo, menuList, childMenuListMap, (String)null, busIntegMenuSearchVo);
      return menuTreeList;
   }

   private void genMenuList(List currMenuTreeList, VisitinfoVo visitinfoVo, List menuList, Map childMenuListMap, String suffixStr, BusIntegMenuSearchVo busIntegMenuSearchVo) throws Exception {
      Integer mrDocIndex = null;
      Integer babyMrDocIndex = null;

      for(int i = 0; i < menuList.size(); ++i) {
         BusIntegMenu temp = (BusIntegMenu)menuList.get(i);
         List<BusIntegMenuTreeVo> children = (List)childMenuListMap.get(temp.getId());
         List<BusIntegMenuTreeVo> childrenNew = new ArrayList(1);
         if (children != null) {
            children.forEach((t) -> {
               t.setHisInhosTimeStr(suffixStr);
               t.setPatientId(visitinfoVo.getPatientId());
               t.setVisitId(visitinfoVo.getVisitId());
               t.setInpNo(visitinfoVo.getInpNo());
               t.setIndexType(t.getCode().equals("doctor_list") ? "orderList" : null);
               if (StringUtils.isNotBlank(t.getRoutePath()) && (t.getTypeCode().toString().equals("3") || t.getTypeCode().toString().equals("4"))) {
                  String routePath = t.getRoutePath();
                  routePath = routePath.replace("@staffName", SecurityUtils.getLoginUser().getUser().getNickName());
                  routePath = routePath.replace("@staffCode", SecurityUtils.getUsername());
                  routePath = routePath.replace("@deptCode", visitinfoVo.getDeptCd());
                  routePath = routePath.replace("@admissionNo", visitinfoVo.getInpNo());
                  routePath = routePath.replace("@caseNo", visitinfoVo.getRecordNo());
                  routePath = routePath.replace("@hospitalizedCount", visitinfoVo.getVisitId() + "");
                  routePath = routePath.replace("@staffDeptCode", SecurityUtils.getLoginUser().getUser().getDept().getDeptCode());
                  t.setRoutePath(routePath);
               }

               BusIntegMenuTreeVo t2 = new BusIntegMenuTreeVo();
               BeanUtils.copyProperties(t, t2);
               childrenNew.add(t2);
            });
         }

         BusIntegMenuTreeVo tempVo = new BusIntegMenuTreeVo(temp);
         tempVo.setChildren(childrenNew);
         tempVo.setHisInhosTimeStr(suffixStr);
         tempVo.setPatientId(visitinfoVo.getPatientId());
         tempVo.setVisitId(visitinfoVo.getVisitId());
         tempVo.setInpNo(visitinfoVo.getInpNo());
         tempVo.setIndexType(temp.getCode().equals("em_hp") ? "mrhp" : null);
         if (temp.getCode().equals("mr_doc")) {
            mrDocIndex = i;
         }

         if (temp.getCode().equals("baby_mr_doc")) {
            babyMrDocIndex = i;
         }

         if (StringUtils.isNotBlank(tempVo.getRoutePath()) && tempVo.getRoutePath().equals("inPapersMzNew")) {
            IndexMzVo indexMzVo = this.emrIndexMMenuService.selectIndexMzVoList(visitinfoVo.getPatientId());
            tempVo.setProofId(indexMzVo != null ? indexMzVo.getProofId() : null);
         }

         currMenuTreeList.add(tempVo);
      }

      if (babyMrDocIndex != null) {
         currMenuTreeList.remove(babyMrDocIndex);
      }

      if (mrDocIndex != null) {
         currMenuTreeList.remove(mrDocIndex);
      }

      List<BusIntegMenuTreeVo> currMrDocList = mrDocIndex != null ? this.getMrDocList(busIntegMenuSearchVo) : null;
      if (currMrDocList != null && !currMrDocList.isEmpty()) {
         for(int i = 0; i < currMrDocList.size(); ++i) {
            BusIntegMenuTreeVo temp = (BusIntegMenuTreeVo)currMrDocList.get(i);
            temp.setHisInhosTimeStr(suffixStr);
            temp.setPatientId(visitinfoVo.getPatientId());
            temp.setVisitId(visitinfoVo.getVisitId());
            temp.setInpNo(visitinfoVo.getInpNo());
            currMenuTreeList.add(mrDocIndex + i, temp);
         }
      }

   }

   private List getMrDocList(BusIntegMenuSearchVo busIntegMenuSearchVo) throws Exception {
      String openFlag = null;
      List<String> qcStateNofinishIdList = new ArrayList();
      List<String> qcStateNofinishMrTypeList = new ArrayList();
      if (StringUtils.isNotBlank(busIntegMenuSearchVo.getIndexNofinishQcRedColorFlag()) && busIntegMenuSearchVo.getIndexNofinishQcRedColorFlag().equals("1")) {
         List<String> qcStateList = new ArrayList();
         qcStateList.add("1");
         qcStateList.add("4");
         List<EmrQcList> qcListList = this.emrQcListService.selectEmrSubFileQcList(busIntegMenuSearchVo.getPatientId(), qcStateList, busIntegMenuSearchVo.getQcSection());
         qcStateNofinishIdList = (List)qcListList.stream().map((t) -> t.getMrFileId()).collect(Collectors.toList());
         qcStateNofinishMrTypeList = (List)qcListList.stream().map((t) -> t.getMrType()).collect(Collectors.toList());
      }

      Index indexParam = new Index();
      indexParam.setPatientId(busIntegMenuSearchVo.getPatientId());
      indexParam.setDelFlag("0");
      List<Index> indexList = this.indexService.selectIndexList(indexParam);
      TdCaConsApplyVo tdCaConsApplyVo = new TdCaConsApplyVo();
      tdCaConsApplyVo.setPatientId(busIntegMenuSearchVo.getPatientId());
      List<TdCaConsApplyVo> consApplyVos = this.tdCaConsApplyService.selectTdCaConsApplyList(tdCaConsApplyVo);
      List<String> indexStateList = busIntegMenuSearchVo.getIndexStateList();
      if (busIntegMenuSearchVo.getIndexStateList() != null && !busIntegMenuSearchVo.getIndexStateList().isEmpty()) {
         indexList = this.indexMrStateFilter(indexList, indexStateList);
      }

      Map<String, List<Index>> indexListMap = (Map)indexList.stream().collect(Collectors.groupingBy((t) -> t.getMrTypeCd()));
      List<SysDictData> dictDataList = this.dictTypeService.selectDictDataByType("s003");
      List<BusIntegMenuTreeVo> mrDocList = new ArrayList(1);
      Map<String, List<BusIntegMenuTreeVo>> babyIndexMap = new HashMap(1);

      for(SysDictData mrTypeObj : dictDataList) {
         String mrType = mrTypeObj.getDictValue();
         List<Index> mrIndexList = (List)indexListMap.get(mrType);
         if (mrIndexList != null && !mrIndexList.isEmpty()) {
            BusIntegMenuTreeVo tempMenu = new BusIntegMenuTreeVo();
            tempMenu.setName(mrTypeObj.getDictLabel());
            tempMenu.setCode(mrType);
            if (qcStateNofinishMrTypeList.contains(mrType)) {
               tempMenu.setColorFlag("1");
            }

            List<BusIntegMenuTreeVo> children = new ArrayList(1);

            for(Index index : mrIndexList) {
               String mrTypeTemp = StringUtils.isNotEmpty(index.getMrType()) && index.getMrType().equals("MAINFILE") ? "MAINFILE" : "";
               BusIntegMenuTreeVo tempChild = new BusIntegMenuTreeVo();
               tempChild.setPatientId(busIntegMenuSearchVo.getPatientId());
               tempChild.setCode(index.getId().toString());
               tempChild.setRoutePath("mouldNotes");
               tempChild.setMrType(mrTypeTemp);
               tempChild.setConsFlag("0");
               if (consApplyVos != null && !consApplyVos.isEmpty()) {
                  List<TdCaConsApplyVo> applyVoList = (List)consApplyVos.stream().filter((s) -> s.getMrFileId().toString().equals(index.getId().toString())).collect(Collectors.toList());
                  if (applyVoList != null && !applyVoList.isEmpty()) {
                     tempChild.setConsFlag("1");
                  }
               }

               if ("MAINFILE".equals(mrTypeTemp) && "病程记录".equals(index.getMrFileShowName())) {
                  tempChild.setName(index.getMrFileShowName() + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss", index.getRecoDate()));
               } else {
                  tempChild.setName(index.getMrFileShowName());
               }

               tempChild.setIndexType("index");
               if (qcStateNofinishIdList.contains(String.valueOf(index.getId()))) {
                  tempChild.setColorFlag("1");
               }

               if (StringUtils.isBlank(openFlag)) {
                  openFlag = "1";
                  tempChild.setOpenFlag(openFlag);
               }

               if (!StringUtils.isNotBlank(index.getBabyId())) {
                  children.add(tempChild);
               } else {
                  List<BusIntegMenuTreeVo> babyListTemp = (List)babyIndexMap.get(index.getBabyId());
                  if (babyListTemp == null || babyListTemp != null && babyListTemp.isEmpty()) {
                     babyListTemp = new ArrayList(1);
                  }

                  babyListTemp.add(tempChild);
                  babyIndexMap.put(index.getBabyId(), babyListTemp);
               }
            }

            tempMenu.setChildren(children);
            mrDocList.add(tempMenu);
         }
      }

      int i = 1;

      for(String babyId : babyIndexMap.keySet()) {
         BusIntegMenuTreeVo tempChild = new BusIntegMenuTreeVo();
         tempChild.setCode(babyId);
         tempChild.setName("婴儿病历" + i);
         List<BusIntegMenuTreeVo> babyListTemp = (List)babyIndexMap.get(babyId);
         List<BusIntegMenuTreeVo> colorIndexList = (List)babyListTemp.stream().filter((t) -> StringUtils.isNotEmpty(t.getColorFlag()) && t.getColorFlag().equals("1")).collect(Collectors.toList());
         if (colorIndexList != null && colorIndexList.size() > 0) {
            tempChild.setColorFlag("1");
         }

         tempChild.setChildren((List)babyIndexMap.get(babyId));
         mrDocList.add(tempChild);
         ++i;
      }

      return mrDocList;
   }

   private List indexMrStateFilter(List indexList, List indexStateList) {
      indexList = (List)indexList.stream().filter((t) -> indexStateList.contains(t.getMrState()) || t.getMrType().equals("MAINFILE")).collect(Collectors.toList());
      List<Index> mainIndexList = (List)indexList.stream().filter((t) -> t.getMrType().equals("MAINFILE")).collect(Collectors.toList());
      if (mainIndexList != null && !mainIndexList.isEmpty()) {
         List<SubfileIndex> subfileIndexList = this.subfileIndexService.selectSubfileIndexByMainId(((Index)mainIndexList.get(0)).getId());
         subfileIndexList = subfileIndexList != null && !subfileIndexList.isEmpty() ? (List)subfileIndexList.stream().filter((t) -> indexStateList.contains(t.getMrState())).collect(Collectors.toList()) : null;
         if (subfileIndexList == null || subfileIndexList != null && subfileIndexList.isEmpty()) {
            indexList = (List)indexList.stream().filter((t) -> !t.getMrType().equals("MAINFILE")).collect(Collectors.toList());
         }
      }

      return indexList;
   }

   @DataSource(DataSourceType.mzdb)
   public List busmenuTreeslistMz(BusIntegMenuSearchVo busIntegMenuSearchVo, String menuClass, List mzMrFileInfoList1) throws Exception {
      IndexVo indexVo = new IndexVo();
      indexVo.setPatientId(busIntegMenuSearchVo.getPatientId());
      indexVo.setVisitNo(busIntegMenuSearchVo.getVisitNo());
      List<IndexVo> mzMrFileInfoList = this.indexMapper.selectEmrListByVisitNo(indexVo);
      BusIntegMenu param = new BusIntegMenu();
      param.setIsValid("1");
      param.setMenuClass(menuClass);
      List<BusIntegMenu> busIntegMenuList = this.busIntegMenuMapper.selectBusIntegMenuList(param);
      List<BusIntegMenu> menuList = (List)busIntegMenuList.stream().filter((t) -> t.getParentId() == null).collect(Collectors.toList());
      List<BusIntegMenuTreeVo> menuTreeList = new ArrayList(menuList.size());
      List<BusIntegMenu> childMenuList = (List)busIntegMenuList.stream().filter((t) -> t.getParentId() != null).collect(Collectors.toList());
      List<BusIntegMenuTreeVo> childTreeMenuList = new ArrayList(childMenuList.size());
      childMenuList.forEach((t) -> childTreeMenuList.add(new BusIntegMenuTreeVo(t)));
      Map<Long, List<BusIntegMenuTreeVo>> childMenuListMap = (Map)childTreeMenuList.stream().collect(Collectors.groupingBy((t) -> t.getParentId()));

      for(int i = 0; i < menuList.size(); ++i) {
         BusIntegMenu temp = (BusIntegMenu)menuList.get(i);
         List<BusIntegMenuTreeVo> children = (List)childMenuListMap.get(temp.getId());
         if (children != null) {
            children.forEach((t) -> {
               t.setMzh(busIntegMenuSearchVo.getPatientId());
               t.setVisitNo(busIntegMenuSearchVo.getVisitNo());
               if (StringUtils.isNotBlank(t.getRoutePath()) && (t.getTypeCode().toString().equals("3") || t.getTypeCode().toString().equals("4"))) {
                  String routePath = t.getRoutePath();
                  routePath = routePath.replace("@staffName", SecurityUtils.getLoginUser().getUser().getNickName());
                  routePath = routePath.replace("@staffCode", SecurityUtils.getUsername());
                  routePath = routePath.replace("@deptCode", SecurityUtils.getLoginUser().getUser().getDept().getDeptCode());
                  routePath = routePath.replace("@mzh", busIntegMenuSearchVo.getVisitNo());
                  t.setRoutePath(routePath);
               }

            });
         } else if (temp.getCode().equals("mz_mr_doc") && CollectionUtils.isNotEmpty(mzMrFileInfoList)) {
            children = (List<BusIntegMenuTreeVo>)(CollectionUtils.isEmpty(children) ? new ArrayList(1) : children);

            for(int j = 0; j < mzMrFileInfoList.size(); ++j) {
               IndexVo mzMrFileInfo = (IndexVo)mzMrFileInfoList.get(i);
               BusIntegMenuTreeVo tempMenu = new BusIntegMenuTreeVo();
               tempMenu.setId(mzMrFileInfo.getId());
               tempMenu.setCode("mz_mr_doc");
               tempMenu.setName(mzMrFileInfo.getMrFileName());
               tempMenu.setMzh(busIntegMenuSearchVo.getPatientId());
               tempMenu.setVisitNo(busIntegMenuSearchVo.getVisitNo());
               children.add(tempMenu);
            }
         }

         BusIntegMenuTreeVo tempVo = new BusIntegMenuTreeVo(temp);
         tempVo.setChildren(children);
         tempVo.setMzh(busIntegMenuSearchVo.getPatientId());
         tempVo.setVisitNo(busIntegMenuSearchVo.getVisitNo());
         menuTreeList.add(tempVo);
      }

      return menuTreeList;
   }
}
