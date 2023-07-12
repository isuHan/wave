package com.wave.member.entity;

import com.wave.BaseTimeEntity;
import com.wave.member.dto.MemberFormDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id; //회원 pk

    private String name; //이름

    private String nickName; //닉네임

    private String password; //비밀번호

    @Column(unique = true)
    private String email; //이메일


    @Builder
    public Member(Long id, String name, String email, String password, String nickName) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.nickName = nickName;
    }



    public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder) {
        Member member = Member.builder()
                .name(memberFormDto.getName())
                .email(memberFormDto.getEmail())
                .password(passwordEncoder.encode(memberFormDto.getPassword()))  //암호화처리
                .build();
        return member;
    }
}
