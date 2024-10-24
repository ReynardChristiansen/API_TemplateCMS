package com.api.templatecms.apitemplatecms.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name="Master_Group")
public class MasterGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer id;
    private String nama;
    private LocalDateTime createddate;
    private Integer createdby;
    private Integer sts; 
    private LocalDateTime updateddate;
    private Integer updatedby;

    public MasterGroup() {}

    // Getter and Setter for id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    // Getter and Setter for nama
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    // Getter and Setter for createddate
    public LocalDateTime getCreateddate() {
        return createddate;
    }

    public void setCreateddate(LocalDateTime createddate) {
        this.createddate = createddate;
    }

    // Getter and Setter for createdby
    public Integer getCreatedby() {
        return createdby;
    }

    public void setCreatedby(Integer createdby) {
        this.createdby = createdby;
    }

    // Getter and Setter for sts
    public Integer getSts() {
        return sts;
    }

    public void setSts(Integer sts) {
        this.sts = sts;
    }

    // Getter and Setter for updateddate
    public LocalDateTime getUpdatedate() {
        return updateddate;
    }

    public void setUpdatedate(LocalDateTime updateddate) {
        this.updateddate = updateddate;
    }

    // Getter and Setter for updatedby
    public Integer getUpdateby() {
        return updatedby;
    }

    public void setUpdateby(Integer updatedby) {
        this.updatedby = updatedby;
    }
}

