package com.ssafy.enjoytrip.controller;

import com.ssafy.enjoytrip.common.response.BaseResponse;
import com.ssafy.enjoytrip.dto.attraction.*;
import com.ssafy.enjoytrip.service.AttractionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/attractions")
public class AttractionController {

    private final AttractionService service;

    @GetMapping("/sido")
    public BaseResponse<List<GetSidoResponse>> getSidoList() {
        return new BaseResponse<>(service.getSidoList());
    }

    @GetMapping("/gugun")
    public BaseResponse<List<GetGugunResponse>> getGugunList(@RequestParam("sido-code") int sidoCode) {
        return new BaseResponse<>(service.getGugunList(sidoCode));
    }

    @GetMapping("/content-type")
    public BaseResponse<List<GetTypeResponse>> getTypeList() {
        return new BaseResponse<>(service.getTypeList());
    }

    @GetMapping("/info")
    public BaseResponse<List<GetInfoResponse>> getInfoList(
            @RequestParam(value="sido-code", required=false) Integer sidoCode,
            @RequestParam(value="gugun-code", required=false) Integer gugunCode,
            @RequestParam(value="content-type-id", required=false) Integer contentTypeId) {
        return new BaseResponse<>(service.getInfoList(sidoCode, gugunCode, contentTypeId));
    }

    @GetMapping("/description/{content-id}")
    public BaseResponse<GetDescriptionResponse> getDescription(@PathVariable("content-id") int contentId) {
        return new BaseResponse<>(service.findDescriptionById(contentId));
    }

}
