package com.api.sub.common;

public interface GenericEnum<T>{
    /**
     * <pre>
     *     Enum의 Generic value를 반환합니다.
     * </pre>
     * @return T Enum에 지정한 value값
     */

    T getValue();
    /**
     * <pre>
     * Enum 의 설명을 반환합니다.
     * </pre>
     * @return 설명
     */
    String getDescription();


}
