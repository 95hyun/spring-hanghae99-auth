package com.sparta.springauth.controller;

import com.sparta.springauth.dto.LoginRequestDto;
import com.sparta.springauth.dto.SignupRequestDto;
import com.sparta.springauth.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/login-page")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/user/signup")
    public String signupPage() {
        return "signup";
    }

    @PostMapping("/user/signup")
    public String signup(@ModelAttribute SignupRequestDto requestDto) {
        userService.signup(requestDto);

        return "redirect:/api/user/login-page";
    }

    @PostMapping("/user/login")
    public String login(LoginRequestDto requestDto, HttpServletResponse res) {
        // res에 응답에 대한 서블릿정보를 담을거니까 HttpServletResponse 객체(그릇)을 파라미터로 필요.
        try {
            userService.login(requestDto, res);
            // 로그인 로직 실행 :
            // requestDto의 정보(로그인정보)로 jwt 토큰을 만들고
            // 쿠키를 만들어 jwt 토큰을 넣은 후
            // res 에 넣어준다.
        } catch (Exception e) {
            return "redirect:/api/user/login-page?error";
        }

        return "redirect:/";
        // 중요!! 계속 헷갈리고 모르던 Point !! ??????? 아;; 확실하지않음 일단 보류
        // HttpServletResponse에 쿠키를 설정하더라도 클라이언트로 즉시 전송되는 것은 아니며,
        // 응답이 클라이언트로 전달되는 시점은 해당 메서드의 실행이 종료되고 클라이언트에게 응답이 반환될 때입니다.

        // 즉 return "redirect:/"; 이 수행되어 login 메서드가 종료될 때, HttpServletResponse가 클라이언트로 전달된다.
    }
}