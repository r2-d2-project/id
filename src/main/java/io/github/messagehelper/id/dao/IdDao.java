package io.github.messagehelper.id.dao;

public interface IdDao {
  Long generate();

  Long parse(Long id);
}
