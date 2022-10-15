package com.api.sub.member.enums;

import com.api.sub.common.exception.BusinessErrorCode;
import com.api.sub.common.exception.BusinessErrorCodeException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.function.BiFunction;

public enum MemberErrorType implements BusinessErrorCode {
    // httpStatus
    MBBC000("%s : 슈퍼기업관 연계 ID로 등록되어 있습니다. 연계 ID 삭제 후, ID정지 / 사업자번호 변경 작업을 할 수 있습니다.", BusinessErrorCodeException.BAD_REQUEST::httpStatus),
    MBBC001("%s : 메인 아이디 [ %s ] 1000대 기업 메인ID로 등록되어 있습니다. 메인 ID를 해지, company 연결 여부 ‘대기’로 변경 후, 사업자번호를 변경할 수 있습니다.", BusinessErrorCodeException.BAD_REQUEST::httpStatus),
    MBBC002("%s : 1000대 기업에 등록된 ID 입니다. 1000대 기업 관리자에서 해당 사업자번호의 company 연결 여부를 ‘대기’로 변경 후, 사업자 번호를 변경할 수 있습니다.", BusinessErrorCodeException.BAD_REQUEST::httpStatus),
    MBBC003("%s : 기업마스터에 등록된 ID입니다. 기업마스터 ID를 다른 ID로 변경 후, 사업자번호를 변경할 수 있습니다.", BusinessErrorCodeException.BAD_REQUEST::httpStatus),
    MBBC004("%s : 변경 요청한 사번이 잡코리아 가입불가 사번리스트에 %s 으로 등록되어 있습니다. 확인 후 다시 처리해 주시기 바랍니다.", BusinessErrorCodeException.BAD_REQUEST::httpStatus),
    MBBC005("%s : 회원정보 캐시테이블 적용이 원활하지 않습니다.", BusinessErrorCodeException.BAD_REQUEST::httpStatus),
    MBBC006("%s :변경사번이 기업마스터 DB에 없어 자동으로 업데이트 되었습니다.", BusinessErrorCodeException.BAD_REQUEST::httpStatus),
    MBBC007("%s :사번변경 완료, 기업마스터 DB 업데이트 시도 중 에러 %s", BusinessErrorCodeException.BAD_REQUEST::httpStatus);


    MemberErrorType(String message, BiFunction<BusinessErrorCode, Object[], BusinessErrorCodeException> exception) {
        this.message = message;
        this.exception = exception;
    }

    @Getter
    private final String message;

    private final BiFunction<BusinessErrorCode, Object[], BusinessErrorCodeException> exception;

    public BusinessErrorCodeException exception(Object... args) {
        //todo apply;;;;;;;
        return exception.apply(this, args);
    }

    @Override
    public String getErrorCode() {
        return toString();
    }

}
