package com.ssafy.enjoytrip.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {

    @Id
    @Column(name = "member_id")
    private String id;

    @Column
    private String password;

    @Column
    private String name;

    @Column(name = "email_id")
    private String emailId;

    @Column(name = "email_domain")
    private String emailDomain;

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Article> articles = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Bookmark> bookmarks = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Hotplace> hotplaces = new ArrayList<>();

    @Builder
    public Member(String id, String password, String name, String emailId, String emailDomain) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.emailId = emailId;
        this.emailDomain = emailDomain;
    }

}
