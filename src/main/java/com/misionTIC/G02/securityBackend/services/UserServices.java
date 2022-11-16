package com.misionTIC.G02.securityBackend.services;

import com.misionTIC.G02.securityBackend.models.User;
import com.misionTIC.G02.securityBackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
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

    public Optional<User> showByNickname(String nickname){
        /**
         *Method to show a user given a nickname
         */
        return this.userRepository.findByNickname(nickname);
    }

    public Optional<User> showByEmail(String email){
        /**
         * Method to show a user given an email
         */
        return this.userRepository.findByEmail(email);
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
            if (newUser.getEmail() != null && newUser.getNickname() != null && newUser.getPassword() != null) {
                newUser.setPassword(this.convertToSHA256(newUser.getPassword()));
                return this.userRepository.save(newUser);

            }else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Mandatory fields have not been send");
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
                    tempUser.get().setPassword(this.convertToSHA256(updatedUser.getPassword()));

                return this.userRepository.save(tempUser.get());
            }
            else {
                return updatedUser;
            }
        }
        else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Id requested");
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

    public User login(User user){
        /**
         * Login function
         */
        User result = null;
        if(user.getPassword() != null && user.getEmail() != null) {
            String email = user.getEmail();
            String password = this.convertToSHA256(user.getPassword());
            Optional<User> tempUser = this.userRepository.login(email, password);
            //TODO improve

            if (tempUser.isEmpty())
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid access");
            else
                result = tempUser.get();
        }
        else
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Mandatory fields have not been send");

        return result;
    }
    public String convertToSHA256(String password){
        /**
         * Function to encrypt password
         */
        MessageDigest md = null;
        try{
            md = MessageDigest.getInstance("SHA-256");
        }
        catch (NoSuchAlgorithmException e){
            e.printStackTrace();
            return null;
        }
        byte[] hash = md.digest(password.getBytes());
        StringBuffer sb = new StringBuffer();
        for(byte b : hash){
            sb.append(String.format("%02X", b));
        }

        return sb.toString();
    }
}
