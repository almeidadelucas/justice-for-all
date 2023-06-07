package com.justice.justiceforall.repository;

import org.springframework.data.repository.CrudRepository;

import com.justice.justiceforall.entity.userentity.UserEntity;

public interface UsersRepository extends CrudRepository<UserEntity, Long> {

  UserEntity findFirstsByEmailAndPassword(String email, String password);

}