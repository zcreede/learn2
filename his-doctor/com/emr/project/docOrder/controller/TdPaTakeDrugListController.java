package com.emr.project.docOrder.controller;

import com.emr.common.utils.DateUtils;
import com.emr.common.utils.StringUtils;
import com.emr.common.utils.ip.IpUtils;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.project.docOrder.domain.resp.TdPaTakeDrugListDetailPrint;
import com.emr.project.docOrder.domain.vo.DrugListAndPerformReturnResultVo;
import com.emr.project.docOrder.domain.vo.DrugListAndPerformReturnVo;
import com.emr.project.docOrder.domain.vo.HisYfkcHz;
import com.emr.project.docOrder.domain.vo.TdPaTakeDrugListVO;
import com.emr.project.docOrder.service.ITdPaTakeDrugListService;
import com.emr.project.operation.service.TakeDrugReturnService;
import com.emr.project.tmpm.domain.PrintTmplInfo;
import com.emr.project.tmpm.service.IPrintTmplInfoService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/take/drug"})
public class TdPaTakeDrugListController extends BaseController {
   @Autowired
   private ITdPaTakeDrugListService tdPaTakeDrugListService;
   @Autowired
   private TakeDrugReturnService takeDrugReturnService;
   @Autowired
   private IPrintTmplInfoService printTmplInfoService;

