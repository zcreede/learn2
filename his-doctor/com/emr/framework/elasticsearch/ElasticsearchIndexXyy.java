package com.emr.framework.elasticsearch;

public interface ElasticsearchIndexXyy {
   void createIndex(Class clazz, String indexName) throws Exception;

   boolean exists(Class var1, String indexName) throws Exception;

   void dropIndex(String indexname) throws Exception;

   String getSetting(Class clazz, String index, String setting) throws Exception;
}
