package com.emr.project.mrhp.service.impl;

import com.emr.common.utils.StringUtils;
import com.emr.project.mrhp.domain.MrHpDia;
import com.emr.project.mrhp.domain.vo.MrHpVo;
import com.emr.project.mrhp.service.MrHpCheckSet06UtilService;
import com.emr.project.sys.domain.SysDiaIcd;
import com.emr.project.sys.service.ISysDiaIcdService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("MrHpCheckSet0603")
public class MrHpCheckSet0603UtilServiceImpl implements MrHpCheckSet06UtilService {
   @Autowired
   private ISysDiaIcdService sysDiaIcdService;

   public Boolean infoVerify(MrHpVo mrHpVo) throws Exception {
      boolean flag = true;
      List<MrHpDia> xyList = mrHpVo.getMrHpDiaXYList();
      List<MrHpDia> zyList = mrHpVo.getMrHpDiaZYList();
      List<String> xyDiaCdList = (List<String>)(xyList != null ? (List)xyList.stream().map((t) -> t.getDiaCd()).collect(Collectors.toList()) : new ArrayList());
      List<String> zyDiaCdList = (List<String>)(zyList != null ? (List)zyList.stream().map((t) -> t.getDiaCd()).collect(Collectors.toList()) : new ArrayList());
      xyDiaCdList.addAll(zyDiaCdList);
      List<String> diaCdList = (List)xyDiaCdList.stream().filter((t) -> StringUtils.isNotBlank(t)).collect(Collectors.toList());
      if (diaCdList != null && !diaCdList.isEmpty()) {
         List<SysDiaIcd> list = this.sysDiaIcdService.selectSysDiaIcdByIcdCdList(diaCdList);
         String sexCd = mrHpVo.getSexCd();
         if (sexCd.equals("1") || sexCd.equals("2")) {
            List<SysDiaIcd> listNew = (List)list.stream().filter((t) -> StringUtils.isBlank(t.getSex()) || t.getSex().equals(sexCd)).collect(Collectors.toList());
            flag = listNew != null && listNew.size() == list.size();
         }
      }

      return flag;
   }
}
