package com.emr.project.sys.service.impl;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.common.utils.StringUtils;
import com.emr.project.sys.domain.QuoteElem;
import com.emr.project.sys.domain.vo.QuoteElemSaveVo;
import com.emr.project.sys.domain.vo.QuoteElemTypeNumVo;
import com.emr.project.sys.domain.vo.QuoteElemVo;
import com.emr.project.sys.mapper.QuoteElemMapper;
import com.emr.project.sys.service.IQuoteElemService;
import com.emr.project.system.domain.SysUser;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class QuoteElemServiceImpl implements IQuoteElemService {
   @Autowired
   private QuoteElemMapper quoteElemMapper;

   public QuoteElem selectQuoteElemById(Long id) throws Exception {
      return this.quoteElemMapper.selectQuoteElemById(id);
   }

   public List selectQuoteElemList(QuoteElem quoteElem) throws Exception {
      return this.quoteElemMapper.selectQuoteElemList(quoteElem);
   }

   public List selectQuoteElemStandardList(QuoteElem quoteElem) throws Exception {
      return this.quoteElemMapper.selectQuoteElemStandardList(quoteElem);
   }

   public int insertQuoteElem(QuoteElem quoteElem) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      quoteElem.setId(SnowIdUtils.uniqueLong());
      quoteElem.setIsValid("1");
      quoteElem.setCreatorid(user.getUserName());
      return this.quoteElemMapper.insertQuoteElem(quoteElem);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void insertQuoteElemList(List list) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      list.forEach((t) -> {
         t.setId(SnowIdUtils.uniqueLong());
         t.setIsValid("1");
         t.setCreatorid(user.getUserName());
      });
      this.quoteElemMapper.insertQuoteElemList(list);
   }

   public int updateQuoteElem(QuoteElem quoteElem) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      quoteElem.setAltPerCode(user.getUserName());
      return this.quoteElemMapper.updateQuoteElem(quoteElem);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void updateQuoteElemList(QuoteElemSaveVo quoteElemSaveVo, List list) throws Exception {
      this.quoteElemMapper.deleteQuoteElemByElemId(quoteElemSaveVo.getTempType(), quoteElemSaveVo.getElemCd());
      SysUser user = SecurityUtils.getLoginUser().getUser();
      list.forEach((t) -> {
         t.setId(SnowIdUtils.uniqueLong());
         t.setIsValid("1");
         t.setCreatorid(user.getUserName());
      });
      this.quoteElemMapper.insertQuoteElemList(list);
   }

   public int deleteQuoteElemByIds(Long[] ids) throws Exception {
      return this.quoteElemMapper.deleteQuoteElemByIds(ids);
   }

   public int deleteQuoteElemById(Long id) throws Exception {
      return this.quoteElemMapper.deleteQuoteElemById(id);
   }

   public List selectTypeNumVo(QuoteElemTypeNumVo quoteElemTypeNumVo) throws Exception {
      return this.quoteElemMapper.selectNumGroupByType(quoteElemTypeNumVo);
   }

   public List selectQuoteElemVoByTemp(String tempType, List elemIdList) throws Exception {
      return this.quoteElemMapper.selectQuoteElemVoByTemp(tempType, elemIdList);
   }

   public List selectFromQuoteElemForBase64(String tempType) throws Exception {
      List<QuoteElemVo> list = null;
      if (StringUtils.isNotBlank(tempType)) {
         list = this.quoteElemMapper.selectFromQuoteElemForBase64(tempType);
      }

      return list;
   }
}
