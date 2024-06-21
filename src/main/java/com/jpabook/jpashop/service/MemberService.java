package com.jpabook.jpashop.service;

import com.jpabook.jpashop.domain.Member;
import com.jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// 비즈니스 로직을 담당하는 서비스 계층 클래스에 부여
// 스프링이 인식하고 관리
@Service
// 작업이 하나라도 실패하면 모든 작업을 롤백
@Transactional(readOnly = true) // 읽기 최적화
@RequiredArgsConstructor
public class MemberService {


    private final MemberRepository memberRepository;

    /*
     * 회원 가입
     */
    @Transactional // 이게 덮어씀
    public Long join(Member member) {
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        // EXCEPTION
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    // 회원 전체 조회
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    // 단일 조회
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

}
