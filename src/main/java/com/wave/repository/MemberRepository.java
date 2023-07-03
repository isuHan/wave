package com.wave.repository;

import com.wave.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    //회원 가입 시 중복된 회원이 있는지 검사하기 위해 이메일로 회원을 검사할 수 있는 쿼리 메소드
    Member findByEmail(String email);
}
