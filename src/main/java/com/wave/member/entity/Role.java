package com.wave.member.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 삭제 예정
 */
@Getter
@RequiredArgsConstructor
//멤버가 일반 유저인지, 관리자인지 구분하기 위해 역할 지정
public enum Role {

    USER, ADMIN
}


