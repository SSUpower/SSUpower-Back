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
    @NotBlank(message = "Null값은 허용되지 않습니다.")
    private String email;
    @Column
    @NotBlank(message = "Null값은 허용되지 않습니다.")
    private String name;
    @Column
    @NotBlank(message = "Null값은 허용되지 않습니다.")
    private String password;
    @Column
    @NotBlank(message = "Null값은 허용되지 않습니다.")
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
