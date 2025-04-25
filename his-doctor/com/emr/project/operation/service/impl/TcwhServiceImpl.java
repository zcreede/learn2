package com.emr.project.operation.service.impl;

import com.emr.project.operation.domain.Tcwh;
import com.emr.project.operation.domain.TcwhMx;
import com.emr.project.operation.mapper.TcwhMapper;
import com.emr.project.operation.mapper.TcwhMxMapper;
import com.emr.project.operation.service.TcwhService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TcwhServiceImpl implements TcwhService {
   @Autowired
   private TcwhMapper tcwhMapper;
   @Autowired
   private TcwhMxMapper tcwhMxMapper;

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public void savePackage(Tcwh tcwh, List tcwhMxList) {
      List<Tcwh> addList = new ArrayList();
      addList.add(tcwh);
      this.tcwhMapper.insertList(addList);
      this.tcwhMxMapper.insertList(tcwhMxList);
   }
}
