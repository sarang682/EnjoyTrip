package com.ssafy.enjoytrip.service;

import com.ssafy.enjoytrip.util.JwtUtil;
import com.ssafy.enjoytrip.common.exception.AttractionException;
import com.ssafy.enjoytrip.common.exception.DatabaseException;
import com.ssafy.enjoytrip.common.exception.JwtBadRequestException;
import com.ssafy.enjoytrip.common.exception.MemberException;
import com.ssafy.enjoytrip.common.response.ExceptionStatus;
import com.ssafy.enjoytrip.domain.AttractionInfo;
import com.ssafy.enjoytrip.domain.Bookmark;
import com.ssafy.enjoytrip.domain.Member;
import com.ssafy.enjoytrip.dto.bookmark.PostBookmarkRequest;
import com.ssafy.enjoytrip.dto.bookmark.PostBookmarkResponse;
import com.ssafy.enjoytrip.repository.attraction.InfoRepository;
import com.ssafy.enjoytrip.repository.bookmark.BookmarkRepository;
import com.ssafy.enjoytrip.repository.member.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;
    private final MemberRepository memberRepository;
    private final InfoRepository infoRepository;
    private final JwtUtil jwtUtil;

    @Transactional
    public PostBookmarkResponse postBookmark(HttpServletRequest httpServletRequest, PostBookmarkRequest request) {
        // 멤버
        Member member = getMemberByAuth(httpServletRequest);

        // 관광지
        AttractionInfo attractionInfo = findAttractonById(request.getAttractionId());

        // 즐겨찾기 생성
        Bookmark bookmark = request.toEntity(member, attractionInfo);

        // 즐겨찾기 추가
        if (bookmarkRepository.save(bookmark) == null) {
            throw new DatabaseException(ExceptionStatus.DATABASE_ERROR);
        }

        return new PostBookmarkResponse(bookmark);
    }

    private Member getMemberByAuth(HttpServletRequest httpServletRequest) {
        // 토큰
        String token = getToken(httpServletRequest);
        // 멤버 아이디
        String memberId = jwtUtil.getUserId(token);
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(ExceptionStatus.MEMBER_NOT_FOUND));
    }

    private String getToken(HttpServletRequest httpServletRequest) {
        // 토큰 가져오기
        String token = jwtUtil.resolveToken(httpServletRequest);
        // 토큰 유효성 검사
        jwtUtil.validateToken(token);

        return token;
    }

    private Member findMemberById(String memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(ExceptionStatus.MEMBER_NOT_FOUND));
        return member;
    }

    private AttractionInfo findAttractonById(int attractionId) {
        AttractionInfo attractionInfo = infoRepository.findById(attractionId)
                .orElseThrow(() -> new AttractionException(ExceptionStatus.ATTRACTION_NOT_FOUND));
        return attractionInfo;
    }

}
