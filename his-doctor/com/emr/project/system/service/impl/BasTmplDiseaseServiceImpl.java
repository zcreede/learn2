package com.emr.project.system.service.impl;

import com.emr.common.exception.CustomException;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.project.system.domain.BasTmplDisease;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.mapper.BasTmplDiseaseMapper;
import com.emr.project.system.service.IBasTmplDiseaseService;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BasTmplDiseaseServiceImpl implements IBasTmplDiseaseService {
   @Autowired
   private BasTmplDiseaseMapper basTmplDiseaseMapper;

   public BasTmplDisease selectBasTmplDiseaseById(Long id) {
      return this.basTmplDiseaseMapper.selectBasTmplDiseaseById(id);
   }

   public List selectBasTmplDiseaseList(BasTmplDisease basTmplDisease) {
      return this.basTmplDiseaseMapper.selectBasTmplDiseaseList(basTmplDisease);
   }

   public List selectDiseaseList(BasTmplDisease basTmplDisease) throws Exception {
      return this.basTmplDiseaseMapper.basTmplDiseaseTreeSelect(basTmplDisease);
   }

   public int insertBasTmplDisease(BasTmplDisease basTmplDisease) {
      basTmplDisease.setId(SnowIdUtils.uniqueLong());
      basTmplDisease.setCode(basTmplDisease.getId().toString());
      boolean diseaseIsExist = this.getDiseaseIsExist(basTmplDisease);
      if (diseaseIsExist) {
         throw new CustomException("病种编码重复");
      } else {
         SysUser user = SecurityUtils.getLoginUser().getUser();
         basTmplDisease.setCrePerCode(user.getUserName());
         basTmplDisease.setCrePerName(user.getNickName());
         return this.basTmplDiseaseMapper.insertBasTmplDisease(basTmplDisease);
      }
   }

   private boolean getDiseaseIsExist(BasTmplDisease basTmplDisease) {
      int count = this.basTmplDiseaseMapper.getDiseaseIsExistCount(basTmplDisease);
      return count > 0;
   }

   public int updateBasTmplDisease(BasTmplDisease basTmplDisease) {
      boolean diseaseIsExist = this.getDiseaseIsExist(basTmplDisease);
      if (diseaseIsExist) {
         throw new CustomException("病种编码重复");
      } else {
         SysUser user = SecurityUtils.getLoginUser().getUser();
         basTmplDisease.setAltPerCode(user.getUserName());
         basTmplDisease.setAltPerName(user.getNickName());
         return this.basTmplDiseaseMapper.updateBasTmplDisease(basTmplDisease);
      }
   }

   public int deleteBasTmplDiseaseByIds(Long[] ids) {
      return this.basTmplDiseaseMapper.deleteBasTmplDiseaseByIds(ids);
   }

   public int deleteBasTmplDiseaseById(Long id) {
      int count = this.basTmplDiseaseMapper.selectBasTmplDiseaseIsUse(id);
      if (count > 0) {
         throw new CustomException("该病种被引用,不允许删除");
      } else {
         return this.basTmplDiseaseMapper.deleteBasTmplDiseaseById(id);
      }
   }

   public void deleteBasTmplDiseaseByMedicineIds(List ids) {
      this.basTmplDiseaseMapper.deleteBasTmplDiseaseByMedicineIds(ids);
   }

   public int updateBasTmplDiseaseValidFlag(BasTmplDisease basTmplDisease) {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      basTmplDisease.setAltPerCode(user.getUserName());
      basTmplDisease.setAltPerName(user.getNickName());
      return this.basTmplDiseaseMapper.updateBasTmplDisease(basTmplDisease);
   }

   public List selectListByMedicineIds(List ids) throws Exception {
      List<BasTmplDisease> list = null;
      if (CollectionUtils.isNotEmpty(ids)) {
         list = this.basTmplDiseaseMapper.selectListByMedicineIds(ids);
      }

      return list;
   }
}
