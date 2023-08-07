package repository;

import model.Post;

import java.util.*;

// Stub
public class PostRepository {
  private final List<Post> posts;

  public PostRepository() {
    posts = Collections.synchronizedList(new ArrayList<>());
  }

  public List<Post> all() {
    synchronized (posts) {
      return Collections.unmodifiableList(posts);
    }
  }

  public Optional<Post> getById(long id) {
    synchronized (posts ) {
      return posts.stream()
              .filter(p -> (p.getId() == id))
              .findFirst();
    }
  }

  public Post save(Post post) {
    posts.add(post);
    return post;
  }

  public Optional<Post> update(Post post) {
    synchronized (posts) {
      Optional<Post> foundPost = getById(post.getId());
      foundPost.ifPresent(value -> value.setContent(post.getContent()));
      return  foundPost;
    }
  }

  public boolean removeById(long id) {
    return posts.remove(new Post(id, ""));
  }
}
