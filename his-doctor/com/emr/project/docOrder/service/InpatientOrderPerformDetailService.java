package com.emr.project.docOrder.service;

import com.emr.project.docOrder.domain.InpatientOrderPerformDetail;
import java.util.List;
import java.util.Map;

public interface InpatientOrderPerformDetailService {
   InpatientOrderPerformDetail selectByPrimaryKey(InpatientOrderPerformDetail inpd);

   List selectListByNo(Map map);

   int addList(List detailList) throws Exception;
}
