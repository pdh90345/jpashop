package com.jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

// Entitiy : 이 클래스가 JPA 엔터티임을 나타낸다
// JPA 엔터티는 데이터베이스 테이블에 매핑된다
@Entity
@Getter
@Setter
public class Member {

    @Id // id 필드가 엔터티에 기본 키(PK)
    @GeneratedValue // id 필드의 값을 자동으로 생성
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded
    private Address address;

    // 일대다 관계를 정의하는데 사용
    // mappedBy가 있으면 주인이 아니다
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();
}
