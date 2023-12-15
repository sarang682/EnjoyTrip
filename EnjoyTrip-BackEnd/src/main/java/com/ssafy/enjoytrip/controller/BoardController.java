package com.ssafy.enjoytrip.controller;

import com.ssafy.enjoytrip.common.response.BaseResponse;
import com.ssafy.enjoytrip.dto.board.ArticleDto;
import com.ssafy.enjoytrip.dto.board.CommentDto;
import com.ssafy.enjoytrip.dto.board.ModifyArticleRequest;
import com.ssafy.enjoytrip.dto.board.PostRequest;
import com.ssafy.enjoytrip.service.BoardService;
import com.ssafy.enjoytrip.dto.board.*;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = { "*" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE} , maxAge = 6000)
@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService service;

    // ** 게시글 ***
    @PostMapping("articles")
    public BaseResponse<?> post(@RequestBody PostRequest request, Authentication authentication) {
        service.post(request.getTitle(),request.getContent(), authentication.getName());
        return new BaseResponse<>(null);
    }

    @GetMapping("articles")
    public BaseResponse<List<ArticleDto>> listArticle() {
        return new BaseResponse<>(service.listArticle());
    }

    @GetMapping("/articles/{article-no}")
    public BaseResponse<ArticleDto> getArticle(@PathVariable("article-no") int articleId) {
        return new BaseResponse<>(service.getArticle(articleId));
    }

    @PutMapping("/articles")
    public BaseResponse<?> modifyArticle(@RequestBody ModifyArticleRequest request, Authentication authentication) {
        service.modifyArticle(request.getArticleId(), request.getTitle(), request.getContent(), authentication.getName());
        return new BaseResponse<>(null);
    }

    @DeleteMapping("/articles/{article-no}")
    public BaseResponse<?> deleteArticle(@PathVariable("article-no") int articleId, Authentication authentication) {
        service.deleteArticle(articleId, authentication.getName());
        return new BaseResponse<>(null);
    }


    // *** 댓글 ***
    @PostMapping("/{article-no}/comments")
    public BaseResponse<?> comment(@PathVariable("article-no") int articleId, @RequestBody CommentRequest request, Authentication authentication) {
        service.comment(articleId, authentication.getName(), request.getContent());
        return new BaseResponse<>(null);
    }

    @GetMapping("{article-no}/comments")
    public BaseResponse<List<CommentDto>> comment(@PathVariable("article-no") int articleId) {
        return new BaseResponse<>(service.getComment(articleId));
    }

    @DeleteMapping("comments/{comment-id}")
    public BaseResponse<?> deleteComment(@PathVariable("comment-id") int commentId, Authentication authentication) {
        service.deleteComment(commentId, authentication.getName());
        return new BaseResponse<>(null);
    }

}
