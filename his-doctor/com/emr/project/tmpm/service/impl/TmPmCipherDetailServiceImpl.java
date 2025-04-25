package com.emr.project.tmpm.service.impl;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.SnowIdUtils;
import com.emr.framework.elasticsearch.ElasticsearchIndexXyy;
import com.emr.framework.elasticsearch.ElasticsearchTemplateXyy;
import com.emr.project.common.service.ICommonService;
import com.emr.project.docOrder.util.OrderUtil;
import com.emr.project.esSearch.domain.DrugAndClin;
import com.emr.project.esSearch.domain.vo.DrugAndClinSearchVo;
import com.emr.project.system.domain.BasEmployee;
import com.emr.project.system.domain.SysUser;
import com.emr.project.tmpm.domain.TmPmCipherDetail;
import com.emr.project.tmpm.domain.vo.TmPmCipherDetailVo;
import com.emr.project.tmpm.domain.vo.TmPmCipherMainVo;
import com.emr.project.tmpm.mapper.TmPmCipherDetailMapper;
import com.emr.project.tmpm.service.ITmPmCipherDetailService;
import com.emr.project.tmpm.service.ITmPmCipherMainService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zxp.esclientrhl.repository.ElasticsearchTemplate;
import org.zxp.esclientrhl.repository.PageList;
import org.zxp.esclientrhl.repository.PageSortHighLight;

@Service
public class TmPmCipherDetailServiceImpl implements ITmPmCipherDetailService {
   @Autowired
   private TmPmCipherDetailMapper tmPmCipherDetailMapper;
   @Autowired
   private ElasticsearchTemplateXyy elasticsearchTemplateXyy;
   @Autowired
   private ElasticsearchIndexXyy elasticsearchIndexXyy;
   @Autowired
   private ElasticsearchTemplate elasticsearchTemplate;
   @Autowired
   private ICommonService commonService;
   @Autowired
   private ITmPmCipherMainService tmPmCipherMainService;

   public TmPmCipherDetail selectTmPmCipherDetailById(Long id) {
      return this.tmPmCipherDetailMapper.selectTmPmCipherDetailById(id);
   }

   public List selectTmPmCipherDetailList(TmPmCipherDetail tmPmCipherDetail) {
      return this.tmPmCipherDetailMapper.selectTmPmCipherDetailList(tmPmCipherDetail);
   }

   public int insertTmPmCipherDetail(TmPmCipherDetail tmPmCipherDetail) {
      return this.tmPmCipherDetailMapper.insertTmPmCipherDetail(tmPmCipherDetail);
   }

   public void insertTmPmCipherDetailList(List tmPmCipherDetailList) throws Exception {
      this.tmPmCipherDetailMapper.insertTmPmCipherDetailList(tmPmCipherDetailList);
   }

   public int updateTmPmCipherDetail(TmPmCipherDetail tmPmCipherDetail) {
      return this.tmPmCipherDetailMapper.updateTmPmCipherDetail(tmPmCipherDetail);
   }

   public int deleteTmPmCipherDetailByIds(Long[] ids) {
      return this.tmPmCipherDetailMapper.deleteTmPmCipherDetailByIds(ids);
   }

   public int deleteTmPmCipherDetailById(Long id) {
      return this.tmPmCipherDetailMapper.deleteTmPmCipherDetailById(id);
   }

   public void deleteTmPmCipherDetailByCipherCd(String cipherCd) throws Exception {
      this.tmPmCipherDetailMapper.deleteTmPmCipherDetailByCipherCd(cipherCd);
   }

   public List selectTmPmCipherDetailByCdList(TmPmCipherDetailVo tmPmCipherDetail) throws Exception {
      List<TmPmCipherDetailVo> detailList = this.tmPmCipherDetailMapper.selectTmPmCipherDetailByCipherCd(tmPmCipherDetail.getCipherCd());
      this.setDetailDrugList(detailList, tmPmCipherDetail);
      return detailList;
   }

   public List selectTmPmCipherDetailByCipherCd(String cipherCd) throws Exception {
      return this.tmPmCipherDetailMapper.selectTmPmCipherDetailByCipherCd(cipherCd);
   }

   public List selectDetailDrugList(TmPmCipherDetailVo tmPmCipherDetail) throws Exception {
      List<TmPmCipherDetailVo> list = this.tmPmCipherDetailMapper.selectDetailDrugListByIds(tmPmCipherDetail.getIdList());
      this.setDetailDrugList(list, tmPmCipherDetail);
      return list;
   }

