package com.misionTIC.G02.securityBackend.controllers;

import com.misionTIC.G02.securityBackend.models.User;
import com.misionTIC.G02.securityBackend.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServices userServices;
    @GetMapping("/all")
    public List<User>getAllUsers(){
        /**
         * Bring all users
         */
        return this.userServices.index();
    }

    @GetMapping("/{id}")
    public Optional<User>getUserById(@PathVariable("id") int id){
        /**
         * Bring a user given an id
         */
        return  this.userServices.show(id);
    }

    @PostMapping("/insert")
    @ResponseStatus(HttpStatus.CREATED)
    public User insertUser(@RequestBody User user){

        /**
         * Create a new user
         */return this.userServices.create(user);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public User updateUser (@PathVariable("id") int id,@RequestBody User user){
        /**
         * Update an user given an id
         */
        return  this.userServices.update(id,user);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean deleteUser (@PathVariable("id") int id){
        /**
         * Delete an user given an id
         */return this.userServices.delete(id);
    }

}
