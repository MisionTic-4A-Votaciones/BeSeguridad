package com.misionTIC.G02.securityBackend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUser;
    private  String nickname;
    private String email;
    private  String password;

    /**
     * Relation tables User-Rol
     */
    @ManyToOne
    @JoinColumn(name="idRol")
    @JsonIgnoreProperties("users")
    private Rol rol;

    public Integer getId() {
        /**
         * Getter
         */
        return idUser;
    }

    public void setId(Integer id) {
        /**
         * Setter
         */
        this.idUser = id;
    }

    public String getNickname() {
        /**
         * Getter
         */
        return nickname;
    }

    public void setNickname(String nickname) {
        /**
         * Setter
         */
        this.nickname = nickname;
    }

    public String getEmail() {
        /**
         * Getter
         */
        return email;
    }

    public void setEmail(String email) {
        /**
         * Setter
         */
        this.email = email;
    }

    public String getPassword() {
        /**
         * Getter
         */
        return password;
    }

    public void setPassword(String password) {
        /**
         * Setter
         */
        this.password = password;
    }
}
