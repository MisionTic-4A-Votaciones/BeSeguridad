package com.misionTIC.G02.securityBackend.services;

import com.misionTIC.G02.securityBackend.models.Permission;
import com.misionTIC.G02.securityBackend.repositories.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PermissionServices {
    /**
     * Business logic layer
     */
    @Autowired
    private PermissionRepository permissionRepository;

    public List<Permission>index(){
        /**
         * Method implementation to bring all permissions
         */
        return (List<Permission>) this.permissionRepository.findAll();
    }

    public Optional<Permission>show(int id){
        /**
         * Method implementation to bring a permission given an id
         */
        return this.permissionRepository.findById(id);
    }

    public Permission create(Permission newPermission) {
        /**
         * Method implementation to create a new permission
         */
        if (newPermission.getId() == null) {
            if (newPermission.getUrl() != null && newPermission.getMethod() != null)
                return this.permissionRepository.save(newPermission);

            else {
                //TODO return 400 code bad request
                return newPermission;
            }
        } else {
            //TODO validate if id exists, if not create
            return newPermission;
        }
    }

    public Permission update (int id,Permission updatedPermission){
        /**
         * Method implementation to update a permission given an id
         */
        if (id >0){
            Optional<Permission>tempPermission =this.show(id);
            if (tempPermission.isPresent()){
                if (updatedPermission.getUrl()!=null)
                    tempPermission.get().setUrl(updatedPermission.getUrl());
                return this.permissionRepository.save(tempPermission.get());
            }
            else {
                return updatedPermission;
            }
        }
        else{
            //TODO return 400 BadRequest, no ID
            return updatedPermission;
        }
    }

    public boolean delete(int id){
        /**
         * Method implementation to delete a permission  given an id
         */
        boolean success = this.show(id).map(permission -> {
            this.permissionRepository.delete(permission);
            return true;
        }).orElse(false);
        return success;
    }
}
