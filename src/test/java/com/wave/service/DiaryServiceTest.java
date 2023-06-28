package com.wave.service;

import com.wave.domain.diary.Diary;
import com.wave.repository.DiaryRepository;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class DiaryServiceTest {

    @Autowired
    DiaryRepository diaryRepository;


    @Test
    @DisplayName("게시글 저장")
    public void save() {
        Diary params = Diary.builder()
                .userId(1)
                .content("오늘의 일기")
                .build();

        Diary savedDiary = diaryRepository.save(params);
        assertNotNull(savedDiary);
        assertNotNull(savedDiary.getId());
    }

    @Test
    @DisplayName("전체 게시글 리스트 조회")
    public void findAllDiaries() {
        Diary diary1 = Diary.builder()
                .userId(1)
                .content("날씨가 흐리다")
                .build();
        diaryRepository.save(diary1);

        Diary diary2 = Diary.builder()
                .userId(2)
                .content("떡볶이를 먹었다")
                .build();
        diaryRepository.save(diary2);


        List<Diary> diaryList = diaryRepository.findAll();
        assertNotNull(diaryList);
        assertTrue(diaryList.size() > 0);
    }

    @Test
    @DisplayName("게시글 상세")
    public void detail() {
        Diary diary = Diary.builder()
                .userId(1)
                .content("운동을 하러 갔다")
                .build();

        diaryRepository.save(diary);
        Long id = diary.getId();

        Optional<Diary> optionalDiary = diaryRepository.findById(10L);
        assertTrue(optionalDiary.isPresent());

        Diary retrievedDiary = optionalDiary.get();
    }

    @Test
    @DisplayName("게시글 수정")
    public void update() {
        Diary diary = Diary.builder()
                .userId(1)
                .content("운동을 하러 갔다")
                .build();

        Optional<Diary> optionalDiary = diaryRepository.findById(15L);
        assertTrue(optionalDiary.isPresent());

        Diary diary2 = optionalDiary.get();
        diary2.update(2, "이번 달은 6월");

        Diary updatedDiary = diaryRepository.save(diary2);
        assertNotNull(updatedDiary);
    }

    @Test
    @DisplayName("게시글 삭제")
    public void delete() {
        Optional<Diary> optionalDiary = diaryRepository.findById(7L);
        assertTrue(optionalDiary.isPresent());

        Diary diary = optionalDiary.get();
        diaryRepository.delete(diary);
    }

}
