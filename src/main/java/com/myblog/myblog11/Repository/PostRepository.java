package com.myblog.myblog11.Repository;

import com.myblog.myblog11.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
