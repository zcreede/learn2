package com.emr.framework.config;

import com.emr.common.utils.Md5PasswordEncoder;
import com.emr.framework.security.filter.JwtAuthenticationTokenFilter;
import com.emr.framework.security.handle.AuthenticationEntryPointImpl;
import com.emr.framework.security.handle.LogoutSuccessHandlerImpl;
import com.emr.framework.security.provider.AutoLoginAuthenticationProvider;
import com.emr.framework.security.provider.CALoginAuthenticationProvider;
import com.emr.framework.security.provider.OperationRoomLoginAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.filter.CorsFilter;

@EnableGlobalMethodSecurity(
   prePostEnabled = true,
   securedEnabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
   @Autowired
   private UserDetailsService userDetailsService;
   @Autowired
   private AuthenticationEntryPointImpl unauthorizedHandler;
   @Autowired
   private LogoutSuccessHandlerImpl logoutSuccessHandler;
   @Autowired
   private JwtAuthenticationTokenFilter authenticationTokenFilter;
   @Autowired
   private CorsFilter corsFilter;
   @Autowired
   private CALoginAuthenticationProvider caLoginAuthenticationProvider;
   @Autowired
   private OperationRoomLoginAuthenticationProvider operationRoomLoginAuthenticationProvider;
   @Autowired
   private AutoLoginAuthenticationProvider autoLoginAuthenticationProvider;

   @Bean
   public AuthenticationManager authenticationManagerBean() throws Exception {
      return super.authenticationManagerBean();
   }

   protected void configure(HttpSecurity httpSecurity) throws Exception {
      ((HttpSecurity)((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)((HttpSecurity)((HttpSecurity)((HttpSecurity)httpSecurity.csrf().disable()).exceptionHandling().authenticationEntryPoint(this.unauthorizedHandler).and()).sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()).authorizeRequests().antMatchers(HttpMethod.POST, new String[]{"/rpc/oper/*", "/rpc/qc/*", "/rpc/pat/*", "/rpc/mrhp/*", "/rpc/pat/inOutLCLJ"})).permitAll().antMatchers(new String[]{"/auth/login", "/auth/mainlogin", "/auth/orlogin", "/auth/autoLogin", "/auth/ssoGetInfo", "/auth/ssoGetRouter", "/auth/calogin", "/captchaImage", "/captchaImageByUsername/*", "/system/emr/config/caInfo"})).anonymous().antMatchers(HttpMethod.GET, new String[]{"/", "/*.html", "/**/*.html", "/**/*.css", "/**/*.js", "/profile/**", "/emrser/**", "/common/checkKey"})).permitAll().antMatchers(new String[]{"/common/download**"})).anonymous().antMatchers(new String[]{"/common/download/resource**"})).anonymous().antMatchers(new String[]{"/swagger-ui.html"})).anonymous().antMatchers(new String[]{"/swagger-resources/**"})).anonymous().antMatchers(new String[]{"/webjars/**"})).anonymous().antMatchers(new String[]{"/*/api-docs"})).anonymous().antMatchers(new String[]{"/druid/**"})).anonymous().anyRequest()).authenticated().and()).headers().frameOptions().disable();
      httpSecurity.logout().logoutUrl("/logout").logoutSuccessHandler(this.logoutSuccessHandler);
      httpSecurity.addFilterBefore(this.authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
      httpSecurity.addFilterBefore(this.corsFilter, JwtAuthenticationTokenFilter.class);
      httpSecurity.addFilterBefore(this.corsFilter, LogoutFilter.class);
   }

   @Bean
   public BCryptPasswordEncoder bCryptPasswordEncoder() {
      return new BCryptPasswordEncoder();
   }

   @Bean
   public Md5PasswordEncoder md5PasswordEncoder() {
      return new Md5PasswordEncoder();
   }

   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth.authenticationProvider(this.autoLoginAuthenticationProvider).authenticationProvider(this.operationRoomLoginAuthenticationProvider).authenticationProvider(this.caLoginAuthenticationProvider).userDetailsService(this.userDetailsService).passwordEncoder(this.md5PasswordEncoder());
   }
}
