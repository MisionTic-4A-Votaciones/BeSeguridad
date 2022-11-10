package com.misionTIC.G02.securityBackend.repositories;

import com.misionTIC.G02.securityBackend.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
}
