package com.ssafy.enjoytrip.service;

import com.ssafy.enjoytrip.common.exception.AttractionException;
import com.ssafy.enjoytrip.domain.Gugun;
import com.ssafy.enjoytrip.domain.Sido;
import com.ssafy.enjoytrip.dto.attraction.GetGugunResponse;
import com.ssafy.enjoytrip.dto.attraction.GetSidoResponse;
import com.ssafy.enjoytrip.repository.GugunRepository;
import com.ssafy.enjoytrip.repository.SidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.ssafy.enjoytrip.common.response.ExceptionStatus.*;

@Service
@RequiredArgsConstructor
public class AttractionService {

    private final SidoRepository sidoRepository;
    private final GugunRepository gugunRepository;

    public List<GetSidoResponse> getSidoList() {
        List<Sido> sidos = sidoRepository.findAll();

        List<GetSidoResponse> result = new ArrayList<>();
        for (Sido sido: sidos) {
            result.add(new GetSidoResponse(sido));
        }

        return result;
    }

    public List<GetGugunResponse> getGugunList(int sidoCode) {
        // 시도 코드 유효성 검사
        findSidoByCode(sidoCode);

        List<Gugun> guguns = gugunRepository.findAllBySidoCode(sidoCode);

        List<GetGugunResponse> result = new ArrayList<>();
        for (Gugun gugun: guguns) {
            result.add(new GetGugunResponse(gugun));
        }

        return result;
    }

    public Sido findSidoByCode(int code) {
        return sidoRepository.findByCode(code)
                .orElseThrow(() -> new AttractionException(SIDO_NOT_FOUND));
    }
}
