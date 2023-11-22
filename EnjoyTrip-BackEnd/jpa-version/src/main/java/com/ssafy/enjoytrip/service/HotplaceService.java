package com.ssafy.enjoytrip.service;

import com.ssafy.enjoytrip.common.exception.*;
import com.ssafy.enjoytrip.common.response.ExceptionStatus;
import com.ssafy.enjoytrip.domain.AttractionType;
import com.ssafy.enjoytrip.domain.Hotplace;
import com.ssafy.enjoytrip.domain.Member;
import com.ssafy.enjoytrip.dto.hotplace.GetHotplaceResponse;
import com.ssafy.enjoytrip.dto.hotplace.HotplaceDto;
import com.ssafy.enjoytrip.dto.hotplace.HotplaceResponse;
import com.ssafy.enjoytrip.dto.hotplace.PostHotplaceRequest;
import com.ssafy.enjoytrip.repository.attraction.TypeRepository;
import com.ssafy.enjoytrip.repository.hotplace.HotplaceRepository;
import com.ssafy.enjoytrip.repository.member.MemberRepository;
import com.ssafy.enjoytrip.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HotplaceService {

    private final HotplaceRepository hotplaceRepository;
    private final MemberRepository memberRepository;
    private final TypeRepository attractionTypeRepository;
    private final JwtUtil jwtUtil;

    @Transactional
    public HotplaceResponse postHotplace(String token, PostHotplaceRequest request) {
        // 멤버
        Member member = getMemberByToken(token);

        // 관광지 유형
        AttractionType attractionType = findAttractionTypeById(request.getAttractionTypeId());

        // 추천 여행지 생성
        Hotplace hotplace = request.toEntity(member, attractionType);

        // 즐겨찾기 추가
        if (hotplaceRepository.save(hotplace) == null) {
            throw new DatabaseException(ExceptionStatus.DATABASE_ERROR);
        }

        return new HotplaceResponse(hotplace);
    }

    public List<HotplaceDto> getHotplaceList() {
        // 추천 여행지 가져오기
        List<Hotplace> hotplaces;
        try {
            hotplaces = hotplaceRepository.findAll();
        } catch (Exception e) {
            throw new DatabaseException(ExceptionStatus.DATABASE_ERROR);
        }

        List<HotplaceDto> result = new ArrayList<>();
        for (Hotplace hotplace: hotplaces) {
            result.add(new HotplaceDto(hotplace));
        }

        return result;
    }

    public GetHotplaceResponse getHotplace(int hotplaceId) {
        Hotplace hotplace = findHotplaceById(hotplaceId);
        return new GetHotplaceResponse(hotplace);
    }

    @Transactional
    public HotplaceResponse deleteHotplace(String token, int hotplaceId) {
        // 멤버
        String memberId = getMemberIdByToken(token);
        validateMember(memberId);

        // 추천 여행지
        Hotplace hotplace = findHotplaceById(hotplaceId);

        // 토큰에 들어있는 멤버 정보와 추천 여행지의 작성자가 일치하는지 확인
        if (!memberId.equals(hotplace.getMember().getId())) {
            throw new JwtBadRequestException(ExceptionStatus.TOKEN_MISMATCH);
        }

        // 삭제
        try {
            hotplaceRepository.deleteById(hotplaceId);
        } catch (Exception e) {
            throw new DatabaseException(ExceptionStatus.DATABASE_ERROR);
        }

        return new HotplaceResponse(hotplace);
    }

    private Hotplace findHotplaceById(int hotplaceId) {
        return hotplaceRepository.findById(hotplaceId)
                .orElseThrow(() -> new HotplaceException(ExceptionStatus.HOTPLACE_NOT_FOUND));
    }

    private void validateMember(String memberId) {
        if (!memberRepository.existsById(memberId)) {
            throw new MemberException(ExceptionStatus.MEMBER_NOT_FOUND);
        }
    }

    private Member getMemberByToken(String token) {
        // 멤버 아이디
        String memberId = getMemberIdByToken(token);
        return findMemberById(memberId);
    }

    private String getMemberIdByToken(String token) {
        // 유효성 검사
        jwtUtil.validateToken(token);
        return jwtUtil.getUserId(token);
    }

    private Member findMemberById(String memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(ExceptionStatus.MEMBER_NOT_FOUND));
    }

    private AttractionType findAttractionTypeById(int attractionTypeId) {
        return attractionTypeRepository.findById(attractionTypeId)
                .orElseThrow(() -> new AttractionException(ExceptionStatus.ATTRACTION_TYPE_NOT_FOUND));
    }

}
