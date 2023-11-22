package com.ssafy.enjoytrip.controller;

import com.ssafy.enjoytrip.common.response.BaseResponse;
import com.ssafy.enjoytrip.dto.bookmark.BookmarkResponse;
import com.ssafy.enjoytrip.dto.bookmark.GetBookmarkResponse;
import com.ssafy.enjoytrip.dto.bookmark.PostBookmarkRequest;
import com.ssafy.enjoytrip.service.BookmarkService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bookmarks")
public class BookmarkController {

    private final BookmarkService service;

    /**
     * 즐겨찾기 존재 여부에 따라
     * O -> 삭제
     * X -> 삽입
     */
    @PostMapping("")
    public BaseResponse<BookmarkResponse> bookmark(
            @RequestBody PostBookmarkRequest request,
            HttpServletRequest httpServletRequest) {
        return new BaseResponse<>(service.bookmark(httpServletRequest, request));
    }

    @GetMapping("")
    public BaseResponse<GetBookmarkResponse> getBookmark(HttpServletRequest httpServletRequest) {
        return new BaseResponse<>(service.getBookmark(httpServletRequest));
    }

}
