package com.emr.project.system.controller;

import com.emr.common.utils.DateUtils;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.StringUtils;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.common.service.ICommonService;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.domain.WorkLoadItem;
import com.emr.project.system.domain.WorkLoadMain;
import com.emr.project.system.domain.WorkLoadOther;
import com.emr.project.system.domain.WorkLoadPatient;
import com.emr.project.system.domain.req.WorkLoadNewBuiltReq;
import com.emr.project.system.domain.req.WorkLoadReportReq;
import com.emr.project.system.domain.req.WorkLoadSaveReq;
import com.emr.project.system.domain.resp.WorkLoadReportResp;
import com.emr.project.system.domain.vo.WorkLoadPatientVo;
import com.emr.project.system.service.IWorkLoadItemService;
import com.emr.project.system.service.IWorkLoadMainService;
import com.emr.project.system.service.IWorkLoadOtherService;
import com.emr.project.system.service.IWorkLoadPatientService;
import com.emr.project.tmpm.domain.PrintTmplInfo;
import com.emr.project.tmpm.service.IPrintTmplInfoService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/mris"})
public class WorkLoadMainController extends BaseController {
   @Autowired
   private IWorkLoadMainService workLoadMainService;
   @Autowired
   private IWorkLoadItemService workLoadItemService;
   @Autowired
   private IWorkLoadPatientService workLoadPatientService;
   @Autowired
   private ICommonService commonService;
   @Autowired
   private IWorkLoadOtherService workLoadOtherService;
   @Autowired
   private IPrintTmplInfoService printTmplInfoService;

   @PreAuthorize("@ss.hasPermi('mris:main:list')")
   @GetMapping({"/main/list"})
   public TableDataInfo list(WorkLoadMain workLoadMain) {
      this.startPage();
      SysUser loginUser = SecurityUtils.getLoginUser().getUser();
      workLoadMain.setDeptCode(loginUser.getDept().getDeptCode());
      List<WorkLoadMain> list = this.workLoadMainService.selectWorkLoadMainList(workLoadMain);
      return this.getDataTable(list);
   }

   @GetMapping({"/main/getInfo"})
   public AjaxResult getInfo() {
      AjaxResult ajaxResult = AjaxResult.success();

      try {
         SysUser loginUser = SecurityUtils.getLoginUser().getUser();
         WorkLoadItem workLoadItem = new WorkLoadItem();
         workLoadItem.setStatus("1");
         workLoadItem.setOrgCd(loginUser.getHospital().getOrgCode());
         List<WorkLoadItem> workLoadItems = this.workLoadItemService.selectWorkLoadItemList(workLoadItem);
         if (!workLoadItems.isEmpty()) {
            Map<String, List<WorkLoadItem>> sourceFromMap = (Map)workLoadItems.stream().collect(Collectors.groupingBy(WorkLoadItem::getSourceFromType));

            for(String key : sourceFromMap.keySet()) {
               List<WorkLoadItem> collect = (List)((List)sourceFromMap.get(key)).stream().sorted(Comparator.comparing(WorkLoadItem::getSortNo)).collect(Collectors.toList());
               ajaxResult.put(key, collect);
            }
         }
      } catch (Exception e) {
         this.log.error("查询标签列表出现异常，", e);
         ajaxResult = AjaxResult.error("查询标签列表出现异常，请联系管理员！");
      }

      return ajaxResult;
   }

