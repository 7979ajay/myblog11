package com.myblog.myblog11.controller;


import com.myblog.myblog11.Payload.PostDto;
import com.myblog.myblog11.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/posts")
public class PostController {

        private PostService postService;

        public PostController(PostService postService) {
                this.postService = postService;
        }


        @PostMapping
        public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
               PostDto dto = postService.createPost(postDto)  ;

               return new ResponseEntity<>(dto,HttpStatus.CREATED);

        }
           //http://localhost:8080/api/posts/particular?Id=1

    @GetMapping("/particular")
        public  ResponseEntity<PostDto> getPostById(@RequestParam long Id){
           PostDto dto =  postService.getPostById(Id);
           return new ResponseEntity<>(dto,HttpStatus.OK);
        }


           //http://localhost:8080/api/posts
//        @GetMapping()
//       public   List<PostDto>getAllPosts(){
//            List<PostDto> postDtos =postService.getAllPosts();
//            return postDtos;
//        }


    //http://localhost:8080/api/posts?pageNo=0&pageSize=3&sortBy=title&sortDir=des
         @GetMapping()
       public List<PostDto>getAllPosts(

                 @RequestParam(name="pageNo",required = false,defaultValue = "0") int pageNo,
                 @RequestParam(name="pageSize",required = false,defaultValue = "3") int pageSize,
                 @RequestParam(name="sortBy",required = false,defaultValue = "id") String sortBy,
                 @RequestParam(name="sortDir",required = false,defaultValue ="id") String sortDir

         ){

                   List<PostDto> postDtos = postService.getAllPosts(pageNo,pageSize,sortBy,sortDir);
                   return postDtos;
             }


}
