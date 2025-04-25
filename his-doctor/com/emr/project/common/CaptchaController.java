package com.emr.project.common;

import com.emr.common.constant.Constants;
import com.emr.common.utils.UUIDUtils;
import com.emr.common.utils.sign.Base64;
import com.emr.framework.redis.RedisCache;
import com.emr.framework.web.domain.AjaxResult;
import com.google.code.kaptcha.Producer;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CaptchaController {
   @Resource(
      name = "captchaProducer"
   )
   private Producer captchaProducer;
   @Resource(
      name = "captchaProducerMath"
   )
   private Producer captchaProducerMath;
   @Autowired
   private RedisCache redisCache;
   @Value("${emr.captchaType}")
   private String captchaType;

   @GetMapping({"/captchaImageByUsername/{username}"})
   public AjaxResult loginErrorGetCode(@PathVariable String username) {
      AjaxResult ajax = AjaxResult.success();
      String loginErrorKey = "login_error:" + username;
      Integer loginNum = (Integer)this.redisCache.getCacheObject(loginErrorKey);
      loginNum = loginNum == null ? 0 : loginNum;
      if (loginNum >= Constants.LOGIN_ERROR_CAPTCHA_NUM) {
         this.genCode(ajax);
      }

      return ajax;
   }

   @GetMapping({"/captchaImage"})
   public AjaxResult getCode(HttpServletResponse response) throws IOException {
      AjaxResult ajax = AjaxResult.success();
      this.genCode(ajax);
      return ajax;
   }

   private void genCode(AjaxResult ajax) {
      String uuid = UUIDUtils.simpleUUID();
      String verifyKey = "captcha_codes:" + uuid;
      String capStr = null;
      String code = null;
      BufferedImage image = null;
      if ("math".equals(this.captchaType)) {
         String capText = this.captchaProducerMath.createText();
         capStr = capText.substring(0, capText.lastIndexOf("@"));
         code = capText.substring(capText.lastIndexOf("@") + 1);
         image = this.captchaProducerMath.createImage(capStr);
      } else if ("char".equals(this.captchaType)) {
         capStr = code = this.captchaProducer.createText();
         image = this.captchaProducer.createImage(capStr);
      }

      this.redisCache.setCacheObject(verifyKey, code, Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
      FastByteArrayOutputStream os = new FastByteArrayOutputStream();

      try {
         ImageIO.write(image, "jpg", os);
         ajax.put("uuid", uuid);
         ajax.put("img", Base64.encode(os.toByteArray()));
      } catch (IOException e) {
         ajax = AjaxResult.error(e.getMessage());
      }

   }
}
