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

    @PostMapping("")
    public BaseResponse<BookmarkResponse> postBookmark(
            @RequestBody PostBookmarkRequest request,
            HttpServletRequest httpServletRequest) {
        return new BaseResponse<>(service.postBookmark(httpServletRequest, request));
    }

    @GetMapping("")
    public BaseResponse<GetBookmarkResponse> getBookmark(HttpServletRequest httpServletRequest) {
        return new BaseResponse<>(service.getBookmark(httpServletRequest));
    }

    @DeleteMapping("/{bookmark-id}")
    public BaseResponse<BookmarkResponse> deleteBookmark(
            HttpServletRequest httpServletRequest,
            @PathVariable(value = "bookmark-id") int bookmarkId) {
        return new BaseResponse<>(service.deleteBookmark(httpServletRequest, bookmarkId));
    }

}
