package com.misionTIC.G02.securityBackend.controllers;

import com.misionTIC.G02.securityBackend.models.Permission;
import com.misionTIC.G02.securityBackend.models.Rol;
import com.misionTIC.G02.securityBackend.services.RolServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin
@RestController
@RequestMapping("/rol")
public class RolController {
    @Autowired
    private RolServices rolServices;

    @GetMapping("/all")
    public List<Rol> getAllRoles(){
        /**
         * Bring all roles
         */
        return this.rolServices.index();
    }

    @GetMapping("/{id}")
    public Optional<Rol> getById(@PathVariable("id") int id){
        /**
         * Bring a role given the id
         */
        return this.rolServices.show(id);
    }

    @GetMapping("/validate/{idRol}")
    public ResponseEntity<Boolean> getValidation(@PathVariable("idRol") int idRol, @RequestBody Permission permission){
        return this.rolServices.validateGrant(idRol, permission);
    }

    @PostMapping("/insert")
    public Rol insertRol(@RequestBody Rol rol){
        /**
         * Create a new role
         */
        return this.rolServices.create(rol);
    }

    @PutMapping("/update/{id}")
    public Rol updateRol(@PathVariable("id") int id, @RequestBody Rol rol){
        /**
         * Update a role given an id
         */
        return this.rolServices.update(id,rol);
    }
    @PutMapping("/update/{idRol}/add_permission/{idPermission}")
    public ResponseEntity<Rol> updateRolAddPermission(@PathVariable("idRol") int idRol, @PathVariable("idPermission") int idPermission){
        /**
         * to assign permission to a roll
         */
        return this.rolServices.updateAddPermission(idRol,idPermission);
    }


    @DeleteMapping("/delete/{id}")
    public boolean deleteRol(@PathVariable("id") int id){
        /**
         * Delete a role given an id
         */
        return this.rolServices.delete(id);
    }
}
