package com.ssafy.enjoytrip.service;

import com.ssafy.enjoytrip.common.exception.AttractionException;
import com.ssafy.enjoytrip.domain.*;
import com.ssafy.enjoytrip.dto.attraction.*;
import com.ssafy.enjoytrip.repository.attraction.*;
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
    private final TypeRepository typeRepository;
    private final InfoRepository infoRepository;
    private final DescriptionRepository descriptionRepository;

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

    public List<GetTypeResponse> getTypeList() {
        List<AttractionType> types = typeRepository.findAll();

        List<GetTypeResponse> result = new ArrayList<>();
        for (AttractionType type: types) {
            result.add(new GetTypeResponse(type));
        }

        return result;
    }

    public List<GetInfoResponse> getInfoList(Integer sidoCode, Integer gugunCode, Integer attractionTypeId) {
        // 시도코드 유효성 검사
        if (sidoCode != null) {
            findSidoByCode(sidoCode);
        }

        // 구군코드 유효성 검사
        if (gugunCode != null) {
            if (sidoCode == null) {
                throw new AttractionException(INVALID_ATTRACTION_VALUE);
            }
            findGugunById(sidoCode, gugunCode);
        }

        // 관광지 유형 유효성 검사
        if (attractionTypeId != null) {
            findTypeById(attractionTypeId);
        }

        List<AttractionInfo> infos;
        // 전체
        if (sidoCode == null && gugunCode == null && attractionTypeId == null) {
            infos = infoRepository.findAll();
        }
        // 시도
        else if (gugunCode == null && attractionTypeId == null) {
            infos = infoRepository.findAllBySido(sidoCode);
        }
        // 관광지 유형
        else if (sidoCode == null && gugunCode == null) {
            infos = infoRepository.findAllByType(attractionTypeId);
        }
        // 시도 + 구군
        else if (attractionTypeId == null) {
            infos = infoRepository.findAllBySidoAndGugun(sidoCode, gugunCode);
        }
        // 시도 + 관광지 유형
        else if (gugunCode == null) {
            infos = infoRepository.findAllBySidoAndType(sidoCode, attractionTypeId);
        }
        // 시도 + 구군 + 관광지 유형
        else {
            infos = infoRepository.findAllBySidoAndGugunAndType(sidoCode, gugunCode, attractionTypeId);
        }

        List<GetInfoResponse> result = new ArrayList<>();
        for (AttractionInfo info: infos) {
            result.add(new GetInfoResponse(info));
        }
        return result;
    }

    public GetDescriptionResponse findDescriptionById(int attractionId) {
        AttractionDescription description = descriptionRepository.findById(attractionId)
                .orElseThrow(() -> new AttractionException(ATTRACTION_NOT_FOUND));
        return new GetDescriptionResponse(description);
    }

    private Sido findSidoByCode(int sidoCode) {
        return sidoRepository.findByCode(sidoCode)
                .orElseThrow(() -> new AttractionException(SIDO_NOT_FOUND));
    }

    private Gugun findGugunById(int sidoCode, int gugunCode) {
        return gugunRepository.findByGugunId(new GugunId(sidoCode, gugunCode))
                .orElseThrow(() -> new AttractionException(GUGUN_NOT_FOUND));
    }

    private AttractionType findTypeById(int id) {
        return typeRepository.findById(id)
                .orElseThrow(() -> new AttractionException(ATTRACTION_TYPE_NOT_FOUND));
    }
}
