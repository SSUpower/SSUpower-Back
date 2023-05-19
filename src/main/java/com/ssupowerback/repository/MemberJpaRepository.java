package com.ssupowerback.repository;


import com.ssupowerback.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberJpaRepository extends JpaRepository<Member,Long> {
    Optional<Object> findByEmail(String email);
}
