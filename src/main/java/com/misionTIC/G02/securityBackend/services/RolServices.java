package com.misionTIC.G02.securityBackend.services;

import com.misionTIC.G02.securityBackend.models.Permission;
import com.misionTIC.G02.securityBackend.models.Rol;
import com.misionTIC.G02.securityBackend.repositories.PermissionRepository;
import com.misionTIC.G02.securityBackend.repositories.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RolServices {
    /**
     * Business logic layer
     */
    @Autowired
    private RolRepository rolRepository;
    @Autowired
    private PermissionRepository permissionRepository;

    public List<Rol>index(){
        /**
         * Method implementation to bring all roles
         */
        return (List<Rol>) this.rolRepository.findAll();
    }

    public Optional<Rol>show(int id){
        /**
         * Method implementation to bring a rol given an id
         */
        return this.rolRepository.findById(id);
    }

    public Rol create(Rol newRol){
        /**
         * Method implementation to create a new rol
         */
        if(newRol.getIdRol() == null){
            if (newRol.getName()!=null)
                return this.rolRepository.save(newRol);
            else {
                //TODO return 400 code bad request
                return newRol;
            }
        }
        else {
            //TODO validate if id exists, if not create
            return newRol;
        }
    }

    public Rol update(int id, Rol updatedRol){
        /**
         * Method implementation to update a rol given an id
         */
        if(id>0){
            Optional<Rol> tempRol = this.show(id);
            if(tempRol.isPresent()){
                if (updatedRol.getName()!= null)
                    tempRol.get().setName(updatedRol.getName());
                if(updatedRol.getDescription()!= null)
                    tempRol.get().setDescription(updatedRol.getDescription());
                return this.rolRepository.save(tempRol.get());
            }
            else {
                return updatedRol;
            }
        }
        else {
                //TODO return 400 BadRequest, no ID
                return updatedRol;
             }
    }

    public boolean delete(int id){
        /**
         * Method implementation to delete a rol given an id
         */
        Boolean success = this.show(id).map(rol -> {
            this.rolRepository.delete(rol);
            return true;
        }).orElse(false);
        return success;
    }

    public ResponseEntity<Rol> updateAddPermission(int idRol, int idPermission){
        /**
         * Method to add permissions to a rol
         */
        Optional<Rol>rol = this.rolRepository.findById(idRol);
        if(rol.isPresent()){ //validated if id exist
            Optional<Permission>permission = permissionRepository.findById(idPermission);
            if (permission.isPresent()){ //validate if the permission have been already assigned
                Set<Permission> tempPermissions = rol.get().getPermissions();
                if(tempPermissions.contains(permission))
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Permission already assigned");
                else {
                    tempPermissions.add(permission.get());
                    rol.get().setPermissions(tempPermissions);
                    return new ResponseEntity<>(this.rolRepository.save(rol.get()),HttpStatus.CREATED);
                }

            }
            else
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The provided idPermission is not  match for any database register");
        }
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The provided idRol is not  match for any database register");
    }

    public ResponseEntity<Boolean>validateGrant(int idRol, Permission permission){
        /**
         * Methods to validate
         */
        Boolean isGrant=false;
        Optional<Rol>rol = this.rolRepository.findById(idRol);
        if (rol.isPresent()){
            for(Permission rolPermission:rol.get().getPermissions()){
                if(rolPermission.getUrl().equals(permission.getUrl())&&
                rolPermission.getMethod().equals(permission.getMethod())){
                    isGrant=true;
                    break;
                }
            }
            if (isGrant)
                return new ResponseEntity<>(true,HttpStatus.OK);
            else
                return new ResponseEntity<>(false,HttpStatus.UNAUTHORIZED);
        }
        else
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "The provided rol doesn't exist on the database");
    }
}
