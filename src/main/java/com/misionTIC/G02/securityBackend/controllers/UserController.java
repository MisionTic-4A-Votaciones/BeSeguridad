package com.misionTIC.G02.securityBackend.controllers;

import com.misionTIC.G02.securityBackend.models.Rol;
import com.misionTIC.G02.securityBackend.models.User;
import com.misionTIC.G02.securityBackend.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
@CrossOrigin
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

    @GetMapping("/by_id/{id}")
    public Optional<User>getUserById(@PathVariable("id") int id){
        /**
         * Bring a user given an id
         */
        return  this.userServices.show(id);
    }

    @GetMapping("/by_nickname/{nickname}")
    public Optional<User> getUserByNickname(@PathVariable("nickname")String nickname){
        return this.userServices.showByNickname(nickname);
    }

    @GetMapping("/by_email/{email}")
    public Optional<User> getUserByEmail(@PathVariable("email") String email){
        return this.userServices.showByEmail(email);
    }


    @PostMapping("/login")
    public User loginUser(@RequestBody User user){
        /**
         * User log in
         */
        return this.userServices.login(user);
    }

    @PostMapping("/insert")
    public User insertUser(@RequestBody User user){

        /**
         * Create a new user
         */return this.userServices.create(user);
    }

    @PutMapping("/update/{id}")
    public User updateUser (@PathVariable("id") int id,@RequestBody User user){
        /**
         * Update an user given an id
         */
        return  this.userServices.update(id,user);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteUser (@PathVariable("id") int id){
        /**
         * Delete an user given an id
         */return this.userServices.delete(id);
    }

}
