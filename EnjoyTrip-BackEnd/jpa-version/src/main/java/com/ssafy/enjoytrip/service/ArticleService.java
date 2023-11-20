package com.ssafy.enjoytrip.service;

import com.ssafy.enjoytrip.common.exception.MemberException;
import com.ssafy.enjoytrip.common.response.ExceptionStatus;
import com.ssafy.enjoytrip.domain.Article;
import com.ssafy.enjoytrip.domain.Member;
import com.ssafy.enjoytrip.repository.ArticleRepository;
import com.ssafy.enjoytrip.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void post(String memberId, String title, String content) {
        Member member=getMemberOrException(memberId);
        articleRepository.save(new Article(title,content,member));
    }

    private Member getMemberOrException(String memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(()-> new MemberException(ExceptionStatus.MEMBER_NOT_FOUND));
    }
}
