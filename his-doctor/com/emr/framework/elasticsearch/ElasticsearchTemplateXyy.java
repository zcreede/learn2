package com.emr.framework.elasticsearch;

import java.util.List;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.zxp.esclientrhl.repository.PageList;
import org.zxp.esclientrhl.repository.PageSortHighLight;

public interface ElasticsearchTemplateXyy {
   PageList search(QueryBuilder var1, PageSortHighLight var2, Class var3) throws Exception;

   PageList search(QueryBuilder var1, PageSortHighLight var2, Class var3, String... var4) throws Exception;

   BulkResponse[] saveBatch(List var1, String indexname) throws Exception;

   BulkResponse batchUpdate(QueryBuilder queryBuilder, Object t, Class clazz, int limitcount, boolean asyn) throws Exception;
}
