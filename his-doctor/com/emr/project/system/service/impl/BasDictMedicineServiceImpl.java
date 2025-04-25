package com.emr.project.system.service.impl;

import com.emr.common.constant.CommonConstants;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.project.system.domain.BasDictMedicine;
import com.emr.project.system.domain.SysDept;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.domain.TmplMedicineDept;
import com.emr.project.system.domain.vo.BasDictMedicineVo;
import com.emr.project.system.mapper.BasDictMedicineMapper;
import com.emr.project.system.service.IBasDictMedicineService;
import com.emr.project.system.service.IBasTmplDiseaseService;
import com.emr.project.system.service.ISysDeptService;
import com.emr.project.system.service.ISysEmrConfigService;
import com.emr.project.system.service.ITmplMedicineDeptService;
import com.emr.project.tmpl.service.ITmplDeptService;
import com.emr.project.tmpl.service.impl.TmplDeptServiceImpl;
import com.emr.project.tmpl.service.impl.TmplIndexServiceImpl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BasDictMedicineServiceImpl implements IBasDictMedicineService {
   @Autowired
   private BasDictMedicineMapper basDictMedicineMapper;
   @Autowired
   private IBasTmplDiseaseService basTmplDiseaseService;
   @Autowired
   private ISysDeptService deptService;
   @Autowired
   private ITmplMedicineDeptService tmplMedicineDeptService;
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;
   @Autowired
   private TmplIndexServiceImpl tmplIndexService;
   @Autowired
   private ITmplDeptService iTmplDeptService;
   @Autowired
   private TmplDeptServiceImpl tmplDeptService;

   public BasDictMedicineVo selectBasDictMedicineById(Long id) {
      BasDictMedicine basDictMedicine = this.basDictMedicineMapper.selectBasDictMedicineById(id);
      BasDictMedicineVo basDictMedicineVo = new BasDictMedicineVo();
      BeanUtils.copyProperties(basDictMedicine, basDictMedicineVo);
      TmplMedicineDept param = new TmplMedicineDept();
      param.setMedicineId(id);
      List<TmplMedicineDept> deptList = this.tmplMedicineDeptService.selectTmplMedicineDeptList(param);
      if (CollectionUtils.isNotEmpty(deptList)) {
         List<String> deptCodeList = (List)deptList.stream().map((t) -> t.getDeptCd()).collect(Collectors.toList());
         basDictMedicineVo.setDeptCodeList(deptCodeList);
      }

      if (basDictMedicine != null && StringUtils.isNotBlank(basDictMedicine.getSoleDeptCode())) {
         List<String> soleDeptList = Arrays.asList(StringUtils.split(basDictMedicine.getSoleDeptCode(), ","));
         basDictMedicineVo.setSoleDeptCodeList(soleDeptList);
      }

      return basDictMedicineVo;
   }

   public BasDictMedicine selectBasDictMedicineByName(String name) {
      return this.basDictMedicineMapper.selectBasDictMedicineByName(name);
   }

   public List selectBasDictMedicineTree(BasDictMedicine basDictMedicine) throws Exception {
      BasDictMedicine param = new BasDictMedicine();
      List<BasDictMedicine> listAll = this.basDictMedicineMapper.selectBasDictMedicineList(param);
      List<BasDictMedicine> searchList = this.basDictMedicineMapper.selectBasDictMedicineList(basDictMedicine);
      List<BasDictMedicine> firstLeaveList = new ArrayList(1);
      if (searchList != null && !searchList.isEmpty()) {
         if (StringUtils.isNotBlank(basDictMedicine.getName())) {
            firstLeaveList = (List)searchList.stream().filter((t) -> t.getLevelCd().equals("1")).collect(Collectors.toList());

            for(BasDictMedicine temp : firstLeaveList) {
               List<BasDictMedicine> children = (List)searchList.stream().filter((t) -> t.getLevelCd().equals("2") && t.getParentCode().equals(temp.getCode())).collect(Collectors.toList());
               temp.setChildren(children);
            }
         } else {
            firstLeaveList = (List)listAll.stream().filter((t) -> t.getLevelCd().equals("1")).collect(Collectors.toList());

            for(BasDictMedicine temp : firstLeaveList) {
               List<BasDictMedicine> children = (List)searchList.stream().filter((t) -> t.getLevelCd().equals("2") && t.getParentCode().equals(temp.getCode())).collect(Collectors.toList());
               temp.setChildren(children);
            }
         }
      }

      return firstLeaveList;
   }

   public List selectBasDictMedicineTreeByDept(BasDictMedicine basDictMedicine) throws Exception {
      BasDictMedicine firstLeaveParam = new BasDictMedicine();
      firstLeaveParam.setLevelCd("2");
      firstLeaveParam.setValidFlag("1");
      List<BasDictMedicine> secondLevelList = this.basDictMedicineMapper.selectBasDictMedicineList(firstLeaveParam);
      TmplMedicineDept param = new TmplMedicineDept();
      param.setDeptCd(SecurityUtils.getLoginUser().getUser().getDept().getDeptCode());
      param.setValidFlag("1");
      String configValue = this.sysEmrConfigService.selectSysEmrConfigByKey("0054");
      if (StringUtils.isNotBlank(configValue)) {
         List<String> configValueList = Arrays.asList(configValue.split(","));
         if (configValueList.contains(SecurityUtils.getLoginUser().getUser().getDept().getDeptCode())) {
            param.setDeptCd("000000");
         }
      }

      List<TmplMedicineDept> tmplMedicineDeptList = this.tmplMedicineDeptService.selectTmplMedicineDeptList(param);
      if (CollectionUtils.isNotEmpty(tmplMedicineDeptList)) {
         List<Long> medicineIdList = (List)tmplMedicineDeptList.stream().map((t) -> t.getMedicineId()).collect(Collectors.toList());
         List<BasDictMedicine> basDictMedicineList = null;
         if (StringUtils.isNotBlank(basDictMedicine.getName())) {
            basDictMedicineList = this.basDictMedicineMapper.selectBasDictMedicineList(basDictMedicine);
         } else {
            BasDictMedicine medicineParam = new BasDictMedicine();
            basDictMedicineList = this.basDictMedicineMapper.selectBasDictMedicineList(medicineParam);
         }

         if (CollectionUtils.isNotEmpty(basDictMedicineList)) {
            secondLevelList = (List)basDictMedicineList.stream().filter((t) -> medicineIdList.contains(t.getId())).collect(Collectors.toList());
         }
      }

      return secondLevelList;
   }

   public List selectBasDictMedicineList(BasDictMedicine basDictMedicine) {
      return this.basDictMedicineMapper.selectBasDictMedicineList(basDictMedicine);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public BasDictMedicine insertBasDictMedicine(BasDictMedicineVo basDictMedicine) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      basDictMedicine.setId(SnowIdUtils.uniqueLong());
      basDictMedicine.setCode(basDictMedicine.getId().toString());
      basDictMedicine.setCrePerCode(user.getUserName());
      basDictMedicine.setCrePerName(user.getNickName());
      if (basDictMedicine.getSoleDeptCodeList() != null && basDictMedicine.getSoleDeptCodeList().size() > 0) {
         String soleDeptCode = String.join(",", basDictMedicine.getSoleDeptCodeList());
         basDictMedicine.setSoleDeptCode(soleDeptCode);
      }

      this.basDictMedicineMapper.insertBasDictMedicine(basDictMedicine);
      if (basDictMedicine.getLevelCd().equals("2") && CollectionUtils.isNotEmpty(basDictMedicine.getDeptCodeList())) {
         this.addTmplMedicineDepts(basDictMedicine);
      }

      return basDictMedicine;
   }

   private void addTmplMedicineDepts(BasDictMedicineVo basDictMedicine) throws Exception {
      List<SysDept> deptList = null;
      SysDept allDept = new SysDept();
      allDept.setOrgCode(SecurityUtils.getLoginUser().getUser().getDept().getOrgCode());
      allDept.setDeptId(CommonConstants.SYSTEM.ALL_DEPT_ID);
      allDept.setDeptCode("000000");
      allDept.setDeptName("全部科室");
      if (basDictMedicine.getCode().equals("1111")) {
         deptList = new ArrayList(1);
         deptList.add(allDept);
      } else {
         deptList = this.deptService.selectListByDeptCodeList(SecurityUtils.getLoginUser().getUser().getDept().getOrgCode(), basDictMedicine.getDeptCodeList());
         deptList = (List<SysDept>)(CollectionUtils.isNotEmpty(deptList) ? deptList : new ArrayList(1));
         if (basDictMedicine.getDeptCodeList() != null && basDictMedicine.getDeptCodeList().contains("000000")) {
            deptList.add(allDept);
         }
      }

      if (CollectionUtils.isNotEmpty(deptList)) {
         List<TmplMedicineDept> medicineDeptList = new ArrayList(basDictMedicine.getDeptCodeList().size());

         for(SysDept dept : deptList) {
            TmplMedicineDept medicineDept = new TmplMedicineDept();
            medicineDept.setId(SnowIdUtils.uniqueLong());
            medicineDept.setOrgCd(dept.getOrgCode());
            medicineDept.setMedicineId(basDictMedicine.getId());
            medicineDept.setMedicineCode(basDictMedicine.getCode());
            medicineDept.setDeptCd(dept.getDeptCode());
            medicineDept.setDeptName(dept.getDeptName());
            medicineDept.setDeptId(dept.getDeptId());
            medicineDept.setValidFlag(basDictMedicine.getValidFlag());
            medicineDept.setCrePerCode(SecurityUtils.getLoginUser().getUser().getUserName());
            medicineDept.setCrePerName(SecurityUtils.getLoginUser().getUser().getNickName());
            medicineDeptList.add(medicineDept);
         }

         this.tmplMedicineDeptService.insertList(medicineDeptList);

         for(Long tempId : this.iTmplDeptService.getTempIdsByDepts(basDictMedicine.getId())) {
            this.tmplDeptService.deleteTmplDeptByTempId(tempId);
            this.tmplIndexService.insertTmplDeptList(basDictMedicine.getDeptCodeList(), tempId);
         }
      }

   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void updateBasDictMedicine(BasDictMedicineVo basDictMedicine) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      basDictMedicine.setAltPerCode(user.getUserName());
      basDictMedicine.setAltPerName(user.getNickName());
      if (basDictMedicine.getSoleDeptCodeList() != null && basDictMedicine.getSoleDeptCodeList().size() > 0) {
         String soleDeptCode = String.join(",", basDictMedicine.getSoleDeptCodeList());
         basDictMedicine.setSoleDeptCode(soleDeptCode);
      } else {
         basDictMedicine.setSoleDeptCode("");
      }

      this.basDictMedicineMapper.updateBasDictMedicine(basDictMedicine);
      if (basDictMedicine.getLevelCd().equals("2") && CollectionUtils.isNotEmpty(basDictMedicine.getDeptCodeList())) {
         this.tmplMedicineDeptService.deleteTmplMedicineDeptByMedicineId(basDictMedicine.getId());
      }

      this.addTmplMedicineDepts(basDictMedicine);
   }

   public int deleteBasDictMedicineByIds(Long[] ids) {
      return this.basDictMedicineMapper.deleteBasDictMedicineByIds(ids);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public int deleteBasDictMedicineById(Long id) {
      BasDictMedicine basDictMedicine = this.basDictMedicineMapper.selectBasDictMedicineById(id);
      if (basDictMedicine != null) {
         if (basDictMedicine.getLevelCd().equals("1")) {
            BasDictMedicine param = new BasDictMedicine();
            param.setParentCode(basDictMedicine.getCode());
            List<BasDictMedicine> list = this.basDictMedicineMapper.selectBasDictMedicineList(param);
            List<Long> childrenIds = (List)list.stream().map((t) -> t.getId()).collect(Collectors.toList());
            if (childrenIds != null && !childrenIds.isEmpty()) {
               this.basTmplDiseaseService.deleteBasTmplDiseaseByMedicineIds(childrenIds);
            }

            this.basDictMedicineMapper.deleteBasDictMedicineByParentCode(basDictMedicine.getCode());
         } else if (basDictMedicine.getLevelCd().equals("2")) {
            List<Long> childrenIds = new ArrayList(1);
            childrenIds.add(id);
            this.basTmplDiseaseService.deleteBasTmplDiseaseByMedicineIds(childrenIds);
         }
      }

      return this.basDictMedicineMapper.deleteBasDictMedicineById(id);
   }

   public List selectListByIds(List list) {
      List<BasDictMedicine> resList = null;
      if (CollectionUtils.isNotEmpty(list)) {
         resList = this.basDictMedicineMapper.selectListByIds(list);
      }

      return resList;
   }

   public List selectListByCodes(List list) {
      List<BasDictMedicine> resList = null;
      if (CollectionUtils.isNotEmpty(list)) {
         resList = this.basDictMedicineMapper.selectListByCodes(list);
      }

      return resList;
   }
}
