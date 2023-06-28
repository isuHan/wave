package com.wave.domain.diary;

import com.wave.domain.member.Member;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class Diary {

    @Id
    @GeneratedValue
    @Column(name = "diary_id")
    private Long id;    //일기 pk

    private int userId; //사용자 id

    @Column(length = 1000)
    private String content; //일기 내용


    private LocalDateTime createdAt = LocalDateTime.now(); //생성일

    private LocalDateTime updatedAt; //수정일

    private LocalDateTime deletedAt; //삭제일


    @Builder
    public Diary(int userId, String content, Trap trap) {
        this.userId = userId;
        this.content = content;
    }

    //일기 수정
    public void update(int userId, String content) {
        this.userId = userId;
        this.content = content;
        this.updatedAt = LocalDateTime.now();
    }

    //일기 삭제
    public void delete() {
        this.deletedAt = LocalDateTime.now();
    }
}
