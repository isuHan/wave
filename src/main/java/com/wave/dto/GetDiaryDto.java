package com.wave.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class GetDiaryDto {

    private Long id;
    private String date;
    private String title;
    private String content;
    private String create_at;
    private String modify_at;

    @Builder
    public GetDiaryDto(Long id, String date, String title, String content, String create_at, String modify_at) {
        this.id = id;
        this.date = date;
        this.title = title;
        this.content = content;
        this.create_at = create_at;
        this.modify_at = modify_at;
    }
}
