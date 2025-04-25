package com.emr.project.emr.mapper;

import com.emr.project.emr.domain.WardRounRecfile;
import java.util.List;

public interface WardRounRecfileMapper {
   WardRounRecfile selectWardRounRecfileById(Long id);

   List selectWardRounRecfileList(WardRounRecfile wardRounRecfile);

   int insertWardRounRecfile(WardRounRecfile wardRounRecfile);

   int updateWardRounRecfile(WardRounRecfile wardRounRecfile);

   int deleteWardRounRecfileById(Long id);

   int deleteWardRounRecfileByIds(Long[] ids);
}
