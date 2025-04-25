package com.emr.project.mrhp.controller;

import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.project.mrhp.domain.TmHsDictType;
import com.emr.project.mrhp.domain.req.DictTypePageReq;
import com.emr.project.mrhp.service.ITmHsDictTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/escalation/hs/type"})
@Api(
   tags = {"MRIS0084-数据上报-上报字典对照字典类型"}
)
public class TmHsDictTypeController extends BaseController {
   @Autowired
   private ITmHsDictTypeService tmHsDictTypeService;

   @PreAuthorize("@ss.hasPermi('escalation:hsType:list')")
   @GetMapping({"/list"})
   @ApiOperation(
      value = "查询病案数据标准字典类型列表",
      notes = "权限制值为：escalation:hsType:list"
   )
   public AjaxResult list(DictTypePageReq req) {
      try {
         TmHsDictType type = new TmHsDictType();
         BeanUtils.copyProperties(req, type);
         List<TmHsDictType> list = this.tmHsDictTypeService.selectTmHsDictTypeList(type);
         return AjaxResult.success((Object)list);
      } catch (Exception e) {
         this.log.error("查询病案数据标准字典类型列表出现异常", e);
         return AjaxResult.error("查询病案数据标准字典类型列表出现异常,请联系管理员！");
      }
   }
}
