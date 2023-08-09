package ru.sobse.service;

import ru.sobse.exception.NotFoundException;
import ru.sobse.model.Post;
import org.springframework.stereotype.Service;
import ru.sobse.repository.PostRepository;

import java.util.List;

@Service
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

  public Post removeById(long id) {
    return repository.removeById(id).orElseThrow(NotFoundException::new);
  }
}

