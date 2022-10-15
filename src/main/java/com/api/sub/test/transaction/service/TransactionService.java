package com.api.sub.test.transaction.service;


import com.api.sub.common.exception.BusinessErrorCode;
import com.api.sub.member.enums.MemberErrorType;
import com.api.sub.member.enums.MemberType;
import com.api.sub.test.transaction.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.*;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;


    private final TransactionProcessService transactionProcessService ;


    /**
     *  callByNotTransaction에서  @Transactional 적용을 하지않는 경우
     *  =>트랜잭션이 보장안됨.
     *
     *  성공한놈은 걍 성공, 실패한놈이있어도 신경쓰지않음
     *  process1은 성공하고 process2의 user02 성공, process2의 user01이 실패인경우 process1은 커밋 / process2.user02 커밋
     *  팀이고뭐고 그냥 개인플레이라고 생각하면됨.
     *
     *  결과)
     *  process1.user01 성공
     *  process2.user02 성공
     *  process2.user01 실패
     *
     * process01.user01 롤백안됨
     * process01.user02 롤백안됨
     *
     *
     *
     */
    public void callByNotTransaction(){
        process1();
        process2();
    }


    /**
     * call에 @Transactional을 적용한경우
     * 실행되는 메서드 process1과 process2가 트랜잭션 하길원함
     *
     * call에서 실행되는 메서드중에서 하나라도 실패하면 실행되는 *모든 메서드*들은  롤백
     * process1은 성공하고 process2의 user02 성공, process2의 user01이 실패인경우 process1, process2 모두 롤백
     * call에서 호출되는 모든메서드가 하나의 팀이라고 생각하면됨.
     * 결과)
     * process1.user01 성공
     * process2.user02 성공
     * process2.user01 실패
     *
     * process1.user01 롤백
     * process2.user02 롤백
     *
     *
     */
    @Transactional
    public void call(){
        process1();
        process2();;
    }


    private void process1(){
        System.out.println("===============process1");
        Map<String, String> map = new HashMap<>();
        map.put("id", "user01");
        map.put("name", "홍길동");

        transactionRepository.saveMember(map);
    }

    private void process2(){
        System.out.println("===============process2");
        Map<String, String> map = new HashMap<>();

        map.put("id", "user02");
        map.put("name", "김길동");
        transactionRepository.saveMember(map);

        map.put("id", "user01");
        map.put("name", "홍길동");
        transactionRepository.saveMember(map);
    }

    /**
     * call2에서 호출되는 메서드*끼리* 트랜잭션하길원하는경우(process1, process2 별로 트랜잭션 하길 경우)
     * => 다른서비스 생성해서 호출해야함. 스프링은 프록시기반으로 트랜잭션이 적용되서.
     *
     * call2에서 실행되는 메서드에서 *실패한 메서드*에 대해서만 트랜잭션을 보장 - 성공한메서드는 커밋함
     * process1은 성공하고 process2의 user02 성공, process2의 user01이 실패인경우 process1은 커밋 / process2 전체에 대해 롤백
     *
     * call2에서 호출되는 메서드끼리 팀
     *
     * 결과)
     * transactionProcessService.process1.user01 성공
     *
     * transactionProcessService.process2.user02 성공
     * transactionProcessService.process2.user01 실패
     *
     * transactionProcessService.process2.user02 롤백
     *
     *
     */
    public void call2(){
        transactionProcessService.process1();
        transactionProcessService.process2();
    }

    //===============================================================================
    /**
     * 아래 실험1, 실험2, 실험3은 id별로 process3이 트랜잭션하길원할때의 실험
     * 즉 상황은 아래와같다.
     * user01의 saveLog성공, saveStatus성공
     * user02의 saveLog성공, saveStatus실패
     * user03의 saveLog성공, saveStatus성공
     * 이 때 성공한 user01, user03은 커밋되고 user02의 saveStatus가 실패하므로 user02의 saveLog는 롤백되어야함
     *
     * 중간에 실패하는 user02의 saveStatus때문에 try~catch를 하지않으면 user03도 실행하지않으니 try~catch필수
     */


    /**
     * 실험1) 실패 =>  user02의 saveLog가 롤백되지않고 커밋됨.
     *
     * 이유는
     * 스프링은 프록시기반이라 자기호출의 @Transactional은 안됨
     *
     */
    public void test(String userId, String description){
        List<String> ids = new ArrayList<>();
        if(userId.contains(",")){
          ids = Arrays.asList(userId.split(","));
        }else{

            ids.add(userId);
        }

        for(String id : ids){
            try{
                process3(id, description);
            }catch (Exception e){
                System.out.println("===============ERROR============");
            }

        }
    }

    @Transactional //-> 보장못함.
    public void process3(String id, String description){

        transactionRepository.saveLog(id, description);

        transactionRepository.saveStatus(id, "Y");

    }

    //=================================================================================

    /**
     * 실험2) 성공
     * 프록시기반이므로 transactionProcessService 새로운서비스에서 process3를 생성하면서 @Transactional붙혔고, 요놈을 호출
     *
     */

    public void test2(String userId, String description){
        List<String> ids = new ArrayList<>();
        if(userId.contains(",")){
            ids = Arrays.asList(userId.split(","));
        }else{

            ids.add(userId);
        }

        for(String id : ids){
            try{
                transactionProcessService.process3(id, description);
            }catch (Exception e){
                System.out.println("===============ERROR============");
            }

        }
    }
    //==================================================================================

    private final PlatformTransactionManager transactionManager;

    /**
     * 실험3) 성공
     *
     * @Transactinal 대신 구간별 트랜잭션 적용
     */
    public void test3(String userId, String description){
        List<String> ids = new ArrayList<>();
        if(userId.contains(",")){
            ids = Arrays.asList(userId.split(","));
        }else{
            ids.add(userId);
        }

        for(String id : ids){
            TransactionStatus txStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
            try{
                process4(id, description);
                transactionManager.commit(txStatus);
            }catch (Exception e){
                System.out.println(e.getMessage());
                transactionManager.rollback(txStatus);
            }

        }
    }

    public void process4(String id, String description){

        transactionRepository.saveLog(id, description);
        if("user02".equals(id)){
            throw MemberErrorType.MBBC000.exception(id);
        }
        transactionRepository.saveStatus(id, "Y");

    }
    //======================================================================================

}
