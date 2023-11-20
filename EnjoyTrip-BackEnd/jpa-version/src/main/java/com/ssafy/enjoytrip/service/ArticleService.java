package com.ssafy.enjoytrip.service;

import com.ssafy.enjoytrip.Util.JWTUtil;
import com.ssafy.enjoytrip.common.exception.BoardException;
import com.ssafy.enjoytrip.common.exception.MemberException;
import com.ssafy.enjoytrip.common.response.ExceptionStatus;
import com.ssafy.enjoytrip.domain.Article;
import com.ssafy.enjoytrip.domain.Comment;
import com.ssafy.enjoytrip.domain.Member;
import com.ssafy.enjoytrip.dto.board.ArticleDto;
import com.ssafy.enjoytrip.repository.ArticleRepository;
import com.ssafy.enjoytrip.repository.CommentRepository;
import com.ssafy.enjoytrip.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;
    private final CommentRepository commentRepository;
    private final JWTUtil jwtUtil;


    // *** 게시글 ***
    @Transactional
    public void post(String memberId, String title, String content) {
        Member member=getMemberOrException(memberId);
        articleRepository.save(new Article(title,content,member));
    }

    public Page<ArticleDto> listArticle(Pageable pageable) {
        return articleRepository.findAll(pageable).map(ArticleDto::fromEntity);
    }

    public ArticleDto getArticle(int id) {
        return ArticleDto.fromEntity(getArticleOrException(id));
    }

    // *** 댓글 ***
    @Transactional
    public void comment(Integer articleId, String token, String comment) {
        Article article = getArticleOrException(articleId);
        Member member = getMemberOrException(getMemberIdByToken(token));
        commentRepository.save(new Comment(comment,member,article));
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
