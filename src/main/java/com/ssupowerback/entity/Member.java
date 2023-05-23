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
    /**
     * 모든 attribute는 null을 허용하지 않는다
     */
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @NotBlank
    private String email;
    @Column
    @NotBlank
    private String name;
    @Column
    @NotBlank
    private String password;
    @Column
    @NotBlank
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
