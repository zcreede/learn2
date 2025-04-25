package com.emr.project.operation.service.impl;

import com.emr.common.utils.PinYinUtil;
import com.emr.common.utils.StringUtils;
import com.emr.project.operation.domain.TmNaTcwhMx;
import com.emr.project.operation.domain.req.TcwhMxReq;
import com.emr.project.operation.mapper.TmNaTcwhMxMapper;
import com.emr.project.operation.service.ITmNaTcwhMxService;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TmNaTcwhMxServiceImpl implements ITmNaTcwhMxService {
   @Autowired
   private TmNaTcwhMxMapper tmNaTcwhMxMapper;

   public TmNaTcwhMx selectTmNaTcwhMxById(Long id) throws Exception {
      return this.tmNaTcwhMxMapper.selectTmNaTcwhMxById(id);
   }

   public List selectTmNaTcwhMxList(TmNaTcwhMx tmNaTcwhMx) throws Exception {
      return this.tmNaTcwhMxMapper.selectTmNaTcwhMxList(tmNaTcwhMx);
   }

   public List selectTmNaTcwhMxInfoList(TcwhMxReq req) throws Exception {
      return null;
   }

   public int insertTmNaTcwhMx(TmNaTcwhMx tmNaTcwhMx) throws Exception {
      return this.tmNaTcwhMxMapper.insertTmNaTcwhMx(tmNaTcwhMx);
   }

   public int save(List tmNaTcwhMxs) throws Exception {
      List<TmNaTcwhMx> addList = (List)tmNaTcwhMxs.stream().filter((t) -> t.getId() == null).collect(Collectors.toList());
      List<TmNaTcwhMx> updateList = (List)tmNaTcwhMxs.stream().filter((t) -> t.getId() != null).collect(Collectors.toList());

      for(TmNaTcwhMx item : addList) {
         item.setId(this.tmNaTcwhMxMapper.selectMaxId());
         if (StringUtils.isNotEmpty(item.getChargeName())) {
            item.setChargeNamePym(PinYinUtil.getPinYinHeadChar(item.getChargeName()));
         }
      }

      for(TmNaTcwhMx item : updateList) {
         if (StringUtils.isNotEmpty(item.getChargeName())) {
            item.setChargeNamePym(PinYinUtil.getPinYinHeadChar(item.getChargeName()));
         }
      }

      if (CollectionUtils.isNotEmpty(addList)) {
         this.tmNaTcwhMxMapper.batchInsert(addList);
      }

      if (CollectionUtils.isNotEmpty(updateList)) {
         this.tmNaTcwhMxMapper.batchUpdate(updateList);
      }

      return 1;
   }

   public int updateTmNaTcwhMx(TmNaTcwhMx tmNaTcwhMx) throws Exception {
      return this.tmNaTcwhMxMapper.updateTmNaTcwhMx(tmNaTcwhMx);
   }

   public int deleteTmNaTcwhMxByIds(Long[] ids) throws Exception {
      return this.tmNaTcwhMxMapper.deleteTmNaTcwhMxByIds(ids);
   }

   public int deleteTmNaTcwhMxById(Long id) throws Exception {
      return this.tmNaTcwhMxMapper.deleteTmNaTcwhMxById(id);
   }
}
