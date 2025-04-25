package com.emr.project.operation.service.impl;

import com.emr.common.utils.PinYinUtil;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.StringUtils;
import com.emr.project.operation.domain.TmNaTcwh;
import com.emr.project.operation.domain.req.TcwhListReq;
import com.emr.project.operation.mapper.TmNaTcwhMapper;
import com.emr.project.operation.service.HisProcService;
import com.emr.project.operation.service.ITmNaTcwhService;
import com.emr.project.system.domain.SysUser;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TmNaTcwhServiceImpl implements ITmNaTcwhService {
   @Autowired
   private TmNaTcwhMapper tmNaTcwhMapper;
   @Autowired
   private HisProcService hisProcService;

   public TmNaTcwh selectTmNaTcwhById(Long id) throws Exception {
      return this.tmNaTcwhMapper.selectTmNaTcwhById(id);
   }

   public List selectTmNaTcwhList(TcwhListReq req) throws Exception {
      if (StringUtils.isNotEmpty(req.getShareClass()) && "4".equals(req.getShareClass())) {
         req.setShareClass((String)null);
         req.setFlag("1");
      }

      return this.tmNaTcwhMapper.selectTmNaTcwhInfoList(req);
   }

   public int insertTmNaTcwh(TmNaTcwh tmNaTcwh) throws Exception {
      return this.tmNaTcwhMapper.insertTmNaTcwh(tmNaTcwh);
   }

   public int save(List tmNaTcwhs) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      List<TmNaTcwh> addList = (List)tmNaTcwhs.stream().filter((t) -> t.getId() == null).collect(Collectors.toList());
      List<TmNaTcwh> updateList = (List)tmNaTcwhs.stream().filter((t) -> t.getId() != null).collect(Collectors.toList());

      for(TmNaTcwh item : addList) {
         item.setId(this.tmNaTcwhMapper.selectMaxId());
         item.setOperatorNo(user.getUserName());
         item.setHospitalCode(user.getHospital().getOrgCode());
         item.setWardNo(user.getDept().getDeptCode());
         item.setPackagePym(PinYinUtil.getPinYinHeadChar(item.getPackageName()));
         item.setPackageNo(this.hisProcService.getDocumentOrBillNo(user.getUserName(), 5));
      }

      for(TmNaTcwh item : updateList) {
         item.setPackagePym(PinYinUtil.getPinYinHeadChar(item.getPackageName()));
      }

      if (CollectionUtils.isNotEmpty(addList)) {
         this.tmNaTcwhMapper.batchInsert(addList);
      }

      if (CollectionUtils.isNotEmpty(updateList)) {
         this.tmNaTcwhMapper.batchUpdate(updateList);
      }

      return 1;
   }

   public int updateTmNaTcwh(TmNaTcwh tmNaTcwh) throws Exception {
      return this.tmNaTcwhMapper.updateTmNaTcwh(tmNaTcwh);
   }

   public int deleteTmNaTcwhByIds(Long[] ids) throws Exception {
      return this.tmNaTcwhMapper.deleteTmNaTcwhByIds(ids);
   }

   public int deleteTmNaTcwhById(Long id) throws Exception {
      return this.tmNaTcwhMapper.deleteTmNaTcwhById(id);
   }

   public List selectListFromMx() {
      return this.tmNaTcwhMapper.selectListFromMx();
   }
}
