package com.ssafy.enjoytrip.controller;

import com.ssafy.enjoytrip.common.response.BaseResponse;
import com.ssafy.enjoytrip.domain.Member;
import com.ssafy.enjoytrip.dto.member.JoinRequest;
import com.ssafy.enjoytrip.dto.member.LoginRequest;
import com.ssafy.enjoytrip.dto.member.MemberDto;
import com.ssafy.enjoytrip.dto.member.UpdateRequest;
import com.ssafy.enjoytrip.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = { "*" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.POST, RequestMethod.DELETE} , maxAge = 6000)
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

    @GetMapping("/info")
    public BaseResponse<MemberDto> getMember(Authentication authentication) {
        return new BaseResponse<>(service.getMember(authentication.getName()));
    }

    @GetMapping("/info/{memberId}")
    public BaseResponse<Member> userInfo(@PathVariable String memberId) {
        return new BaseResponse<>(service.userInfo(memberId));
    }

    @PutMapping("/{memberId}")
    public BaseResponse<Integer> modify(@PathVariable String memberId, @RequestBody UpdateRequest request) {
        return new BaseResponse<>(service.modify(memberId, request.getName(), request.getPassword(), request.getEmailId(), request.getEmailDomain()));
    }

    @DeleteMapping("/{memberId}")
    public BaseResponse<Integer> delete(@PathVariable String memberId) {
        service.delete(memberId);
        return new BaseResponse<>(null);
    }
}
