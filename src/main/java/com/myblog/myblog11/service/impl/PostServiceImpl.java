package com.myblog.myblog11.service.impl;


import com.myblog.myblog11.Entity.Post;
import com.myblog.myblog11.Payload.PostDto;
import com.myblog.myblog11.Repository.PostRepository;
import com.myblog.myblog11.service.PostService;
import org.springframework.stereotype.Service;


@Service
public class PostServiceImpl implements PostService {

      private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {

            Post post = new Post();
            post.setTitle(postDto.getTitle());
            post.setContent(postDto.getContent());
            post.setDescription(postDto.getDescription());
            Post savePost = postRepository.save(post);


            PostDto dto = new PostDto();
            dto.setTitle(savePost.getTitle());
            dto.setContent(savePost.getContent());
            dto.setDescription(savePost.getDescription());

             return dto;
    }
}