   @GetMapping({"/main/upload"})
   @PreAuthorize("@ss.hasPermi('mris:main:upload')")
   public AjaxResult upload(Long id) {
      AjaxResult ajaxResult = AjaxResult.success();
      if (id == null) {
         ajaxResult = AjaxResult.error("主键id不能为空！");
         return ajaxResult;
      } else {
         WorkLoadMain workLoadMain = this.workLoadMainService.selectWorkLoadMainById(id);
         if (workLoadMain == null) {
            ajaxResult = AjaxResult.error("工作量上报数据不存在！");
            return ajaxResult;
         } else if (workLoadMain.getStatus().equals("1")) {
            ajaxResult = AjaxResult.error("工作量已上报，请刷新后再试！");
            return ajaxResult;
         } else {
            try {
               SysUser loginUser = SecurityUtils.getLoginUser().getUser();
               workLoadMain.setUploadDate(this.commonService.getDbSysdate());
               workLoadMain.setUploadPerCode(loginUser.getUserName());
               workLoadMain.setUploadPerName(loginUser.getNickName());
               workLoadMain.setStatus("1");
               this.workLoadMainService.updateWorkLoadMain(workLoadMain);
            } catch (Exception e) {
               this.log.error("工作量上报出现异常，", e);
               ajaxResult = AjaxResult.error("工作量上报出现异常，请联系管理员！");
            }

            return ajaxResult;
         }
      }
   }

   @GetMapping({"/main/query"})
   @PreAuthorize("@ss.hasPermi('mris:main:query')")
   public AjaxResult query(String dateTime) {
      AjaxResult ajaxResult = AjaxResult.success();
      if (StringUtils.isEmpty(dateTime)) {
         ajaxResult = AjaxResult.error("时间不能为空！");
         return ajaxResult;
      } else {
         try {
            WorkLoadMain workLoadMain = this.workLoadMainService.queryMain(dateTime);
            if (workLoadMain == null) {
               SysUser loginUser = SecurityUtils.getLoginUser().getUser();
               workLoadMain = new WorkLoadMain();
               workLoadMain.setSumDate(DateUtils.parseDate(dateTime));
               workLoadMain.setDeptCode(loginUser.getDept().getDeptCode());
               workLoadMain.setDeptName(loginUser.getDept().getDeptName());
               workLoadMain.setOrgCd(loginUser.getHospital().getOrgCode());
               Integer originalNum = this.workLoadMainService.selectLastNowNum(dateTime);
               workLoadMain.setOriginalNum(originalNum);
            }

            ajaxResult.put("main", workLoadMain);
            if (workLoadMain.getId() != null) {
               Map<String, Object> patientMap = new HashMap();
               WorkLoadPatient workLoadPatient = new WorkLoadPatient();
               workLoadPatient.setMainId(workLoadMain.getId());
               List<WorkLoadPatientVo> list = this.workLoadPatientService.selectWorkLoadPatientList(workLoadPatient);
               if (!list.isEmpty()) {
                  Map<String, List<WorkLoadPatientVo>> itemMap = (Map)list.stream().collect(Collectors.groupingBy(WorkLoadPatient::getItemCode));
                  Map<String, Integer> itemNumMap = new HashMap();

                  for(String key : itemMap.keySet()) {
                     itemNumMap.put(key, ((List)itemMap.get(key)).size());
                  }

                  ajaxResult.put("itemNum", itemNumMap);
                  patientMap.put("data", list);
                  List<WorkLoadPatient> inList = (List)list.stream().filter((t) -> t.getItemTypeCode().equals("1")).collect(Collectors.toList());
                  patientMap.put("inNum", inList.size());
                  List<WorkLoadPatient> outList = (List)list.stream().filter((t) -> t.getItemTypeCode().equals("2")).collect(Collectors.toList());
                  patientMap.put("outNum", outList.size());
               }

               ajaxResult.put("patient", patientMap);
               WorkLoadOther workLoadOther = new WorkLoadOther();
               workLoadOther.setMainId(workLoadMain.getId());
               List<WorkLoadOther> otherList = this.workLoadOtherService.selectWorkLoadOtherList(workLoadOther);
               ajaxResult.put("otherList", otherList);
            }
         } catch (Exception e) {
            this.log.error("根据时间查询工作量主数据出现异常,", e);
            ajaxResult = AjaxResult.error("根据时间查询工作量主数据出现异常,请联系管理员！");
         }

         return ajaxResult;
      }
   }

