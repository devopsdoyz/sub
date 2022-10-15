package com.api.sub.test.transaction.repository;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

//@Repository
@Mapper
public interface TransactionRepository {

    void saveMember(Map<String, String> map);

    void saveLog(String id, String description);

    void saveStatus(String id, String status);
}
