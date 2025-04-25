package com.emr.project.tmpl.service.impl;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.project.tmpl.domain.TmplElemLinkElem;
import com.emr.project.tmpl.domain.vo.TmplElemLinkElemVo;
import com.emr.project.tmpl.mapper.TmplElemLinkElemMapper;
import com.emr.project.tmpl.service.ITmplElemLinkElemService;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TmplElemLinkElemServiceImpl implements ITmplElemLinkElemService {
   @Autowired
   private TmplElemLinkElemMapper tmplElemLinkElemMapper;

   public TmplElemLinkElem selectTmplElemLinkElemById(Long id) {
      return this.tmplElemLinkElemMapper.selectTmplElemLinkElemById(id);
   }

   public List selectTmplElemLinkElemList(TmplElemLinkElem tmplElemLinkElem) {
      return this.tmplElemLinkElemMapper.selectTmplElemLinkElemList(tmplElemLinkElem);
   }

   public int insertTmplElemLinkElem(TmplElemLinkElem tmplElemLinkElem) {
      tmplElemLinkElem.setId(SnowIdUtils.uniqueLong());
      tmplElemLinkElem.setCrePerCode(SecurityUtils.getLoginUser().getUser().getUserName());
      tmplElemLinkElem.setCrePerName(SecurityUtils.getLoginUser().getUser().getNickName());
      return this.tmplElemLinkElemMapper.insertTmplElemLinkElem(tmplElemLinkElem);
   }

   public int updateTmplElemLinkElem(TmplElemLinkElem tmplElemLinkElem) {
      return this.tmplElemLinkElemMapper.updateTmplElemLinkElem(tmplElemLinkElem);
   }

   public int deleteTmplElemLinkElemByIds(Long[] ids) {
      return this.tmplElemLinkElemMapper.deleteTmplElemLinkElemByIds(ids);
   }

   public int deleteTmplElemLinkElemById(Long id) {
      return this.tmplElemLinkElemMapper.deleteTmplElemLinkElemById(id);
   }

   public void deleteByLinkId(Long linkId) {
      this.tmplElemLinkElemMapper.deleteByLinkId(linkId);
   }

   public void deleteByLinkIdAndType(Long linkId, String linkType) throws Exception {
      this.tmplElemLinkElemMapper.deleteByLinkIdAndType(linkId, linkType);
   }

   public void insertList(List list) throws Exception {
      this.tmplElemLinkElemMapper.insertList(list);
   }

   public List selectByLinkIdList(List linkIdList) throws Exception {
      List<TmplElemLinkElemVo> list = null;
      if (CollectionUtils.isNotEmpty(linkIdList)) {
         list = this.tmplElemLinkElemMapper.selectByLinkIdList(linkIdList);
      }

      return list;
   }
}
