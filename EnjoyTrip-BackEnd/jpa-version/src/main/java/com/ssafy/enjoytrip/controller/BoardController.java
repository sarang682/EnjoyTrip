package com.ssafy.enjoytrip.controller;

import com.ssafy.enjoytrip.common.response.BaseResponse;
import com.ssafy.enjoytrip.dto.board.*;
import com.ssafy.enjoytrip.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = { "*" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.POST, RequestMethod.DELETE} , maxAge = 6000)
@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final ArticleService service;

    // ** 게시글 ***
    @PostMapping("articles")
    public BaseResponse<?> post(@RequestBody PostRequest request, @RequestHeader("Authorization") String token) {
        service.post(request.getTitle(),request.getContent(), token);
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
    public BaseResponse<?> modifyArticle(@RequestBody ModifyArticleRequest request, @RequestHeader("Authorization") String token) {
        service.modifyArticle(request.getArticleId(), request.getTitle(), request.getContent(), token);
        return new BaseResponse<>(null);
    }

    @DeleteMapping("/articles/{article-no}")
    public BaseResponse<?> deleteArticle(@PathVariable("article-no") int articleId, @RequestHeader("Authorization") String token) {
        service.deleteArticle(articleId, token);
        return new BaseResponse<>(null);
    }


    // *** 댓글 ***
    @PostMapping("/{article-no}/comments")
    public BaseResponse<?> comment(@PathVariable("article-no") int articleId, @RequestBody CommentRequest request, @RequestHeader("Authorization") String token) {
        service.comment(articleId,token, request.getContent());
        return new BaseResponse<>(null);
    }

    @GetMapping("{article-no}/comments")
    public BaseResponse<List<CommentDto>> comment(@PathVariable("article-no") int articleId) {
        return new BaseResponse<>(service.getComment(articleId));
    }

    @DeleteMapping("comments/{comment-id}")
    public BaseResponse<?> deleteComment(@PathVariable("comment-id") int commentId, @RequestHeader("Authorization") String token) {
        service.deleteComment(commentId, token);
        return new BaseResponse<>(null);
    }

}
