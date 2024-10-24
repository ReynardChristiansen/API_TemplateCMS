package com.api.templatecms.apitemplatecms.repository.cms;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="Master_Users")
public class MasterUsersEntity {
    @Id
    @Column(name="id")
    Long id;
    @Column(name="createddate")
    Date createdDate;
    @Column(name="createdby")
    Integer createdBy;
    @Column(name="updateddate")
    Date updatedDate;
    @Column(name="updatedby")
    Integer updatedBy;
    @Column(name="userLogin")
    String userLogin;
    @Column(name="nama")
    String nama;
    @Column(name="sts")
    Integer sts;
    @Column(name="ssoToken")
    String ssoToken;
    @Column(name="ssoRefreshToken")
    String ssoRefreshToken;
}
