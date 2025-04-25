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

@Service("MrHpCheckSet0602")
public class MrHpCheckSet0602UtilServiceImpl implements MrHpCheckSet06UtilService {
   @Autowired
   private ISysDiaIcdService sysDiaIcdService;

   public Boolean infoVerify(MrHpVo mrHpVo) throws Exception {
      boolean flag = true;
      List<MrHpDia> xyList = (List<MrHpDia>)(mrHpVo.getMrHpDiaXYList() != null ? mrHpVo.getMrHpDiaXYList() : new ArrayList());
      List<MrHpDia> zyList = (List<MrHpDia>)(mrHpVo.getMrHpDiaZYList() != null ? mrHpVo.getMrHpDiaZYList() : new ArrayList());
      xyList.addAll(zyList);
      List<MrHpDia> list = (List)xyList.stream().filter((t) -> t.getDiaItem().equals("03") && t.getDiaClass().equals("01")).collect(Collectors.toList());
      flag = list != null && !list.isEmpty();
      List<String> diaCdList = flag ? (List)list.stream().map((t) -> t.getDiaCd()).collect(Collectors.toList()) : null;
      List<SysDiaIcd> dialist = diaCdList != null ? this.sysDiaIcdService.selectSysDiaIcdByIcdCdList(diaCdList) : null;
      flag = flag && dialist != null && !dialist.isEmpty();
      List<SysDiaIcd> dialistnew = flag ? (List)dialist.stream().filter((t) -> StringUtils.isBlank(t.getFirstDiagFlag()) || StringUtils.isNotBlank(t.getFirstDiagFlag()) && t.getFirstDiagFlag().equals("1")).collect(Collectors.toList()) : null;
      flag = flag && dialistnew != null && !dialistnew.isEmpty() && dialistnew.size() == dialist.size();
      return flag;
   }
}
