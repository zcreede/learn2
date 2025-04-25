package com.emr.project.docOrder.service;

import com.emr.project.pat.domain.Visitinfo;
import com.emr.project.system.domain.SysUser;
import java.util.List;

public interface ITdMsgSendMainService {
   void addMsgs(List orderList, Visitinfo visitinfo, int operationType, String operationTypeName) throws Exception;

   void operMsgs(List orderNoList, SysUser user) throws Exception;

   void revokeMsg(List orderNoList, SysUser user) throws Exception;
}
