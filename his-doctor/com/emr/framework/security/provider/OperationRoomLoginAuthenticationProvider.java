package com.emr.framework.security.provider;

import com.emr.common.utils.MessageUtils;
import com.emr.framework.security.token.OperationRoomAuthenticationToken;
import com.emr.project.system.domain.BasEmployee;
import com.emr.project.system.service.IBasEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class OperationRoomLoginAuthenticationProvider implements AuthenticationProvider {
   @Autowired
   private IBasEmployeeService basEmployeeService;
   @Autowired
   private UserDetailsService userDetailsService;

   public Authentication authenticate(Authentication authentication) throws AuthenticationException {
      String sn = (String)authentication.getCredentials();
      BasEmployee basEmployee = this.basEmployeeService.selectByEmplNumber(sn);
      if (basEmployee == null) {
         throw new BadCredentialsException(MessageUtils.message("user.key.not.match"));
      } else {
         UserDetails userDetails = this.userDetailsService.loadUserByUsername(basEmployee.getEmplNumber());
         OperationRoomAuthenticationToken result = new OperationRoomAuthenticationToken(basEmployee.getEmplNumber(), sn);
         result.setDetails(userDetails);
         return result;
      }
   }

   public boolean supports(Class aClass) {
      return OperationRoomAuthenticationToken.class.isAssignableFrom(aClass);
   }
}
