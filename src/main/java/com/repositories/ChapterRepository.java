package com.repositories;

import com.models.Chapters;
import com.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface ChapterRepository extends JpaRepository<Chapters, Integer> {

    List<Chapters> findByPost(Post post);
    Chapters findById(int id);
    Chapters findByPostAndNumber(Post post, int numb);
}
