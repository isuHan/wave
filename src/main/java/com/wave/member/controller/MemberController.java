package com.wave.member.controller;

import com.wave.member.dto.MemberFormDto;
import com.wave.member.entity.Member;
import com.wave.member.service.MemberService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("/members")
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    //회원가입 페이지로 이동시키는 메소드
    @GetMapping(value = "/new")
    public String memberForm(Model model) {
        model.addAttribute("memberFormDto", new MemberFormDto());
        return "memberForm";
    }

    //회원가입 메소드
    @PostMapping(value = "/new")
    public String memberForm(MemberFormDto memberFormDto) {
        Member member = Member.createMember(memberFormDto, passwordEncoder);
        memberService.saveMember(member);

        return "redirect:/";
    }

    //회원가입 성공, 실패 메소드
    @PostMapping(value = "/new")
    public String newMember(@Valid MemberFormDto memberFormDto, BindingResult bindingResult, Model model) {

        //에러가 있다면 회원가입 페이지로 이동시키는 메소드
        if(bindingResult.hasErrors()) {
            return "member/memberForm";
        }

        try {
            Member member = Member.createMember(memberFormDto, passwordEncoder);
            memberService.saveMember(member);
        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage()); //중복회원가입 예외가 발생하면 에러 메시지를 뷰로 전달한다
            return "member/memberForm";
        }

        //성공 시 메인페이지로 리다이렉트 시킨다
        return "redirect:/";
    }

    //로그인 메소드
    @GetMapping(value = "/login")
    public String loginMember() {
        return "/member/memberLoginForm";
    }

    //로그인 실패 메소드
    @GetMapping(value = "/login/error")
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요");
        return "/member/memberLoginForm";
    }
}
