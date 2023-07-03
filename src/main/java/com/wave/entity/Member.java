package com.wave.entity;

import com.wave.BaseTimeEntity;
import com.wave.dto.MemberFormDto;
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

    @Column(unique = true)
    private String email; //이메일

    private String userId; //사용자 id

    private String password; //비밀번호

    @Enumerated(EnumType.STRING)
    private Role role; //역할 구분(일반회원, 관리자)


    @Builder
    public Member(Long id, String name, String email, String userId, String password, Role role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.userId = userId;
        this.password = password;
        this.role = role;
    }



    public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder) {
        Member member = Member.builder()
                .name(memberFormDto.getName())
                .email(memberFormDto.getEmail())
                .password(passwordEncoder.encode(memberFormDto.getPassword()))  //암호화처리
                .role(Role.USER)
                .build();
        return member;
    }
}
