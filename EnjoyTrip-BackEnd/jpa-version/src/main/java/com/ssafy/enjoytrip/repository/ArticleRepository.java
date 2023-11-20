package com.ssafy.enjoytrip.repository;

import com.ssafy.enjoytrip.domain.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {

    Page<Article> findAll(Pageable pageable);

    @Override
    Optional<Article> findById(Integer integer);

}
