package com.jpabook.jpashop.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

// JPA에 사용되는 어노테이션
// 이를 통해 해당 클래스의 필드를 다른 엔터티의 필드로 쉽게 재사용 가능
// 값 타입은 변경 불가능하게 설계해야 한다.
@Embeddable
@Getter
public class Address {
    private String city;
    private String street;
    private String zipcode;

    // JPA 스펙상 엔티티나 임베디드 타입은 자바 기본 생성자를 public 또는 protected로 설정해야 한다.
    protected Address() {

    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
