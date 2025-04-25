package com.emr.project.writePad.controller;

import com.emr.common.utils.StringUtils;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.framework.web.page.TableDataInfo;
import com.emr.project.pat.domain.Personalinfo;
import com.emr.project.pat.service.IPersonalinfoService;
import com.emr.project.system.domain.SysDictData;
import com.emr.project.system.service.ISysDictDataService;
import com.emr.project.system.service.ISysEmrConfigService;
import com.emr.project.writePad.domain.EmrIndexSignPerson;
import com.emr.project.writePad.service.IEmrIndexSignPersonService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/writePad/person"})
public class EmrIndexSignPersonController extends BaseController {
   @Autowired
   private IEmrIndexSignPersonService emrIndexSignPersonService;
   @Autowired
   private IPersonalinfoService personalinfoService;
   @Autowired
   private ISysDictDataService sysDictDataService;
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;

   @PreAuthorize("@ss.hasPermi('tmpa:person:list')")
   @GetMapping({"/list"})
   public TableDataInfo list(EmrIndexSignPerson emrIndexSignPerson) {
      this.startPage();
      List<EmrIndexSignPerson> list = this.emrIndexSignPersonService.selectEmrIndexSignPersonList(emrIndexSignPerson);
      return this.getDataTable(list);
   }

   @PreAuthorize("@ss.hasPermi('tmpa:person:query')")
   @GetMapping({"/{id}"})
   public AjaxResult getInfo(@PathVariable("id") Long id) {
      return AjaxResult.success((Object)this.emrIndexSignPersonService.selectEmrIndexSignPersonById(id));
   }

   @GetMapping({"/getInfoByAdmissionNo/{admissionNo}"})
   public AjaxResult getInfoByAdmissionNo(@PathVariable("admissionNo") String admissionNo) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");
      Map<String, Object> map = new HashMap();

