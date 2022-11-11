package com.misionTIC.G02.securityBackend.controllers;

import com.misionTIC.G02.securityBackend.models.Rol;
import com.misionTIC.G02.securityBackend.services.RolServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rol")
public class RolController {
    @Autowired
    private RolServices rolServices;

    @GetMapping("/all")
    public List<Rol> getAllRoles(){
        return this.rolServices.index();
    }

    @GetMapping("/{id}")
    public Optional<Rol> getById(@PathVariable("id") int id){
        return this.rolServices.show(id);
    }

    @PostMapping("/insert")
    public Rol insertRol(@RequestBody Rol rol){
        return this.rolServices.create(rol);
    }

    @PutMapping("/update/{id}")
    public Rol updateRol(@PathVariable("id") int id, @RequestBody Rol rol){
        return this.rolServices.update(id,rol);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteRol(@PathVariable("id") int id){
    return this.rolServices.delete(id);
    }
}
