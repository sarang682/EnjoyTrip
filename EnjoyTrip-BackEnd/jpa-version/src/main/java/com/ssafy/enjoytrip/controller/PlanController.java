package com.ssafy.enjoytrip.controller;

import com.ssafy.enjoytrip.common.response.BaseResponse;
import com.ssafy.enjoytrip.dto.plan.PlanAttractionRequest;
import com.ssafy.enjoytrip.dto.plan.PlanRequest;
import com.ssafy.enjoytrip.service.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/plan")
@RestController
public class PlanController {

    private final PlanService service;

    @PostMapping("")
    public BaseResponse<Integer> plan(@RequestBody PlanRequest request, @RequestHeader("Authorization") String token) {
        return new BaseResponse<>(service.plan(request.getTitle(),token)); // PK 반환
    }

    @PostMapping("/{plan-id}")
    public BaseResponse<Integer> planAttraction(@PathVariable("plan-id") Integer planId, @RequestBody PlanAttractionRequest request) {
        return new BaseResponse<>(service.planAttraction(planId,request.getAttractionId()));
    }

}
