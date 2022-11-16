package com.misionTIC.G02.securityBackend.services;

import com.misionTIC.G02.securityBackend.models.Rol;
import com.misionTIC.G02.securityBackend.repositories.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolServices {
    /**
     * Business logic layer
     */
    @Autowired
    private RolRepository rolRepository;

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
}
