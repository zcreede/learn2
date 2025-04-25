package com.emr.project.emr.service.impl;

import com.emr.common.utils.SecurityUtils;
import com.emr.framework.aspectj.lang.annotation.DataSource;
import com.emr.framework.aspectj.lang.enums.DataSourceType;
import com.emr.framework.config.EMRConfig;
import com.emr.project.emr.domain.SubfileIndex;
import com.emr.project.emr.domain.vo.IndexMzVo;
import com.emr.project.emr.domain.vo.IndexVo;
import com.emr.project.emr.mapper.IndexMapper;
import com.emr.project.emr.service.IEmrIndexMMenuService;
import com.emr.project.emr.service.ISubfileIndexService;
import com.emr.project.mrhp.domain.vo.MrHpVo;
import com.emr.project.mrhp.service.IMrHpService;
import com.emr.project.pat.domain.vo.VisitinfoVo;
import com.emr.project.pat.service.IVisitinfoService;
import com.emr.project.sys.domain.BusIntegMenu;
import com.emr.project.sys.mapper.BusIntegMenuMapper;
import com.emr.project.system.domain.SysDictData;
import com.emr.project.system.service.ISysDictDataService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmrIndexMMenuServiceImpl implements IEmrIndexMMenuService {
   @Autowired
   private BusIntegMenuMapper busIntegMenuMapper;
   @Autowired
   private ISubfileIndexService subfileIndexService;
   @Resource
   private ISysDictDataService sysDictDataService;
   @Autowired
   private IndexMapper indexMapper;
   @Autowired
   private IVisitinfoService visitinfoService;
   @Autowired
   private IMrHpService mrHpService;

   public List selectPatEmrPrintList(IndexVo indexVo) throws Exception {
      List<IndexVo> resultList = new ArrayList();
      List<SysDictData> dictType = this.sysDictDataService.selectDictDataByType("s003");
      int i = 0;
      List<IndexVo> emrList = this.indexMapper.selectEmrListByPat(indexVo);
      if (emrList != null && !emrList.isEmpty()) {
         Map<String, List<IndexVo>> emrMap = (Map)emrList.stream().collect(Collectors.groupingBy((s) -> s.getMrTypeCd()));
         Iterator visitinfoVo = dictType.iterator();

         label160:
         while(true) {
            List<IndexVo> indexVoList;
            IndexVo index;
            label158:
            while(true) {
               if (!visitinfoVo.hasNext()) {
                  break label160;
               }

               SysDictData sysDictData = (SysDictData)visitinfoVo.next();
               indexVoList = (List)emrMap.get(sysDictData.getDictValue());
               if (indexVoList != null && !indexVoList.isEmpty()) {
                  index = new IndexVo();
                  index.setMrTypeCd(sysDictData.getDictValue());
                  index.setMrTypeName(sysDictData.getDictLabel());
                  index.setMrTypeCdIndex(i++);

                  for(IndexVo indexVo1 : indexVoList) {
                     indexVo1.setRoutePath("mouldNotes");
                     if (StringUtils.isNotEmpty(indexVo1.getMrServPath())) {
                        String reOld = EMRConfig.getProfile();
                        String reNew = EMRConfig.getNfsPdfurl();
                        String path = indexVo1.getMrServPath().replace(reOld, reNew);
                        indexVo1.setMrServPath(path);
                     }
                  }

                  if (!sysDictData.getDictValue().equals("01")) {
                     break;
                  }

                  List<SubfileIndex> subList = this.subfileIndexService.selectSubFileByPat(indexVo);
                  if (subList != null && !subList.isEmpty()) {
                     Iterator var32 = indexVoList.iterator();

                     while(true) {
                        if (!var32.hasNext()) {
                           break label158;
                        }

                        IndexVo indexVo1 = (IndexVo)var32.next();
                        indexVo1.setMrFileShowName("病程合并记录");
                     }
                  }
               }
            }

            index.setMrFileShowName(index.getMrTypeName());
            index.setChildList(indexVoList);
            resultList.add(index);
         }
      }

      List<VisitinfoVo> visitinfoVos = this.visitinfoService.selectVisitinfosById(indexVo.getPatientId());
      VisitinfoVo visitinfoVo = (VisitinfoVo)((List)visitinfoVos.stream().filter((t) -> t.getPatientId().equals(indexVo.getPatientId())).collect(Collectors.toList())).get(0);
      BusIntegMenu param = new BusIntegMenu();
      param.setIsValid("1");
      param.setMenuClass("5");
      List<BusIntegMenu> busIntegMenuList = this.busIntegMenuMapper.selectBusIntegMenuList(param);
      List<BusIntegMenu> menuList = (List)busIntegMenuList.stream().filter((t) -> t.getParentId() == null).collect(Collectors.toList());
      List<BusIntegMenu> childMenuList = (List)busIntegMenuList.stream().filter((t) -> t.getParentId() != null).collect(Collectors.toList());
      Map<Long, List<BusIntegMenu>> childMenuListMap = (Map)childMenuList.stream().collect(Collectors.groupingBy(BusIntegMenu::getParentId));
      MrHpVo mrHpVo = this.mrHpService.selectMrHpByPatientId(indexVo.getPatientId());

      for(BusIntegMenu menu : menuList) {
         if ("em_hp".equals(menu.getCode())) {
            if (mrHpVo != null) {
               IndexVo index = new IndexVo();
               if ("mzRecord".equals(menu.getCode())) {
                  index.setDataType("mz_new");
                  index.setId(indexVo.getFileId());
               }

               if ("doc_inhospital".equals(menu.getCode())) {
                  index.setDataType("mz_new");
                  index.setId(indexVo.getProofId());
               }

               Long id = menu.getId();
               index.setMrTypeCd(menu.getCode());
               index.setMrTypeName(menu.getName());
               index.setMrTypeCdIndex(i++);
               index.setMrFileShowName(index.getMrTypeName());
               index.setRoutePath(menu.getRoutePath());
               index.setMenuTypeCode(menu.getTypeCode() != null ? menu.getTypeCode().toString() : null);
               if (childMenuListMap.containsKey(id)) {
                  List<IndexVo> childList = new ArrayList();
                  int j = 0;

                  for(BusIntegMenu busIntegMenu : (List)childMenuListMap.get(id)) {
                     IndexVo indexVo1 = new IndexVo();
                     indexVo1.setMrTypeCd(busIntegMenu.getCode());
                     indexVo1.setMrTypeName(busIntegMenu.getName());
                     indexVo1.setMrTypeCdIndex(j++);
                     indexVo1.setMrFileShowName(indexVo1.getMrTypeName());
                     if (StringUtils.isNotBlank(busIntegMenu.getRoutePath()) && (busIntegMenu.getTypeCode().toString().equals("3") || busIntegMenu.getTypeCode().toString().equals("4"))) {
                        String routePath = busIntegMenu.getRoutePath();
                        routePath = routePath.replace("@staffName", SecurityUtils.getLoginUser().getUser().getNickName());
                        routePath = routePath.replace("@staffCode", SecurityUtils.getUsername());
                        routePath = routePath.replace("@deptCode", visitinfoVo.getDeptCd());
                        routePath = routePath.replace("@admissionNo", visitinfoVo.getInpNo());
                        routePath = routePath.replace("@caseNo", visitinfoVo.getRecordNo());
                        routePath = routePath.replace("@hospitalizedCount", visitinfoVo.getVisitId() + "");
                        busIntegMenu.setRoutePath(routePath);
                     }

                     indexVo1.setBrowserType(busIntegMenu.getBrowserType());
                     indexVo1.setBrowserTypeName(busIntegMenu.getBrowserTypeName());
                     indexVo1.setRoutePath(busIntegMenu.getRoutePath());
                     indexVo1.setMenuTypeCode(busIntegMenu.getTypeCode() != null ? busIntegMenu.getTypeCode().toString() : null);
                     childList.add(indexVo1);
                  }

                  index.setChildList(childList);
               }

               resultList.add(index);
            }
         } else {
            IndexVo index = new IndexVo();
            if ("mzRecord".equals(menu.getCode())) {
               index.setDataType("mz_new");
               index.setId(indexVo.getFileId());
            }

            if ("doc_inhospital".equals(menu.getCode())) {
               index.setDataType("mz_new");
               index.setId(indexVo.getProofId());
            }

            Long id = menu.getId();
            index.setMrTypeCd(menu.getCode());
            index.setMrTypeName(menu.getName());
            index.setMrTypeCdIndex(i++);
            index.setMrFileShowName(index.getMrTypeName());
            index.setRoutePath(menu.getRoutePath());
            index.setMenuTypeCode(menu.getTypeCode() != null ? menu.getTypeCode().toString() : null);
            if (childMenuListMap.containsKey(id)) {
               List<IndexVo> childList = new ArrayList();
               int j = 0;

               for(BusIntegMenu busIntegMenu : (List)childMenuListMap.get(id)) {
                  IndexVo indexVo1 = new IndexVo();
                  indexVo1.setMrTypeCd(busIntegMenu.getCode());
                  indexVo1.setMrTypeName(busIntegMenu.getName());
                  indexVo1.setMrTypeCdIndex(j++);
                  indexVo1.setMrFileShowName(indexVo1.getMrTypeName());
                  if (StringUtils.isNotBlank(busIntegMenu.getRoutePath()) && (busIntegMenu.getTypeCode().toString().equals("3") || busIntegMenu.getTypeCode().toString().equals("4"))) {
                     String routePath = busIntegMenu.getRoutePath();
                     routePath = routePath.replace("@staffName", SecurityUtils.getLoginUser().getUser().getNickName());
                     routePath = routePath.replace("@staffCode", SecurityUtils.getUsername());
                     routePath = routePath.replace("@deptCode", visitinfoVo.getDeptCd());
                     routePath = routePath.replace("@admissionNo", visitinfoVo.getInpNo());
                     routePath = routePath.replace("@caseNo", visitinfoVo.getRecordNo());
                     routePath = routePath.replace("@hospitalizedCount", visitinfoVo.getVisitId() + "");
                     busIntegMenu.setRoutePath(routePath);
                  }

                  indexVo1.setBrowserType(busIntegMenu.getBrowserType());
                  indexVo1.setBrowserTypeName(busIntegMenu.getBrowserTypeName());
                  indexVo1.setRoutePath(busIntegMenu.getRoutePath());
                  indexVo1.setMenuTypeCode(busIntegMenu.getTypeCode() != null ? busIntegMenu.getTypeCode().toString() : null);
                  childList.add(indexVo1);
               }

               index.setChildList(childList);
            }

            resultList.add(index);
         }
      }

      return resultList;
   }

   @DataSource(DataSourceType.mzdb)
   public IndexMzVo selectIndexMzVoList(String patientId) {
      return this.indexMapper.selectIndexMzVoList(patientId);
   }
}
