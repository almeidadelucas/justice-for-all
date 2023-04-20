package com.justice.justiceforall.repository;

import com.justice.justiceforall.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<UserEntity, Long> {

}
