package com.misionTIC.G02.securityBackend.services;

import com.misionTIC.G02.securityBackend.models.User;
import com.misionTIC.G02.securityBackend.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServices {

    private UserRepository userRepository;

    public List<User> index() {
        return (List<User>) this.userRepository.findAll();
    }

    public Optional<User> show(int id) {
        return this.userRepository.findById(id);
    }

    public User create(User newUser) {
        if (newUser.getId() == null) {
            if (newUser.getEmail() != null && newUser.getNickname() != null && newUser.getPassword() != null)
                return this.userRepository.save(newUser);

            else {
                return newUser;
            }
        } else {
            return newUser;
        }

    }

    public User update (int id,User updatedUser){
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
            return updatedUser;
        }
    }
    public boolean delete(int id){
        Boolean success =this.show(id).map(user -> {
            this.userRepository.delete(user);
            return true;
                }
        ).orElse(false);
        return success;
    }
}