      try {
         if (StringUtils.isBlank(String.valueOf(admissionNo))) {
            return AjaxResult.error("未查询到患者信息");
         }

         Personalinfo baseInfo = this.personalinfoService.selectPersonalinfoById(admissionNo);
         map.put("baseInfo", baseInfo);
         List<SysDictData> dictCard = this.sysDictDataService.selectDictDataByType("s022_1");
         map.put("s022_1", dictCard);
         List<SysDictData> dictLxr = this.sysDictDataService.selectDictDataByType("RC033");
         map.put("RC033", dictLxr);
         List<SysDictData> dictInformationVest = this.sysDictDataService.selectDictDataByType("s107");
         map.put("s107", dictInformationVest);
         EmrIndexSignPerson emrIndexSignPerson = new EmrIndexSignPerson();
         emrIndexSignPerson.setAdmissionNo(admissionNo);
         List<EmrIndexSignPerson> signPersonList = this.emrIndexSignPersonService.selectEmrIndexSignPersonList(emrIndexSignPerson);
         if (signPersonList != null && signPersonList.size() <= 0 && baseInfo != null) {
            EmrIndexSignPerson emrIndexSignPerson1 = new EmrIndexSignPerson();
            emrIndexSignPerson1.setKinsfolk(baseInfo.getPatientName());
            emrIndexSignPerson1.setName(baseInfo.getPatientName());
            emrIndexSignPerson1.setAdmissionNo(baseInfo.getPatientId());
            emrIndexSignPerson1.setIdCard(baseInfo.getIdCard());
            emrIndexSignPerson1.setRelationCodeType("0");
            new ArrayList();
            List caType;
            if (StringUtils.isNotBlank(baseInfo.getCardType())) {
               String cardType = null;
               if (baseInfo.getCardType().equals("1")) {
                  cardType = "1";
               } else if (baseInfo.getCardType().equals("2")) {
                  cardType = "4";
               } else if (baseInfo.getCardType().equals("3")) {
                  cardType = "2";
               } else if (!baseInfo.getCardType().equals("5") && !baseInfo.getCardType().equals("6")) {
                  if (baseInfo.getCardType().equals("8")) {
                     cardType = "3";
                  } else {
                     cardType = "0";
                  }
               } else {
                  cardType = "5";
               }

               caType = (List)dictCard.stream().filter((t) -> cardType.equals(t.getDictValue())).collect(Collectors.toList());
               emrIndexSignPerson1.setCardCode(cardType);
               emrIndexSignPerson1.setCardName(((SysDictData)caType.get(0)).getDictLabel());
            } else {
               emrIndexSignPerson1.setCardCode("1");
               caType = (List)dictCard.stream().filter((t) -> "1".equals(t.getDictValue())).collect(Collectors.toList());
               emrIndexSignPerson1.setCardName(((SysDictData)caType.get(0)).getDictLabel());
            }

            String cardName = null;
            if (caType != null && caType.size() > 0) {
               cardName = ((SysDictData)caType.get(0)).getDictLabel();
            }

            emrIndexSignPerson1.setCardName(cardName);
            emrIndexSignPerson1.setRelationCode("0");
            List<SysDictData> lxrTypeList = (List)dictLxr.stream().filter((t) -> "0".equals(t.getDictValue())).collect(Collectors.toList());
            String lxrName = null;
            if (lxrTypeList != null && lxrTypeList.size() > 0) {
               lxrName = ((SysDictData)lxrTypeList.get(0)).getDictLabel();
            }

            emrIndexSignPerson1.setRelationName(lxrName);
            signPersonList.add(emrIndexSignPerson1);
            this.emrIndexSignPersonService.updateEmrIndexSignPerson(emrIndexSignPerson1);
         }

         map.put("signPerson", signPersonList);
         String qdh = this.sysEmrConfigService.selectSysEmrConfigByKey("0078");
         map.put("config_0078", qdh);
         String caType = this.sysEmrConfigService.selectSysEmrConfigByKey("0002");
         map.put("config_0002", caType);
         ajaxResult.put("data", map);
      } catch (Exception e) {
         this.log.error("查询患者信息异常", e);
         ajaxResult = AjaxResult.error("查询患者和亲属关系出错");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('emr:index:save')")
   @Log(
      title = "患者与亲属关系",
      businessType = BusinessType.INSERT
   )
   @PostMapping
   public AjaxResult add(@RequestBody EmrIndexSignPerson emrIndexSignPerson) {
      return this.toAjax(this.emrIndexSignPersonService.insertEmrIndexSignPerson(emrIndexSignPerson));
   }

   @PreAuthorize("@ss.hasPermi('emr:index:add')")
   @Log(
      title = "患者与亲属关系",
      businessType = BusinessType.UPDATE
   )
   @PutMapping({"/add"})
   public AjaxResult edit(@RequestBody EmrIndexSignPerson emrIndexSignPerson) {
      AjaxResult ajaxResult = AjaxResult.success("新增成功");
      boolean flag = true;

      try {
         if (flag && StringUtils.isBlank(emrIndexSignPerson.getAdmissionNo())) {
            flag = false;
            ajaxResult = AjaxResult.error("查询患者信息失败");
         }

         if (flag && StringUtils.isBlank(emrIndexSignPerson.getIdCard())) {
            flag = false;
            ajaxResult = AjaxResult.error("请输入证件号码");
         }

         if (flag && StringUtils.isBlank(emrIndexSignPerson.getKinsfolk())) {
            flag = false;
            ajaxResult = AjaxResult.error("请填写姓名");
         }

         if (flag && StringUtils.isBlank(emrIndexSignPerson.getRelationName())) {
            flag = false;
            ajaxResult = AjaxResult.error("请选择医患关系");
         }

         if (flag && StringUtils.isBlank(emrIndexSignPerson.getRelationCode())) {
            flag = false;
            ajaxResult = AjaxResult.error("请选择医患关系");
         }

         if (flag && StringUtils.isBlank(emrIndexSignPerson.getCardName())) {
            flag = false;
            ajaxResult = AjaxResult.error("请选择证件类型");
         }

         if (flag) {
            EmrIndexSignPerson emrIndexSignPersonRes = this.emrIndexSignPersonService.updateEmrIndexSignPerson(emrIndexSignPerson);
            ajaxResult.put("data", emrIndexSignPersonRes);
         }
      } catch (Exception e) {
         this.log.error("新增医患关系出现异常！", e);
         ajaxResult = AjaxResult.error("新增医患关系出现异常!");
      }

      return ajaxResult;
   }

   @PreAuthorize("@ss.hasPermi('tmpa:person:remove')")
   @Log(
      title = "患者与亲属关系",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/{ids}"})
   public AjaxResult remove(@PathVariable Long[] ids) {
      return this.toAjax(this.emrIndexSignPersonService.deleteEmrIndexSignPersonByIds(ids));
   }

   @Log(
      title = "患者与亲属关系",
      businessType = BusinessType.DELETE
   )
   @DeleteMapping({"/delete/{id}"})
   public AjaxResult delete(@PathVariable Long id) {
      return this.toAjax(this.emrIndexSignPersonService.deleteEmrIndexSignPersonById(id));
   }
}
