package com.api.sub.member.response;


import com.api.sub.member.enums.MemberType;
import lombok.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberTypeResponse {

    private List<MemberType> memberType;
}
