package com.emr.project.docOrder.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.http.HttpUtils;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.project.docOrder.domain.req.LcljBaseInfoReq;
import com.emr.project.docOrder.domain.req.StageListReq;
import com.emr.project.docOrder.domain.vo.StageDataList;
import com.emr.project.docOrder.domain.vo.VaryTreeData;
import com.emr.project.docOrder.service.ILCLJBaseInfoService;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.service.ISysEmrConfigService;
import com.emr.project.webservice.utils.SignUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LCLJBaseInfoServiceImpl implements ILCLJBaseInfoService {
   @Autowired
   private ISysEmrConfigService sysEmrConfigService;

   public List getVaryTree() throws Exception {
      List<VaryTreeData> list = new ArrayList();
      String lcljFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0076");
      String lcljCj = this.sysEmrConfigService.selectSysEmrConfigByKey("007601");
      if (StringUtils.isNotBlank(lcljFlag) && lcljFlag.equals("1") && StringUtils.isNotBlank(lcljCj) && lcljCj.equals("LB")) {
         String url = this.sysEmrConfigService.selectSysEmrConfigByKey("007605");
         SysUser user = SecurityUtils.getLoginUser().getUser();
         LcljBaseInfoReq req = new LcljBaseInfoReq();
         req.setOperCode(user.getUserName());
         req.setProjectCode("HIS");
         req.setSign("372C4DCE634A45A184E8CACAD37A8547");
         HashMap<String, Object> paramMap = (HashMap)JSONObject.parseObject(JSONObject.toJSONString(req), HashMap.class);
         String sign = SignUtil.encryptString(paramMap, req.getSign());
         req.setSign(sign);
         paramMap = (HashMap)JSONObject.parseObject(JSONObject.toJSONString(req), HashMap.class);
         String urlBaseInfo2 = url + "?" + SignUtil.mapToString(paramMap);
         String resStr = HttpUtils.sendPost(urlBaseInfo2, JSONObject.toJSONString(req), "application/json");
         AjaxResult res = (AjaxResult)JSONObject.parseObject(resStr, AjaxResult.class);
         if (res.get("code").equals(200)) {
            JSONArray resArr = (JSONArray)res.get("data");

            for(int i = 0; i < resArr.size(); ++i) {
               VaryTreeData varyTreeData = new VaryTreeData();
               JSONObject resObj = (JSONObject)resArr.get(i);
               varyTreeData.setName(resObj.getString("byzlMc"));
               varyTreeData.setCode(resObj.getString("byzlBm"));
               varyTreeData.setPyName(resObj.getString("byzlZjm"));
               JSONArray childrenArr = (JSONArray)resObj.get("children");
               List<VaryTreeData> children = new ArrayList();

               for(int z = 0; z < childrenArr.size(); ++z) {
                  VaryTreeData varyTree = new VaryTreeData();
                  JSONObject resChildObj = (JSONObject)childrenArr.get(z);
                  varyTree.setName(resChildObj.getString("byzlMc"));
                  varyTree.setCode(resChildObj.getString("byzlBm"));
                  varyTree.setPyName(resChildObj.getString("byzlZjm"));
                  children.add(varyTree);
               }

               varyTreeData.setChildren(children);
               list.add(varyTreeData);
            }
         }
      }

      return list;
   }

   public StageDataList getStageList(StageListReq req) throws Exception {
      StageDataList data = new StageDataList();
      String lcljFlag = this.sysEmrConfigService.selectSysEmrConfigByKey("0076");
      String lcljCj = this.sysEmrConfigService.selectSysEmrConfigByKey("007601");
      if (StringUtils.isNotBlank(lcljFlag) && lcljFlag.equals("1") && StringUtils.isNotBlank(lcljCj) && lcljCj.equals("LB")) {
         String url = this.sysEmrConfigService.selectSysEmrConfigByKey("007609");
         req.setProjectCode("HIS");
         req.setSign("372C4DCE634A45A184E8CACAD37A8547");
         HashMap<String, Object> paramMap = (HashMap)JSONObject.parseObject(JSONObject.toJSONString(req), HashMap.class);
         String sign = SignUtil.encryptString(paramMap, req.getSign());
         req.setSign(sign);
         paramMap = (HashMap)JSONObject.parseObject(JSONObject.toJSONString(req), HashMap.class);
         String urlBaseInfo2 = url + "?" + SignUtil.mapToString(paramMap);
         String resStr = HttpUtils.sendPost(urlBaseInfo2, JSONObject.toJSONString(req), "application/json");
         AjaxResult res = (AjaxResult)JSONObject.parseObject(resStr, AjaxResult.class);
         if (res.get("code").equals(200)) {
            JSONArray resArr = (JSONArray)res.get("data");
            if (!resArr.isEmpty()) {
               JSONObject resObject = (JSONObject)resArr.get(0);
               data.setStageCode(resObject.getString("stageBmchr"));
               data.setStageName(resObject.getString("stageMcchr"));
               data.setType(resObject.getString("type"));
               data.setProjectType(resObject.getString("ljmxXmlbchr"));
               data.setStageGsljchr(resObject.getString("stageGsljchr"));
               data.setStageGszljchr(resObject.getString("stageGszljchr"));
               JSONArray childrenArr = (JSONArray)resObject.get("children");
               List<StageDataList> list = new ArrayList();

               for(int i = 0; i < childrenArr.size(); ++i) {
                  StageDataList dataList = new StageDataList();
                  JSONObject resObj = (JSONObject)childrenArr.get(i);
                  dataList.setStageCode(resObj.getString("stageBmchr"));
                  dataList.setStageName(resObj.getString("stageMcchr"));
                  dataList.setType(resObj.getString("type"));
                  dataList.setProjectType(resObj.getString("ljmxXmlbchr"));
                  dataList.setStageGsljchr(resObj.getString("stageGsljchr"));
                  dataList.setStageGszljchr(resObj.getString("stageGszljchr"));
                  list.add(dataList);
               }

               data.setChildren(list);
            }
         }
      }

      return data;
   }
}
