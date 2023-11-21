package com.ssafy.enjoytrip.service;

import com.ssafy.enjoytrip.util.JwtUtil;
import com.ssafy.enjoytrip.common.exception.BoardException;
import com.ssafy.enjoytrip.common.exception.MemberException;
import com.ssafy.enjoytrip.common.response.ExceptionStatus;
import com.ssafy.enjoytrip.domain.Article;
import com.ssafy.enjoytrip.domain.Comment;
import com.ssafy.enjoytrip.domain.Member;
import com.ssafy.enjoytrip.dto.board.ArticleDto;
import com.ssafy.enjoytrip.dto.board.CommentDto;
import com.ssafy.enjoytrip.repository.ArticleRepository;
import com.ssafy.enjoytrip.repository.CommentRepository;
import com.ssafy.enjoytrip.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;
    private final CommentRepository commentRepository;
    private final JwtUtil jwtUtil;


    // *** 게시글 ***
    @Transactional
    public void post(String memberId, String title, String content) {
        Member member=getMemberOrException(memberId);
        articleRepository.save(new Article(title,content,member));
    }

    public List<ArticleDto> listArticle() {
        return articleRepository.findAll()
                .stream().map(ArticleDto::fromEntity)
                .collect(Collectors.toList());
    }

    public ArticleDto getArticle(int id) {
        return ArticleDto.fromEntity(getArticleOrException(id));
    }

    @Transactional
    public void modifyArticle(Integer articleId, String title, String content) {
        Article article = getArticleOrException(articleId);
        article.setTitle(title);
        article.setContent(content);
        articleRepository.saveAndFlush(article);
    }

    @Transactional
    public void deleteArticle(Integer articleId) {
        Article article = getArticleOrException(articleId);
        commentRepository.deleteAllByArticle(article);
        articleRepository.delete(article);
    }

    // *** 댓글 ***
    @Transactional
    public void comment(Integer articleId, String token, String comment) {
        Article article = getArticleOrException(articleId);
        Member member = getMemberOrException(getMemberIdByToken(token));
        commentRepository.save(new Comment(comment,member,article));
    }

    public List<CommentDto> getComment(Integer articleId) {
        Article article = getArticleOrException(articleId);
        List<Comment> findComments=commentRepository.findAllByArticle(article);
        return findComments.stream().map(CommentDto::fromEntity).collect(Collectors.toList());
    }


    // *** 메소드 ***
    private Member getMemberOrException(String memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(()-> new MemberException(ExceptionStatus.MEMBER_NOT_FOUND));
    }

    private Article getArticleOrException(int id) {
        return articleRepository.findById(id)
                .orElseThrow(()->new BoardException(ExceptionStatus.ARTICLE_NOT_FOUND));
    }

    private String getMemberIdByToken(String token) {
        if(!jwtUtil.checkToken(token)) throw new MemberException(ExceptionStatus.INVALID_TOKEN);
        String memberId=jwtUtil.getUserId(token);
        if(memberId==null) throw new MemberException(ExceptionStatus.INVALID_TOKEN);
        return memberId;
    }
}
