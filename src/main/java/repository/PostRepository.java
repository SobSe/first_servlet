package repository;

import exception.NotFoundException;
import model.Post;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

// Stub
public class PostRepository {
  private final Map<Long, Post> posts;
  private final AtomicLong counter;

  public PostRepository() {
    posts = new ConcurrentHashMap<>();
    counter = new AtomicLong(0);
  }

  public List<Post> all() {
    return posts.values().stream().toList();
  }

  public Optional<Post> getById(long id) {
    return Optional.of(posts.get(id));
  }

  public Post save(Post post) {
    if (post.getId() == 0) {
      post.setId(counter.incrementAndGet());
      posts.put(post.getId(), post);
      return post;
    } else {
      return update(post).orElseThrow(NotFoundException::new);
    }
  }

  private Optional<Post> update(Post post) {
      Optional<Post> foundPost = Optional.of(posts.get(post.getId()));
      foundPost.ifPresent(value -> value.setContent(post.getContent()));
      return  foundPost;
  }

  public Optional<Post>  removeById(long id) {
    return Optional.of(posts.remove(id));
  }
}
