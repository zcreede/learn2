package com.emr.project.operation.service.impl;

import com.emr.common.utils.StringUtils;
import com.emr.project.operation.domain.TmBsAccountThird;
import com.emr.project.operation.domain.vo.TmBsAccountThirdProjectVo;
import com.emr.project.operation.mapper.TmBsAccountThirdMapper;
import com.emr.project.operation.service.TmBsAccountThirdService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TmBsAccountThirdServiceImpl implements TmBsAccountThirdService {
   @Autowired
   private TmBsAccountThirdMapper tmBsAccountThirdMapper;

   public List selectThirdAndProjectByCodeList(List list) throws Exception {
      List<TmBsAccountThirdProjectVo> resList = null;
      if (list != null && !list.isEmpty()) {
         resList = this.tmBsAccountThirdMapper.selectThirdAndProjectByCodeList(list);
      }

      return resList;
   }

   public TmBsAccountThirdProjectVo selectThirdAndProjectByCode(String code) throws Exception {
      return StringUtils.isNotBlank(code) ? this.tmBsAccountThirdMapper.selectThirdAndProjectByCode(code) : null;
   }

   public List queryTmBsAccountThirdList(TmBsAccountThird tmBsAccountThird) throws Exception {
      return this.tmBsAccountThirdMapper.selectTmBsAccountThirdList(tmBsAccountThird);
   }
}
