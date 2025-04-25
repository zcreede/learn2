package com.emr.project.qc.service.impl;

import com.emr.common.utils.PinYinUtil;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.project.qc.domain.QcScoreDedDetail;
import com.emr.project.qc.domain.QcScoreItem;
import com.emr.project.qc.domain.vo.QcScoreItemVo;
import com.emr.project.qc.mapper.QcScoreItemMapper;
import com.emr.project.qc.service.IQcScoreDedDetailService;
import com.emr.project.qc.service.IQcScoreItemService;
import com.emr.project.system.domain.SysUser;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QcScoreItemServiceImpl implements IQcScoreItemService {
   @Autowired
   private QcScoreItemMapper qcScoreItemMapper;
   @Autowired
   private IQcScoreDedDetailService qcScoreDedDetailService;

   public QcScoreItem selectQcScoreItemById(Long id) throws Exception {
      return this.qcScoreItemMapper.selectQcScoreItemById(id);
   }

   public List selectQcScoreItemList(QcScoreItem qcScoreItem) throws Exception {
      qcScoreItem.setDelFlag("0");
      List<QcScoreItemVo> list = this.qcScoreItemMapper.selectQcScoreItemList(qcScoreItem);
      List<QcScoreDedDetail> qcScoreDedDetails = this.qcScoreDedDetailService.selectDedDetailList(new QcScoreDedDetail());
      if (list != null && !list.isEmpty() && qcScoreDedDetails != null && !qcScoreDedDetails.isEmpty()) {
         Map<String, List<QcScoreDedDetail>> detailMap = (Map)qcScoreDedDetails.stream().collect(Collectors.groupingBy((s) -> s.getItemCd()));

         for(QcScoreItemVo qcScoreItemVo : list) {
            List<QcScoreDedDetail> dedDetails = (List)detailMap.get(qcScoreItemVo.getId().toString());
            qcScoreItemVo.setDedDetailNum(dedDetails == null ? 0 : dedDetails.size());
         }
      }

      return list;
   }

   public int insertQcScoreItem(QcScoreItem qcScoreItem) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      qcScoreItem.setId(SnowIdUtils.uniqueLong());
      qcScoreItem.setOrgCd(user.getHospital().getOrgCode());
      qcScoreItem.setInputstrphtc(PinYinUtil.getPinYinHeadChar(qcScoreItem.getItemName()));
      qcScoreItem.setCrePerCode(user.getUserName());
      qcScoreItem.setCrePerName(user.getNickName());
      qcScoreItem.setDelFlag("0");
      return this.qcScoreItemMapper.insertQcScoreItem(qcScoreItem);
   }

   public int updateQcScoreItem(QcScoreItem qcScoreItem) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      qcScoreItem.setAltPerCode(user.getUserName());
      qcScoreItem.setAltPerName(user.getNickName());
      qcScoreItem.setInputstrphtc(PinYinUtil.getPinYinHeadChar(qcScoreItem.getItemName()));
      return this.qcScoreItemMapper.updateQcScoreItem(qcScoreItem);
   }

   public int deleteQcScoreItemByIds(Long[] ids) throws Exception {
      return this.qcScoreItemMapper.deleteQcScoreItemByIds(ids);
   }

   public int deleteQcScoreItemById(Long id) throws Exception {
      return this.qcScoreItemMapper.deleteQcScoreItemById(id);
   }

   public void changeDelFlag(Long ids) throws Exception {
      this.qcScoreItemMapper.changeDelFlag(ids);
   }

   public List selectItemListGroupByClass(QcScoreItem qcScoreItem) throws Exception {
      List<QcScoreItem> qcScoreItemGroupList = this.qcScoreItemMapper.selectItemListGroupByClass(qcScoreItem);
      List<String> classCd = (List)qcScoreItemGroupList.stream().map((t) -> t.getItemClassCd()).collect(Collectors.toList());
      List<QcScoreItemVo> qcScoreItemList = this.qcScoreItemMapper.selectItemList(classCd);
      return qcScoreItemList;
   }
}
