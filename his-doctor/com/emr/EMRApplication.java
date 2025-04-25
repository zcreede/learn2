package com.emr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.zxp.esclientrhl.annotation.EnableESTools;
import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableAsync
@EnableOpenApi
@EnableScheduling
@SpringBootApplication(
   exclude = {DataSourceAutoConfiguration.class}
)
@EnableESTools(
   basePackages = {"com.emr.project.esSearch.service"},
   entityPath = {"com.emr.project.esSearch.domain"},
   printregmsg = true
)
public class EMRApplication {
   public static void main(String[] args) {
      SpringApplication.run(EMRApplication.class, args);
      System.out.println("(♥◠‿◠)ﾉﾞ  启动成功   ლ(´ڡ`ლ)ﾞ  \n .-------.       ____     __        \n |  _ _   \\      \\   \\   /  /    \n | ( ' )  |       \\  _. /  '       \n |(_ o _) /        _( )_ .'         \n | (_,_).' __  ___(_ o _)'          \n |  |\\ \\  |  ||   |(_,_)'         \n |  | \\ `'   /|   `-'  /           \n |  |  \\    /  \\      /           \n ''-'   `'-'    `-..-'              ");
   }
}
