package com.emr.common.filter;

import com.emr.common.utils.StringUtils;
import com.emr.common.utils.html.EscapeUtil;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import org.apache.commons.io.IOUtils;

public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {
   public XssHttpServletRequestWrapper(HttpServletRequest request) {
      super(request);
   }

   public String[] getParameterValues(String name) {
      String[] values = super.getParameterValues(name);
      if (values == null) {
         return super.getParameterValues(name);
      } else {
         int length = values.length;
         String[] escapseValues = new String[length];

         for(int i = 0; i < length; ++i) {
            escapseValues[i] = EscapeUtil.clean(values[i]).trim();
         }

         return escapseValues;
      }
   }

   public ServletInputStream getInputStream() throws IOException {
      if (!this.isJsonRequest()) {
         return super.getInputStream();
      } else {
         String json = IOUtils.toString(super.getInputStream(), "utf-8");
         if (StringUtils.isEmpty(json)) {
            return super.getInputStream();
         } else {
            json = EscapeUtil.clean(json).trim();
            final ByteArrayInputStream bis = new ByteArrayInputStream(json.getBytes("utf-8"));
            return new ServletInputStream() {
               public boolean isFinished() {
                  return true;
               }

               public boolean isReady() {
                  return true;
               }

               public void setReadListener(ReadListener readListener) {
               }

               public int read() throws IOException {
                  return bis.read();
               }
            };
         }
      }
   }

   public boolean isJsonRequest() {
      String header = super.getHeader("Content-Type");
      return StringUtils.startsWithIgnoreCase(header, "application/json");
   }
}
