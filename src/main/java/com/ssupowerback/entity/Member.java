package com.ssupowerback.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
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
