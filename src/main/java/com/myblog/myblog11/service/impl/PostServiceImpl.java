package com.myblog.myblog11.service.impl;


import com.myblog.myblog11.Entity.Post;
import com.myblog.myblog11.Exception.ResourceNotFoundException;
import com.myblog.myblog11.Payload.PostDto;
import com.myblog.myblog11.Repository.PostRepository;
import com.myblog.myblog11.service.PostService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


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

    @Override
    public PostDto getPostById(Long Id) {
       Post post = postRepository.findById(Id).orElseThrow(
                ()->new ResourceNotFoundException("post not found with id: " +Id)
        );

           PostDto dto = new PostDto();
           dto.setId(post.getId());
           dto.setTitle(post.getTitle());
           dto.setContent(post.getContent());
           dto.setDescription(post.getDescription());

        return dto;
    }



//    @Override
//    public List<PostDto> getAllPosts() {
//
//       List<Post> post =postRepository.findAll();
//            List<PostDto> dtos = post.stream().map(Post->mapToDto(Post)).collect(Collectors.toList());
//            return  dtos;
//        }
//
//               PostDto  mapToDto(Post post){
//                  PostDto dto = new PostDto();
//                  dto.setId(post.getId());
//                  dto.setTitle(post.getTitle());
//                  dto.setDescription(post.getDescription());
//                  dto.setContent(post.getContent());
//                  return dto;
//               }
//
//                Post mapToEntity(PostDto postDto){
//                      Post post = new Post();
//                      post.setTitle(postDto.getTitle());
//                      post.setContent(postDto.getContent());
//                      post.setDescription(postDto.getDescription());
//                      return post;
//                }

    @Override
    public List<PostDto> getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort =(sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
       Pageable pageable = PageRequest.of(pageNo,pageSize,sort);
        Page<Post> pagePost = postRepository.findAll(pageable);
        List<Post> posts = pagePost.getContent();

          List<PostDto> dtos =  posts.stream().map(p->mapToDto(p)).collect(Collectors.toList());
            return dtos;
    }
         PostDto  mapToDto(Post post){
           PostDto dto = new PostDto();
           dto.setId(post.getId());
           dto.setTitle(post.getTitle());
           dto.setDescription(post.getDescription());
           dto.setContent(post.getContent());
           return dto;
         }
           Post  mapToEntity(PostDto postDto){
                Post post = new Post();
                post.setTitle(postDto.getTitle());
                post.setDescription(postDto.getDescription());
                post.setContent(postDto.getContent());
                return post;
            }


    }

