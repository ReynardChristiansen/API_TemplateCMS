package com.api.templatecms.apitemplatecms.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Master_Users") 
public class MasterUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    private LocalDateTime createddate;
    private String createdby;
    private LocalDateTime updateddate;
    private Integer updatedby;
    private String userLogin;
    private String userPass;
    private String nama;
    private Integer isLDAP; 
    private Integer sts; 
    private Integer countFailedLogin; 
    private Integer isLogin;
    private LocalDateTime lastActive;

    
    public MasterUser() {}

    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreateddate() {
        return createddate;
    }

    public void setCreateddate(LocalDateTime createddate) {
        this.createddate = createddate;
    }

    public void setUpdateddate(LocalDateTime updateddate) {
        this.updateddate = updateddate;
    }

    public LocalDateTime getUpdateddate() {
        return updateddate;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public Integer getUpdatedby() {
        return updatedby;
    }

    public void setUpdatedby(int updatedby) {
        this.updatedby = updatedby;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Integer isLDAP() {
        return isLDAP;
    }

    public void setLDAP(Integer isLDAP) {
        this.isLDAP = isLDAP;
    }

    public Integer getSts() {
        return sts;
    }

    public void setSts(Integer sts) {
        this.sts = sts;
    }

    public int getCountFailedLogin() {
        return countFailedLogin;
    }

    public void setCountFailedLogin(int countFailedLogin) {
        this.countFailedLogin = countFailedLogin;
    }

    public Integer isLogin() {
        return isLogin;
    }

    public void setLogin(Integer isLogin) {
        this.isLogin = isLogin;
    }

    public LocalDateTime getLastActive() {
        return lastActive;
    }

    public void setLastActive(LocalDateTime lastActive) {
        this.lastActive = lastActive;
    }
}
