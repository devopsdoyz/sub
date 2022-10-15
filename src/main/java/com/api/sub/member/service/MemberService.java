package com.api.sub.member.service;


import com.api.sub.member.entity.MemberEntity;
import com.api.sub.member.enums.MemberType;
import com.api.sub.member.mapper.MemberMapper;
import com.api.sub.member.repository.MemberRepository;
import com.api.sub.member.request.MemberJoinRequest;
import com.api.sub.member.response.MemberResponse;
import com.api.sub.member.response.MemberTypeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.reflect.Member;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {

   private final MemberRepository memberRepository;

    public MemberResponse findById(String id){

       // Optional.ofNullable(memberRepository.getMemberList(id)).orElseThrow( () -> new IllegalArgumentException(""));
       // Optional.ofNullable(memberRepository.getMemberList(id)).orElseThrow(IllegalArgumentException::new);

        MemberEntity memberEntity = memberRepository.findById(id);

        System.out.println(Optional.ofNullable(memberEntity).orElseGet(MemberEntity::new).getId()); // orElseGe ->memberEntity가 null일수도있는경우
        try{
            Thread.sleep(5000);
        }catch (Exception e){

        }
        //todo MemberMapper.INSTANCE 이지랄로 쓰는 특별한이유가있나
        //그냥 private final MemberMapper memberMapper 선언해서 쓰면되지?
        return MemberMapper.INSTANCE.toResponse(memberEntity);

    }
    public List<MemberResponse> all() {

        List<MemberEntity> memberEntities = memberRepository.findAll();

        //그냥 private final MemberMapper memberMapper 선언해서 쓰면되지?
        return MemberMapper.INSTANCE.toResponses(memberEntities);

    }

    public MemberTypeResponse findAllMemberType() {
        List<MemberType> collect = Arrays.stream(MemberType.class.getEnumConstants()).collect(Collectors.toList());

        System.out.println( collect.toString());
        return MemberTypeResponse.builder().memberType(collect).build();
    }

    public void join(MemberJoinRequest request) {

        memberRepository.join(request);
    }

    //회원정보수정
    public void memberUpdate(MemberJoinRequest request) {
        //todo
        //회원정보수정일경우 id값으로 조건걸어서 update를할텐데 id가null인경우야 앞단 validationd에서걸릴텐데
        // 회원에 가입된 id가 아닐경우 무작정 update를해봣자 조건에안걸리니까 업데이트도안되겠지만
        // 이런 서비스 로직인경우 무조건 아이디가있는지 검증 하는게 맞나?
    }

/**
 *
 * // 안 좋음
 * Optional<Member> member = ...;
 * return member.orElse(new Member());  // member에 값이 있든 없든 new Member()는 무조건 실행됨
 *
 * // 좋음
 * Optional<Member> member = ...;
 * return member.orElseGet(Member::new);  // member에 값이 없을 때만 new Member()가 실행됨
 *
 * // 좋음
 * Member EMPTY_MEMBER = new Member();
 * ...
 * Optional<Member> member = ...;
 * return member.orElse(EMPTY_MEMBER);  // 이미 생성됐거나 계산된 값은 orElse()를 사용해도 무방
 */

}
