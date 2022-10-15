package com.api.sub.member.controller;
/*
import com.api.sub.member.entity.MemberEntity;
import com.api.sub.member.enums.MemberType;
import com.api.sub.member.request.MemberJoinRequest;
import com.api.sub.member.response.MemberResponse;
import com.api.sub.member.response.MemberTypeResponse;
import com.api.sub.member.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

 spring-fox 용 ->  spring-doc으로 대체해서 안씀
@RestController
@AllArgsConstructor
@Slf4j
@Api(tags={"메인화면 API"})
public class MemberController {

    private final MemberService memberService;



    @GetMapping("/param/get/test1")
    @ApiOperation(value="이름")
    public ResponseEntity<List<MemberResponse>> getTest1(@RequestParam String id) {
        log.info("name : {}",id);

        return ResponseEntity.ok(memberService.test(id));
    }


    @GetMapping("/param/get/member-type")
    @ApiOperation(value="회원 유형 ")
    public ResponseEntity<MemberTypeResponse> getMemberType() {

        return ResponseEntity.ok(memberService.getMemberType());
    }



    //https://yeonyeon.tistory.com/257
    //https://a1010100z.tistory.com/106
    @PostMapping("/param/member/join")
    @ApiOperation(value="회원 가입 ")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void joinMember(@RequestBody MemberJoinRequest request) {
        memberService.saveMember(request);
    }





}

*/