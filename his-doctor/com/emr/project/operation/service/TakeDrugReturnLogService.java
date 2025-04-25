package com.emr.project.operation.service;

import java.util.List;
import java.util.Map;

public interface TakeDrugReturnLogService {
   void addTakeDrugListLogList(int operateType, List takeDrugReturns, int returnType, Map sourceDoseMap) throws Exception;
}