   public void setDetailDrugList(List detailList, TmPmCipherDetailVo tmPmCipherDetail) throws Exception {
      if (detailList != null && !detailList.isEmpty()) {
         List<String> cpNoList = (List)detailList.stream().map((s) -> s.getDrugCd()).collect(Collectors.toList());
         DrugAndClinSearchVo param = new DrugAndClinSearchVo();
         param.setOrderClassCode("01");
         param.setHerbalFlag("1");
         param.setPerformDepCode(tmPmCipherDetail.getExecDeptCd());
         param.setItemCdList(cpNoList);
         List<DrugAndClin> list = this.selectCipAndGroupDetailList(param);
         if (list != null && !list.isEmpty()) {
            Map<String, List<DrugAndClin>> map = (Map)list.stream().collect(Collectors.groupingBy((s) -> s.getCpNo().trim()));

            for(TmPmCipherDetailVo detail : detailList) {
               List<DrugAndClin> details = (List)map.get(detail.getDrugCd().trim());
               if (details != null && !details.isEmpty()) {
                  detail.setDrugAndClin((DrugAndClin)details.get(0));
               }
            }
         }
      }

   }

   public List selectCipAndGroupDetailList(DrugAndClinSearchVo param) throws Exception {
      SysUser user = SecurityUtils.getLoginUser().getUser();
      String indexName = "drugandclin_zy_" + user.getUserName();
      boolean existsFlag = this.elasticsearchIndexXyy.exists(DrugAndClin.class, indexName);
      if (!existsFlag) {
      }

      BoolQueryBuilder bqb = QueryBuilders.boolQuery();
      if (StringUtils.isNotBlank(param.getOrderClassCode())) {
         QueryBuilder qb1 = QueryBuilders.termQuery("orderClassCode", param.getOrderClassCode());
         bqb.must(qb1);
      }

      if (StringUtils.isNotBlank(param.getHerbalFlag())) {
         QueryBuilder qb1 = QueryBuilders.termQuery("herbalFlag", param.getHerbalFlag());
         bqb.must(qb1);
      }

      if (StringUtils.isNotBlank(param.getPerformDepCode())) {
         QueryBuilder qb1 = QueryBuilders.termQuery("performDepCode", param.getPerformDepCode());
         bqb.must(qb1);
      }

      if (param.getItemCdList() != null && !param.getItemCdList().isEmpty()) {
         List<String> itemCdList = (List)param.getItemCdList().stream().map((t) -> t.toLowerCase()).collect(Collectors.toList());
         QueryBuilder qb1 = QueryBuilders.termsQuery("cpNo", itemCdList);
         bqb.must(qb1);
      }

      long count = this.elasticsearchTemplate.count(bqb, DrugAndClin.class, new String[]{indexName});
      PageSortHighLight psh = new PageSortHighLight(1, (int)count);
      PageList<DrugAndClin> pageList = this.elasticsearchTemplateXyy.search(bqb, psh, DrugAndClin.class, indexName);
      List<DrugAndClin> list = (List<DrugAndClin>)(pageList == null ? new ArrayList() : pageList.getList());
      return list;
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void saveTmPmCipher(TmPmCipherMainVo tmPmCipherMain) throws Exception {
      BasEmployee basEmployee = SecurityUtils.getLoginUser().getUser().getBasEmployee();
      List<TmPmCipherDetail> detailList = tmPmCipherMain.getInsertList();
      if (detailList != null && !detailList.isEmpty()) {
         Integer sort = 1;
         this.tmPmCipherDetailMapper.deleteTmPmCipherDetailByCipherCd(tmPmCipherMain.getCipherCd());

         for(TmPmCipherDetail tmPmCipherDetail : detailList) {
            tmPmCipherDetail.setId(SnowIdUtils.uniqueLong());
            Integer var7 = sort;
            sort = sort + 1;
            tmPmCipherDetail.setCipherSort(OrderUtil.getNumStr(var7));
            tmPmCipherDetail.setCreDate(this.commonService.getDbSysdate());
            tmPmCipherDetail.setCrePerCode(basEmployee.getEmplNumber());
            tmPmCipherDetail.setCrePerName(basEmployee.getEmplName());
         }

         this.tmPmCipherDetailMapper.insertTmPmCipherDetailList(detailList);
      }

      tmPmCipherMain.setAltDate(this.commonService.getDbSysdate());
      tmPmCipherMain.setAltPerCode(basEmployee.getEmplNumber());
      tmPmCipherMain.setAltPerName(basEmployee.getEmplName());
      this.tmPmCipherMainService.updateTmPmCipherMain(tmPmCipherMain);
   }
}
