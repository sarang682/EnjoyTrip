package com.ssafy.enjoytrip.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private int id;

    @Column
    private String title;

    @Column(length = 2000)
    private String content;

    @Column
    @ColumnDefault("0")
    private int hit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", referencedColumnName = "member_id")
    private Member member;

    @OneToMany(mappedBy = "article")
    private List<Comment> comments = new ArrayList<>();

    @Builder
    public Article(String title, String content, Member member) {
        this.title = title;
        this.content = content;
        this.member = member;
    }
}
