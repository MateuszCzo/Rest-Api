package pl.nullpointerexception.restapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.nullpointerexception.restapi.model.Post;
import pl.nullpointerexception.restapi.repository.PostRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
class PostControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private PostRepository postRepository;

    @Test
    @Transactional
    void shouldGetSinglePost() throws Exception {
        // given
        Post newPost = new Post();
        newPost.setTitle("Test");
        newPost.setContent("Test content");
        newPost.setCreated(LocalDateTime.now());
        postRepository.save(newPost);
        // when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/posts/" + newPost.getId()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andReturn();
        // then
        Post post = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Post.class);
        Assertions.assertThat(post).isNotNull();
        Assertions.assertThat(post.getId()).isEqualTo(newPost.getId());
        Assertions.assertThat(post.getTitle()).isEqualTo(newPost.getTitle());
        Assertions.assertThat(post.getContent()).isEqualTo(newPost.getContent());
    }
}