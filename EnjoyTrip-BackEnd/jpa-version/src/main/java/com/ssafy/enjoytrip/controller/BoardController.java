package com.ssafy.enjoytrip.controller;

import com.ssafy.enjoytrip.common.response.BaseResponse;
import com.ssafy.enjoytrip.domain.Article;
import com.ssafy.enjoytrip.dto.board.PostRequest;
import com.ssafy.enjoytrip.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final ArticleService service;

    @PostMapping("articles")
    public BaseResponse<?> post(@RequestBody PostRequest request) {

        service.post(request.getMemberId(),request.getTitle(),request.getContent());
        return new BaseResponse<>(null);
    }
}
