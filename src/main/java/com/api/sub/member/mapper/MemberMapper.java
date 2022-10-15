package com.api.sub.member.mapper;

import com.api.sub.member.entity.MemberEntity;
import com.api.sub.member.repository.MemberRepository;
import com.api.sub.member.response.MemberResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.lang.reflect.Member;
import java.util.List;

@Mapper(componentModel = "spring") //todo 이거뭔데?
public interface MemberMapper {
    //todo 모냐이게 이러는이유가있을것아니냐
    MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

    List<MemberResponse> toResponses(List<MemberEntity> memberEntitys);

    // entity와 response 매핑 이름이 다를경우 이렇게 아래처럼 맞춰주는데, 이부분은 toResponses에 해줄게아니라 toResponse를 따로만들어서 여기에 해줘야됨
    // entity와 response 이름이 똑같은 list를 변경해야할경우는 toResponse와 같은 하나의객체?를 위한 메서드를 만들어주지않아도 알아서 맵스트럿츠가 만듬.
    @Mapping(source = "name", target="userName")
    @Mapping(source = "id", target="userId")
    MemberResponse toResponse(MemberEntity entity);

}
