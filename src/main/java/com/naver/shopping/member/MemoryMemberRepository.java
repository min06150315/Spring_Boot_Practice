package com.naver.shopping.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository {
    private static Map<Long, Member> store = new HashMap<>(); // store 탄생
    @Override
    public void save(Member member) {
        store.put(member.getId(), member); // 저장 구현
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId); // 값 가져오기 구현
    }
}
