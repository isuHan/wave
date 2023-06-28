package com.wave.service;

import com.wave.domain.diary.Diary;
import com.wave.dto.DiaryRequestDTO;
import com.wave.dto.DiaryResponseDTO;
import com.wave.repository.DiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class DiaryService {
    private final DiaryRepository diaryRepository;

    //다이어리 목록
    public List<DiaryResponseDTO> getAllDiaries() {
        List<Diary> diaryList = diaryRepository.findAll();
        return diaryList.stream().map(DiaryResponseDTO::new).collect(Collectors.toList());
    }

    //다이어리 저장
    public Long saveDiary(final DiaryRequestDTO params) {
        Diary entity = diaryRepository.save(params.toEntity());

        return entity.getId();
    }

    //다이어리 조회
    public DiaryResponseDTO getDiaryById(Long id) {
        Diary entity = diaryRepository.findById(id).orElseThrow(IllegalArgumentException::new);

        return new DiaryResponseDTO(entity);
    }

    //다이어리 수정
    public DiaryResponseDTO updateDiary(Long id, DiaryRequestDTO params) {
        Diary entity = diaryRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        entity.update(params.getUserId(), params.getContent());

        return new DiaryResponseDTO(entity);
    }

    //다이어리 삭제
    public Long deleteDiary(final Long id) {
        Diary entity = diaryRepository.findById(id).orElseThrow();
        entity.delete();

        return id;
    }
}
