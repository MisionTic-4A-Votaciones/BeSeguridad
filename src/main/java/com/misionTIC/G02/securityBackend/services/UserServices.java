package com.misionTIC.G02.securityBackend.services;

import com.misionTIC.G02.securityBackend.models.User;
import com.misionTIC.G02.securityBackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServices {
    /**
     * Business logic layer
     */
    @Autowired

    private UserRepository userRepository;

    public List<User> index() {
        /**
         * Method implementation to bring all users
         */
        return (List<User>) this.userRepository.findAll();
    }

    public Optional<User> show(int id) {
        /**
         * Method implementation to bring a user given an id
         */
        return this.userRepository.findById(id);
    }

    public User create(User newUser) {
        /**
         * Method implementation to create a new user
         */
        if (newUser.getId() == null) {
            if (newUser.getEmail() != null && newUser.getNickname() != null && newUser.getPassword() != null)
                return this.userRepository.save(newUser);

            else {
                //TODO return 400 code bad request
                return newUser;
            }
        } else {
            //TODO validate if id exists, if not create
            return newUser;
        }

    }

    public User update (int id,User updatedUser){
        /**
         * Method implementation to update a user given an id
         */
        if (id >0){
            Optional<User>tempUser =this.show(id);
            if (tempUser.isPresent()){
                //email can't be updated
                if (updatedUser.getNickname()!=null)
                    tempUser.get().setNickname(updatedUser.getNickname());

                if (updatedUser.getPassword()!=null)
                    tempUser.get().setPassword(updatedUser.getPassword());

                return this.userRepository.save(tempUser.get());
            }
            else {
                return updatedUser;
            }
        }
        else{
            //TODO return 400 BadRequest, no ID
            return updatedUser;
        }
    }
    public boolean delete(int id){
        /**
         * Method implementation to delete a rol given an id. Use a lambda function
         */
        Boolean success = this.show(id).map(user -> {
            this.userRepository.delete(user);
            return true;
                }
        ).orElse(false);
        return success;
    }
}
