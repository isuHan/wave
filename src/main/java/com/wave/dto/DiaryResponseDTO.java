package com.wave.dto;

import com.wave.domain.diary.Diary;
import com.wave.domain.diary.Trap;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class DiaryResponseDTO {
    private Long id;   //pk
    private int userId; //사용자 id
    private String content; //내용
    private LocalDateTime createdAt; //생성일
    private LocalDateTime updatedAt; //수정일
    private LocalDateTime deletedAt; //삭제일

    @Builder
    public DiaryResponseDTO(Diary entity) {
        this.id = entity.getId();
        this.userId = entity.getUserId();
        this.content = entity.getContent();
        this.createdAt = entity.getCreatedAt();
        this.updatedAt = entity.getUpdatedAt();
        this.deletedAt = entity.getDeletedAt();
    }
}
