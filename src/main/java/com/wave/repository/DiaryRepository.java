package com.wave.repository;

import com.wave.domain.diary.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Long> {
    //일기 조회
    List<Diary> findAllByNickname(String nickname);

    //일기 작성
    Diary save(Diary diary);

    //일기 수정
    Diary update(Diary diary);

    //일기 삭제
    void deleteById(Long id);

}
