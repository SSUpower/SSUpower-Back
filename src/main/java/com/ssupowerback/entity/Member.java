package com.ssupowerback.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.ColumnDefault;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @NotBlank(message = "이메일을 입력해주세요.")
    private String email;
    @Column
    @NotBlank(message = "이름을 입력해주세요.")
    private String name;
    @Column
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;
    @Column
    @NotBlank(message = "학교정보를 입력해주세요.")
    private String school;

    @Builder
    public Member(Long id,String email, String name, String password,String school) {
        this.id = id;
        this.email=email;
        this.name=name;
        this.password=password;
        this.school=school;
    }

    public void update(String email, String name, String password,String school) {
        this.email=email;
        this.name=name;
        this.password=password;
        this.school=school;
    }
}
