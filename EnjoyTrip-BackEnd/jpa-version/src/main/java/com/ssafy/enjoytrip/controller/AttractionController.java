package com.ssafy.enjoytrip.controller;

import com.ssafy.enjoytrip.common.response.BaseResponse;
import com.ssafy.enjoytrip.dto.attraction.GetTypeResponse;
import com.ssafy.enjoytrip.dto.attraction.GetGugunResponse;
import com.ssafy.enjoytrip.dto.attraction.GetSidoResponse;
import com.ssafy.enjoytrip.service.AttractionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

}
