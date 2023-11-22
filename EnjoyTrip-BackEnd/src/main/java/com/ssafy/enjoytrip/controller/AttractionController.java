package com.ssafy.enjoytrip.controller;

import com.ssafy.enjoytrip.common.response.BaseResponse;
import com.ssafy.enjoytrip.dto.attraction.*;
import com.ssafy.enjoytrip.service.AttractionService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = { "*" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE} , maxAge = 6000)
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

    @GetMapping("/attraction-type")
    public BaseResponse<List<GetTypeResponse>> getTypeList() {
        return new BaseResponse<>(service.getTypeList());
    }

    @GetMapping("/info")
    public BaseResponse<List<GetInfoResponse>> getInfoList(
            @RequestParam(value="sido-code", required=false) Integer sidoCode,
            @RequestParam(value="gugun-code", required=false) Integer gugunCode,
            @RequestParam(value="attraction-type-id", required=false) Integer attractionTypeId,
            HttpServletRequest request) {
        return new BaseResponse<>(service.getInfoList(sidoCode, gugunCode, attractionTypeId, request));
    }

    @GetMapping("/description/{attraction-id}")
    public BaseResponse<GetDescriptionResponse> getDescription(
            @PathVariable("attraction-id") int attractionId,
            HttpServletRequest request) {
        return new BaseResponse<>(service.findDescriptionById(request, attractionId));
    }

}
