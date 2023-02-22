package com.a44.group10.forum_management_system.helpers;

import com.a44.group10.forum_management_system.models.Post;
import com.a44.group10.forum_management_system.models.PostDto;
import com.a44.group10.forum_management_system.services.UserService;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {

    private final UserService userService;
    //Here we can take data directly from the UserRepository just as well.

    public PostMapper(UserService userService) {
        this.userService = userService;
    }

    public Post dtoToObject(PostDto postDto) {
        Post post = new Post();
        post.setId(postDto.getId());
        return dtoToObject(post, postDto);
    }

    private Post dtoToObject(Post post, PostDto postDto) {
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setUser(userService.getById(postDto.getUserId()));
        return post;
    }
}
