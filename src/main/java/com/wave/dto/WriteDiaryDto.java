package com.wave.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class WriteDiaryDto {

    private String writer;
    private String date;
    private String title;
    private String content;

    @Builder
    public WriteDiaryDto(String writer, String date, String title, String content) {
        this.writer = writer;
        this.date = date;
        this.title = title;
        this.content = content;
    }
}
