package com.emr.project.tool.swagger;

import com.emr.common.utils.SnowIdUtils;
import com.emr.common.utils.StringUtils;
import com.emr.framework.web.controller.BaseController;
import com.emr.framework.web.domain.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(
   value = "test-controller",
   tags = {"测试controller"}
)
@RestController
@RequestMapping({"/test/user"})
public class TestController extends BaseController {
   private static final Map users = new LinkedHashMap();

   public TestController() {
      users.put(1, new UserEntity(1, "admin", "admin123", "15888888888"));
      users.put(2, new UserEntity(2, "ry", "admin123", "15666666666"));
   }

   @ApiOperation("获取用户列表")
   @GetMapping({"/list"})
   public AjaxResult userList() {
      List<UserEntity> userList = new ArrayList(users.values());
      Long snowId = SnowIdUtils.uniqueLong();
      this.log.info("==============snowId: " + snowId);
      return AjaxResult.success((Object)userList);
   }

   @ApiOperation("获取用户详细")
   @ApiImplicitParam(
      name = "userId",
      value = "用户ID",
      required = true,
      dataType = "int",
      paramType = "path"
   )
   @GetMapping({"/{userId}"})
   public AjaxResult getUser(@PathVariable Integer userId) {
      return !users.isEmpty() && users.containsKey(userId) ? AjaxResult.success(users.get(userId)) : this.error("用户不存在");
   }

   @ApiOperation("新增用户")
   @ApiImplicitParams({@ApiImplicitParam(
   name = "userId",
   value = "用户id",
   dataType = "Integer"
), @ApiImplicitParam(
   name = "username",
   value = "用户名称",
   dataType = "String"
), @ApiImplicitParam(
   name = "password",
   value = "用户密码",
   dataType = "String"
), @ApiImplicitParam(
   name = "mobile",
   value = "用户手机",
   dataType = "String"
)})
   @PostMapping({"/save"})
   public AjaxResult save(UserEntity user) {
      return !StringUtils.isNull(user) && !StringUtils.isNull(user.getUserId()) ? AjaxResult.success(users.put(user.getUserId(), user)) : this.error("用户ID不能为空");
   }

   @ApiOperation("更新用户")
   @PutMapping({"/update"})
   public AjaxResult update(@RequestBody UserEntity user) {
      if (!StringUtils.isNull(user) && !StringUtils.isNull(user.getUserId())) {
         if (!users.isEmpty() && users.containsKey(user.getUserId())) {
            users.remove(user.getUserId());
            return AjaxResult.success(users.put(user.getUserId(), user));
         } else {
            return this.error("用户不存在");
         }
      } else {
         return this.error("用户ID不能为空");
      }
   }

   @ApiOperation("删除用户信息")
   @ApiImplicitParam(
      name = "userId",
      value = "用户ID",
      required = true,
      dataType = "int",
      paramType = "path"
   )
   @DeleteMapping({"/{userId}"})
   public AjaxResult delete(@PathVariable Integer userId) {
      if (!users.isEmpty() && users.containsKey(userId)) {
         users.remove(userId);
         return this.success();
      } else {
         return this.error("用户不存在");
      }
   }
}
