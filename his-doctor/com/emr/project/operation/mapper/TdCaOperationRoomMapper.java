package com.emr.project.operation.mapper;

import com.emr.project.operation.domain.TdCaOperationRoom;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TdCaOperationRoomMapper {
   TdCaOperationRoom selectTdCaOperationRoomById(Long id);

   List selectTdCaOperationRoomList(TdCaOperationRoom tdCaOperationRoom);

   int insertTdCaOperationRoom(TdCaOperationRoom tdCaOperationRoom);

   int batchInsert(List list);

   int updateTdCaOperationRoom(TdCaOperationRoom tdCaOperationRoom);

   int batchUpdate(List list);

   int deleteTdCaOperationRoomById(Long id);

   int deleteTdCaOperationRoomByIds(Long[] ids);

   List selectTdCaOperationRoomListOfSearch(@Param("orgCode") String orgCode, @Param("searchValue") String searchValue);

   Long selectMaxId();

   int queryMaxNumber(@Param("OrgCode") String OrgCode, @Param("deptCode") String deptCode);

   int removeCaOperationRoomById(@Param("id") Long id);
}
