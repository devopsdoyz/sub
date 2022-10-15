package com.api.sub.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * AOP - @Component, @Aspect 를 써야됨
 *
 * @Pointcut 어디서 중간개입을 할건지에 대한 범위지정
 */
@Component
@Aspect
@Slf4j
public class LogInspect {


    @Pointcut("execution(* com.api..*Controller.*(..))") // 이런 패턴이 실행될 경우 수행
    public void loggerPointCut() {
    }

    @Pointcut("execution(* com.api..*Service.find*(..))") // 이런 패턴이 실행될 경우 수행
    public void executeTimePointCut() {
    }

    @Around("executeTimePointCut()")
    public Object executeTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
         long start = System.currentTimeMillis();

        Object result = proceedingJoinPoint.proceed();

        long end = System.currentTimeMillis();

        log.info("시간 : {}",TimeUnit.MILLISECONDS.toSeconds(end-start));

        return result;
    }

    //AfterReturning proceedingJoinPoint.proceed()->service.findbyall 리턴됨과 동시에 실행
    @AfterReturning("executeTimePointCut()")
    public void afterReturning(JoinPoint jp) {
        System.out.println("핵심기능 정상 종료: 하루를 반성");
    }

    @Around("loggerPointCut()")
    public Object methodLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        try {
            Object result = proceedingJoinPoint.proceed();
            HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest(); // request 정보를 가져온다.

            String controllerName = proceedingJoinPoint.getSignature().getDeclaringType().getSimpleName();
            String methodName = proceedingJoinPoint.getSignature().getName();

            Map<String, Object> params = new HashMap<>();

            try {
                params.put("controller", controllerName);
                params.put("method", methodName);
                params.put("params", getParams(request));
//				params.put("log_time", LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                params.put("log_time", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                params.put("request_uri", request.getRequestURI());
                params.put("http_method", request.getMethod());
                params.put("request_ip",request.getRemoteAddr());
            } catch (Exception e) {
                log.error("LoggerAspect error", e);
            }
            log.info("params : {}", params); // param에 담긴 정보들을 한번에 로깅한다.

            ObjectMapper om = new ObjectMapper();
            log.info("objectMapper : {}",om.writeValueAsString(params));


            return result;

        } catch (Throwable throwable) {
            throw throwable;
        }
    }

    /**
     * request 에 담긴 정보를 JSONObject 형태로 반환한다.
     * @param request
     * @return
     */
    private static JSONObject getParams(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        Enumeration<String> params = request.getParameterNames();

        while (params.hasMoreElements()) {
            String param = params.nextElement();
            String replaceParam = param.replaceAll("\\.", "-");
            jsonObject.put(replaceParam, request.getParameter(param));
        }
        return jsonObject;
    }
}
