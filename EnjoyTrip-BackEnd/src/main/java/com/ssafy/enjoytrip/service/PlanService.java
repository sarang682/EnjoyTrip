package com.ssafy.enjoytrip.service;

import com.ssafy.enjoytrip.common.exception.AttractionException;
import com.ssafy.enjoytrip.common.exception.MemberException;
import com.ssafy.enjoytrip.common.exception.PlanException;
import com.ssafy.enjoytrip.common.response.ExceptionStatus;
import com.ssafy.enjoytrip.config.jwt.JwtUtil;
import com.ssafy.enjoytrip.domain.*;
import com.ssafy.enjoytrip.dto.attraction.GetInfoResponse;
import com.ssafy.enjoytrip.dto.plan.PlanAttractionDto;
import com.ssafy.enjoytrip.dto.plan.PlanDto;
import com.ssafy.enjoytrip.repository.attraction.InfoRepository;
import com.ssafy.enjoytrip.repository.bookmark.BookmarkRepository;
import com.ssafy.enjoytrip.repository.member.MemberRepository;
import com.ssafy.enjoytrip.repository.plan.PlanAttractionRepository;
import com.ssafy.enjoytrip.repository.plan.PlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlanService {

//    private final PlanRepository planRepository;
//    private final MemberRepository memberRepository;
//    private final PlanAttractionRepository planAttractionRepository;
//    private final BookmarkRepository bookmarkRepository;
//    private final InfoRepository infoRepository;
//    private final JwtUtil jwtUtil;
//
//    @Transactional
//    public Integer plan(String title, String token) {
//        String memberId = jwtUtil.getUsernameFromToken(token);
//        Member member=getMemberOrException(memberId);
//        return planRepository.save(new Plan(title,member)).getId();
//    }
//
//    @Transactional
//    public Integer planAttraction(Integer planId, Integer attractionId) {
//        Plan plan=getPlanOrException(planId);
//        AttractionInfo attractionInfo=getAttractionOrException(attractionId);
//        return planAttractionRepository.save(new PlanAttractionInfo(plan,attractionInfo)).getId();
//    }
//
//    public List<PlanDto> getPlan(String token) {
//        String memberId = jwtUtil.getUsernameFromToken(token);
//        Member member=getMemberOrException(memberId);
//        List<Plan> findPlans=planRepository.findAllByMember(member);
//        return findPlans.stream().map(PlanDto::fromEntity).collect(Collectors.toList());
//    }
//
//    public List<PlanAttractionDto> getAttraction(Integer planId) {
//        Plan plan=getPlanOrException(planId);
//        Member member=plan.getMember();
//        List<PlanAttractionInfo> findInfo=planAttractionRepository.findAllByPlan(plan);
//        List<PlanAttractionDto> result=new ArrayList<>();
//        for(PlanAttractionInfo i:findInfo) {
//            if(findBookmark(member,i.getAttractionInfo())==null) { // 관광지를 북마크 안함
//                result.add(new PlanAttractionDto(i,true,false));
//            }else{ // 관광지를 북마크함
//                result.add(new PlanAttractionDto(i,true,true));
//            }
//        }
//        return result;
//    }
//
//
//    // *** 메소드 ***
//    private Member getMemberOrException(String memberId) {
//        return memberRepository.findById(memberId)
//                .orElseThrow(()-> new MemberException(ExceptionStatus.MEMBER_NOT_FOUND));
//    }
//
//    private Plan getPlanOrException(Integer planId) {
//        return planRepository.findById(planId)
//                .orElseThrow(()-> new PlanException(ExceptionStatus.PLAN_NOT_FOUND));
//    }
//
//    private AttractionInfo getAttractionOrException(Integer attractionId) {
//        return infoRepository.findById(attractionId)
//                .orElseThrow(()->new AttractionException(ExceptionStatus.ATTRACTION_NOT_FOUND));
//    }
//
//    private Bookmark findBookmark(Member member, AttractionInfo info) {
//        return bookmarkRepository.findByMemberAndAttractionInfo(member, info);
//    }
}
