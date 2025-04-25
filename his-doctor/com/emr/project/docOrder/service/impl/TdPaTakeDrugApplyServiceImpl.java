package com.emr.project.docOrder.service.impl;

import com.emr.common.utils.DateUtils;
import com.emr.project.docOrder.domain.TdPaTakeDrugApply;
import com.emr.project.docOrder.domain.vo.TdPaTakeDrugApplyVo;
import com.emr.project.docOrder.mapper.TdPaTakeDrugApplyMapper;
import com.emr.project.docOrder.service.ITdPaTakeDrugApplyService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TdPaTakeDrugApplyServiceImpl implements ITdPaTakeDrugApplyService {
   @Autowired
   private TdPaTakeDrugApplyMapper tdPaTakeDrugApplyMapper;

   public TdPaTakeDrugApply selectTdPaTakeDrugApplyById(Long id) {
      return this.tdPaTakeDrugApplyMapper.selectTdPaTakeDrugApplyById(id);
   }

   public List selectTdPaTakeDrugApplyList(TdPaTakeDrugApply tdPaTakeDrugApply) {
      return this.tdPaTakeDrugApplyMapper.selectTdPaTakeDrugApplyList(tdPaTakeDrugApply);
   }

   public int insertTdPaTakeDrugApply(TdPaTakeDrugApply tdPaTakeDrugApply) {
      return this.tdPaTakeDrugApplyMapper.insertTdPaTakeDrugApply(tdPaTakeDrugApply);
   }

   public int updateTdPaTakeDrugApply(TdPaTakeDrugApply tdPaTakeDrugApply) {
      return this.tdPaTakeDrugApplyMapper.updateTdPaTakeDrugApply(tdPaTakeDrugApply);
   }

   public int deleteTdPaTakeDrugApplyByIds(Long[] ids) {
      return this.tdPaTakeDrugApplyMapper.deleteTdPaTakeDrugApplyByIds(ids);
   }

   public int deleteTdPaTakeDrugApplyById(Long id) {
      return this.tdPaTakeDrugApplyMapper.deleteTdPaTakeDrugApplyById(id);
   }

   public TdPaTakeDrugApply selectLastApply(String orgCode, String deptCode, Date currDate) throws Exception {
      TdPaTakeDrugApplyVo vo = new TdPaTakeDrugApplyVo();
      vo.setOrgCode(orgCode);
      vo.setDeptCode(deptCode);
      vo.setTakeDrugDateStr(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, currDate));
      return this.tdPaTakeDrugApplyMapper.selectLastApply(vo);
   }
}
