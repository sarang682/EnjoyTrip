package com.ssafy.enjoytrip.controller;

import com.ssafy.enjoytrip.common.response.BaseResponse;
import com.ssafy.enjoytrip.dto.attraction.GetInfoResponse;
import com.ssafy.enjoytrip.dto.plan.PlanAttractionDto;
import com.ssafy.enjoytrip.dto.plan.PlanAttractionRequest;
import com.ssafy.enjoytrip.dto.plan.PlanDto;
import com.ssafy.enjoytrip.dto.plan.PlanRequest;
import com.ssafy.enjoytrip.service.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = { "*" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.POST, RequestMethod.DELETE} , maxAge = 6000)
@RequiredArgsConstructor
@RequestMapping("/plan")
@RestController
public class PlanController {

//    private final PlanService service;
//
//    @PostMapping("")
//    public BaseResponse<Integer> plan(@RequestBody PlanRequest request, @RequestHeader("Authorization") String token) {
//        return new BaseResponse<>(service.plan(request.getTitle(),token)); // PK 반환
//    }
//
//    @PostMapping("/{plan-id}")
//    public BaseResponse<Integer> planAttraction(@PathVariable("plan-id") Integer planId, @RequestBody PlanAttractionRequest request) {
//        return new BaseResponse<>(service.planAttraction(planId,request.getAttractionId()));
//    }
//
//    @GetMapping("")
//    public BaseResponse<List<PlanDto>> getPlan(@RequestHeader("Authorization") String token) {
//        return new BaseResponse<>(service.getPlan(token));
//    }
//
//    @GetMapping("/{plan-id}")
//    public BaseResponse<List<PlanAttractionDto>> getAttraction(@PathVariable("plan-id")Integer planId) {
//        return new BaseResponse<>(service.getAttraction(planId));
//    }

}
