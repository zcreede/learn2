package com.emr.project.operation.service;

import com.emr.project.operation.domain.TdCaOperationRoom;
import java.util.List;

public interface ITdCaOperationRoomService {
   TdCaOperationRoom selectTdCaOperationRoomById(Long id) throws Exception;

   List selectTdCaOperationRoomList(TdCaOperationRoom tdCaOperationRoom) throws Exception;

   int insertTdCaOperationRoom(TdCaOperationRoom tdCaOperationRoom) throws Exception;

   int updateTdCaOperationRoom(TdCaOperationRoom tdCaOperationRoom) throws Exception;

   int deleteTdCaOperationRoomByIds(Long[] ids) throws Exception;

   int deleteTdCaOperationRoomById(Long id) throws Exception;

   List selectTdCaOperationRoomListOfSearch(String orgCode, String searchValue);
}
