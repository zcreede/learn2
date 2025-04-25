package com.emr.framework.security.filter;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.StringUtils;
import com.emr.framework.security.LoginUser;
import com.emr.framework.security.service.TokenService;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
   @Autowired
   private TokenService tokenService;

   protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
      LoginUser loginUser = this.tokenService.getLoginUser(request);
      if (StringUtils.isNotNull(loginUser) && StringUtils.isNull(SecurityUtils.getAuthentication())) {
         this.tokenService.verifyToken(loginUser);
         UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, (Object)null, loginUser.getAuthorities());
         authenticationToken.setDetails((new WebAuthenticationDetailsSource()).buildDetails(request));
         SecurityContextHolder.getContext().setAuthentication(authenticationToken);
      }

      chain.doFilter(request, response);
   }
}
