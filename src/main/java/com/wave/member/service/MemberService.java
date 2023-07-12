package com.wave.member.service;

import com.wave.member.entity.Member;
import com.wave.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
//UserDetailsService : 데이터베이스 회원 정보를 가져오는 인터페이스, 스프링 시큐리티에서 UserDetailsService 를 구현하는 클래스를 통해 로그인 기능을 구현한다.
public class MemberService implements UserDetailsService {

   private final MemberRepository memberRepository;

   //회원 저장 메서드
    public Member saveMember(Member member) {
        validateDuplicateUser(member);
        return memberRepository.save(member);
    }

    //이미 가입된 회원인 경우 예외를 발생시키는 메서드
    private void validateDuplicateUser(Member member) {
        Member findMember = memberRepository.findByEmail(member.getEmail());

        if(findMember != null) {
            throw new IllegalStateException("이미 가입된 회원입니다");
        }
    }


    //loadUserByUsername() : UserDetailsService 의 메소드, 회원정보를 조회하여 사용자의 정보와 권한을 갖는 UserDetail 인터페이스 반환
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email);

        if(member == null) {
            throw new UsernameNotFoundException(email);
        }

        return User.builder()
                .username(member.getEmail())
                .password(member.getPassword())
                .build();
    }
}
