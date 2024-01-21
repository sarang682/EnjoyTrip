package com.ssafy.enjoytrip.controller;

import com.ssafy.enjoytrip.common.response.BaseResponse;
import com.ssafy.enjoytrip.dto.member.JoinRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = { "*" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE} , maxAge = 6000)
@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class HealthController {

    @GetMapping("/health")
    public BaseResponse<?> health() {
        return new BaseResponse<>(null);
    }

}
