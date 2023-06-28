package com.wave.dto;

import com.wave.domain.diary.Diary;
import com.wave.domain.diary.Trap;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access =  AccessLevel.PROTECTED)
@AllArgsConstructor
public class DiaryRequestDTO {

    private int userId;  //사용자 id
    private String content; //일기 내용

    // DTO -> Entity
    public Diary toEntity() {
        return Diary.builder()
                .userId(userId)
                .content(content)
                .build();
    }

}
