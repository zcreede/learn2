package com.emr.project.common;

import com.emr.common.utils.DateUtils;
import com.emr.common.utils.StringUtils;
import com.emr.common.utils.file.FileUploadUtils;
import com.emr.common.utils.file.FileUtils;
import com.emr.framework.config.EMRConfig;
import com.emr.framework.config.ServerConfig;
import com.emr.framework.redis.RedisCache;
import com.emr.framework.web.domain.AjaxResult;
import com.emr.project.common.domain.vo.BirthdayVo;
import com.emr.project.common.domain.vo.CheckKeyVo;
import com.emr.project.common.domain.vo.NursingLevelVo;
import com.emr.project.common.service.ICommonService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class CommonController {
   private static final Logger log = LoggerFactory.getLogger(CommonController.class);
   @Autowired
   private ServerConfig serverConfig;
   @Autowired
   private ICommonService commonService;
   @Autowired
   private RedisCache redisCache;

   @GetMapping({"common/download"})
   public void fileDownload(String fileName, Boolean delete, HttpServletResponse response, HttpServletRequest request) {
      try {
         if (!FileUtils.checkAllowDownload(fileName)) {
            throw new Exception(StringUtils.format("文件名称({})非法，不允许下载。 ", fileName));
         }

         String realFileName = System.currentTimeMillis() + fileName.substring(fileName.indexOf("_") + 1);
         String filePath = EMRConfig.getDownloadPath() + fileName;
         response.setContentType("application/octet-stream");
         FileUtils.setAttachmentResponseHeader(response, realFileName);
         FileUtils.writeBytes(filePath, response.getOutputStream());
         if (delete) {
            FileUtils.deleteFile(filePath);
         }
      } catch (Exception e) {
         log.error("下载文件失败", e);
      }

   }

   @PostMapping({"/common/upload"})
   public AjaxResult uploadFile(MultipartFile file) throws Exception {
      try {
         String filePath = EMRConfig.getUploadPath();
         String fileName = FileUploadUtils.upload(filePath, file);
         String url = this.serverConfig.getUrl() + fileName;
         AjaxResult ajax = AjaxResult.success();
         ajax.put("fileName", fileName);
         ajax.put("url", url);
         return ajax;
      } catch (Exception e) {
         return AjaxResult.error(e.getMessage());
      }
   }

   @GetMapping({"/common/download/resource"})
   public void resourceDownload(String resource, HttpServletRequest request, HttpServletResponse response) throws Exception {
      try {
         if (!FileUtils.checkAllowDownload(resource)) {
            throw new Exception(StringUtils.format("资源文件({})非法，不允许下载。 ", resource));
         }

         String localPath = EMRConfig.getProfile();
         String downloadPath = localPath + StringUtils.substringAfter(resource, "/profile");
         String downloadName = StringUtils.substringAfterLast(downloadPath, "/");
         response.setContentType("application/octet-stream");
         FileUtils.setAttachmentResponseHeader(response, downloadName);
         FileUtils.writeBytes(downloadPath, response.getOutputStream());
      } catch (Exception e) {
         log.error("下载文件失败", e);
      }

   }

   @RequestMapping({"/con/ntrInfo"})
   @ResponseBody
   public AjaxResult getNyrInfo(@RequestBody BirthdayVo birthdayVo) {
      AjaxResult ajaxResult = AjaxResult.success("计算成功");
      boolean flag = true;
      if (flag && StringUtils.isBlank(birthdayVo.getCsrq())) {
         flag = false;
         return AjaxResult.error("出生日期不能为空");
      } else if (flag && StringUtils.isBlank(birthdayVo.getRyrq())) {
         flag = false;
         return AjaxResult.error("入院日期不能为空");
      } else {
         if (flag) {
            try {
               String csrq = birthdayVo.getCsrq();
               String ryrq = birthdayVo.getRyrq();
               SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
               Date birthday = simple.parse(csrq);
               Date ryrqD = simple.parse(ryrq);
               Map map = DateUtils.getAge(birthday, ryrqD);
               ajaxResult.put("date", map);
            } catch (Exception e) {
               log.error("年龄计算出现异常：", e);
               ajaxResult = AjaxResult.error("年龄计算出现异常，请联系系统管理员");
            }
         }

         return ajaxResult;
      }
   }

   @GetMapping({"common/queryQcFlag"})
   public AjaxResult queryQcFlag() {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         Boolean isQCDept = this.commonService.isQCDept();
         ajaxResult.put("isQCDept", isQCDept);
      } catch (Exception e) {
         log.error("查询是否是质控科室出现异常", e);
         ajaxResult = AjaxResult.error("查询是否是质控科室出现异常");
      }

      return ajaxResult;
   }

   @GetMapping({"common/nursingLevelList"})
   public AjaxResult nursingLevelList() {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         List<NursingLevelVo> list = this.commonService.nursingLevelList();
         ajaxResult.put("data", list);
      } catch (Exception e) {
         log.error("查询所有护理等级列表出现异常", e);
         ajaxResult = AjaxResult.error("查询所有护理等级列表出现异常");
      }

      return ajaxResult;
   }

   @GetMapping({"common/checkKey"})
   public AjaxResult checkKey(CheckKeyVo vo) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");
      if (StringUtils.isEmpty(vo.getFlag())) {
         ajaxResult = AjaxResult.error("参数不能为空！");
         return ajaxResult;
      } else if (StringUtils.isEmpty(vo.getKey())) {
         ajaxResult = AjaxResult.error("参数不能为空！");
         return ajaxResult;
      } else if (!vo.getFlag().equals("1") && !vo.getFlag().equals("2")) {
         ajaxResult = AjaxResult.error("操作类型错误！");
         return ajaxResult;
      } else {
         if (vo.getFlag().equals("1")) {
            this.redisCache.setCacheObject("check_key:" + vo.getKey(), "1", 30, TimeUnit.SECONDS);
         } else {
            Object object = this.redisCache.getCacheObject("check_key:" + vo.getKey());
            if (object != null) {
               this.redisCache.deleteObject("check_key:" + vo.getKey());
               ajaxResult = AjaxResult.success("key存在！", Boolean.TRUE);
            } else {
               ajaxResult = AjaxResult.success("key不存在！", Boolean.FALSE);
            }
         }

         return ajaxResult;
      }
   }

   @GetMapping({"common/timeIsWorkingTime"})
   public AjaxResult timeIsWorkingTime(String timeStr) {
      AjaxResult ajaxResult = AjaxResult.success("查询成功");

      try {
         Date time = DateUtils.parseDate(timeStr, new String[]{DateUtils.YYYY_MM_DD_HH_MM_SS});
         boolean isHoliday = this.commonService.dayIsHoliday(time);
         boolean isWorkingTime = this.commonService.timeIsWorkingTime(time);
         boolean isWorkingTime2 = this.commonService.dateIsWorkingTime(time);
         ajaxResult = AjaxResult.success("查询成功");
         ajaxResult.put("isHoliday", isHoliday);
         ajaxResult.put("isWorkingTime", isWorkingTime);
         ajaxResult.put("isWorkingTime2", isWorkingTime2);
      } catch (Exception e) {
         log.error("查询当前时间是否是上班时间出现异常：", e);
      }

      return ajaxResult;
   }
}
