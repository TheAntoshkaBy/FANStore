package com.repositories;

import com.models.Post;
import com.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

    Post findById(int id);
    List<Post> findByAuthor(User author);
    Post findByAuthorAndAndAnnotation(User author,String a);
}
