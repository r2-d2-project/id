package io.github.r2d2project.id.dao;

public interface IdDao {
  Long generate();

  Long parse(Long id);
}
