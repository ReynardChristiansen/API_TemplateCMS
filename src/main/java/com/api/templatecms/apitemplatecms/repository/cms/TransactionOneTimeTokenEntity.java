package com.api.templatecms.apitemplatecms.repository.cms;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="Transaction_OneTimeToken")
public class TransactionOneTimeTokenEntity {
    @Id
    @Column(name="bcafCode")
    String bcafCode;
    @Column(name="oneTimeToken")
    String oneTimeToken;
    @Column(name="sts")
    Integer sts;
    @Column(name="createdDate")
    Date createdDate;
    @Column(name="updatedDate")
    Date updatedDate;
    @Column(name="expiredDate")
    Date expiredDate;
}
