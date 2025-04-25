package com.emr.project.system.mapper;

import java.util.List;

public interface SysUserPostMapper {
   int deleteUserPostByUserId(Long userId);

   int countUserPostById(Long postId);

   int deleteUserPost(Long[] ids);

   int batchUserPost(List userPostList);
}
