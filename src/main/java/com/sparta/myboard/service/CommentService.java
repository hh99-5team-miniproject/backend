package com.sparta.myboard.service;

import com.sparta.myboard.dto.CommentRequestDto;
import com.sparta.myboard.dto.CommentResponseDto;
import com.sparta.myboard.dto.MsgResponseDto;
import com.sparta.myboard.entity.Comment;
import com.sparta.myboard.entity.Post;
import com.sparta.myboard.entity.User;
import com.sparta.myboard.entity.UserRoleEnum;
import com.sparta.myboard.exception.customexception.CommentCustomException;
import com.sparta.myboard.exception.customexception.ErrorCode;
import com.sparta.myboard.exception.customexception.PostCustomException;
import com.sparta.myboard.exception.customexception.UserCustomException;
import com.sparta.myboard.repository.CommentRepository;
import com.sparta.myboard.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    // 댓글 작성
    @Transactional
    public CommentResponseDto createComment(Long id, CommentRequestDto commentRequestDto, User user) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new PostCustomException(ErrorCode.POST_NOT_FOUND)
        );
        Comment comment = commentRepository.save(new Comment(commentRequestDto, post, user));

        return new CommentResponseDto(comment);
    }

    // 댓글 수정
    @Transactional
    public CommentResponseDto updateComment(Long commentId, CommentRequestDto commentRequestDto, User user) {

        if (commentRepository.existsByIdAndUser(commentId, user)) {
            Comment comment = commentRepository.findById(commentId).orElseThrow(
                    () -> new CommentCustomException(ErrorCode.COMMENT_NOT_FOUND)
            );
            comment.update(commentRequestDto);
            return new CommentResponseDto(comment);
        } else {
            throw new UserCustomException(ErrorCode.NOT_ALLOW_UPDATE);
        }

    }

    // 댓글 삭제
    @Transactional
    public MsgResponseDto deleteComment(Long commentId, User user ) {

        UserRoleEnum userRoleEnum = user.getRole();

        //유효한 토큰일 경우 삭제
        if (user.getRole().equals(UserRoleEnum.ADMIN)){
            commentRepository.deleteById(commentId);
            return new MsgResponseDto("관리자 권한으로 댓글이 삭제되었습니다.");
        } else if (commentRepository.existsByIdAndUser(commentId, user) && userRoleEnum == UserRoleEnum.USER){
            commentRepository.deleteById(commentId);
            return new MsgResponseDto("댓글이 삭제되었습니다.");
        } else {
            throw new UserCustomException(ErrorCode.NOT_ALLOW_DELETE);
        }
    }
}