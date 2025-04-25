package com.emr.framework.security.provider;

import com.emr.common.utils.MessageUtils;
import com.emr.framework.security.token.CALoginAuthenticationToken;
import com.emr.project.other.domain.BasCertInfo;
import com.emr.project.other.service.IBasCertInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class CALoginAuthenticationProvider implements AuthenticationProvider {
   @Autowired
   private UserDetailsService userDetailsService;
   @Autowired
   private IBasCertInfoService basCertInfoService;

   public Authentication authenticate(Authentication authentication) throws AuthenticationException {
      String sn = (String)authentication.getCredentials();
      BasCertInfo basCertInfo = this.basCertInfoService.selectBasCertInfoByCerSn(sn);
      if (basCertInfo == null) {
         throw new BadCredentialsException(MessageUtils.message("user.key.not.match"));
      } else {
         UserDetails userDetails = this.userDetailsService.loadUserByUsername(basCertInfo.getEmployeenumber());
         CALoginAuthenticationToken result = new CALoginAuthenticationToken(basCertInfo.getEmployeenumber(), sn);
         result.setDetails(userDetails);
         return result;
      }
   }

   public boolean supports(Class aClass) {
      return CALoginAuthenticationToken.class.isAssignableFrom(aClass);
   }
}
