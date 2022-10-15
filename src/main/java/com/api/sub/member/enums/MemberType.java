package com.api.sub.member.enums;

import com.api.sub.common.GenericEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
//@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum MemberType implements GenericEnum<String> {
    NORMAL("N", "일반 회원"),
    VIP("V", "VIP");

    private String value;
    private String description;
    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
