package com.emr.project.webservice.service;

import com.emr.project.webservice.domain.req.FoodborneReq;
import com.emr.project.webservice.domain.resp.WebServiceFoodborneResp;

public interface IFoodborneDiseaseService {
   WebServiceFoodborneResp getFoodborneDiseaseUrl(FoodborneReq foodborneReq) throws Exception;
}
