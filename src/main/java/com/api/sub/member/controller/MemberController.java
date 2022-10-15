package com.api.sub.member.controller;
import com.api.sub.member.request.MemberJoinRequest;
import com.api.sub.member.response.MemberResponse;
import com.api.sub.member.response.MemberTypeResponse;
import com.api.sub.member.service.MemberService;
import com.api.sub.test.transaction.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@Tag(name = "posts", description = "회원 관리 API")
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;


    @GetMapping("/findById")
    @Operation(summary = "get member", description = "id로 회원 정보 찾기")
    public ResponseEntity<MemberResponse> getMemberById(@RequestParam String id) {
        log.info("name : {}",id);
        return ResponseEntity.ok(memberService.findById(id));
    }

    @GetMapping("/all")
    @Operation(summary = "all member", description = "모든 회원 정보 찾기")
    public ResponseEntity<List<MemberResponse>> getAllMember() {
        return ResponseEntity.ok(memberService.all());
    }

    @GetMapping("/findAllMemberType")
    @Operation(summary = "회원 모든 유형 가져오기", description = "회원 유형 가져오기 ")
    public ResponseEntity<MemberTypeResponse> getMemberType() {

        return ResponseEntity.ok(memberService.findAllMemberType());
    }



    //https://yeonyeon.tistory.com/257
    //https://a1010100z.tistory.com/106
    @PostMapping("/param/member/join")
    @Operation(summary = "회원 가입", description = "회원 가입")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void joinMember(@RequestBody @Valid MemberJoinRequest request) {
        memberService.join(request);
    }


}
