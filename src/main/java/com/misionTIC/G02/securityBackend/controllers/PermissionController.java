package com.misionTIC.G02.securityBackend.controllers;

import com.misionTIC.G02.securityBackend.models.Permission;
import com.misionTIC.G02.securityBackend.services.PermissionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin
@RestController
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private PermissionServices permissionServices;

    @GetMapping("/all")
    public List<Permission> getAllPermissions(){
        /**
         * Bring all permissions
         */
        return this.permissionServices.index();
    }

    @GetMapping("/{id}")
    public Optional<Permission> getById(@PathVariable("id")int id){
        /**
         * Bring a permission given the id
         */
        return this.permissionServices.show(id);
    }

    @PostMapping("/insert")
    @ResponseStatus(HttpStatus.CREATED)
    public Permission insertPermission(@RequestBody Permission permission){
        /**
         * Create a new permission
         */
        return  this.permissionServices.create(permission);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Permission updatePermission(@PathVariable("id")int id, @RequestBody Permission permission){
        /**
         * Update an existing permission given an id
         */
        return this.permissionServices.update(id,permission);

    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean deletePermission(@PathVariable("id")int id){
        /**
         * Delete a permission given the id
         */
        return this.permissionServices.delete(id);
    }
}
