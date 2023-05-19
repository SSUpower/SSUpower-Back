package com.ssupowerback.controller;

import com.ssupowerback.entity.Member;
import com.ssupowerback.entity.MemberLoginDTO;
import com.ssupowerback.repository.MemberJpaRepository;
import com.ssupowerback.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class MemberController {

    private final MemberService memberService;

    private final MemberJpaRepository memberJpaRepository;

    @PostMapping("/")
    public Object login(@RequestBody MemberLoginDTO memberLoginDTO) {
        /**
         * 입력받은 DTO form을 멤버에 초기화
         */
        String email = memberLoginDTO.getEmail();
        String password = memberLoginDTO.getPassword();
        try {
            /**
             * Service class email기준으로 search
             */
            Member member = memberService.findByEmail(email);
            if (member != null && member.getPassword().equals(password)) {
                /**
                 * 로그인 성공
                 */
                return member;
            } else {
                /**
                 *로그인 실패 case 1 : 비밀번호 불일치시 email값이 null인 임시객체 반환
                 */
//                Member temp = new Member(null, member.getName(), member.getPassword());
                Member temp = Member.builder()
                        .id(member.getId())
                        .email(null)
                        .name(member.getName())
                        .password(member.getPassword()).build();
                return temp;
            }
        } catch (IllegalArgumentException e) {
            /**
             * 로그인 실패 case 2 : 계정 없음
             */
            return null;
        }
}


    @PostMapping("/join")
    public Long create(@RequestBody Member member) {
        /**
         *  중복회원 검사
         */
        if(validateDuplicateMember(member)) {
            return memberService.save(member);
        }else {
            return null;
        }
    }

    private boolean validateDuplicateMember(Member member) {
        if(memberJpaRepository.findByEmail(member.getEmail())
                .isPresent()){
            return false;
        }else return true;
    }

    @GetMapping("/member/{id}")
    public Member read(@PathVariable Long id) {

        return memberService.findById(id);
    }

    @PostMapping("/member/{id}/update")
    public Long update(@PathVariable Long id, @RequestBody Member member) {
        return memberService.update(id, member);
    }

    @PostMapping("/member/{id}/delete")
    public Long delete(@PathVariable Long id) {
        memberService.delete(id);
        return id;
    }
}
