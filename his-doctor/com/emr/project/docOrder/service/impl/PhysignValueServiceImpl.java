package com.emr.project.docOrder.service.impl;

import com.emr.common.utils.SnowIdUtils;
import com.emr.common.utils.StringUtils;
import com.emr.framework.aspectj.lang.annotation.DataSource;
import com.emr.framework.aspectj.lang.enums.DataSourceType;
import com.emr.project.docOrder.domain.vo.PhysignDayVo;
import com.emr.project.docOrder.domain.vo.PhysignValueVo;
import com.emr.project.docOrder.mapper.PhysignDayMapper;
import com.emr.project.docOrder.mapper.PhysignValueMapper;
import com.emr.project.docOrder.service.IPhysignValueService;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class PhysignValueServiceImpl implements IPhysignValueService {
   @Resource
   private PhysignValueMapper physignValueMapper;
   @Resource
   private PhysignDayMapper physignDayMapper;

   @DataSource(DataSourceType.physignValueList)
   public List getValueList(PhysignValueVo physignValueVo) throws Exception {
      List<PhysignValueVo> resultList = new ArrayList();

      for(PhysignValueVo phy : this.physignValueMapper.selectValueList(physignValueVo)) {
         if ((phy.getTzNameCode().equals("bloodpressure1") || phy.getTzNameCode().equals("bloodpressure2")) && StringUtils.isNotEmpty(phy.getTzValue())) {
            String[] xys = phy.getTzValue().split("/");
            String id = SnowIdUtils.uniqueLongHex();

            for(int i = 0; i < xys.length; ++i) {
               PhysignValueVo phtsign = new PhysignValueVo();
               BeanUtils.copyProperties(phy, phtsign);
               phtsign.setXyValue(xys[i]);
               if (i == 0) {
                  phtsign.setXyType("0");
               } else {
                  phtsign.setXyType("1");
               }

               phtsign.setXyGroup(id);
               resultList.add(phtsign);
            }
         } else {
            resultList.add(phy);
         }
      }

      return resultList;
   }

   @DataSource(DataSourceType.physignDayList)
   public List getDayList(PhysignDayVo physignDayVo) throws Exception {
      return this.physignDayMapper.selectDayList(physignDayVo);
   }

   @DataSource(DataSourceType.physignValueListCurDayRange)
   public List getValueRangeList(PhysignValueVo physignValueVo) throws Exception {
      return this.physignValueMapper.getValueRangeList(physignValueVo);
   }
}
