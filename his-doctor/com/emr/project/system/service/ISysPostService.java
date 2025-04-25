package com.emr.project.system.service;

import com.emr.project.system.domain.SysPost;
import java.util.List;

public interface ISysPostService {
   List selectPostList(SysPost post);

   List selectPostAll();

   SysPost selectPostById(Long postId);

   List selectPostListByUserId(Long userId);

   String checkPostNameUnique(SysPost post);

   String checkPostCodeUnique(SysPost post);

   int countUserPostById(Long postId);

   int deletePostById(Long postId);

   int deletePostByIds(Long[] postIds);

   int insertPost(SysPost post);

   int updatePost(SysPost post);

   int deleteUserPostByUserId(Long userId) throws Exception;
}
