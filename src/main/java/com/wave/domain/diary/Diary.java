package com.wave.domain.diary;

import com.wave.domain.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Getter
@NoArgsConstructor
@Entity
public class Diary {

    @Id
    @GeneratedValue
    @Column(name = "diary_id")
    private Long id;    //일기 pk

    @Column(length = 1000)
    private String content; //일기 내용

    @Enumerated(EnumType.STRING)
    private Trap trap; //감정의 덫

    @ManyToOne //다대일 단방향 관계, member 삭제되면 일기도 삭제
    private Member member; //회원 pk

    @Builder
    public Diary(Long id, String content, Trap trap, Member member) {
        this.id = id;
        this.content = content;
        this.trap = trap;
        this.member = member;
    }
}
