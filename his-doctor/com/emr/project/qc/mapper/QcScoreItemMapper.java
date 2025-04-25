package com.emr.project.qc.mapper;

import com.emr.project.qc.domain.QcScoreItem;
import java.util.List;

public interface QcScoreItemMapper {
   QcScoreItem selectQcScoreItemById(Long id);

   List selectQcScoreItemList(QcScoreItem qcScoreItem);

   int insertQcScoreItem(QcScoreItem qcScoreItem);

   int updateQcScoreItem(QcScoreItem qcScoreItem);

   int deleteQcScoreItemById(Long id);

   int deleteQcScoreItemByIds(Long[] ids);

   void changeDelFlag(Long id);

   List selectItemListGroupByClass(QcScoreItem qcScoreItem);

   List selectItemList(List itemClassList);
}
