package com.ssupowerback.entity;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Data
@RequiredArgsConstructor
public class MemberLoginDTO {
    private String email;
    private String password;

}
