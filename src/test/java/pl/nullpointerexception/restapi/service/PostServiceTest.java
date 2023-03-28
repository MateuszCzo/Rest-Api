package pl.nullpointerexception.restapi.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.nullpointerexception.restapi.model.Post;

@SpringBootTest
class PostServiceTest {
    @Autowired
    private PostService postService;

    @Test
    void shouldGetSinglePost() {
        Post post = postService.getSinglePost(1L);
        Assertions.assertThat(post).isNotNull();
        Assertions.assertThat(post.getId()).isEqualTo(1L);
    }
}