package com.api.sub.member.request;


import com.api.sub.member.enums.MemberType;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springdoc.api.annotations.ParameterObject;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberJoinRequest {
    //todo @Schema와 @Parameter..차이가.......@Parameter로하면 example도안나옴,, notnull은 적용됨
    /*
     *
     *
     * null기준 - "id" : null (Null x , NULL X ."null" X, ) OR "id"필드가 아예없는경우를 뜻함
     *
      NotNull - null을 허용하지 않습니다. "", " "는 허용합니다.
      {
          "name": "string",
          "type": "NORMAL",
          "phone": "string"
        }

        NotEmpty - null, ""을 허용하지 않습니다. " "는 허용합니다.
       {
         "id" :""
          "name": "string",
          "type": "NORMAL",
          "phone": "string"
        }

        @NotBlank  // null, "", " " 모두 허용하지 않습니다.
     */

    @Schema(description = "회원 아이디" , example = "test" )
    @NotEmpty(message = "회원아이디가 없습니다") //@valid를 해야만 적용됨
    private String id;

    @NotEmpty(message = "회원아이디가 없습니다")
    @Schema(description = "회원 이름", example = "test")
    private String name;

    @Schema(description = "회원 유형", example = "NORMAL")
    private MemberType type;

    @Schema(description = "연락처" ,example = "test")
    private String phone;
}