   @GetMapping({"/main/newBuilt"})
   @PreAuthorize("@ss.hasPermi('mris:main:newBuilt')")
   public AjaxResult newBuilt(WorkLoadNewBuiltReq req) {
      AjaxResult ajaxResult = AjaxResult.success();
      String dateTime = req.getDateTime();
      if (StringUtils.isEmpty(req.getDateTime())) {
         ajaxResult = AjaxResult.error("时间不能为空！");
         return ajaxResult;
      } else if (req.getOriginalNum() == null) {
         ajaxResult = AjaxResult.error("原有人数不能为空！");
         return ajaxResult;
      } else {
         try {
            WorkLoadMain workLoadMain = this.workLoadMainService.queryMain(dateTime);
            if (workLoadMain != null) {
               ajaxResult = AjaxResult.error(dateTime + ",已经被创建，请刷新后再试！");
               return ajaxResult;
            }

            SysUser loginUser = SecurityUtils.getLoginUser().getUser();
            String deptCode = loginUser.getDept().getDeptCode();
            Integer count = this.workLoadMainService.selectCountByDeptCode(deptCode, (String)null);
            if (count != 0) {
               Integer other = this.workLoadMainService.selectCountByDeptCode(deptCode, "0");
               if (other != 0) {
                  ajaxResult = AjaxResult.error("本科室有未完成的工作量上报信息，请先上报！");
                  return ajaxResult;
               }

               String lastDate = DateUtils.addDay((String)dateTime, -1);
               WorkLoadMain main = new WorkLoadMain();
               main.setSumDate(DateUtils.parseDate(lastDate));
               main.setDeptCode(deptCode);
               List<WorkLoadMain> mains = this.workLoadMainService.selectWorkLoadMainList(main);
               if (mains.isEmpty()) {
                  ajaxResult = AjaxResult.error("统计日期【" + lastDate + "】，工作量未创建,不能创建" + dateTime + "上报数据！");
                  return ajaxResult;
               }

               WorkLoadMain loadMain = (WorkLoadMain)mains.get(0);
               req.setOriginalNum(loadMain.getNowNum());
            }

            WorkLoadMain main = this.workLoadMainService.newBuilt(req);
            ajaxResult.put("main", main);
            Map<String, Object> patientMap = new HashMap();
            if (main.getId() != null) {
               WorkLoadPatient workLoadPatient = new WorkLoadPatient();
               workLoadPatient.setMainId(main.getId());
               List<WorkLoadPatientVo> list = this.workLoadPatientService.selectWorkLoadPatientList(workLoadPatient);
               if (!list.isEmpty()) {
                  Map<String, List<WorkLoadPatientVo>> itemMap = (Map)list.stream().collect(Collectors.groupingBy(WorkLoadPatient::getItemCode));
                  Map<String, Integer> itemNumMap = new HashMap();

                  for(String key : itemMap.keySet()) {
                     itemNumMap.put(key, ((List)itemMap.get(key)).size());
                  }

                  ajaxResult.put("itemNum", itemNumMap);
                  patientMap.put("data", list);
                  List<WorkLoadPatient> inList = (List)list.stream().filter((t) -> t.getItemTypeCode().equals("1")).collect(Collectors.toList());
                  patientMap.put("inNum", inList.size());
                  List<WorkLoadPatient> outList = (List)list.stream().filter((t) -> t.getItemTypeCode().equals("2")).collect(Collectors.toList());
                  patientMap.put("outNum", outList.size());
               }

               ajaxResult.put("patient", patientMap);
               WorkLoadOther workLoadOther = new WorkLoadOther();
               workLoadOther.setMainId(main.getId());
               List<WorkLoadOther> otherList = this.workLoadOtherService.selectWorkLoadOtherList(workLoadOther);
               ajaxResult.put("otherList", otherList);
            }
         } catch (Exception e) {
            this.log.error("新建出现异常,", e);
            ajaxResult = AjaxResult.error("新建出现异常,请联系管理员！");
         }

         return ajaxResult;
      }
   }

