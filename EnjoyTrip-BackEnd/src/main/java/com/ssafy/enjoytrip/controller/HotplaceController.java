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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = { "*" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE} , maxAge = 6000)
@RestController
@RequiredArgsConstructor
@RequestMapping("/hotplaces")
public class HotplaceController {

    private final HotplaceService service;

    @PostMapping(value="")
    public BaseResponse<HotplaceResponse> postHotplace (
            @RequestPart PostHotplaceRequest postHotplaceRequest,
            @RequestPart(required = false) MultipartFile image,
            HttpServletRequest httpServletRequest) throws IOException {
        return new BaseResponse<>(service.postHotplace(httpServletRequest, postHotplaceRequest, image));
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
