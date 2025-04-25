package com.emr.common.utils.html;

import com.emr.common.utils.StringUtils;

public class EscapeUtil {
   public static final String RE_HTML_MARK = "(<[^<]*?>)|(<[\\s]*?/[^<]*?>)|(<[^<]*?/[\\s]*?>)";
   private static final char[][] TEXT = new char[64][];

   public static String escape(String text) {
      return encode(text);
   }

   public static String unescape(String content) {
      return decode(content);
   }

   public static String clean(String content) {
      return (new HTMLFilter()).filter(content);
   }

   private static String encode(String text) {
      int len;
      if (text != null && (len = text.length()) != 0) {
         StringBuilder buffer = new StringBuilder(len + (len >> 2));

         for(int i = 0; i < len; ++i) {
            char c = text.charAt(i);
            if (c < '@') {
               buffer.append(TEXT[c]);
            } else {
               buffer.append(c);
            }
         }

         return buffer.toString();
      } else {
         return "";
      }
   }

   public static String decode(String content) {
      if (StringUtils.isEmpty(content)) {
         return content;
      } else {
         StringBuilder tmp = new StringBuilder(content.length());
         int lastPos = 0;
         int pos = 0;

         while(lastPos < content.length()) {
            pos = content.indexOf("%", lastPos);
            if (pos == lastPos) {
               if (content.charAt(pos + 1) == 'u') {
                  char ch = (char)Integer.parseInt(content.substring(pos + 2, pos + 6), 16);
                  tmp.append(ch);
                  lastPos = pos + 6;
               } else {
                  char ch = (char)Integer.parseInt(content.substring(pos + 1, pos + 3), 16);
                  tmp.append(ch);
                  lastPos = pos + 3;
               }
            } else if (pos == -1) {
               tmp.append(content.substring(lastPos));
               lastPos = content.length();
            } else {
               tmp.append(content.substring(lastPos, pos));
               lastPos = pos;
            }
         }

         return tmp.toString();
      }
   }

   public static void main(String[] args) {
      String html = "<script>alert(1);</script>";
      System.out.println(clean(html));
      System.out.println(escape(html));
      System.out.println(unescape(html));
   }

   static {
      for(int i = 0; i < 64; ++i) {
         TEXT[i] = new char[]{(char)i};
      }

      TEXT[39] = "&#039;".toCharArray();
      TEXT[34] = "&#34;".toCharArray();
      TEXT[38] = "&#38;".toCharArray();
      TEXT[60] = "&#60;".toCharArray();
      TEXT[62] = "&#62;".toCharArray();
   }
}
