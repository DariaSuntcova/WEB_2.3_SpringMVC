package ru.netology.service;

public class PostDTO {
    private long id;
    private String content;

    public PostDTO(long id, String content) {
        this.id = id;
        this.content = content;
    }
}
