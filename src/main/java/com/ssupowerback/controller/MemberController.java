package com.ssupowerback.controller;

import com.ssupowerback.entity.Member;
import com.ssupowerback.entity.MemberLoginDTO;
import com.ssupowerback.exception.ErrorResponse;
import com.ssupowerback.repository.MemberJpaRepository;
import com.ssupowerback.service.MemberService;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Pattern;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = {"http://localhost:3000", "https://web-ssupower-front-p8xrq2mlfqixcp0.sel3.cloudtype.app"})
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
    public ResponseEntity<?> join(@Valid @RequestBody Member member) {
            if (!isValidEmail(member.getEmail())) {
                ErrorResponse errorResponse = ErrorResponse.of(HttpStatus.BAD_REQUEST, "유효하지 않은 이메일 주소입니다.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
            }
            else if (validateDuplicateMember(member)) {
                ErrorResponse errorResponse = ErrorResponse.of(HttpStatus.CONFLICT, "이미 등록된 회원입니다.");
                return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
            } else {
                /**
                 * 회원가입 성공
                 */
                Long memberId = memberService.save(member);
                return ResponseEntity.ok(memberId);
            }
    }

    private boolean validateDuplicateMember(Member member) {
        if(memberJpaRepository.findByEmail(member.getEmail())
                .isPresent()){
            return true;
        }else return false;
    }

    private boolean isValidEmail(String email) {
        final String EMAIL_PATTERN = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        return pattern.matcher(email).matches();
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
