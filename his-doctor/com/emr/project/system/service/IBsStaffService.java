package com.emr.project.system.service;

import com.emr.project.system.domain.BsStaff;
import java.util.List;

public interface IBsStaffService {
   List selectStaffList(List staffCodeList) throws Exception;

   List selectBsStaffByStaffType(BsStaff bsStaff) throws Exception;
}
