package com.emr.project.task;

import com.emr.common.utils.DateUtils;
import com.emr.common.utils.StringUtils;
import com.emr.project.docOrder.service.OrderPerformTaskService;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Component
@RequestMapping({"/orderPerformTask"})
public class OrderPerformTask {
   protected final Logger log = LoggerFactory.getLogger(OrderPerformTask.class);
   @Autowired
   private OrderPerformTaskService orderPerformTaskService;

   @GetMapping({"/start"})
   public void transferOrderPerform(String depts) {
      this.log.info("患者执行单记录转移定时任务开始执行", DateUtils.dateFormat(new Date(), DateUtils.YYYY_MM_DD_HH_MM_SS));

      try {
         List<String> deptList = null;
         if (StringUtils.isNotBlank(depts)) {
            deptList = Arrays.asList(depts.split(";"));
         }

         this.orderPerformTaskService.transferOrderPerform(deptList);
      } catch (Exception e) {
         this.log.error("患者执行单记录转移定时任务出现异常", e);
      }

      this.log.info("患者执行单记录转移定时任务结束执行", DateUtils.dateFormat(new Date(), DateUtils.YYYY_MM_DD_HH_MM_SS));
   }
}
