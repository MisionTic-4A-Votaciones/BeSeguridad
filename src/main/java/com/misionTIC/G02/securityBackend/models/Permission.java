package com.misionTIC.G02.securityBackend.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "permission")

public class Permission implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPermission;
    private String url;
    private String method;

    @ManyToMany(mappedBy = "permissions")
    private Set<Rol>roles;

    public Integer getId() {
        /**
         * Getter
         */
        return idPermission;
    }

    public void setId(Integer id) {
        /**
         * Setter
         */
        this.idPermission = id;
    }

    public String getUrl() {
        /**
         * Getter
         */return url;
    }

    public void setUrl(String url) {
        /**
         * Setter
         */
        this.url = url;
    }

    public String getMethod() {
        /**
         * Getter
         */
        return method;
    }

    public void setMethod(String method) {
        /**
         * Setter
         */
        this.method = method;
    }
}
