package com.api.templatecms.apitemplatecms.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Master_UserGroup") 
public class MasterUser_Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer idUser;
    private Integer idGroup;

    
    public MasterUser_Group() {}

    public Integer getIdUser() {
        return idUser;
    }

    public void setId(Integer idUser) {
        this.idUser = idUser;
    }
    
    public Integer getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(Integer idGroup) {
        this.idGroup = idGroup;
    }
}