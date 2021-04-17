package com.chongin12.practice.springboot.service.posts;

import com.chongin12.practice.springboot.domain.posts.Posts;
import com.chongin12.practice.springboot.domain.posts.PostsRepository;
import com.chongin12.practice.springboot.web.dto.PostsResponseDto;
import com.chongin12.practice.springboot.web.dto.PostsSaveRequestDto;
import com.chongin12.practice.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("There is no such posts"));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById (Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("There is no such posts. id=" + id));

        return new PostsResponseDto(entity);
    }
}
