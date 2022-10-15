package com.api.sub.test.transaction.service;


import com.api.sub.member.entity.MemberEntity;
import com.api.sub.member.enums.MemberType;
import com.api.sub.member.mapper.MemberMapper;
import com.api.sub.member.repository.MemberRepository;
import com.api.sub.member.request.MemberJoinRequest;
import com.api.sub.member.response.MemberResponse;
import com.api.sub.member.response.MemberTypeResponse;
import com.api.sub.test.abstract_interface.UtteranceSetSimilarityProvider;
import com.api.sub.test.abstract_interface.wordcomma.abstracts.WordCommaRelTextSimilarity;
import com.api.sub.test.transaction.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Array;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionProcessService {

    private final TransactionRepository transactionRepository;

    @Transactional
    public void process1(){

        System.out.println("===============process1");
        Map<String, String> map = new HashMap<>();
        map.put("id", "user01");
        map.put("name", "홍길동");

        transactionRepository.saveMember(map);
    }

    @Transactional
    public void process2(){
        System.out.println("===============process2");
        Map<String, String> map = new HashMap<>();

        map.put("id", "user02");
        map.put("name", "김길동");
        transactionRepository.saveMember(map);


        map.put("id", "user01");
        map.put("name", "홍길동");
        transactionRepository.saveMember(map);

    }

    @Transactional
    public void process3(String id, String description){

        transactionRepository.saveLog(id, description);

        transactionRepository.saveStatus(id, "Y");

    }

}
