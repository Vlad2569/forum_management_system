package com.a44.group10.forum_management_system.repositories;

import com.a44.group10.forum_management_system.exceptions.EntityNotFoundException;
import com.a44.group10.forum_management_system.models.Post;
import com.a44.group10.forum_management_system.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
@Repository
public class PostRepositoryImpl implements PostRepository {

    private final UserRepository userRepository;
    private final List<Post> posts;
    @Autowired
    public PostRepositoryImpl(UserRepository userRepository) {
        List<User> users = userRepository.getAll();
        this.userRepository = userRepository;
        posts = new ArrayList<>();
        posts.add(new Post(1, "My Very First Post",
                "The First Content of My Very First Post", users.get(0)));
        posts.add(new Post(2, "My Merry Second Post",
                "The Second Content of My Merry Second Post", users.get(1)));
        posts.add(new Post(3, "My Twirly Third Post",
                "The Third Content Of My Third Post.", users.get(2)));
    }

    @Override
    public List<Post> getAll(){
        return new ArrayList<>(posts);
    }

    @Override
    public Post getById(int id){
        return posts.stream()
                .filter(post -> post.getId() == id)
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Post", id));
    }

    @Override
    public void create(Post post){
        posts.add(post);
    }

    @Override
    public void update(Post post){
        Post postToUpdate = getById(post.getId());
        postToUpdate.setTitle(post.getTitle());
        postToUpdate.setContent(post.getContent());
    }

    @Override
    public void delete(int id){
        Post postToDelete = getById(id);
        posts.remove(postToDelete);
    }
}
