package com.emr.project.system.service.impl;

import com.emr.common.exception.CustomException;
import com.emr.common.utils.StringUtils;
import com.emr.project.system.domain.SysPost;
import com.emr.project.system.mapper.SysPostMapper;
import com.emr.project.system.mapper.SysUserPostMapper;
import com.emr.project.system.service.ISysPostService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysPostServiceImpl implements ISysPostService {
   @Autowired
   private SysPostMapper postMapper;
   @Autowired
   private SysUserPostMapper userPostMapper;

   public List selectPostList(SysPost post) {
      return this.postMapper.selectPostList(post);
   }

   public List selectPostAll() {
      return this.postMapper.selectPostAll();
   }

   public SysPost selectPostById(Long postId) {
      return this.postMapper.selectPostById(postId);
   }

   public List selectPostListByUserId(Long userId) {
      return this.postMapper.selectPostListByUserId(userId);
   }

   public String checkPostNameUnique(SysPost post) {
      Long postId = StringUtils.isNull(post.getPostId()) ? -1L : post.getPostId();
      SysPost info = this.postMapper.checkPostNameUnique(post.getPostName());
      return StringUtils.isNotNull(info) && info.getPostId() != postId ? "1" : "0";
   }

   public String checkPostCodeUnique(SysPost post) {
      Long postId = StringUtils.isNull(post.getPostId()) ? -1L : post.getPostId();
      SysPost info = this.postMapper.checkPostCodeUnique(post.getPostCode());
      return StringUtils.isNotNull(info) && info.getPostId() != postId ? "1" : "0";
   }

   public int countUserPostById(Long postId) {
      return this.userPostMapper.countUserPostById(postId);
   }

   public int deletePostById(Long postId) {
      return this.postMapper.deletePostById(postId);
   }

   public int deletePostByIds(Long[] postIds) {
      for(Long postId : postIds) {
         SysPost post = this.selectPostById(postId);
         if (this.countUserPostById(postId) > 0) {
            throw new CustomException(String.format("%1$s已分配,不能删除", post.getPostName()));
         }
      }

      return this.postMapper.deletePostByIds(postIds);
   }

   public int insertPost(SysPost post) {
      return this.postMapper.insertPost(post);
   }

   public int updatePost(SysPost post) {
      return this.postMapper.updatePost(post);
   }

   public int deleteUserPostByUserId(Long userId) throws Exception {
      return this.userPostMapper.deleteUserPostByUserId(userId);
   }
}
