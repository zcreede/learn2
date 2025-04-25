package com.emr.project.tool.gen.util;

import java.util.Properties;
import org.apache.velocity.app.Velocity;

public class VelocityInitializer {
   public static void initVelocity() {
      Properties p = new Properties();

      try {
         p.setProperty("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
         p.setProperty("resource.default_encoding", "UTF-8");
         Velocity.init(p);
      } catch (Exception e) {
         throw new RuntimeException(e);
      }
   }
}
