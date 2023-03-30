package ru.netology.repository;

import org.springframework.stereotype.Repository;
import ru.netology.model.Post;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class PostRepository {
    private final AtomicLong postID;
    private final Map<Long, Post> postMap;

    public PostRepository() {
        postID = new AtomicLong(1);
        postMap = new ConcurrentHashMap<>();
    }

    public List<Post> all() {
        return postMap.values().stream()
                .filter(x -> !x.isRemoved())
                .collect(Collectors.toList());
    }

    public Post getById(long id) {
        if (postMap.containsKey(id)) {
            Post post = postMap.get(id);
            if (!post.isRemoved()){
                return post;
            }
        }
        return null;
    }

    public Post save(Post post) {
        long id = post.getId();
        if (id > 0 && postMap.containsKey(id)) {
            if (postMap.get(id).isRemoved()) {
                return null;
            }
            postMap.put(id, post);
        } else {
            id = postID.getAndIncrement();
            post.setId(id);
            postMap.put(id, post);
        }
        return post;
    }

    public void removeById(long id) {
        postMap.get(id).remove();
    }
}
