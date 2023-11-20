package com.ssafy.enjoytrip.service;

import com.ssafy.enjoytrip.domain.Sido;
import com.ssafy.enjoytrip.dto.attraction.GetSidoResponse;
import com.ssafy.enjoytrip.repository.SidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttractionService {

    private final SidoRepository sidoRepository;

    public List<GetSidoResponse> getSidoList() {
        List<Sido> sidos = sidoRepository.findAll();

        List<GetSidoResponse> result = new ArrayList<>();
        for (Sido sido: sidos) {
            result.add(new GetSidoResponse(sido));
        }

        return result;
    }
}
