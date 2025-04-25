package com.emr.project.emr.mapper;

import com.emr.project.emr.domain.BasEmrAcceAuthor;
import java.util.List;

public interface BasEmrAcceAuthorMapper {
   BasEmrAcceAuthor selectBasEmrAcceAuthorById(Long id);

   List selectBasEmrAcceAuthorList(BasEmrAcceAuthor basEmrAcceAuthor);

   int insertBasEmrAcceAuthor(BasEmrAcceAuthor basEmrAcceAuthor);

   int updateBasEmrAcceAuthor(BasEmrAcceAuthor basEmrAcceAuthor);

   int deleteBasEmrAcceAuthorById(Long id);

   int deleteBasEmrAcceAuthorByIds(Long[] ids);

   BasEmrAcceAuthor selectBasEmrAcceAuthorByObjectId(String objectId);

   List selectBasEmrAcceAuthorByObjectIds(List list);
}
