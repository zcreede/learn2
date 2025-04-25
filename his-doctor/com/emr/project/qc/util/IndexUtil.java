package com.emr.project.qc.util;

import com.emr.project.emr.domain.Index;
import com.emr.project.emr.domain.SubfileIndex;
import com.emr.project.mrhp.domain.MrHp;
import com.emr.project.pat.domain.vo.VisitinfoVo;
import com.emr.project.qc.domain.vo.EmrQcFlowVo;
import com.emr.project.system.domain.SysDictData;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class IndexUtil {
   public static Map getIndexMrTypeAndName(List dictDataList, MrHp mrHp, Index index, SubfileIndex subfileIndex) {
      Map<String, Object> res = new HashMap();
      Map<String, SysDictData> dictDataMap = (Map)dictDataList.stream().collect(Collectors.toMap((t) -> t.getDictValue(), Function.identity()));
      String mrType = null;
      String mrTypeName = null;
      String doctCd = null;
      String doctName = null;
      if (mrHp != null) {
         mrType = "61";
         mrTypeName = "病案首页";
         doctCd = mrHp.getResDocCd();
         doctName = mrHp.getResDocName();
      } else {
         mrType = index != null ? index.getMrType() : subfileIndex.getMrType();
         mrTypeName = ((SysDictData)dictDataMap.get(mrType)).getDictLabel();
         doctCd = index != null ? index.getCrePerCode() : subfileIndex.getCrePerCode();
         doctName = index != null ? index.getCrePerName() : subfileIndex.getCrePerName();
      }

      res.put("mrType", mrType);
      res.put("mrTypeName", mrTypeName);
      res.put("doctCd", doctCd);
      res.put("doctName", doctName);
      return res;
   }

   public static void setVisitinfoFlag(EmrQcFlowVo rt, Map visitinfoMap) {
      rt.setOperIcon(((VisitinfoVo)visitinfoMap.get("operFlag")).getColorValue());
      rt.setConsIcon(((VisitinfoVo)visitinfoMap.get("consFlag")).getColorValue());
      rt.setDieIcon(((VisitinfoVo)visitinfoMap.get("dieFlag")).getColorValue());
      rt.setBloodIcon(((VisitinfoVo)visitinfoMap.get("bloodTrans")).getColorValue());
      rt.setChangeIcon(((VisitinfoVo)visitinfoMap.get("changeFlag")).getColorValue());
      rt.setInfectIcon(((VisitinfoVo)visitinfoMap.get("infectFlag")).getColorValue());
      rt.setRescueIcon(((VisitinfoVo)visitinfoMap.get("rescueFlag")).getColorValue());
      rt.setDayNumIcon(((VisitinfoVo)visitinfoMap.get("dayNumFlag")).getColorValue());
      rt.setCostSumIcon(((VisitinfoVo)visitinfoMap.get("costSumFlag")).getColorValue());
   }
}
