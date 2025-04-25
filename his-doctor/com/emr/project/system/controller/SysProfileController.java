package com.emr.project.system.controller;

import com.emr.common.utils.SecurityUtils;
import com.emr.common.utils.ServletUtils;
import com.emr.common.utils.StringUtils;
import com.emr.common.utils.file.FileUploadUtils;
import com.emr.framework.aspectj.lang.annotation.Log;
import com.emr.framework.aspectj.lang.enums.BusinessType;
import com.emr.framework.config.EMRConfig;
import com.emr.framework.security.LoginUser;
import com.emr.framework.security.service.TokenService;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.project.system.domain.SysUser;
import com.emr.project.system.service.ISysUserService;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping({"/system/user/profile"})
public class SysProfileController extends BaseController {
   @Autowired
   private ISysUserService userService;
   @Autowired
   private TokenService tokenService;

   @GetMapping
   public AjaxResult profile() {
      LoginUser loginUser = this.tokenService.getLoginUser(ServletUtils.getRequest());
      SysUser user = loginUser.getUser();
      AjaxResult ajax = AjaxResult.success((Object)user);
      return ajax;
   }

   @Log(
      title = "个人信息",
      businessType = BusinessType.UPDATE
   )
   @PutMapping
   public AjaxResult updateProfile(@RequestBody SysUser user) {
      if (StringUtils.isNotEmpty(user.getPhonenumber()) && "1".equals(this.userService.checkPhoneUnique(user))) {
         return AjaxResult.error("修改用户'" + user.getUserName() + "'失败，手机号码已存在");
      } else if (StringUtils.isNotEmpty(user.getEmail()) && "1".equals(this.userService.checkEmailUnique(user))) {
         return AjaxResult.error("修改用户'" + user.getUserName() + "'失败，邮箱账号已存在");
      } else if (this.userService.updateUserProfile(user) > 0) {
         LoginUser loginUser = this.tokenService.getLoginUser(ServletUtils.getRequest());
         loginUser.getUser().setNickName(user.getNickName());
         loginUser.getUser().setPhonenumber(user.getPhonenumber());
         loginUser.getUser().setEmail(user.getEmail());
         loginUser.getUser().setSex(user.getSex());
         this.tokenService.setLoginUser(loginUser);
         return AjaxResult.success();
      } else {
         return AjaxResult.error("修改个人信息异常，请联系管理员");
      }
   }

   @Log(
      title = "个人信息",
      businessType = BusinessType.UPDATE
   )
   @PutMapping({"/updatePwd"})
   public AjaxResult updatePwd(String oldPassword, String newPassword) {
      LoginUser loginUser = this.tokenService.getLoginUser(ServletUtils.getRequest());
      String userName = loginUser.getUsername();
      String password = loginUser.getPassword();
      if (!SecurityUtils.matchesPassword(oldPassword, password)) {
         return AjaxResult.error("修改密码失败，旧密码错误");
      } else if (SecurityUtils.matchesPassword(newPassword, password)) {
         return AjaxResult.error("新密码不能与旧密码相同");
      } else if (this.userService.resetUserPwd(userName, SecurityUtils.encryptPassword(newPassword)) > 0) {
         loginUser.getUser().setPassword(SecurityUtils.encryptPassword(newPassword));
         this.tokenService.setLoginUser(loginUser);
         return AjaxResult.success();
      } else {
         return AjaxResult.error("修改密码异常，请联系管理员");
      }
   }

   @Log(
      title = "用户头像",
      businessType = BusinessType.UPDATE
   )
   @PostMapping({"/avatar"})
   public AjaxResult avatar(@RequestParam("avatarfile") MultipartFile file) throws IOException {
      if (!file.isEmpty()) {
         LoginUser loginUser = this.tokenService.getLoginUser(ServletUtils.getRequest());
         String avatar = FileUploadUtils.upload(EMRConfig.getAvatarPath(), file);
         if (this.userService.updateUserAvatar(loginUser.getUsername(), avatar)) {
            AjaxResult ajax = AjaxResult.success();
            ajax.put("imgUrl", avatar);
            loginUser.getUser().setAvatar(avatar);
            this.tokenService.setLoginUser(loginUser);
            return ajax;
         }
      }

      return AjaxResult.error("上传图片异常，请联系管理员");
   }
}
