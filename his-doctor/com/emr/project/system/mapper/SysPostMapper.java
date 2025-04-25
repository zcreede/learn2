package com.emr.project.system.mapper;

import com.emr.project.system.domain.SysPost;
import java.util.List;

public interface SysPostMapper {
   List selectPostList(SysPost post);

   List selectPostAll();

   SysPost selectPostById(Long postId);

   List selectPostListByUserId(Long userId);

   List selectPostsByUserName(String userName);

   int deletePostById(Long postId);

   int deletePostByIds(Long[] postIds);

   int updatePost(SysPost post);

   int insertPost(SysPost post);

   SysPost checkPostNameUnique(String postName);

   SysPost checkPostCodeUnique(String postCode);
}
