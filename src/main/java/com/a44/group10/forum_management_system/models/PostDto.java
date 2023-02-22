package com.a44.group10.forum_management_system.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class PostDto {
    @Positive(message = "Beer id should be positive")
    private int id;
    @NotNull(message = "Post cannot be null")
    @NotBlank(message = "Post cannot be blank")
    @Size(min = 16, max = 64, message = "Post title should be between 16 and 64 symbols.")
    private String title;
    @NotNull(message = "Content cannot be null")
    @NotBlank(message = "Content cannot be blank")
    @Size(min = 32, max = 8192, message = "Post content should be between 16 and 64 symbols.")
    private String content;
    @Positive(message = "User id should be positive")
    private int userId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
