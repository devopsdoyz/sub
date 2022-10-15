package com.api.sub.test.transaction.controller;
import com.api.sub.test.transaction.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Slf4j
@Tag(name = "트랜잭션 테스트", description = "트랜잭션 테스트")
@RequestMapping("/api")
public class TransactionController {


    private final TransactionService transactionService;

    //https://yeonyeon.tistory.com/257
    //https://a1010100z.tistory.com/106
    @PostMapping("/test")
    @Operation(summary = "트랜잭션 테스트1", description = "")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void test() {
        transactionService.callByNotTransaction();
    }



    @PostMapping("/test2")
    @Operation(summary = "트랜잭션 테스트2", description = "")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void test2() {
        transactionService.call();
    }


    @PostMapping("/test3")
    @Operation(summary = "트랜잭션 테스트3", description = "")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void test3() {
        transactionService.call2();
    }


    @PostMapping("/test4")
    @Operation(summary = "심화1 - 트랜잭션 테스트4", description = "")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void test4() {
        String id  ="user01,user02,user03";
        String description = "first try";
        transactionService.test(id, description);
    }

    @PostMapping("/test5")
    @Operation(summary = "심화1 - 트랜잭션 테스트5", description = "")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void test5() {
        String id  ="user01,user02,user03";
        String description = "first try";
        transactionService.test2(id, description);
    }

    @PostMapping("/test6")
    @Operation(summary = "심화1 - 트랜잭션 테스트6", description = "")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void test6() {
        String id  ="user01,user02,user03";
        String description = "first try";
        transactionService.test3(id, description);
    }


}
