package com.ssafy.enjoytrip.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Embeddable
@NoArgsConstructor
public class GugunId implements Serializable {

    @Column(name = "sido_code")
    private int sidoCode;

    @Column(name = "gugun_code")
    private int gugunCode;

    public GugunId(int sidoCode, int gugunCode) {
        this.sidoCode = sidoCode;
        this.gugunCode = gugunCode;
    }
}
