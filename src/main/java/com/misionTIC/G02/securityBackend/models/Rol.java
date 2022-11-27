package com.misionTIC.G02.securityBackend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "rol")
public class Rol implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRol;
    private String name;
    private String description;

    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "rol")
    @JsonIgnoreProperties("rol")
    private List<User> users;

    @ManyToMany
    @JoinTable(
            name = "permissions_rol",
            joinColumns = @JoinColumn(name = "idRol"),
            inverseJoinColumns = @JoinColumn(name = "idPermission")

    )
    private Set<Permission> permissions;

    public Integer getIdRol() {
        /**
         * Getter for id
         */
        return idRol;
    }

    public void setIdRol(Integer id) {
        /**
         * Setter for id
         */
        this.idRol = id;
    }

    public String getName() {
        /**
         * Getter for name
         */
        return name;
    }

    public void setName(String rol) {
        /**
         * Setter for name
         */
        this.name = rol;
    }

    public String getDescription() {
        /**
         * Getter for description
         */
        return description;
    }

    public void setDescription(String description) {
        /**
         * Setter for description
         */
        this.description = description;
    }

    /**
     * Creation table that break the many-to-many relation
     */
    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }
}
