package com.wave.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
//모든 Entity의 상위 클래스에서 createdDate, updateDate를 자동으로 관리해주는 역할
public class BaseTimeEntity {
    @CreatedDate
    private LocalDateTime createdDate; //생성시 날짜 자동 생성

    @LastModifiedDate
    private LocalDateTime modifiedDate; //수정시 날짜 자동 갱신
}
