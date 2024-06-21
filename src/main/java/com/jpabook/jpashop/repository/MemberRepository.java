package com.jpabook.jpashop.repository;

import com.jpabook.jpashop.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

// Repository : 이 클래스가 데이터 접근 계층의 구성요소임을 나타낸다
// Spring이 데이터 접근 관롄 예외를 처리하도록 도와준다
@Repository
// final 필드와 @NonNull로 표시된 필드를 포함하는 생성자를 자동으로 생성
@RequiredArgsConstructor
public class MemberRepository {

    // PersistenceContext : JPA의 EntityManager를 주입 하기 위해 사용된다.
    // EntityManager는 엔터티를 관리하고 데이터 베이스 연산을 수행하는데 사용된다.
//    @PersistenceContext
    private final EntityManager em; // 엔터티매니저 인스턴스 저장

    // Member 엔터티(테이블에 매핑되는 클래스)를 데이터 베이스에 저장
    public void save(Member member) {
        // Member 엔터티를 데이터 베이스에 영속화(persist)
        // 영속화 : 엔터티 객체를 데이터 베이스에 저장하여 지속적으로 관리하는 것(디비에 연결하는 느낌)
        em.persist(member);
    }

    // 단일 조회
    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    // 리스트 조회
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    // 이름으로 회원 조회
    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name).getResultList();
    }
}
