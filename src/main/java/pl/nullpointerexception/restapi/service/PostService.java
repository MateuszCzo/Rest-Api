package pl.nullpointerexception.restapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.nullpointerexception.restapi.model.Post;
import pl.nullpointerexception.restapi.repository.PostReposytory;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostReposytory postReposytory;
    public List<Post> getPosts() {
        return postReposytory.findAll();
    }

    public Post getSinglePost(Long id) {
        return postReposytory.findById(id)
                .orElseThrow();
    }
}
