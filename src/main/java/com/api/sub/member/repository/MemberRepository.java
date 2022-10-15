package com.api.sub.member.repository;


import com.api.sub.member.entity.MemberEntity;
import com.api.sub.member.request.MemberJoinRequest;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
@Mapper
public interface MemberRepository {

    MemberEntity findById(String id);

    List<MemberEntity> findAll();

    void join(MemberJoinRequest request);

}
