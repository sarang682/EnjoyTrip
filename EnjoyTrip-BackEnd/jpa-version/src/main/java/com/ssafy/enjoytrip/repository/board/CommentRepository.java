package com.ssafy.enjoytrip.repository.board;

import com.ssafy.enjoytrip.domain.Article;
import com.ssafy.enjoytrip.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    List<Comment> findAllByArticle(Article article);


    void deleteAllByArticle(Article article);

}
