package com.emr.framework.security.token;

import java.util.Collection;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class CALoginAuthenticationToken extends AbstractAuthenticationToken {
   private final Object principal;
   private Object credentials;

   public CALoginAuthenticationToken(Collection authorities, Object principal, Object credentials) {
      super(authorities);
      this.principal = principal;
      this.credentials = credentials;
      super.setAuthenticated(true);
   }

   public CALoginAuthenticationToken(Object principal, Object credentials) {
      super((Collection)null);
      this.principal = principal;
      this.credentials = credentials;
      super.setAuthenticated(false);
   }

   public Object getCredentials() {
      return this.credentials;
   }

   public Object getPrincipal() {
      return this.principal;
   }

   public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
      if (isAuthenticated) {
         throw new IllegalArgumentException("Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
      } else {
         super.setAuthenticated(false);
      }
   }

   public void eraseCredentials() {
      super.eraseCredentials();
      this.credentials = null;
   }
}
