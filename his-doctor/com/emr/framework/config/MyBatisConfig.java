package com.emr.framework.config;

import com.emr.common.utils.StringUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import javax.sql.DataSource;
import org.apache.ibatis.io.VFS;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.ClassUtils;

@Configuration
public class MyBatisConfig {
   @Autowired
   private Environment env;
   static final String DEFAULT_RESOURCE_PATTERN = "**/*.class";

   public static String setTypeAliasesPackage(String typeAliasesPackage) {
      ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
      MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(resolver);
      List<String> allResult = new ArrayList();

      try {
         for(String aliasesPackage : typeAliasesPackage.split(",")) {
            List<String> result = new ArrayList();
            aliasesPackage = "classpath*:" + ClassUtils.convertClassNameToResourcePath(aliasesPackage.trim()) + "/" + "**/*.class";
            Resource[] resources = resolver.getResources(aliasesPackage);
            if (resources != null && resources.length > 0) {
               MetadataReader metadataReader = null;

               for(Resource resource : resources) {
                  if (resource.isReadable()) {
                     metadataReader = metadataReaderFactory.getMetadataReader(resource);

                     try {
                        result.add(Class.forName(metadataReader.getClassMetadata().getClassName()).getPackage().getName());
                     } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                     }
                  }
               }
            }

            if (result.size() > 0) {
               HashSet<String> hashResult = new HashSet(result);
               allResult.addAll(hashResult);
            }
         }

         if (allResult.size() <= 0) {
            throw new RuntimeException("mybatis typeAliasesPackage 路径扫描错误,参数typeAliasesPackage:" + typeAliasesPackage + "未找到任何包");
         }

         typeAliasesPackage = String.join(",", (String[])allResult.toArray(new String[0]));
      } catch (IOException e) {
         e.printStackTrace();
      }

      return typeAliasesPackage;
   }

   public Resource[] resolveMapperLocations(String[] mapperLocations) {
      ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();
      List<Resource> resources = new ArrayList();
      if (mapperLocations != null) {
         for(String mapperLocation : mapperLocations) {
            try {
               Resource[] mappers = resourceResolver.getResources(mapperLocation);
               resources.addAll(Arrays.asList(mappers));
            } catch (IOException var9) {
            }
         }
      }

      return (Resource[])resources.toArray(new Resource[resources.size()]);
   }

   @Bean
   public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
      String typeAliasesPackage = this.env.getProperty("mybatis.typeAliasesPackage");
      String mapperLocations = this.env.getProperty("mybatis.mapperLocations");
      String configLocation = this.env.getProperty("mybatis.configLocation");
      typeAliasesPackage = setTypeAliasesPackage(typeAliasesPackage);
      VFS.addImplClass(SpringBootVFS.class);
      SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
      sessionFactory.setDataSource(dataSource);
      sessionFactory.setTypeAliasesPackage(typeAliasesPackage);
      sessionFactory.setMapperLocations(this.resolveMapperLocations(StringUtils.split(mapperLocations, ",")));
      sessionFactory.setConfigLocation((new DefaultResourceLoader()).getResource(configLocation));
      return sessionFactory.getObject();
   }
}
