package com.sparta.myboard.controller;

import com.sparta.myboard.dto.*;
import com.sparta.myboard.security.UserDetailsImpl;
import com.sparta.myboard.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor //생성자를 만들어야 하는 번거로움을 없애줌
public class UserController {

    private final UserService userService; //의존성주입

    @GetMapping("/signup")
    public ModelAndView signupPage(){
        return new ModelAndView("signup");
    }

    @PostMapping("/signup")
    public void signup(@RequestBody @Valid SignupRequestDto signupRequestDto) {
        userService.signup(signupRequestDto);
    }


    @PostMapping("/login")
    public LoginNicknameResponseDto login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        return userService.login(loginRequestDto, response);
    }

    @DeleteMapping("/deleteUser")
    public MsgResponseDto deleteUser(@RequestBody DeleteUserRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return userService.deleteUser(requestDto, userDetails.getUser());
    }

}
