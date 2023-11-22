package com.ssafy.enjoytrip.controller;

import com.ssafy.enjoytrip.common.response.BaseResponse;
import com.ssafy.enjoytrip.dto.hotplace.GetHotplaceResponse;
import com.ssafy.enjoytrip.dto.hotplace.HotplaceDto;
import com.ssafy.enjoytrip.dto.hotplace.HotplaceResponse;
import com.ssafy.enjoytrip.dto.hotplace.PostHotplaceRequest;
import com.ssafy.enjoytrip.service.HotplaceService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = { "*" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE} , maxAge = 6000)
@RestController
@RequiredArgsConstructor
@RequestMapping("/hotplaces")
public class HotplaceController {

    private final HotplaceService service;

    @PostMapping("")
    public BaseResponse<HotplaceResponse> postHotplace (
            @RequestBody PostHotplaceRequest request,
            HttpServletRequest httpServletRequest) {
        return new BaseResponse<>(service.postHotplace(httpServletRequest, request));
    }

    @GetMapping("")
    public BaseResponse<List<HotplaceDto>> getHotplaceList() {
        return new BaseResponse<>(service.getHotplaceList());
    }

    @GetMapping("/{hotplace-id}")
    public BaseResponse<GetHotplaceResponse> getHotplace (
            @PathVariable(value = "hotplace-id") int hotplaceId) {
        return new BaseResponse<>(service.getHotplace(hotplaceId));
    }

    @DeleteMapping("/{hotplace-id}")
    public BaseResponse<HotplaceResponse> deleteHotplace (
            @PathVariable(value = "hotplace-id") int hotplaceId,
            HttpServletRequest httpServletRequest) {
        return new BaseResponse<>(service.deleteHotplace(httpServletRequest, hotplaceId));
    }

}
