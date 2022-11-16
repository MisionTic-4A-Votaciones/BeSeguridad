package com.misionTIC.G02.securityBackend.controllers;

import com.misionTIC.G02.securityBackend.models.Rol;
import com.misionTIC.G02.securityBackend.services.RolServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @PostMapping("/insert")
    @ResponseStatus(HttpStatus.CREATED)
    public Rol insertRol(@RequestBody Rol rol){
        /**
         * Create a new role
         */
        return this.rolServices.create(rol);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Rol updateRol(@PathVariable("id") int id, @RequestBody Rol rol){
        /**
         * Update a role given an id
         */
        return this.rolServices.update(id,rol);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean deleteRol(@PathVariable("id") int id){
        /**
         * Delete a role given an id
         */
        return this.rolServices.delete(id);
    }
}
