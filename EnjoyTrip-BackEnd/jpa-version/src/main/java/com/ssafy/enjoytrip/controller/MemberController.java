package com.ssafy.enjoytrip.controller;

import com.ssafy.enjoytrip.common.response.BaseResponse;
import com.ssafy.enjoytrip.dto.member.JoinRequest;
import com.ssafy.enjoytrip.domain.Member;
import com.ssafy.enjoytrip.dto.member.LoginRequest;
import com.ssafy.enjoytrip.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService service;

    @PostMapping("")
    public BaseResponse<?> join(@RequestBody JoinRequest request) {
        service.join(request);
        return new BaseResponse<>(null);
    }

    @PostMapping("/login")
    public BaseResponse<String> login(@RequestBody LoginRequest request) {
        return new BaseResponse<>(service.login(request.getMemberId(),request.getPassword()));
    }

    @GetMapping("/info/{memberId}")
    public BaseResponse<Optional<Member>> getUserInfo(@PathVariable String memberId) {
//        return new BaseResponse<>(service.getUerInfo(memberId));
        return null;
    }
}
