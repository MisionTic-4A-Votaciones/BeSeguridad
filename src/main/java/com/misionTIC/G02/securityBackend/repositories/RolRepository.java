package com.misionTIC.G02.securityBackend.repositories;

import com.misionTIC.G02.securityBackend.models.Rol;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends CrudRepository<Rol,Integer> {
}