   @PostMapping({"/main/save"})
   @PreAuthorize("@ss.hasPermi('mris:main:save')")
   public AjaxResult save(@RequestBody WorkLoadSaveReq req) {
      AjaxResult ajaxResult = AjaxResult.success();
      if (req.getMainId() == null) {
         ajaxResult = AjaxResult.error("主id不能为空！");
         return ajaxResult;
      } else {
         WorkLoadMain workLoadMain = this.workLoadMainService.selectWorkLoadMainById(req.getMainId());
         if (workLoadMain == null) {
            ajaxResult = AjaxResult.error("工作量上报数量不存在！");
            return ajaxResult;
         } else {
            String status = workLoadMain.getStatus();
            if (!status.equals("1") && !status.equals("2")) {
               List<WorkLoadPatient> patientList = req.getPatientList();
               if (!patientList.isEmpty()) {
                  for(int i = 0; i < patientList.size(); ++i) {
                     WorkLoadPatient workLoadPatient = (WorkLoadPatient)patientList.get(i);
                     if (StringUtils.isEmpty(workLoadPatient.getItemCode())) {
                        ajaxResult = AjaxResult.error("第" + (i + 1) + "行，项目编码不能为空！");
                        return ajaxResult;
                     }

                     if (StringUtils.isEmpty(workLoadPatient.getItemName())) {
                        ajaxResult = AjaxResult.error("第" + (i + 1) + "行，项目名称不能为空！");
                        return ajaxResult;
                     }

                     if (StringUtils.isEmpty(workLoadPatient.getItemTypeCode())) {
                        ajaxResult = AjaxResult.error("第" + (i + 1) + "行，项目类型编码不能为空！");
                        return ajaxResult;
                     }

                     if (StringUtils.isEmpty(workLoadPatient.getItemTypeName())) {
                        ajaxResult = AjaxResult.error("第" + (i + 1) + "行，项目类型名称不能为空！");
                        return ajaxResult;
                     }

                     if (StringUtils.isEmpty(workLoadPatient.getAdmissionNo())) {
                        ajaxResult = AjaxResult.error("第" + (i + 1) + "行，住院号不能为空！");
                        return ajaxResult;
                     }
                  }
               }

               List<WorkLoadOther> otherList = req.getOtherList();
               if (!otherList.isEmpty()) {
                  SysUser loginUser = SecurityUtils.getLoginUser().getUser();
                  String orgCode = loginUser.getHospital().getOrgCode();

                  for(int i = 0; i < otherList.size(); ++i) {
                     WorkLoadOther workLoadOther = (WorkLoadOther)otherList.get(i);
                     workLoadOther.setMainId(req.getMainId());
                     workLoadOther.setOrgCd(orgCode);
                     if (StringUtils.isEmpty(workLoadOther.getItemCode())) {
                        ajaxResult = AjaxResult.error("第" + (i + 1) + "个，项目编码不能为空！");
                        return ajaxResult;
                     }

                     if (StringUtils.isEmpty(workLoadOther.getItemName())) {
                        ajaxResult = AjaxResult.error("第" + (i + 1) + "个，项目名称不能为空！");
                        return ajaxResult;
                     }

                     if (workLoadOther.getItemNum() == null) {
                        ajaxResult = AjaxResult.error("第" + (i + 1) + "个，项目数量不能为空！");
                        return ajaxResult;
                     }
                  }
               }

               try {
                  this.workLoadMainService.save(req);
                  WorkLoadMain main = this.workLoadMainService.selectMainByUpdate(req.getMainId());
                  ajaxResult.put("data", main);
               } catch (Exception e) {
                  this.log.error("修改或保存出现异常，", e);
                  ajaxResult = AjaxResult.error("修改或保存出现异常,请联系管理员！");
               }

               return ajaxResult;
            } else {
               ajaxResult = AjaxResult.error("已上报或已确认的不能修改！");
               return ajaxResult;
            }
         }
      }
   }

   @GetMapping({"/main/printInfo"})
   public AjaxResult getPrintInfo() {
      AjaxResult ajaxResult = AjaxResult.success();

      try {
         List<String> typeCodeList = new ArrayList(Arrays.asList("100029001"));
         List<PrintTmplInfo> printTmplList = this.printTmplInfoService.selectTmPmPrintTmplInfoByCodes(typeCodeList);
         ajaxResult.put("printTmplList", printTmplList);
      } catch (Exception e) {
         this.log.error("查询打印模板出现异常，", e);
         ajaxResult = AjaxResult.error("查询打印模板出现异常，请联系管理员！");
      }

      return ajaxResult;
   }

