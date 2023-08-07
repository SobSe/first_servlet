package service;

import exception.NotFoundException;
import model.Post;
import repository.PostRepository;

import java.util.List;

public class PostService {
  private final PostRepository repository;

  public PostService(PostRepository repository) {
    this.repository = repository;
  }

  public List<Post> all() {
    return repository.all();
  }

  public Post getById(long id) {
    return repository.getById(id).orElseThrow(NotFoundException::new);
  }

  public Post save(Post post) {
    if (post.getId() == 0) {
      Counter counter = Counter.getCounter();
      post.setId(counter.increment());
      return repository.save(post);
    } else {
      return repository.update(post).orElseThrow(NotFoundException::new );
    }

  }

  public boolean removeById(long id) {
    return repository.removeById(id);
  }
}

