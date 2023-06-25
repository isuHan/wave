package com.wave.repository;

import com.wave.domain.diary.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Long> {
    //사용자별로 일기 조회
    List<Diary> findAllByWriter(String writer);

    List<Diary> keyword(String writer, String )
}
