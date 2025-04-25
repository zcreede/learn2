package com.emr.project.system.mapper;

import com.emr.project.system.domain.BsStaff;
import java.util.List;

public interface BsStaffMapper {
   BsStaff selectBsStaffById(Long id);

   BsStaff selectBsStaffByStaffCode(String staffCode);

   List selectBsStaffByStaffType(BsStaff bsStaff);

   BsStaff selectByConn(BsStaff record);

   List selectStaffList(List staffCodeList);
}
