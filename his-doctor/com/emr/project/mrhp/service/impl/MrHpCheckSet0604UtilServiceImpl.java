package com.emr.project.mrhp.service.impl;

import com.emr.common.utils.StringUtils;
import com.emr.project.mrhp.domain.vo.MrHpOpeVo;
import com.emr.project.mrhp.domain.vo.MrHpVo;
import com.emr.project.mrhp.service.MrHpCheckSet06UtilService;
import com.emr.project.sys.domain.SysOpeIcd;
import com.emr.project.sys.service.ISysOpeIcdService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("MrHpCheckSet0604")
public class MrHpCheckSet0604UtilServiceImpl implements MrHpCheckSet06UtilService {
   @Autowired
   private ISysOpeIcdService sysOpeIcdService;

   public Boolean infoVerify(MrHpVo mrHpVo) throws Exception {
      boolean flag = true;
      List<MrHpOpeVo> mrHpOpeList = mrHpVo.getMrHpOpeList();
      if (mrHpOpeList != null && !mrHpOpeList.isEmpty()) {
         List<String> icdCdList = (List)mrHpOpeList.stream().map((t) -> t.getOprIcd()).collect(Collectors.toList());
         List<SysOpeIcd> sysOpeIcdList = this.sysOpeIcdService.selectOpeIcdListByIcdCdList(icdCdList);
         String sexCd = mrHpVo.getSexCd();
         if (sexCd.equals("1") || sexCd.equals("2")) {
            List<SysOpeIcd> listNew = (List)sysOpeIcdList.stream().filter((t) -> StringUtils.isBlank(t.getSex()) || t.getSex().equals(sexCd)).collect(Collectors.toList());
            flag = listNew != null && listNew.size() == sysOpeIcdList.size();
         }
      }

      return flag;
   }
}
