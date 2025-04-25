package com.emr.project.qc.service;

import com.emr.project.qc.domain.QcScoreItem;
import java.util.List;

public interface IQcScoreItemService {
   QcScoreItem selectQcScoreItemById(Long id) throws Exception;

   List selectQcScoreItemList(QcScoreItem qcScoreItem) throws Exception;

   int insertQcScoreItem(QcScoreItem qcScoreItem) throws Exception;

   int updateQcScoreItem(QcScoreItem qcScoreItem) throws Exception;

   int deleteQcScoreItemByIds(Long[] ids) throws Exception;

   int deleteQcScoreItemById(Long id) throws Exception;

   void changeDelFlag(Long ids) throws Exception;

   List selectItemListGroupByClass(QcScoreItem qcScoreItem) throws Exception;
}
