package com.emr.project.system.service.impl;

import com.emr.common.utils.CardNoGenUtil;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.common.utils.StringUtils;
import com.emr.framework.aspectj.lang.enums.DataSourceType;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.domain.WorkLoadItem;
import com.emr.project.system.mapper.WorkLoadItemMapper;
import com.emr.project.system.service.IWorkLoadItemService;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkLoadItemServiceImpl implements IWorkLoadItemService {
   @Autowired
   private WorkLoadItemMapper workLoadItemMapper;
   private static final String PRE_CODE = "XM";

   public WorkLoadItem selectWorkLoadItemById(Long id) {
      return this.workLoadItemMapper.selectWorkLoadItemById(id);
   }

   public List selectWorkLoadItemList(WorkLoadItem workLoadItem) {
      return this.workLoadItemMapper.selectWorkLoadItemList(workLoadItem);
   }

   public int insertWorkLoadItem(WorkLoadItem workLoadItem) throws Exception {
      workLoadItem.setId(SnowIdUtils.uniqueLong());
      SysUser loginUser = SecurityUtils.getLoginUser().getUser();
      workLoadItem.setCrePerCode(loginUser.getUserName());
      workLoadItem.setCrePerName(loginUser.getNickName());
      workLoadItem.setOrgCd(loginUser.getHospital().getOrgCode());
      String initCode = "XM00000001";
      String maxCode = this.workLoadItemMapper.selectMaxCode("XM");
      if (StringUtils.isNotEmpty(maxCode)) {
         String replace = maxCode.replace("XM", "");
         BigDecimal ss = new BigDecimal(replace);
         BigDecimal add = ss.add(BigDecimal.ONE);
         initCode = "XM" + CardNoGenUtil.getSequence(add.longValue());
      }

      if (workLoadItem.getSortNo() == null) {
         Integer maxSortNo = this.workLoadItemMapper.selectMaxSortNo();
         int sortNo = 10;
         if (maxSortNo != null) {
            sortNo = maxSortNo + 10;
         }

         workLoadItem.setSortNo(sortNo);
      }

      workLoadItem.setItemCode(initCode);
      return this.workLoadItemMapper.insertWorkLoadItem(workLoadItem);
   }

   public int updateWorkLoadItem(WorkLoadItem workLoadItem) throws Exception {
      SysUser loginUser = SecurityUtils.getLoginUser().getUser();
      workLoadItem.setAltPerCode(loginUser.getUserName());
      workLoadItem.setAltPerName(loginUser.getNickName());
      return this.workLoadItemMapper.updateWorkLoadItem(workLoadItem);
   }

   public int deleteWorkLoadItemByIds(Long[] ids) {
      return this.workLoadItemMapper.deleteWorkLoadItemByIds(ids);
   }

   public int deleteWorkLoadItemById(Long id) {
      return this.workLoadItemMapper.deleteWorkLoadItemById(id);
   }

   public List selectDsPreserveOutList() throws Exception {
      return this.workLoadItemMapper.selectDsPreserveOutList(DataSourceType.workLoad.toString());
   }
}
