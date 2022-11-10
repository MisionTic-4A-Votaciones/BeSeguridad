package com.misionTIC.G02.securityBackend.repositories;

import com.misionTIC.G02.securityBackend.models.Permission;
import org.springframework.data.repository.CrudRepository;

public interface PermissionRepository extends CrudRepository<Permission, Integer> {
}
