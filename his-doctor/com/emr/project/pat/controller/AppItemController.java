package com.emr.project.pat.controller;

import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.project.pat.domain.AppItem;
import com.emr.project.pat.service.IAppItemService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/pat/appItem"})
public class AppItemController extends BaseController {
   @Autowired
   private IAppItemService appItemService;

   @PreAuthorize("@ss.hasPermi('pat:appItem:list')")
   @GetMapping({"/list"})
   public AjaxResult list(AppItem appItem) {
      List<AppItem> list = this.appItemService.selectAppItemList(appItem);
      return AjaxResult.success((Object)list);
   }
}
