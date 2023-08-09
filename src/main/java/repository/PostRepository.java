package repository;

import model.Post;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

// Stub
public class PostRepository {
  private final Map<Long, Post> posts;

  public PostRepository() {
    posts = new ConcurrentHashMap<>();
  }

  public List<Post> all() {
    return posts.values().stream().toList();
  }

  public Optional<Post> getById(long id) {
    return Optional.of(posts.get(id));
  }

  public Post save(Post post) {
    posts.put(post.getId(), post);
    return post;
  }

  public Optional<Post> update(Post post) {
      Optional<Post> foundPost = Optional.of(posts.get(post.getId()));
      foundPost.ifPresent(value -> value.setContent(post.getContent()));
      return  foundPost;
  }

  public Optional<Post>  removeById(long id) {
    return Optional.of(posts.remove(id));
  }
}
