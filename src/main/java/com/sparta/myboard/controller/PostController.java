package com.sparta.myboard.controller;

import com.sparta.myboard.dto.*;
import com.sparta.myboard.security.UserDetailsImpl;
import com.sparta.myboard.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostController {

    private final PostService postService;

    @GetMapping("/posts") // 전체 게시글 목록 조회
    public List<MainPostResponseDto> getPosts() { //#1 메서드 실행
        return postService.getPosts(); //#8 서비스가 리턴한 데이터를 클라이언트에게 리턴한다.
    }

    @PostMapping("/posts") // 게시글 작성
    public PostResponseDto createPost(@RequestBody PostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return postService.createPost(requestDto, userDetails.getUser());
    }

    @GetMapping("/posts/{id}") // 선택한 게시글 조회
    //@PathVariable => {id}에 들어오는 값을 Long id에 담아줌 (받을데이터가 1개일때)
    //@requestBody => json 형식 (받을 데이터가 여러개일때)
    public PostResponseDto getPost(@PathVariable Long id){
        return postService.getPost(id);
    }

    @PutMapping("/posts/{id}") //선택한 게시글 수정
    public PostResponseDto updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return postService.updatePost(id, requestDto, userDetails.getUser());
    }

    @DeleteMapping("/posts/{id}") //선택한 게시글 삭제
    public MsgResponseDto deletePost(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.deletePost(id, userDetails.getUser());
    }

    @GetMapping("/posts/category")
    public List<MainPostResponseDto> getPostCategory(@RequestParam String category) {
        return postService.getPostCategory(category);
    }
}
