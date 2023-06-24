package com.wave.domain.diary;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Getter
public class Diary {

    @Id
    @GeneratedValue
    @Column(name = "diary_id")
    private Long id;

    private String writer;
    private Date date;
    private String content;
    private Date create_at;
    private Date modify_at;

    @Builder
    public Diary(long id, String writer, Date date, String title, String content, Date create_at, Date modify_at) {
        this.id = id;
        this.writer = writer;
        this.date = date;
        this.content = content;
        this.create_at = create_at;
        this.modify_at = modify_at;
    }
}