   @PreAuthorize("@ss.hasAnyPermi('oper:room:takeDrugList,operation:room:refund:takeDrugList')")
   @GetMapping({"/selectPatList"})
   public AjaxResult selectPatList(TdPaTakeDrugListVO tdPaTakeDrugListVO) {
      AjaxResult ajaxResult = AjaxResult.success();
      boolean flag = true;

      try {
         if (tdPaTakeDrugListVO == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(tdPaTakeDrugListVO.getTakeFlag())) {
            flag = false;
            ajaxResult = AjaxResult.error("是否领药参数不能为空");
         }

         if (flag) {
            if (tdPaTakeDrugListVO.getStartDate() != null && tdPaTakeDrugListVO.getEndDate() != null) {
               Calendar c = Calendar.getInstance();
               c.setTime(tdPaTakeDrugListVO.getEndDate());
               c.add(5, 1);
               tdPaTakeDrugListVO.setEndDate(c.getTime());
            }

            List<TdPaTakeDrugListVO> list = this.tdPaTakeDrugListService.selectTakeDrugPatList(tdPaTakeDrugListVO);
            ajaxResult = AjaxResult.success((Object)list);
         }
      } catch (Exception e) {
         this.log.error("查询领药单左侧患者列表----手术室：", e);
         ajaxResult = AjaxResult.error("查询领药单左侧患者列表----手术室");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('oper:room:takeDrugList')")
   @GetMapping({"/selectTakeDetailList"})
   public AjaxResult selectTakeDetailList(TdPaTakeDrugListVO tdPaTakeDrugListVO) {
      AjaxResult ajaxResult = AjaxResult.success();
      boolean flag = true;

      try {
         if (tdPaTakeDrugListVO == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(tdPaTakeDrugListVO.getQueryType())) {
            flag = false;
            ajaxResult = AjaxResult.error("查询类型参数不能为空");
         }

         if (flag && StringUtils.isEmpty(tdPaTakeDrugListVO.getTakeFlag())) {
            flag = false;
            ajaxResult = AjaxResult.error("是否领药参数不能为空");
         }

         if (flag) {
            if (tdPaTakeDrugListVO.getStartDate() != null && tdPaTakeDrugListVO.getEndDate() != null) {
               Calendar c = Calendar.getInstance();
               c.setTime(tdPaTakeDrugListVO.getEndDate());
               c.add(5, 1);
               tdPaTakeDrugListVO.setEndDate(c.getTime());
            }

            List<TdPaTakeDrugListVO> list = this.tdPaTakeDrugListService.selectTakeDetailList(tdPaTakeDrugListVO);
            ajaxResult = AjaxResult.success((Object)list);
            if (list != null && !list.isEmpty()) {
               double sum = list.stream().mapToDouble(TdPaTakeDrugListVO::getOrderTotal).sum();
               BigDecimal sumDecimal = BigDecimal.valueOf(sum).setScale(3, 1);
               ajaxResult.put("amount", sumDecimal);
               ajaxResult.put("remark", "共 " + list.size() + " 种药品");
               ajaxResult.put("totalDate", DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss", new Date()));
            }

            List<String> typeCodeList = new ArrayList();
            typeCodeList.add("100011001");
            typeCodeList.add("100011002");
            typeCodeList.add("100011003");
            typeCodeList.add("100011004");
            List<PrintTmplInfo> printTmplInfos = this.printTmplInfoService.selectTmPmPrintTmplInfoByCodes(typeCodeList);
            ajaxResult.put("printTmpl", printTmplInfos);
         }
      } catch (Exception e) {
         this.log.error("查询领药单详情----手术室：", e);
         ajaxResult = AjaxResult.error("查询领药单详情----手术室");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('oper:room:takeDrugList')")
   @GetMapping({"/printTakeDetailList"})
   public AjaxResult printTakeDetailList(TdPaTakeDrugListVO tdPaTakeDrugListVO) {
      AjaxResult ajaxResult = AjaxResult.success();
      boolean flag = true;

      try {
         if (tdPaTakeDrugListVO == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && StringUtils.isEmpty(tdPaTakeDrugListVO.getQueryType())) {
            flag = false;
            ajaxResult = AjaxResult.error("查询类型参数不能为空");
         }

         if (flag && StringUtils.isEmpty(tdPaTakeDrugListVO.getTakeFlag())) {
            flag = false;
            ajaxResult = AjaxResult.error("是否领药参数不能为空");
         }

         if (flag) {
            if (tdPaTakeDrugListVO.getStartDate() != null && tdPaTakeDrugListVO.getEndDate() != null) {
               Calendar c = Calendar.getInstance();
               c.setTime(tdPaTakeDrugListVO.getEndDate());
               c.add(5, 1);
               tdPaTakeDrugListVO.setEndDate(c.getTime());
            }

            List<TdPaTakeDrugListDetailPrint> list = this.tdPaTakeDrugListService.printTakeDetailList(tdPaTakeDrugListVO);
            ajaxResult = AjaxResult.success((Object)list);
         }
      } catch (Exception e) {
         this.log.error("查询领药单详情----手术室：", e);
         ajaxResult = AjaxResult.error("查询领药单详情----手术室");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasAnyPermi('oper:room:takeDrugList')")
   @PutMapping({"/delete"})
   public AjaxResult deleteTakeDrug(@RequestBody TdPaTakeDrugListVO tdPaTakeDrugListVO, HttpServletRequest request) {
      AjaxResult ajaxResult = AjaxResult.success();
      boolean flag = true;

      try {
         if (tdPaTakeDrugListVO == null) {
            flag = false;
            ajaxResult = AjaxResult.error("参数不能为空");
         }

         if (flag && tdPaTakeDrugListVO.getTakeDrugList() == null) {
            flag = false;
            ajaxResult = AjaxResult.error("领药单集合不能为空");
         }

         if (flag) {
            boolean returnFlag = false;
            List<HisYfkcHz> hisList = null;
            DrugListAndPerformReturnResultVo resultVo = null;

            try {
               this.log.info("领药单作废oracle操作开始");
               List<DrugListAndPerformReturnVo> returnVoList = new ArrayList(1);

               for(TdPaTakeDrugListVO takeDrugListVO : tdPaTakeDrugListVO.getTakeDrugList()) {
                  DrugListAndPerformReturnVo returnVo = new DrugListAndPerformReturnVo(takeDrugListVO.getId(), (String)null, (Integer)null, takeDrugListVO.getOrderDose());
                  returnVoList.add(returnVo);
               }

               String ip = IpUtils.getIpAddr(request);
               resultVo = this.takeDrugReturnService.doReturnDrug(returnVoList, ip);
               hisList = resultVo.getYfkcHzList();
               this.log.info("领药单作废oracle操作结束");
               returnFlag = true;
            } catch (Exception e) {
               this.log.error("领药单作废出现异常：", e);
               ajaxResult = AjaxResult.error("领药单作废出现异常");
               returnFlag = false;
            }

            if (returnFlag) {
               try {
                  this.log.info("修改sqlserver药库虚存操作开始");
                  this.tdPaTakeDrugListService.updateHisYfkc(hisList);
                  ajaxResult = AjaxResult.success("领药单作废成功！");
                  this.log.info("修改sqlserver药库虚存操作结束");
               } catch (Exception e) {
                  if (resultVo != null) {
                     this.takeDrugReturnService.doReturnDrugCancel(resultVo);
                  }

                  this.log.error("领药单作废出现异常：", e);
                  ajaxResult = AjaxResult.error("领药单作废出现异常");
               }
            }
         }
      } catch (Exception e) {
         this.log.error("领药单作废出现异常----手术室：", e);
         ajaxResult = AjaxResult.error("领药单作废出现异常----手术室");
      }

      return ajaxResult;
   }
}
