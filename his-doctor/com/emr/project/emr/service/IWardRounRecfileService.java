package com.emr.project.emr.service;

import com.emr.project.emr.domain.WardRounRecfile;
import java.util.List;

public interface IWardRounRecfileService {
   WardRounRecfile selectWardRounRecfileById(Long id);

   List selectWardRounRecfileList(WardRounRecfile wardRounRecfile) throws Exception;

   int insertWardRounRecfile(WardRounRecfile wardRounRecfile) throws Exception;

   int updateWardRounRecfile(WardRounRecfile wardRounRecfile) throws Exception;

   int deleteWardRounRecfileByIds(Long[] ids) throws Exception;

   int deleteWardRounRecfileById(Long id) throws Exception;
}
