package com.emr.project.webEditor.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.emr.project.system.domain.vo.SysStaElemVo;
import com.emr.project.webEditor.zb.util.ZBElemVoToElemUtil;
import java.util.List;

public class ElemVoToElemUtil {
   public static String getSysStaElemQuaJson(SysStaElemVo sysStaElemVo, List dropDownDataList, String editorType) {
      switch (editorType) {
         case "ZB":
            return ZBElemVoToElemUtil.getSysStaElemQuaJson(sysStaElemVo, dropDownDataList);
         default:
            return null;
      }
   }

   public static String getQualityJsonString(SysStaElemVo sysStaElemVo, List objectList) {
      return ZBElemVoToElemUtil.getQualityJsonString(sysStaElemVo, objectList);
   }

   public static JSONObject getElemJSONObject(String elemStr) {
      JSONObject jsonObject = JSON.parseObject(elemStr);
      return jsonObject;
   }

   public static void main(String[] args) {
      String elemStr = "{\"elems\":[{\"elem\":{\"TypeName\":\"TextBox\",\"PlaceHolder\":\"？？？？\",\"validFlag\":\"1\",\"IsCtrlHidden\":false,\"sourceSetId\":\"885642622901682176\",\"sourceColu\":\"now_addr_flagty\",\"isNullPrint\":\"FALSE\",\"medicalformulaCon\":\"\",\"elemName\":\"现住址-市（地区、州）\",\"sourFlag\":\"1\",\"sourceUnitId\":\"885642623623102478\",\"macroField\":\"现住址-市（地区、州）\",\"Name\":\"数据元174i\",\"macroReplaceType\":\"1\",\"TextBoxMaxLen\":\"0\",\"clasId\":\"DE02.01\",\"unitId\":\"100035\",\"windowName\":\"\",\"notRecordInXML\":\"TRUE\",\"MustFillContent\":false,\"TextBoxMinLen\":\"0\",\"DelFlag\":\"TRUE\",\"sourceTable\":\"V_PAT_INFO\",\"inputstrphtc\":\"xzzsdqz\",\"consistencyCheck\":\"1\",\"elemCd\":\"DE02.01.009.02\",\"contType\":\"1\",\"specialVerify\":\"1\",\"TextBoxRes\":\"\",\"ViewSecret\":false,\"typeFlag\":\"1\",\"elemId\":\"100035\",\"isMacroReplace\":\"TRUE\",\"Type\":\"1\",\"clasName\":\"人口学及社会经济学特征\",\"elemSex\":\"3\",\"HelpTip\":\"现住址-市（地区、州）\",\"IsNotRecordInXML\":false,\"Id\":\"720\",\"DeleteProtect\":false,\"Printable\":true,\"Tag\":\"\",\"ReverseEdit\":false,\"EditProtect\":false,\"BackgroundColor\":\"dcdcdc\"},\"elemAttrArr\":[\"Id\",\"Name\",\"Type\",\"TypeName\",\"HelpTip\",\"PlaceHolder\",\"IsCtrlHidden\",\"DeleteProtect\",\"EditProtect\",\"ReverseEdit\",\"BackgroundColor\",\"Printable\",\"MustFillContent\",\"ViewSecret\",\"IsNotRecordInXML\",\"TextBoxMaxLen\",\"TextBoxMinLen\",\"DelFlag\",\"Tag\",\"TextBoxRes\",\"clasId\",\"clasName\",\"consistencyCheck\",\"contType\",\"elemCd\",\"elemId\",\"elemName\",\"elemSex\",\"inputstrphtc\",\"isMacroReplace\",\"isNullPrint\",\"macroField\",\"macroReplaceType\",\"medicalformulaCon\",\"notRecordInXML\",\"sourFlag\",\"sourceColu\",\"sourceSetId\",\"sourceTable\",\"sourceUnitId\",\"specialVerify\",\"typeFlag\",\"unitId\",\"validFlag\",\"windowName\"]}]}";
      JSONObject jsonObject = getElemJSONObject(elemStr);
      System.out.println("aaaaaaaaaaaaa");
   }
}
