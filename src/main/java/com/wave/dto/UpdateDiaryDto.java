package com.wave.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UpdateDiaryDto {

    private Long id;
    private String writer;
    private String date;
    private String content;
    private String modify_at;

    @Builder
    public UpdateDiaryDto(Long id, String writer, String date, String content, String modify_at) {
        this.id = id;
        this.writer = writer;
        this.date = date;
        this.content = content;
        this.modify_at = modify_at;
    }
}
