package com.emr.project.operation.service.impl;

import com.emr.project.operation.mapper.TcwhMxMapper;
import com.emr.project.operation.service.TcwhMxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TcwhMxServiceImpl implements TcwhMxService {
   @Autowired
   private TcwhMxMapper tcwhMxMapper;
}
