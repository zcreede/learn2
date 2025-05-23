package com.emr.project.monitor.controller;

import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.domain.Server;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/monitor/server"})
public class ServerController {
   @PreAuthorize("@ss.hasPermi('monitor:server:list')")
   @GetMapping
   public AjaxResult getInfo() throws Exception {
      Server server = new Server();
      server.copyTo();
      return AjaxResult.success((Object)server);
   }
}
