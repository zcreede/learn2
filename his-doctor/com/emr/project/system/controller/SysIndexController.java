package com.emr.project.system.controller;

import com.emr.common.utils.StringUtils;
import com.emr.framework.config.EMRConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SysIndexController {
   @Autowired
   private EMRConfig ruoyiConfig;

   @RequestMapping({"/"})
   public String index() {
      return StringUtils.format("欢迎使用{}后台管理框架，当前版本：v{}，请通过前端地址访问。", this.ruoyiConfig.getName(), this.ruoyiConfig.getVersion());
   }
}
