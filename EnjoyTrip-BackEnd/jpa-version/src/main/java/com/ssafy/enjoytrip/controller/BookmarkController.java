package com.ssafy.enjoytrip.controller;

import com.ssafy.enjoytrip.common.response.BaseResponse;
import com.ssafy.enjoytrip.dto.bookmark.GetBookmarkResponse;
import com.ssafy.enjoytrip.dto.bookmark.PostBookmarkRequest;
import com.ssafy.enjoytrip.dto.bookmark.PostBookmarkResponse;
import com.ssafy.enjoytrip.service.BookmarkService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bookmarks")
public class BookmarkController {

    private final BookmarkService service;

    @PostMapping("")
    public BaseResponse<PostBookmarkResponse> postBookmark(
            @RequestBody PostBookmarkRequest request,
            HttpServletRequest httpServletRequest) {
        return new BaseResponse<>(service.postBookmark(httpServletRequest, request));
    }

    @GetMapping("")
    public BaseResponse<GetBookmarkResponse> getBookmark(HttpServletRequest httpServletRequest) {
        return new BaseResponse<>(service.getBookmark(httpServletRequest));
    }

}
