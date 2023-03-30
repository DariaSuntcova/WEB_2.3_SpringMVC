package ru.netology.service;

import org.springframework.stereotype.Service;
import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;
import ru.netology.repository.PostRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final PostRepository repository;

    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    public List<Post> all() {
        return repository.all();
    }

    public List<PostDTO> allDTO() {
        return all().stream()
                .map(x -> new PostDTO(x.getId(), x.getContent()))
                .collect(Collectors.toList());
    }

    public PostDTO getById(long id) {
        Post post = repository.getById(id);
        if (post != null) {
            return new PostDTO(post.getId(), post.getContent());
        } else
            throw new NotFoundException();
    }

    public PostDTO save(Post post) {
        repository.save(post);
        return new PostDTO(post.getId(), post.getContent());
    }

    public void removeById(long id) {
        repository.removeById(id);
    }
}


