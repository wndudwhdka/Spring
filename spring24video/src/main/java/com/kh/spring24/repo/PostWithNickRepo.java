package com.kh.spring24.repo;

import java.util.List;

import com.kh.spring24.dto.PostWithNickDto;
import com.kh.spring24.vo.SearchVO;

public interface PostWithNickRepo {
    // 통합게시물+닉네임 상세조회
    PostWithNickDto selectOne(Long postNo);
    // 통합게시물+닉네임 목록조회
    List<PostWithNickDto> selectList(SearchVO searchVO);
}