   @GetMapping({"/main/report"})
   @PreAuthorize("@ss.hasPermi('mris:main:report')")
   public AjaxResult workLoadReport(WorkLoadReportReq req) {
      AjaxResult ajaxResult = AjaxResult.success();
      if (!StringUtils.isEmpty(req.getStartDate()) && !StringUtils.isEmpty(req.getEndDate())) {
         try {
            List<WorkLoadReportResp> list = this.workLoadMainService.workLoadReport(req);
            ajaxResult.put("data", list);
         } catch (Exception e) {
            this.log.error("查询工作量统计报表出现异常，", e);
            ajaxResult = AjaxResult.error("查询工作量统计报表出现异常，请联系管理员！");
         }

         return ajaxResult;
      } else {
         ajaxResult = AjaxResult.error("统计时间范围不能为空！");
         return ajaxResult;
      }
   }

   @GetMapping({"/main/delete"})
   @PreAuthorize("@ss.hasPermi('mris:main:list')")
   public AjaxResult delete(Long id) {
      AjaxResult ajaxResult = AjaxResult.success();
      if (id == null) {
         ajaxResult = AjaxResult.error("请求参数不能为空！");
         return ajaxResult;
      } else {
         WorkLoadMain main = this.workLoadMainService.selectWorkLoadMainById(id);
         if (main == null) {
            ajaxResult = AjaxResult.error("该工量数据不存在，请刷新后再试！");
            return ajaxResult;
         } else {
            String status = main.getStatus();
            if (!status.equals("0") && !status.equals("-1")) {
               ajaxResult = AjaxResult.error("只有暂存或撤回状态的工作量才能删除！");
               return ajaxResult;
            } else {
               Integer count = this.workLoadMainService.selectCountBySubminDate(main.getSumDate(), main.getDeptCode());
               if (count > 0) {
                  ajaxResult = AjaxResult.error("当前工作量报表后还有未删除的工作量，请依次进行删除！");
                  return ajaxResult;
               } else {
                  try {
                     this.workLoadMainService.deleteMain(id);
                  } catch (Exception e) {
                     this.log.error("删除的工作量出现异常，", e);
                     ajaxResult = AjaxResult.error("删除的工作量出现异常，请联系管理员！");
                  }

                  return ajaxResult;
               }
            }
         }
      }
   }

   @GetMapping({"/main/cancel"})
   @PreAuthorize("@ss.hasPermi('mris:main:list')")
   public AjaxResult cancel(Long id) {
      AjaxResult ajaxResult = AjaxResult.success();
      if (id == null) {
         ajaxResult = AjaxResult.error("请求参数不能为空！");
         return ajaxResult;
      } else {
         WorkLoadMain main = this.workLoadMainService.selectWorkLoadMainById(id);
         if (main == null) {
            ajaxResult = AjaxResult.error("该工量数据不存在，请刷新后再试！");
            return ajaxResult;
         } else {
            Integer count = this.workLoadMainService.selectCancelCountBySubminDate(main.getSumDate(), main.getDeptCode());
            if (count > 0) {
               ajaxResult = AjaxResult.error("当前工作量报表后还有未撤回的工作量，请依次进行撤回！");
               return ajaxResult;
            } else {
               String status = main.getStatus();
               if (!status.equals("1")) {
                  ajaxResult = AjaxResult.error("只有已上报的工作量才能撤回上报！");
                  return ajaxResult;
               } else {
                  try {
                     WorkLoadMain cancel = this.workLoadMainService.cancel(main);
                     ajaxResult.put("data", cancel);
                  } catch (Exception e) {
                     this.log.error("撤回上报工作量出现异常，", e);
                     ajaxResult = AjaxResult.error("撤回上报工作量出现异常，请联系管理员！");
                  }

                  return ajaxResult;
               }
            }
         }
      }
   }
}
