package com.emr.project.mrhp.service.impl;

import com.emr.common.utils.CheckIdCard;
import com.emr.common.utils.DateUtils;
import com.emr.project.mrhp.domain.vo.MrHpVo;
import com.emr.project.mrhp.service.MrHpCheckSet06UtilService;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service("MrHpCheckSet0601")
public class MrHpCheckSet0601UtilServiceImpl implements MrHpCheckSet06UtilService {
   public Boolean infoVerify(MrHpVo mrHpVo) throws Exception {
      boolean flag = true;
      flag = flag && CheckIdCard.check(mrHpVo.getCardTypeNo());
      if (flag) {
         String birDateTarget = DateUtils.parseDateToStr("yyyyMMdd", mrHpVo.getBirDate());
         String birDate = CheckIdCard.getBirthDate(mrHpVo.getCardTypeNo());
         flag = birDate.equals(birDateTarget);
      }

      if (flag) {
         Map<String, Object> sexAndAge = CheckIdCard.identityCard(mrHpVo.getCardTypeNo(), mrHpVo.getInhosTime());
         String sex = (String)sexAndAge.get("sex");
         flag = sex.equals(mrHpVo.getSexCd());
         int age = (Integer)sexAndAge.get("age");
         int ageTarget = mrHpVo.getAgeY() == null ? 0 : mrHpVo.getAgeY().intValue();
         int b = age - ageTarget;
         flag = flag && b <= 1 && b >= -1;
      }

      return flag;
   }
}
