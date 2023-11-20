package com.ssafy.enjoytrip.dto.board;

import com.ssafy.enjoytrip.domain.Article;
import com.ssafy.enjoytrip.dto.member.MemberDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ArticleDto {

    private int id;
    private String title;
    private String content;
    private MemberDto member;
    private int hit;
    private String createdAt;
    private String updatedAt;

    public static ArticleDto fromEntity (Article article) {
        return new ArticleDto(
                article.getId(),
                article.getTitle(),
                article.getContent(),
                MemberDto.fromEntity(article.getMember()),
                article.getHit(),
                article.getCreatedAt(),
                article.getUpdatedAt()
        );
    }
}
