package com.ssafy.enjoytrip.controller;

import com.ssafy.enjoytrip.common.response.BaseResponse;
import com.ssafy.enjoytrip.dto.bookmark.ChangeBookmarkResponse;
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

@CrossOrigin(origins = { "*" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE} , maxAge = 6000)

@RestController
@RequiredArgsConstructor
@RequestMapping("/bookmarks")
public class BookmarkController {

//    private final BookmarkService service;
//
//    /**
//     * 즐겨찾기 존재 여부에 따라
//     * O -> 삭제
//     * X -> 삽입
//     */
//    @PostMapping("")
//    public BaseResponse<ChangeBookmarkResponse> changeBookmark(
//            @RequestBody PostBookmarkRequest request,
//            HttpServletRequest httpServletRequest) {
//        return new BaseResponse<>(service.changeBookmark(httpServletRequest, request));
//    }
//
//    @GetMapping("")
//    public BaseResponse<GetBookmarkResponse> getBookmarkList(HttpServletRequest httpServletRequest) {
//        return new BaseResponse<>(service.getBookmarkList(httpServletRequest));
//    }

}
