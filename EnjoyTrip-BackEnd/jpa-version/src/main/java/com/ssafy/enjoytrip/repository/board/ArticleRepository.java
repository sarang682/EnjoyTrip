package com.ssafy.enjoytrip.repository.board;

import com.ssafy.enjoytrip.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {

    List<Article> findAll();

    @Override
    Optional<Article> findById(Integer integer);

}
